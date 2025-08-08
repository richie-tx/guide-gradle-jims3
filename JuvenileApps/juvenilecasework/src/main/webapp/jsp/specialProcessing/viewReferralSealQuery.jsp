<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.PDJuvenileCaseConstants" %>

 
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/viewReferralSealQuery.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript">
function closeWindow(el) {
	window.close();

	return 0;
}
function Refresh() {
	$("#juvenileNum").val("");
	return true;
}
function validate() 
{
		localStorage.setItem("SearchWin", "open");
		var juvNum = $("#juvenileNum").val();
		if (juvNum != "") {
			if (juvNum.trim().length < 6) {
				alert("Juvenile number is invalid. Please retry search");
				$("#juvenileNum").focus();
				return false;
			}

			// juvenile Number
			if (Math.floor(juvNum) != juvNum || $.isNumeric(juvNum) == false) {
				alert("Juvenile number is invalid. Please retry search");
				$("#juvenileNum").focus();
				return false;
			} else {
				if (juvNum.match("^0")) {
					alert("Juvenile number preceded by 0. Please retry search without 0");
					$("#juvenileNum").focus();
					return false;
				}

			}
			
			if ( true ) {
				spinner();
			}

		} 
		else 
		{
			alert("Juvenile number is required");
			$("#juvenileNum").focus();
			return false;
		}	
		
	}
	</script>
</head>
<body>
<h2 align="center">Seal Juvenile Referral Record</h2>
<hr>
<p align="center">Please enter a JUVENILE_ID to find out what data exists.</p>

	<div align="center">
	<html:form method="post" action="/displayJuvenileReferralSealingSearchResults" onsubmit="return this;">
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
			<td align="right"><font color="#0000aa"><b>Juvenile ID:</b></font>
			</td>
			<td>				
				<html:text name="ProdSupportForm" property="juvenileId" size="8" maxlength="8" styleId="juvenileNum" />
			</td>
		</tr>
		
		<tr>
		<td>&nbsp;</td>
		<%-- <td>
			<html:submit value="Submit" />
		</td></tr> --%>
		<table align="center" border="0" width="100%">
		<tr>
			<td align="center"><html:submit property="submitAction"
					styleId="submitBtn" onclick="return validate()">
					<bean:message key="button.submit" />
				</html:submit> &nbsp; <input type="button" id="BtnRefresh" onclick="return Refresh()"
				value="<bean:message key='button.refresh'/>" /> &nbsp; <html:button
					property="org.apache.struts.taglib.html.CANCEL"
					onclick="return closeWindow(this.form)">
					<bean:message key="button.cancel"></bean:message>
				</html:button></td>
		</tr>
	</table>
		<tr>
		<td>&nbsp;</td>
		</tr>
		</table>
	</html:form>
	
	
    
	</div>

</body>
</html:html>