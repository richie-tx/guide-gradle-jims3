<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteAssociatedMsReferralQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 

<script language="javascript">
$(document).ready(function(){
	
	$("#deleteReferral").click(function(e){
		 e.preventDefault(); 
		 
		 var comments = $("#delComments").val();		
		 if (comments.trim() === '') {
	            alert("Please provide comments on why you're deleting this referral.");
	            return false;
	        }
		 else
			 {
			 var newField = $('<input>').attr({
			        type: 'hidden',
			        name: 'delComments', 
			        value: comments 
			    });

			 $('form').append(newField); 
			    
			 }
		 
		if ( confirmDelete() ) {
			$('form')
			.attr(
					'action',
					"/JuvenileCasework/PerformDeleteAssociatedMsReferral.do?submitAction=Delete Referral");
			$('form').submit();
			spinner();
		}
	})
})

function setTableId(idName){
     console.log("table name: " + idName);
     $("#tableId").val(idName);
     $('form')
		.attr(
				'action',
				"/JuvenileCasework/PerformDeleteAssociatedMsReferral.do?submitAction=Details&tableId="+idName);
	spinner();
	$('form').submit();
}

function confirmDelete(){
	if(confirm('Are you sure you want to delete the Referral and its associated Offense and Intake History records?'))
		return true;	
	else
		return false;
}

</script>

</head>

<html:form>
<html:hidden styleId="tableId" name="ProdSupportForm" property="tableId"/>


	<div>
	
	<h2 align="center">Associated Records for Referral_ID = 
			<bean:write name="ProdSupportForm" property="referralOID" /></h2>
	<p align="center"><b><i>The following associated records will be affected upon <font color="red">DELETION</font> of Referral_Id <bean:write name="ProdSupportForm" property="referralOID" /> </i></b></p>
	<p align="center"><b><i>Please review the items below and make nay necessary updates or deletions </i></b></p>
	<br><br>
	
	
<hr>
<table width="100%" cellspacing="1">	
	<!-- The idea here is to present a simple row count and offer a button to drill down and 
	display another page that offers details on the table contents. -->
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font></td>
	</tr>
	
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Offenses</b></font><br>

	  <logic:notEmpty name="ProdSupportForm" property="juvRefOffensesCount">
	  	<logic:notEqual name="ProdSupportForm" property="juvRefOffensesCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="juvRefOffensesCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('referralOffense')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="juvRefOffensesCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="juvRefOffensesCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>

	</td>
	<td align="center">
	<font size="+1"><b>Associated Assignments</b></font><br>

	  <logic:notEmpty name="ProdSupportForm" property="assignmentCount">
	  	<logic:notEqual name="ProdSupportForm" property="assignmentCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="assignmentCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('assignment')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="assignmentCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="assignmentCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>

	</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
		<td align="center">
		<font size="+1"><b>Associated Intake History</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="intakeHistCount">
		  	<logic:notEqual name="ProdSupportForm" property="intakeHistCount" value="0">	 
		   		<i><bean:write name="ProdSupportForm" property="intakeHistCount" />
		    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
		    	<input type="button" name="details" value="Details" onClick="setTableId('intakeHistory')"/>	  
		  	</logic:notEqual>
		 	<logic:equal name="ProdSupportForm" property="intakeHistCount" value="0">
		    	<i>No Result(s) Returned</i>
		  	</logic:equal>
		  </logic:notEmpty>
		  
		  <logic:empty name="ProdSupportForm" property="intakeHistCount">
		    <i>No Result(s) Returned</i>
		  </logic:empty>
	
		</td>
		<td align="center">
		<font size="+1"><b>Associated Trans Offense Referrals</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="transOffenseReferralsCount">
		  	<logic:notEqual name="ProdSupportForm" property="transOffenseReferralsCount" value="0">	 
		   		<i><bean:write name="ProdSupportForm" property="transOffenseReferralsCount" />
		    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
		    	<input type="button" name="details" value="Details" onClick="setTableId('transOffenseReferral')"/>	  
		  	</logic:notEqual>
		 	<logic:equal name="ProdSupportForm" property="transOffenseReferralsCount" value="0">
		    	<i>No Result(s) Returned</i>
		  	</logic:equal>
		  </logic:notEmpty>
		  
		  <logic:empty name="ProdSupportForm" property="transOffenseReferralsCount">
		    <i>No Result(s) Returned</i>
		  </logic:empty>
	
		</td>
	</tr>
	
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
		<td align="center">
		<font size="+1"><b>Associated Facility Headers</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="facilityHeaderCount">
		  	<logic:notEqual name="ProdSupportForm" property="facilityHeaderCount" value="0">	 
		   		<i><bean:write name="ProdSupportForm" property="facilityHeaderCount" />
		    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
		    	<input type="button" name="details" value="Details" onClick="setTableId('facilityHeader')"/>	  
		  	</logic:notEqual>
		 	<logic:equal name="ProdSupportForm" property="facilityHeaderCount" value="0">
		    	<i>No Result(s) Returned</i>
		  	</logic:equal>
		  </logic:notEmpty>
		  
		  <logic:empty name="ProdSupportForm" property="facilityHeaderCount">
		    <i>No Result(s) Returned</i>
		  </logic:empty>
	
		</td>
		
		<td align="center">
		<font size="+1"><b>Associated detention facilities</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="facilityDetentionCount">
		  	<logic:notEqual name="ProdSupportForm" property="facilityDetentionCount" value="0">	 
		   		<i><bean:write name="ProdSupportForm" property="facilityDetentionCount" />
		    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
		    	<input type="button" name="details" value="Details" onClick="setTableId('facilityDetention')"/>	  
		  	</logic:notEqual>
		 	<logic:equal name="ProdSupportForm" property="facilityDetentionCount" value="0">
		    	<i>No Result(s) Returned</i>
		  	</logic:equal>
		  </logic:notEmpty>
		  
		  <logic:empty name="ProdSupportForm" property="facilityDetentionCount">
		    <i>No Result(s) Returned</i>
		  </logic:empty>
	
		</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
		<td align="center">
		<font size="+1"><b>Associated Detention Court Records</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="juvDetCourtRecordCount">
		  	<logic:notEqual name="ProdSupportForm" property="juvDetCourtRecordCount" value="0">	 
		   		<i><bean:write name="ProdSupportForm" property="juvDetCourtRecordCount" />
		    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
		    	<input type="button" name="details" value="Details" onClick="setTableId('juvDetCourtRecord')"/>	  
		  	</logic:notEqual>
		 	<logic:equal name="ProdSupportForm" property="juvDetCourtRecordCount" value="0">
		    	<i>No Result(s) Returned</i>
		  	</logic:equal>
		  </logic:notEmpty>
		  
		  <logic:empty name="ProdSupportForm" property="juvDetCourtRecordCount">
		    <i>No Result(s) Returned</i>
		  </logic:empty>
	
		</td>
		
		<td align="center">
		<font size="+1"><b>Associated District Court Records</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="juvDistCourtRecordCount">
		  	<logic:notEqual name="ProdSupportForm" property="juvDistCourtRecordCount" value="0">	 
		   		<i><bean:write name="ProdSupportForm" property="juvDistCourtRecordCount" />
		    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
		    	<input type="button" name="details" value="Details" onClick="setTableId('juvDistCourtRecord')"/>	  
		  	</logic:notEqual>
		 	<logic:equal name="ProdSupportForm" property="juvDistCourtRecordCount" value="0">
		    	<i>No Result(s) Returned</i>
		  	</logic:equal>
		  </logic:notEmpty>
		  
		  <logic:empty name="ProdSupportForm" property="juvDistCourtRecordCount">
		    <i>No Result(s) Returned</i>
		  </logic:empty>
	
		</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
		<td align="center">
		<font size="+1"><b>Associated Petitions </b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="petitionRecordCount">
		  	<logic:notEqual name="ProdSupportForm" property="petitionRecordCount" value="0">	 
		   		<i><bean:write name="ProdSupportForm" property="petitionRecordCount" />
		    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
		    	<input type="button" name="details" value="Details" onClick="setTableId('petition')"/>	  
		  	</logic:notEqual>
		 	<logic:equal name="ProdSupportForm" property="petitionRecordCount" value="0">
		    	<i>No Result(s) Returned</i>
		  	</logic:equal>
		  </logic:notEmpty>
		  
		  <logic:empty name="ProdSupportForm" property="petitionRecordCount">
		    <i>No Result(s) Returned</i>
		  </logic:empty>
	
		</td>
		
		<td align="center">
		<font size="+1"><b>Associated Casefiles</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="casefileCount">
		  	<logic:notEqual name="ProdSupportForm" property="casefileCount" value="0">	 
		   		<i><bean:write name="ProdSupportForm" property="casefileCount" />
		    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
		    	<input type="button" name="details" value="Details" onClick="setTableId('casefile')"/>	  
		  	</logic:notEqual>
		 	<logic:equal name="ProdSupportForm" property="casefileCount" value="0">
		    	<i>No Result(s) Returned</i>
		  	</logic:equal>
		  </logic:notEmpty>
		  
		  <logic:empty name="ProdSupportForm" property="casefileCount">
		    <i>No Result(s) Returned</i>
		  </logic:empty>
	
		</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
		<td align="center">
		<font size="+1"><b>Associated Casefile Closings </b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="casefileClosingCount">
		  	<logic:notEqual name="ProdSupportForm" property="casefileClosingCount" value="0">	 
		   		<i><bean:write name="ProdSupportForm" property="casefileClosingCount" />
		    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
		    	<input type="button" name="details" value="Details" onClick="setTableId('casefileClosing')"/>	  
		  	</logic:notEqual>
		 	<logic:equal name="ProdSupportForm" property="casefileClosingCount" value="0">
		    	<i>No Result(s) Returned</i>
		  	</logic:equal>
		  </logic:notEmpty>
		  
		  <logic:empty name="ProdSupportForm" property="casefileClosingCount">
		    <i>No Result(s) Returned</i>
		  </logic:empty>
	
		</td>
		
		<td align="center">
		<font size="+1"><b>Associated JuvProgRefs</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="programReferralCount">
		  	<logic:notEqual name="ProdSupportForm" property="programReferralCount" value="0">	 
		   		<i><bean:write name="ProdSupportForm" property="programReferralCount" />
		    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
		    	<input type="button" name="details" value="Details" onClick="setTableId('juvprogref')"/>	  
		  	</logic:notEqual>
		 	<logic:equal name="ProdSupportForm" property="programReferralCount" value="0">
		    	<i>No Result(s) Returned</i>
		  	</logic:equal>
	 	 </logic:notEmpty>
	  
		  <logic:empty name="ProdSupportForm" property="programReferralCount">
		    <i>No Result(s) Returned</i>
		  </logic:empty>
		</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated PACT Risk and Needs</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="riskNeedLevelCount">
	  	<logic:notEqual name="ProdSupportForm" property="riskNeedLevelCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="riskNeedLevelCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('riskanalysis')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="riskNeedLevelCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="riskNeedLevelCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	<td align="center">
	<font size="+1"><b>Associated Risk Responses</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="riskresponsesCount">
	  	<logic:notEqual name="ProdSupportForm" property="riskresponsesCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="riskresponsesCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('riskresponse')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="riskresponsesCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="riskresponsesCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated MAYSI Details</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="maysidetailCount">
	  	<logic:notEqual name="ProdSupportForm" property="maysidetailCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="maysidetailCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('maysiDetail')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="maysidetailCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="maysidetailCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	<td align="center">
	<font size="+1"><b>Associated MAYSI Assessments</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="maysiCount">
	  	<logic:notEqual name="ProdSupportForm" property="maysiCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="maysiCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('maysiAssessment')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="maysiCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="maysiCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>
	
	</tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Juvenile Warrants</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="juvenileWarrantCount">
	  	<logic:notEqual name="ProdSupportForm" property="juvenileWarrantCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="juvenileWarrantCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('juvenileWarrant')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="juvenileWarrantCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="juvenileWarrantCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	<td align="center">
	<font size="+1"><b>Associated Juvenile Warrant Charges</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="juvenileWarrantChargeCount">
	  	<logic:notEqual name="ProdSupportForm" property="juvenileWarrantChargeCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="juvenileWarrantChargeCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('juvenileWarrantCharge')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="juvenileWarrantChargeCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="juvenileWarrantChargeCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>
	
	</tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Juvenile Warrant Fields</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="juvenileWarrantFieldCount">
	  	<logic:notEqual name="ProdSupportForm" property="juvenileWarrantFieldCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="juvenileWarrantFieldCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('juvenileWarrantField')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="juvenileWarrantFieldCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="juvenileWarrantFieldCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	<td align="center">
	<font size="+1"><b>Associated Juvenile Warrant Service Officers</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="juvenileWarrantServiceOfficerCount">
	  	<logic:notEqual name="ProdSupportForm" property="juvenileWarrantServiceOfficerCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="juvenileWarrantServiceOfficerCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('juvenileWarrantServiceOfficer')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="juvenileWarrantServiceOfficerCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="juvenileWarrantServiceOfficerCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>
	
	</tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Juvenile Inactive Warrants</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="juvenileInactivatedWarrantCount">
	  	<logic:notEqual name="ProdSupportForm" property="juvenileInactivatedWarrantCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="juvenileInactivatedWarrantCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('juvenileInactivatedWarrant')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="juvenileInactivatedWarrantCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="juvenileInactivatedWarrantCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	<td align="center">
	<font size="+1"><b>Associated Juvenile Warrant Recalls</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="juvenileWarrantRecallCount">
	  	<logic:notEqual name="ProdSupportForm" property="juvenileWarrantRecallCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="juvenileWarrantRecallCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('juvenileWarrantRecall')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="juvenileWarrantRecallCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="juvenileWarrantRecallCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>
	
	</tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Juvenile Warrant Associates</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="juvenileWarrantAssociateCount">
	  	<logic:notEqual name="ProdSupportForm" property="juvenileWarrantAssociateCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="juvenileWarrantAssociateCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('juvenileWarrantAssociate')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="juvenileWarrantAssociateCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="juvenileWarrantAssociateCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	<td align="center">
	<font size="+1"><b>Associated Juvenile Warrant Services</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="juvenileWarrantServeCount">
	  	<logic:notEqual name="ProdSupportForm" property="juvenileWarrantServeCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="juvenileWarrantServeCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('juvenileWarrantServe')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="juvenileWarrantServeCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="juvenileWarrantServeCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>		
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Cautions</b></font><br>

		<logic:notEmpty name="ProdSupportForm" property="associatedCautionCount">
		  	<logic:notEqual name="ProdSupportForm" property="associatedCautionCount" value="0">	 
		   		<i><bean:write name="ProdSupportForm" property="associatedCautionCount" />
		    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
		    	<input type="button" name="details" value="Details" onClick="setTableId('associatedCaution')"/>	  
		  	</logic:notEqual>
		 	<logic:equal name="ProdSupportForm" property="associatedCautionCount" value="0">
		    	<i>No Result(s) Returned</i>
		  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="associatedCautionCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	<td align="center">
	<font size="+1"><b>Associated Charges</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="associatedChargeCount">
	  	<logic:notEqual name="ProdSupportForm" property="associatedChargeCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="associatedChargeCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('associatedCharge')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="associatedChargeCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="associatedChargeCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Scar Marks</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="associatedScarMarkCount">
	  	<logic:notEqual name="ProdSupportForm" property="associatedScarMarkCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="associatedScarMarkCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('associatedScarMark')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="associatedScarMarkCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="associatedScarMarkCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	<td align="center">
	<font size="+1"><b>Associated Tattoos</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="associatedTattooCount">
	  	<logic:notEqual name="ProdSupportForm" property="associatedTattooCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="associatedTattooCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('associatedTattoo')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="associatedTattooCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="associatedTattooCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Addresses</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="associatedAddressCount">
	  	<logic:notEqual name="ProdSupportForm" property="associatedAddressCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="associatedAddressCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('associatedAddress')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="associatedAddressCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="associatedAddressCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	
	<td align="center">
	<font size="+1"><b>Associated Corespondents</b></font><br>

	<logic:notEmpty name="ProdSupportForm" property="corespondentCount">
	  	<logic:notEqual name="ProdSupportForm" property="corespondentCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="corespondentCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('associatedCorespondent')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="corespondentCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	</logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="corespondentCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
	<td align="center">
	<font size="+1"><b>Associated VOPs</b></font><br>
	  <logic:notEmpty name="ProdSupportForm" property="jcVOPCount">
	  	<logic:notEqual name="ProdSupportForm" property="jcVOPCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="jcVOPCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="button" name="details" value="Details" onClick="setTableId('associatedVOPs')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="jcVOPCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="jcVOPCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	<td></td>
	</tr>		
	</table>
	
	<BR>
	<table align="center"">
	<tr>
	  <td>
	   <b>Comments</b>
	   </td>
	  <td>
	    <textarea id="delComments" name="delComments" maxlength="250" rows="6" cols="50" style="overflow: hidden;resize: none;"></textarea>
	  </td>
	</tr>
	</table>
	
	<table border="0" width="700" align="center">
	
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	</table>
</div>
	<p align="center">
		<html:submit property="submitAction" styleId="deleteReferral"><bean:message key="button.deleteReferral" /></html:submit>
	</p>
</html:form>
<html:form action="/DeleteMsReferralQuery?clr=Y">
<p align="center">
	<input type="submit" name="details" value="Back to Query"/>
</p>
</html:form>	

</html:html>