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
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>



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
<html:javascript formName="juvenileMemberFormContact"/>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyMemberContactCreate.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script type="text/javascript">
	$(document).ready(function (){
	
		$("#updateMemberContact").on("click", function(){
				spinner();
				$('form').attr('action','/JuvenileCasework/displayManageFamilyMemberContactAction.do?submitAction=Link&source=MemberContactBtn');
				$('form').submit();
		});
	
	});
</script>

<title><bean:message key="title.heading" /> -familyMemberContactCreate.jsp</title>
</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/submitManageFamilyMemberContactAction">


<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|253">
</logic:notEqual>
<logic:equal name="juvenileMemberForm" property="action" value="viewOnly">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|254">
</logic:equal>


	<%-- BEGIN HEADING TABLE --%>
	<table width="98%">
			<tr>
				<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.contact"/>

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
				
	<%-- begin instruction table  --%>
<div class=spacer></div>
	<table width="98%" border="0">
			<tr>
				<td>
				<ul>
					<li>Add new contact information for family member and click Add
					button.</li>
					<li>Click Remove link to remove contact information just added.</li>
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_CI_U%>'>	
					<li>Review information and click the Update button to proceed.
					
					</li>
					</jims2:isAllowed>
				</ul>
				</td>
			</tr>
	</table>
	<%-- end  instruction table  --%>
</logic:notEqual>


	<%-- begin header info --%>
	<table cellpadding="1" cellspacing="0" border="0" width="100%"
		align="center">
			<tr>
				<td ><%--header info start--%> <tiles:insert
					page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
					<tiles:put name="headerType" value="profileheader" />
				</tiles:insert> <%--header info end--%></td>
			</tr>
	</table>
	<%-- end header info --%>



	<%-- begin the tabs and data --%>
<div class=spacer></div>
	<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td><%-- begin green tabs (1st row) --%>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top"><%--tabs start--%> <tiles:insert
								page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
								<tiles:put name="tabid" value="family" />
								<tiles:put name="juvnum"
									value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
							</tiles:insert> <%--tabs end--%></td>
						</tr>
						<tr>
							<%-- ### change the next line, if required, to the proper path --%>
							<td bgcolor="#33cc66"><img src="../../images/spacer.gif"
								width="5"></td>
						</tr>
				</table>


				<%-- end green tabs --%> <%-- begin outer green border --%>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
						<tr>
							<td valign="top" align="center">
							<%-- begin red tabs (3rd row) --%>
							<div class=spacer></div>
										<table width="98%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top"><%--tabs start--%> <tiles:insert
														page="../caseworkCommon/memberInfoTabs.jsp" flush="true">
														<tiles:put name="tabid" value="ContactInfo" />
														<tiles:put name="juvnum"
															value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
													</tiles:insert> <%--tabs end--%></td>
												</tr>
												<tr>
													<%-- ### change the next line, if required, to the proper path --%>
													<td bgcolor="#6699FF"><img src="../../images/spacer.gif" width="5"></td>
												</tr>
										</table>
										<%-- end red tabs --%> 
										
										<%--begin red outer border --%>
										<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
												<tr>
													<td valign="top" align="center">
													<%-- BEGIN Previous Contact TABLE --%>
													
		      						  				<logic:notEmpty name="juvenileMemberForm" property="contactList">
														<logic:iterate id="contacts" name="juvenileMemberForm" property="contactList">
																<logic:notEmpty name="contacts" property="memberContactId">
																<logic:notPresent name="ExistingRecords">
																	 <bean:define id="ExistingRecords" value="1" type="java.lang.String"/>
																 </logic:notPresent>
																</logic:notEmpty>
																<logic:empty name="contacts" property="memberContactId">
																    <logic:notPresent name="NewRecords">
																		 <bean:define id="NewRecords" value="1" type="java.lang.String"/>
																	 </logic:notPresent>
																</logic:empty>
															</logic:iterate>
		              								</logic:notEmpty>

												<logic:notPresent name="ExistingRecords">
												<div class=spacer></div>
												<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
									  			<tr>
									  				<td valign=top class=detailHead>
									  					<table width='100%' cellpadding=0 cellspacing=0>
									  						<tr>
									  							<td class=detailHead>&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.contact"/> <bean:message key="prompt.info"/> - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
									  						</tr>
									  					</table>
									  				</td>
									  			</tr>
									  			<tr>
									  				<td valign=top class=normalRow>
									  					No Contact Information Available
									  				</td>
									  			</tr>
									  			</table>
									  			<div class=spacer></div>
									  			</logic:notPresent>


												<logic:present name="ExistingRecords">
												<div class=spacer></div>
											  		<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
											  			<tr>
											  				<td valign=top class=detailHead>
											  					<table width='100%' cellpadding=0 cellspacing=0>
											  						<tr>
											  							<td width='1%'><a href="javascript:showHideMulti('Characteristics', 'pChar', 1,'/<msp:webapp/>')" border=0><img border=0 src="../../images/expand.gif" name="Characteristics"></a></td>
											  							<td class=detailHead>&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.contact"/> <bean:message key="prompt.info"/> - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
											  						</tr>
											  					</table>
											  				</td>
											  			</tr>
											  			<tr id="pChar0" class=hidden>
											  				<td valign=top>
																	<table width="100%" bgcolor="#cccccc" cellspacing="1">
																			<tr bgcolor="#f0f0f0">
																				<td class="subhead" valign="top" width="5%">OID</td>
																				<td class="subhead" valign="top" width="10%"><bean:message key="prompt.entryDate"/></td>
																				<td class="subhead"><bean:message key="prompt.phoneOrEmail"/> <bean:message key="prompt.type"/></td>
																				<td class="subhead"><bean:message key="prompt.phone"/>/<bean:message key="prompt.email"/></td>
																				<td class="subhead">Primary</td>
																				<td class="subhead">Unknown</td>
																			</tr>
																		<%int RecordCounter = 0;
																		String bgcolor = "";%>  
																		<logic:iterate id="contacts" name="juvenileMemberForm" property="contactList">
																		<logic:notEmpty name="contacts" property="memberContactId">
																		 <tr class=<%RecordCounter++;bgcolor = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";out.print(bgcolor);%> height="100%">
																		 	<td>
																				<bean:write name="contacts" property="memberContactId" />
																			</td> 
																			<td>
																				<bean:write name="contacts" property="entryDate" formatKey="date.format.mmddyyyy"/>
																			</td>
																			<logic:equal name="contacts" property="phone" value="true">
																				<td><bean:write name="contacts" property="contactType"/></td>
																				<td><bean:write name="contacts" property="contactPhoneNumber.formattedPhoneNumber"/> <logic:notEqual name="contacts" property="contactPhoneNumber.ext" value="">Ext:<bean:write name="contacts" property="contactPhoneNumber.ext"/></logic:notEqual></td>
																				<logic:equal name="contacts" property="primaryInd" value="Primary">
																					<td>Primary</td>
																				</logic:equal>
																				<logic:notEqual name="contacts" property="primaryInd" value="Primary">
																					<td></td>
																				</logic:notEqual>
																				<logic:equal name="contacts" property="contactPhoneNumber.formattedPhoneNumber" value="000-000-0000">
																					<td>Yes</td>
																				</logic:equal>
																				<logic:notEqual name="contacts" property="contactPhoneNumber.formattedPhoneNumber" value="000-000-0000">
																					<td></td>
																				</logic:notEqual>
																			</logic:equal>
																			<logic:notEqual name="contacts" property="phone" value="true">
																				<td><bean:write name="contacts" property="emailContactType"/></td>
																				<td><bean:write name="contacts" property="emailAddress"/></td>
																				<logic:equal name="contacts" property="primaryIndEmail" value="Primary">
																					<td>Primary</td>
																				</logic:equal>
																				<logic:notEqual name="contacts" property="primaryIndEmail" value="Primary">
																					<td></td>
																				</logic:notEqual>
																				<td></td>
																			</logic:notEqual>
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
									   				<br>
									   		</logic:present>
        						  			<%-- END Previous Contact TABLE --%>
													<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
				
													<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
															<tr>
																<td valign="top" class="detailHead"><bean:message key="prompt.new"/> <bean:message key="prompt.family"/> <bean:message key="prompt.member"/>
																<bean:message key="prompt.contact"/> <bean:message key="prompt.info"/>  - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
															</tr>
															<tr>
																<td><%--	BEGIN FAMILY MEMBER INNER TABLE --%>
																<table border="0" cellpadding="2" cellspacing="1" width="100%">
																	<tr>
											                			<td class="formDeLabel" nowrap width="1%"><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border=0 width=10 height=9><bean:message key="prompt.phoneOrEmail"/> <bean:message key="prompt.type"/></td>
													                 	<td class=formDe>
														                 	<select id="contactTypeMain">
														                  		<option value="" selected>Select Type</option>
														                		<option value="EMAIL">EMAIL</option>
														               			<option value="PHONE">PHONE</option>
	        																</select>
													               		</td>
												               		</tr>
																	
																		<tr id="phone1" class="hidden">
																			<td class="formDeLabel">
																			  <img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9">Phone <bean:message key="prompt.type"/></td>
																			<td class="formDe">
																				<html:select name="juvenileMemberForm" property="currentContact.contactTypeId">
																				<option value="">Select One</option>
																				<html:optionsCollection name="juvenileMemberForm" property="contactTypeList" value="code" label="description" />
																			    </html:select>
																			</td>
                                       								    </tr>
                                        								<tr id="phone2" class="hidden">
																			<td class="formDeLabel" nowrap><img
																				src="/<msp:webapp/>images/required.gif"
																				title="Required" alt="required icon" border="0"
																				width="10" height="9"><bean:message key="prompt.phone"/></td>
																			<td class="formDe"><html:text
																				name="juvenileMemberForm"
																				property="currentContact.contactPhoneNumber.areaCode"
																				size="3" maxlength="3" styleId="areaCode" onkeyup="return autoTab(this, 3);" /> - <html:text
																				name="juvenileMemberForm"
																				property="currentContact.contactPhoneNumber.prefix"
																				size="3" maxlength="3" styleId="prefix" onkeyup="return autoTab(this, 3);" /> - <html:text
																				name="juvenileMemberForm"
																				property="currentContact.contactPhoneNumber.last4Digit"
																				size="4"  maxlength="4" styleId="last4Digit" onkeyup="return autoTab(this, 4);" /> Ext.
																			<html:text name="juvenileMemberForm"
																				property="currentContact.contactPhoneNumber.ext"
																				size="6" maxlength="6" styleId="ext" onkeyup="return autoTab(this, 6);">
																			</html:text>
																			<html:checkbox name="juvenileMemberForm" property="primaryInd" value="YES"/>Primary&nbsp;&nbsp;&nbsp;
																			<input type="checkbox" name="unknownPhone"/>Unknown
																		</td>
																		</tr>

																		<tr id="email1" class="hidden">
																			<td class="formDeLabel"><img
																				src="/<msp:webapp/>images/required.gif"
																				title="Required" alt="required icon" border="0"
																				width="10" height="9"><bean:message key="prompt.email"/> <bean:message key="prompt.type"/></td>
																			<td class="formDe"><html:select
																				name="juvenileMemberForm"
																				property="currentContact.emailContactTypeId">
																				<option value="">Select One</option>
																				<jims:codetable codeTableName="<%=PDCodeTableConstants.EMAIL_TYPE%>"/> 
																			    </html:select></td>
                                     									 </tr>
                                     									 <tr id="email2" class="hidden">
																			<td class="formDeLabel" nowrap><img
																				src="/<msp:webapp/>images/required.gif"
																				title="Required" alt="required icon" border="0"
																				width="10" height="9"><bean:message key="prompt.email"/> <bean:message key="prompt.address"/></td>
																			<td class="formDe"><html:text
																				name="juvenileMemberForm"
																				property="currentContact.emailAddress"
																				size="50" maxlength="50"/> 
																			    </html:text>
																			    <html:checkbox name="juvenileMemberForm" property="primaryIndEmail" value="YES"/>Primary&nbsp;&nbsp;&nbsp;
																		    </td>
																		</tr>
																		
																		<tr>
																			<td colspan="4">
																			<div class="paddedFourPix" align="center">
																				</html:submit> <html:submit property="submitAction" styleId="addContactValidate">
																						<bean:message key="button.addToList"/>
																					</html:submit>
																				
																				</div>
																			</td>
																		</tr>
																</table>
																</td>
															</tr>

															<logic:present name="NewRecords" >
															<tr>
																<td>
																 <div class=spacer></div>
  																<table width="100%" bgcolor="#cccccc" cellspacing="1">
																		<tr bgcolor="#f0f0f0">
																			<td width="10%">&nbsp;</td>
																			<td class="subhead"><bean:message key="prompt.phoneOrEmail"/> <bean:message key="prompt.type"/></td>
																			<td class="subhead"><bean:message key="prompt.phone"/>/<bean:message key="prompt.email"/></td>
																			<td class="subhead">Primary</td>
																			<td class="subhead">Unknown</td>
																		</tr>
																		 <%int RecordCounter = 0;
																		String bgcolor = "";%>  
																	<logic:iterate id="contacts" name="juvenileMemberForm" property="contactList">
																	<logic:empty name="contacts" property="memberContactId">
																	 <tr class=<%RecordCounter++; bgcolor = "alternateRow"; if (RecordCounter % 2 == 1) bgcolor = "normalRow"; out.print(bgcolor);%> height="100%"> 
																			<td>
																				<a href="/<msp:webapp/>submitManageFamilyMemberContactAction.do?submitAction=Remove&selectedValue=<%=(RecordCounter-1)%>"> Remove</a>
																			</td>
																				<logic:equal name="contacts" property="phone" value="true">
																					<td><bean:write name="contacts" property="contactType"/></td>
																					<td><bean:write name="contacts" property="contactPhoneNumber.formattedPhoneNumber"/> <logic:notEqual name="contacts" property="contactPhoneNumber.ext" value="">Ext:<bean:write name="contacts" property="contactPhoneNumber.ext"/></logic:notEqual></td>
																					<logic:equal name="contacts" property="primaryInd" value="YES">
																						<td>Yes</td>
																					</logic:equal>
																					<logic:notEqual name="contacts" property="primaryInd" value="YES">
																						<td></td>
																					</logic:notEqual>
																					<logic:equal name="contacts" property="contactPhoneNumber.formattedPhoneNumber" value="000-000-0000">
																						<td>Yes</td>
																					</logic:equal>
																					<logic:notEqual name="contacts" property="contactPhoneNumber.formattedPhoneNumber" value="000-000-0000">
																						<td></td>
																					</logic:notEqual>
																				</logic:equal>
																				<logic:notEqual name="contacts" property="phone" value="true">
																					<td><bean:write name="contacts" property="emailContactType"/></td>
																					<td><bean:write name="contacts" property="emailAddress"/></td>
																					<logic:equal name="contacts" property="primaryIndEmail" value="YES">
																						<td>Yes</td>
																					</logic:equal>
																					<logic:notEqual name="contacts" property="primaryIndEmail" value="YES">
																						<td></td>
																					</logic:notEqual>
																				</logic:notEqual>
																		</tr>
																		</logic:empty>
																	</logic:iterate>
																</table>
															  </td>
															</tr>
														</logic:present>
													</table>
													</logic:notEqual>


														<%-- ### begin button table --%>
													<table border="0" width="100%">
															<tr>
																<td align="center"><html:submit property="submitAction">
																	<bean:message key="button.back"></bean:message>
																</html:submit> 
																<logic:notEqual name="juvenileMemberForm" property="action" value=" ">
																	<logic:notEqual name="juvenileMemberForm" property="action" value="update">																	
																		<logic:notEqual name="juvenileMemberForm" property="action" value="createMemberConfirmation">
																			<input type="button" value="Update Member Contact" id="updateMemberContact">
																		</logic:notEqual>
																	</logic:notEqual>
																</logic:notEqual>
																<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
																<logic:present name="NewRecords" >
																<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_CI_U%>'>	
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
													<div class=spacer></div>
													<%-- end button table --%>
													</td>
												</tr>
										</table>
									<div class=spacer></div>
										<%-- end outer red --%>
										</td>
						</tr>
				</table>
				<%-- end outer green --%>
				</td>
			</tr>
	</table>
	<%-- ************************************************************ --%>


</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

