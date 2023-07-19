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

  @JsonProperty("data")
  private Object data;

  @JsonProperty("error")
  private Object error;

  @JsonProperty("time_stamp")
  private Date timestamp;

  @JsonProperty("response_message")
  private String responseMessage;

  @JsonProperty("response_code")
  private String responseCode;
}
