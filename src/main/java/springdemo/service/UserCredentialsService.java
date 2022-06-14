package springdemo.service;

import javax.servlet.http.HttpServletResponse;

import springdemo.entity.UserCredentialsEntity;
import springdemo.entity.PageData;
import java.util.List;

public interface UserCredentialsService {

	void add(UserCredentialsEntity entity);

	void delete(UserCredentialsEntity entity);

	void update(UserCredentialsEntity entity);

	List<UserCredentialsEntity> select(UserCredentialsEntity entity);

	PageData<UserCredentialsEntity> likeSelect(UserCredentialsEntity entity);

    void exportExcel(UserCredentialsEntity entity, HttpServletResponse response);

}
