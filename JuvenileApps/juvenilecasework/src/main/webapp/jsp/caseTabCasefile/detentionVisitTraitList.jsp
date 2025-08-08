<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>




<%--BEGIN HEADER TAG--%>
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
<title><bean:message key="title.heading"/>/detentionVisitTraitList.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

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
		theForm.traitTypeDescriptionId.disabled = true;

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

<%--HELP JAVASCRIPT FILE --%> 
<%-- <SCRIPT SRC="../js/help.js" /> --%>
</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onload="populateTraitTypeDescription(document.forms[0]);">
<html:form action="/displayJuvenileBriefingDetails" target="content">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Juvenile Briefing  - Trait History - Banned for Detention Visit</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select the <b>Close Window</b> button to close this window.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
			  <tr>
			    <td>
      			<table width='100%' border="0" cellpadding="2" cellspacing="1">
							<tr>
								<td class='detailHead' nowrap='nowrap' colspan='2'>Trait Details</td>
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
								<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.traitTypeDescription" /></td>
								<td class="formDe">
									<html:select name="juvenileTraitsForm" property="traitTypeDescriptionId" disabled="true">
										<html:option value=""><bean:message key="prompt.selectAll" /></html:option>
									</html:select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<logic:notEmpty name="juvenileTraitsForm" property="traitsSearchResult">
							<%-- Begin Pagination Header Tag --%>
							<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define>
							<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
							    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
							<%-- End Pagination header stuff --%>
			
							<table width="100%" cellpadding="2" cellspacing="1" class="borderTableGrey">
								<tr bgcolor="#cccccc">
									
									<td class="subhead" valign="top"><bean:message key="prompt.entryDate" /></td>
									<td class="subhead" valign="top"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" /></td>
									<td class="subhead" valign="top"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.description" /></td>
									<td class="subhead" valign="top" width="20%"><bean:message key="prompt.traitStatus" />&nbsp;</td>
									<td class="subhead" valign="top"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.comments" /></td>
								</tr>
			
								<logic:iterate indexId="indexer" id="t" name="juvenileTraitsForm" property="traitsSearchResult">
			            			<pg:item>
						            	<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
						            		
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
			</table>
			<div class='spacer'></div>
			<table width='100%' border="0" cellpadding="2" cellspacing="0">
				<tr>
					<td align="center" colspan='2'>
						<input type="button" value="Close Window" onClick="window.close();">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END  TABLE --%>

</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
