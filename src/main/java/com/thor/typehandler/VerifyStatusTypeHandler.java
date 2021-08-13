package com.thor.typehandler;

import com.thor.type.VerifyStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerifyStatusTypeHandler extends BaseTypeHandler<VerifyStatus> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, VerifyStatus verifyStatus, JdbcType jdbcType) throws SQLException {
        if(verifyStatus == VerifyStatus.VERIFYING) {
            preparedStatement.setString(i,"verifying");
        } else if(verifyStatus == VerifyStatus.ACCEPTED) {
            preparedStatement.setString(i,"accepted");
        } else if(verifyStatus == VerifyStatus.REFUSED) {
            preparedStatement.setString(i,"refused");
        }
    }

    @Override
    public VerifyStatus getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if(resultSet.getString(s).equals("verifying")) {
            return VerifyStatus.VERIFYING;
        } else if(resultSet.getString(s).equals("accepted")) {
            return VerifyStatus.ACCEPTED;
        } else if(resultSet.getString(s).equals("refused")) {
            return VerifyStatus.REFUSED;
        }
        return null;
    }

    @Override
    public VerifyStatus getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if(resultSet.getString(i).equals("verifying")) {
            return VerifyStatus.VERIFYING;
        } else if(resultSet.getString(i).equals("accepted")) {
            return VerifyStatus.ACCEPTED;
        } else if(resultSet.getString(i).equals("refused")) {
            return VerifyStatus.REFUSED;
        }
        return null;
    }

    @Override
    public VerifyStatus getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if(callableStatement.getString(i).equals("verifying")) {
            return VerifyStatus.VERIFYING;
        } else if(callableStatement.getString(i).equals("accepted")) {
            return VerifyStatus.ACCEPTED;
        } else if(callableStatement.getString(i).equals("refused")) {
            return VerifyStatus.REFUSED;
        }
        return null;
    }
}
