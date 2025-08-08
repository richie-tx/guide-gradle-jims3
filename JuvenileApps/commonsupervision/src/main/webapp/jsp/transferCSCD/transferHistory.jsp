<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 06/20/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 03/10/2009 C Shimek          - #57787 revised prompts and field display order to match PT -->
<!-- 04/17/2012 R Capestani       - #65295 add alert Selected case does not have an active supervision order-->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%@ page import="naming.UIConstants" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - transferCSCD/transferHistory.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_court_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/sorttable.js"></script>
	<script type="text/javascript">

		function checkForSingleResult() {
		    var rbs = document.getElementsByName("transferCasesInfo.caseNumSelectedForTransferUpdate");
			if (rbs.length == 1){
				rbs[0].checked = true;
			}	
		}
		function checkSelectedForActive() {
	    	for (x=0; x<document.forms[0].elements.length; x++) {
	    		if (document.forms[0].elements[x].type == "radio" && document.forms[0].elements[x].checked == true){
	    			if (document.forms[0].elements[x+1].type == "hidden" && document.forms[0].elements[x+1].value == 'false') {
	    				alert("Selected case does not have an active supervision order");
	    				return false;
	    			}
	    		}
	    	}	
		    return true;
		}
		
	</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkForSingleResult();">
<html:form action="/viewTransferCaseHistoryAction" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Transfers/CSCD_Transfers.htm#|2">
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
							    <tiles:put name="tab" value="caseloadTab" />
						    </tiles:insert>
							<!--tabs end-->
						</td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
				<!-- BEGIN BLUE BORDER TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
						<tiles:insert page="../common/superviseeHeader.jsp" flush="true"></tiles:insert>	
<!-- END SUPERVISEE INFORMATION TABLE  -->	
					</td>
				</tr>	
<!-- BEGIN GREEN TABS TABLE -->		
				<tr>
					<td valign="top" align="center"> 
							<!--casefile tabs start-->
							<table width="98%" border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign="top">
										<!--tabs start-->
										<tiles:insert page="../common/caseloadCSCDSubTabs.jsp" flush="true">
										    <tiles:put name="tab" value="SuperviseeTab" />
									    </tiles:insert>
										<!--tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
							</table>
							<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign="top" align="center">
										<!-- BEGIN HEADING TABLE -->
										<div class="header">
											<bean:message key="title.CSCD"/>&nbsp;-&nbsp;
											<bean:message key="prompt.transfer"/>&nbsp;
											<bean:message key="prompt.history"/>
										</div>
										<!-- END HEADING TABLE -->										
										<!-- BEGIN PAGINATION HEADER TAG -->
										<bean:define id="paginationResultsPerPage" type="java.lang.String">
											<bean:message key="pagination.recordsPerPage"></bean:message>
										</bean:define> 
										<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
											maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
											<input type="hidden" name="pager.offset" value="<%= offset %>">
										<!-- END PAGINATION HEADER TAG -->
											<!-- BEGIN ERROR TABLE -->
											<table width="98%" align="center">							
												<tr>
													<td align="center" class="errorAlert"><html:errors></html:errors></td>
												</tr>		
											</table>
										    <!-- END ERROR TABLE -->
										    <div class="confirm"><bean:write name="superviseeForm" property="confirmMessage"/></div>
											<!-- BEGIN INSTRUCTION TABLE -->
											<table width="98%" border="0">
												<tr>
													<td>
														<ul>
															<li>Select a transfer record and click Update.</li>
														</ul>
													</td>
												</tr>
											</table>
											<!-- END INSTRUCTION TABLE -->

											<!-- BEGIN transfer history TABLE -->
											<table width="98%" cellpadding="2" cellspacing="1" border="0" class="borderTableBlue">
												<tr class="formDeLabel">
													<td width="25%" colspan="4"><bean:message key="prompt.harrisCountyCases"/> <bean:message key="prompt.transferredOut"/></td>
													<td colspan="2" align="center"><bean:message key="prompt.out"/></td>
													<td colspan="3" align="center"><bean:message key="prompt.in"/></td>
												</tr>
												<tr class="detailHead">
													<td></td>
													<td>
														<bean:message key="prompt.CDI"/>
														<jims2:sortResults beanName="superviseeForm" results="harrisCountyCases" primaryPropSort="cdi" primarySortType="STRING" defaultSortOrder="ASC" sortId="1" />		
													</td>											
													<td>
														<bean:message key="prompt.case#"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="harrisCountyCases" primaryPropSort="caseNum" primarySortType="STRING"  defaultSortOrder="ASC" defaultSort="true" sortId="2" />
	                                                </td>
													<td>
														<bean:message key="prompt.CRT"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="harrisCountyCases" primaryPropSort="courtNum" primarySortType="NUMERIC"  defaultSortOrder="ASC" sortId="3" />
	                                                </td>
													<td>
														<bean:message key="prompt.transferOutDate"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="harrisCountyCases" primaryPropSort="hcTransferOutDate" primarySortType="DATE"  defaultSortOrder="ASC" sortId="4" />
	                                                </td>
													<td>
														<bean:message key="prompt.otherCountyState"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="harrisCountyCases" primaryPropSort="receivingCountyStateName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="5" />
	                                                </td>
													<td>
														<bean:message key="prompt.transferInDate"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="harrisCountyCases" primaryPropSort="hcTransferInDate" primarySortType="DATE"  defaultSortOrder="ASC" sortId="6" />
	                                                </td>
													<td>
														<bean:message key="prompt.otherCountyStatePID"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="harrisCountyCases" primaryPropSort="otherCountyState" primarySortType="NUMERIC"  defaultSortOrder="ASC" sortId="7" />
	                                                </td>
													<td>
														<bean:message key="prompt.rejected"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="harrisCountyCases" primaryPropSort="rejectedAsStr" primarySortType="STRING"  defaultSortOrder="ASC" sortId="8" />
	                                                </td>
												</tr>
	                                        	<logic:notEmpty name="superviseeForm" property="harrisCountyCases">
													<logic:iterate id="harrisCountyCaseIndex" name="superviseeForm" property="harrisCountyCases" indexId="index1">
														<pg:item>
															<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																<td>
																	<html:radio idName="harrisCountyCaseIndex" value="caseSelectedKey" property="transferCasesInfo.caseNumSelectedForTransferUpdate" />
															 	 	<logic:equal name="harrisCountyCaseIndex" property="caseHasActiveSupervisionOrder" value="true">
																		<input type="hidden" name="hasActiveOrder" value="true" >
																	</logic:equal>
																	<logic:equal name="harrisCountyCaseIndex" property="caseHasActiveSupervisionOrder" value="false">
																		<input type="hidden" name="hasActiveOrder" value="false" >
																	</logic:equal> 
																</td>
																<td><bean:write name="harrisCountyCaseIndex" property="cdi"/></td>
																<td>
																	<%-- 
																	<a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<bean:write name="harrisCountyCaseIndex" property="supervisionOrderId"/>')">																																						
																		<bean:write name="harrisCountyCaseIndex" property="caseNum"/>
																	</a>
																	--%>
																	<bean:write name="harrisCountyCaseIndex" property="caseNum"/>
																</td>
																<td><bean:write name="harrisCountyCaseIndex" property="courtNum"/></td>
																<td><bean:write name="harrisCountyCaseIndex" property="hcTransferOutDate"/></td>
																<td><bean:write name="harrisCountyCaseIndex" property="receivingCountyStateName"/></td>
																<td><bean:write name="harrisCountyCaseIndex" property="hcTransferInDate"/></td>
																<td><bean:write name="harrisCountyCaseIndex" property="otherCountyStatePersonIdNumber"/></td>
																<td>
																	<logic:equal name="harrisCountyCaseIndex" property="rejectedAsStr" value="Y">
																		<bean:message key="prompt.yes"/>
																	</logic:equal>
																	<logic:notEqual name="harrisCountyCaseIndex" property="rejectedAsStr" value="Y">
																		<bean:message key="prompt.no"/>
																	</logic:notEqual>
																	&nbsp;
																</td>
															</tr>
				                                       </pg:item>
		 	                                      	</logic:iterate>
	                                       		</logic:notEmpty>        
											</table>
											<br>
											<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
												<tr class="formDeLabel">
													<td width="25%" colspan="3"><bean:message key="prompt.courtesyCases"/> <bean:message key="prompt.transferredIn"/></td>
													<td colspan="3" align="center"><bean:message key="prompt.in"/></td>
													<td colspan="2" align="center"><bean:message key="prompt.out"/></td>
												</tr>
												<tr class="detailHead">
													<td>
														<bean:message key="prompt.CDI"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="courtesyCases" primaryPropSort="cdi" primarySortType="STRING"  defaultSortOrder="ASC" sortId="1"/>
	                                                 </td>
													<td>
														<bean:message key="prompt.case#"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="courtesyCases" primaryPropSort="caseNum" primarySortType="STRING"  defaultSortOrder="ASC" defaultSort="true" sortId="2" />
	                                                </td>
													<td>
														<bean:message key="prompt.CRT"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="courtesyCases" primaryPropSort="courtNum" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" />
	                                                </td>
													<td>
														<bean:message key="prompt.transferInDate"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="courtesyCases" primaryPropSort="outOfCountyTransferInDate" primarySortType="DATE"  defaultSortOrder="ASC" sortId="4" />
	                                                </td>
													<td>
														<bean:message key="prompt.otherCountyState"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="courtesyCases" primaryPropSort="transferringCountyStateName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="5" />
	                                                </td>
													<td>
														<bean:message key="prompt.otherCountyStatePID"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="courtesyCases" primaryPropSort="otherCountyStatePersonIdNumber" primarySortType="NUMERIC"  defaultSortOrder="ASC" sortId="6" />
	                                                </td>
													<td>
														<bean:message key="prompt.transferOutDate"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="courtesyCases" primaryPropSort="outOfCountyTransferOutDate" primarySortType="DATE"  defaultSortOrder="ASC" sortId="7" />
	                                                </td>
													<td>
														<bean:message key="prompt.closureReason"/>
	                                                    <jims2:sortResults beanName="superviseeForm" results="courtesyCases" primaryPropSort="courtNum" primarySortType="STRING"  defaultSortOrder="ASC" sortId="8" />
	                                                </td>
												</tr>
		                                        <logic:notEmpty name="superviseeForm" property="courtesyCases">
													<logic:iterate id="courtesyCasesIndex" name="superviseeForm" property="courtesyCases" indexId="index2">
														<pg:item>
															<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																<td><bean:write name="courtesyCasesIndex" property="cdi"/></td>
																<td>
																	<%-- 
																	<a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<bean:write name="harrisCountyCaseIndex" property="supervisionOrderId"/>')">																																						
																		<bean:write name="courtesyCasesIndex" property="caseNum"/>
																	</a>
																	--%>
																	<bean:write name="courtesyCasesIndex" property="caseNum"/>
																</td>																
																<td><bean:write name="courtesyCasesIndex" property="courtNum"/></td>
																<td><bean:write name="courtesyCasesIndex" property="outOfCountyTransferInDate"/></td>
																<td><bean:write name="courtesyCasesIndex" property="transferringCountyStateName"/></td>
																<td><bean:write name="courtesyCasesIndex" property="otherCountyStatePersonIdNumber"/></td>
																<td><bean:write name="courtesyCasesIndex" property="outOfCountyTransferOutDate"/></td>
																<td><bean:write name="courtesyCasesIndex" property="outOfCountyClosureReason"/></td>
															</tr>
				                                        </pg:item>
			                                        </logic:iterate>
		                                        </logic:notEmpty>
											</table>
											<div class="spacer4px"></div>
											<!-- END BEGIN transfer history TABLE -->
	
											<!-- Begin Pagination navigation Row-->
											<table align="center">
												<tr><td>
													<pg:index>
														<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
															<tiles:put name="pagerUniqueName" value="pagerSearch"/>
															<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
														</tiles:insert>
													</pg:index>
												</td></tr>
											</table>
											<!-- End Pagination navigation Row-->														
										</pg:pager>	
										<div class="spacer4px"></div>
										<!--button start -->
										<table border="0" width="100%">
											<tr>
												<td align="center">
													<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
													<logic:notEmpty name="superviseeForm" property="harrisCountyCases">
											            <html:submit property="submitAction" onclick="return checkSelectedForActive() && disableSubmit(this, this.form);"> <bean:message key="button.update" /></html:submit> 
										            </logic:notEmpty>
     										        <html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</table>
										<!--button end -->
									</td>
								</tr>
							</table>
						<div class="spacer4px"></div>
						<!-- END DETAIL TABLE -->
					</td>
				</tr>
			</table>
			<br>
		</td>
	</tr>
</table>
<br>
		<!--casefile tabs end-->
<!-- END  TABLE -->

<!-- BEGIN NOTES>
	Back and Cancel not available when confirmation message is there
< END NOTES -->

</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
