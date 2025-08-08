<!DOCTYPE HTML>
<!-- Used to search for juvenile process Return of Service confirmation. -->
<!--MODIFICATIONS -->
<%-- CShimek	02/25/2005	Create JSP --%>
<%-- CShimek    08/15/2005  Revise to new look and feel --%> 
<%-- JFisher    08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek    02/02/2006  Add hidden field for HelpFile name --%>
<%-- CShimek	12/21/2006  revised helpfile reference value--%>
<%-- CShimek	01/31/2007  #39097 added multiple submit button logic --%>
<%-- CShimek	02/21/2007  #39776 removed acknowledge status check around DTA print button, PD no longer return this value. --%>
<%-- LDeen		05/16/2007  #42033 display middle name for officer/executor --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RYoung     08/06/2015  #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<title><bean:message key="title.heading"/> - warrantProcessReturnOfServiceConfirm.jsp</title>
<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<html:base />

</head>
<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitWarrantProcessReturnOfService" target="content">
   <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|94">

<!-- BEGIN HEADING TABLE -->
 <table align="center">
   <tr>
     <td class="header">
          <bean:message key="title.juvWarrantProcessReturnOfService"/> Confirmation
     </td>
   </tr>
</table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN MESSAGE TABLE -->
   <table width="98%">
   	 <tr>
       <td align="center" class="confirm">Warrant Return Service has been processed.</td>
    </tr>
  </table>

<!-- END MESSAGE TABLE -->
<!-- BEGIN MAIN INFORMATION TABLE -->
<table width=98% border=0 cellspacing=1 cellpadding=4 align=center>
<!-- BEGIN GENERAL INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan=4 nowrap><bean:message key="prompt.warrantInfo" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantNumber"/></td>
		<td class=formDe>
			<logic:equal name="juvenileWarrantForm" property="warrantNum" value="">&nbsp;</logic:equal>             
			<bean:write name="juvenileWarrantForm" property="warrantNum"/>
		</td>
		<td class=formDeLabel><bean:message key="prompt.warrantStatus"/></td>
		<td class=formDe>
			<logic:equal name="juvenileWarrantForm" property="warrantStatus" value="">&nbsp;</logic:equal>                             
			<bean:write name="juvenileWarrantForm" property="warrantStatus"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantType"/></td>
		<td class=formDe colspan=3>
			<logic:equal name="juvenileWarrantForm" property="warrantType" value="">&nbsp;</logic:equal>                             
			<bean:write name="juvenileWarrantForm" property="warrantType"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.juvenileName"/></td>          
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="juvenileName.formattedName"/></td>
	</tr>
<!-- END WARRANT INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- BEGIN WARRANT EXECUTOR/SERVICE INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan=4>&nbsp;<bean:message key="prompt.executorServiceInfo" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.name" /></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="executorName.formattedName" /></td>
	</tr>					
	<tr>
        <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.officerIdNumber"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="executorId"/></td>
        <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.officerIdType"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="executorIdType"/></td> 
    </tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.department" /></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="executorDepartmentName" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.workPhone" /></td>
		<td class=formDe>
			<logic:equal name="juvenileWarrantForm" property="executorPhoneNum" value="">&nbsp;</logic:equal>
			<bean:write name="juvenileWarrantForm" property="executorPhoneNum.formattedPhoneNumber" />
		</td>
		<td class=formDeLabel><bean:message key="prompt.cellPhone" /></td>
		<td class=formDe>
			<logic:equal name="juvenileWarrantForm" property="executorCellNum" value="">&nbsp;</logic:equal>
			<bean:write name="juvenileWarrantForm" property="executorCellNum.formattedPhoneNumber" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.pager" /></td>
		<td class=formDe>
			<logic:equal name="juvenileWarrantForm" property="executorPager" value="">&nbsp;</logic:equal>
			<bean:write name="juvenileWarrantForm" property="executorPager.formattedPhoneNumber" />
		</td>
		<td class=formDeLabel><bean:message key="prompt.email" /></td>
		<td class=formDe>
			<logic:equal name="juvenileWarrantForm" property="executorEmail" value="">&nbsp;</logic:equal>
			<bean:write name="juvenileWarrantForm" property="executorEmail" />
		</td>
	</tr>							
	<tr>			
		<td class=formDeLabel><bean:message key="prompt.serviceStatus" /></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="serviceStatus"/></td>				
	</tr>
	<tr>			
		<td class=formDeLabel><bean:message key="prompt.serviceDate" /></td>
		<td class=formDe>
			<bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="date.format.mmddyyyy" />
		</td>
		<td class=formDeLabel><bean:message key="prompt.serviceTime" /></td>
		<td class=formDe>
			<bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="time.format.HHmm" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.address" /></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="serviceAddress" /></td>				
	</tr>	
	<tr>
        <td class=formDeLabel><bean:message key="prompt.serviceReturnGeneratedStatus"/></td>          
        <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="serviceReturnGeneratedStatus"/></td>             
	</tr>  
	<tr>
        <td class=formDeLabel><bean:message key="prompt.serviceReturnSignatureStatus"/></td>          
        <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="serviceReturnSignatureStatus"/></td>             
	</tr>  				
<!-- END WARRANT EXECUTOR/SERVICE INFORMATION BLOCK -->				
</table>
<!-- END MIAN INFORMATION TABLE -->
<br>


<!-- BEGIN BUTTON TABLE -->
<table width="10%" border="0" align="center">
	<tr>
	  <td align="center" valign=top>
	  		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
       				 <bean:message key="button.mainPage"></bean:message>
			 </html:submit>
	 </td>
</html:form>
 	<!--BEGIN of the code for Printing/Reporting Functionality -->	
	<logic:equal name="juvenileWarrantForm" property="warrantType" value="DIRECTIVE TO APPREHEND"> 
		<td align="center">
			<html:form action="/printWarrantTemplateReport" target="new">
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
					<bean:message key="button.printDTAWarrantReturn"></bean:message>   
				</html:submit>
			</html:form>
		</td>
	</logic:equal>
	<logic:equal name="juvenileWarrantForm" property="warrantType" value="ORDER OF IMMEDIATE CUSTODY"> 
		<td align="center">
			<html:form action="/printWarrantTemplateReport" target="new">
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
					<bean:message key="button.printOICWarrantReturn"></bean:message>   
				</html:submit>
			</html:form>
 		</td>
	</logic:equal>
	<logic:equal name="juvenileWarrantForm" property="warrantType" value="ARREST WARRANT"> 
		<td align="center">
			<html:form action="/printWarrantTemplateReport" target="new">
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
					<bean:message key="button.printArrestWarrantReturn"></bean:message>   
				</html:submit>
			</html:form>
 		</td>
	</logic:equal>
		
 	<!--END of the code for Printing/Reporting Functionality -->		 	 
	</tr>
</table>
<!-- END BUTTON TABLE -->

<!-- END FORM -->
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>