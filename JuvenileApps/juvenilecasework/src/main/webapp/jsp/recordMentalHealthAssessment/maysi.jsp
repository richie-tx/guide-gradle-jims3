<!DOCTYPE HTML>
<%-- Used to display maysi assessment details by clicking screen date hyperlink from maysi details. --%>
<%--MODIFICATIONS --%>
<%--DApte	05/16/2005	Create JSP --%>
<%--CShimek 09/26/2012  #74333 revised Was the MAYSI administered prompt to ARP entry with new value and revised validation accordingly --%>
<%--CShimek 07/26/2013  #75802 Added Schedule Office Interview Date input --%>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>


<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
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
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/AnchorPosition.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>

<%-- <html:javascript formName="newMaysiForm"/> --%>



<title><bean:message key="title.heading" /> - maysi.jsp</title>
</head>

<html:form action="/displayNewMAYSISummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|186">
<input type="hidden" name="useCase" value="manageJuvenileCasework">

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - 
			<logic:equal name="mentalHealthForm" property="action" value="update"> Update <html:hidden name="mentalHealthForm" property="action" styleId="maysiPageStatus" value="update"/></logic:equal>
			<logic:notEqual name="mentalHealthForm" property="action" value="update"> Create <html:hidden name="mentalHealthForm" property="action" styleId="maysiPageStatus" value="create"/></logic:notEqual><bean:message key="title.mentalAssessment"/>
		</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class=spacer></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td align="left">
			<ul>
				<li>Complete Mental Health Assessment Questionaire, then select Next button to view Summary.</li>
				<li>Select Back button to return to previous page. </li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class=required><bean:message key="prompt.diamond"/>&nbsp;Required Fields</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>

<%-- BEGIN JUVENILE HEADER INCLUDE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" > 
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%>
<div class='spacer'></div> 
<%-- BEGIN Program Referral History TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	  	<td valign='top'>
	  		<table width='100%' border="0" cellpadding="0" cellspacing="0">
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
		      					<td valign='top' align='center'>
									<div class=spacer></div>
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
		             						<td valign='top' align='center'>
												<div class='spacer'></div>
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
      
												<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" class="borderTableRed"> 
													<tr>
														<td valign='top' align='center'>
															<div class='spacer'></div>                		
															<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
																<tr>
																	<td class='detailHead'>Mental Health Assessment</td>
																</tr>
																<tr>
																	<td align='center'>
																		<table cellpadding='4' cellspacing='1' width='100%'>
																			<tr>
																				<td class='formDeLabel'><bean:message key="prompt.race"/></td>
																				<td class='formDe'><bean:write name="mentalHealthForm" property="race"/></td>
																				<td class='formDeLabel' width="20%"><bean:message key="prompt.hispanic"/>?</td>
																				<td class='formDe' width="30%"><bean:write name="mentalHealthForm" property="ethnicityHispanic"/></td>
																			</tr>
																			<tr>
																				<td class='formDeLabel'><bean:message key="prompt.sex"/></td>
																				<td class='formDe' colspan='3'><bean:write name="mentalHealthForm" property="sex"/></td>
																			</tr>
																			<logic:notEqual name="mentalHealthForm" property="action" value="update">
																				<tr>
																					<td class='formDeLabel' width="1%" nowrap="nowrap"><img src="/<msp:webapp/>images/required.gif" alt="required icon">
																						<bean:message key="prompt.wasTheMaysiAdministered"/>
																					</td>
																					<td class='formDe' colspan='3'>
																						&nbsp;Yes <html:radio property="administer" value="yes" />&nbsp;No<html:radio property="administer" value="no" />
																					</td>
																				</tr>
		            
																				<tr id="reasonNotDoneRow" class='hidden'>
																					<td class='formDeLabel'><bean:message key="prompt.diamond"/>
																						<bean:message key="prompt.reasonMaysiNotAdministered"/>
																					</td>
																					<td class='formDe' colspan='3'>
																						<html:select name="mentalHealthForm"  property="reasonNotDoneId" styleId="notDone">
																							<html:option value=""><bean:message key="select.generic" /></html:option>
																							<%-- <html:optionsCollection property="reasonsNotDone" value="code" label="description" /> --%> 
																							<html:optionsCollection property="maysireasonCodes" value="code" label="description" />
																						</html:select>																						
																					</td>
																				</tr>
																				
																				<tr id="schdOffIntDateRow1" class='hidden'>
																					<td class='formDeLabel'><bean:message key="prompt.diamond"/>
																						<bean:message key="prompt.dateOfScheduledOfficeInterview"/>
																					</td>
																					<td class='formDe' colspan='3'>
																						<html:text name="mentalHealthForm" property="scheduledOffIntDateStr" size="10" maxlength="10" styleId="soiDate1"/>						    		      
																					</td>
																					</td>
																				</tr>
																				
																				<tr id="otherReasonNotDone" class='hidden'>
																					<td class='formDeLabel'><bean:message key="prompt.diamond"/>
																						Detailed reason for Other.(Max.characters allowed: 250)
																					</td> 
																					<td class="formDe" colspan=2>
																						<html:textarea name="mentalHealthForm" property="otherReasonNotDone" style="width:100%" rows="3" styleId="othReasonNotDone" ></html:textarea></td>					    		      
																					</td>
																					<td class="formDe"></td>
																					</td>
																				</tr>
																				
																				<tr>      		
																					<td class='formDeLabel'><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon">
																						<bean:message key="prompt.referralNumber"/>
																					</td>
																					<td class='formDe' colspan='3'>
																						<html:select property="referralNum" styleId="refNum">
																							<html:option value=""><bean:message key="select.generic" /></html:option>
																							<html:optionsCollection property="referralNums" value="code" label="description" /> 
																						</html:select>
																					</td>
																				</tr>
																				<tr>    
																					<td class='formDeLabel'>
																						<img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" >
																						<bean:message key="prompt.location"/> Unit
																					</td>
																					<td class='formDe' colspan='3'>
																						<html:select property="locationUnitId" styleId="locUnit">
																							<html:option value=""><bean:message key="select.generic" /></html:option>
																							<html:optionsCollection property="locations" value="juvLocationUnitId" label="locationUnitName" /> 
																						</html:select>
																					</td>
																				</tr>
																				<tr>
																					<td class='formDeLabel'>
																						<img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" >
																						<bean:message key="prompt.howLongHasYouthBeenHere"/>
																					</td>
																					<td class='formDe' colspan='3'>
																						<html:select property="lengthOfStayId" styleId="lenStay">
																							<html:option value=""><bean:message key="select.generic" /></html:option>
																							<html:optionsCollection property="lengthsOfStay" value="code" label="description" /> 
																						</html:select>
																					</td>
																				</tr>
																				<tr>	
																					<td class='formDeLabel' nowrap='nowrap'>
																						<img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" >
																						<bean:message key="prompt.typeOfFacility"/>
																					</td>
																					<td class='formDe' colspan='3'>
																						<html:select property="facilityTypeId" styleId="typeFacility">
																							<html:option value=""><bean:message key="select.generic" /></html:option>
																							<html:optionsCollection property="placementTypes" value="code" label="description" /> 
																						</html:select>
																					</td>
																				</tr>
	                  
																				<tr>
																					<td class='formDeLabel' width='1%' nowrap='nowrap'><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" >
																						<bean:message key="prompt.hasYouthTakenMAYSIBefore"/>
																					</td>
																					<td class='formDe' colspan='3'>&nbsp;Yes<html:radio property="hasPreviousMAYSI" value="yes"/>&nbsp;&nbsp; No<html:radio property="hasPreviousMAYSI" value="no"/></td>
																				</tr>
																			</logic:notEqual>
																			<logic:equal name="mentalHealthForm" property="action" value="update">
																				<tr>					
																					<td class='formDeLabel'><bean:message key="prompt.wasTheMaysiAdministered"/></td>
																					<td class='formDe' colspan='3'>
																						<elogic:switch name="mentalHealthForm" property="administer">
																							<elogic:case value="true">YES</elogic:case>
																							<elogic:default>NO</elogic:default>
																						</elogic:switch>
																					</td>
																				</tr>            									
																				<tr>					
																					<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/>Identify reason MAYSI was not administered</td>
																					<td class='formDe' colspan='3'>
																						<html:select name="mentalHealthForm" property="reasonNotDoneId" size="1" styleId="notDoneU">
																							<html:option value=""><bean:message key="select.generic" /></html:option> 
																							<html:optionsCollection property="maysireasonCodes" value="code" label="description" />			            	  	
																							<%-- <jims2:codetable codeTableName='REASON_NOT_DONE'></jims2:codetable> --%>																						
																						</html:select>
																					</td>
																				</tr>
																				<tr id="schdOffIntDateRow2" class='hidden'>
																					<td class='formDeLabel'>2
																						<bean:message key="prompt.diamond"/><bean:message key="prompt.dateOfScheduledOfficeInterview"/>
																					</td>
																					<td class='formDe' colspan='3'>
																						<html:text name="mentalHealthForm" property="scheduledOffIntDateStr" size="10" maxlength="10" styleId="soiDate2"/>						    		      
																					</td>
																				</tr>
																				<tr>
																					<td class='formDeLabel'><bean:message key="prompt.referralNumber"/></td>
																					<td class='formDe' colspan='3'><bean:write name="mentalHealthForm" property="referralNum"/></td>
																				</tr>
																				<tr>	
																					<td class='formDeLabel'><bean:message key="prompt.location"/> Unit</td>
																					<td class='formDe' colspan='3'><bean:write name="mentalHealthForm" property="locationUnit"/></td>
																				</tr>
																				<tr>					
																					<td class='formDeLabel'><bean:message key="prompt.howLongHasYouthBeenHere"/></td>
																					<td class='formDe' colspan='3'><bean:write name="mentalHealthForm" property="lengthOfStay"/></td>
																				</tr>
																				<tr>	
																					<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.typeOfFacility"/></td>
																					<td class='formDe' colspan='3'><bean:write name="mentalHealthForm" property="facilityType"/></td>
																				</tr>
																				<tr>
																					<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.hasYouthTakenMAYSIBefore"/></td>
																					<td class='formDe' colspan='3'>
																						<elogic:switch name="mentalHealthForm" property="hasPreviousMAYSI">
																							<elogic:case value="true">YES</elogic:case>
																							<elogic:default>NO</elogic:default>
																						</elogic:switch>
																					</td>
																				</tr>
																			</logic:equal>              
																		</table>
<%-- END Program Referral History TABLE --%>
																	</td>
																</tr>
															</table>
		       												<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
								       						<table border="0" width="100%">
																<tr>
																	<td align="center">
																		<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
																		<logic:notEqual name="mentalHealthForm" property="action" value="update">
																			<html:submit property="submitAction" styleId="maysiSubmitButton">                  							  
																				<bean:message key="button.next"></bean:message>
																			</html:submit>
																		</logic:notEqual>
																		<logic:equal name="mentalHealthForm" property="action" value="update">
																			<html:submit property="submitAction"  styleId="maysiUpdateButton">                    							  
																				<bean:message key="button.next"></bean:message>
																			</html:submit>
																		</logic:equal>
																		<html:button styleId="maysiFormResetButton" property="org.apache.struts.taglib.html.BUTTON"><bean:message key="button.reset" /></html:button>
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
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:form>
</html:html>