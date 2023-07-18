package com.QFlightService.common.utils;

import java.util.UUID;

public class QUtil {
  public static String generateRef(){
    return UUID.randomUUID().toString();
  }
}
