<!DOCTYPE HTML>

<%-- Used to display juvenile traits details off Traits Tab in both Casefile and Juvenile Profile --%>
<%--MODIFICATIONS --%>
<%-- 06/10/2005		DWilliamson	Create Traits tile --%>
<%-- 12/13/2006 Hien Rodriguez  ER#37077 Add Buttons security features  --%>
<%-- 05/11/2007 Uma Gopinath code added to display traits on Medical page-- %>
<%-- 11/07/2011 C Shimek        #71787 added hidden field to hold expandTrait value for medical tab expand/contract state -- %>
<%-- 04/20/2012 C Shimek	    #73232 added allowUpdate edit to buttons for closed casefile status  --%>
<%-- 07/10/2012 C Shimek     	#73565 added age > 20 check (juvUnder21) to Add Trait buttons --%>
<%-- 09/12/2013 C Shimek     	#76047 added Trait Status Update button including radio select --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>

<%@ page import="naming.Features" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<tiles:importAttribute name="source" ignore="true"/>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title><bean:message key="title.heading" />Juvenile Casework - juvenileTraitsSearch.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/traits.js"></script>

<script type="text/javascript">

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
	<tr>
		<td valign="top" colspan="2" class="detailHead">
 			<logic:present name="source">
 			  <logic:equal name="source" value="medical"><a href="javascript:showHideMulti('medical', 'pMedical', 4, '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="medical"></a></logic:equal>
 			</logic:present>
			<bean:message key="prompt.traits" />
 		</td>
	</tr>

	<logic:present name="source">
		<logic:equal name="source" value="medical">		
			<tr id="pMedical0" class="hidden">
		</logic:equal>
	</logic:present>
		
	<logic:notPresent name="source">
		<tr>
	</logic:notPresent>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" /></td>
		<td class="formDe">
			<logic:greaterThan name="moreThanOne" value="1">
				<html:select name="juvenileTraitsForm" property="traitTypeId"  styleId="traitTypeId" onchange="this.form.changeSelection.value='true';populateTraitTypeDescription(this.form);">
					<html:option value=""><bean:message key="select.generic" /></html:option>
					<html:optionsCollection name="juvenileTraitsForm" property="rootTraitTypes" value="traitTypeId" label="traitName" />
					<html:option value="all"><bean:message key="select.all" /></html:option>
				</html:select>
			</logic:greaterThan> 
			
			<logic:lessEqual name="moreThanOne" value="1">
				<html:select name="juvenileTraitsForm" property="traitTypeId" styleId="traitTypeId" onchange="this.form.changeSelection.value='true';populateTraitTypeDescription(this.form);" disabled="true">
					<html:optionsCollection name="juvenileTraitsForm" property="rootTraitTypes" value="traitTypeId" label="traitName" />
				</html:select>
			</logic:lessEqual>
		</td>
	</tr>

	<logic:present name="source">
		<logic:equal name="source" value="medical">		
			<tr id="pMedical1" class="hidden">
		</logic:equal>
	</logic:present>

	<logic:notPresent name="source">
		<tr>
	</logic:notPresent>

		<td class="formDeLabel" width="1%" nowrap="nowrap" valign="top"><bean:message key="prompt.traitTypeDescription" /></td>
		<td class="formDe">
			<html:select name="juvenileTraitsForm" property="traitTypeDescriptionId" styleId="traitTypeDescriptionId">
				<html:option value=""><bean:message key="prompt.selectAll" /></html:option>
			</html:select>
			<script type="text/javascript">populateTraitTypeDescription(document["juvenileTraitsForm"]);</script>
			<br /><html:submit property="submitAction" styleId="viewTraits"><bean:message key="button.viewTraits" /></html:submit>
		</td>
	</tr>

	<logic:present name="source">
		<logic:equal name="source" value="medical">		
			<tr id="pMedical2" class="hidden">
		</logic:equal>
	</logic:present>

	<logic:notPresent name="source">
		<tr>
	</logic:notPresent>

		<td valign="top" colspan="2">

			<logic:empty name="juvenileTraitsForm" property="traitsSearchResult">
				<table width="100%" cellpadding="4" cellspacing="1" bgcolor="#999999">
					<tr class="normalRow">
						<td colspan="5">
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
				<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
				    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
				<%-- End Pagination header stuff --%>

				<table width="100%" cellpadding="2" cellspacing="1" class="borderTableGrey">
					<tr bgcolor="#cccccc">
						<td class="subhead" valign="top" align="left">&nbsp;</td>
						<td class="subhead" valign="top" align="left"><bean:message key="prompt.entryDate" /></td>
						<td class="subhead" valign="top" align="left"><bean:message key="prompt.traitId" /></td>
						<td class="subhead" valign="top" align="left"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" /></td>
						<td class="subhead" valign="top" align="left"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.description" /></td>
						<td class="subhead" valign="top" align="left"><bean:message key="prompt.traitStatus" />&nbsp;</td>
						<td width="1%" class="subhead" valign=top  align="left"><bean:message key="prompt.info" /> <bean:message key="prompt.source" /></td>
						<td class="subhead" valign="top" align="left"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.comments" /></td>
					</tr>

					<logic:iterate indexId="indexer" id="t" name="juvenileTraitsForm" property="traitsSearchResult">
            			<pg:item>
			            	<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
			            		<td valign="top">
				            		<logic:notEqual name="juvenileTraitsForm" property="UICasefile"	value="true">
				            			<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
				            				<logic:equal name="juvenileTraitsForm" property="activeCasefileFound" value="true" >
					            				<logic:equal name="t" property="statusId" value="CU">
					            					<input type="radio" name="selectedValue" value='<bean:write name="t" property="juvenileTraitId"/>' styleId="<bean:write name="t"	property="parentTypeId"/>-traitId">
					            				</logic:equal>
					            			</logic:equal>	
					            		</logic:equal>
					            	</logic:notEqual>	
					            	<logic:equal name="juvenileTraitsForm" property="UICasefile" value="true">
				            			<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
				            				<logic:equal name="juvenileTraitsForm" property="activeCasefileFound" value="true" >
					            				<logic:equal name="t" property="statusId" value="CU">
					            					<input type="radio" name="selectedValue" value='<bean:write name="t" property="juvenileTraitId"/>' styleId="<bean:write name="t" property="parentTypeId" />-traitId">
					            				</logic:equal>
					            			</logic:equal>	
					            		</logic:equal>
					            	</logic:equal>		
			            		</td>
								<td valign="top" align="left"><bean:write name="t" property="entryDate" format="MM/dd/yyyy" /></td>
								<td valign="top" align="left"><bean:write name="t" property="juvenileTraitId" /></td>
								<td valign="top" align="left"><bean:write name="t" property="traitTypeName" /></td>
								<td valign="top" align="left"><bean:write name="t" property="traitTypeDescription" /></td>
								<td width="1%" valign="top" align="left"><bean:write name="t" property="status" /></td>
								<td valign="top" align="left"><span title='<bean:write name="t" property="informationSrcDesc"/>'><bean:write name="t" property="informationSrcCd"/></span>&nbsp;&nbsp;</td>
								<td valign="top" align="left"><bean:write name="t" property="traitComments" /></td>
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
</table>

<table>
	<logic:present name="source">
		<logic:equal name="source" value="medical">		
			<tr id="pMedical3" class="hidden">
		</logic:equal>
	</logic:present>
	<logic:notPresent name="source">
		<tr>
	</logic:notPresent>

	<logic:notEqual name="juvenileTraitsForm" property="action" value="<%=naming.UIConstants.FIND%>">
		<td>
			<logic:notEqual name="juvenileTraitsForm" property="UICasefile"	value="true">
				<logic:notEqual name="juvenileTraitsForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
					<logic:equal name="juvenileTraitsForm" property="categoryName" value="">
						<html:button property="button.back" onclick="history.back();"><bean:message key="button.back" /></html:button> 
					</logic:equal>
					<!-- ER GANG CHANGES -->
					<!-- 
					<logic:equal name="juvenileTraitsForm" property="categoryName" value="GANGS">
						<html:button property="button.back" onclick="history.back();"><bean:message key="button.back" /></html:button>
					</logic:equal>  -->
					<!-- ER GANG CHANGES -->
					<logic:equal name="juvenileTraitsForm" property="categoryName" value="SPECIAL_INTERESTS">
						<html:button property="button.back" onclick="history.back();"><bean:message key="button.back" /></html:button>
					</logic:equal>
				</logic:notEqual>  
				<logic:notEmpty name="juvenileProfileHeader" property="status">
					<jims2:isAllowed requiredFeatures="<%=Features.JCW_PRF_TRAITS_U%>">
				 		<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
				 			<html:submit onclick="spinner()" property="submitAction" disabled="true" styleId="utsbtn1"><bean:message key="button.updateTraitStatus" /></html:submit>
							<html:submit onclick="spinner()" property="submitAction" styleId="addMoreTraitsId"><bean:message key="button.addMoreTraits" /></html:submit>
						</logic:equal>	
					</jims2:isAllowed>
				</logic:notEmpty>
			</logic:notEqual>
					
			<logic:equal name="juvenileTraitsForm" property="UICasefile" value="true">
				<logic:notEqual name="juvenileTraitsForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
					<html:button property="button.back" onclick="history.back();"><bean:message key="button.back" /></html:button>
				</logic:notEqual>    
				<jims2:isAllowed requiredFeatures="<%=Features.JCW_PRF_TRAITS_U%>">
					<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
						<html:submit onclick="spinner()" property="submitAction" disabled="true" styleId="utsbtn2"><bean:message key="button.updateTraitStatus" /></html:submit>
						<html:submit onclick="spinner()"  property="submitAction" styleId="addMoreTraitsId2"><bean:message key="button.addMoreTraits"/></html:submit>
					</logic:equal>	
				</jims2:isAllowed>
			</logic:equal>
		</td>
	</logic:notEqual>

</html:form>

		<%-- since this JSP is used for both Casefile and Profile, we have to route the user to the correct path --%>
		<logic:equal name="juvenileTraitsForm" property="UICasefile" value="true">
	    <logic:notEqual name="juvenileTraitsForm" property="action" value="<%=naming.UIConstants.FIND%>">
		    <html:form action="/displayJuvenileMasterInformation" target="content">
           <td><input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')"></td>
		    </html:form>
			</logic:notEqual>

			<logic:equal name="juvenileTraitsForm" property="action" value="<%=naming.UIConstants.FIND%>">
			    <input type="button" value="Close Window" onclick="window.close()"> 
			</logic:equal>    
		</logic:equal>

		<logic:equal name="juvenileTraitsForm" property="UICasefile" value="false">
		  <logic:equal name="juvenileTraitsForm" property="categoryName" value="">
			<html:form action="/displayJuvenileMasterInformation" target="content">
				<td><html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit></td>
			</html:form>
		  </logic:equal>
		  <!-- ER GANG CHANGES -->
		  <!-- 
		  <logic:equal name="juvenileTraitsForm" property="categoryName" value="GANGS">
			<html:form action="/displayJuvenileMasterInformation" target="content">
				<td><html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit></td>
			</html:form>
		  </logic:equal>
		   -->
		    <!-- ER GANG CHANGES -->
		  <logic:equal name="juvenileTraitsForm" property="categoryName" value="SPECIAL_INTERESTS">
			<html:form action="/displayJuvenileMasterInformation" target="content">
				<td><html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit></td>
			</html:form>
		  </logic:equal>	
		</logic:equal>

	</tr>
</table>
<%-- END TRAITS TABLE --%>

<logic:present name="source">
	<input type="hidden" name="loadCtr" id="expandTriatsId" value="<bean:write name="juvenileTraitsForm" property="expandTraits" />" >
  	<logic:present name="traitAdded">
    	<script>showHideMulti('medical', 'pMedical', 4, '/<msp:webapp/>');</script>
  	</logic:present>
</logic:present>
