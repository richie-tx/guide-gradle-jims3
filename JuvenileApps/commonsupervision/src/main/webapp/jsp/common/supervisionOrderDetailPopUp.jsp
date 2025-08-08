<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@page import="naming.UIConstants"%>
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
<title>Common Supervision - common/supervisionOrderDetailPopUp.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>

<body topmargin=0 leftmargin="0">
<html:form action="/displaySupervisionOrderDetails" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign=top>
			
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				
				<tr>
					<td valign=top align=center>
						<!-- BEGIN HEADING TABLE -->
							<table width=98%>
								<tr>
									<td align="center" class="header">
											<bean:message key="title.processSupervisionOrder" />&nbsp;-&nbsp;<bean:message key="prompt.view" />&nbsp;<bean:message key="prompt.version" />
								
									</td>
								 </tr>	
								 <tr>
								 <td>
								 	<br>
								 </td>
								 </tr>	
							</table>									
						<!-- END HEADING TABLE -->								
						<!-- BEGIN ERROR TABLE -->
							<table width=98% align=center>							
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->
						
						<!-- BEGIN DETAIL HEADER TABLE -->
																									
							<tiles:insert page="../processSupervisionOrder/caseOrderHeaderTile.jsp" flush="true"></tiles:insert>
						
						<!-- END DETAIL HEADER TABLE -->
							
						<!-- BEGIN OTHER VERSION TABLE -->
								<logic:notEmpty name="supervisionOrderForm" property="orderVersionList">
								<br>
								<table width=98% border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
									<tr class=detailHead>
										<td class=detailHead><bean:message key="prompt.otherVersions" /></td>                          
									</tr>
									<tr>
										<td>
											<table width="100%" cellpadding="2" cellspacing="1">
												<tr class=formDeLabel>
													<td><bean:message key="prompt.orderActivated" /></td>
													<td><bean:message key="prompt.version" /></td>
													<td><bean:message key="prompt.orderTitle" /></td>
												</tr>
												<%int RecordCounter = 0;
												String bgcolor = "";%>
												<logic:iterate id="orderVersionListIndex" name="supervisionOrderForm" property="orderVersionList">
													<tr
													class=<%RecordCounter++;
														bgcolor = "alternateRow";
														if (RecordCounter % 2 == 1)
															bgcolor = "normalRow";
														out.print(bgcolor);%>>
											
														<td>
															<bean:write name="orderVersionListIndex" property="activationDate" formatKey="datetime.format.mmddyyyyHHmmAMPM" />
														</td>
														<td title="View this version's Order"><a href="/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=<bean:message key="button.compareOrderVersions"/>&orderId=<bean:write name="orderVersionListIndex" property="orderId"/>"><bean:write name="orderVersionListIndex" property="orderVersion" /></a></td>
														<td><bean:write name="orderVersionListIndex" property="orderTitle" /></td>
													</tr>										                
												</logic:iterate>
											</table>
										</td>
									</tr>
								</table>
								</logic:notEmpty>
						<!-- END OTHER VERSION TABLE -->
							<br>				  
						<!-- BEGIN ORDER PRESENTATION TABLE -->
							<table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
								<tr class=detailHead>
									<td class=detailHead ><bean:message key="prompt.orderPresentation" /></td>				                        	
									<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>				                          
								</tr>
								<tr>
									<td colspan=2>
									<logic:equal name="supervisionOrderForm" property="action" value="view">                        
										<!-- Begin ORDER PRESENTATION SECTION for single version -->
										<logic:equal name="supervisionOrderForm" property="previousOrderVersion" value="">
										<table width=100% cellpadding=4 cellspacing=1 border=0>	
											<tr>				                          	
												<td class=formDeLabel nowrap width="1%"><bean:message key="prompt.versionType" /></td>
												<td class=formDe><bean:write name="supervisionOrderForm" property="selectedOrderVersion.versionType" /></td>		
											</tr>
											<tr>				                          	
												<td class=formDeLabel><bean:message key="prompt.orderTitle" /></td>
												<td class=formDe><bean:write name="supervisionOrderForm" property="selectedOrderVersion.orderTitle" /></td>		
											</tr>
											 <tr>
	<td class=formDeLabel><bean:message key="prompt.offenseCode" /></td>
	<td class=formDe><bean:write name="supervisionOrderForm" property="selectedOrderVersion.offenseId" />&nbsp;<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.offense"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.offense" /></logic:notEmpty></td>
</tr>
<tr>
	<td class=formDeLabel><bean:message key="prompt.dispositionType"/></td>
	<td class=formDe><bean:write name="supervisionOrderForm" property="selectedOrderVersion.dispositionType" /></td>
</tr>
<logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>">
<tr>
	<td class=formDeLabel><bean:message key="prompt.confinementLength"/></td>
	<td class=formDe>
		<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthYears"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthYears" /> Years </logic:notEmpty>
		<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthMonths"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthMonths" /> Months </logic:notEmpty>
		<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthDays"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthDays" /> Days </logic:notEmpty>	
	</td>
</tr>
</logic:equal>
<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
<tr>
	<td class=formDeLabel><bean:message key="prompt.supervisionLength"/></td>
	<td class=formDe>
		<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthYears"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthYears" /> Years </logic:notEmpty>
		<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthMonths"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthMonths" /> Months </logic:notEmpty>
		<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthDays"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthDays" /> Days </logic:notEmpty>	
	</td>
</tr>
</logic:notEqual>
<tr>
	<td class=formDeLabel nowrap>
	<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
	<bean:message key="prompt.supervisionBeginDate"/>
	</logic:notEqual>
	<logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
	<bean:message key="prompt.pretrialInterventionBegin"/>&nbsp;<bean:message key="prompt.date"/>
	</logic:equal>
	</td>
	<td class=formDe><bean:write name="supervisionOrderForm" property="selectedOrderVersion.caseSupervisionBeginDateAsString" /></td>
</tr>
<tr>
	<td class=formDeLabel nowrap>
	<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
	<bean:message key="prompt.supervisionEndDate"/>
	</logic:notEqual>
	<logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
	<bean:message key="prompt.pretrialInterventionEnd"/>&nbsp;<bean:message key="prompt.date"/>
	</logic:equal>
	</td>
	<td class=formDe><bean:write name="supervisionOrderForm" property="selectedOrderVersion.caseSupervisionEndDateAsString" /></td>
</tr>
											
											
											
											
											<logic:equal name="supervisionOrderForm" property="agencyId" value="<%=UIConstants.CSC%>">
												<tr>				                          	
													<td class=formDeLabel><bean:message key="prompt.plea" /></td>
													<td class=formDe><bean:write name="supervisionOrderForm" property="plea" /></td>		
												</tr>
											</logic:equal>
											<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.modificationReason" >
											<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.modificationReason" value="">
											<tr>				                          	
												<td class=formDeLabel colspan="2"><bean:message key="prompt.modificationReason" /></td>
											</tr>
											
											<tr>
												<td class=formDe colspan="2"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.modificationReason" /></td>		
											</tr>
											</logic:notEqual>
											</logic:notEmpty>
										</table>
										</logic:equal>
										<!-- End ORDER PRESENTATION SECTION for single version -->
										
										<!-- Begin ORDER PRESENTATION SECTION for multiple versions -->
										<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion">
										<table width="100%" border="0" cellspacing=1 cellpadding=4>
					                        <tr>
					                          	<td colspan=2 class=formDeLabel><bean:message key="prompt.selectedVersion" />(<bean:write name="supervisionOrderForm" property="selectedOrderVersion.orderVersion" />)</td>
					                          	<td colspan=2 class=formDeLabel><bean:message key="prompt.previousVersion" />(<bean:write name="supervisionOrderForm" property="previousOrderVersion.orderVersion" />)</td>
					                        </tr>
					                        <tr>
					                        	<td class=formDeLabel nowrap width="1%"><bean:message key="prompt.versionType" /></td>
												<td class=formDe><bean:write name="supervisionOrderForm" property="selectedOrderVersion.versionType" /></td>
												<td class=formDeLabel nowrap width="1%"><bean:message key="prompt.versionType" /></td>
												<td class=formDe><bean:write name="supervisionOrderForm" property="previousOrderVersion.versionType" /></td>
					                        </tr>
					                        <tr>
					                        	<td class=formDeLabel><bean:message key="prompt.orderTitle" /></td>
												<td class=formDe><bean:write name="supervisionOrderForm" property="selectedOrderVersion.orderTitle" /></td>
												<td class=formDeLabel><bean:message key="prompt.orderTitle" /></td>
												<td class=formDe><bean:write name="supervisionOrderForm" property="previousOrderVersion.orderTitle" /></td>
					                        </tr>
					                        
					                        
					                       
					                       
					                        <tr>
													<td class=formDeLabel><bean:message key="prompt.offenseCode" /></td>
													<td class=formDe><bean:write name="supervisionOrderForm" property="selectedOrderVersion.offenseId" />&nbsp;<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.offense"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.offense" /></logic:notEmpty></td>
												<td class=formDeLabel><bean:message key="prompt.offenseCode" /></td>
													<td class=formDe><bean:write name="supervisionOrderForm" property="previousOrderVersion.offenseId" />&nbsp;<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion.offense"><bean:write name="supervisionOrderForm" property="previousOrderVersion.offense" /></logic:notEmpty></td>
												
												</tr>
												
												<tr>
													<td class=formDeLabel><bean:message key="prompt.dispositionType"/></td>
													<td class=formDe><bean:write name="supervisionOrderForm" property="selectedOrderVersion.dispositionType" /></td>
													<td class=formDeLabel><bean:message key="prompt.dispositionType"/></td>
													<td class=formDe><bean:write name="supervisionOrderForm" property="previousOrderVersion.dispositionType" /></td>
													
												</tr>
												<jims2:if name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>" op="equal">
												<jims2:or name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>" op="equal">
												<tr>
													<logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>">
													<td class=formDeLabel><bean:message key="prompt.confinementLength"/></td>
													<td class=formDe>
														<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthYears"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthYears" /> Years </logic:notEmpty>
														<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthMonths"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthMonths" /> Months </logic:notEmpty>
														<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthDays"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthDays" /> Days </logic:notEmpty>	
													</td>
													</logic:equal>
													<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>">
														<td class=formDeLabel><bean:message key="prompt.confinementLength"/>&nbsp;</td>
													<td class=formDe>&nbsp;</td>
													</logic:notEqual>
													
													<logic:equal name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>">
													<td class=formDeLabel><bean:message key="prompt.confinementLength"/></td>
													<td class=formDe>
														<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion.confinementLengthYears"><bean:write name="supervisionOrderForm" property="previousOrderVersion.confinementLengthYears" /> Years </logic:notEmpty>
														<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion.confinementLengthMonths"><bean:write name="supervisionOrderForm" property="previousOrderVersion.confinementLengthMonths" /> Months </logic:notEmpty>
														<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion.confinementLengthDays"><bean:write name="supervisionOrderForm" property="previousOrderVersion.confinementLengthDays" /> Days </logic:notEmpty>	
													</td>
													</logic:equal>
													<logic:notEqual name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>">
														<td class=formDeLabel><bean:message key="prompt.confinementLength"/>&nbsp;</td>
													<td class=formDe>&nbsp;</td>
													</logic:notEqual>
												</tr>
												</jims2:or>
												</jims2:if>
												<jims2:if name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>" op="notEqual">
												<jims2:or name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>"  op="notEqual">
												
												<tr>
													<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<td class=formDeLabel><bean:message key="prompt.supervisionLength"/></td>
													<td class=formDe>
														<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthYears"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthYears" /> Years </logic:notEmpty>
														<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthMonths"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthMonths" /> Months </logic:notEmpty>
														<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthDays"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthDays" /> Days </logic:notEmpty>	
													</td>
													</logic:notEqual>
													<logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<td class=formDeLabel><bean:message key="prompt.supervisionLength"/>&nbsp;</td>
													<td class=formDe>&nbsp;</td>
													</logic:equal>
													<logic:notEqual name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<td class=formDeLabel><bean:message key="prompt.supervisionLength"/></td>
													<td class=formDe>
														<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion.supervisionLengthYears"><bean:write name="supervisionOrderForm" property="previousOrderVersion.supervisionLengthYears" /> Years </logic:notEmpty>
														<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion.supervisionLengthMonths"><bean:write name="supervisionOrderForm" property="previousOrderVersion.supervisionLengthMonths" /> Months </logic:notEmpty>
														<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion.supervisionLengthDays"><bean:write name="supervisionOrderForm" property="previousOrderVersion.supervisionLengthDays" /> Days </logic:notEmpty>	
													</td>
													</logic:notEqual>
													<logic:equal name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<td class=formDeLabel><bean:message key="prompt.supervisionLength"/>&nbsp;</td>
													<td class=formDe>&nbsp;</td>
													</logic:equal>
													
												</tr>
												
												</jims2:or>
												</jims2:if>
												<tr>
													<td class=formDeLabel nowrap>
													<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.supervisionBeginDate"/>
													</logic:notEqual>
													<logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.pretrialInterventionBegin"/>&nbsp;<bean:message key="prompt.date"/>
													</logic:equal>
													</td>
													<td class=formDe><bean:write name="supervisionOrderForm" property="selectedOrderVersion.caseSupervisionBeginDateAsString" /></td>
													<td class=formDeLabel nowrap>
													<logic:notEqual name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.supervisionBeginDate"/>
													</logic:notEqual>
													<logic:equal name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.pretrialInterventionBegin"/>&nbsp;<bean:message key="prompt.date"/>
													</logic:equal>
													</td>
													<td class=formDe><bean:write name="supervisionOrderForm" property="previousOrderVersion.caseSupervisionBeginDateAsString" /></td>
												</tr>
												<tr>
													<td class=formDeLabel nowrap>
													<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.supervisionEndDate"/>
													</logic:notEqual>
													<logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.pretrialInterventionEnd"/>&nbsp;<bean:message key="prompt.date"/>
													</logic:equal>
													</td>
													<td class=formDe><bean:write name="supervisionOrderForm" property="selectedOrderVersion.caseSupervisionEndDateAsString" /></td>
													<td class=formDeLabel nowrap>
													<logic:notEqual name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.supervisionEndDate"/>
													</logic:notEqual>
													<logic:equal name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.pretrialInterventionEnd"/>&nbsp;<bean:message key="prompt.date"/>
													</logic:equal>
													</td>
													<td class=formDe><bean:write name="supervisionOrderForm" property="previousOrderVersion.caseSupervisionEndDateAsString" /></td>
												</tr>
										                       
										                        
										                        
										                        <logic:equal name="supervisionOrderForm" property="agencyId" value="<%=UIConstants.CSC%>">
																<tr>				                          	
																<td class=formDeLabel><bean:message key="prompt.plea" /></td>
																<td class=formDe><bean:write name="supervisionOrderForm" property="selectedOrderVersion.plea" /></td>
															<td class=formDeLabel><bean:message key="prompt.plea" /></td>
														<td class=formDe><bean:write name="supervisionOrderForm" property="previousOrderVersion.plea" /></td>		
												</tr>
											</logic:equal>
											<tr>				                          	
											<td class=formDeLabel colspan="2"><bean:message key="prompt.modificationReason" /></td>
											<td class=formDeLabel colspan="2"><bean:message key="prompt.modificationReason" /></td>		
											</tr>
											<tr>
												<td class=formDe colspan="2"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.modificationReason" /></td>
												<td class=formDe colspan="2"><bean:write name="supervisionOrderForm" property="previousOrderVersion.modificationReason" /></td>		
											</tr>
					                    </table>			                          	
										</logic:notEmpty>
										<!-- End ORDER PRESENTATION SECTION for multiple versions -->
								</logic:equal>
								<logic:notEqual name="supervisionOrderForm" property="action" value="view">                        
									<!-- Begin ORDER PRESENTATION SECTION for single version -->
									<tiles:insert page="orderPresentationTile.jsp" flush="true"></tiles:insert>
								</logic:notEqual>
								
								</td>
								</tr>
							</table>    
						<!-- END ORDER PRESENTATION TABLE -->        
						
							<br>
						<!-- BEGIN CONDITIONS SECTION -->
							<table width="98%" border="0" cellspacing=0 cellpadding=2 class=borderTableBlue>
								<tr class=detailHead>
									<td>
										<table width=100% cellpadding=0 cellspacing=0>
											<tr>
												<td class=detailHead><bean:message key="prompt.conditions" /></td>
												<td align=right><img src="/<msp:webapp/>images/step_2.gif"></td>
											</tr>
										</table>
									</td>
								</tr>
													  
								<tr>
									<td>
										<table border=0 align=center width=100%>
											<tr>
												<td>
													<table width=100% border=0 cellpadding=4 cellspacing=1 class=borderTable>
														<tr>
															<td class=formDeLabel width=1%></td>
															<td class=formDeLabel width=1%></td>
															<td class=formDeLabel><bean:message key="prompt.condition" /></td>
														</tr>
														<%int RecordCounter2 = 0;
														String bgcolor = "";%>
														<logic:iterate id="conditionSelectedListIndex" name="supervisionOrderForm" property="conditionSelectedList">
															<% RecordCounter2++; %>															
															<logic:equal name="conditionSelectedListIndex" property="compareToPreviousVersion" value="" >															
																<tr
																	class=<% bgcolor = "alternateRow";
																	if (RecordCounter2 % 2 == 1)
																		bgcolor = "normalRow";
																	out.print(bgcolor);%>>
																	<td class="hidden"><bean:write name="conditionSelectedListIndex" property="conditionId" /></td>
																<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="true">
																	<td class=impactedOrderBold title="This order has Impacted Orders with Like Conditions"/>
																	<bean:write name="conditionSelectedListIndex" property="sequenceNum" />
																	</td>
																</logic:equal>
																<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="false">
																	<td class=boldText><bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
																</logic:equal>																																		
																	<td>&nbsp;</td>
																	<td>
																		<logic:notEmpty name="conditionSelectedListIndex" property="resolvedDescription">
																			<bean:write name="conditionSelectedListIndex" property="resolvedDescription"  filter="false"/>
																		</logic:notEmpty>																																		
																		<logic:empty name="conditionSelectedListIndex" property="resolvedDescription">
																			<bean:write name="conditionSelectedListIndex" property="description"  filter="false"/>
																		</logic:empty>																																		
																	</td>
																</tr>															
															</logic:equal>														
															<logic:equal name="conditionSelectedListIndex" property="compareToPreviousVersion" value="updated" >
																<tr class=versionUpdatedCondition title="Updated from prior version">
																	<td class="hidden"><bean:write name="conditionSelectedListIndex" property="conditionId" /></td>														
																	<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="true">
																		<td class=impactedOrderBold title="This order has Impacted Orders with Like Conditions"/><bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
																	</logic:equal>
																	<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="false">
																		<td class=boldText><bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
																	</logic:equal>																																		
																	<td width=1%><img src="/<msp:webapp/>images/blue_flag.gif"></td>
																	<td>
																		<logic:notEmpty name="conditionSelectedListIndex" property="resolvedDescription">
																			<bean:write name="conditionSelectedListIndex" property="resolvedDescription"  filter="false"/>
																		</logic:notEmpty>																																		
																		<logic:empty name="conditionSelectedListIndex" property="resolvedDescription">
																			<bean:write name="conditionSelectedListIndex" property="description"  filter="false"/>
																		</logic:empty>																																		
																	</td>
																</tr>
															</logic:equal>		
															<logic:equal name="conditionSelectedListIndex" property="compareToPreviousVersion" value="added" >
																<tr class=versionAddedCondition title="Condition Added">
																	<td class="hidden"><bean:write name="conditionSelectedListIndex" property="conditionId" /></td>														
																	<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="true">
																		<td class=impactedOrderBold title="This order has Impacted Orders with Like Conditions"/><bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
																	</logic:equal>
																	<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="false">
																		<td class=boldText><bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
																	</logic:equal>																																		
																	<td width=1% align=center><span style="font-size:medium; font-weight:bold">+</span></td>
																	<td>
																		<logic:notEmpty name="conditionSelectedListIndex" property="resolvedDescription">
																			<bean:write name="conditionSelectedListIndex" property="resolvedDescription"  filter="false"/>
																		</logic:notEmpty>																																		
																		<logic:empty name="conditionSelectedListIndex" property="resolvedDescription">
																			<bean:write name="conditionSelectedListIndex" property="description" filter="false" />
																		</logic:empty>																																		
																	</td>
																</tr>
															</logic:equal>	
															<logic:equal name="conditionSelectedListIndex" property="compareToPreviousVersion" value="removed" >
																<tr class=versionRemovedCondition title="Condition Removed">
																	<td class="hidden"><bean:write name="conditionSelectedListIndex" property="conditionId" /></td>														
																	<td> </td>
																	<td width=1% align=center><span style="font-size:small; font-weight:bold">X</span></td>
																	<td>
																		<logic:notEmpty name="conditionSelectedListIndex" property="resolvedDescription">
																			<bean:write name="conditionSelectedListIndex" property="resolvedDescription"  filter="false"/>
																		</logic:notEmpty>																																		
																		<logic:empty name="conditionSelectedListIndex" property="resolvedDescription">
																			<bean:write name="conditionSelectedListIndex" property="description"  filter="false"/>
																		</logic:empty>																																		
																	</td>
																</tr>
															</logic:equal>														
														</logic:iterate>																						
													</table>
														<table cellpadding=0 cellspacing=0 border="0">
															<tr>
																<td><span style="font-size:small; font-weight:bold">X</span></td>
																<td class=legendSmallText><span class=versionRemovedCondition>Red Conditions</span> signify that the condition was <b>removed</b> compared to prior version</td>
															</tr>
															<tr>
																<td><img src="/<msp:webapp/>images/blue_flag.gif"></td>
																<td class=legendSmallText><span class=versionUpdatedCondition>Blue Conditions</span> signify that the condition has been <b>updated</b> compared to prior version</td>
															</tr>
															<tr>
																<td><span style="font-size:medium; font-weight:bold">+</span></td>
																<td class=legendSmallText><span class=versionAddedCondition>Green Conditions</span> signify that the condition has been <b>added</b> compared to prior version</td>
															</tr>
														</table>

												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>	
						<!-- END CONDITIONS SECTION -->
							<br>
					
						
					
			 
						<br>
						<input type=button value=Close onclick=window.close()>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</div>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>