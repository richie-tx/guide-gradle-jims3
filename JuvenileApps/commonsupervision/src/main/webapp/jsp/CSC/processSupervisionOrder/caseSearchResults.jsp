<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 11/30/2005	 Hien Rodriguez  - Create JSP -->
<!-- 01/16/2007	 Hien Rodriguez  - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 04/15/2008	 Richard Young   - Pointed print references to the web focus action. -->
<!-- 10/13/2008	 Richard Young   - #49945 highlights for impacted and incomplete orders. -->
<!-- 12/17/2008  Clarence Shimek - #56061 added onload to set buttons when page accessed via Back button -->
<!-- 12/17/2008  Clarence Shimek - #61088 added hidden field "hasWithdrawFeature" under feature check for withdraw order button dispaly -->
<!-- 08/14/2009  Clarence Shimek - #61938 added hidden field next to radio button for migrated orders only.  Need to recognize order type -->
<!-- 05/12/2010  Richard Capestani - #63952 added New Case Documents button. -->
<!-- 04/12/2011  RYoung  - #70010 changed the New Case Documents button to use the change form settings. -->
<!-- 06/11/2012  Richard Capestani - #73568 set update date to display as Status Change Date -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%@ page import="naming.PDCommonSupervisionConstants" %> 
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
<title><bean:message key="title.heading" /> - processSupervisionOrder/caseSearchResults.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script>
function findAndInitializeRadio(){
 var radioBtnElems=document.getElementsByName("primaryCaseOrderKey");
 var actualElement;
 if(radioBtnElems!=null && radioBtnElems.length>0){	
 	for(var loopX=0;loopX<radioBtnElems.length;loopX++){
		actualElement=radioBtnElems[loopX];
		if(actualElement.checked==true){
			actualElement.checked=true;
			show("NewCaseDocuments", 1);
			actualElement.onclick();
		}
		else{
			if(radioBtnElems.length==1){
				actualElement.checked=true;
				actualElement.onclick();
				show("NewCaseDocuments", 1);
			}
		}
	}			
  }
 
}
function displayNCDbutton(){
	show("NewCaseDocuments", 1);
}
/** function setHiddenVersion(orderVer)
{
	if (orderVer.length < 2 ){
		orderVer = "";
	} else {
		orderVer = orderVer.toUpperCase();
		if (orderVer.indexOf("MODIFIED") > -1 || orderVer.indexOf("AMENDED") > -1){
			orderVer = "AM";
		} else {
			orderVer = "";
		}	
	}
	document.forms[0].selectedValue.value = orderVer;
} **/
</script>


</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="findAndInitializeRadio();">
<html:form action="/handleSupervisionOrderSelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|5">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
    	<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="processOrderTab"/>
						</tiles:insert>		
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>							
								<td align="center" class="header">
									<bean:message key="title.processSupervisionOrder" />&nbsp;-&nbsp;<bean:message key="prompt.associated" />&nbsp;<bean:message key="prompt.cases" />
								</td>						
							</tr>
							<logic:equal name="supervisionOrderForm" property="action" value="defendentSigAquired">																	
								<tr>
									<td align="center" class="confirm"> Defendant Signature Acquired information saved.</td>
								</tr>
							</logic:equal>
							
						</table>
						<!-- END HEADING TABLE -->
						
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
									<li>Select an Order/Case click appropriate button.</li>
									<li>Use CASE and SPN search to create an Original Order.</li> 
								</ul></td>
							</tr>										
						</table>
						<!-- END INSTRUCTION TABLE -->
						<table>
							<tr>
								<td align="center">
									<bean:size id="caseOrderListSize" name="supervisionOrderSearchForm" property="caseOrderList"/>
									<b><bean:write name="caseOrderListSize"/></b>&nbsp; case(s) found in search results</td>            					
							</tr>
						</table>
						<!-- BEGIN DETAIL TABLE -->	
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
						<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
							<tr class="detailHead">
								<td width="1%"></td>
								<td><bean:message key="prompt.name" />
									<jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="caseOrderList" primaryPropSort="name" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
								</td>	
								<td><bean:message key="prompt.SPN" />
									<jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="caseOrderList" primaryPropSort="spn" primarySortType="STRING" sortId="2" />
								</td>																			
								<td><bean:message key="prompt.CON" />
									<jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="caseOrderList" primaryPropSort="connectionId" primarySortType="STRING" sortId="3" />
								</td>
								<td><bean:message key="prompt.CDI" />
									<jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="caseOrderList" primaryPropSort="cdi" primarySortType="STRING" sortId="4" />
								</td>
								<td><bean:message key="prompt.case#" />
								<jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="caseOrderList" primaryPropSort="caseNum" primarySortType="STRING" sortId="5" />
								</td>
								<td><bean:message key="prompt.CRT" />
									<jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="caseOrderList" primaryPropSort="currentCourtNum" primarySortType="INTEGER" sortId="6" />
								</td>
								<td><bean:message key="prompt.offense" />
									<jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="caseOrderList" primaryPropSort="offense" primarySortType="STRING" sortId="7" />
								</td>
								<td><bean:message key="prompt.pretrialInterview" /></td>
								<td><bean:message key="prompt.caseFiled" />
									<jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="caseOrderList" primaryPropSort="caseFileDate" primarySortType="DATE" sortId="8" />
								</td>
								<td><bean:message key="prompt.orderStatus" />
									<jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="caseOrderList" primaryPropSort="orderStatus" primarySortType="STRING" sortId="9" />
								</td>
								<td><bean:message key="prompt.version" />
									<jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="caseOrderList" primaryPropSort="orderVersion" primarySortType="STRING" sortId="10" />
								</td>
								<td><bean:message key="prompt.statusChangeDate" />
									<jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="caseOrderList" primaryPropSort="statusChangeDate" primarySortType="DATE" sortId="11" />
								</td>
							</tr>
							<logic:notEmpty name="supervisionOrderSearchForm" property="caseOrderList">	
								<logic:iterate id="caseOrderListIndex" name="supervisionOrderSearchForm" property="caseOrderList" indexId="index">
									<pg:item>
									<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
										<script>
										var theValue = ""
										function setVal(val)
										{
										 theValue=val;   						
										}
										
										function buildPrintButton(){
										    openWindow('submitSupervisionOrderWebFocusPrintRequest.do?submitAction=Print&compositeKey=' + theValue);
										}
										</script>
										<td width="1%">                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
											<input type="radio" name="primaryCaseOrderKey" value="<bean:write name="caseOrderListIndex" property="primaryKey" />" onclick="displayNCDbutton() && setVal(this.value);showBtnGrp('<bean:write name='caseOrderListIndex' property='caseActivityRuleStatus' />','<%=PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR%>','<bean:write name='caseOrderListIndex' property='isHistoricalOrder' />','<bean:write name='caseOrderListIndex' property='orderChainNum' />','<bean:write name='caseOrderListIndex' property='outOfCountyCase' />','<bean:write name='caseOrderListIndex' property='versionNum' />' )">
									<%--	<input type="radio" name="primaryCaseOrderKey" value="<bean:write name="caseOrderListIndex" property="primaryKey" />" onclick="setVal(this.value);setHiddenVersion('<bean:write name='caseOrderListIndex' property='orderVersion' />');showBtnGrp('<bean:write name='caseOrderListIndex' property='caseActivityRuleStatus' />','<%=PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR%>','<bean:write name='caseOrderListIndex' property='isHistoricalOrder' />','<bean:write name='caseOrderListIndex' property='orderChainNum' />','<bean:write name='caseOrderListIndex' property='outOfCountyCase' />' )"> --%>
											<logic:equal name="caseOrderListIndex" property="isMigratedOrder" value="true" >
												<input type="hidden" name="<bean:write name="caseOrderListIndex" property="primaryKey" />" value="<bean:write name="caseOrderListIndex" property="orderTitle" />" />
											</logic:equal>
										</td>
										<td><bean:write name="caseOrderListIndex" property="name" /> </td>
										<td><bean:write name="caseOrderListIndex" property="spn" /> </td>
										<td><bean:write name="caseOrderListIndex" property="connectionId" /></td>
										<td><bean:write name="caseOrderListIndex" property="cdi" /></td>
										<logic:equal name="caseOrderListIndex" property="likeConditionInd" value="true">
											<td class="impactedOrder" title="This order has Impacted Orders with Like Conditions"/>														
												<bean:write name="caseOrderListIndex" property="caseNum" />
												<input type="hidden" name="printAction" value='<bean:write name="supervisionOrderForm" property="printAction"/>'/>
											</td>	
										</logic:equal>
										<logic:equal name="caseOrderListIndex" property="likeConditionInd" value="false">
											<td><bean:write name="caseOrderListIndex" property="caseNum" />
												<input type="hidden" name="printAction" value='<bean:write name="supervisionOrderForm" property="printAction"/>'/>
											</td>	
										</logic:equal> 
										<td><bean:write name="caseOrderListIndex" property="currentCourtNum" /></td>
										<td><bean:write name="caseOrderListIndex" property="offense" /></td>
										<td>Not available yet</td>													
										<td><bean:write name="caseOrderListIndex" property="caseFileDate" formatKey="date.format.mmddyyyy" /></td>
										
										<logic:equal name="caseOrderListIndex" property="orderStatus" value="INCOMPLETE">
											<td class="incompleteRecord"><bean:write name="caseOrderListIndex" property="orderStatus" /></td>
										</logic:equal>
										<logic:notEqual name="caseOrderListIndex" property="orderStatus" value="INCOMPLETE">
											<td><bean:write name="caseOrderListIndex" property="orderStatus" /></td>
										</logic:notEqual>
										
										<td><bean:write name="caseOrderListIndex" property="orderVersion" /></td>
										<td><bean:write name="caseOrderListIndex" property="statusChangeDate" formatKey="date.format.mmddyyyy" /></td>							
									</tr>
									</pg:item>																
								</logic:iterate>
							</logic:notEmpty> 				
						</table>				
						<!-- END DETAIL TABLE -->
						<table width="98%" cellpadding="0" cellspacing="0">
							<tr>
								<td class="legendSmallText">Orange <span class="impactedOrder">Case #'s</span> signify that a like condition on this case has different details.</td>
							</tr>
							<tr>
								<td class="legendSmallText"><span class="incompleteRecord">Incomplete</span> order status signify orders with unfinished steps.</td>
							</tr>
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
					  
						<br>
						</pg:pager>
						<!-- hidden values needed for "Defendant Signature Acquired" button display -->
						<input type="hidden" name="selectedValue" value="">  
						<input type="hidden" name="userPosition" value="<bean:write name='supervisionOrderSearchForm' property='userPosition' />">
 						<!-- hidden value needed for "Withdraw" button display -->
 						<jims2:isAllowed requiredFeatures="CS-ORDER-ALLOW-WITHDRAWAL"> 
 							<input type="hidden" name="hasWithdrawFeature" value="Y">
 						</jims2:isAllowed>	
						<!-- BEGIN BUTTON TABLE -->
						<tiles:insert page="searchOrderResultsCaseButtonsTile.jsp" flush="true"/>
						
<%-- This is causing a javascript error when there is only 1 result - DAW
 					    <script>
							findAndInitializeRadio();
						</script> --%>
						<table cellpadding="0" cellspacing="0">
							<tr align="center">
								<td>	
									<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.back"/></html:submit>
								<%-- 	<html:reset onclick="clearCaseButtons()"><bean:message key="button.reset"></bean:message></html:reset> --%>
									<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.cancel"></bean:message></html:submit>
								</td>
								<td id="NewCaseDocuments" class="hidden">
									&nbsp;<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.newCaseDocuments"/></html:submit>
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

</div>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>