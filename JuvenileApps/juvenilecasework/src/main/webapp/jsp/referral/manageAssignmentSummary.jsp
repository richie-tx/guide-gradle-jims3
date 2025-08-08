<!DOCTYPE HTML>

<%-- Used for Create Juvenile Referrals --%>
<%--MODIFICATIONS --%>
<%-- UGopinath 10/25/2018	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!-- Changes for JIMS200077276 Starts -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!-- Changes for JIMS200077276 ends -->
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - manageAssignmentSummary.jsp</title>


<%-- Javascript for emulated navigation --%>
<html:javascript formName="createReferralForm"/>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/referral/manageAssignment.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<%--BEGIN FORM TAG--%>
<html:form action="/displayManageAssignment.do" target="content" >
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|162">       

<%-- BEGIN HEADING TABLE --%>
</br>
<table width='100%'>
  <tr>
    <td align="center" class="header"> Process Referrals - Add Assignment Record Summary</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<logic:messagesPresent message="true"> 
	<table width="100%">
		<tr>		  
			<td align="center" class="messageAlert"><html:messages id="message" message="true"><bean:write name="message"/></html:messages></td>		  
		</tr>   	  
	</table>
</logic:messagesPresent> 
</br>

<%-- BEGIN ERROR TABLE  --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table> 
<%-- END ERROR TABLE --%>
</br>

<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		
		<tr>
			<td class="required"><bean:message key="prompt.requiredFields" /></td>
		</tr>
		<tr>
			<td>
				<ul>
				     <li>Select Finish button to create the assignment.</li>
					 <li>Select Back button to return to previous page.</li>
				</ul>
			</td>
		</tr>
	</table>
	<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN INQUIRY TABLE --%>

<%-- END INQUIRY TABLE --%>

<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
		
		<%-- BEGIN INFORMATION TABLE --%>
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
			<tr>
				<td width='50%' valign="top">
					<%--general info start --%>
						<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
							<tr class='referralDetailHead'>
								<td colspan= '2' class='paddedFourPix' align='left' nowrap='nowrap'>Profile Information</td>
							</tr>
							<tr>
								<td colspan="2">
 									<table width="100%" cellpadding="2" cellspacing="0" border='0'>
										<tr>
 											<td valign='top'>
												<table width="100%" cellpadding="2" cellspacing="1">
												<tr>
													<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.juvenile#"/></td>
													<td class='formDe'>
														<logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><font color="red"><b><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/></b></font></logic:equal>	
														<logic:notEqual name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/></logic:notEqual>
														<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="I.JUVENILE">
														&nbsp;&nbsp;&nbsp;&nbsp;<font color="Red" face="verdana"><b><span title='Purged'>P</span></b></font>
														</logic:equal>
														<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="S.JUVENILE">
														&nbsp;&nbsp;&nbsp;&nbsp;<font color="Red" face="verdana"><b><span title='Sealed'>S</span></b></font>
														</logic:equal>
													</td>
													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.juvenileName"/></td>
													<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.formattedName"/> 
														<logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><span title='RESTRICTED ACCESS'> <font color="red"><b>(RESTRICTED)</b></font></span></logic:equal></td>
													<td class='formDeLabel' nowrap='nowrap' width="1%"><bean:message key="prompt.hispanic" />?</td>
													<td class='formDe' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="hispanic"/></td>
												</tr>
											
												<tr>
													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.currentAge"/></td>
													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="age"/>
													<td class='formDeLabel' width="1%"><bean:message key="prompt.race" /></td>
													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.race"/></td>
													<td class='formDeLabel' width="1%" ><bean:message key="prompt.sex" /></td>
													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.sex"/></td>
													<td class='formDeLabel'> <bean:message key="prompt.dob"/></td>
													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.dateOfBirth" formatKey="date.format.mmddyyyy"/></td>														
													<td class='formDeLabel' width="1%" ><bean:message key="prompt.operator"/></td>
													<td class='formDe'><span title="<bean:write name="juvenileReferralForm" property="operatorDesc" />"><bean:write name="juvenileReferralForm" property="operator"/></span></td>
												</tr>											

									</table>				
							<div class='spacer'></div>						
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<div class='spacer'></div>
			<tr><td>
					<table width='100%' cellpadding="2" cellspacing="1">
						<tr class="referralDetailHead" colspan=6>
							<td><bean:message key="prompt.refNo"/></td>
							<td valign = 'top'><bean:message key="prompt.referralDate"/></td>
							<td valign = 'top'><bean:message key="prompt.offense"/></td>
							<td valign = 'top'><bean:message key="prompt.offenseCategory"/></td>
							<td valign = 'top'><bean:message key="prompt.intakeDecision"/></td>
							<td valign = 'top'><bean:message key="prompt.intakeDate"/></td>
							<td valign = 'top'><bean:message key="prompt.petitionNumber"/></td>
							<td><bean:message key="prompt.disposition"/></td>
							<td valign = 'top'><bean:message key="prompt.decisionDate"/></td>
							<td><bean:message key="prompt.currentSuprvision"/></td>
							<td valign = 'top'><bean:message key="prompt.JPO"/></td>
						</tr>
	<logic:iterate id="referralList" name="juvenileReferralForm" property="selectedReferrals" indexId="indexer"> 
	<bean:define id="referralId" name="referralList" property="referralNumber" type="java.lang.String"></bean:define>  
			  <%-- Begin Pagination item wrap --%>
		  <pg:item>
			<logic:notEqual name="referralList" property="recType" value="REFERRAL">
				<tr class="<%out.print((indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" disabled="true">
	      	</logic:notEqual>
			<logic:equal name="referralList" property="recType" value="REFERRAL">
				<tr class="<%out.print((indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
			</logic:equal>		
			
				<td class='formDe' ><bean:write name="referralList" property="referralNumber"/></td>
				<td class='formDe' ><bean:write name="referralList" property="referralDate" formatKey="date.format.mmddyyyy"/></td>
				<td class='formDe' ><span title="<bean:write name="referralList" property="offenseDesc"/>"><bean:write name="referralList" property="offense"/></span></td>
				<td class='formDe' ><bean:write name="referralList" property="offenseCategory"/></td>
				<td class='formDe' ><span title="<bean:write name="referralList" property="intakeDecision"/>"><bean:write name="referralList" property="intakeDecisionId"/></span></td>
				<td class='formDe' ><bean:write name="referralList" property="intakeDecDate" formatKey="date.format.mmddyyyy"/></td>
				<td class='formDe' ><bean:write name="referralList" property="petitionNumber"/></td>
				<%-- <td class='formDe' ><bean:write name="referralList" property="courtDate" formatKey="date.format.mmddyyyy"/></td>
				<td class='formDe' ><span title="<bean:write name="referralList" property="courtResultDesc"/>"><bean:write name="referralList" property="courtResult"/></span></td> --%>
				<td class='formDe' ><span title="<bean:write name="referralList" property="finalDispositionDescription"/>"><bean:write name="referralList" property="finalDisposition"/></span></td>
				<td class='formDe' ><bean:write name="referralList" property="courtDate" formatKey="date.format.mmddyyyy"/></td>
				<td class='formDe' >
				<logic:notEmpty  name="referralList" property="supervisionCategory">
					<span title="<bean:write name="referralList" property="supervisionCategory"/>"><bean:write name="referralList" property="supervisionCategoryId"/></span>/<span title="<bean:write name="referralList" property="supervisionType"/>"><bean:write name="referralList" property="supervisionTypeId"/></span>
				</logic:notEmpty>
				</td>
				<td class='formDe' ><span title="<bean:write name="referralList" property="jpo"/>"><bean:write name="referralList" property="jpoId"/></span></td>
			</tr>
		  </pg:item>
		  <%-- End Pagination item wrap --%>
		</logic:iterate>					
	</table></td>
		</tr>	

			<div class='spacer'></div>
			<tr>
				<td colspan="2">
					<table width="100%" cellpadding="2" cellspacing="0" border='0'>
						<tr class='referralDetailHead'>
							<td colspan= '2' class='paddedFourPix' align='left' nowrap='nowrap'>Assignment Details</td>
						</tr>
						<tr>
							<td colspan="2">
								<table width="100%" cellpadding="2" cellspacing="1" border='0'>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.assignmentType"/></td>
									<td class='formDe' colspan= '4'><bean:write name="juvenileReferralForm" property="assignmentType"/></td>
								</tr>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.supervisionCategory"/></td>									
									<td class='formDe' colspan= '4'><bean:write name="juvenileReferralForm" property="supervisionCat"/></td>
								</tr>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.supervisionType"/></td>
									<td class='formDe' colspan= '4'><bean:write name="juvenileReferralForm" property="supervisionType"/></td>
								</tr>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.jpo"/></td>									
									<td class='formDe' colspan= '4'><span title="<bean:write name="referralList" property="jpo"/>"><bean:write name="juvenileReferralForm" property="jpo"/></span></td>
								</tr>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.controllingReferral"/></td>									
									<td class='formDe' colspan= '4'><bean:write name="juvenileReferralForm" property="controllingRef"/></td>
								</tr>
								</table
									</td>
								</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
	</table>
	</td>
	</tr></table>
<html:hidden styleId="hascasefiles" name="juvenileBriefingDetailsForm" property="hasCasefiles"/>	
<html:hidden styleId="action" name="juvenileReferralForm" property="action"/>	
<html:hidden styleId="selectedReferrals" name="juvenileReferralForm" property="selectedRefToOverride"/> <!-- Using this in JS -->	
		<%-- END INFORMATION TABLE --%>
<%-- END DETAIL TABLE --%>
	<!-- BEGIN BUTTON TABLE -->
	<div class='spacer'></div> 
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center">
				<html:submit property="submitAction"  styleId="backBtn"> <bean:message key="button.back" /></html:submit>&nbsp;
				<html:submit property="submitAction"  styleId="finishBtn"> <bean:message key="button.finish" /></html:submit>&nbsp;
				<html:button property="org.apache.struts.taglib.html.CANCEL" onclick="goNav('/appshell/displayHome.do')">
  					<bean:message key="button.cancel"></bean:message>
  		 	 	</html:button>
  		 	 	
			</td>
		</tr>
	</table>
<%-- END BUTTON TABLE --%>
<br>
</html:form>

<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
