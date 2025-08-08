<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2006		AWidjaja Create JSP--%>
<%-- 03/27/2013 C Shimek     #75331 added onLoad script to keep JPO Activity List expanded during pagination selects  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- reviewCaseplan.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	sessionStorage.removeItem("isRecomSupLevel");
	sessionStorage.removeItem("isJpoMaintain");
})
function onloadForm()
{
	if( location.search.indexOf( "pager.offset" ) != (-1) )
	{
		showHideMulti('reviewCaseplanPlus', 'acChar', 1, '/<msp:webapp/>');
	}
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0"  onload="onloadForm();">
<html:form action="/displayCaseplanComments?clr=Y" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|120"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Juvenile Casework - Process Caseplan - Review Caseplan</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class=spacer></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
			<ul>
				<li>Review goals and Placement Information(if present), then select Next button to enter comments.</li>
				<li>Select Back button to return to the previous page.</li>
			</ul>
		</td>
	</tr>
	<tr id='reqFieldsInstruct'> 
		<td class="required"><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border=0 width=10 height=9>&nbsp;Required Fields</td> 
	</tr> 
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>
<div class=spacer></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    	<td valign='top'>
	    	<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	    		<tiles:put name="tabid" value="goalstab"/>
	    		<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	    	</tiles:insert>				

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>
						<div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
									<tiles:insert page="../caseworkCommon/casePlanTabs.jsp" flush="true">
										<tiles:put name="tabid" value="Caseplan"/>
										<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
									</tiles:insert>				
								</td>
							</tr>
							<tr>
								<td bgcolor='#33cc66'><img src='../../images/spacer.gif' width=5></td>
							</tr>
						</table>

						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td valign='top' align='center'>
									<div class=spacer></div>
									<table width="98%" border=0 cellpadding=0 cellspacing=0> 
										<tr>
											<td>
												<logic:notEqual name="caseplanForm" property="placementInfoExist" value="N">
													<tiles:insert page="placementInfoTile.jsp" flush="true">
														<tiles:put name="tableHeader" value="Review Caseplan - Placement Information"/>
														<tiles:put name="isExpandable" value="true"/>
														<tiles:put name="placementInfo"  beanName="caseplanForm" beanProperty="currentPlacementInfo"/>	
													</tiles:insert>
												</logic:notEqual>
											</td>
										</tr>
									</table>
      
     								<div class='spacer'></div>
				            	    <table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				                   		<tr>
				                			<td valign=top class=detailHead colspan=6>
				                				<table width='100%' cellpadding=0 cellspacing=0>
				                  					<tr>
				                    					<td width='1%'><a href="javascript:showHideMulti('reviewCaseplanPlus', 'acChar', 1,'/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="reviewCaseplanPlus"></a></td>
				                    					<td class=detailHead colspan=6>&nbsp;Review Caseplan - JPO Activity List</td>
				                    				</tr>
				                  				</table>
				                			</td>
				                  		</tr>
				                		<tr id="acChar0" class=hidden>
                   							<td>
                       							<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
                       							<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
     
						                       		<table cellpadding='2' cellspacing='1' width='100%'>
						                       			<logic:notEmpty name="caseplanForm" property="cpActivities">
						                          			<tr bgcolor='#cccccc'>
						                          				<td class="subhead" valign=top nowrap><bean:message key="prompt.entryDate"/>
						                            				<jims:sortResults beanName="caseplanForm" results="cpActivities" primaryPropSort="activityDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="1" />				
						                          				</td>
						                          				<td class="subhead" valign=top nowrap><bean:message key="prompt.activity"/>
						                            				<jims:sortResults beanName="caseplanForm" results="cpActivities" primaryPropSort="activityDesc" primarySortType="STRING"  sortId="2" />	
						                          				</td>
						                          				<td class="subhead" valign=top nowrap><bean:message key="prompt.comments"/>
						                            				<jims:sortResults beanName="caseplanForm" results="cpActivities" primaryPropSort="comments" primarySortType="STRING"  sortId="3" />	
						                          				</td>
						                          			</tr>
	       
					                         				<logic:iterate id="activityIndex" name="caseplanForm" property="cpActivities" indexId="index">
				                         					<%-- Begin Pagination item wrap --%>
						                   						<pg:item>
						                   							<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
					                                      				<td><bean:write name="activityIndex" property="activityDate" formatKey="date.format.mmddyyyy" /></td>       
						                   								<td valign=top><bean:write name="activityIndex" property="activityDesc" /></td>
						                   								<td valign=top><bean:write name="activityIndex" property="comments" /></td>
						                   							</tr>
						                   						</pg:item>
				                   						   <%-- End Pagination item wrap --%>
															</logic:iterate>
                       									</logic:notEmpty>
       
						                       			<logic:empty name="caseplanForm" property="cpActivities">
															<tr>
																<td>No caseplan related activities available.</td>
															</tr>
						                       			</logic:empty>
                          							</table>
	      
	                          							<%-- Begin Pagination navigation Row--%>
				                  					<table align="center">
                					  		  			<tr>
                  		    								<td>
                  		    									<pg:index>
                  		    										<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
                  		    											<tiles:put name="pagerUniqueName" value="pagerSearch"/>
                  		    											<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
                  		    										</tiles:insert>
                  		    			 						</pg:index>
                  		  		    						</td>
                  				    					</tr>
                  				  					</table>
                    		  						<%-- End Pagination navigation Row--%>
                        						</pg:pager>
											</td>
                   						</tr>
               						</table>
               						<div class='spacer'></div>									
           						</td>
           					</tr>
           					<tr>
								<td valign='top' align='center'>
									<tiles:insert page="goalListTile.jsp" flush="true">
										<tiles:put name="mode" value="review"/>
										<tiles:put name="goalList"  beanName="caseplanForm" beanProperty="currentCaseplan.goalList"/>	
									</tiles:insert>
									<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
									<table width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
												<html:submit property="submitAction"><bean:message key="button.next"/></html:submit>
												<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
											</td>
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
<%-- END DETAIL TABLE --%>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>