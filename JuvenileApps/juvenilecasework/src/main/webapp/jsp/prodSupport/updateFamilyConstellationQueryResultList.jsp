<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>
<%-- 03/23/2016	RCarter	   New initial query jsp for facilities --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
 
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateFamilyConstellationQueryResultList.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="javascript">
	
	function fillId(value)
	{
		document.forms[0].familyConstellationId.value = value;
		return true;
	}
</script>

</head>

<body>

<h2 align="center">Production Support - Family Constellation List&nbsp;
	<!-- 
		<logic:notEmpty name="ProdSupportForm" property="searchedJuvenileId">
			-&nbsp; Juvenile ID: &nbsp;<bean:write name="ProdSupportForm" property="searchedJuvenileId" />
		</logic:notEmpty>
	 -->
</h2>
<hr>

<p align="center" style="font-size: 14px">Select an item from the list by clicking a radio button and then click SUBMIT to view the detail.</p>

<div align="center">
<html:form method="post" action="/UpdateFamilyConstellationQuery?list=N" onsubmit="return this;">

<br />

<logic:notEmpty name="ProdSupportForm" property="familyConstellationList">

    <table border="1" width="750" align="center">

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CONSTELLATION ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CONSTELLATION DATE</font></td>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">STATUS</font></td>				
	</tr>
	
	<logic:iterate id="familyConstellation" name="ProdSupportForm" property="familyConstellationList">

	<tr>
		<td>
	 		<input type="radio" name="radioProp" value="<bean:write name="familyConstellation" property="familyConstellationId"/>"
	 		onclick="fillId(<bean:write name="familyConstellation" property="familyConstellationId"/>)">
	 		<bean:write name="familyConstellation" property="familyConstellationId"/>
		</td>			
		<td><font size="-1"><bean:write name="familyConstellation" property="juvenileId" />&nbsp;</font></td>	
		<td><font size="-1"><bean:write name="familyConstellation" property="entryDate" format="MM/dd/yyyy" />&nbsp;</font></td>			
		<td><font size="-1"><bean:write name="familyConstellation" property="active" />&nbsp;</font></td>					
	</tr>				

	</logic:iterate>
  </table>
  <table border="0" width="700">
	<tr>
		<td align="right">
		<font face="" style="FONT-FAMILY: Arial" color="#0000aa"><strong> Selected Constellation ID:</strong></font>
		</td>
		<td><html:text styleId="familyConstellationId" name="familyConstellation" property="familyConstellationId" size="10" maxlength="15" readonly="true"/>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><html:submit onclick="spinner()" value="Submit" /></td>
	</tr>

	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
</logic:notEmpty>

	
<logic:empty name="ProdSupportForm" property="familyConstellationList">
	<table align="center" border="0" width="700">
		<tr>
			<td align="center"><h4><i>No Family Constellation record(s) found.</i></h4></td>
		</tr>
	</table>
</logic:empty>	

	&nbsp;
</html:form>
	
	<table border="0">
		<tr>
		<td align="center">
		<html:form method="post" action="/UpdateFamilyConstellationQuery?clr=Y" onsubmit="return this;">
		<html:submit onclick="return this;" value="Search a Different Record"/>
		</html:form>
		</td>
	
		<td>&nbsp;</td>
	
		<td align="center">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>

    </table>
    
    <table border="0" width="700">
	
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font></td>
	</tr>
	</table>
    
	</div>

</body>
</html:html>