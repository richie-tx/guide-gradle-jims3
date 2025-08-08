<!DOCTYPE HTML>
<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Caseplan" tab on Juvenile Profile Master Details page after searching for a juvenile profile --%>
<%-- 01/29/2007	Debbie Williamson		Create JSP --%>
<%-- 07/17/2009 C Shimek                #61004 added timeout.js  --%>
<%-- 08/08/2013 C Shimek                #75913 added missing age check on copy button(found while testing, not part of Activity) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - prevCaseplansList.jsp</title>
<%--END HEADER TAG--%>

<script type="text/javascript">
function onloadForm()
{
	if( location.search.indexOf( "pager.offset" ) != (-1) )
	{
		showHideMulti('JPO Activity List', 'acChar', 1, '/<msp:webapp/>');
	}
}
</script>

</head>

<%--BEGIN BODY TAG--%>
<body onload="onloadForm();">
<html:form action="/displayPreviousCaseplanVersions" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|169">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" /> - Process Caseplan - Caseplan Versions</td>
	</tr>
</table>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class="spacer"></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select <b>Supervision Number</b> hyperlink to view additional details.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<logic:notEqual name="caseplanForm" property="juvProfile" value="true">
<%-- BEGIN HEADER INFO TABLE --%>
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="casefileheader" />
	</tiles:insert>
<%-- END HEADER INFO TABLE --%>
<div class="spacer"></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="goalstab" />
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>
<%-- BEGIN BLUE TABS BORDER TABLE --%>						
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" align="center">
						<div class="spacer"></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign="top">
									<tiles:insert page="../caseworkCommon/casePlanTabs.jsp" flush="true">
  										<tiles:put name="tabid" value="PreviousVersions" />
  										<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  									</tiles:insert>
								</td>
							</tr>
							<tr>
    							<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5" alt=""></td>
							</tr>
						</table>
<%-- BEGIN GREEN BORDER TABLE --%>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  							<tr>
								<td valign="top" align="center">
</logic:notEqual>

<logic:equal name="caseplanForm" property="juvProfile" value="true">
<%-- BEGIN HEADER TABLE --%>
									<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
										<tiles:put name="headerType" value="profileheader"/>
									</tiles:insert>
<%-- END HEADER TABLE --%>
									<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
									<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="top">
<%--tabs start--%> 
												<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
													<tiles:put name="tabid" value="goalstab" />
													<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
												</tiles:insert> 
<%--tabs end--%>		

  												<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  													<tr>
  														<td valign="top" align="center">
    														<div class="spacer"></div>
										  					<table width='98%' border="0" cellpadding="0" cellspacing="0" >
										  						<tr>
														  			<td valign="top">
														  				<tiles:insert page="../caseworkCommon/juvenileCasePlanTabs.jsp" flush="true">
														  					<tiles:put name="tabid" value="PreviousVersions"/>
														  					<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
														  				</tiles:insert>				
														  			</td>
														  		</tr>
										  						<tr>
													    		  	<td bgcolor='6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
												    			</tr>
														    </table>

  															<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
    															<tr>
    																<td valign="top" align="center">
</logic:equal>
    																	<div class='spacer'></div>				
<%-- BEGIN Activities TABLE --%>
											          					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											          						<tr>
											          							<td valign="top" class="detailHead" colspan="6">
											            							<table width='100%' cellpadding="0" cellspacing="0">
											            								<tr>
												          									<logic:notEmpty name="caseplanForm" property="cpActivities">
												            									<td width='1%'><a href="javascript:showHideMulti('JPO Activity List', 'acChar', 1, '/<msp:webapp/>')" border="0">
												            									  <img border="0" src="/<msp:webapp/>images/expand.gif" name="JPO Activity List" alt=""></a>
												    													</td>
												          									</logic:notEmpty>	    													
											    													<td class="detailHead" colspan="6">&nbsp;Caseplan&nbsp;<bean:message key="prompt.activities" />
													          									<logic:empty name="caseplanForm" property="cpActivities">
													          										(No Caseplan Activities)
													          									</logic:empty>
												          									</td>
											             								</tr>
											            							</table>
											            						</td>
											            					</tr>

											          						<tr id="acChar0" class="hidden">
											          							<td>
																					<bean:define id="paginationResultsPerPage" type="java.lang.String">
											            								<bean:message key="pagination.recordsPerPage"></bean:message>
											            							</bean:define> 
											          								<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
          								
	          																			<table cellpadding="2" cellspacing="1" width='100%'>
												          									<logic:notEmpty name="caseplanForm" property="cpActivities">
												          										<tr bgcolor='#cccccc'>
												          											<td class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.entryDate"/>
																										<jims:sortResults beanName="caseplanForm" results="cpActivities" primaryPropSort="activityDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="1" />				
																									</td>
																									<td class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.activity"/>
																										<jims:sortResults beanName="caseplanForm" results="cpActivities" primaryPropSort="activityDesc" primarySortType="STRING"  sortId="2" />	
																									</td>
																									<td class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.comments"/>
																										<jims:sortResults beanName="caseplanForm" results="cpActivities" primaryPropSort="comments" primarySortType="STRING"  sortId="3" />	
																									</td>
																								</tr>
												           										<logic:iterate id="activityIndex" name="caseplanForm" property="cpActivities" indexId="indexer">
<%-- Begin Pagination item wrap --%>
												           											<pg:item>
												           												<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
												             												<td align="left"><bean:write name="activityIndex" property="activityDate" formatKey="date.format.mmddyyyy" /></td>       
												                     										<td valign="top" align="left"><bean:write name="activityIndex" property="activityDesc" /></td>
												                     										<td valign="top" align="left"><bean:write name="activityIndex" property="comments" /></td>
												           												</tr>
												           											</pg:item>
<%-- End Pagination item wrap --%>
									           													</logic:iterate>          
												          									</logic:notEmpty>
        	  																			</table>
<%-- Begin Pagination navigation Row--%>
												          								<table align="center">
												          									<tr>
												          										<td><pg:index>
												          											<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
												          												<tiles:put name="pagerUniqueName" value="pagerSearch" />
												          												<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>" />
												          											</tiles:insert>
												          										</pg:index></td>
												          									</tr>
												          								</table>
<%-- End Pagination navigation Row--%>
											          								</pg:pager>
																				</td>
																			</tr>
          																</table>
          																<div class='spacer'></div>
<%-- BEGIN Caseplan TABLE --%> 
																		<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
																			<tr>
																				<td class="detailHead">Caseplan Versions</td>
																			</tr>
																			<tr>
																				<td>
																					<table cellpadding="2" cellspacing="1" width='100%'>
																						<logic:empty name="caseplanForm" property="caseplanList">
																							<tr><td align="left">No Previous Caseplans available.</td></tr>
																						</logic:empty>

											          									<logic:notEmpty name="caseplanForm" property="caseplanList">
											          										<tr bgcolor='#cccccc'>
											          											<td class="subHead" width='1%'><bean:message key="prompt.entryDate" /> 
																									<jims:sortResults beanName="caseplanForm" results="caseplanList" primaryPropSort="createDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="8" />
											          											</td>
											          											<td class="subHead" width='1%'><bean:message key="prompt.status" />
											          												<jims:sortResults beanName="caseplanForm" results="caseplanList" primaryPropSort="status" primarySortType="STRING" sortId="9" />
											          											</td>
											          											<td class="subHead" width='1%'><bean:message key="prompt.reviewDate" />
											          												<jims:sortResults beanName="caseplanForm" results="caseplanList" primaryPropSort="reviewDate" primarySortType="DATE" sortId="9" />
											          											</td>
											          										</tr>
																							<logic:iterate id="cpIndex" name="caseplanForm" property="caseplanList" indexId="indexer">
																								<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																									<td align="left">
																										<a href="/<msp:webapp/>displayPreviousCaseplanVersions.do?submitAction=<bean:message key='button.view'/>&amp;selectedValue=<bean:write name="cpIndex" property="caseplanID" />">
																										<bean:write name="cpIndex" property="createDate" formatKey="date.format.mmddyyyy" /></a>
																									</td>
																									<td valign="top" width='1%' align="left"><bean:write name="cpIndex" property="status" /></td>
																									<td valign="top" width='1%' align="left"><bean:write name="cpIndex" property="reviewDate" formatKey="date.format.mmddyyyy" /></td>
																								</tr>
<%-- End Pagination item wrap --%>
																							</logic:iterate>
																						</logic:notEmpty>
											          								</table>
											          							</td>
											          						</tr>
											          					</table>
<%-- END casefile TABLE --%>
										              					<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>              				
										              					<table border="0" align="center">
										              						<tr>
										              							<td align="center">
																					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
                																		<bean:message key="button.back"></bean:message>
                																	</html:button>&nbsp;  
																				</td>
																				<logic:notEmpty name="caseplanForm" property="caseplanList">  
																					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CASEPLAN_CP%>'>	
																						<logic:equal name="caseplanForm" property="juvProfile" value="true">
																							<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">  										
			  																					<td>	
																									<html:submit property="submitAction"><bean:message key="button.copyCaseplan"/></html:submit>
																								</td>
																							</logic:equal>	
																						</logic:equal>
																						<logic:notEqual name="caseplanForm" property="juvProfile" value="true">
																			 				<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true"> 
																			 					<td>	
																									<html:submit property="submitAction"><bean:message key="button.copyCaseplan"/></html:submit>
																								</td>
																							</logic:equal>	
																						</logic:notEqual> 	
																					</jims2:isAllowed>
																				</logic:notEmpty>	
									            								<td align="center"><html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit></td>
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
  								</td>
  							</tr>
						</table>
<%-- END GREEN BORDER TABLE --%>	
					</td>
				</tr>
			</table>
<%-- END BLUE TABS BORDER TABLE --%>				
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>					
</html:form>

<div class="spacer" align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>