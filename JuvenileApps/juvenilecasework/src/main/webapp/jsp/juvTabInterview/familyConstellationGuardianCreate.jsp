<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 08/05/2009 L Deen      #61367 fix problem with code duplicated due to merge  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" /> 
<html:base />

<html:javascript formName="juvenileFamilyForm" />

<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyConstellationGeneral.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>



<title><bean:message key="title.heading" /> -
familyConstellationGuardianCreate.jsp</title>
</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"
	topmargin="0" leftmargin="0">
<html:form action="/displayFamilyConstellationGuardianCreateSummary">


	<logic:equal name="juvenileFamilyForm" property="action" value="update">
		<input type="hidden" name="helpFile"
			value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|241">
	</logic:equal>
	<logic:notEqual name="juvenileFamilyForm" property="action"
		value="update">
		<input type="hidden" name="helpFile"
			value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|241">
	</logic:notEqual>

	<%-- BEGIN HEADING TABLE --%>
	<table width="98%">
		<tr>
			<td align="center" class="header"><bean:message
				key="title.juvenileCasework" /> - <bean:message
				key="title.juvenileProfile" /> - <logic:equal
				name="juvenileFamilyForm" property="action" value="update">
				<bean:message key="prompt.update" />
			</logic:equal> <logic:notEqual name="juvenileFamilyForm" property="action"
				value="update">
				<bean:message key="prompt.create" />
			</logic:notEqual> <bean:message key="prompt.family" /> <bean:message
				key="prompt.constellation" /> <bean:message key="prompt.guardian" />
			</td>
		</tr>
	</table>

	<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
	<%-- END HEADING TABLE --%>

	<%-- BEGIN INSTRUCTION TABLE --%>
	<div class='spacer'></div>
	<table width="98%" border="0">
		<tr>
			<td>
			<ul>
				<li>Enter information and select Next button to view summary.</li>
				<li>Enter only the dollar amount and cent amount in asset and
				liability fields. Do not enter commas or dollar signs.</li>
			</ul>
			</td>
		</tr>
	</table>
	<%-- END INSTRUCTION TABLE --%>


	<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%>
	<div class='spacer'></div>
	<table align="center" cellpadding="1" cellspacing="0" border="0"
		width='100%'>
		<tr>
			<td><%--header info start--%> <tiles:insert
				page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader" />
			</tiles:insert> <%--header info end--%></td>
		</tr>
	</table>
	<%-- END JUVENILE PROFILE HEADER TABLE --%>

	<%-- BEGIN DETAIL TABLE  Main Table Begin --%>
	<div class='spacer'></div>
	<table width="98%" border="0" cellpadding="0" cellspacing="0"
		align="center">
		<tr>
			<td><%-- begin green tabs (1st row) --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><%--tabs start--%> <tiles:insert
						page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="family" />
						<tiles:put name="juvnum"
							value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
					</tiles:insert> <%--tabs end--%></td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img
						src='../../common/images/spacer.gif' width="5" alt=""></td>
				</tr>
			</table>
			<%-- end green tabs --%> <%-- begin outer green border --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0"
				class="borderTableGreen">
				<tr>
					<td valign="top" align="center"><%-- begin red tabs (3rd row) --%>
					<div class='spacer'></div>
					<table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top"><%--tabs start--%> <tiles:insert
								page="../caseworkCommon/constellationInfoTabs.jsp" flush="true">
								<tiles:put name="tabid" value="FamilyFinancial" />
							</tiles:insert> <%--tabs end--%></td>
						</tr>
						<tr>
							<td bgcolor='#6699FF'><img
								src='../../common/images/spacer.gif' width="5" alt=""></td>
						</tr>
					</table>
					<%-- end red tabs --%> <%--begin red outer border --%>
					<table width='98%' border="0" cellpadding="0" cellspacing="0"
						class="borderTableBlue">
						<tr>
							<td valign="top" align="center">
							<%-- BEGIN Family constellation List TABLE -Table 4 Begin--%>
							<nested:nest property="currentGuardian">
								<div class='spacer'></div>
								<table width='98%' cellspacing="0" cellpadding="2" border="0"
									class="borderTableBlue">
									<tr>
										<td align="left" class="detailHead" colspan="4"><bean:message
											key="prompt.family" /> <bean:message
											key="prompt.constellation" /> <bean:message
											key="prompt.guardian" /> <bean:message key="prompt.info" /></td>
									</tr>
									<tr>
										<td colspan="4">
										<%--BEGIN Member 1 Inner Table --%>
										<table width='100%' cellspacing="1" class="borderTableGrey">
											<tr bgcolor='#cccccc'>
												<td valign="top" class="subHead"><bean:message
													key="prompt.member" /> #</td>
												<td valign="top" class="subHead"><bean:message
													key="prompt.name" /></td>
												<td valign="top" class="subHead"><bean:message
													key="prompt.relationship" /></td>
												<td valign="top" class="subHead"><bean:message
													key="prompt.deceased" /></td>
											</tr>
											<tr class="normalRow">
												<td valign="top"><nested:write property="memberNumber" /></td>
												<td valign="top"><nested:write
													property="name.formattedName" /></td>
												<td valign="top"><nested:write
													property="relationshipToJuv" /></td>
												<td valign="top"><nested:write property="deceased" /></td>
											</tr>
										</table>
										<%--END Member 1 Inner Table --%>
										</td>
									</tr>
									<tr>
										<td align="center"><bean:define id="refCounter">pChar0</bean:define>
										<tiles:insert page="../caseworkCommon/employmentSummary.jsp"
											flush="false">
											<tiles:put name="employmentInfoList"
												beanName="juvenileFamilyForm"
												beanProperty="currentGuardian.employmentInfoList" />
											<tiles:put name="rowIdHeader" beanName="refCounter" />
										</tiles:insert></td>
									</tr>
									<tr>
										<td align='center'>
										<%-- BEGIN Family Financial Info --%>
										<table width='100%' cellpadding='2' cellspacing='1'>
											<tr>
												<td valign='top' class='detailHead' colspan='4'>Family
												Financial Information of Guardian</td>
											</tr>
											<tr>
												<td class='formDeLabel'><bean:message
													key="prompt.numOfDependents" /></td>
												<td class='formDe' colspan="3"><nested:text size="20"
													property="numberOfDependents" /></td>
											</tr>
											<tr>
												<td class='formDeLabel'><bean:message
													key="prompt.numLivingInHome" /></td>
												<td class='formDe'><nested:text size="20"
													property="numberLivingInHome" /></td>
												<td class='formDeLabel'><bean:message
													key="prompt.familyNum" /></td>
												<td class='formDe'><nested:text size="20"
													property="numberInFamily" /></td>
											</tr>
											<tr>
												<td class='formDeLabel'><bean:message
													key="prompt.childSupportPayor" /></td>
													
												<td class='formDe' colspan='3'>
												<%-- BEGIN CHILD SUPPORT INNER TABLE  --%>
												<table>
													<tr>
														<td class='formDeLabel' colspan="2"><bean:message
															key="prompt.last" /></td>
													</tr>
													<tr>
														<td colspan="2"><nested:text size="30"
															property="childSupportPayorName.lastName" /></td>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message
															key="prompt.first" /></td>
														<td class='formDeLabel'><bean:message
															key="prompt.middle" /></td>
													</tr>
													<tr>
														<td><nested:text size="25"
															property="childSupportPayorName.firstName" /></td>
														<td><nested:text size="25"
															property="childSupportPayorName.middleName" /></td>
													</tr>
												</table>
												</td> 
                                </tr>
                                    <tr><td></td></tr>

												
													<tr bgcolor="#AAAAAA">
														<td class='subHead' colspan="2"><bean:message
															key="prompt.assets" /></td>
														<td class='subHead' colspan="2"><bean:message
															key="prompt.liabilities" /></td>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message
															key="prompt.monthlyFoodStamps" /></td>
														<td class='formDe'><nested:text size="20"
															property="foodStamps" /></td>
														<td class='formDeLabel'><bean:message
															key="prompt.monthlyUtilities" /></td>
														<td class='formDe'><nested:text size="20"
															property="utilitiesExpenses" /></td>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message
															key="prompt.realProperty" /></td>
														<td class='formDe'><nested:text size="20"
															property="propertyValue" /></td>
														<td class='formDeLabel'><bean:message
															key="prompt.monthlyLifeInsurancePremiums" /></td>
														<td class='formDe'><nested:text size="20"
															property="lifeInsurancePremium" /></td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message
															key="prompt.intangibleProperty" /></td>
														<td class='formDe'><nested:text size="20"
															property="intangibleValue" /></td>
														<td class='formDeLabel'><bean:message
															key="prompt.monthlySchoolSupplies" /></td>
														<td class='formDe'><nested:text size="20"
															property="schoolExpenses" /></td>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message
															key="prompt.monthlyTANF" /></td>
														<td class='formDe'><nested:text size="20"
															property="tanfAfdc" /></td>
														<td class='formDeLabel'><bean:message
															key="prompt.monthlyRent" /></td>
														<td class='formDe'><nested:text size="20"
															property="rentExpenses" /></td>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message
															key="prompt.otherIncome" /></td>
														<td class='formDe'><nested:text size="20"
															property="otherIncome" /></td>
														<td class='formDeLabel' nowrap='nowrap'><bean:message
															key="prompt.monthlyGroceryExpenses" /></td>
														<td class='formDe'><nested:text size="20"
															property="groceryExpenses" /></td>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message
															key="prompt.savingsAccountBalances" /></td>
														<td class='formDe'><nested:text size="20"
															property="savings" /></td>
														<td class='formDeLabel'><bean:message
															key="prompt.monthlyMedicalExpenses" /></td>
														<td class='formDe'><nested:text size="20"
															property="medicalExpenses" /></td>
													</tr>
													<tr>

														<td class='formDeLabel'><bean:message
															key="prompt.monthlyChildSupportReceived" /></td>
														<td class='formDe'><nested:text size="20"
															property="childSupportReceived" /></td>
														<td class='formDeLabel'><bean:message
															key="prompt.monthlyChildSupportPaid" /></td>
														<td class='formDe'><nested:text size="20"
															property="childSupportPaid" /></td>
													</tr>
													<tr>
														<td class="formDeLabel" width="1%" nowrap='nowrap'>SSI</td>
														<td class="formDe"><nested:text property="ssi"
															size="20" /></td>
														<td class="formDeLabel" width="1%" nowrap='nowrap'></td>
														<td class="formDe">&nbsp;</td>
													</tr>
													<tr>
														<td class='formDeLabel' colspan="4"><bean:message
															key="prompt.notes" />&nbsp; <tiles:insert
															page="../caseworkCommon/spellCheckTile.jsp" flush="false">
															<tiles:put name="tTextField"
																value="currentGuardian.notes" />
															<tiles:put name="tSpellCount" value="spellBtn1" />
														</tiles:insert> (Max. characters allowed: 255)</td>
													</tr>
													<tr>
														<td class='formDe' colspan="4"><nested:textarea
															rows="2" cols="40" style="width:100%" property="notes"/></td>
													</tr>
												</table>
												
												<%-- BEGIN BUTTON TABLE --%>
												<div class='spacer'></div>
												<table border="0" width="100%">
													<tr>
														<td align="center"><html:submit
															property="submitAction">
															<bean:message key="button.back"></bean:message>
														</html:submit> <html:submit property="submitAction"
															onclick="return validateJuvenileFamilyForm(this.form)">
															<bean:message key="button.next"></bean:message>
														</html:submit> <html:submit property="submitAction">
															<bean:message key="button.cancel"></bean:message>
														</html:submit></td>
													</tr>
												</table>
												<div class='spacer'></div>
												<%-- END BUTTON TABLE --%>
												</td>
											 </tr> 
                       </table> 
           						<div class='spacer'></div>
           						</nested:nest>
                       <%-- End Family constellation List TABLE -Table 4 End--%>
                       </td> 
                    </tr> 
                  </table> 
                  <div class='spacer'></div> 
                <%-- end outer red --%>  
							 </td> 
             </tr> 
           </table> 
           <%-- end outer green --%> 
				</td> 
       </tr> 
     </table> 
     <div class='spacer'></div> 


</html:form> 
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
