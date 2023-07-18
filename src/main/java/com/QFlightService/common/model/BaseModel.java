package com.QFlightService.common.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Date created_date;
  private Date updated_date;
}
