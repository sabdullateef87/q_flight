package com.QFlightService.user.repository;

import com.QFlightService.common.exception.DatabaseException;
import com.QFlightService.user.model.User;
import com.QFlightService.user.model.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
  private static final String SINGLE_RESULT = "single";
  private static final String MULTIPLE_RESULT = "list";
  private static final String COUNT = "count";
  private SimpleJdbcCall createUser;

  @Autowired
  public void setDataSource(DataSource dataSource){
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    createUser = new SimpleJdbcCall(jdbcTemplate)
        .withProcedureName("insert_new_user")
        .returningResultSet(SINGLE_RESULT, BeanPropertyRowMapper.newInstance(User.class));
  }

  public User createUser(CreateUserRequest request){
    try{
      SqlParameterSource in = new MapSqlParameterSource()
          .addValue("p_first_name", request.getFirstName())
          .addValue("p_last_name", request.getLastName())
          .addValue("p_email", request.getEmail())
          .addValue("p_password", request.getPassword())
          .addValue("p_date_of_birth", request.getDateOfBirth())
          .addValue("p_address", request.getAddress())
          .addValue("p_image", request.getImage())
          .addValue("p_mobile_number", request.getMobileNumber());

      Map<String, Object> out = this.createUser.execute(in);
      List<User> newUser = (List<User>) out.get(SINGLE_RESULT);
      return newUser.get(0);
    }catch(Exception ex){
      throw new DatabaseException(ex.getMessage());
    }
  }
}
