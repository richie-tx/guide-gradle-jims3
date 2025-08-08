<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/13/2007	 Hien Rodriguez - Create JSP -->
<!-- 08/29/2008  Clarence Shimek - revised renderButtons and resetButtons scripts to check for existing fields. found issue while testing defect 51481  -->
<!-- 09/29/2008  Clarence Shimek - defect#54607 added script edtis to not display vacate or reassign buttons for retired position selection -->
<!-- 03/06/2009  Dawn Gibler - defect#56497 - changed indent class to nbsps -->
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
<title><bean:message key="title.heading" /> - adminStaff/positionSearchResults.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="workgroupSearchForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
function renderButtons(isRetired, isVacated, isDivisionMgr){
	var theForm = document.forms[0];
	if (typeof(theForm.viewButton) != "undefined"){
		show("viewButton", 1, "inline");
	}
	if (typeof(theForm.updateButton) != "undefined"){
		if(isRetired){
			show("updateButton", 0, "inline");
		}else{
			show("updateButton", 1, "inline");
		}
	}

	if (typeof(theForm.puHistoryButton) != "undefined"){	
		if(isDivisionMgr){
			show("puHistoryButton",  0, "inline");
		}else {
			show("puHistoryButton",  1, "inline");
		}
	}

	if (typeof(theForm.positionHistoryButton) != "undefined"){	
		show("positionHistoryButton",  1, "inline");
	}	

	if (isVacated){
		if (typeof(theForm.assignButton) != "undefined"){	
			show("assignButton",  1, "inline");
			if(isRetired){
				show("assignButton",  0, "inline");
			}
		}
		if (typeof(theForm.reassignButton) != "undefined"){	
			show("reassignButton",  0, "inline");
		}
		if (typeof(theForm.vacateButton) != "undefined"){	
			show("vacateButton",  0, "inline");
		}
		if (typeof(theForm.staffHistoryButton) != "undefined"){	
			show("staffHistoryButton",  0, "inline");
		}
	}else {
		if (typeof(theForm.assignButton) != "undefined"){	
			show("assignButton",  0, "inline");
		}
		if (typeof(theForm.reassignButton) != "undefined"){	
			show("reassignButton",  1, "inline");
			if(isRetired){
				show("reassignButton",  0, "inline");
			}
		}
		if (typeof(theForm.vacateButton) != "undefined"){	
			show("vacateButton",  1, "inline");
			if(isRetired){
				show("vacateButton",  0, "inline");
			}
		}
		if (typeof(theForm.staffHistoryButton) != "undefined"){	
			show("staffHistoryButton",  1, "inline");
		}
	} 
}

	//called onload for the prototype...
	function resetButtons(){
		var theForm = document.forms[0];
		if (typeof(theForm.reassignButton) != "undefined"){	
			show("reassignButton",  0, "inline");
		}
		if (typeof(theForm.vacateButton) != "undefined"){	
			show("vacateButton",  0, "inline");
		}
		if (typeof(theForm.assignButton) != "undefined"){	
			show("assignButton",  0, "inline");
		}
		if (typeof(theForm.updateButton) != "undefined"){	
			show("updateButton",  0, "inline");
		}
		if (typeof(theForm.viewButton) != "undefined"){	
			show("viewButton",  0, "inline");
		}
		if (typeof(theForm.puHistoryButton) != "undefined"){	
			show("puHistoryButton",  0, "inline");
		}
		if (typeof(theForm.staffHistoryButton) != "undefined"){	
			show("staffHistoryButton",  0, "inline");
		}
		if (typeof(theForm.positionHistoryButton) != "undefined"){	
			show("positionHistoryButton",  0, "inline");
		}
	}
	

	function findAndInitializeRadio() {
	    var rbs = document.getElementsByName("selectedValue");
	    var actualElement
	    if (rbs != null && rbs.length>0){
		    for(var loopx=0; loopx<rbs.length; loopx++) {
			    actualElement=rbs[loopx];
			    if(actualElement.checked==true){
				    actualElement.checked=true;
				    actualElement.onclick();
			    }
			    else{
				    if(rbs.length==1){
					    actualElement.checked=true;
					    actualElement.onclick();
				    }
			    }
		    } 
		}	
	}	
</script> 

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="findAndInitializeRadio()">
<html:form action="/handlePositionSelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|10">
<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign="top"><img src="../../common/images/spacer.gif" height="5"></td>
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
											<tiles:put name="tabid" value="positionsTab"/>
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
								    				<bean:message key="title.CSCD" /> - 
														<bean:message key="prompt.position" /> 
														<bean:message key="prompt.searchResults" />
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
											
											<bean:size id="totalResults" name="adminStaffSearchForm" property="foundPositions"/>			
											
											<tr>
												<td align="center"> <b><bean:write name="totalResults"/></b> search results for 
												<bean:write name="adminStaffSearchForm" property="posSearchString" filter="false"/> 
												
												</td>
											</tr>
										</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN RESULTS TABLE -->
										<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
											<tr class="detailHead">
												<td title="Position Type"><bean:message key="prompt.pos" /> <bean:message key="prompt.type" />
												</td>
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
											<logic:notEmpty name="totalResults">  
												<nest:iterate name="adminStaffSearchForm" property="foundPositions">
													<nest:notEmpty property="positionId">
														<nest:notEqual property="positionId" value="">
															<tr class="<% out.print( ((++RecordCounter) % 2 == 1) ? "normalRow" : "alternateRow" );%>">
																<td><input type="radio" name="selectedValue" onclick='renderButtons(<nest:write property="retired"/>,<nest:write property="vacant"/>,<nest:write property="divisionManager"/>)' value='<nest:write property="positionId"/>' />
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
																				<td>&nbsp;&nbsp;<input type="radio" name="selectedValue" onclick='renderButtons(<nest:write property="retired"/>,<nest:write property="vacant"/>,<nest:write property="divisionManager"/>)' value='<nest:write property="positionId"/>'>
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
																				<td  class="boldText"><nest:write property="probOfficerInd"/></td>
																				<td  class="boldText"><nest:write property="positionName"/></td>
																				<td><nest:write property="jobTitleDesc"/></td>
																				<td><nest:write property="programUnitDesc"/></td>
																			</tr>
																			<nest:notEmpty property="subordinates">
																				<nest:iterate property="subordinates">
																					<nest:notEmpty property="positionId">
																						<nest:notEqual property="positionId" value="">
																							<tr class="<% out.print( ((++RecordCounter) % 2 == 1) ? "normalRow" : "alternateRow" );%>">
																								<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="selectedValue" onclick='renderButtons(<nest:write property="retired"/>,<nest:write property="vacant"/>,<nest:write property="divisionManager"/>)' value='<nest:write property="positionId"/>'>
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
																												<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="selectedValue" onclick='renderButtons(<nest:write property="retired"/>,<nest:write property="vacant"/>,<nest:write property="divisionManager"/>)' value='<nest:write property="positionId"/>'>
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
											</logic:notEmpty>	
										</table>
										<script>
							findAndInitializeRadio();
						</script>
<!-- END RESULTS TABLE -->

										<div class="spacer"></div>
<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%" cellpadding="1" cellspacing="1">
											<tr>
												<td align="center">    
													<jims2:isAllowed requiredFeatures="<%=Features.CS_POS_VACATE_CSC%>">
														<html:submit property="submitAction" styleId="vacateButton" styleClass="hidden" onclick="return disableSubmit(this, this.form)"><bean:message key="button.vacatePosition"/> </html:submit>
													</jims2:isAllowed>
													<jims2:isAllowed requiredFeatures="<%=Features.CS_POS_ASSIGN_CSC%>">
														<html:submit property="submitAction" styleId="assignButton" styleClass="hidden" onclick="return disableSubmit(this, this.form)"><bean:message key="button.assignPosition"/> </html:submit>
													</jims2:isAllowed>
													<jims2:isAllowed requiredFeatures="<%=Features.CS_POS_REASSIGN_CSC%>">
														<html:submit property="submitAction" styleId="reassignButton" styleClass="hidden" onclick="return disableSubmit(this, this.form)"><bean:message key="button.reassignPosition"/> </html:submit>
													</jims2:isAllowed>
												</td>
											</tr>
											<tr>
												<td align="center">
													<jims2:isAllowed requiredFeatures="<%=Features.CS_POS_VIEW_CSC%>">
														<html:submit property="submitAction" styleId="viewButton" styleClass="hidden" onclick="return disableSubmit(this, this.form)"><bean:message key="button.viewPosition"/> </html:submit>
													</jims2:isAllowed>
													<jims2:isAllowed requiredFeatures="<%=Features.CS_POS_UPDATE_CSC%>">
														<html:submit property="submitAction" styleId="updateButton" styleClass="hidden" onclick="return disableSubmit(this, this.form)"><bean:message key="button.updatePosition"/> </html:submit>
													</jims2:isAllowed>
													<jims2:isAllowed requiredFeatures="<%=Features.CS_POS_CREATE_CSC%>">
														<html:submit property="submitAction" styleId="createButton" styleClass="visibleInline" onclick="return disableSubmit(this, this.form)"><bean:message key="button.createNewPosition"/> </html:submit>
													</jims2:isAllowed>
												</td>
											</tr>
											<tr>
												<td align="center">
													<jims2:isAllowed requiredFeatures="<%=Features.CS_POS_REPORTS_CSC%>">
														<html:submit property="submitAction" styleId="positionHistoryButton" styleClass="hidden" onclick="return disableSubmit(this, this.form)"><bean:message key="button.positionHistory"/> </html:submit>
													</jims2:isAllowed>
													<jims2:isAllowed requiredFeatures="<%=Features.CS_POS_REPORTS_CSC%>">
														<html:submit property="submitAction" styleId="staffHistoryButton" styleClass="hidden" onclick="return disableSubmit(this, this.form)"><bean:message key="button.staffHistory"/> </html:submit>
													</jims2:isAllowed>
													<jims2:isAllowed requiredFeatures="<%=Features.CS_POS_REPORTS_CSC%>">
														<html:submit property="submitAction" styleId="puHistoryButton" styleClass="hidden" onclick="return disableSubmit(this, this.form)"><bean:message key="button.programUnitHistory"/> </html:submit>
													</jims2:isAllowed>
													<jims2:isAllowed requiredFeatures="<%=Features.CS_POS_REPORTS_CSC%>">
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.retiredPositions" /> </html:submit>
													</jims2:isAllowed>
												</td>
											</tr>
											<tr>
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.back" /></html:submit>
													<input type="reset" value="Reset" onclick="resetButtons()">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.cancel" /></html:submit>
														
												</td>
											</tr>
										</table>
<!-- END BUTTON TABLE -->
									</td>
								</tr>
							</table>
							<br>
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
