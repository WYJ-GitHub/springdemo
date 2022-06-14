package springdemo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springdemo.entity.SysRoleEntity;



@Mapper
@Repository
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

}
