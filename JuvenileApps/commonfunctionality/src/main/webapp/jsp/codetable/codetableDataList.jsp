<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 02/07/2007		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.Features" %>



<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonfunctionality.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;Manage Code Table&nbsp;- codetableDataList.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script>
	$(document).ready(function(){
		if ( "JUVENILE OFFENSES AND ADMIN. ACTIONS" == "<bean:write name="codetableForm" property="codetableName"/>"
				|| "SCHOOL" == "<bean:write name="codetableForm" property="codetableName"/>"
				|| "COURT DECISION" == "<bean:write name="codetableForm" property="codetableName"/>"
				|| "SUPERVISION TYPE TJJD MAP" == "<bean:write name="codetableForm" property="codetableName"/>" ){
			$("#secondAttribute").css("visibility", "visible");
		}
		
		 if ($("#filterAttributeId").val() != ""
	    		&& $("#filterType").val() != ""
	    		&& $("#filterAttributeValue").val() != "" ){
	    	$("#secondFilterAttributeId").attr("disabled", false);
	    	$("#secondFilterType").attr("disabled", false);
	    	$("#secondFilterAttributeValue").attr("disabled", false);
	    } else {
	    	$("#secondFilterAttributeId").attr("disabled", true);
	    	$("#secondFilterType").attr("disabled", true);
	    	$("#secondFilterAttributeValue").attr("disabled", true);
	    }
		 
		secondAttributeAllowed("filterAttributeId");
		secondAttributeAllowed("filterType");
		secondAttributeAllowed("filterAttributeValue");
		
		
		
	})
	
	function secondAttributeAllowed(id){
		$('#'+ id).on('change keyup paste click', function() {
			console.log("filter change");
		    if ($("#filterAttributeId").val() != ""
		    		&& $("#filterType").val() != ""
		    		&& $("#filterAttributeValue").val() != "" ){
		    	$("#secondFilterAttributeId").attr("disabled", false);
		    	$("#secondFilterType").attr("disabled", false);
		    	$("#secondFilterAttributeValue").attr("disabled", false);
		    } else {
		    	$("#secondFilterAttributeId").attr("disabled", true);
		    	$("#secondFilterType").attr("disabled", true);
		    	$("#secondFilterAttributeValue").attr("disabled", true);
		    }
		});
	}
</script>


</head>


<body topmargin=0 leftmargin='0'  onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<html:form action="/handleCodeSelection" target="content" focus="filterAttributeValue"> 


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >Manage Code Table - Code Table Data List</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager
	index="center"
	maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
	maxIndexPages="10"
	export="offset,currentPageNumber=pageNumber"
	scope="request">
	<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>	
	
<%-- BEGIN INSTRUCTION TABLE --%>
<br>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select a hyperlinked Code to view the Code Table Record Details for that code.</li>
        <li>To narrow the number of codes displayed, select an Attribute, a Comparison operator, enter a Value,
		 then select the Filter button.</li>
		 <li>Select hyperlinked Code table name to refresh the page.</li>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>


<table align="center" width="98%"  border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
  <tr>
    <td colspan=4 class=detailHead>Code Table:&nbsp;<a href="/<msp:webapp/>displayCodetableDataList.do?submitAction=Link&codetableRegId=<bean:write name="codetableForm" property="codetableRegId"/>"><bean:write name="codetableForm" property="codetableName"/></a>
		</td>
  </tr>

  <tr>
    <td bgcolor='#ffffff' colspan=4><img src='/<msp:webapp/>images/spacer.gif'></td>
  </tr>
	<tr bgcolor='#cccccc'>
    <td align="left" class=subHead>Attribute</td>
    <td align="left" class=subHead>Comparison</td>
    <td align="left" class=subHead colspan=2>Value</td>
	</tr>
	<tr bgcolor='#cccccc'>
    <td align="left" class=subHead>
      <html:select styleId="filterAttributeId" 
      				name="codetableForm" 
      				property="filterAttributeId">
        <html:option value="">Please Select</html:option>
        <logic:iterate id="codetableHeaderIter" name="codetableForm" property="codetableAttributes">
			<logic:notEqual name="codetableHeaderIter" property="audit" value="true">
				<bean:define id="attrValue" name="codetableHeaderIter" property="codetableRegAttributeId" type="java.lang.String"/>
				<html:option value="<%=attrValue%>"><bean:write name="codetableHeaderIter" property="displayName"/></html:option>
			</logic:notEqual>	
        </logic:iterate>
      </html:select>
		</td>
    <td align="left" class=subHead>
		<html:select styleId="filterType" 
					name="codetableForm" 
					property="filterType">  
			<html:option value="">Please Select</html:option>
			<html:option value="contains">contains</html:option>
			<html:option value="starts with">starts with</html:option>
			<html:option value="is exactly">is exactly</html:option>
		</html:select>
	</td>
    <td align="left" class=subHead>
    	<html:text styleId="filterAttributeValue"
    				name="codetableForm" 
    				property="filterAttributeValue"/></td>
    <td align="left" class=subHead>
		<html:submit property="submitAction"><bean:message key="button.filter"/></html:submit>
  		<html:submit property="submitAction"><bean:message key="button.reset"/></html:submit>
		</td>
	</tr>
	<tr id="secondAttribute" style="visibility: collapse;" bgcolor='#cccccc'>
		 <td align="left" class=subHead>
		      <html:select styleId="secondFilterAttributeId" 
		      				name="codetableForm" 
		      				property="secondFilterAttributeId"  disabled="true">
		        <html:option value="">Please Select</html:option>
		        <logic:iterate id="codetableHeaderIter" name="codetableForm" property="codetableAttributes">
					<logic:notEqual name="codetableHeaderIter" property="audit" value="true">
						<bean:define id="attrValue" name="codetableHeaderIter" property="codetableRegAttributeId" type="java.lang.String"/>
						<html:option value="<%=attrValue%>"><bean:write name="codetableHeaderIter" property="displayName"/></html:option>
					</logic:notEqual>	
		        </logic:iterate>
		      </html:select>
		</td>
		<td align="left" class=subHead>
			<html:select styleId="secondFilterType" 
							name="codetableForm" 
							property="secondFilterType" disabled="true">  
				<html:option value="">Please Select</html:option>
				<html:option value="contains">contains</html:option>
				<html:option value="starts with">starts with</html:option>
				<html:option value="is exactly">is exactly</html:option>
			</html:select>
		</td>
		<td align="left" class=subHead><html:text styleId="secondFilterAttributeValue" 
													name="codetableForm" 
													property="secondFilterAttributeValue"  disabled="true"/></td>
		<td></td>
	</tr>
	<tr>
    <td bgcolor='#ffffff' colspan=2><img src='/<msp:webapp/>images/spacer.gif'></td>
  </tr>

 


	<%-- BEGIN Complex DETAIL TABLE --%>
	<tr>
		<td colspan=4> 
			<table width='100%' id='complexTable'>
      	<tr bgcolor='#cccccc'>	
					<logic:iterate id="codetableHeaderIter" name="codetableForm" property="codetableAttributes">
						<logic:notEqual name="codetableHeaderIter" property="audit" value="true">
							<td class=subHead align="left"><bean:write name="codetableHeaderIter" property="displayName"/></td>
						</logic:notEqual>	
					</logic:iterate>
		    </tr>

        <% int RecordCounter = 0; %>
				<logic:iterate indexId="idx" id="codetableDataIter" name="codetableForm" property="filteredCodetables">
        <pg:item>
  
    			<tr class="
    				<% RecordCounter++;
    					if (RecordCounter % 2 == 1)
    						out.print("normalRow");
    					else
    						out.print("alternateRow");
    				%>"
					>		 	
  					<logic:notEmpty name="codetableDataIter" property="valueMap">
  						<logic:iterate indexId="dataIndex" id="dataIter" name="codetableDataIter" property="valueMap">
  								<logic:iterate id="codetableHeaderIter" name="codetableForm" property="codetableAttributes">
  									<logic:notEqual name="codetableHeaderIter" property="audit" value="true">
  											<bean:define id="displayOrder" name="codetableHeaderIter" property="displayOrder" type="java.lang.String"/>
  											<logic:equal name="dataIter" property="key" value="<%=displayOrder%>">
  												<%-- Begin Pagination item wrap --%>
  												<td align="left">
  													<logic:equal name="dataIndex" value="0">
  														<a href="/<msp:webapp/>handleCodeSelection.do?submitAction=View&selCodetableDataId=<bean:write name='codetableDataIter' property='codetableDataId'/>">
  													</logic:equal>
  													<logic:equal name="codetableHeaderIter" property="compound" value="true">
  
  														<logic:iterate id="compoundListIter" name="codetableForm" property="codetableCompoundList">
  															<%String codetableIdentifier = "";%>
  															<logic:notEmpty name="codetableHeaderIter" property="contextKey">
  																	<bean:define id="contextKey" name="codetableHeaderIter" property="contextKey" type="java.lang.String"/>
  																	<%codetableIdentifier = contextKey;%>
  															</logic:notEmpty>
  															<logic:empty name="codetableHeaderIter" property="contextKey">
  																	<bean:define  id="entityName" name="codetableHeaderIter" property="entityName" type="java.lang.String"/>
  																	<%codetableIdentifier = entityName;%>
  															</logic:empty>
  
  															<logic:equal name="compoundListIter" property="codetableIdentifier" value="<%=codetableIdentifier%>">
  																<logic:iterate id="listIter" name="compoundListIter" property="list">
  																	<logic:notEmpty name="dataIter" property="value">
  																		<bean:define id="codeId" name="dataIter" property="value" type="java.lang.String"/>
  																		<logic:equal name="listIter" property="codeId" value="<%=codeId%>">
  																			<bean:write name="listIter" property="description"/></a>
  																		</logic:equal>
  																	</logic:notEmpty>
  																</logic:iterate>
  															</logic:equal>
  														</logic:iterate>
  
  													</logic:equal>
  													<logic:notEqual name="codetableHeaderIter" property="compound" value="true">
  														<bean:write name="dataIter" property="value"/>
  													</logic:notEqual>
  												</td>
  												<%-- End Pagination item wrap --%>
  											</logic:equal>
  										
  								</logic:notEqual>	
  							</logic:iterate>
  						</logic:iterate>
  					</logic:notEmpty>
	        </tr>
  			</pg:item>
				</logic:iterate>
			   
			</table>
			<%-- Begin Pagination navigation Row--%>
			<table align="center">
  			<tr>
    			<td>
    				<pg:index>
    					<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
    						<tiles:put name="pagerUniqueName" value="pagerSearch"/>
    						<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
    					</tiles:insert>
    				</pg:index>
    			</td>
  			</tr>
			</table>
			<%-- End Pagination navigation Row--%>
		</td>
	</tr>
</table>	
  <%-- END Complex DETAIL TABLE --%>

</pg:pager>
<%-- End Pagination Closing Tag --%>

 


<%-- BEGIN BUTTON TABLE --%>
<br>
<table border="0" width="100%">
  <tr>
    <td align="center">
      <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
      <jims2:isAllowed requiredFeatures='<%=Features.CF_UPDATE_CODETABLE%>'>
          <html:submit property="submitAction"><bean:message key="button.add"/></html:submit>
      </jims2:isAllowed>
      <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
    </td>
  </tr>
</table>
<%-- END BUTTON TABLE --%>


</html:form>

<span align=center><script type="text/javascript">renderBackToTop()</script></span>

</body>
</html:html>

