package cn.ekgc.ams.dao;

import cn.ekgc.ams.pojo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>用户模块数据持久层接口</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface UserDao {
	/**
	 * <b>根据查询条件查询用户信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<User> findListByQuery(User query) throws Exception;

	/**
	 * <b>保存用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int save(User user) throws Exception;

	/**
	 * <b>更新用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int update(User user) throws Exception;

	/**
	 * <b>保存用户列表信息</b>
	 * @param userList
	 * @return
	 * @throws Exception
	 */
	int saveUserList(List<User> userList) throws Exception;
}
