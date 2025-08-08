<!DOCTYPE HTML>
<!-- Used to create release to PERSON information. -->
<!--MODIFICATIONS -->
<%-- CShimek	01/13/2005	Create JSP --%>
<%-- CShimek    10/04/2005  ER#24012 change assocaite labels to responsible adult --%>
<%-- CShimek    02/02/2006  Add hidden field for HelpFile name --%>
<%-- CShimek    07/21/2006  Uncomment County to work with state selection --%>
<%-- LDeen	    12/21/2006  Revised help file code --%>
<%-- CShimek	03/22/2007  #40475 added missing weight unit annotation --%>
<%-- CShimek	04/11/2007 	#41031 revised RA href to open in new window --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RYoung     08/06/2015 #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.juvenilewarrant.helper.JuvenileWarrantListHelper" />

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css"/>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<!-- JAVASCRIPT FILES-->

<script type="text/javascript" src="/<msp:webapp/>js/warrants.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
$(function() {

    $("#dob").datepicker({
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        $( "#dob" ).datepicker( "option", "minDate", selectedDate );
	    }
    });
 });
</script>

<tiles:insert page="../js/warrantReleaseToPerson.js" />
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading"/> - warrantReleaseToPerson.jsp</title>
</head>
<!--END HEADING TAG-->

<!--BEGIN BODY TAG-->
<body onload="maintainAssociateRadio(document.forms[0].releaseAssociateNum), setStateCounty()">
<html:form action="/displayReleaseToPersonSummary" target="content">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|185">	

<!-- BEGIN HEADING TABLE -->
 <table align="center">
   <tr>
     <td class="header" align="center">
        <bean:message key="title.juvWarrantReleaseToPerson"/> Information
     </td>
   </tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%">	
   <tr>
	 <td>
	     <ul>
	       <li>Select radio button for existing responsible adult if juvenile is released to that person OR select New Responsible Adult radio button and
	        complete "Person Released To" information if juvenile is released to someone other than an existing responsible adult.</li>
	       <li>Select Next button to view Release to Person Summary.</li>
	       <li>Click on Existing Responsible Adult name to display full address and contact information.</li>
	     </ul>
	  </td>
   </tr>	
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN REQUIRED TABLE -->
<table border="0" width=98% align=center>	
	<tr>
	  <td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/>&nbsp;<bean:message key="prompt.ifExistingResponsibleAdultNotSelected"/>&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
    </tr>	
</table>
<!-- END REQUIRED TABLE -->
<!-- BEGIN JUVENILE INFORMATION TABLE -->
          <table align="center" width=98% cellspacing="1" cellpadding=4>
          	<tr>
				<td class=detailHead colspan=4 nowrap><bean:message key="prompt.juvenileInfo" /></td>
			</tr>
            <tr>
                <td class="formDeLabel"><bean:message key="prompt.juvenileName"/></td>          
                <td class="formDe" colspan="3">
                 <%-- <bean:write name="juvenileWarrantForm" property="juvenileName.formattedName"/> --%>
                 <bean:write name="juvenileWarrantForm" property="juvenileName.lastName"/>, 
                 <bean:write name="juvenileWarrantForm" property="juvenileName.firstName"/>
                 <bean:write name="juvenileWarrantForm" property="juvenileName.middleName"/> 
                 <bean:write name="juvenileWarrantForm" property="nameSuffix"/>
                </td>
             </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.aka"/></td>
                <td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="aliasName"/></td>
             </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.dateOfBirth"/></td>
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
                <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.juvenileNumber"/></td>
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="juvenileNum"/></td>
             </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.race"/></td>
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="race"/></td>
                <td class="formDeLabel"><bean:message key="prompt.sex"/></td>
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="sex"/></td>
             </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.height"/></td>
                <td class="formDe">
		       		<logic:notEqual name="juvenileWarrantForm" property="height" value="">
						<logic:notEqual name="juvenileWarrantForm" property="heightFeet" value="0">
		           			<bean:write name="juvenileWarrantForm" property="heightFeet"/>ft&nbsp;
   							<bean:write name="juvenileWarrantForm" property="heightInch"/>in&nbsp;
		           		</logic:notEqual> 
					</logic:notEqual>
                </td>
                <td class="formDeLabel"><bean:message key="prompt.weight"/></td>
                <td class="formDe">
		 			<logic:notEqual name="juvenileWarrantForm" property="weight" value="">
						<logic:notEqual name="juvenileWarrantForm" property="weight" value="0">
							<bean:write name="juvenileWarrantForm" property="weight"/>&nbsp;lbs
						</logic:notEqual>	
					</logic:notEqual>
                </td>
             </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.eyeColor"/></td>
                <td class="formDe" colspan="3">             
                  <bean:write name="juvenileWarrantForm" property="eyeColor"/>
                </td>
             </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.hairColor"/></td>
                <td class="formDe" colspan="3">              
                  <bean:write name="juvenileWarrantForm" property="hairColor"/>
                </td>
             </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.complexion"/></td>
                <td class="formDe" colspan="3">               
                  <bean:write name="juvenileWarrantForm" property="complexion"/>
                </td>
             </tr>   
             <tr>
                <td class=formDeLabel width=1% nowrap><bean:message key="prompt.scarsMarks"/></td>
                <td class="formDe" colspan="3">
		           <logic:notEmpty name="juvenileWarrantForm" property="scarsAndMarksSelected">
		             <logic:iterate id="scars" name="juvenileWarrantForm" property="scarsAndMarksSelected">
		                <bean:write name="scars" property="description" /><br>
		           	</logic:iterate>
		           </logic:notEmpty>
                </td>
             </tr>
             <tr>
                <td class=formDeLabel><bean:message key="prompt.tattoos"/></td>
                <td class="formDe" colspan="3">
		            <logic:notEmpty name="juvenileWarrantForm" property="tattoosSelected">
		              	<logic:iterate id="tattoo" name="juvenileWarrantForm" property="tattoosSelected">
		                  		<bean:write name="tattoo" property="description" /><br>
		               	</logic:iterate>
		            </logic:notEmpty>
                </td>
             </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.schoolDistrict"/></td>
                <td class="formDe" colspan="3">
                   <bean:write name="juvenileWarrantForm" property="schoolDistrictName"/>
                </td> 
            </tr>
            <tr>        
                <td class="formDeLabel"><bean:message key="prompt.schoolName"/></td>
                <td class="formDe" colspan="3">
                  <bean:write name="juvenileWarrantForm" property="schoolName"/>
                </td> 
            </tr> 
             <!-- END JUVENILE INFORMATION TABLE -->
             <tr><td><br></td></tr>
             <!-- BEGIN RELEASE TO PROBATION OFFICER INFORMATION TABLE -->
           	<tr>
			 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.releaseJPOfficerInfo" /></td>
			</tr>
			<tr>
                <td class="formDeLabel"><bean:message key="prompt.jpOfficerName"/></td>          
                <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="releaseDecisionUserName"/></td>
            </tr>
            <!-- END RELEASE TO PROBATION OFFICER INFORMATION TABLE -->
             <tr><td><br></td></tr>           
             <!-- BEGIN EXISTING RESPONSIBLE ADULT INFORMATION TABLE -->
           	<tr>
						 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.existingAssociateInfo" /></td>
						</tr>
						<%int RecordCounter = 0;
							 String bgcolor = "";%>
					    <tr bgcolor="#f0f0f0">
					      <td colspan="4">
					         <table width="100%" align="center" border=0 cellspacing="1">
					            <tr bgcolor="#CCCCCC">
					               <td width="2%">&nbsp;</td>
						           <td width="60%" class="subhead" align="left"><bean:message key="prompt.name"/></td>
						           <td width="38%" class="subhead" align="left"><bean:message key="prompt.relationshipToJuvenile"/></td>
					            </tr>
					         <logic:empty name="juvenileWarrantForm" property="associates"> 
					            <tr>
					               <td width="2%">No responsible adults found</td>
					               <td colspan="2" align="center">&nbsp;</td>
					            </tr> 
					         </logic:empty>    
					<%-- not required as part of interation #7   ?associateNumber=10--%> 
					       <logic:notEmpty name="juvenileWarrantForm" property="associates">   
					              <logic:iterate id="associate" name="juvenileWarrantForm" property="associates"> 
					                <tr class= <% RecordCounter++;
					                      bgcolor = "alternateRow";
					                      if (RecordCounter % 2 == 1)
					                          bgcolor = "normalRow";
					                      out.print(bgcolor);  %>	align="left">
						               <td align="left">
						                 <html:radio property="releaseAssociateNum" idName="associate" value="associateNum"  onclick="radioIsNewAssociate(this)" />
					                   </td> 
						               <td><a href="javascript:openWindow('/<msp:webapp/>displayAssociateDetails.do?associateNumber=<bean:write name="associate" property="associateNum"/>');"> 
						                  <bean:write name="associate" property="associateName.lastName" />, <bean:write name="associate" property="associateName.firstName" /> <bean:write name="associate" property="associateName.middleName" /></a></td>
						               <td nowrap><bean:write  name="associate" property="relationshipToJuvenile" /></td>			 
					                </tr>
					              </logic:iterate> 
					              <tr class= <% RecordCounter++;
					                      bgcolor = "alternateRow";
					                      if (RecordCounter % 2 == 1)
					                          bgcolor = "normalRow";
					                      out.print(bgcolor);  %>	>
						               <td>
						                 <html:radio property="releaseAssociateNum" value="" onclick="radioIsNewAssociate(this)" />
					                   </td> 
						               <td colspan=2><bean:message key="prompt.newResponsibleAdult" /></td> 
					              </tr>     
					           </logic:notEmpty> 
					          </table>
					       </td>
					    </tr>    
					    <!--END EXISTING RESPONSIBLE ADULT INFORMATION TABLE -->        					    
          </table>
        

<!-- BEGIN PERSON RELEASED TO TABLES -->
<span id="newAssociate" class=visible>
<br>
   
	      <table width="98%" align="center" border="0" cellpadding="2" cellspacing="1">
          <tr>
					 	<td colspan=4 class=detailHead nowrap><bean:message key="prompt.personReleasedTo" /></td>
					</tr>
					<!-- BEGIN PERSONAL INFORMATION TABLE -->	 
          <tr bgcolor="#CCCCCC">
            <td colspan="4" class="subhead"><bean:message key="prompt.personalInfo" /></td>
          </tr>           
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.lastName"/></td>
                <td colspan="3" class=formDe><html:text name="juvenileWarrantForm" property="newReleaseToAssociate.associateName.lastName" size="30" maxlength="30"/></td>
             </tr>
             <tr>   
                <td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.firstName"/></td>
                <td colspan="3" class=formDe><html:text name="juvenileWarrantForm" property="newReleaseToAssociate.associateName.firstName" size="25" maxlength="25"/></td>
             </tr>
             <tr> 	
                <td class="formDeLabel"><bean:message key="prompt.middleName"/></td>
                <td colspan="3" class=formDe><html:text name="juvenileWarrantForm" property="newReleaseToAssociate.associateName.middleName" size="25" maxlength="25"/></td>   
             </tr>
             <tr>
	            <td class="formDeLabel"><bean:message key="prompt.1.diamond"/>
	            	<bean:message key="prompt.dateOfBirth"/>
	            </td>
                <td colspan="3" class=formDe>
                	<html:text name="juvenileWarrantForm" property="newReleaseToAssociate.dateOfBirthString" size="10" maxlength="10" styleId="dob"/>&nbsp;
                </td>
             </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.race"/></td>
                <td class=formDe>
                    <html:select name="juvenileWarrantForm" property="newReleaseToAssociate.raceId">
  	                  <html:option value=""><bean:message key="select.generic" /></html:option>  	
                      <html:optionsCollection name="codeHelper" property="races" value="code" label="description" />
	                </html:select>
                </td>
         
                <td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.sex"/></td>
                <td class=formDe>
                    <html:select name="juvenileWarrantForm" property="newReleaseToAssociate.sexId"> 
  	                   <html:option value=""><bean:message key="select.generic" /></html:option>  	                  	
                       <html:optionsCollection name="codeHelper" property="sexes" value="code" label="description" />
	                 </html:select>
	            </td>
             </tr>
             <tr>
                <td class="formDeLabel" width=1% nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.relationshipToJuvenile"/></td>
                <td colspan="3" class=formDe>
                    <html:select name="juvenileWarrantForm" property="newReleaseToAssociate.relationshipToJuvenileId">		
  	                  <html:option value=""><bean:message key="select.generic" /></html:option>  	
                      <html:optionsCollection name="codeHelper" property="relationshipsToJuvenile" value="code" label="description" />
	                </html:select>
                </td>
             <tr>
	            <td class="formDeLabel"><bean:message key="prompt.ssn"/></td>
	             <td colspan="3" class=formDe>
   	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.ssn.SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3)"/> -
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.ssn.SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2)"/> -
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.ssn.SSN3" size="4" maxlength="4" onkeyup="return autoTab(this, 4)"/> 
	             </td> 
             </tr>
		 	<tr>	
				<td class=formDeLabel><bean:message key="prompt.driversLicenseNumber"/></td>
				<td class=formDe colspan=3><html:text name="juvenileWarrantForm" property="newReleaseToAssociate.dlNumber" size="10" maxlength="10" /></td>
			</tr>    
	
			<tr>	
			<td class=formDeLabel><bean:message key="prompt.driversLicenseState"/></td>
			<td class=formDe colspan=3>		
				<html:select name="juvenileWarrantForm" property="newReleaseToAssociate.dlStateId">
					<html:option value=""><bean:message key="select.generic"/></html:option>
					<html:optionsCollection name="codeHelper" property="states" value="code" label="description"/> 
				</html:select> 
			</td>
			</tr>    			             
             <!-- END PERSONAL INFORMATION TABLE -->	 
             
             <!-- BEGIN CONTACT INFORMATION TABLE -->
              <tr class="subhead" bgcolor="#CCCCCC">
                  <td colspan="4" class="subhead"><bean:message key="prompt.contactInfo"/></td>
              </tr>
              <tr>	 
   	            <td class="formDeLabel"><bean:message key="prompt.homePhone"/></td> 
   	            <td class=formDe>
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.homePhoneNum.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3)" /> -
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.homePhoneNum.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3)" /> -
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.homePhoneNum.4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4)"/>  
	            </td>           
   	            <td class="formDeLabel"><bean:message key="prompt.workPhone"/></td> 
   	            <td class=formDe>
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.workPhoneNum.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3)"/> -
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.workPhoneNum.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3)"/> -
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.workPhoneNum.4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4)"/>  
	            </td>
              </tr>
              <tr>	 
   	            <td class="formDeLabel"><bean:message key="prompt.cellPhone"/></td> 
   	            <td class=formDe>
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.cellPhoneNum.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3)"/> -
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.cellPhoneNum.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3)"/> -
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.cellPhoneNum.4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4)"/>  
	            </td>
                
   	            <td class="formDeLabel"><bean:message key="prompt.pager"/></td> 
   	            <td class=formDe>
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.pager.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3)"/> -
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.pager.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3)"/> -
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.pager.4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4)"/>  
	            </td>
              </tr>
              <tr>	 
   	            <td class="formDeLabel"><bean:message key="prompt.faxNumber"/></td> 
   	            <td class=formDe>
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.faxNum.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3)"/> -
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.faxNum.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3)"/> -
	               <html:text name="juvenileWarrantForm" property="newReleaseToAssociate.faxNum.4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4)"/>  
	            </td>
   	            <td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.faxLocation"/></td> 
   	            <td class=formDe><html:text name="juvenileWarrantForm" property="newReleaseToAssociate.faxLocation" size="30" maxlength="30"/></td>
              </tr>
              <tr>	 
   	            <td class="formDeLabel"><bean:message key="prompt.email"/> 1</td> 
   	            <td class=formDe colspan="3"><html:text name="juvenileWarrantForm" property="newReleaseToAssociate.email1" size="40" maxlength="40"/></td>
              </tr>
              <tr>	 
   	            <td class="formDeLabel"><bean:message key="prompt.email"/> 2</td> 
   	            <td class=formDe colspan="3"><html:text name="juvenileWarrantForm" property="newReleaseToAssociate.email2" size="40" maxlength="40"/></td>
              </tr>
              <tr>	 
   	            <td class="formDeLabel"><bean:message key="prompt.email"/> 3</td> 
   	            <td class=formDe colspan="3"><html:text name="juvenileWarrantForm" property="newReleaseToAssociate.email3" size="40" maxlength="40"/></td>
              </tr>
              <!-- END CONTACT INFORMATION TABLE -->  
              <tr><td><br></td></tr>
              <%-- BEGIN UPDATE ADDRESS INFORMATION TABLE --%>	
              <tr>
							  <td colspan=4>   
							  
							  <% int AddrCounter = 0; %>
							  <logic:iterate id="address" name="juvenileWarrantForm" property="newReleaseToAssociate.associateAddresses"> 
							  <table width="100%" border="0" cellpadding="2" cellspacing="0" align="center" class="borderTableBlue">
							    <tr>
								  <td class="detailHead"><bean:message key="prompt.associateAddressInfo" />&nbsp;<% AddrCounter++; out.print(AddrCounter);  %></td>
								</tr>
							    <tr>
								  <td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1" > 
									  <tr>
									    <td class="formDeLabel">
								    	<% if(AddrCounter == 1){ %><bean:message key="prompt.1.diamond"/><%}%><bean:message key="prompt.streetNumber"/></td>
									    <td class="formDeLabel" colspan=2><% if(AddrCounter == 1){ %><bean:message key="prompt.1.diamond"/><%}%><bean:message key="prompt.streetName"/></td>									    
									  </tr>
									  <tr>
									    <td class=formDe><html:text name="address" property="streetNum" size="6" maxlength="6"/></td>
									    <td class=formDe colspan=2><html:text name="address" property="streetName" size="30" maxlength="30"/></td>
									  </tr>
									  <tr>
									  	<td class="formDeLabel"><bean:message key="prompt.streetType"/></td>
									  	<td class="formDeLabel" colspan=2><bean:message key="prompt.apartmentNumber"/></td>
									  </tr>
									  <tr>
									  	<td class=formDe>
									      <html:select name="address" property="streetTypeId">
										     <html:option value=""><bean:message key="select.generic"/></html:option>
										     <html:optionsCollection name="codeHelper" property="streetTypes" value="code" label="description"/>
										   </html:select> 
							            </td>
											<td class=formDe colspan=2><html:text name="address" property="aptNum" size="6" maxlength="6"/></td>
									  </tr>
									  <tr>
									    <td class="formDeLabel"><% if(AddrCounter == 1){ %><bean:message key="prompt.1.diamond"/><%}%><bean:message key="prompt.city"/></td>
									    <td class="formDeLabel"><% if(AddrCounter == 1){ %><bean:message key="prompt.1.diamond"/><%}%><bean:message key="prompt.state"/></td>
									    <td class="formDeLabel"><bean:message key="prompt.zipCode"/></td>
									  </tr>
									  <tr>
										<td class=formDe><html:text name="address" property="city" size="15" maxlength="15"/></td>
										<td class=formDe>
									      <html:select name="address" indexed="true" property="stateId" onchange="javascript:enableCounty(this, this.name)">
										     <html:option value=""><bean:message key="select.generic"/></html:option>
										     <html:optionsCollection name="codeHelper" property="states" value="code" label="description"/>
										   </html:select> 
							            </td>
									    <td class=formDe>
										  <html:text name="address" property="zipCode" size="5" maxlength="5" onkeyup="return autoTab(this, 5);"/>-
										  <html:text name="address" property="additionalZipCode" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/></td>
									  </tr> 
									  <tr>
										<td class="formDeLabel" ><bean:message key="prompt.addressType"/></td>
										<td class="formDeLabel" colspan="2"><bean:message key="prompt.county"/></td>
									  </tr>
									  <tr>
									    <td class=formDe class=formDe>
									     <html:select name="address" property="addressTypeId">
										     <html:option value=""><bean:message key="select.generic"/></html:option>
										     <html:optionsCollection name="codeHelper" property="addressTypes" value="code" label="description"/> 
										  </html:select>
										</td>  
										<td colspan="2">
											<html:select name="address" indexed="true" property="countyId">
											<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
											<html:optionsCollection name="codeHelper" property="counties" value="code" label="description" />
											</html:select>  
										</td>
									  </tr>  
								    </table>
								  </td>
							    </tr>
							  </table> 		
							  <br>  
							  </logic:iterate> 
							<!-- END ASSOCIATE ADDRESS INFORMATION TABLE -->           
				        </td>
				     </tr>   
            </table>
</span>
<!-- END PERSON RELEASED TO TABLES --> 

<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0" align="center">
	<tr valign="top">
	  <td align="right" width="55%">
		<logic:notEmpty name="juvenileWarrantForm" property="warrants">
	         <html:button
				property="org.apache.struts.taglib.html.BUTTON"
				onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message>
			 </html:button>&nbsp;
		</logic:notEmpty>
	    <html:submit property="submitAction" onclick="return validateFields(this.form) && disableSubmit(this, this.form)"><bean:message key="button.next"></bean:message> </html:submit>&nbsp;
		<html:reset onclick="showNewAssociate()"><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
	  </td>
	  </html:form>
	<!-- END 1st FORM -->  
	  <td align="left">	
	    <html:form action="/displayGenericSearch.do?warrantTypeUI=releaseToPerson"> 			  
			<html:submit property="submitAction">
				<bean:message key="button.cancel"></bean:message>
			</html:submit>
		</html:form>      
	 </td>
	</tr>
</table>
<!-- END BUTTON TABLE -->


<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>