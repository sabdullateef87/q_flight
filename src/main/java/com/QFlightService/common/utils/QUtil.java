package com.QFlightService.common.utils;

import java.util.Objects;
import java.util.UUID;

public class QUtil {
  public static String generateRef(){
    return UUID.randomUUID().toString();
  }

  public static boolean isEmpty(String value){
    if(value == null || value  == "" || value.strip() == ""){
      return true;
    }
    return false;
  }
}
