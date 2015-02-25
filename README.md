pdfngreport (An PDF reporting plugin for TestNG )  [![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/uttesh/pdfngreport/trend.png)](https://bitdeli.com/free "Bitdeli Badge")
===========
This is the part of maven repository now,Directly add in pom following.

        <dependency>
            <groupId>com.uttesh</groupId>
            <artifactId>pdfngreport</artifactId>
            <version>1.0.0</version>
        </dependency>
        
This is the pdf report plugin written for testng, this listener will generate the pdf report on testcases run, its very simple to configure no need to write any code.

<b>How to use pdfngreport Plugin</b>
<hr/>

Download latest version of pdfreport <a href="https://github.com/uttesh/mavenrepos/raw/master/com/uttesh/pdfngreport/1.0.0/pdfngreport-1.0.0.jar">download</a>

Sample demo example source <a href="https://github.com/uttesh/pdfngreportdemo">download sample</a>

PDF report sample <a href="https://github.com/uttesh/pdfngreportdemo/raw/master/automation_report/pdfng_report.pdf">report</a>

 pdf report preview snaps :
 
![demo](https://raw.github.com/uttesh/pdfngreportdemo/master/report_snaps/pdfngreport_1.png)
![demo](https://raw.github.com/uttesh/pdfngreportdemo/master/report_snaps/pdfngreport_2.png)
![demo](https://raw.github.com/uttesh/pdfngreportdemo/master/report_snaps/pdfngreport_3.png)

<b>Environment and dependent jar file</b>
<hr/>

1. Minimum JDK 1.5 or higher
2. Jfree jar 
3. Itext jar


<b> ANT users configuration </b>
<hr/>
Download latest jfree and itext jars from respective links and keep in lib.

ANT/MAVEN sample demo example <a href="https://github.com/uttesh/pdfngreportdemo" target"_blank">demo source</a>

configure build.xml file with below testng tag

```
  <testng classpathref="test-path"
          outputdir="${test-results.dir}"
          haltonfailure="true"
          useDefaultListeners="false"
          listeners="com.uttesh.pdfngreport.PDFReportListener">
    <sysproperty key="pdfreport.title" value="My Test Report"/>
    <sysproperty key="pdfreport.chart" value="show"/>
    <sysproperty key="pdfreport.outputdir" value="// system path to save generatef pdf report"/>
  </testng>
 ``` 
 If linteners configured in ant build.xml then no need configure/set listener in testsuit xml, if configured both placess it will call PDFReportListener class two times.


<b> MAVEN users configuration </b>
<hr/>

Add below jfree and itext dependencies in pom.xml

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
                            <pdfreport.logger>true</pdfreport.logger>
                            <pdfreport.outputdir>E:\rivetsys\automation</pdfreport.outputdir>
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
                            <!-- if testng suit xml file is configured with listener no need here -->
<!--                            <property>
                                <name>listener</name>
                                <value>com.uttesh.pdfngreport.PDFReportListener</value>
                            </property>
                            <property>
                                <name>reporter</name>
                                <value>listenReport.Reporter</value>
                            </property>-->
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
            <version>1.0.0</version>
        </dependency> 
 ```
          
and add repository in <repositories>

        <repository>
            <id>git-uttesh</id>
            <name>uttesh's Git based repo</name>
            <url>https://github.com/uttesh/mavenrepos/raw/master</url>
        </repository>

<b>testng suit xml file </b>
<hr/>

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
<b> reporting property setting in ant/maven</b>

ANT :
```
    <sysproperty key="pdfreport.title" value="My Test Report"/>(Required)
    <sysproperty key="pdfreport.outputdir" value="file:// system path to save generatef pdf report"/>(Required)
    <sysproperty key="pdfreport.chart" value="show"/>(Optional)
    <sysproperty key="pdfreport.logger" value="true"/>(Optional)
```    
MAVEN :
```
    <systemPropertyVariables>
        <pdfreport.title>Testing Title</pdfreport.title>
        <pdfreport.chart>show</pdfreport.chart>
        <pdfreport.logger>true</pdfreport.logger>
        <pdfreport.outputdir>file:// system path to save generatef pdf report</pdfreport.outputdir>
    </systemPropertyVariables>
```
contributions
=============

All credit goes to <a href="http://www.jfree.org/jfreechart/">jfree</a> and <a href="http://itextpdf.com/">itext</a> open source jar file which i used to generate the pdf report and pie chart statistic graph.

PDFngreport Developer : [@uttesh](https://twitter.com/uttesh) ( my self ;) )

<h3>
<a name="license" class="anchor" href="#license"><span class="mini-icon mini-icon-link"></span></a>License</h3>

<p>(The MIT license)</p>

<p>Copyright (c) 2013 Uttesh Kumar</p>

<p>Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:</p>

<p>The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.</p>

<p>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.</p></article>


