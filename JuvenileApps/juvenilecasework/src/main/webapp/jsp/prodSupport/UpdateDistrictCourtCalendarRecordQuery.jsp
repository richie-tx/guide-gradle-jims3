<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/UpdateDistrictCourtCalendarRecordQuery</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/UpdateDistrictCourtCalendar.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript'>

function closeWindow(el)
{
	window.close();

	return 0;
}
</script>
</head>
<body>
<h2 align="center">Production Support - Search District Court Calendar Data </h2>
<hr>

<p align="center">Please enter a Juvenile Number to view current District Court Calendar Data.</p>

	<div align="center">
	<html:form method="post" action="/UpdateDistrictCourtCalendarRecordQuery" onsubmit="return this;">
	<table width="100%">
			<tr>
				<td align="center" class="errorAlert"><font color="red"><html:errors></html:errors></font>				
				</td>
			</tr>
			<tr>
		<td>&nbsp;</td>
		<td>
		</table>
	<table border="0" width="700">
		<tr>
			<%-- <td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><span id="reqJuvNum"><bean:message key="prompt.2.diamond"/></span><bean:message key="prompt.juvNo"/></td> --%>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong> Juvenile Number:</strong></font></td>
			<td>
				<html:text property="juvenileId" styleId='juvNum' size="7" maxlength="7" />
			</td>
		</tr>
		<%-- <tr>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong> Court ID:</strong></font></td>
			<td>
				<html:text property="courtId" styleId='courtNum' size="3" maxlength="3" />				
			</td>
		</tr> --%>
		<tr>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong> Referral #</strong></font></td>
			<td>
				<html:text property="referralNum" styleId='reffNum' size="4" maxlength="4" />				
			</td>
		</tr>
		<tr>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong> Court Date:</strong></font>
			</td>
			 <td>				
				<html:text property="courtDate" styleId="courtDate"  maxlength="10" size="10"/>
			</td> 
		</tr>
		<tr>
			<td>&nbsp;</td>
			<html:hidden styleId="hdnForward" name="ProdSupportForm" property="hiddenForward"/>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
			<html:submit property="submitAction"
							styleId="submitBtn">
							<bean:message key="button.submit" />
						</html:submit>
				 
				<%-- <input type="button"  id="submitBtn" value="<bean:message key='button.submit'/>" />	 --%>				
				&nbsp;
				<input type="button" onclick="return closeWindow(this.form)" id="BtnCancel" value="<bean:message key='button.cancel'/>"/>		
				&nbsp;
				<input type="button"  id="BtnRefresh" value="<bean:message key='button.refresh'/>"/>	
				<%-- onclick="goNav('/<msp:webapp/>UpdateDistrictCourtCalendarRecordQuery.do?submitAction=refresh')" --%>				
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>				
	</table>	
	
    <tr>
    	<td>&nbsp;</td>
	</tr>

</html:form>
 <table border="0" width="700"> 
		<tr>
		<td colspan="2" align="center">		
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>
    </table> 
	
    
    <table border="0" width="700">
	
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	
	<%-- <tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font></td>
	</tr> --%>
	<%-- <tr align="center">
		<td>		
		<html:button property="submitAction" styleId="backToQryBtn">
  				<bean:message key="button.backToSearch"></bean:message>
  			</html:button>
		</td>
	</tr> --%>
	</table>
    
	</div>
	
	<html:hidden styleId="juvenileNum" name="ProdSupportForm" property="juvenileId"/>	
	<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>