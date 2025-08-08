<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/moveMaysiDetailSummary.jsp</title>
</head>

<body>
	
<h2 align="center">Move MAYSI Detail Summary</h2>
<hr>

<p align="center"><font color="green"><b>MAYSI Detail was successfully updated.</b></font></p>

<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this modification was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
<hr>

<html:form action="/PerformMoveMaysiDetail" onsubmit="return this;">
<br/>

<logic:notEmpty name="ProdSupportForm" property="maysidetails">

    <table border="1" width="750" align="center">

	<logic:iterate id="maysidetails" name="ProdSupportForm" property="maysidetails">

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">MAYSIDETAIL_ID</font></td>
		<td><font size="-1"><bean:write name="maysidetails" property="maysiDetailId"/>&nbsp;</font></td>
	</tr>
	<tr>
	<logic:notEmpty name="ProdSupportForm" property="toJuvenileId">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="toJuvenileId" />&nbsp;</font></td>
		<td>
		<font color="red"><i>Updated, previous value: </i><bean:write name="maysidetails" property="juvenileNum" /></font>
		</td>
		</logic:notEmpty>
		<logic:empty name="ProdSupportForm" property="toJuvenileId">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td><font size="-1"><bean:write  name="maysidetails" property="juvenileNum" />&nbsp;</font></td>
	</logic:empty>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SCREENINGDATE</font></td>
		<td><font size="-1"><bean:write name="maysidetails" property="screenDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>				
		<td><font size="-1"><bean:write name="maysidetails" property="referralNumber" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILEAGE</font></td>				
		<td><font size="-1"><bean:write name="maysidetails" property="testAge" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">GENDER</font></td>						
		<td><font size="-1"><bean:write name="maysidetails" property="sex" />&nbsp;</font></td>	
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ETHNICBACKGROUND</font></td>				
		<td><font size="-1"><bean:write name="maysidetails" property="ethnicity" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LENGTHOFSTAY</font></td>		
		<td><font size="-1"><bean:write name="maysidetails" property="lengthOfStay" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PRIORSTAY</font></td>				
		<td><font size="-1"><bean:write name="maysidetails" property="priorPreviousMaysi" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FACILITYTYPE</font></td>
		<td><font size="-1"><bean:write name="maysidetails" property="facilityType" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FACILITYNAME</font></td>		
		<td><font size="-1"><bean:write name="maysidetails" property="facilityName" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LOCATION_ID</font></td>				
		<td><font size="-1"><bean:write name="maysidetails" property="locationUnitId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">REASONNOTDONE</font></td>
		<td><font size="-1"><bean:write name="maysidetails" property="reasonNotDone" />&nbsp;</font></td>			
	</tr>				

	</logic:iterate>
  </table>
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