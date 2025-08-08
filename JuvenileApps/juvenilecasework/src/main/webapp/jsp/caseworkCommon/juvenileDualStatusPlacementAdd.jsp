<!DOCTYPE HTML>

<%-- Used to display traits details off Traits Tab --%>
<%--MODIFICATIONS --%>
<%-- 06/13/2005		DWilliamson	Create tile --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>


<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.PDCodeTableConstants"%>


<msp:nocache />
<%-- ensures the user is logged in. --%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<title><bean:message key="title.heading" />Juvenile Casework -
	juvenileDualStatusPlacementAdd.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%@include file="../jQuery.fw"%>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/placement.js"></script>
</head>


<%-- BEGIN TRAITS TABLE --%>
<table width='98%' border="0" cellpadding="0" cellspacing="0"
	class="borderTableBlue">
	<tr>
		<td valign=top class=detailHead><bean:message key="prompt.add" />&nbsp;<bean:message
				key="prompt.placement" />
		</td>
	</tr>
	
	<tr>
		<td class=bodyText>
			<table width='100%' cellspacing=2 cellpadding=2>
				<tr>
					<td class=formDeLabel nowrap><bean:message
							key="prompt.placementDate" /> <bean:message key="prompt.month" />

						<html:select styleId="placementdateMonth"
							property="placementdateMonth">
							<html:option key="select.generic" value="" />
							<html:option value="01">JAN</html:option>
							<html:option value="02">FEB</html:option>
							<html:option value="03">MAR</html:option>
							<html:option value="04">APR</html:option>
							<html:option value="05">MAY</html:option>
							<html:option value="06">JUN</html:option>
							<html:option value="07">JUL</html:option>
							<html:option value="08">AUG</html:option>
							<html:option value="09">SEP</html:option>
							<html:option value="10">OCT</html:option>
							<html:option value="11">NOV</html:option>
							<html:option value="12">DEC</html:option>
						</html:select> <bean:message key="prompt.year" /> <html:select
							styleId="placementdateYear" property="placementdateYear">
							<html:option key="select.generic" value="" />
							<html:optionsCollection name="juvenileAbuseForm"
								property="placementYears" value="code" label="desc" />
						</html:select> <html:text property="placementDate" styleId="placementDt"
							disabled="true" />
					</td>

					<%-- <td class="formDe" nowrap>
						<html:text property="placementDate" styleId="placementDt"  maxlength="10" size="10"/>
					</td> --%>
					<td class="formDeLabel" nowrap width=1%><bean:message
							key="prompt.placementType" /> <html:select
							property="placementType" styleId="placementType">
							<html:option key="select.generic" value="" />
							<html:option value="RTC">RTC</html:option>
							<html:option value="FOSTER HOME">FOSTER HOME</html:option>
							<html:option value="CWOP (CHILD WITHOUT PLACEMENT)">CWOP (CHILD WITHOUT PLACEMENT)</html:option>
							<html:option value="MOTHER/FATHER">MOTHER/FATHER</html:option>
							<html:option value="FICTIVE KIN">FICTIVE KIN</html:option>
							<html:option value="OTHER">OTHER</html:option>
						</html:select>
					</td>

					<td class="formDeLabel" nowrap width=1%><bean:message
							key="prompt.placementRemovalReason" /> <html:select
							property="placementRemovalReason"
							styleId="placementRemovalReason">
							<html:option key="select.generic" value="" />
							<html:option value="FACILITY CLOSING">FACILITY CLOSING</html:option>
							<html:option value="YOUTH LEVEL OF CARE INCREASED">YOUTH LEVEL OF CARE INCREASED</html:option>
							<html:option value="YOUTH LEVEL OF CARE DECREASED">YOUTH LEVEL OF CARE DECREASED</html:option>
							<html:option value="YOUTH COMPLETED">YOUTH COMPLETED</html:option>
							<html:option value="UNSUCCESSFUL DISCHARGE">UNSUCCESSFUL DISCHARGE</html:option>
							<html:option value="OTHER REASON">OTHER REASON</html:option>
						</html:select>
					</td>

				</tr>
				<%-- <logic:equal property="placementRemovalReason" value="Other reason"> --%>
				<tr>
					<td></td>
					<%-- <logic:equal name="juvenileAbuseForm" property="placementType" value="OTHER">  				
	  				 --%>
					<td class=formDeLabel align="center" nowrap><bean:message
							key="prompt.placementTypeOtherReason" /> <!-- <input type="text" name=placementremovalreasonOther Id="placementremovalreasonOther" size="50" maxlength="50" /> -->
						<html:text name="juvenileAbuseForm"
							property="placementtypeotherReason"
							styleId="placementtypeotherReason" size="50" maxlength="50"
							disabled="true" />
					</td>
					<%-- </logic:equal> --%>
					<%-- <logic:equal name="juvenileAbuseForm" property="placementRemovalReason" value="OTHER REASON">  				
	  				  --%>
					<td class=formDeLabel align="center" nowrap><bean:message
							key="prompt.placementotherReason" /> <!-- <input type="text" name=placementremovalreasonOther Id="placementremovalreasonOther" size="50" maxlength="50" /> -->
						<html:text name="juvenileAbuseForm"
							property="placementremovalreasonOther"
							styleId="placementremovalreasonOther" size="50" maxlength="50"
							disabled="true" />
					</td>
					<%-- </logic:equal> --%>

				</tr>
				
				<tr>

					<td align="center" colspan="6"><html:submit
							property="submitAction" styleId="btnplacementAdd">
							<bean:message key="button.addPlacement" />
						</html:submit>
					</td>
				</tr>
				<logic:notEmpty name="juvenileAbuseForm" property="newPlacements">
					<div class=spacer></div>
					<table width='100%' cellspacing=1>
						<tr bgcolor='#cccccc'>
							<td class="subhead" valign=top width="8%">&nbsp;</td>
							<td class="subhead" valign=top width="14%" align="left"><bean:message
									key="prompt.placementDate" />
							</td>
							<td class="subhead" valign=top width="14%" align="left"><bean:message
									key="prompt.placementType" />
							</td>
							<td class="subhead" valign=top width="14%" align="left"><bean:message
									key="prompt.placementTypeOtherReason" />
							</td>
							<td class="subhead" valign=top width="14%" align="left"><bean:message
									key="prompt.placementRemovalReason" />
							</td>
							<td class="subhead" valign=top width="14%" align="left"><bean:message
									key="prompt.placementotherReason" />
							</td>
						</tr>

						<logic:iterate id="placement" name="juvenileAbuseForm"
							property="newPlacements" indexId="index">
							<tr>

								<td align="left"><a
									href="/<msp:webapp/>displayJuvenileAbuseCreate.do?submitAction=Remove&selectedValue=<%=(index.intValue())%>">Remove&nbsp;</a>
								</td>
								<td nowrap align="left"><bean:write name="placement"
										property="placementDate" />&nbsp;&nbsp;</td>
								<td nowrap align="left"><bean:write name="placement"
										property="placementType" />&nbsp;&nbsp;</td>
								<td nowrap align="left"><bean:write name="placement"
										property="placementtypeotherReason" />&nbsp;&nbsp;</td>
								<td nowrap align="left"><bean:write name="placement"
										property="placementRemovalReason" />&nbsp;&nbsp;</td>
								<td nowrap align="left"><bean:write name="placement"
										property="placementremovalreasonOther" />&nbsp;&nbsp;</td>
							</tr>
						</logic:iterate>

					</table>
				</logic:notEmpty>
			</table>
		</td>
	</tr>
</table>