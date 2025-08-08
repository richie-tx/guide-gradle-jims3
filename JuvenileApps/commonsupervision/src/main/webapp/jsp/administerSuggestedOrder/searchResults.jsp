<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/14/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/09/2005  Hien Rodriguez - JIMS200027540 - Only show Back & Cancel buttons when search result is zero -->
<!-- 01/24/2006  Hien Rodriguez - Implementing interim Back button -->
<!-- 01/16/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerSuggestedOrder/searchResults.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>
<bean:define id="btnsEnabled" value="false" type="java.lang.String"/>
<jims2:isAllowed requiredFeatures="CS-ASO-COPY">
	<% btnsEnabled="true"; %>
</jims2:isAllowed>
<jims2:isAllowed requiredFeatures="CS-ASO-UPDATE">
	<% btnsEnabled="true"; %>
</jims2:isAllowed>
	
<jims2:isAllowed requiredFeatures="CS-ASO-DELETE">
<% btnsEnabled="true"; %>
</jims2:isAllowed>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleSuggestedOrderSelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|2">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>    	
  	</tr>
  	<tr>
    	<td valign="top">
    	<!-- BEGIN BLUE TAB TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value="suggestedOrderTab"/>
							</tiles:insert>						
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>				
			</table>
		<!-- END BLUE TAB TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">					
					<!-- BEGIN HEADING TABLE -->
						<table width="98%">						
							<tr>							
							    <td align="center" class="header">
									<bean:message key="title.suggestedOrder" />&nbsp;<bean:message key="title.searchResults" />
								</td>						
						  	</tr>						  
						</table>
					<!-- END HEADING TABLE -->
					<%-- Begin Pagination Header Tag--%>
					<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
					<br>
					<pg:pager
					    index="center"
					    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
					    maxIndexPages="10"
					    export="offset,currentPageNumber=pageNumber"
					    scope="request">
					    <input type="hidden" name="pager.offset" value="<%= offset %>">
					<%-- End Pagination header stuff --%>
					<!-- BEGIN ERROR TABLE -->
						<table width="98%" align="center">							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table>
					<!-- END ERROR TABLE -->
					<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">						
							<tr>
								<td><ul>
									<li>Select a Suggested Order and click the Appropriate button below. </li>
								</ul></td>
							</tr>						
						</table>
					<!-- END INSTRUCTION TABLE -->															
						<table>
							<tr>
								<td align="center">
									<bean:size id="suggestedOrderSize" name="suggestedOrderForm" property="suggestedOrderList"/>
									<b><bean:write name="suggestedOrderSize"/></b>&nbsp; order(s) found in search results</td>            					
            				</tr>
            			</table>            												
					<!-- BEGIN DETAIL TABLE -->	
						<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
							<tr class="detailHead">
								<td width="1%"></td>
								<td><bean:message key="prompt.name" />
									<jims2:sortResults beanName="suggestedOrderForm" results="suggestedOrderList" primaryPropSort="suggestedOrderName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
								</td>
								<td><bean:message key="prompt.description" />
									<jims2:sortResults beanName="suggestedOrderForm" results="suggestedOrderList" primaryPropSort="suggestedOrderDescription" primarySortType="STRING" sortId="2" />
								</td>								
								<%--td><bean:message key="prompt.conditionName" /></td --%>
							</tr>
							<%int RecordCounter = 0;
							String bgcolor = "";%>  
							<logic:notEmpty name="suggestedOrderForm" property="suggestedOrderList">	
								<logic:iterate id="suggestedOrderListIndex" name="suggestedOrderForm" property="suggestedOrderList">
									<pg:item>
									<tr class=<%RecordCounter++;
										bgcolor = "alternateRow";
										if (RecordCounter % 2 == 1)
											bgcolor = "normalRow";
										out.print(bgcolor);%>>
										<td width="1%">
										<logic:notEqual name="btnsEnabled" value="<%=btnsEnabled%>">
										<input type="radio" name="orderId" value=<bean:write name="suggestedOrderListIndex" property="suggestedOrderId"/> />
										</logic:notEqual>
										</td>
										
										<td><a href="/<msp:webapp/>displaySuggestedOrderDetails.do?action=view&orderId=<bean:write name="suggestedOrderListIndex" property="suggestedOrderId"/>">
											<bean:write name="suggestedOrderListIndex" property="suggestedOrderName" /></a></td>								
										<td><bean:write name="suggestedOrderListIndex" property="suggestedOrderDescription" /></td>
										<%--td><bean:write name="suggestedOrderListIndex" property="suggestedOrderConditionName" /></td--%>							
									</tr>		
									</pg:item>														
								</logic:iterate>
							</logic:notEmpty> 				
						</table>					
					<!-- END DETAIL TABLE -->	
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
					<br>
					<!-- BEGIN BUTTON TABLE -->		
						<table align="center" width="98%">				
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
									<logic:notEmpty name="suggestedOrderForm" property="suggestedOrderList">
										<jims2:isAllowed requiredFeatures="CS-ASO-UPDATE">
											<html:submit property="submitAction" onclick="return validateRadios(this.form, 'orderId', 'Please select an order.') && disableSubmit(this, this.form);"><bean:message key="button.update"></bean:message></html:submit>&nbsp;
										</jims2:isAllowed>
										<jims2:isAllowed requiredFeatures="CS-ASO-COPY">
											<html:submit property="submitAction" onclick="return validateRadios(this.form, 'orderId', 'Please select an order.') && disableSubmit(this, this.form);"><bean:message key="button.copy"></bean:message></html:submit>&nbsp;
										</jims2:isAllowed>
										<jims2:isAllowed requiredFeatures="CS-ASO-DELETE">
											<html:submit property="submitAction" onclick="return validateRadios(this.form, 'orderId', 'Please select an order.') && disableSubmit(this, this.form);"><bean:message key="button.delete"></bean:message></html:submit>&nbsp;
										</jims2:isAllowed>
									</logic:notEmpty> 	
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																					
								</td>
							</tr>					
						</table>						
					<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>		
		</td>
	</tr>
</table>
<br>	
	
</div>
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
