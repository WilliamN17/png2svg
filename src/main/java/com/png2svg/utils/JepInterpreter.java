package com.png2svg.utils;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import jep.Interpreter;
import jep.JepConfig;
import jep.MainInterpreter;

public final class JepInterpreter {

  private String packagePython;
  private Interpreter subInterp;
  private String pathExecutor;
  private jep.JepConfig jepConf;

  public JepInterpreter() {
  }

  public void loadInterpreterPython(String path){
    MainInterpreter.setJepLibraryPath(path);
    // set path for python docs with python script to run
    jepConf = new JepConfig();
    jepConf.addIncludePaths(path);
    this.pathExecutor = path;
  }

  public void createInterpreter(){
    subInterp = jepConf.createSubInterpreter();
  }

  public void shutdown(){
    subInterp.close();;
  }

  public void execute(String importPackage,String command) throws FileNotFoundException {
    Path pathOfPy = Path.of(this.pathExecutor);
    if (Files.exists(pathOfPy)){
      subInterp.eval(importPackage);
      subInterp.eval("command");
    }else{
      throw new FileNotFoundException();
    }
  }



  private String getDefaultPackagePython(){
    return System.getProperty("user.dir")+"/src/main/python/";
  }

  public String getPackagePython() {
    return packagePython;
  }

  public void setPackagePython(String packagePython) {
    this.packagePython = packagePython;
  }
}
