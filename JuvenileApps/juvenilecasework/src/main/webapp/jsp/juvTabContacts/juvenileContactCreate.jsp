<!DOCTYPE HTML>
<%-- User selects the "Add More Contacts" button --%>
<%--MODIFICATIONS --%>
<%-- 06/09/2005	Hien Rodriguez	Create JSP --%>
<%-- 09/22/2006	C Shimek   	Defect#32539 modified address "Validate" button to be functional  --%>
<%-- 12/13/2006 Hien Rodriguez  ER#37077 Remove casefileOperations box & add BackToTop link  --%>
<%-- 07/09/2007 LDeen		Add link to address.js file  --%>
<%-- 08/07/2009 DWilliamson Added validation for phones - Defect #61368 --%>
<%-- 11/08/2012 DGibler JIMS200073746-MJCW: Add Street Number suffix field (PD)-DAG --%>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.UIConstants" %>




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

<%-- <logic:equal name="juvenileContactForm" property="action" value="create">  
<html:javascript formName="juvenileContactForm"/>
</logic:equal>
<logic:notEqual name="juvenileContactForm" property="action" value="create">  
<html:javascript formName="juvenileContactForm"/>
</logic:notEqual> --%>

<html:javascript formName="juvenileContactForm"/>

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - juvenileContactsCreate.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
</script><!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/contacts.js"></script>

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0">

<html:form action="/displayJuvenileContactCreateSummary" target="content"  >


<%-- BEGIN HEADING TABLE --%>
<table width="98%">
 	<tr>
   	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - 
     	<logic:equal name="juvenileContactForm" property="action" value="create"> Create <html:hidden name="juvenileContactForm" property="action" styleId="contactCreateStatus" value="create"/></logic:equal>
  
   		<logic:notEqual name="juvenileContactForm" property="action" value="create"> Update <html:hidden name="juvenileContactForm" property="action" styleId="contactCreateStatus" value="update"/></logic:notEqual>
   	Contact
   	</td>
	</tr>  	
</table>
<%-- END HEADING TABLE --%>
<table width="22%" align="center">
	<tr> 
        <td align="center" class="errorAlert" ><html:errors /></td>
    </tr>
</table>
<%-- BEGIN INSTRUCTION TABLE --%>
<br>
<table width="98%" border="0">
 	<tr>
   	<td>
		  <ul>
       	<li>Click Next button to Continue</li>
 	    </ul>
   	</td>    
  </tr>
  <tr>     	
  	<td class="required"><bean:message key="prompt.diamond" /> &nbsp; Required Fields</td>
 	</tr>
 	<tr>
 	   <td class="required"><bean:message key="prompt.diamond" /><bean:message key="prompt.diamond" />&nbsp; Required for Validate Address</td>
 	</tr>   
</table>
<%-- END INSTRUCTION TABLE --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|224">

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>   
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
  	<td valign='top'>
  		<table width='98%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
        	<td valign='top'>
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

			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign='top' align='center'>	

          	<%-- BEGIN CONTACT DETAILS SECTION --%>
						<div class='spacer'></div> 
						<table cellpadding='4' cellspacing='1' border='0' width='98%' class="borderTableBlue">
							<tr>
								<td class='detailHead' colspan="4"><bean:message key="prompt.contactDetails" /></td>
							</tr>
							<tr id="fnreq">
								<logic:equal name="juvenileContactForm" property="action" value="create"> 			
									<td class='formDeLabel' valign='top'><bean:message key="prompt.name" /></td>
									<td class='formDe' colspan="3">	
										<table border="0" cellpadding='2' cellspacing='1'>
											<tr class='formDeLabel'>
												<td><bean:message key="prompt.title" /></td>
												<td><bean:message key="prompt.diamond" /><bean:message key="prompt.last" /></td>
											</tr>
												 
											<tr class='formDe'>
												<td>
												  <html:select property="titleId">
	  												<html:option key="select.generic" value="" />
	  												<html:optionsCollection property="prefixes" value="code" label="description"/>				
	  											</html:select>
												</td>
	  											<td><html:text size="35" maxlength="75"  styleId="juvLastName" property="contactName.lastName"/></td>
											</tr>
											<tr class='formDeLabel'>	
												<td><bean:message key="prompt.diamond" /><bean:message key="prompt.first" /></td>
												<td><bean:message key="prompt.middle" /></td>
											</tr>
											
											<tr class='formDe'>								
												<td><html:text size="35" maxlength="50" property="contactName.firstName" /></td>
												<td><html:text size="35" maxlength="50" property="contactName.middleName" /></td>
											</tr>
											
										</table>
									</td>										
								</logic:equal>

								
								<logic:notEqual name="juvenileContactForm" property="action" value="create">  
									<html:hidden  property="contactName.firstName" />
									<html:hidden  property="contactName.lastName" />
									<td class='formDeLabel' valign='top'><bean:message key="prompt.name" /></td>
									<td colspan="3" class='formDe'><bean:write name="juvenileContactForm" property="contactName.formattedName" /></td>
								</logic:notEqual>						
	  						</tr>
	  						<tr  id = "ageOver21" styleId = "ageOver21">									
									<td class='formDeLabel'>Age Over 21?</td>
									<td colspan="3" class='formDe'>
										<html:radio name="juvenileContactForm" property="ageOver21" value="true" styleId="Over21Yes"/>Yes
										<html:radio name="juvenileContactForm" property="ageOver21" value="false" styleId="Over21No"/>No
									</td>
							</tr>
							<tr class='hidden' id="detentionVisit" styleId = "detentionVisit">
							<html:hidden name ="juvenileContactForm" property="detVisitContactsCount" styleId="detVisitContactsCount"/>
							<html:hidden name ="juvenileContactForm" property="daVisit" styleId="daVisit"/>
							<html:hidden name ="juvenileContactForm" property="visitorCapRemoved" styleId="visitorCapRemoved"/>
									<td class='formDeLabel'>Detention Visit</td>
									<td colspan="3" class='formDe'>
										<html:radio name="juvenileContactForm" property="detentionVisit" value="true" styleId="detVisitYes"/>Yes
										<html:radio name="juvenileContactForm" property="detentionVisit" value="false" styleId="detVisitNo"/>No
									</td>
									
							</tr>
	  						<tr id="fnreqPlacement" class="hidden">
								<logic:equal name="juvenileContactForm" property="action" value="create"> 			
									<td class='formDeLabel' valign='top'><bean:message key="prompt.diamond" /><bean:message key="prompt.facility" /> <bean:message key="prompt.name" /></td>
									<td colspan="3" class='formDe'><input type="text" size="50" maxlength="75" id="txtFacilityName" name="facilityName"/></td>										
								</logic:equal>
								<logic:notEqual name="juvenileContactForm" property="action" value="create">  
									<td class='formDeLabel' valign='top'><bean:message key="prompt.name" /></td>
									<td colspan="3" class='formDe'><bean:write name="juvenileContactForm" property="contactName.formattedName" /></td>
								</logic:notEqual>		
	  						</tr>
							<tr>	
								<td class='formDeLabel'><bean:message key="prompt.diamond" /><bean:message key="prompt.relationship" /></td>
								<td colspan="3" class='formDe'>
								 	<html:select property="relationshipId" styleId="relationshipId" >
  										<html:option key="select.generic" value="" />
	  									<html:optionsCollection property="relationships" value="code" label="description"/>				
  									</html:select>
								</td>
							</tr>
							<tr>									
								<td class='formDeLabel'><bean:message key="prompt.email" /></td>
								<td colspan="3" class='formDe'><html:text size="50" maxlength="100" property="EMail" /></td>	
							</tr>
							<tr>
								<td class='formDeLabel' valign='top'>Mobile/Pager <bean:message key="prompt.phone" /></td>
								<td class='formDe' width='1%' nowrap='nowrap'>
								  <html:text size="3" maxlength="3" property="cellPhone.areaCode" onkeyup="return autoTab(this, 3);" />
									- <html:text size="3" maxlength="3" property="cellPhone.prefix" onkeyup="return autoTab(this, 3);" />
									- <html:text size="4" maxlength="4" property="cellPhone.4Digit" />
								</td>
								<td colspan="2" class='formDe' width='1%'><html:radio property="phonePriorityInd" value="C"/>Primary</td>
							</tr>
							<tr>
								<td class='formDeLabel'>Work <bean:message key="prompt.phone" /></td>
								<td class='formDe' width='1%' nowrap='nowrap'>
								  <html:text size="3" maxlength="3" property="workPhone.areaCode" onkeyup="return autoTab(this, 3);" />
									- <html:text size="3" maxlength="3" property="workPhone.prefix" onkeyup="return autoTab(this, 3);" />
									- <html:text size="4" maxlength="4" property="workPhone.4Digit" />&nbsp;<b></b>
								  &nbsp;<strong>Ext</strong>&nbsp;<html:text size="6" maxlength="10" property="workExtension" />
								</td>
								<td colspan="2" class='formDe' width='1%'><html:radio property="phonePriorityInd" value="W"/>Primary</td>
							</tr>
							<tr>
								<td class='formDeLabel'>Work Fax <bean:message key="prompt.phone" /></td>
								<td class='formDe' width='1%' nowrap='nowrap'>
								  <html:text size="3" maxlength="3" property="fax.areaCode" onkeyup="return autoTab(this, 3);" />
									- <html:text size="3" maxlength="3" property="fax.prefix" onkeyup="return autoTab(this, 3);" />
									- <html:text size="4" maxlength="4" property="fax.4Digit" />
								</td>
								<td colspan="2" class='formDe' width='1%'><html:radio property="phonePriorityInd" value="F"/>Primary</td>
							</tr>
            	<%-- END CONTACT DETAILS SECTION --%>
            							
            	<%-- BEGIN AGENCY INFORMATION SECTION --%>						
							<tr>		
								<td class='formDeLabel' width='1%'><bean:message key="prompt.diamond" /><bean:message key="prompt.currentAgencyInvolvement" /></td>
								<td class='formDe' colspan='3'>
									<html:select property="currentAgencyInvolvementId">
  									<html:option key="select.generic" value="" />
  									<html:optionsCollection property="juvenileAgencies" value="code" label="description"/>				
  								</html:select>
								</td>			
							</tr>
							<tr>
								<td class='formDeLabel' width='1%'><bean:message key="prompt.previousAgencyInvolvement" /></td>
								<td class='formDe' colspan='3'>
									<html:select property="previousAgencyInvolvementId">
  									<html:option key="select.generic" value="" />
  									<html:optionsCollection property="juvenileAgencies" value="code" label="description"/>				
  								</html:select>
								</td>	
							</tr>		
          			 <%-- BEGIN DRIVER LICENSE/ID CARD INFORMATION --%>
					<tr class='detailHead' id = "driverLicenseIDInfo" styleId = "driverLicenseIDInfo">
  				
      					<td colspan="4" class='detailHead' colspan="1"><bean:message key="prompt.driverLicense"/>/<bean:message key="prompt.idCardInfo" /></td>
						</tr>
						<tr id = "driverLicenseIDInfo2" styleId = "driverLicenseIDInfo2">	
						<td class="formDeLabel"><bean:message key="prompt.number" /></td>
						<td class="formDe" ><html:text name="juvenileContactForm" property="driverLicenseNum" size="8" maxlength="8" styleId="dlNum" /></td>
						<td class="formDeLabel"><bean:message key="prompt.state" /></td>
						<td class="formDe">
							 <html:select name="juvenileContactForm" property="driverLicenseStateId" size="1" styleId="state1">
							 <option value="" selected='selected'>Please Select</option>
  							 <html:optionsCollection property="states" value="code" label="description"/>				
  							 </html:select>
						</td>
						</tr>
						
						 <tr id = "driverLicenseIDInfo3">	
						<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.expirationDate" /></td>
						<td class='formDe'>
							<html:text styleId="dlExperationDate" name="juvenileContactForm" property="driverLicenseExpirationDate" size="10" maxlength="10" />
						</td>
						<td class='formDeLabel' valign="top" nowrap='nowrap'><bean:message key="prompt.class" /></td>
						<td class='formDe'>
							 <html:select name="juvenileContactForm" property="driverLicenseClassId" styleId="dlClass">
							<html:option value=""><bean:message key="select.generic" /></html:option>
							<html:optionsCollection property="driversLicenseClass" value="code" label="description" />
							</html:select> 
						</td>
		 				</tr>
						<tr id = "driverLicenseIDInfo4">
							<td class="formDeLabel" nowrap='nowrap' width="1%"><bean:message key="prompt.state" />
                  					<bean:message key="prompt.issued" />
									<bean:message key="prompt.idCard#" />
							</td>
							<td class="formDe"><html:text name="juvenileContactForm" property="stateIssuedIdNum" size="20" maxlength="20" styleId="stateIdNum"/></td>
							<td class="formDeLabel"><bean:message key="prompt.state" /> </td>
							<td class="formDe">
							 <html:select name="juvenileContactForm" property="issuedByStateId" size="1" styleId="state2">
								<option value="" selected='selected'>Please Select</option>
  								<html:optionsCollection property="states" value="code" label="description"/>				
  							</html:select>
							</td>
						</tr>
			<!-- BEGIN Add passport details -->
						<tr id = "driverLicenseIDInfo5">
							<td class="formDeLabel" nowrap='nowrap' width="1%"><bean:message key="prompt.passportNumber" /></td>                             					
							<td class="formDe"><html:text name="juvenileContactForm" property="passportNum" size="20" maxlength="20" styleId="passportNum"/></td>
						    <td class="formDeLabel"><bean:message key="prompt.countryOfIssuance" /></td>		
							<td class="formDe" colspan="3">
								 <html:select size="1" name="juvenileContactForm" property="countryOfIssuanceId" styleId="passportCountry">
									<option value="">Please Select</option>
									<html:optionsCollection name="juvenileContactForm" property="countries" value="code" label="description" />
								</html:select>
							</td>
						</tr>
						<tr id = "driverLicenseIDInfo6">	
							<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.expirationDate" /></td>
							<td class='formDe' colspan="3">
							<html:text styleId="passportExpirationDate" name="juvenileContactForm" property="passportExpirationDate" size="10" maxlength="10" />
							</td>
						</tr>
			<!--end of passport details -->
           			
      	<%-- END DRIVER LICENSE/ID CARD INFORMATION --%>
      						<tr>
								<td class='formDeLabel' width='1%' colspan='5'>
									Contact Comments
									<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
  										<tiles:put name="tTextField" value="memberComments"/>
  										<tiles:put name="tSpellCount" value="spellBtn1" />
									</tiles:insert>
									(Max. characters allowed: 255)
								</td>
							</tr>
							<tr>
                      			<td class='formDe' colspan='5'>
                      				<html:textarea styleId="memberComments" 
                      								rows="4" style="width:100%" 
                      								name="juvenileContactForm" 
                      								property="contactMemberComments"
                      								onkeyup="textCounter(this,255);">
                      				</html:textarea>
                      			</td>
                    		</tr>				
            	<%-- BEGIN ADDRESS INFORMATION SECTION --%>	
							<tr class='detailHead'>
  							<td colspan="4">
    							<table width="100%">
    								<tr>
      								<td class='detailHead' colspan="1"><bean:message key="prompt.addressInfo" /></td>
      								<td class="detailHead" style="text-align:right"> 
      									<input type='button' value="<bean:message key='button.validate' />" name="submitAction" id="addressValidate" />
   						    	    <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="return displayResearchWindow();">
													<bean:message key="button.research"></bean:message>
												</html:button>	
       								</td>
      								<td class="detailHead" style="text-align:right" nowrap='nowrap'><bean:message key="prompt.addressStatus"/>:</td>
      							  <td class="errorAlert">
      									<%-- TODO replace logic tags with code table values --%>	 
      									<logic:equal name="juvenileContactForm" property="addressStatus" value="">UNPROCESSED</logic:equal>       	    
   						   	      <logic:equal name="juvenileContactForm" property="addressStatus" value="U">UNPROCESSED</logic:equal>
   						   	      <logic:equal name="juvenileContactForm" property="addressStatus" value="Y">VALID</logic:equal>
 						   	        <logic:equal name="juvenileContactForm" property="addressStatus" value="N">INVALID</logic:equal>
   					   	    	</td>		  
    								</tr>
    							</table>
  							</td>
							</tr>
							<tr>
								<td colspan='4'>
									<table width="100%" border="0" cellpadding='2' cellspacing='1'>
										<tr>
											<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.diamond" /><bean:message key="prompt.diamond" /><bean:message key="prompt.streetNum" /></td>
											<td class='formDeLabel' colspan="2"><bean:message key="prompt.streetNum" />&nbsp;<bean:message key="prompt.suffix" /></td>
										</tr>
										<tr>	
											<td class='formDe'><html:text size="9" maxlength="9" property="streetNum" /></td> <!--  bug fix #21331 change max length from 15-9-->
											<td class='formDe' colspan="2"><html:select property="streetNumSuffixId">
												<html:option key="select.generic" value="" />
												<html:optionsCollection property="streetNumSuffixes" value="code" label="description"/>				
											</html:select></td>
										</tr>
										<tr>
											<td class='formDeLabel' colspan="4"><bean:message key="prompt.diamond" /><bean:message key="prompt.diamond" /><bean:message key="prompt.streetName" /></td>
										</tr>
										
										<tr>
											<td class='formDe' colspan='3'><html:text size="30" maxlength="50" property="streetName" /></td>
										</tr>
										<tr>
											<td class='formDeLabel'><bean:message key="prompt.streetType" /></td>
											<td class='formDeLabel' colspan="2"><bean:message key="prompt.aptSuite" /></td>
										</tr>
										<tr>	
											<td class='formDe'><html:select property="streetTypeId">
												<html:option key="select.generic" value="" />
												<html:optionsCollection property="streetTypes" value="code" label="description"/>				
											</html:select></td>
											<td class='formDe' colspan='2'><html:text size="20" maxlength="20" property="apartmentNum" /></td>
										</tr>
										<tr valign='top'>
											<td class='formDeLabel'><bean:message key="prompt.diamond" /><bean:message key="prompt.diamond" /><bean:message key="prompt.city" /></td>
											<td class='formDeLabel'><bean:message key="prompt.diamond" /><bean:message key="prompt.diamond" /><bean:message key="prompt.state" /></td>
											<td class='formDeLabel'><bean:message key="prompt.diamond" /><bean:message key="prompt.diamond" /><bean:message key="prompt.zipCode" /></td>
										</tr>
										<tr>
											<td class='formDe'><html:text size="15" maxlength="25" property="city" /></td>
											<td class='formDe'>
											  <html:select property="stateId" size="1" styleId="state1">
											    <option value="" selected='selected'>Please Select</option>
  												<html:optionsCollection property="states" value="code" label="description"/>				
  											</html:select>
											</td>
											<td class='formDe'><html:text size="5" maxlength="5" property="zipCode" /> - <html:text size="4" maxlength="4" property="additionalZipCode" /></td>								
										</tr>
										<tr>
											<td class='formDeLabel'><bean:message key="prompt.addressType" /></td>
											<td class='formDeLabel' colspan="2"><bean:message key="prompt.county" /></td>
										</tr>
										<tr>	
											<td class='formDe'>
											  <html:select property="addressTypeId">
  												<html:option key="select.generic" value="" />
  												<html:optionsCollection property="addressTypes" value="code" label="description"/>				
  											</html:select>
											</td>								
											<td class='formDe' colspan='2'>
											  <span class="hidden" id="emptySelect1"> 
  											  <select disabled='disabled'>
    												<option>Please Select</option>
    											</select> 
												</span> 
												<span class="visible" id="county1Span">
												  <html:select property="countyId" styleId="county1">
    												<option value="" selected='selected'>Please Select</option>
    												<html:optionsCollection property="counties" value="code" label="description"/>				
    											</html:select>
												</span> 
											</td>								
										</tr>
									</table>
          			</td>
							</tr>
						</table>
           	<%-- END ADDRESS INFORMATION SECTION --%>

						<%-- BEGIN BUTTON TABLE --%>
						<div class='spacer'></div>
  						<div align="center">
    						<table>
  						  	<tr>
  						    	<td>  		 		
    									<html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>

    									<logic:equal name="juvenileContactForm" property="action" value="create"> 
      									<html:submit property="submitAction" styleId="contactCreateNext">
      										<bean:message key="button.next"></bean:message>
      									</html:submit>	
    									</logic:equal>

    									<logic:notEqual name="juvenileContactForm" property="action" value="create">  
      									<html:submit property="submitAction" styleId="contactUpdateNext">
      										<bean:message key="button.next"></bean:message>
      									</html:submit>	
    									</logic:notEqual>
  							    </td>
  						    	<td>  	
  										<input type="button" id="contactCancel" data-href="/<msp:webapp/>displayJuvenileContactList.do" value="<bean:message key='button.cancel'/>"/>	    	    	
  							    </td>
  						  	</tr>
  						  </table>
                <%-- END BUTTON TABLE --%>
							</div>
  						<div class='spacer'></div>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

<%-- BEGIN HIDDEN FIELDS FOR ADDRESS VALIDATION --%>
<table width='100%'>
  <tr>
  	<td>
  	  <html:hidden property="validStreetNum" value="" />
	  <html:hidden property="validStreetName" value="" />
  	  <html:hidden property="validZipCode" value="" />
  	  <html:hidden property="validAddrNum" value="" />
   	  <html:hidden property="inputPage" value="" />
  	</td>
  </tr>	  
</table>
<%-- ENd HIDDEN FIELDS FOR ADDRESS VALIDATION --%>

</html:form>	
<%-- END FORM --%>

<br>


<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
