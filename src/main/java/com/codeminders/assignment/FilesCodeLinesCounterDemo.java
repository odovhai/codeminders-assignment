package com.codeminders.assignment;

import com.codeminders.assignment.util.FileLinesCounter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FilesCodeLinesCounterDemo {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter full file name or path to a directory: ");
    String path = scanner.nextLine();
    try {
      int linesCount = FileLinesCounter.calculateLinesWithCode(path);
      System.out.println(String.format("%s: %d", path, linesCount));
    } catch (FileNotFoundException ex) {
      System.out.println(String.format("Could not find file: [%s]", path));
      ex.printStackTrace();
    }
  }
}
