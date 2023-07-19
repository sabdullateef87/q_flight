package com.QFlightService.user.util;

import com.QFlightService.common.exception.DatabaseException;
import com.QFlightService.user.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomUserEntityRowMapper implements RowMapper<User> {
  @Override
  public User mapRow(ResultSet rs, int rowNum) {

    try{
      return User.builder()
          .email(rs.getString("email"))
          .firstname(rs.getString("first_name"))
          .lastname(rs.getString("last_name"))
          .password(rs.getString("password"))
          .image(rs.getString("image"))
          .address(rs.getString("address"))
          .dateOfBirth(rs.getString("date_of_birth"))
          .mobileNumber(rs.getString("mobile_number"))
          .build();
    }catch (SQLException ex){
      throw  new DatabaseException(ex.getMessage());
    }

  }
}
