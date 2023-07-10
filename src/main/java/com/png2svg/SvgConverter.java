package com.png2svg;

import com.png2svg.utils.FileId;
import com.png2svg.utils.FileManagement;
import com.png2svg.utils.JepInterpreter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SvgConverter implements ImageConverter {

  private final JepInterpreter jepInterpreter;
  private final static String  SVG_EXT= ".svg";

  private final Command command = new Command();
  private final FileManagement fileManagement = new FileManagement();

  public SvgConverter(JepInterpreter jepInterpreter) {
    this.jepInterpreter = jepInterpreter;
    String path = System.getProperty("usr.dir");
    jepInterpreter.createInterpreter(path);

  }

  @Override
  public byte[] transcode(File pngFile) throws IOException {
    String svgPath = FileId.getInstance().nextId();
    String absoluteOutputPath = new StringBuilder()
        .append(pngFile.getParent())
        .append("/")
        .append(svgPath)
        .toString();
    File outputFile = File.createTempFile(absoluteOutputPath,SVG_EXT);

    String commandBuilder = command.command(pngFile.getAbsolutePath(),outputFile.getAbsolutePath());
    jepInterpreter.execute(command.importPackage(),  commandBuilder);

    return Files.readAllBytes(outputFile.toPath());
  }

  public byte[] transcode(byte[] bitmap){
    return new byte[0];
  }

  @Override
  public void shutdown() {
    jepInterpreter.shutdown();
  }


}
