<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/assessUpdateQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script language="javascript">

$(document).ready(function(){
	
	if(typeof  $("#newActDate") != "undefined"){	
		datePickerSingle($("#newActDate"),"Date entered ",false);
	}
	$("#updateRecord").click(function(){
		if ( confirmUpdate() ) {
			spinner();
		} else {
			return false;
		}
	});
	
	$("body").on('change', '#newSupervisionNum', function(e){		  
		var newSprvNum     = $("#newSupervisionNum").val();
		var hiddenSprvNum  = $("#curBookingSprvNum").val();
		
		if( newSprvNum === hiddenSprvNum ){
			
			alert("Supervision Number matches previous. Please select a different Supervision Number");
		}
		else{
			$("#newSprvNum").val(newSprvNum);
		}
	 });




function confirmUpdate(){
	
	var casefileId  = document.getElementById ('newSupervisionNum');

	if (verifyActDate() || casefileId.value != "")
	{
		if(confirm('Are you sure you want to update the assessment?'))
			return true;	
		else
			return false;
	}
	else 
		alert('You must make a change to Submit.');
		return false;
}


function verifyActDate(){
	if ( $("#newActDate").val() != null &&
			$("#newActDate").val() != "") {
		return true;
	} else {
		return false;
	}
}



});
</script>

</head>

<!-- Error Message Area -->
<logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="700" align="center">

		<tr align="center">
			<td colspan="4"><font style="font-weight: bold;" color="#FF0000"
				size="3" face="Arial"> <bean:write name="ProdSupportForm"
				property="msg" /> </font></td>
		</tr>
	</table>
</logic:notEqual>
<!-- End Error Message Area -->


<div>
<html:form action="/PerformAssessUpdate" onsubmit="return this;">

	<h2 align="center">Results for Risk Assessment ID = <bean:write
		name="ProdSupportForm" property="riskanalysisId" /></h2>
	<logic:notEmpty name="ProdSupportForm" property="riskanalyses">

		<p>&nbsp;</p>


		<logic:iterate id="riskanalyses" name="ProdSupportForm"
			property="riskanalyses">
			<table class="resultTbl" border="1" width="750" align="center">

				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKANALYSIS_ID</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="riskAnalysisId" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">FINALSCORE</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="finalScore" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSMENTTYPE</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="assessmentType" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="caseFileId" />&nbsp;</font></td>
					<html:hidden styleId='curBookingSprvNum' name='riskanalyses' property='caseFileId' indexed="true"/>
						
				<td> 
					<html:select name="ProdSupportForm" property="newcasefileId" styleId='newSupervisionNum' style="width:105px">
						<option value="" selected="selected"><bean:message key="select.generic"/></option>
						<html:optionsCollection name="ProdSupportForm" property="bookingSprvisionNumbers"  value="description" label="description"/>
					</html:select> 
				</td>
				</td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="juvenileId" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">JPOUSERID</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="jpoUserId" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">DATEENTERED</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="dateEntered" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
					<td align="center"><font color="red">New value:&nbsp;</font>
						<html:text name="ProdSupportForm" 
									property="newActDate" 
									size="10" 
									maxlength="10" 
									styleId="newActDate"/>
					</td>

				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="createUserID" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEUSER</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="updateUser" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEDATE</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="createJIMS2UserID" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="updateJIMS2UserID" />&nbsp;</font></td>
				</tr>
			</table>
		</logic:iterate>

	</logic:notEmpty> <logic:empty name="ProdSupportForm" property="riskanalyses">
		<table border="1" width="700" align="center">
			<tr>
				<td>
				<h3 align="center"><i>No associated Risk Analysis Records
				Found</i></h3>
				</td>
			</tr>
		</table>
	</logic:empty>

	<table align="center"">
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<logic:notEmpty name="ProdSupportForm" property="riskanalyses">
			
					<td>
					<input type="submit" 
							id="updateRecord"
							value="Update Record"  />
					</td>
			</logic:notEmpty>
		</tr>
		</table>
		<html:hidden styleId="newSprvNum" name="ProdSupportForm" property="newcasefileId"/>
		</html:form>
		<table align="center">
		<tr>
		<td>&nbsp;</td>
		</tr>
		<tr>
		<td><p align="center"><html:form action="/AssessUpdateQuery?clr=Y"
				 onsubmit="return this;">
				<html:submit value="Back to Query" />
			</html:form></p></td>
		</tr>
	</table>
	</div>
</html:html>