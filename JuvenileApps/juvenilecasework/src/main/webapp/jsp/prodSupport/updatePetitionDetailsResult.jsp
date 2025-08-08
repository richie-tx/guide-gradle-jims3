<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.Features" %>

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updatePetitionDetailsResult.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<style>
	 h2 { text-align: center;}
	 #message {
	 	text-align: center;
	 	color: red;
	 }
	 
	 #container{
	 	margin-top: 50px;
	 
	 }
	 
	.updatedPetitionDetail{
	 	width: 500px;
	 	margin-top: 15px;
	 	margin-bottom: 15px;
	 	margin-left: auto;
	 	margin-right: auto;
	 }
	 .updatedPetitionDetail label{
	 	display: inline-block;
	 	width: 200px; text-align: right;
	 	font-weight: bold;
	 }
	 .updatedPetitionDetail div {
	 	margin-left: 25px;
	 	display: inline-block;
	 	
	 }
	 
	 #command {
	 	text-align: center;
	 }
	 
	 #command div {
	 	display: inline-block;
	 }
	 
	 #command input {
	 	margin-top: 45px;
	 	width: 150px;
	 }
	 
	 table {
	 	border: 1px solid black;
	 	width: 500px;
		margin-left: auto;
		margin-right: auto;
	 }
	 
	 .updatedPetitionDetail th {
	 	font-family: Geneva, Arial, Helvetica, sans-serif;
		font-size: small;
		font-weight: bold;
		color: #000000;
		background-color: #cccccc;
		padding-right:5px;
		text-align:left;
	 }
	 
	 .updatedPetitionDetail td {
	 	border: 1px solid black;
	 	text-align: center;
	 }
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updatePetitionDetails.js?Clr=Y"></script>
<script>
	function backToQuery(){
		$("#goBackToQuery").submit();
	}
	
	function updateAgain(){
		$("#viewDetailsForm").submit();
	}
</script>
</head>
<body>
	<h2 align="center">Petition Detail Record for 
			<bean:write name="ProdSupportForm" property="juvenileId" />
			and Referral <bean:write name="ProdSupportForm" property="referralId" /></h2>
	<hr>
	<div id="message">
		<logic:notEmpty	name="ProdSupportForm" property="msg">
			<bean:write name="ProdSupportForm" property="msg" />
		</logic:notEmpty>
	</div>
	<div id="container">
		<logic:iterate id="petitionDetail" name="ProdSupportForm" property="petitionDetails" indexId="index">
		<table class="resultTbl" align="center" border="1" width="850">
		<tr class="updatedPetitionDetail">		
			<th>Juvenile Number:</th>
			<td><bean:write name="petitionDetail" property="juvenileNum" /></td>				
			 <td>
			 <jims2:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEJUVENILENUM%>">
			 	<font size="-1"><html:text name="ProdSupportForm" property="juvenileId" size="6" styleId='juvNum' maxlength="8" indexed="true"/></font>
			 </jims2:isAllowed>
			 </td>
		</tr> 
			<tr class="updatedPetitionDetail">
				<th>Petition Allegation:</th>
				<td><bean:write name="petitionDetail" property="petitionAllegation" /></td>
				<td> 
					<html:select name="ProdSupportForm" property="petitionAllegation" styleId='crtResult' indexed="true">
					<html:option key="select.generic" value="" />
					<html:optionsCollection name="ProdSupportForm" property="offenseCodes" value="alphaCode" label="alphaCode"/> 				
					</html:select>
				</td>
				<bean:define id="petitionId" name='petitionDetail' property='OID' type="java.lang.String"></bean:define>
				<html:hidden styleId='petOID' name='ProdSupportForm' property='OID' value='<%=petitionId%>'/>
			</tr>
			<tr class="updatedPetitionDetail">
				<th nowrap="nowrap">Petition Allegation Severity:</th>
				<td><bean:write name="petitionDetail" property="severity" /></td>
				<td><font size="-1"><html:text name="ProdSupportForm" property="petitionSeverity" styleId='petSeverity' maxlength="4"/></font></td>
			</tr>
			<tr class="updatedPetitionDetail">
				<th>Petition Amendment:</th>
				<td><bean:write name="petitionDetail" property="amend" /></td>
				<td><font size="-1"><html:text name="ProdSupportForm" property="petitionAmended" styleId='petAmend' maxlength="4"/></font></td>
			</tr>
			<tr class="updatedPetitionDetail">
				<th>Petition Date:</th>
					<td><font size="-1"><bean:write name="petitionDetail" property="petitionDate" formatKey="date.format.mmddyyyy" /></font></td>
					<td><font size="-1"><html:text name="ProdSupportForm" property="newPetitionDate" styleId='petDate'/></font></td>
 			</tr>
			<tr class="updatedPetitionDetail">
				<th>Petition Number:</th>
				<td><bean:write name="petitionDetail" property="petitionNum" /></td>
				<td><font size="-1"><html:text name="ProdSupportForm" property="petitionNum" styleId='petNumber' maxlength="15"/></font></td>
			</tr>
			<tr class="updatedPetitionDetail">
				<th>Petition Type:</th>
				<td><bean:write name="petitionDetail" property="petitionType" /></td>
				<td><font size="-1"><html:text name="ProdSupportForm" property="petitionType" styleId='petType' maxlength="4"/></font></td>
			</tr>
			
			<tr class="updatedPetitionDetail">
				<th>Referral Number:</th>
				<td><font size="-1"><bean:write name="petitionDetail" property="referralNum"/>&nbsp;</font></td>
				<td><font size="-1"><html:text name="ProdSupportForm" property="newReferralNum" styleId='refNum' maxlength="4"/></font></td>
			</tr>
			<tr class="updatedPetitionDetail">
				<th>Petition Status:</th>
				<td><bean:write name="petitionDetail" property="petitionStatus" /></td>
				<td><font size="-1"><html:text name="ProdSupportForm" property="petitionStatus" styleId='petStatus' maxlength="4"/></font></td>
			</tr>

			<tr class="updatedPetitionDetail">
				<th>Sequence Number:</th>
				<td><bean:write name="petitionDetail" property="sequenceNum" /></td>
				<td><font size="-1"><html:text name="ProdSupportForm" property="sequenceNumber" styleId='petSeqNum' maxlength="4"/></font></td>
			</tr>			
			<tr class="updatedPetitionDetail">
				<th>Termination Date:</th>
				<td><bean:write name="petitionDetail" property="terminationDate"  formatKey="date.format.mmddyyyy"/></td>	
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PS_TERMINATIONDATE%>'>			
					<td><font size="-1"><html:text name="ProdSupportForm" property="newTerminationDate" styleId='terminationDate' /></font></td>
				</jims2:isAllowed>
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PS_TERMINATIONDATE%>' value="false">			
					<td><html:text name="ProdSupportForm" property="newTerminationDate" styleId='terminationDate' disabled="true"/></font></td>
				</jims2:isAllowed>
			</tr>
			<tr class="updatedPetitionDetail">
				<th>Termination Create Date:</th>
					<td><bean:write name="petitionDetail" property="terminationCreateDate"  formatKey="date.format.mmddyyyy"/></td>
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PS_TERMINATIONDATE%>'>
				<td><font size="-1"><html:text name="ProdSupportForm" property="newTerminationCreateDate" styleId='terminationCreateDate'/></font></td>
			 </jims2:isAllowed>
			 <jims2:isAllowed requiredFeatures='<%=Features.JCW_PS_TERMINATIONDATE%>' value="false">
			 		<td><html:text name="ProdSupportForm" property="newTerminationCreateDate" styleId='terminationCreateDate' disabled="true"/></font></td>
				</jims2:isAllowed>	
			</tr>
			<tr class="updatedPetitionDetail">
				<th>DPScode:</th>
				<td><bean:write name="petitionDetail" property="DPSCode" /></td>
				<td><font size="-1"><html:text name="ProdSupportForm" property="DPSCode" styleId='dpsCode' maxlength="3"/></font></td>
			</tr>
			<tr class="updatedPetitionDetail">
				<th>CJIS Number:</th>
				<td><bean:write name="petitionDetail" property="petCJISNum" /></td>
				<td><font size="-1"><html:text name="ProdSupportForm" property="CJISNumber" styleId='cjisNum' maxlength="14"/></font></td>
			</tr>
			<tr class="updatedPetitionDetail">
				<th>Last Change Date/Time:</th>
				<td><bean:write name="petitionDetail" property="lastChangeDate" /></td>
			</tr>
			<tr class="updatedPetitionDetail">
				<th>LastChange User:</th>
				<td><bean:write name="petitionDetail" property="lastChangeUser" /></td>
			</tr>
			
		</table>
		</logic:iterate>
	</div>
	<div id="command">
		<div>
			<html:form styleId="goBackToQuery" method="post" action="/MainMenu" onsubmit="return this;">
				<input type="button" value="Back to Main Menu" onclick="backToQuery()"/>
			</html:form>
		</div>
		<div>
			<html:form method="post" styleId="viewDetailsForm"
									  action="/updatePetitionDetailsQuery">
			<input id="submitBtn" type="button" value="Update Petition"/>
			
			<html:hidden styleId="newCharge" name="ProdSupportForm" property="assignmentId"/>
			<html:hidden styleId="petitionOid" name="ProdSupportForm" property="OID"/>
			<html:hidden styleId="updateSeq" name="ProdSupportForm" property="hiddenForward"/>
			<html:hidden styleId="referralDt" name="ProdSupportForm" property="referralDate"/>
			<html:hidden styleId='petitionDt'  name='ProdSupportForm' property='newPetitionDate'/>
			<html:hidden styleId='petitionAmend'  name='ProdSupportForm' property='petitionAmended'/>
			<html:hidden styleId='juvenileId'  name='ProdSupportForm' property='juvenileId'/>
			<html:hidden styleId='petitionNum'  name='ProdSupportForm' property='petitionNum'/>
			<html:hidden styleId='petitionType'  name='ProdSupportForm' property='petitionType'/>
			<html:hidden styleId='referralId'  name='ProdSupportForm' property='newReferralNum'/>
			<html:hidden styleId='petitionStat'  name='ProdSupportForm' property='petitionStatus'/>
			<html:hidden styleId='petitionSeq'  name='ProdSupportForm' property='sequenceNumber'/>
			<html:hidden styleId='petitionCJIS'  name='ProdSupportForm' property='CJISNumber'/>
			<html:hidden styleId='petitionDPS'  name='ProdSupportForm' property='DPSCode'/>
			<html:hidden styleId='terminationDt'  name='ProdSupportForm' property='newTerminationDate'/>
			<html:hidden styleId='terminationCreateDt'  name='ProdSupportForm' property='newTerminationCreateDate'/>
			<html:hidden name="ProdSupportForm" property="petitionSeverity" styleId='petSeverityId'/>
		</html:form>
		</div>
		
		
	</div>				
</body>

</html:html>