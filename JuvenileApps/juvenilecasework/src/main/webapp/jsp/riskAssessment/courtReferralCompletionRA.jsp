<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%--MODIFICATIONS --%>
<%-- 09/20/2005		DWilliamson	Create Interview RA jsp--%>
<%-- 07/20/2009     CShimek    	#61004 added timeout.js  --%>
<%-- 05/31/2011		DGibler		#JIMS200058178 Risk: CLM modification of Completed Assessments --%>
<%-- 05/01/2012		CShimek		#73346 Revise hardcoded TJPC prompts to TJJD --%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="ui.juvenilecase.form.riskanalysis.RiskAssessmentCourtReferralForm" %>
<%@ page import="java.awt.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="messaging.juvenilecase.reply.RiskQuestionResponseEvent" %>



<head>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- courtReferralCompletionRA.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/riskAnalysis.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>


</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/displayCourtReferralCompletionSummary"> 

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|203">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
    	<td align="center" class="header">
	     	<bean:message key="title.juvenileCasework"/> - Risk Assessment TJJD Risk Completion	
	    	<logic:equal name="riskAnalysisForm" property="mode" value="update">
				Update 
			</logic:equal>
    	</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
    	<td>
			<ul>
        		<li>Complete <strong>all</strong> TJJD Risk completion questions and then select Next button to view summary.</li>
        		<li>Select Back button to return to previous page.</li>
      		</ul>
		</td>
  	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<%-- hyperlink table for access to viewing traits --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    	<td valign='top'>
      		<table align="center" cellpadding='1' cellspacing='0' border='0' width='98%'>
		        <tr>
		          <td align='center' class='detailHead'>
		      	    <a href='javascript:openCustomRestrictiveWindow("/<msp:webapp/>displayJuvenileCasefileTraitsSearch.do?juvenileNum=<bean:write name='juvenileCasefileForm' property='juvenileNum'/>&casefileId=<bean:write name='juvenileCasefileForm' property='supervisionNum'/>&supervisionNum=<bean:write name='juvenileCasefileForm' property='supervisionNum'/>&submitAction=Find", 600, 700);'>View Traits&nbsp;&nbsp;(in new window)</a>
		      		</td>
		        </tr>
      		</table>

			<div class='spacer'></div>
	    	<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	        	<tiles:put name="tabid" value="casefiledetailstab"/>
	        	<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	      	</tiles:insert>				
	
			<%-- BEGIN DETAIL TABLE --%>
  			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  				<tr>
  					<td valign='top' align='center'>
  			  
    					<%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
  						<div class='spacer'></div>
	  			  		<table width='98%' border="0" cellpadding="0" cellspacing="0">
  							<tr>
  								<td>
	  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
	  									<tr>
	  										<td valign='top'>
	    										<%--tabs start--%>
	  											<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
	  												<tiles:put name="tabid" value="riskAnalysis"/>
	   												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	  											</tiles:insert>	
	    										<%--tabs end--%>
	  										</td>
	  									</tr>
	  									<tr>
										  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
	  								  	</tr>
							  	  	</table>

	  								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
	  									<tr>
	  										<td valign='top' align='center'>

						                       
						                        <div class='spacer'></div>
                  								<table align="center" width='98%' border="0" cellpadding="1" cellspacing="1" class="borderTableBlue">
	                       							<tr>
	                   									<td class="detailHead">
	                   										TJJD Risk Completion
	                   									</td>
	                     							</tr>
	                     							<tr>
								                    	<td align='center'>
								                    		<table width='100%' cellpadding='4' cellspacing='1'>
																<tr>
					            									<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'>
					            										<!--  JJS Court Decision -->
					            										JJS Disposition  
					            									</td>
					            									<td valign='top' class='formDe' colspan="3">
					            										<bean:write name="riskCourtReferralForm" property="jjsCourtDecision"/>
					            										<html:hidden name="riskCourtReferralForm" property="jjsCourtDecision"/>
					            										
					            										<!-- Used to Find out if disposition is a final disposition -->
					            										<html:hidden name="riskCourtReferralForm" property="jjsCourtDisposition"/>
					            									</td>
					            								</tr>
                        									                                
                      											<tr>
					            									<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'>
					            										Collateral Visits 
					            									</td>
					            									<td valign='top' class='formDe' colspan="3">
					            										<html:text name="riskCourtReferralForm" property="collateralVisits" styleId="cVisits" maxlength="5"/>
					            									</td>
					            								</tr>
					            								
					            								<tr>
					            									<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'>
					            										Face To Face Visits 
					            									</td>
					            									<td valign='top' class='formDe' colspan="3">
					            										<html:text name="riskCourtReferralForm" property="face2FaceVisits" styleId="f2fVisits" maxlength="5"/>
					            									</td>
					            								</tr>
					            								
					            								<tr>
					            									<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'>
					            										Court Disposition TJJD 
					            									</td>
					            									<td valign='top' class='formDe' colspan="3">
					            										<html:select name="riskCourtReferralForm" property="courtDispositionTJPC" size="1" styleId="courtDispositionTJPC">
																	    	<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																	  		<jims2:codetable codeTableName='TJPC_COURT_DISPOSITIONS'></jims2:codetable>
																		</html:select>
																		
					            									</td>
					            								</tr>
		                                					</table>
	                   									</td>
	                 								</tr>
	                       						</table>
                        						

							                  	<%-- BEGIN BUTTON TABLE --%>
							                  	<div class='spacer'></div>
							                  	<table border="0" width="100%">
							                    	<tr>
								                      	<td align="center">
								                  	    	<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
								                  	    	<html:submit property="submitAction" onclick="return validateCompletionFields(this.form)"><bean:message key="button.next" /></html:submit>
								                  	    	<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
								                      	</td>
							                    	</tr>
							                  	</table>
							                  	<%-- END BUTTON TABLE --%>
			                				</td>
			              				</tr>
			            			</table>
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

</html:form>

<div class='spacer'></div>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
