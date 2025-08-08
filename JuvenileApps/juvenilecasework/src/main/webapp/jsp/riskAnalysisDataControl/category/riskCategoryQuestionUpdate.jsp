<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- CShimek  01/18/2012	Create JSP --%>
<%-- CShimek  04/24/2012	#73272 added scripting for UIControl type = DATE --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.RiskAnalysisConstants"%>
<%@ page import="naming.UIConstants" %>


<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - riskCategoryQuestionUpdate.jsp</title>

<%-- STRUTS VALIDATION --%>
<%--html:javascript formName="riskQuestionsSearchForm" /--%>
<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/riskAnalysis.js"></script>
<script type="text/javascript">
function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen)
	{
		field.value = field.value.substring(0, maxlen);
  		alert("Your input has been truncated to " + maxlen +" characters!");
	}
}
function enableTextUpdate(el)
{
	document.getElementById("questionText").disabled = el.checked;
	document.getElementById("questTextMod").value = el.checked;
//	fld.disabled = el.checked;
}
function uiControlTypeChoice(el)
{
	document.getElementById("collapsibleHeader").selectedIndex = 0; 
	if (el.options[el.selectedIndex].value == "QUESTIONHEADER")
	{
		document.getElementById("collapseHeader").className = "visible";;
	} else {
		document.getElementById("collapseHeader").className = "hidden";
	}
	var d1 = document.getElementById( 'defaultSysDate');
	var d2 = document.getElementById( 'allowFutDate');
    if (el.options[el.selectedIndex].value == "DATE") 
	{ 
   		d1.disabled = false;
   		d2.disabled = false;
	} 
	else 
	{
		d1.selectedIndex = 0;
		d2.selectedIndex = 0;
   		d1.disabled = true;
   		d2.disabled = true;
       }
}
function checkControlType()
{
	var fld = document.getElementById("uiControlType"); 
	if (fld.options[fld.selectedIndex].value == "QUESTIONHEADER")
	{
		document.getElementById("collapseHeader").className = "visible";
	} else {
		document.getElementById("collapseHeader").className = "hidden";
	}
	var d1 = document.getElementById( 'defaultSysDate');
	var d2 = document.getElementById( 'allowFutDate');
    if (fld.options[fld.selectedIndex].value == "DATE") 
	{ 
   		d1.disabled = false;
   		d2.disabled = false;
	} 
	else 
	{
		d1.selectedIndex = 0;
		d2.selectedIndex = 0;
   		d1.disabled = true;
   		d2.disabled = true;
       }
}
</script>

</head>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkControlType()">

<%--BEGIN FORM TAG--%>
<html:form action="/displayUpdateCategoryQuestionSummary" target="content" focus="question.questionName"> 
<input type="hidden" name="helpFile" value="">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" ><bean:message key="title.riskTotalCategories"/> - <bean:message key="title.update"/>&nbsp;<bean:message key="prompt.question"/> </td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
<%-- END ERROR TABLE --%>
	<div class="spacer4px" ></div>
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Select Cancel button to return to Category Details page.</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="required"><bean:message key="prompt.3.diamond" /> <bean:message key="prompt.requiredFieldsInstruction"/></td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<!-- Place formName in request for tiles to use -->	
	<bean:define id="form" value="riskCategoryDetailsForm" toScope="page"/>
<%-- BEGIN CATEGORY BLOCK TABLE --%>	
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
        	<td class="detailHead">
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead">
							<a href="javascript:showHideMulti('Category', 'cat', 1, '/<msp:webapp/>')">
			   					<img border='0' src="/<msp:webapp/>images/expand.gif" name="Category"/></a>
							<bean:message key="prompt.category"/>
						</td>
 					</tr>
				</table>
			</td>
		</tr>
		<tr id="cat0" class='hidden'>
			<td>	
<%-- BEGIN CATEGORY DETAIL TABLE --%>
				<tiles:insert page="riskCategoryInfoTile.jsp" flush="true">
					<tiles:put name="formName" type="String" value="${form}" />
					<tiles:put name="categoryBoxTitle" type="String" value="Category Information" />
				</tiles:insert>
<%-- END CATEGORY TABLE --%>
			</td>
		</tr>
	</table> 
<%-- END CATEGORY BLOCK TABLE --%>	
	<div class="spacer4px" ></div>
<%-- BEGIN QUESTOIN BLOCK TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead">
<%-- BEGIN QUESTION BLOCK HEADER TABLE --%>			
				<table width="100%" cellpadding="2" cellspacing="0">
		        	<tr>
		            	<td class="detailHead"><bean:message key="prompt.question"/></td>
		         	</tr>
		        </table>
<%-- END CATEGORY BLOCK HEADER TABLE --%>	
	        </td>
	    </tr>
	    <tr>
			<td>
<%-- BEGIN QUESTION DETAILS TABLE --%>				
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
		        	<tr class="detailHead">
		            	<td colspan="4"><bean:message key="prompt.question"/>&nbsp;<bean:message key="prompt.information"/></td>
		          	</tr>
		          	<tr>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.3.diamond" /> <bean:message key="prompt.questionName"/></td>
						<td width="35%" class="formDe"><html:text styleId="questionName" name="riskQuestionUpdateForm" property="question.questionName" size="25" maxlength="25" /></td>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.entryDate" /></td>
						<td width="35%" class="formDe">
							<bean:write name="riskQuestionUpdateForm" property="question.questonEntryDate" formatKey="datetime.format.mmddyyyyHHmm" />
							<html:hidden name="riskQuestionUpdateForm" property="question.questonEntryDate"/>
						 </td>
					</tr>					
					<tr>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.3.diamond" /> <bean:message key="prompt.questionText"/><div><span class="bodyText">&nbsp;(Max Char=150)</span></div></td>
						<td class="formDe" colspan="3">
							<html:textarea styleId="questionText" name="riskQuestionUpdateForm" property="question.questionText" style="width:100%" rows="2" onmouseout="textLimit( this, 150 )" onkeyup="textLimit( this, 150 )"></html:textarea>
						</td>
					</tr>
					<tr>					
						<td width="15%" class="formDeLabel"><bean:message key="prompt.3.diamond" /> <bean:message key="prompt.UIControlType" /></td>
						<td width="35%" class="formDe">
							<html:select styleId="uiControlType" name="riskQuestionUpdateForm" property="question.uiControlType" styleId="uiControlType" onchange="uiControlTypeChoice(this);">
								<html:option value=""><bean:message key="select.generic" /></html:option>
						 		<jims2:codetable codeTableName="<%=RiskAnalysisConstants.JUV_RISK_UI_CONTROL_TYPE%>"/>   
							</html:select>
						</td>
						<td width="15%" class="formDeLabel">Default to System Date</td>
						<td width="35%" class="formDe">
							<html:select name="riskQuestionUpdateForm" property="question.defaultToSystemDate" styleId="defaultSysDate">
								<html:option value=""><bean:message key="select.generic" /></html:option>									                          								  		
								<html:option value="true"><%=UIConstants.YES_FULL_TEXT%></html:option>
								<html:option value="false"><%=UIConstants.NO_FULL_TEXT%></html:option>
							</html:select>
						</td>	 
						
					</tr>	
					<tr id="collapseHeader" class="hidden" >
						<td width="15%" class="formDeLabel"><bean:message key="prompt.3.diamond" /> <bean:message key="prompt.collapsibleHeader" /></td>
						<td width="35%" class="formDe" colspan="3">
							<html:select styleId="collapsibleHeader" name="riskQuestionUpdateForm" property="question.collapsibleHeader">
								<html:option value=""><bean:message key="select.generic" /></html:option>									                          								  		
								<html:option value="true"><%=UIConstants.YES_FULL_TEXT%></html:option>
								<html:option value="false"><%=UIConstants.NO_FULL_TEXT%></html:option>
							</html:select>
						</td>
					</tr>
		          	<logic:equal name="riskQuestionUpdateForm" property="question.uiControlType" value="QUESTIONHEADER">
						<script type="text/javascript">
							document.getElementById("collapseHeader").className = "visible";
						</script>
					</logic:equal>
					<tr>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.3.diamond" /><bean:message key="prompt.UIDisplayOrder"/></td>
						<td width="35%" class="formDe"><html:text styleId="uiDisplayOrder" name="riskQuestionUpdateForm" property="question.uiDisplayOrder" size="2" maxlength="2"/></td>             
						<td width="15%" class="formDeLabel"><bean:message key="prompt.allowsFutureDates"/></td>
						<td width="35%" class="formDe">
							<html:select name="riskQuestionUpdateForm" property="question.allowFutureDates" styleId="allowFutDate">
								<html:option value=""><bean:message key="select.generic" /></html:option>									                          								  		
								<html:option value="true"><%=UIConstants.YES_FULL_TEXT%></html:option>
								<html:option value="false"><%=UIConstants.NO_FULL_TEXT%></html:option>
							</html:select>
						</td>	 
					</tr>
					<tr>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.numericOnly"/></td>
						<td width="35%" class="formDe">
							<html:select name="riskQuestionUpdateForm" property="question.numericOnly" >
								<html:option value=""><bean:message key="select.generic" /></html:option>									                          								  		
								<html:option value="true"><%=UIConstants.YES_FULL_TEXT%></html:option>
								<html:option value="false"><%=UIConstants.NO_FULL_TEXT%></html:option>
							</html:select>
						</td>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.questionTextNotModifiable"/></td>
						<td width="35%" class="formDe">
							<input type="checkbox" name="questionTextNotMod" id="hardcoded" onclick="enableTextUpdate(this)"/>
							<html:hidden name="riskQuestionUpdateForm" property="question.hardcoded"  styleId="questTextMod" />
						</td>	
					</tr>
						<script type="text/javascript">
							var fld1 = document.getElementById("questTextMod");
							var fld2 = document.getElementById("hardcoded");
							if (fld1.value == 'true')
							{	
								fld2.checked = true;
								document.getElementById("questionText").disabled = true;
							}	
						</script>
					<tr>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.initialAction"/></td>
						<td width="35%" class="formDe">
							<html:select name="riskQuestionUpdateForm" property="question.questionInitialAction" >
								<html:option value=""><bean:message key="select.generic" /></html:option>									                          								  		
								<html:option value="HIDE">HIDE</html:option>
								<html:option value="DISABLE">DISABLE</html:option>
							</html:select>
						</td>   
						<td width="15%" class="formDeLabel"><bean:message key="prompt.3.diamond" /><bean:message key="prompt.required?"/> </td>
						<td width="35%" class="formDe">
							<html:select styleId="required" name="riskQuestionUpdateForm" property="question.required" >
								<html:option value=""><bean:message key="select.generic" /></html:option>									                          								  		
								<html:option value="true"><%=UIConstants.YES_FULL_TEXT%></html:option>
								<html:option value="false"><%=UIConstants.NO_FULL_TEXT%></html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.controlCode" /></td>
						<td width="35%" class="formDe">
							<html:select size="1" name="riskQuestionUpdateForm" property="question.controlCode">
							<html:option value=""><bean:message key="select.generic" /></html:option>
							<html:optionsCollection property="controlCodes" value="code" label="name" />  					
							</html:select>
						</td>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.3.diamond" />Allow Print</td>
						<td width="35%" class="formDe">
							<html:select styleId="reqPrint" name="riskQuestionUpdateForm" property="question.allowPrint" >
								<html:option value=""><bean:message key="select.generic" /></html:option>									                          								  		
								<html:option value="true"><%=UIConstants.YES_FULL_TEXT%></html:option>
								<html:option value="false"><%=UIConstants.NO_FULL_TEXT%></html:option>
							</html:select>
						</td>
					</tr>
				</table>
<%-- END QUESTION DETAILS TABLE --%>					
			</td>
		</tr>
	</table>
<%-- END QUESTION BLOCK TABLE --%>
    <div class="spacer4px" ></div>
<%-- BEGIN BUTTONS TABLE --%>	
	<table width="100%">
		<tr id="submitButtons">
			<td align="center">
				<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
				<html:submit property="submitAction" onclick="return validateQuestionFields(this.form) && disableSubmit(this, this.form)"><bean:message key="button.next" /></html:submit>
			<%--	<html:submit property="submitAction" onclick="return validateQuestionFields(this.form)"><bean:message key="button.next" /></html:submit> --%>
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
	</table> 
<%-- END BUTTONS TABLE --%>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>