<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/24/2006	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 08/11/2008  C, shimek      - defect#51372 added sorting to Date/Time display field -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

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
<title><bean:message key="title.heading" /> - outOfCountyCase/reasonForUpdateHistory.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitOutOfCountyCaseCreateUpdate" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|26">
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
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<table width=98% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
								<!--tabs start-->
									<tiles:insert page="../common/manageFeaturesTabs.jsp" flush="true">
										<tiles:put name="tabid" value="oocTab"/>
									</tiles:insert>	
								<!--tabs end-->
								</td>
							</tr>
							
						</table>
						<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign=top align=center>
								<!-- BEGIN HEADING TABLE -->
									<table width=98%>
										<tr>
											<td align="center" class="header"><bean:message key="prompt.view" />&nbsp;<bean:message key="prompt.reasonForUpdate" />&nbsp;<bean:message key="prompt.history" /></td>
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
									<tiles:insert page="partyInfoHeaderTile.jsp" flush="true">
										<tiles:put name="partyHeader" beanName="outOfCountyCaseForm"/>
									</tiles:insert>	
								<!-- END DETAIL HEADER TABLE -->
								<!-- BEGIN DETAIL TABLE -->
                        			<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
                          				<tr>
                            				<td align=center>
                              					<table border=0 width=100% cellspacing=1 cellpadding=2>
					                                <tr>
					                                  	<td colspan="3" class="detailHead"><bean:message key="prompt.reasonForUpdate" /></td>
					                                </tr>
													<tr class="formDeLabel">
														<td class="formDeLabel"><bean:message key="prompt.reason" /></td>
														<td class="formDeLabel"><bean:message key="prompt.name" /></td>
														<td class="formDeLabel">
															<bean:message key="prompt.dateTime" />
															<jims2:sortResults beanName="outOfCountyCaseForm" results="reasonForUpdateHistoryList" primaryPropSort="updateDate" primarySortType="DATE" secondPropSort="updateTime" secondarySortType="STRING" defaultSort="true" defaultSortOrder="DESC" sortId="1" hideMe="true" />
														</td>
													</tr>
													<logic:iterate id="reasonForUpdateHistoryListIndex" name="outOfCountyCaseForm" property="reasonForUpdateHistoryList" indexId="index">
            											<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
															<td><bean:write name="reasonForUpdateHistoryListIndex" property="reasonForUpdate" /></td>
															<td><bean:write name="reasonForUpdateHistoryListIndex" property="updateUserName" /></td>
															<td><bean:write name="reasonForUpdateHistoryListIndex" property="updateDate" formatKey="date.format.mmddyyyy" /> <bean:write name="reasonForUpdateHistoryListIndex" property="updateTime"  formatKey="time.format.HHmm" /></td>				
														</tr>						
													</logic:iterate>													
												</table>
                            				</td>
                          				</tr>
                        			</table>
                        		<!-- END DETAIL TABLE -->
                        			<br>
                        		<!-- BEGIN BUTTON TABLE -->
                        			<table border="0" width="98%">
                          				<tr>											
											<td align="center">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
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

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
