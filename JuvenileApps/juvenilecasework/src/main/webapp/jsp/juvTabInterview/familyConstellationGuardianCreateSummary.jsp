<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" /> 
<html:base />

<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyConstellationGeneral.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<title><bean:message key="title.heading" /> - familyConstellationGuardianCreateSummary.jsp</title>
</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/submitFamilyConstellationGuardianCreate">

<logic:equal name="juvenileFamilyForm" property="action" value="update">
    <logic:notEqual name="juvenileFamilyForm" property="secondaryAction" value="confirm">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/Manage_Juvenile_Casework.htm#|242">
    </logic:notEqual>
    <logic:equal name="juvenileFamilyForm" property="secondaryAction" value="confirm">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/Manage_Juvenile_Casework.htm#|243">
    </logic:equal>
</logic:equal>
<logic:notEqual name="juvenileFamilyForm" property="action" value="update">
    <logic:notEqual name="juvenileFamilyForm" property="secondaryAction" value="confirm">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/Manage_Juvenile_Casework.htm#|242">
    </logic:notEqual>
    <logic:equal name="juvenileFamilyForm" property="secondaryAction" value="confirm">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/Manage_Juvenile_Casework.htm#|243">
    </logic:equal>
</logic:notEqual>            

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - 
			<logic:equal name="juvenileFamilyForm" property="action" value="update"><bean:message key="prompt.update"/></logic:equal>
			<logic:notEqual name="juvenileFamilyForm" property="action" value="update"><bean:message key="prompt.create"/></logic:notEqual>
			  <bean:message key="prompt.family"/>  <bean:message key="prompt.constellation"/> <bean:message key="prompt.guardian"/>  
		<logic:notEqual name="juvenileFamilyForm" property="secondaryAction" value="confirm"><bean:message key="prompt.summary"/></logic:notEqual>
		<logic:equal name="juvenileFamilyForm" property="secondaryAction" value="confirm"><bean:message key="prompt.confirmation"/></logic:equal>
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
<table width="98%" border="0">
	<tr>
		<td>
		<ul>
			<logic:equal name="juvenileFamilyForm" property="action" value="update">
   			<logic:equal name="juvenileFamilyForm" property="secondaryAction" value="summary">
      			<li>Verify that information is correct and select Finish button to update Guardian. </li>
   			</logic:equal>
			</logic:equal>

			<logic:notEqual name="juvenileFamilyForm" property="action" value="update">
  			<li>Verify that information is correct and select Next button to proceed.</li>
 			</logic:notEqual>
 			<logic:notEqual name="juvenileFamilyForm" property="secondaryAction" value="confirm">
  			<li>If any changes are needed, select Back button. </li>
			</logic:notEqual>
		</ul>
		</td>
	</tr>
</table>

<logic:equal name="juvenileFamilyForm" property="action" value="update">
	<logic:equal name="juvenileFamilyForm" property="secondaryAction" value="confirm">						
  	<table align="center" width="100%">
  			<tr>
  				<td align="center" class="confirm">The following Guardian has been successfully updated.</td>
  			</tr>
  	</table>
	</logic:equal>
</logic:equal>
<%-- END INSTRUCTION TABLE --%>


<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%>
<div class='spacer'></div>
<table align="center" cellpadding="1" cellspacing="0" border="0" width="100%">
	<tr>
		<td ><%--header info start--%> 
		  <tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  			<tiles:put name="headerType" value="profileheader" />
  		</tiles:insert> <%--header info end--%>
		</td>
	</tr>
</table>
<%-- END JUVENILE PROFILE HEADER TABLE --%>


<%-- BEGIN DETAIL TABLE  Main Table Begin --%>
<div class='spacer'></div>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td><%-- begin green tabs (1st row) --%>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
					  <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
  						<tiles:put name="tabid" value="family" />
  						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  					</tiles:insert> 
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66"><img src="../../images/spacer.gif" width="5"></td>
				</tr>
		</table>
		<%-- end green tabs --%> 
		
		<%-- begin outer green border --%>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  		<tr>
  			<td valign="top" align="center">
				  <div class='spacer'></div>
 					<table width="98%" border="0" cellpadding="0" cellspacing="0">
 						<tr>
							<td valign="top">
  							<tiles:insert page="../caseworkCommon/constellationInfoTabs.jsp" flush="true">
									<tiles:put name="tabid" value="FamilyFinancial" />
								</tiles:insert>
							</td>
						</tr>
						<tr>
							<td bgcolor="#6699FF"><img src="../../images/spacer.gif" width="5"></td>
						</tr>
				</table>
								
				<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
						<tr>
							<td valign="top" align="center">
							<%-- BEGIN Family constellation List TABLE -Table 4 Begin--%>
							<nested:nest property="currentGuardian">
							  <div class='spacer'></div>
								<table width="98%" cellspacing="0" cellpadding="4" border="0" class="borderTableBlue">
										<tr>
											<td align="left" class="detailHead" colspan="4">Family Constellation Guardian Information</td>
										</tr>
										<tr>
											<td colspan="4">
											  <%--BEGIN Member 1 Inner Table --%>
													<table width="100%" cellspacing="1" class="borderTableGrey">
															<tr bgcolor="#cccccc">
																<td valign=top class=subHead><bean:message key="prompt.member"/> #</td> 
                                  <td valign=top class=subHead><bean:message key="prompt.name"/></td> 
                                  <td valign=top class=subHead><bean:message key="prompt.relationship"/></td> 
                                  <td valign=top class=subHead><bean:message key="prompt.deceased"/></td> 
															</tr>
															<tr class="normalRow">
																<td valign="top"><nested:write
																	property="memberNumber" /></td>
																<td valign="top"><nested:write
																	property="name.formattedName" /></td>
																<td valign="top"><nested:write
																	property="relationshipToJuv" /></td>
																<td valign="top"><nested:write property="deceased" /></td>
															</tr>
													</table>
													<%--END Member 1 Inner Table --%></td>
										</tr>
							        <tr>
											<td align="center">

            						   <bean:define id="refCounter">pChar0</bean:define>
                           <tiles:insert page="../caseworkCommon/employmentSummary.jsp" flush="false">
                            <tiles:put name="employmentInfoList"  beanName="juvenileFamilyForm" beanProperty="currentGuardian.employmentInfoList"/>	
                            <tiles:put name="rowIdHeader" beanName="refCounter"/>
                           </tiles:insert>
            				  	 </td>
            				  </tr>
										<tr>
											<td align="center">
											<table width="100%" cellpadding="2" cellspacing="1">
												<%-- BEGIN Family Financial Info --%>
													<tr>
														<td valign="top" class="detailHead" colspan="4"><bean:message key="prompt.family"/>
														<bean:message key="prompt.financialInformation"/> of <bean:message key="prompt.guardian"/></td>
													</tr>
													<tr>
													   <td valign="top" colspan="4">
                                <tiles:insert page="../caseworkCommon/familyFinancialInfo.jsp" flush="false">
                                <tiles:put name="familyFinancialInfo" beanName="juvenileFamilyForm" beanProperty="currentGuardian" />	
                                </tiles:insert>
                                </td>
                             </tr>
													<tr>
														<td class="formDeLabel" width="1%" nowrap colspan="1"><bean:message key="prompt.notes"/></td>
														<td colspan="3" class="formDe"><nested:write property="notes" />
														</td>
													</tr>
											</table>
											<%-- End Inner Table 1 --%></td>
										</tr>
								</table>
							</nested:nest>

							<%-- BEGIN BUTTON TABLE --%>
							<div class=spacer></div>
							<table border="0" width="100%">
										<tr>
											<td align="center">
												
												<logic:notEqual name="juvenileFamilyForm" property="action" value="update">
													<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
													<html:submit property="submitAction"><bean:message key="button.next"/></html:submit> 
													<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
												</logic:notEqual>
												<logic:equal name="juvenileFamilyForm" property="action" value="update">
													<logic:equal name="juvenileFamilyForm" property="secondaryAction" value="summary">
													<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
														<html:submit property="submitAction" styleId="guardianSummaryFinish"><bean:message key="button.finish"/></html:submit>
														<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
													</logic:equal>
													<logic:equal name="juvenileFamilyForm" property="secondaryAction" value="confirm"> 
														<html:submit property="submitAction"><bean:message key="button.returnToConstellation"/></html:submit>
													</logic:equal>
												</logic:equal>
											</td>
										</tr>
								</table>
								<%-- END BUTTON TABLE --%>
								<div class=spacer></div>
							</td>
						</tr>
				</table>
				<div class='spacer'></div>
				</td>
			</tr>
	</table>
      <div class='spacer'></div>
<%-- end outer red --%></td>
	</tr>
</table>
<%-- end outer green --%>

<div class='spacer'></div>

</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
