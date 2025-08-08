<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="ui.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<jsp:useBean id="reportInfo" class="ui.juvenilewarrant.ReportingBean" scope="session" />
<pdf onload="pdf:ActualSize">
<head>
	<meta name="title" value="<%=PDFReport.ARRESTWARRANT_REPORT_NAME.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.ARRESTWARRANT_REPORT_NAME.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
<!-- STYLE SHEET LINK -->

<style>
span.underline
	{	border-bottom:1px solid #555;
		}
table.margin-top-8px
	{	margin-top: 8px;}
table.margin-top-12px
	{	margin-top: 12px;}
table.margin-top-24px
	{	margin-top: 24px;}
table.margin-top-30px
	{	margin-top: 30px;}
table.margin-top-40px
	{	margin-top: 40px;}
table.margin-top-50px
	{	margin-top: 50px;}
table.margin-top-60px
	{	margin-top: 60px;}
table.margin-top-70px
	{	margin-top: 70px;}
table.margin-top-80px
	{	margin-top: 80px;}
table.margin-left-12px
	{	margin-left: 12px;}
table.indent-15px
	{	text-indent:15px;}
table.indent-50px
	{	text-indent:50px;}
body
	{	margin-left: .2in;
    	margin-right: .2in;
		margin-top: .3in; 
		margin-bottom: .3in;}
div.header
	{ 	font-size:12;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
div.photo
	{	font-size:10;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		padding:11;
		text-align:left;}
div.body
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
#page1 
	{	footer:myfooter; footer-height:0.1in;}
#page2 
	{	footer:myfooter; footer-height:0.1in;}
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="left" width="50%">
						Page <pagenumber/> of <totalpages/>
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<body>
<!--  Header information -->
<div class="header">
<table width="100%" border-style="none">
		<tr align="center">
			<td align="center" width="50%">The State Of Texas</td><td align="left" width="20%">&#167;</td><td align="left" width="30%">Arrest Warrant</td>
		</tr>
		<tr align="center">
			<td align="center" width="50">County Of Harris</td><td align="left" width="20%">&#167;</td><td align="left" width="30%">Warrant Number: <%=reportInfo.getWarrantNum()%></td>
		</tr>
</table>	
</div>
<div class="body">
<!--  Attention -->
<table width="100%" border-style="none" class="margin-top-30px">
		<tr>
			<td  align="left">TO THE SHERIFF OR ANY PEACE OFFICER OF HARRIS COUNTY, TEXAS  
			</td>
		</tr>
</table>
<!--  Greetings -->
<table width="100%" border-style="none" class="margin-top-30px">
		<tr>
			<td align="left">GREETINGS:</td>
		</tr>
</table>
<!--  WHEREAS First Paragraph -->
<table width="100%" border-style="none" class="margin-top-30px">
		<tr>
			<td align="left">				
				<%=StringEscapeUtils.escapeXml("WHEREAS, Complaint in writing, under oath, has been made before me by " + reportInfo.getOfficerName() +
						", a peace officer employed by the " + reportInfo.getOfficerAgencyName() + ", which complaint is attached hereto and expressly made a part " + 
						"hereof, stating facts and information in my opinion sufficient to establish probable cause for the issuance of this " + 
						"warrant.") %>
			</td>
		</tr>
</table>
<!--  NOW THEREFORE Second Paragraph -->
<table width="100%" border-style="none" class="margin-top-24px">
		<tr>
			<td align="left">			
				<%=StringEscapeUtils.escapeXml("NOW THEREFORE, you are commanded to arrest " +  reportInfo.getFirstName() + " " + reportInfo.getMiddleName() + " " +
						reportInfo.getLastName() + " " + reportInfo.getSuffix() + ", hereinafter the Respondent, date of birth " + 
						reportInfo.getDateOfBirthString() + ", described and accused in said affidavit for the " +  reportInfo.getLevel() + " " + 
						reportInfo.getDegree() + " offense of " + reportInfo.getChargeDescription() + " committed on or about " +
						reportInfo.getOffenseDateString() + ".") %>
			</td>
		</tr>
</table>
<!--  HEREIN FAIL NOT paragraph 3 -->
<table width="100%" border-style="none" class="margin-top-50px">
		<tr>
			<td align="left">			
				HEREIN FAIL NOT and due return make hereof.
			</td>
		</tr>
</table>
<!--  WITNESS MY SIGNATURE paragraph 4 -->
<table width="100%" border-style="none" class="margin-top-12px">
		<tr>
			<td>			
				WITNESS MY SIGNATURE on this the _____ day of __________________________, _______ at____o'clock_____.M.
			</td>
		</tr>
</table>
<!--  	MAGISTRATE signature -->
<table width="100%" border-style="none" class="margin-top-50px">
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">			
				___________________________________________
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">			
				MAGISTRATE
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">			
				HARRIS COUNTY, TEXAS
			</td>
		</tr>
</table>
</div>
<pbr/>
 <!--  SECOND PAGE -->
<div class="header">
<table width="100%" border-style="none">
		<tr align="center">
			<td align="center" width="50%">The State Of Texas</td><td align="left" width="20%">&#167;</td><td align="left" width="30%">Arrest Warrant</td>
		</tr>
		<tr align="center">
			<td align="center" width="50">County Of Harris</td><td align="left" width="20%">&#167;</td><td align="left" width="30%">Warrant Number: <%=reportInfo.getWarrantNum()%></td>
		</tr>
</table>	
</div>
<div class="body">
<!--  2nd Page - Affidavit For Arrest Warrant -->
<table width="100%" border-style="none" class="margin-top-40px">
		<tr>
			<td align="center" >			
				AFFIDAVIT FOR ARREST WARRANT
			</td>
		</tr>
</table>
<!--  2nd Page - Sworn Clause -->
<table width="100%" border-style="none" class="margin-top-30px">
		<tr>
			<td align="left">			
				<%=StringEscapeUtils.escapeXml("I, " + reportInfo.getOfficerName() + ", employed as a peace officer with " +
						reportInfo.getOfficerAgencyName() + ", do solemnly swear that I have reason to believe and do believe that " + 
						reportInfo.getFirstName() + " " + reportInfo.getMiddleName() + " " + reportInfo.getLastName() + " " + reportInfo.getSuffix() + 
						", hereinafter the Respondent, date of birth " + reportInfo.getDateOfBirthString() + ", did, in Harris County, " +
						"Texas, on or about " + reportInfo.getOffenseDateString() + ", commit the " + reportInfo.getLevel() + " " + 
						reportInfo.getDegree() + " offense of " + reportInfo.getChargeDescription() + ".")%>
			</td>
		</tr>
</table>
<!--  2nd Page - MY BELIEF -->
<table width="100%" border-style="none" class="margin-top-40px">
		<tr>
			<td align="left">			
				MY BELIEF IS BASED UPON THE FOLLOWING FACTS:
			</td>
		</tr>
</table>
<!--  2nd Page - STATEMENT -->
<table width="100%" border-style="none" class="margin-top-12px">
		<tr>
			<td align="left">			
				<%=StringEscapeUtils.escapeXml(reportInfo.getAffidavitStatement())%>
			</td>
		</tr>
</table>
<!--  2nd Page - WHEREFORE -->
<table width="100%" border-style="none" class="margin-top-30px">
		<tr>
			<td align="left">			
				<%=StringEscapeUtils.escapeXml("WHEREFORE PREMISES CONSIDERED, affiant respectfully requests that a warrant issue for " + 
						"the arrest of " + reportInfo.getFirstName() + " " + reportInfo.getMiddleName() + " " + reportInfo.getLastName() + " " + reportInfo.getSuffix() + 
						" for the " + reportInfo.getLevel() + " " + reportInfo.getDegree() + " offense of " + reportInfo.getChargeDescription() + ".") %>
			</td>
		</tr>
</table>
<!--  2nd Page - AGAINST -->
<table width="100%" border-style="none" class="margin-top-60px">
		<tr>
			<td align="left">			
				AGAINST THE PEACE AND DIGNITY OF THE STATE
			</td>
		</tr>
</table>
<!--  2nd Page - AFFIANT SIGNATURE -->
<table width="100%" border-style="none" class="margin-top-60px">
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">			
				___________________________________________
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">			
				AFFIANT
			</td>
		</tr>
</table>
<!--  2nd Page - WITNESS SIGNATURE -->
<table width="100%" border-style="none" class="margin-top-60px">
		<tr>
			<td align="left">			
				Sworn to and Subscribed before me on this the _____ day of ___________________________ A.D., _______ at____o'clock _____.M.
			</td>
		</tr>
</table>
<!--  	MAGISTRATE signature -->
<table width="100%" border-style="none" class="margin-top-70px">
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">			
				___________________________________________
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">			
				MAGISTRATE
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">			
				HARRIS COUNTY, TEXAS
			</td>
		</tr>
</table>
</div>
</body>
</pdf>
