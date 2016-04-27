package com.uttesh.pdfngreport;

import org.testng.Assert;
import static org.testng.Assert.assertTrue;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class GoogleTest {

    @Test(testName = "Custom Google test name1")
    public void GooglePage1Test(String name) {
        Assert.assertEquals(true, false);
    }

    @Test
    public void GooglePage2Test() {
        Reporter.log("Google WAS CALLED");
        assertTrue(true);
    }

    @Test(testName = "Custom Google test name3")
    public void GooglePage3Test() {
        throw new SkipException("Skipping - This is not ready for testing ");
    }
    

}
