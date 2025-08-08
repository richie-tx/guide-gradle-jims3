<!DOCTYPE HTML>

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
<title>Juvenile Casework -/prodSupport/deleteMsReferralQueryListResult.jsp"</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/deleteReferral.js"></script>

</head>

<body>

<html:form action="/DeleteMsReferralQuery" onsubmit="return this;">

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

	<table border="1" width="750" align="center">
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Name</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileName" />&nbsp; (<bean:write name="ProdSupportForm"
				property="rectype" />&nbsp;)</font></td>
		</tr>
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
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SSN</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileSsn" />&nbsp;</font></td>
		</tr>	
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Master Status</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="statusId" />&nbsp;</font></td>
		</tr>
		
	</table>     
<p align="center"><b><i>Select the radio button next to the record <br>
you want to delete and click the Submit button.</i></b></p>	     
	<logic:notEmpty	name="ProdSupportForm" property="juvprogrefs">
	<h3 align="center">Referral Details</h3>	
	<table border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Number</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Date</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Offense</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Status</font></td>
	</tr>
	
	<logic:iterate id="juvprogrefs" name="ProdSupportForm" property="juvprogrefs">
	
	<tr>
		<bean:define id="referralNum" name='juvprogrefs' property='referralNum' type="java.lang.String"></bean:define>
		<bean:define id="oid" name='juvprogrefs' property='referralOID' type="java.lang.String"></bean:define>
		<td width="1%"><input type="radio" name="referralOID" value="<bean:write name='juvprogrefs' property='referralOID'/>" /></td>
		<td width="20%"><html:text name="juvprogrefs" property="referralNum" styleId='<%="refNum-" + referralNum%>' size="4" style="border: 0;background-color: white" indexed="true" disabled="true"/></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="referralDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="offenseCode" />&nbsp;</font></td>
		<elogic:if name="juvprogrefs" property="closeDate" op="notequal" value="">
			<elogic:then>
				<td><font size="-1">CLOSED</font></td>
			</elogic:then>			
				<elogic:else>
					<td><font size="-1">ACTIVE</font></td>
				</elogic:else>
		</elogic:if>
		
		

	 </tr>
	</logic:iterate>
		
	</table>

	</logic:notEmpty>
	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="juvprogrefs">
	<td>
	<p align="center">
	<html:submit property="submitAction" styleId="selectRefBtn"><bean:message key="button.submit"/></html:submit>
	</p>
	</td>
	</logic:notEmpty>

	</table>
	
	
	<logic:empty name="ProdSupportForm" property="juvprogrefs">
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
	

<table align="center"">
<tr>

<td>&nbsp;</td>

</tr>
</table>
<html:hidden styleId="tempRefOID" name="ProdSupportForm" property="referralOID"/>
<html:hidden styleId="tempJuvNum" name="ProdSupportForm" property="juvenileId"/>
</html:form>

<html:form action="/DeleteMsReferralQuery?clr=Y" onsubmit="return this;">
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