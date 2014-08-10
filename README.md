pdfngreport
===========

This is the pdf report plugin written for testng, this listner will generate the pdf report on testcases run, its very simple to configure no need to write any code.

<h3>An PDF reporting plugin for TestNG </h3>

<h4>How to use pdfngreport Plugin</h4>
====================================

Download latest version of pdfreport <a href="https://github.com/uttesh/mavenrepos/raw/master/com/uttesh/pdfngreport/1.0.0/pdfngreport-1.0.0.jar">download</a>

<h4>Environment and dependent jar file</h4>
===========================================

1. Minimum JDK 1.5 or higher
2. Jfree jar 
3. Itext jar

for Maven add below jfree and itext dependents

```
       <dependency>
            <groupId>jfree</groupId>
            <artifactId>jfreechart</artifactId>
            <version>1.0.13</version>
        </dependency>
        
        <dependency>
            <groupId>com.lowagie</groupId>
            <artifactId>itext</artifactId>
            <version>4.2.1</version>
        </dependency>
```


To use the reporting plug-in, set the "listeners" attribute of the "testng"
element in your Ant build file.The class names for the pdfreport is:

```
  com.uttesh.pdfngreport.PDFReportListener
  
```

You may also want to disable the default TestNG reporters by setting the
"useDefaultListeners" attribute to "false".

Your Ant task will probably look something like this:

```
  <testng classpathref="test-path"
          outputdir="${test-results.dir}"
          haltonfailure="true"
          useDefaultListeners="false"
          listeners="com.uttesh.pdfngreport.PDFReportListener">
    <sysproperty key="pdfreport.title" value="My Test Report"/>
  </testng>
 ``` 
 
 POM testng configuration with pdfngreport plugin paramerters
 
 ``` 
              <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>2.17</version>
                    <configuration>
                        <suiteXmlFiles>
                            <suiteXmlFile>testng.xml</suiteXmlFile>
                        </suiteXmlFiles>
                        <systemPropertyVariables>
                            <pdfreport.title>Testing Title</pdfreport.title>
                            <pdfreport.chart>show</pdfreport.chart>
                        </systemPropertyVariables>
                        <properties>
                            <property>
                                <name>outputdir</name>
                                <value>D:\maven\automation_report</value> 
                            </property>
                            <property>
                                <name>usedefaultlisteners</name>
                                <value>false</value> <!-- disabling default listeners is optional -->
                            </property>
                            <property>
                                <name>listener</name>
                                <value>com.uttesh.pdfngreport.PDFReportListener</value>
                            </property>
                            <property>
                                <name>reporter</name>
                                <value>listenReport.Reporter</value>
                            </property>
                        </properties>
                    </configuration>
                </plugin>
 ``` 

For Maven pom.xml configuration is add the dependecy.

 ``` 
        <dependency>
            <groupId>com.uttesh</groupId>
            <artifactId>pdfngreport</artifactId>
            <version>1.0.0</version>
        </dependency> 
 ```
          
and add reporsitory in <repositories>

        <repository>
            <id>git-uttesh</id>
            <name>uttesh's Git based repo</name>
            <url>https://github.com/uttesh/mavenrepos/raw/master</url>
        </repository>

we can configure the listner in the testng suite xml file of application also.

```
<?xml version="1.0" encoding="UTF-8"?>
<suite name="Simple Reporter Suite">
  <listeners>
    <listener class-name="com.uttesh.pdfngreport.PDFReportListener" />
  </listeners>

  <test name="Simple Reporter test">
    <classes>
      <class name="xyz" />
      <class name="abc" />
    </classes>
  </test>
</suite>
```

contributions
=============

First of all price goes to <a href="http://www.jfree.org/jfreechart/">jfree</a> and <a href="http://itextpdf.com/">itext</a> open source jar file which i used to generate the pdf report and pie chart statistic graph.

Developer : [@uttesh](https://twitter.com/uttesh) ( my self ;) )



