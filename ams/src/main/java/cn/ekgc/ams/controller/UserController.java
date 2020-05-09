package cn.ekgc.ams.controller;

import cn.ekgc.ams.controller.base.BaseController;
import cn.ekgc.ams.controller.base.enums.StatusEnum;
import cn.ekgc.ams.pojo.entity.Role;
import cn.ekgc.ams.pojo.entity.User;
import cn.ekgc.ams.pojo.vo.Page;
import cn.ekgc.ams.service.RoleService;
import cn.ekgc.ams.service.UserService;
import cn.ekgc.ams.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import java.util.List;

/**
 * <b>用户信息控制器</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;


	/**
	 * <b>转发到登录页面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/login")
	public String forwardLoginPage() throws Exception {
		return "user/user_login";
	}

	/**
	 * <b>登陆失败后的访问地址</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/login")
	public String forwardLogout() throws Exception {
		//只需要重定向到退出即可
		return "redirect:/user/logout";
	}



	/**
	 * <b>转发到用户首页</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/index")
	public String forwardIndexPage() throws Exception {
		return "user/user_index";
	}

	/**
	 * <b>分页查询用户信息列表</b>
	 * @param pageNo
	 * @param pageSize
	 * @param draw
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/page")
	@ResponseBody
	public Page<User> getUserListByPage(Integer pageNo, Integer pageSize, Integer draw) throws Exception {
		//创建 Page 对象
		Page<User> page = new Page<User>(pageNo,  pageSize,  draw);
		//根据分页信息查询分页用户列表
		page = userService.getUserListByPage(page);
		return page;
	}

	/**
	 * <b>转发到用户修改密码页面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/update")
	public String forwardUpdatePage() throws Exception {
		return "user/user_update";
	}

	/**
	 * <b>转发到修改用户密码页面</b>
	 * @param cellphone
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/updatePwd")
	public String forwardUpdateModel(String cellphone, ModelMap map) throws Exception {
		map.put("cellphone", cellphone);
		return "user/user_update";
	}

	@PostMapping(value = "/updateStatus")
	@ResponseBody
	public boolean updateStatus(User user) throws Exception {
		return userService.updateUser(user);
	}

	/**
	 * <b>更新用户信息</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/update")
	@ResponseBody
	public boolean updateUser(String oldPassWord, String password, String cellphone) throws Exception {
		if (cellphone == null) {
			User user = (User) session.getAttribute("user");
			cellphone = user.getCellphone();
		}
		if (oldPassWord != null && !"".equals(oldPassWord.trim())
			&& password !=null && !"".equals(password)) {
			//对旧密码进行加密
			oldPassWord = MD5Util.encrypt(oldPassWord);
			//根据手机号查询用户信息
			User user = userService.getUserByCellphone(cellphone);
			if (user != null) {
				if (user.getPassword().equals(oldPassWord)){
					//对新密码进行加密
					password = MD5Util.encrypt(password);
					//封装 User 对象
					user.setPassword(password);
					System.out.println(user.getPassword());
					return userService.updateUser(user);
				}
			}
		}
		return true;
	}

	/**
	 * <b>转发到添加用户页面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/add")
	public String forwardAddUserPage(ModelMap map) throws Exception {
		return "/user/user_add";
	}

	/**
	 * <b>添加用户</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/add")
	@ResponseBody
	public boolean addUser(User user, Long roleId) throws Exception {
		System.out.println(user.getGender() + "roleId" + roleId);
		//校验用户信息
		if (user.getCellphone() != null && !"".equals(user.getCellphone())) {
			User userOld = userService.getUserByCellphone(user.getCellphone());
			if (userOld == null) {
				if (roleId != null && roleId != 0) {
					//设定默认密码为：123456
					user.setPassword(MD5Util.encrypt("123456"));
					//设定状态为启用状态
					user.setStatus(StatusEnum.STATUS_ENABLE.getCode());
					//谁顶角色信息
					Role role = new Role();
					role.setId(roleId);
					user.setRole(role);
					return userService.saveUser(user);
				}
			}
		}
		return false;
	}
}
