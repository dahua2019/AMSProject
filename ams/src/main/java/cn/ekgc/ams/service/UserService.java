package cn.ekgc.ams.service;

import cn.ekgc.ams.pojo.entity.User;
import cn.ekgc.ams.pojo.vo.Page;

import java.util.List;

/**
 * <b>用户模块服务层接口</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
public interface UserService {


	/**
	 * <b>根据手机号获得用户对象</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	User getUserByCellphone(String cellphone) throws Exception;

	/**
	 * <b>根据分页信息查询分页用户列表</b>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	Page<User> getUserListByPage(Page<User> page) throws Exception;

	/**
	 * <b>更新用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean updateUser(User user) throws Exception;

	/**
	 * <b>保存用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean saveUser(User user) throws Exception;



	/**
	 * <b>判断密码是否正确</b>
	 * @param password
	 * @return
	 * @throws Exception
	 */
	boolean getUserByPassword(String password) throws Exception;

	/**
	 * <b>保存用户列表信息</b>
	 * @param userList
	 * @return
	 * @throws Exception
	 */
	boolean saveUserList(List<User> userList) throws Exception;

	/**
	 * <b>获得所有的用户列表</b>
	 * @return
	 * @throws Exception
	 */
	List<User> getUserList() throws Exception;


	boolean getUserByUser(User user) throws Exception;
}
