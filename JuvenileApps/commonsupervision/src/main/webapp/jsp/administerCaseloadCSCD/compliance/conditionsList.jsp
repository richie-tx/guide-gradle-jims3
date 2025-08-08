<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/27/2007	 Debbie Williamson - Create JSP -->
<!-- 03/17/2009  C Shimek          - #57812 add pagination reset script call to filter button -->
<!-- 09/21/2009	 Clarence Shimek   - #61740 revised View Cases to use displayCaseNum for case number dispaly -->
<!-- 02/03/2010  RYoung            - #63779 added scrolling no more paging -->
<!-- 02/05/2010  Ldeen             - removed pager tag library and tags since paging was removed -->
<!-- 02/17/2010  C Shimek          - #64040 added notEmpty tag around unique condition display -->
<!-- 02/17/2010  C Shimek          - #64043 revised instructions to match PT -->
<!-- 02/22/2010  C Shimek          - removed resetPagination() call on Filter button, missed change in #63779 -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>  
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@page import="naming.Features"%>

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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/compliance/conditionsList.jsp</title>

<!-- JavaScripts -->
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/administerCompliance/conditionsList.js"></script>
<!-- FUNCTIONS FOR FILTER CONDITIONS GROUPS  -->
<script type="text/javascript">
function filterConditonSet()
{
	var group1Id = document.forms[0].group1Id.options[ document.forms[0].group1Id.selectedIndex].value;
	var group2IdSelect = "";
	if (group1Id > "")
	{
		updateGroup2(document.forms[0]);
		if (document.forms[0].group2Id.options.length > 1)
		{
			group2IdSelect = document.forms[0].group2IdSelect.value;
			for (x = 0; x < document.forms[0].group2Id.options.length; x++)
			{
				if (document.forms[0].group2Id.options[x].value == group2IdSelect)
				{
					document.forms[0].group2Id.selectedIndex = x;
					break;
				}
			}
		}
	}
	for (y =0; y < document.forms[0].showFilter.length; y++)
	{
		if (document.forms[0].showFilter[y].value == document.forms[0].showFilterSelected.value)
		{
			document.forms[0].showFilter[y].checked = true;
		}
	}	
}
function subgroup(id, name)
{
	this.id = id;
	this.name = name;
	this.subgroups = new Array();
}

var groups = new Array(); //the array of group1
function updateGroup2(theForm)
{
	var group1Id = theForm.group1Id.options[theForm.group1Id.selectedIndex].value;
	theForm.group2Id.options.length = 0;
	theForm.group2Id.options[0] = new Option( "Please Select", "", false, false );
	if(theForm.group1Id.selectedIndex == 0)
	{
		theForm.group2Id.selectedIndex = 0; //reset choice back to default
		theForm.group2Id.value="";
		theForm.group2Id.disabled = true; //disable group2 choice
		
		return;
	}
	else
	{
		for(i in groups)
		{
			if(groups[i].id == group1Id)
			{
				for(j in groups[i].subgroups)
				{
					//alert(groups[i].subgroups[j].id+":"+groups[i].subgroups[j].name);
					theForm.group2Id.options[theForm.group2Id.options.length] = new Option( groups[i].subgroups[j].name, groups[i].subgroups[j].id);
				}
			}
		}
		if(theForm.group2Id.options.length>1){
			theForm.group2Id.disabled = false;
			theForm.group2Id.value="";
		}
		else{
			theForm.group2Id.selectedIndex = 0; //reset choice back to default
			theForm.group2Id.value="";
			theForm.group2Id.disabled = true;
		}
	}	
}

<logic:iterate indexId="groupIterIndex" id="groupIter" name="complianceForm" property="groups">
	groups[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="groupId" />", "<bean:write name="groupIter" property="name" filter="false" />");
	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="subgroups">
		var innerGroup = new subgroup("<bean:write name="groupIter2" property="groupId" />", "<bean:write name="groupIter2" property="name" filter="false" />");
		groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>] = innerGroup;
	</logic:iterate>
</logic:iterate>
</script>  
</head>

<body topmargin="0" leftmargin="0" onload="filterConditonSet()" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleComplianceConditions" target="content" focus="group1Id">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Administer_Compliance/CSCD_Compliance.htm#|1">
<!-- input type="hidden" name="helpFile" value="commonsupervision/Administer_Compliance/Common_Sup_Compliance_and_Casenotes.htm#|1"-->

<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
					   	<%-- 	<tiles:put name="tabid" value="conplianceTab"/> --%>
				     	</tiles:insert>					
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>js/images/spacer.gif" height="5"></td> 
				</tr>
			</table>
<!-- END BLUE TABS TABLE -->  
<!-- BEGIN BLUE BORDER TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
						<tiles:insert page="../../common/superviseeInfoForComplianceHeader.jsp" flush="true"></tiles:insert>	
<!-- END SUPERVISEE INFORMATION TABLE  -->	
					</td>
				</tr>	
<!-- BEGIN GREEN TABS TABLE -->		
				<tr>
					<td valign="top" align="center"> 
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>						
							<tr>
								<td valign="top">
									<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
					   				 	<tiles:put name="tab" value="ComplianceTab"/> 
						     		</tiles:insert>					
								</td>
							</tr>
							<tr>
								<td  bgcolor="#33cc66"><img src="/<msp:webapp/>js/images/spacer.gif" height="5"></td> 
							</tr>
						</table>
					</td>
				</tr>		
<!-- END GREEN TABS TABLE -->				
				<tr>
					<td valign="top" align="center">
<!-- BEGIN GREEN BORDER TABLE -->					
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">
												<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.compliance" />&nbsp;-
												Select Conditions
											</td>	
										</tr>
									</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
									<table width="98%" align="center">							
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>		
									</table>
<!-- END ERROR TABLE -->
<!-- BEGIN CONFIRMATION TABLE -->
							        <logic:notEqual name="complianceForm" property="confirmMessage" value="">  	  	
										<table width="98%" align="center">							
											<tr>
									        	<td align="center" class="confirm"><bean:write name="complianceForm" property="confirmMessage" /></td>
											</tr>		
										</table>
							    	</logic:notEqual>  
<!-- BEGIN CONFIRMATION TABLE -->    	
<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Select 1 or more conditions, click appropriate button below.</li>
												</ul>
											</td>
										</tr>
									</table>
<!-- END INSTRUCTION TABLE -->
								</td>											
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
<!-- BEGIN FILTER CONDTIONS TABLE -->
			 						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td class="detailHead"><bean:message key="prompt.filterConditions"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
								 					<tr>
														<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.group1"/></td>
														<td class="formDe">
												        	<html:select name="complianceForm" property="group1Id" size="1" onchange="updateGroup2(this.form);"> 
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<html:optionsCollection property="groups" value="groupId" label="name" /> 
															</html:select>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.group2"/></td>
														<td class="formDe">
															<logic:empty name="complianceForm" property="group2">
																<html:select property="group2Id" disabled="true">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																</html:select>
															</logic:empty>
															<logic:notEmpty name="complianceForm" property="group2">
																<html:select property="group2Id" size="1">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection property="group2" value="groupId" label="name" /> 
																</html:select>
															</logic:notEmpty>
															<input type="hidden" name="group2IdSelect" value="<bean:write name="complianceForm" property="group2Id" />" >
														</td>
													</tr>  
									 				<tr>
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.case#"/></td>
														<td class="formDe"> 
															<logic:empty name="complianceForm" property="caseNumbers">
																<html:select property="group2Id" disabled="true">
																<html:option value=""><bean:message key="select.generic" /></html:option>
																</html:select>
															</logic:empty>
															<logic:notEmpty name="complianceForm" property="caseNumbers">
				                            					<html:select name="complianceForm" property="selectedCaseNumbers" size="3" multiple="true">
																	<html:option value=""><bean:message key="select.generic" /> One or More</html:option>
																	<html:optionsCollection property="caseNumbers" value="key" label="value" />
																</html:select>
																<input type="hidden" name="clearSelectedCaseNumbers" value="true">																
															</logic:notEmpty>											
														</td>
													</tr> 
									 				<tr>
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.show"/></td>
														<td class="formDe">
															<input type="radio" name="showFilter" value="false"><img src="/<msp:webapp/>images/redLight.gif" name="redLight" title="Out of Compliance" border="0">
															<input type="radio" name="showFilter" value="true"><img src="/<msp:webapp/>images/greenLight.gif" name="greenLight" title="In Compliance" border="0">
															<input type="radio" name="showFilter" value="">Both
															<input type="hidden" name="showFilterSelected" value="<bean:write name="complianceForm" property="showFilter" />" >
														</td>
													</tr>  
													<tr>
														<td class="formDeLabel"></td>
														<td class="formDe">
						                    				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.filter" /></html:submit> 
						                    				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.viewAll" /></html:submit> 																			
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
<!--END FILTER CONDITIONS TABLE -->
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>	
<!--  BEGIN VARIABLES FOR DISPLAY -->
					<% int RecordCounter=0;
		      		   String bgcolor = "";
		      		   String fontColor = "";
		      		   int likeCtr=0; 
				       int caseCtr=0;%>
<!--  END VARIABLES FOR DISPLAY -->				       
							<tr>
								<td valign="top" align="center">	
<!-- BEGIN CONDITIONS TABLE -->		
 									<table width="98%" border="0" cellspacing="0" cellpadding="2"  class="borderTableBlue">
										<tr>
											<td class="detailHead">Supervisee Conditions</td>
										</tr>
										<logic:empty name="complianceForm" property="likeConditions">
											<logic:empty name="complianceForm" property="uniqueConditions">
												<bean:define id="noConditions" value="" />
												<tr>
													<td>
														<table width="100%" border="0" cellspacing="1" class="borderTableGrey">
															<tr>
																<td>No Conditions found to display</td>
															</tr>
														</table>
													</td>			
												</tr>
											</logic:empty>
										</logic:empty>
										<logic:notEmpty name="complianceForm" property="likeConditions">
										<logic:iterate id="likeCondIndex" name="complianceForm" property="likeConditions" > 
											<% likeCtr++; %>
											<% caseCtr=0; %>
											<logic:iterate id="condIndex" name="likeCondIndex" property="complianceConditionResponseEvents" length="1">
<!--  BEGIN LIKE CONDITIONS - SINGLE CONDTION VIEW -->							
									 			<tr id="condView<% out.print(likeCtr); %>" class="visible">
													<td>	
														<table width="100%" border="0" cellspacing="1" class="borderTableGrey">
															<bean:size id="likeCasesSize" name="likeCondIndex" property="complianceConditionResponseEvents" />	
															<tr class="likeConditionGroup">
																<td colspan="2">This Condition is used in <bean:write name="likeCasesSize"/> cases. Click to <a href="javascript:show('condView<% out.print(likeCtr); %>', 0); show('caseView<% out.print(likeCtr); %>', 1);">View Cases</a>.</td>
															</tr> 
															<tr>  
																<td width="1%">
																	<table cellpadding="0" cellspacing="0">
																		<tr>
																			<td valign="top">
																				<input type="checkbox" id="LG<%out.print(likeCtr); %>" onclick="checkAllConditions('<%out.print(likeCtr); %>',<bean:write name="likeCasesSize"/>, this)">
																				<input type="hidden" name="LG<%out.print(likeCtr); %>" value="<bean:write name='condIndex' property='compliant'/>" >
																			</td>
																			<logic:equal name="condIndex" property="compliant" value="false">
																				<td><img src="/<msp:webapp/>images/redLight.gif" title="Out of Compliance" border="0"></td>
																			</logic:equal>
																			<logic:equal name="condIndex" property="compliant" value="true">
																				<td><img src="/<msp:webapp/>images/greenLight.gif" title="In Compliance" border="0"></td>
																			</logic:equal>
																			<logic:notEqual name="condIndex" property="ncCount" value="0">
																				<td width="1%" title="Noncompliance Count">&nbsp;<bean:write name='condIndex' property='ncCount'/></td>
																			</logic:notEqual>	
																			<logic:equal name="condIndex" property="ncCount" value="0">
																				<td width="1%"><font color="#ffffff">&nbsp;<bean:write name='condIndex' property='ncCount'/></font>
																				</td>
																			</logic:equal>	
																		</tr>
																	</table>
																</td>
																<td class="subHead">
																	<table cellpadding="0" cellspacing="0" width="100%">
																		<tr>
				  															<td width="1%"><a href="javascript:showHide('condition<%out.print(likeCtr); %>','row','/<msp:webapp/>')"><img border="0" src="/<msp:webapp/>images/expand.gif" name="condition<%out.print(likeCtr); %>" ></a>&nbsp;</td>
				 															<td class="boldText"><a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Link&sprOrderConditionId=<bean:write name='condIndex' property='sprOrderConditionId'/>')" title=""><bean:write name='condIndex' property='orderConditionName'/></a></td> 
																			<td align="right"><a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Info&chainNum=<bean:write name='condIndex' property='orderChainNumber'/>&caseNumber=<bean:write name='condIndex' property='caseNumber'/>&conditionId=<bean:write name='condIndex' property='conditionId'/>')" title="View Casenotes details">View Casenotes</a></td>
																		</tr>
																	</table>
																</td>
															</tr>
<!-- BEGIN POLICY DISPLAYS FOR LIKE CONDITIONS - SINGLE CONDITION VIEW -->	
															<tr class="hidden" id="condition<%out.print(likeCtr); %>Span"> 
																<td></td>
																<td class="subToCondition">
																	<table width="100%" border="0">
																		<tr>
																			<td width="1%" class="subhead"><a href="javascript:showHide('assocCrtPolExpand<%out.print(likeCtr); %>','row','/<msp:webapp/>')"><img src="/<msp:webapp/>images/expand.gif" name="assocCrtPolExpand<%out.print(likeCtr); %>" border="0"></a></td>
																			<td class="subhead"><bean:message key="prompt.associatedCourtPolicies" /></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td>
																				<table width="100%" border="0" cellspacing="1" id="assocCrtPolExpand<%out.print(likeCtr); %>Span" class="hidden">
																					<tr class="formDeLabel">
																						<td><bean:message key="prompt.courtPolicyName" /></td>
																						<td width="1%">F/M</td>
																					</tr>
																					<logic:present name="likeCondIndex" property="courtPolicies">
																						<logic:iterate id="cpIterater" name="likeCondIndex" property="courtPolicies">
																							<tr>
																								<td class="subHead"><a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Details&courtPolicyId=<bean:write name='cpIterater' property='courtPolicyId'/>')" title=""><bean:write name='cpIterater' property='courtPolicyName'/></a></td>
																								<td align="center"><bean:write name='condIndex' property='offenseIndicator' /></td>
																							</tr>
																						</logic:iterate>	
																					</logic:present>	
																				</table>
																			</td>
																		</tr> 
																		<tr>
																			<td width="1%" class="subhead"><a href="javascript:showHide('assocDeptPolExpand<%out.print(likeCtr); %>','row','/<msp:webapp/>')"><img src="/<msp:webapp/>images/expand.gif" name="assocDeptPolExpand<%out.print(likeCtr); %>" border="0"></a></td>
																			<td class="subhead">Associated Department Policies</td>
																		</tr>
																		<tr>
																			<td></td>
																			<td>
																				<table width="100%" border="0" id="assocDeptPolExpand<%out.print(likeCtr); %>Span" class="hidden">
																					<tr class="formDeLabel">
																						<td>Department Policy Name</td>
																						<td width="1%">F/M</td>
																					</tr>
																					<logic:present name="likeCondIndex" property="departmentPolicies">
																						<logic:iterate id="dpIterater" name="likeCondIndex" property="departmentPolicies">
																							<tr>
																								<td class="subhead"><a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=View&agencyPolicyId=<bean:write name='dpIterater' property='agencyPolicyId'/>')" title="" ><bean:write name='dpIterater' property='agencyPolicyName'/></a></td>
																								<td align="center"><bean:write name='condIndex' property='offenseIndicator' /></td>
																							</tr>
																						</logic:iterate>
																					</logic:present>	
																				</table> 
																			</td>
																		</tr> 
																	</table> 
																</td> 
															</tr>  
<!-- END POLICY DISPLAYS FOR LIKE CONDITIONS - SINGLE CONDITION VIEW -->											
														</table> 
													</td>
												</tr>
											</logic:iterate> 
<!-- END LIKE CONDITIONS - SINGLE CONDTION VIEW -->			
<!-- BEGIN LIKE CONDITIONS - CASES VIEW -->	
											<tr id="caseView<% out.print(likeCtr); %>" class="hidden">
												<td>	
													<table width="100%" border="0" cellspacing=1 class="borderTableGrey">
														<bean:size id="likeCasesSize" name="likeCondIndex" property="complianceConditionResponseEvents" />	
														<tr class="likeConditionGroup">
															<td colspan="2">This Condition is used in <bean:write name="likeCasesSize"/> cases. Click to <a href="javascript:show('condView<% out.print(likeCtr); %>', 1); show('caseView<% out.print(likeCtr); %>', 0);">Hide Cases</a>.</td>
														</tr>
														<tr>
															<td width="1%">
																<table cellpadding="0" cellspacing="0" width="100%"> 
																	<% RecordCounter=0;
														      		   bgcolor = "";
														      		   caseCtr = 0;%>
																	<logic:iterate id="casesIndex" name="likeCondIndex" property="complianceConditionResponseEvents">
																		<tr class=<% RecordCounter++;
																			bgcolor = "alternateRow";				
																			if (RecordCounter % 2 == 1)
																			{
																				bgcolor = "normalRow";
																			}	
																			out.print(bgcolor); %> >
																			<td>
																				<input type="checkbox" id="LG<% out.print(likeCtr);%>C<%out.print(RecordCounter); %>" name="selectedConditionIds" value="<bean:write name='casesIndex' property='sprOrderConditionId'/>" onclick="checkLikeConditions('<% out.print(likeCtr); %>', this)">
																				<input type="hidden" name="LG<% out.print(likeCtr);%>C<%out.print(RecordCounter); %>" value="<bean:write name='casesIndex' property='compliant'/>" >
																			</td>
																			<logic:equal name="casesIndex" property="compliant" value="false">
																				<td><img src="/<msp:webapp/>images/redLight.gif" title="Out of Compliance" border="0"></td>
																			</logic:equal>
																			<logic:equal name="casesIndex" property="compliant" value="true">
																				<td><img src="/<msp:webapp/>images/greenLight.gif" title="" border="0"></td>
																			</logic:equal> 
																			<logic:notEqual name="casesIndex" property="ncCount" value="0">
																				<td width="1%" title="Noncompliance Count">&nbsp;<bean:write name='casesIndex' property='ncCount'/>&nbsp;</font></td>
																			</logic:notEqual>	
																			<logic:equal name="casesIndex" property="ncCount" value="0">
																				<td width="1%">
																				 <font color=<% fontColor = "#f0f0f0";				
																						if (RecordCounter % 2 == 1)
																						{
																						fontColor = "#ffffff";
																						}	
																						out.print(fontColor); %> >&nbsp;<bean:write name='casesIndex' property='ncCount'/>&nbsp;</font>
																				</td>
																			</logic:equal>	
																			<td class="boldText" nowrap="nowrap"><a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Link&sprOrderConditionId=<bean:write name='casesIndex' property='sprOrderConditionId'/>')" title="" ><bean:write name='casesIndex' property='orderConditionName'/></a></td> 
																			<td align="right" nowrap="nowrap">&nbsp;
																				<a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Info&chainNum=<bean:write name='casesIndex' property='orderChainNumber'/>&caseNumber=<bean:write name='casesIndex' property='caseNumber'/>&conditionId=<bean:write name='casesIndex' property='conditionId'/>')" title="View Casenotes details">View Casenotes</a>
																			</td>
																			<td width="1%" nowrap="nowrap">
																				<jims:isAllowed requiredFeatures="<%=Features.CS_COMPLIANCE_DECREMENT%>">
																					<logic:notEqual name='casesIndex' property='ncCount' value='0'>	
																						&nbsp;| <a href="/<msp:webapp/>handleComplianceConditions.do?submitAction=GO&conditionId=<bean:write name='casesIndex' property='sprOrderConditionId'/>" title="">Decrement</a>&nbsp;
																					</logic:notEqual> 
																				</jims:isAllowed>	
																				<logic:equal name='casesIndex' property='ncCount' value='0'>
																				 	&nbsp;<img src="/<msp:webapp/>images/spacer.gif">&nbsp;
																				</logic:equal> 
																			</td> 
																			<td>CRT:<bean:write name="casesIndex" property="courtId" />&nbsp;Case:<bean:write name="casesIndex" property="displayCaseNum"/></td> 
																		</tr> 
																	</logic:iterate> 
																</table> 
															</td>
														</tr>
<!-- BEGIN POLICY DISPLAYS FOR LIKE CONDITIONS - CASE VIEW -->										
													 	<tr>
															<td class="subToCondition">
																	<table width="100%" border="0">
															  			<tr>
																			<td width="1%" class="subhead"><a href="javascript:showHide('assocCrtPol2Expand<%out.print(likeCtr); %>','row','/<msp:webapp/>')"><img src="/<msp:webapp/>images/expand.gif" name="assocCrtPol2Expand<%out.print(likeCtr); %>" border="0"></a></td>
																			<td class="subhead"><bean:message key="prompt.associatedCourtPolicies" /></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td>
																				<table width="100%" border="0" cellspacing="1" id="assocCrtPol2Expand<%out.print(likeCtr); %>Span" class="hidden">
																					<tr class="formDeLabel">
																						<td><bean:message key="prompt.courtPolicyName" /></td>
																						<td width="1%">F/M</td>
																					</tr>
																						<logic:present name="likeCondIndex" property="courtPolicies">
																							<logic:iterate id="cpIter" name="likeCondIndex" property="courtPolicies">
																								<tr>
																									<td class="subhead"><a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Details&courtPolicyId=<bean:write name='cpIter' property='courtPolicyId'/>')" title=""><bean:write name='cpIter' property='courtPolicyName'/></a></td>
																									<td align="center"><bean:write name='likeCondIndex' property='offenseIndicator'/></td>
																								</tr>
																							</logic:iterate>	
																						</logic:present>	
																				</table>
																			</td>
																		</tr> 
																		<tr>
																			<td width="1%" class="subhead"><a href="javascript:showHide('assocDeptPol2Expand<%out.print(likeCtr); %>','row','/<msp:webapp/>')"><img src="/<msp:webapp/>images/expand.gif" name="assocDeptPol2Expand<%out.print(likeCtr); %>" border="0"></a></td>
																			<td class="subhead">Associated Department Policies</td>
																		</tr>
																		<tr>
																			<td></td>
																			<td>
																				<table width="100%" border="0" id="assocDeptPol2Expand<%out.print(likeCtr); %>Span" class="hidden">
																					<tr class="formDeLabel">
																						<td>Department Policy Name</td>
																						<td width="1%">F/M</td>
																					</tr>
																					<logic:present name="likeCondIndex" property="departmentPolicies">
																							<logic:iterate id="dpIter" name="likeCondIndex" property="departmentPolicies">
																								<tr>
																									<td class="subhead"><a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=View&agencyPolicyId=<bean:write name='dpIter' property='agencyPolicyId'/>')" title="" ><bean:write name='dpIter' property='agencyPolicyName'/></a></td> 
																									<td align="center"><bean:write name='likeCondIndex' property='offenseIndicator'/></td>
																								</tr>
																							</logic:iterate>
																					</logic:present>	
																				</table>
																			</td>
																		</tr> 
																	</table> 
																</td> 
															</tr> 
<!-- END POLICY DISPLAYS FOR LIKE CONDITIONS - CASE VIEW -->											
														</table>
													</td>
												</tr> 	
											</logic:iterate>  
<!-- END LIKE CONDITIONS - CASE VIEW -->	
										</logic:notEmpty>
<!-- BEGIN UNIQUE CONDITOINS DISPLAY TABLE -->	
										<logic:notEmpty name="complianceForm" property="uniqueConditions">
										<logic:notPresent name="noConditions">
 										<tr>
											<td>	
												<div class="scrollingDiv400">										
												<table width="100%" border="0" cellspacing="0" class="borderTableGrey">
													<%RecordCounter=0;
										      		  bgcolor = "";%>
<!-- BEGIN UNIQUE CONDITIONS DISPLAY LOOP -->
<!-- BEGIN PAGINATION HEADER TAG -->
													<!-- END PAGINATION HEADER TAG -->	
													<logic:iterate id="uCondIndex" name="complianceForm" property="uniqueConditions">
					 									
					 									<tr>
															<td width="1%">
																<table cellpadding="0" cellspacing="0">
																	<tr class=<% RecordCounter++;
																		bgcolor = "alternateRow";				
																		if (RecordCounter % 2 == 1)
																		{
																			bgcolor = "normalRow";
																		}	
																		out.print(bgcolor); %> >
																		<td valign="top" width="1%"> 
																 			<input type="checkbox" name="selectedConditionIds" value="<bean:write name='uCondIndex' property='sprOrderConditionId'/>" id="U<% out.print(RecordCounter); %>" > 
																 			<input type="hidden" name="U<%out.print(RecordCounter); %>" value="<bean:write name='uCondIndex' property='compliant'/>" >
																		</td>
													 		 			<logic:equal name="uCondIndex" property="compliant" value="false">
																			<td width="1%"><img src="/<msp:webapp/>images/redLight.gif" name="redLight" title="Out of Compliance" border="0"></td>
																		</logic:equal>
																		<logic:equal name="uCondIndex" property="compliant" value="true">
																			<td width="1%"><img src="/<msp:webapp/>images/greenLight.gif" name="greenLight" title="In Compliance" border="0"></td>
																		</logic:equal>
																		<logic:notEqual name="uCondIndex" property="ncCount" value="0">
																			<td width="1%" title="Noncompliance Count">&nbsp;<bean:write name='uCondIndex' property='ncCount'/></td>
																		</logic:notEqual>	
																		<logic:equal name="uCondIndex" property="ncCount" value="0">
																			<td width="1%">
																			 <font color=<% fontColor = "#f0f0f0";				
																					if (RecordCounter % 2 == 1)
																					{
																					fontColor = "#ffffff";
																					}	
																					out.print(fontColor); %> >&nbsp;<bean:write name='uCondIndex' property='ncCount'/></font>
																			</td>
																		</logic:equal>	
																	</tr>
													 			</table>
															</td>
															<td class="subHead">
																<table cellpadding="2" cellspacing="0" width="100%"> 
																	<tr class="<% out.print(bgcolor); %>">  
																		<td width="1%" nowrap="nowrap"><a href="javascript:showHide('ucondition<%out.print(RecordCounter); %>','row','/<msp:webapp/>')"><img border="0" src="/<msp:webapp/>images/expand.gif" name="ucondition<%out.print(RecordCounter); %>"></a></td> 
													 					<td class="boldText" width="97%" nowrap="nowrap">
													 						<a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Link&sprOrderConditionId=<bean:write name='uCondIndex' property='sprOrderConditionId'/>')" title=""><bean:write name='uCondIndex' property='orderConditionName'/></a>
													 					</td> 
																		<td width="1%" nowrap="nowrap">
																			<a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Info&chainNum=<bean:write name='uCondIndex' property='orderChainNumber'/>&caseNumber=<bean:write name='uCondIndex' property='caseNumber'/>&conditionId=<bean:write name='uCondIndex' property='conditionId'/>')" title="View Casenotes details">View Casenotes</a>
																		</td>
																		<td width="1%" nowrap="nowrap">	
																			<jims:isAllowed requiredFeatures="<%=Features.CS_COMPLIANCE_DECREMENT%>">
																				<logic:notEqual name='uCondIndex' property='ncCount' value='0'>
																				 | <a href="/<msp:webapp/>handleComplianceConditions.do?submitAction=GO&conditionId=<bean:write name='uCondIndex' property='sprOrderConditionId'/>" title="">Decrement</a>
																				</logic:notEqual> 
																			</jims:isAllowed>
																		</td>	
																	</tr>
										 						</table>
															</td>  
														</tr>  
<!--  BEGIN COURT AND DEPARTMENT POLICIES DISPLAY FOR UNIQUE CONDITIONS -->						
									 					<tr class="hidden" id="ucondition<%out.print(RecordCounter); %>Span"> 
														 	<td></td> 
															<td class="subToCondition">
																<table width="100%" cellpadding="0" cellspacing="0" border="0">
															  		<tr>
																		<td width="1%" class="subhead" valign="top"><a href="javascript:showHide('crtPolExpand<%out.print(RecordCounter); %>','row','/<msp:webapp/>')"><img src="/<msp:webapp/>images/expand.gif" name="crtPolExpand<%out.print(RecordCounter); %>" border="0"></a>&nbsp;</td>
																		<td class="subhead"><bean:message key="prompt.associatedCourtPolicies" /></td>
																	</tr>
																	<tr id="crtPolExpand<%out.print(RecordCounter); %>Span" class="hidden">
																		<td></td>
																		<td>
																			<table width="100%" border="0" cellspacing="1" >
																				<tr class="formDeLabel">
																					<td><bean:message key="prompt.courtPolicyName" /></td>
																					<td width="1%">F/M</td>
																				</tr>
																			<logic:present name="uCondIndex" property="courtPolicies">
																					<logic:iterate id="ucpIterater" name="uCondIndex" property="courtPolicies">
																						<tr>
																							<td class="subhead"><a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Details&courtPolicyId=<bean:write name='ucpIterater' property='courtPolicyId'/>')" title=""><bean:write name='ucpIterater' property='courtPolicyName'/></a></td>
																							<td align="center"><bean:write name='uCondIndex' property='offenseIndicator'/></td>
																						</tr>
																					</logic:iterate>	
																				</logic:present>
																			</table>
																		</td>
																	</tr> 
																	<tr>
																		<td width="1%" class="subhead"><a href="javascript:showHide('deptPolExpand<%out.print(RecordCounter); %>','row','/<msp:webapp/>')"><img src="/<msp:webapp/>images/expand.gif" name="deptPolExpand<%out.print(RecordCounter); %>" border="0"></a></td>
																		<td class="subhead">Associated Department Policies</td>
																	</tr>
																	<tr id="deptPolExpand<%out.print(RecordCounter); %>Span" class="hidden">
																		<td></td>
																		<td>
																			<table width="100%" border="0" >
																				<tr class="formDeLabel">
																					<td>Department Policy Name</td>
																					<td width="1%">F/M</td>
																				</tr>
																				<logic:present name="uCondIndex" property="departmentPolicies">
																					<logic:iterate id="udpIterater" name="uCondIndex" property="departmentPolicies">
																						<tr>
																							<td class="subhead"><a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=View&agencyPolicyId=<bean:write name='udpIterater' property='agencyPolicyId'/>')" title="" ><bean:write name='udpIterater' property='agencyPolicyName'/></a></td> 																						
																							<td align="center"><bean:write name='uCondIndex' property='offenseIndicator'/></td>
																						</tr>
																					</logic:iterate>
																				</logic:present>	
																			</table>
																		</td>
																	</tr> 
																</table> 
															</td> 
														</tr> 
													
<!--  END COURT AND DEPARTMENT POLICIES DISPLAY FOR UNIQUE CONDITIONS -->																				
													</logic:iterate>
<!-- END UNIQUE CONDITIONS DISPLAY LOOP -->	  
												</table>
												</div>
											</td>
										</tr>
										</logic:notPresent>
										</logic:notEmpty>
<!-- BEGIN PAGINATION NAVIGATOIN ROW -->
										
<!-- END PAGINATION NAVIGATOIN ROW -->	
									
									</table>
									
<!-- END CONDITIONS TABLE -->
<!-- BEGIN LEGEND TABLE -->
								<table cellpadding="0" cellspacing="0" border="0" width="98%">
									<tr>
										<td class="legendSmallText"><img src="/<msp:webapp/>images/redLight.gif"> Condition is Noncompliant <img src="/<msp:webapp/>images/greenLight.gif"> Condition is In Compliance</td>
									</tr>
								</table>
<!-- END LEGEND TABLE -->					
<!-- BEGIN BUTTON TABLE  -->				
								<table align="center">
									<tr>
										<td align="center">
											<html:submit property="submitAction" onclick="return checkConditionSelects('N')&& disableSubmit(this, this.form);"><bean:message key="button.resolveNoncompliance" /></html:submit> 
											<html:submit property="submitAction" onclick="return checkConditionSelects('C')&& disableSubmit(this, this.form);"><bean:message key="button.setToNoncompliant" /></html:submit> 
										</td>
									</tr>
									<tr>
										<td align="center">
									<%-- 		<html:submit property="submitAction"><bean:message key="button.createGeneralCasenote" /></html:submit>  --%>
									 		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.casenotes" /></html:submit>  									
											<html:submit property="submitAction" onclick="return checkConditionSelects('') && disableSubmit(this, this.form);"><bean:message key="button.createConditionCasenote" /></html:submit> 
										</td>
									</tr>
									<tr>
										<td align="center">
									        <logic:notEqual name="complianceForm" property="confirmMessage" value="">  	  	
												<input type="Reset" value="Reset">									        
									    	</logic:notEqual>  										
									        <logic:equal name="complianceForm" property="confirmMessage" value="">  	  	
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back" /></html:submit>
												<input type="Reset" value="Reset">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel" /></html:submit>								
									    	</logic:equal>  										
										</td>
									</tr>
								</table>
<!-- END BUTTON TABLE -->				
							</td>
						</tr>
					</table>
<!-- END GREEN BORDER TABLE -->
				</td>
			</tr>
			<tr>
				<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
			</tr>			
		</table>	
<!-- END BLUE BORDER TABLE -->			
	</td>
</tr>
</table>
<!-- END  TABLE -->

</div>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>