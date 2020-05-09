package cn.ekgc.ams.service.impl;

import cn.ekgc.ams.dao.RoleDao;
import cn.ekgc.ams.dao.UserDao;
import cn.ekgc.ams.pojo.entity.Role;
import cn.ekgc.ams.pojo.entity.User;
import cn.ekgc.ams.pojo.vo.Page;
import cn.ekgc.ams.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>角色模块服务层接口实现类</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	/**
	 * <b>查询角色信息列表</b>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page<Role> getRoleListByPage(Page<Role> page) throws Exception {
		//调用 PageHelper 拦截器
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<Role> roleList = roleDao.findListByQuery(null);
		PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
		//提取 PageInfo 里面的信息
		BeanUtils.copyProperties(pageInfo, page);
		page.setPages((long) pageInfo.getPages());
		return page;
	}


	public boolean grantRoleAuth(Long roleId, String ids) throws Exception {
		//根据 roleId  删除旧的权限表
		int delResult = roleDao.delRoleMenuByRoleId(roleId);
		if (delResult >= 0) {
			String[] menuIds = ids.split("-");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			int count = 0;
			for (int i = 0; i < menuIds.length; i++) {
				//添加新的权限
				Long menuId = Long.parseLong(menuIds[i]);
				paramMap.put("roleId", roleId);
				paramMap.put("menuId", menuId);
				int result = roleDao.addRoleAndMenu(paramMap);
				if (result > 0) {
					count++;
				}
			}
			if (count == menuIds.length) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <b>查询角色列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleListByQuery(Role query) throws Exception {
		List<Role> roleList = roleDao.findListByQuery(query);
		if (roleList != null && roleList.size() > 0) {
			return roleList;
		}
		return new ArrayList<Role>();
	}

	/**
	 * <b>添加角色信息</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public boolean saveRole(Role role) throws Exception {
		if (roleDao.save(role) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * <b>更新角色信息</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public boolean updateRole(Role role) throws Exception {
		if (roleDao.update(role) > 0) {
			return true;
		}
		return false;
	}
}
