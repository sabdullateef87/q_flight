package com.QFlightService.user.repository;

import com.QFlightService.common.exception.DatabaseException;
import com.QFlightService.common.exception.RecordNotFoundException;
import com.QFlightService.user.model.User;
import com.QFlightService.user.model.request.CreateUserRequest;
import com.QFlightService.user.model.request.GetUserDetailsRequest;
import com.QFlightService.user.util.CustomUserEntityRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository implements IUserRepository {
  private static final String SINGLE_RESULT = "single";
  private static final String MULTIPLE_RESULT = "list";
  private static final String COUNT = "count";
  private SimpleJdbcCall createUser, getUserDetails;

  @Autowired
  public void setDataSource(DataSource dataSource){
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    createUser = new SimpleJdbcCall(jdbcTemplate)
        .withProcedureName("insert_new_user")
        .returningResultSet(SINGLE_RESULT, new CustomUserEntityRowMapper());

    getUserDetails = new SimpleJdbcCall(jdbcTemplate)
        .withProcedureName("get_user_details")
        .returningResultSet(SINGLE_RESULT, new CustomUserEntityRowMapper());
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

  @Override
  public User getUserDetails(GetUserDetailsRequest request) {
    try{
      SqlParameterSource in = new MapSqlParameterSource()
          .addValue("p_email", request.getEmail());
      Map<String, Object> out = this.getUserDetails.execute(in);
      List<User> user = (List<User>) out.get(SINGLE_RESULT);
      if(user == null || user.size() == 0){
        String errorMessage = String.format("User with email %s or phone number %s does not exist", request.getEmail(), request.getMobileNumber());
        throw new RecordNotFoundException(errorMessage);
      }
      return user.get(0);
    } catch(Exception ex){
      if(ex instanceof RecordNotFoundException){
        throw ex;
      }
      throw new DatabaseException(ex.getMessage());
    }
  }
}
