<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/viewReferralSealQueryResult.jsp</title>
</head>

<html:form action="/ViewReferralSealQuery" onsubmit="return this;">

<h2 align="center">REFERRAL Detail Results</h2>
<br>
<hr>

<logic:notEmpty	name="ProdSupportForm" property="eventreferrals">
<p>&nbsp;</p>
<table border="1" width="700">	
	<tr>  
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">REFERRALNUM</font></td>  	  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSIGNEDJPO</font></td>  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">REFERRALDATE</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">DALOGNUM</font></td>        
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">REFERRALTYPE</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">REFERRALSOURCE</font></td>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">REFERRALFOUND</font></td>
    </tr>
	<logic:iterate id="eventreferral" name="ProdSupportForm" property="eventreferrals">
 	<tr>		
	 	<td><font size="-1">
	 		<bean:write name="eventreferral" property="referralNumber" />&nbsp;</font>	 		
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="eventreferral" property="ctAssignJpoId" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="eventreferral" property="referralDate" format="MM/dd/yyyy"/>&nbsp;</font>	 		
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="eventreferral" property="jpoId" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="eventreferral" property="referralTypeInd" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="eventreferral" property="referralSource" />&nbsp;</font>
	 	</td>
	 	<td><font size="-1">
	 		<bean:write name="eventreferral" property="referralFound" />&nbsp;</font>
	 	</td>	 	
	 </tr>	
  	</logic:iterate>
</table>
</logic:notEmpty>
<br />
	
<logic:empty name="ProdSupportForm" property="eventreferrals">
	<table align="center" border="0" width="700">
		<tr>
			<td align="center"><h4><i>No Juvenile Details found.</i></h4></td>
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
<table border="0" width="90%">

	<td>
	<html:form action="/PerformReferralSealing?clr=Y">
		<p align="center">
			<input type="submit" name="submit" value="Seal"/>
		</p>
	</html:form>	
	</td>
	<td>
	<html:form action="/ViewReferralSealQuery?clr=Y">
		<p align="center">
			<input type="submit" name="details" value="Back to Query"/>
		</p>
	</html:form>	
	</td>
	</tr>
</table>


</html:html>