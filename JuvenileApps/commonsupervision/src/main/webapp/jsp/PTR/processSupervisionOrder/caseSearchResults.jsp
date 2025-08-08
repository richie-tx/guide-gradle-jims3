<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 11/30/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/16/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

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
			actualElement.onclick();
		}
		else{
			if(radioBtnElems.length==1){
				actualElement.checked=true;
				actualElement.onclick();
			}
		}
	}			
  }
}
</script>


</head>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleSupervisionOrderSelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|5">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign=top>
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<!--tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="processOrderTab"/>
						</tiles:insert>		
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign=top align=center>
						<!-- BEGIN HEADING TABLE -->
						<table width=100%>
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
						<table width=98% align=center>							
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
						<table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
							<tr class="detailHead">
								<td width=1%></td>
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
									<jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="caseOrderList" primaryPropSort="courtNum" primarySortType="INTEGER" sortId="6" />
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
								<td><bean:message key="prompt.orderFiled" />
									<jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="caseOrderList" primaryPropSort="orderFileDate" primarySortType="DATE" sortId="11" />
								</td>
							</tr>
							<%int RecordCounter = 0;
							String bgcolor = "";%>  
							<logic:notEmpty name="supervisionOrderSearchForm" property="caseOrderList">	
								<logic:iterate id="caseOrderListIndex" name="supervisionOrderSearchForm" property="caseOrderList">
									<pg:item>
									<tr
									class=<%RecordCounter++;
										bgcolor = "alternateRow";
										if (RecordCounter % 2 == 1)
											bgcolor = "normalRow";
										out.print(bgcolor);%>>
										<td width=1%><input type="radio" name="primaryCaseOrderKey" value="<bean:write name="caseOrderListIndex" property="primaryKey" />" onclick="showBtnGrp('<bean:write name='caseOrderListIndex' property='caseActivityRuleStatus' />','<%=PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR%>' )"></td>
										<td><bean:write name="caseOrderListIndex" property="name" /> </td>
										<td><bean:write name="caseOrderListIndex" property="spn" /> </td>
										<td><bean:write name="caseOrderListIndex" property="connectionId" /></td>
										<td><bean:write name="caseOrderListIndex" property="cdi" /></td>
										
										<logic:equal name="caseOrderListIndex" property="likeConditionInd" value="true">
											<td class=impactedOrder title="This order has Impacted Orders with Like Conditions"/>														
											<bean:write name="caseOrderListIndex" property="caseNum" />
										</logic:equal>
										<logic:equal name="caseOrderListIndex" property="likeConditionInd" value="false">
											<td><bean:write name="caseOrderListIndex" property="caseNum" />
										</logic:equal>
										<input type="hidden" name="printAction" value='<bean:write name="supervisionOrderForm" property="printAction"/>'/> </td>
										<td><bean:write name="caseOrderListIndex" property="courtNum" /></td>
										<td><bean:write name="caseOrderListIndex" property="offense" /></td>
										<td>Not available yet</td>													
										<td><bean:write name="caseOrderListIndex" property="caseFileDate" formatKey="date.format.mmddyyyy" /></td>
										
										<logic:equal name="supervisionOrderSearchForm" property="orderStatus" value="Incomplete">
											<td class=incompleteRecord><bean:write name="caseOrderListIndex" property="orderStatus" /></td>
										</logic:equal>
										<logic:notEqual name="supervisionOrderSearchForm" property="orderStatus" value="Incomplete">
											<td><bean:write name="caseOrderListIndex" property="orderStatus" /></td>
										</logic:notEqual>
										
										<td><bean:write name="caseOrderListIndex" property="orderVersion" /></td>
										<td><bean:write name="caseOrderListIndex" property="orderFileDate" formatKey="date.format.mmddyyyy" /></td>							
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
						
						
						
						<tiles:insert page="searchOrderResultsCaseButtonsTile.jsp" flush="true"/>
						<script>
							findAndInitializeRadio();
						</script>
						<table cellpadding=0 cellspacing=0>
							<tr align="center">
								<td>	
									<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.back"/></html:submit>
								<%-- 	<html:reset onclick="clearCaseButtons()"><bean:message key="button.reset"></bean:message></html:reset> --%>
									<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.cancel"></bean:message></html:submit>																																		 			
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
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>
</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>