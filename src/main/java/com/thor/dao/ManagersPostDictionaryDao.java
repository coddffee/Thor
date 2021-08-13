package com.thor.dao;

import com.thor.type.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ManagersPostDictionaryDao {

    @Select("select manager_role from managers_post_dictionary where id = #{id}")
    Role selectRoleById(@Param("id") Integer id);

}
