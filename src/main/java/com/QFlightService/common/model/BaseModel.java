package com.QFlightService.common.model;

import jakarta.persistence.*;

import java.util.Date;

public class BaseModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Date created_date;
  private Date updated_date;
}
