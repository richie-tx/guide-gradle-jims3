<!DOCTYPE HTML>

<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Rules" tab on Casefile Details page after searching for a casefile --%>
<%-- 1/24/2006	 Blake Schwartz	Create JSP --%>
<%-- 08/21/2006  HRodriguez     Add calendar icon & implement new UI Guidelines --%>
<%-- 01/17/2007  Hien Rodriguez ER#37077 Add Tab & Buttons security features --%>
<%-- 04/19/2012  CShimek    	#73232 added allowUpdate edit to buttons for closed casefile status  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/ruleLiteral.tld" prefix="rule" %>
<%@ page import="naming.Features" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<tiles:useAttribute id="isprofile" name="isprofile" classname="java.lang.String" ignore="true"/> <!-- this attribute is optional-->



<%--BEGIN HEADER TAG--%><head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
<html:base />
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<title><bean:message key="title.heading" /> caseworkCommon - ruleDetailsTile.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<logic:equal name="action" value="edit">
<script type='text/javascript'>

$(function() {

    $( "#completionDate" ).datepicker({
      changeMonth: true,
      changeYear: true,
    });
});


var dateChanged = false;

function setDateChanged()
{
	dateChanged = true;
}

function produceCurrentDate()
{
		var theDate = new Date();
		var theMonth = theDate.getMonth() +1;
		var theDay = theDate.getDate();
		var theYear = theDate.getFullYear();

		if(theMonth < 10)
		{
			theMonth = "0" +theMonth;
		}
		
		if (theDay < 10)
		{
			theDay = "0" +theDay;
		}

		var dateStr = (theMonth + "/" + theDay + "/" + theYear);
		return dateStr;
}

function changeDateOnForm()
{
	if(dateChanged == false)
	{
		var theDateElem = document.getElementsByName('completionDate')[0] ;
		theDateElem.value = produceCurrentDate();
		dateChanged = true;
	}
}

function checkMyDates()
{
	var theDateElem = document.getElementsByName('completionDate')[0];
	var currentDateStr = produceCurrentDate();

	if( compDate1GreaterEqualDate2(currentDateStr,theDateElem.value) != true)
	{
		alert("Change Status Date cannot be greater than today");
		theDateElem.value = currentDateStr;
		return false;
	}	

	return true;
}

function textCounter( field, maxlimit )
{
  if( field.value.length > maxlimit )
  {
    alert( 'Maximum text length of ' + maxlimit + ' reached for this field - input has been truncated.' );
    field.value = field.value.substring( 0, maxlimit -1 );
  } 
  else
  {
    maxlimit = maxlimit - field.value.length;
  }
}

function validateTheEdit(theForm)
{
	clearAllValArrays();
	customValRequired ("completionDate","Status Change Date is required.","");
	customValRequired ("selectedRule.completionStatusId","Completion Status is required.","");
	addMMDDYYYYDateValidation("completionDate","Status Change Date must be a date.");
	addDB2FreeTextValidation("selectedRule.additionalInformation", "<bean:message key='errors.comments' arg0='Additional Info'/>");
	customValMaxLength("selectedRule.additionalInformation", "Additional information cannot be greater than 255 characters",255);
	return (validateCustomStrutsBasedJS(theForm) && checkMyDates());
}
</script>
</logic:equal>
</head>
<%--END HEADER TAG--%>


<%-- BEGIN DETAIL TABLE --%>

<logic:present name="isprofile"> 
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
</logic:present>

<logic:notPresent name="isprofile"> 
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
</logic:notPresent>

<script type='text/javascript'>
    clearAllValArrays();
    var cusVarCal = new CalendarPopup();
    cusVarCal.showYearNavigation();
</script>

  <tr>
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
  </tr>
  <tr>
  	<td valign=top align=center>
  		<%-- BEGIN RULES TABLE --%>
  		
  			<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  				<bean:define id="rule" name="supervisionRulesForm" property="selectedRule" />
  					<tr>
  						<td valign=top colspan="5" class="detailHead"><bean:message key="prompt.rule" /> <bean:message key="prompt.details" /></td>
  					</tr>
  					<tr> 
           		<td>
      					<table width='100%' border="0" cellpadding="4" cellspacing="1"> 
        					 <tr>
        						<td class=formDeLabel><bean:message key="prompt.entryDate" /></td>
        						<td class=formDe><bean:write name="rule" property="entryDate" formatKey="date.format.mmddyyyy" /></td>
        						<td class=formDeLabel width='1%'><bean:message key="prompt.rule" /> <bean:message key="prompt.id" /></td>
        						<td class=formDe colspan="2"><bean:write name="rule" property="ruleId" /></td>
        					</tr>
        					<tr>
        						<td class=formDeLabel><bean:message key="prompt.rule" /> <bean:message key="prompt.name" /></td>
        						<td class=formDe><bean:write name="rule" property="ruleName" /></td>
        						<td class=formDeLabel> <bean:message key="prompt.standard" /></td>
        						<td class=formDe colspan="2">
        						<logic:equal name="rule" property="standard" value="true">
      							 	STANDARD
      							</logic:equal>
      							<logic:equal name="rule" property="standard" value="false">
      								CUSTOM
      							</logic:equal>
        						
        						</td>
        					</tr>
        					<tr>
        						<td class=formDeLabel><bean:message key="prompt.monitor" /> <bean:message key="prompt.frequency" /></td>
        						<td class=formDe><bean:write name="rule" property="monitorFreq" /></td>
        						<td class=formDeLabel><bean:message key="prompt.rule" /> <bean:message key="prompt.type" /></td>
        						<td class=formDe colspan="2"><bean:write name="rule" property="ruleTypeDesc" /></td>
        					</tr>
        					<tr>
        						<td class=formDeLabel nowrap width="1%">
          						<logic:equal name="action" value="edit"><bean:message key="prompt.2.diamond"/></logic:equal>
          						<bean:message key="prompt.status" />&nbsp;<bean:message key="prompt.change" />&nbsp;<bean:message key="prompt.date" />
								</td>
        						<td class=formDe>
        							<logic:notEqual name="action" value="edit"><bean:write name="rule" property="completionDate" formatKey="date.format.mmddyyyy" /></logic:notEqual>
          						<logic:equal name="action" value="edit">
          							<html:text name="supervisionRulesForm" property="completionDate" maxlength="10" size="10" onchange="setDateChanged()" styleId="completionDate"/>
          						</logic:equal>
        						</td>
        
        						<td class=formDeLabel nowrap>
          							<logic:equal name="action" value="edit"><bean:message key="prompt.2.diamond"/></logic:equal>
          							<bean:message key="prompt.completion" />&nbsp;<bean:message key="prompt.status" />
								</td>
        						<td class=formDe colspan="2">
        						  	<logic:notEqual name="action" value="edit">
        						  		<bean:write name="rule" property="completionStatus" />
        						  	</logic:notEqual> 
      								<logic:notPresent name="isprofile">
      									<logic:equal name="action" value="display">
	          								<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RULES_U%>'>
	            								<logic:equal name="supervisionRulesForm" property="selectedRule.editable" value="true">
	            									<logic:equal name="supervisionRulesForm" property="allowUpdates" value="true"> 
	             										<a href="/<msp:webapp/>displayCasefileSupervisionRuleList.do?submitAction=Edit&action=edit">&nbsp;Edit>></a>
	             									</logic:equal>
	             								</logic:equal>
	           								</jims2:isAllowed>
	           							</logic:equal> 
          							</logic:notPresent>	
         							<logic:equal name="action" value="edit">
	           							<html:select name="supervisionRulesForm" property="selectedRule.completionStatusId" size="1" onchange="changeDateOnForm()">
	           								<html:optionsCollection name="supervisionRulesForm" property="completionStatusCodes" value="code" label="descriptionType" />
	           							</html:select>
	             					</logic:equal>
        						</td>
        					</tr>
        
        					<tr>
        						<td class=formDeLabel colspan=5><bean:message key="prompt.rule" />&nbsp;<bean:message key="prompt.literal" /></td>
        					</tr>
        					<tr>
        						<td class=formDe colspan="5"><bean:write name="rule" property="ruleLiteralResolved" filter="false"/></td>
        					</tr>
        					<tr>
        						<td class=formDeLabel nowrap><bean:message key="prompt.additional" />&nbsp;<bean:message key="prompt.info" /></td>
        						<td class=formDe colspan=4>
          						<logic:notEqual name="action" value="edit">
            						<bean:write name="rule" property="additionalInformation" />
          						</logic:notEqual>
          						<logic:equal name="action" value="edit">
            						<html:textarea name="supervisionRulesForm" property="selectedRule.additionalInformation" style="width: 100%" rows="3" onmouseout="textCounter(this,255)" onkeydown="textCounter(this,255)"></html:textarea>
          						</logic:equal>
        						</td>
        					</tr>
        			  </table>
        			</td> 
            </tr> 
          </table> 
    			<%-- end rules table --%> 
					
					<%-- start associated goals table --%>
    			<logic:notEmpty name="supervisionRulesForm" property="goalList">
    			<div class='spacer'></div>
  				<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  					<tr>
  						<td class=detailHead>Associated Goals</td>
  					</tr>
  					<tr>
  						<td>
  							<table cellpadding=2 cellspacing=1 width='100%'>
  								<tr bgcolor='#cccccc'>
  									<td class=subHead><bean:message key="prompt.goalNumber"/></td>
  									<td class=subHead><bean:message key="prompt.goal"/></td>
  								</tr>
  								
   								<logic:iterate id="goalsIndex" name="supervisionRulesForm" property="goalList" indexId="indexR">
 								<tr class="<%out.print((indexR.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
  									<td>						
  										<a href="javascript:goNav('/<msp:webapp/>displayGoalDetails.do?submitAction=Link&selectedValue=<bean:write name="goalsIndex" property="goalID"/>')"><bean:write name="goalsIndex" property="goalID"/></a>																		
  									</td>
  									<td><bean:write name="goalsIndex" property="goalDescription"/></td>										
  								</tr>
  							  </logic:iterate>
  							</table>
  						</td>
  					</tr>							
  				</table>
  			</logic:notEmpty>

			<logic:notEqual name="isprofile" value="viewWindow">
	  			<%-- BEGIN BUTTONS INSIDE DETAIL TABLE --%>
	    		<table border="0" width="100%">
	    			<tr>
	    				<td align="center">
	  
	    					<logic:notEqual name="action" value="confirmation">
	      					<input type="button" value="Back" name="return" onClick="history.go(-1);">
	    					</logic:notEqual>
	    					
	    					<logic:equal name="action" value="edit">
	    						&nbsp;<html:submit property="submitAction" onclick="return validateTheEdit(this.form)"><bean:message key="button.next"/></html:submit>
	    						&nbsp;<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
	    					</logic:equal>
	    
	    					<logic:equal name="action" value="summary">
	    						&nbsp;<input type="button" name="submitAction" value="<bean:message key='button.finish'/>" onclick="changeActionFormURL(this.form, '/<msp:webapp/>submitCasefileSupervisionRule.do?submitAction=<bean:message key='button.finish'/>&action=finish', true)">
	    						&nbsp;<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
	    					</logic:equal>
	    			  
	    					<logic:equal name="action" value="confirmation">
	    						<input type="button" name="submitAction" value="<bean:message key='button.returnToCasefileRules'/>" onclick="changeActionFormURL(this.form, '/<msp:webapp/>displayCasefileSupervisionRuleList.do?submitAction=Display All', true)">
	    					</logic:equal>
	    				</td>			
	    			</tr>
	    		</table>
	    		<%-- END BUTTONS INSIDE DETAIL TABLE --%>
			</logic:notEqual>
			
			<logic:equal name="isprofile" value="viewWindow">
				<table border='0' width='100%'>
					<tr>
						<td align=center>
							<input type=button name=close value=Close onclick="javascript:window.close()">
						</td>
					</tr>	
				</table>
			</logic:equal>	
        <div class=spacer></div>
  			<%-- END INNER RULES TABLE --%>
		</td>
	</tr>
</table>
<%-- END RULES TABLE --%>

</html:html>
