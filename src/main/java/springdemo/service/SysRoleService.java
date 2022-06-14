package springdemo.service;

import javax.servlet.http.HttpServletResponse;

import springdemo.entity.SysRoleEntity;
import springdemo.entity.PageData;
import java.util.List;

public interface SysRoleService {

	void add(SysRoleEntity entity);

	void delete(SysRoleEntity entity);

	void update(SysRoleEntity entity);

	List<SysRoleEntity> select(SysRoleEntity entity);

	PageData<SysRoleEntity> likeSelect(SysRoleEntity entity);

    void exportExcel(SysRoleEntity entity, HttpServletResponse response);

}
