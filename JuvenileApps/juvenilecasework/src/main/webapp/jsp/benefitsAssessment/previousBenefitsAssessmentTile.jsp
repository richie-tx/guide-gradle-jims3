<%-- 11/09/2005	 Aswin Widjaja - Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>

<tiles:importAttribute name="previousBenefitsAssessments"/>
<bean:size id="prevBenCount" name="previousBenefitsAssessments" />

<div class='spacer'></div>
<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" align='center'>
	<tr>
		<td colspan='4' class='detailHead'><bean:message key="prompt.previousBenefitsAssessments"/></td>
	</tr>
	<tr>
		<td>

			<logic:notEmpty name="previousBenefitsAssessments">
				<logic:greaterThan name="prevBenCount" value="10">
				  <%-- make it a scrolling region with header titles locked --%>
					<div class='scrollingDiv200'>
	   			<table cellpadding='2' cellspacing='1' width='100%' class='notFirstColumn'>
		   			<thead>
				</logic:greaterThan>
			</logic:notEmpty>
			
			<logic:lessThan name="prevBenCount" value="10">
   			<table cellpadding='2' cellspacing='1' width='100%'>
			</logic:lessThan>

				<logic:empty name="previousBenefitsAssessments">
				  <tr class='formDeLabel'><td colspan='3'>No Previous Benefits Assessments</td></tr>
				</logic:empty>

				<logic:notEmpty name="previousBenefitsAssessments">
					<tr class='formDeLabel'>
						<td valign='top'><bean:message key="prompt.entryDate"/></td>
						<td valign='top'>Title IV-E Candidate?</td>
						<td valign='top'>Guardian</td>
					</tr>

					<logic:greaterThan name="prevBenCount" value="10">
						</thead>
						<tbody>
					</logic:greaterThan>

					<logic:iterate indexId="index" id="prevAssessmentIter" name="previousBenefitsAssessments">
 						<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
 							<td valign='top'><a href="/<msp:webapp/>displayBenefitsAssessmentView.do?currentAssessment.assessmentId=<bean:write name='prevAssessmentIter' property='assessmentId'/>"><bean:write name="prevAssessmentIter" property="entryDate" formatKey="date.format.mmddyyyy"/></a></td>													
 							
 							<%-- Define the values as local variables for scripting--%>
 							<bean:define id="isEligibleForTitleIVe" name="prevAssessmentIter" property="eligibleForTitleIVe" type="java.lang.Boolean"/>
 							
 							<td valign='top'><%out.println(UIUtil.getYesNo(isEligibleForTitleIVe.booleanValue(), false));%></td>
 							<td valign='top'><bean:write name="prevAssessmentIter" property="guardianName"/></td>
					 </tr>
					</logic:iterate>

					<logic:greaterThan name="prevBenCount" value="10">
						</tbody>
					</logic:greaterThan>
				</logic:notEmpty>
			</table>

			<logic:greaterThan name="prevBenCount" value="10">
				</div>
			</logic:greaterThan>

		</td>
	</tr>
</table>

<%-- End Pagination Closing Tag --%>

