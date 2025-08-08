<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/13/2007	 Hien Rodriguez - Create JSP -->
<!-- 02/06/2008  L. Deen 		- defect#49259 fixed page titles to match prototypes -->
<!-- 03/30/2010  D  Williamson  - defect#64445 pre-select radio button when only 1 result  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - manageWorkgroup/searchResults.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
var workgroupID = "none" ;
	function enableButtons(hasOpenTask, workgroupIDin){
	        if( workgroupID != workgroupIDin )
	        {
	          workgroupID = workgroupIDin ;
	        }
	        
            if (hasOpenTask){
                show("viewTaskButton", 1, "inline")
                show("deleteButton", 0)

               }else{
               show("deleteButton", 1, "inline")
               show("viewTaskButton", 0)                                                                                                    
               }
             }

function executeViewAssociatedTasks(formName)
{
	return changeFormActionURL(formName, '/<msp:webapp/>handleTaskSearch.do?submitAction=<bean:message key="button.viewAssociatedTasks"/>&workgroupId=' + workgroupID ) ;
}

function checkForSingleResult() {
    var rbs = document.getElementsByName("workgroupId");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}
</script>

</head>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="checkForSingleResult()">
<html:form action="/handleWorkgroupSelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Workgroups/Manage_Workgroups.htm#|8">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign=top>
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
					<!--tabs start-->
					<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="setupTab"/>
					</tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign=top align=center>
						<table width=98% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
								<!--tabs start-->
									<tiles:insert page="../common/manageFeaturesTabs.jsp" flush="true">
										<tiles:put name="tabid" value="workgroupsTab"/>
									</tiles:insert>	
								<!--tabs end-->
								</td>
							</tr>
							
						</table>
						<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign=top align=center>
								<!-- BEGIN HEADING TABLE -->
									<table width=100%>
										<tr>							
							    			<td align="center" class="header">
												<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.workgroup" />&nbsp;<bean:message key="prompt.searchResults" />
											</td>						
						  				</tr>
									</table>
								<!-- END HEADING TABLE -->
								<%-- Begin Pagination Header Tag--%>
								<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
								<br>
								<pg:pager
								    index="center"
								    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
								    maxIndexPages="10"
								    export="offset,currentPageNumber=pageNumber"
								    scope="request">
								    <input type="hidden" name="pager.offset" value="<%= offset %>">
								<%-- End Pagination header stuff --%>
								<!-- BEGIN ERROR TABLE -->
									<table width=98% align=center>							
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>		
									</table>
								<!-- END ERROR TABLE -->
								<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td><ul>
                              					<li>Select a workgroup and the appropriate button below.</li>
                              					<li>Click a workgroup name to view its details.</li>
											</ul></td>
										</tr>										
									</table>
								<!-- END INSTRUCTION TABLE -->
									<table>
										<tr>
											<td align="center">
												<bean:size id="workgroupListSize" name="workgroupSearchForm" property="workgroupList"/>
												<b><bean:write name="workgroupListSize"/></b>&nbsp;workgroup(s) found in search results </td>            					
			            				</tr>
			            			</table>  
                    			<!-- BEGIN DETAIL TABLE -->	
									<table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
										<tr class="detailHead">
											<td width=1%></td>
											<td><bean:message key="prompt.name" />
												<jims2:sortResults beanName="workgroupSearchForm" results="workgroupList" primaryPropSort="workgroupName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
											</td>
											<td><bean:message key="prompt.type" />
												<jims2:sortResults beanName="workgroupSearchForm" results="workgroupList" primaryPropSort="workgroupTypeDesc" primarySortType="STRING" sortId="2" />
											</td>
											<td><bean:message key="prompt.description" />
												<jims2:sortResults beanName="workgroupSearchForm" results="workgroupList" primaryPropSort="workgroupDescription" primarySortType="STRING" sortId="3" />
											</td>
										</tr>
										<%int RecordCounter = 0;
										String bgcolor = "";%>  
										<logic:notEmpty name="workgroupSearchForm" property="workgroupList">	
											<logic:iterate id="workgroupListIndex" name="workgroupSearchForm" property="workgroupList">
												<pg:item>
												<tr
												class=<%RecordCounter++;
													bgcolor = "alternateRow";
													if (RecordCounter % 2 == 1)
														bgcolor = "normalRow";
													out.print(bgcolor);%>>
													<td width=1%><input type=radio name="workgroupId" value='<bean:write name="workgroupListIndex" property="workgroupId"/>' onclick="enableButtons(<bean:write name="workgroupListIndex" property="hasOpenTasks"/>, <bean:write name="workgroupListIndex" property="workgroupId"/>)" /></td>
													<td><a href="/<msp:webapp/>handleWorkgroupSelection.do?submitAction=<bean:message key="button.view"/>&workgroupId=<bean:write name="workgroupListIndex" property="workgroupId"/>">
														<bean:write name="workgroupListIndex" property="workgroupName" /></a></td>
													<td><bean:write name="workgroupListIndex" property="workgroupTypeDesc" /></td>
													<td><bean:write name="workgroupListIndex" property="workgroupDescription" /></td>							
												</tr>
												</pg:item>																
											</logic:iterate>
										</logic:notEmpty> 				
									</table>					
								<!-- END DETAIL TABLE -->
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
							                        
                      				<br>
                      			<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
												
												<jims2:isAllowed requiredFeatures="CS-WORKGRP-UPDATE">
													<html:submit property="submitAction" onclick="return validateRadios(this.form, 'workgroupId', 'Please select a workgroup name.') && disableSubmit(this, this.form);"><bean:message key="button.update"></bean:message></html:submit>&nbsp;
												</jims2:isAllowed>
												
												
													<span id="deleteButton" class="hidden"><jims2:isAllowed requiredFeatures="CS-WORKGRP-DELETE">	
														<html:submit  property="submitAction" onclick="return validateRadios(this.form, 'workgroupId', 'Please select a workgroup name.') && disableSubmit(this, this.form) ;"><bean:message key="button.delete"></bean:message></html:submit>&nbsp;
													</jims2:isAllowed></span>

												<span id="viewTaskButton" class="hidden">
												<jims2:isAllowed requiredFeatures="CS-TASKS-VIEW">
												<%-- Future feature - Not for release 0.2 - Defect #54316 - DAW
													<input type="button"  onclick="return executeViewAssociatedTasks(this.form.name)  && disableSubmit(this, this.form);" value='<bean:message  key="button.viewAssociatedTasks"/>'></input>&nbsp; --%>
													<input type="button"  onclick="javascript:openWindow('/<msp:webapp/>jsp/common/futureFeaturePopUp.jsp')" value='<bean:message  key="button.viewAssociatedTasks"/>'</input>&nbsp;
												</jims2:isAllowed></span>
												
												
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																																		 			
											</td>
										</tr>										  	
									</table>
								<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table><br>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</div>
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>
</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>