package cn.ekgc.ams.service.impl;

import cn.ekgc.ams.dao.MenuDao;
import cn.ekgc.ams.pojo.entity.Menu;
import cn.ekgc.ams.pojo.entity.Role;
import cn.ekgc.ams.pojo.vo.Node;
import cn.ekgc.ams.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>菜单模块服务层接口实现类</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	/**
	 * <b>根据角色信息获得对应的菜单列表</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<Menu> getMenuListByRole(Role role) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		//根据角色查询所有的一级菜单
		queryMap.put("parentId", null);
		queryMap.put("roleId", role.getId());
		queryMap.put("status", role.getStatus());
		List<Menu> menuList = menuDao.findMenuListByMap(queryMap);
		if (menuList != null && menuList.size() > 0) {
			//循环遍历 menuList 得到一级菜单
			for (Menu firstMenu : menuList) {
				//根据一级菜单和角色查询对应的子菜单
				queryMap.put("parentId", firstMenu.getId());
				List<Menu> childList = menuDao.findMenuListByMap(queryMap);
				firstMenu.setChildList(childList);

			}
		}
		return menuList;
	}

	/**
	 * <b>为授权页面查询节点集合</b>
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<Node> getNodeListForAuth(Long roleId) throws Exception {
		List<Node> nodeList = new ArrayList<Node>();
		//查询所有的菜单
		List<Menu> menuList = menuDao.findListByQuery(null);
		//查询角色所拥有的菜单
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("roleId", roleId);
		List<Menu> roleMenuList = menuDao.findListByQuery(query);
		if (menuList != null && menuList.size() > 0) {
			for (Menu menu : menuList) {
				//创建 Node 对象
				Node node = new Node();
				//设置 id
				node.setId(menu.getId());
				//设置 pId
				node.setpId(menu.getParent() == null ? 0 : menu.getParent().getId());
				//设置 name
				node.setName(menu.getText());
				//设置是否打开
				if (menu.getParent() == null) {
					node.setOpen(true);
				}
				//设置是否勾选
				if (roleMenuList.contains(menu)) {
					node.setChecked(true);
				}
				nodeList.add(node);
			}
		}
		return nodeList;
	}
}
