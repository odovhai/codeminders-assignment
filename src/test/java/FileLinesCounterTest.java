import static com.codeminders.assignment.util.FileLinesCounter.calculateLinesWithCode;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.codeminders.assignment.file.FileNode;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Test;

public class FileLinesCounterTest {

  @Test
  public void fileLinesCounterTest() throws FileNotFoundException {
    assertThat(calculateLinesWithCode(getResourceFilePath("root/SimpleTest1.java")), is(6));
    assertThat(calculateLinesWithCode(getResourceFilePath("root/sub1/SimpleTest2.java")), is(8));
    assertThat(calculateLinesWithCode(getResourceFilePath("root/sub2/SimpleTest3.java")), is(6));
    assertThat(calculateLinesWithCode(getResourceFilePath("root/sub2/sub2_1/SimpleTest4.java")), is(5));
    assertThat(calculateLinesWithCode(getResourceFilePath("root/sub2/sub2_1/sub2_1_1/ComplexTest.java")), is(60));
  }

  @Test
  public void FileNodeSystemTest() throws FileNotFoundException {
    FileNode fileNode = FileNode.createFileNodeSystem(getResourceFilePath("root"));
    assertThat(
        fileNode.toString(),
        is(
            "root: 85\n"
                + " sub1: 8\n"
                + "  SimpleTest2.java: 8\n"
                + " sub2: 71\n"
                + "  sub2_1: 65\n"
                + "   sub2_1_1: 60\n"
                + "    ComplexTest.java: 60\n"
                + "   SimpleTest4.java: 5\n"
                + "  SimpleTest3.java: 6\n"
                + " SimpleTest1.java: 6\n"));
  }

  private String getResourceFilePath(String fileName) {
    return new File(getClass().getClassLoader().getResource(fileName).getFile()).getAbsolutePath();
  }
}
