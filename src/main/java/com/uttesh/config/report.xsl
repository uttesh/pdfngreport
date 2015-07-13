<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
	<xsl:output indent="yes" />
	<xsl:strip-space elements="*" />
	<xsl:template match="/">
		<fo:root>
                 
                    
			<fo:layout-master-set>
				<fo:simple-page-master master-name="rest"
					page-height="150mm" margin-left="3mm" margin-right="3mm"
					margin-top="3mm" margin-bottom="3mm">
					<fo:region-body region-name="xsl-region-body"
						margin-top="10mm" margin-bottom="5mm" />
					<fo:region-before region-name="xsl-region-before"
						extent="10cm" />
					<fo:region-after region-name="xsl-region-after"
						extent="5mm" margin-bottom="5mm" />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence initial-page-number="1" language="en"
				country="us" master-reference="rest">
				<fo:static-content flow-name="xsl-region-before">
                                    <fo:block-container position="absolute" font-weight="bold" color="#2580BC" line-height="5mm"
											font-family="Arial, Helvetica, Gyosho, Trado, sans-serif"
											padding-top="1cm" font-size="18pt"
											text-align="left"  margin-left="2mm">
                                        
                                        <fo:block text-align="{ReportData/TitleAlign}" padding-top="5mm">
                                             <xsl:value-of select="ReportData/ReportTitle" text-align="center"/>
                                        </fo:block>
                                        <fo:block text-align="{ReportData/TitleAlign}" margin-right="0mm" font-weight="bold"
                                                  font-family="Arial, Helvetica, Gyosho, Trado, sans-serif"
                                                  color="#999999" padding-bottom="1%" font-size="8pt">
                                            <xsl:choose>
                                                <xsl:when test="(ReportData/BuildSystemDetailBy)='manual'">
                                                    <xsl:value-of select="ReportData/BuildVersion" />
                                                    <fo:block></fo:block>
                                                    <xsl:value-of select="ReportData/ADDITIONAL_LINE1" />
                                                    <fo:block></fo:block>
                                                    <xsl:value-of select="ReportData/ADDITIONAL_LINE2" />
                                                    <fo:block></fo:block>
                                                    <xsl:value-of select="ReportData/ADDITIONAL_LINE3" />
                                                    <fo:block></fo:block>
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    <xsl:if test="(ReportData/BuildSystemDetailBy)='code'">
                                                    <fo:block padding-top="4%">
                                                    <fo:external-graphic content-height="scale-to-fit" height="12pt" padding-right="1mm"  content-width="25pt"  src="data:image/png;base64,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">
                                                    </fo:external-graphic>
                                                        <xsl:value-of select="ReportData/BuildSystemDetails" />
                                                    </fo:block>
                                                    </xsl:if>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                        </fo:block>
                                        
                                    </fo:block-container>
                                    <xsl:if test="(ReportData/Logo)='show'">
                                    <fo:block-container position="absolute">
                                        <fo:block text-align="{ReportData/LogoAlign}"  margin-right="4mm">
                                            <xsl:choose>
                                                <xsl:when test="(ReportData/OSName)='w'">
                                                    <fo:external-graphic content-width="2in" src="url(file:/{ReportData/LogoFile})" content-height="2cm" />
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    <fo:external-graphic content-width="2in" src="{ReportData/LogoFile}" content-height="2cm" />
                                                </xsl:otherwise>
                                            </xsl:choose>
                                        </fo:block>
                                    </fo:block-container>
                                    </xsl:if>
				</fo:static-content>

				<fo:static-content flow-name="xsl-region-after" >
					<fo:block font-size="10pt" >
						<fo:table width="100%" table-layout="fixed"
							border-top-color="black" border-top-width=".5pt"
							border-top-style="solid">
							<fo:table-column column-width="6mm" />
							<fo:table-column column-width="19.2cm" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-weight="normal"
											font-family="Arial, Helvetica, Gyosho, Trado, sans-serif"
											padding-top="5%" padding-bottom="5%" font-size="10pt"
											text-align="center" >
											<fo:page-number />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block font-weight="normal"
											font-family="Arial, Helvetica, Gyosho, Trado, sans-serif"
											font-size="6.5pt" padding-top="1%" margin-left="2mm"
											text-align="left">
											generated by <fo:basic-link external-destination="http://uttesh.github.io/pdfngreport/" show-destination="new">pdfngreport </fo:basic-link>
											<xsl:value-of select="ReportData/CreatedBy" />
											on
											<xsl:value-of
												select="java:format(java:java.text.SimpleDateFormat.new
												('dd MMM yyyy h:mm:ss a'), java:java.util.Date.new())" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<xsl:apply-templates />
					<fo:block id="end"></fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
        
	<xsl:template match="ReportData" >

		<xsl:for-each select="Table">
                    <fo:block font-size="10pt" margin-top="1.5cm">
						<fo:table width="100%" table-layout="fixed" >
							<fo:table-column 
								column-width="20cm" />
							<fo:table-body background-color="{TableHeaderColor}">
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-weight="bold"
											font-family="Arial, Helvetica, Gyosho, Trado, sans-serif"
											 padding-bottom="1.3%" font-size="10pt"
											text-align="left" color="#fff" margin-left="2mm">
											<fo:external-graphic content-height="scale-to-fit" height="15pt" margin-right="2mm"   content-width="25pt" src="{TableHeaderIcon}"/>
                                                                                <xsl:value-of select="TableName" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
                    </fo:block>
			<fo:table space-after="4pt" table-layout="fixed" width="98.1%"
				table-omit-header-at-break="false">
				<fo:table-header>
					<fo:table-row>
						<xsl:apply-templates select="ColumnHeaders/ColumnHeader" />
					</fo:table-row>
				</fo:table-header>
				<fo:table-body>
					<xsl:apply-templates select="Rows/Row" />
				</fo:table-body>
			</fo:table>
                    <xsl:if test="TableName = 'Statistics'">
                     <fo:block font-size="10pt" margin-top="10pt">
                         <xsl:choose>
                             <xsl:when test="OSName='w'">
                                 <fo:external-graphic src="url(file:/{ReportLocation}/chart.png)"  content-height="scale-to-fit" scaling="non-uniform"/>
                             </xsl:when>
                             <xsl:otherwise>
                                 <fo:external-graphic src="{ReportLocation}/chart.png"  content-height="scale-to-fit" scaling="non-uniform"/>
                             </xsl:otherwise>
                         </xsl:choose>
                     </fo:block>
                    </xsl:if>
			<xsl:if test="position() != last()">
				<fo:block break-after="page" />
			</xsl:if>
		</xsl:for-each>
                
                <xsl:if test="ExceptionPage = 'show'">
                    <fo:block font-size="10pt" >
                        <fo:block break-after="page" />
                        <fo:table width="100%" table-layout="fixed" margin-top="1.2cm">
                            <fo:table-column  column-width="20cm" />
                            <fo:table-body background-color="#D54125">
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"
                                              font-family="Arial, Helvetica, Gyosho, Trado, sans-serif"
                                              padding-top="1.5%" padding-bottom="1.3%" font-size="10pt"
                                              text-align="left" color="#fff" margin-left="2mm">
                                            <fo:external-graphic content-height="scale-to-fit" height="15pt" margin-right="2mm"  content-width="25pt" src="{ExceptionIcon}"/>
                                            Exception Summary
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>      
                    </fo:block>
                     
                    <xsl:for-each select="ExceptionMeta">
                        <fo:block id="{ErrorCode}" 
                         font-family="Arial, Helvetica, Gyosho, Trado, sans-serif"
                         padding-top="1.5%" padding-bottom="1.3%" font-size="10pt"
                         text-align="left" margin-left=".2mm" margin-right="3.6mm" border=".5pt"
                         border-style="solid" border-color="black" padding="1mm">
                            <fo:inline border-after-width=".5pt" color="red" border-after-style="solid">
                                <xsl:value-of select="Heading"/>
                            </fo:inline>
                            <fo:block></fo:block>
                            <xsl:value-of select="Description"/>
                        </fo:block>
                        <fo:block>
                            <fo:leader leader-pattern="rule" leader-length="19cm" />
                        </fo:block>
                    </xsl:for-each>
                </xsl:if>
	</xsl:template>
	<xsl:template match="ColumnHeader">
		<fo:table-cell border-bottom-color="black" background-color="{ColorCode}" 
			border-top-color="black" border-right-color="black" display-align="center"
			padding-top="1.5%" padding-bottom="1.3%" border-bottom-width=".5pt"
			border-top-width=".5pt" border-top-style="solid" border-bottom-style="solid">
			<fo:block color="white"
				font-family="Arial, Helvetica, Gyosho, Trado, sans-serif" font-size="8pt"
				vertical-align="bottom" text-align="left" font-weight="bold" margin-left="2mm">
				<xsl:value-of select="Name" />
			</fo:block>
		</fo:table-cell>

	</xsl:template>
	<xsl:template match="Row">
		<xsl:if test="(position() mod 2) = 1">
			<fo:table-row>
				<xsl:apply-templates />
			</fo:table-row>
		</xsl:if>
		<xsl:if test="(position() mod 2) = 0">
			<fo:table-row background-color="#F2F2F2">
				<xsl:apply-templates  />
			</fo:table-row>
		</xsl:if>
	</xsl:template>
	<xsl:template match="RowMeta">
            
                <fo:table-cell border-bottom-width=".5pt"
			border-bottom-style="solid" border-bottom-color="black" padding="1mm" margin-left="2mm">
                        <fo:block font-size="7.5pt"
                            font-family="Arial, Helvetica, Gyosho, Trado, sans-serif"
                            font-style="normal" text-align="left" wrap-option="wrap">
                            
                             <xsl:choose>
                                    <xsl:when test="TableName = 'Statistics'">
                                      <xsl:value-of select="PASSED"/>
                                    </xsl:when>
                                    <xsl:otherwise>
                                     <xsl:value-of select="TIME"/>
                                    </xsl:otherwise>
                             </xsl:choose>
                       </fo:block>
		</fo:table-cell>
                <fo:table-cell border-bottom-width=".5pt"
			border-bottom-style="solid" border-bottom-color="black" padding="1mm" margin-left="2mm">
			<fo:block font-size="7.5pt"
				font-family="Arial, Helvetica, Gyosho, Trado, sans-serif"
				font-style="normal" text-align="left" wrap-option="wrap">
                            <xsl:choose>
                                    <xsl:when test="TableName = 'Statistics'">
                                      <xsl:value-of select="SKIPPED"/>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="CLASSNAME"/>
                                    </xsl:otherwise>
                             </xsl:choose>
                          
			</fo:block>
		</fo:table-cell>
                <fo:table-cell border-bottom-width=".5pt"
			border-bottom-style="solid" border-bottom-color="black" padding="1mm" margin-left="2mm">
			<fo:block font-size="7.5pt"
				font-family="Arial, Helvetica, Gyosho, Trado, sans-serif"
				font-style="normal" text-align="left" wrap-option="wrap">
                             <xsl:choose>
                                    <xsl:when test="TableName = 'Statistics'">
                                       <xsl:value-of select="FAILED"/> 
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="METHOD"/>
                                    </xsl:otherwise>
                             </xsl:choose>
                         
			</fo:block>
		</fo:table-cell>
                <fo:table-cell border-bottom-width=".5pt"
			border-bottom-style="solid" border-bottom-color="black" padding="1mm" margin-left="2mm">
			<fo:block font-size="7.5pt"
				font-family="Arial, Helvetica, Gyosho, Trado, sans-serif"
				font-style="normal" text-align="left" wrap-option="wrap">
                            <xsl:choose>
                                    <xsl:when test="TableName = 'Statistics'">
                                       <xsl:value-of select="PERCENTAGE"/> 
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="TIMETAKEN"/>
                                    </xsl:otherwise>
                             </xsl:choose>
                          
			</fo:block>
		</fo:table-cell>
                <xsl:if test="ExceptionPage = 'show'">
                    <xsl:if test="STATUS='FAILED'">
                        <fo:table-cell border-bottom-width=".5pt"
                                   border-bottom-style="solid" border-bottom-color="black" padding="1mm" margin-left="2mm">
                            <fo:block font-size="7.5pt"
                                                  font-family="Arial, Helvetica, Gyosho, Trado, sans-serif"
                                                  font-style="italic" text-align="left">
                                <fo:basic-link internal-destination="{BLOCKID}" show-destination="new">
                                    <fo:external-graphic content-height="scale-to-fit" height="12pt" margin-right="2mm"  content-width="25pt"  src="data:image/jpeg;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACABAMAAAAxEHz4AAAAMFBMVEUAAAD///+cpLDIzNPT19yRmqj09fa9wst7hpYAAAAAAAAAAAAAAAAAAAAAAAAAAABeYK5aAAAAAXRSTlMAQObYZgAAARhJREFUeNpjYBgFo2AUjIJRAAOMuCQE0fjvcahjotQFA28AWhgwGd0loMHo7gM8LmCNJ6Sf4dy3ABQ+MwovfwVhN/97/hWnF1h5iPK2/QZcXmAkLtwEcOpR/EBcwL/D5QIBMmKOYgMY0dM/LM03TkDRxP4CoYb1FTEpsQ2VG4HE/k1UUv6ZgMxjm0B6XpiejEgfZpnEBYggnnyPS80wKA9GDRg1AARYiKsZcdeOwzcM3o+mg9F0MJoORtPBaDoYTQej6WA0HYzkdDBqwKgBGHnB4AL2kgANMON0AXFDKAz/aWfAU+IM8MNpwH/iDPiA04DfCcToZ9uAOxqnBBDWz2qJewiEiOFAVl204cBRMApGwSgYBaMAGQAAyJ4yiurAIEIAAAAASUVORK5CYII="/>
                                </fo:basic-link>
                                <xsl:if test="SHOW_SCREEN_SHOT_LINK = 'show'">
                                    <fo:basic-link external-destination="{FAILED_SCREEN_SHOT_LOCATION}//{BLOCKID}.png" show-destination="new">
                                        <fo:external-graphic content-height="scale-to-fit" height="15pt" margin-right="2mm"  content-width="25pt" src="data:image/jpeg;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACABAMAAAAxEHz4AAAAMFBMVEUAAACcpLDIzNPT19z///+Rmqj09fa9wst7hpbe4eVwe42nrrmyuMKGkJ/p6+5lcYXfCGFQAAAAAXRSTlMAQObYZgAAAedJREFUeNpjYBgFo2AUjIJRMAoQgBGNq6RIQMO7ex/wGMAa/4CglUxcG1C4KJL5hPUz/PuOwmVGcQAHMb7+r3cDlwsYiQs3AZxe4CPOgIc4DRAgI+YoNoAF3YA9BHS7oAU8ExmJ7x+lBjCMGjBSDeAoNm+gyICci7zHKTGA/QIDw98CCgyoBRE3KDAAXBD/p8CAAyDiz0BGowNG2UOiAQK4y2ziDGgGERoUGPDTAFgSTqAkEKfoB1til2EhzoAfvSOzPODabHyWEgO4tCYKlr+lwAAvUPILLyDbAB5IW+om2QZ4QKjfBWQawAVrzF0k0wB46cHcQJ4BfXDWA7IMYEc0Jl+SZYAvgkkgGJnwByEIXCXDAJQCmHEB6Qb0obSpHCB0u1EAsQawo7THGZIhSXOv0M8PRBrgi9awBJuXgT08mQgFIQjMhdav2KKUiVAQggtlYDDmgqP0AFEG9GE0TT8wcF4As3qJMQAtCEFgE0M31C3EGOCLxaM7oYb+UyBsABeWuII5gIFBl7ABf/BXcoQN6CO+qY/VACxBSFpm8qW0Yvkw2tgeNWDUAMoNIDIj/R/EBnwizgB5nAb8J86ADzgN+G1AjH5mPGNpExWIiDdO3GNCRAwHMr2594FhFIyCUTAKRsEowAUAuqVjKkMvEHEAAAAASUVORK5CYII="/>
                                    </fo:basic-link>
                                </xsl:if>
                            </fo:block>
                        </fo:table-cell>
                    </xsl:if>
                </xsl:if>
	</xsl:template>
        
        
</xsl:stylesheet>