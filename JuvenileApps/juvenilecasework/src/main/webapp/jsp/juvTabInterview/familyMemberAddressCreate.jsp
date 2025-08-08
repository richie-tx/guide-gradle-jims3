<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/09/2007	LDeen		Revised to add link to address.js --%>
<%-- 06/02/2010	LDeen		Defect#65807 Revised new address code to work with new list --%>
<%-- 10/24/2012 DGibler		73746 MJCW: Add Street Number suffix field --%>

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
<html:javascript formName="juvenileMemberFormAddr"/>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/juvTabInterview/familyMemberAddressCreate.js"></script>

<script type="text/javascript">
$(document).ready(function (){

	$("#updateMemberAddress").on("click", function(){
			spinner();
			$('form').attr('action','/JuvenileCasework/displayManageFamilyMemberAddressAction.do?submitAction=Link&source=MemberAddressBtn');
			$('form').submit();
	});

});
</script>


<title><bean:message key="title.heading" /> - familyMemberAddressCreate.jsp</title>
</head>

<%--END HEADER TAG--%>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">
<html:form action="/submitManageFamilyMemberAddressAction">

<logic:equal name="juvenileMemberForm" property="action" value="viewOnly">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|250">
</logic:equal>
<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|249">
</logic:notEqual>

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.address"/>
			<logic:equal name="juvenileMemberForm" property="action" value="viewOnly"><bean:message key="title.details" /></logic:equal>
			<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly"><bean:message key="title.create" /></logic:notEqual>
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
	<div class='spacer'></div>	
<%-- begin instruction table --%>
	<table width="98%" border="0">
		<tr>
			<td>
		  		<ul>
		  			<li>Add new address information for family member and click Add button.</li>
		  			<li>Click Remove link to remove address information just added.</li>
		  
		  			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_AI_U%>'>		
		 					<li>Review information and click Update button to proceed.</li>
		  			</jims2:isAllowed>
				</ul>
			</td>
		</tr>
		<tr>
    		<td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.2.diamond"/>Required for Validate Address.</td>
  		</tr>
	</table>
<%-- end instruction table --%>
</logic:notEqual>
<div class='spacer'></div>
<%-- begin profile header table --%>
<table align="center" cellpadding="1" cellspacing="0" border="0" width="100%">
	<tr>
		<td><%--header info start--%> <tiles:insert
			page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
			<tiles:put name="headerType" value="profileheader" />
		</tiles:insert> <%--header info end--%></td>
	</tr>
</table>
<%-- end profile header table --%>

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
					<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class='spacer'></div>
<%-- begin member info tabs table) --%>
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign="top">
 									<tiles:insert page="../caseworkCommon/memberInfoTabs.jsp" flush="true">
  										<tiles:put name="tabid" value="AddressInfo" />
  										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  									</tiles:insert>
								</td>
							</tr>
							<tr>
								<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
							</tr>
						</table>
<%-- end info tabs table --%>
<%--begin red outer border --%>
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	  						<tr>
  								<td valign="top" align="center">
									<logic:notEmpty name="juvenileMemberForm" property="addressList">
										<logic:iterate id="addresses" name="juvenileMemberForm" property="addressList">
											<logic:notEmpty name="addresses" property="memberAddressId">
												<logic:notPresent name="ExistingRecords">
													<bean:define id="ExistingRecords" value="1" type="java.lang.String"/>
												</logic:notPresent>
											</logic:notEmpty>
											<logic:empty name="addresses" property="memberAddressId">
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
															<td class='detailHead'>&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.address"/> <bean:message key="prompt.info"/> - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td valign='top' class='normalRow'>No Address Information Available</td>
											</tr>
										</table>
										<div class='spacer'></div>
										<html:hidden styleId="memberId" name="juvenileMemberForm" property="memberNumber"/>
									</logic:notPresent>

									<logic:present name="ExistingRecords">
										<div class='spacer'></div>
										<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
											<tr>
												<td valign='top' class='detailHead'>
													<table width='100%' cellpadding='0' cellspacing='0'>
														<tr>
															<td width='1%'><a href="javascript:showHideMulti('Characteristics', 'pChar', 1,'/<msp:webapp/>')" border='0'>
																<img border='0' src="../../images/contract.gif" name="Characteristics"></a></td>
															<td class='detailHead'>&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.address"/> <bean:message key="prompt.info"/> - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr id="pChar0" class='visible'>
												<td valign='top'>
													<table width="100%" bgcolor="#cccccc" cellspacing="1">
														<tr bgcolor="#f0f0f0">
															<td class="subhead" valign="top" width="50px">OID</td>
															<td class="subhead" valign="top" width="5%"><bean:message key="prompt.entryDate"/></td>
															<td class="subhead" valign="top"><bean:message key="prompt.addressType"/></td>
															<td class="subhead" valign="top"><bean:message key="prompt.address"/></td>
															<td class="subhead" valign="top"><bean:message key="prompt.validationSymbol"/></td>
															<td class="subhead" valign="top"><bean:message key="prompt.county"/></td>
														</tr>
														<%int RecordCounter = 0;%>  
														<logic:iterate id="addresses" name="juvenileMemberForm" property="addressList">
															<logic:notEmpty name="addresses" property="memberAddressId">
																<tr class="<%out.print((RecordCounter % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																	<% RecordCounter++;%>
																	<td valign="top" align="center" width="50px">
																		<bean:write name="addresses" property="memberAddressId"/>
    																</td>
																	<td valign="top" align="center" width="5%">
																		<bean:write name="addresses" property="entryDateString"/>
    																</td>
																	<td valign="top"><bean:write name="addresses" property="addressType"/></td>
																	<td>
																		<a href="javascript:openMapquest('<bean:write name="addresses" property="streetNumber"/>', '<bean:write name="addresses" property="streetName" /> <bean:write name="addresses" property="streetTypeId" />', '<bean:write name="addresses" property="city" />', '<bean:write name="addresses" property="stateId" />', '<bean:write name="addresses" property="zipCode" />', '','<bean:write name="addresses" property="county" />')" title="Click to open Mapquest">       					  
																			<bean:write name="addresses" property="streetNumber"/>&nbsp;
																			<logic:notEmpty name="addresses" property="streetNumSuffix">
																				<bean:write name="addresses" property="streetNumSuffix"/>&nbsp;
																			</logic:notEmpty>
																			<bean:write name="addresses" property="streetName"/>
																			<logic:notEmpty name="addresses" property="streetType">
				    														<logic:notEqual name="addresses" property="streetType" value="">
				  															 &nbsp;<bean:write name="addresses" property="streetType"/>
				    														</logic:notEqual>
				    													</logic:notEmpty>	

				    													<logic:notEmpty name="addresses" property="aptNumber">
				    														<logic:notEqual name="addresses" property="aptNumber" value="">
				    															 &nbsp;<bean:write name="addresses" property="aptNumber"/>
				    														</logic:notEqual>
				    													</logic:notEmpty>	
																		,&nbsp; 
																		<bean:write name="addresses" property="city"/>&nbsp;
																		<bean:write name="addresses" property="state"/> &nbsp;
																		<bean:write name="addresses" property="zipCode"/>
																		<logic:notEmpty name="addresses" property="additionalZipCode">
																			-<bean:write name="addresses" property="additionalZipCode"/>
																		</logic:notEmpty></a>
																	</td>
																	<td>
  													   <%-- based on the Address validation, display a specific icon --%>
				  														<logic:notEmpty name="addresses" property="validated">
																				<logic:equal name="addresses" property="validated" value="Y">
					  																<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
																				</logic:equal>
																				<logic:equal name="addresses" property="validated" value="N">
				  																	<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
																				</logic:equal>
				 														</logic:notEmpty>
																	</td>
																	<td valign="top"><bean:write name="addresses" property="county"/></td>
																</tr>
															</logic:notEmpty>
														</logic:iterate>
													</table>
				 				 				</td>
				  							</tr>
				  						</table>
								  		<script type='text/javascript'>
									    	if (location.search == "?confirmPC")
				   							{
				   								showHideMulti('Characteristics', 'pChar', 1)
				   							}
				   						</script>

								   		<div class='spacer'></div>
							   		</logic:present>
    				  <%-- END Previous Address TABLE --%>
          							<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
											<%-- begin address info outer table --%>
										<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr bgcolor="#B3C9F5">
												<td class="subhead"><bean:message key="prompt.new"/> <bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.address"/> <bean:message key="prompt.info"/> - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
												<td align="right" class="detailHead">&nbsp; 
													<input type='submit' value="<bean:message key='button.validate' />" name="submitAction" onClick="return validateAddrAction('juvenileMemberForm','/<msp:webapp/>validateMemberAddress.do','', 'currentAddress','/jsp/juvTabInterview/familyMemberAddressCreate.jsp', false);"></input>
										    	    <!--
										    	    <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="return displayResearchWindow();">
														  <bean:message key="button.research"></bean:message>
													</html:button>	
													-->
	   											</td>
												<td align="right" class="detailHead" nowrap='nowrap'><bean:message key="prompt.addressStatus"/>:</td>
										   	    <td class="errorAlert">
					<%-- TODO replace logic tags with code table values --%>	 
													<logic:equal name="juvenileMemberForm" property="addressStatus" value="">
														UNPROCESSED
													</logic:equal>       	    
													<logic:equal name="juvenileMemberForm" property="addressStatus" value="U">
														UNPROCESSED
													</logic:equal>
													<logic:equal name="juvenileMemberForm" property="addressStatus" value="Y">
														VALID
													</logic:equal>
													<logic:equal name="juvenileMemberForm" property="addressStatus" value="N">
														INVALID
													</logic:equal>
													&nbsp;
								   	    		</td>		  
											</tr>
											<logic:notEqual name="juvenileFamilyForm" property="action" value="update">
												<tr>
													<td class="detailhead" colspan="4">
														<a id="newWindow" >Click here if same as another family member</a>
													</td>
												</tr>
											</logic:notEqual>
											<tr>
												<td colspan="4">
								<%-- begin address info inner table --%>
													<table border="0" cellspacing="1" width="100%">
														<tr>
															<td class="formDeLabel">
																<bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" />
																<bean:message key="prompt.streetNumber"/>
															</td>
															<td class="formDeLabel">
																<bean:message key="prompt.streetNumber"/>&nbsp;<bean:message key="prompt.suffix"/>
															</td>
															<td class="formDeLabel">
																<bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" />
																<bean:message key="prompt.streetName"/>
															</td>
															<td class="formDeLabel">
																<input type="checkbox" name="unknownAddress"/>&nbsp;Address Unknown (Residence)															
															</td>
															<td class="formDeLabel">
																<input type="checkbox" name="outOfCountry" />&nbsp;Out of Country (Residence)															
															</td>
														</tr>
														<tr>			
															<td class="formDe"><html:text name="juvenileMemberForm" styleId="streetNumber" property="currentAddress.streetNumber" size="10" maxlength="9" /></td>
															<td class="formDe">
																<html:select name="juvenileMemberForm" property="currentAddress.streetNumSuffixId" size="1">
																	<option value="">Please Select</option>
																	<html:optionsCollection name="juvenileMemberForm" property="streetNumSuffixList"  value="code" label="description"/>
																</html:select>
															</td>
															<td class="formDe" colspan="3"><html:text name="juvenileMemberForm" styleId="streetName" property="currentAddress.streetName" size="30" maxlength="50" /></td>
														</tr>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.streetType"/></td>
															<td class="formDeLabel" colspan="4" nowrap='nowrap'><bean:message key="prompt.aptSuite"/></td>
														</tr>
														<tr>	
															<td class="formDe">
																<html:select name="juvenileMemberForm" property="currentAddress.streetTypeId" size="1">
																	<option value="">Select One</option>
																	<html:optionsCollection name="juvenileMemberForm" property="streetTypeList"  value="code" label="description"/>
																</html:select>
															</td>
															<td class="formDe" colspan="4">
																<html:text name="juvenileMemberForm" property="currentAddress.aptNumber" styleId="apt" size="10" maxlength="10" />
															</td>
														</tr>
														<tr>
															<td class="formDeLabel">
																<bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" />
																<bean:message key="prompt.city"/>
															</td>
															<td class="formDeLabel">
  																<bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" />
																<bean:message key="prompt.state"/>
															</td>
															<td class="formDeLabel" colspan="3">
  																<bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" />
																<bean:message key="prompt.zipCode"/>
															</td>
														</tr>
														<tr>			
															<td class="formDe"><html:text name="juvenileMemberForm" styleId="city" property="currentAddress.city" size="15" maxlength="15"></html:text></td>
															<td class="formDe">
																<html:select name="juvenileMemberForm" property="currentAddress.stateId" size="1" styleId="stateId" onchange="javascript:enableCounty(this, this.name)">
																	<option value="">Please Select</option>
																		<html:optionsCollection name="juvenileMemberForm" property="stateList"  value="code" label="description"/>
																	</html:select>
															</td>
															<td class="formDe" colspan="3">
																<html:text name="juvenileMemberForm" styleId="zipCode" property="currentAddress.zipCode" size="5" maxlength="5" onkeyup="return autoTab(this, 5);" /> - 
																<html:text name="juvenileMemberForm" styleId="additionalZipCode" property="currentAddress.additionalZipCode" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
															</td>
														</tr>
														<tr>
															<td class="formDeLabel">
																<bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" />
																<bean:message key="prompt.addressType"/>
															</td>
															<td class="formDeLabel" colspan="4"><bean:message key="prompt.county"/></td>
														</tr>
														<tr>	
															<td class="formDe">
																<html:select name="juvenileMemberForm" styleId="addressType" property="currentAddress.addressTypeId">
																	<option value="">Please Select</option>
																	<html:optionsCollection name="juvenileMemberForm" property="addressTypeList"  value="code" label="description"/>
																</html:select>
															</td>
															<td class="formDe" colspan="4">
																<span class="hidden" id="emptySelect1"> 
																	<select disabled='disabled'>
    																	<option>Please Select</option>
    																</select> 
																</span> 
																<span class="visible" id="county1Span"> 
 																	<html:select name="juvenileMemberForm" property="currentAddress.countyId" styleId="county1" >
    																	<option value="">Please Select</option>
    																	<html:optionsCollection name="juvenileMemberForm" property="countyList"  value="code" label="description"/>
    																</html:select> 
																</span>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<div class="paddedFourPix" align="center">
	      												<html:submit property="submitAction" styleId="validateAddress">
   															<bean:message key="button.addToList"/>
   														</html:submit>
   														<input type="button" name="reset" value="<bean:message key='button.refresh'/>" /> 
  													</div>
															
	<%--            							 <logic:present name="NewRecords" >  --%>
													<logic:notEmpty name="juvenileMemberForm" property="newAddressList">
                                                    	<logic:notPresent name="NewRecords">
                                                        	<bean:define id="NewRecords" value="1" type="java.lang.String"/>
														</logic:notPresent>
														<table width="100%" bgcolor="#cccccc" cellspacing="1">
															<tr bgcolor="#f0f0f0">
																<td class="subhead" valign="top" width="1%"><bean:message key="prompt.remove"/></td>
																<td class="subhead" valign="top"><bean:message key="prompt.addressType"/></td>
																<td class="subhead" valign="top"><bean:message key="prompt.address"/></td>
																<td class="subhead" valign="top"><bean:message key="prompt.validationSymbol"/></td>
																<td class="subhead" valign="top"><bean:message key="prompt.county"/></td>
															</tr>
															<logic:iterate id="addresses" name="juvenileMemberForm" property="newAddressList" indexId="index">
																<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																	<td valign="top" align="center" width="1%">                               
																		<a href="/<msp:webapp/>submitManageFamilyMemberAddressAction.do?submitAction=Remove&selectedValue=<%= index.intValue()%>">
																		<bean:message key="prompt.remove"/></a>
																	</td>
																	<td valign="top"><bean:write name="addresses" property="addressType"/></td>
																	<td><bean:write name="addresses" property="streetNumber"/>&nbsp;
																		<logic:notEmpty name="addresses" property="streetNumSuffix">
																			<bean:write name="addresses" property="streetNumSuffix"/>&nbsp;
																		</logic:notEmpty>
																		<bean:write name="addresses" property="streetName"/>
																		<logic:notEmpty name="addresses" property="streetType">
																			<logic:notEqual name="addresses" property="streetType" value="">
																				&nbsp;<bean:write name="addresses" property="streetType"/>
																			</logic:notEqual>
																		</logic:notEmpty>    
																		<logic:notEmpty name="addresses" property="aptNumber">
																			<logic:notEqual name="addresses" property="aptNumber" value="">
																				&nbsp;<bean:write name="addresses" property="aptNumber"/>
																			</logic:notEqual>
																		</logic:notEmpty>    
																		,&nbsp; 
																		<bean:write name="addresses" property="city"/>&nbsp;
																		<bean:write name="addresses" property="state"/> &nbsp;
																		<bean:write name="addresses" property="zipCode"/>
																		<logic:notEmpty name="addresses" property="additionalZipCode">
																			-<bean:write name="addresses" property="additionalZipCode"/>
																		</logic:notEmpty>
																	</td>
                                                                    <td>
                                            <%-- based on the Address validation, display a specific icon --%>
																		<logic:notEmpty name="addresses" property="validated">
																			<logic:equal name="addresses" property="validated" value="Y">
																				<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
																			</logic:equal>
																			<logic:equal name="addresses" property="validated" value="N">
																				<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
																			</logic:equal>
																		</logic:notEmpty>
																	</td>
																	<td valign="top"><bean:write name="addresses" property="county"/></td>
																</tr>
                                                        <%--      </logic:empty> --%>
															</logic:iterate>
														</table>
													</logic:notEmpty> 
												<%--      </logic:present>  --%>
												</td>
											</tr>
										</table>
			        				</logic:notEqual>
									<div class='spacer'></div>
  					<%-- ### begin button table --%>		
									<table border="0" width="100%">
										<tr>
											<td align="center"><html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit> 
												<logic:notEqual name="juvenileMemberForm" property="action" value=" ">
													<logic:notEqual name="juvenileMemberForm" property="action" value="update">
														<input type="button" value="Update Member Address" id="updateMemberAddress">
													</logic:notEqual>
												</logic:notEqual>
												<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
													<logic:present name="NewRecords" >
														<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_AI_U%>'>		
															<html:submit property="submitAction" styleId="updateBtn"><bean:message key="button.update"></bean:message></html:submit> 
														</jims2:isAllowed>
													</logic:present> 
													<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
												</logic:notEqual>
											</td>
										</tr>
									</table>
      								<div class='spacer'></div>
      					<%-- end button table --%>
        						</td>
        					</tr>
      					</table>
<%-- end red outer border --%>      					
      					<div class='spacer'></div>
		      		</td>
      			</tr>
      		</table>
      <%-- end outer green --%>
    	</td>
  	</tr>
</table>
<%-- BEGIN HIDDEN FIELDS FOR ADDRESS VALIDATION --%>
<table width='100%'>
	<tr>
		<td>
			<html:hidden property="validStreetNum" value="" />
			<html:hidden property="validStreetName" value="" />
			<html:hidden property="validZipCode" value="" />
			<html:hidden property="validAddrNum" value="" />
			<html:hidden property="inputPage" value="" />
			<html:hidden property="currentAddressInd" value="" />
		</td>
	</tr>	  
</table>
<%-- ENd HIDDEN FIELDS FOR ADDRESS VALIDATION --%>
<div class='spacer'></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>