package com.png2svg.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public final class FileManagement {

  public String writeFile(byte[] bytearray,String format) throws IOException {
    String fileNameId = FileId.getInstance().nextId();
    File fileTemp = File.createTempFile(fileNameId,format);
    FileUtils.writeByteArrayToFile(fileTemp,bytearray);

    return fileTemp.getAbsolutePath();
  }

  public byte[] readFile(String path) throws IOException {
    File file = new File(path);
    if(!file.exists()){
      throw new FileNotFoundException(String.format("Path = %s is not found",path));
    }
    return FileUtils.readFileToByteArray(file);
  }

  public void deleteTemp(String path) throws FileNotFoundException {
    File file = new File(path);
    if(!file.exists()){
      throw new FileNotFoundException(String.format("Path = %s is not found",path));
    }
    file.delete();

  }


}
