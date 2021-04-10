package com.elyte.partner.dao;

import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.elyte.partner.vo.request.Admin;
@Repository
public class UserDaoImpl implements UserDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public Long createPartner(Admin userDetails) {
		KeyHolder instKeyHolder = new GeneratedKeyHolder();

		String query = "INSERT INTO cloud_core.user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USER_REF_ID) values (?,?,?,?,?)";

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, userDetails.getFirstName() );
			ps.setString(2, userDetails.getLastName() );
			ps.setString(3, userDetails.getEmail() );
			ps.setString(4, userDetails.getPassword());
			ps.setString(5, userDetails.getUserRefernaceId());
			
			return ps;

		}, instKeyHolder);

		return (Long) instKeyHolder.getKey();
	}

}
