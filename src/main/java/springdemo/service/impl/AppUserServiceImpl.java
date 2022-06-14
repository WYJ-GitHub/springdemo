package springdemo.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springdemo.dao.AppUserDao;
import springdemo.service.AppUserService;
import springdemo.entity.PageData;
import springdemo.utils.ExcelUtil;
import springdemo.utils.PageUtil;
import java.util.LinkedHashMap;
import springdemo.entity.AppUserEntity;
import java.util.List;
import java.util.Map;

@Service
public class AppUserServiceImpl implements AppUserService {


	private final AppUserDao dao;

	@Autowired
	public AppUserServiceImpl(AppUserDao dao) {
		this.dao = dao;
	}

	@Override
	public void add(AppUserEntity entity) {
		dao.add(entity);
	}

	@Override
	public void delete(AppUserEntity entity) {
		dao.delete(entity);
	}

	@Override
	public void update(AppUserEntity entity) {
		dao.update(entity);
	}

	@Override
	public List<AppUserEntity> select(AppUserEntity entity) {
		return dao.select(entity);
	}

	@Override
	public PageData<AppUserEntity> likeSelect(AppUserEntity entity) {
		return PageUtil.getPageData(entity, dao);
	}

	@Override
	public void exportExcel(AppUserEntity entity, HttpServletResponse response) {

		// 获取头部信息（可以设置为动态）
		String[] headList = new String[] { "自增id", "用户名", "密码", "昵称", "头像url", "手机号", "性别", "状态（1有效,0无效）", "类型（暂未用）", "创建时间", "修改时间"};

		String[] headEngList = new String[]{ "id", "username", "password", "nickname", "headImgUrl", "phone", "sex", "enabled", "type", "createTime", "updateTime"};

		String[] describeList = new String[] { "", "", "", "", "", "", "", "", "", "", ""};

		//设置头部以及描述信息
        Map<String, String> headAndDescribeMap = new LinkedHashMap<>();
        for (int i = 0; i < headEngList.length; i++) {
            headAndDescribeMap.put(headEngList[i], describeList[i]);
        }

		ExcelUtil.exportExcel(entity, response, dao, headList, headAndDescribeMap);
	}
}
