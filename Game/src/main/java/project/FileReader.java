package project;

import java.io.File;
import java.util.Arrays;

/**
 * ReadFiles, class used to read files from folders to increase
 *  code readability.
 *
 * @author James Langille
 */
public class FileReader {

  private static File directory;
  private static File[] files;

  /**
   * readFiles, reads the files from the folder name into
   *  a File array and creates the string array for file names.
   *
   * @param folderName root folder
   */
  public static void readFiles(String folderName) {
    directory = new File(folderName);
    files = directory.listFiles();
  }

  /**
   * engineImages, get all files from images folders
   *  containing images of engines
   *
   * @return list of engine image file names
   */
  public static String[] engineImages() {
    return Arrays.stream(files)
        .map(File::getName)
        .filter(name ->
            name.contains("engine"))
        .toArray(String[]::new);
  }

  /**
   * chassisImages, get all files from images folders
   *  containing images of chassis
   *
   * @return list of chassis image file names
   */
  public static String[] chassisImages() {
    return Arrays.stream(files)
        .map(File::getName)
        .filter(name ->
            name.contains("chassis"))
        .toArray(String[]::new);
  }

  /**
   * aerodynamicsImages, get all files from images folders
   *  containing images of aerodynamics
   *
   * @return list of aerodynamics image file names
   */
  public static String[] aerodynamicsImages() {
    return Arrays.stream(files)
        .map(File::getName)
        .filter(name ->
            name.contains("aero"))
        .toArray(String[]::new);
  }

  /**
   * carModTitles, get all files from images folder
   *  containing titles used in CarModMenu
   * @return list of title image file names
   */
  public static String[] carModTitles() {
    return Arrays.stream(files)
        .map(File::getName)
        .filter(name ->
            name.contains("partTitle"))
        .toArray(String[]::new);
  }
}
