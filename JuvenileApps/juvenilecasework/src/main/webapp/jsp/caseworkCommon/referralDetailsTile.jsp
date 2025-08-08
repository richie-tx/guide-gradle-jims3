<!DOCTYPE HTML>

<%-- ************** NOTES ***************************
    Used to display casefile petition details
    06/10/2005 glyons  Create JSP 
**************************************************--%>
<%-- 06/29/2007	Uma Gopinath Modify JSP --%>
<%-- 08/24/2015 RCapestani #29441 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Juvenile Profile Referrals) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>


<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- referralDetailsTile.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<tiles:importAttribute name="assignedReferralsForm"/>
<tiles:useAttribute id="mode" name="mode" classname="java.lang.String" ignore="true" />

<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
	<tr>
		<td valign='top' align='center'>
			
			<div class='spacer'></div>
			<table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue">
				<tr>
					<td class='detailHead'>Referral Details - Referral #: <bean:write name="assignedReferralsForm" property="referralNum"/></td>
				</tr>
				<tr>
					<td valign='top' align='center'>
						<table cellpadding="4" cellspacing="1" width='100%' border='0'>
							<tr>
								<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.transaction"/> <bean:message key="prompt.#"/></td>
								<td class='formDe'><bean:write name="assignedReferralsForm" property="transactionNum"/></td>
								<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.daLog#"/></td>
								<td class='formDe'>
								<jims2:isAllowed requiredFeatures='<%=Features.JCW_REFERRALVIEW_DALOG_PETITION%>'>
									<a href="/<msp:webapp/>displayJuvenileCasefilePetitionDetails.do?submitAction=View&jotChargesDisplayOnly=true&page=casefile&notDetailed=false"><bean:write name="assignedReferralsForm" property="daLogNum"/></a>
								</jims2:isAllowed>
								<jims2:isAllowed requiredFeatures='<%=Features.JCW_REFERRALVIEW_DALOG_PETITION%>' value="false">
									<bean:write name="assignedReferralsForm" property="daLogNum"/>
								</jims2:isAllowed>
								</td>
							</tr>
							<tr>
								<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.dastatus"/></td>
								<td class='formDe' width='50%'><bean:write name="assignedReferralsForm" property="daStatus"/></td>
								<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.dadateOut"/></td>
								<td class='formDe' width='50%'><bean:write name="assignedReferralsForm" property="DAdateOut" formatKey="date.format.mmddyyyy" /></td>
							</tr>
							<tr>
								<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.court"/> <bean:message key="prompt.id"/></td>
								<td class='formDe' width='50%'><bean:write name="assignedReferralsForm" property="courtId"/></td>
								<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.court"/> <bean:message key="prompt.date"/></td>
								<td class='formDe' width='50%'><bean:write name="assignedReferralsForm" property="courtDate"/></td>
							</tr>
							<tr>
								<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.court"/> <bean:message key="prompt.result"/></td>
								<td class='formDe' colspan='4'><bean:write name="assignedReferralsForm" property="courtResultDesc"/></td>
							</tr>
							<tr>									
								<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.court"/> <bean:message key="prompt.disposition"/></td>
								<td class='formDe' colspan='4'><bean:write name="assignedReferralsForm" property="courtDispositionDesc"/></td>
							</tr>
							<tr>
								<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.intake"/> <bean:message key="prompt.date"/></td>
								<td class='formDe'><bean:write name="assignedReferralsForm" property="intakeDate"/></td>
								<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.intake"/> <bean:message key="prompt.decision"/></td>
								<td class='formDe'><bean:write name="assignedReferralsForm" property="intakeDecision"/></td>
							</tr>
							<tr>
								<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.closed"/> <bean:message key="prompt.date"/></td>
								<td class='formDe' colspan='4'><bean:write name="assignedReferralsForm" property="closeDate"/></td>
							</tr>
							<tr>
								<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.referralDate"/></td>
								<td class='formDe'><bean:write name="assignedReferralsForm" property="referralDate" formatKey="date.format.mmddyyyy" /></td>
								<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.referral"/></td>
								<td class='formDe'><bean:write name="assignedReferralsForm" property="referralNum"/></td>
							</tr>

							<tr>
								<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.supervisionBeginDate" /></td>
								<td class='formDe'><bean:write name="assignedReferralsForm" property="supervisionBeginDate" formatKey="date.format.mmddyyyy" /></td>
								<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.supervisionEndDate" /></td>
								<td class='formDe'><bean:write name="assignedReferralsForm" property="supervisionEndDate" formatKey="date.format.mmddyyyy" /></td>
							</tr>
							<tr>
								<td class='formDeLabel' nowrap='nowrap'>Type of Case</td>
								<td class='formDe' colspan='3'><bean:write name="assignedReferralsForm" property="caseType" /></td>
							</tr>

							<tr>
								<td class='formDeLabel' colspan='4'><bean:message key="prompt.levelOfCare"/></td>										
							</tr>
							<tr>
								<td class='formDe' colspan='4'><bean:write name="assignedReferralsForm" property="levelOfCare"/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<%--offenses start--%>
			<div class='spacer'></div>
			<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr>
					<td class='detailHead'>Offenses</td>
				</tr>
				<tr>
					<td>
					<table cellpadding='2' cellspacing='1' width='100%' border="0">
						<tr bgcolor='#cccccc'>
							<td align='left' class='subHead'><bean:message key="prompt.investigation"/> <bean:message key="prompt.#"/></td>									
							<td align='left' class='subHead'><bean:message key="prompt.offense"/> <bean:message key="prompt.date"/></td>
							<td align='left' class='subHead'><bean:message key="prompt.offense"/> <bean:message key="prompt.level"/></td>
							<td align='left' class='subHead'><bean:message key="prompt.citation"/> <bean:message key="prompt.source"/></td>
							<td align='left' class='subHead'><bean:message key="prompt.citation"/> </td>
							<td align='left' class='subHead'><bean:message key="prompt.description"/></td>
							<td align='left' class='subHead'><bean:message key="prompt.seq"/> <bean:message key="prompt.#"/></td>
						</tr>

						<logic:empty name="assignedReferralsForm" property="offenses">
  						<tr>
  							<td colspan="2">No offenses exist for this referral.</td>
  						</tr>
						</logic:empty>								

						<logic:notEmpty name="assignedReferralsForm" property="offenses">
  						<logic:iterate indexId="index" id="offenses" name="assignedReferralsForm" property="offenses"> 								
								<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
									<td valign='top'><bean:write name="offenses" property="investigationNum"/></td>
									<td valign='top'><bean:write name="offenses" property="offenseDateFormatted"/></td>
									<td valign='top'><bean:write name="offenses" property="catagory"/></td>	
									<td valign='top'><bean:write name="offenses" property="citationSource"/></td>	
									<td valign='top'><bean:write name="offenses" property="citationCode"/></td>									
									<td valign='top'><bean:write name="offenses" property="offenseDescription"/></td>	
									<td valign='top'><bean:write name="offenses" property="sequenceNum"/></td>
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
					<td class='detailHead'>Petitions</td>
				</tr>
				<tr>
					<td>
					<table width='100%' cellpadding='4' cellspacing='1' border='0'>						
						<tr bgcolor='#cccccc'>
							<td align='left' class='formDeLabel' align="left"><bean:message key="prompt.petition"/> <bean:message key="prompt.#"/></td>
							<td align='left' class='formDeLabel'>Amendment #</td>
							<td align='left' class='formDeLabel'>Filed/Amend Date</td>
							<td align='left' class='formDeLabel'><bean:message key="prompt.level"/> <bean:message key="prompt.degree"/></td>
							<td align='left' class='formDeLabel'><bean:message key="prompt.penal"/> <bean:message key="prompt.category"/></td>
							<td align='left' class='formDeLabel'><bean:message key="prompt.allegation"/></td>
						</tr>				

						<logic:empty name="assignedReferralsForm" property="petitions">			
  						<tr class='normalRow'>
  							<td colspan="3" valign='top'>No petitions exist for this referral.</td>																		
  						</tr>								
						</logic:empty> 							

						<logic:notEmpty name="assignedReferralsForm" property="petitions">			
							<logic:iterate indexId="index" id="petitions" name="assignedReferralsForm" property="petitions">
 								<bean:define id="petitionNum" name="petitions" property="petitionNum" type="java.lang.String"/>
								<% 
									StringBuffer url = new StringBuffer();
									url.append(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM + "=");
									url.append(request.getParameter(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM));
									url.append("&" + naming.PDJuvenileCaseConstants.PETITIONNUM_PARAM + "=" + petitionNum);
									String queryUrl = url.toString();							
								%>									
								<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
									<td valign='top'>
									<jims2:isAllowed requiredFeatures='<%=Features.JCW_REFERRALVIEW_DALOG_PETITION%>'>
										<a href="/<msp:webapp/>displayJuvenileCasefilePetitionDetails.do?submitAction=<bean:message key="button.view"/>&page=casefile&notDetailed=false&jotChargesDisplayOnly=false&<%out.print(queryUrl);%>"><bean:write name="petitions" property="petitionNum"/></a>
									</jims2:isAllowed>
									<jims2:isAllowed requiredFeatures='<%=Features.JCW_REFERRALVIEW_DALOG_PETITION%>' value="false">
										<bean:write name="petitions" property="petitionNum"/>
									</jims2:isAllowed>
									</td>
									<td valign='top'><bean:write name="petitions" property="amend"/></td>	
									<td valign='top'><bean:write name="petitions" property="filedAmendDate" format="MM/dd/yyyy"/></td>
									<td valign='top'><bean:write name="petitions" property="levelDegree"/></td>	
									<td valign='top'><bean:write name="petitions" property="penalCategory"/></td>										
									<td valign='top'><bean:write name="petitions" property="offense"/></td>																		
								</tr>
							</logic:iterate>
						</logic:notEmpty>

					</table>

					</td>
				</tr>
			</table>
			<%-- petitions end--%>

     	<%-- BUTTON TABLE --%>
     	<logic:notPresent name="mode">
   			<div class='spacer'></div>
   			<table border="0" cellpadding='1' cellspacing='1' align='center'>						
   				<tr>						
         		<td align="center"><input type='button' value='Back' onclick="goNav('back')">
         			<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')">
         		</td>				      	
   				</tr>					
   			</table>
	     	<div class='spacer'></div>
     	</logic:notPresent>
     	<%-- BUTTON TABLE END --%>
		</td>
	</tr>
</table>
