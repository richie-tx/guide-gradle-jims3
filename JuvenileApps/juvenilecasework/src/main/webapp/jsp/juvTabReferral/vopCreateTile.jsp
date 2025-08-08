<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 07/06/2023	US 159965 Create JSP  --%>

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

<title><bean:message key="title.heading"/> - juvTabReferral - vopCreateTile.jsp</title>

<%-- BEGIN OF VOP CREATE TABLE--%> 
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 
	<tr> 
		<td class="detailHead">Violation of Probation Details</td> 
	</tr> 
	<tr>
		<td>
		<table width='100%' border="0" cellpadding="2" cellspacing="1">
		<tr>
			<td class="formDeLabel" colspan="1" width="1%"  nowrap><bean:message key="prompt.vopShort"/></td>
			<td class='formDe' colspan="3"> 
			<bean:write name="vopOffenseForm" property="referralNum"/> -  
			<logic:iterate id="offenseList" name="vopOffenseForm" property="offensesVOPs" indexId="indexOff"> 
    				<bean:write name="offenseList" property="offenseDescription"/>  
    				 <logic:notEqual name="vopOffenseForm" property="offenseCollectionSize" value="<%=indexOff.toString()%>">
    				, 
    				</logic:notEqual> 
    			</logic:iterate>&nbsp;&nbsp;&nbsp;<bean:write name="vopOffenseForm" property="petitionNumVOP"/>
			</td>
		</tr>
		<tr>
			<td class="formDeLabel" colspan="1" width="20%"  nowrap><bean:message key="prompt.inCountry"/><bean:message key="prompt.referral"/></td>
			<td  width="100%" colspan="3"  > 
			<table width='99%' border="0" cellpadding="2" cellspacing="0" >
			<logic:iterate id="inCountyPetitions" name="vopOffenseForm" property="inCountyPetitions" indexId="indexer">
			<tr class="<%out.print((indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%" colspan='3'  width="100%">
			<td width="100%">
			<%-- <bean:define id="refId" name='inCountyPetitions' property='referralNum' type="java.lang.String"></bean:define> --%>
			<input type="radio" name="selectedPetition" value=<bean:write name='inCountyPetitions' property='referralNum'/> id="referralPetitionRadio">
			<bean:write name="inCountyPetitions" property="referralNum"/> - 
			<span title='<bean:write name="inCountyPetitions" property="offense"/>'><bean:write name="inCountyPetitions" property="offenseCodeId"/></span>,  
			<bean:write name="inCountyPetitions" property="petitionNum"/>
			</td></tr></logic:iterate>
			<input type="hidden" name="selValue" value="<bean:write name="vopOffenseForm" property="selectedPetition" />"  id="selPetfId" />
			</table>
			</td>
		</tr>
		<logic:equal name="vopOffenseForm" property="selectedSubSevType4VOP" value="E">
		<tr id="detailsVOP">
			<td class="formDeLabel" colspan="1" width="1%"  nowrap><bean:message key="prompt.newIncoming"/> <bean:message key="prompt.offense"/>  <bean:message key="prompt.adult"/> <bean:message key="prompt.indicator"/></td>
			<td class="formDe">
			<html:radio name="vopOffenseForm" property="adultIndicatorStr" value="Yes" styleId="adultInd" unchecked/><bean:message key="prompt.yes"/>
			<html:radio name="vopOffenseForm" property="adultIndicatorStr" value="No" styleId="adultInd" unchecked/><bean:message key="prompt.no"/>
			</td>
			<td class="formDeLabel" colspan="1" width="1%"  nowrap><bean:message key="prompt.newIncoming"/> <bean:message key="prompt.charge"/> <bean:message key="prompt.location"/></td>
			<td>
			<html:select name="vopOffenseForm" property="locationIndicator" styleId="locationInd">
			<html:option value="">Please Select</html:option>
			<html:option key="select.inCounty" value="In County" />
			<html:option key="select.outOfCounty" value="Out Of County" />
			<html:option key="select.outOfState" value="Out Of State" />
			</html:select></td>
		</tr>
	<tr id="detailsVOP2">
		<td class="formDeLabel" colspan="1" width="1%"  nowrap><bean:message key="prompt.newIncoming"/> <bean:message key="prompt.offense"/> <bean:message key="prompt.charge"/></td>
		<td class='formDe' colspan="3">
			<html:text name="vopOffenseForm" property="offenseCharge" styleId="offCodeID"  maxlength="6" size="6"/>
			<html:submit property="submitAction" styleId="validateCode"> <bean:message key="button.validateOffenseCode" /></html:submit>&nbsp;Or&nbsp;
			<html:submit property="submitAction" styleId="searchCode"><bean:message key="button.searchForOffenseCode" /></html:submit>
		</td>
	</tr>
	<tr id="detailsVOP3">
		<td class="formDeLabel" colspan="1" width="1%"  nowrap><bean:message key="prompt.newIncoming"/> <bean:message key="prompt.offense"/> <bean:message key="prompt.charge"/> <bean:message key="prompt.date"/></td>
		<td class='formDe' valign="top" colspan="3" width="10%"><html:text name="vopOffenseForm" styleId="offenseChargeDate" property="offenseChargeDate" maxlength="10" size="10" /></td>
	</tr>
	</logic:equal>
		</table>
		</td>
	</tr>
</table>			
	 <html:hidden styleId="selectedVOPsevSubType" name="vopOffenseForm" property="selectedSubSevType4VOP"/>
	  <html:hidden styleId="referralDate" name="vopOffenseForm" property="referralDateStr"/>												
<%-- END OF VOP CREATE TABLE--%> 
				<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
				<table border="0" cellpadding="1" cellspacing="1" align="center">
					<tr>
						<td align="center">
							<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
							<html:submit property="submitAction" styleId="nextBtn"><bean:message key="button.next" /></html:submit>
							<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
						</td>
					</tr>
				</table>
<%-- END BUTTON TABLE --%>
				<div class='spacer4px'></div>
