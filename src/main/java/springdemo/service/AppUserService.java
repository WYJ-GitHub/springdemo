package springdemo.service;

import javax.servlet.http.HttpServletResponse;

import springdemo.entity.AppUserEntity;
import springdemo.entity.PageData;
import java.util.List;

public interface AppUserService {

	void add(AppUserEntity entity);

	void delete(AppUserEntity entity);

	void update(AppUserEntity entity);

	List<AppUserEntity> select(AppUserEntity entity);

	PageData<AppUserEntity> likeSelect(AppUserEntity entity);

    void exportExcel(AppUserEntity entity, HttpServletResponse response);

}
