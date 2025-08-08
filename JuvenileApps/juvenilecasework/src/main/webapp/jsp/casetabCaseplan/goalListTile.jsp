<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/21/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%-- by default, mode is review. The other option of mode is modify --%>
<%-- This is used to change the title of the table only --%>
<tiles:importAttribute name="mode" ignore="true"/>


<html:base />

<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td class=detailHead colspan=2 nowrap>
			<logic:equal name="mode" value="modify">Modify Caseplan - Existing Goals</logic:equal>
			<logic:notEqual name="mode" value="modify">Review Caseplan - Goals</logic:notEqual>
		</td>
	</tr>

	<logic:empty name="caseplanForm" property="jpoReviewGoalList">
  	<tr><td>No Goals exist for Caseplan.</td></tr>
	</logic:empty>

	<logic:notEmpty name="caseplanForm" property="jpoReviewGoalList">
	<tr>
		<td valign=top align=center>
			<table width='100%' cellpadding="2" cellspacing="1" border=0 >

  			<logic:iterate id = "goal" name="caseplanForm" property="jpoReviewGoalList">
  				<tr>
  					<td class="formDeLabel" width="1%" nowrap>Goal #</td>
  					<td class="formDe"><bean:write name="goal" property="goalId"/></td>
  					<td class="formDeLabel" width="1%" nowrap>Status</td>
  					<td class="formDe"><bean:write name="goal" property="statusStr"/></td>
  				</tr>
  				<tr>
  					<td class="formDeLabel" width="1%" nowrap>Domain Type</td>
  					<td class="formDe"><bean:write name="goal" property="domainTypeStr"/></td>
  					<td class="formDeLabel" width="1%" nowrap>Time Frame</td>
  					<td class="formDe"><bean:write name="goal" property="timeFrameStr"/></td>
  				</tr>
  				<tr>
  					<td class="formDeLabel" valign="top" nowrap>Person(s) Responsible</td>
    					<logic:notEmpty name="goal" property="personsResponsibleIds">
      					<td class="formDe" colspan=3>
      						<logic:iterate name="goal" property="personsResponsibleIds" id="idIndex">
      							<bean:write name="idIndex"/>
  									<br>
      						</logic:iterate>
      					</td>
    					</logic:notEmpty>
  				</tr>  			
  				<tr>
  					<td class="formDeLabel" valign="top" nowrap>Goal</td>
  					<td class="formDe" colspan=3><bean:write name="goal" property="goal"/></td>
  				</tr>  
  				<tr>
  					<td class="formDeLabel" valign="top" nowrap>Describe progress/lack of progress</td>
  					<td class="formDe" colspan=3><bean:write name="goal" property="progressNotes"/></td>
  				</tr>  
  				<tr><td></td></tr>			
  			</logic:iterate>			
			</table>
		</td>
	</tr>	
	</logic:notEmpty>
</table>
