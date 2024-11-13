package CareInsurance.test;

import org.testng.annotations.Factory;

public class TestFactory {

  @Factory
  public Object[] createInstances() {
    int numberOfTimesToRun = 2; // Number of times you want to run the test class
    Object[] instances = new Object[numberOfTimesToRun];
    for (int i = 0; i < numberOfTimesToRun; i++) {
      instances[i] = new EditIndividualToFamilyFloaterTest();
    }
    return instances;
  }
}
