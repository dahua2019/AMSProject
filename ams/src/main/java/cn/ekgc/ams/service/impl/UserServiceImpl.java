package cn.ekgc.ams.service.impl;

import cn.ekgc.ams.dao.UserDao;
import cn.ekgc.ams.pojo.entity.Role;
import cn.ekgc.ams.pojo.entity.User;
import cn.ekgc.ams.pojo.vo.Page;
import cn.ekgc.ams.service.UserService;
import cn.ekgc.ams.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>用户模块服务层接口实现类</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	/**
	 * <b>根据手机号获得用户对象</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	public User getUserByCellphone(String cellphone) throws Exception {
		//封装查询对象
		User query = new User();
		query.setCellphone(cellphone);
		//通过查询对象查询用户列表
		List<User> userList = userDao.findListByQuery(query);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	/**
	 * <b>根据分页信息查询分页用户列表</b>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page<User> getUserListByPage(Page<User> page) throws Exception {
		//调用 PageHelper 拦截器
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<User> userList = userDao.findListByQuery(null);
		PageInfo<User> pageInfo = new PageInfo<User>(userList);
		//提取 PageInfo 里面的信息
		BeanUtils.copyProperties(pageInfo, page);
		page.setPages((long) pageInfo.getPages());
		return page;
	}

	/**
	 * <b>更新用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateUser(User user) throws Exception {
		int count = userDao.update(user);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * <b>保存用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveUser(User user) throws Exception {
		if (userDao.save(user) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * <b>校验密码是否正确</b>
	 * @return
	 * @throws Exception
	 */
	public boolean getUserByPassword(String password) throws Exception {
		User query = new User();
		query.setPassword(MD5Util.encrypt(password));
		List<User> userList = userDao.findListByQuery(query);
		if (userList == null || userList.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * <b>保存用户列表信息</b>
	 * @param userList
	 * @return
	 * @throws Exception
	 */
	public boolean saveUserList(List<User> userList) throws Exception {
		int count = userDao.saveUserList(userList);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> getUserList() throws Exception {
		List<User> userList = userDao.findListByQuery(null);
		//创建 Excel 表头字段
		User user = new User();
		user.setUsername("姓名");
		user.setCellphone("电话");
		user.setGender("性别");
		Role role = new Role();
		role.setName("职能");
		user.setRole(role);
		user.setIdCard("身份证");
		user.setEmail("电子邮件");
		userList.add(0, user);
		if (userList != null && userList.size() > 0) {
			return userList;
		}
		return new ArrayList<User>();
	}

	@Override
	public boolean getUserByUser(User user) throws Exception {
		List<User> userList = userDao.findListByQuery(user);
		if (userList == null || userList.size() == 0) {
			return true;
		}
		return false;
	}
}
