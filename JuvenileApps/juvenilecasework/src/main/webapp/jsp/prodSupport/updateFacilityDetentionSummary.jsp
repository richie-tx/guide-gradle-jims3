<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>
<%-- 03/30/2016	RCarter	   New initial query jsp for facilities --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@page import="mojo.km.utilities.DateUtil"%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateFacilityDetentionSummary.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/facilityProdSupport.js"></script>

</head>

<body>

<h2 align="center">Production Support - Update Facilities Detention Record Summary</h2>
<hr>

<p align="center"><font color="green"><b>Detention Record 
<bean:write name="ProdSupportForm" property="detentionId" /> was successfully updated.</b></font></p>


<p align="center"><b>The following is the record affected by this change. This is for auditing purposes.<br></p>
<hr>


<html:form action="/PerformUpdateFacilityDetention" onsubmit="return this;">

<div>	
	     

<logic:notEmpty	name="ProdSupportForm" property="facilityDetentionList">
	<logic:iterate id="facilityDetention" name="ProdSupportForm" property="facilityDetentionList">
	<p>&nbsp;</p>
	<table border="1" width="900" align="center">
	
	<tr>
		<td bgcolor="gray" width="30%"> <font color="white" face="bold" size="-1">DETENTION ID</font></td>
		<td><font size="-1" width="40%"><bean:write  name="facilityDetention" property="detentionId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVENILE NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="juvenileId" />&nbsp;</font></td>
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
		
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CURRENT REFERRAL NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="currentReferralNum" />&nbsp;</font></td>
			
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">BOOKING SUPERVISION NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="bookingSupervisionNum" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CURRENT SUPERVISION NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="currentSupervisionNum" />&nbsp;</font></td>	
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SEQUENCE NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="sequenceNum" />&nbsp;</font></td>		
	</tr>	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CURRENT OFFENSE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="currentOffense" />&nbsp;</font></td>		
	</tr>	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">FACILITY</font></td>
		<td>
			<font size="-1">
				<bean:write  name="facilityDetention" property="facilityCode" />&nbsp;-&nbsp;
				<bean:write  name="facilityDetention" property="facilityName" />
			</font>
		</td>
	</tr>	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">FACILITY STATUS</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="facilityStatusCode" /></font></td>		
	</tr>		
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ADMITTANCE REASON CODE</font></td>
		<td>
			<font size="-1">
				<bean:write  name="facilityDetention" property="admittanceReasonCode" />&nbsp;-&nbsp;
				<bean:write  name="facilityDetention" property="admittanceReasonCodeDesc" />
			</font>
		</td>
		</tr>	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SECURE STATUS</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="secureStatus" />&nbsp;</font></td>
	</tr>	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">TRANSFER TO FACILITY</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="transferFacilityCode" />&nbsp;</font></td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LOCATION UNIT</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="locationUnit" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LOCATION FLOOR NUM</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="locationFloorNumber" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ROOM</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="roomNumber" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">MULTIPLE OCCUPANCY UNIT</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="multipleOccupanyUnit" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LOCKER NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="lockerNumber" />&nbsp;</font></td>		
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">VALUABLES RECEIPT NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="valuablesReceiptNumber" />&nbsp;</font></td>			
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DETAINED DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="detainedDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ADMITTED BY</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="admittedByAuthority" />&nbsp;</font></td>				
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ADMITTED DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="admittedDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ADMITTED TIME</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="admittedTime" />&nbsp;</font></td>	
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">AUTHORIZE JPO</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="admittedAuthorizeJPO" />&nbsp;</font></td>		
	</tr>	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ORIGINAL ADMITTED DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="originalAdmittedDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>		
	</tr>	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ORIGINAL ADMITTED TIME</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="originalAdmittedTime" />&nbsp;</font></td>			
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
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASE DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>		 
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASE TIME</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseTime"/></font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASED BY</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseBy" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASED TO</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseTo" />&nbsp;</font></td>	
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASED AUTHORIZED BY</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseAuthorizedBy" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASED REASON</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseReason" />&nbsp;</font></td>	
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RETURN DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="returnDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>		 
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RETURN TIME</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="returnTime" />&nbsp;</font></td>			
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RETURN REASON</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="returnReason" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RETURN STATUS</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="returnStatus" />&nbsp;</font></td>
	
	</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPECIAL ATTENTION</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="specialAttention" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPECIAL ATTENTION REASON</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="specialAttentionReason" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPECIAL ATTENTION OTHER COMMENTS</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="specialAttentionOtherComments" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TEMP RELEASE REASON</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="tempReleaseReasonCode" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TEMP RELEASE COMMENTS</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="tempReleaseOtherComments" />&nbsp;</font></td>
	</tr>
	<tr>
    <td bgcolor="gray"><font color="white" face="bold" size="-1">ORIGINAL ADMIT OID</font></td>
    <td><font size="-1"><bean:write name="facilityDetention" property="originalAdmitOID" />&nbsp;</font></td>
</tr>
<tr>
    <td bgcolor="gray"><font color="white" face="bold" size="-1">AVG COST PER DAY</font></td>
    <td><font size="-1"><bean:write name="facilityDetention" property="avgCostPerDay" />&nbsp;</font></td>
</tr>
<tr>
    <td bgcolor="gray"><font color="white" face="bold" size="-1">DETAINED BY IND</font></td>
    <td><font size="-1"><bean:write name="facilityDetention" property="detainedByInd" />&nbsp;</font></td>
</tr>
<tr>
    <td bgcolor="gray"><font color="white" face="bold" size="-1">TJJD FUNDING SRC</font></td>
    <td><font size="-1"><bean:write name="facilityDetention" property="tjjdFundingSrc" />&nbsp;</font></td>
</tr>
<tr>
    <td bgcolor="gray"><font color="white" face="bold" size="-1">ORIGINAL ADMIT SEQ NUM</font></td>
    <td><font size="-1"><bean:write name="facilityDetention" property="originaladmitSEQNUM" />&nbsp;</font></td>
</tr>
<tr>
    <td bgcolor="gray"><font color="white" face="bold" size="-1">POST ADMIT OID</font></td>
    <td><font size="-1"><bean:write name="facilityDetention" property="postAdmitOID" />&nbsp;</font></td>
</tr>
<tr>
    <td bgcolor="gray"><font color="white" face="bold" size="-1">TJJD FACILITY ID</font></td>
    <td><font size="-1"><bean:write name="facilityDetention" property="tjjdFacilityId" />&nbsp;</font></td>
</tr>
<tr>
    <td bgcolor="gray"><font color="white" face="bold" size="-1">CUSTODY FIRST NAME</font></td>
    <td><font size="-1"><bean:write name="facilityDetention" property="custodyfirstName" />&nbsp;</font></td>
</tr>
<tr>
    <td bgcolor="gray"><font color="white" face="bold" size="-1">CUSTODY LAST NAME</font></td>
    <td><font size="-1"><bean:write name="facilityDetention" property="custodylastName" />&nbsp;</font></td>
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
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</table>	    

	<BR>
	
</logic:iterate>
</logic:notEmpty>
	
</html:form>

<table align="center" border="0" width="500">
		<tr>
		<td colspan="2" align="center">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>

    </table>    

</body>
</html:html>