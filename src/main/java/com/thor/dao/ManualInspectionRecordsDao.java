package com.thor.dao;

import com.thor.entity.ManualInspectionRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface ManualInspectionRecordsDao {

    @Insert("insert into manual_inspection_records" +
            "(task_id,temperature,photo,evaluation,description,date)" +
            "values(" +
            "#{record.taskId}," +
            "#{record.temperature}," +
            "#{record.photo}," +
            "#{record.evaluation}," +
            "#{record.description}," +
            "#{record.date}" + ")")
    boolean insertOne(@Param("record") ManualInspectionRecord record);

    @Update("update manual_inspection_records set " +
            "task_id = #{record.taskId}," +
            "temperature = #{record.temperature}," +
            "photo = #{record.photo}," +
            "evaluation = #{record.evaluation}," +
            "description = #{record.description}," +
            "date = #{record.date}")
    boolean updateById(@Param("id") Integer id,@Param("record") ManualInspectionRecord record);

    @Select("select * from manual_inspection_records where id = #{id}")
    ManualInspectionRecord selectById(@Param("id") Integer id);

    @Select("select * from manual_inspection_records where task_id = #{taskId}")
    ManualInspectionRecord selectByTaskId(@Param("taskId") Integer taskId);

    @Select("select count(*) from manual_inspection_records where task_id in " +
            "(select id from tasks where inspector_id = #{inspectorId})")
    Integer selectCountByInspectorId(@Param("inspectorId") Integer inspectorId);

    @Select("select * from manual_inspection_records where task_id in " +
            "(select id from tasks where inspector_id = #{inspectorId}) " +
            "limit #{off} , #{size}")
    List<ManualInspectionRecord> selectByInspectorIdAndPage(@Param("inspectorId") Integer inspectorId,
                                                            @Param("off") Integer off,
                                                            @Param("size") Integer size);

    @Select("select count(*) from manual_inspection_records where task_id in " +
            "(select id from tasks where inspector_id in " +
            "(select id from inspectors where manager_id = #{managerId}))")
    Integer selectCountByManagerId(@Param("managerId") Integer managerId);

    @Select("select * from manual_inspection_records where task_id in " +
            "(select id from tasks where inspector_id in " +
            "(select id from inspectors where manager_id = #{managerId})) " +
            "limit #{off} , #{size}")
    List<ManualInspectionRecord> selectByManagerIdAndPage(@Param("managerId") Integer managerId,
                                                          @Param("off") Integer off,
                                                          @Param("size") Integer size);
}
