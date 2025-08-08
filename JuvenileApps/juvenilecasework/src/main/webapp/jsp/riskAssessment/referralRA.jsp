<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 09/13/2005		DWilliamson	Create Risk Analysis jsp--%>
<%-- 12/16/2005		UGopinath  Added Validations for Radio buttons--%>
<%-- 07/20/2009     CShimek    #61004 added timeout.js  --%>
<%-- 05/04/2011		DGibler	   #69838 added CLM Update --%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>



<head>
<msp:nocache/>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/facility.js"></script>

<html:base />
<title>juvenilecasework/riskAssessment/referralRA.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/riskAnalysis.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type="text/javascript">
$(function() { 
	$("input[id*='formElementDate']").each(function(){	 
		datePickerSingle($(this),'Entered Date',false);		      
	});
 });
 
 function nextBtn(thisForm) {
	  var isValid = validateFields(thisForm);
	  if ( isValid != false ) {
		  if ( confirm("Content displayed inside of brackets '[ ]' will not show on PCS print. Please modify character use if print details are required.") ) {
			  return true;
		  } else {
			  return false;
		  }
	  } else {
		  return false;
	  }
	  
		 
	 
 }

 </script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayReferralSummary">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|22">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.riskAssessmentPreCourtStaffingInformation"/>
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
        <li>Complete <strong>all</strong> interview questions and then select Next button to view summary.</li>
        <li>Select Back button to return to previous page. </li>
		  </ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<table align="center" cellpadding='1' cellspacing='0' border='0' width='98%'>
	<tr>
    <td>   
     <tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		   <tiles:put name="headerType" value="casefileheader"/>
     </tiles:insert>
    </td>   
	</tr>
</table>
<%-- END HEADER INFO TABLE --%>

<%-- hyperlink table for access to viewing traits --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    	<td valign='top'>
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
                      							<table align="center" cellpadding='1' cellspacing='0' border='0' width='98%'>
                        							<tr>
                          								<td align='center' class='detailHead'>
                      	    								<a href='javascript:openCustomRestrictiveWindow("/<msp:webapp/>displayJuvenileCasefileTraitsSearch.do?juvenileNum=<bean:write name='juvenileCasefileForm' property='juvenileNum'/>&casefileId=<bean:write name='juvenileCasefileForm' property='supervisionNum'/>&supervisionNum=<bean:write name='juvenileCasefileForm' property='supervisionNum'/>&submitAction=Find", 600, 700);'>View Traits&nbsp;&nbsp;(in new window)</a>
                      									</td>
                        							</tr>
                      							</table>

		                    					<%-- Begin TABLE --%>
                    							<div class='spacer'></div>
                     
	                  							<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	                  								<tr>
                      									<td class="detailHead" colspan="2">
                      										Pre-Court Staffing(PCS) 
                      									</td>
                      								</tr>
	                    							<tr>
								                    	<td align="center">
                        									<table width="100%" cellpadding="4" cellspacing="1">
                        										<tr>
	                         										<td class='formDeLabel'>
	                         											<bean:message key="prompt.dateTaken"/>
	                         										</td>
	                         										<td class='formDe'>
	                         											<nested:write  name="riskAnalysisForm" property="riskAssessmentDate" formatKey="date.format.mmddyyyy"/>
	                         										</td>
                             									</tr>
                        									                                
                      											<tiles:insert page="../caseworkCommon/riskAnalysisQuestionAnswers.jsp" flush="false">
																	<tiles:put name="tilesAFormName" value="riskAnalysisForm"/>
																	<tiles:put name="tilesImageLevel" value=""/>
																</tiles:insert>
                    											<logic:equal name="riskAnalysisForm" property="mode" value="update">
													 				<tr id='modificationReasonRow' class=''>
				        												<td colspan='2'>
														          			<table align="center" width='100%' border="0" cellpadding="4" cellspacing="1" class="borderTableGrey">
																					<tr>
						                 											<td class='formDeLabel' colspan='2'>Reason for modification&nbsp;
						                 												<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
								                  											<tiles:put name="tTextField" value="modReason"/>
								                   											<tiles:put name="tSpellCount" value="spellBtn1" />
						                												</tiles:insert>
						                												(Max. characters allowed: 550)
					                												</td>
						                 											</tr>
							                 										<tr>
							                 											<td class='formDe' colspan='2'><html:textarea name="riskAnalysisForm" property="modReason" rows='3' style="width:100%" onmouseout="textCounter(this,550)" onkeydown="textCounter(this,550)" styleId="reasonMod"></html:textarea></td>
							  	              										</tr>
					      	          										</table>
					          	      									</td>
				        											</tr>
																</logic:equal>
                       										
                       										</table>
                    									</td>
                  									</tr>
                  								</table>
                      							<%-- End TABLE --%>

                  								<%-- BEGIN BUTTON TABLE --%>
                  								<div class='spacer'></div>
                  								<table border="0" width="100%">
                    								<tr>
                      									<td align="center">
                        									<html:submit property="submitAction">
                        										<bean:message key="button.back"></bean:message>
                        									</html:submit>
                        									
                        									<html:submit property="submitAction" onclick="return (nextBtn(this.form))">
                        										<bean:message  key="button.next"></bean:message>
                        									</html:submit>
                          										&nbsp;
                        									<html:submit property="submitAction">
                        										<bean:message key="button.cancel" />
                        									</html:submit>
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