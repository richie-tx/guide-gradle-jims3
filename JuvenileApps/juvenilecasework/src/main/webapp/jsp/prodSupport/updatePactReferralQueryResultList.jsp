<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework -/prodSupport/UpdatePactReferralQueryResultList</title>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<script>
$(document).ready(function () {

	$("#selectNxtBtn").on("click",function(){
		var riskneedID = $('input:radio[name=riskneedVal]:checked').val();
 
		if( riskneedID !="" ){     	
			
			$("#tempPactID").val( riskneedID ); //set it in the form
    		
		 }
		
		spinner();
	});	

});

</script>
</head>

<body>

<html:form action="/UpdatePactReferralQuery?clr=U" onsubmit="return this;">

<div>
	
	<h2 align="center">Results for Juvenile ID = 
			<bean:write name="ProdSupportForm" property="juvenileId" /></h2>
	     
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
<table class="resultTbl" border="1" width="700" align="center">
		
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileId"/>&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile DOB</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="birthDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Master Status</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="statusId" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Record Type</font></td>
		<td><font size="-1">
			<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="JUVENILE">
				<elogic:then>
					<font size="-1">ACTIVE</font>
				</elogic:then>	
			</elogic:if>		
				<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="I.JUVENILE">
						<elogic:then>
						<font size="-1">PURGED </font>
				</elogic:then>
			</elogic:if>
				<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="S.JUVENILE">
						<elogic:then>
						<font size="-1">SEALED </font>
				</elogic:then>
			</elogic:if>
			</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SSN</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileSsn" />&nbsp;</font></td>
		</tr>		
	</table>  
   
<p align="center"><b><i>Select the radio button next to the record <br>
you want to update and click the Next button.</i></b></p>	     
	<logic:notEmpty	name="ProdSupportForm" property="pactReferrals">
	<h3 align="center">Referral Details</h3>	
	<table class="resultTbl" border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral No. </font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Casefile ID </font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Code</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PACT Date </font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Risk Level</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Needs Level</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Status </font></td>
	</tr>
	
	<logic:iterate id="pactReferral" name="ProdSupportForm" property="pactReferrals">
		<tr>
		<td width="1%"><input type="radio" name="riskneedVal" value="<bean:write name='pactReferral' property='riskNeedLvlId'/>" /></td>
		
		<td><font size="-1"><bean:write  name="pactReferral" property="referralNumber" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="pactReferral" property="caseFileId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="pactReferral" property="offenseCode" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="pactReferral" property="lastPactDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="pactReferral" property="riskLvl" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="pactReferral" property="needsLvl" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="pactReferral" property="status" />&nbsp;</font></td>
		
	 	</tr>
		
	
	</logic:iterate>
		
	</table>

	</logic:notEmpty>
	<BR>
	
	<table align="center"">
		<logic:notEmpty	name="ProdSupportForm" property="pactReferrals">
		<td>
		<p align="center">
		<html:submit property="submitAction" styleId="selectNxtBtn"><bean:message key="button.next"/></html:submit>
			</p>
		</td>
		</logic:notEmpty>
	</table>
	
	
	<logic:empty name="ProdSupportForm" property="pactReferrals">
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
	
<html:hidden styleId="tempPactID" name="ProdSupportForm" property="riskneedID"/>

</html:form>

<table align="center">
	<tr>
		<td>
		<html:form action="/UpdatePactReferralQuery?clr=Y" onsubmit="return this;">
			<html:submit value="Back to Query"/>
		</html:form>
		</td>
		<td>
		<html:form method="post" action="/MainMenu">
			<html:submit onclick="return this;" value="Cancel"/>
		</html:form>	
		</td>			
	</tr>
</table>


</body>
</html:html>