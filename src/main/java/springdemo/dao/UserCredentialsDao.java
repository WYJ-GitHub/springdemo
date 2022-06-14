package springdemo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springdemo.entity.UserCredentialsEntity;



@Mapper
@Repository
public interface UserCredentialsDao extends BaseDao<UserCredentialsEntity> {

}
