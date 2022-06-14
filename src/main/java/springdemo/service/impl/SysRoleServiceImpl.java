package springdemo.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springdemo.dao.SysRoleDao;
import springdemo.service.SysRoleService;
import springdemo.entity.PageData;
import springdemo.utils.ExcelUtil;
import springdemo.utils.PageUtil;
import java.util.LinkedHashMap;
import springdemo.entity.SysRoleEntity;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {


	private final SysRoleDao dao;

	@Autowired
	public SysRoleServiceImpl(SysRoleDao dao) {
		this.dao = dao;
	}

	@Override
	public void add(SysRoleEntity entity) {
		dao.add(entity);
	}

	@Override
	public void delete(SysRoleEntity entity) {
		dao.delete(entity);
	}

	@Override
	public void update(SysRoleEntity entity) {
		dao.update(entity);
	}

	@Override
	public List<SysRoleEntity> select(SysRoleEntity entity) {
		return dao.select(entity);
	}

	@Override
	public PageData<SysRoleEntity> likeSelect(SysRoleEntity entity) {
		return PageUtil.getPageData(entity, dao);
	}

	@Override
	public void exportExcel(SysRoleEntity entity, HttpServletResponse response) {

		// 获取头部信息（可以设置为动态）
		String[] headList = new String[] { "自增id", "角色code", "角色名", "创建时间", "修改时间"};

		String[] headEngList = new String[]{ "id", "code", "name", "createTime", "updateTime"};

		String[] describeList = new String[] { "", "", "", "", ""};

		//设置头部以及描述信息
        Map<String, String> headAndDescribeMap = new LinkedHashMap<>();
        for (int i = 0; i < headEngList.length; i++) {
            headAndDescribeMap.put(headEngList[i], describeList[i]);
        }

		ExcelUtil.exportExcel(entity, response, dao, headList, headAndDescribeMap);
	}
}
