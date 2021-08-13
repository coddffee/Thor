package com.thor.dao;

import com.thor.entity.ManagerRegistry;
import com.thor.type.VerifyStatus;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface ManagersRegistryDao {
    @Insert("insert into managers_registry" +
            "(name,phone,password,gender,age,icon,post_id,date,status)" +
            "values" + "(" +
            "#{registry.name}," +
            "#{registry.phone}," +
            "#{registry.password}," +
            "#{registry.gender}," +
            "#{registry.age}," +
            "#{registry.icon}," +
            "#{registry.postId}," +
            "#{registry.date}," +
            "#{registry.status}" + ")")
    boolean insertOne(@Param("registry") ManagerRegistry registry);

    @Select("select * from managers_registry where id = #{id}")
    ManagerRegistry selectById(@Param("id") Integer id);

    @Select("select * from managers_registry where phone = #{phone}")
    ManagerRegistry selectByPhone(@Param("phone") String phone);

    @Select("select count(*) from managers_registry")
    Integer selectCount();

    @Select("select * from managers_registry limit #{off} , #{size}")
    List<ManagerRegistry> selectByPage(@Param("off") Integer off ,
                                       @Param("size") Integer size);

    @Update("update managers_registry set status = #{status} where id = #{id}")
    boolean updateStatusById(@Param("id") Integer id,
                             @Param("status") VerifyStatus status);
}
