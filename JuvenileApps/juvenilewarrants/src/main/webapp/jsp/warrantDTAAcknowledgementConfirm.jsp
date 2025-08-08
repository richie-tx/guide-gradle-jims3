<!-- Used to display request acknowledgement summary resulting from acknowledgement search. -->
<!--MODIFICATIONS -->
<!--DWilliamson	10/06/2004	Create JSP -->
<!--LDeen		10/26/2004	Revise JSP -->
<!--CShimek     08/12/2005  Revised to new look and feel -->
<%-- JFisher    08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek    01/31/2006  Revised hidden field helpFile for each warrant type --%>
<%-- CShimek	12/21/2006  revised helpfile reference value--%>
<%-- LDeen		05/15/2006  added width attribute --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RYoung     08/6/2015   #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!DOCTYPE HTML>
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
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
<!-- JAVASCRIPT LINK FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<html:base />
<title><bean:message key="title.heading"/> - warrantDTAAcknowledgementConfirm.jsp</title>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayGenericSearch" target="content">
   <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|21">
<!-- BEGIN HEADING TABLE -->
	<table width="100%">
      <tr>
        <td align="center" class="header"><bean:message key="title.juvWarrantDTA"/>&nbsp;Acknowledgement Confirmation</td>
      </tr>
	 </table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN INSTRUCTION/REQUIRED FIELDS TABLES -->
<table width="95%" align="center" border="0">
   <tr>
     <td align="center" class="confirm">Warrant has been acknowledged.</td>
  </tr>
</table>  
<!-- END INSTRUCTION/REQUIRED FIELDS TABLE -->
<!-- BEGIN MAIN BODY TABLE -->
<table width=98% border=0 cellspacing=1 cellpadding=4 align=center>
<!-- BEGIN JUVENILE WARRANT INFORMATION BLOCK -->  
	<tr>
		<td class=detailHead colspan=4 nowrap><bean:message key="prompt.juvenileWarrantInfo" /></td>
	</tr>
    <tr>			
        <td class=formDeLabel><bean:message key="prompt.warrantNumber"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="warrantNum"/></td> 
		<td class=formDeLabel><bean:message key="prompt.signatureStatus"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="warrantSignedStatus"/></td>                     
    </tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.fileStampDate"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="fileStampDate" formatKey="date.format.mmddyyyy" /></td>
		<td class=formDeLabel><bean:message key="prompt.fileStampTime"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="fileStampDate" formatKey="time.format.HHmm" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.fileStampName"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="fileStampName.formattedName"/></td>
	</tr>
<!-- END JUVENILE WARRANT INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- BEGIN WARRANT ACKNOWLEDGEMENT INFORMATION BLOCK -->  
	<tr>
	 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.acknowledgeInfo" /></td>
	</tr>
	<tr>
      <td class=formDeLabel><bean:message key="prompt.acknowledgeDate"/></td>
      <td class=formDe><bean:write name="juvenileWarrantForm" property="warrantAcknowledgementDate" formatKey="date.format.mmddyyyy" /></td>
	  <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.acknowledgeStatus"/></td>
	  <td class=formDe><bean:write name="juvenileWarrantForm" property="warrantAcknowledgeStatus"/></td>
   </tr>
<!-- END WARRANT ACTIVATION INFORMATION BLOCK --> 
<tr><td><br></td></tr>
<!-- BEGIN JUVENILE INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan=4 nowrap><bean:message key="prompt.juvenileInfo" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.number"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="juvenileNum"/></td>
		<td class=formDeLabel><bean:message key="prompt.name"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="juvenileName.formattedName"/></td>
	</tr> 
<!-- END JUVENILE INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- BEGIN LAW ENFORCEMENT INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan=4 nowrap><bean:message key="prompt.lawEnforcementInfo" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.officerIdNumber"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="officerId"/></td>
		<td class=formDeLabel><bean:message key="prompt.officerIdType"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="officerIdType"/></td>
	</tr>
	<tr> 
		<td class=formDeLabel><bean:message key="prompt.departmentName"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="officerAgencyName"/></td>
	</tr> 
	<tr> 
		<td class=formDeLabel><bean:message key="prompt.name"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="officerName.formattedName"/></td>
	</tr> 
<!-- END LAW ENFORCEMENT INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- BEGIN WARRANT ORIGINATOR INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan=4 nowrap><bean:message key="prompt.warrantOriginatorInfo" /></td>
	</tr>
	<tr> 
		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.warrantOriginatorName"/></td>
		<td class=formDe colspan=3>
			<logic:equal name="juvenileWarrantForm" property="warrantOriginatorName" value="">&nbsp;</logic:equal>
			<logic:notEmpty name="juvenileWarrantForm" property="warrantOriginatorName"><bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/></logic:notEmpty>
		</td>
	</tr> 
<!-- END WARRANT ORIGINATOR INFORMATION BLOCK -->
</table>
<!-- END MAIN BODY TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
    <table width="10%" align="center">
      <tr>
	    <td align="center" valign=top>
		    <%--<html:submit property="submitAction">
       				 <bean:message key="button.mainPage"></bean:message>
       				 </html:submit> --%>
			 <html:button property="org.apache.struts.taglib.html.CANCEL" 
						 onclick="document.location.href='../security.war/jsp/mainMenu.jsp'">
						 <bean:message key="button.mainPage"></bean:message>
    			   </html:button> 
    		
		</td>
</html:form>	
	 	<!--Start of the code for Printing/Reporting Functionality -->		
		<td align="center">
			<html:form action="/printWarrantTemplateReport" target="new">
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> 
					<bean:message key="button.printDTAWarrant"></bean:message>   
				</html:submit>
			</html:form>
		</td>	
		<td align="center">
			<html:form action="/printWarrantTemplateReport" target="new">
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
					<bean:message key="button.printDTAAffidavit"></bean:message>   
				</html:submit>
			</html:form>
		</td>
 	<!--End of the code for Printing/Reporting Functionality -->	
      </tr>
    </table>
	 <!-- END BUTTON TABLE -->


<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>