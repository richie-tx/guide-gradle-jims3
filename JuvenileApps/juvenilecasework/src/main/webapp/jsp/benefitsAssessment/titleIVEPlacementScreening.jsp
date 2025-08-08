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

<tiles:importAttribute name="titleIVEQuestion"/>


<%-- Begin Title IV-e Assessment TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead" align="left">Title IV-E Placement Screening</td>
		</tr>
		<tr>
			<td>
				<table cellpadding="2"  cellspacing="0" width="100%" class="borderTableGrey">
					<tr>
						<td bgcolor="999999" align="left"><strong>AFDC Requirements</strong></td>
					</tr>
					<tr>
						<td align="center">
							<table cellpadding="2" cellspacing="1" width="100%">
								<tr>
									<td></td>
									<td class="formDeLabel">Date Taken</td>
									<td class="formDe" colspan="2"><bean:write name="titleIVEQuestion" property="dateTaken" formatKey="date.format.mmddyyyy"/></td>
								</tr>
								<tr>
									<td width="1%">1.</td>
									<td class="formDeLabel">Is the child a U.S. citizen, Legal Permanent Resident, or Qualified alien? If a permanent resident, attach a copy of the INS Form I-551 (green card)</td>
									<td class="formDe" colspan="2"><bean:write name="titleIVEQuestion" property="isLegalResidentDisp"/></td>
								</tr>
								<tr><td><div class='spacer'></div></td></tr>
	
								<%-- new deprivation section --%>
								<tr>
									<td valign="top">2.</td>
									<td colspan="3">
										<tiles:insert page="/jsp/benefitsAssessment/titleIVEParentalDeprivation.jsp" flush="true">
											<tiles:put name="titleIVEQuestion" beanName="titleIVEQuestion"/>
										</tiles:insert>
									</td>
								</tr>
								<tr><td><div class='spacer'></div></td></tr>
								<%-- end new deprivation section --%>
    
								<tr>
									<td valign="top">3.</td>
									<td class="formDeLabel">Was the child living with a parent or specified relative at the time of removal, or was the child living with a parent or specified relative with legal managing conservatorship within six months of removal?</td>
									<td class="formDe" colspan="2"><bean:write name="titleIVEQuestion" property="wasChildLivingWithParentDisp"/></td>
								</tr>
								<logic:equal name="titleIVEQuestion" property="wasChildLivingWithParent" value="true">
									<tr id="removalGuardian">
										<td>&nbsp;</td>
										<td colspan=3>
											<table cellpadding="2" cellspacing="1" width="100%">
												<tr>
													<td class="formDeLabel">Name:</td>
													<td class="formDe"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.guardian.name.formattedName"/></td>
												</tr>
												<tr>
													<td class="formDeLabel">Relationship:</td>
													<td colspan="2" class="formDe"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.guardian.relationship"/></td>
												</tr>
											</table>
										</td>
									</tr>
								</logic:equal>
								<tr><td><div class='spacer'></div></td></tr>
    
								<tr>
									<td valign="top">4.</td>
									<td colspan="3">
										<table cellpadding="2" cellspacing="1" width="100%" class="borderTableGrey">
											<tr>
												<td colspan="3" bgcolor="999999" align="left"><strong>AFDC Income Determination Worksheet</strong></td>
											</tr>
		 
											<tr>  <%-- AFDC Determination Worksheet  --%>
												<td colspan="3">
													<%-- BEGIN Worksheet TABLE --%>
													<tiles:insert page="/jsp/benefitsAssessment/titleIVEAFDCIncomeDeterminationWorksheet.jsp" flush="true">
														<tiles:put name="titleIVEQuestion" beanName="titleIVEQuestion" />	
													</tiles:insert>
													<%-- END Worksheet TABLE --%>
												</td>
											</tr>
											<tr>
												<td width="1%"></td>
												<td class="formDeLabel" width="78%">Did the certified group's income meet the applicable AFDC income limit requirements on the AFDC Income Limits Chart?</td>
												<td class="formDe" nowrap>
													<bean:define id="isAfdcIncomeLimitsMet" name="titleIVEQuestion" property="afdcIncomeLimitsMet" type="java.lang.Boolean"/>
													<%out.println(UIUtil.getYesNo(isAfdcIncomeLimitsMet.booleanValue(), false));%>
												</td>
											</tr>
											<tr><td></td></tr>													
										</table>
									</td>
								</tr>

								<tr><td><div class='spacer'></div></td></tr>
								<tr>
									<td>5.</td>
									<td class="formDeLabel">Are the certified group's resources under the $10,000 limit?</td>
									<td class="formDe" nowrap colspan="2">
										<bean:define id="isGroupUnder10kLimit" name="titleIVEQuestion" property="under10KLimit" type="java.lang.Boolean"/>
										<%out.println(UIUtil.getYesNo(isGroupUnder10kLimit.booleanValue(), false));%>
									</td>
								</tr>

								<tr>
									<td>6.</td>
									<td class="formDeLabel">This child appears to meet AFDC eligibility criteria.</td>
									<td class="formDe" nowrap colspan="2">
										<bean:define id="doesChildAppearsToMeetAFDCEligibility" name="titleIVEQuestion" property="childMeetsEligibilityCriteria" type="java.lang.Boolean"/>
										<%out.println(UIUtil.getYesNo(doesChildAppearsToMeetAFDCEligibility.booleanValue(), false));%>
									</td>
								</tr>             
								<tr>
								  <td></td>
									<td class="detailHead" align="left" colspan='3'><strong>Source(s) used to gather information</strong></td>
								</tr>
								<tr>
									<td class="formDeLabel" colspan="3">The following source(s) were used to gather this information</td>
									<td class=formDe><bean:write name="titleIVEQuestion" property="infoSource"/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>	
	</table>
	<div class=spacer></div>
<%-- END Title IV-e Assessment TABLE --%>
