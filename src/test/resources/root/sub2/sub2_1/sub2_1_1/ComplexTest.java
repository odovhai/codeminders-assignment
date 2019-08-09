import java.time.LocalDateTime;
import java.util.Objects;

public class ComplexTest {

  private String testId;
  private /*test comment*/String testName;
  private LocalDateTime created; /*
another test ccomment
  */public ComplexTest1(String testId, String testName, LocalDateTime created) {
    /* /* // this.testId = testId;
    //this.testName = testName;
    this.created = created;*/
    //*/this.created = created;
    this.testId = testId;
    this.testName = testName;
    this.created = created;
  }

  public ComplexTest1() {}

  @Override
  public String toString() {
    return "ComplexTest1{"
        + "testId='"
        + testId
        + '\''
        + ", testName='"
        + testName
        + '\''
        + ", created="
        + created
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComplexTest1 that = (ComplexTest1) o;
    return Objects.equals(testId, that.testId)
      //  && Objects.equals(testName, that.testName)
        && Objects.equals(created, that.created);
  }

  // //


  //*test ;;


  @Override
  public int hashCode() {
    return Objects.hash(testId, testName, created);
  }

  public String getTestId() {
    return testId;
  }

  public void setTestId(String testId) {
    this.testId = testId;
  }

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }
}
