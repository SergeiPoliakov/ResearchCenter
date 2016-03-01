package com.netcracker.unc.beans.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<Object> {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setLogin(rs.getString("login"));
        user.setHash_sum(rs.getInt("hash_sum"));
        user.setName(rs.getString("name"));
        user.setAccount_type("account_type");
        user.setSalt(rs.getInt("salt"));
        return user;
    }

}
