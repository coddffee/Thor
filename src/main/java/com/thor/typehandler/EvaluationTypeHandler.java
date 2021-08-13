package com.thor.typehandler;

import com.thor.type.Evaluation;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EvaluationTypeHandler extends BaseTypeHandler<Evaluation> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Evaluation evaluation, JdbcType jdbcType) throws SQLException {
        if(evaluation == Evaluation.NORMAL) {
            preparedStatement.setString(i,"normal");
        } else if(evaluation == Evaluation.IMPERFECT) {
            preparedStatement.setString(i,"imperfect");
        } else if(evaluation == Evaluation.FAULT) {
            preparedStatement.setString(i,"fault");
        }
    }

    @Override
    public Evaluation getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if(resultSet.getString(s).equals("normal")) {
            return Evaluation.NORMAL;
        } else if(resultSet.getString(s).equals("imperfect")) {
            return Evaluation.IMPERFECT;
        } else if(resultSet.getString(s).equals("fault")) {
            return Evaluation.FAULT;
        }
        return null;
    }

    @Override
    public Evaluation getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if(resultSet.getString(i).equals("normal")) {
            return Evaluation.NORMAL;
        } else if(resultSet.getString(i).equals("imperfect")) {
            return Evaluation.IMPERFECT;
        } else if(resultSet.getString(i).equals("fault")) {
            return Evaluation.FAULT;
        }
        return null;
    }

    @Override
    public Evaluation getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if(callableStatement.getString(i).equals("normal")) {
            return Evaluation.NORMAL;
        } else if(callableStatement.getString(i).equals("imperfect")) {
            return Evaluation.IMPERFECT;
        } else if(callableStatement.getString(i).equals("fault")) {
            return Evaluation.FAULT;
        }
        return null;
    }
}
