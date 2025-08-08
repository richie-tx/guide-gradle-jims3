<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>

<tiles:importAttribute name="source" ignore="true"/>

<head>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/facility.js"></script>
<title><bean:message key="title.heading" />Juvenile Casework - facilityTraitsSearch.jsp</title>
<script type="text/javascript">
function checkSelectedTraits(theForm)
{
	
	var parentId = theForm.traitTypeId.value;
	if(parentId == "" || parentId == "Please Select")
	{
		alert("Please Select a Trait Type");
		return false;
	}
	
	//theForm.traitTypeId.disabled = false;
	
	if ( true ) {
		spinner();
	}

	return true;
}

function populateTraitTypeDescription(theForm)
{
	theForm.traitTypeDescriptionId.options.length = 0;
	theForm.traitTypeDescriptionId.options[0] = new Option( "All", "");
		
	var selectedTraitId = "<bean:write name='juvenileTraitsForm' property='traitTypeDescriptionId'/>";
	var parentId = theForm.traitTypeId.value;
	var selectedDescriptionVal = 0;

	if( parentId == "" || parentId == "all")
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
					var tempOption = new Option("<bean:write name='childIter' property='traitName' filter='false'/>", "<bean:write name="childIter" property="traitTypeId"/>");
					
					theForm.traitTypeDescriptionId.options[theForm.traitTypeDescriptionId.options.length] = tempOption;
					if(selectedTraitId == '<bean:write name="childIter" property="traitTypeId"/>')
					{
						tempOption.selected = true;
					}
				</logic:iterate>
			}
		</logic:iterate>
	}
}
</script>

</head>

<bean:define id="actionPath">
	<tiles:getAsString name="actionPath" />
</bean:define>


<html:form action="<%=actionPath%>">

<html:hidden property="changeSelection" value='false' />
<bean:size id="moreThanOne" name="juvenileTraitsForm" property="rootTraitTypes" />


<%-- BEGIN TRAITS TABLE --%>
<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	<tr class='facDetailHead'>
		<td align='left' colspan='8' class='paddedFourPix'>Facility Traits</td>
	</tr>
	<tr>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" /></td>
		<td class="formDe">
				<html:select name="juvenileTraitsForm" property="traitTypeId" onchange="this.form.changeSelection.value='true';populateTraitTypeDescription(this.form);" disabled="true">
					<html:optionsCollection name="juvenileTraitsForm" property="rootTraitTypes" value="traitTypeId" label="traitName" />
				</html:select>
		</td>
	</tr>

	<tr>
		<td class="formDeLabel" width="1%" nowrap="nowrap" valign="top"><bean:message key="prompt.traitTypeDescription" /></td>
		<td class="formDe">
			<html:select name="juvenileTraitsForm" property="traitTypeDescriptionId">
				<html:option value=""><bean:message key="prompt.selectAll" /></html:option>
			</html:select>
			<script type="text/javascript">populateTraitTypeDescription(document["juvenileTraitsForm"]);</script>
			<br /><html:submit property="submitAction" onclick="return checkSelectedTraits(this.form)"><bean:message key="button.viewTraits" /></html:submit>
		</td>
	</tr>

	<tr>
		<td valign="top" colspan="2">
			<logic:empty name="juvenileTraitsForm" property="traitsSearchResult">
				<table width="100%" cellpadding="4" cellspacing="1" bgcolor="#999999">
					<tr class="normalRow">
						<td colspan="5" align="left">
							<logic:empty name="juvenileTraitsForm" property="traitTypeId">
								<logic:greaterThan name="moreThanOne" value="1">Select a trait type.</logic:greaterThan>
								<logic:lessEqual name="moreThanOne" value="1">Select a Trait Type Description or click View Traits.</logic:lessEqual>
							</logic:empty>
							
							<logic:notEmpty name="juvenileTraitsForm"	property="traitTypeId">
								<logic:notEmpty name="juvenileTraitsForm" property="traitTypeDescriptionId">
									<bean:write name="juvenileTraitsForm" property="traitType" />: 
									<bean:write name="juvenileTraitsForm" property="traitTypeDescription" /> traits do not exist for this juvenile.
								</logic:notEmpty>
	
								<logic:empty name="juvenileTraitsForm" property="traitTypeDescriptionId">
									<bean:write name="juvenileTraitsForm" property="traitType" /> traits do not exist for this juvenile.
								</logic:empty>
						  </logic:notEmpty>
					  </td>
				  </tr>
				 </table>
		  </logic:empty>
		<logic:notEmpty name="juvenileTraitsForm" property="traitsSearchResult">
				<%-- Begin Pagination Header Tag --%>
				<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define>
				<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
				<%-- End Pagination header stuff --%>

				<table width="100%" cellpadding="2" cellspacing="1" class="borderTableGrey">
					<tr bgcolor="#cccccc">
						<td class="subhead" valign="top">&nbsp;</td>
						<td class="subhead" valign="top"><bean:message key="prompt.entryDate" /></td>
						<td class="subhead" valign="top"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" /></td>
						<td class="subhead" valign="top"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.description" /></td>
						<td class="subhead" valign="top" width="20%"><bean:message key="prompt.traitStatus" />&nbsp;</td>
						<td class="subhead" valign="top"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.comments" /></td>
					</tr>

					<logic:iterate indexId="indexer" id="t" name="juvenileTraitsForm" property="traitsSearchResult">
            			<pg:item>
			            	<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
			            		<td valign="top">
				            		<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
				            			<logic:equal name="juvenileTraitsForm" property="activeCasefileFound" value="true" >
					            			<logic:equal name="t" property="statusId" value="CU">
					            				<logic:notEqual name="juvenileTraitsForm" property="isReleaseView" value="true">
					            					<input type="radio" name="selectedValue" value='<bean:write name="t" property="juvenileTraitId"/>' styleId="<bean:write name="t" property="juvenileTraitId"/>-traitId">
					            				</logic:notEqual>
					            			</logic:equal>
					            		</logic:equal>	
					            	</logic:equal>
			            		</td>
								<td valign="top"><bean:write name="t" property="entryDate" format="MM/dd/yyyy" /></td>
								<td valign="top"><bean:write name="t" property="traitTypeName" /></td>
								<td valign="top"><bean:write name="t" property="traitTypeDescription" /></td>
								<td width="1%" valign="top"><bean:write name="t" property="status" /></td>
								<td valign="top"><bean:write name="t" property="traitComments" /></td>
							</tr>
						</pg:item>
					</logic:iterate>
				</table>
	     			<%-- Begin Pagination navigation Row--%>
					<table cellpadding="2" cellspacing="1" align="center">
	      				<tr>
	        				<td>
	        					<pg:index>
		        					<tiles:insert page="/jsp/jimsPagination.jsp"  flush="false">
		        						<tiles:put name="pagerUniqueName" value="pagerSearch"/>
		        						<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
		        					</tiles:insert>
	        		 			</pg:index>
	        			  </td>
	       	  			</tr>
	       			</table>
	       			 <%-- End Pagination navigation Row--%>
				</pg:pager>
			</logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td align="center" colspan='8'>
			<table align="center" width="99%">
				<tr>
					<td align="center">
						<logic:notEqual name="juvenileTraitsForm" property="action" value="<%=naming.UIConstants.FIND%>">
						 <logic:notEmpty name="juvenileProfileHeader" property="status">
							 <jims2:isAllowed requiredFeatures="<%=Features.JCW_PRF_TRAITS_U%>">
								 <logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
									<html:submit property="submitAction" disabled="true" styleId="facUpdateTraitStatus"><bean:message key="button.updateTraitStatus" /></html:submit>
									<logic:notEqual name="juvenileTraitsForm" property="isAdmitView" value="true">
										<html:submit property="submitAction"  disabled="true" styleId="facAddMoreTraits"><bean:message key="button.addMoreTraits" /></html:submit>
									</logic:notEqual>
									<logic:equal name="juvenileTraitsForm" property="isAdmitView" value="true">
										<html:submit property="submitAction"  styleId="facAddMoreTraits"><bean:message key="button.addMoreTraits" /></html:submit>
									</logic:equal>
								 </logic:equal>	
							 </jims2:isAllowed>
						 </logic:notEmpty>
						</logic:notEqual>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<%-- since this JSP is used for both Casefile and Profile, we have to route the user to the correct path --%>
<table align="center" width="99%">
	<tr>
		<td align="center" colspan='8'>
		   <input type="button" value="Close Window" onclick="window.close()">
		</td>
	</tr>
</table> 
</html:form>
<%-- END TRAITS TABLE --%>
