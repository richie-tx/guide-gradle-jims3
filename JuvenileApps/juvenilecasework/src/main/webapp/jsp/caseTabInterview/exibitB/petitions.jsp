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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		//$('input[type=checkbox]').prop('checked', false);
		$("input[name='selectedReferral']").val("");
		
		$("#nextBtn").click(function(e){
			e.preventDefault()
			$("#currentTab").val("programServices");
			 var checked = $("input[type=checkbox]:checked").length;
			
			if (!checked ) {
				alert("Please select at least one referral number or no petition filed.");
				return false;
			}
			
			if (true){
				$('form').attr('action','/JuvenileCasework/handleExhibitBAction.do?submitAction=Next');
				$('form').submit();
				spinner();		
			}
			
		})
		
		$("input[type='checkbox']").on("change", function(){
			   if($(this).prop('checked')) {
			   	console.log("Checked");
			   	var id= $(this).val();
			   	console.log("Id: " + id);
			   	$("#"+id).val(id);
			   	console.log("Selected value: " + $("#"+id).val() );
			   } else {
			    console.log("UnCheck"); 
			   }
		});
	})
</script>

<title><bean:message key="title.heading"/>/petitions.jsp</title>
</head>
<body>

<html:form action="/handleExhibitBAction" target="content">
<html:hidden styleId="currentTab" name="exhibitBForm" property="currentTab"/>
	<table width='100%'> 
		<tr> 
			<td align="center" class="header">
				Juvenile Casework - Conduct Interview<br>
				Exhibit B - Petitions
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
																				<logic:notEmpty name="exhibitBForm" property="petitions">
																				<tr>
																					<td valign=top >
																						<table align="center" cellpadding="2" cellspacing="1" border="0" width='100%'>
																							<tr bgcolor='#cccccc'>
																								<td></td>
																								<td>Ref No</td>
																								<td>Referral Date</td>
																								<td>Petition Allegation</td>
																								<td>Filed Date</td>
																								<td>Petition Number</td>
																								<td>Petition Status</td>
																							</tr>
																							<%String rowClass = "";%>  
																							<logic:iterate indexId="index" id="petition" name="exhibitBForm" property="petitions">
																								<bean:define id="referralId" name="petition" property="referralNum" type="java.lang.String"></bean:define>
															    								<% rowClass = ((index.intValue()) % 2 == 1) ? "normalRow" : "alternateRow";	
															    								%>
															    								<tr class=<%=rowClass%> >
															    									<td>
															    										<input type="checkbox" name="petitions.selectedReferralNum"  value="<%=referralId %>"  autocomplete="off"  />
															    										<input id="<%= referralId %>" type="hidden" name="selectedReferral"/>
															    										
															    									</td>	
															    									
															    									<td><bean:write name="petition" property="referralNum"/></td>
															    									<td><bean:write name="petition" property="referralDate" format="MM/dd/yyyy"/></td>
															    									<td><bean:write name="petition" property="offenseCodeId"/></td>
															    									<td><bean:write name="petition" property="courtDate"/></td>
															    									<td><bean:write name="petition" property="petitionNum"/></td>
															    									<td><bean:write name="petition" property="petitionStatus"/></td>
															    								</tr>
														    								</logic:iterate>
																						</table>
																					</td>
																				</tr>
																				</logic:notEmpty>
																				<logic:empty name="exhibitBForm" property="petitions">
																					<tr bgcolor='#cccccc'>
																						<td colspan = 6 ><input type="checkbox"/>No Petition Filed</td>
																					</tr>
																				</logic:empty>
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