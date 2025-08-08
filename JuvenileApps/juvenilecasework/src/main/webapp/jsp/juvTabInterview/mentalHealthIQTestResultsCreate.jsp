<!DOCTYPE HTML>
<!-- User selects to Add IQ Test Results-->
<!--MODIFICATIONS -->
<!-- 02/12/2007	Uma Gopinath	Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!-- Javascript for emulated navigation -->
<title><bean:message key="title.heading"/> - mentalHealthIQTestResultsCreate.jsp</title>

<html:javascript formName="iqTestForm"/>
<!-- Javascript for emulated navigation -->
<script language="JavaScript" src="/<msp:webapp/>/js/casework_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>/js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/AnchorPosition.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="JavaScript" src="/<msp:webapp/>/js/juvTabInterview/iqTestCreate.js"></script>

</head> 

<body topmargin=0 leftmargin=0 >
<html:form action="/displayMentalHealthIQTestSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|326">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<tr>
   	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.mentalHealth"/></td>
  </tr>
  <tr>
    <td align="center" class="header">  	
 		 <bean:message key="prompt.create"/> 
	 <bean:message key="prompt.iq"/>   	
  	 <bean:message key="prompt.test"/> 
     <bean:message key="prompt.results"/> 
     </td>
  </tr>  
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
  <tr>
    <td>
     <ul>
    		<li>Enter values, then select <b>Next</b> button to continue.</li>
    		<li>Select <b>Back</b> button to return to previous page.</li>
      </ul>
    </td>
  </tr>
 <tr>
		<td class="required"><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.requiredFieldsInstruction" />&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
  </tr>
</table>
<!-- end instruction table -->

<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END ERROR TABLE --> 

<!--juv profile header start-->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<!--juv profile header end-->

<!-- BEGIN DETAIL TABLE -->
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign=top>
    	<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  			<tr>
  				<td valign=top><tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="interviewinfotab"/>
						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
					</tiles:insert>		</td>
  			</tr>
        <tr>
	  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
	  </tr>
      </table>

  			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
        		<tr>
         		 <td valign=top align=center>  
		            <!-- BEGIN TABLE -->
								<div class=spacer></div>
		            <table width='98%' border="0" cellpadding="0" cellspacing="0">
		              <tr>
		                <td>
		                  <table width='100%' border="0" cellpadding="0" cellspacing="0" >
  		                  <tr>
            							<td valign=top>
    		                   	<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
              								<tiles:put name="tabid" value="mentalhealthtab"/>
              								<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
              							</tiles:insert>	
              						</td>
            						</tr>
  		                 <tr>
          						  	<td bgcolor='#6699FF'><img src='../../common/images/spacer.gif' width=5></td>
          						  </tr>
		                  </table>
  
  									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  										<tr>
  											<td valign=top align=center>
												<div class=spacer></div>
  												<table width='98%' border="0" cellpadding="0" cellspacing="0">
  												<tr>
  														<td valign=top>
  													<tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
														<tiles:put name="tabid" value="iq"/>
														<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
													</tiles:insert>	
													</td>
												</tr>
  													<tr>
												  	  <td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
												    </tr>												   
  												</table>
  												
  												<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
  												  <tr>
  												  	<td align=center>
															 <div class=spacer></div>									  		
  															<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
    																<tr>
    																	<td class=detailHead>IQ Test Results for Program Referral #  <bean:write name='testingSessionForm' property='programReferralNum' /></td>
    																</tr>
    																<tr>
    																	<td align=center>
    																		<table border=0 cellpadding=4 cellspacing=1 width='100%'>
    																			<tr>
  																				<td class=formDeLabel><bean:message key="prompt.serviceProvider"/></td>
  																				<td class=formDe colspan=6><bean:write name="testingSessionForm" property="serviceProviderName"/>
  																			</td>
  																			</tr>
  																			<tr>
  																				<td class=formDeLabel><bean:message key="prompt.instructorName"/></td>
  																				<td class=formDe colspan=6><bean:write name="testingSessionForm" property="instructorName"/></td>
  																			</tr>

  																			<tr>
  																				<td class=formDeLabel><bean:message key="prompt.2.diamond"/><bean:message key="prompt.test"/> <bean:message key="prompt.date"/></td>
  																				<td class=formDe>
  																					<html:text styleId="recTestDate" name="testingSessionForm" property="iqRec.testDate" size="10" maxlength="10"/>						    		      
            							    		      	  					</td>
  																				<td class=formDeLabel><bean:message key="prompt.2.diamond"/>Name of Test Given</td>
  																				<td class=formDe colspan=3>
  																					<html:select property="iqRec.testNameId" name="testingSessionForm">
																					   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																					  <jims2:codetable codeTableName='IQ_TEST_NAME'></jims2:codetable>
																					</html:select>  
  																				</td>
  																			</tr>
  																			<tr>	
  																				<td class=formDeLabel nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.iqScore(Composite/Full)"/></td>
  																				<td class=formDe><html:text name="testingSessionForm" property="iqRec.fullScore" size="3" maxlength="3"/></td>
  																				<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.performanceScore"/></td>
  																				<td class=formDe><html:text name="testingSessionForm" property="iqRec.performanceScore" size="3" maxlength="3"/></td>
  																				<td class=formDeLabel nowrap><bean:message key="prompt.verbalScore"/></td> 
  																				<td class=formDe><html:text name="testingSessionForm" property="iqRec.verbalScore" size="3" maxlength="3"/></td> 
  																			</tr> 
  																			<tr>	
  																				<td class=formDeLabel nowrap><bean:message key="prompt.verbalComprehension"/></td>
  																				<td class=formDe><html:text name="testingSessionForm" property="iqRec.verbalComprehension" size="3" maxlength="3"/></td>
  																				<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.perceptualReasoning"/></td>
  																				<td class=formDe><html:text name="testingSessionForm" property="iqRec.perceptualReasoning" size="3" maxlength="3"/></td>
  																				<td class=formDeLabel nowrap><bean:message key="prompt.nonVerbalIQ"/></td> 
  																				<td class=formDe><html:text name="testingSessionForm" property="iqRec.nonVerbalIQ" size="3" maxlength="3"/></td> 
  																			</tr> 
  																			<tr>	
  																				<td class=formDeLabel nowrap><bean:message key="prompt.processingSpeed"/></td>
  																				<td class=formDe><html:text name="testingSessionForm" property="iqRec.processingSpeed" size="3" maxlength="3"/></td>
  																				<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.workingMemory"/></td>
  																				<td class=formDe><html:text name="testingSessionForm" property="iqRec.workingMemory" size="3" maxlength="3"/></td>
  																				<td class=formDeLabel nowrap><bean:message key="prompt.pictorialIQ"/></td> 
  																				<td class=formDe><html:text name="testingSessionForm" property="iqRec.pictorialIQ" size="3" maxlength="3"/></td> 
  																			</tr> 
  																			<tr>	
  																				<td class=formDe colspan="4"></td>
  																				
  																				<td class=formDeLabel nowrap><bean:message key="prompt.geometricIQ"/></td> 
  																				<td class=formDe><html:text name="testingSessionForm" property="iqRec.geometricIQ" size="3" maxlength="3"/></td> 
  																			</tr> 
  																			<tr>
  																				<td colspan=6 class=formDeLabel><bean:message key="prompt.eval/Recommendations"/>
                  												  &nbsp;
                                  					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                                  						<tiles:put name="tTextField" value="iqRec.recommendations"/>
                                   						<tiles:put name="tSpellCount" value="spellBtn1" />
                                						</tiles:insert>  
																					</td>
  																			</tr>
  																			<tr>
  																				<td colspan=6 class=formDe> <html:textarea rows="4" style="width:100%" name="testingSessionForm" property="iqRec.recommendations"></html:textarea></td>
  																			</tr>																			
  																		</table> 
  																	</td>
  																</tr>
  															</table><!-- borderTableBlue -->
  	                         <!-- BEGIN BUTTON TABLE -->                            
                              <table border="0" width="100%">
                                <tr>
                                  <td align="center">    
                                     <html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>									 
                					 <html:submit property="submitAction" styleId="validateIqTestForm"><bean:message key="button.next"></bean:message></html:submit>		
                					 <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>                             
                                  </td>
                                </tr>
                              </table>
                              <!-- END BUTTON TABLE -->
                            </td>
                          </tr>
                        </table>
												<div class=spacer></div> <!-- borderTableRed -->
                      </td>
   									</tr>
   								</table>
									<div class=spacer></div>
									<!-- borderTableBlue --><!-- END TABLE -->							
                </td>
              </tr>
            </table> 
          </td>
        </tr>
      </table> <!-- borderTableGreen -->
   </td>
  </tr>
</table> 
<!-- END DETAIL TABLE -->

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

