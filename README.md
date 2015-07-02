pdfngreport (PDF report plugin for TestNG) 
==========================================

[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/uttesh/pdfngreport/trend.png)](https://bitdeli.com/free "Bitdeli Badge")
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.uttesh/pdfngreport/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.uttesh/pdfngreport/)
[![Build Status](https://travis-ci.org/uttesh/pdfngreport.svg)](https://travis-ci.org/uttesh/pdfngreport)

[![Support via Gratipay](https://cdn.rawgit.com/gratipay/gratipay-badge/2.3.0/dist/gratipay.png)](https://gratipay.com/uttesh/)


Join our online chat at [![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/uttesh/pdfngreport?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)


======================================================================
This is the part of maven repository now,Directly add in pom following.

        <dependency>
            <groupId>com.uttesh</groupId>
            <artifactId>pdfngreport</artifactId>
            <version>2.0.5</version>
        </dependency>
        
This is the pdf report plugin written for testng, this listener will generate the pdf report on testcases run, its very simple to configure no need to write any code.

<b>How to use pdfngreport Plugin</b>
<hr/>

Download latest version of pdfreport <a href="https://oss.sonatype.org/content/repositories/releases/com/uttesh/pdfngreport/2.0.5/">download</a>

Sample demo example source <a href="https://github.com/uttesh/pdfngreportdemo">download sample</a>

PDF report sample <a href="https://github.com/uttesh/pdfngreportdemo/raw/master/report/pdfng_report.pdf">report</a>

 pdf report preview snaps :
 
![demo](https://raw.github.com/uttesh/pdfngreportdemo/master/report_snaps/pdfngreport_1.png)
![demo](https://raw.github.com/uttesh/pdfngreportdemo/master/report_snaps/pdfngreport_2.png)
![demo](https://raw.github.com/uttesh/pdfngreportdemo/master/report_snaps/pdfngreport_3.png)

<b>Environment and dependent jar file</b>
<hr/>

1. Minimum JDK 1.6 or higher
2. Jfree jar 
3. Apache fop jar


<b> ANT users configuration </b>
<hr/>
Download latest jfree and Apache fop jars from respective links and keep in lib.

ANT/MAVEN sample demo example <a href="https://github.com/uttesh/pdfngreportdemo" target"_blank">demo source</a>

configure build.xml file with below testng tag

```
  <testng classpathref="test-path"
          outputdir="${test-results.dir}"
          haltonfailure="true"
          useDefaultListeners="false"
          listeners="com.uttesh.pdfngreport.PDFReportListener">
  </testng>
 ``` 
 If linteners configured in ant build.xml then no need configure/set listener in testsuit xml, if configured both placess it will call PDFReportListener class two times.


<b> MAVEN users configuration </b>
<hr/>

Add below jfree and apache fop dependencies in pom.xml

```
       <dependency>
            <groupId>jfree</groupId>
            <artifactId>jfreechart</artifactId>
            <version>1.0.13</version>
        </dependency>
        <dependency>
            <groupId>org.apache.xmlgraphics</groupId>
            <artifactId>fop</artifactId>
            <version>1.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.avalon.framework</groupId>
            <artifactId>avalon-framework-api</artifactId>
            <version>4.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.avalon.framework</groupId>
            <artifactId>avalon-framework-impl</artifactId>
            <version>4.3.1</version>
        </dependency>
```


To use the reporting plug-in, set the "listeners" attribute of the "testng"
element in your Ant build file.The class names for the pdfreport is:

```
  com.uttesh.pdfngreport.PDFReportListener
  
```

You may also want to disable the default TestNG reporters by setting the
"useDefaultListeners" attribute to "false".

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
                        <properties>
                            <property>
                                <name>usedefaultlisteners</name>
                                <value>false</value> <!-- disabling default listeners is optional -->
                            </property>
                        </properties>
                    </configuration>
                </plugin>
 ``` 

For Maven pom.xml configuration, Add this dependecy.

 ``` 
        <dependency>
            <groupId>com.uttesh</groupId>
            <artifactId>pdfngreport</artifactId>
            <version>2.0.5</version>
        </dependency> 
 ```

<b>testng suit xml file </b>
<hr/>

```
<?xml version="1.0" encoding="UTF-8"?>
<suite name="Simple Reporter Suite">

  <parameter name="pdfngreport-properties" value="D:\property_files\pdfngreport.properties" />
  
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
<b> set the following in the respective properties file for globel parameter pdfngreport-properties</b>
```
#Title
pdfreport.title.text=Report Title Here
pdfreport.title.align=left


#Logo
pdfreport.logo=show
pdfreport.report.logo.file=E:/rivetsys/automation/pdfnglogo/logo.png
pdfreport.report.logo.align=right

# chat related
pdfreport.pie.chart.type=normal
#pdfreport.pie.chart.type=explode

# pdf report output location
pdfreport.outputdir=E:/rivetsys/automation/pdfngreport

# Custom color setting Coming soon on 3.0.0 version


# Below setting only for selenium user for selenium failed screen shot link show related
#pdfreport.show.selenium.screenshot.link=hide
#pdfreport.selenium.failed.test.screenshot.outputdir=E:/rivetsys/automation/loan_connector_10Sep14Nova/loan_connector/screenshots

```
contributions
=============

All credit goes to <a href="http://www.jfree.org/jfreechart/">jfree</a> and <a href="https://xmlgraphics.apache.org">Apache fop</a> open source jar file which were used to generate the pdf report and pie chart statistic graph.

PDFngreport Developer : <a href="http://www.uttesh.com" target="_blank">uttesh.com</a>

<h3>
<a name="license" class="anchor" href="#license"><span class="mini-icon mini-icon-link"></span></a>License</h3>

<p>(The Apache License)</p>

<p>Copyright (c) 2013 Uttesh Kumar T.H.</p>

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.</p>




