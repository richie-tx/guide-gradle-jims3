<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 06/28/2013	CShimek		ER#75613 Create JSP  --%>
<%-- 08/31/2015 RCapestani #29441 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Juvenile Profile Referrals) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%-- STYLE SHEET LINK --%>
<html:base />

<title><bean:message key="title.heading"/> - juvTabReferral - transferredOffensesCreateTile.jsp</title>

<%-- BEGIN OF TRANSFERRED OFFENSES TABLE--%> 
				<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 
					<tr> 
						<td class="detailHead"><bean:message key="prompt.transferredOffense" /> <bean:message key="prompt.info" /></td> 
					</tr> 
					<tr>
						<td>
							<table width='100%' border="0" cellpadding="2" cellspacing="1">
								<logic:notEmpty name="transferredOffenseForm" property="availableTransferredOffenseReferralList">
									<tr>
										<td class="formDeLabel" colspan="1" width="1%"  nowrap><bean:message key="prompt.diamond" /><bean:message key="prompt.transferredOffense"/>(s) <div>(Select One)</div></td>
										<td class="formDe" colspan="3">
											<logic:iterate id="troffIndex" name="transferredOffenseForm" property="availableTransferredOffenseReferralList" indexId="index">
												<div>
 													<logic:equal name="troffIndex" property="available" value="Y">
 													 <input type="radio" name="selectedTransfer" id='selectedTransfer-<bean:write name="troffIndex" property="offenseCode"/>' value='<bean:write name="troffIndex" property="referralNum"/>' />
 													 <input type="hidden" name="selectedOffense" id='selectedOffense' value="<bean:write name="transferredOffenseForm" property="selectedTransfer"/>" />
 													</logic:equal>
 													<logic:notEqual name="troffIndex" property="available" value="Y">
 														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 													</logic:notEqual>
 													<bean:write name="troffIndex" property="referralNum"/> - <bean:write name="troffIndex" property="offenseShortDesc"/> 
												</div>
											</logic:iterate>
											<input type="hidden" name="selValue" value="<bean:write name="transferredOffenseForm" property="selectedTransfer" />"  id="selTrfId" />
										</td> 
									</tr>
									<tr>
										<td class="formDeLabel" colspan="1" width="1%" nowrap><bean:message key="prompt.diamond" /><bean:message key="prompt.from" /> <bean:message key="prompt.county" /></td>
										<td class="formDe" colspan="1" width="30%">
											<html:select property="countyId" styleId="countySelID">
												<html:option key="select.generic" value="" />
												<html:optionsCollection property="countiesList" value="code" label="description"/>				
											</html:select>
											<html:hidden styleId='selectedCounty' name="transferredOffenseForm" property="countyId"/>											
										</td>
										
										<td class="formDeLabel" colspan="1" width="1%" nowrap>
											<div id="personIdReq">
												<bean:message key="prompt.diamond"/><bean:message key="prompt.sendingCountyPID"/>
											</div> 
											<div id="personIdNReq">
												<bean:message key="prompt.sendingCountyPID"/>
											</div> 
										</td>
										<td class="formDe" colspan="1">
											<html:text name="transferredOffenseForm" size="7" maxlength="7" property="personId" styleId="personId"/>
										</td>			 
									</tr>
									<tr>
										<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.diamond" /><bean:message key="prompt.offenseCode" /></td>
										<td class="formDe" colspan="3">
											<html:text name="transferredOffenseForm" size="6" maxlength="6" property="offenseId" styleId="offCodeID"/>
											<html:submit property="submitAction" onclick="return validateCode()"><bean:message key="button.validateOffenseCode" /></html:submit>&nbsp;Or&nbsp;
											<html:submit property="submitAction"><bean:message key="button.searchForOffenseCode" /></html:submit>
											<input type="hidden" name="validateMsg" value="<bean:write name="transferredOffenseForm" property="validateMsg" />"  id="valOffMsgId" />
										</td>		 
									</tr>
									<tr>
										<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.offense" /> <bean:message key="prompt.description" /></td>
										<td class="formDe" colspan="3"><bean:write name="transferredOffenseForm" property="offenseDesc" /></td>		 
									</tr>
									<tr>								
										<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.diamond" /><bean:message key="prompt.offenseDate" /></td>
										<td class="formDe" colspan="3">
											<html:text name="transferredOffenseForm" size="10" maxlength="10" property="offenseDateStr" styleId="offDateStrID"/>
											<%-- <a href="#" onClick="cal1.select(document.forms[0].offenseDateStr,'anchor1','MM/dd/yyyy'); return false;" 
											NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.2.calendar"/></a>			 --%>														
										</td>												
									</tr>
									<tr>								
										<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.diamond" /><bean:message key="prompt.adjudication" /> <bean:message key="prompt.date"/></td>
										<td class="formDe" colspan="3">
											<html:text name="transferredOffenseForm" size="10" maxlength="10" property="adjudicationDateStr" styleId="adjDateStrID"/>
											<%-- <a href="#" onClick="cal1.select(document.forms[0].adjudicationDateStr,'anchor2','MM/dd/yyyy'); return false;" 
											NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.2.calendar"/></a>	 --%>																
										</td>												
									</tr>
									<tr>								
										<td class="formDeLabel" width="2%" nowrap><bean:message key="prompt.probationStartDate"/></td>
										<td class="formDe" colspan="1" width="30%">
											<html:text name="transferredOffenseForm" size="10" maxlength="10" property="probationStartDateStr" styleId="pbsDateStrID"/>															
										</td>
										<td class="formDeLabel" width="2%" nowrap><bean:message key="prompt.probationEndDate"/></td>
										<td class="formDe" colspan="1" width="30%">
											<html:text name="transferredOffenseForm" size="10" maxlength="10" property="probationEndDateStr" styleId="preDateStrID"/>															
										</td>												
									</tr>
									<tr>
										<td colspan="4" align="center">
											<html:submit property="submitAction" styleId="add">				
												<bean:message key="button.add" />
											</html:submit>
										</td>												
									</tr>
								</logic:notEmpty >
								<logic:empty name="transferredOffenseForm" property="availableTransferredOffenseReferralList">
									<tr>
										<td align="left">No transferrable offenses found for this juvenile</td>
									</tr>
								</logic:empty>	
							</table>
						</td>
					</tr>
					<logic:notEmpty name="transferredOffenseForm" property="newTransferredOffensesList" >
						<tr>
							<td>		
								<table width='100%' border="0" cellpadding="2" cellspacing="1">	
					                <tr class="formDeLabel"> 
					                	<td></td>
										<td class="subHead" width='1%' nowrap="nowrap"><bean:message key="prompt.referral" /> #</td> 
										<td class="subHead" nowrap="nowrap"><bean:message key="prompt.from" /> <bean:message key="prompt.county" /></td> 
										<td class="subHead"><bean:message key="prompt.PID"/> #</td>		
										<td class="subHead"><bean:message key="prompt.offense"/> <bean:message key="prompt.description"/></td>
										<td class="subHead"><bean:message key="prompt.severityLevel"/></td>
										<td class="subHead"><bean:message key="prompt.DPS"/> <bean:message key="prompt.code"/></td>
										<td class="subHead"><bean:message key="prompt.offenseDate"/></td>
										<td class="subHead"><bean:message key="prompt.adjudication"/> <bean:message key="prompt.date"/></td>	
										<td class="subHead"><bean:message key="prompt.probationStartDate"/></td>
										<td class="subHead"><bean:message key="prompt.probationEndDate"/></td>	             
						            </tr> 
	        						<logic:iterate id="toIndex" name="transferredOffenseForm" property="newTransferredOffensesList" indexId="index"> 
	            						<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
		             						<td>
		             							<a href="/<msp:webapp/>handleJuvenileProfileTransferredOffensesSelection.do?submitAction=Remove&selectedId=<%=(index.intValue())%>">Remove&nbsp;</a>
		             						</td>
		             						<td><bean:write name="toIndex" property="referralNum"/>
		             						<td><bean:write name="toIndex" property="countyDesc"/></td>
		             						<td><bean:write name="toIndex" property="personId"/></td>
		             						<td><bean:write name="toIndex" property="offenseDesc"/></td>
		             						<td><bean:write name="toIndex" property="offenseCategory"/></td>
             								<td><bean:write name="toIndex" property="dpsCode"/></td>
		      								<td><bean:write name="toIndex" property="offenseDateStr" /></td>														
		          							<td><bean:write name="toIndex" property="adjudicationDateStr"/></td>
		          							<td><bean:write name="toIndex" property="probationStartDateStr"/></td>
		          							<td><bean:write name="toIndex" property="probationEndDateStr"/></td>
			          					</tr>
									</logic:iterate> 
				         		</table> 
				        	</td>
				    	</tr>
				    	<bean:define id="NewRecord" value="1" />
				    </logic:notEmpty>	 
				</table> 
<%-- END OF TRANSFERRED OFFENSES TABLE--%> 
				<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
				<table border="0" cellpadding="1" cellspacing="1" align="center">
					<tr>
						<td align="center">
							<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
							<logic:present name="NewRecord">
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>
							</logic:present>
							<logic:notPresent name="NewRecord">
								<html:submit property="submitAction" disabled="true"><bean:message key="button.finish" /></html:submit>
							</logic:notPresent>
							<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
						</td>
					</tr>
				</table>
<%-- END BUTTON TABLE --%>
				<div class='spacer4px'></div>
