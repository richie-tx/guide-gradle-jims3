<!DOCTYPE HTML>

<%-- Used to display casefile traits details off Traits Tab --%>
<%--MODIFICATIONS --%>
<%-- 06/09/2005		DWilliamson	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>




<head>
<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1"/> 
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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- facilityTraitsCreate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- Javascript for emulated navigation --%>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/facility.js"></script>


<script>
function checkSelectedTraits(theForm)
{
	var parentId = theForm.traitTypeId.value;

	if(parentId == "" || parentId == "Select One")
	{
		alert("Please Select a Trait Type");
		return false;
	}
	theForm.traitTypeId.disabled = false;
	return true;
}

function populateTraitTypeDescription(theForm)
{
	theForm.traitTypeDescriptionId.options.length = 0;
	theForm.traitTypeDescriptionId.options[0] = new Option( "Please Select", "");

	var selectedTraitId="<bean:write name='juvenileTraitsForm' property='traitTypeDescriptionId'/>";
	var parentId = theForm.traitTypeId.value;
	var selectedDescriptionVal = 0;

	if(parentId == "" || parentId == "All")
	{
		theForm.traitTypeDescriptionId.disabled = true;
	}
	else
	{
		theForm.traitTypeDescriptionId.disabled = false;
		<logic:iterate id="iter" name="juvenileTraitsForm" property="descriptionTraitMap">
			
			if(parentId == "<bean:write name='iter' property='key'/>")
			{
				<bean:define id="listOfChilds" name="iter" property="value"/>
	
				<logic:iterate id="childIter" name="listOfChilds"> 
					var tempOption = new Option("<bean:write name="childIter" property="traitName"/>", "<bean:write name="childIter" property="traitTypeId"/>");
					
					theForm.traitTypeDescriptionId.options[theForm.traitTypeDescriptionId.options.length] = tempOption;
					if(selectedTraitId == '<bean:write name="childIter" property="traitTypeId"/>'){
						tempOption.selected = true;
					}
				</logic:iterate>
			}
		</logic:iterate>
	}	
}
</script>
</head>
<%-- <html:javascript formName="juvenileTraitsForm" /> --%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/submitJuvenileFacilityTraits">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|121">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- Facility Create Traits</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
	    <ul>
        <li>Select a Trait Type and Click View to see list of traits, enter Trait Comments, and click Add button to add new trait to list.</li>
        <li>To remove trait from list, check traits to remove then click Remove Selected button to remove trait from list.</li>
        <li>Click Finish button to create Trait(s). </li>
	    </ul>
	  </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class=spacer></div>

<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  <tr>
	<td valign=top align=center>
		<div class=spacer></div>
	    <%-- BEGIN TRAITS TABLE --%>
	    <%-- BEGIN TRAITS TABLE --%>
		<table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
		  <tr>
		  	<td valign=top class=facDetailHead colspan=4><bean:message key="prompt.add" />&nbsp;<bean:message key="prompt.traits" /></td>
		  </tr>
		  <tr>
		  	<td colspan=4 class=bodyText>										
		  		<table width='100%' cellspacing=1 cellpadding=1>
		  			<tr>
		  				<td class=formDeLabel width="18%">
		  					<bean:message key="prompt.2.diamond"/> <bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" />
		  				</td>
		  				<td class=formDe width="82%">
			        	  <html:select name="juvenileTraitsForm" property="traitTypeId" onchange="populateTraitTypeDescription(this.form);" disabled="true">
			          			  <html:optionsCollection name="juvenileTraitsForm" property="rootTraitTypes" value="traitTypeId" label="traitName" />
			        	  </html:select>
		         		 </td>
		       		</tr>
		
		  			<tr>										
		  				<td class=formDeLabel width="18%" nowrap><bean:message key="prompt.2.diamond"/>
		  					<bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" />&nbsp;<bean:message key="prompt.description" />
		  				</td>
		  				<td class=formDe width="82%">
		           			 <html:select name="juvenileTraitsForm" styleId="traitTypeDescriptionId" property="traitTypeDescriptionId" disabled="true"/>
		  				</td>									
		  			</tr>   
					<tr>										
		  				<td class=formDeLabel width="18%" nowrap><bean:message key="prompt.2.diamond"/>
		  					<bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.status" />
		  				</td>
						<td class=formDe width="82%">
				           	<html:select name="juvenileTraitsForm" styleId="statusId" property="statusId">
				           		<html:option value=""><bean:message key="select.generic" /></html:option>
				           		<jims:codetable codeTableName="<%=PDCodeTableConstants.FAMILY_TRAIT_STATUS%>" sort="true"/>
				           	</html:select>
						  </td>									    
					  </tr>   
						<script type='text/javascript'>populateTraitTypeDescription(document.forms[0]);</script>
						<tr>       
			    			<td class=formDeLabel colspan="2">
								 <bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.comments" />&nbsp;
			  					<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
			  						<tiles:put name="tTextField" value="traitComments"/>
			  						<tiles:put name="tSpellCount" value="spellBtn1" />
								</tiles:insert>
									(Max. characters allowed: 255)
							</td>
		  				</tr>
						<tr>     
						    <td class="formDeLabel" colspan="2">
								<html:textarea style="width:100%" property="traitComments" rows='3' onkeyup="textCounter(this.form.traitComments,255)" />
			        		</td>
						</tr>
						<tr>
			  				<td align="center" colspan="2">
			  					<html:submit styleId="addFacilityTraitBtn" property="submitAction"><bean:message key="button.addTrait" /></html:submit>
			  				</td>
		  				</tr>
					</table>
		
					<logic:notEmpty name="juvenileTraitsForm" property="newTraits">
			  			<div class=spacer></div>
			    		<table width='100%' bgcolor='#999999' cellspacing=1>
			    			<tr bgcolor='#cccccc'>
			    				<td class="subhead" valign=top width="0%">&nbsp;</td>
			    				<td class="subhead" valign=top width="14%"><bean:message key="prompt.entryDate" /></td>
			    				<td class="subhead" valign=top width="14%"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" /></td>
			    				<td class="subhead" valign=top width="14%"><bean:message key="prompt.description" /></td>
			    				<td class="subhead" valign=top width="14%"><bean:message key="prompt.traitStatus" /></td>
			    				<td class="subhead" valign=top width="58%">
			    					<bean:message key="prompt.trait" />&nbsp;
			   				    	<bean:message key="prompt.comments" />
			    				</td>
			    			</tr>
			
			  				<logic:iterate id="trait" name="juvenileTraitsForm" property="newTraits" indexId="index">
			   					<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" id = row<bean:write name="trait" property="juvenileTraitId"/>  >
			            		  <td>
					              		<a href="/<msp:webapp/>submitJuvenileFacilityTraits.do?submitAction=Remove&selectedValue=<%=(index.intValue())%>">Remove&nbsp;</a>
			             		 </td>
			             		 	<td valign="top"><bean:write name="trait" property="entryDate" format="MM/dd/yyyy" /></td>
			  						<td nowrap><bean:write name="trait" property="traitTypeName"/>&nbsp;&nbsp;</td>
			  						<td nowrap><bean:write name="trait" property="traitTypeDescription"/>&nbsp;&nbsp;</td>
			  						<td nowrap><bean:write name="trait" property="status"/>&nbsp;&nbsp;</td>
			  						<td><bean:write name="trait" property="traitComments"/></td>
			           			 </tr>
			  				</logic:iterate> 
						</table>
					</logic:notEmpty>
		    </td>
		  </tr>
		</table>
<%-- END TRAITS TABLE --%>
		<div class=spacer></div>
	    <table border="0" cellpadding=1 cellspacing=1 align=center>
	    	<tr>
	          <td align="center">
				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
	     		<html:submit property="submitAction" styleId="facCreateTraitsBtn"><bean:message key="button.finish"/></html:submit>
	     		<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
	      	  </td>
			</tr>
	 	</table> 
		<div class=spacer></div>
	</td>
 </tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form>
</body>
</html:html>

