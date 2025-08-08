<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2006		AWidjaja Create JSP--%>
<%-- 08/30/2013		CShimek		# 75843 Added other time frame description field --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>



<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- goalUpdate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%--THIS LINE FOR STRUTS VALIDATION--%>
<html:javascript formName="caseplanGoalUpdateForm" />
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casetabCaseplan/goalUpdate.js"></script>
<script type="text/javascript">

function validateFields(theForm)
{
	clearAllValArrays();

  	customValRequired("currentGoalInfo.domainTypeCd", "Domain Type is required", "");
  	customValRequired("currentGoalInfo.personsResponsibleIds", "Person(s) Responsible is required", "");
  	customValRequired("currentGoalInfo.timeFrameCd", "Time Frame is required", "");
	var fld = document.getElementById("tFId");
	if (fld != null)
	{
		if (fld.options[fld.selectedIndex].value == "OT")
		{
			customValRequired("currentGoalInfo.otherTimeFrameDesc", "Explain Other is required", "");
			addDB2FreeTextValidation("currentGoalInfo.otherTimeFrameDesc", "<bean:message key='errors.goalcomments' arg0='Explain Other'/>");
		}	
	}	
  	customValRequired("currentGoalInfo.goal", "Goal is required", "");
  	addDB2FreeTextValidation("currentGoalInfo.goal", "<bean:message key='errors.goalcomments' arg0='Goal'/>");
  	customValMaxLength("currentGoalInfo.goal", "Goal cannot be more than 500 characters",500);
  	
  	addDB2FreeTextValidation("currentGoalInfo.intervention", "<bean:message key='errors.goalcomments' arg0='Intervention'/>");
	customValMaxLength("currentGoalInfo.intervention", "Intervention cannot be more than 1500 characters",1500);
	
	addDB2FreeTextValidation("currentGoalInfo.progressNotes", "<bean:message key='errors.goalcomments' arg0='Progress Notes'/>");
	customValMaxLength("currentGoalInfo.progressNotes", "Progress Notes cannot be more than 1100 characters",1100);
  	if(validateCustomStrutsBasedJS(theForm))
	{
    	return validateCaseplanGoalUpdateForm(theForm);
  	}
 	return false; 
}

function updateValidate(theFormElem)
{ 
	clearAllValArrays();
	<logic:equal name="caseplanForm" property="goalInfoEditable" value="true">
		customValRequired("currentGoalInfo.domainTypeCd", "Domain Type is required", "");
		customValRequired("currentGoalInfo.personsResponsibleIds", "Person(s) Responsible is required", "");
		customValRequired("currentGoalInfo.timeFrameCd", "Time Frame is required", "");
		var fld = document.getElementById("tFId");
		if (fld != null)
		{
			if (fld.options[fld.selectedIndex].value == "OT")
			{
				customValRequired("currentGoalInfo.otherTimeFrameDesc", "Explain Other is required", "");
				addDB2FreeTextValidation("currentGoalInfo.otherTimeFrameDesc", "<bean:message key='errors.goalcomments' arg0='Explain Other'/>");
			}	
		}	
		customValRequired("currentGoalInfo.goal", "Goal is required", "");
		addDB2FreeTextValidation("currentGoalInfo.goal", "<bean:message key='errors.goalcomments' arg0='Goal'/>");
		customValMaxLength("currentGoalInfo.goal", "Goal cannot be more than 500 characters",500);
	</logic:equal>

	addDB2FreeTextValidation("currentGoalInfo.progressNotes", "<bean:message key='errors.goalcomments' arg0='Progress Notes'/>");
	customValMaxLength("currentGoalInfo.progressNotes", "Progress Notes cannot be more than 1100 characters",1100);
	addDB2FreeTextValidation("currentGoalInfo.intervention", "<bean:message key='errors.goalcomments' arg0='Intervention'/>");
	customValMaxLength("currentGoalInfo.intervention", "Intervention cannot be more than 1500 characters",1500);

	<logic:notEqual name="caseplanForm" property="currentGoalInfo.statusStr" value="PENDING">
		addDB2FreeTextValidation("currentGoalInfo.endRecommendations", "<bean:message key='errors.goalcomments' arg0='End Recommendation'/>");
		customValMaxLength("currentGoalInfo.endRecommendations", "End Recommendations cannot be more than 1000 characters",1000);
	</logic:notEqual>
	return validateCustomStrutsBasedJS(theFormElem);
}

function showRecommendationsTable()
{
	showHide( "recommendationTable1", 1 ) ;
	showHide( "recommendationTable2", 1 ) ;
}
function checkSelect(el)
{
	showHide("timeFrameDesc", 0);
	if (el.options[el.options.selectedIndex].value == 'OT')
	{
		showHide("timeFrameDesc", 1);
	}
	document.getElementById("tFDId").value = "";
}

function checkTimeFrameSel()
{

	var fld = document.getElementById("tFId");
	if (fld != null)
	{
		if (fld.options[fld.selectedIndex].value == "OT") 
		{
			showHide("timeFrameDesc", 1);
		}	
	}	
}
</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/displayUpdateGoalSummary" target="content">

<logic:equal name="caseplanForm" property="action" value="create">
	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|65">
</logic:equal>

<logic:equal name="caseplanForm" property="action" value="update">
   	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|74">
</logic:equal> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Juvenile Casework - Process Caseplan - 
			<logic:equal name="caseplanForm" property="action" value="create">Create</logic:equal>
			<logic:equal name="caseplanForm" property="action" value="update">Update</logic:equal>
			Goal
		</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
			<ul id='editInstruct'>
				<li>Enter information and select Next button to view Summary.</li>
			</ul>
		</td>
	</tr>
	<tr id='reqFieldsInstruct'> 
		<td class="required"><bean:message key='prompt.diamond' />&nbsp;Required Fields</td> 
	</tr> 
</table>
<%-- END INSTRUCTION TABLE --%>

<logic:messagesPresent>
	<div class='spacer'></div>
	<table width="100%">
		<tr>		  
			<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
		</tr>   	  
	</table>
	<div class='spacer'></div>
</logic:messagesPresent> 

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top'>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="goalstab"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>
						<div class='spacer'></div>	
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign='top'>
									<tiles:insert page="../caseworkCommon/casePlanTabs.jsp" flush="true">
										<tiles:put name="tabid" value="Caseplan"/>
										<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
									</tiles:insert>				
								</td>
							</tr>
							<tr>
								<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
							</tr>
						</table>

	  					<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td valign='top' align='center'>
									<div class='spacer'></div>
<%-- BEGIN Goal TABLE --%>
									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td class='detailHead' colspan='2' nowrap='nowrap'>Goal Information</td>
										</tr>
										<tr>
											<td colspan='2'>
												<table align="center" width='100%' cellpadding="4" cellspacing="1">
													<tr>
														<td class='formDeLabel' valign='top' width='1%' nowrap='nowrap'>
															<logic:notEqual name="caseplanForm" property="action" value="update">
																<bean:message key='prompt.diamond' />
															</logic:notEqual>
															<bean:message key="prompt.domain" /> <bean:message key="prompt.type"/>
														</td>
      													<td class='formDe'>
      														<logic:equal name="caseplanForm" property="goalInfoEditable" value="true">
      															<html:select name="caseplanForm" property="currentGoalInfo.domainTypeCd">
      																<html:option value=""><bean:message key="select.generic" /></html:option>
      																<html:optionsCollection name="caseplanForm" property="dormainTypeList" value="code" label="description" />
      															</html:select>
      														</logic:equal>
      														<logic:notEqual name="caseplanForm" property="goalInfoEditable" value="true">
    															<bean:write name="caseplanForm" property="currentGoalInfo.domainTypeStr"/>
      														</logic:notEqual>
      													</td>
      												</tr>
      												<tr>
      													<td class='formDeLabel' valign='top' nowrap='nowrap'>
															<logic:notEqual name="caseplanForm" property="action" value="update">
																<bean:message key='prompt.diamond' />
															</logic:notEqual> 
															<bean:message key="prompt.personsResponsible"/>
														</td>
      													<td class='formDe'>
      														<logic:equal name="caseplanForm" property="goalInfoEditable" value="true">
      															<html:select name="caseplanForm" property="currentGoalInfo.personsResponsibleIds" size="5" multiple="true">
    																<html:optionsCollection name="caseplanForm" property="personsResponsibleList" value="name" label="name" />
      															</html:select>
      														</logic:equal>
		      												<logic:notEqual name="caseplanForm" property="goalInfoEditable" value="true">
	    														<table width='100%' cellpadding="0" cellspacing="0">
	    															<logic:notEmpty name="caseplanForm" property="currentGoalInfo.personsResponsibleIds">
	    																<logic:iterate name="caseplanForm" property="currentGoalInfo.personsResponsibleIds" id="idIndex">
	      																<tr>
	        																<td><bean:write name="idIndex"/></td>
	      																</tr>
	    																</logic:iterate>
	    															</logic:notEmpty>
	    														</table>
		      												</logic:notEqual>
      													</td>
      												</tr>
      												<tr>
      													<td class='formDeLabel'>
															<logic:notEqual name="caseplanForm" property="action" value="update">
																<bean:message key='prompt.diamond' />
															</logic:notEqual> 
															<bean:message key="prompt.time"/> <bean:message key="prompt.frame"/>
														</td>
      													<td class='formDe'>
      														<logic:equal name="caseplanForm" property="goalInfoEditable" value="true">
      															<html:select name="caseplanForm" property="currentGoalInfo.timeFrameCd" styleId="tFId">
      																<html:option value=""><bean:message key="select.generic" /></html:option>
     																<html:optionsCollection name="caseplanForm" property="timeFrameList" value="code" label="description" />
      															</html:select>
      														</logic:equal>
	    													<logic:notEqual name="caseplanForm" property="goalInfoEditable" value="true">
    		  													<bean:write name="caseplanForm" property="currentGoalInfo.timeFrameStr"/>
      														</logic:notEqual>
      													</td>
      												</tr>
													<tr id="timeFrameDesc" class="hidden">
      													<td class='formDeLabel'>
															<bean:message key='prompt.diamond' /> <bean:message key='prompt.explain' /> <bean:message key='prompt.other' />
      													</td>
      													<td class='formDe'>
      														<html:text name="caseplanForm" property="currentGoalInfo.otherTimeFrameDesc" size="50" maxlength="50" styleId="tfDId"/>
      													</td>
      												</tr>      																									
      												<tr>
      													<td class='formDeLabel' colspan='2' nowrap='nowrap'>
															<logic:notEqual name="caseplanForm" property="action" value="update">
																<bean:message key='prompt.diamond' />
															</logic:notEqual> 
  															<bean:message key="prompt.goal" />
															<logic:notEqual name="caseplanForm" property="action" value="view">
															  &nbsp;
                      											<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                      												<tiles:put name="tTextField" value="currentGoalInfo.goal" />
                      												<tiles:put name="tSpellCount" value="spellBtn1" />
                    											</tiles:insert>
                    											(Max. characters allowed: 500)
															</logic:notEqual> 
														</td>
      												</tr>
      												<tr>
      													<td class='formDe' colspan='2'>
      														<logic:equal name="caseplanForm" property="goalInfoEditable" value="true">
      															<html:textarea style="width:100%" rows="3" name="caseplanForm" property="currentGoalInfo.goal" onmouseout="textCounter(this, 500);" onkeydown="textCounter( this, 500)"/>
      														</logic:equal>
      														<logic:notEqual name="caseplanForm" property="goalInfoEditable" value="true">
      															<bean:write name="caseplanForm" property="currentGoalInfo.goal"/>
      														</logic:notEqual>
      													</td>
      												</tr>
      										<%--Start of adding Intervention JIMS200075816 --%>
			      									<tr>
      													<td class='formDeLabel' colspan='2' nowrap='nowrap'>
    	  													<table width="100%">
    		  													<tr>
    			  													<td class='formDeLabel'><bean:message key="prompt.intervention"/> 
      																	<logic:notEqual name="caseplanForm" property="action" value="view">
      														  			&nbsp;
                            												<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                            													<tiles:put name="tTextField" value="currentGoalInfo.intervention" />
                            													<tiles:put name="tSpellCount" value="spellBtn3" />
                          													</tiles:insert>
                          													(Max. characters allowed: 1500)
      																	</logic:notEqual> 
																	</td>			  											
    			  												</tr>
    	  													</table>
      													</td>
      												</tr>
      												<tr>
      										 			<td class='formDe' colspan='2'>
   															<logic:equal name="caseplanForm" property="goalInfoEditable" value="true">
   																<html:textarea style="width:100%" rows="3" name="caseplanForm" property="currentGoalInfo.intervention" onmouseout="textCounter(this, 1500);" onkeydown="textCounter( this, 1500 )"/>
   															</logic:equal>
   															<logic:notEqual name="caseplanForm" property="goalInfoEditable" value="true">
   																<bean:write name="caseplanForm" property="currentGoalInfo.intervention"/>
   															</logic:notEqual>
   														</td>
		      										</tr>
      										<%--End of adding Intervention JIMS200075816 --%>
      											<logic:notEqual name="caseplanForm" property="action" value="create">
		      										<tr>
		      											<td class='formDeLabel' colspan='2' nowrap='nowrap'>
		    	  											<table width="100%">
		    		  											<tr>
		    			  											<td class='formDeLabel'><bean:message key="prompt.progress"/> <bean:message key="prompt.notes"/>
		      															<logic:notEqual name="caseplanForm" property="action" value="view">
		      															
		      																&nbsp;
							                            					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
							                            						<tiles:put name="tTextField" value="currentGoalInfo.progressNotes" />
							                            						<tiles:put name="tSpellCount" value="spellBtn2" />
							                          						</tiles:insert>
							                          						(Max. characters allowed: 1100)
     																		</logic:notEqual> 
																	</td>			  											
   			  														<td align='right'>
<%-- 03 oct 2007 - mjt - Defect 41711 ... End Recommendation should not be "enabled" when adding a new goal--%>
<%-- 10/05/2007 - awidjaja enable End Recommendation for Update Goal only. --%>
																		<logic:equal name="caseplanForm" property="action" value="update">
  																		<logic:notEqual name="caseplanForm" property="currentGoalInfo.statusStr" value="PENDING">
     																		  		<a href="#reco">&nbsp;End Recommendation</a>
   																	  		</logic:notEqual>
																		</logic:equal>
   																	</td>
    		  													</tr>
	    	  												</table>
	      												</td>
	      											</tr>
		      										<tr>       
		      											<td class='formDe' colspan='2'>
															<html:textarea style="width:100%" rows="3" name="caseplanForm" property="currentGoalInfo.progressNotes" onmouseout="textCounter(this, 1100);" onkeydown="textCounter( this, 1100 )"/>
														</td>
		      										</tr>
      										
		      										<tr id="recommendationTable1" class="hidden">
	    	    										<td class='formDeLabel' colspan='2' nowrap='nowrap'>
	        		  										<table width='100%'>
	          													<tr>
	          														<td class='formDeLabel' colspan='2' nowrap='nowrap'><bean:message key="prompt.endRecommendations"/>
	  																	<logic:equal name="caseplanForm" property="action" value="update">
	    																	<logic:notEqual name="caseplanForm" property="currentGoalInfo.statusStr" value="PENDING">
	        																	&nbsp;
	                              												<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
	                              													<tiles:put name="tTextField" value="currentGoalInfo.endRecommendations" />
	                              													<tiles:put name="tSpellCount" value="spellBtn3" />
	                            												</tiles:insert>
	     																  	</logic:notEqual>
	     																  	(Max. characters allowed: 1000)
	  																	</logic:equal>
																	</td>
	          													</tr>
	           												</table>
	        											</td>
	      											</tr>
		      										<tr id="recommendationTable2" class="hidden">
		      										  	<td class="formDe" colspan="2"><html:textarea style="width:100%" rows="3" name="caseplanForm" property="currentGoalInfo.endRecommendations" onmouseout="textCounter(this, 1000);" onkeydown="textCounter(this, 1000)"/></td>          											
		      										</tr>
		      										</logic:notEqual>
		      									</table>
	    	  								</td>
	      								</tr>
	      							</table>
<%-- END GOAL INFORMATION TABLE --%>
		      						<div class='spacer'></div>    
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<logic:equal name="caseplanForm" property="action" value="update">
													<html:submit property="submitAction" onclick="return updateValidate(this.form);"><bean:message key="button.next"/></html:submit>
												</logic:equal>
												<logic:notEqual name="caseplanForm" property="action" value="update">
													<html:submit property="submitAction" onclick="return validateFields(this.form);"><bean:message key="button.next"/></html:submit>
												</logic:notEqual>   
												<html:reset><bean:message key="button.reset"/></html:reset>
												<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
											</td>
										</tr>
									</table>
<%-- END BUTTON TABLE --%>
									<div class='spacer'></div>	
								</td>
	  						</tr>
	  					</table>
<%-- END DETAIL TABLE --%>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
			<div class='spacer'></div>
		</td>
	</tr>
</table>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>