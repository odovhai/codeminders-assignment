package com.codeminders.assignment.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class FileLinesCounter {

  private static final char SLASH = '/';
  private static final char STAR = '*';

  private FileLinesCounter() {}

  /**
   * Calculates code lines of file ignoring whitespace and commented lines.
   *
   * @param fileName full file path
   * @return number of code lines
   * @throws FileNotFoundException if could not find the file
   */
  public static int calculateLinesWithCode(String fileName) throws FileNotFoundException {
    Scanner filesScanner = new Scanner(new File(fileName));
    int linesCount = 0;
    boolean insideMultiLineComment = false;
    while (filesScanner.hasNextLine()) {
      String currentLine = filesScanner.nextLine();
      boolean lineCounted = false;
      for (int charIndex = 0; charIndex < currentLine.length(); charIndex++) {
        char currentChar = currentLine.charAt(charIndex);
        if (insideMultiLineComment) {
          if (currentChar == STAR
              && charIndex < currentLine.length() - 1
              && currentLine.charAt(charIndex + 1) == SLASH) {

            insideMultiLineComment = false;
            charIndex++;
          }
        } else {
          if (currentChar == SLASH
              && charIndex < currentLine.length() - 1
              && currentLine.charAt(charIndex + 1) == STAR) {

            insideMultiLineComment = true;
            charIndex++;

          } else if (currentChar == SLASH
              && charIndex < currentLine.length() - 1
              && currentLine.charAt(charIndex + 1) == SLASH) {

            break;

          } else if (!Character.isWhitespace(currentChar) && !lineCounted) {
            linesCount++;
            lineCounted = true;
          }
        }
      }
    }
    return linesCount;
  }
}
