package com.uttesh.pdfngreport;


import org.testng.Assert;
import static org.testng.Assert.assertTrue;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class AmazonTests {



        @Test(testName = "testing the testname attribute 1 ",description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut porttitor ut augue ut sodales. Vivamus maximus arcu ac odio vehicula, convallis fringilla arcu malesuada. Aenean eleifend in sem at ultricies. Proin molestie bibendum diam, ac tincidunt neque ornare non. Mauris ultricies est vel turpis volutpat, quis imperdiet leo sodales. Fusce ullamcorper lacus eros, rutrum consequat erat tempor vel. Suspendisse finibus sapien hendrerit rhoncus tempor. Nam congue tellus in aliquam bibendum")
	public void Page1Test() {
            Assert.assertEquals(true,false);
	}
        
        @Test(testName = "testing the testname attribute 2 ",description = "test description")
	public void Page2Test() {
            Reporter.log("M3 WAS CALLED");
            assertTrue(true);
	}
        
        @Test
	public void Page3Test()  {
            Reporter.log("Page3Test WAS CALLED");
            assertTrue(true);
	}

        @Test(testName = "testing the testname attribute 3 ",description = "test description")
	public void Page4Test() {
           throw new SkipException("Skipping - This is not ready for testing ");
	}

}
