package com.uttesh.pdfngreport;


import org.testng.Assert;
import static org.testng.Assert.assertTrue;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class AmazonTests {



        @Test(testName = "testing the testname attribute 1 ")
	public void Page1Test() {
            Assert.assertEquals(true,false);
	}
        
        @Test(testName = "testing the testname attribute 2 ")
	public void Page2Test() {
            Reporter.log("M3 WAS CALLED");
            assertTrue(true);
	}
        
        @Test
	public void Page3Test()  {
            Reporter.log("Page3Test WAS CALLED");
            assertTrue(true);
	}

        @Test(testName = "testing the testname attribute 3 ")
	public void Page4Test() {
           throw new SkipException("Skipping - This is not ready for testing ");
	}

}
