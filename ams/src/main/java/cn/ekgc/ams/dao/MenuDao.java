package cn.ekgc.ams.dao;

import cn.ekgc.ams.pojo.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>菜单模块数据持久层层接口</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface MenuDao {
	/**
	 * <b>根据上级菜单和角色查询菜单列表</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<Menu> findMenuListByMap(Map<String, Object> queryMap) throws Exception;

	/**
	 * <b>根据查询条件查询菜单列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Menu> findListByQuery(Map<String, Object> query) throws Exception;
}
