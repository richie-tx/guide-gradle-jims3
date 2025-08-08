<!DOCTYPE HTML>
<%-- Used for creating Risk Formulas in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek	02/24/2012	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../../WEB-INF/msp.tld" prefix="msp"%>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - riskFormulaDeleteSummary.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

<script>

function disableSubmit( button, form )
{
  var hasURLExtensions = (-1);
  var myAction = form.action;
  var btnVal = button.value;
  var hasAmpersendInValue;

  hasAmpersendInValue = btnVal.indexOf( "&" );
  if( hasAmpersendInValue != (-1) )
  {
     btnVal = btnVal.replace( /&/,"%26" );
  }   

  hasURLExtensions = myAction.indexOf( "?" );
  if( hasURLExtensions != (-1) )
  {
		form.action += "&" + button.name +"=" +btnVal;
  }
  else
  {
		form.action += "?" +button.name +"=" +btnVal;;
  }

  form.submit( );
  button.disabled = true;
  //hide the buttons on click of confirm.
  document.getElementById('backBtn').style.visibility = 'hidden';
  document.getElementById('cancelBtn').style.visibility = 'hidden';

  return false;

}

</script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/submitRiskFormulaDelete" target="content">

<span align="center">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskFormulas"/> - <bean:message key="prompt.formula"/>&nbsp;<bean:message key="prompt.delete"/>
				<logic:equal name="riskFormulaDetailsForm" property="pageType" value="summary">
					<bean:message key="prompt.summary" />
				</logic:equal>
				<logic:equal name="riskFormulaDetailsForm" property="pageType" value="confirm">
					<bean:message key="prompt.confirmation" />
				</logic:equal>		
			</td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN MESSAGES TABLE --%>
	<table width="98%" border="0" align="center">
		<logic:equal name="riskFormulaDetailsForm" property="pageType" value="confirm">
			<tr>
				<td align="center" class="confirm"><bean:write name="riskFormulaDetailsForm" property="confirmMessage"/></td>
			</tr>
		</logic:equal>
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
<%-- END MESSAGES TABLE --%>
	<div class="spacer4px"></div>	
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<logic:equal name="riskFormulaDetailsForm" property="pageType" value="summary">
					<ul>
						<li>Select Back button to return to search results page.</li>       
					</ul>
				</logic:equal>
				<logic:equal name="riskFormulaDetailsForm" property="pageType" value="confirm">
					<ul>
						<li>Select Formula Search button to return to search page.</li>
					</ul>
				</logic:equal>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
	<logic:equal name="riskFormulaDetailsForm" property="pageType" value="summary">
		<bean:define id="boxTitle" value="Formula to be Deleted" toScope="page"/>
	</logic:equal>
	<logic:equal name="riskFormulaDetailsForm" property="pageType" value="confirm">
		<bean:define id="boxTitle" value="Formula Deleted" toScope="page"/>
	</logic:equal>	
<%-- BEGIN FORMULA INFO TABLE --%>
	<table width="98%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
				<tiles:insert page="riskFormulaInfoTile.jsp" flush="true">
					<tiles:put name="formName" type="String" value="riskFormulaDetailsForm" />
					<tiles:put name="boxTitle" type="String" value="${boxTitle}" />
					<tiles:put name="borderClass" type="String" value="borderTableBlue" />
				</tiles:insert>
			</td>
		</tr>
	</table>		
<%-- END FORMULA INFO TABLE --%>					
</span>
<div class="spacer4px"></div>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<logic:equal name="riskFormulaDetailsForm" property="pageType" value="summary">
				<html:submit property="submitAction" styleId ="backBtn" onclick="disableSubmit(this,this.form)"><bean:message key="button.back"/></html:submit>
				<html:submit property="submitAction" styleId ="confirmBtn" onclick="disableSubmit(this,this.form)"><bean:message key="button.confirmDelete" /></html:submit>
				<html:submit property="submitAction" styleId="cancelBtn" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
			</logic:equal>
			<logic:equal name="riskFormulaDetailsForm" property="pageType" value="confirm">
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.formulaSearch"/></html:submit>
			</logic:equal>	
		</td>
	</tr>
</table>
<div class="spacer4px"></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>