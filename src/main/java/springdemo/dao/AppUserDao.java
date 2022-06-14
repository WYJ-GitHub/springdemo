package springdemo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springdemo.entity.AppUserEntity;



@Mapper
@Repository
public interface AppUserDao extends BaseDao<AppUserEntity> {

}
