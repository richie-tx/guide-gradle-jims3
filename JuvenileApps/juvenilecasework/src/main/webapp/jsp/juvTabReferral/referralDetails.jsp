<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 08/24/2015 RCapestani #29441 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Juvenile Profile Referrals) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base/>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading"/> - referralDetails.jsp</title>

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayJuvenileProfileReferralDetails" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|216">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
  	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Referral Details</td>	  	    	 
	</tr>  	
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td>
  		<ul>
  			<li>Click on a hyperlinked Disposition Date to view Disposition detail.</li>
  			<li>Select the Back button to return to theprevious page.</li>
  		</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%> 

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>


<%-- END CASEFILE HEADER INFO --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
  		<table width='100%' border="0" cellpadding="0" cellspacing="0">
  			<tr>
  				<%--tabs start--%>
  				<td valign="top">
    				<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
    					<tiles:put name="tabid" value="referralstab"/>
    					<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  					</tiles:insert>	
    				<%--tabs end--%>
  				</td>
  			</tr>
  			<tr>
  				<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
  			</tr>
  		</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
					<div class='spacer'></div>
  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td valign="top">
								  <tiles:insert page="/jsp/caseworkCommon/referralTabs.jsp" flush="true">    								
    							</tiles:insert>
								</td>
  						</tr>
  						<tr>
  							<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
  						</tr>
			  		</table>

			  		<%-- BEGIN DETAILS --%> 
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" align="center">
			
									<div class='spacer'></div>
									<table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue">
										<tr>
											<td colspan="4" class="detailHead">Referral # - <bean:write name="assignedReferralsForm" property="referralNum"/></td>
										</tr>
										<tr>
											<td valign="top" align="center">
				  							<table cellpadding="4" cellspacing="1" width='100%'>
				  								<tr>
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.transaction"/> <bean:message key="prompt.#"/></td>
														<td class="formDe"><bean:write name="assignedReferralsForm" property="transactionNum"/></td>
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.daLog#"/></td>
														<td class='formDe'>
														<jims2:isAllowed requiredFeatures="<%=Features.JCW_REFERRALVIEW_DALOG_PETITION%>">
															<a href="/<msp:webapp/>displayJuvenileProfilePetitionDetails.do?submitAction=View&jotChargesDisplayOnly=true&page=profile&notDetailed=false"><bean:write name="assignedReferralsForm" property="daLogNum"/></a>
														</jims2:isAllowed>
														<jims2:isAllowed requiredFeatures="<%=Features.JCW_REFERRALVIEW_DALOG_PETITION%>" value="false">
															<bean:write name="assignedReferralsForm" property="daLogNum"/>
														</jims2:isAllowed>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.dastatus"/></td>
														<td class="formDe"><bean:write name="assignedReferralsForm" property="daStatus"/></td>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.dadateOut"/></td>
														<td class='formDe'><bean:write name="assignedReferralsForm" property="DAdateOut" formatKey="date.format.mmddyyyy" /></td>
													</tr>
													<tr>
														<td class="formDeLabel" width='1%' nowrap="nowrap"><bean:message key="prompt.court"/> <bean:message key="prompt.id"/></td>
														<td class="formDe" width='50%'><bean:write name="assignedReferralsForm" property="courtId"/></td>
														<td class="formDeLabel" width='1%' nowrap="nowrap"><bean:message key="prompt.court"/> <bean:message key="prompt.date"/></td>
														<td class="formDe" width='50%'><bean:write name="assignedReferralsForm" property="courtDate"/></td>
													</tr>
													<tr>
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.court"/> <bean:message key="prompt.result"/></td>
														<td class="formDe" colspan="4"><bean:write name="assignedReferralsForm" property="courtResultDesc"/></td>
													</tr>
													<tr>									
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.court"/> <bean:message key="prompt.disposition"/></td>
														<td class="formDe" colspan="4"><bean:write name="assignedReferralsForm" property="courtDispositionDesc"/></td>
													</tr>
													<tr>
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.intake"/> <bean:message key="prompt.date"/></td>
														<td class="formDe"><bean:write name="assignedReferralsForm" property="intakeDate"/></td>
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.intake"/> <bean:message key="prompt.decision"/></td>
														<td class="formDe"><bean:write name="assignedReferralsForm" property="intakeDecision"/></td>
													</tr>
													<tr>
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.closed"/> <bean:message key="prompt.date"/></td>
														<td class="formDe" colspan="4"><bean:write name="assignedReferralsForm" property="closeDate"/></td>
													</tr>
													<tr>
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.referralDate"/></td>
														<td class="formDe"><bean:write name="assignedReferralsForm" property="referralDate" formatKey="date.format.mmddyyyy"/></td>
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.referral"/></td>
														<td class="formDe"><bean:write name="assignedReferralsForm" property="referralNum"/></td>
													</tr>
				
				    							<tr>
				    								<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.supervisionBeginDate" /></td>
				    								<td class="formDe"><bean:write name="assignedReferralsForm" property="supervisionBeginDate" formatKey="date.format.mmddyyyy" /></td>
				    								<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.supervisionEndDate" /></td>
				    								<td class="formDe"><bean:write name="assignedReferralsForm" property="supervisionEndDate" formatKey="date.format.mmddyyyy" /></td>
				    							</tr>
				    							<tr>
				    								<td class="formDeLabel" nowrap="nowrap">Type of Case</td>
				    								<td class="formDe"><bean:write name="assignedReferralsForm" property="caseType" /></td>
				    								<td class="formDeLabel" nowrap="nowrap">Termination Date</td>
				    								<td class="formDe"><bean:write name="assignedReferralsForm" property="terminationDate"  formatKey="date.format.mmddyyyy" /></td>
				    							</tr>
				
													<tr>
														<td class="formDeLabel" colspan="4"><bean:message key="prompt.levelOfCare"/></td>										
													</tr>
													<tr>
														<td class="formDe" colspan="4"><bean:write name="assignedReferralsForm" property="levelOfCare"/></td>
													</tr>
				  							</table>
											</td>
										</tr>
									</table>
				
									<%--offenses start--%>
									<div class='spacer'></div>
									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td class="detailHead">Offenses</td>
										</tr>
										<tr>
											<td>
											<table cellpadding='2' cellspacing='1' width='100%'>
												<tr bgcolor='#cccccc'>
													<td align='left' class="subHead"><bean:message key="prompt.investigation"/> <bean:message key="prompt.#"/></td>									
													<td align='left' class="subHead"><bean:message key="prompt.offense"/> <bean:message key="prompt.date"/></td>
													<td align='left' class="subHead"><bean:message key="prompt.offense"/> <bean:message key="prompt.level"/></td>
													<td align='left' class="subHead"><bean:message key="prompt.citation"/> <bean:message key="prompt.source"/></td>
													<td align='left' class="subHead"><bean:message key="prompt.citation"/> </td>
													<td align='left' class="subHead"><bean:message key="prompt.description"/></td>
													<td align='left' class="subHead"><bean:message key="prompt.seq"/> <bean:message key="prompt.#"/></td>
												</tr>
				
												<logic:empty name="assignedReferralsForm" property="offenses">
				  								<tr>
				  									<td colspan="2">No offenses exist for this referral.</td>
				  								</tr>
												</logic:empty>								
				
												<logic:notEmpty name="assignedReferralsForm" property="offenses">
				  								<logic:iterate indexId="index" id="offenses" name="assignedReferralsForm" property="offenses"> 								
				    								<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
				    									<td valign="top"><bean:write name="offenses" property="investigationNum"/></td>
				    									<td valign="top"><bean:write name="offenses" property="offenseDateFormatted"/></td>
				    									<td valign="top"><bean:write name="offenses" property="catagory"/></td>	
				    									<td valign="top"><bean:write name="offenses" property="citationSource"/></td>	
				    									<td valign="top"><bean:write name="offenses" property="citationCode"/></td>									
				    									<td valign="top"><bean:write name="offenses" property="offenseDescription"/></td>	
				    									<td valign="top"><bean:write name="offenses" property="sequenceNum"/></td>			
				    								</tr>
				  								</logic:iterate>
												</logic:notEmpty>
				
											</table>
											</td>
										</tr>
									</table>
									<%--offenses end--%> 
				
									<%-- petitions begin --%>
									<div class='spacer'></div>
									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td class="detailHead">Petitions</td>
										</tr>
										<tr>
											<td>
				  							<table width="100%" cellpadding="4" cellspacing="1">						
				  								<tr bgcolor="#cccccc">
				  									<td align='left' class="formDeLabel" align="left"><bean:message key="prompt.petition"/> <bean:message key="prompt.#"/></td>
				  									<td align='left' class="formDeLabel">Amendment #</td>
				  									<td align='left' class="formDeLabel">Filed/Amend Date</td>
				  									<td align='left' class="formDeLabel"><bean:message key="prompt.level"/> <bean:message key="prompt.degree"/></td>
				  									<td align='left' class="formDeLabel"><bean:message key="prompt.penal"/> <bean:message key="prompt.category"/></td>
				  									<td align='left' class="formDeLabel"><bean:message key="prompt.allegation"/></td>
				  								</tr>				
				
				  								<logic:empty name="assignedReferralsForm" property="petitions">			
				    								<tr class="normalRow">
				    									<td colspan="3" valign="top">No petitions exist for this referral.</td>																		
				    								</tr>								
				  								</logic:empty> 							
				  
				  								<logic:notEmpty name="assignedReferralsForm" property="petitions">			
				    								<logic:iterate indexId="index" id="petitions" name="assignedReferralsForm" property="petitions">
				      								<bean:define id="petitionNum" name="petitions" property="petitionNum" type="java.lang.String"/>
				      								<% StringBuffer url = new StringBuffer();
				      									url.append(naming.PDJuvenileCaseConstants.PETITIONNUM_PARAM + "=" + petitionNum);
				      									String queryUrl = url.toString();							
				      								%>									
					    								<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
				      									<td valign="top">
				      									<jims2:isAllowed requiredFeatures='<%=Features.JCW_REFERRALVIEW_DALOG_PETITION%>'>
				      										<a href="/<msp:webapp/>displayJuvenileProfilePetitionDetails.do?submitAction=View&jotChargesDisplayOnly=false&notDetailed=false&page=profile&<%out.print(queryUrl);%>"><bean:write name="petitions" property="petitionNum"/></a>
				      									</jims2:isAllowed>
														<jims2:isAllowed requiredFeatures='<%=Features.JCW_REFERRALVIEW_DALOG_PETITION%>' value="false">
															<bean:write name="petitions" property="petitionNum"/>
														</jims2:isAllowed>
				      									</td>
				      									<td valign="top"><bean:write name="petitions" property="amend"/></td>	
				      									<td valign="top"><bean:write name="petitions" property="filedAmendDate" format="MM/dd/yyyy"/></td>
				      									<td valign="top"><bean:write name="petitions" property="levelDegree"/></td>	
				      									<td valign="top"><bean:write name="petitions" property="penalCategory"/></td>										
				      									<td valign="top"><bean:write name="petitions" property="offense"/></td>																
				      								</tr>
				    								</logic:iterate>
				  								</logic:notEmpty>
				
				  							</table>
											</td>
										</tr>
									</table>
									<%-- petitions end--%> 
				
									<%-- BEGIN BUTTON TABLE --%>
									<div class='spacer'></div>
									<table border="0" width="100%" align="center">
									  <tr>
				  					  <td align="center">
				                <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="goNav('back')">
				                  <bean:message key="button.back"></bean:message>
				                </html:button>
				                <input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileMasterInformation.do')">

				  					 	</td>
									  </tr>
									</table>
									<%-- END BUTTON TABLE --%>
									<div class='spacer'></div>
								</td>
							</tr>
						</table>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- DETAIL TABLE --%>

<div class='spacer'></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</html:form> 
</body>

</html:html>
