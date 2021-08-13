package com.thor.dao;

import com.thor.entity.InspectorRegistry;
import com.thor.type.VerifyStatus;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface InspectorsRegistryDao {

    @Insert("insert into inspectors_registry" +
            "(name,phone,password,gender,age,icon,post_id,manager_id,date,status)" +
            "values" + "(" +
            "#{inspector.name}," +
            "#{inspector.phone}," +
            "#{inspector.password}," +
            "#{inspector.gender}," +
            "#{inspector.age}," +
            "#{inspector.icon}," +
            "#{inspector.postId}," +
            "#{inspector.managerId}," +
            "#{inspector.date}," +
            "#{inspector.status}" + ")")
    boolean insertOne(@Param("inspector") InspectorRegistry registry);

    @Select("select * from inspectors_registry where phone = #{phone}")
    InspectorRegistry selectByPhone(@Param("phone") String phone);

    @Select("select * from inspectors_registry where id = #{id}")
    InspectorRegistry selectById(@Param("id") Integer id);

    @Update("update inspectors_registry set status = #{status} where id = #{id}")
    boolean updateStatusById(@Param("id") Integer id, @Param("status") VerifyStatus status);

    @Select("select count(*) from inspectors_registry where manager_id = #{managerId}")
    Integer selectCountByManagerId(@Param("managerId") Integer managerId);

    @Select("select * from inspectors_registry where manager_id = #{managerId} limit #{off} , #{size}")
    List<InspectorRegistry> selectByManagerIdAndPage(@Param("managerId") Integer managerId,@Param("off") Integer off,@Param("size") Integer size);
}
