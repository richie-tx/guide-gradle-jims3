<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>
<%-- 02/19/2016	RCarter	   New initial query jsp for facilities --%>

	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.Features" %>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework -/prodSupport/updateFacilityHeaderQueryResult.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updateFacilityHeader.js?Clr=1"></script>

<script language="javascript">

function confirmUpdate(){

	//confirm( "Are you sure");

}

function checkSelect() 
{ 
	var myForm = document.forms[0];
	//alert("Form Submitted!");
  	myForm.submit();
  return true;

} 

function validateJuvNumber(){
	var result = true;
	
	var juvenileNumber = $('#juvenileNumber').val();
	
	if(!juvenileNumber)
	{
		result = false;
	}
	
	if(juvenileNumber.length !== 6){
		result = false;
	}
	
	return result;
}

$(document).ready(function () {
	
	$('#juvenileNumber').change(function(){	
		 var isValid = validateJuvNumber();
		 
		 if(isValid){
			 $('#submitBtn').prop('disabled', false)
		 } else {
			 alert('invalid juvenile number. Please enter a valid juvenile number.');
			 $('#juvenileNumber').focus();
			 $('#submitBtn').prop('disabled', true)
		 }
		 
	});
	
	
});

</script>

</head>

<body>


<html:form styleId="formFacility" action="/PerformUpdateFacilityHeader">

<div>

	<h2 align="center">Results for Juvenile Number = 
			<bean:write name="ProdSupportForm" property="juvenileId" /></h2>
	     
<!-- Error Message Area -->
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="900" align="center">

	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<bean:write name="ProdSupportForm" property="msg" />
	 		</font></td>
	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->	     
	     
<logic:notEmpty	name="ProdSupportForm" property="facilityHeaderList">
	<i><h4 align="center">Please update the following Header information</h4></i>
	<h3 align="center">Facility Header Information</h3>
	<logic:iterate id="facilityHeaderList" name="ProdSupportForm" property="facilityHeaderList">
	
	<table class="resultTbl" border="1" width="900" align="center">
	
	<tr>
		<td bgcolor="gray" width="30%"><font color="white" face="bold" size="-1">HEADER ID</font></td>
		<td><font size="-1" width="37%"><bean:write  name="facilityHeaderList" property="headerId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE ID</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="juvenileId" />&nbsp;</font></td>
		<td>
		  <jims2:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEJUVENILENUM%>">
			 <font size="-1"><html:text styleId="juvenileNumber"  name="ProdSupportForm"  property="juvenileId" maxlength="6"/>&nbsp;
			 </font>
		  </jims2:isAllowed>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">FULL NAME</font></td>
		<td><font size="-1">
			<bean:write name="facilityHeaderList" property="firstName" />&nbsp;
			<bean:write name="facilityHeaderList" property="middleName" />&nbsp;
			<bean:write name="facilityHeaderList" property="lastName" />&nbsp;
			<bean:write name="facilityHeaderList" property="suffixName" />
			</font>
			<elogic:if name="facilityHeaderList" property="rectype" op="equal" value="S.JUVENILE">
					<elogic:then>
						<font size="-1"> - S </font>
					</elogic:then>	
				</elogic:if>		
				<elogic:if name="facilityHeaderList" property="rectype" op="equal" value="I.JUVENILE">
						<elogic:then>
						<font size="-1"> - I </font>
					</elogic:then>
			</elogic:if>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">BOOKING REFERRAL</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="bookingReferralNum"/>&nbsp;</font></td>
		<html:hidden styleId='curBookingRefNum'  name='facilityHeaderList' property='bookingReferralNum' indexed="true"/>
		<td>  
			<html:select name="ProdSupportForm" property="newReferralNum" styleId='newReferralNum' style="width:105px">
					<option value="" selected="selected"><bean:message key="select.generic"/></option>
					<html:optionsCollection name="ProdSupportForm" property="bookingReferrals"  value="description" label="description"/>
			</html:select>
		</td>	
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">BOOKING SUPERVISION NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="bookingSupervisionNum" />&nbsp;</font></td>
		<html:hidden styleId='oldbookingSprvNum'  name='facilityHeaderList' property='bookingSupervisionNum' indexed="true"/>
		<html:hidden styleId='curBookingSprvNum'  name='ProdSupportForm' property='bookingSupervisionNum' indexed="true"/>
		<td> 
			<html:select name="ProdSupportForm" property="newcasefileId" styleId='newSupervisionNum' style="width:105px">
					<option value="" selected="selected"><bean:message key="select.generic"/></option>
					<html:optionsCollection name="ProdSupportForm" property="bookingSprvisionNumbers"  value="description" label="description"/>
			</html:select> 
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LAST SEQUENCE NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="lastSequenceNum" />&nbsp;</font></td>
		<html:hidden styleId='curSequenceNum'  name='facilityHeaderList' property='lastSequenceNum' indexed="true"/>
		<html:hidden styleId='headerSeqNum'  name='ProdSupportForm' property='sequenceNum' indexed="true"/>
		<td><font size="-1"><html:text name="ProdSupportForm" property="newJuvseqnum" size="3" styleId='nextSeqNum' indexed="true"/></font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">HIGHEST SEQUENCE IN USE</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="highestSequenceNumInUse" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">HEADER FACILITY</font></td>
		<td>
			<font size="-1"><bean:write  name="facilityHeaderList" property="facilityCode" />&nbsp;-&nbsp;
			<bean:write  name="facilityHeaderList" property="facilityName" /></font>
			<html:hidden styleId='curFacilityCd'  name='facilityHeaderList' property='facilityCode' indexed="true"/>
		</td>
		<td>  
			<html:select name="ProdSupportForm" property="newHeaderFacilityCd" styleId="newFacId" style="width:300px">
				<option value="" ><bean:message key="select.generic"/></option>
				<html:optionsCollection name="ProdSupportForm" property="activeFacilitiesList" label="descriptionWithCode" value="code" />
			</html:select>
		</td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FACILITY STATUS</font></td>
		<td>
			<font size="-1"><bean:write  name="facilityHeaderList" property="facilityStatusCode" />
			<html:hidden styleId='curFacilityStatus'  name='facilityHeaderList' property='facilityStatusCode' indexed="true"/>
		</td>
		<td> 
			<html:select name="ProdSupportForm" property="facilityStatus" styleId='newStatId' style="width:105px">
					<option value="" selected="selected"><bean:message key="select.generic"/></option>
					<html:optionsCollection name="ProdSupportForm" property="statusCodes"  value="code" label="description"/>
			</html:select> 
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">NEXT HEARING DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="nextHearingDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		<html:hidden styleId='curHearingDate'  name='facilityHeaderList' property='nextHearingDate' indexed="true"/>
		<td><font size="-1"><html:text name="ProdSupportForm" property="newBeginDate" styleId='newHearingDate' size="10" indexed="true"/></font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PC HEARING DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="probableCauseHearingDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<html:hidden styleId='curPCHearingDate'  name='facilityHeaderList' property='probableCauseHearingDate' indexed="true"/>
		<td><font size="-1"><html:text name="ProdSupportForm" property="newEndDate" styleId='newProbCauseDate' size="10" indexed="true"/></font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="createUserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="updateUser" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
		
	</table>
	</logic:iterate>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="facilityHeaderList">
	<br>
	<table align="center" border="1" width="900">
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

<logic:notEmpty	name="ProdSupportForm" property="facilityHeaderList">
	<td>
	<p align="center">
	<html:submit property="submitAction" styleId="submitBtn"><bean:message key="button.updateRecord" /></html:submit>
	</p>
	</td>
</logic:notEmpty>
</tr>
</table>
<html:hidden styleId="newRefNum" name="ProdSupportForm" property="newReferralNum"/>
<html:hidden styleId="newSprvNum" name="ProdSupportForm" property="newcasefileId"/>
<html:hidden styleId="newSequenceNum" name="ProdSupportForm" property="newJuvseqnum"/>
<html:hidden styleId="newPCDate" name="ProdSupportForm" property="newEndDate"/>
<html:hidden styleId="newHearDate" name="ProdSupportForm" property="newBeginDate"/>
<html:hidden styleId="newStatusCode" name="ProdSupportForm" property="facilityStatus"/>
<html:hidden styleId="newFacilityCode" name="ProdSupportForm" property="newHeaderFacilityCd"/>
</html:form>

<html:form action="/UpdateFacilityHeaderQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>


</body>
</html:html>