package com.thor.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class BlobTypeHandler extends BaseTypeHandler<Blob> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Blob blob, JdbcType jdbcType) throws SQLException {
    }

    @Override
    public Blob getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public Blob getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Blob getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
