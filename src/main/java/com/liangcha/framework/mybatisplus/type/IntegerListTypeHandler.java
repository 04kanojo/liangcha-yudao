package com.liangcha.framework.mybatisplus.type;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List<Integer> 的类型转换器实现类，对应数据库的 varchar 类型
 * 例：数据库数据 1,2,3
 * 转换后得到List<Integer>类型的数据
 *
 * @author 凉茶
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(List.class)
public class IntegerListTypeHandler implements TypeHandler<List<Integer>> {

    private static final String CONNECTORS = ",";

    @Override
    public void setParameter(PreparedStatement ps, int i, List<Integer> strings, JdbcType jdbcType) throws SQLException {
        ps.setString(i, CollUtil.join(strings, CONNECTORS));
    }

    @Override
    public List<Integer> getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return getResult(value);
    }

    @Override
    public List<Integer> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return getResult(value);
    }

    @Override
    public List<Integer> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return getResult(value);
    }

    private List<Integer> getResult(String value) {
        if (value == null) {
            return null;
        }
        return Arrays.stream(StrUtil.splitToInt(value, CONNECTORS)).boxed().collect(Collectors.toList());
    }
}
