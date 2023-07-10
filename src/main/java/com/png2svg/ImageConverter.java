package com.png2svg;

import java.io.File;
import java.io.IOException;

public interface ImageConverter {


  byte[] transcode(File pngFile) throws IOException;

  void shutdown();
}
