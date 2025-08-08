<!DOCTYPE HTML>
<%-- User selects the "Education" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 01/14/2011 D Williamson Create JSP  --%>
<%-- 03/04/2011 C Shimek     added check to Update VEP to requrie program selection --%>
<%-- 07/10/2012 C Shimek     #73565 added age > 20 check (juvUnder21) to Add/Update buttons --%>
<%-- 08/6/2015  R Young      #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/> - educationCharterList.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript">
function switchText(theLiteralRowID){
	var theLink = 'view' + theLiteralRowID;
	if (document.getElementById(theLink).innerHTML == "View")
	{
		show(theLiteralRowID, 1, 'row');
		document.getElementById(theLink).innerHTML = "Hide"
	}else {
		show(theLiteralRowID, 0);
		document.getElementById(theLink).innerHTML = "View"
	}
}
function onloadForm()
{
	var vepAdd = document.getElementById("addVEP");
	var vepUpdate = document.getElementById("updateVEP");
	if (vepAdd != null && vepUpdate != null)
	{	
		var incompleteVEPs = document.getElementsByName("incompleteVEP");
		if (incompleteVEPs.length == 0)
		{
			vepAdd.disabled = false;
			vepUpdate.disabled = true;
		} else {
			vepAdd.disabled = true;
			vepUpdate.disabled = false;
		}
	}	
}
function programSelectCheck()
{
	var rbs = document.getElementsByName("vepProgramCode");
	for (x=0; x<rbs.length; x++)
	{
		if(rbs[x].checked == true){
			return true;
		}	 
	}
	alert("Program selection required for VEP Update.");
	return false;
}
</script>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0" onLoad="onloadForm();">
<html:form action="handleCharterDetails" target="content">

<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> -  
			<bean:message key="title.charter"/>&nbsp;<bean:message key="title.details"/>
		</td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<!-- BEGIN INSTRUCTION TABLE -->
<br>
<table width="98%" border="0">
   <tr>
        <td>
        	<ul>
        		<li>Select appropriate Add/Update button to Add/Update information.</li>
        	</ul>
        </td>
   </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<%-- BEGIN DISPLAY PROFILE HEADER --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<!-- END DISPLAY PROFILE HEADER -->
<div class="spacer"></div>
<!-- BEGIN DETAIL TABLE -->
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
<!-- BEGIN GREEN TABS TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66"><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
				</tr>
			</table>
<!-- END GREEN TABS TABLE -->	
<!-- BEGIN GREEN BORDER TABLE -->	
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  				<tr>
  					<td valign="top" align="center">
						<div class="spacer4px"></div>
<!-- BEGIN BLUE TABS TABLE -->		  							
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign='top'>
									<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>	
								</td>
							</tr>
							<tr>
								<td bgcolor="#6699FF"><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
							</tr>
						</table>
						<div class="spacer"></div>
						<!-- END BLUE TABS TABLE -->	
						<!-- BEGIN BLUE BORDER TABLE --> 								
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" align="center">
									<div class="spacer"></div>
									<!-- BEGIN RED TABS TABLE -->											
									<table width="98%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="top">
												<tiles:insert page="../caseworkCommon/educationTabs.jsp" flush="true">
                                             		<tiles:put name="tabid" value="charter"/>
                                                    <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
                                                </tiles:insert>
											</td>
										</tr>
										<tr>
											<td bgcolor="#ff6666"><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
										</tr>
									</table>
									
								<!-- END RED TABS TABLE -->	
								<!-- BEGIN RED BORDER TABLE -->
									<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
									
										<tr>
      										<td valign="top" align="center">
      										<div class="spacer"></div>      										
      											<!-- BEGIN HSE PROGRAM TABLE  -->																
												<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr>
														<td class="detailHead" width="1%"><a href="javascript:showHideMulti('HESP', 'xHSEP',  2, '/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="HESP"></a></td>
														<td valign="top" class="detailHead">
															<bean:message key="title.highSchoolEquivalencyProgram"/>
															<logic:empty name="educationCharterDetailsForm" property="hsepProgramList"> 
																(No GED information available at this time.)
															</logic:empty>
														</td>
													</tr>      													
												
													<tr id="xHSEP0" class="hidden">
														<td colspan="2">
														<!-- BEGIN HSE PROGRAM DETAILS TABLE  -->																
															<table width="100%" cellspacing="1">
			        								             <logic:notEmpty name="educationCharterDetailsForm" property="hsepProgramList">
																	<tr>
																		<td class="formDeLabel" width="40%" nowrap><bean:message key="prompt.entryDate" /></td>																			
																		<td class="formDeLabel" nowrap><bean:message key="prompt.totalScore" /></td>
																		<td class="formDeLabel" nowrap><bean:message key="prompt.passFail" /></td>
														
											
																	</tr>
																	<!-- BEGIN HSE PAGINATION ITEM WRAP -->
																	<pg:item>
																			<logic:iterate id="hsepIndex" name="educationCharterDetailsForm" property="hsepProgramList" indexId="indexer">
																	  			<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																 					<td nowrap>
																						<a href="/<msp:webapp/>handleCharterDetails.do?submitAction=Link&charterGEDId=<bean:write name="hsepIndex" property="charterGEDId"/>"><bean:write name="hsepIndex" property="GEDEntryDate" formatKey="datetime.format.mmddyyyyHHmmAMPM"/></a>     	
																	 				</td> 
																					<td>
																						<logic:notEqual name="hsepIndex" property="totalScore" value="0">
																							<bean:write name="hsepIndex" property="totalScore" />
																						</logic:notEqual>	
																						<logic:equal name="hsepIndex" property="totalScore" value="0">
																							<bean:message key="prompt.notApplicable" />
																						</logic:equal>	
																					</td>
																					<td>
																					<logic:notEqual name="hsepIndex" property="totalIncomplete" value="1">
																						<logic:equal name="hsepIndex" property="gedPassFailIndicator" value="true">
																							<bean:message key="prompt.pass" />
																						</logic:equal>
																						<logic:equal name="hsepIndex" property="gedPassFailIndicator" value="false">
																							<bean:message key="prompt.fail" />
																						</logic:equal>
																					</logic:notEqual>
																					<logic:equal name="hsepIndex" property="totalIncomplete" value="1">
		                                												<bean:message key="prompt.incomplete" />
		                															</logic:equal>
																					</td>                                                                            
																		 			</tr>
																			</logic:iterate>
																  		</pg:item>
																		<%-- END HSE PAGINATION ITEM WRAP --%>
																</logic:notEmpty>
																<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_VEP_U%>'>
																		<logic:equal name="educationCharterDetailsForm" property="lockStatus" value="false">
																			<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
																			<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
																				<tr>
																					<td colspan="4" align="center">
																						<html:submit property="submitAction"><bean:message key="button.addGED" /></html:submit>
																					</td>
																				</tr>
																				</logic:notEqual>
																				<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
																				<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
																				<tr>
																					<td colspan="4" align="center">
																						<html:submit property="submitAction"><bean:message key="button.addGED" /></html:submit>
																					</td>
																				</tr>
																				</jims2:isAllowed>
																				</logic:equal>
																			</logic:equal>	
																		</logic:equal>
  		                                                           	</jims2:isAllowed>
															</table>
														<!-- END HSE PROGRAM DETAILS TABLE  -->															
														</td>
													</tr>	
												</table>
												<div class="spacer"></div>
												<!-- END HSE PROGRAM TABLE -->	
												<!-- BEGIN VE PROGRAM TABLE  -->															
													<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
														<tr>
															<td class="detailHead" width="1%"><a href="javascript:showHideMulti('VEP', 'xVEP', 2, '/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="VEP"></a></td>
															<td valign="top" class="detailHead">
																<bean:message key="title.vocationalEducationProgram"/>
																<logic:empty name="educationCharterDetailsForm" property="vepProgramList"> 
     														    	(No VEP information available at this time.)
              								                    </logic:empty>																
															</td>
														</tr>	
														<tr id="xVEP0" class="hidden">
														<td colspan="2">
															<!-- BEGIN VE PROGRAM DETAILS TABLE  -->																
															<table width="100%" cellspacing="1">
             								                        <logic:notEmpty name="educationCharterDetailsForm" property="vepProgramList">
																	<tr>
																		<td class="formDeLabel" width="1%"></td>
																		<td class="formDeLabel"><bean:message key="prompt.program" /></td>
																		<td class="formDeLabel"><bean:message key="prompt.startDate" /></td>
                                                                   		<td class="formDeLabel"><bean:message key="prompt.completionStatus" /></td>
                                                                        <td class="formDeLabel"><bean:message key="prompt.completionDate" /></td>
																	</tr>
																	<%-- BEGIN VE PAGINATION ITEM WRAP--%> 
                                                             			<pg:item>
																			<logic:iterate id="vepIndex" name="educationCharterDetailsForm" property="vepProgramList" indexId="indexer" length="0">
																				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																					<td>
																						<logic:equal name="vepIndex" property="completed" value="true">
																							&nbsp;
																						</logic:equal>
																						<logic:equal name="vepIndex" property="completed" value="false">
																							<input type="radio" name="vepProgramCode" value="<bean:write name='vepIndex' property='juvenileCharterVEPId'/>" >
																							<input type="hidden" name="incompleteVEP" value="">
																						</logic:equal>
																					</td>
																					<td nowrap><bean:write name="vepIndex" property="programName" /></td>
																					<td><bean:write name="vepIndex" property="startDate" formatKey="date.format.mmddyyyy"/></td>
																					<td>
																						<logic:equal name="vepIndex" property="completed" value="true">
																							<bean:message key="prompt.completed" />
																						</logic:equal>
																						<logic:equal name="vepIndex" property="completed" value="false">
																							Not <bean:message key="prompt.completed" />
																						</logic:equal>
																					</td>
																					<td>
																						<bean:write name="vepIndex" property="completionDate" formatKey="date.format.mmddyyyy"/>
																						<input type="hidden" name="vepCompDate" value="<bean:write name='vepIndex' property='completionDate' formatKey='date.format.mmddyyyy'/>" >
																					</td>
																					
																				</tr>
																			</logic:iterate>
																		</pg:item>
																<%-- END PAGINATION ITEM WRAP --%>
																</logic:notEmpty>
																  	<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_VEP_U%>'>
	  		                                                           	<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
	  		                                                           	<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
																			<tr>
																				<td colspan="5" align="center">
																				    <html:submit property="submitAction" disabled="true" styleId="addVEP"><bean:message key="button.addVEP" /></html:submit>
																				    <html:submit property="submitAction" disabled="true" styleId="updateVEP" onclick="return programSelectCheck()"><bean:message key="button.updateVEP" /></html:submit>
																				</td>
																			</tr>
																		</logic:notEqual>
																		<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
																		<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
																		<tr>
																				<td colspan="5" align="center">
																				    <html:submit property="submitAction" disabled="true" styleId="addVEP"><bean:message key="button.addVEP" /></html:submit>
																				    <html:submit property="submitAction" disabled="true" styleId="updateVEP" onclick="return programSelectCheck()"><bean:message key="button.updateVEP" /></html:submit>
																				</td>
																		</tr>
																		</jims2:isAllowed>
																		</logic:equal>
																		</logic:equal>	
															     	</jims2:isAllowed>
														</table>
														<div class="spacer"></div>
														<!-- END VE PROGRAM DETAILS TABLE  -->
														</td>
													  </tr>											
													</table>
													<div class="spacer"></div>
												<!-- END VE PROGRAM TABLE  -->	
												<!-- BEGIN POST RELEASE TRACKING TABLE  -->															
												<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
														<tr>
															<td class="detailHead" width="1%"><a href="javascript:showHideMulti('PRTrk', 'xPRTrk', 2, '/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="PRTrk"></a></td>
															<td valign="top" class="detailHead">
																<bean:message key="title.postReleaseTracking"/>
																<logic:empty name="educationCharterDetailsForm" property="postReleaseTrackingList"> 
																	(No Post Release Tracking information available at this time.)
																</logic:empty>
															</td>
														</tr>
														<tr id="xPRTrk0" class="hidden">
															<td colspan="2">	
														<!-- BEGIN POST RELEASE TRACKING DETAILS TABLE  -->																	
														<table width="100%" cellspacing="1">
															<logic:notEmpty name="educationCharterDetailsForm" property="postReleaseTrackingList">
																<tr>
																	<td class="formDeLabel" width="1%" nowrap>&nbsp;&nbsp;<bean:message key="prompt.postReleaseStatusDate" /></td>																	
																	<td class="formDeLabel"><bean:message key="prompt.employed" /></td>
																	<td class="formDeLabel"><bean:message key="prompt.education" /></td>
																</tr>
																<!-- BEGIN POST RELEASE TRACKING PAGINATION ITEM WRAP --> 											
				                                                 <pg:item>
																	<logic:iterate id="prtIndex" name="educationCharterDetailsForm" property="postReleaseTrackingList" indexId="indexer" >
																		<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
																			<td nowrap><bean:write name="prtIndex" property="statusDate" formatKey="date.format.mmddyyyy"/></td>																				
																			<td nowrap><bean:write name="prtIndex" property="employedCodeDesc" /></td>
																			<td>
																				<bean:write name="prtIndex" property="continuingEducationCodesStr" />
																				<logic:greaterThan name="prtIndex" property="comments" value="">
																					<span class="paddedFourPix"><a href="javascript:switchText('<%out.print(indexer.intValue());%>');" id='view<%out.print(indexer.intValue());%>'>View</a></span>
																				</logic:greaterThan>
																			</td>
																		</tr>
																		<logic:greaterThan name="prtIndex" property="comments" value="">
																			<tr id=<%out.print(indexer.intValue());%> class="hidden">
																				<td valign="top" align="right" class="boldText">Comments:&nbsp;</td>
																				<td colspan="2"><bean:write name="prtIndex" property="comments" /></td>
																			</tr>
																		</logic:greaterThan>
																	</logic:iterate>
																</pg:item>
																<!-- END POST RELEASE TRACKING PAGINATION ITEM WRAP --> 
															</logic:notEmpty>
															<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_VEP_U%>'>
																<logic:equal name="educationCharterDetailsForm" property="releasedFromFacility" value="true"> 
																	<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
																	<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
																		<tr>
																			<td colspan="3" align="center">
																			    <html:submit property="submitAction"><bean:message key="button.addPostRelease" /></html:submit>
																			</td>
																		</tr>
																	</logic:notEqual>
																	<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
																	<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
																	<tr>
																			<td colspan="3" align="center">
																			    <html:submit property="submitAction"><bean:message key="button.addPostRelease" /></html:submit>
																			</td>
																	</tr>
																	</jims2:isAllowed>
																	</logic:equal>
																	</logic:equal>	
																</logic:equal>
															</jims2:isAllowed>
														</table>
														<div class="spacer"></div>
														<!-- END POST RELEASE TRACKING DETAILS TABLE  -->																	
													</td>
													</tr>	
												</table>
													<div class="spacer"></div>
												<!-- END POST RELEASE TRACKING TABLE  -->	
												
												<!-- BEGIN BUTTON TABLE -->
													<table border="0" width="100%" id="buttonTable" class="hidden">
														<tr>
															<td align="center">
																<logic:notEqual name="juvenileSchoolHistoryForm" property="action" value="confirm">
  					                                               <td><html:submit property="submitAction"><bean:message key="button.back" /></html:submit></td>
  					                                                 
  					                                                    	<td><html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit></td>
  					                                                  
 					                                          </logic:notEqual>  
															</td>
														</tr>
													</table>
												<!-- END BUTTON TABLE -->															
											</td>
										</tr>			
									</table>
									<div class="spacer"></div>
									<!-- END RED BORDER TABLE -->							
      																			
								</td>
							</tr>
						</table>
						<div class="spacer"></div>
						<!-- END BLUE BORDER TABLE -->		
			</td>
		</tr>
	</table>
	<!-- END GREEN BORDER TABLE -->	
<!-- END DETAIL TABLE -->
<br>
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>
<%--/html:form--%>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</html:form>
</body>
</html:html>