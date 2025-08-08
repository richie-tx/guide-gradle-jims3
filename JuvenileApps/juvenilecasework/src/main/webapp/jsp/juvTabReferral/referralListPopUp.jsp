<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2005	UGopinath	Create JSP --%>

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
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>


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

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base/>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading"/> - referralListPopUp.jsp</title>

 
<script type="text/javascript">
$(function () {
	window.onbeforeunload = function (e) {	
		
		var facilityWinStatus = localStorage.getItem("facilityHistWin");	
		localStorage.setItem("referralWin", "close");	
		localStorage.setItem("juvnum", "");
		//$(window).off("beforeunload");

		};
		
	
		/*setTimeout(function() {
			console.log("Page is reloaded");
			location.reload();
	    }, 60000);*/


});

 </script>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayJuvenileProfileReferralList" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|217">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
  		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Referral List</td>	  	    	 
	</tr>  	
</table>
<%-- END HEADING TABLE --%>
<div class="spacer"></div>

<%--juv profile header start--%>
<table align="center" cellpadding="1" cellspacing="0" border="0" width="98%">
	<tr>
		<td bgcolor='#cccccc'>
			<logic:notEmpty name="juvenileProfileHeader" property="juvenileNum">
				<table width="100%" border="0" cellpadding="2" cellspacing="1">				
           			<tr class=bodyText>
             				<td class="formDeLabel" width="30%" nowrap><bean:message key="prompt.profile" /> <bean:message key="prompt.info" />&nbsp;&nbsp;       			  		
        			  		</td>           			
             				<td class="formDeLabel" nowrap align="right">
              					JPO Of Record - <span title='<bean:write name="juvenileProfileHeader" property="jpoOfRecord"/>'><bean:write name="juvenileProfileHeader" property="jpoOfRecID"/></span></a> 
             				</td>
           			</tr>
           		</table>
           		<table width="100%" border="0" cellpadding="2" cellspacing="1">
  					<tr>
  						<td class="headerLabel" width="16%"><bean:message key="prompt.juvenile" />&nbsp;#</td>
  						<td class="headerData" 	width="27%"><logic:equal name="juvenileProfileHeader" property="restrictedAccess" value="true"><font color="red"><bean:write name="juvenileProfileHeader" property="juvenileNum"/></font></logic:equal>
  						<logic:notEqual name="juvenileProfileHeader" property="restrictedAccess" value="true"><bean:write name="juvenileProfileHeader" property="juvenileNum"/></logic:notEqual></td>
  						<td class="headerLabel" width="16%"><bean:message key="prompt.juvenileName" /> </td>
  						<td class="headerData"><bean:write name="juvenileProfileHeader" property="juvenileName"/> 
  						<logic:equal name="juvenileProfileHeader" property="restrictedAccess" value="true"><span title='RESTRICTED ACCESS'><font color="red">(RESTRICTED)</font></span></logic:equal></td>
  					</tr>
  				</table>
  					<table width="100%" border="0" cellpadding="2" cellspacing="1">
  					<tr>
  						<td class="headerLabel" nowrap width="16%"><bean:message key="prompt.currentAge" /></td>
  						<td class="headerData" width="27%">	  						
  						  <div style="float:left;width:50%;"><bean:write name="juvenileProfileHeader" property="age"/></div>
						  <div style="float:right;width:50%;"><bean:message key="prompt.dob" />:
  								<bean:write name="juvenileProfileHeader" property="dateOfBirth"/></div>
  						</td>
  						<td class="headerLabel" nowrap width="16%"><bean:message key="prompt.juvenile" /> <bean:message key="prompt.status" /></td>
  						<td class="headerData"><bean:write name="juvenileProfileHeader" property="status"/>&nbsp;&nbsp;&nbsp;
  						<div style="float:right;width:50%;"><bean:message key="prompt.facreleasedate" />:
  								<bean:write name="juvenileProfileHeader" property="facilityreleaseDate"/></div>
  						<%-- <td class="headerLabel" nowrap width="16%"><bean:message key="prompt.facreleasedate" /></td>
  						<td class="headerData"><bean:write name="juvenileProfileHeader" property="facilityreleaseDate"/>&nbsp;&nbsp;&nbsp; --%>
  						<logic:notEmpty name="juvenileProfileHeader" property="detentionFacilityId">
    						<logic:notEqual name="juvenileProfileHeader" property="detentionFacilityId" value="">
	      						<bean:write name="juvenileProfileHeader" property="detentionFacilityId"/>:
	      						<bean:write name="juvenileProfileHeader" property="detentionStatusId"/>
    						</logic:notEqual>
  						</logic:notEmpty>
  						</td>
  					</tr>
  				</table>
			</logic:notEmpty>

			<logic:empty name="juvenileProfileHeader" property="juvenileNum">
  				<table width="100%" border="0" cellpadding="2" cellspacing="1">
  					<tr>
  						<td class="headerLabel" width="100%" align="center">Error:  No juvenile profile supplied on the request.</td>
  					</tr>
  				</table>				
			</logic:empty>		
		</td>
	</tr>
</table>		
<%--juv profile header end--%>
<div class="spacer"></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">				

<%-- BEGIN REFERRALS BORDER TABLE--%>
					  				<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
					  				<logic:notEqual name="assignedReferralsForm" property="actionType" value="fromProcessReferral">
					  					<tr>
					  						<td class="detailHead">Referrals</td>
					  					</tr>
					  				</logic:notEqual>
					  				<logic:equal name="assignedReferralsForm" property="actionType" value="fromProcessReferral">	
					  					<tr>
					  						<td class="referralDetailHead">Referrals</td>
					  					</tr>
					  				</logic:equal>
					  					<tr>
					    					<td>
<%-- BEGIN REFERRALS DETAILS TABLE--%>					    					
					    						<table cellpadding="2" cellspacing="1" border="0" width='100%'>
					    							<logic:empty name="assignedReferralsForm" property="referralPopList">
					  							  		<tr bgcolor='#cccccc'> 
					    									<td colspan="5" class="subHead">No Referrals Available</td> 
					  							  		</tr> 
					  								</logic:empty>
													<logic:notEmpty name="assignedReferralsForm" property="referralPopList">
														<tr bgcolor='#cccccc'> <%-- column titles --%>
															<td><bean:message key="prompt.refNo"/>
															<jims:sortResults sortId="1" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="referralNumber" primarySortType="String" defaultSort="true" defaultSortOrder="DESC"/>
															</td>
															<td><bean:message key="prompt.referralDt"/>	
															<jims:sortResults sortId="2" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="referralDate" primarySortType="Date"/>
															</td>
															<td><bean:message key="prompt.petitionAllegation"/>
															<jims:sortResults sortId="3" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="petitionAllegationDescr" primarySortType="String"/>
															</td>
															<td><bean:message key="prompt.levelUnit"/>
															<jims:sortResults sortId="4" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="supervisionCategoryId" primarySortType="String"/>
															</td>
															<td><bean:message key="prompt.JPO"/>
															<jims:sortResults sortId="5" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="jpoId" primarySortType="String"/>
															</td>
															<td><bean:message key="prompt.intake"/>
															<jims:sortResults sortId="6" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="intakeDecisionId" primarySortType="String" />
															</td>														
															<td><bean:message key="prompt.courtId"/>
															<jims:sortResults sortId="7" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="courtId" primarySortType="String"/>
															</td>														
															<td><bean:message key="prompt.courtDate"/>
															<jims:sortResults sortId="8" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="courtDate" primarySortType="Date"/>
															</td>															
															<td><bean:message key="prompt.adjDSN"/>
															<jims:sortResults sortId="9" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="courtResult" primarySortType="String"/>
															</td>															
															<td><bean:message key="prompt.finalDisposition"/>
															<jims:sortResults sortId="10" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="finalDisposition" primarySortType="String"/>
															</td>														
															<td><bean:message key="prompt.petition"/>
															<jims:sortResults sortId="11" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="petitionNumber" primarySortType="String"/>
															</td>														
															<td><bean:message key="prompt.petition"/> <bean:message key="prompt.status"/>
															<jims:sortResults sortId="12" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="petitionStatus" primarySortType="String"/>
															</td>															
															<td><bean:message key="prompt.probationStartDate"/>
															<jims:sortResults sortId="13" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="probationStartDate" primarySortType="Date"/>
															</td>	
															<td><bean:message key="prompt.probationEndDate"/>
															<jims:sortResults sortId="14" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="probationEndDate" primarySortType="Date"/>
															</td>
															<td><bean:message key="prompt.dateClosed"/>
															<jims:sortResults sortId="15" beanName="assignedReferralsForm" results="referralPopList" primaryPropSort="closeDate" primarySortType="Date"/>
															</td>																				
														</tr>
						  								<%String rowClass = "";%>  
													<logic:iterate indexId="index" id="myReferrals" name="assignedReferralsForm" property="referralPopList">
														    <% rowClass = ((index.intValue()) % 2 == 1) ? "normalRow" : "alternateRow"; %>
														    <logic:notEqual name="myReferrals" property="recType" value="REFERRAL">
														    	<jims2:isAllowed requiredFeatures='<%=Features.JCW_SEALPURGE_VIEW%>'>
														    		<tr class=<%=rowClass%> >
							  											<td>
							  												<span title="<bean:write name="myReferrals" property="daStatus"/> &nbsp; <bean:write name="myReferrals" property="daDateOut" format="MM/dd/yyyy"/> ">
							  													<bean:write name="myReferrals" property="referralNumber"/>
							  												</span>
							  												<logic:equal name="myReferrals" property="recType" value="I.REFERRAL">														                       
														                       <span style="color:red; font-weight: bold;" title=" Purged Referral"/>P</span>
														                    </logic:equal>
														                    <logic:notEqual name="myReferrals" property="recType" value="I.REFERRAL">
							  												 <span style="color:blue; font-weight: bold;" title=" Sealed Referral"/>S</span>
							  												 </logic:notEqual>
							  											</td>
																		<td><bean:write name="myReferrals" property="referralDate" format="MM/dd/yyyy"/></td>	
																		<td>
																			<logic:equal name="myReferrals" property="petitionsAvailable" value="true">
																					<span title="<bean:write name="myReferrals" property="petitionAllegationDescrExtended"/>"><bean:write name="myReferrals" property="petitionAllegation"/></span>
																			</logic:equal>
																		</td>
																		<td>
																			<logic:notEmpty  name="myReferrals" property="supervisionCategory">
																				<span title="<bean:write name="myReferrals" property="supervisionCategory"/>"><bean:write name="myReferrals" property="supervisionCategoryId"/></span>/<span title="<bean:write name="myReferrals" property="supervisionType"/>"><bean:write name="myReferrals" property="supervisionTypeId"/></span>
																			</logic:notEmpty>
																		</td>
																		<td><span title="<bean:write name="myReferrals" property="jpo"/>"><bean:write name="myReferrals" property="jpoId"/></span></td>
																		<td><span title="<bean:write name="myReferrals" property="intakeDecision"/>"><bean:write name="myReferrals" property="intakeDecisionId"/></span></td>
																		<td><bean:write name="myReferrals" property="courtId"/></td>
																		<td><bean:write name="myReferrals" property="courtDate" format="MM/dd/yyyy"/></td>																
																		<td><span title="<bean:write name="myReferrals" property="courtResultDesc"/>"><bean:write name="myReferrals" property="courtResult"/></span></td>
																		<td><span title="<bean:write name="myReferrals" property="finalDispositionDescription"/>"><bean:write name="myReferrals" property="finalDisposition"/></span></td>
																		<td><bean:write name="myReferrals" property="petitionNumber"/></td>
																		<td><span title="<bean:write name="myReferrals" property="petitionStatusDescr"/>"><bean:write name="myReferrals" property="petitionStatus"/></span></td>
																		<td><bean:write name="myReferrals" property="probationStartDate" formatKey="date.format.mmddyyyy"/></td>
																		<td><bean:write name="myReferrals" property="probationEndDate" formatKey="date.format.mmddyyyy"/></td>
																		<td><bean:write name="myReferrals" property="closeDate" formatKey="date.format.mmddyyyy"/></td>  
														  			</tr>
														  			<tr class=<%=rowClass%> >
						      											<td></td> 
						      											<td colspan='14'><b><bean:message key="prompt.offenses"/></b>: 
									      									<logic:equal name="myReferrals" property="offensesAvailable" value="true">
								     											<logic:iterate id="theOffenses" name="myReferrals" property="offenseCodes" indexId="index"> 
						    		 												<span title='<bean:write name="theOffenses" property="longDescription"/>, <bean:write name="theOffenses" property="offenseCategory"/>'>
						     															<bean:write name="theOffenses" property="offenseCode"/>
						     															<logic:notEqual name="myReferrals" property="offenseCollectionSize" value="<%=index.toString()%>">
						     																,
						     															</logic:notEqual>
						     														</span>
						     													</logic:iterate>
						     												</logic:equal>
						     											</td>
				     												</tr>
														    	</jims2:isAllowed>
														    </logic:notEqual>
														    <logic:equal name="myReferrals" property="recType" value="REFERRAL">
															    <tr class=<%=rowClass%> >
						  											<td><span title="<bean:write name="myReferrals" property="daStatus"/> &nbsp; <bean:write name="myReferrals" property="daDateOut" format="MM/dd/yyyy"/> "><bean:write name="myReferrals" property="referralNumber"/></span></td>
																	<td><bean:write name="myReferrals" property="referralDate" format="MM/dd/yyyy"/></td>	
																	<td>
																			<logic:equal name="myReferrals" property="petitionsAvailable" value="true">
																		<span title="<bean:write name="myReferrals" property="petitionAllegationDescrExtended"/>"><bean:write name="myReferrals" property="petitionAllegation"/></span>
																		</logic:equal>
																	</td>
																	<td>
																		<logic:notEmpty  name="myReferrals" property="supervisionCategory">
																			<span title="<bean:write name="myReferrals" property="supervisionCategory"/>"><bean:write name="myReferrals" property="supervisionCategoryId"/></span>/<span title="<bean:write name="myReferrals" property="supervisionType"/>"><bean:write name="myReferrals" property="supervisionTypeId"/></span>
																		</logic:notEmpty>
																	</td>
																	<td><span title="<bean:write name="myReferrals" property="jpo"/>"><bean:write name="myReferrals" property="jpoId"/></span></td>
																	<td><span title="<bean:write name="myReferrals" property="intakeDecision"/>"><bean:write name="myReferrals" property="intakeDecisionId"/></span></td>
																	<td><bean:write name="myReferrals" property="courtId"/></td>
																	<td><bean:write name="myReferrals" property="courtDate" format="MM/dd/yyyy"/></td>																
																	<td><span title="<bean:write name="myReferrals" property="courtResultDesc"/>"><bean:write name="myReferrals" property="courtResult"/></span></td>
																	<td><span title="<bean:write name="myReferrals" property="finalDispositionDescription"/>"><bean:write name="myReferrals" property="finalDisposition"/></span></td>
																	<td><bean:write name="myReferrals" property="petitionNumber"/></td>
																	<td><span title="<bean:write name="myReferrals" property="petitionStatusDescr"/>"><bean:write name="myReferrals" property="petitionStatus"/></span></td>
																	<td><bean:write name="myReferrals" property="probationStartDate" formatKey="date.format.mmddyyyy"/></td>
																	<td><bean:write name="myReferrals" property="probationEndDate" formatKey="date.format.mmddyyyy"/></td>
																	<td><bean:write name="myReferrals" property="closeDate" formatKey="date.format.mmddyyyy"/></td>  
															  	</tr>
														  		<tr class=<%=rowClass%> >
					      											<td></td> 
					      											<td colspan='14'><b><bean:message key="prompt.offenses"/></b>: 
								      									<logic:equal name="myReferrals" property="offensesAvailable" value="true">
							     											<logic:iterate id="theOffenses" name="myReferrals" property="offenseCodes" indexId="index"> 
					    		 												<span title='<bean:write name="theOffenses" property="longDescription"/>, <bean:write name="theOffenses" property="offenseCategory"/>'>
					     															<bean:write name="theOffenses" property="offenseCode"/>
					     															<logic:notEqual name="myReferrals" property="offenseCollectionSize" value="<%=index.toString()%>">
					     																,
					     															</logic:notEqual>
					     														</span>
					     													</logic:iterate>
					     												</logic:equal>
					     											</td>
				     											</tr>
				     									</logic:equal>
													  	</logic:iterate>
													</logic:notEmpty>	
												</table>
<%-- END REFERRALS DETAILS TABLE--%>												
											</td>
										</tr>	
									</table>
<%-- END REFERRALS BORDER TABLE --%>
									<div class="spacer"></div>
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" width="100%" align="center">
										<tr>
											<td align="center">
												<html:button property="submitAction" onclick="window.close();"><bean:message key="button.close"/></html:button>   
											</td>
										</tr>
									</table>
<%-- END BUTTON TABLE --%>
		  		
			
   		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form> 
<div class='spacer'></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>