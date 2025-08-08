<!DOCTYPE HTML>
<!--This JSP will be used to create, update and create additional addresses.-->
<!--MODIFICATIONS -->
<%-- Hien Rodriguez	06/22/2004	Create JSP --%>
<%-- CShimek        10/04/2005  ER#24012 change assocaite labels to responsible adult --%>
<%-- CShimek        02/02/2006  Add hidden fields for helpFile names --%>
<%-- CShimek        02/14/2006  Defect#26016 Add address validation note --%>
<%-- CShimek        02/16/2006  Defect#27034 Add edits for Manage Addresses button to prevent blank creates --%> 
<%-- LDeen          03/07/2006  Added width=1% attribute --%>
<%-- CShimek        04/12/2006  Defect#30141 Revised page to match prototypes and other small display items --%> 
<%-- CShimek		04/17/2006  Defect#29594 modified Cancel button to use action instead of jsp link --%>
<%-- CShimek        05/12/2006  #30640 - Display county select with Harris as default when state select is Texas otherwise 
									disable county select and show Please Select --%>
<%-- CShimek		12/12/2006  corrected small js problem in checkForm  --%>
<%-- CShimek		12/21/2006  revised helpfile reference value--%>
<%-- CShimek		01/31/2007  #39097 added multiple submit button logic --%>
<%-- CShimek		03/30/2007  #40630 fixed problem on initializing state and county values on initial page entry --%>
<%-- CShimek		04/11/2007  #41031 removed Back To Warrant button --%>
<%-- LDeen		 	06/04/2007  #42564 changed path to app.js --%>
<%-- LDeen		 	06/04/2007  #40649 added include for address.js --%>
<%-- RYoung         08/6/2015   #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%> 

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.juvenilewarrant.helper.JuvenileWarrantListHelper" />

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--CUSTOM TAG LIBRARIES FOR PRESENTION -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="data" %>
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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css"/>
<html:base />
<title><bean:message key="title.heading" /> - warrantAssociateCreateUpdate.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="juvenileAssociateForm" />
<!-- JAVASCRIPT FILE -->

<script type="text/javascript" src="/<msp:webapp/>js/warrants.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/address.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/warrantAssociateCreateUpdate.js"></script>

<script>
$(function() {

    $( "#dob" ).datepicker({
      changeMonth: true,
      changeYear: true,
    });
});
</script>

</head>
<!--END HEADER TAG -->

<!--BEGIN BODY TAG -->
<body onload="setStateCounty();" onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN HEADING TABLE -->
 <table align="center">
    <tr>
      <td align="center" class="header">
          <logic:equal name="juvenileAssociateForm" property="action" value="update">
            <bean:message key="prompt.update"/> <bean:message key="title.juvWarrantAssociate"/> <bean:message key="prompt.info"/> 
          </logic:equal>
          <logic:equal name="juvenileAssociateForm" property="action" value="create">
            <bean:message key="prompt.create"/> <bean:message key="title.juvWarrantAssociate"/> <bean:message key="prompt.info"/> 
          </logic:equal>
      </td>	
	</tr>
  </table>
<!-- END HEADING TABLE -->
<!-- BEGIN WARRANT NUMBER TABLE -->
  <table width="98%" border="0" align="center" cellspacing="1" cellpadding="2">
	<tr>
	  <td align=center class="subhead"><bean:message key="prompt.warrantNumber"/>: <span class=warrantNumber><bean:write name="juvenileWarrantForm" property="warrantNum"/></span></td>
    </tr>
  </table>
<%-- END WARRANT NUMBER TABLE --%>
<!-- BEGIN INSTRUCTION TABLE -->
  <table width=98% border=0 align=center>
	<tr>
	  <td>
   		<ul>
			<li>Enter at least the required information and select Next button to view Summary.</li>
			<li>Click Manage Addresses button to add additional addresses.</li>
	       	<li>Only addresses in the Harris County Geographic Information System can be validated.</li> 		  
   		</ul>
	 </td>
	</tr>
	<tr>
	  <td class="required"><bean:message key="prompt.mjw.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/>&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
	</tr>
  </table>
<%-- END INSTRUCTION TABLE --%>
<!-- BEGIN FORM -->

<!-- BEGIN ERROR TABLE -->
    <table width="98%" border=0 align=center>
      <tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	  </tr>   	  
    </table>
<!-- END ERROR TABLE -->

<table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
<html:form action="/handleWarrantAssociateSelection" focus="associateName.lastName" onsubmit="">
	<logic:equal name="juvenileAssociateForm" property="action" value="update">
    	<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|194">
	</logic:equal>
	<logic:equal name="juvenileAssociateForm" property="action" value="create">
		<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|170">
	</logic:equal>
	<input type="hidden" name="isDetails" value="">
<%-- BEGIN ASSOCIATE NAME INFORMATION BLOCK --%>
	<tr class="detailHead">
		<td class="subhead" colspan="4"><bean:message key="prompt.juvenileAssociateInfo"/></td>
	</tr>
	<logic:equal name="juvenileAssociateForm" property="action" value="update">
		<tr>	
			<td class=formDeLabel nowrap width="1%"><bean:message key="prompt.associateNumber"/></td>
			<td class=formDe colspan=3><bean:write name="juvenileAssociateForm" property="associateNum"/></td>
		</tr>
	</logic:equal>		
    <tr>
		<td class=formDeLabel><bean:message key="prompt.1.diamond"/><bean:message key="prompt.lastName"/></td>
		<td class=formDe colspan=3><html:text property="associateName.lastName" size="30" maxlength="30"/></td>
    </tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.1.diamond"/><bean:message key="prompt.firstName"/></td>
		<td class=formDe colspan=3><html:text property="associateName.firstName" size="25" maxlength="25"/></td>
	</tr>
	<tr>	
		<td class=formDeLabel><bean:message key="prompt.middleName"/></td>
		<td class=formDe colspan=3><html:text property="associateName.middleName" size="25" maxlength="25"/></td>
	</tr>
	<tr>
		<td class=formDeLabel nowrap width="1%"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.relationshipToJuvenile"/></td>
		<td colspan="3" class=formDe>
			<html:select property="relationshipToJuvenileId">
				<html:option value=""><bean:message key="select.generic"/></html:option>
				<html:optionsCollection name="codeHelper" property="relationshipsToJuvenile" value="code" label="description"/> 
			</html:select> 
		</td>
	</tr>
	<tr>  
		<td class=formDeLabel><bean:message key="prompt.ssn" /></td>
		<td  class=formDe>
			<html:text property="ssn.SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/>-
			<html:text property="ssn.SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);"/>-
			<html:text property="ssn.SSN3" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>
		</td>
		<td class=formDeLabel nowrap width="1%"><bean:message key="prompt.dateOfBirth"/></td>
		<td class=formDe><html:text property="dateOfBirthString" size="10" maxlength="10" styleId="dob" />
		</td>    		      
	</tr>  
	<tr>
		<td class=formDeLabel><bean:message key="prompt.race"/></td>
		<td class=formDe>
			<html:select property="raceId">
				<html:option value=""><bean:message key="select.generic"/></html:option>
				<html:optionsCollection name="codeHelper" property="races" value="code" label="description"/> 
			</html:select> 
		</td>
		<td class=formDeLabel><bean:message key="prompt.sex" /></td>
		<td class=formDe>
			<html:select property="sexId">
				<html:option value=""><bean:message key="select.generic"/></html:option>
				<html:optionsCollection name="codeHelper" property="sexes" value="code" label="description"/> 
			</html:select> 		   	 
		</td>  
	</tr>
	<tr>	
		<td class=formDeLabel><bean:message key="prompt.driversLicenseNumber"/></td>
		<td class=formDe colspan=3><html:text property="dlNumber" size="10" maxlength="10" /></td>
	</tr>    
	
	<tr>	
		<td class=formDeLabel><bean:message key="prompt.driversLicenseState"/></td>
		<td class=formDe colspan=3>		
			<html:select property="dlStateId">
				<html:option value=""><bean:message key="select.generic"/></html:option>
				<html:optionsCollection name="codeHelper" property="states" value="code" label="description"/> 
			</html:select> 
		</td>
	</tr>    
	
<%-- END ASSOCIATE NAME INFORMATION BLOCK --%>	
<TR><TD><br></TD></TR>
<!-- BEGIN CONTACT INFORMATION BLOCK -->
	<tr class="detailHead">
		<td class="subhead" colspan="4"><bean:message key="prompt.contactInfo"/></td>
	</tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.homePhone"/></td>
		<td class=formDe colspan=3>
			<html:text property="homePhoneNum.areaCode" size="3" onkeyup="return autoTab(this, 3);" />-
			<html:text property="homePhoneNum.prefix" size="3" onkeyup="return autoTab(this, 3);" />-
			<html:text property="homePhoneNum.4Digit" size="4" onkeyup="return autoTab(this, 4);" /> 
		</td>
	</tr>
	<tr>	
		<td class=formDeLabel nowrap><bean:message key="prompt.workPhone"/></td>
		<td class=formDe colspan=3>
			<html:text property="workPhoneNum.areaCode" size="3" onkeyup="return autoTab(this, 3);" />-
			<html:text property="workPhoneNum.prefix" size="3" onkeyup="return autoTab(this, 3);" />-
			<html:text property="workPhoneNum.4Digit" size="4" onkeyup="return autoTab(this, 4);" /> 
		</td>
	</tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.cellPhone"/></td>
		<td class=formDe colspan=3>
			<html:text property="cellPhoneNum.areaCode" size="3" onkeyup="return autoTab(this, 3);" />-
			<html:text property="cellPhoneNum.prefix" size="3" onkeyup="return autoTab(this, 3);" />-
			<html:text property="cellPhoneNum.4Digit" size="4" onkeyup="return autoTab(this, 4);" /> 
		</td>
	</tr>
	<tr>	
		<td class=formDeLabel nowrap><bean:message key="prompt.pager"/></td>
		<td class=formDe colspan=3>
			<html:text property="pager.areaCode" size="3" onkeyup="return autoTab(this, 3);" />-
			<html:text property="pager.prefix" size="3" onkeyup="return autoTab(this, 3);" />-
			<html:text property="pager.4Digit" size="4" onkeyup="return autoTab(this, 4);"/> 
		</td>
	</tr>
    <tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.faxNumber"/></td>
		<td class=formDe colspan=3>
			<html:text property="faxNum.areaCode" size="3" onkeyup="return autoTab(this, 3);" />-
			<html:text property="faxNum.prefix" size="3" onkeyup="return autoTab(this, 3);" />-
			<html:text property="faxNum.4Digit" size="4" onkeyup="return autoTab(this, 4);" /> 
		</td>
	</tr>
	<tr>	
		<td class=formDeLabel><bean:message key="prompt.faxLocation"/></td>
		<td class=formDe colspan=3><html:text property="faxLocation" size="30" maxlength="30" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.email"/> 1</td>
		<td colspan=3 class=formDe><html:text property="email1" size="40" maxlength="100" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.email"/> 2</td>
		<td colspan=3 class=formDe><html:text property="email2" size="40" maxlength="100" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.email"/> 3</td>
		<td colspan=3 class=formDe><html:text property="email3" size="40" maxlength="100" /></td>
	</tr> 
<!-- END CONTACT INFORMATION BLOCK -->
<TR><TD><br></TD></TR>
</table>
<%-- BEGIN UPDATE ADDRESS INFORMATION TABLE --%>	
<table width=98% border=0 cellspacing=1 cellpadding=2 align=center>	  	
  <% int AddrCounter = 0; 
     int AddrIndex = -1; %>
  <logic:iterate id="associateAddresses" name="juvenileAssociateForm" property="associateAddresses"> 
    <tr>
    	<td class="detailHead" colspan=4>
	    	<table width=100% align=right border=0 cellpadding=0 cellspacing=0>
    			<tr> 	
				    <td class="detailHead" nowrap><bean:message key="prompt.associateAddressInfo"/>&nbsp;<% AddrCounter++; out.print(AddrCounter); %></td>   
					<td class="detailHead" align=right><bean:message key="prompt.addressStatus"/>:&nbsp;</td>
	        	    <td class="errorAlert">
<%-- TODO replace logic tags with code table values --%>	        	    
	        	       <logic:equal name="associateAddresses" property="addressStatus" value="U">
	        	       	UNPROCESSED
	        	       </logic:equal>
	        	       <logic:equal name="associateAddresses" property="addressStatus" value="Y">
	        	       	VALID
	        	       </logic:equal>
	        	       <logic:equal name="associateAddresses" property="addressStatus" value="N">
	        	       	INVALID
	        	       </logic:equal>
	        	       <%-- bean:write name="associateAddresses" property="addressStatus"/ --%>
	        	       <%-- html:hidden indexed="true" property="addressStatus"/ --%>
	        	    </td>
		        </tr>
		        <tr>
					<td colspan=3 align=center>	
						<input type=submit value="<bean:message key='button.validate' />" onClick="return changeAddrFormActionURL('juvenileAssociateForm','/<msp:webapp/>validateAddress.do',<% AddrIndex++; out.print(AddrIndex);  %>, '/jsp/warrantAssociateCreateUpdate.jsp', true);"></input>
			    	    <html:button property="org.apache.struts.taglib.html.BUTTON" 
			        			 onclick="return displayResearchWindow();">
								  <bean:message key="button.research"></bean:message>
					   </html:button>		   
					</td>        
		        </tr>
		     </table>
	     </td>       
	</tr>
	<tr>
       	<% if(AddrCounter == 1){ %>
	    <td class=formDeLabel><bean:message key="prompt.mjw.diamond"/><bean:message key="prompt.streetNumber"/></td>
	    <td class=formDeLabel colspan="2"><bean:message key="prompt.mjw.diamond"/><bean:message key="prompt.streetName"/></td>
       	<% }else if(AddrCounter == 2){ %>
	    <td class=formDeLabel><bean:message key="prompt.streetNumber"/></td>
	    <td class=formDeLabel colspan="2"><bean:message key="prompt.streetName"/></td>
	    <% }%>
	</tr>
	<tr>
	    <td class=formDe><html:text name="associateAddresses" indexed="true" property="streetNum" size="9" maxlength="9"/></td>
	    <td class=formDe colspan="2"><html:text name="associateAddresses" indexed="true" property="streetName" size="30" maxlength="30"/></td>
	</tr>	
	<tr>    
	    <td class=formDeLabel><bean:message key="prompt.streetType"/></td>
	  	<td class=formDeLabel colspan="2"><bean:message key="prompt.apartmentNumber"/></td>
	</tr>

	<tr>
	  	<td class=formDe>
		      <html:select name="associateAddresses" indexed="true" property="streetTypeId">
			     <html:option value=""><bean:message key="select.generic"/></html:option>
			     <html:optionsCollection name="codeHelper" property="streetTypes" value="code" label="description"/>
			   </html:select> 
        </td>
		<td class=formDe colspan="2"><html:text name="associateAddresses" indexed="true" property="aptNum" size="6" maxlength="6"/></td>
    </tr>
    <tr>
       	<% if(AddrCounter == 1){ %>
	    <td class=formDeLabel><bean:message key="prompt.mjw.diamond"/><bean:message key="prompt.city"/></td>
	    <td class=formDeLabel><bean:message key="prompt.mjw.diamond"/><bean:message key="prompt.state"/></td>
   	    <td class=formDeLabel><bean:message key="prompt.mjw.diamond"/><bean:message key="prompt.zipCode"/></td>
       	<% }else if(AddrCounter == 2){ %>
	    <td class=formDeLabel><bean:message key="prompt.city"/></td>
	    <td class=formDeLabel><bean:message key="prompt.state"/></td>
   	    <td class=formDeLabel><bean:message key="prompt.zipCode"/></td>
	    <% }%>
    </tr>
	<tr>
		<td class=formDe><html:text name="associateAddresses" indexed="true" property="city" size="15" maxlength="15"/></td>
		<td class=formDe>
		      <html:select name="associateAddresses" indexed="true" property="stateId" onchange="javascript:enableCounty(this, this.name)">
			     <html:option value=""><bean:message key="select.generic"/></html:option>
			     <html:optionsCollection name="codeHelper" property="states" value="code" label="description"/>
			   </html:select> 
         </td>
	     <td class=formDe>
			  <html:text name="associateAddresses" indexed="true" property="zipCode" size="5" maxlength="5" onkeyup="return autoTab(this, 5);"/>-
			  <html:text name="associateAddresses" indexed="true" property="additionalZipCode" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>
		 </td>
    </tr>
    <tr>
		<td class=formDeLabel><bean:message key="prompt.addressType"/></td>
		<td class=formDeLabel colspan=3><bean:message key="prompt.county"/></td>
    </tr>
    <tr>
	    <td class=formDe>
 	          <html:select name="associateAddresses" indexed="true" property="addressTypeId">
			     <html:option value=""><bean:message key="select.generic"/></html:option>
			     <html:optionsCollection name="codeHelper" property="addressTypes" value="code" label="description"/> 
			  </html:select>
		</td>
		<td class=formDe colspan=2>
	       <html:select name="associateAddresses" indexed="true" property="countyId">
			     <html:option value=""><bean:message key="select.generic"/></html:option>
			     <html:optionsCollection name="codeHelper" property="counties" value="code" label="description"/> 
			  </html:select>
		</td>
    </tr>
    
	<logic:notEqual name="associateAddresses" property="addressMessage" value="">
    	<tr>
        	 <td colspan=3 bgcolor=yellow><bean:write name="associateAddresses" property="addressMessage" /></td>
   	 	</tr>
	</logic:notEqual>
    
<%-- TR><TD><br></TD></TR --%>  
    </logic:iterate> 
    <tr>
    	<td align="center" colspan=4>
          <logic:equal name="juvenileAssociateForm" property="action" value="update">
	   			<html:submit property="submitAction" onclick="return validateJuvenileAssociateForm(this.form) && checkForm(this.form) && disableSubmit(this, this.form)">
	   				<bean:message key="button.manageAddresses"></bean:message>
	   			</html:submit> 
          </logic:equal>
          <logic:equal name="juvenileAssociateForm" property="action" value="create">
  				<html:submit property="submitAction" onclick="return validateJuvenileAssociateForm(this.form) && checkForm(this.form) && disableSubmit(this, this.form)">
  					<bean:message key="button.manageAddresses"></bean:message>
  				</html:submit> 
          </logic:equal>      	
<%--	  		  <html:submit property="submitAction" >
            	   <bean:message key="button.manageAddresses"></bean:message>
           	</html:submit> --%>
	    </td>
    </tr> 
</table> 		  
<%-- END UPDATE ADDRESS INFORMATION --%>

<%-- END ADDRESS INPUT AREA --%>
<br>
<!-- BEGIN BUTTON TABLE --> 
  <table border="0" width="100%">
	<tr>
  	  <td align="center">
          <logic:equal name="juvenileAssociateForm" property="action" value="update">
	   			<html:submit property="submitAction" onclick="return validateJuvenileAssociateForm(this.form) && checkForm(this.form) && disableSubmit(this, this.form)"><bean:message key="button.next" /></html:submit>&nbsp; 
          </logic:equal>
          <logic:equal name="juvenileAssociateForm" property="action" value="create">
  				<html:submit property="submitAction" onclick="return validateJuvenileAssociateForm(this.form) && checkForm(this.form) && disableSubmit(this, this.form)"><bean:message key="button.next" /></html:submit>&nbsp; 
          </logic:equal>  	  
		<html:reset/>&nbsp;
<%-- %>		<INPUT type="BUTTON" value="Cancel" onclick="document.location.href='/<msp:webapp /><bean:write name="juvenileWarrantForm" property="backToWarrantUrl" />'" /> --%>
	  </td>
	</tr>
  </table>
<!-- END BUTTON TABLE -->
<%-- BEGIN HIDDEN FIELDS FOR ADDRESS VALIDATION --%>
	<table width=100%>
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
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
