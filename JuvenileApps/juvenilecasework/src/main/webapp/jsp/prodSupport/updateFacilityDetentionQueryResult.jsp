<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>
<%-- 03/30/2016	RCarter	   New initial query jsp for facilities --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="mojo.km.utilities.DateUtil"%>
<%@ page import="messaging.codetable.criminal.reply.JuvenileReleasedFromResponseEvent"%>
<%@ page import="java.util.*" %>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateFacilityDetentionQueryResult.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	table tr td {
		text-align: left;
	}
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/facilityProdSupport.js"></script>
<bean:define id="releaseTemp" name="ProdSupportForm" property="releaseTo" type= "java.util.ArrayList"/>


</head>

<body>

<h2 align="center">Production Support - Facilities Detention Record</h2>
<hr />

<%-- BEGIN ERROR TABLE Removed from table because it wasn't aligned rry--%>
<p align="center" style="color:red;"><html:errors></html:errors></p>
<%-- END ERROR TABLE --%>
<p align="center"><i>Please update the following Detention information</i></p>

<p align="center"><b>All time values must be entered in HH:MM format</b></p>
<html:form action="/PerformUpdateFacilityDetention" onsubmit="return this;">

<div>
	
	<h2 align="center">Facility Detention Information</h2>
	     
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
<logic:notEmpty	name="ProdSupportForm" property="facilityDetentionList">
	<logic:iterate id="facilityDetention" name="ProdSupportForm" property="facilityDetentionList">
	<p>&nbsp;</p>
	<table id="facilityDetention" border="1" width="900" align="center">
	
	<tr>
		<td bgcolor="gray" width="30%"> <font color="white" face="bold" size="-1">DETENTION ID</font></td>
		<td><font size="-1" width="40%"><bean:write  name="facilityDetention" property="detentionId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVENILE NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="juvenileId" />&nbsp;</font></td>
		<jims2:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEJUVENILENUM%>">		
		<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="juvenileId" size="7" maxlength="7" styleId="juvenileNum"/>
		</jims2:isAllowed>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVENILE NAME</font></td>
		<td><font size="-1">
			<bean:write name="facilityDetention" property="firstName" />&nbsp;
			<bean:write name="facilityDetention" property="middleName" />&nbsp;
			<bean:write name="facilityDetention" property="lastName" />&nbsp;
			<bean:write name="facilityDetention" property="suffixName" />
			</font>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">BOOKING REFERRAL NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="bookingReferralNum" />&nbsp;</font></td>		
		<td width="5%">  
			<html:select name="ProdSupportForm" property="bookingReferral" style="width:400px" styleId="bookingReferral">
				<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="facilityDetention" property="referralNumbers" label="referralNum" value="referralNum" />
			</html:select>
		</td>			
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CURRENT REFERRAL NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="currentReferralNum" />&nbsp;</font></td>
		<td width="5%">  
			<html:select name="ProdSupportForm" property="currentReferral" style="width:400px" styleId="currentReferral">
				<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="facilityDetention" property="referralNumbers" label="referralNum" value="referralNum" />
			</html:select>
		</td>			
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">BOOKING SUPERVISION NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="bookingSupervisionNum" />&nbsp;</font></td>
		<td width="5%">  
			<html:select name="ProdSupportForm" property="bookingSupervisionNum" style="width:400px" styleId="bookingSupervisionNum">
				<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="facilityDetention" property="currentCasefiles" label="supervisionNum" value="supervisionNum" />
			</html:select>
		</td>	
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CURRENT SUPERVISION NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="currentSupervisionNum" />&nbsp;</font></td>
		<td width="5%">  
			<html:select name="ProdSupportForm" property="currentSupervisionNum" style="width:400px" styleId="currentSupervisionNum">
				<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="facilityDetention" property="currentCasefiles" label="supervisionNum" value="supervisionNum" />
			</html:select>
		</td>	
	
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SEQUENCE NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="sequenceNum" />&nbsp;</font></td>
		<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="facilitySeqNum" size="3" maxlength="3" styleId="facilitySeqNum"/></td>
	</tr>	
	
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CURRENT OFFENSE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="currentOffense" />&nbsp;</font></td>
		<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="currentOffense" size="10" maxlength="10" styleId="currentOffense"/></td>	
	</tr>	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">FACILITY</font></td>
		<td>
			<font size="-1">
				<bean:write  name="facilityDetention" property="facilityCode" />&nbsp;-&nbsp;
				<bean:write  name="facilityDetention" property="facilityName" />
			</font>
		</td>
		<td>  
			<html:select name="ProdSupportForm" property="activeFacilityCd" style="width:400px" styleId="activeFacilityCd">
				<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="ProdSupportForm" property="activeFacilitiesList" label="descriptionWithCode" value="code" />
			</html:select>
		</td>		
	</tr>	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">FACILITY STATUS</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="facilityStatusCode" /></font></td>	
		<td> 
			<html:select name="ProdSupportForm" property="facilityStatus" styleId='newStatId'>
					<option value="">Select a New Value</option>
					<html:optionsCollection name="ProdSupportForm" property="statusCodes"  value="code" label="code"/>
			</html:select> 
		</td>
		<%-- <td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="facilityStatus" size="5" maxlength="5" styleId="facilityStatus"/></td> --%>		
	</tr>		
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ADMITTANCE REASON CODE</font></td>
		<td>
			<font size="-1">
				<bean:write  name="facilityDetention" property="admittanceReasonCode" />&nbsp;-&nbsp;
				<bean:write  name="facilityDetention" property="admittanceReasonCodeDesc" />
			</font>
		</td>
		<td>  
			<html:select name="ProdSupportForm" property="newadmitReasonCd" style="width:400px">
				<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="ProdSupportForm" property="admitReasonsList" label="descriptionWithCode" value="code" />
			</html:select>
		</td>		
	</tr>	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SECURE STATUS</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="secureStatus" />&nbsp;</font></td>
	</tr>	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">TRANSFER TO FACILITY</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="transferFacilityCode" />&nbsp;</font></td>
		<td>  
			<html:select name="ProdSupportForm" property="newTransferToFacility" style="width:400px" styleId="newTransferToFacility">
				<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="ProdSupportForm" property="activeFacilitiesList" label="descriptionWithCode" value="code" />
			</html:select>
		</td>			
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LOCATION UNIT</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="locationUnit" />&nbsp;</font></td>
		<td>
				<i>Enter a new value:</i>&nbsp;<html:text property="unitLocation" size="1" maxlength="1"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LOCATION FLOOR NUM</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="locationFloorNumber" />&nbsp;</font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text property="floorLocation" size="1" maxlength="1"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ROOM</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="roomNumber" />&nbsp;</font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text property="roomLocation" size="3" maxlength="3" styleId="roomLoc"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">MULTIPLE OCCUPANCY UNIT</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="multipleOccupanyUnit" />&nbsp;</font></td>
		<td class="" >
				<html:select property="multipleOccupancyUnit" name="ProdSupportForm" styleId="mou" style="width:400px">
				<html:option value="">Select a New Value</html:option>         	  	
						<jims2:codetable codeTableName='MULTIPLE_OCCUPANCY_UNIT'></jims2:codetable>
				</html:select>
		</td>  
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LOCKER NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="lockerNumber" />&nbsp;</font></td>
			<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text property="locker" size="6" maxlength="6"/></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">VALUABLES RECEIPT NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="valuablesReceiptNumber" />&nbsp;</font></td>
		<td class="" >
					<i>Enter a new value:</i>&nbsp;<html:text property="valuablesReceipt" size="10" maxlength="10" styleId="valuablesReceipt"/> 	
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DETAINED DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="detainedDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td class="" >
					<i>Enter a new value:</i>&nbsp;<html:text property="detainedDate" styleId="detainedDate" size="10" maxlength="10" /> 	
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ADMITTED BY</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="admittedByAuthority" />&nbsp;</font></td>
		<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="admitBy" size="5" maxlength="5" styleId="admitBy"/></td>			
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ADMITTED DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="admittedDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td class="" >
					<i>Enter a new value:</i>&nbsp;<html:text property="admitDate" styleId="admitDate" size="10" maxlength="10" /> 	</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ADMITTED TIME</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="admittedTime" />&nbsp;</font></td>
		<td class="" >
					<i>Enter a new value:</i>&nbsp;<html:text property="admitTime" styleId="admitTime" size="5" maxlength="5" /> </td>	
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">AUTHORIZE JPO</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="admittedAuthorizeJPO" />&nbsp;</font></td>
		<td class="" ><i>Enter a new value:</i>&nbsp;<html:text property="admitAuthority" size="5" maxlength="50" styleId="admitAuthority"/></td>	
	</tr>	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ORIGINAL ADMITTED DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="originalAdmittedDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>	
	   <td class="" >
					<i>Enter a new value:</i>&nbsp;<html:text property="originalAdmitDate" styleId="originalAdmitDate" size="10" maxlength="10" /> </td>
	</tr>	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ORIGINAL ADMITTED TIME</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="originalAdmittedTime" />&nbsp;</font></td>
		<td class="" >
					<i>Enter a new value:</i>&nbsp;<html:text property="originalAdmitTime" styleId="originalAdmitTime" size="5" maxlength="5" /> </td>	
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CHANGE EXPLANATION</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="changeExplanation" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENT</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="comments" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ESCAPE ATTEMPTS</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="escapeAttempts" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ESCAPE CODE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="escapeCode" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RISK ANANLYSIS ID</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="riskAnalysisId" />&nbsp;</font></td>
		<td> 
			<html:select name="facilityDetention" property="riskAnalysisId" style="width:105px">
				 <option value="" selected="selected"><bean:message key="select.generic"/></option>
				<html:optionsCollection name="ProdSupportForm" property="riskAnalysisIds"  value="description" label="description"/>
			</html:select> 
		</td>	
	</tr>
	</table>
    <table width="900" align="center">
        <tr>
            <td colspan="2" align="center"><br/></font></td>
        </tr>
    </table>   
    <table border="1" width="900" align="center">
    <tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">OUTCOME</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="outcome" />&nbsp;</font></td>
		<td>	
			<html:select name="ProdSupportForm" property="outcome" styleId="outcome" style="width:400px">			
				<html:option value="">Select a New Value</html:option> 
							<jims2:codetable codeTableName='FACILITY_OUTCOME'></jims2:codetable>				
						</html:select>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASE DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		 <td class="" >
					<i>Enter a new value:</i>&nbsp;<html:text property="releaseDate" styleId="releaseDate" size="10" maxlength="10" /> </td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASE TIME</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseTime"/></font></td>
		<td class="" >
					<i>Enter a new value:</i>&nbsp;<html:text property="releaseTime" styleId="releaseTime" size="5" maxlength="5" /> </td>			
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASED BY</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseBy" />&nbsp;</font></td>
		<td class="" >
					<i>Enter a new value:</i>&nbsp;<html:text property="releasedBy" styleId="releasedBy" size="5" maxlength="5" /> </td>	
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASED TO</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseTo" />&nbsp;</font></td>
		<td class='' width="15%" colspan='1' nowrap>
			<html:select name="ProdSupportForm" property="releasedTo" styleId="releasedTo" style="width:400px">			
				<html:option value="">Select a New Value</html:option> 
				<html:optionsCollection property="releaseTo" value="code" label="descriptionWithCode"/> 				
			</html:select>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASED AUTHORIZED BY</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseAuthorizedBy" />&nbsp;</font></td>
		<td class="" >
					<i>Enter a new value:</i>&nbsp;<html:text property="releaseAuthority" styleId="releaseAuthority" size="5" maxlength="5" /> </td>	
		</logic:notEmpty>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASED REASON</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseReason" />&nbsp;</font></td>
		<td>	
			<html:select name="ProdSupportForm" property="releaseReason" styleId="releaseReason" style="width:400px">			
				<html:option value="">Select a New Value</html:option> 
					<jims2:codetable codeTableName='RELEASE_REASON'></jims2:codetable>				
			</html:select>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RETURN DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="returnDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		 <td class="" >
					<i>Enter a new value:</i>&nbsp;<html:text property="returnDate" styleId="returnDate" size="10" maxlength="10" /> </td>	
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RETURN TIME</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="returnTime" />&nbsp;</font></td>
		<td class="" >
					<i>Enter a new value:</i>&nbsp;<html:text property="returnTime" styleId="returnTime" size="5" maxlength="5" /> </td>	
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RETURN REASON</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="returnReason" />&nbsp;</font></td>
		<td class='' width="15%" colspan='1' nowrap>
			<html:select name="ProdSupportForm" property="returnReason" styleId="returnReason">			
				<html:option value="">Select a New Value</html:option> 
				<html:optionsCollection property="returnReasons" value="code" label="descriptionWithCode"/> 				
			</html:select>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RETURN STATUS</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="returnStatus" />&nbsp;</font></td>
		<td class="formDe">
			<html:select name="ProdSupportForm" property="returnStatus">
				<html:option value="">Select a New Value</html:option> 
				<html:option value="NO">NO</html:option>
				<html:option value="YES">YES</html:option>
			</html:select>
		</td>
	</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPECIAL ATTENTION</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="specialAttention" />&nbsp;</font></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPECIAL ATTENTION REASON</font></td> -->
<%-- 		<td><font size="-1"><bean:write  name="facilityDetention" property="specialAttentionReason" />&nbsp;</font></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPECIAL ATTENTION OTHER COMMENTS</font></td> -->
<%-- 		<td><font size="-1"><bean:write  name="facilityDetention" property="specialAttentionOtherComments" />&nbsp;</font></td> --%>
<!-- 	</tr> -->
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TEMP RELEASE REASON</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="tempReleaseReasonCode" />&nbsp;</font></td>
		<td>
			<html:select name="ProdSupportForm" property="tempReleaseReason" styleId="tempReleaseReason" style="width:400px">			
				<html:option value="">Select a New Value</html:option> 
							<html:optionsCollection property="tempReleaseReasons" value="code" label="descriptionWithCode"/> 				
						</html:select></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ORIGINALADMITOID</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="originalAdmitOID" />&nbsp;</font></td>
		<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="originalAdmitOID" size="6" maxlength="6" styleId="admitBy"/></td>		
	</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">AVGCOSTPERDAY</font>
							</td>
							<td><font size="-1"><bean:write
										name="facilityDetention" property="avgCostPerDay" />&nbsp;</font>
							</td>
							<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="avgCostPerDay" size="5" maxlength="5" styleId="admitBy"/></td>		
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">DETAINEDBYIND</font>
							</td>
							<td><font size="-1"><bean:write
										name="facilityDetention" property="detainedByInd" />&nbsp;</font>
							</td>
							<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="detainedByInd" size="5" maxlength="5" styleId="admitBy"/></td>		
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">TJJDFUNDSRC</font>
							</td>
							<td><font size="-1"><bean:write
										name="facilityDetention" property="tjjdFundingSrc" />&nbsp;</font>
							</td>
							<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="tjjdFundingSrc" size="5" maxlength="5" styleId="admitBy"/></td>		
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">ORIGADMITSEQNUM</font>
							</td>
							<td><font size="-1"><bean:write
										name="facilityDetention" property="originaladmitSEQNUM" />&nbsp;</font>
							</td>
							<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="originaladmitSEQNUM" size="5" maxlength="5" styleId="admitBy"/></td>		
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">POSTADMITOID</font>
							</td>
							<td><font size="-1"><bean:write
										name="facilityDetention" property="postAdmitOID" />&nbsp;</font>
							</td>
							<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="postAdmitOID" size="6" maxlength="6" styleId="admitBy"/></td>		
						</tr>
<!-- 						<tr> -->
<!-- 							<td bgcolor="gray"><font color="white" face="bold" size="-1">DETAINEDDATE</font> -->
<!-- 							</td> -->
<%-- 							<td><font size="-1"><bean:write --%>
<!-- 										name="facilityDetention" property="detainedDate" />&nbsp;</font> -->
<!-- 							</td> -->
<!-- 						</tr> -->
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">TJJDFACILITYID</font>
							</td>
							<td><font size="-1"><bean:write
										name="facilityDetention" property="tjjdFacilityId" />&nbsp;</font>
							</td>
							<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="tjjdFacilityId" size="7" maxlength="7" styleId="admitBy"/></td>		
						</tr>						
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSUMCUSTODYFNAME</font>
							</td>
							<td><font size="-1"><bean:write
										name="facilityDetention" property="custodyfirstName" />&nbsp;</font>
							</td>
							<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="custodyfirstName" size="30" maxlength="30" styleId="admitBy"/></td>		
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSUMCUSTODYLNAME</font>
							</td>
							<td><font size="-1"><bean:write
										name="facilityDetention" property="custodylastName" />&nbsp;</font>
							</td>
							<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="custodylastName" size="30" maxlength="30" styleId="admitBy"/></td>			
						</tr>

						<tr>	
		<td bgcolor="gray" width="30%"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1" width="40%"><bean:write  name="facilityDetention" property="createUserID" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="updateUser" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="updateDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
<!-- 	<tr>	 -->
<!-- 		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td> -->
<%-- 		<td><font size="-1"><bean:write  name="facilityDetention" property="createJIMS2UserID" />&nbsp;</font></td> --%>
<!-- 	</tr> -->
<!-- 	<tr>	 -->
<!-- 		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td> -->
<%-- 		<td><font size="-1"><bean:write  name="facilityDetention" property="updateJIMS2UserID" />&nbsp;</font></td> --%>
<!-- 	</tr> -->
	</table>	    

	<BR>
	
	<html:hidden styleId="referralNo" name="ProdSupportForm" property="headerInfo.referralNo"/>
	<html:hidden styleId="facilityCode" name="ProdSupportForm" property="headerInfo.facilityCode"/>
	<html:hidden styleId="bookingSupervision" name="ProdSupportForm" property="headerInfo.bookingSupervision"/>
	<html:hidden styleId="lastSeqNum" name="ProdSupportForm" property="headerInfo.lastSeqNum"/>
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="facilityDetentionList">
	<td>
	<p align="center">
	<html:submit value="Update Record" styleId="updateRec"/>
	</p>
	</td>
	<td>
	<html:reset value="Reset Form Values" onclick="resetDates();" />
	</td>
	</logic:notEmpty>
	</table>
	
	</logic:iterate>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="facilityDetentionList">
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
	
</html:form>

<html:form action="/UpdateFacilityDetentionQuery?clr=Y" onsubmit="return this;">
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