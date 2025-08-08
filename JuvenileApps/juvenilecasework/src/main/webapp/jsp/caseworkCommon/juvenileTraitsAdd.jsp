<!DOCTYPE HTML>

<%-- Used to display traits details off Traits Tab --%>
<%--MODIFICATIONS --%>
<%-- 06/13/2005		DWilliamson	Create tile --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>


<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>


<html:javascript formName="juvenileTraitsForm" />

<msp:nocache />
<%-- ensures the user is logged in. --%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<title><bean:message key="title.heading"/>Juvenile Casework - juvenileTraitsAdd.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/traits.js"></script>

<script type='text/javascript'>
function checkSelectedTraits(theForm)
{
	var parentId = theForm.traitTypeId.value;
	if(parentId == "" || parentId == "Select One")
	{
		alert("Please Select a Trait Type");
		return false;
	}
	theForm.traitTypeId.disabled = false;
	
	/* if ( true ) {
		spinner();
	} */
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

			
<%-- BEGIN TRAITS TABLE --%>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
  <tr>
  	<td valign=top class=detailHead colspan=4><bean:message key="prompt.add" />&nbsp;<bean:message key="prompt.traits" /></td>
  </tr>
  <tr>
  	<td colspan=4 class=bodyText>										
  		<table width='100%' cellspacing=1 cellpadding=1>
  			<tr>
  				<td>
	  				<td class=formDeLabel width="18%">
	  					<bean:message key="prompt.2.diamond"/> <bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" />
	  				</td>
	  				<td class=formDe width="40%">
	  					<bean:size id="moreThanOne" name="juvenileTraitsForm" property="rootTraitTypes"/>
	
	  					<logic:greaterThan name="moreThanOne" value="1">
	        		  <html:select name="juvenileTraitsForm" property="traitTypeId" styleId="traitTypeIdAdd" onchange="populateTraitTypeDescription(this.form);">
	         		  	<html:option value=""><bean:message key="select.generic" /></html:option>
	          		  <html:optionsCollection name="juvenileTraitsForm" property="rootTraitTypes" value="traitTypeId" label="traitName" />
	        		  </html:select>
	      		  </logic:greaterThan>
	
	      		  <logic:lessEqual name="moreThanOne" value="1">
	        		  <html:select name="juvenileTraitsForm" property="traitTypeId" styleId="traitTypeIdAdd" onchange="populateTraitTypeDescription(this.form);" disabled="true">
	          		  <html:optionsCollection name="juvenileTraitsForm" property="rootTraitTypes" value="traitTypeId" label="traitName" />
	        		  </html:select>
	      		  </logic:lessEqual>
	      		
	          </td>
		        <td class=formDeLabel width="18%" nowrap>
	  					<bean:message key="prompt.2.diamond"/> <bean:message key="prompt.info" />&nbsp;<bean:message key="prompt.source" />
	  				</td>
		       <td class="formDe" width="40%">
		       	  <html:select name="juvenileTraitsForm" property="informationSrcCd" styleId="informationSrcCd" >
		       	  	  <html:option value="">Please Select</html:option>
	          		  <html:optionsCollection name="juvenileTraitsForm" property="informationSources" value="code" label="descriptionWithCode"/>
	        	  </html:select>
				
				</td>
			
        </tr>
        </table>
		<table width='100%' cellspacing=1 cellpadding=1>
  			<tr>										
  				<td class=formDeLabel width="18%" nowrap><bean:message key="prompt.2.diamond"/>
  					<bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" />&nbsp;<bean:message key="prompt.description" />
  				</td>
  				<td class=formDe width="82%">
            <html:select name="juvenileTraitsForm" property="traitTypeDescriptionId" disabled="true"/>
  				</td>									
  			</tr>   
				<tr>										
  				<td class=formDeLabel width="18%" nowrap><bean:message key="prompt.2.diamond"/>
  					<bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.status" />
  				</td>
					<td class=formDe width="82%">
           	<html:select name="juvenileTraitsForm" property="statusId">
           		<html:option value=""><bean:message key="select.generic" /></html:option>
           		  <jims:codetable codeTableName="<%=PDCodeTableConstants.FAMILY_TRAIT_STATUS%>" sort="true"/>
           	</html:select>
				  </td>									    
			  </tr>   
				<script type='text/javascript'>populateTraitTypeDescription(document.forms[0]);</script>
				    
				<tr>       
    			<td class=formDeLabel colspan="2">
					  <bean:message key="prompt.2.diamond"/> <bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.comments" />&nbsp;
  					<tiles:insert page="spellCheckTile.jsp" flush="false">
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
  					<html:submit property="submitAction" onclick="return (checkSelectedTraits(this.form)&& validateJuvenileTraitsForm(this.form))">
             			<bean:message key="button.addTrait" />
           			</html:submit>
  				</td>
  			</tr>
			</table>

			<logic:notEmpty name="juvenileTraitsForm" property="newTraits">
  			<div class=spacer></div>
    		<table width='100%' bgcolor='#999999' cellspacing=1>
    			<tr bgcolor='#cccccc'>
    				<td class="subhead" valign=top width="0%">&nbsp;</td>
    				<td class="subhead" valign=top width="14%" align="left"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" /></td>
    				<td class="subhead" valign=top width="14%" align="left"><bean:message key="prompt.description" /></td>
    				<td class="subhead" valign=top width="14%" align="left"><bean:message key="prompt.traitStatus" /></td>
    				<td class="subhead" valign=top width="1%" align="left"><bean:message key="prompt.info" /> <bean:message key="prompt.source" /></td>
    				<td class="subhead" valign=top width="58%" align="left">
    					<bean:message key="prompt.trait" />&nbsp;
   				    <bean:message key="prompt.comments" />
    				</td>
    			</tr>

  				<logic:iterate id="trait" name="juvenileTraitsForm" property="newTraits" indexId="index">
   					<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" id = row<bean:write name="trait" property="juvenileTraitId"/>  >
              <td align="left">
              	<logic:equal name="juvenileTraitsForm" property="cameFromCasefile" value="true">
              		<a href="/<msp:webapp/>submitJuvenileCasefileTraits.do?submitAction=Remove&selectedValue=<%=(index.intValue())%>">Remove&nbsp;</a>
             	</logic:equal>
              
              	<logic:equal name="juvenileTraitsForm" property="cameFromCasefile" value="false">
              		<a href="/<msp:webapp/>submitJuvenileProfileTraits.do?submitAction=Remove&selectedValue=<%=(index.intValue())%>">Remove&nbsp;</a>
              	</logic:equal>
              
              </td>
  						<td nowrap align="left"><bean:write name="trait" property="traitTypeName"/>&nbsp;&nbsp;</td>
  						<td nowrap align="left"><bean:write name="trait" property="traitTypeDescription"/>&nbsp;&nbsp;</td>
  						<td nowrap align="left"><bean:write name="trait" property="status"/>&nbsp;&nbsp;</td>
  						<td align="left"><span title='<bean:write name="trait" property="informationSrcDesc"/>'><bean:write name="trait" property="informationSrcCd"/></span></td>
  						<td align="left"><bean:write name="trait" property="traitComments"/></td>
            </tr>
  				</logic:iterate> 
  				
				</table>
			</logic:notEmpty>
    </td>
  </tr>
</table>
<%-- END TRAITS TABLE --%>
				
