<!DOCTYPE HTML> 
<%-- 11/09/2005	 Aswin Widjaja - Create JSP --%>
<%-- 12/14/2006 Hien Rodriguez  ER#37077 Add Tab & Button security features --%>
<%-- 07/17/2009 C Shimek        #61004 added timeout.js  --%>
<%-- 08/25/2015 RCapestani #29429 MJCW:  Adapt MJCW and Warrants to IE9, IE11 and Chrome (Benefits Assessment UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>

<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>
<title>Juvenile Casework - Benefits Assessment - benefitsAssessmentSummary.htm</title>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/benefits.js"></script>
<html:base />

<!-- <script type='text/javascript'>
function changeFormAction(myForm, URL, doSubmit)
{
	myForm.action = URL;
	if(doSubmit)
	{
		myForm.submit();
	}
	return true;
}

function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
	{
  	alert("Your input has been truncated to "  +maxlen +" characters!");
	}

	if (field.value.length > maxlen)
	{
  	field.value = field.value.substring(0, maxlen);
	}
} 
</script> -->
 
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/submitProcessBenefitsAssessment.do" target="content">


<logic:equal name="processBenefitsAssessmentForm" property="view" value="false">
	<logic:equal name="pageType" value="summary">
	    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|5">
	</logic:equal>    
	<logic:equal name="pageType" value="confirm">
	    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|2">
	</logic:equal>    
</logic:equal>
<logic:equal name="processBenefitsAssessmentForm" property="view" value="true">
	<logic:equal name="processBenefitsAssessmentForm" property="review" value="false">
	    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|8">
    </logic:equal>
	<logic:equal name="processBenefitsAssessmentForm" property="review" value="true">
		<logic:equal name="pageType" value="review">
		    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|9">
		</logic:equal>    
		<logic:equal name="pageType" value="summary">
		    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|11">
		</logic:equal>    
		<logic:equal name="pageType" value="confirm">
		    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|10">
		</logic:equal>    
	</logic:equal>
</logic:equal>

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" >Juvenile Casework - Benefits Assessment			
			<logic:equal name="processBenefitsAssessmentForm" property="view" value="false">
				<logic:equal name="pageType" value="summary"> Summary</logic:equal>
				<logic:equal name="pageType" value="confirm"> Confirmation</logic:equal>
			</logic:equal>

			<logic:equal name="processBenefitsAssessmentForm" property="view" value="true">
				<logic:equal name="processBenefitsAssessmentForm" property="review" value="false"> Details</logic:equal>

				<logic:equal name="processBenefitsAssessmentForm" property="review" value="true">
					<logic:equal name="pageType" value="review"> Review</logic:equal>
					<logic:equal name="pageType" value="summary"> Review Summary</logic:equal>
					<logic:equal name="pageType" value="confirm"> Review Confirmation</logic:equal>
				</logic:equal>
			</logic:equal>
		
		</td>
	</tr>
	<tr>
		<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
	</tr>
	
	<%-- very very rushed implementation, please refactor later--%>
	<logic:equal name="processBenefitsAssessmentForm" property="view" value="false">
		<logic:equal name="pageType" value="confirm">
			<tr>
				<td class="confirm" align="center">Benefits Assessment Created and Notification sent to JPO.</td>
			</tr>
		</logic:equal>
	</logic:equal>
	
	<logic:equal name="processBenefitsAssessmentForm" property="view" value="true">
		<logic:equal name="processBenefitsAssessmentForm" property="review" value="true">
			<logic:equal name="pageType" value="confirm">
				<tr>
					<td class="confirm" align="center">Benefits Assessment has been reviewed.</td>
				</tr>
			</logic:equal>
		</logic:equal>
	</logic:equal>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class="spacer"></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select Finish to complete the Benefits Assessment or Back to make changes.</li>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>

<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top">

<jims2:if name="processBenefitsAssessmentForm" property="view" op="equal" value="true">
  <jims2:or name="processBenefitsAssessmentForm" property="review" op="equal" value="true" />
  	<jims2:then>
						
       		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
      			<tr>
      				<td valign="top">
      					<tiles:insert page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
      						<tiles:put name="tabid" value="casefiledetailstab"/>
      						<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
      					</tiles:insert>				
      				</td>
      			</tr>
      			<tr>
      		  	<td bgcolor="#6699FF"><img src="../../images/spacer.gif" width="5"></td>
      			</tr>
      		</table><%-- END CASEFILE TABS TABLE --%>	
    	
    			 <%-- BEGIN DETAIL TABLE --%>
      		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
      			<tr>
      			  <td valign="top" align="center">

        			  <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
								<div class="spacer"></div>
    	  			  <table width='98%' border="0" cellpadding="0" cellspacing="0">
      						<tr>
      							<td>
      								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
      									<tr>
      										<td valign="top">
        										<%--tabs start--%>
      											<tiles:insert page="/jsp/caseworkCommon/casefileInfoTabs.jsp" flush="true">
      												<tiles:put name="tabid" value="benefitsAssess"/>
       												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
      											</tiles:insert>	
        										<%--tabs end--%>
      										</td>
      									</tr>
      									<tr>
    									  	<td bgcolor="#33cc66"><img src="../../images/spacer.gif" width="5"></td>
    								    </tr>
    							  	</table>

      								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
      									<tr>
      										<td valign="top" align="center">
		</jims2:then>
</jims2:if>
					 			 					<div class="spacer"></div>
                        <table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
                        	<tr>
                        		<td valign="top">
                           		<%-- Begin Detail Table --%>
                        			<table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" class="borderTableBlue">
                  							<tr>
                  								<td valign="top" class="detailHead" align="left">
                  									<table width="100%" cellpadding="0" cellspacing="0">
                  										<tr>
                  											<td width="1%">
                  												<a href="javascript:showHideMulti('Guardian','pMore',4, '/<msp:webapp/>')" border="0">
                    												<img border="0" 
                    													<logic:equal name="processBenefitsAssessmentForm" property="view" value="true"> src="/<msp:webapp/>images/expand.gif"</logic:equal>
                    													<logic:notEqual name="processBenefitsAssessmentForm" property="view" value="true"> src="/<msp:webapp/>images/contract.gif" </logic:notEqual> name="Guardian">
																					</a>
                  											</td>
                  											<td class="detailHead" align="left">&nbsp;Guardian Name <bean:write name="processBenefitsAssessmentForm" property="currentAssessment.guardian.name.formattedName"/></td>
                  										</tr>
                  									</table>
                  								</td>
                  							</tr>
							
                  							<tr>
            											<td>			
              											<span id='pMore3'
                  										<logic:equal name="processBenefitsAssessmentForm" property="view" value="true"> class="hidden" </logic:equal>
                  										<logic:notEqual name="processBenefitsAssessmentForm" property="view" value="true"> class="visible" </logic:notEqual>
                  									>
																		  <div class="spacer"></div>
              												<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                      									<tr>
                     											<td valign="top">	
                        										<tiles:insert page="/jsp/caseworkCommon/employmentSummary.jsp" flush="false">
                                              <tiles:put name="employmentInfoList"  beanName="processBenefitsAssessmentForm" beanProperty="employmentInfoList"/>	
                                              <tiles:put name="rowIdHeader" value="benAsses0"/>
                                            </tiles:insert>
             												      </td>
             										        </tr>
                          						</table>      
                        						</span>                     
                    						  </td>
                  						  </tr>
							
                  							<tr>
                  								<td>
                  									<%-- BEGIN Family Financial Info TABLE --%>
                  									<span id='pMore2' <logic:equal name="processBenefitsAssessmentForm" property="view" value="true">	class="hidden" </logic:equal>
                  										<logic:notEqual name="processBenefitsAssessmentForm" property="view" value="true"> class="visible" </logic:notEqual>
                  									>
																		  <div class="spacer"></div>
                  										<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" class="borderTableBlue">
                  											<tr>
                  												<td valign="top" class="detailHead" align="left">
                  													<table width="98" cellpadding="0" cellspacing="0">
                  														<tr>
                  															<td width="1%">
                  																<a href="javascript:showHideMulti('Characteristics','pChar',1, '/<msp:webapp/>')" border="0">
                  																	<img border="0" src="/<msp:webapp/>images/expand.gif" name="Characteristics">
                  																</a>
                  															</td>
                  															<td class="detailHead" nowrap="nowrap" align="left">&nbsp;Family Financial Information</td>
                  														</tr>
                  													</table>
                  												</td>
                  											</tr>

                  											<tr id="pChar0" class="hidden">
                  												<td valign="top">
                  													<tiles:insert page="/jsp/caseworkCommon/familyFinancialInfo.jsp" flush="true">
                  														<tiles:put name="familyFinancialInfo" beanName="processBenefitsAssessmentForm" beanProperty="guardianFinancialInfo" />	
                  													</tiles:insert>
                  												</td>
                  											</tr>
                  										</table>
                  										<%-- END Family Financial Info TABLE --%>
		
                  										<%-- Begin Title IV-e Assessment TABLE --%>
                  										<div class="spacer"></div>
                  										<span id='pMore0' 
															<logic:equal name="processBenefitsAssessmentForm" property="view" value="true">	class="hidden" </logic:equal>
    															<logic:notEqual name="processBenefitsAssessmentForm" property="view" value="true"> class="visible" </logic:notEqual>
  															>		
                  											<tiles:insert page="/jsp/benefitsAssessment/titleIVEPlacementScreening.jsp" flush="true">
                  												<tiles:put name="titleIVEQuestion" beanName="processBenefitsAssessmentForm" beanProperty="currentAssessment.question" />	
                  											</tiles:insert>
                  										</span>
									
                    									<span id='pMore1' 
                    										<logic:equal name="processBenefitsAssessmentForm" property="view" value="true">	class="hidden" </logic:equal>
                    										<logic:notEqual name="processBenefitsAssessmentForm" property="view" value="true">	class="visible" </logic:notEqual>
                    										>
                    											<table cellpadding="2" cellspacing="1" width="98%" align='center' class="borderTableGrey" border="0">
                    												<tr>
                    													<td bgcolor="999999" align="left"><strong>IV-e Eligibility (To be completed by JPD Staff ONLY)</strong></td>
                    												</tr>
                    												<tr>
                    													<td align="center">
                    														<table cellpadding="4" cellspacing="1" width="100%" border="0">
                    															<tr>
                    																<td width="1%">7a.</td>
                    																<td class="formDeLabel">Does the initial order of removal contain the "Best Interest" language?</td>
                    																<td class="formDe" nowrap="nowrap" width="16%" colspan="2">
                    																	<bean:define id="doesInitialOrderRemovalContainBestInterest" name="processBenefitsAssessmentForm" 
                    																		property="currentAssessment.initialOrderRemovalContainBestInterest" type="java.lang.Boolean" scope="session"/> 
                    																	<%out.println(UIUtil.getYesNo(doesInitialOrderRemovalContainBestInterest.booleanValue(), false));%>
                    																</td>
                    															</tr>
                    															<tr>
                    																<td>7b.</td>
                    																<td class="formDeLabel">Was the "Reasonable Efforts" language made within 60 days of the child's removal?</td>
                    																<td class="formDe" nowrap="nowrap" colspan="2">
                    																	<bean:define id="wasReasonableEffortsMadeWithin60DaysOfChildRemoval" name="processBenefitsAssessmentForm" 
                    																		property="currentAssessment.reasonableEffortsMadeWithin60DaysOfChildRemoval" type="java.lang.Boolean" scope="session"/> 
                    																	<%out.println(UIUtil.getYesNo(wasReasonableEffortsMadeWithin60DaysOfChildRemoval.booleanValue(), false));%>
                    																</td>
                    															</tr>
                    															<tr>
                    																<td>7c.</td>
                    																<td class="formDeLabel">Do court orders include the finding of "responsibility for the child's care and placement?</td>
                    																<td class="formDe" colspan="2">
                    																	<bean:define id="doesCourtOrderIncludeFindingChildCareAndPlacement" name="processBenefitsAssessmentForm" 
                    																		property="currentAssessment.courtOrderIncludeFindingChildCareAndPlacement" type="java.lang.Boolean" scope="session"/> 
                    																	<%out.println(UIUtil.getYesNo(doesCourtOrderIncludeFindingChildCareAndPlacement.booleanValue(), false));%>	
                    																</td>
                    															</tr>
                    															<tr>
                    																<td>8.</td>
                    																<td class="formDeLabel">Does this child meet the AFDC requirements and did the court orders contain the appropriate language?</td>
                    																<td class="formDe" colspan="2">
                    																	<bean:define id="doesChildMeetAFDCRequirement" name="processBenefitsAssessmentForm" 
                    																		property="currentAssessment.childMeetAFDCRequirement" type="java.lang.Boolean" scope="session"/> 
                    																	<%out.println(UIUtil.getYesNo(doesChildMeetAFDCRequirement.booleanValue(), false));%>	
                    																</td>
                    															</tr>
                    															<tr>
                    																<td>&nbsp;</td>
                    																<td>
                    																	<table width="100%" class="borderTableGrey">
                    																		<tr>
                    																			<td class="formDeLabel" width='1%' nowrap="nowrap">Title IV-E Officer</td>
                    																			<td class="formDe"><bean:write name="processBenefitsAssessmentForm" property="titleIVeOfficerName.formattedName"/></td>
                    																		</tr>
                    																	</table>
                    																</td>
                    															</tr>
                    														</table>
                    													</td>
                    												</tr>
                    											</table>
																<div class="spacer"></div>
                    									  </span>
                    									</span>
                    									<%-- END Title IV-e Assessment TABLE --%>
								
                          						<%-- BEGIN FAMILY MEMBER BENEFITS TABLE --%>
                          						<div class="spacer"></div>
                          						<div id='summaryOneTable' class='hidden'>					
                          							<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" align="center">
                          								<tr>
                          									<td colspan="4" class="detailHead" align="left">Benefits Assessments</td>
                          								</tr>			
                          								<tr>
                          									<td align="center">
                          										<table cellpadding="4" cellspacing="1" width="100%" align="center">
                          											<tr>
                          												<td class="formDeLabel" nowrap="nowrap">Eligibility Type</td>
                          												<td class="formDeLabel">Eligible</td>
                          												<td class="formDeLabel">Receiving</td>
                          											</tr>
                          											<tr>
                          												<td class="formDe">Medicaid</td>
                          												<td class="formDe"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.eligibleForMedicaidDisp"/></td>
                          												<td class="formDe"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.receivingMedicaidDisp"/></td>
                          											</tr>
                          											<tr>
                          												<td class="formDe">Title IV-e Candidacy</td>
                          												<td class="formDe"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.eligibleForTitleIVeDisplay"/></td>
                          												<td class="formDe"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.receivingTitleIVeDisp"/></td>
                          											</tr>
                          										</table>
                          									</td>
                          								</tr>
                          							</table>
                          						</div>
                          					
                          						<div id='summaryViewTable' >					
                          							<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" align="center">
                          								<tr>
                          									<td colspan="4" class="detailHead" align="left">Benefits Assessments</td>
                          								</tr>
                          								<tr>
                          									<td>
                          										<table cellpadding="2" cellspacing="0" width="100%">
                          											<tr class="formDeLabel">
                          												<td valign="top"><bean:message key="prompt.entryDate"/></td>
                          												<td valign="top"><bean:message key="prompt.name"/></td>
                          												<td valign="top"><bean:message key="prompt.eligibilityType"/></td>
                          												<td valign="top"><bean:message key="prompt.eligible"/></td>
                          												<td valign="top"><bean:message key="prompt.receiving"/></td>
                          											</tr>
                          											<tr bgcolor='#ffffff'>
                          												<td align='left' valign="top"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.question.dateTaken" formatKey="date.format.mmddyyyy"/></td>
                          												<td align='left' valign="top"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.guardian.name.formattedName"/></td>
                          												<td align='left' valign="top"><bean:message key="prompt.medicaid"/></td>
                          												<td align='left' valign="top"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.eligibleForMedicaidDisp"/></td>
                          												<td align='left' valign="top"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.receivingMedicaidDisp"/></td>
                          											</tr>
                          											<tr bgcolor='#ffffff'>
                          												<td valign="top"></td>
                          												<td valign="top"></td>
                          												<td align='left' valign="top"><bean:message key="prompt.titleIVECandidacy"/></td>
                          												<td align='left' valign="top"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.eligibleForTitleIVeDisplay"/></td>
                          												<td align='left' valign="top"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.receivingTitleIVeDisp"/></td>
                          											</tr>
                          										</table>
                          									</td>
                          								</tr>
                          						  </table>
                          						</div>
                          						<%-- END FAMILY MEMBER BENEFITS TABLE --%>
                          	
                          						<%-- BEGIN BENEFITS ASSESSMENT COMMENTS TABLE  --%>
                          						<logic:equal name="processBenefitsAssessmentForm" property="review" value="true">
                          						
                          							<logic:equal name="pageType" value="review">
                          								<div id='commentsEditTable'>
                            								<div class="spacer"></div>
                          									<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class='borderTableBlue'>
                          										<tr>
                          											<td colspan="4" class='detailHead' align="left">Benefits Assessment Comments&nbsp;
                                        					<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
                                        						<tiles:put name="tTextField" value="currentAssessment.comments"/>
                                        						<tiles:put name="tSpellCount" value="spellBtn1" />
                                      						</tiles:insert>  
							            												(Max. characters allowed: 200)
                                      					</td>
                          										</tr>
                          										<tr>
                          											<td class='formDe' colspan="4"><html:textarea name="processBenefitsAssessmentForm" property="currentAssessment.comments" style="width:100%" rows="4" onmouseout="textCounter(this,32000)" onkeyup="textCounter(this,200)"/></td>
                          										</tr>
                          									</table>
                          								</div>
                          							</logic:equal>
                          							
                          							<logic:notEqual name="pageType" value="review">
                          								<div id='commentsTable'>
                          									<div class="spacer"></div>
                          									<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class='borderTableBlue'>
                          										<tr>
                          											<td colspan="4" class='detailHead' align="left">Benefits Assessment Comments</td>
                          										</tr>
                          										<tr>
                          											<td class='formDe' colspan="4"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.comments"/></td>
                          										</tr>
                          									</table>
                          								</div>
                          							</logic:notEqual>
                          						</logic:equal>	
												<div class="spacer"></div>
                          						<%-- END BENEFITS ASSESSMENT COMMENTS TABLE --%>
                                			<%-- END DETAIL TABLE --%>
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
          
                  <%-- BEGIN BUTTON TABLE --%>
									<div class="spacer"></div>
                  <table border="0" width="100%">
                    <tr>
                    	<td align="center">
                    	
                    		<logic:equal name="processBenefitsAssessmentForm" property="view" value="false">
                    			<logic:equal name="pageType" value="summary">
                    				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
                    				<html:submit property="submitAction"><bean:message key="button.finish"/></html:submit>
                    				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                    			</logic:equal>
                    			<logic:equal name="pageType" value="confirm">
                    				<html:submit property="submitAction"><bean:message key="button.mainPage"/></html:submit>
                    			</logic:equal>
                    		</logic:equal>
  
                    		<logic:equal name="processBenefitsAssessmentForm" property="view" value="true">
                    			<logic:equal name="processBenefitsAssessmentForm" property="review" value="false">
                    				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
                    				<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">	
	                    				<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_BENASSESS_U%>'> 
	                    					<input type="button" value="<bean:message key='button.reviewAssessment'/>" id="reviewAssessmentBtn" name="submitAction" >
	                    				</jims2:isAllowed>
	                    			</logic:equal>	
                    				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                    			</logic:equal>
  
                    			<logic:equal name="processBenefitsAssessmentForm" property="review" value="true">
                    				<logic:equal name="pageType" value="review">
                    					<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
                    					<input type="button" value="<bean:message key='button.next'/>" name="submitAction" id="submitBtnNext">
                    					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                    				</logic:equal>
                    				<logic:equal name="pageType" value="summary">
                    					<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
                    					<input type="button" value="<bean:message key='button.finish'/>" name="submitAction" id="submitBtnFinish">
                    					
                    					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                    				</logic:equal>
                    				<logic:equal name="pageType" value="confirm">
                    				    <html:submit property="submitAction"><bean:message key="button.returnToCasefile"/></html:submit>
                    				</logic:equal>
                    			</logic:equal>
                    		</logic:equal>

                  	  </td>
                    </tr>
                  </table>
                  <%-- END BUTTON TABLE --%>
                </td>
              </tr>
            </table>
						<div class="spacer"></div>
          </td>
        </tr>
      </table>
		</td>
	</tr>
</table>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

