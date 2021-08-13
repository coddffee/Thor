package com.thor.dao;

import com.thor.entity.Task;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface TasksDao {

    @Insert("insert into tasks " +
            "(manager_id,inspector_id,equipment_id,description,task_date,date)" +
            "values" + "(" +
            "#{task.managerId}," +
            "#{task.inspectorId}," +
            "#{task.equipmentId}," +
            "#{task.description}," +
            "#{task.taskDate}," +
            "#{task.date}" + ")")
    boolean insertOne(@Param("task") Task task);

    @Select("select count(*) from tasks where manager_id = #{managerId}")
    Integer selectCountByManagerId(@Param("managerId") Integer managerId);

    @Select("select * from tasks where manager_id = #{managerId} limit #{off} , #{size}")
    List<Task> selectByManagerIdAndPage(@Param("managerId") Integer managerId,
                                        @Param("off") Integer off,
                                        @Param("size") Integer size);

    @Select("select * from tasks where inspector_id = #{inspectorId} limit #{off} , #{size}")
    List<Task> selectByInspectorIdAndPage(@Param("inspectorId") Integer inspectorId,
                                          @Param("off") Integer off,
                                          @Param("size") Integer size);

    @Select("select count(*) from tasks where inspector_id = #{inspectorId}")
    Integer selectCountByInspectorId(@Param("inspectorId") Integer inspectorId);

    @Select("select * from tasks where id = #{id}")
    Task selectById(@Param("id") Integer id);

    @Update("update tasks set " +
            "manager_id = #{task.managerId}," +
            "inspector_id = #{task.inspectorId}," +
            "equipment_id = #{task.equipmentId}," +
            "description = #{task.description}," +
            "task_date = #{task.taskDate}," +
            "date = #{task.date} " +
            "where id = #{id}")
    boolean updateById(@Param("id") Integer id,@Param(("task")) Task task);

}
