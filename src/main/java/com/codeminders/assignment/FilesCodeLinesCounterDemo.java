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

    try {
      FileNode fileNode = createFileNodes(path, new ArrayList<>(), 0);
      System.out.println(fileNode.toString());
    } catch (FileNotFoundException ex) {
      System.out.println(String.format("Could not find file: [%s]", path));
      ex.printStackTrace();
    }
  }

  private static FileNode createFileNodes(String directoryName, List<File> files, int nesting)
      throws FileNotFoundException {
    File currentDirectory = new File(directoryName);

    File[] fileList = currentDirectory.listFiles();
    FileNode resultNode = FileNode.createDirectory(currentDirectory.getName());
    if (null == fileList) {
      if (currentDirectory.isFile()) {
        return FileNode.createFile(
            currentDirectory.getName(), calculateLinesWithCode(currentDirectory.getAbsolutePath()));
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
        resultNode.addChild(createFileNodes(file.getAbsolutePath(), files, nesting + 1));
      }
    }
    return resultNode;
  }
}
