<!DOCTYPE HTML>
<%-- Used to display juvenile trait details for trait status updates in Casefile and Juvenile Profile --%>
<%--MODIFICATIONS --%>
<%-- 09/16/2013		CShimek	ER 75751 Create page --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>



<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- juvenileTraitStatusUpdate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript'>
function validateSelect()
{
	var fld= document.getElementById("statSel");
	if (fld.selectedIndex == 0)
	{
		alert("New Trait Status selection is required.");
		fld.focus();
		return false;
	}
	
	if ( true ) {
		spinner();
	}
	return true;
}
</script>
</head>

<bean:define id="juvenileNumberDef" name="juvenileTraitsForm" property="juvenileNumber"/>

<%-- BEGIN TRAITS TABLE --%>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
	<tr>
		<td valign="top" class="detailHead" ><bean:message key="prompt.update" />&nbsp;<bean:message key="prompt.trait" /></td>
	</tr>
	<tr>
		<td colspan="4" class="bodyText">										
	  		<table width='100%' cellspacing='1' cellpadding='1'>
	  			<logic:iterate id="tIndx" name="juvenileTraitsForm" property="updateTraitsList" >
		  			<tr>
		  				<td class="formDeLabel">
		  					<bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" />
		  				</td>
		  				<td class="formDe"><bean:write name="tIndx" property="traitTypeName" /></td>
		        	</tr>
		  			<tr>										
		  				<td class="formDeLabel" width="1%" nowrap="nowrap">
		  					<bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" />&nbsp;<bean:message key="prompt.description" />
		  				</td>
		  				<td class="formDe"><bean:write name="tIndx" property="traitTypeDescription" /></td>									
		  			</tr>  
		  			<tr>										
		  				<td class="formDeLabel">
		  					<bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.status" />
		  				</td> 
		  				<td class="formDe"><bean:write name="tIndx" property="status" /></td>
					<tr>										
		  				<td class="formDeLabel" ><bean:message key="prompt.2.diamond"/>
		  					<bean:message key="prompt.new" /> <bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.status" />
		  				</td>
						<td class="formDe">
		           			<html:select name="juvenileTraitsForm" property="newStatusId" styleId="statSel">
		           				<html:option value=""><bean:message key="select.generic" /></html:option>
		           		  		<html:optionsCollection property="updateTraitStatuses" value="code" label="description"/>	
		           			</html:select>
						</td>									    
					</tr>  
					<tr>
						<td class="formDeLabel" ><bean:message key="prompt.info" />&nbsp;<bean:message key="prompt.source" /></td>
						<td class="formDe"> <bean:write name="tIndx" property="informationSrcDesc"/></td>
					</tr> 
					<tr>       
		    			<td class="formDeLabel" colspan="2"> 
		    				<bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.comments" />
						</td>
		  			</tr>
					<tr>     
					    <td class="formDe" colspan="2">
					    	<bean:write name="tIndx" property="traitComments" />
			        	</td>
					</tr>
				</logic:iterate>
				<logic:empty  name="juvenileTraitsForm" property="updateTraitsList" >
					<tr>
						<td>No active casefile found, trait status cannot be updated.</td>
					</tr>
				</logic:empty>	
			</table>
		</td>
	</tr>
</table>			
<div class="spacer"></div>		         
<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
			<logic:notEmpty  name="juvenileTraitsForm" property="updateTraitsList" >
				<html:submit property="submitAction" onclick="return validateSelect() && disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
			</logic:notEmpty>
			<logic:empty  name="juvenileTraitsForm" property="updateTraitsList" >
				<input onclick="spinner()" type="button" name="finishBtn" value=<bean:message key="button.finish"/> disabled />
			</logic:empty>
			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		</td>
	</tr>
</table>
 <%-- END BUTTON TABLE --%>
</html:html>