<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<head>
	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">

	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title>Common Supervision - common/taskListTile.jsp</title>
	<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	
</head> 

<tiles:importAttribute name="taskConfig"/>

<table width="98%" border="0" cellspacing=1 cellpadding=4>
									<tr>
										<td colspan=2 class=detailHead>
											<table width=100% cellpadding=0 cellspacing=0>
												<tr>
													<td width=1%><a href="javascript:showHideMulti('taskInfo', 'ti', 6, '/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="taskInfo"></a></td>
													<td class=detailHead>&nbsp;<bean:message key="prompt.taskEmailNotificationInfo" /></td>
													<td align=right><img src="/<msp:webapp/>images/step_2.gif" vspace=0></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr id="ti0" class="hidden">
										<td class=formDeLabel width=1% nowrap valign=top><bean:message key="prompt.to" /></td>
										<td class=formDe>
										<logic:notEmpty name="taskConfig" property="taskItems">
											<table width=100%>
											
												<nest:iterate name="taskConfig" property="taskItems" id="taskList">
													<nest:notEqual property="deleted" value="true">
														<nest:notEmpty property="recipientId">
															<tr>
															<td>
															 	<bean:write name="taskList" property="recipient"/> : 
															 	<bean:write name="taskList" property="taskListType"/>
															 </td>
															</tr>
														</nest:notEmpty>
													</nest:notEqual>
											</nest:iterate>
											
											</table>
											</logic:notEmpty>
											<logic:empty name="taskConfig" property="taskItems">
												&nbsp;
											</logic:empty>
										</td>
									</tr>
									<tr id="ti1" class="hidden">
										<td class=formDeLabel width=1% valign=top nowrap><bean:message key="prompt.taskSubject" /></td>
										<td class=formDe><bean:write name="taskConfig" property="subject2"/></td>
									</tr>
									<tr id="ti2" class="hidden">
										<td class=formDeLabel width=1% valign=top nowrap><bean:message key="prompt.taskDueBy" /></td>
										<td class=formDe><bean:write name="taskConfig" property="dueBy"/>
											<logic:notEmpty name="taskConfig" property="dueBy">
											&nbsp;Day(s)
											</logic:notEmpty>
										</td>
									</tr>
									<tr id="ti3" class="hidden">
										<td colspan=2><br></td>
									</tr>
								
									<!--email info start-->
								
									<tr id="ti4" class="hidden">
										<td colspan=2 class=detailHead>
											<table width=100% cellpadding=0 cellspacing=0 border=0>
												<tr>
													<td class=detailHead><bean:message key="prompt.email" /> <bean:message key="prompt.info" /></td>
												</tr>
											</table>
										</td>
									</tr>
							
									<tr id="ti5" class="hidden">
										<td class=formDeLabel width=1% nowrap valign=top><bean:message key="prompt.to" /></td>
										<td class=formDe>
											<logic:notEmpty name="taskConfig" property="emailTaskItems">
											<table>
												<nest:iterate name="taskConfig" property="emailTaskItems" id="emailTaskList">
												<nest:equal property="selected" value="true">
												<tr>
												<td>
													 <bean:write name="emailTaskList" property="recipient"/>
													 <nest:notEmpty property="emailAddress">
													
														: <bean:write name="emailTaskList"  property="emailAddress"/>
													
													</nest:notEmpty>
													 </td>
												</tr>
													
												
												</nest:equal>
												
											</nest:iterate>
											
											</table>
											</logic:notEmpty>
											<logic:empty name="taskConfig" property="emailTaskItems">
												&nbsp;
											</logic:empty>
										</td>
									</tr>
								</table>
