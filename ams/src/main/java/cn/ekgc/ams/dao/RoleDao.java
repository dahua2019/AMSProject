package cn.ekgc.ams.dao;

import cn.ekgc.ams.pojo.entity.Role;
import cn.ekgc.ams.pojo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>用户模块数据持久层接口</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface RoleDao {

	/**
	 * <b>查询角色信息列表</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	List<Role> findListByQuery(Role role) throws Exception;

	/**
	 * <b>保存角色信息</b>
	 * @return
	 * @throws Exception
	 */
	int save(Role role) throws Exception;

	/**
	 * <b>修改角色信息</b>
	 * @return
	 * @throws Exception
	 */
	int update(Role role) throws Exception;

	int delRoleMenuByRoleId(Long roleId) throws Exception;

	int addRoleAndMenu(Map<String, Object> map) throws Exception;
}
