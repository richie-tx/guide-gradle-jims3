<!DOCTYPE HTML>

<%-- Used for searching of juvenile JIMS reports in MJCW --%>
<%--MODIFICATIONS --%>
<%-- 10/28/2013	CShimek  	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - facilityHistoricalReceiptReport.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
function validateInputs()
{
	var errorMessage = "";
	var isSuccessful = true;
	var fld = document.getElementById("juvenileNumber");
	if (fld.value == "")
	{
		errorMessage += "Juvenile Number is required.\n";
		fld.focus();
		isSuccessful = false;
	}
	if (!isNumeric(fld.value,false))
	{
		errorMessage += "Juvenile Number must be numeric.\n";
		fld.focus();
		isSuccessful = false;
	}
	var firstCharacter = fld.value.substring(0,1);
	if (firstCharacter.indexOf("0")!= -1)
	{
		errorMessage += "Juvenile Number cannot start with zero.\n";
		fld.focus();
		isSuccessful = false;
	}
	if(!isSuccessful){
		alert(errorMessage); 
	}else{
		findInfo();
	}	
	return isSuccessful;
}
function validatePrintInputs()
{
	var errorMessage = "";
	var isSuccessful = true;
	fld = document.getElementById("juvenileNumber");
	if (fld.value == "")
	{
		errorMessage += "Juvenile Number is required.\n";
		fld.focus();
		isSuccessful = false;
	}
	if (!isNumeric(fld.value,false))
	{
		errorMessage += "Juvenile Number must be numeric.\n";
		fld.focus();
		isSuccessful = false;
	}
	var firstCharacter = fld.value.substring(0,1);
	if (firstCharacter.indexOf("0")!= -1)
	{
		errorMessage += "Juvenile Number cannot start with zero.\n";
		fld.focus();
		isSuccessful = false;
	}
	if(!isSuccessful){
		alert(errorMessage); 
	}
	return isSuccessful;
}
function findInfo()
{
	var forwardURL = "/<msp:webapp/>displayHistoricalReceiptReport.do?submitAction=Submit";	
	document.forms[0].action = forwardURL;
	document.forms[0].submit();	
}
</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<%--BEGIN FORM TAG--%>
<html:form action="/displayHistoricalReceiptReport" target="content" focus="facilityId" >
<input type="hidden" name="helpFile" value="">       

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - 
    	Historical Receipt List
    </td>
  </tr>
</table>
<%-- END HEADING TABLE --%>
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
<!-- BEGIN INSTRUCTION TABLE -->
<br>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
		<li>Select Facility from Facility drop down and enter Juvenile# to view different report.</li>
        <li>Select Print button to print the generated report.</li>
        <li>Select Cancel button to return to search page.</li>
      </ul>
    </td>
  </tr>
</table>
<br>
<!-- END INSTRUCTION TABLE -->
<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>
<%-- BEGIN SEARCH FOR TABLE --%>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1">
	<tr id = "facilitiesRow">
		<td align="center" colspan="2">
			<b><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.facility" /></b>:
			<html:select name="juvenileFacilityReceiptForm" property="facilityId" onchange="return validateInputs(this.form);">
				<html:optionsCollection name="juvenileFacilityReceiptForm" property="facilities" value="code" label="descriptionWithCode" />
			</html:select>
		</td>
	</tr>
</table>
<br/>
<%-- END SEARCH FOR TABLE  --%>
<%-- BEGIN JUVENILE SUMMARY --%>
<table width="98%" border="0" >
	<tr>			
		<td ><span class='diamond'></span>
			<b>Juvenile#: <input name="juvenileNumber" id="juvenileNumber" type="text" size="10" maxlength="8" value="<bean:write name="juvenileFacilityReceiptForm" property="juvenileNumber"/>"/></b></td>
		<td><b>Juvenile Name: <bean:write name="juvenileFacilityReceiptForm" property="juvenileName"/></b></td>
		<td><b>Race: <bean:write name="juvenileFacilityReceiptForm" property="juvenileRace"/></b></td>
		<td><b>Sex: <bean:write name="juvenileFacilityReceiptForm" property="juvenileSex"/></b></td>
		<td><b>D.O.B: <bean:write name="juvenileFacilityReceiptForm" property="juvenileDateOfBirth"/></td>			 
	 </tr>
</table>
<br/>
<%-- END JUVENILE SUMMARY --%>
<%-- BEGIN INFORMATION TABLE --%>      
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
	<tr>
		<td colspan="6" align="center"><bean:write name="juvenileFacilityReceiptForm" property="numReceipts"/> records found in search results.</td>
	</tr>
	<logic:notEqual name="juvenileFacilityReceiptForm" property="numReceipts" value="0">
		<tr>
			<td class="detailHead"><bean:message key="prompt.facilityCode" /></td>
			<td class="detailHead"><bean:message key="prompt.admitDate" /></td>
			<td class="detailHead"><bean:message key="prompt.releaseDate" /></td>
			<td class="detailHead"><bean:message key="prompt.receiptNumber" /></td>
			<td class="detailHead"><bean:message key="prompt.lockerNumber" /></td>
			<td class="detailHead"><bean:message key="prompt.referralNumber" /></td>
		</tr>
		<% int RecordCounter = 0; %>
		<logic:iterate id="resultReceiptId" name="juvenileFacilityReceiptForm" property="historicalReceipts">
			<%-- Begin Pagination item wrap --%>
		  	<pg:item>																										
		    <tr class=<%RecordCounter++;
						String className = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";
					    out.print(className);%>>
		             <td align="left" valign=top>
		             	<bean:write name="resultReceiptId" property="facilityCode"/>
		             </td>
		             <td align="left" valign=top>
		                <bean:write name="resultReceiptId" property="admitDate" formatKey="date.format.mmddyyyy"/>
					 </td>
		             <td align="left" valign=top>
		             	<bean:write name="resultReceiptId" property="releaseDate" formatKey="date.format.mmddyyyy"/>
		             </td>	
		             <td align="left" valign=top>
		                <bean:write name="resultReceiptId" property="receiptNumber"/>
					 </td>	
		             <td align="left" valign=top>
		             	<bean:write name="resultReceiptId" property="lockerNumber"/>
					 </td>
		             <td align="left" valign=top>
		             	<bean:write name="resultReceiptId" property="referralNumber"/>            
		             </td>																												                    																			                 
		      </tr>
		      </pg:item>
		</logic:iterate>	
	 </logic:notEqual>
</table>

<%-- END INFORMATION TABLE --%>	
<div class="spacer4px" />
<%-- Begin Pagination navigation Row--%>
<table align="center">
		<tr>
 			<td>
 				<pg:index>
 					<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
 						<tiles:put name="pagerUniqueName" value="pagerSearch"/>
 						<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
 					</tiles:insert>
 			 	</pg:index>
	    </td>
   </tr>
 </table>
 <%-- End Pagination navigation Row--%>
		  
<%-- BEGIN BUTTON TABLE --%>
<table align="center" border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction"  onclick="return validateInputs()"><bean:message key="button.submit" /></html:submit>
			<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
			<html:submit property="submitAction" onclick="return validatePrintInputs(this.form)"><bean:message key="button.print" /></html:submit>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
</pg:pager>
</html:form>
<div class="spacer4px" />
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>