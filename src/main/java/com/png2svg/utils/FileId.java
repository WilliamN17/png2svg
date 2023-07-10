package com.png2svg.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class FileId {

  private static volatile FileId instance;
  private final static String FORMAT_DATETIME ="YYYYMMDD";

  private volatile int sequence = 0;

  public static FileId getInstance(){
    if(instance == null){
      synchronized (FileId.class){
        if(instance ==null){
          instance = new FileId();
        }
      }
    }
    return instance;
  }

  public synchronized String nextId(){
    String datetime = timestamp();
    sequence+=1;
    return datetime+sequence;
  }

  // Get current timestamp in milliseconds, adjust for the custom epoch.
  public static String timestamp() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATETIME);
    return  now.format(formatter);
  }
}
