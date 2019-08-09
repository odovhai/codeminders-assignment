package com.codeminders.assignment.file;

import static com.codeminders.assignment.util.FileLinesCounter.calculateLinesWithCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FileNode {
  private FileNode parent;
  private List<FileNode> leafs = new ArrayList<>();
  private List<FileNode> children = new ArrayList<>();
  private boolean isDirectory;
  private String name;
  private int linesCount;

  public static FileNode createDirectory(String name) {
    FileNode result = new FileNode(name);
    result.isDirectory = true;
    return result;
  }

  public static FileNode createFile(String name, int linesCount) {
    FileNode result = new FileNode(name);
    result.linesCount = linesCount;
    result.isDirectory = false;
    return result;
  }

  public static FileNode createFileNodeSystem(String directoryName) throws FileNotFoundException {
    return createFileNodes(directoryName, new ArrayList<>());
  }

  private static FileNode createFileNodes(String directoryName, List<File> files)
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
        resultNode.addChild(createFileNodes(file.getAbsolutePath(), files));
      }
    }
    return resultNode;
  }

  private FileNode(String name) {
    this.name = name;
  }

  public void addLeaf(FileNode node) {
    node.parent = this;
    leafs.add(node);
  }

  public void addChild(FileNode node) {
    node.parent = this;
    children.add(node);
  }

  @Override
  public String toString() {
    if (isDirectory) {
      StringBuilder result = new StringBuilder();
      if (null == parent) {
        result.append(name).append(": ").append(aggregateCodeLines(this)).append('\n');
      }
      for (FileNode child : children) {
        appendIndents(result, child);
        result
            .append(child.name)
            .append(": ")
            .append(aggregateCodeLines(child))
            .append('\n')
            .append(child.toString());
      }
      for (FileNode leaf : leafs) {
        appendIndents(result, leaf);
        result.append(leaf.name).append(": ").append(leaf.linesCount).append('\n');
      }
      return result.toString();
    }
    return name + ": " + linesCount + '\n';
  }

  private void appendIndents(StringBuilder sb, FileNode node) {
    while (null != node.parent) {
      sb.append(' ');
      node = node.parent;
    }
  }

  public int getLinesCount() {
    return linesCount;
  }

  private int aggregateCodeLines(FileNode node) {
    if (isDirectory) {
      int leafsCodeLines = node.leafs.stream().mapToInt(FileNode::getLinesCount).sum();
      int childsCodeLines =
          node.children.stream().mapToInt(value -> value.aggregateCodeLines(value)).sum();

      return leafsCodeLines + childsCodeLines;
    }

    return linesCount;
  }
}
