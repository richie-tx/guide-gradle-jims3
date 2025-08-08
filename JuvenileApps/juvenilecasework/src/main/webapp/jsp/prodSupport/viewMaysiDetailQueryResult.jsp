<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/viewMaysiDetailQueryResult.jsp</title>
</head>

<html:form action="/ViewMaysiDetailQuery" onsubmit="return this;">

<h2 align="center">MAYSI Detail Results</h2>
<br>
<hr>

<logic:notEmpty	name="ProdSupportForm" property="maysidetails">
<p>&nbsp;</p>
<table border="1" width="700">	
	<tr>    	  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">MAYSIDETAIL_ID</font></td>  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">SCREENINGDATE</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVENILEAGE</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">GENDER</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">ETHNICBACKGROUND</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">LENGTHOFSTAY</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">PRIORSTAY</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">FACILITYTYPE</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">FACILITYNAME</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">LOCATION_ID</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">ANGRYIRRITABLE</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">THOUGHTDISTURB</font></td>  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">SOMATICCOMPLAINTS</font></td>  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">ALCOHOLDRUGUSE</font></td>  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">SUICIDEIDEATION</font></td>  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">TRAUMATICEXP</font></td>  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">DEPRESSEDANXIOUS</font></td>  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">ISADMINISTER</font></td>    
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">REASONNOTDONE</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">ISSUBASMNTREF</font></td> 
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSESSMENTFOUND</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
  		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
  		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEUSER</font></td>
  		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEDATE</font></td>
  		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
  		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>	  	   
    </tr>
	<logic:iterate id="maysidetails" name="ProdSupportForm" property="maysidetails">
 	<tr>		
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="maysiDetailId" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="juvenileNum" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="screenDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="referralNumber" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="testAge" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="sex" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="ethnicity" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="lengthOfStay" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="priorPreviousMaysi" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="facilityType" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="facilityName" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="locationUnitId" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="angryIrritable" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="thoughtDisturbance" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="somaticComplaint" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="alcoholDrug" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="suicideIdetaion" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="traumaticExpression" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="depressionAnxiety" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="administered" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="reasonNotDone" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="subAssessComplete" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="assessmentFound" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="createUser" />&nbsp;</font>
	 	</td>	
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
	 	</td>	
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="updateUser" />&nbsp;</font>
	 	</td>	
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
	 	</td>		
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="createJims2User" />&nbsp;</font>
	 	</td>	
	 	<td><font size="-1">
	 		<bean:write name="maysidetails" property="updateJims2User" />&nbsp;</font>
	 	</td>
	 </tr>	
  	</logic:iterate>
</table>
</logic:notEmpty>
<br />
	
<logic:empty name="ProdSupportForm" property="maysidetails">
	<table align="center" border="0" width="700">
		<tr>
			<td align="center"><h4><i>No Maysi Details found.</i></h4></td>
		</tr>
	</table>
</logic:empty>
	     
<table border="0" width="700">			
	<tr><td colspan="4">&nbsp;</td></tr>
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
<table border="0" width="700">
	<tr>
	<td>
	<html:form action="/ViewMaysiDetailQuery?clr=Y">
		<p align="center">
			<input type="submit" name="details" value="Back to Query"/>
		</p>
	</html:form>	
	</td>
	</tr>
</table>


</html:html>