<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/specialProcessing/viewJuvenileSealQuerySummary.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/specialProcessing/juvenileSealing.js"></script>

</head>

<html:form action="/ViewJuvenileSealQuery" onsubmit="return this;">

<h2 align="center">Juvenile Master Seal Results</h2>
<br>
<hr>
<table width="900" align="center">
<tr>
		<td align="center"><h4><font color="green"><i>Juvenile Master Record is Sealed successfully.</i></font></h4></td>
</tr>
</table>
<logic:notEmpty	name="ProdSupportForm" property="juveniles">

	<logic:iterate id="juveniles" name="ProdSupportForm" property="juveniles">
 	<table border="1" width="900" align="center">
	
			
	<elogic:if name="juveniles" property="recType" op="notequal" value="S.JUVENILE">
		<elogic:then>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE NUMBER</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="juvenileNum" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVENILE NAME</font></td>
			<td><font size="-1">
				<bean:write name="juveniles" property="firstName" />&nbsp;
				<bean:write name="juveniles" property="middleName" />&nbsp;
				<bean:write name="juveniles" property="lastName" />&nbsp;
				<bean:write name="juveniles" property="nameSuffix" />
				</font>
				
			</td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">RECORD TYPE</font></td>
			<td><font size="-1">
			<elogic:if name="juveniles" property="recType" op="equal" value="JUVENILE">
				<elogic:then>
					<font size="-1"> ACTIVE</font>
				</elogic:then>	
			</elogic:if>		
				<elogic:if name="juveniles" property="recType" op="equal" value="I.JUVENILE">
						<elogic:then>
						<font size="-1"> PURGED </font>
				</elogic:then>
			</elogic:if>
			</font></td>
		</tr>
		
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">DOB</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="dateOfBirth" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		</tr>
		
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SEX</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="sex" />&nbsp;</font></td>
		</tr>
		
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">RACE</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="race" />&nbsp;</font></td>
		</tr>
		
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">HISPANIC</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="hispanic" />&nbsp;</font></td>
		</tr>
		
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SSN</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="completeSSN" />&nbsp;</font></td>
		</tr>
		
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">MASTER STATUS</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="statusId" />&nbsp;</font></td>
		</tr>
		<br /><br />
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">REASON FOR SEAL:</font></td>
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="sealComments" />&nbsp;</font></td>
  		</tr>   
  
	  <tr>
		  <td bgcolor="gray"><font color="white" face="bold" size="-1">SEALED DATE:</font></td>
		  <td><font size="-1"><bean:write  name="ProdSupportForm" property="sealedDate" />&nbsp;</font></td>	  
	  </tr>  
		</elogic:then>
		</elogic:if>
	</table>
	
  	</logic:iterate>
</logic:notEmpty>
<br />
	
<logic:empty name="ProdSupportForm" property="juveniles">
	<table align="center" border="0" width="700">
		<tr>
			<td align="center"><h4><i>No Juvenile Details found.</i></h4></td>
		</tr>
	</table>
</logic:empty>
	     
<table border="0" width="700">			
	<tr><td colspan="4">&nbsp;</td></tr>	
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font>
			</td>
	</tr>
</table>

</html:form>
	<br />
	<table align="center" border="0" width="50%">
	<tr colspan="2">
		<td align="center">
		<html:form action="/ViewJuvenileSealQuery?clr=Y">
			<input type="submit" name="Return" value="Return To Juvenile Master Seal"/>
		</html:form>	
		</td>
	</tr>
</table>


</html:html>