<!DOCTYPE HTML>

<%-- Used to display detention traits in Facility --%>
<%--MODIFICATIONS --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>

<head>
	<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
</head>

<%-- BEGIN TRAITS TABLE --%>
<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	<tr>
		<td valign="top" colspan="6" class="detailHead">
 			
			<bean:message key="prompt.detention" /> <bean:message key="prompt.trait" /> <bean:message key="prompt.information" />
 		</td>
	</tr>
	<logic:equal name="admitReleaseForm" property="action" value="create">
	<tr>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" /></td>
		<td class="formDe" width="40%">
			
				<html:select name="admitReleaseForm" property="detentionTrait.traitTypeId" disabled="true">
					<html:option value=""><bean:message key="select.generic" /></html:option>
					<html:optionsCollection name="admitReleaseForm" property="detentionTrait.rootTraitTypes" value="traitTypeId" label="traitName" />
					<html:option value="all"><bean:message key="select.all" /></html:option>
				</html:select>		
		
		</td>
		<td class="formDeLabel" width="1%" nowrap="nowrap" valign="top"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.traitTypeDescription" /></td>
		<td class="formDe" width="20%">
			<html:select name="admitReleaseForm" property="detentionTrait.traitTypeDescriptionId">
				<html:option value=""><bean:message key="select.generic" /></html:option>
					<html:optionsCollection name="admitReleaseForm" property="detentionTraitDescriptions" value="traitTypeId" label="traitName" />
					
			</html:select>			
		</td>
		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.2.diamond"/>
  					<bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.status" />
  				</td>
		<td class=formDe>
           	<html:select name="admitReleaseForm" property="detentionTrait.statusId">
           		<html:option value=""><bean:message key="select.generic" /></html:option>
           		  <jims:codetable codeTableName="<%=PDCodeTableConstants.FAMILY_TRAIT_STATUS%>" sort="true"/>
           	</html:select>
		</td>								
	</tr>
	<tr>       
 		<td class=formDeLabel colspan="6">
		  <bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.comments" />&nbsp;
					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
					<tiles:put name="tTextField" value="detentionTrait.traitComments"/>
					<tiles:put name="tSpellCount" value="spellBtn1" />
			</tiles:insert>
			(Max. characters allowed: 255)
		</td>
		</tr>
	<tr>     
    <td class="formDeLabel" colspan="6">
			<html:textarea style="width:100%" property="detentionTrait.traitComments" rows='3' onkeyup="textCounter(this.form.detentionTrait.traitComments,255)" />
       </td>
	</tr>
	<tr>
		<td align="center" colspan="6">
			<html:submit property="submitAction" onclick="return checkTraitSelection();">
         	<bean:message key="button.addTrait" />
        </html:submit>
		</td>
	</tr>
	</logic:equal>
	
	<logic:notEmpty name="admitReleaseForm" property="detentionTrait.newTraits">
		<div class=spacer></div>
		<tr>
			<td colspan="6">
	  		<table width='100%' bgcolor='#999999' cellspacing=1>
	  			<tr bgcolor='#cccccc'>
	  				<logic:equal name="admitReleaseForm" property="action" value="create">
	  					<td class="subhead" valign=top width="0%">&nbsp;</td>
	  				</logic:equal>
	  				 <logic:notEqual name="admitReleaseForm" property="action" value="create">						         	
						<td class="subhead" valign="top" nowrap><bean:message key="prompt.entryDate" /></td>
			     </logic:notEqual>
	  				<td class="subhead" valign=top width="14%"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" /></td>
	  				<td class="subhead" valign=top width="14%"><bean:message key="prompt.description" /></td>
	  				<td class="subhead" valign=top width="14%"><bean:message key="prompt.traitStatus" /></td>
	  				<td class="subhead" valign=top width="58%">
	  					<bean:message key="prompt.trait" />&nbsp;
	 				    <bean:message key="prompt.comments" />
	  				</td>
	  			</tr>
	
					<logic:iterate id="trait" name="admitReleaseForm" property="detentionTrait.newTraits" indexId="index">
	 					<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" id = row<bean:write name="trait" property="juvenileTraitId"/>  >
	 						<logic:equal name="admitReleaseForm" property="action" value="create">
			              <td>         	
			              		<a href="/<msp:webapp/>displayJuvenileFacilityAdmitSummary.do?submitAction=Remove Selected&selectedValue=<%=(index.intValue())%>">Remove&nbsp;</a>              
			              </td>
			         </logic:equal>
			         <logic:notEqual name="admitReleaseForm" property="action" value="create">
			         	<td valign="top"><bean:write name="admitReleaseForm" property="detentionTrait.entryDate" format="MM/dd/yyyy" /></td>
			         </logic:notEqual>
	  						<td nowrap><bean:write name="trait" property="traitTypeName"/>&nbsp;&nbsp;</td>
	  						<td nowrap><bean:write name="trait" property="traitTypeDescription"/>&nbsp;&nbsp;</td>
	  						<td nowrap><bean:write name="trait" property="status"/>&nbsp;&nbsp;</td>
	  						<td><bean:write name="trait" property="traitComments"/></td>
	            </tr>
					</logic:iterate> 
					
			</table>
			</td>
		</tr>
	</logic:notEmpty>
	
	
</table>	
	
<%-- END TRAITS TABLE --%>



