package com.QFlightService.common.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private Long id;

  private Date created_on;
  private Date updated_on;
}
