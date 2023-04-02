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
   * getImage, get all files from images folders for the parameter.
   *
   * @param image specified image
   * @return list of engine image file names
   */
  public static String[] getImageFilePath(String image) {
    return Arrays.stream(files)
        .map(File::getName)
        .filter(name -> name.contains(image))
        .toArray(String[]::new);
  }
}
