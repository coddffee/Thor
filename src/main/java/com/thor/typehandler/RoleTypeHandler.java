package com.thor.typehandler;

import com.thor.type.Role;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleTypeHandler extends BaseTypeHandler<Role> {
    public RoleTypeHandler() {
        super();
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Role role, JdbcType jdbcType) throws SQLException {
        if(role == Role.MANUAL_INSPECTION_MANAGER) {
            preparedStatement.setString(i,"manual_inspection_manager");
        } else if(role == Role.AUTOMATIC_INSPECTION_MANAGER) {
            preparedStatement.setString(i,"automatic_inspection_manager");
        } else if(role == Role.EQUIPMENT_MANAGER) {
            preparedStatement.setString(i,"equipment_manager");
        }
    }

    @Override
    public Role getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if(resultSet.getString(s).equals("manual_inspection_manager")) {
            return Role.MANUAL_INSPECTION_MANAGER;
        } else if(resultSet.getString(s).equals("automatic_inspection_manager")) {
            return Role.AUTOMATIC_INSPECTION_MANAGER;
        } else if(resultSet.getString(s).equals("equipment_manager")) {
            return Role.EQUIPMENT_MANAGER;
        }
        return null;
    }

    @Override
    public Role getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if(resultSet.getString(i).equals("manual_inspection_manager")) {
            return Role.MANUAL_INSPECTION_MANAGER;
        } else if(resultSet.getString(i).equals("automatic_inspection_manager")) {
            return Role.AUTOMATIC_INSPECTION_MANAGER;
        } else if(resultSet.getString(i).equals("equipment_manager")) {
            return Role.EQUIPMENT_MANAGER;
        }
        return null;
    }

    @Override
    public Role getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if(callableStatement.getString(i).equals("manual_inspection_manager")) {
            return Role.MANUAL_INSPECTION_MANAGER;
        } else if(callableStatement.getString(i).equals("automatic_inspection_manager")) {
            return Role.AUTOMATIC_INSPECTION_MANAGER;
        } else if(callableStatement.getString(i).equals("equipment_manager")) {
            return Role.EQUIPMENT_MANAGER;
        }
        return null;
    }
}
