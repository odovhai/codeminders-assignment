import com.codeminders.assignment.util.FileLinesCounter;
import java.io.File;
import java.io.FileNotFoundException;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class FileLinesCounterTest {

  @Test
  public void fileLinesCounterTest() throws FileNotFoundException {
    Assert.assertThat(FileLinesCounter.calculateLinesWithCode(getResourceFilePath("SimpleTest1.java")), Is.is(6));
    Assert.assertThat(FileLinesCounter.calculateLinesWithCode(getResourceFilePath("SimpleTest2.java")), Is.is(8));
    Assert.assertThat(FileLinesCounter.calculateLinesWithCode(getResourceFilePath("SimpleTest3.java")), Is.is(6));
    Assert.assertThat(FileLinesCounter.calculateLinesWithCode(getResourceFilePath("SimpleTest4.java")), Is.is(5));
    Assert.assertThat(FileLinesCounter.calculateLinesWithCode(getResourceFilePath("ComplexTest.java")), Is.is(60));
  }

  private String getResourceFilePath(String fileName) {
    return new File(getClass().getClassLoader().getResource(fileName).getFile()).getAbsolutePath();
  }
}
