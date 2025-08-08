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
	  	var intakeDate = '<bean:write name="ProdSupportForm" property="referralDetail.intakeDate"/>';
		var intakeDecision = '<bean:write name="ProdSupportForm" property="referralDetail.intakeDecision"/>';
		var originAssignmentType= '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.assignmentType"/>';
		var originSupervisionTypeCode = '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.supervisionTypeId" />';
		var originAssignmentDate = '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.assignmentDate" formatKey="date.format.mmddyyyy" />';
		var originIntakeDate = '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.intakeDate" formatKey="date.format.mmddyyyy" />';
		var originIntakeDecision = '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.intakeDecisionId" />';
		var originIntakeJPO = '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.jpoId" />';
		var originJuvenileNumber = '<bean:write name="ProdSupportForm" property="originalJuvenileNumber" />';
		var originReferralNumber = '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.referralNumber" />';
		
		if(typeof  $("#intakeDate") != "undefined"){	
			datePickerSingle($("#intakeDate"),"Intake  Date",false);
		}
		
		if(typeof  $("#assignmentDate") != "undefined"){	
			datePickerSingle($("#assignmentDate"),"Intake  Date",false);
		}
		
		$("#assignmentDate").change(function(){
			
			console.log( $("#assignmentDate").val() );
		})
		
		$("#intakeDate").change(function(){
			
			console.log( $("#newIntakeDate").val() < 0);
		})
		
		$("#mainMenuBtn").click(function(){
			$('form').attr('action',"DisplayProductionSupportMainPopup.do");
			$('form').submit();
		})
		
		$("#updateBtn").click(function(){
		$("#notice").html("");
			if ( originAssignmentType === $("#assmntType").val()
				&&  originSupervisionTypeCode === $("#supervisionType").val()
				&& 	originAssignmentDate === $("#assignmentDate").val()
				&&	originIntakeDate === $("#intakeDate").val()
				&&  originIntakeDecision === $("#intakeDecision").val()
				&&  originIntakeJPO === $("#intakeJPO").val()
				&&  originJuvenileNumber === $("#juvenileNumber").val() 
				&&  originReferralNumber === $("#referralNumber").val()
				 ) {
				alert("At least one value must be modified to submit system changes.");
			} else {
				if ( isValid() ){
					if ( $("#intakeDate").val() != intakeDate 
							&& $("#intakeDate").val() != originIntakeDate  ) {
						alert( 'Intake Date does not match the Referral table value. Please verify system entries.')
					}
					
					if ( $("#intakeDecision").val() != intakeDecision 
							&& $("#intakeDecision").val() != originIntakeDecision   ) {
						alert('Intake Decision does not match the Referral table value. Please verify system entries');
					}
					
					$("#newIntakeDate").val( $("#intakeDate").val() );
					$("#newAssignmentDate").val( $("#assignmentDate").val() );
					spinner();
					$('form').submit();
				}
			}
			
		})
		
		
	})
	
	
	
	function isValid(){
		if ( $("#assmntType").val() == "" ){
			alert("Assmnt Type is required");
			return false;
		}
		
		if ( $("#supervisionType").val() == "" ){
			alert("Supervision Type code is required");
			return false;
		}
		
		if ( $("#assignmentDate").val() == "" ){
			alert("Assignment Date is required");
			return false;
		}
		
		if ( $("#intakeDate").val() == "" ){
			alert("Intake Date is required");
			return false;
		}
		
		if ( $("#intakeDecision").val() == "" ){
			alert("Intake Decision is required");
			return false;
		}
		
		if ( $("#intakeJPO").val() == "" ){
			alert("Intake JPO is required");
			return false;
		}
		
		//if ( $("#intakeJPO").val().length != 5  ){
		//	alert("Intake JPO is not valid");
		//	return false;
		//}
		
		if (  document.getElementById('juvenileNumber') != null ) {
			if ( $("#juvenileNumber").val() == "" ){
				alert("Juvinile Number is required");
				return false;
			}
			
			
			if ( !( $.isNumeric( $("#juvenileNumber").val() )
					&& ($("#juvenileNumber").val()).length == 6 )  ){
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
			Intake History Record for Juvenile Number = <bean:write name="ProdSupportForm" property="juvenileId" />
			and Referral Number = <bean:write name="ProdSupportForm" property="referralId" /></h2>
			<hr>
		</div>
		<div id="notice" style="color: red;"></div>
			
			
			<html:form action="/UpdateIntakeHistoryRecord">
			<div>
				<table class="resultTbl">
					<tr>
						<th>Intake History Id</th>
						<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.OID" /></td>
					</tr>
					<tr>
						<th>Assmnt Type</th>
						<td><bean:write name="ProdSupportForm" property="originalAssmntType" /></td>
						<td style="text-align: left; padding-left: 5px;">
							<html:select styleId="assmntType" name="ProdSupportForm"  property="intakeHistoryRecord.assignmentType">
	 						<html:option value="">Select a New Value</html:option>
	 						<html:optionsCollection name="ProdSupportForm" property="referralAssignmentCodes" value="code" label="codeWithDescription" />
	 						</html:select>
	 					</td>
					</tr>
					<tr>
						<th>Supervision Type Code</th>
						<td><bean:write name="ProdSupportForm" property="originalSupervisionType" /></td>
						<td style="text-align: left; padding-left: 5px;">
							<html:select styleId="supervisionType" name="ProdSupportForm" property="intakeHistoryRecord.supervisionTypeId" style="width:300px">
								<html:option value=""><bean:message key="select.generic"/> </html:option>
								<html:optionsCollection name="ProdSupportForm" property="supervisionTypeCodes" value="code" label="description" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>Assignment Date</th>
						<td><bean:write name="ProdSupportForm" property="originalAssignmenDate" formatKey="date.format.mmddyyyy" /></td>
						<td style="text-align: left; padding-left: 5px;"><input id="assignmentDate" 
									value="<bean:write name="ProdSupportForm" property="intakeHistoryRecord.assignmentDate"  formatKey="date.format.mmddyyyy" />"/></td>
					</tr>
					<tr>
						<th>Intake Date</th>
						<td><bean:write name="ProdSupportForm" property="originalIntakeDate"  formatKey="date.format.mmddyyyy"/></td>
						<td style="text-align: left; padding-left: 5px;"><input id="intakeDate" 
									value="<bean:write name="ProdSupportForm" property="intakeHistoryRecord.intakeDate" formatKey="date.format.mmddyyyy"/>"/></td>
					</tr>
					<tr>
						<th>Intake Decision</th>
						<td><bean:write name="ProdSupportForm" property="originalIntakeDecision" /></td>
						<td style="text-align: left; padding-left: 5px;"><html:select styleId="intakeDecision"  
										name="ProdSupportForm" 
										property="intakeHistoryRecord.intakeDecisionId" >
								<html:option value=""><bean:message key="select.generic"/> </html:option>
								<html:optionsCollection name="ProdSupportForm" property="outcomeCodes" label="codeWithDescription" value="code" />
							</html:select>
						</td
					</tr>
					<tr>
						<th>Intake JPO</th>
						<td><bean:write name="ProdSupportForm" property="originalIntakeJPO" /></td>
						<td style="text-align: left; padding-left: 5px;"><html:text styleId="intakeJPO" 
										name="ProdSupportForm" 
										property="intakeHistoryRecord.jpoId"
										maxlength="5"/></td>
					</tr>
					<tr>
						<th>Juvenile Number</th>
						<td><bean:write name="ProdSupportForm" property="originalJuvenileNumber" /></td>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_INTAKEHISTORYFULLACCESS%>">
							<td style="text-align: left; padding-left: 5px;"><html:text styleId="juvenileNumber" 
											name="ProdSupportForm" 
											property="intakeHistoryRecord.juvenileNum" 
											maxlength="7"/></td>
						</jims:isAllowed>
					</tr>
					<tr>
						<th>Referral Number</th>
						<td><bean:write name="ProdSupportForm" property="originalReferralNumber" /></td>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_INTAKEHISTORYFULLACCESS%>">
							<td style="text-align: left; padding-left: 5px;"><html:text styleId="referralNumber" 
											name="ProdSupportForm" 
											property="intakeHistoryRecord.referralNumber"
											maxlength="4"/></td>
						</jims:isAllowed>
					</tr>
					<tr>
						<th>Create User</th>
						<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.createUserID" /></td>
					</tr>
					<tr>
						<th>Create Date</th>
						<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.createTimestamp" formatKey="date.format.mmddyyyy" /></td>
					</tr>
					<tr>
						<th>Update User</th>
						<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.updateUserID" /></td>
					</tr>
					<tr>
						<th>Update Date</th>
						<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.updateTimestamp" /></td>
					</tr>
					<tr>
						<th>Create JIMS2 User</th>
						<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.createJIMS2UserID" /></td>
					</tr>
					<tr>
						<th>Update JIMS2 User</th>
						<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.updateJIMS2UserID" /></td>
					</tr>
				</table>
			</div>
			<html:hidden styleId="newAssignmentDate" name="ProdSupportForm" property="assignmentDate"/>
			<html:hidden styleId="newIntakeDate" name="ProdSupportForm" property="intakeDate"/>
			</html:form>
			<div id="command">
				<input id="updateBtn" type="button" value="Update"/>
				<input id="mainMenuBtn" type="button" value="Return to Main Menu"/>
			</div>
			
	</div>
</body>
</html:html>