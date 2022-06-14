package springdemo.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springdemo.dao.UserCredentialsDao;
import springdemo.service.UserCredentialsService;
import springdemo.entity.PageData;
import springdemo.utils.ExcelUtil;
import springdemo.utils.PageUtil;
import java.util.LinkedHashMap;
import springdemo.entity.UserCredentialsEntity;
import java.util.List;
import java.util.Map;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService {


	private final UserCredentialsDao dao;

	@Autowired
	public UserCredentialsServiceImpl(UserCredentialsDao dao) {
		this.dao = dao;
	}

	@Override
	public void add(UserCredentialsEntity entity) {
		dao.add(entity);
	}

	@Override
	public void delete(UserCredentialsEntity entity) {
		dao.delete(entity);
	}

	@Override
	public void update(UserCredentialsEntity entity) {
		dao.update(entity);
	}

	@Override
	public List<UserCredentialsEntity> select(UserCredentialsEntity entity) {
		return dao.select(entity);
	}

	@Override
	public PageData<UserCredentialsEntity> likeSelect(UserCredentialsEntity entity) {
		return PageUtil.getPageData(entity, dao);
	}

	@Override
	public void exportExcel(UserCredentialsEntity entity, HttpServletResponse response) {

		// 获取头部信息（可以设置为动态）
		String[] headList = new String[] { "用户名或手机号等", "账号类型（用户名、手机号）", "用户id"};

		String[] headEngList = new String[]{ "username", "type", "userId"};

		String[] describeList = new String[] { "", "", ""};

		//设置头部以及描述信息
        Map<String, String> headAndDescribeMap = new LinkedHashMap<>();
        for (int i = 0; i < headEngList.length; i++) {
            headAndDescribeMap.put(headEngList[i], describeList[i]);
        }

		ExcelUtil.exportExcel(entity, response, dao, headList, headAndDescribeMap);
	}
}
