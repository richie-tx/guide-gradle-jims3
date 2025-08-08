<!DOCTYPE HTML>
<!-- User selects the "Search for Offense Code" on Transferred Offenses Create page -->
<%--MODIFICATIONS --%>
<%--06/11/2013 CShimek CREATE JSP --%>
<%-- 08/31/2015     RCapestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Casefile Referrals UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>



<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- juvenileCasefileOffenseSearch.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
function selectSingleOffense()
{
	document.getElementById("acId").focus();
	document.getElementById("selectBtn").disabled = true;
    var rbs = document.getElementsByName("selectedValue");
	if (rbs.length == 1 && typeof(document.forms[0].pagerSearch) == "undefined"){
		rbs[0].checked = true;
		document.getElementById("selectBtn").disabled = false;
	}
}

function enableSelectButton()
{
	document.getElementById("selectBtn").disabled = false;
}
function checkInputs()
{
	var fld1 = Trim(document.getElementById("acId").value);
	var fld2 = Trim(document.getElementById("descId").value);
	var fld3 = Trim(document.getElementById("dpsId").value);
	var fld4 = Trim(document.getElementById("catId").value);
	// remove any possible spaces in inputs
	document.getElementById("acId").value = fld1.toUpperCase();
	document.getElementById("descId").value = fld2.toUpperCase();
	document.getElementById("dpsId").value = fld3;
	document.getElementById("catId").value = fld4.toUpperCase();
	var str = fld1 + fld2 + fld3 + fld4;
	if (str == "")	 {
	 	alert("At least one field is required for search");
	 	document.getElementById("acId").focus();
	 	return false;
	}
	var msg = "";
	var numericRegExp = /^[0-9]*$/;
	var alphaNumericRegExp = /^[A-Z0-9]*$/;
	var shortDescRegExp = /^[A-Z0-9 ,()\x3C\x3E\x2D\x2F\x2E\x20\x2B\x27\x24\x3D\x22\x5F\x23\x3A]*$/;
	fld1 = document.getElementById("acId");
	if (fld1.value > "") {
		if (alphaNumericRegExp.test(fld1.value,alphaNumericRegExp) == false) {
			if (msg == ""){
				fld1.focus();
			}
			msg = "Alpha Code must be alphabetic character and/or numeric value.\n";
		}
	}
	fld1 = document.getElementById("descId");
	if (fld1.value > "") {
		if (shortDescRegExp.test(fld1.value,shortDescRegExp) == false) {
			if (msg == ""){
				fld1.focus();
			}
			msg = "Short Description contains invalid search character -- such as * ? @ or ~.\n";
		}
	}
	fld1 = document.getElementById("dpsId");
	if (fld1.value > "") {
		if (numericRegExp.test(fld1.value,numericRegExp) == false) {
			if (msg == ""){
				fld1.focus();
			}
			msg += "DPS Code must be numeric value.\n";
		}
	}
	fld1 = document.getElementById("catId");
	if (fld1.value > "") {
		if (alphaNumericRegExp.test(fld1.value,alphaNumericRegExp) == false) {
			if (msg == ""){
				fld1.focus();
			}
			msg += "Severity Level must be alphabetic character and/or numeric value.\n";
		}
	}
	if (msg == "") {
		// reset paging to page 1 
		if (typeof(document.forms[0].pagerSearch) != "undefined") {
			document.getElementsByName("pager.offset")[0].value = -10;
		}
		return true;
	}
	alert(msg);
	return false;
}

</script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="selectSingleOffense()">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|0">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header">
			Juvenile Casework - 
			<logic:equal name="transferredOffenseForm" property="fromPage" value="<%=naming.UIConstants.CASEFILE%>">
				<bean:message key="prompt.casefile"/>
			</logic:equal>
			<logic:notEqual name="transferredOffenseForm" property="fromPage"  value="<%=naming.UIConstants.CASEFILE%>">
				<bean:message key="prompt.juvenile"/> <bean:message key="prompt.profile"/>
			</logic:notEqual>		 
			- Search Offense
		</td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE  -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr> 
	<tr>		  
		<td align="center" class="errorAlert"><bean:write name='transferredOffenseForm' property='errMessage' /></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE  -->
<html:form action="/handleJuvenileCasefileOffenseSearch"  target="content" focus="alphaCodeId">
<!-- BEGIN INSTRUCTION TABLE -->
<table width="100%">
	<tr>
    	<td>
            <ul>
                <li>Enter search criteria and then select "Find Offenses" button to perform search.</li>
				<li>Select Offense and then click "Select" button.</li>
            </ul>
		</td>
	</tr> 
	<tr>
		<td class="required">&nbsp;At least one field is required for search.</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<%-- BEGIN DETAILS TABLE --%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>	
		<td width="98%" align="center" valign="top">
<%-- BEGIN BLUE BORDER DETAILS TABLE --%>		
			<table width="98%" cellpadding="0" cellspacing="0" border="0" class="borderTableBlue">						
				<tr>
					<td align='center'>
<%-- BEGIN SEARCH INPUT TABLE --%>					
						<table border="0" cellspacing='1' cellpadding="2" width="100%">
							<tr class="detailHead">
								<td colspan="2" class='detailHead'><bean:message key="prompt.searchFor"/> <bean:message key="prompt.offenses"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.alpha"/> <bean:message key="prompt.code"/></td>
								<td class="formDe"><html:text name='transferredOffenseForm' property='alphaCodeId' size="6" maxlength="6" styleId="acId"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"  width="1%" nowrap="nowrap"><bean:message key="prompt.short"/> <bean:message key="prompt.description"/></td>
								<td class="formDe"><html:text name='transferredOffenseForm' property='shortDesc' size="30" maxlength="30" styleId="descId"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.DPS"/> <bean:message key="prompt.code"/></td>
								<td class="formDe"><html:text name='transferredOffenseForm' property='dpsCodeId' size="8" maxlength="8" styleId="dpsId"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.severityLevel"/> </td>
								<td class="formDe"><html:text name='transferredOffenseForm' property="categoryId" size="2" maxlength="2" styleId="catId"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"></td>
								<td class="formDe">
									 <html:submit property="submitAction" onclick="return checkInputs();"><bean:message key="button.findOffenses"/></html:submit>		
									 <html:submit property="submitAction"><bean:message key="button.refresh"/></html:submit>		
								</td>
							</tr>
						</table>
<%-- END SEARCH INPUT TABLE --%>
						<div class="spacer4px"></div>
						<logic:notEmpty name="transferredOffenseForm" property="offenseResultList">
<%-- BEGIN SEARCH RESULTS TABLE --%>						
							<table border="0" cellspacing='1' cellpadding="2" width="100%">
								<tr>
									<td align="center" style="padding:2px" colspan="2"><br>
										<bean:size id="collSize" name="transferredOffenseForm" property="offenseResultList"/>
										<bean:write name="collSize"/> search results found.
									</td>
								</tr>
								<tr>			
									<td>	
<%-- BEGIN SEARCH RESULTS LIST TABLE --%>									
										<table border="0" width="100%" cellspacing="1" cellpadding="2">				
											<tr bgcolor="#cccccc">
												<td width="1%"></td>
												<td class="subhead"><bean:message key="prompt.alpha" /> <bean:message key="prompt.code" /><jims2:sortResults beanName="transferredOffenseForm" results="offenseResultList" primaryPropSort="alphaCode" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" /></td>
												<td class="subhead"><bean:message key="prompt.short" /> <bean:message key="prompt.description" /><jims2:sortResults beanName="transferredOffenseForm" results="offenseResultList" primaryPropSort="shortDescription" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" /></td>
												<td class="subhead"><bean:message key="prompt.DPS" /> <bean:message key="prompt.code" /><jims2:sortResults beanName="transferredOffenseForm" results="offenseResultList" primaryPropSort="dpsCode" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" /></td>
												<td class="subhead"><bean:message key="prompt.severityLevel" /><jims2:sortResults beanName="transferredOffenseForm" results="offenseResultList" primaryPropSort="category" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" /></td>
											</tr>
	
											<%-- Begin Pagination Header Tag--%>
					                        <bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"/></bean:define>
					                            <pg:pager
					                           		 index="center"
					                           		 maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
					                           		 maxIndexPages="10"
					                           		 export="offset,currentPageNumber=pageNumber"
					                           		 scope="request">
					                        		 <input type="hidden" name="pager.offset" value="<%= offset %>">
					                         <%-- End Pagination header stuff --%>
			  								<logic:iterate id="offIndex" name="transferredOffenseForm" property="offenseResultList" indexId="index">
			  									<%-- Begin Pagination item wrap --%>
                              					<pg:item>
			  										<bean:define id="offId" name="offIndex" property="alphaCode" type="java.lang.String"/>
													<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
														<td align="center">																																		
															<html:radio name="transferredOffenseForm" property="selectedValue" value='<%=offId%>' onclick="enableSelectButton();" />
														</td>
														<td><bean:write name='offIndex' property='alphaCode'/></td>
														<td><bean:write name='offIndex' property='shortDescription'/></td>
														<td><bean:write name='offIndex' property='dpsCode'/></td>	
														<td><bean:write name='offIndex' property='category'/></td>														
													</tr>
												</pg:item>
                        	   					<%-- End Pagination item wrap --%>
											</logic:iterate>
										</table>
<%-- END SEARCH RESULTS LIST TABLE --%>										
<%-- Begin Pagination navigation TABLE --%>
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
<%-- End Pagination navigation TABLE --%>
<%-- Begin Pagination Header Closing Tag --%>
			                </pg:pager>
<%-- End Pagination Header Closing Tag --%>
								</td>
							</tr>
						</table>
<%-- END SEARCH RESULTS TABLE --%>							
							</logic:notEmpty>
					</td>
				</tr>
			</table>
<%-- END BLUE BORDER DETAILS TABLE --%>			
		</td>
	</tr>
</table>
<%-- BEGIN DETAILS TABLE --%>
<div class="spacer4px"></div>
<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
	<tr> 
		<td align="center">
			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 				
			<html:submit property="submitAction" disabled="true" styleId="selectBtn"><bean:message key="button.select" /></html:submit>
			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>