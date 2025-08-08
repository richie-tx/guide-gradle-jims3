<!DOCTYPE HTML>
<!--This JSP will be used to create, update and create additional addresses.-->
<!--MODIFICATIONS -->
<!-- Hien Rodriguez	06/22/2004	Create JSP -->
<!-- Leslie Deen	02/22/2005	Revise  JSP -->
<!-- C Shimek       03/18/2005  Revised addresses into 3 rows -->
<%-- CShimek        10/04/2005  ER#24012 change assocaite labels to responsible adult --%>
<%-- CShimek        04/12/2006  correct Responsible Adult Name display --%>
<%-- CShimek		12/21/2006  revised helpfile reference value--%>
<%-- CShimek 		03/01/2007	#39097 added multiple submit button logic --%>
<%-- CShimek 		04/12/2007	#41031 removed target=content in form tag to allow page to display in popup window --%>
<%-- LDeen			06/04/2007  #42564 changed path to app.js --%>
<%-- LDeen			02/15/2008	#49255 removed extra </html:form> tag --%>
<%-- RYoung         08/6/2015   #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

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

<title><bean:message key="title.heading" /> - warrantAssociateAddressSummary.jsp</title>
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<!-- app.js JAVASCRIPT LINK -->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<html:base />

</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN FORM -->		
<html:form action="/handleWarrantAssociateSelection">
	<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|208">

<!-- BEGIN HEADING TABLE -->
	<table width="100%">
      <tr>
        <td align="center" class="header"><bean:message key="title.juvWarrantAssociate"/>&nbsp;Addresses Summary</td>
      </tr>
	 </table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Verify that information is correct and select Continue button to view Responsible Adult Summary.</li>
        <li>If any changes are needed, select Back button.</li>
 	  </ul>
 	 </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<br>
<!-- BEGIN RESPONSIBLE ADULT HEADER TABLE -->
<table align="center" width="98%" border="0">
   <tr>
     <td align="right" class="subhead">
	  	<bean:message key="prompt.associateName"/>:&nbsp;
	 </td>
	 <td>
	 	<bean:write name="juvenileAssociateForm" property="associateName.lastName"/>, <bean:write name="juvenileAssociateForm" property="associateName.firstName"/> <bean:write name="juvenileAssociateForm" property="associateName.middleName"/>
 	 </td>
  </tr>
</table>
<!-- END RESPONSIBLE ADULT HEADER TABLE -->
<!-- BEGIN CONTENT TABLE -->
<!-- BEGIN CREATE SECTION -->
<!-- VARIABLES NEEDED FOR DISPLAY -->
<%int RecordCounter = 0; 
  String bgcolor = "";%>
<table width="98%" border="0" cellpadding="2" cellspacing="0" align="center" class="borderTableBlue">
	<tr>
		<td class=detailHead nowrap><bean:message key="prompt.recordsToCreate"/></td>
	</tr>
	<tr bgcolor="#f0f0f0">
		<td>
       		<table width="100%" border="0" cellpadding="2" cellspacing="0">		
				<logic:iterate id="addressIndex" name="juvenileAssociateForm" property="associateAddresses"> 
				<logic:equal name="addressIndex" property="addressId" value="">
                <tr class= <% RecordCounter++;
                      bgcolor = "normalRow";                      
                      if (RecordCounter % 2 == 1)
                          bgcolor = "alternateRow";
                      out.print(bgcolor);  %> >
                 <td><table align="center" width="100%" cellpadding="0" cellspacing="0"> 
					<tr bgcolor="#CCCCCC">
			          <td class="subhead"><bean:message key="prompt.streetNumber"/></td>
			          <td>&nbsp;&nbsp;</td>
			 		  <td class="subhead"><bean:message key="prompt.streetName"/></td>
			 		  <td>&nbsp;&nbsp;</td>
			 		  <td class="subhead"><bean:message key="prompt.streetType"/></td>
			          <td>&nbsp;&nbsp;</td>
					  <td class="subhead"><bean:message key="prompt.apartmentNumber"/></td>
				   </tr>
				   <tr class=<% out.print(bgcolor); %> >
			           <td><bean:write name="addressIndex" property="streetNum"/></td>
			           <td>&nbsp;&nbsp;</td>
			           <td><bean:write name="addressIndex" property="streetName"/></td>
			           <td>&nbsp;&nbsp;</td>
			           <td><bean:write name="addressIndex" property="streetType"/></td>
			           <td>&nbsp;&nbsp;</td>
			           <td><bean:write name="addressIndex" property="aptNum"/></td>
				   </tr>
				   <tr bgcolor="#CCCCCC">	  
			          <td class="subhead"><bean:message key="prompt.city"/></td>
			          <td>&nbsp;&nbsp;</td>
					  <td class="subhead"><bean:message key="prompt.state"/></td>
					  <td>&nbsp;&nbsp;</td>
			          <td class="subhead" colspan="3"><bean:message key="prompt.zipCode"/></td>
			       </tr>
				   <tr class=<% out.print(bgcolor); %> >
			           <td><bean:write name="addressIndex" property="city"/></td>
			           <td>&nbsp;&nbsp;</td>
			           <td><bean:write name="addressIndex" property="state"/></td>
			           <td>&nbsp;&nbsp;</td>           
			           <td colspan="3"><bean:write name="addressIndex" property="zipCode"/>-  
			               <bean:write name="addressIndex" property="additionalZipCode"/></td>
			       </tr>
			       <tr bgcolor="#CCCCCC">  
			          <td class="subhead"><bean:message key="prompt.addressType"/></td>
			   		  <td>&nbsp;&nbsp;</td>
			          <td class="subhead" colspan="5"><bean:message key="prompt.county"/></td>
			       </tr>
				   <tr class=<% out.print(bgcolor); %> >
			           <td><bean:write name="addressIndex" property="addressType"/></td>    
			           <td>&nbsp;&nbsp;</td>
			           <td colspan="5"><bean:write name="addressIndex" property="county"/></td>          
			       </tr>
			   </table>
		      </td>
	         </tr>
	         </logic:equal>
	        </logic:iterate>
           </table>
		</td>
	</tr>
</table>
<!-- END CREATE SECTION -->
<br>
<!-- BEGIN UPDATE SECTION -->
<table width="98%" border="0" cellpadding="2" cellspacing="0" align="center" class="borderTableBlue">
	<tr>
		<td class=detailHead nowrap><bean:message key="prompt.recordsToUpdate"/></td>
	</tr>
	<tr bgcolor="#f0f0f0">
		<td>
       		<table width="100%" border="0" cellpadding="0" cellspacing="0">		
				<logic:iterate id="addressIndex" name="juvenileAssociateForm" property="associateAddresses"> 
				<logic:notEqual name="addressIndex" property="addressId" value="">
                <tr class= <% RecordCounter++;
                      bgcolor = "normalRow";                      
                      if (RecordCounter % 2 == 1)
                          bgcolor = "alternateRow";
                      out.print(bgcolor);  %> >
                 <td><table align="center" width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#CCCCCC">
			          <td class="subhead"><bean:message key="prompt.streetNumber"/></td>
			          <td>&nbsp;&nbsp;</td>
			 		  <td class="subhead"><bean:message key="prompt.streetName"/></td>
			 		  <td>&nbsp;&nbsp;</td>
			 		  <td class="subhead"><bean:message key="prompt.streetType"/></td>
			          <td>&nbsp;&nbsp;</td>
					  <td class="subhead"><bean:message key="prompt.apartmentNumber"/></td>
				   </tr>
				   <tr class=<% out.print(bgcolor); %> >
			           <td><bean:write name="addressIndex" property="streetNum"/></td>
			           <td>&nbsp;&nbsp;</td>
			           <td><bean:write name="addressIndex" property="streetName"/></td>
			           <td>&nbsp;&nbsp;</td>
			           <td><bean:write name="addressIndex" property="streetType"/></td>
			           <td>&nbsp;&nbsp;</td>
			           <td><bean:write name="addressIndex" property="aptNum"/></td>
				   </tr>
				   <tr bgcolor="#CCCCCC">	  
			          <td class="subhead"><bean:message key="prompt.city"/></td>
			          <td>&nbsp;&nbsp;</td>
					  <td class="subhead"><bean:message key="prompt.state"/></td>
					  <td>&nbsp;&nbsp;</td>
			          <td class="subhead" colspan="3"><bean:message key="prompt.zipCode"/></td>
			       </tr>
				   <tr class=<% out.print(bgcolor); %> >
			           <td><bean:write name="addressIndex" property="city"/></td>
			           <td>&nbsp;&nbsp;</td>
			           <td><bean:write name="addressIndex" property="state"/></td>
			           <td>&nbsp;&nbsp;</td>           
			           <td colspan="3"><bean:write name="addressIndex" property="zipCode"/>-  
			               <bean:write name="addressIndex" property="additionalZipCode"/></td>
			       </tr>
			       <tr bgcolor="#CCCCCC">  
			          <td class="subhead"><bean:message key="prompt.addressType"/></td>
			   		  <td>&nbsp;&nbsp;</td>
			          <td class="subhead" colspan="5"><bean:message key="prompt.county"/></td>
			       </tr>
				   <tr class=<% out.print(bgcolor); %> >
			           <td><bean:write name="addressIndex" property="addressType"/></td>    
			           <td>&nbsp;&nbsp;</td>
			           <td colspan="5"><bean:write name="addressIndex" property="county"/></td>          
			       </tr>
			   </table>
		      </td>
	         </tr>
	         </logic:notEqual>
	        </logic:iterate>
           </table>
		</td>
	</tr>
</table>
<!-- END UPDATE SECTION -->
<!-- END CONTENT TABLE -->
<br>
<hr>
<!-- BEGIN BUTTON TABLE -->
<table border="0" align="center">
  <tr>
    <td>
      <html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
	  <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
		  <bean:message key="button.continue"/>
	  </html:submit>
	  </td>
</html:form>	
<!--CLOSE OUT FIRST FORM -->
    <td>
     <!-- 1 button for Create Responsible Adult Address Summary page --> 	
	 <logic:equal name="juvenileAssociateForm" property="action" value="cancelCreate">
		<html:form action="/displayWarrantAssociateCreateUpdate.do?action=create"> 
             <html:submit>
                <bean:message key="button.cancel"></bean:message>
             </html:submit>
        </html:form> 
	  </logic:equal>
      <!-- 1 button for Update Responsible Adult Address Summary page --> 	
	  <logic:equal name="juvenileAssociateForm" property="action" value="cancelUpdate">	
		<html:form action="/displayWarrantAssociateCreateUpdate.do?action=update"> 
             <html:submit>
                <bean:message key="button.cancel"></bean:message>
             </html:submit>
        </html:form> 
	  </logic:equal>
    </td>
  </tr>
</table>
<!-- END BUTTON TABLE -->
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>