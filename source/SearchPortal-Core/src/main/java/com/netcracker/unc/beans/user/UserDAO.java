package com.netcracker.unc.beans.user;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import com.netcracker.unc.beans.ObjectDAO;
import com.netcracker.unc.mvc.SQLQuery;

public class UserDAO implements ObjectDAO {

	private JdbcTemplate jdbcTemplate;

	public List<Object> getAllObjectsDB() {
		String query = SQLQuery.SP_USERS_VIEW_ALL;
		List<Object> list = new ArrayList<Object>();
		list = jdbcTemplate.query(query, new UserMapper());
		return list;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addObject(Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getObject(Object object) {
		User user = (User) object;
		String query = null;
		if (user.getUser_id() == 0) {
			query = SQLQuery.SP_USERS_GET_BY_LOGIN;
			user = (User) jdbcTemplate.queryForObject(query, new Object[]{user.getLogin()}, new UserMapper());
		}
		else {
			query = SQLQuery.SP_USERS_GET_BY_ID;
			user = (User) jdbcTemplate.queryForObject(query, new Object[]{user.getUser_id()}, new UserMapper());
		}
		return user;
	}

	@Override
	public void updateObject(Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteObject(Object object) {
		// TODO Auto-generated method stub

	}

}
