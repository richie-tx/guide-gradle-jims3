<!DOCTYPE HTML>
<%--This JSP will be used to create, update and create additional addresses.--%>
<%--MODIFICATIONS --%>
<%-- DWilliamson	  07/31/2007  Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
 

<%--BEGIN HEADER TAG--%>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<title><bean:message key="title.heading" /> - maysiUpdateSummary.jsp</title>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/submitNewMAYSI" target="content">
<%-- BEGIN HEADING TABLE --%>
<table width="100%">
  <tr>
	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - 
                                      <bean:message key="title.update"/>
                                      <bean:message key="title.mentalAssessment"/>
                                  <logic:equal name="mentalHealthForm" property="action" value="summary">
                                      <bean:message key="prompt.summary"/>
                                  </logic:equal>
                                      <logic:equal name="mentalHealthForm" property="action" value="confirm">
                                      <bean:message key="prompt.confirmation"/>
                                  </logic:equal>
    </td>
  </tr>
 <logic:equal name="mentalHealthForm" property="action" value="summary">
  <tr><td>&nbsp;</td></tr>
  <tr>
	  <td class="confirm">Mental Health Assessment updated.</td>
  </tr>
 </logic:equal>

</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>
<!-- BEGIN INSTRUCTION TABLE -->
<br>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Verify that information is correct and select Finish button to generate and create Mental Health Assessment.</li>
        <li>Select Back button to return to previous page.</li>
      </ul>	
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<%-- BEGIN JUVENILE HEADER INCLUDE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" > 
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%>

<br>

<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>
   		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
    		<tr>
    			<td valign=top>
    				<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
    				<tiles:put name="tabid" value="interviewinfotab"/>
    				<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
    			</tiles:insert>		
    			</td>
    		</tr>
    		<tr>
    			<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
    		</tr>
    	    <tr>
    	  	    <td>
      			    <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
      				    <tr>
      					    <td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
      				    </tr>
      				    <tr>
      					    <td valign=top align=center>
  								<table width='98%' border="0" cellpadding="0" cellspacing="0" >
  									<tr>
  										<td valign=top>
  											<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
  												<tiles:put name="tabid" value="mentalhealthtab"/>
  												<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  											</tiles:insert>	
  										</td>
  									</tr>
  									<tr>
  						  			<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
 						  			</tr>
  								</table>
                 	            <table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  									<tr>
  									    <td><img src='../../common/images/spacer.gif' width=5></td>
  									</tr>
                                    <tr>
             					        <td valign=top align=center>
        								    <table width='98%' border="0" cellpadding="0" cellspacing="0" >
        									    <tr>
        										    <td valign=top>
        											    <tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
        												    <tiles:put name="tabid" value="maysi"/>
        												    <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
        											    </tiles:insert>	
        										    </td>
        									    </tr>
        									    <tr>
        						  			        <td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
       						  			       </tr>
        								    </table>   	
											<table width='98%' align=center border="0" cellpadding="0" cellspacing="0" class="borderTableRed"> 
												<tr>
													<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
												</tr>
												<tr>
													<td valign=top align=center>
															<div class='spacer'></div>
														<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
															<tr>
																<td class=detailHead><bean:message key="prompt.mentalHealthAssessment" /></td>
															</tr>
															<tr id="editTable" class=hidden>
																<td align=center>
																	<table cellpadding=4 cellspacing=1 width='100%'>
																		<tr>
																			<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.sex" /></td>
																			<td class=formDe><bean:write name="mentalHealthForm" property="sex"/></td>
																			<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.race" /></td>
																			<td class=formDe><bean:write name="mentalHealthForm" property="race"/></td>
																		</tr>
																		<tr>
																			<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.wasTheMaysiAdministered" />?</td>
																			<td class=formDe colspan=3><bean:write name="mentalHealthForm" property="administer"/></td>
																		</tr>
																		<tr>
																			<td class=formDeLabel width='1%'><span class=diamond></span><bean:message key="prompt.reasonMaysiNotAdministered" /></td>
																			<td class=formDe colspan=3><bean:write name="mentalHealthForm" property="reasonNotDone"/></td>
																		</tr>
																		<tr>					
																			<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.referralNumber" /></td>
																			<td class=formDe colspan=3><bean:write name="mentalHealthForm" property="referralNum"/></td>
																		</tr>
																		<tr>	
																			<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.locationUnit" /></td>
																			<td class=formDe colspan=3><bean:write name="mentalHealthForm" property="locationUnit"/></td>
																		</tr>
																		<tr>					
																			<td class=formDeLabel><bean:message key="prompt.howLongHasYouthBeenHere" />?</td>
																			<td class=formDe colspan=3><bean:write name="mentalHealthForm" property="lengthOfStay"/></td>
																		</tr>
																		<tr>	
																			<td class=formDeLabel nowrap><bean:message key="prompt.typeOfFacility" /></td>
																			<td class=formDe colspan=3><bean:write name="mentalHealthForm" property="facilityType"/></td>
																		</tr>
																		<tr>
																			<td nowrap width='1%' class=formDeLabel><bean:message key="prompt.hasYouthTakenMAYSIBefore" />?</td>
																			<td class=formDe colspan=3><elogic:switch name="mentalHealthForm" property="hasPreviousMAYSI">
													                                                     <elogic:case value="true">YES</elogic:case>
													                                                     <elogic:default>NO</elogic:default>
												                                                       </elogic:switch></td>
																		</tr>
																	</table>
																</td>
															</tr>
  								                        </table>
                                                        <div class=spacer></div>
														<table border="0" width="100%">
															<tr>
																<td align="center">
																   <logic:equal name="mentalHealthForm" property="action" value="summary">
																	  <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
																	  <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
																		  <bean:message key="button.finish"/>
																	  </html:submit>
																	  <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
																   </logic:equal>
																   <logic:equal name="mentalHealthForm" property="action" value="confirm">
																	  <html:submit property="submitAction"><bean:message key="button.mainMenu"/></html:submit>
																   </logic:equal>  
																</td>
															</tr>
															<tr>
																<td><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
															</tr>
														</table>
                                       
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
            </tr>
        </table>
    </td>
  </tr>
</table>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

