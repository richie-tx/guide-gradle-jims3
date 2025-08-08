<!DOCTYPE HTML>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!-- Changes for JIMS200077276 Starts -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!-- Changes for JIMS200077276 ends -->

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.PDCodeTableConstants"%>
<!-- Changes for JIMS200077276 Starts -->
<%@ page import="naming.Features"%>
<!-- Changes for JIMS200077276 ends -->




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>

<%--msp:login / --%>

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading" />-selectControllingReferral.jsp</title>

<html:javascript formName="juvenileReferralForm"/>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/referral/selectControllingReferral.js"></script>

<script type='text/javascript'>
var harrisCountyDropDownValue = <%=PDCodeTableConstants.HARRIS_COUNTY%>;
</script>
</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0">
<%--BEGIN FORM TAG--%>
<html:form action="/displayManageAssignment.do" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Create_Juvenile.htm#|5">
	<br/>
<%-- BEGIN HEADING TABLE --%>
	<table width='100%'>
		<tr>
			<td align="center" class="header">Process Referrals - Select Controlling Referral</td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td class="required"><bean:message key="prompt.requiredFields" /></td>
		</tr>
		<tr>
			<td>
				<ul>
					<li>Click the radio button to select the controlling referral and click the Next button.</li>
				</ul>
			</td>
		</tr>
	</table>
	<%-- END INSTRUCTION TABLE --%>
	
	<%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
		
	<table cellpadding='1' cellspacing='1' border='0' width="99%" class="borderTableBlue" align="center">
	 	<tr>
			<td>
		  		<table width='100%' cellpadding="1" cellspacing="1">
	 				<tr class="referralDetailHead">   
	 					<td width = '5%'><bean:message key="prompt.controllingReferral" /></td>
	    				<td>   
							<bean:message key="prompt.referral" />   
	          			</td>
	          			<td>   
							<bean:message key="prompt.referralDate"/>
	          			</td>
	          			<td> 
							<bean:message key="prompt.offense" />
	          			</td>
	          			<td> 
							<bean:message key="prompt.offenseCategory"/> 
	          			</td>
		          		<td>
	  						<bean:message key="prompt.disposition"/>
		          		</td>
		          		<td>
	  						<bean:message key="prompt.decisionDate"/>
		          		</td>
		          		<td>
	  						<bean:message key="prompt.probationStartDate"/>
		          		</td>
		          		<td>
	  						<bean:message key="prompt.probationEndDate"/>
		          		</td>
					</tr>
					
		  <%-- End Pagination item wrap --%>
		
	  				<logic:iterate id="referralList" name="juvenileReferralForm" property="selectedReferrals" indexId='indexer'>
		  				<bean:define id="refNum" name="referralList" property="referralNumber" type="java.lang.String"></bean:define> 	  					
		          		<!-- Begin Pagination item wrap -->
		       			<pg:item>
			   				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" colspan="10" id=''>
			   					<td class='formDe' colspan="1"><html:radio name="referralList" property="referralNumber" value="<%=refNum%>" styleId=''></html:radio></td>
			   					<td class='formDe' colspan="1"><bean:write name="referralList" property="referralNumber"/></td>
					 			<td class='formDe'><bean:write name="referralList" property="referralDate" formatKey="date.format.mmddyyyy"/></td>
			   					<td class='formDe'><span title="<bean:write name="referralList" property="offenseDesc"/>"><bean:write name="referralList" property="offense"/></span></td>
			   					<td class='formDe'><bean:write name="referralList" property="offenseCategory"/></td>
								<td class='formDe'><span title="<bean:write name="referralList" property="finalDispositionDescription"/>"><bean:write name="referralList" property="finalDisposition"/></span></td>
								<td class='formDe'><bean:write name="referralList" property="intakeDecDate" formatKey="date.format.mmddyyyy"/></td>
								<%-- <td class='formDe'><html:text name="referralList" styleId="probationStartDate" property="probationStartDateStr"  size="8" maxlength="10"/></td>
								<td class='formDe'><html:text name="referralList" styleId="probationEndDate" property="probationEndDateStr" size="8" maxlength="10"/></td> --%>
								<td class='formDe'><bean:write name="referralList" property="probationStartDate" formatKey="date.format.mmddyyyy"/></td>
								<td class='formDe'><bean:write name="referralList" property="probationEndDate" formatKey="date.format.mmddyyyy"/></td>
							</tr>
		  				</pg:item>
	 				</logic:iterate> 
				</table>
			</td>
		</tr>
 	</table>
	<html:hidden styleId="action" name="juvenileReferralForm" property="action"/>
	<!-- BEGIN BUTTON TABLE -->
	<div class='spacer'></div> 
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center">
				<html:submit property="submitAction"  styleId="backBtn"> <bean:message key="button.back" /></html:submit>&nbsp;
				<html:submit property="submitAction"  styleId="nextBtn"> <bean:message key="button.next" /></html:submit>&nbsp;
				<html:button property="org.apache.struts.taglib.html.CANCEL" onclick="goNav('/appshell/displayHome.do')">
  					<bean:message key="button.cancel"></bean:message>
  		 	 	</html:button>
  		 	 	<%-- <html:button property="submitAction" styleId="backBtn"><bean:message key="button.back"></bean:message></html:button> --%>
			</td>
		</tr>
	</table>
</html:form>
<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
	