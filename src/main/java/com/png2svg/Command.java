package com.png2svg;

public class Command {

  private final String IMPORT_PIXELS2SVG = "import png2svg as png2svg";
  private final String transcode = "transcode";

  public String importPackage(){
    return IMPORT_PIXELS2SVG;
  }

  public String command(String input, String output){
    StringBuilder command = new StringBuilder();
    command.append(transcode)
        .append("(")
        .append(input)
        .append(",")
        .append(output)
        .append(")");

    return command.toString();
  }
}
