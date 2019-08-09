package com.codeminders.assignment.file;

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
      for (FileNode child : children) {
        appendIndents(result, child);
        result.append(child.name);
        result.append(": ");
        result.append(aggregateCodeLines(child));
        result.append('\n');
        result.append(child.toString());
      }
      for (FileNode leaf : leafs) {
        appendIndents(result, leaf);
        result.append(leaf.name);
        result.append(": ");
        result.append(leaf.linesCount);
        result.append('\n');
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
