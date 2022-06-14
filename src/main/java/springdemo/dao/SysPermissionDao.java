package springdemo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springdemo.entity.SysPermissionEntity;



@Mapper
@Repository
public interface SysPermissionDao extends BaseDao<SysPermissionEntity> {

}
