<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/12/2006	 Hien Rodriguez - Create JSP -->
<!-- 01/16/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 01/25/2007	 Hien Rodriguez - ER#38535 Remove Manage Party Link for now. -->
<!-- 02/07/2007	 Hien Rodriguez - Defect#39167 Put Manage Party Link back until Mary talks to BA. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

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
<title><bean:message key="title.heading" /> - outOfCountyCase/searchResults.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayOutOfCountyCaseSearchResults" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|3">
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
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign=top align=center>
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
									<table width=100%>
										<tr>							
							    			<td align="center" class="header">
												<bean:message key="title.outOfCountyCase" />&nbsp;-&nbsp;<bean:message key="prompt.advancedSearch" />&nbsp;<bean:message key="prompt.results" />
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
								<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td><ul>
                              					<li>Select Supervisee Name and click Submit button to View Associated Cases.</li>
											</ul></td>
										</tr>										
									</table>
								<!-- END INSTRUCTION TABLE -->
									<table>
										<tr>
											<td align="center">
												<bean:size id="partyListSize" name="outOfCountyCaseSearchForm" property="partyList"/>
												<b><bean:write name="partyListSize"/></b>&nbsp;search results for Last Name:<bean:write name="outOfCountyCaseSearchForm" property="lastName"/></td>            					
			            				</tr>
			            			</table>  
                    			<!-- BEGIN DETAIL TABLE -->	
									<table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
										<tr class="detailHead">
											<td width=1%></td>
											<td><bean:message key="prompt.supervisee" />&nbsp;<bean:message key="prompt.name" />
											<jims:sortResults beanName="outOfCountyCaseSearchForm" results="partyList" primaryPropSort="name" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1"  />	
											</td>
											<td><bean:message key="prompt.SPN" />
											<jims:sortResults beanName="outOfCountyCaseSearchForm" results="partyList" primaryPropSort="spn" primarySortType="INTEGER" sortId="2"  />	
											</td>
											<td><bean:message key="prompt.SSN" />
											<jims:sortResults beanName="outOfCountyCaseSearchForm" results="partyList" primaryPropSort="ssn" primarySortType="INTEGER" sortId="3"  />
											</td>
											<td><bean:message key="prompt.dob" />
											<jims:sortResults beanName="outOfCountyCaseSearchForm" results="partyList" primaryPropSort="dateOfBirth" primarySortType="DATE" sortId="4"  />
											</td>
											<td><bean:message key="prompt.race" />
											<jims:sortResults beanName="outOfCountyCaseSearchForm" results="partyList" primaryPropSort="raceId" primarySortType="STRING" sortId="5"  />
											</td>
											<td><bean:message key="prompt.sex" />
											<jims:sortResults beanName="outOfCountyCaseSearchForm" results="partyList" primaryPropSort="sexId" primarySortType="STRING" sortId="6"  />
											</td>
										</tr>
										<%int RecordCounter = 0;
										String bgcolor = "";%>  
										<logic:notEmpty name="outOfCountyCaseSearchForm" property="partyList">	
											<logic:iterate id="partyListIndex" name="outOfCountyCaseSearchForm" property="partyList">
												<tr
												class=<%RecordCounter++;
													bgcolor = "alternateRow";
													if (RecordCounter % 2 == 1)
														bgcolor = "normalRow";
													out.print(bgcolor);%>>
													<td width=1%><input type=radio name="spn" value=<bean:write name="partyListIndex" property="spn"/> /></td>
													<td>
													<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name="partyListIndex" property="spn"/>">
														<bean:write name="partyListIndex" property="name" /></td>
													<td><bean:write name="partyListIndex" property="spn" /></td>
													<td><msp:formatter name="partyListIndex" property="ssn" format="A-B-C"/></td>
													<td><bean:write name="partyListIndex" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
													<td><bean:write name="partyListIndex" property="raceId" /></td>
													<td><bean:write name="partyListIndex" property="sexId" /></td>							
												</tr>																
											</logic:iterate>
										</logic:notEmpty> 				
									</table>					
								<!-- END DETAIL TABLE -->                      
                      				<br>
                      			<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
												<html:submit property="submitAction" onclick="return validateRadios(this.form, 'spn', 'Please select a party.');"><bean:message key="button.submit"></bean:message></html:submit>&nbsp;
												<%--<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.createParty"/></html:submit>&nbsp;--%>
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

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>