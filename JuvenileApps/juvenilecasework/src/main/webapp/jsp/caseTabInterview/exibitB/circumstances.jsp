<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<html:base />
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript">
	function textCounter(field, maxlen) {
		if (field.value.length > maxlen + 1) {
			alert("Your input has been truncated to " +maxlen +" characters.");
		}
	
		if (field.value.length > maxlen) {
			field.value = field.value.substring(0, maxlen);
		}
	} 
	
	$(document).ready(function(){
			
			$("#printBtn").click(function(e){
					spinner();
					$.ajax({
				        url: '/JuvenileCasework/handleExhibitBAction.do?submitAction=Print',
				        type: 'post',
				        data: $('form#exhibitData').serialize(),
				        success: function(data, textStatus , xhr) {
				         	if (200 == xhr.status){
				         		$(".overlay").remove();
				         		//console.log("Data: " + data);
				         		if (isJson(data)) {
				         			alert((JSON.parse(data)).error);
				         		} else {
				         			setTimeout(function(){alert("Documents can be located in the Documents tab")}, 1000);
				         			//sessionStorage.setItem("isReportGenerated", "true");
				         		}
				         		
				         	}
				         	
				        }
				    });
			})
			
		function isJson(str) {
	   		try {
	        	json = JSON.parse(str);
	    	} catch (e) {
	        	return false;
	    	}
    		return true;
		}
		
			
	})
</script>
<title><bean:message key="title.heading"/>/circumstances.jsp</title>
</head>
<body>
<html:form styleId="exhibitData" action="/handleExhibitBAction" target="content">
	<table width='100%'> 
		<tr> 
			<td align="center" class="header">
				Juvenile Casework - Conduct Interview<br>
				Exhibit B - Circumstances
			</td>
		</tr> 
	</table> 
	<div class='spacer'></div>
	<tiles:insert page="../../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
	</tiles:insert>
	<div class='spacer'></div>
	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr valign=top>
			<td>
				<%-- BEGIN TAB  --%>
				<tiles:insert page="../../caseworkCommon/casefileTabs.jsp" flush="true">
	  				<tiles:put name="tabid" value="casefiledetailstab"/>	
	  				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  				</tiles:insert>	
  				<%-- BEGIN BORDER TABLE BLUE TABLE --%>
  				<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  					<tr>
  						<td valign=top align=center>
  							<div class='spacer'></div>
  							<table width='98%' border="0" cellpadding="0" cellspacing="0">
  								<tr>
  									<td>
  										<table width='100%' border="0" cellpadding="0" cellspacing="0" >
											<tr>
												<td valign=top>
												<%--tabs start--%>
													<tiles:insert page="../../caseworkCommon/casefileInfoTabs.jsp" flush="true">
														<tiles:put name="tabid" value="interviewtab"/>
		  												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
													</tiles:insert>	
												<%--tabs end--%>
												</td>
											</tr>
											<tr>
										  		<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
										  	</tr>
										</table>
										<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
											<tr>
												<td valign=top align=center>
													<div class='spacer'></div>
													<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td valign=top>
																<table width='100%' border="0" cellpadding="0" cellspacing="0" >
																	<tr>
																		<td valign=top>
																			<%--tabs start--%>
																				<tiles:insert page="../../caseworkCommon/exhibitBTab.jsp" flush="true">
																					<tiles:put name="tabid"><bean:write name="exhibitBForm" property="currentTab"/></tiles:put>
																				</tiles:insert>
																			<%--tabs end--%>
																		</td>
																	</tr>
																	<tr><td bgcolor=#ff6666><img src="/<msp:webapp/>images/spacer.gif" width=5></td></tr>
																</table>
																<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
																	<tr>
																		<td valign=top align=center>
																		<div class='spacer'></div>
																		<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
																				<tr>
																					<td  valign=top>
																						<div class='spacer'></div>
																						<table cellpadding="2" cellspacing="1" border="0" width='100%'>
																							<tr>
																								<td class=formDeLabel nowrap width='1%' colspan=4>
                    																				The child and /or family is receiving or has previously received services from TDFPS or the Harris Center <font color="red">(LIST SERVICES)</font>
											                    									<tiles:insert page="../../caseworkCommon/spellCheckTile.jsp" flush="false">
											               												<tiles:put name="tTextField" value="firstQuestion"/>
											               												<tiles:put name="tSpellCount" value="spellBtn1" />
											             											</tiles:insert> 
                    																			</td>
																							</tr>
																							<tr>
                      																			<td class='formDe' colspan='4'><html:textarea styleId="firstQuestion" 
                      																															rows="8" style="width:100%" 
                      																															name="exhibitBForm" 
                      																															property="firstCircQuestion"
                      																															onkeyup="textCounter(this,1000);"
                      																															 ></html:textarea></td>
                    																		</tr>
                    																		<tr>
																								<td class=formDeLabel nowrap width='1%' colspan=4>
                    																					The nature of the circumstances in the child's home, which may include the offense, required the child's removal.
											                    									<tiles:insert page="../../caseworkCommon/spellCheckTile.jsp" flush="false">
											               												<tiles:put name="tTextField" value="secondQuestion"/>
											               												<tiles:put name="tSpellCount" value="spellBtn2" />
											             											</tiles:insert> 
                    																			</td>
																							</tr>
																							<tr>
                      																			<td class='formDe' colspan='4'><html:textarea styleId="secondQuestion" 
                      																											rows="8" style="width:100%" 
                      																											name="exhibitBForm" 
                      																											property="secondCircQuestion"
                      																											onkeyup="textCounter(this,1000);"
                      																											 ></html:textarea></td>
                    																		</tr>
                    																		<tr>
																								<td class=formDeLabel nowrap width='1%' colspan=4>
                    																				<font color="red">LIST AND EXPLAIN</font> any other specific circumstances not addressed above.
											                    									<tiles:insert page="../../caseworkCommon/spellCheckTile.jsp" flush="false">
											               												<tiles:put name="tTextField" value="thirdQuestion"/>
											               												<tiles:put name="tSpellCount" value="spellBtn3" />
											             											</tiles:insert> 
                    																			</td>
																							</tr>
																							<tr>
                      																			<td class='formDe' colspan='4'><html:textarea styleId="thirdQuestion" 
                      																											rows="8" style="width:100%" 
                      																											name="exhibitBForm" 
                      																											property="thirdCircQuestion"
                      																											onkeyup="textCounter(this,1000);"
                      																											 ></html:textarea></td>
                    																		</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																				
																				</tr>
																		</table>
																			<div class='spacer'></div>
																			<!--<html:submit styleId="printBtn" property="submitAction"><bean:message key="button.print"/></html:submit> -->
																			<input id="printBtn" type="button" value="Print"/>
																			<div class='spacer'></div>
																		</td>
																	</tr>
																</table>
																<div class='spacer'></div>
															</td>
														</tr>
													</table>
													<div class='spacer'></div>
												</td>
											</tr>			
										</table>
										<div class='spacer'></div>
  									</td>
  								</tr>
  							</table>
  							<div class='spacer'></div>
  						</td>
  					</tr>
  				</table>
  				<div class='spacer'></div>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html>