<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

 
<head>

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework -/prodSupport/DeleteDistrictCourtCalendarRecordQueryResult</title>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/DeleteDistrictCourtCalendar.js"></script>

</head>

<body>

<html:form action="/PerformDeleteDistrictCourtCalendarRecord" onsubmit="return this;">
 <!-- BEGIN Error Message Table -->
		 <logic:messagesPresent message="true"> 
			<table width="100%">
				<tr>		  
					<td align="center" class="messageAlert"><html:messages id="message" message="true"><font style="font-weight: bold;"
							color="green" size="3" face="Arial"><bean:write name="message"/></html:messages></font></td>		  
				</tr>   	  
			</table>
		</logic:messagesPresent> 
		
		<!-- BEGIN ERROR TABLE -->
		<table width="100%">
			<%-- <tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr> --%>
			<tr>
						<td align="center" colspan="4" class="errorAlert"><font style="font-weight: bold;"
							color="red" size="3" face="Arial"><html:errors></html:errors></font></td>
			</tr>
		</table>
	<!-- END ERROR TABLE -->
<div>
	
	<h2 align="center">Results for Delete Juvenile ID = 
			<bean:write name="ProdSupportForm" property="juvenileId" /></h2>
	     
<!-- Error Message Area -->
   <%--  <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="700" align="center">

	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<bean:write name="ProdSupportForm" property="msg" />
	 		</font></td>
	</tr>
	</table>
	</logic:notEqual> --%>
<!-- End Error Message Area -->	     

	<table class="resultTbl" border="1" width="750" align="center">

		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Name</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileName" />&nbsp;</font>
				<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="S.JUVENILE">
					<elogic:then>
						<font size="-1">( S )</font>
					</elogic:then>	
				</elogic:if>		
				<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="I.JUVENILE">
						<elogic:then>
						<font size="-1">( P )</font>
					</elogic:then> 
				</elogic:if>
				</td>
		</tr>
		
	</table>     
<p align="center"><b><i>For Record Deletion <br>
click delete</i></b></p>		     
<logic:notEmpty	name="ProdSupportForm" property="juvDistCourtRecords">
	<logic:iterate id="juvDistCourtRecords" name="ProdSupportForm" property="juvDistCourtRecords">
	<h3 align="center">The following District Court Record will be <font color="red" face="bold">DELETED.</font></h3>
	<h3 align="center">District Court Details</h3>
	
	<table class="resultTbl" border="1" width="800" align="center">
	
	<tr>		
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="juvenileNumber"/>&nbsp;</font></td>
	</tr> 
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court </font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="court"/>&nbsp;</font></td>		
	</tr>
	 <tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Date</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="courtDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Time</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="formattedCourtTime" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Number</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="referralNum"/>&nbsp;</font></td>			
	</tr>
	 <tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Chain Number</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="chainNumber"/>&nbsp;</font></td>			
	</tr>	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Sequence Number</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="seqNum"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Result</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="courtResult"/>&nbsp;</font></td>		
	</tr> 	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Disposition</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="disposition"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Hearing Type</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="hearingType"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Reset Hearing Type</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="resetHearingType"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Petition Number</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="petitionNumber"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Petition Status</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="petitionStatus"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Allegation</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="oldallegation"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Filing Date</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="filingDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Amendment</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="petitionAmendment"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Amendment Date</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="petitionAmendmentDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Bar Number</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="oldbarNum"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Attorney Connection</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="attorneyConnection"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Attorney Name</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="oldattorneyName"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Previous Notes</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="prevNotes"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Changed Date/Time</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="lcDt" formatKey="date.format.mmddyyyy"/>&nbsp;</font>
	</tr> 
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Changed User</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="lcUser"/>&nbsp;</font></td>		
	</tr>
		
	</table>
	</logic:iterate>
	</logic:notEmpty>
	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="juvDistCourtRecords">
	<td>
	<p align="center">
		<html:submit property="submitAction" styleId="deleteBtn" onclick="return disableSubmit(this, this.form);" ><bean:message key="button.deleteRecord" /></html:submit>
	</p>
	</td>
	</logic:notEmpty>

	</table>
	
	
	<logic:empty name="ProdSupportForm" property="juvDistCourtRecords">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Records Returned</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>

	</div>
	

<table align="center"">
<tr>

<td>&nbsp;</td>

</tr>

	<tr>
		<td>		
		<html:button property="submitAction" styleId="backToQryBtn">
  				<bean:message key="button.backToSearch"></bean:message>
  			</html:button>
		</td>
	</tr>
</table>
<html:hidden styleId="prevJuvNum" name="ProdSupportForm" property="fromJuvenileId"/>
<html:hidden styleId="juvenileNum" name="ProdSupportForm" property="juvenileId"/>
<html:hidden styleId="referralNum" name="ProdSupportForm" property="referralId"/>
</html:form>

</body>
</html:html>