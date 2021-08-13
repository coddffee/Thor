package com.thor.entity;

import com.thor.type.Role;
import java.sql.Date;

/**
 * manager post dictionary
 */
public class ManagerPostDictionary {
    private Integer id;
    private Role managerRole;
    private String name;
    private String description;
    private Date date = new Date(0);

    public ManagerPostDictionary() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getManagerRole() {
        return managerRole;
    }

    public void setManagerRole(Role managerRole) {
        this.managerRole = managerRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "ManagerPostDictionary{" +
                "id=" + id +
                ", managerRole=" + managerRole +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
