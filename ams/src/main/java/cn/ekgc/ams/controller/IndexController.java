package cn.ekgc.ams.controller;

import cn.ekgc.ams.controller.base.BaseController;
import cn.ekgc.ams.pojo.entity.Menu;
import cn.ekgc.ams.pojo.entity.Role;
import cn.ekgc.ams.pojo.entity.User;
import cn.ekgc.ams.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <b>首页控制器</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller("indexController")
public class IndexController extends BaseController {

	@Autowired
	private MenuService menuService;

	/**
	 * <b>加载首页面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/")
	public String index(ModelMap map) throws Exception {
		//获得当前的用户对象
		User user = (User) session.getAttribute("user");
		//获得当前登录用户的角色信息
		Role role = user.getRole();
		//根据角色信息获取该角色对应的菜单列表
		List<Menu> menuList = menuService.getMenuListByRole(role);
		//将查询的菜单列表转发到首页面
		map.put("menuList", menuList);
		return "index";
	}
}
