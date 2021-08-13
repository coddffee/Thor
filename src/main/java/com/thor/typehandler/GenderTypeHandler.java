package com.thor.typehandler;

import com.thor.type.Gender;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenderTypeHandler extends BaseTypeHandler<Gender> {

    public GenderTypeHandler() {
        super();
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Gender gender, JdbcType jdbcType) throws SQLException {
        if(gender == Gender.MALE) {
            preparedStatement.setString(i,"m");
        } else if(gender.equals(Gender.FEMALE)) {
            preparedStatement.setString(i,"f");
        }
    }

    @Override
    public Gender getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if(resultSet.getString(s).equals("m")) {
            return Gender.MALE;
        } else if(resultSet.getString(s).equals("f")) {
            return Gender.FEMALE;
        }
        return null;
    }

    @Override
    public Gender getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if(resultSet.getString(i).equals("m")) {
            return Gender.MALE;
        } else if(resultSet.getString(i).equals("f")) {
            return Gender.FEMALE;
        }
        return null;
    }

    @Override
    public Gender getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if (callableStatement.getString(i).equals("m")) {
            return Gender.MALE;
        } else if(callableStatement.getString(i).equals("f")) {
            return Gender.FEMALE;
        }
        return null;
    }
}
