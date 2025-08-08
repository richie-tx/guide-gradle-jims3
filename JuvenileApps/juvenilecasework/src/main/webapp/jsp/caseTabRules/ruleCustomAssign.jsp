<!DOCTYPE HTML>

<%-- 07/05/2005	 Aswin Widjaja - Create JSP --%>
<%-- 08/06/2015 RYoung          #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@page import="naming.UIConstants"%>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<html:base />

<title><bean:message key="title.heading" /> - ruleCustomAssign.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/groups.js"></script>

<script type="text/javascript">
function reloadGroup(theForm, group2Id, group3Id)
{
	if (theForm.group1Id.options[theForm.group1Id.selectedIndex].value != "")
	{
		updateGroup2(theForm);
		
		for(i = 0; i < theForm.group2Id.options.length; i++)
		{
			if(theForm.group2Id.options[i].value == group2Id)
			{
				theForm.group2Id.options.selectedIndex = i;
				break;
			}
		}
		
		if (theForm.group2Id.options[theForm.group2Id.selectedIndex].value != "")
		{
			updateGroup3(theForm);
			
			for(i = 0; i < theForm.group3Id.options.length; i++)
			{
				if(theForm.group3Id.options[i].value == group3Id)
				{
					theForm.group3Id.options.selectedIndex = i;
					break;
				}
			}
		}
	}
}

function validateRuleCustomAssign(theForm, checkBoxListName)
{
  if( theForm['condRem'] )
    return true;
  else
  {
    alert("You need to have a rule selected before you may proceed.");
    return false;
  }
}

<logic:iterate indexId="groupIterIndex" id="groupIter" name="juvenileCaseworkConditionsForm" property="groups">
	groups[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="groupId" />", "<bean:write name="groupIter" property="name" filter="false"/>");
	
	
	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="subgroups">
		var innerGroup = new subgroup("<bean:write name="groupIter2" property="groupId" />", "<bean:write name="groupIter2" property="name"  filter="false"/>");
		groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>] = innerGroup;
		
		
		<logic:iterate indexId="groupIterIndex3" id="groupIter3" name="groupIter2" property="subgroups">
			var innerGroup = new subgroup("<bean:write name="groupIter3" property="groupId" />", "<bean:write name="groupIter3" property="name"  filter="false"/>");
			groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>].subgroups[<bean:write name="groupIterIndex3"/>] = innerGroup;
		
		</logic:iterate>
	</logic:iterate>
</logic:iterate>

</script>
</head>

<bean:define id="group1Caption" name="juvenileCaseworkConditionsForm" property="group1Caption" type="java.lang.String"/>
<bean:define id="group2Caption" name="juvenileCaseworkConditionsForm" property="group2Caption" type="java.lang.String"/>
<bean:define id="group3Caption" name="juvenileCaseworkConditionsForm" property="group3Caption" type="java.lang.String"/>
<bean:define id="literalCaption" name="juvenileCaseworkConditionsForm" property="conditionLiteralCaption" type="java.lang.String"/>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  onload="javascript:reloadGroup(document.forms[0],'<bean:write name="juvenileCaseworkConditionsForm" property="group2Id"/>', '<bean:write name="juvenileCaseworkConditionsForm" property="group3Id"/>')">
<%@ taglib uri="/WEB-INF/condition.tld" prefix="jims2" %>
<html:form action="/displayCasefileSupervisionConditions.do?standard=false" target="content" >
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|110">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" /> - Casefile - Assign Custom Rules</td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class="spacer"></div>
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
			<ul>
				<li>Select Rules of Probation fields, then select Go button to continue.</li>
				<li>Select box(es) next to Rule Literal(s) to be added, then select Add Selected button.</li>
				<li>Select box(es) next to Rule Literal(s) to be removed, then select Remove Selected button.</li>
				<li>Select Next button to view Assign Custom Rules screen</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN CASEFILE HEADER TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>


<%-- END CASEFILE HEADER TABLE --%>
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top"><%-- BEGIN BLUE TABS TABLE --%>

      <%--tabs start--%> 
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="rulestab" />
        <tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
      </tiles:insert>
      <%--tabs end--%>

			<%-- BEGIN DETAIL TABLE --%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  				<td valign="top" align="center">
  					<div class="spacer"></div>
      			<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
      				<tr>
      					<td valign="top" class="detailHead" nowrap="nowrap">Custom Rules of Probation</td>
      					<td class="detailHead" align="right" nowrap="nowrap">&nbsp;</td>
      				</tr>
      				<tr>
      					<td colspan="2">
					
        					<table align="center" width='100%' border="0" cellpadding="4" cellspacing="1">
        						<tr>
        							<td valign="top" class="formDeLabel" width='1%' nowrap="nowrap"><bean:message key="<%=group1Caption%>" /></td>
        							<td class="formDe">
											  <html:select property="group1Id" size="1" onchange="updateGroup2(this.form);">
          								<html:option value=""><bean:message key="select.generic" /></html:option>
          								<html:optionsCollection property="groups" value="groupId" label="name" />
        								</html:select>
        							</td>
        							<td valign="top" class="formDeLabel" width='1%' nowrap="nowrap"><bean:message key="<%=group2Caption%>" /></td>
        							<td class="formDe">
											  <html:select property="group2Id" disabled="true" onchange="updateGroup3(this.form);">
          								<html:option value=""><bean:message key="select.generic" /></html:option>
        								</html:select>
        							</td>
        							<td valign="top" class="hidden" width='1%' nowrap="nowrap"><bean:message key="<%=group3Caption%>" /></td>
        							<td nowrap="nowrap" class="hidden">
											  <html:select property="group3Id" disabled="true">
          								<html:option value=""><bean:message key="select.generic" /></html:option>
        								</html:select>
        							</td>
        							<td nowrap="nowrap" class="formDe">
        								<html:submit property="submitAction">
        									<bean:message key="button.goCustomRules"></bean:message>
        								</html:submit>
        							</td>							
        						</tr>
        					</table>

        					<%-- Query results. --%>
        					<table width="100%" cellpadding="2" cellspacing="1" id="ruleListTable">
        						<tr>
        							<td class="formDeLabel" width="1%"></td>
        							<td class="formDeLabel"> Rule Name</td>
        							<td class="formDeLabel"> Rule Literal(s)</td>
        						</tr>
									
        						<logic:iterate id="condition" name="juvenileCaseworkConditionsForm" property="conditions">
          						<tr id="cond1">
          							<td width="1%"><input type="checkbox" name="condSel" value="<bean:write name='condition' property='conditionId'/>"></td>
          							<td width='30%'><bean:write name="condition" property="name" filter="false"/></td>
          							<td><bean:write name="condition" property="description" filter="false"/></td>
          						</tr>
        						</logic:iterate>
        						
        						<tr>
        							<td colspan="3">
        								<table width='100%' cellpadding="2" cellspacing="1">
          								<tr>
          									<td align='center'>
          										<html:submit property="submitAction" styleClass="greenButton">
          											<bean:message key="button.addCustomRules"></bean:message>
          										</html:submit>										
          									</td>
          								</tr>
        								</table>
        							</td>
        						</tr>    						
        					</table>
					
        					<%-- Current slections. --%>
        					<div class="spacer"></div>
        					<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
          					<tr>
          						<td id="runningList">
          							<table width="100%">
            							<tr valign="top" class="detailHead">
            								<td class="subHead" colspan="2">Rule List</td>
            							</tr>
        							
            							<logic:iterate id="condition" name="juvenileCaseworkConditionsForm" property="selectedConditions">
              							<tr id="cond1">
              								<td width="1%"><input type="checkbox" name="condRem" value="<bean:write name='condition' property='conditionId'/>"></td>
              								<td><bean:write name="condition" property="name" filter="false"/> - <bean:write name="condition" property="description" filter="false"/></td>
              							</tr>
              							</logic:iterate>
							
              							<tr>
              								<td align="center" colspan="2">
              									<html:submit property="submitAction">
              										<bean:message key="button.removeCustomRules"></bean:message>
              									</html:submit>										
              								</td>
              							</tr>
              						</table>
            						</td>
            					</tr>
            			  <tr>
        					</table>
        					<div class="spacer"></div>
   					    </td>
   					  </tr>
					</table>
					<%-- BEGIN BUTTON TABLE --%>
  				<div class="spacer"></div>					
  				<table border="0" width="100%">
						<tr>
							<td align="center">
						  <html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit> 
  							<html:submit property="submitAction" onclick="return validateRuleCustomAssign(this.form);">
							  <bean:message key="button.next"></bean:message>
  							</html:submit> 
							  <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
  						</td>
						</tr>
  				</table>
  				<div class="spacer"></div>
  				<%-- END BUTTON TABLE --%>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>



</html:form>
<%-- END FORM --%>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

