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
<title><bean:message key="title.heading" /> - overrideReferralAssignment.jsp</title>


<%-- Javascript for emulated navigation --%>
<html:javascript formName="createReferralForm"/>

<!--JQUERY FRAMEWORK-->

<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/referral/overrideAssignment.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<%--BEGIN FORM TAG--%>
<html:form action="/submitOverrideAssignment" target="content" >
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|162">       

<%-- BEGIN HEADING TABLE --%>
</br>
<table width='100%'>
  <tr>
    <td align="center" class="header"> Process Referrals - Override Assignment</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<logic:messagesPresent message="true"> 
	<table width="100%">
		<tr>		  
			<td align="center" class="messageAlert"><html:messages id="message" message="true"><bean:write name="message"/></html:messages>
			</td>		  
		</tr>   	  
	</table>
</logic:messagesPresent> 
</br>

<%-- BEGIN ERROR TABLE --%>
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
					<li>Enter required fields then click Submit.</li>
				</ul>
				
			</td>
		</tr>
		<tr>
			<td>
				<ul>
					<li>All date fields must be in the format of mm/dd/yyyy</li>
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
														<logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><span title='RESTRICTED ACCESS'> <font color="red"><b>(RESTRICTED)</b></font></span></logic:equal>
														&nbsp;
												  		<logic:equal name="juvenileBriefingDetailsForm" property="traitTypeId" value="MER">
												  			<span style="color:#8417A0; font-weight: bold;" title="<bean:write name="juvenileBriefingDetailsForm" property="traitTypeDescription"/>">(M)</span>
												  		</logic:equal>
													</td>
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
							<td></td>
							<td><bean:message key="prompt.refNo"/>
							<jims:sortResults beanName="juvenileReferralForm" results="referralList" primaryPropSort="referralNumber" primarySortType="STRING" defaultSortOrder="DESC" sortId="1" defaultSort="true"/>
							</td>
							<td valign = 'top'><bean:message key="prompt.referralDate"/>
							<jims:sortResults beanName="juvenileReferralForm" results="referralList" primaryPropSort="referralDate" primarySortType="DATE"  defaultSortOrder="ASC" sortId="2" />
							</td>
							<td valign = 'top'><bean:message key="prompt.offense"/>
							<jims:sortResults beanName="juvenileReferralForm" results="referralList" primaryPropSort="offense" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" />
							</td>
							<td valign = 'top'><bean:message key="prompt.offenseCategory"/>
							<jims:sortResults beanName="juvenileReferralForm" results="referralList" primaryPropSort="offenseCategory" primarySortType="STRING"  defaultSortOrder="ASC" sortId="4" />
							</td>
							<td valign = 'top'><bean:message key="prompt.intakeDecision"/>
							<jims:sortResults beanName="juvenileReferralForm" results="referralList" primaryPropSort="intakeDecisionId" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
							</td>
							<td valign = 'top'><bean:message key="prompt.intakeDate"/>
							<jims:sortResults beanName="juvenileReferralForm" results="referralList" primaryPropSort="" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
							</td>
							<td valign = 'top'><bean:message key="prompt.petitionNumber"/>
							<jims:sortResults beanName="juvenileReferralForm" results="referralList" primaryPropSort="petitionNumber" primarySortType="STRING" defaultSortOrder="ASC" sortId="11" />
							</td>
							<td><bean:message key="prompt.disposition"/>
							<jims:sortResults beanName="juvenileReferralForm" results="referralList" primaryPropSort="finalDisposition" primarySortType="STRING"  defaultSortOrder="ASC" sortId="10" />
							</td>
							<td valign = 'top'><bean:message key="prompt.decisionDate"/>
							<jims:sortResults beanName="juvenileReferralForm" results="referralList" primaryPropSort="courtDate" primarySortType="DATE"  defaultSortOrder="ASC" sortId="8" />
							</td>
							<td><bean:message key="prompt.currentSuprvision"/>
							<jims:sortResults beanName="juvenileReferralForm" results="referralList" primaryPropSort="supervisionCategoryId" primarySortType="STRING"  defaultSortOrder="ASC" sortId="10" />
							</td>
							<td valign = 'top'><bean:message key="prompt.JPO"/>
							<jims:sortResults beanName="juvenileReferralForm" results="referralList" primaryPropSort="jpo" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
							</td>
						</tr>
	<logic:iterate id="referralList" name="juvenileReferralForm" property="referralList" indexId="indexer">
		<bean:define id="referralId" name="referralList" property="referralNumber" type="java.lang.String"></bean:define>  
		  	<logic:notEqual name="referralList" property="recType" value="REFERRAL">
				<tr class="<%out.print((indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" disabled="true">
	      	</logic:notEqual>
			<logic:equal name="referralList" property="recType" value="REFERRAL">
				<tr class="<%out.print((indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
			</logic:equal>	
			<td class='formDe' >
				<input type=checkbox name="selectedToOverride" id='<%="overrideRefNum-"+referralId%>' value="<bean:write name="referralList" property="referralNumber"/>"></input>
			</td>
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
		</logic:iterate>					
	</table></td>
		</tr>	

			<div class='spacer'></div>
			<tr>
				<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0" border='0'>
						<tr class='referralDetailHead'>
							<td colspan= '2' class='paddedFourPix' align='left' nowrap='nowrap'>Assignment Details</td>
						</tr>
						<tr>
							<td colspan="2">
								<table width="100%" cellpadding="2" cellspacing="1" border='0'>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.assignmentType"/></td>
									<td class='formDe' colspan= '4'>
									<jims2:if name="juvenileReferralForm" property="disbleAssignment" op="equal" value="Y">
										<jims2:then>	
											<html:select property="assignmentType" styleId="assignmentType" disabled="true">
										   	 	<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="assignmentTypes" value="code" label="description" />
											</html:select>
										</jims2:then>
										<jims2:else>
											<html:select property="assignmentType" styleId="assignmentType">
										   	 	<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="assignmentTypes" value="code" label="description" />
											</html:select>
										</jims2:else>
									</jims2:if>
									</td>
								</tr>
								
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.supervisionCategory"/></td>									
									<td class='formDe' colspan= '4'>
											
									<jims2:if name="juvenileReferralForm" property="assignmentType" op="equal" value="REC">	
									<jims2:or name="juvenileReferralForm" property="assignmentType" value="INT" op="equal" />
									<jims2:or name="juvenileReferralForm" property="assignmentType" value="SBQ" op="equal" />
									<jims2:or name="juvenileReferralForm" property="disbleAssignment" value="Y" op="equal" />
									<jims2:or name="juvenileReferralForm" property="assignmentType" value="ADM" op="equal" />										
											<jims2:then>
											  <html:select property="supervisionCat" styleId="supervisionCat" disabled="true">	
												<html:option value="">
												<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="supervisionCategories" value="code" label="description" />
										      </html:select>								   	 	
									   	 </jims2:then>
										<jims2:else>
											<html:select property="supervisionCat" styleId="supervisionCat">
												<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="supervisionCategories" value="code" label="description" />
										    </html:select>
										</jims2:else>
									</jims2:if>
											
									</td>
								</tr>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.supervisionType"/></td>
									<td class='formDe' colspan= '4'>
									
										<jims2:if name="juvenileReferralForm" property="supervisionCat" op="equal" value="RC">	
										<jims2:or name="juvenileReferralForm" property="supervisionCat" value="PP" op="equal" />
										<jims2:or name="juvenileReferralForm" property="assignmentType" value="SBQ" op="equal" />										
											<jims2:then>
												<logic:notEqual  name="juvenileReferralForm" property="assignmentType" value="INT">
												<logic:notEqual  name="juvenileReferralForm" property="assignmentType" value="TRN">
												<html:select property="supervisionType" styleId="supervisionType" disabled="true">
											    	<html:option value="">
														<bean:message key="select.generic" />
													</html:option>
													<html:optionsCollection property="supervisionTypes" value="code" label="description" />
												</html:select>
												</logic:notEqual>
												</logic:notEqual>
												<logic:equal  name="juvenileReferralForm" property="assignmentType" value="INT">
												<html:select property="supervisionType" styleId="supervisionType" disabled="false">
											    	<html:option value="">
														<bean:message key="select.generic" />
													</html:option>
													<html:optionsCollection property="supervisionTypes" value="code" label="description" />
												</html:select>
												</logic:equal>
												<logic:equal  name="juvenileReferralForm" property="assignmentType" value="TRN">
												<html:select property="supervisionType" styleId="supervisionType" disabled="false">
											    	<html:option value="">
														<bean:message key="select.generic" />
													</html:option>
													<html:optionsCollection property="supervisionTypes" value="code" label="description" />
												</html:select>
												</logic:equal>
											</jims2:then>
										<jims2:else>
											<html:select property="supervisionType" styleId="supervisionType">
										   	 	<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="supervisionTypes" value="code" label="description" />
											</html:select>
										</jims2:else>
									</jims2:if>
									</td>
								</tr>
								
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.JPO"/></td>
									<td class='formDe'>
										<jims2:if name="juvenileReferralForm" property="assignmentType" op="equal" value="SBQ">	
										<jims2:or name="juvenileReferralForm" property="disbleAssignment" value="Y" op="equal" />
										<jims2:or name="juvenileReferralForm" property="assignmentType" value="REC" op="equal" />	
											<jims2:then>
												<html:text styleId="jpo" property="jpo" size="5" maxlength="5" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
												<html:submit property="submitAction"  styleId="validateBtn2" disabled="true"> <bean:message key="button.validate" /></html:submit>&nbsp; 
											</jims2:then>
											<jims2:else>
												<html:text styleId="jpo" property="jpo" size="5" maxlength="5"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
												<html:submit property="submitAction"  styleId="validateBtn1"> <bean:message key="button.validate" /></html:submit>&nbsp; 
												<input id="searchBtn" onclick="spinner();" type="button" value="Search" />
											</jims2:else>
										</jims2:if>
									</td>
								
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.assignmentDate"/></td>
									<td class='formDe'><html:text styleId="assignmentDateStr" property="assignmentDateStr" size="8" maxlength="10"/></td>
									<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.overrideReason"/></td>
									<td class='formDe' colspan ='4'>
										<html:select name="juvenileReferralForm" property="overrideReasonStr" styleId="overrideReasonStr">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="overrideReason" value="code" label="description"/> &nbsp;&nbsp;&nbsp;&nbsp;
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<span id="otherComments" class = "hidden">
								<bean:message key="prompt.reason"/>
								<!-- <input type="text" property="overrideOTHComments" id="overrideOTHComments" name="overrideOTHComments" size="50" maxlength="50" /> -->
								<html:text styleId="overrideOTHComments" property="overrideOTHComments" size="50" maxlength="50" />
								</span>
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
<html:hidden styleId='juvNum'  name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/>
		<%-- END INFORMATION TABLE --%>
<%-- END DETAIL TABLE --%>
	<!-- BEGIN BUTTON TABLE -->
	<div class='spacer'></div> 
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center">
			<logic:equal name="juvenileReferralForm" property="action" value="overrideAssignment">
				<html:button property="submitAction" styleId="submitBtn"> <bean:message key="button.submit" /></html:button>&nbsp;
				<input type="button" id="refreshBtn" onclick="goNav('/<msp:webapp/>processReferralBriefing.do?submitAction=Override Assignment')" value="<bean:message key='button.refresh'/>"/>&nbsp;
				<html:button styleId="cancelBtn" property="org.apache.struts.taglib.html.CANCEL" onclick="goNav('/appshell/displayHome.do')">
  					<bean:message key="button.cancel"></bean:message>
  		 	 	</html:button>
  		 	</logic:equal>
  		 	<logic:equal name="juvenileReferralForm" property="action" value="overrideAssignmentfromRefBrief">
				<html:button property="submitAction" styleId="submitBtn"> <bean:message key="button.submit" /></html:button>&nbsp;
				<input type="button" id="refreshBtn" onclick="goNav('/<msp:webapp/>processReferralBriefing.do?submitAction=Override Assignment')" value="<bean:message key='button.refresh'/>"/>&nbsp;
				<html:button styleId="cancelBtn" property="org.apache.struts.taglib.html.CANCEL" onclick="goNav('/appshell/displayHome.do')">
  					<bean:message key="button.cancel"></bean:message>
  		 	 	</html:button>
  		 	</logic:equal>
  		 	 	<logic:equal name="juvenileReferralForm" property="action" value="overrideSuccess">
  		 	 	<input type="submit" name="submitAction" onclick="spinner()" value="<bean:message key='button.searchJuvenile'/>" id="searchJuvenileBtn">
  		 	 	<input type="submit" name="submitAction" onclick="spinner()" value="<bean:message key='button.referralBriefing'/>" id="referralBriefingBtn">
				<input type="submit" name="submitAction" onclick="spinner()" value="<bean:message key='button.updateReferral'/>" id="updateRefBtn">
				<input type="submit" name="submitAction" onclick="spinner()" value="<bean:message key='button.nextOverride'/>" id="nxtOverrideBtn">
  		 	 	</logic:equal>
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
