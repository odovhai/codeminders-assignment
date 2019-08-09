package com.codeminders.assignment;

import static com.codeminders.assignment.util.FileLinesCounter.calculateLinesWithCode;

import com.codeminders.assignment.file.FileNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilesCodeLinesCounterDemo {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter full file name or path to a Directory: ");
    String path = scanner.nextLine();

    FilesCodeLinesCounterDemo tmp = new FilesCodeLinesCounterDemo();

    try {
      FileNode fileNode = tmp.listFilesLinesCount(path, new ArrayList<>(), 0);
      System.out.println(fileNode.toString());
    } catch (FileNotFoundException e) {
      System.out.println(String.format("Could not find file: [%s]", path));
      e.printStackTrace();
    }
  }

  private FileNode listFilesLinesCount(String directoryName, List<File> files, int nesting)
      throws FileNotFoundException {
    File currentDirectory = new File(directoryName);

    File[] fileList = currentDirectory.listFiles();
    FileNode resultNode = FileNode.createDirectory(currentDirectory.getName());
    if (null == fileList) {
      if (currentDirectory.isFile()) {
        calculateLinesWithCode(currentDirectory.getAbsolutePath());
      }
      return resultNode;
    }

    for (File file : fileList) {
      if (file.isFile()) {
        int linesCount = calculateLinesWithCode(file.getAbsolutePath());
        files.add(file);
        FileNode newLeaf = FileNode.createFile(file.getName(), linesCount);
        resultNode.addLeaf(newLeaf);
      } else if (file.isDirectory()) {
        resultNode.addChild(listFilesLinesCount(file.getAbsolutePath(), files, nesting + 1));
      }
    }
    return resultNode;
  }


}
