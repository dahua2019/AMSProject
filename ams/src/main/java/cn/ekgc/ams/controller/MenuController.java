package cn.ekgc.ams.controller;

import cn.ekgc.ams.controller.base.BaseController;
import cn.ekgc.ams.pojo.vo.Node;
import cn.ekgc.ams.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <b>菜单功能控制器</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller("menuController")
@RequestMapping("/menu")
public class MenuController extends BaseController {

	@Autowired
	private MenuService menuService;

	/**
	 * <b>为授权页面查询节点集合</b>
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/auth")
	@ResponseBody
	public List<Node> getNodeListForAuth(Long roleId) throws Exception {
		return menuService.getNodeListForAuth(roleId);
	}
}
