<!DOCTYPE HTML>
<%-- Used to display update juvenile benefit off Benefits Tab in Juvenile Profile --%>
<%--MODIFICATIONS --%>
<%-- 07/22/2016		ugopinath User Story 27022 --%>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- juvenileBenefitsUpdate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>

<script type='text/javascript' src="/<msp:webapp/>js/benefits.js"></script>


</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">


<html:form action="/submitJuvenileInsuranceCreate" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|0">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Update Benefit Status
		</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class=spacer></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul> 
				<li>Select a new Benefit Status.</li>
				<li>Click Finish to complete status update.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9> Required Fields</td>		  
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
   		<tiles:put name="headerType" value="profileheader"/>
	</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 
<div class="spacer"></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top">
    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">			 	    
    						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">	
    						<tiles:put name="tabid" value="interviewinfotab"/>				
    						</tiles:insert>	    				
					</td>
			  	</tr>
  				<tr>
			  		<td bgcolor="#33cc66" height="5"></td>
  				</tr>
  			</table>

  			<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableGreen">
  				<tr>
  					<td valign="top" align="center">
					<%-- BEGIN TABLE --%>
            			
            				<div class=spacer></div>
					  		<table width='98%' border="0" cellpadding="0" cellspacing="0">
						  		<tr>
							  		<td>
		  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
		  									<tr>
		  										<td valign="top">
		  										<%--tabs start--%>
		  											<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
		  												<tiles:put name="tabid" value='benefitstab' />
		  											</tiles:insert>	
		  										<%--tabs end--%>
		  										</td>
		  									</tr>
		  									<tr>
		  								  	<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
		  									</tr>
		  								</table>
		  								<table width='100%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
		  									<tr>
												<td>
													<div class="spacer"></div>
													<%-- BEGIN BENFITS TABLE --%>
														<table width='98%' border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
															<tr>
																<td valign="top" class="detailHead" colspan="8"><bean:message key="prompt.update" />&nbsp;<bean:message key="prompt.benefit" /></td>
															</tr>
															
				      										<tr>
			  													<td class="formDeLabel" width="12%" nowrap>Eligible for Benefits</td>
			  													<td class="formDe">
			  														<logic:equal name="juvenileBenefitsInsuranceForm" property="currentBenefit.eligibleForBenefits" value="true">Yes</logic:equal>
																	<logic:notEqual name="juvenileBenefitsInsuranceForm" property="currentBenefit.eligibleForBenefits" value="true">No</logic:notEqual>
																</td>
			  													<td class="formDeLabel"  width="10%" nowrap>Receiving Benefits</td>
			  													<td class="formDe" colspan="8">
			  														<logic:equal name="juvenileBenefitsInsuranceForm" property="currentBenefit.receivingBenefits" value="true">Yes</logic:equal>
																	<logic:notEqual name="juvenileBenefitsInsuranceForm" property="currentBenefit.receivingBenefits" value="true">No</logic:notEqual>
																</td>
				      										</tr>						      									
				      										<tr class="formDe">      											
					      											<td class=formDeLabel >Type of Eligibility</td>
					      											<td class=formDe><bean:write name="juvenileBenefitsInsuranceForm" property="currentBenefit.eligibilityType" /></td>
					      											<td class=formDeLabel width="5%" nowrap>Received By</td>
				      											
					      											<td class=formDe><bean:write name="juvenileBenefitsInsuranceForm" property="currentBenefit.receivedBy" /></td>
					      										
					      											<td class=formDeLabel width="1%" nowrap>Amount</td>
					      											<td class=formDe>
					      												<html:text name="juvenileBenefitsInsuranceForm" property="currentBenefit.receivedAmt" styleId='receivedAmt' size="15" />
					      											</td>
					      											<td class=formDeLabel width="1%" nowrap>ID Number</td>
					      											<td class=formDe>
					      												<html:text name="juvenileBenefitsInsuranceForm" property="currentBenefit.idNumber" styleId='idNumber' size="15" />
					      											</td>										      								
				      										</tr>	
				      										<tr>										
												  				<td class="formDeLabel">
												  					<bean:message key="prompt.benefit" />&nbsp;<bean:message key="prompt.status" />
												  				</td> 
												  				<td class="formDe" colspan="8"><bean:write name="juvenileBenefitsInsuranceForm" property="currentBenefit.benefitStatus" /></td>
															<tr>				  												
																      									
						    								<tr>
				      											<td class=formDeLabel width="1%"><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border=0 width=10 height=9> New Benefit Status</td>
				      											</td>
				      											<td class=formDe colspan="8">
				      												<html:select name="juvenileBenefitsInsuranceForm" property="currentBenefit.benefitStatus" styleId="benefitStatusId">
												           				<html:option value=""><bean:message key="select.generic" /></html:option>
												           		  		<html:optionsCollection property="benefitStatuses" value="code" label="description"/>	
												           			</html:select>
				      													
															    </td>
				      										</tr>
														</table>
												</td>
											</tr>
		  								</table>									 
									</td>
								</tr>
								
						</table>
				</table>
					
				<div class="spacer"></div>
			</td>
			</tr>
				
					
			</table>
		</td>
	</tr>
</table>

<div class="spacer"></div>		         
<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
			
				<html:submit property="submitAction" styleId="updateFinishButton"><bean:message key="button.finish"/></html:submit>			
			
			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		</td>
	</tr>
</table>
 <%-- END BUTTON TABLE --%>
<%-- END DETAIL TABLE --%>
</html:form>
<div class='spacer'></div>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>