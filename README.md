pdfngreport (PDF report plugin for TestNG) 
==========================================

[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/uttesh/pdfngreport/trend.png)](https://bitdeli.com/free "Bitdeli Badge")
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.uttesh/pdfngreport/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.uttesh/pdfngreport/)
[![Build Status](https://travis-ci.org/uttesh/pdfngreport.svg)](https://travis-ci.org/uttesh/pdfngreport)

<!--
[![Support via Gratipay](https://cdn.rawgit.com/gratipay/gratipay-badge/2.3.0/dist/gratipay.png)](https://gratipay.com/uttesh/)-->

Join our online chat at [![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/uttesh/pdfngreport?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

<h4>PDF NG Report home page <a href="http://uttesh.github.io/pdfngreport/">http://uttesh.github.io/pdfngreport/
</a></h4>

======================================================================
This is the part of maven repository now,Directly add in pom following.

        <dependency>
            <groupId>com.uttesh</groupId>
            <artifactId>pdfngreport</artifactId>
            <version>2.1.1</version>
        </dependency>
        
This is the pdf report plugin written for testng, this listener will generate the pdf report on testcases run, its very simple to configure no need to write any code.

<b>How to use pdfngreport Plugin</b>
<hr/>

Download latest version of pdfreport <a href="https://oss.sonatype.org/content/repositories/releases/com/uttesh/pdfngreport/2.1.1/">download</a>

Sample demo example source <a href="https://github.com/uttesh/pdfngreportdemo">download sample</a>

eclipse sample code <a href="https://github.com/uttesh/pdfngreport/raw/gh-pages/download/eclipse_archieve/pdfngreport_sample.zip">download eclipse sample</a>

PDF report sample <a href="https://github.com/uttesh/pdfngreportdemo/raw/master/report/pdfng_report.pdf">report</a>

 pdf report preview snaps :
 
![demo](https://raw.github.com/uttesh/pdfngreportdemo/master/report_snaps/pdfngreport_1.png)
![demo](https://raw.github.com/uttesh/pdfngreportdemo/master/report_snaps/pdfngreport_2.png)
![demo](https://raw.github.com/uttesh/pdfngreportdemo/master/report_snaps/pdfngreport_3.png)
![demo](https://raw.github.com/uttesh/pdfngreportdemo/master/report_snaps/pdfngreport_4.png)
explode pie chart
![demo](https://raw.github.com/uttesh/pdfngreportdemo/master/report_snaps/pdfngreport_5.png)

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
            <version>2.1.1</version>
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

# application build version
pdfreport.app.build.version=Build v2.0.8

# Build System Details manual/code/hide, hide is default
# enable below only for manual setting by setting code mode it will automactically populate the values in report
# manual only for the mobile/tab env related details
pdfreport.build.system.details.by=code
#pdfreport.additional.line1=OS : testOS (64 bit)
#pdfreport.additional.line2=SYSTEM : test@testingbox
#pdfreport.additional.line3=Lorem ipsum dolor sit amet


#TimeColumn Date Format
#"yyyy.MM.dd G 'at' HH:mm:ss z" | 2001.07.04 AD at 12:08:56 PDT
#"EEE, MMM d, ''yy" | Wed, Jul 4, '01
#"h:mm a"  |  12:08 PM
#"hh 'o''clock' a, zzzz" | 12 o'clock PM, Pacific Daylight Time
#"K:mm a, z" | 0:08 PM, PDT
#"yyyyy.MMMMM.dd GGG hh:mm aaa" | 02001.July.04 AD 12:08 PM
#"EEE, d MMM yyyy HH:mm:ss Z"  |  Wed, 4 Jul 2001 12:08:56 -0700
#"yyMMddHHmmssZ" | 010704120856-0700
#"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" |  2001-07-04T12:08:56.235-0700
#"yyyy-MM-dd'T'HH:mm:ss.SSSXXX" |  2001-07-04T12:08:56.235-07:00
#"YYYY-'W'ww-u" | 2001-W27-3

pdfngreport.time.column.format=MMMM dd yyyy hh:mm:sss

#Logo
pdfreport.logo=show
pdfreport.report.logo.file=E://rivetsys//automation//pdfnglogo//logo.png
pdfreport.report.logo.align=right

# chart related
pdfreport.pie.chart.type=normal
#pdfreport.pie.chart.type=explode

#report file name
pdfreport.file.name=sample_pdf_report

# pdf report output location note: use duble forward slash for the windows system
pdfreport.outputdir=E://rivetsys//automation//pdfngreport

# tables/page setting : show/hide
pdfreport.exception.page=hide

# Below setting only for selenium user for selenium failed screen shot link show related
#pdfreport.show.selenium.screenshot.link=show
#pdfreport.selenium.failed.test.screenshot.outputdir=E://rivetsys/automation//loan_connector_10Sep14Nova//loan_connector/screenshots

# error screen shot/image name standards.

#image type : png
#image name : className + "_" + methodName

# Custom color setting Coming soon on 3.0.0 version

```

## Properties

property | default | description
---------|---------|------------
`pdfreport.title.text` |  | Specifies the required report title.
`pdfreport.title.align` | left | align the title left/right
`pdfreport.app.build.version` |  | Specifies the required application build version.
`pdfreport.build.system.details.by` |  | Build System Details manual/code/hide
`pdfreport.additional.line1` |  | by setting the system details properties, we can set this for the manual system details data entry
`pdfreport.additional.line2` |  | by setting the system details properties, we can set this for the manual system details data entry
`pdfreport.additional.line3` |  | by setting the system details properties, we can set this for the manual system details data entry
`pdfngreport.time.column.format` | MMMM dd YYYY hh:mm:sss | set date format for the test case execution time display.
`pdfreport.logo` | hide | by setting this property along with below properties we can add logo image to report
`pdfreport.report.logo.file` | no default value | set the location of the logo image
`pdfreport.report.logo.align` | right | align the logo image right/left 
`pdfreport.pie.chart.type` | normal | set the values 'explode' for the ex[plode pie chart
`pdfreport.file.name` |  | set the report file name
`pdfreport.outputdir` |  | set the location where generated report will be saved


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




