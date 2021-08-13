package com.thor.entity;

import com.thor.type.Evaluation;
import java.sql.Blob;
import java.sql.Date;

/**
 * manual inspection record
 */
public class ManualInspectionRecord {
    private Integer id;
    private Integer taskId;
    private float temperature;
    private Blob photo;
    private Evaluation evaluation;
    private String description;
    private Date date = new Date(0);

    public ManualInspectionRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ManualInspectionRecord{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", temperature=" + temperature +
                ", photo=" + photo +
                ", evaluation=" + evaluation +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
