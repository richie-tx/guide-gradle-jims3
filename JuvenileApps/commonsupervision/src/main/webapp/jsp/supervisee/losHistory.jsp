<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 05/28/2008	 D Williamson	Create JSP -->
<!-- 12/23/2009  Dawn Gibler    #63105 add feature checks around Delete and Correct buttons -->
<!-- 12/28/2009  C Shimek       Revised delete button coding to retain correct alignment -->
<!-- 12/30/2009  C Shimek       #63105 Revised coding to hide radio select button if user does not have update feature(s) -->
<!-- 03/01/2010  Dawn Gibler    Added error table -->
<!-- 05/18/2010  Dawn Gibler    Added check of current LOS to determine if correct button should display -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.Features" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - supervisee/losEntry.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script>
function checkSelect(theForm, sentToState, currentLOS){
	var buttonExist = document.getElementById("deleteButton");
	if (document.getElementById("deleteButton") != null) {
		show("deleteButton", 0);
     	if (sentToState == false && typeof(document.forms[0].deleteWOR) == "undefined"){
           show("deleteButton", 1);
     	}
	}
	if (document.getElementById("correctButton") != null) {
		show("correctButton", 0);
     	if (currentLOS == false){
           show("correctButton", 1);
     	}
	}	
		
}

function checkForSingleResult() {
    var rbs = document.getElementsByName("selectedValue");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkForSingleResult()">
<html:form action="/handleLOS" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|10">
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
    <pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
        maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
    <input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign="top">
							<!--tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
					   		   <tiles:put name="tab" value="caseloadTab"/> 
				     	    </tiles:insert>
							<!--tabs end-->
						</td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
				<!-- BEGIN BLUE BORDER TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
						<tiles:insert page="../common/superviseeHeader.jsp" flush="true"></tiles:insert>	
<!-- END SUPERVISEE INFORMATION TABLE  -->	
					</td>
				</tr>	
<!-- BEGIN GREEN TABS TABLE -->		
				<tr>
					<td valign="top" align="center"> 
							<!--casefile tabs start-->
							<table width="98%" border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign="top">
										<!--tabs start-->
										<tiles:insert page="../common/caseloadCSCDSubTabs.jsp" flush="true">
										    <tiles:put name="tab" value="SuperviseeTab" />
									    </tiles:insert>
										<!--tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
							</table>
							<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign="top" align="center">
										<!-- BEGIN HEADING TABLE -->
										<div class="header"><bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.LOS"/>&nbsp;<bean:message key="prompt.history"/></div>
										<div class="confirm">
											<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.CONFIRM_UPDATE%>">
												LOS record successfully corrected.
                                            </logic:equal>
											<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.CONFIRM_DELETE%>">
												LOS record successfully deleted.
											</logic:equal>
										</div>
										<logic:equal name="superviseeForm" property="allowLOSUpdates" value="true">
											<div class="instructions"><li>Select an LOS record and click the appropriate button below.</li></div>
										</logic:equal>
										<div class="spacer"></div>
										<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
									<table width="98%" align="center">							
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>		
									</table>
<!-- END ERROR TABLE -->										
										<!-- BEGIN LOS NAME INFORMATION TABLE -->
										<div class="borderTableBlue" style="width:98%">
										<table width="100%" border="0" cellpadding="2" cellspacing="1" align="center" class="notFirstColumn">
											<tr class="detailHead">
											<logic:equal name="superviseeForm" property="allowLOSUpdates" value="true">
											  	<td width="1%"></td>
											 </logic:equal>
											  <td><bean:message key="prompt.effectiveDate"/>
                                                  <jims2:sortResults beanName="superviseeForm" results="losHistories" primaryPropSort="losEffectiveDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="1" levelDeep="2" />
                                              </td>
											  <td><bean:message key="prompt.supervisionLevel"/>
                                                  <jims2:sortResults beanName="superviseeForm" results="losHistories" primaryPropSort="supervisionLevelDesc" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="2" levelDeep="2" />
                                              </td>
											  <td><bean:message key="prompt.comments"/>
                                                  <jims2:sortResults beanName="superviseeForm" results="losHistories" primaryPropSort="comments" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="3" levelDeep="2" />
                                              </td>
											  <td><bean:message key="prompt.userName"/>
                                                  <jims2:sortResults beanName="superviseeForm" results="losHistories" primaryPropSort="userName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="4" levelDeep="2" />
                                              </td>
											  <td><bean:message key="prompt.entryDate"/>
                                                  <jims2:sortResults beanName="superviseeForm" results="losHistories" primaryPropSort="entryDate" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="5" levelDeep="2" />
                                              </td>
											</tr>
        			                       <logic:notEmpty name="superviseeForm" property="losHistories">	
								              <logic:iterate id="superviseeIndex" name="superviseeForm" property="losHistories" indexId="index">
									             <pg:item>
													<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
														<logic:equal name="superviseeForm" property="allowLOSUpdates" value="true">
													  		<td><input type="radio" name="selectedValue" value='<bean:write name="superviseeIndex" property="supervisionLevelHistoryId" />' onclick="checkSelect(this.form,<bean:write name='superviseeIndex' property='sentToState' />,<bean:write name='superviseeIndex' property='currentLOS' />)" ></td>
													  	</logic:equal>
													  <td><bean:write name="superviseeIndex" property="losEffectiveDate" formatKey="date.format.mmddyyyy" /></td>
													  <td><bean:write name="superviseeIndex" property="supervisionLevelDesc" /></td>
													  <td><bean:write name="superviseeIndex" property="comments" /></td>
													  <td><bean:write name="superviseeIndex" property="userName" /></td>
													  <td><bean:write name="superviseeIndex" property="entryDate" formatKey="date.format.mmddyyyy" /></td>
													</tr>
												 </pg:item>
        				                      </logic:iterate>
        			                       </logic:notEmpty>
										</table>
<!-- END LOS INFORMATION TABLE -->
										</div>
<!-- BEGIN PAGINATION NAVIGATOIN ROW -->
									<table align=center>
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
<!-- END PAGINATION NAVIGATOIN ROW -->
							<!--button start -->
										<table border="0" width="1%" align="center" nowrap="nowrap">
											<tr>
												<logic:notEqual name="superviseeForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">   
													<td width="1%">
													    <html:submit property="submitAction" onclick="javascript: disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>
                                                	</td>   
												</logic:notEqual>
                                                <logic:notEmpty name="superviseeForm" property="losHistories">	
                                                   	<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_LOS_CORRECT%>'>
														<td width="1%" id="correctButton" class="hidden">	
															<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedValue', 'Please select a LOS.') && disableSubmit(this, this.form)"><bean:message key="button.correct"/></html:submit>
														</td>
													</jims2:isAllowed>
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_LOS_DELETE_WITHOUT_RESTRC%>'>
														<td width="1%">	
															<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedValue', 'Please select a LOS.') && disableSubmit(this, this.form)"><bean:message key="button.delete"/></html:submit>
														 	<input type="hidden" name="deleteWOR" value="" />
														</td>														 	
													</jims2:isAllowed> 
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_LOS_DELETE_WITH_RESTRC%>' >
														<td id="deleteButton" class="hidden"> 
           													<html:submit property="submitAction"><bean:message key="button.delete"/></html:submit>
														</td> 
													</jims2:isAllowed>
												</logic:notEmpty>
												<logic:notEqual name="superviseeForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">         
													<td width="1%"> 
													    <html:submit property="submitAction" onclick="javascript: disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>&nbsp;
                                                    </td>
												</logic:notEqual> 
											</tr>
										</table>
										<!--button end -->
									</td>
								</tr>
							</table>
							<div class="spacer4px"></div>
							<!-- END DETAIL TABLE -->
						</td>
					</tr>
				</table>
				<br>
			</td>
		</tr>
	</table>
<br>
<!--casefile tabs end-->
<!-- END  TABLE -->
<!-- BEGIN NOTES
   Back and Cancel not available when confirmation message displays.
END NOTES -->
</div>
</pg:pager>
<br>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
