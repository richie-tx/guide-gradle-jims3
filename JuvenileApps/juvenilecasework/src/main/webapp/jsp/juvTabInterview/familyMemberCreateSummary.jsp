<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 10/23/2013  CShimek        #76282 Made cosmetic change to Driver License/ID Card Information block --%>

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
<html:base/>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/juvTabInterview/familyMemberCreate.js"></script>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<title><bean:message key="title.heading"/> - familyMemberCreateSummary.jsp</title>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/submitCreateFamilyMember" >

<logic:equal name="juvenileMemberForm" property="action" value="createMemberSummary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|235">
</logic:equal>
<logic:equal name="juvenileMemberForm" property="action" value="findCreateMemberSummary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|235">
</logic:equal>
<logic:equal name="juvenileMemberForm" property="action" value="createMemberConfirmation">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|236">
</logic:equal>
<logic:equal name="juvenileMemberForm" property="action" value="updateMemberSummary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|246">
</logic:equal>
<logic:equal name="juvenileMemberForm" property="action" value="updateMemberConfirmation">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|248">
</logic:equal>

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
 	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/> <bean:message key="prompt.member"/>
  			<logic:equal name="juvenileMemberForm" property="action" value="createMemberSummary"><bean:message key="prompt.create"/> <bean:message key="prompt.summary"/></logic:equal>
  			<logic:equal name="juvenileMemberForm" property="action" value="findCreateMemberSummary"><bean:message key="prompt.create"/> <bean:message key="prompt.summary"/></logic:equal>
  			<logic:equal name="juvenileMemberForm" property="action" value="createMemberConfirmation"><bean:message key="prompt.create"/> <bean:message key="prompt.confirmation"/></logic:equal>
  			<logic:equal name="juvenileMemberForm" property="action" value="updateMemberSummary"><bean:message key="prompt.update"/> <bean:message key="prompt.summary"/></logic:equal>
  			<logic:equal name="juvenileMemberForm" property="action" value="updateMemberConfirmation"><bean:message key="prompt.update"/> <bean:message key="prompt.confirmation"/></logic:equal>
  			<div class='spacer'></div>
  		</td>	  	    	 
	</tr>  	
</table>

<table align="center" width="100%">
	<logic:equal name="juvenileMemberForm" property="action" value="createMemberConfirmation">
		<tr>
			<td align="center" class="confirm">The following Family Member #<bean:write name="juvenileMemberForm" property="memberNumber"/>&nbsp;has been successfully created.</td>
		</tr>
	</logic:equal>
			
	<logic:equal name="juvenileMemberForm" property="action" value="updateMemberConfirmation">
		<tr>
			<td align="center" class="confirm">The following Family Member #<bean:write name="juvenileMemberForm" property="memberNumber"/>&nbsp;has been successfully updated.</td>
		</tr>
	</logic:equal>
</table>

<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>
<%-- begin instruction table --%> 
<table width="98%" border="0"> 
	<tr> 
		<td> 
			<ul> 
				<logic:notEqual name="juvenileMemberForm" property="action" value="updateMemberConfirmation">
					<logic:notEqual name="juvenileMemberForm" property="action" value="createMemberConfirmation">
						<li>Verify that information is correct and select Finish button to proceed.</li> 
						<li>If any changes are needed, select Back button to return to previous page.</li> 
					</logic:notEqual>
				</logic:notEqual>
					
				<logic:equal name="juvenileMemberForm" property="action" value="updateMemberConfirmation">
					<logic:notEqual name="juvenileFamilyForm" property="action" value="">	
						<li>Click <bean:message key="button.returnToConstellation"/> to return back to the constellation.</li> 
					</logic:notEqual>
					<logic:equal name="juvenileFamilyForm" property="action" value="">	
						<li>Click Next to continue.</li> 
					</logic:equal>
				</logic:equal>

				<logic:equal name="juvenileMemberForm" property="action" value="createMemberConfirmation">
					<logic:notEqual name="juvenileFamilyForm" property="action" value="">	
						<li>Click <bean:message key="button.continueToCreateConstellationList"/> to return to create constellation list.</li> 
					</logic:notEqual>
					<logic:equal name="juvenileFamilyForm" property="action" value="">	
						<li>Click Next to continue.</li> 
					</logic:equal>
				</logic:equal>
			</ul>
		</td> 
	</tr> 
</table> 
<%-- end instruction table --%> 
<div class='spacer'></div>
<%-- begin detail table --%> 
<table cellpadding="0" cellspacing="0" border="0" width="100%" align='center'> 
	<tr> 
		<td> 
				<%--header info start--%> 
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader" />
			</tiles:insert> <%--header info end--%>
		</td> 
	</tr> 
</table> 

<div class='spacer'></div> 
<table width="98%" border="0" cellpadding="0" cellspacing="0" align='center'> 
	<tr> 
    	<td>
<%-- begin green tabs (1st row) --%> 
			<table width='100%' border="0" cellpadding="0" cellspacing="0" > 
				<tr> 
					<td valign='top'><%--tabs start--%>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab" />
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>
						<%--tabs end--%>
					</td> 
				</tr> 
				<tr> 
					<td bgcolor='#33cc66' height='5'></td> 
				</tr> 
			</table> 
<%-- end green tabs --%> 

<%-- begin outer green border --%> 
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen"> 
				<tr> 
					<td valign='top' align='center'> 
						<div class='spacer'></div> 
						<table width='98%' border="0" cellpadding="0" cellspacing="0" > 
							<tr> 
								<td valign='top'>
									<tiles:insert page="../caseworkCommon/memberInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" value="MemberInformation" />
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>
								</td> 
							</tr> 
							<tr> 
								<td bgcolor='#6699FF' height='5'></td> 
							</tr> 
						</table> 
<%-- end red tabs --%> 

<%--begin red outer border --%> 
			            <table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue"> 
							<tr> 
								<td valign='top' align='center'>
<%-- BEGIN FAMILY MEMBER TABLE  --%> 
									<div class='spacer'></div> 
									<table border="0" cellpadding="2" width='98%' cellspacing='1' class="borderTableBlue" > 
										<tr> 
											<td colspan='4' valign="top" class="detailHead"><bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.info"/> <bean:message key="prompt.summary"/></td> 
										</tr> 
										<tr> 
											<td class="formDeLabel"><bean:message key="prompt.name"/></td> 
											<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
										</tr>
										<tr> 
											<td class="formDeLabel"><bean:message key="prompt.over21"/></td> 
											<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="over21Str"/></td>
										</tr>
										<tr> 
											<td class="formDeLabel"><bean:message key="prompt.socialSecurity#"/></td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="ssn.formattedSSN"/>&nbsp;</td>
											<td class="formDeLabel"><bean:message key="prompt.dateOfBirth"/></td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="dateOfBirth"/></td>
										</tr>
										<tr>  
											<td class="formDeLabel"><bean:message key="prompt.sex"/></td> 
											<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="sex"/></td>
										</tr>										
										<tr> 
											<td class="formDeLabel"><bean:message key="prompt.usCitizen"/></td> 
											<td class="formDe" colspan="3"> <bean:write name="juvenileMemberForm" property="isUSCitizen"/> </td>
											</tr>
										<tr> 
											<td class="formDeLabel"><bean:message key="prompt.nationality"/></td> 
											<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="nationality"/></td>
										</tr>
										<tr>  
											<td class="formDeLabel"><bean:message key="prompt.ethnicity"/></td> 
											<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="ethnicity"/></td> 
										</tr> 
										<tr> 
											<td class="formDeLabel"><bean:message key="prompt.primaryLanguage"/></td> 
											<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="primaryLanguage"/></td>
										</tr>
										<tr>  
											<td class="formDeLabel"><bean:message key="prompt.secondaryLanguage"/></td> 
											<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="secondaryLanguage"/></td> 
										</tr> 
										<tr> 
											<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.isFamilyMemberDeceased"/></td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="deceasedYesNo"/></td>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.causeOfDeath"/></td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="causeofDeath"/></td> 
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.juvenileAgeAtFamilyMemberDeath"/></td> 
											<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="juvenileAgeAtDeath"/></td> 
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.isFamilyMemberIncarcerated"/></td> 
											<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="incarceratedYesNo"/></td> 
										</tr>
										<tr> 
											<td class="formDeLabel" valign='top' colspan='4'><bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.comments"/></td> 
										</tr> 
										<tr> 
											<td class="formDe" colspan='4'><bean:write name="juvenileMemberForm" property="familyMemberComments"/>&nbsp; </td> 
										</tr>
									</table>
<%-- end family member table  --%>
			   						<div class='spacer'></div> 
			   						<table width='98%' border="0" cellpadding="4" cellspacing="1" class='borderTableBlue'>
<%-- BEGIN DRIVER LICENSE/ID CARD INFORMATION --%>
										<tr>
											<td colspan='4' class="detailHead"><bean:message key="prompt.driverLicense"/>/<bean:message key="prompt.idCardInfo" /></td>
										</tr>
										<tr>   
											<td class="formDeLabel"><bean:message key="prompt.number" /></td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="driverLicenseNum"/></td> 
											<td class='formDeLabel'><bean:message key="prompt.state" /></td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="driverLicenseState"/></td>
										</tr>
										<tr>   
											<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.expirationDate" /></td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="driverLicenseExpirationDate"/></td> 
											<td class="formDeLabel"><bean:message key="prompt.class" /></td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="driverLicenseClass"/></td>
										</tr>
										<tr> 
											<td class='formDeLabel' width="1%" nowrap='nowrap'>
												<bean:message key="prompt.state" /> 
												<bean:message key="prompt.issued" />
												<bean:message key="prompt.idCard#" />
										</td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="stateIssuedIdNum"/></td>
											<td class="formDeLabel" width="1%"><bean:message key="prompt.state"/></td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="issuedByState"/></td> 
										</tr>
<%--adding passport details --%>
										<tr>                      
											<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.passportNumber" /></td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="passportNum"/></td> 
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.countryOfIssuance" /></td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="countryOfIssuance"/></td>
										</tr>  
										<tr>  
											<td class="formDeLabel"><bean:message key="prompt.stateidSID"/></td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="sidNum"/></td> 
											<td class="formDeLabel"><bean:message key="prompt.alienReg#"/></td> 
											<td class="formDe"><bean:write name="juvenileMemberForm" property="alienNum"/></td> 
										</tr> 
										<tr>   
											<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.expirationDate" /></td> 
											<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="passportExpirationDate"/></td>                   
										</tr>
<%--end of passport details --%>
									</table>
<%-- end DRIVER LICENSE/ID CARD INFORMATION --%>
            			    	   <div class='spacer'></div> 
<%-- BEGIN Marital Status TABLE --%>
			    				    <table width='98%' border="0" cellpadding="0" cellspacing="1" class="borderTableBlue">
			    					    <tr>
			    					    	<td class='detailHead'><bean:message key="prompt.marital" /> <bean:message key="prompt.status" /></td>
			    					    </tr>
			                    		<tr>
			                      			<td>
												<table width='100%' border="0" cellpadding="2" cellspacing="1">
													<tr bgcolor='#cccccc'>
														<td class="formDeLabel"><bean:message key="prompt.marital" /> <bean:message key="prompt.status" /></td>
														<td class='formDeLabel'><bean:message key="prompt.relationship"/> With</td>
														<td class="formDeLabel"><bean:message key="prompt.marriage" /><bean:message key="prompt.date" /></td>
														<td class="formDeLabel"><bean:message key="prompt.divorce" /><bean:message key="prompt.date" /></td>
														<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.#OfChildren" /></td>
													</tr>
													<tr> 
														<td class="formDe"><bean:write name="juvenileMemberForm" property="maritalStatus"/></td>
														<td class="formDe">
															<logic:notEmpty name="juvenileMemberForm" property="relatedFamMemId">
																<logic:notEqual name="juvenileMemberForm" property="relatedFamMemId" value=""> 
																	<bean:write name="juvenileMemberForm" property="relatedFamMemId"/> - <bean:write name="juvenileMemberForm" property="relatedFamMemName.formattedName"/>
																</logic:notEqual>
															</logic:notEmpty>
															&nbsp;
														</td>
														<td class="formDe"><bean:write name="juvenileMemberForm" property="marriageDate"/></td> 
														<td class="formDe"><bean:write name="juvenileMemberForm" property="divorceDate"/></td>
														<td class="formDe"><bean:write name="juvenileMemberForm" property="numOfChildren"/></td> 
													</tr>
												</table>
											</td>		
										</tr> 
									</table> 
	                  				<div class='spacer'></div> 
<%-- begin button table --%>
			      					<table border="0" width="100%"> 
			      						<tr> 
			      							<td align="center"> 
			         							<logic:equal name="juvenileMemberForm" property="action" value="createMemberSummary">
			       						    		<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
			       						      		<html:submit property="submitAction" styleId="createSummaryFinish">
			      										  <bean:message key="button.finish"></bean:message>
			       									</html:submit>
			             							<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
			         							</logic:equal>
			         							
			         							<logic:equal name="juvenileMemberForm" property="action" value="findCreateMemberSummary">
			       						    		<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
			       						      		<html:submit property="submitAction" styleId="createSummaryFinish">
			      										  <bean:message key="button.finish"></bean:message>
			       									</html:submit>
			             							<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
			         							</logic:equal>
			
			         							<logic:equal name="juvenileMemberForm" property="action" value="updateMemberSummary">
													<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
													<html:submit property="submitAction" styleId="updateSummaryFinish"><bean:message key="button.finish"></bean:message></html:submit>
													<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
			         							</logic:equal>
			
			         							<logic:equal name="juvenileMemberForm" property="action" value="createMemberConfirmation">
			         								<logic:notEqual name="juvenileFamilyForm" property="action" value="">	
			         									<html:submit property="submitAction"><bean:message key="button.continueToCreateConstellationList"/></html:submit>
			         								</logic:notEqual>
			         							</logic:equal>
			         							<logic:equal name="juvenileMemberForm" property="action" value="updateMemberConfirmation">
			        									<!-- <input type="button" value="Return to Family Constellation" id="displayConList" data-href="displayFamilyConstellationList.do" >
			         									 bug fix for 132271-->
			         									 <input type="button" value="Return to Family Constellation" onClick="goNav('/<msp:webapp/>displayFamilyConstellationList.do')">
			         							</logic:equal>
				      						</td>
			      						</tr> 
			      					</table>
<%-- end button table --%>
			     					<div class='spacer'></div> 
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
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>