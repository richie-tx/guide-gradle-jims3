<!DOCTYPE HTML>

<%-- Used for searching of juvenile profile in MJCW --%>
<%--MODIFICATIONS --%>
<%-- DWilliamson  06/03/2005	Create JSP --%>

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

<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> -
	juvenileReferralSealingSearch.jsp</title>


<%-- Javascript for emulated navigation --%>
<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<script type="text/javascript">
	/***
	 * Combination existing javascript validation (SearchValidator) and Struts javascript generated
	 * validation (validateJuvenileProfileSearchForm) - only on dateOfBirth
	 */
	
	function closeWindow(el) {
		window.close();

		return 0;
	}
	function Refresh() {
		$("#juvenileNum").val("");
		return true;
	}
	function validate() {
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

		} else {
			alert("Juvenile number is required");
			$("#juvenileNum").focus();
			return false;
		}
		//document.theForm.submit();
		/* var webApp = '/<msp:webapp/>';
		url = webApp+ "displayJuvenileReferralSealingSearchResults.do?submitAction=Submit&juvNum="+juvNum;
		var WinStatus = localStorage.getItem("SearchWin");		
		if (WinStatus == "open") 
		{			
			var Window = window
					.open(
							url,
							"pictureWindow",
							"height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");			
			Window.focus();
			//openWindow(webApp + 'displayJuvenileReferralSealingSearchResults.do?submitAction=Submit&juvNum='+juvNum);
			//localStorage.setItem("SearchWin", "close");
			return false; 
		}  */
		
	}
	/* function Submit()
	 {
	 localStorage.setItem("SearchWin", "open");
	 alert('set localstorage');
	 } */
</script>

</head>


<%--BEGIN FORM TAG--%>
<html:form method="post" action="/displayJuvenileReferralSealingSearchResults"
	target="content" onsubmit="return this;">




	<%-- BEGIN HEADING TABLE --%>
	<table width='100%'>
		<tr>
			<td align="center" class="header">Seal Juvenile Referral Record
			</td>
		</tr>
	</table>
	<%-- END HEADING TABLE --%>


	<%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors>
			</td>
		</tr>
	</table>
	<%-- END ERROR TABLE --%>
	<br>
	<br>
	<br>
	<br>
	<table align="center" width="98%" border="0" cellpadding="2"
		cellspacing="1" class='borderTableBlue'>
		<tr>
			<BR>
		</tr>
		<tr>
			<td class="formDeLabel" nowrap='nowrap' width="1%" align="center"><bean:message
					key="prompt.2.diamond" />
				<bean:message key="prompt.juvenileNumber" />
			</td>
			<td class="formDe" align="center"><html:text
					name="ProdSupportForm" property="juvenileId" size="8" maxlength="8" styleId="juvenileNum" />
			</td>
		</tr>
		<%-- <tr>			
			<html:hidden styleId="hdnForward" name="ProdSupportForm" property="hiddenForward"/>
		</tr> --%>
	</table>


	<%-- BEGIN BUTTON TABLE --%>
	<br>
	<br>
	<br>
	<table align="center" border="0" width="100%">
		<tr>
			<td align="center"><html:submit property="submitAction"
					onclick="return validate()" styleId="submitBtn">
					<bean:message key="button.submit" />
				</html:submit> &nbsp; <input type="button" id="BtnRefresh" onclick="return Refresh()"
				value="<bean:message key='button.refresh'/>" /> &nbsp; <html:button
					property="org.apache.struts.taglib.html.CANCEL"
					onclick="return closeWindow(this.form)">
					<bean:message key="button.cancel"></bean:message>
				</html:button></td>
		</tr>
	</table>
	<%-- END BUTTON TABLE --%>

</html:form>

<br>


</body>
</html:html>
