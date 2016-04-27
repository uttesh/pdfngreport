package com.uttesh.pdfngreport.dynamic_test_name;

import java.lang.reflect.Method;
import static org.testng.Assert.assertTrue;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicDataProviderTestName implements ITest {

    // Has to be set to prevent NullPointerException from reporters
    protected String mTestCaseName = "";

    @DataProvider(name = "BasicDataProvider")
    public Object[][] getTestData() {
        Object[][] data = new Object[][]{
            {new TestParameters("TestCase1", "Sample test 1")},
            {new TestParameters("TestCase2", "Sample test 2")},
            {new TestParameters("TestCase3", "Sample test 3")},
            {new TestParameters("TestCase4", "Sample test 4")},
            {new TestParameters("TestCase5", "Sample test 5"),}
        };
        return data;
    }

    @DataProvider(name = "BasicDataProvider1")
    public Object[][] getTestData1() {
        Object[][] data = new Object[][]{
            {"9798 hale dr", "63123.0", "Firefox", "choose_103111010", "$96.96"},
            {"9798 hale dr", "63123.0", "Firefox", "choose_103111010", "$96.96"},};
        return data;
    }

    @BeforeMethod(alwaysRun = true)
    public void testData(Method method, Object[] testData) {
        String testCase = "";
        if (testData != null && testData.length > 0) {
            TestParameters testParams = null;
            String _dyna_name = null;
            //Check if test method has actually received required parameters
            for (Object testParameter : testData) {
                if (testParameter instanceof TestParameters) {
                    testParams = (TestParameters) testParameter;
                    break;
                }
                if (testParameter instanceof String) {
                    _dyna_name = (String) testParameter;
                    break;
                }
            }
            if (testParams != null) {
                testCase = testParams.getTestName();
            }
            if(_dyna_name!=null){
                testCase = _dyna_name;
            }
        }
        this.mTestCaseName = String.format("%s(%s)", method.getName(), testCase);
    }

    @Override
    public String getTestName() {
        return this.mTestCaseName;
    }

    @Test(dataProvider = "BasicDataProvider")
    public void testSample1(TestParameters testParams) {
        assertTrue(true);
    }
    
    @Test(dataProvider = "BasicDataProvider1")
    public void testSample4444(String address,String zipcode,String browserType,String packageId,String monthlycost) {
        assertTrue(true);
    }

    @Test(dataProvider = "BasicDataProvider")
    public void testSample2(TestParameters testParams) {
        assertTrue(true);
    }

    @Test
    public void testSample3() {
        assertTrue(true);
    }
}
