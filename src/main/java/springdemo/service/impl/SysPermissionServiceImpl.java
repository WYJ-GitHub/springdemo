package springdemo.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springdemo.dao.SysPermissionDao;
import springdemo.service.SysPermissionService;
import springdemo.entity.PageData;
import springdemo.utils.ExcelUtil;
import springdemo.utils.PageUtil;
import java.util.LinkedHashMap;
import springdemo.entity.SysPermissionEntity;
import java.util.List;
import java.util.Map;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {


	private final SysPermissionDao dao;

	@Autowired
	public SysPermissionServiceImpl(SysPermissionDao dao) {
		this.dao = dao;
	}

	@Override
	public void add(SysPermissionEntity entity) {
		dao.add(entity);
	}

	@Override
	public void delete(SysPermissionEntity entity) {
		dao.delete(entity);
	}

	@Override
	public void update(SysPermissionEntity entity) {
		dao.update(entity);
	}

	@Override
	public List<SysPermissionEntity> select(SysPermissionEntity entity) {
		return dao.select(entity);
	}

	@Override
	public PageData<SysPermissionEntity> likeSelect(SysPermissionEntity entity) {
		return PageUtil.getPageData(entity, dao);
	}

	@Override
	public void exportExcel(SysPermissionEntity entity, HttpServletResponse response) {

		// 获取头部信息（可以设置为动态）
		String[] headList = new String[] { "自增id", "权限标识", "名称", "创建时间", "修改时间"};

		String[] headEngList = new String[]{ "id", "permission", "name", "createTime", "updateTime"};

		String[] describeList = new String[] { "", "", "", "", ""};

		//设置头部以及描述信息
        Map<String, String> headAndDescribeMap = new LinkedHashMap<>();
        for (int i = 0; i < headEngList.length; i++) {
            headAndDescribeMap.put(headEngList[i], describeList[i]);
        }

		ExcelUtil.exportExcel(entity, response, dao, headList, headAndDescribeMap);
	}
}
