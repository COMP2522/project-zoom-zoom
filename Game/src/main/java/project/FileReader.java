package project;

import java.io.File;

/**
 * ReadFiles, class used to read files from folders to increase
 *  code readability.
 *
 * @author James Langille
 */
public class FileReader {

  private static File directory;
  private static File[] files;
  private static String[] fileNames;


  /**
   * readFiles, reads the files from the folder name into
   *  a File array and creates the string array for file names.
   *
   * @param folderName root folder
   */
  public static void readFiles(String folderName) {
    directory = new File(folderName);
    files = directory.listFiles();
    fileNames = new String[files.length];
  }

  /**
   * engineImages, get all files from images folders
   *  containing images of engines
   *
   * @return list of engine image file names
   */
  public static String[] engineImages() {
    int i = 0;
    for (int j = 0; j < files.length; j++) {
      if (files[j].getName().contains("engine")) {
        fileNames[i] = files[j].getName();
        i++;
      }
    }
    return fileNames;
  }

  /**
   * chassisImages, get all files from images folders
   *  containing images of chassis
   *
   * @return list of chassis image file names
   */
  public static String[] chassisImages() {
    int i = 0;
    for (int j = 0; j < files.length; j++) {
      if (files[j].getName().contains("chassis")) {
        fileNames[i] = files[j].getName();
        i++;
      }
    }
    return fileNames;
  }

  /**
   * aerodynamicsImages, get all files from images folders
   *  containing images of aerodynamics
   *
   * @return list of aerodynamics image file names
   */
  public static String[] aerodynamicsImages() {
    int i = 0;
    for (int j = 0; j < files.length; j++) {
      if (files[j].getName().contains("aero")) {
        fileNames[i] = files[j].getName();
        i++;
      }
    }
    return fileNames;
  }

  /**
   * carModTitles, get all files from images folder
   *  containing titles used in CarModMenu
   * @return list of title image file names
   */
  public static String[] carModTitles() {
    int i = 0;
    for (int j = 0; j < files.length; j++) {
      if (files[j].getName().contains("partTitle")) {
        fileNames[i] = files[j].getName();
        i++;
      }
    }
    return fileNames;
  }



}
