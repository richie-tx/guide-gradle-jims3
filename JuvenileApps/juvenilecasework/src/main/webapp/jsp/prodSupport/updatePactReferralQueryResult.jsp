<!DOCTYPE HTML>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updatePactReferralQueryResult</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updatePactReferralQuery.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<!-- <script language="javascript">

function confirmUpdate(){
	var moreThanOneCurrent = "<bean:write  name="ProdSupportForm" property="moreThanOneCurrent" />";	
	var currentCtr		   = "<bean:write  name="ProdSupportForm" property="currentPactReferrals" />";
	var pactStatus		   = "<bean:write  name="ProdSupportForm" property="pactStatus" />";
	var newStatus		   = document.getElementById("status_id").value;
   // var curr_stat = document.getElementById("tempstatusFlag").value;
    //alert(currentCtr + "more than 1 " + moreThanOneCurrent + "NEW STATUS " + newStatus);
    
    //if (!stat.localeCompare(curr_stat)) {
    //	alert("More than one assessment has a Status equal to CURRENT.Please verify entry and modify if required");
   //}
    if ( "false" === moreThanOneCurrent && currentCtr == 1 && "CURRENT" === newStatus ) {
    	alert("Please review the current supervision’s PACT details. Another referral has an identified status equal to CURRENT.");
    }
        
    if ( "true" === moreThanOneCurrent ) {
    	alert("Please review the current supervision’s PACT details. Another referral has an identified status equal to CURRENT.");
    }
    var refid = document.getElementById("tempReferralID").value;
	if(refid == null || refid.length < 4 || !$.isNumeric($("#tempReferralID").val())){
		alert("Invalid Referral Number. Please modify");
		return false;
	}
	var caseid = document.getElementById("case_Id").value;	
	if(caseid == null || caseid.length < 8 || !$.isNumeric($("#case_Id").val())){
		alert("Invalid Casefile ID. Please modify");
		return false;
	}
	if (document.forms[0].newPactDate==null || document.forms[0].newPactDate.value==""){
		alert("PACT date is required");
		return false;
	}
	if (document.forms[0].riskLevel.selectedIndex == 0){
		alert("Risk Level is required");
		return false;
	}
	
	if (document.forms[0].needLevel.selectedIndex == 0){
		alert("Need Level is required");
		return false;
	}
	if (document.forms[0].newPactStatus.selectedIndex == 0){
		alert("Status is required");
		return false;
	}
	
	else 
	{
		if(confirm('Are you sure you want to update the PACT Referral?'))
			
			return true;	
		else
			return false;
			}

}
</script> -->
</head>
<body>
<html:form method="post" action="/PerformUpdatePactReferral" onsubmit="return this;">
<div>
	
	<h2 align="center">Production Support – Update PACT Referral Details</h2>
<!-- Error Message Area -->
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="700" align="center">

	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<bean:write name="ProdSupportForm" property="msg" />
	 		</font></td>
	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->		

<!-- <p align="center"><b><i>Choose a new Pact details  <br>
</i></b></p> -->
	<table class="resultTbl" border="1" width="800" align="center">
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Name</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="juvenileName" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="juvenileId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral No.</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.referralNumber" />&nbsp;</font></td>
		<td>
	  	<font color="red">New value:&nbsp;</font>
	  	<html:text styleId="tempReferralID" name="ProdSupportForm" property="newReferralID" size="10" maxlength="4"/> 
       </td>
	</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKNDSLVLID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.riskNeedLvlId" />&nbsp;</font></td>
		<!--  <td>
	  	<font color="red">New value:&nbsp;</font>
	  	<html:text styleId="tempRiskNeedLvlId" name="ProdSupportForm" property="newRiskNeedLvlId" size="10" maxlength="10"/> 
       </td> //Bug 106409--> 
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Casefile ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.caseFileId" />&nbsp;</font></td>
		<html:hidden styleId='prevCasefileId'  name='ProdSupportForm' property='pactRecord.caseFileId'/>
		<td>
	  	<font color="red">New value:&nbsp;</font>
	  	<html:text styleId="case_Id" name="ProdSupportForm" property="newCaseID" size="10" maxlength="10"/> 
       </td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Code</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="offCode" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PACT Date</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactDate" />&nbsp;</font></td>
		<td>
	  <font color="red">New value:&nbsp;</font>
	  <html:text name="ProdSupportForm" property="newPactDate" size="10" maxlength="10" styleId="pacdate"/> 
    </td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PACT ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactId" />&nbsp;</font></td>
		<td>
	  <font color="red">New value:&nbsp;</font>
	  <html:text name="ProdSupportForm" property="newPactId" size="10" maxlength="10" styleId="pactOID"/> 
    </td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Risk Level</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="riskValue" />&nbsp;</font></td>
		<td><i>Select New Value:</i>&nbsp;
				<select name="riskLevel" size="1">
				<jims2:if name="ProdSupportForm" property="riskValue" value="A" op="equal">
					<jims2:then>
      					<option value="A" selected>A</option>
      				</jims2:then>
      				<jims2:else>
      				<option value="A">NOT ADMINISTERED</option>
					<jims2:if name="ProdSupportForm" property="riskValue" value="L" op="equal">
					<jims2:then>
      					<option value="L" selected>LOW</option>
      				</jims2:then>
      				<jims2:else>
      					<option value="L" >LOW</option>
      				</jims2:else>
    				</jims2:if>
    				
    				<jims2:if name="ProdSupportForm" property="riskValue" value="M" op="equal">
					<jims2:then>
      					<option value="M" selected>MODERATE</option>
      				</jims2:then>
      				<jims2:else>
      					<option value="M">MODERATE</option>
      				</jims2:else>
    				</jims2:if>
    				<jims2:if name="ProdSupportForm" property="riskValue" value="H" op="equal">
					<jims2:then>
      					<option value="H" selected>HIGH</option>
      				</jims2:then>
      				<jims2:else>
      					<option value="H">HIGH</option>
      				</jims2:else>
    				</jims2:if>
					</jims2:else>
					</jims2:if>
      			</select>      				
    	</td>	
    	
	</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Needs Level</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="needValue" />&nbsp;</font></td>
		<td><i>Select New Value:</i>&nbsp;
		<select name="needLevel" size="1">
				<jims2:if name="ProdSupportForm" property="needValue" value="A" op="equal">
					<jims2:then>
      					<option value="A" selected>A</option>
      				</jims2:then>
      				<jims2:else>
      				<option value="A">NOT ADMINISTERED</option>	
					<jims2:if name="ProdSupportForm" property="needValue" value="L" op="equal">
					<jims2:then>
      					<option value="L" selected>LOW</option>
      				</jims2:then>
      				<jims2:else>
      					<option value="L" >LOW</option>
      				</jims2:else>
    				</jims2:if>
    				
    				<jims2:if name="ProdSupportForm" property="needValue" value="M" op="equal">
					<jims2:then>
      					<option value="M" selected>MODERATE</option>
      				</jims2:then>
      				<jims2:else>
      					<option value="M">MODERATE</option>
      				</jims2:else>
    				</jims2:if>
    				<jims2:if name="ProdSupportForm" property="needValue" value="H" op="equal">
					<jims2:then>
      					<option value="H" selected>HIGH</option>
      				</jims2:then>
      				<jims2:else>
      					<option value="H">HIGH</option>
      				</jims2:else>
    				</jims2:if>
    			  </jims2:else>
    			  </jims2:if>
    	</select></td>	
	</tr>

	<tr>
	<html:hidden styleId="tempStatusFlag" name="ProdSupportForm" property="statusFlag"/>
	<html:hidden styleId="boolCurrent" name="ProdSupportForm" property="moreThanOneCurrent"/>
	<html:hidden styleId="currentCtr" name="ProdSupportForm" property="currentPactReferrals"/>	
	<html:hidden styleId="pactStatusId" name="ProdSupportForm" property="pactStatus"/>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Status</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactStatus" />&nbsp;</font></td>
	  <td><i>Select New Value:</i>&nbsp;
    	<select name="newPactStatus" size="1" id="status_id">
    		<jims2:if name="ProdSupportForm" property="pactStatus" value="SYS GENERATE" op="equal">
					<jims2:then>
      					<option value="SYS GENERATE" selected>SYS GENERATE</option>
      				</jims2:then>
      				<jims2:else>
					<option value=""></option>
					<jims2:if name="ProdSupportForm" property="pactStatus" value="CURRENT" op="equal">
					<jims2:then>
      					<option value="CURRENT" selected>CURRENT</option>
      				</jims2:then>
      				<jims2:else>
      					<option value="CURRENT" >CURRENT</option>
      				</jims2:else>
    				</jims2:if>
    				
    				<jims2:if name="ProdSupportForm" property="pactStatus" value="NOT CURRENT" op="equal">
					<jims2:then>
      					<option value="NOT CURRENT" selected>NOT CURRENT</option>
      				</jims2:then>
      				<jims2:else>
      					<option value="NOT CURRENT">NOT CURRENT</option>
      				</jims2:else>
    				</jims2:if>
    				<jims2:if name="ProdSupportForm" property="pactStatus" value="SYS GENERATE" op="equal">
					<jims2:then>
      					<option value="SYS GENERATE" selected>SYS GENERATE</option>
      				</jims2:then>
    				</jims2:if>
    				</jims2:else>
    				</jims2:if>
    	</select></td>  	
    	
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Create User</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.createUserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Create Date</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.createTimestamp" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Update User</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.updateUserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Date/Time</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.updateTimestamp" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Create JIMS2User</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Update JIMS2User</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	
	</table>
	
	<table align="center"">
	
	<td>
	<p align="center">
	<html:submit value="Update Records" styleId="updateRecBtn"/>
	</p>
	</td>
	<td>
	<html:reset value="Reset Form Values" />
	</td>
	
	</table>
	
	</div>

<input type="hidden" id="pactReferralsCurr" name="ProdSupportForm" value='<bean:write name="ProdSupportForm" property="pactReferralsJson"/>'/>
</html:form>

<html:form action="/UpdatePactReferralQuery?clr=Y" onsubmit="return this;">
<table align="center">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>


</body>
</html:html>