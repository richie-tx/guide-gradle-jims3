<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/13/2007	 Hien Rodriguez - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %> 
<%@ page import="naming.PDCodeTableConstants" %> 
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
<title><bean:message key="title.heading" /> - posttrial/positionSearchResults.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="workgroupSearchForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
function clearRadios() 
    {
    var rbs = document.getElementsByName("selectedValue");
	if (rbs.length == 1){
		rbs[0].checked = true;
	} else	{
		for( var i = 0; i < rbs.length; i++) 
 		{
			rbs[i].checked = false;
		}
	}	
}
function checkSelection(theForm){
	var selValues = document.getElementsByName("selectedValue");
	for (var x = 0; x <selValues.length; x++){
		if (selValues[x].checked == true){
			return true;
			break;
		}	
	}
	alert("Position selection required.");
	return false;
}

</script> 

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="clearRadios()" >
<html:form action="/handleTaskPositionSelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Tasks/CSCD_Tasks.htm#|7">

<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign="top"><img src="../../common/images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
<!-- BEGIN TABS TABKE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign="top">
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tab" value="setupTab"/>
							</tiles:insert>		
						</td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
<!-- END TABS TABLE -->
<!-- BEGIN BLUE BORDER TABLE -->				
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
				    					<bean:message key="title.CSCD" /> -
				    					<bean:message key="prompt.create"/>&nbsp;<bean:message key="prompt.task"/> - 
										<bean:message key="prompt.searchFor" />&nbsp;<bean:message key="prompt.position" />&nbsp;<bean:message key="prompt.results" /> 
				
									</td>						
			  					</tr>
							</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">							
								<tr>
									<td align="center" class="errorAlert"><html:errors/></td>
								</tr>		
							</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td>
										<ul>
											<li>Select a user and click appropriate button.</li>
										</ul>
									</td>
								</tr>
								<bean:size id="totalResults" name="cscdTaskForm" property="foundUsers"/>			
								<tr>
									<td align="center"> <b><bean:write name="totalResults"/></b> search results for 
										<bean:write name="cscdTaskForm" property="posSearchString" filter="false"/> 
									</td>
								</tr>
							</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN RESULTS TABLE -->
							<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
								<tr class="detailHead">
									<td title="Position Type"><bean:message key="prompt.pos" /> <bean:message key="prompt.type" /></td>
									<td><bean:message key="prompt.name" /></td>
									<td title="Probation Officer Indicator"><bean:message key="prompt.cjad" /></td>
									<td><bean:message key="prompt.cstsOfficerType" /></td>
									<td title="Probation Officer Indicator"><bean:message key="prompt.poi" /></td>
									<td title="Position Name"><bean:message key="prompt.positionName" /></td>
									<td><bean:message key="prompt.jobTitle" /></td>
									<td><bean:message key="prompt.programUnit" /></td>
								</tr>
								<logic:equal name="totalResults" value="0">
									<tr>
										<td colspan="8"> <bean:message key="error.no.search.results.found"/></td>
									</tr>
								</logic:equal>
					<%  int RecordCounter = 0;
						String bgcolor = "";%>  
								<nest:iterate name="cscdTaskForm" property="foundUsers">
									<nest:notEmpty property="positionId">
										<nest:notEqual property="positionId" value="">
											<tr class="<% out.print( ((++RecordCounter) % 2 == 1) ? "normalRow" : "alternateRow" );%>">
												<td><input type="radio" name="selectedValue" value='<nest:write property="positionId"/>'/>
													<nest:equal property="retired" value="true">
														<i>
													</nest:equal>
													<nest:write property="positionTypeDesc"/>
													<nest:equal property="retired" value="true">
															</i>
													</nest:equal>
												</td>
												<td>
													<nest:notEqual property="vacant" value="true">
													<nest:notEmpty property="user">
														<nest:notEmpty property="user.userName">
															<nest:write property="user.userName.formattedName"/>
														</nest:notEmpty>
													</nest:notEmpty>
													</nest:notEqual>
													<nest:equal property="vacant" value="true">
														<bean:message key="prompt.noOfficerAssigned"/>
													</nest:equal>
												</td>
												<td>
													<nest:notEmpty property="user">
														<nest:write property="user.cjad"/>
													</nest:notEmpty>
												</td>
												
												<td ><nest:write property="officerTypeDesc"/>&nbsp;</td>
												<td  class="boldText"><nest:write property="probOfficerInd" filter="true"/>&nbsp;</td>
												<td  class="boldText"><nest:write property="positionName"/>&nbsp;</td>
												<td><nest:write property="jobTitleDesc"/>&nbsp;</td>
												<td><nest:write property="programUnitDesc"/>&nbsp;</td>
											</tr>
											<nest:notEmpty property="subordinates">
												<nest:iterate property="subordinates">
													<nest:notEmpty property="positionId">
														<nest:notEqual property="positionId" value="">
															<tr class="<% out.print( ((++RecordCounter) % 2 == 1) ? "normalRow" : "alternateRow" );%>">
																<td class="supervisorIndent"><input type="radio" name="selectedValue" value='<nest:write property="positionId"/>'>
																	<nest:equal property="retired" value="true">
																		<i>
																	</nest:equal>
																	<nest:write property="positionTypeDesc"/>
																	<nest:equal property="retired" value="true">
																		</i>
																	</nest:equal>
																</td>
																<td>
																	<nest:notEqual property="vacant" value="true">
																		<nest:notEmpty property="user">
																			<nest:notEmpty property="user.userName">
																				<nest:write property="user.userName.formattedName"/>
																			</nest:notEmpty>
																		</nest:notEmpty>
																	</nest:notEqual>
																	<nest:equal property="vacant" value="true">
																		<bean:message key="prompt.noOfficerAssigned"/>
																	</nest:equal>
																</td>
																<td>
																	<nest:notEmpty property="user">
																		<nest:write property="user.cjad"/>
																	</nest:notEmpty>
																</td>
																<td ><nest:write property="officerTypeDesc"/>&nbsp;</td>
																<td class="boldText"><nest:write property="probOfficerInd"/></td>
																<td class="boldText"><nest:write property="positionName"/></td>
																<td><nest:write property="jobTitleDesc"/></td>
																<td><nest:write property="programUnitDesc"/></td>
															</tr>
															<nest:notEmpty property="subordinates">
																<nest:iterate property="subordinates">
																	<nest:notEmpty property="positionId">
																		<nest:notEqual property="positionId" value="">
																			<tr class="<% out.print( ((++RecordCounter) % 2 == 1) ? "normalRow" : "alternateRow" );%>">
																			<td class="assistantSupervisorIndent"><input type="radio" name="selectedValue" value='<nest:write property="positionId"/>'>
																				<nest:equal property="retired" value="true">
																					<i>
																				</nest:equal>
																				<nest:write property="positionTypeDesc"/>
																				<nest:equal property="retired" value="true">
																					</i>
																				</nest:equal>
																			</td>
																			<td>
																				<nest:notEqual property="vacant" value="true">
																					<nest:notEmpty property="user">
																						<nest:notEmpty property="user.userName">
																							<nest:write property="user.userName.formattedName"/>
																						</nest:notEmpty>
																					</nest:notEmpty>
																				</nest:notEqual>
																				<nest:equal property="vacant" value="true">
																					<bean:message key="prompt.noOfficerAssigned"/>
																				</nest:equal>
																			</td>
																			<td>
																				<nest:notEmpty property="user">
																					<nest:write property="user.cjad"/>
																				</nest:notEmpty>
																			</td>
																			<td><nest:write property="officerTypeDesc"/>&nbsp;</td>
																			<td class="boldText"><nest:write property="probOfficerInd"/></td>
																			<td class="boldText" ><nest:write property="positionName"/></td>
																			<td><nest:write property="jobTitleDesc"/></td>
																			<td><nest:write property="programUnitDesc"/></td>
																		</tr>
																		<nest:notEmpty property="subordinates">
																			<nest:iterate property="subordinates">
																				<nest:notEmpty property="positionId">
																					<nest:notEqual property="positionId" value="">
																						<tr class="<% out.print( ((++RecordCounter) % 2 == 1) ? "normalRow" : "alternateRow" );%>">
																							<td class="officerIndent"><input type="radio" name="selectedValue" value='<nest:write property="positionId"/>'>
																								<nest:equal property="retired" value="true">
																									<i>
																								</nest:equal>
																								<nest:write property="positionTypeDesc"/>
																								<nest:equal property="retired" value="true">
																									</i>
																								</nest:equal>
																							</td>
																							<td>
																								<nest:notEqual property="vacant" value="true">
																									<nest:notEmpty property="user">
																										<nest:notEmpty property="user.userName">
																											<nest:write property="user.userName.formattedName"/>
																										</nest:notEmpty>
																									</nest:notEmpty>
																								</nest:notEqual>
																								<nest:equal property="vacant" value="true">
																									<bean:message key="prompt.noOfficerAssigned"/>
																								</nest:equal>
																							</td>
																							<td>
																								<nest:notEmpty property="user">
																									<nest:write property="user.cjad"/>
																								</nest:notEmpty>
																							</td>
																							<td><nest:write property="officerTypeDesc"/>&nbsp;</td>
																							<td class="boldText"><nest:write property="probOfficerInd"/></td>
																							<td class="boldText"><nest:write property="positionName"/></td>
																							<td><nest:write property="jobTitleDesc"/></td>
																							<td><nest:write property="programUnitDesc"/></td>
																						</tr>
																					</nest:notEqual>
																				</nest:notEmpty>
																			</nest:iterate>
																		</nest:notEmpty>
																	</nest:notEqual>
																</nest:notEmpty>
															</nest:iterate>
														</nest:notEmpty>
													</nest:notEqual>
												</nest:notEmpty>
											</nest:iterate>
										</nest:notEmpty>
									</nest:notEqual>
								</nest:notEmpty>
							</nest:iterate>
							</table>
<!-- END RESULTS TABLE -->
							<div class="spacer"></div>
<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%" cellpadding="1" cellspacing="1">
								<tr>
									<td align="center">    
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.back" /></html:submit>
										<html:submit property="submitAction" onclick="return checkSelection(this.form) && disableSubmit(this, this.form)"><bean:message key="button.next" /></html:submit>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.cancel" /></html:submit>
									</td>
								</tr>
							</table>
<!-- END BUTTON TABLE -->
						</td>
					</tr>
				</table>
<!-- END BLUE BORDER TABLE -->				
			<br>
		</td>
	</tr>
</table>
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>