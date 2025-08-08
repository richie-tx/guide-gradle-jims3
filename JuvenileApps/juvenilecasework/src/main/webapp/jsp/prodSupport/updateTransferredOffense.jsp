<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.Features" %>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<!DOCTYPE HTML>

<head>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	#container {
		text-align: center;
	}
	
	#container div {
		margin-top : 20px;
		margin-bottom: 20px;
	}
	
	 table {
	 	border: 1px solid black;
	 	width: 500px;
	 	margin-top: 20px;
		margin-left: auto;
		margin-right: auto;
	 }
	 
	 table  th {
	 	font-family: Geneva, Arial, Helvetica, sans-serif;
		font-size: small;
		font-weight: bold;
		color: #000000;
		background-color: #cccccc;
		padding-right:5px;
		text-align:left;
	 }
	 
	 table th, td {
	 	border: 1px solid black;
	 	text-align: center;
	 }
	 
</style>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script>
$(document).ready(function(){
	$("#notice").html('<bean:write name="ProdSupportForm" property="msg" />');
  	var originalOffenseDate = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.offenseDate" formatKey="date.format.mmddyyyy"/>';	
	var originalAdjudicationDate = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.adjudicationDate" formatKey="date.format.mmddyyyy"/>';
	
	var originalCountyId= '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.countyId"/>';
	var originalOffenseCode = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.offenseCode" />';
	var originalCategory = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.category" />';
	var originalDpsCode = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.dpsCode" />';
	var originalPersonId = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.personId" />';
	
	
	var originJuvenileNumber = '<bean:write name="ProdSupportForm" property="originalJuvenileNumber" />';
	var originReferralNumber = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.referralNum" />';
	
	if(typeof  $("#offenseDate") != "undefined"){	
		datePickerSingle($("#offenseDate"),"Offense  Date",false);
	}
	
	if(typeof  $("#adjudicationDate") != "undefined"){	
		datePickerSingle($("#adjudicationDate"),"Adjudication  Date",false);
	}
	
	
	$("#mainMenuBtn").click(function(){
		$('form').attr('action',"DisplayProductionSupportMainPopup.do");
		$('form').submit();
	})
	
	$("#updateBtn").click(function(){
	$("#notice").html("");
		if ( originalOffenseDate === $("#offenseDate").val()
			&&  originalAdjudicationDate === $("#adjudicationDate").val()
			&& 	originalCountyId === $("#countyId").val()
			&&	originalOffenseCode === $("#offenseCode").val()
			&&  originalCategory === $("#category").val()
			&&  originalDpsCode === $("#dpsCode").val()
			&&  originalPersonId === $("#personId").val() 
			&&  originJuvenileNumber === $("#juvenileNum").val() 
			&&  originReferralNumber === $("#referralNumber").val()
			 ) {
			alert("At least one value must be modified to submit system changes.");
		} else {
			if ( isValid() ){				
				spinner();
				$('form').submit();
			}
		}
		
	})
	
	
})



function isValid(){
	
	
	//if ( $("#intakeJPO").val().length != 5  ){
	//	alert("Intake JPO is not valid");
	//	return false;
	//}
	
	if (  document.getElementById('juvenileNum') != null ) {
		if ( $("#juvenileNum").val() == "" ){
			alert("Juvinile Number is required");
			return false;
		}
		
		
		if ( !( $.isNumeric( $("#juvenileNum").val() )
				&& ($("#juvenileNum").val()).length == 6 )  ){
			alert("Juvenile Number is not valid");
			return false;
		}
	}
	
	
	if (  document.getElementById('referralNumber') != null ) {
		if ( $("#referralNumber").val() == "" ){
			alert("Referral Number is required");
			return false;
		}
		
		if ( !$.isNumeric($("#referralNumber").val() )
			|| $("#referralNumber").val().length != 4
			|| $("#referralNumber").val() < 1000   ){
			alert("Referral Number is not valid");
			return false;
		}
	}
	
	return true;
}
	
</script>
</head>
<body>
	<div id="container">
		<div>
			<h2>
			Transferred Offense Referral Record for TRANSOFFNSREF_ID = <bean:write name="ProdSupportForm" property="transferredOffenseRecord.transOffenseReferralId" />
			</h2>
			<hr>
		</div>
		<div id="notice" style="color: red;"></div>
			
			
			<html:form action="/UpdateTransferredOffenseRecord">
			<div>
				<table class="resultTbl">
					<tr>
						<th>TRANSOFFNSREF_ID</th>
						<td><bean:write name="ProdSupportForm" property="transferredOffenseRecord.transOffenseReferralId" /></td>
					</tr>
					<tr>
						<th>JUVENILENUMBER</th>
						<td><bean:write name="ProdSupportForm" property="originalJuvenileNumber" /></td>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEJUVENILENUM%>">
						<td style="text-align: left; padding-left: 5px;"><html:text styleId="juvenileNum" 
											name="ProdSupportForm" 
											property="transferredOffenseRecord.juvenileNum"
											maxlength="7"/>							
	 					</td>
	 					</jims:isAllowed>
					</tr>
					<tr>
						<th>REFERRALNUMBER</th>
						<td><bean:write name="ProdSupportForm" property="originalReferralNumber" /></td>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEJUVENILENUM%>">
							<td style="text-align: left; padding-left: 5px;"><html:text styleId="referralNumber" 
											name="ProdSupportForm" 
											property="transferredOffenseRecord.referralNum"
											maxlength="4"/></td>
						</jims:isAllowed>
					</tr>
					<tr>
						<th>FROMCOUNTYCD</th>
						<td><bean:write name="ProdSupportForm" property="originalCountyId" /></td>
						<td style="text-align: left; padding-left: 5px;"><html:text styleId="countyId" 
											name="ProdSupportForm" 
											property="transferredOffenseRecord.countyId"
									maxlength="3"/>							
						</td>
					</tr>
					<tr>
						<th>OFFENSECD</th>
						<td><bean:write name="ProdSupportForm" property="originalOffenseCode"/></td>
						<td style="text-align: left; padding-left: 5px;"><html:text styleId="offenseCode" 
											name="ProdSupportForm" 
											property="transferredOffenseRecord.offenseCode"
									maxlength="6"/>
						</td>
					</tr>
					<tr>
						<th>CATEGORYCD</th>
						<td><bean:write name="ProdSupportForm" property="originalCategory"/></td>
						<td style="text-align: left; padding-left: 5px;"><html:text styleId="category" 
											name="ProdSupportForm" 
											property="transferredOffenseRecord.category"
									maxlength="2"/>
						</td>
					</tr>
					<tr>
						<th>DPSCD</th>
						<td><bean:write name="ProdSupportForm" property="originalDpsCode" /></td>
						<td style="text-align: left; padding-left: 5px;"><html:text styleId="dpsCode" 
											name="ProdSupportForm" 
											property="transferredOffenseRecord.dpsCode"
									maxlength="8"/>
						</td
					</tr>
					<tr>
						<th>OFFENSEDATE</th>
						<td><bean:write name="ProdSupportForm" property="originalOffenseDate" formatKey="date.format.mmddyyyy"/></td>
						<td style="text-align: left; padding-left: 5px;"><input id="offenseDate"  name="transferredOffenseRecord.offenseDateStr"
										value="<bean:write name="ProdSupportForm" property="transferredOffenseRecord.offenseDate" formatKey="date.format.mmddyyyy"/>"	/></td>
					</tr>
					<tr>
						<th>ADJUDICATIONDATE</th>
						<td><bean:write name="ProdSupportForm" property="originalAdjudicationDate" formatKey="date.format.mmddyyyy"/></td>
						<td style="text-align: left; padding-left: 5px;"><input id="adjudicationDate"  name="transferredOffenseRecord.adjudicationDateStr"
						                    value="<bean:write name="ProdSupportForm" property="transferredOffenseRecord.adjudicationDate" formatKey="date.format.mmddyyyy"/>"											
											/></td>
						
					</tr>
					<tr>
						<th>PERSONID</th>
						<td><bean:write name="ProdSupportForm" property="originalPersonId" /></td>
						<td style="text-align: left; padding-left: 5px;"><html:text styleId="personId" 
											name="ProdSupportForm" 
											property="transferredOffenseRecord.personId"
									maxlength="7"/></td>
						
					</tr>					
				</table>
			</div>
<%-- 			<html:hidden styleId="newAssignmentDate" name="ProdSupportForm" property="assignmentDate"/> --%>
<%-- 			<html:hidden styleId="newIntakeDate" name="ProdSupportForm" property="intakeDate"/> --%>
			</html:form>
			<div id="command">
				<input id="updateBtn" type="button" value="Update"/>
				<input id="mainMenuBtn" type="button" value="Return to Main Menu"/>
			</div>
			
	</div>
</body>
</html:html>