package com.png2svg;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import jep.Interpreter;
import jep.JepConfig;
import jep.MainInterpreter;
public class Transcode {


  public Transcode() throws FileNotFoundException {
    MainInterpreter.setJepLibraryPath("/Library/Frameworks/Python.framework/Versions/3.10/lib/python3.10/site-packages/jep/libjep.jnilib");
    // set path for python docs with python script to run
    jep.JepConfig jepConf = new JepConfig();
    String path = System.getProperty("user.dir")+"/src/main/python/";
    jepConf.addIncludePaths(path);

    //create the interpreter for python executing
    try(Interpreter subInterp = jepConf.createSubInterpreter()){
      Path pathOfPy = Path.of(path);
      if (Files.exists(pathOfPy)){
        subInterp.eval("import png2svg as png2svg");
        subInterp.eval("png2svg.test()");
      }else{
        throw new FileNotFoundException();
      }
    }

  }

  public static void main(String[] args) throws FileNotFoundException {
    Transcode transcode = new Transcode();
  }

  public native int transcode(String input, String output);

}
