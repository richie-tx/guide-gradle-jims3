<!DOCTYPE HTML>
<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
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
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyMemberBenefitCreate.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<title><bean:message key="title.heading" /> - familyMemberBenefitCreate.jsp</title>

</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/submitManageFamilyMemberBenefitsAction">

<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
  <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|251">
</logic:notEqual>
<logic:equal name="juvenileMemberForm" property="action" value="viewOnly">
  <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|252">
</logic:equal>

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
		<tr>
			<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/> <bean:message key="prompt.member"/> 
				<bean:message key="prompt.benefit"/>

				<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly"><bean:message key="prompt.create"/></logic:notEqual>
				<logic:equal name="juvenileMemberForm" property="action" value="viewOnly"><bean:message key="prompt.details"/></logic:equal>
			</td>
		</tr>
	</table>

<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	 
	<logic:equal name="juvenileMemberForm" property="action" value="createMemberConfirmation">
		<tr>		  
			<td align="center" class="confirm">Update was successful</td>	
		</tr>  	  
	</logic:equal>	  
</table>
<%-- END HEADING TABLE --%>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>

<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
				
<%-- begin instruction table --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
	  <td>
			<ul>
				<li>Add new benefit information for family member and click Add button.</li>
	        <li>Click Remove link to remove benefit information just added.</li>
	        <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_BEN_U%>'>		
						<li>Review information and click the Update button to proceed.</li>
					</jims2:isAllowed>
			</ul>
	  </td>
	</tr>
  <tr>
  	<td class="required"><bean:message key="prompt.2.diamond"/> Indicates Required Fields</td>
  </tr>
</table>
<%-- end instruction table --%>
</logic:notEqual>

<%-- begin detail table --%>
<table cellpadding="1" cellspacing="0" border="0" width="100%" align="center">
	<tr>
		<td >
			<%--header info start--%> 
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader" />
			</tiles:insert> <%--header info end--%>
		</td>
	</tr>
</table>
<%-- end detail table --%>

<%-- begin the tabs and data --%>
<div class='spacer'></div>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align='center'>
	<tr>
		<td>
			<%-- begin green tabs (1st row) --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign='top'>
						<%--tabs start--%> 
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="family" />
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert> <%--tabs end--%>
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
				</tr>
			</table><%-- end green tabs --%>

			<%-- begin outer green border --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign='top' align='center'>
						
						<%-- begin red tabs (3rd row) --%>
						<div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign='top'>
									<%--tabs start--%> 
									<tiles:insert page="../caseworkCommon/memberInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" value="Benefits" />
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert> <%--tabs end--%>
								</td>
							</tr>
							<tr>
								<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
							</tr>
						</table><%-- end red tabs --%>

						<%--begin red outer border --%>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<%-- Be sure to include the autoTab.js --%>
								<td valign='top' align='center'>
                  <%-- BEGIN Previous Benefit TABLE --%>
									
				  				<logic:notEmpty name="juvenileMemberForm" property="benefitsInfoList">
										<logic:iterate id="benefits" name="juvenileMemberForm" property="benefitsInfoList">
											<logic:notEmpty name="benefits" property="memberBenefitId">
												<logic:notPresent name="ExistingRecords">
													 <bean:define id="ExistingRecords" value="1" type="java.lang.String"/>
												 </logic:notPresent>
											</logic:notEmpty>
											<logic:empty name="benefits" property="memberBenefitId">
											  <logic:notPresent name="NewRecords">
												  <bean:define id="NewRecords" value="1" type="java.lang.String"/>
											  </logic:notPresent>
											</logic:empty>
										</logic:iterate>
									</logic:notEmpty>

 									<logic:notPresent name="ExistingRecords">
										<div class='spacer'></div>
  									<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
  						  			<tr>
  						  				<td valign='top' class='detailHead'>
  						  					<table width='100%' cellpadding='0' cellspacing='0'>
  						  						<tr>
  						  							<td class='detailHead'>&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.benefit"/> <bean:message key="prompt.info"/> - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
  						  						</tr>
  						  					</table>
  						  				</td>
  						  			</tr>
  						  			<tr>
  						  				<td valign='top' class='normalRow'>No Benefit Information Available</td>
  						  			</tr>
 						  			</table>
 						  		</logic:notPresent>


 									<logic:present name="ExistingRecords">
										<div class='spacer'></div>
    						  		<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
    						  			<tr>
    						  				<td valign='top' class='detailHead'>
    						  					<table width='100%' cellpadding='0' cellspacing='0'>
    						  						<tr>
    						  							<td width='1%'><a href="javascript:showHideMulti('Characteristics', 'pChar', 1,'/<msp:webapp/>')" border='0'><img border='0' src="../../images/expand.gif" name="Characteristics"></a></td>
    						  							<td class='detailHead'>&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.benefit"/> <bean:message key="prompt.info"/> - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
    						  						</tr>
    						  					</table>
    						  				</td>
    						  			</tr>
    						  			<tr id="pChar0" class='hidden'>
    						  				<td valign='top'>
														<table width="100%" cellspacing="1">
															<tr class='subhead' bgcolor="#CCCCCC">
																<td valign="top" width="10%"><bean:message key="prompt.entryDate"/></td>
																<td valign="top"><bean:message key="prompt.eligibilityType"/></td>
																<td valign="top"><bean:message key="prompt.eligible"/></td>
																<td valign="top"><bean:message key="prompt.receiving"/></td>
																<td class="subhead" valign="top">Received By</td>
				      											<td class="subhead" valign="top">Amount</td>
				      											<td class="subhead" valign="top">ID No.</td>
															</tr>

															<logic:iterate indexId="index" id="benefits" name="juvenileMemberForm" property="benefitsInfoList">
																<logic:notEmpty name="benefits" property="memberBenefitId">
																 <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>"> 
																	<td valign="top"><bean:write name="benefits" property="entryDate"/></td>
																	<td valign="top"><bean:write name="benefits" property="eligibilityType"/></td>
																	<td>
																		<logic:equal name="benefits" property="eligibleForBenefits" value="true">Yes</logic:equal>
																		<logic:notEqual name="benefits" property="eligibleForBenefits" value="true">No</logic:notEqual>
																	</td>
																	<td>
																		<logic:equal name="benefits" property="receivingBenefits" value="true">Yes</logic:equal>
																		<logic:notEqual name="benefits" property="receivingBenefits" value="true">No</logic:notEqual>
																	</td>
																	<td><logic:notEqual name="benefits" property="formattedName" value="NOT AVAILABLE"><bean:write name="benefits" property="receivedBy"/></logic:notEqual></td>
			  														<td><logic:equal name="benefits" property="receivingBenefits" value="true"><bean:write name="benefits" property="receivedAmt"/></logic:equal></td>
			  														<td><bean:write name="benefits" property="idNumber"/></td>
																</tr>
															</logic:notEmpty>
														</logic:iterate>
													</table>
							  				</td>
							  			</tr>
							  		</table>
							  		<script  type='text/javascript'>
							       if (location.search == "?confirmPC")
							   			{
							   				showHideMulti('Characteristics', 'pChar', 1)
							   			}
							   		</script>
						   		</logic:present>
								  <%-- END Previous Benefit TABLE --%>

		               <logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
										<div class='spacer'></div>				
										<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr>
												<td valign="top" class="detailHead"><bean:message key="prompt.new"/> <bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.benefit"/> <bean:message key="prompt.info"/>  - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
											</tr>
											<tr>
												<td align="center">
													<table border="0" cellpadding="2" cellspacing="1" width="100%">
														<tr>
															<td class="formDeLabel" width="12%" nowrap><bean:message key="prompt.2.diamond"/>Eligible for Benefits</td>
															<td class="formDe">Yes<html:radio name="juvenileMemberForm" property="currentBenefit.eligibleForBenefits" value="true"/>No<html:radio name="juvenileMemberForm" property="currentBenefit.eligibleForBenefits" value="false"/></td>
															<td class="formDeLabel" width="10%" nowrap><bean:message key="prompt.2.diamond"/>Receiving Benefits</td>
															<td class="formDe" colspan="8">Yes<html:radio name="juvenileMemberForm" property="currentBenefit.receivingBenefits" value="true" styleId="benefitsYes"/>No<html:radio name="juvenileMemberForm" property="currentBenefit.receivingBenefits" value="false" styleId="benefitsNo"/></td>
														</tr>	
													</table>
													<table border="0" cellpadding="2" cellspacing="1" width="100%">			
														<tr class="formDe">
															<td class="formDeLabel" width="12%" nowrap><bean:message key="prompt.2.diamond"/>Type of Eligibility</td>
														  	<td class="formDe" colspan="3">
																<html:select name="juvenileMemberForm" property="currentBenefit.eligibilityTypeId"> 
									                              <option value="">Please Select</option> 
									                               <html:optionsCollection name="juvenileMemberForm" property="benefitEligibilityList"  value="code" label="description"/>
									                            </html:select> 
															</td>
															<td>
				      											<table class="hidden" id="receivingBenefitsId">  	      								
					      											<td class=formDeLabel nowrap><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9>Received By</td>
					      											<td class=formDe>
					      											
					      												<html:select name="juvenileMemberForm" property="currentBenefit.receivedBy" styleId="receivedById">
					      													<logic:iterate id="receivedByIter" name="juvenileMemberForm" property="benefitsReceivers">
					      														<option value="<bean:write name='receivedByIter' property='formattedName'/>">
					      															<bean:write name="receivedByIter" property="formattedName"/>
						      														<logic:notEmpty name="receivedByIter" property="relationship">
						      															(<bean:write name="receivedByIter" property="relationship"/>)
						      														</logic:notEmpty>
					      														</option>			      													
					      													</logic:iterate>			      												
					      												</html:select>
					      												
					      											</td>
					      											<td class=formDeLabel nowrap><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9>Amount</td>
					      											<td class=formDe><html:text size="4" maxlength="7" property="currentBenefit.receivedAmt" styleId="receivedAmtId"/></td>
					      											<td class=formDeLabel nowrap>ID Number</td>
					      											<td class=formDe><html:text size="15" maxlength="64" property="currentBenefit.idNumber"/></td>
						      									</table>
				      										</td>
														</tr>
														<tr>
														  <td colspan='8' align='center'>
														  	<html:submit property="submitAction" styleId="addBenefitsList">
															  	<bean:message key="button.addToList"/>
																</html:submit> 
															</td>
														</tr> 
													</table>
												</td>
											</tr>
											<tr>
												<td>
													<logic:present name="NewRecords" >
													<div class='spacer'></div>
														<table width="100%" cellspacing="1">
															<tr class="subhead"  bgcolor="#CCCCCC" >
																<td valign="top" width="10%"></td>
																<td valign="top"><bean:message key="prompt.eligibilityType"/></td>
																<td valign="top"><bean:message key="prompt.eligible"/></td>
																<td valign="top"><bean:message key="prompt.receiving"/></td>
																<td class="subhead" valign="top">Received By</td>
				      											<td class="subhead" valign="top">Amount</td>
				      											<td class="subhead" valign="top">ID No.</td>
															</tr>
			
															<logic:iterate indexId='index' id="benefits" name="juvenileMemberForm" property="benefitsInfoList">
																<logic:empty name="benefits" property="memberBenefitId">
																 <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>"> 
																	<td valign="top">
																		<a href="/<msp:webapp/>submitManageFamilyMemberBenefitsAction.do?submitAction=Remove&selectedValue=<%=index%>">Remove</a>
																	</td>
																	<td valign="top"><bean:write name="benefits" property="eligibilityType"/></td>
																	<td><logic:equal name="benefits" property="eligibleForBenefits" value="true">Yes</logic:equal>
																		<logic:notEqual name="benefits" property="eligibleForBenefits" value="true">No</logic:notEqual>
																	</td>
																	<td>
																		<logic:equal name="benefits" property="receivingBenefits" value="true">Yes</logic:equal>
																		<logic:notEqual name="benefits" property="receivingBenefits" value="true">No</logic:notEqual>
																	</td>
																	<td><bean:write name="benefits" property="receivedBy"/></td>
			  														<td><logic:equal name="benefits" property="receivingBenefits" value="true"><bean:write name="benefits" property="receivedAmt"/></logic:equal></td>
			  														<td><bean:write name="benefits" property="idNumber"/></td>
																</tr>
															</logic:empty>
														</logic:iterate>
													</table>
				        				</logic:present>
											</td>
										</tr>
									</table>
									<%-- END DETAIL TABLE --%>
								</logic:notEqual>
			
								<%-- ### begin button table --%>
								<div class='spacer'></div>
								<table border="0" width="100%">
									<tr>
										<td align="center">
											<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit> 
												<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
													<logic:present name="NewRecords" >
														<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_BEN_U%>'>	
															<html:submit property="submitAction"><bean:message key="button.update"></bean:message></html:submit> 
														</jims2:isAllowed>
													</logic:present> <html:submit property="submitAction">
													<bean:message key="button.cancel"></bean:message>
												</html:submit>
											</logic:notEqual>
										</td>
									</tr>
								</table>
								<div class='spacer'></div>
								<%-- end button table --%>
								</td>
							</tr>
						</table>
						<div class='spacer'></div>
						<%-- end outer red --%>
					</td>
				</tr>
			</table><%-- end outer green --%>
		</td>
	</tr>
</table>
				
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
