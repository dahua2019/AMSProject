package cn.ekgc.ams.service;

import cn.ekgc.ams.pojo.entity.Role;
import cn.ekgc.ams.pojo.entity.User;
import cn.ekgc.ams.pojo.vo.Page;

import java.util.List;

/**
 * <b>角色模块服务层接口</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
public interface RoleService {
	/**
	 * <b>查询角色信息列表</b>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	Page<Role> getRoleListByPage(Page<Role> page) throws Exception;

	/**
	 * <b>给角色授权</b>
	 * @param roleId
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	boolean grantRoleAuth(Long roleId, String ids) throws Exception;

	/**
	 * <b>查询角色列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Role> getRoleListByQuery(Role query) throws Exception;

	/**
	 * <b>添加角色信息</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	boolean saveRole(Role role) throws Exception;

	/**
	 * <b>更新角色信息</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	boolean updateRole(Role role) throws Exception;
}
