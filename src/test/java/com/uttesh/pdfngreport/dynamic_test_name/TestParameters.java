package com.uttesh.pdfngreport.dynamic_test_name;

public class TestParameters {

    private String testName = null;
    private String testDescription = null;

    public TestParameters(String name,
            String description) {
        this.testName = name;
        this.testDescription = description;
    }

    public String getTestName() {
        return testName;
    }

    public String getTestDescription() {
        return testDescription;
    }
}
