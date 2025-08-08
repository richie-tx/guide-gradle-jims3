<!-- Task Search List-->
<!-- 03/08/2007	 Hien Rodriguez - Create JSP -->
<!-- 09/19/2007	 Richard Young  - Un-comment the logic empty tags -->
<!-- 10/10/2008	 Richard Young  - 53124 Fix hyperlink to go to admin supervisee -->
<!-- 02/05/2009	 Richard Young  - 56839 Manage Task Close features - UI\PD -->
<!-- 02/10/2008	 Ryoung  - 56356 Tasklist - display buttons after Back button is used  -->
<!-- 05/27/2009	 Ryoung  - 57852 Tasklist - Manage Task - Create Task  -->
<!-- 01/05/2010	 CShimek         - 63122 revised sortResult for Status to DESC from ASC  -->
<!-- 07/09/2010	 RYoung          - 65955 remove severity level  -->
<!-- 07/16/2010	 RYoung          - #65955 Add case number to results screen  -->
<!-- 08/06/2010	 RYoung  - #66538 Allow code to use the OPEN status -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="naming.Features"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<%-- Begin Pagination Header Tag--%>
		<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
		<pg:pager
	    index="center"
	    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
	    maxIndexPages="10"
	    export="offset,currentPageNumber=pageNumber"
	    scope="request">
	    <input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<!-- BEGIN SEARCH USERS RESULTS SECTION -->
<logic:notEmpty name="tasksSearchForm" property="taskResultList">
	<table width="98%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center"><bean:size id="taskResultListSize" name="tasksSearchForm" property="taskResultList" /> <b> 
				<bean:write name="taskResultListSize" /></b>&nbsp;search results found.
			</td>
		</tr>
		<tr>
			<td>
				<div class="borderTableBlue" style="width:100%">
					<table width="100%" border="0" cellpadding="2" cellspacing="1">
						<tr class="detailHead">
							<td valign="top" width="1%"></td>
							<td valign="top"><bean:message key="prompt.name" />	
								<div>							
									<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="superviseeName" primarySortType="STRING" sortId="1" />	
								</div>
							</td>
							<td valign="top" nowrap><bean:message key="prompt.SPN" />
								<div>
									<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="defendantId" primarySortType="INTEGER" sortId="2" />								
								</div>
							</td>
							<td valign="top" nowrap><bean:message key="prompt.caseNum" />
								<div>
									<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="criminalCaseId" primarySortType="STRING" sortId="3" />								
								</div>
							</td>
							<td valign="top"><bean:message key="prompt.date" />
								<div>
									<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="createDate" primarySortType="DATE" sortId="4" />
								</div>
							</td>
							<td valign="top"><bean:message key="prompt.status" />
								<div>
									<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="status" primarySortType="STRING" secondPropSort="createDate" secondarySortType="DATE"  defaultSort="true" defaultSortOrder="DESC" sortId="5" />
								</div>
							</td>
							<td valign="top"><bean:message key="prompt.subject" />
							<div>
								<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="taskSubject" primarySortType="STRING" sortId="6" />
							</div>
							</td>
							<td valign="top"><bean:message key="prompt.dueDate" />
							<div>
								<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="dueDate" primarySortType="DATE" sortId="7" />
							</div>
							</td>
							<%-- This sort tag may need to be changed to single STRING instead of 2 STRINGs --%>							
							<td valign="top"><bean:message key="prompt.CRT" />
							<div>
								<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="courtId" primarySortType="STRING" secondPropSort="courtId2" secondarySortType="STRING" sortId="8" />							
							</div>
							</td>
						</tr>
						<logic:iterate id="taskResultListIndex" name="tasksSearchForm" property="taskResultList" indexId="index">
							<pg:item>
							<logic:notEqual name="taskResultListIndex" property="taskPastDueInd" value="true">
								<logic:notEqual name="taskResultListIndex" property="taskNearingDue" value="24">
									<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
			    				</logic:notEqual>
							</logic:notEqual>
							<logic:equal name="taskResultListIndex" property="taskPastDueInd" value="true">
								<tr class="taskPastDue" title="This task is past due.">
							</logic:equal>					
							<logic:equal name="taskResultListIndex" property="taskNearingDue" value="24">
								<tr class="taskNearingDue" title="This task is due within 24 hours."> 
							</logic:equal>
									<td width="1%" id="<bean:write name="taskResultListIndex" property="scenario"/>"><input type="radio" name="taskId" value="<bean:write name="taskResultListIndex" property="taskId"/>" taskStatus="<bean:write name="taskResultListIndex" property="statusId"/>" isPersonal="0" onclick="toggleButtons(this)" /></td>
									<td><a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name="taskResultListIndex" property="defendantId"/>">
											<bean:write name="taskResultListIndex" property="superviseeName" /></a></td>
									<td><bean:write name="taskResultListIndex" property="defendantId" /></td>
									<logic:notEqual name="taskResultListIndex" property="criminalCaseId" value="">
										<bean:define id="ccaseId" name="taskResultListIndex" property="criminalCaseId"/>
									</logic:notEqual>
									<td nowrap="nowrap" ><c:out value="${fn:substring(ccaseId,0 ,3)}"/>&nbsp;<c:out value="${fn:substring(ccaseId,3 ,15)}"/></td>
									<c:set var="ccaseId" value=""></c:set>
									<td><bean:write name="taskResultListIndex" property="createDate" formatKey="date.format.mmddyyyy" /></td>
									<td><bean:write name="taskResultListIndex" property="status" /></td>
									<td><bean:write name="taskResultListIndex" property="taskSubject" /></td>
									<td><bean:write name="taskResultListIndex" property="dueDate" formatKey="date.format.mmddyyyy" /></td>
									<td><bean:write name="taskResultListIndex" property="courtId" />
										<logic:notEqual name="taskResultListIndex" property="courtId2" value="">
											<bean:write name="taskResultListIndex" property="courtId" />
										</logic:notEqual>	
									</td>
								</tr>
							</pg:item>
						</logic:iterate>
					</table>
				</div>
			</td>
		</tr>
	</table>				
</logic:notEmpty>

 <logic:notEmpty name="tasksSearchForm" property="taskResultList">
        <div style="width:98%; text-align:left;"><span class="taskPastDue">Red records</span> indicate task is past due. <span class="taskNearingDue">Yellow records</span> indicate task is due within 24 hours. </div>
   </logic:notEmpty>
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
<!-- BEGIN BUTTON TABLE -->
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>
  
<br>							
							<table border="0" width="100%">							  
								<tr>
									<td align="center">
										<html:submit styleId="closeButton" styleClass="hidden" property="submitAction" onclick="return validateRadios(this.form, 'taskId', 'Please select a task.') && disableSubmit(this, this.form);">
											<bean:message key="button.closeTask"></bean:message>
										</html:submit>
																				
										<html:submit styleId="acceptButton" styleClass="hidden" property="submitAction" onclick="return validateRadios(this.form, 'taskId', 'Please select a task.') && disableSubmit(this, this.form);">
											<bean:message key="button.acceptTask"></bean:message>
										</html:submit>
										
										<html:submit styleId="continueButton" styleClass="hidden" property="submitAction" onclick="return validateRadios(this.form, 'taskId', 'Please select a task.') && disableSubmit(this, this.form);">
											<bean:message key="button.continueTask"></bean:message>
										</html:submit>
										
										<html:submit styleId="taskDetailsButton" styleClass="hidden" property="submitAction" onclick="return validateRadios(this.form, 'taskId', 'Please select a task.') && disableSubmit(this, this.form);">
											<bean:message key="button.taskDetails"></bean:message>
										</html:submit>
										
										<html:submit styleId="transferButton" styleClass="hidden" property="submitAction" onclick="return validateRadios(this.form, 'taskId', 'Please select a task.') && disableSubmit(this, this.form);">
											<bean:message key="button.transferTask"></bean:message>
										</html:submit> 
									</td>
								</tr>
								<tr>
									<td align="center">
										<jims2:isAllowed requiredFeatures='<%=Features.CSCD_TASKS_CREATE%>'>
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.createTask"/></html:submit>
										</jims2:isAllowed>
									 </td>
								</tr>
							</table>
<!-- END BUTTON TABLE -->