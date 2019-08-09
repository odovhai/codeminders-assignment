package com.codeminders.assignment;

import com.codeminders.assignment.file.FileNode;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FilesCodeLinesCounterDemo {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter full file name or path to a Directory: ");
    String path = scanner.nextLine();

    try {
      FileNode fileNode = FileNode.createFileNodeSystem(path);
      System.out.println(fileNode.toString());
    } catch (FileNotFoundException ex) {
      System.out.println(String.format("Could not find file: [%s]", path));
      ex.printStackTrace();
    }
  }


}
