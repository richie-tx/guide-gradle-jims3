<!DOCTYPE HTML>
<%-- Dwilliamson  12/09/2010	Create Tile.  This Question tile collapses --%>
<%-- 04/15/2011		DGibler		Changed to handle multiple recommendations and scores --%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.RiskAnalysisConstants" %>
<%@ page import="naming.Features" %>

<title><bean:message key="title.heading" /> - overrideTile.jsp</title>

<tiles:useAttribute id="formName" name="formName"/>

<%-- begin override section --%>
<logic:equal name="${formName}" property="secondaryAction" value="<%=UIConstants.EDIT%>"> 
<%-- this is the section shown for the Edit screen --%>
	<div class='spacer'></div>
	<table align="center" width='98%' border="0" cellpadding="1" cellspacing="1" class="borderTableBlue">	
		<tr>
			<td class='detailHead'>Referral Recommendation</td>
		</tr>
		<tr>
			<td align='center'>
				<table width='100%' cellpadding='4' cellspacing='1'>
					<logic:iterate id="recommendationsIndex" name="riskAnalysisForm" property="recommendations" indexId="index">
						<tr>
							<td class='formDeLabel' width='1%'>Recommendation</td>
							<td class='formDe'><bean:write name="recommendationsIndex" property="riskAnalysisRecommendation"/></td>
						</tr>
						<logic:notEmpty name="recommendationsIndex" property="riskAnalysisScore">
							<logic:equal name="riskAnalysisForm" property="riskAssessmentType" value="<%=RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL%>">
               					<tr>
									<td class="formDeLabel">Aggregate Score</td>
									<td class="formDe" ><bean:write name="recommendationsIndex" property="riskAnalysisScore"/></td>
								</tr>
							</logic:equal>
						</logic:notEmpty>
					</logic:iterate>

					<tr id='viewOverrideRecommentationRow'>
						<td class='formDeLabel' nowrap='nowrap'>Override Recommendation?</td>
						<td class='formDe'>
							<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="true">YES</logic:equal>
							<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="false">NO</logic:equal>
						</td>
					</tr>

					<tr id='viewOverrideRecommentationReasonRow'>
						<td align='center' colspan='2'>
							<table width='100%' cellpadding='4' cellspacing='1'>
								<tr>
									<td class='formDeLabel'><bean:write name="riskAnalysisForm" property="overRideType" /></td>
								</tr>

								<logic:notEqual name="riskAnalysisForm" property="overRideType" value="<%=RiskAnalysisConstants.OVERRIDE_TYPE_OTHER%>">
									<tr>
										<td class='formDe'>
											<bean:write  name="riskAnalysisForm" property="overRiddenReasonDesc" />
										</td>
									</tr>
								</logic:notEqual>

								<logic:equal name="riskAnalysisForm" property="overRideType" value="<%=RiskAnalysisConstants.OVERRIDE_TYPE_OTHER%>">
									<%-- the user entered free-form text for the reason --%>
									<tr>
										<td class='formDe'>
											<bean:write name="riskAnalysisForm" property="overRiddenReasonOther" />
										</td>
									</tr>
								</logic:equal>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_OVERRIDE_RECOMMENDATION%>'>
			<tr>  
				<td align='center'>
					<script type='text/javascript'>
						show( 'viewOverrideRecommentationRow', HIDE_ITEM, 'row' ) ;
						show( 'viewOverrideRecommentationReasonRow', HIDE_ITEM, 'row' ) ;
					</script> 
					<logic:equal name="riskAnalysisForm" property="riskAssessmentTypeDesc" value="<%=RiskAnalysisConstants.RISK_TYPE_DETENTION%>">
						<table width='100%' cellpadding='4' cellspacing='1'>
							<tr id='overrideRadioRow'>
								<td class='formDeLabel' width='1%' nowrap='nowrap'>Override Recommendation?</td>
								<td class='formDe'> 
									<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
										Yes<html:radio name="riskAnalysisForm" property="recommendationOverridden" value="true" onclick="radioCallback('yes');" />
										No<html:radio name="riskAnalysisForm" property="recommendationOverridden" value="false" onclick="radioCallback('no');" />
									</logic:equal>
									<logic:notEqual name="juvenileCasefileForm" property="juvUnder21" value="true">
										Juvenile's age is 21 or older, no override allowed.
									</logic:notEqual>
								</td>
							</tr>
						</table>
					</logic:equal>
				</td>
			</tr>
	                         	
			<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="true">
				<logic:notEqual name="juvenileCasefileForm" property="juvUnder21" value="true">
					<tr class='hidden' id='overrideEditRow'>
				</logic:notEqual>
				<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
					<tr id='overrideEditRow'
				</logic:equal>	
			</logic:equal>

			<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="false">
				<tr class='hidden' id='overrideEditRow'>
			</logic:equal>	

			<td align='center'>
				<table width='100%' cellpadding='4' cellspacing='1'>
					
					<tr>
						<td class='formDeLabel'>Release Override</td>
					</tr>

					<logic:iterate name="riskAnalysisForm" property="releaseOverrideReasons" id="rors" >
						<tr>
							<td class='formDe'> 
								<html:radio name="riskAnalysisForm" property="overRiddenReasonCd" idName="rors" value="code" onclick="overRideRadioCallback('none');" />
								<bean:write name="rors" property="description"/><br>
							</td>
						</tr>
		                                								
						<bean:define id="code" name="rors" property="code" type="java.lang.String" />
						<%  char overRideChar = code.charAt(1);
							if (String.valueOf(overRideChar).equalsIgnoreCase("O")) {
						%>
						<tr>
							<td class='formDe'>
								<div width='100%'>
									<html:textarea name="riskAnalysisForm" property="overRiddenReasonOther" onkeyup="textLimit(this, 500);" style="width:100%" rows='4'></html:textarea>
								</div>
							</td>
						</tr>
						<% 
							}
						%>
					</logic:iterate>

					<tr>
						<td class='formDeLabel'>Detention Override</td>
					</tr>

					<logic:iterate name="riskAnalysisForm" property="detentionOverrideReasons" id="rors" indexId="ind">
						<tr>
							<td class='formDe'> 
								<html:radio name="riskAnalysisForm" property="overRiddenReasonCd" idName="rors" value="code" onclick="overRideRadioCallback('none');" />
								<bean:write name="rors" property="description"/><br>
							</td>
						</tr>
               								
						<bean:define id="code" name="rors" property="code" type="java.lang.String" />
						<%  char overRideChar = code.charAt(1);
							if (String.valueOf(overRideChar).equalsIgnoreCase("O")) {
						%>
						<tr>
							<td class='formDe'>
								<div width='100%'>
									<html:textarea name="riskAnalysisForm" property="overRiddenReasonDetentionOther" onkeyup="textLimit(this, 500);" style="width:100%" rows='4'></html:textarea>
								</div>
							</td>
						</tr>
						<% 
							}
						%>
					</logic:iterate>

					</tr> 
				</table>
			</td>
			</tr>
			</tr> <%-- starting <tr> in logic tag --%>
		</jims2:isAllowed>
	</table>
</logic:equal>
							
<logic:notEqual name="${formName}" property="secondaryAction" value="<%=UIConstants.EDIT%>">
<%-- this section is shown for the Summary and Confirmation screens --%>
	<div class='spacer'></div>
	<table align="center" width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">	
		<tr>
			<td class='detailHead'>Referral Recommendation</td>
		</tr>
		<tr>
			<td align='center'>
				<table width='100%' cellpadding='4' cellspacing='1'>
					<logic:iterate id="recommendationsIndex" name="riskAnalysisForm" property="recommendations" indexId="index">
						<tr>
							<td class='formDeLabel' width='1%'>Recommendation</td>
							<td class='formDe'><bean:write name="recommendationsIndex" property="riskAnalysisRecommendation"/></td>
						</tr>
						<logic:notEmpty name="recommendationsIndex" property="riskAnalysisScore">
							<logic:equal name="riskAnalysisForm" property="riskAssessmentType" value="<%=RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL%>">
              						<tr>
									<td class="formDeLabel">Aggregate Score</td>
									<td class="formDe" ><bean:write name="recommendationsIndex" property="riskAnalysisScore"/></td>
								</tr>
							</logic:equal>
						</logic:notEmpty>
					</logic:iterate>
					<tr>
						<td class='formDeLabel' nowrap='nowrap'>Override Recommendation?</td>
						<td class='formDe'>
							<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="true">YES</logic:equal>
							<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="false">NO</logic:equal>
						</td>
					</tr>
					<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="true">
						<tr>
							<td class='formDeLabel' width="50%">Override Type</td>
							<td class='formDe' width="50%"><bean:write name="riskAnalysisForm" property="overRideType"/></td>
								
						</tr>
					</logic:equal>
				</table>
			</td>
		</tr>

		<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="true">
		<%-- if the recommendation has been overriden, then show this section --%>	
			<tr>
				<td align='center'>
					<table width='100%' cellpadding='4' cellspacing='1'>
						<tr>
							<td class='formDeLabel'><bean:write name="riskAnalysisForm" property="overRideType" /></td>
						</tr>
						<tr>
							<td class='formDe'>
								<bean:write  name="riskAnalysisForm" property="overRiddenReasonDesc" />
							</td>
						</tr>
                                								
						<bean:define id="code" name="riskAnalysisForm" property="overRiddenReasonCd" type="java.lang.String" />
						<bean:define id="overRiddenReasonOther" name="riskAnalysisForm" property="overRiddenReasonOther" type="java.lang.String" />
						<bean:define id="overRiddenReasonDetentionOther" name="riskAnalysisForm" property="overRiddenReasonDetentionOther" type="java.lang.String" />

						<%  char overRideChar0 = code.charAt(0);
							char overRideChar1 = code.charAt(1);
						
							if ( (overRiddenReasonOther != null && overRiddenReasonOther.length() > 0) ||
								(overRiddenReasonDetentionOther != null && overRiddenReasonDetentionOther.length() > 0)) {	
							
								if (String.valueOf(overRideChar1).equalsIgnoreCase(String.valueOf(RiskAnalysisConstants.OVERRIDE_TYPE_SECOND_CHAR))) {
						%>
						<tr>
							<td class='formDe'>
								<%
									if (String.valueOf(overRideChar0).equalsIgnoreCase(String.valueOf(RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE_CODE_FIRST_CHAR))) {
								%>
								<bean:write  name="riskAnalysisForm" property="overRiddenReasonOther" />
								<% 
									} else if (String.valueOf(overRideChar0).equalsIgnoreCase(String.valueOf(RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION_CODE_FIRST_CHAR))) {
								%>
								<bean:write  name="riskAnalysisForm" property="overRiddenReasonDetentionOther" />
								<% 
									}
								%>
							</td>
						</tr>
						<% 
							}
						}
						%>
					</table>
				</td>
			</tr>
		</logic:equal>
	</table>
<%-- end override section --%>
</logic:notEqual>
