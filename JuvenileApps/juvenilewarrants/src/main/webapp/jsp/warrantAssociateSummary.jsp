<!DOCTYPE HTML>
<!--This JSP will be used to create, update and create additional addresses.-->
<!--MODIFICATIONS -->
<!-- Hien Rodriguez	06/22/2004	Create JSP -->
<!-- CShimek        03/18/2005  Revised addresses into 3 rows -->
<!-- CShimek        08/08/2005  Revised to display new look and feel --> 
<%-- CShimek        10/04/2005  ER#24012 change assocaite labels to responsible adult --%>
<%-- CShimek        04/12/2006  Updated helpfile reference, no mapId available at this time --%>
<%-- CShimek		12/21/2006  revised helpfile reference value--%>
<%-- CShimek		01/31/2007  #39097 added multiple submit button logic --%>
<%-- CShimek		04/11/2007  #41031 removed Back To Warrant button and target=content in form tag so page works in new window --%>
<%-- LDeen			06/04/2007  #42564 changed path to app.js --%>
<%-- CShimek		08/31/2007  #44943 revised Update to Finish button when page used for Update --%>
<%-- RYoung         08/6/2015   #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<title><bean:message key="title.heading" /> - warrantAssociateSummary.jsp</title>
<!-- app.js JAVASCRIPT LINK -->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<html:base />

</head>
<!--END HEADER TAG-->

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN FORM -->		
<html:form action="/submitWarrantAssociateCreateUpdate">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|167">

<!-- BEGIN HEADING TABLE -->
<table width=100%>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvWarrantAssociate"/>&nbsp;<bean:message key="prompt.summary"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table width=98% border=0 cellspacing=1 cellpadding=2 align=center>
	<tr>
	  	  <td align=center class="subhead"><bean:message key="prompt.warrantNumber"/>: <span class=warrantNumber><bean:write name="juvenileWarrantForm" property="warrantNum"/></span></td>
    </tr>
   <tr>
     <td>
   		<ul>
   		<logic:equal name="juvenileAssociateForm" property="action" value="create">
		  <li>The following Responsible Adult record will be created when you select the Finish button.</li>
		</logic:equal>  
		<logic:equal name="juvenileAssociateForm" property="action" value="update">
		  <li>Verify that information is correct and select Finish button to update Responsible Adult.</li>
		  <li>If any changes are needed, select Back button.</li>
		</logic:equal>  
		</ul>
	 </td>
   </tr>	 	
 </table>
<!-- END INSTRUCTION TABLE -->

<%-- BEGIN ASSOCIATE NAME INFO BLOCK --%>
<table width="98%" border="0" cellpadding="4" cellspacing="1" align="center">
	<tr>
		<td class="detailHead" colspan=4><bean:message key="prompt.juvenileAssociateInfo"/></td>
	</tr>
	<logic:equal name="juvenileAssociateForm" property="action" value="update">
		<tr>	
			<td class=formDeLabel width=1% nowrap><bean:message key="prompt.associateNumber"/></td>
			<td class=formDe colspan=3><bean:write name="juvenileAssociateForm" property="associateNum"/></td>
		</tr>
	</logic:equal>		
	<tr>
		<td class=formDeLabel><bean:message key="prompt.name"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileAssociateForm" property="associateName.lastName"/>,&nbsp; 
			<bean:write name="juvenileAssociateForm" property="associateName.firstName"/>&nbsp;
			<bean:write name="juvenileAssociateForm" property="associateName.middleName"/>
		</td>
	</tr> 
	<tr>
		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.relationshipToJuvenile"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileAssociateForm" property="relationshipToJuvenile"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.race"/></td>
		<td class=formDe><bean:write name="juvenileAssociateForm" property="race"/></td>
		<td class=formDeLabel><bean:message key="prompt.sex"/></td>
		<td class=formDe><bean:write name="juvenileAssociateForm" property="sex"/></td>
	</tr>
	<tr>	
		<td class=formDeLabel><bean:message key="prompt.ssn"/></td>
		<td class=formDe><bean:write name="juvenileAssociateForm" property="ssn.formattedSSN"/></td>
		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.dateOfBirth"/></td>
		<td class=formDe><bean:write name="juvenileAssociateForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
	</tr>
	<tr>
		<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.driversLicenseState" /></td>
		<td class="formDe" colspan=3><bean:write name="juvenileAssociateForm" property="dlState" /></td>
	</tr>
	<tr>
		<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.driversLicenseNumber" /></td>
		<td class="formDe" colspan=3><bean:write name="juvenileAssociateForm" property="dlNumber" /></td>
	</tr>				
<%-- END ASSOCIATE NAME INFO BLOCK --%>
	<tr><td><br></td></tr>
<!-- BEGIN CONTACT BLOCK -->
	<tr>
		<td class=detailHead colspan=4><bean:message key="prompt.contactInfo"/></td>
	</tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.homePhone"/></td>
		<td class=formDe><bean:write name="juvenileAssociateForm" property="homePhoneNum" /></td>
		<td class=formDeLabel nowrap><bean:message key="prompt.workPhone"/></td>
		<td class=formDe><bean:write name="juvenileAssociateForm" property="workPhoneNum" /></td>
	</tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.cellPhone"/></td>
		<td class=formDe><bean:write name="juvenileAssociateForm" property="cellPhoneNum" /></td>
		<td class=formDeLabel nowrap><bean:message key="prompt.pager"/></td>
		<td class=formDe><bean:write name="juvenileAssociateForm" property="pager" /></td>
	</tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.faxNumber"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileAssociateForm" property="faxNum" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.faxLocation"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileAssociateForm" property="faxLocation" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.email"/>1</td>
		<td class=formDe colspan=3><bean:write name="juvenileAssociateForm" property="email1"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.email"/>2</td>
		<td class=formDe colspan=3><bean:write name="juvenileAssociateForm" property="email2"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.email"/>3</td>
		<td class=formDe colspan=3><bean:write name="juvenileAssociateForm" property="email3"/></td>
	</tr> 
</table>
<!-- END CONTACT BLOCK -->
<tr><td><br></td></tr>
<%-- BEGIN ASSOCIATE ADDRESS INFO TABLES --%>
<%-- this table will need "LOOP" to show mulitple addresses --%>
<% int AddrCounter = 0; %>
<logic:iterate id="address" name="juvenileAssociateForm" property="associateAddresses"> 
	<table width="98%" border="0" cellpadding="4" cellspacing="1" align="center">
		<tr>
			<td class="detailHead" colspan=4><bean:message key="prompt.associateAddressInfo"/>&nbsp;<% AddrCounter++; out.print(AddrCounter);  %></td>
		</tr>
		<tr>
			<td class=formDeLabel><bean:message key="prompt.streetNumber"/></td>
			<td class=formDeLabel><bean:message key="prompt.streetName"/></td>
			<td class=formDeLabel><bean:message key="prompt.streetType"/></td>
			<td class=formDeLabel><bean:message key="prompt.apartmentNumber"/></td>
		</tr>
		<tr>
			<td class=formDe ><bean:write name="address" property="streetNum"/></td>
			<td class=formDe ><bean:write name="address" property="streetName"/></td>
			<td class=formDe ><bean:write name="address" property="streetType"/></td>
			<td class=formDe ><bean:write name="address" property="aptNum"/></td>
		</tr>
		<tr>	  
			<td class=formDeLabel><bean:message key="prompt.city"/></td>
			<td class=formDeLabel><bean:message key="prompt.state"/></td>
			<td class=formDeLabel colspan=2"><bean:message key="prompt.zipCode"/></td>
		</tr>
		<tr>
			<td class=formDe ><bean:write name="address" property="city"/></td>
			<td class=formDe ><bean:write name="address" property="state"/></td>
			<td class=formDe colspan=2><bean:write name="address" property="zipCode"/>-  
				<bean:write name="address" property="additionalZipCode"/></td>
		</tr>
		<tr>  
			<td class=formDeLabel><bean:message key="prompt.addressType"/></td>
			<td class=formDeLabel colspan=3><bean:message key="prompt.county"/></td>
		</tr>
		<tr>
			<td class=formDe>
				<logic:equal name="address" property="addressType" value="">&nbsp;</logic:equal>
			     <bean:write name="address" property="addressType"/>
			</td>    
			<td class=formDe colspan=3>
				<bean:write name="address" property="county"/>
			</td>    
		</tr>
	</table>
</logic:iterate>
<!-- END ASSOCIATE ADDRESS INFO TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table border="0" align="center">
  <tr>
    <td valign="top">
      	  <html:submit property="submitAction"><bean:message key="button.back" /></html:submit>&nbsp;
	</td>  
	<td valign="top">
	  <logic:equal name="juvenileAssociateForm" property="action" value="create">
  			<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>&nbsp;
  	  </logic:equal>
	  <logic:equal name="juvenileAssociateForm" property="action" value="update">
  			<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>&nbsp; 
  	  </logic:equal>
  	  </td>
<%-- commented out for activity 41031   	  
  	  <td valign="top">
  	  	<INPUT type="BUTTON" value="Cancel" onclick="document.location.href='/<msp:webapp /><bean:write name="juvenileWarrantForm" property="backToWarrantUrl" />'" />
     </td> --%>
  </tr>
</table>
<!-- END BUTTON TABLE -->
<script type="text/javascript">
   	document.write("<input type='hidden' name='flowInd' value=" + window.name + ">");
</script>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>