<!DOCTYPE HTML>
<!-- Used to display associate details by clicking hyperlink from warrant. -->
<!--MODIFICATIONS -->
<!-- LDeen		11/30/2004	Create JSP -->
<!-- CShimek	08/23/2005  Corrected alignment of buttons when page allows updates --> 
<%-- CShimek	02/02/2006  Add hidden field for helpFile name --%>
<%-- CShimek	02/09/2006  Defect #26003 display fax number and location, just had to uncomment tr --%> 
<%-- CShimek	05/19/2006  #31533 Add DL State and Number --%>
<%-- CShimek	12/21/2006  revised helpfile reference value--%>
<%-- CShimek	03/01/2007	#39097 added multiple submit button logic --%>
<%-- CShimek	04/11/2007  #41031 removed Back To Warrant button and revised buttons to save flow value --%>
<%-- CShimek	04/26/2007  #41475 added script to check window.name and display "Close Window" button accordingly --%>
<%-- CShimek	05/01/2007  #41586 added field and script to update button to set associate number so user goes directly to update page instead of list  --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- CShimek    07/09/2007  #43326 added logic tag to check for warrant number to display create/update buttons (hope this does not break something else) --%>
<%-- RCapestani 11/20/2007  #45649, #45706, #45707 changed ?action=updateList&details=false to ?action=update --%>
<%-- RYoung     08/6/2015   #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%> 

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

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
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading" /> - warrantAssociateDetails.jsp</title>

<!--GENERAL UTILITY JAVASCRIPT FOR THIS PAGE --> 
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
function setAction(action)
{
	document.forms[0].action.value=action;
}
function checkAssocNum()
{
	var aNums = document.getElementsByName("associateNum");
	if (aNums.length == 0)
	{
		alert("No associate number found, can not update this Associate");
		return false;
	}
	if (aNums[0].value == "" || aNums[0].value == null)
	{
		alert("No associate number found, can not update this Associate");
		return false;	
	}	
	return true;
}
</script>
	
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayWarrantAssociateCreateUpdate" target="content">
    <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|81">
	<html:hidden property="action" />
	<!-- BEGIN HEADING TABLE -->
	<table width="100%">
		<tr>
			<td align="center" class="header"><bean:message
				key="title.juvWarrantAssociate" />&nbsp;Details</td>
		</tr>
	</table>
	<!-- END HEADING TABLE -->
	<br>
	<!-- BEGIN ASSOCIATE INFORMATION TABLE -->
			<table cellspacing="1" align=center width=98% cellpadding=4>
				<tr>
					<td class=detailHead colspan=4 nowrap><bean:message key="prompt.associateInfo" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.associateNumber" /></td>
					<td class="formDe" colspan="3">
						<bean:write name="juvenileAssociateForm" property="associateNum" />
					</td>
				</tr>	
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.name" /></td>
					<td class="formDe" colspan="3"><bean:write name="juvenileAssociateForm" property="associateName" /> 
					</td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.relationship" /></td>
					<td class="formDe" colspan="3"><bean:write name="juvenileAssociateForm" property="relationshipToJuvenile" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.ssn" /></td>
					<td class="formDe"><bean:write name="juvenileAssociateForm" property="ssn" /></td>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.dateOfBirth" /></td>
					<td class="formDe"><bean:write name="juvenileAssociateForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.race" /></td>
					<td class="formDe"><bean:write name="juvenileAssociateForm" property="ethnicity" /></td>
					<td class="formDeLabel"><bean:message key="prompt.sex" /></td>
					<td class="formDe"><bean:write name="juvenileAssociateForm" property="sex" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.driversLicenseState" /></td>
					<td class="formDe" colspan=3><bean:write name="juvenileAssociateForm" property="dlState" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.driversLicenseNumber" /></td>
					<td class="formDe" colspan=3><bean:write name="juvenileAssociateForm" property="dlNumber" /></td>
				</tr>				
				<tr><td><br></td></tr>
					<!-- BEGIN CONTACT TABLE -->
				<tr>
					<td class=detailHead colspan=4 nowrap><bean:message key="prompt.contactInfo" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.homePhone"/>&nbsp;</td>
					<td class="formDe"><bean:write name="juvenileAssociateForm" property="homePhoneNum" /></td>
					<td class="formDeLabel">Work Phone</td>
					<td class="formDe"><bean:write name="juvenileAssociateForm" property="workPhoneNum" /></td>
				</tr>
				<tr>
					<td class="formDeLabel">Cell Phone</td>
					<td class="formDe"><bean:write name="juvenileAssociateForm" property="cellPhoneNum" /></td>
					<td class="formDeLabel">Pager</td>
					<td class="formDe"><bean:write name="juvenileAssociateForm" property="pager" /></td>
				</tr>
				<tr>
					<td class="formDeLabel">Fax Number</td>
					<td class="formDe"><bean:write name="juvenileAssociateForm" property="faxNum" /></td>
					<td class="formDeLabel">Fax Location</td>
					<td class="formDe"><bean:write name="juvenileAssociateForm" property="faxLocation" /></td>
				</tr>
				<tr>
					<td class="formDeLabel">Email 1</td>
					<td colspan="3" class="formDe"><bean:write name="juvenileAssociateForm" property="email1" /></td>
				</tr>
				<tr>
					<td class="formDeLabel">Email 2</td>
					<td colspan="3" class="formDe"><bean:write name="juvenileAssociateForm" property="email2" /></td>
				</tr>
				<tr>
					<td class="formDeLabel">Email 3</td>
					<td colspan="3" class="formDe"><bean:write name="juvenileAssociateForm" property="email3" /></td>
				</tr>
				<!-- END CONTACT TABLE -->
				<tr><td><br></td></tr>
				<!-- BEGIN ASSOCIATE ADDRESS INFORMATION TABLE -->
				<tr>
					<td colspan=4 class=detailHead nowrap><bean:message key="prompt.associateAddress" /> Information</td>
				</tr>
				<logic:iterate id="address" name="juvenileAssociateForm" property="associateAddresses"> 
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.address" /></td>
					<td class="formDe"><bean:write name="address" property="streetNum" />
						<bean:write name="address" property="streetName" />
						<bean:write name="address" property="streetType" />
						<bean:write name="address" property="aptNum" /></td>
					<td class="formDeLabel"><bean:message key="prompt.type" /></td>
					<td class="formDe"><bean:write name="address"
						property="addressType" /></td>
				</tr>
				<tr>
					<td class="formDeLabel">&nbsp;&nbsp;&nbsp;</td>
					<td class="formDe" colspan="4"><bean:write name="address"
						property="city" /> <bean:write name="address"
						property="state" /> <bean:write name="address"
						property="zipCode" /> <bean:write name="address"
						property="additionalZipCode" /></td>
				</tr>
				</logic:iterate>
	<!-- END ASSOCIATE ADDRESS INFORMATION TABLE -->
			</table>			
	<!-- END ASSOCIATE INFORMATION TABLE -->
	<br>

</html:form>
<!-- END FIRST CLOSE TAG -->	
<!-- BEGIN BUTTON TABLE -->
<table align="center" width="60%" cellpadding=0>
<!-- 
   	<tr>
    <logic:notEmpty name="juvenileWarrantForm" property="associates"> 
    <logic:notEqual name="juvenileWarrantForm" property="warrantNum" value="">
	 	<td valign="top" align="center">
        	<jims2:isAllowed requiredFeatures="JW-MNG-ASSOC"> 
         	<html:form action="/displayWarrantAssociateCreateUpdate.do?action=create&details=false">
 	   			<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
					<bean:message key="button.createAssociate"></bean:message>
				</html:submit>
		       <script type="text/javascript">
			       	document.write("<input type='hidden' name='flowInd' value=" + window.name + ">");
		       </script>				
			</html:form>
    	   	</jims2:isAllowed> 
	 	</td> 
 	    <td valign="top" align="center">
       <jims2:isAllowed requiredFeatures="JW-MNG-ASSOC"> 
		 	<html:form action="/displayWarrantAssociateCreateUpdate.do?action=update">
	    	   <html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
	        	<bean:message key="button.updateAssociate"></bean:message>
		       </html:submit>
		       <script type="text/javascript">
			       	document.write("<input type='hidden' name='flowInd' value=" + window.name + ">");
		       </script>		       
			</html:form>
         </jims2:isAllowed> 
	    </td>
	  </logic:notEqual>  
	 </logic:notEmpty> 
   
     <logic:empty name="juvenileWarrantForm" property="associates"> 
	 	<td valign="top" align="center" colspan="2">
        <jims2:isAllowed requiredFeatures="JW-MNG-ASSOC"> 
			<html:form action="/displayWarrantAssociateCreateUpdate.do?action=create&details=false">
 	   			<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
					<bean:message key="button.createAssociate"></bean:message>
				</html:submit>
			    <script type="text/javascript">
			       	document.write("<input type='hidden' name='flowInd' value=" + window.name + ">");
			    </script>				
			</html:form>
        </jims2:isAllowed>
	 	</td>	
	 </logic:empty>  	 
   </tr>
 -->	
   <tr>
	 <td valign="top" align="center" colspan="2">
	 	<script type="text/javascript">
            	var windowName = window.name;
            	if (windowName != "content"){
            		document.write("<input type='button' name='closeWindow' value='Close Window' onclick='window.close()'>");
            	}
            </script>
      </td>
    </tr>
</table>
<!-- END BUTTON TABLE -->
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
