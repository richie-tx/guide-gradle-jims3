<!DOCTYPE HTML>
<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- may 2007 - daw - create jsp --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>


<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.Features" %>
<%-- @ page import="naming.PDJuvenileCaseConstants" --%>




<%--BEGIN HEADER TAG--%>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;caseTabProgramReferrals - programReferralNoEventCreateSummary.jsp</title>

<link href="/<msp:webapp/>css/casework.css" rel="stylesheet" type="text/css">
<%--Added for the Bug fix #28488 starts--%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#healthServices").click(function(){
			var healthServiceUrl = "https://hcjpd.webhc.hctx.net/private/HealthServices/default.aspx";
			
			if ( confirm("Access to the SharePoint site is permitted for users within the Harris County network. Do you wish to continue?") ) {
				console.log("open new window");
				window.open( healthServiceUrl,"_blank");
			}
		});
	})
</script>
<%--Added for the Bug fix #28488 ends--%>
<html:base />
 
</head>

<body topmargin="0" leftmargin="0"> 
<html:form action="/submitProgramReferralNoEvent" target="content">
<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" >    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|347">
</logic:equal>
<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|238">
</logic:equal> 
<table width='100%'>
	<tr>
		<td align="center" class="header" >
			<bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;<bean:message key="title.create" />&nbsp;<bean:message key="prompt.programReferral" />
			<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" >
				<bean:message key="title.summary" />
			</logic:equal>
			<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
				<bean:message key="title.confirmation" />
			</logic:equal> 
		</td>
	</tr>
	<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
		<tr>
			<td align="center" class="confirm" >Program Referral information has been saved.</td>
		</tr>
	</logic:equal>   
</table> 
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%">
	<tr>
    	<td>
			<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" >
				<ul>
					<li>Review information, then select the Finish button to save the information.</li>	    
					<li>Select the Back button to return to the previous page to change information.</li>
				</ul>
			</logic:equal>
			<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
				<ul>
					<li>Select the Return to Referral List button to return to the list.</li>
				</ul>
			</logic:equal>   
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE --> 
<!--header info start -->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader" />
</tiles:insert> 
<!--header info end -->
<div class='spacer'></div>
<!-- BEGIN DETAIL TABLE -->
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>
  		<!--tabs start-->
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="programreferraltab" />
				<tiles:put name="casefileid" value="casefileId" /> 
			</tiles:insert>  
  		<!--tabs end-->

			<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td>
						<div class='spacer'></div>
<!-- BEGIN PROGRAM REFERRAL DETAILS TABLE -->
						<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class='detailHead'><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.details" /> - <bean:write name="programReferralForm" property="programReferral.providerProgramName"/></td> 
							</tr>
							<tr>
								<td valign='top' align='center'>
  					    			<table cellpadding='2' cellspacing='1' width='100%'>
                  						<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.serviceProviderName" /></td>
											<td class="formDe" colspan="3"><bean:write name="programReferralForm" property="programReferral.juvServiceProviderName" /></td>
										</tr>
                  						<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.programName" /></td>
											<td class="formDe" colspan="3"><bean:write name="programReferralForm" property="programReferral.providerProgramName" /></td>
										</tr>
                  						<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.beginDate" /></td>
											<td class="formDe" colspan="3"><bean:write name="programReferralForm" property="programReferral.beginDateStr" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.assignedHours" /></td>
											<td class="formDe"><bean:write name="programReferralForm" property="programReferral.assignedHours"/></td>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.courtOrdered" /></td>	
											<bean:define id="progRef" name="programReferralForm" property="programReferral"/>		
											<td class="formDe">
												<jims:displayBoolean name="progRef" property="courtOrdered" trueValue="YES" falseValue="NO"/>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.referralStatus" /></td>
											<td class="formDe" colspan="3"><bean:write name="programReferralForm" property="programReferral.referralStatus" /></td>
										</tr>	
										<tr>
											<td class="formDeLabel" colspan="4"><bean:message key="prompt.comments" /></td>
										</tr>
										<tr>	
											<td class="formDe" colspan="4">
												<div class='scrollingDiv100'>
													<bean:write name="programReferralForm" property="programReferral.comments" />
												</div>	
											</td>
										</tr>	
		              				</table>
			          			</td>
				      		</tr>
				   		</table>
<!-- END PROGRAM REFERRAL DETAILS TABLE -->							
						<div class='spacer'></div>		        
<!-- BEGIN BUTTONS TABLE -->
 						<table width='100%' colspan="3">
							<tr>
								<td align="center">
									<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>">
										<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
										<%--Bug fix #28488  starts--%>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> 
												<bean:message key="button.finish"/> 
										</html:submit>		
										<%--Bug fix #28488  ends--%>						
										<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
									</logic:equal>
									<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>">								
										<html:submit property="submitAction"><bean:message key="button.returnToReferralList"/></html:submit>
										<jims:isAllowed requiredFeatures="<%=Features.JCW_CF_PGMREF_HLTHSVCPGMASS%>">
										<input id="healthServices" type="button" value="Health Services Program Assessments"/>
										</jims:isAllowed>
									</logic:equal>		  
		          				</td>
		          				
		          					
		          				
		        			</tr>
		      			</table> 
		      <!--END BUTTONS TABLE -->	
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table> 
<!-- END DETAIL TABLE -->
</html:form>
<div id="test">
</div>
<div id="error">
</div>
</body>
</html:html>