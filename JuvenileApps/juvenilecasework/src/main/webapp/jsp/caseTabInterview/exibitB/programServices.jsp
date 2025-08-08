
<!DOCTYPE HTML>

<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@page import="java.util.*"%>
<%@page import="ui.juvenilecase.interviewinfo.form.ProgramService"%>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" /> 
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<html:base />
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<script type="text/javascript">
	var userInputSize = 0;
	$(document).ready(function(){
		userInputSize = userInputSize +1;
		$("#programServices").append(	'<tr>'
				+ '<td><input class="'+ userInputSize +'" type="radio" name="'+userInputSize+'" value="P" onclick=" newPsUserInput ('+userInputSize+')"  /></td>'
				+ '<td><input class="'+ userInputSize +'" type="radio" name="'+userInputSize+'" value="S" onclick=" newPsUserInput ('+userInputSize+')"/></td>'
				+ '<td><input id="program' + userInputSize + '" class="'+ userInputSize +'" name="program"  onchange=" changeValidation('+ userInputSize +')"/></td>'
				+ '</tr>');
		
		
		$("#nextBtn").click(function(e){
			e.preventDefault()
			$("#currentTab").val("circumstances");
			$('form').attr('action','/JuvenileCasework/handleExhibitBAction.do?submitAction=Next');
			$('form').submit();
			spinner();
			
		})
		
	})
	
	
	function newPsUserInput (selectedRadio){
		var className = userInputSize;
		var selectedRadioName = selectedRadio;
		
		if (  (userInputSize - selectedRadioName) == 0 ){
			//$("." + className).prop("readonly", true);
			if ( inputValidation( selectedRadio ) ){
				userInputSize = userInputSize +1;
				$("#programServices").append(	'<tr>'
												+ '<td><input class="'+ userInputSize +'" type="radio" name="'+userInputSize+'" value="P" onclick=" newPsUserInput ('+userInputSize+')"  /></td>'
												+ '<td><input class="'+ userInputSize +'" type="radio" name="'+userInputSize+'" value="S" onclick=" newPsUserInput ('+userInputSize+')" /></td>'
												+ '<td><input id="program' + userInputSize + '" class="'+ userInputSize +'" name="program" onkeyup=" changeValidation('+ userInputSize +')"  onchange=" changeValidation('+ userInputSize +')"/></td>'
												+ '</tr>');
			}
		} else {
			inputValidation( selectedRadio )
		}
		
		
	
	}
	
	function inputValidation( id ){
		program		= $("#program"+id).val();
		//service		= $("#service"+userInputSize).val();
		
		if ( program == ""
				&& userInputSize > 0) {
			alert("Program is required.");
			$("input[name='"+ id  +"']").prop("checked", false);
			return false;
		}
		
		return true;
	}
	
	function changeValidation(id){
		$("input[name='"+ id  +"']").prop("checked", false);
	}
	
	
	
	
	
</script>
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- programServices.jsp</title>
</head>
<body>
<html:form action="/handleExhibitBAction" target="content">
<html:hidden styleId="currentTab" name="exhibitBForm" property="currentTab"/>
	<table width='100%'> 
		<tr> 
			<td align="center" class="header">
				Juvenile Casework - Conduct Interview<br>
				Exhibit B - Program/Services
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
																					<td valign=top>
																						<table id="programServices" cellpadding="2" cellspacing="1" border="0" width='100%'>
																							<tr bgcolor='#cccccc'>
																								<td>Program</td>
																								<td>Service</td>
																								<td>Program &nbsp;
																									<jims:sortResults beanName="exhibitBForm" results="programServices" 
                                 																			primaryPropSort="providerProgramName" primarySortType="STRING" defaultSort="true" 
                                																		 	defaultSortOrder="ASC" sortId="1" levelDeep="3" />
																								</td> 
																								<td>Service Provider &nbsp;
																									<jims:sortResults beanName="exhibitBForm" results="programServices" 
                                 																			primaryPropSort="juvServiceProviderName" primarySortType="STRING"
                                																		 	defaultSortOrder="ASC" sortId="2" levelDeep="3" />
																								</td>
																								<td>Begin Date &nbsp;
																									<jims:sortResults beanName="exhibitBForm" results="programServices" 
                                 																			primaryPropSort="beginDate" primarySortType="DATE"
                                																		 	defaultSortOrder="ASC" sortId="3" levelDeep="3" />
																								
																								</td>
																								<td>End Date &nbsp;
																									<jims:sortResults beanName="exhibitBForm" results="programServices" 
                                 																			primaryPropSort="endDate" primarySortType="DATE"
                                																		 	defaultSortOrder="ASC" sortId="4" levelDeep="3" />
																								</td>
																								<td>Referral Status &nbsp;
																									<jims:sortResults beanName="exhibitBForm" results="programServices" 
                                 																			primaryPropSort="referralStatusDescription" primarySortType="STRING"
                                																		 	defaultSortOrder="ASC" sortId="5" levelDeep="3" />
																								</td>
																							</tr>
																							<logic:iterate indexId="index" id="programService" name="exhibitBForm" property="programServices">
																								<tr>
																									
																									<td><input type="radio" name="programService_<%= index %>" value='P_<bean:write name= "programService" property="providerProgramName"/>'/></td>
																									<td><input type="radio" name="programService_<%= index %>" value='S_<bean:write name= "programService" property="juvServiceProviderName"/>'/></td>
																									<td><bean:write name="programService" property="providerProgramName"/></td>
																									<td><bean:write name="programService" property="juvServiceProviderName"/></td>
																									<td><bean:write name="programService" property="beginDate" format="MM/dd/yyyy"/></td>
																									<td>
																										<bean:write name="programService" property="endDate" format="MM/dd/yyyy"/>
																									</td>
																									<td><bean:write name="programService" property="referralStatusDescription"/> 
																										&nbsp; <bean:write name="programService" property="referralSubStatusDescription"/> </td>
																									
																								</tr>
																							</logic:iterate>
																							
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td valign=top align=center>
																			<div class='spacer'></div>
																			<input id="nextBtn" type="button" value="Next"/>
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