<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/12/2006	 Hien Rodriguez - Create JSP -->
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

<%@page import="naming.Features"%>

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
<title><bean:message key="title.heading" /> - outOfCountyCase/caseSearchResults.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
	var goNavLocation = "";	
	function validateRadio(reactivateInd, acdi, acaseNum){
		document.forms[0].cdi.value = acdi;
		document.forms[0].caseNum.value = acaseNum;
		switch (reactivateInd)		
		{
			case "false":	
			<jims2:isAllowed requiredFeatures="CS-OOC-UPDATE">	
				show("Update Case", 1)
			</jims2:isAllowed>
			<jims2:isAllowed requiredFeatures="CS-OOC-REACTIVATE">	
				show("Reactivate Case", 0)
			</jims2:isAllowed>
			<jims2:isAllowed requiredFeatures="<%=Features.CSCD_OOC_CLOSE_CASE%>">	
				show("Close Case", 1)
			</jims2:isAllowed>	
			<jims2:isAllowed requiredFeatures="<%=Features.CSCD_OOC_UPDATE_CLOSURE%>">
				show("Update Closure", 0)
			</jims2:isAllowed>		
			  break
			  case "true":
			  <jims2:isAllowed requiredFeatures="CS-OOC-UPDATE">	
				show("Update Case", 0)
			  </jims2:isAllowed>
			  <jims2:isAllowed requiredFeatures="CS-OOC-REACTIVATE">	
				show("Reactivate Case", 1)
			  </jims2:isAllowed>
			  <jims2:isAllowed requiredFeatures="<%=Features.CSCD_OOC_CLOSE_CASE%>">	
			  	show("Close Case", 0)
			  </jims2:isAllowed>	
			  <jims2:isAllowed requiredFeatures="<%=Features.CSCD_OOC_UPDATE_CLOSURE%>">
			  	show("Update Closure", 1)
			  </jims2:isAllowed>		
			  break		
			default:
			<jims2:isAllowed requiredFeatures="CS-OOC-UPDATE">	
				show("Update Case", 0)
			</jims2:isAllowed>
			<jims2:isAllowed requiredFeatures="CS-OOC-REACTIVATE">	
				show("Reactivate Case", 0)
			</jims2:isAllowed>
			<jims2:isAllowed requiredFeatures="<%=Features.CSCD_OOC_CLOSE_CASE%>">
				show("Close Case", 0)
			</jims2:isAllowed>	
			<jims2:isAllowed requiredFeatures="<%=Features.CSCD_OOC_UPDATE_CLOSURE%>">	
				show("Update Closure", 0)
			</jims2:isAllowed>	
		}
	}

	function checkRadioSelect()
	{
		var rb = document.getElementsByName("primaryCaseKey");
		if (rb.length > 0)
		{
			for (x = 0; x <rb.length; x++)
			{
				if (rb[x].checked == true)
				{
					rb[x].click();
					break;
				}	
			}		
		}
	}	

	function checkForSingleResult() {
	    var rbs = document.getElementsByName("primaryCaseKey");
		if (rbs.length == 1){
			rbs[0].checked = true;
		}	
	}
</script>


</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)"  onload="checkForSingleResult(); checkRadioSelect();">
<html:form action="/handleOutOfCountyCaseSelection" target="content" >
<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|4">
<input type="hidden" name="cdi" value="" />
<input type="hidden" name="caseNum" value="" />
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
					<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="setupTab"/>
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
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
								<!--tabs start-->
									<tiles:insert page="../common/manageFeaturesTabs.jsp" flush="true">
										<tiles:put name="tabid" value="oocTab"/>
									</tiles:insert>	
								<!--tabs end-->
								</td>
							</tr>
							
						</table>
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
								<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>							
							    			<td align="center" class="header">
												<bean:message key="title.outOfCountyCase" />&nbsp;-&nbsp;<bean:message key="prompt.searchResults" />
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
												<li>Select radio button for the case you wish to Update or Reactivate, or click Create to create a new one.</li>
												<li>Click on hyperlinked Case Number to view case details.</li>
											</ul></td>
										</tr>										
									</table>
								<!-- END INSTRUCTION TABLE -->
								<!-- BEGIN DETAIL HEADER TABLE -->										
									<tiles:insert page="partyInfoHeaderTile.jsp" flush="true">
										<tiles:put name="partyHeader" beanName="outOfCountyCaseSearchForm"/>
									</tiles:insert>
								<!-- END DETAIL HEADER TABLE -->
									<table>
										<tr>
											<td align="center">
												<bean:size id="caseListSize" name="outOfCountyCaseSearchForm" property="caseList"/>
												<b><bean:write name="caseListSize"/></b>&nbsp; case(s) found in search results</td>            					
			            				</tr>
			            			</table>
			            		<!-- BEGIN DETAIL TABLE -->	
									<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr class="detailHead">
											<td width="1%"></td>
											<td><bean:message key="prompt.CDI"/>
												<jims2:sortResults beanName="outOfCountyCaseSearchForm" results="caseList" primaryPropSort="cdi" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
											</td>
											<td><bean:message key="prompt.case#"/>
												<jims2:sortResults beanName="outOfCountyCaseSearchForm" results="caseList" primaryPropSort="caseNum" primarySortType="STRING" sortId="2" />
											</td>
											<td><bean:message key="prompt.CRT"/>
												<jims2:sortResults beanName="outOfCountyCaseSearchForm" results="caseList" primaryPropSort="courtNum" primarySortType="STRING" sortId="3" />
											</td>
											<td><bean:message key="prompt.CON"/>
												<jims2:sortResults beanName="outOfCountyCaseSearchForm" results="caseList" primaryPropSort="connectionId" primarySortType="STRING" sortId="4" />
											</td>
											<td><bean:message key="prompt.file" /><bean:message key="prompt.date"/>
												<jims2:sortResults beanName="outOfCountyCaseSearchForm" results="caseList" primaryPropSort="filingDate" primarySortType="DATE" sortId="5" />
											</td>
											<td><bean:message key="prompt.offense"/>
												<jims2:sortResults beanName="outOfCountyCaseSearchForm" results="caseList" primaryPropSort="offense" primarySortType="INTEGER" sortId="6" />
											</td>
											<td><bean:message key="prompt.DST"/>
												<jims2:sortResults beanName="outOfCountyCaseSearchForm" results="caseList" primaryPropSort="defendantStatus" primarySortType="STRING" sortId="7" />
											</td>
											<td><bean:message key="prompt.CST"/>
												<jims2:sortResults beanName="outOfCountyCaseSearchForm" results="caseList" primaryPropSort="caseStatus" primarySortType="STRING" sortId="8" />												
											</td>
											<td><bean:message key="prompt.INS"/>
												<jims2:sortResults beanName="outOfCountyCaseSearchForm" results="caseList" primaryPropSort="instrumentTypeId" primarySortType="STRING" sortId="9" />												
											</td>
											<td><bean:message key="prompt.disp"/>&nbsp;<bean:message key="prompt.type"/>
												<jims2:sortResults beanName="outOfCountyCaseSearchForm" results="caseList" primaryPropSort="dispositionTypeId" primarySortType="STRING" sortId="10" />												
											</td>
											<td><bean:message key="prompt.disp"/>&nbsp;<bean:message key="prompt.date"/>
												<jims2:sortResults beanName="outOfCountyCaseSearchForm" results="caseList" primaryPropSort="dispositionDate" primarySortType="DATE" sortId="11" />												
											</td>
										</tr>
										<%int RecordCounter = 0;
										String bgcolor = "";%>  
										<logic:notEmpty name="outOfCountyCaseSearchForm" property="caseList">	
											<logic:iterate id="caseListIndex" name="outOfCountyCaseSearchForm" property="caseList">
											<pg:item>
												<tr
												class=<%RecordCounter++;
													bgcolor = "alternateRow";
													if (RecordCounter % 2 == 1)
														bgcolor = "normalRow";
													out.print(bgcolor);%>>
													<td width="1%"><input type="radio" name="primaryCaseKey" value="<bean:write name="caseListIndex" property="primaryKey" />" onclick="validateRadio('<bean:write name="caseListIndex" property="reactivateInd" />','<bean:write name="caseListIndex" property="cdi" />','<bean:write name="caseListIndex" property="caseNum" />')"></td>
													<td><bean:write name="caseListIndex" property="cdi" /></td>
													<td><a href="/<msp:webapp/>handleOutOfCountyCaseSelection.do?submitAction=<bean:message key="button.view"/>&cdi=<bean:write name="caseListIndex" property="cdi" />&caseNum=<bean:write name="caseListIndex" property="caseNum" />"><bean:write name="caseListIndex" property="caseNum" /></a></td>
													<td><bean:write name="caseListIndex" property="courtNum" /></td>
													<td><bean:write name="caseListIndex" property="connectionId" /></td>
													<td><bean:write name="caseListIndex" property="filingDate" formatKey="date.format.mmddyyyy" /></td>
													<td><bean:write name="caseListIndex" property="offense" /></td>
													<td><bean:write name="caseListIndex" property="defendantStatus" /></td>
													<td><bean:write name="caseListIndex" property="caseStatus" /></td>
													<td><bean:write name="caseListIndex" property="instrumentTypeId" /></td>
													<td><bean:write name="caseListIndex" property="dispositionTypeId" /></td>
													<td><bean:write name="caseListIndex" property="dispositionDate" formatKey="date.format.mmddyyyy" /></td>							
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
									<table border="0">
										<tr align="center">
											<td>
												<jims2:isAllowed requiredFeatures="CS-OOC-CREATE">	
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.createCase"></bean:message></html:submit>
												</jims2:isAllowed>
											</td>
											<td>
												<jims2:isAllowed requiredFeatures="CS-OOC-UPDATE">	
													<html:submit styleClass="hidden" styleId="Update Case" property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.updateCase"></bean:message></html:submit>
												</jims2:isAllowed>
											</td>
											<td>
												<jims2:isAllowed requiredFeatures="CS-OOC-REACTIVATE">	
													<html:submit styleClass="hidden" styleId="Reactivate Case" property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.reactivateCase"></bean:message></html:submit>
												</jims2:isAllowed>
											</td>
											
											<jims2:isAllowed requiredFeatures='<%=Features.CSCD_OOC_CLOSE_CASE%>'>  	
												<td>
													<html:submit styleClass="hidden" styleId="Close Case" property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.closeCase"></bean:message></html:submit>
												</td>
											</jims2:isAllowed>
											
											<jims2:isAllowed requiredFeatures='<%=Features.CSCD_OOC_UPDATE_CLOSURE%>'>  	
												<td>
													<html:submit styleClass="hidden" styleId="Update Closure" property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.updateClosure"></bean:message></html:submit>
												</td>
											</jims2:isAllowed>	
										</tr>
									</table>
                      				<table cellpadding="0" cellspacing="0">
										<tr align="center">
											<td>	
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																																		 			
											</td>
										</tr>										  	
									</table>
								<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table><br>
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

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>