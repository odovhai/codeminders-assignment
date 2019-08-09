package com.codeminders.assignment;

import static com.codeminders.assignment.util.FileLinesCounter.calculateLinesWithCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilesCodeLinesCounterDemo {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter full file name or path to a directory: ");
    String path = scanner.nextLine();

    try {
      listFilesLinesCount(path, new ArrayList<>(), 0);
    } catch (FileNotFoundException e) {
      System.out.println(String.format("Could not find file: [%s]", path));
      e.printStackTrace();
    }
  }

  private static void listFilesLinesCount(String directoryName, List<File> files, int nesting)
      throws FileNotFoundException {
    File currentDirectory = new File(directoryName);

    File[] fileList = currentDirectory.listFiles();
    if (null == fileList) {
      if (currentDirectory.isFile()) {
        System.out.println(
            currentDirectory.getName()
                + ": "
                + calculateLinesWithCode(currentDirectory.getAbsolutePath()));
      }
      return;
    }
    for (File file : fileList) {
      printIndent(nesting);
      if (file.isFile()) {
        int linesCount = calculateLinesWithCode(file.getAbsolutePath());
        System.out.println(file.getName() + ": " + linesCount);
        files.add(file);
      } else if (file.isDirectory()) {
        System.out.println(file.getName() + ": ");
        listFilesLinesCount(file.getAbsolutePath(), files, nesting + 1);
      }
    }
  }

  private static void printIndent(int nesting) {
    for (int i = 0; i < nesting; i++) {
      System.out.print(" ");
    }
  }
}
