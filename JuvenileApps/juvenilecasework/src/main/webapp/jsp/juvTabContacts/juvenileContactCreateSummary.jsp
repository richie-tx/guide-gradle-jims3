<!DOCTYPE HTML>
<%-- User selects the "Contact #" hyperlink on one of the row --%>
<%--MODIFICATIONS --%>
<%-- 06/09/2005	Hien Rodriguez	Create JSP --%>
<%-- 12/13/2006 Hien Rodriguez  ER#37077 Add BackToTop link  --%>
<%-- 06/29/2007 Leslie Deen		Defect #42874 - remove Contact # as this is not needed and we won't have it until confirm screen --%>
<%-- 09/11/2007	CShimek			#45082 revised address display to single detail line --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" /> 
<html:base />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - juvenileContactViewDetails.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</script><!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/contacts.js"></script>

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<html:form action="/submitJuvenileContactCreate" target="content">


<%-- BEGIN HEADING TABLE --%>
<table width="98%">
 	<tr>
   	<td align="center" class="header">
			<logic:equal name="juvenileContactForm" property="secondaryAction" value="summary">			
   			<bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Contact Summary  				
			</logic:equal>
		
			<logic:equal name="juvenileContactForm" property="secondaryAction" value="confirm">			
   			<bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Contact Confirmation   				

				<logic:equal name="juvenileContactForm" property="action" value="create">	
  				<tr align="center"><td class="confirm">Contact successfully added.</td></tr>
				</logic:equal>

				<logic:notEqual name="juvenileContactForm" property="action" value="create">	
  				<tr align="center"><td class="confirm">Contact successfully updated.</td></tr>
				</logic:notEqual>
			</logic:equal>
		</td>
	</tr>  	
</table>
<%-- END HEADING TABLE --%>

<%--juv profile header start--%>
<br />
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>


<logic:equal name="juvenileContactForm" property="secondaryAction" value="summary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|225">
</logic:equal>
<logic:equal name="juvenileContactForm" property="secondaryAction" value="confirm">    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|226">
</logic:equal>    

<%-- BEGIN DETAIL TABLE --%>  
<div class=spacer></div> 
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
 	<tr>
   	<td valign=top>
   		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="contactstab"/>
							<tiles:put name="juvNumId" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
					</td>
				</tr>
				<tr>
			 		<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
			 	</tr>
			</table>
			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign=top align=center>	
					  <div class=spacer></div>
						<table cellpadding=4 cellspacing=1 border=0 width='98%' class="borderTableBlue">
            	<%-- BEGIN CONTACT DETAILS SECTION --%>
							<tr>
								<td class=detailHead colspan="4"><bean:message key="prompt.contactDetails" /></td>
							</tr>
							<tr>
								<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.entryDate" /></td>
								<td class=formDe colspan="3"><bean:write name="juvenileContactForm" property="entryDate" /></td>
							</tr>
							<tr>
								<td class=formDeLabel><bean:message key="prompt.name" /></td>
								<td class=formDe colspan="3"><bean:write name="juvenileContactForm" property="contactName.formattedName" /></td>			
							</tr>
							<tr>	
								<td class=formDeLabel>Is Age Over 21</td>
								<td class=formDe colspan="3">
									<logic:equal name="juvenileContactForm" property="ageOver21" value="true">Yes</logic:equal>
									<logic:notEqual name="juvenileContactForm" property="ageOver21" value="true">No</logic:notEqual>
								</td>
							</tr>
							<tr>	
								<td class=formDeLabel>Detention Visit</td>
								<td class=formDe colspan="3">
									<logic:equal name="juvenileContactForm" property="detentionVisit" value="true">Yes</logic:equal>
									<logic:notEqual name="juvenileContactForm" property="detentionVisit" value="true">No</logic:notEqual>
								</td>
							</tr>
							<tr>	
								<td class=formDeLabel><bean:message key="prompt.relationship" /></td>
								<td class=formDe colspan="3"><bean:write name="juvenileContactForm" property="relationship" /></td>
							</tr>
							<tr>	
								<td class=formDeLabel><bean:message key="prompt.email" /></td>
								<td class=formDe colspan="3"><bean:write name="juvenileContactForm" property="EMail" /></td>
							</tr>
							<tr>
								<td class=formDeLabel>Mobile/Pager <bean:message key="prompt.phone" /></td>
								<td class=formDe colspan="3">
    							<bean:write name="juvenileContactForm" property="cellPhone" /> 
    							<logic:notEmpty name="juvenileContactForm" property="cellPhone">
    						    <logic:notEqual name="juvenileContactForm" property="cellPhone" value="">
    						      <logic:equal name="juvenileContactForm" property="phonePriorityInd" value="C">Primary</logic:equal>
    						    </logic:notEqual>
    						  </logic:notEmpty>
								</td>
						  </tr>
						  <tr>
						    <td class=formDeLabel>Work <bean:message key="prompt.phone" /></td>
								<td class=formDe colspan="3">
    						  <bean:write name="juvenileContactForm" property="workPhone" />
									&nbsp;<strong>Ext</strong>&nbsp;<bean:write name="juvenileContactForm" property="workPhone.extension" /> 
    						  <logic:notEmpty name="juvenileContactForm" property="workPhone">
    						    <logic:notEqual name="juvenileContactForm" property="workPhone" value="">
    						      <logic:equal name="juvenileContactForm" property="phonePriorityInd" value="W">Primary</logic:equal>
    						    </logic:notEqual>
    						  </logic:notEmpty>
								</td>
						  </tr>
						  <tr>
							  <td class=formDeLabel>Work Fax <bean:message key="prompt.phone" /></td>
							  <td class=formDe colspan="3">
    							<bean:write name="juvenileContactForm" property="fax" /> 
   							  <logic:notEmpty name="juvenileContactForm" property="fax">
    								<logic:notEqual name="juvenileContactForm" property="fax" value="">
    								  <logic:equal name="juvenileContactForm" property="phonePriorityInd" value="F">Primary</logic:equal>
    								</logic:notEqual>
    							</logic:notEmpty>
								</td>
							</tr>
            	<%-- END CONTACT DETAILS SECTION --%>
							
            	<%-- BEGIN AGENCY INFORMATION SECTION --%>						
							<tr>	
								<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.currentAgencyInvolvement" /></td>
								<td class=formDe colspan="3"><bean:write name="juvenileContactForm" property="currentAgencyInvolvement" /></td>			
							</tr>
							<tr>
								<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.previousAgencyInvolvement" /></td>
								<td class=formDe colspan="3"><bean:write name="juvenileContactForm" property="previousAgencyInvolvement" /></td>			
							</tr>
							
            	<%-- END AGENCY INFORMATION SECTION --%>
            	<%-- BEGIN DRIVER LICENSE/ID INFORMAITON TABLE --%>
            	
            	<tr>
					<td class="detailHead" colspan="4">Driver License/ID Card Information</td>
				</tr>
				<tr>
					<td class="formDeLabel" width="20%"><bean:message key="prompt.number"/></td>
					<td class="formDe" width="20%"><bean:write name="juvenileContactForm" property="driverLicenseNum"/></td>
					<td class="formDeLabel" width="20%"><bean:message key="prompt.state"/></td>
					<td class="formDe" width="20%"><bean:write name="juvenileContactForm" property="driverLicenseState"/></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.expirationDate"/></td>
					<td class="formDe"><bean:write name="juvenileContactForm" property="driverLicenseExpirationDate"/></td>
					<td class="formDeLabel"><bean:message key="prompt.class"/></td>
					<td class="formDe"><bean:write name="juvenileContactForm" property="driverLicenseClass"/></td>
				</tr>
				<tr>
					<td class="formDeLabel">
						<bean:message key="prompt.state" />
						<bean:message key="prompt.issued" />
						<bean:message key="prompt.idCard#" />
					</td>
						<td class="formDe"><bean:write name="juvenileContactForm" property="stateIssuedIdNum"/></td>
						<td class="formDeLabel"><bean:message key="prompt.state"/></td>
						<td class="formDe"><bean:write name="juvenileContactForm" property="issuedByState"/></td>
				</tr>
				<tr>
						<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.passportNumber" /></td>
						<td class="formDe"><bean:write name="juvenileContactForm" property="passportNum"/></td>
						<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.countryOfIssuance"/></td>
						<td class="formDe"><bean:write name="juvenileContactForm" property="countryOfIssuance"/></td>
				</tr>
				<tr>
						<td class="formDeLabel"><bean:message key="prompt.expirationDate"/></td>
						<td class="formDe" colspan="3"><bean:write name="juvenileContactForm" property="passportExpirationDate"/></td>
				</tr>
										
				<%-- END DRIVER LICENSE/ID INFORMAITON TABLE --%>	
							<tr>
								<td class='formDeLabel' width='1%' colspan='4'>
									Contact Comments
								</td>
							</tr>
							<tr>
								<td class='formDe' width='1%' colspan='4'>
									<bean:write name="juvenileContactForm" property="contactMemberComments" />
								</td>
							</tr>			
            	<%-- BEGIN ADDRESS INFORMATION SECTION --%>	
							<tr>
								<td class=detailHead colspan="4"><bean:message key="prompt.addressInfo" /></td>
							</tr>
							<tr>
								<td colspan=4>
								<table width="100%" cellspacing="1">
										<tr>
											<td class="formDeLabel" valign="top"><bean:message key="prompt.addressType"/></td>
											<td class="formDeLabel" valign="top"><bean:message key="prompt.address"/></td>
											<td class="formDeLabel" valign="top"><bean:message key="prompt.validationSymbol"/></td>
											<td class="formDeLabel" valign="top"><bean:message key="prompt.county"/></td>
										</tr>
										<tr class="normalRow"> 
											<td valign="top"><bean:write name="juvenileContactForm" property="addressType"/></td>
											<td><bean:write name="juvenileContactForm" property="address"/></td>
<%--											
											<td><bean:write name="juvenileContactForm" property="streetNum"/>&nbsp;
												<bean:write name="juvenileContactForm" property="streetName"/>&nbsp; 
												<bean:write name="juvenileContactForm" property="streetType"/>&nbsp; 
												<bean:write name="juvenileContactForm" property="apartmentNum"/>&nbsp; 
												<bean:write name="juvenileContactForm" property="city"/>&nbsp;
												<bean:write name="juvenileContactForm" property="state"/> &nbsp;
												<bean:write name="juvenileContactForm" property="zipCode"/>
												<logic:notEmpty name="juvenileContactForm" property="additionalZipCode">
												  -<bean:write name="juvenileContactForm" property="additionalZipCode"/>
												</logic:notEmpty>
											</td--%>
											<td>
	  											<%-- based on the Address validation, display a specific icon --%>
  												<logic:notEmpty name="juvenileContactForm" property="validated">
														<logic:equal name="juvenileContactForm" property="validated" value="Y">
	  														<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
														</logic:equal>
														<logic:equal name="juvenileContactForm" property="validated" value="N">
  															<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
														</logic:equal>
 												</logic:notEmpty>
											</td>
											<td valign="top"><bean:write name="juvenileContactForm" property="county"/></td>
										</tr>
								</table>
<%-- 
									<table width="100%" border="0" cellpadding=2 cellspacing=1>
										<tr bgcolor="#f0f0f0">
											<td class="subhead" valign="top"><bean:message key="prompt.addressType"/></td>
											<td class="subhead" valign="top"><bean:message key="prompt.address"/></td>
											<td class="subhead" valign="top"><bean:message key="prompt.county"/></td>
										</tr>
										<tr>	
											<td valign="top"><bean:write name="juvenileContactForm" property="addressType"/></td>
											<td><bean:write name="juvenileContactForm" property="streetNum" />&nbsp;
												<logic:empty name="juvenileContactForm" property="streetType">
													<logic:empty name="juvenileContactForm" property="apartmentNum">
														<bean:write name="juvenileContactForm" property="streetName" />,&nbsp; 
													</logic:empty>														
												</logic:empty>	
												<logic:notEmpty name="juvenileContactForm" property="streetType">
													<logic:empty name="juvenileContactForm" property="apartmentNum">
														<bean:write name="juvenileContactForm" property="streetName" />&nbsp; 
														<bean:write name="juvenileContactForm" property="streetType" />,&nbsp;
													</logic:empty>	
													<logic:notEmpty name="juvenileContactForm" property="apartmentNum">												
														<bean:write name="juvenileContactForm" property="streetName" />&nbsp; 
														<bean:write name="juvenileContactForm" property="streetType" />&nbsp;											
														#<bean:write name="juvenileContactForm" property="apartmentNum" />,&nbsp;
													</logic:notEmpty>
												</logic:notEmpty>
												<logic:notEmpty name="juvenileContactForm" property="apartmentNum">
													<logic:empty name="juvenileContactForm" property="streetType">
														<bean:write name="juvenileContactForm" property="streetName" />&nbsp; 												
														#<bean:write name="juvenileContactForm" property="apartmentNum" />,&nbsp;
													</logic:empty>
												</logic:notEmpty>
												
												<bean:write name="juvenileContactForm" property="city" />&nbsp;
												<bean:write name="juvenileContactForm" property="state" /> &nbsp;
												<bean:write name="juvenileContactForm" property="zipCode" />
												<logic:notEmpty name="juvenileContactForm" property="additionalZipCode">
													-<bean:write name="juvenileContactForm" property="additionalZipCode" />
												</logic:notEmpty>
											</td>	
											<td valign="top"><bean:write name="juvenileContactForm" property="county" /></td>											
										</tr>
									</table>
--%>
          			</td>
							</tr>
						</table>
           	<%-- END ADDRESS INFORMATION SECTION --%>

						<%-- BEGIN BUTTON TABLE --%>
						<div class=spacer></div>
						<div align="center">
						<table>
					  	<tr>
					    	<td>   		 		
									<logic:equal name="juvenileContactForm" property="secondaryAction" value="summary">
										<html:button property="button.back" onclick="history.back();">
											<bean:message key="button.back"></bean:message>
										</html:button>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
											<bean:message key="button.finish"></bean:message>
										</html:submit>
									</logic:equal>	
							    </td>
 </html:form>
					<logic:equal name="juvenileContactForm" property="secondaryAction" value="summary">
						<html:form action="/displayJuvenileContactList" target="content">
						    	<td>   		 		
										<html:submit property="submitAction">
											<bean:message key="button.cancel"></bean:message>
										</html:submit>			
							    </td>
						</html:form>
									</logic:equal>	
						
									<logic:equal name="juvenileContactForm" property="secondaryAction" value="confirm">
									<html:form action="/displayJuvenileContactList" target="content">
						    	<td>   		 		
										<html:submit property="submitAction" >
											<bean:message key="button.backToList"></bean:message>
										</html:submit>
							    </td>
									</html:form>
									</logic:equal>					    	    	
						  	</tr>
						</table>
						<%-- END BUTTON TABLE --%>
						<div class=spacer></div>
					</td>
				</tr>
			</table>
   		</td>
  	</tr>
</table>
<%-- END DETAIL TABLE --%>

<br>


<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
