package com.QFlightService.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Response {
  @JsonProperty("reference_id")
  private String referenceId;
  private Object data;
  private Object error;
  private Date timestamp;
}
