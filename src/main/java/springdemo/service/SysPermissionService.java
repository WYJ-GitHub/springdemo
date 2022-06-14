package springdemo.service;

import javax.servlet.http.HttpServletResponse;

import springdemo.entity.SysPermissionEntity;
import springdemo.entity.PageData;
import java.util.List;

public interface SysPermissionService {

	void add(SysPermissionEntity entity);

	void delete(SysPermissionEntity entity);

	void update(SysPermissionEntity entity);

	List<SysPermissionEntity> select(SysPermissionEntity entity);

	PageData<SysPermissionEntity> likeSelect(SysPermissionEntity entity);

    void exportExcel(SysPermissionEntity entity, HttpServletResponse response);

}
