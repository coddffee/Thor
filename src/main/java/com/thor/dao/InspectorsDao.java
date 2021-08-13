package com.thor.dao;

import com.thor.entity.Inspector;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface InspectorsDao {

    @Select("select * from inspectors where phone = #{phone}")
    Inspector selectByPhone(@Param("phone") String phone);

    @Insert("insert into inspectors" +
            "(name,phone,password,gender,age,icon,post_id,manager_id,date)" +
            "values" + "(" +
            "#{inspector.name}," +
            "#{inspector.phone}," +
            "#{inspector.password}," +
            "#{inspector.gender}," +
            "#{inspector.age}," +
            "#{inspector.icon}," +
            "#{inspector.postId}," +
            "#{inspector.managerId}," +
            "#{inspector.date}" + ")")
    boolean insertOne(@Param("inspector") Inspector inspector);

    @Update("update inspectors set ${name} = #{value} where id = #{id}")
    boolean updatePropertyById(@Param("id") Integer id,@Param("name") String name,@Param("value") Object value);

    @Select("select count(*) from inspectors where manager_id = #{managerId}")
    Integer selectCountByManagerId(@Param("managerId") Integer managerId);

    @Select("select * from inspectors where manager_id = #{managerId} limit #{off} , #{size}")
    List<Inspector> selectByManagerIdAndPage(@Param("managerId") Integer managerId,
                                             @Param("off") Integer off,
                                             @Param("size") Integer size);

    @Select("select * from inspectors where id = #{id}")
    Inspector selectById(@Param("id") Integer id);

    @Select("select id from inspectors where phone = #{phone}")
    Integer selectIdByPhone(@Param("phone") String phone);

}
