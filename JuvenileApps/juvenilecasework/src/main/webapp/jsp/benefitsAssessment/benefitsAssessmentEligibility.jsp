<!DOCTYPE HTML>

<%-- 11/09/2005	 Aswin Widjaja - Create JSP --%>
<%-- 08/31/2015 RCapestani #29429 MJCW:  Adapt MJCW and Warrants to IE9, IE11 and Chrome (Benefits Assessment UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<title>Juvenile Casework - Benefits Assessment - Benefits Summary</title>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
  
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/displayBenefitsAssessmentSummary.do" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|1">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" >Juvenile Casework - Benefits Assessment - Benefits Summary</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Fill out Medicaid and Title IV-e benefits information and then select the Next button.</li>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN Guardian TABLE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>

<div class='spacer'></div>
<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign=top>
	
  		 <%-- BEGIN DETAIL TABLE .... 22 mar 2007 - mjtobler: removed " class="borderTableBlue" from next <table..>  --%>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0">
  			<tr>
  			  <td valign=top align=center>
  			  
  			    <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
						<div class='spacer'></div>
	  			  <table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td>

								<table width='100%' border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign=top align=center>
				
                      <table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td valign=top>
                            <%-- Begin Detail Table --%>
                            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="borderTableBlue">
                            	<tr>
                            		<td valign="top" class="detailHead">
                            			<table width="100%" cellpadding="2" cellspacing="0">
                            				<tr>
                            					<td width="1%">
                            						<a href="javascript:showHideMulti('Guardian','pMore',3, '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/contract.gif" name="Guardian"></a>
                            					</td>
                            					<td class="detailHead"><bean:message key="prompt.guardianName"/> <bean:write name="processBenefitsAssessmentForm" property="currentAssessment.guardian.name.formattedName"/></td>
                            				</tr>
                            			</table>
                            		</td>
                            	</tr>
      
                            	<tr>
          											<td>
																  <div class='spacer'></div>			
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
                 										</td>
               										</tr>

               										
                                	<tr>
                                		<td>
                                			<%-- BEGIN Family Financial Info TABLE --%>
                                			<span id='pMore2'>
																			  <div class='spacer'></div>
                                				<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" class="borderTableBlue">
                                					<tr>
                                						<td valign="top" class="detailHead">
  																						
                                							<table width="100%" cellpadding="0" cellspacing="0">
                                								<tr>
                                									<td width="1%">&nbsp;<a href="javascript:showHideMulti('Characteristics','pChar',1, '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="Characteristics"></a></td>
                                									<td class="detailHead">&nbsp;Family Financial Information</td>
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
                                			</span>
                              				<%-- END Family Financial Info TABLE --%>
                      
                              				<%-- Begin Title IV-e Assessment TABLE --%>
                                			<span id='pMore0'>		
             														<div class='spacer'></div>
                                				<tiles:insert page="/jsp/benefitsAssessment/titleIVEPlacementScreening.jsp" flush="true">
                                					<tiles:put name="titleIVEQuestion" beanName="processBenefitsAssessmentForm" beanProperty="currentAssessment.question" />	
                                				</tiles:insert>
                                			</span>	

                          			<span id='pMore1'>
                          			<table cellpadding="2" cellspacing="1" width="98%" align='center' class="borderTableGrey">
                          				 <tr>
                          					<td bgcolor="999999"><strong>IV-e Eligibility (To be completed by JPD Staff ONLY)</strong></td>
                          				 </tr>
                          				 <tr>
                          					<td align="center">
                          					  <table cellpadding="4" cellspacing="1"  align='center' width="100%">
                          						 <tr>
                          							<td width="1%">7a.</td>
                          							<td class="formDeLabel">Does the initial order of removal contain the "Best Interest" language?</td>
                          							<td class="formDe" nowrap width="16%" colspan="2"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.initialOrderRemovalContainBestInterestDisp"/></td>
                          						 </tr>
                          						 <tr>
                          							<td>7b.</td>
                          							<td class="formDeLabel">Was the "Reasonable Efforts" language made within 60 days of the child's removal?</td>
                          							<td class="formDe" nowrap colspan="2"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.reasonableEffortsMadeWithin60DaysOfChildRemovalDisp"/></td>
                          						 </tr>
                          						 <tr>
                          							<td>7c.</td>
                          							<td class="formDeLabel">Do court orders include the finding of "responsibility for the child's care and placement?</td>
                          							<td class="formDe" colspan="2"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.courtOrderIncludeFindingChildCareAndPlacementDisp"/></td>
                          						 </tr>
                          						 <tr>
                          							<td>8.</td>
                          							<td class="formDeLabel">Does this child meet the AFDC requirements and did the court orders contain the appropriate language?</td>
                          							<td class="formDe" colspan="2"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.childMeetAFDCRequirementDisp"/></td>
                          						 </tr>
                          						 <tr>
                          							<td>&nbsp;</td>
                          							<td>
                          							  <table width="100%" class="borderTableGrey">
                          								 <tr>
                          									<td class="formDeLabel" width='1%' nowrap>Title IV-E Officer</td>
                          									<td class="formDe"><bean:write name="processBenefitsAssessmentForm" property="titleIVeOfficerName.formattedName"/></td>
                          								</tr>
                          							  </table>
                          							</td>
                          						 </tr>
                          					  </table>
                          					</td>
                          				 </tr>
                          			  </table>
																	<div class='spacer'></div>
                          			</span>									
                                <%-- END Title IV-e Assessment TABLE --%>
                            	
                                <%-- BEGIN FAMILY MEMBER BENEFITS TABLE --%>
                                <table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" align="center">
                                	<tr>
                                		<td colspan="4" class="detailHead">Benefits Assessments</td>
                                   </tr>			
                                   <tr>
                                		<td align=center>
                                			<table cellpadding=4 cellspacing=1 width='100%' align=center>
                                    			<tr>
                                    				<td class=formDeLabel nowrap>Eligibility Type</td>
                                    				<td class=formDeLabel>Eligible</td>
                                    				<td class=formDeLabel>Receiving</td>
                                    			</tr>
                                    			<tr>
                                    				<td class=formDe>Medicaid</td>
                                    				<td class=formDe>
                                						Yes<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.eligibleForMedicaid" value="true"/> 
                                						No<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.eligibleForMedicaid" value="false"/> 
                                					</td>
                                    				<td class=formDe>
                                						Yes<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.receivingMedicaid" value="true"/> 
                                						No<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.receivingMedicaid" value="false"/> 
                                					</td>
                                    			</tr>
                                				<tr>
                                					<td class=formDe>Title IV-e Candidacy</td>
                                					<td class=formDe>
                                						Yes<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.eligibleForTitleIVe" value="true"/> 
                                						No<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.eligibleForTitleIVe" value="false"/> 
                                					</td>
                                					<td class=formDe>
                                						Yes<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.receivingTitleIVe" value="true"/> 
                                						No<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.receivingTitleIVe" value="false"/> 
                                					</td>
                                				</tr>
                                			</table>
                                		</td>
                                	</tr>
                                </table>
                                <%-- END FAMILY MEMBER BENEFITS TABLE --%>
                                	
                                <%-- BEGIN BENEFITS ASSESSMENT COMMENTS TABLE --%>
                                <div id='commentsEditTable' class='hidden'>
                                <br />
                                <table align=center width='98%' border=0 cellpadding=2 cellspacing=0 class='borderTableBlue'>
                          				 <tr>
                         						 <td colspan=4 class='detailHead'>Benefits Assessment Comments</td>
                          				 </tr>
                          				 <tr>
                         						 <td class='formDe' colspan=4><textarea style='width:100%' rows=4></textarea></td>
                          				 </tr>
                                </table>
                                </div>
                                
                                <%-- END BENEFITS ASSESSMENT COMMENTS TABLE --%>
                              <%-- END DETAIL TABLE --%>
                              
                              <%-- BEGIN BUTTON TABLE --%>
                              <table border="0" width="100%">
                                <tr>
                              		<td align=center>
                              			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
                              			<html:submit property="submitAction"><bean:message key="button.next"/></html:submit>
                              			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                              		</td>
                                </tr>
                              </table>
                              <%-- END BUTTON TABLE --%>
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
						<div class='spacer'></div>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
    </td>
  </tr>
</table>

</html:form>

<div class='spacer'></div>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
