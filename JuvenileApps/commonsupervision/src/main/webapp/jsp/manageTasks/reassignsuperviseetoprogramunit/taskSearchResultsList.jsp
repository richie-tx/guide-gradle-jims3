<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Task Search List-->
<!-- 03/08/2007	 Hien Rodriguez - Create JSP -->
<!-- 09/19/2007	 Richard Young  - Un-comment the logic empty tags -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script>

function toggleButtons(el){
	if (el.checked){
		switch (el.value)
		
		{
		
			case "continue":
			show("continueButton", 1, "inline")
			show("acceptButton", 0)
			break

			default:
			show("acceptButton", 1, "inline")
			show("taskDetailsButton", 1, "inline")
			show("continueButton", 0)
		}
	}
}

</script>

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
								<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="superviseeName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" />	
							</td>
							<td valign="top"><bean:message key="prompt.SPN" />
								<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="defendantId" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="2" />								
							</td>
							<td valign="top"><bean:message key="prompt.sev" />
								<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="severityLevel" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="3" />
							</td>
							<td valign="top"><bean:message key="prompt.date" />
								<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="createDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="4" />
							</td>
							<td valign="top"><bean:message key="prompt.status" />
								<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="statusId" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
							</td>
							<td valign="top"><bean:message key="prompt.subject" />
								<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="taskSubject" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
							</td>
							<td valign="top"><bean:message key="prompt.dueDate" />
								<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="dueDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="7" />
							</td>
<%-- This sort tag may need to be changed to single STRING instead of 2 STRINGs --%>							
							<td valign="top"><bean:message key="prompt.CRT" />
								<jims2:sortResults beanName="tasksSearchForm" results="taskResultList" primaryPropSort="courtId" primarySortType="STRING" secondPropSort="courtId2" secondarySortType="STRING" defaultSortOrder="ASC" sortId="8" />							
							</td>
						</tr>
						<%int RecordCounter = 0;
						String bgcolor = "";%>
								
						<logic:iterate id="taskResultListIndex" name="tasksSearchForm" property="taskResultList">
						<%RecordCounter++; %>
						<pg:item>
						<logic:notEqual name="taskResultListIndex" property="taskPastDueInd" value="true">
								<logic:notEqual name="taskResultListIndex" property="taskNearingDue" value="24">
								  <tr class=<% bgcolor = "alternateRow";
												if (RecordCounter % 2 == 1)
													bgcolor = "normalRow";
												out.print(bgcolor);%>>
								</logic:notEqual>
							</logic:notEqual>
						<logic:equal name="taskResultListIndex" property="taskPastDueInd" value="true">
								<tr class="taskPastDue" title="This task is past due.">
							</logic:equal>					
							<logic:equal name="taskResultListIndex" property="taskNearingDue" value="24">
								<tr class="taskNearingDue" title="This task is due within 24 hours.">
							</logic:equal>
									<td width="1%"><input type="radio" name="taskId" value=<bean:write name="taskResultListIndex" property="taskId"/> taskStatus="<bean:write name="taskResultListIndex" property="statusId"/>" isPersonal="0" onclick="toggleButtons(this)" /></td>
									<td>
									
									<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&superviseeId=<bean:write name="taskResultListIndex" property="defendantId"/>">
										<bean:write name="taskResultListIndex" property="superviseeName" /></a></td>
									<td><bean:write name="taskResultListIndex" property="defendantId" /></td>
									<td><bean:write name="taskResultListIndex" property="severityLevel" /></td>
									<td><bean:write name="taskResultListIndex" property="createDate" formatKey="date.format.mmddyyyy" /></td>
									<td><bean:write name="taskResultListIndex" property="statusId" /></td>
									<td><bean:write name="taskResultListIndex" property="taskSubject" /></td>
									<td><bean:write name="taskResultListIndex" property="dueDate" formatKey="date.format.mmddyyyy" /></td>
									<td>
										<bean:write name="taskResultListIndex" property="courtId" />
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
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
											<bean:message key="button.back" /></html:submit>
									<%--<html:submit property="submitAction" onclick="return validateRadios(this.form, 'spn', 'Please select a name.') && disableSubmit(this, this.form);">
											<bean:message key="button.continueTask"></bean:message>
										</html:submit>&nbsp; 
										<html:submit property="submitAction" onclick="return validateRadios(this.form, 'spn', 'Please select a name.') && disableSubmit(this, this.form);">
											<bean:message key="button.acceptTask"></bean:message>
										</html:submit>&nbsp; 
										<html:submit property="submitAction" onclick="alert('This option is not available yet')">
											<bean:message key="button.transferTask"></bean:message>
										</html:submit>&nbsp; 
										<html:submit property="submitAction" onclick="return validateRadios(this.form, 'spn', 'Please select a name.') && disableSubmit(this, this.form);">
											<bean:message key="button.closeTask"></bean:message>
										</html:submit>&nbsp; 
										<html:submit property="submitAction" onclick="return validateRadios(this.form, 'spn', 'Please select a name.') && disableSubmit(this, this.form);">
											<bean:message key="button.viewOrder"></bean:message>
										</html:submit>&nbsp;--%>
										
										<html:submit styleId="acceptButton" styleClass="hidden" property="submitAction" onclick="return validateRadios(this.form, 'taskId', 'Please select a task.') && disableSubmit(this, this.form);">
											<bean:message key="button.acceptTask"></bean:message>
										</html:submit>
										
										<html:submit styleId="continueButton" styleClass="hidden" property="submitAction" onclick="return validateRadios(this.form, 'taskId', 'Please select a task.') && disableSubmit(this, this.form);">
											<bean:message key="button.continueTask"></bean:message>
										</html:submit>
										
										<html:submit styleId="taskDetailsButton" styleClass="hidden" property="submitAction" onclick="return validateRadios(this.form, 'taskId', 'Please select a task.') && disableSubmit(this, this.form);">
											<bean:message key="button.taskDetails"></bean:message>
										</html:submit>
										
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
											<bean:message key="button.cancel"></bean:message>
										</html:submit>
									</td>
								</tr>
							</table>
<!-- END BUTTON TABLE -->