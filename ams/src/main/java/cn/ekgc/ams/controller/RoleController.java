package cn.ekgc.ams.controller;

import cn.ekgc.ams.controller.base.BaseController;
import cn.ekgc.ams.controller.base.enums.StatusEnum;
import cn.ekgc.ams.pojo.entity.Role;
import cn.ekgc.ams.pojo.vo.Page;
import cn.ekgc.ams.service.MenuService;
import cn.ekgc.ams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <b>角色信息控制器</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller("roleController")
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private  MenuService menuService;

	/**
	 * <b>转发到角色首页</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/index")
	public String forwardIndexPage() throws Exception {
		return "role/role_index";
	}

	/**
	 * <b>分页查询角色信息列表</b>
	 * @param pageNo
	 * @param pageSize
	 * @param draw
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/page")
	@ResponseBody
	public Page<Role> getUserListByPage(Integer pageNo, Integer pageSize, Integer draw) throws Exception {
		//创建 Page 对象
		Page<Role> page = new Page<Role>(pageNo,  pageSize,  draw);
		//根据分页信息查询分页角色列表
		page = roleService.getRoleListByPage(page);
		return page;
	}

	/**
	 * <b>转发到角色授权首页</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/auth")
	public String forwardRolePower(Long id, ModelMap map) throws Exception {
		map.put("roleId", id);
		return "role/role_auth";
	}

	/**
	 * <b>给角色授权</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/auth")
	@ResponseBody
	public boolean grantRoleAuth(Long roleId, String ids) throws Exception {
		if (ids != null) {
			//判断授权是否成功
			boolean flag = roleService.grantRoleAuth(roleId, ids);
			return true;
		}
		return false;
	}

	/**
	 * <b>转发到添加角色页面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/add")
	public String forwardAddRolePage() throws Exception {
		return "/role/role_add";
	}

	/**
	 * <b>添加角色</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/add")
	@ResponseBody
	public boolean addRole(Role role) throws Exception {
		role.setStatus(StatusEnum.STATUS_ENABLE.getCode());
		return roleService.saveRole(role);
	}

	/**
	 * <b>获得角色列表页面</b>
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	@ResponseBody
	public List<Role> getRoleList(ModelMap map) throws Exception {
		//获得启用的角色列表
		Role query = new Role();
		query.setStatus(StatusEnum.STATUS_ENABLE.getCode());
		return roleService.getRoleListByQuery(query);
	}

	/**
	 * <b>更新角色信息</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/update")
	@ResponseBody
	public boolean updateRole(Role role)  throws Exception {
		return roleService.updateRole(role);
	}

	/**
	 * <b>转到修改角色信息页面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/updateRole")
	public String forwardUpdateRolePage(Long id, ModelMap map) throws Exception {
		Role role = new Role();
		role.setId(id);
		role = roleService.getRoleListByQuery(role).get(0);
		map.put("id", role.getId());
		map.put("name", role.getName());
		map.put("code", role.getCode());
		return "/role/role_update";
	}
}
