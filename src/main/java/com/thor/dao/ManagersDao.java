package com.thor.dao;

import com.thor.entity.Manager;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface ManagersDao {
    @Select("select * from managers where phone = #{phone}")
    Manager selectByPhone(@Param("phone") String phone);
    @Insert("insert into managers" +
            "(name,phone,password,gender,age,icon,post_id,date)" +
            "values" + "(" +
            "#{manager.name}," +
            "#{manager.phone}," +
            "#{manager.password}," +
            "#{manager.gender}," +
            "#{manager.age}," +
            "#{manager.icon}," +
            "#{manager.postId}," +
            "#{manager.date}" + ")")
    boolean insertOne(@Param("manager") Manager manager);
    @Update("update managers set ${name} = #{value} where id = #{id}")
    boolean updatePropertyById(@Param("id") Integer id,
                               @Param("name") String name,
                               @Param("value") Object value);

    @Select("select count(*) from managers")
    Integer selectCount();
    @Select("select * from managers limit #{off} , #{size}")
    List<Manager> selectByPage(@Param("off") Integer off,
                               @Param("size") Integer size);
    @Select("select id from managers where phone = #{phone}")
    Integer selectIdByPhone(@Param("phone") String phone);
}
