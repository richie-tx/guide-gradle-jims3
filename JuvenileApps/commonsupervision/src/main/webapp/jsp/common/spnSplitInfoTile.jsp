<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
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
	<title>Common Supervision - common/spnSplitInfoTile.jsp</title>
	<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	
</head> 

<tiles:importAttribute name="erroneousSpn"/>
<tiles:importAttribute name="validSpn"/>

<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
											<tr class=detailHead>
												<td>
													<bean:message key="prompt.spnSplit" />&nbsp;<bean:message key="prompt.information"/>
												</td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="4" cellspacing="1">
														<tr>
															<td class="formDeLabelBottomBorder" nowrap width=1%><bean:message key="prompt.erroneousSPN" /></td>
															<td class="formDeBottomBorder" colspan=3>
																<bean:write name="erroneousSpn" property="spn"/>
															</td>
														</tr>
														<tr>
															<td class=formDeLabel><bean:message key="prompt.name" /></td>
															<td class=formDe><msp:formatter name="erroneousSpn" property="name" format="L, F"/></td>
															<td class=formDeLabel><bean:message key="prompt.dob" /></td>
															<td class=formDe><bean:write name="erroneousSpn" property="dobAsStr" /></td>
														</tr>
														<tr>
															<td class=formDeLabel><bean:message key="prompt.sex" /></td>
															<td class=formDe><bean:write name="erroneousSpn" property="sex" /></td>
															<td class=formDeLabel width=1%><bean:message key="prompt.race" /></td>
															<td class=formDe><bean:write name="erroneousSpn" property="race" /></td>
														</tr>
														<tr>
															<td class=formDeLabel><bean:message key="prompt.jailIndicator" /></td>
															<td class=formDe colspan=3><bean:write name="erroneousSpn" property="jailInd" /></td>
														</tr>
														<tr>
															<td colspan=4><img src="/<msp:webapp/>images/spacer.gif"></td>
														</tr>
														<tr>
															<td class="formDeLabelBottomBorder" nowrap><bean:message key="prompt.validSPN" /></td>
															<td class="formDeBottomBorder" colspan=3>
																<bean:write name="validSpn" property="spn"/>
															</td>
														</tr>
														<tr>
															<td class=formDeLabel><bean:message key="prompt.name" /></td>
															<td class=formDe><msp:formatter name="validSpn" property="name" format="L, F"/></td>
															<td class=formDeLabel><bean:message key="prompt.dob" /></td>
															<td class=formDe><bean:write name="validSpn" property="dobAsStr" /></td>
														</tr>
														<tr>
															<td class=formDeLabel><bean:message key="prompt.sex" /></td>
															<td class=formDe><bean:write name="validSpn" property="sex" /></td>
															<td class=formDeLabel width=1%><bean:message key="prompt.race" /></td>
															<td class=formDe><bean:write name="validSpn" property="race" /></td>
														</tr>
														<tr>
															<td class=formDeLabel><bean:message key="prompt.jailIndicator" /></td>
															<td class=formDe colspan=3><bean:write name="validSpn" property="jailInd" /></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
