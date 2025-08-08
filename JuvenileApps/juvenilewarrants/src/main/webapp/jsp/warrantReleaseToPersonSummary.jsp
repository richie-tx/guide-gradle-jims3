<!-- Used to display release to Person summary and confirmation -->
<!--MODIFICATIONS -->
<%-- CShimek	02/09/2005	Create JSP --%>
<%-- CShimek    10/04/2005  ER#24012 change assocaite labels to responsible adult --%>
<%-- CShimek    02/02/2006  Add hidden fields for HelpFile name --%>
<%-- CShimek    07/21/2006  Added County as display field in address as part of state selection change on create page --%>
<%-- LDeen	    12/21/2006  Revised help file code --%>
<%-- CShimek	03/22/2007  #40475 added missing weight unit annotation --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RYoung     08/06/2015 #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!DOCTYPE HTML>
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<html:base />
<title><bean:message key="title.heading"/> - warrantReleaseToPersonSummary.jsp</title>

</head>
<!--BEGIN BODY TAG-->
<body>
<html:form action="/submitReleaseToPerson" target="content">

<!-- BEGIN HEADING TABLE -->
<logic:equal name="juvenileWarrantForm" property="action" value="summary"> 
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|186">	
   <table align="center">
     <tr>
        <td class="header">
          <bean:message key="title.juvWarrantReleaseToPerson"/> Summary
        </td>
     </tr>
   </table>
   <table width="98%">
   	<tr>
     	<td><ul>
        	<li>Select Finish button to release juvenile to Person.</li>
	  	</ul></td>
  	</tr>
</table>
</logic:equal>
<logic:equal name="juvenileWarrantForm" property="action" value="confirm">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|187">	
   <table align="center">
     <tr>
        <td class="header">
          <bean:message key="title.juvWarrantReleaseToPerson"/> Confirmation
        </td>  
     </tr>
   </table>
   <table width="98%">
   	 <tr>
       <td align="center" class="confirm">Juvenile successfully released to the following Person.</td>
    </tr>
   </table>
</logic:equal>
<!-- END HEADING TABLE -->

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
                <td class="formDeLabel" width=1% nowrap><bean:message key="prompt.juvenileNumber"/></td>
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
                <td class=formDeLabel><bean:message key="prompt.scarsMarks"/></td>
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
             <!-- BEGIN PERSON RELEASED TO INFORMATION TABLE -->   
             <tr>
							 	<td colspan="4" class="detailHead" nowrap><bean:message key="prompt.personReleasedToInfo" /></td>
							</tr>  
							 <tr class="formDeLabel">
							 <td colspan="5" class="subhead"><bean:message key="prompt.personalInfo"/></td>
				          </tr>
						  <tr>
							<td class="formDeLabel"><bean:message key="prompt.name"/></td>
							<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.associateName.formattedName"/></td>
						  </tr>
						  <tr>
							<td class="formDeLabel"><bean:message key="prompt.dateOfBirth"/></td>
							<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.dateOfBirth"  formatKey="date.format.mmddyyyy"/></td>
						  </tr>
						  <tr>
							 <td class="formDeLabel"><bean:message key="prompt.race"/></td>
							 <td class=formDe><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.race"/></td>
				        
							 <td class="formDeLabel"><bean:message key="prompt.sex"/></td>
							 <td class=formDe><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.sex"/></td>
						  </tr>
						  <tr>
							<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.relationshipToJuvenile"/></td>
							<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.relationshipToJuvenile"/></td>
						  </tr>
						  <tr>
							<td class="formDeLabel"><bean:message key="prompt.ssn"/></td>
							<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.ssn.formattedSSN"/></td>
						  </tr>  
						   <tr>
							<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.driversLicenseNumber"/></td>
							<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.dlNumber"/></td>
						  </tr>  
						  <tr>
							<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.driversLicenseState"/></td>
							<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.dlState"/></td>
						  </tr>  
						  
						  <tr class="formDeLabel">						  
						  <td colspan="4" class="subhead"><bean:message key="prompt.contactInfo"/></td>
				        </tr>
				        <tr>
				          <td class="formDeLabel"><bean:message key="prompt.homePhone"/></td>
					      <td class=formDe><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.homePhoneNum"/></td>
				         
				          <td class="formDeLabel"><bean:message key="prompt.workPhone"/></td>
					      <td class=formDe><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.workPhoneNum"/></td>
				        </tr>
				        <tr>
				          <td class="formDeLabel"><bean:message key="prompt.cellPhone"/></td>
					      <td class=formDe><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.cellPhoneNum"/></td>
				         
				          <td class="formDeLabel"><bean:message key="prompt.pager"/></td>
					      <td class=formDe><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.pager"/></td>
				        </tr>
				        <tr>
				          <td class="formDeLabel"><bean:message key="prompt.faxNumber"/></td>
					      <td class=formDe><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.faxNum"/></td>
				          
				          <td class="formDeLabel"><bean:message key="prompt.faxLocation"/></td>
				          <td class=formDe><bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.faxLocation"/></td>
				        </tr>
				        <tr>
				  	      <td class="formDeLabel"><bean:message key="prompt.email"/> 1</td>
				          <td class=formDe colspan="3">
				              <bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.email1"/></td>
				        </tr>
				        <tr>
				  	      <td class="formDeLabel"><bean:message key="prompt.email"/> 2</td>
				          <td class=formDe colspan="3">
				              <bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.email2"/></td>
				        </tr>
				        <tr>
				  	      <td class="formDeLabel"><bean:message key="prompt.email"/> 3</td>
				          <td class=formDe colspan="3">
				              <bean:write name="juvenileWarrantForm" property="newReleaseToAssociate.email3"/></td>
				        </tr>  
            </table>


<br>
<logic:empty name="juvenileWarrantForm" property="newReleaseToAssociate.associateAddresses"> 
   <table width="98%" align="center" border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">     
        <tr>
				     <td class="detailHead"><bean:message key="prompt.associateAddressInfo" /></td>
				  </tr>
        <tr>               
           <td align="center">No addresses found</td>
        </tr> 
    </table>
</logic:empty>  
<logic:notEmpty name="juvenileWarrantForm" property="newReleaseToAssociate.associateAddresses">   		
<%-- this table will need "LOOP" to show mulitple addresses --%>
			<% int AddrCounter = 0; %>
			<logic:iterate id="address" name="juvenileWarrantForm" property="newReleaseToAssociate.associateAddresses"> 
	  <table width="98%" align="center" border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">            	
			  <tr>
			     <td class="detailHead" colspan="5"><bean:message key="prompt.associateAddressInfo" />&nbsp;<% AddrCounter++; out.print(AddrCounter);  %></td>
			  </tr>
			  <tr>
			    <td>       
			      <table width="100%" border="0" cellpadding="2" cellspacing="1">
							<tr>
			      		<td class="formDeLabel"><bean:message key="prompt.streetNumber"/></td>
					 		  <td class="formDeLabel" colspan=2><bean:message key="prompt.streetName"/></td>
							</tr>					
			        <tr>
			           <td class=formDe><bean:write name="address" property="streetNum"/></td>
			           <td class=formDe colspan=2><bean:write name="address" property="streetName"/></td>
			         </tr>
			         <tr>
					 		  <td class="formDeLabel"><bean:message key="prompt.streetType"/></td>
							  <td class="formDeLabel" colspan=2><bean:message key="prompt.apartmentNumber"/></td>
							</tr>
							<tr>
			           <td class=formDe><bean:write name="address" property="streetType"/></td>
			           <td class=formDe colspan=2><bean:write name="address" property="aptNum"/></td>
			        </tr>
							<tr>   
			         <td class="formDeLabel"><bean:message key="prompt.city"/></td>
							  <td class="formDeLabel"><bean:message key="prompt.state"/></td>
			          <td class="formDeLabel"><bean:message key="prompt.zipCode"/></td>
			        </tr>
			        <tr>  
			           <td class=formDe><bean:write name="address" property="city"/></td>
			           <td class=formDe><bean:write name="address" property="state"/></td>
			           <td class=formDe><bean:write name="address" property="zipCode"/>
			               <logic:notEqual name="address" property="additionalZipCode" value="">
			                  -<bean:write name="address" property="additionalZipCode"/>
			               </logic:notEqual>   
			           </td>
			        </tr>
			        <tr>  
			          <td class="formDeLabel"><bean:message key="prompt.addressType"/></td>
			          <td class="formDeLabel" colspan="2"><bean:message key="prompt.county"/></td>			          
			        </tr>
			        <tr>      
			           <td class=formDe><bean:write name="address" property="addressType"/></td>    
			           <td class=formDe colspan="2"><bean:write name="address" property="county"/></td>    			           
			        </tr>
				  	</table>
					</td>
			  </tr>
			</table>   
			<br>
			</logic:iterate>
  
</logic:notEmpty>  
    
<!-- END PERSON RELEASED TO INFORMATION TABLE -->

<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0" align="center">
	<tr>
	  <td align="center">
 	    <logic:equal name="juvenileWarrantForm" property="action" value="summary">
          <html:button
				property="org.apache.struts.taglib.html.BUTTON"
				onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message>
			</html:button>&nbsp;
	      <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message>
		  </html:submit>&nbsp;
 	  	  <html:submit property="submitAction">
				<bean:message key="button.cancel"></bean:message>
		  </html:submit>     
	  	</logic:equal>
	  	<logic:equal name="juvenileWarrantForm" property="action" value="confirm">  
  		   <html:submit property="submitAction">
       				 <bean:message key="button.mainPage"></bean:message>
       				 </html:submit>
			  <%-- <html:button property="org.apache.struts.taglib.html.CANCEL" 
						 onclick="document.location.href='../security.war/jsp/mainMenu.jsp'">
    			   </html:button> 
    		 --%> 
	  	</logic:equal>
	 </td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>