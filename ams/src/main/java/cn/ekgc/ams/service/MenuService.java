package cn.ekgc.ams.service;

import cn.ekgc.ams.pojo.entity.Menu;
import cn.ekgc.ams.pojo.entity.Role;
import cn.ekgc.ams.pojo.vo.Node;

import java.util.List;

/**
 * <b>菜单模块服务层接口</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
public interface MenuService {

	/**
	 * <b>根据角色信息获得对应的菜单列表</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	List<Menu> getMenuListByRole(Role role) throws Exception;

	/**
	 * <b>为授权页面查询节点集合</b>
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<Node> getNodeListForAuth(Long roleId) throws Exception;



}
