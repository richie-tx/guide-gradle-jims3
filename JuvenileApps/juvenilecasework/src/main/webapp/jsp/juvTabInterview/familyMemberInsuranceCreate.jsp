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
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<html:javascript formName="juvenileMemberFormIns"/>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyMemberInsuranceCreate.js"></script>
<title><bean:message key="title.heading" /> - familyMemberInsuranceCreate.jsp</title>
</head>
<%--END HEADER TAG--%>


<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/submitManageFamilyMemberInsuranceAction" >


<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|257">
</logic:notEqual>
<logic:equal name="juvenileMemberForm" property="action" value="viewOnly">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|258">
</logic:equal>

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
		<tr>
				<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.insurance"/>
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

<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
				
<%-- begin instruction table --%>
<div class='spacer'></div>
<table width="98%" border="0">
   <tr>
     <td>
	  <ul>
       <li>Add new insurance information for family member and click Add button.</li>
        <li>Click Remove link to remove insurance information just added.</li>
          <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_INS_U%>'>	
		<li>Review information and click the Update button to proceed.</li>
		</jims2:isAllowed>
	  </ul>	
		</td>
  </tr>
  <tr>
  	<td class="required"> <img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9"> Indicates Required Fields</td>
  </tr>
</table>
<%-- end instruction table --%>
</logic:notEqual>


<%-- begin detail table --%>
<table cellpadding="1" cellspacing="0" border="0" width="100%" align="center">
	<tr>
		<td>
			<%--header info start--%> <tiles:insert
					page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
					<tiles:put name="headerType" value="profileheader" />
				</tiles:insert> <%--header info end--%>
		</td>
	</tr>
</table>


<%-- begin the tabs and data --%>
<div class='spacer'></div>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td>
			<%-- begin green tabs (1st row) --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<%--tabs start--%> <tiles:insert
								page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
								<tiles:put name="tabid" value="family" />
								<tiles:put name="juvnum"
									value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
							</tiles:insert> <%--tabs end--%>
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66"><img src="../../images/spacer.gif" width="5"></td>
				</tr>
			</table><%-- end green tabs --%>

			<%-- begin outer green border --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						
									<%-- begin red tabs (3rd row) --%>
									<div class='spacer'></div>
									<table width='98%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign="top">
												<%--tabs start--%> <tiles:insert
														page="../caseworkCommon/memberInfoTabs.jsp" flush="true">
														<tiles:put name="tabid" value="Insurance" />
														<tiles:put name="juvnum"
															value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
													</tiles:insert> <%--tabs end--%>
											</td>
										</tr>
										<tr>
											<td bgcolor="#6699FF"><img src="../../images/spacer.gif" width="5"></td>
										</tr>
									</table><%-- end red tabs --%>

									<%--begin red outer border --%>
									<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<%-- Be sure to include the autoTab.js --%>
											<td valign="top" align="center">

												<%-- BEGIN Previous insurance TABLE --%>
  						  				<logic:notEmpty name="juvenileMemberForm" property="insuranceInfoList">
  												<logic:iterate id="insurances" name="juvenileMemberForm" property="insuranceInfoList">
  												<logic:notEmpty name="insurances" property="memberInsuranceId">
  													<logic:notPresent name="ExistingRecords">
  													 <bean:define id="ExistingRecords" value="1" type="java.lang.String"/>
  													 </logic:notPresent>
  												</logic:notEmpty>
  												<logic:empty name="insurances" property="memberInsuranceId">
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
        						  				<td class="detailHead" >
        						  					<table width='100%' cellpadding="0" cellspacing="0">
        						  						<tr>
        						  							<td class="detailHead">&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.insurance"/> <bean:message key="prompt.info"/> - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
        						  						</tr>
        						  					</table>
        						  				</td>
        						  			</tr>
        						  			<tr class="normalRow">
        						  				<td valign="top" class="normalRow">
        						  					No Insurance Information Available
        						  				</td>
        						  			</tr>
        						  			</table>
        						  			</logic:notPresent>

        									<logic:present name="ExistingRecords">
													<div class='spacer'></div>
        						  		<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
        						  			<tr>
        						  				<td valign="top" class="detailHead">
        						  					<table width='100%' cellpadding="0" cellspacing="0">
        						  						<tr>
        						  							<td width='1%'><a href="javascript:showHideMulti('Characteristics', 'pChar', 1,'/<msp:webapp/>')" border="0"><img border="0" src="../../images/expand.gif" name="Characteristics"></a></td>
        						  							<td class="detailHead">&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.insurance"/> <bean:message key="prompt.info"/> - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
        						  						</tr>
        						  					</table>
        						  				</td>
        						  			</tr>
        						  			<tr id="pChar0" class="hidden">
        						  				<td valign="top">
																<table width='100%' bgcolor="#cccccc" cellspacing="1">
																<tr bgcolor="#f0f0f0">
																	<td class="subhead" valign="top" width="10%"><bean:message key="prompt.entryDate"/></td>
																	<td class="subhead" valign="top"><bean:message key="prompt.insurance"/> <bean:message key="prompt.type"/></td>
																	<td class="subhead" valign="top"><bean:message key="prompt.policy"/> #</td>
																	<td class="subhead" valign="top"><bean:message key="prompt.insuranceCarrier"/></td>
																</tr>
																<%int RecordCounter = 0;
																			String bgcolor = "";%>  
																		<logic:iterate id="insurances" name="juvenileMemberForm" property="insuranceInfoList">
																		<logic:notEmpty name="insurances" property="memberInsuranceId">
																		 <tr class=<%RecordCounter++;
																			bgcolor = "alternateRow";
																			if (RecordCounter % 2 == 1)
																				bgcolor = "normalRow";
																			out.print(bgcolor);%>> 
																	<td><bean:write name="insurances" property="entryDate"/></td>
																	<td><bean:write name="insurances" property="insuranceType"/></td>
																	<td><bean:write name="insurances" property="policyNumber"/></td>
																	<td><bean:write name="insurances" property="insuranceCarrier"/> </td>
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
						  <%-- END Previous Insurance TABLE --%>


									<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
											<div class='spacer'></div>					
												<table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue">
													<tr>
														<td valign="top" class="detailHead"><bean:message key="prompt.new"/> <bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.insurance"/> <bean:message key="prompt.info"/>  - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
													</tr>
													<tr>
														<td align="center">
															<table border="0" cellpadding="2" cellspacing="1" width='100%'>
																<tr>
																	<td class="formDeLabel" width="20%"><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9"><bean:message key="prompt.health"/> <bean:message key="prompt.insurance"/> <bean:message key="prompt.type"/></td>
																	<td class="formDe"><html:select name="juvenileMemberForm" property="currentInsurance.insuranceTypeId" >
																		<option value="">Select One</option>
																		<html:optionsCollection name="juvenileMemberForm"
																		property="insuranceTypeList"  value="code" label="description"/>
                                  
																		</html:select>
																	</td>
																</tr>
																<tr id='insCarrier' class='hidden'>		
																	<td class="formDeLabel"><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9"><bean:message key="prompt.insuranceCarrier"/></td>
																	<td class="formDe" colspan="1"><html:text name="juvenileMemberForm" property="currentInsurance.insuranceCarrier" size="60" /></td>
																</tr>
																<tr id='policyNum' class='hidden'>
																	<td class="formDeLabel"><bean:message key="prompt.policy"/> #</td>
																	<td class="formDe"><html:text name="juvenileMemberForm" property="currentInsurance.policyNumber"/></td>
																</tr>
																
															</table>

															
														</td>
													</tr>
													<tr>
														<td align="center"> 
                               									<html:submit property="submitAction" styleId="addInsuranceList">
																	<bean:message key="button.addToList"/>
															   </html:submit>
                            </td>
													</tr>
            								<tr><td>

														<logic:present name="NewRecords" >
															<table width='100%' bgcolor="#cccccc" cellspacing="1">
																<tr bgcolor="#f0f0f0">
																	<td width="10%"></td>
																	<td class="subhead" valign="top"><bean:message key="prompt.insurance"/> <bean:message key="prompt.type"/></td>
																	<td class="subhead" valign="top"><bean:message key="prompt.policy"/> #</td>
																	<td class="subhead" valign="top"><bean:message key="prompt.insuranceCarrier"/></td>
																</tr>
																<%int RecordCounter = 0;
																			String bgcolor = "";%>  
																	<logic:iterate id="insurances" name="juvenileMemberForm" property="insuranceInfoList">
																	<logic:empty name="insurances" property="memberInsuranceId">
																	 <tr class=<%RecordCounter++;
																		bgcolor = "alternateRow";
																		if (RecordCounter % 2 == 1)
																			bgcolor = "normalRow";
																		out.print(bgcolor);%>> 
																	<td><a href="/<msp:webapp/>submitManageFamilyMemberInsuranceAction.do?submitAction=Remove&selectedValue=<%=(RecordCounter-1)%>"> Remove</a></td>
																	<td><bean:write name="insurances" property="insuranceType"/></td>
																	<td><bean:write name="insurances" property="policyNumber"/></td>
																	<td><bean:write name="insurances" property="insuranceCarrier"/> </td>
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
												<div class="spacer"></div>
												<table border="0" width="100%">
														<tr>
															<td align="center"><html:submit property="submitAction">
																<bean:message key="button.back"></bean:message>
															</html:submit> 
															<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
															<logic:present name="NewRecords" >
															 <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_INS_U%>'>	
															 <html:submit property="submitAction">
																<bean:message key="button.update"></bean:message>
															</html:submit> 
															</jims2:isAllowed>
															</logic:present> <html:submit property="submitAction">
																<bean:message key="button.cancel"></bean:message>
															</html:submit>
															</logic:notEqual>
															</td>
														</tr>
												</table>
												<div class="spacer"></div>
												<%-- end button table --%>
											
											</td>
										</tr>
									</table>
								  <div class="spacer"></div>
									<%-- end outer red --%>
					</td>
				</tr>
			</table><%-- end outer green --%>
		</td>
	</tr>
</table>

				
	<%-- ************************************************************ --%>


</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
