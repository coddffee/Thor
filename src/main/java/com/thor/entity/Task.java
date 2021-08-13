package com.thor.entity;

import java.sql.Date;

/**
 * manual inspection task
 */
public class Task {
    private Integer id;
    private Integer managerId;
    private Integer inspectorId;
    private Integer equipmentId;
    private String description;
    private Date taskDate;
    private Date date = new Date(0);

    public Task() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(Integer inspectorId) {
        this.inspectorId = inspectorId;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", managerId=" + managerId +
                ", inspectorId=" + inspectorId +
                ", equipmentId=" + equipmentId +
                ", description='" + description + '\'' +
                ", taskDate=" + taskDate +
                ", date=" + date +
                '}';
    }
}
