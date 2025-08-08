<%-- 11/09/2005	 Aswin Widjaja - Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>

<tiles:importAttribute name="titleIVEQuestion"/>

<table cellpadding="2" cellspacing="1" width="100%" class="borderTableGrey">
	<tr>
		<td colspan="4" bgcolor="999999" align="left"><strong>Parental Deprivation Section</strong></td>
	</tr>
	
	<%-- AFDC Determination Worksheet  --%>
	<tr>  
		<td colspan="4">
			<%-- BEGIN Worksheet TABLE --%>
			<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" class="detailHead" align="left">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="1%">
									<a href="javascript:showHideMulti('Deprivation','pDep',1, '/<msp:webapp/>')" border="0">
										<img border="0" src="/<msp:webapp/>images/expand.gif" name="Deprivation">
									</a>
								</td>
								<td class="detailHead" align="left">&nbsp;Parental Deprivation Section</td>
							</tr>
						</table>
					</td>
				</tr>

				<%-- Income of Certified Group section of worksheet --%>
				<tr id="pDep0" class="hidden">
					<td valign="top">
						<table width="100%" cellpadding="4" cellspacing="0">
							<tr> 
								<td> 
									<table width='100%' cellpadding="2"  cellspacing='1'>
										<tr>
											<td></td>
											<td class="formDeLabel" align="left">One of the parents is a stepparent:</td>
											<td class=formDe align="left"><bean:write name="titleIVEQuestion" property="isOneParentIsStepparentDisp"/> </td>
										</tr>

										<tr>
											<td>2a.</td>
											<td  class="formDeLabel">If parental deprivation existed, mark the basis for the deprivation below:
												<br>&nbsp;&nbsp;Death or absence of parent(s)
												<br>&nbsp;&nbsp;Incapacity/disability of a parent
												<br>&nbsp;&nbsp;Primary Wage Earner (PWE) Underemployment based upon either:
												<br>&nbsp;&nbsp;&nbsp;&nbsp;PWE worked less than 100 hours a month on average , or
												<br>&nbsp;&nbsp;&nbsp;&nbsp;PWE monthly gross income is equal to or less than the applicable amount on the Underemployed Parent Income Chart.
											</td>
											<%-- Check boxes, remember to set them all to false in display action--%>
											<td  class="formDe">&nbsp;
												<br><bean:write name="titleIVEQuestion" property="isDeathOrAbsenceDisp"/> 
												<br><bean:write name="titleIVEQuestion" property="isIncapacityOrDisabilityOfParentDisp"/>
												<br><bean:write name="titleIVEQuestion" property="isPrimaryWageEarnerUnderemploymentDisp"/>
												<br><bean:write name="titleIVEQuestion" property="isPweWorkedLessThen100HoursDisp"/>
												<br><bean:write name="titleIVEQuestion" property="isPweIncomeLessThanUnderemployedLimitDisp"/>
											</td>
										</tr>
										
										<logic:equal name="titleIVEQuestion" property="primaryWageEarnerUnderemployment" value="true">
										<tr id="parCheck">
											<td></td>
											<td colspan="1">                                          					
												<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
													<tr>
														<td colspan=2 class=detailHead align="left">Underemployed Parent Checklist</td>
													</tr>

													<tr>
														<td class=formDeLabel width='1%' nowrap>1) Who is the Primary Wage Earner (PWE) in the home of removal?</td>
														<td class=formDe><bean:write name="titleIVEQuestion" property="pweRelationshipToJuvenile"/></td>
													</tr>

													<tr>
														<td class=formDeLabel width='1%'>2) If the PWE has steady employment, did the PWE work less than 100 hours during the month of removal (or review)?</td>
														<td class=formDe><bean:write name="titleIVEQuestion" property="isPweWorkedSteadyLessThan100HoursDisp"/></td>
													</tr>

													<tr>
														<td class=formDeLabel width='1%'>3) If the PWE works irregularly, does the PWE work less than 100 hours per month on average?</td>
														<td class=formDe><bean:write name="titleIVEQuestion" property="isPweWorkedIrregularLessThan100HoursAvgDisp"/></td>
													</tr>

													<tr>
														<td class=formDeLabel width='1%'>4) If the PWE works more than 100 hours per month on average, what is their gross monthly earned income?</td>
														<td class=formDe>$&nbsp;<bean:write name="titleIVEQuestion" property="pweGrossMonthlyIncomeForOver100Hours" formatKey="currency.format"/></td>
													</tr>
												</table>
											</td>
										</tr>
										</logic:equal>
									</table>
								</td>
								
							</tr>
							
						</table>
					</td>
				</tr>
				
			</table>
			<%-- END Worksheet TABLE --%>
		</td>
	</tr>	
	<tr>
		<td width="1%"></td>
		<td class="formDeLabel" width="78%">Did Parental Deprivation exist at the time of removal?</td>
		<td class="formDe" colspan="2"><bean:write name="titleIVEQuestion" property="parentalDepExist"/></td>
	</tr>							
</table>
