<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- CShimek  01/20/2012	Create JSP --%>
<%-- CShimek  04/23/2012	#73272 added onload scripting for UIControlType, needed for weight display field --%>
<%-- CShimek  06/25/2012	#73530 correct js error on Next button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../../WEB-INF/msp.tld" prefix="msp"%>



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
<title><bean:message key="title.heading" /> - riskCategoryAddAnswers.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript">
function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen)
	{
		field.value = field.value.substring(0, maxlen);
  		alert("Your input has been truncated to " + maxlen +" characters!");
	}
}
function validateInput() 
{
	var msg = "";
	var numericRegExp = /^[0-9]*$/;
	var textRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \.\\(),\n\r\x27\x3B\x26\x2f\-]*$/;
	var fld = document.getElementsByName("currentAnswer.answerText");
	var fldValue = Trim(fld[0].value);
	fld[0].value = fldValue;
	if (fldValue == "")
	{
		msg = "Answer Text is required.\n";
		fld[0].focus()
	} else {
		if (textRegExp.test(fldValue,textRegExp) == false) 
		{
			msg += "Answer Text contains 1 or more invalid characters.\n";
			fld[0].focus()
		}
	}	
	fld = document.getElementsByName("currentAnswer.weight");
	fldValue = Trim(fld[0].value);
	fld[0].value = fldValue;
	if (fldValue == "")
	{
		if (msg == "")
		{
			fld[0].focus()
		}	
		msg += "Weight is required.\n";
	} else {
		if (numericRegExp.test(fldValue,numericRegExp) == false) 
		{
			if (msg == "")
			{
				fld[0].focus()
			}
			msg += "Weight must be numeric value.\n";
		}	
	}
	fld = document.getElementsByName("currentAnswer.sortOrder");
	fldValue = Trim(fld[0].value);
	fld[0].value = fldValue;
	if (fldValue == "")
	{
		if (msg == "")
		{
			fld[0].focus()
		}	
		msg += "Sort Order is required.\n";
	} else {
		if (numericRegExp.test(fldValue,numericRegExp) == false) 
		{
			if (msg == "")
			{
				fld[0].focus()
			}
			msg += "Sort Order must be numeric value.\n";
		}	
	}
	fld = document.getElementById("subordinateQuestionName");
	fld.value = "";
	if (msg != "")
	{
  		alert(msg);
  		return false;
	}
	var fld2 = document.getElementById("subordinateQuestionId");
	fld.value = fld2.options[fld2.selectedIndex].text;
	return true;
}

function validateEntry() 
{
	var na = document.getElementById("newAnswersId");
	if (na == null)
	{
  		alert("No new answer found to Add.");
  		return false;
	}
	return true;
}
function checkUIControlType()
{
	var ct = document.getElementById("controlType");
	var wt = document.getElementById("weight");
	
	if (ct.value == "QUESTIONHEADER")
	{
		wt.value = 0;
		wt.disabled = true;
	} else {
		wt.value = "";
		wt.disabled = false;
	}	
}
</script>
</head>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkUIControlType()">

<%--BEGIN FORM TAG--%>
<html:form action="/displayRiskCategoryAddAnswersSummary" target="content" focus="currentAnswer.answerText"> 
<input type="hidden" name="helpFile" value="">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskTotalCategories"/> - <bean:message key="prompt.add"/> <bean:message key="prompt.answer"/>s
			</td>
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
					<li>Select Next button to view Summary page.</li>
					<li>Select Cancel button to return to Category Details page.</li>
				</ul>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<!-- Place formName in request for tiles to use -->	
	<bean:define id="form" value="riskCategoryDetailsForm" toScope="page"/>
	<bean:define id="cqform" value="handleRiskQuestionDetailsSelectionForm" toScope="page"/>	
	<bean:define id="qaform" value="handleRiskQuestionDetailsSelectionForm" toScope="page"/>
<%-- BEGIN CATEGORY BLOCK TABLE --%>	
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
        	<td class="detailHead">
<%-- BEGIN CATEGORY COLAPSE TABLE --%>        	
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead">
							<a href="javascript:showHideMulti('Category', 'cat', 1, '/<msp:webapp/>')">
			   					<img border='0' src="/<msp:webapp/>images/expand.gif" name="Category"/></a>
							<bean:message key="prompt.category"/>
						</td>
 					</tr>
				</table>
<%-- END CATEGORY COLAPSE TABLE --%>   				
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
<%-- BEGIN QUESTION BLOCK TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead">
<%-- BEGIN QUESTION COLAPSE TABLE --%>        	
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead">
							<a href="javascript:showHideMulti('Question', 'quest', 1, '/<msp:webapp/>')">
			   					<img border='0' src="/<msp:webapp/>images/expand.gif" name="Question"/></a>
							<bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.questionName" />
						</td>
 					</tr>
				</table>
<%-- END QUESTION COLAPSE TABLE --%> 			
			</td>
		</tr>
	    <tr id="quest0" class='hidden'>
			<td>
<%-- BEGIN QUESTION DETAILS TABLE --%>	
				<tiles:insert page="riskCategoryQuestionInfoTile.jsp" flush="true">
					<tiles:put name="formName" type="String" value="${cqform}" />
				</tiles:insert>		
<%-- END QUESTION DETAILS TABLE --%>					
			</td>
		</tr>
	</table>
<%-- END QUESTION BLOCK TABLE --%>
    <div class="spacer4px" ></div>
<%-- BEGIN CURRENT ANSWERS BLOCK TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead">
<%-- BEGIN CURRENT ANSWERS COLAPSE TABLE --%>        	
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead">
							<a href="javascript:showHideMulti('currentQuestions', 'curQuest', 1, '/<msp:webapp/>')">
			   					<img border='0' src="/<msp:webapp/>images/expand.gif" name="currentQuestions"/></a>
							<bean:message key="prompt.answer"/>s for <bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.questionName" />
						</td>
 					</tr>
				</table>
<%-- END CURRENT ANSWERS COLAPSE TABLE --%> 			
			</td>
		</tr>
	    <tr id="curQuest0" class='hidden'>
			<td>
<%-- BEGIN CURRENT ANSWERS LIST TABLE --%>			
				<tiles:insert page="riskCategoryQuestionAnswersTile.jsp" flush="true">
					<tiles:put name="formName" type="String" value="${qaform}" />
				</tiles:insert>
<%-- END CURRENT ANSWERS LIST TABLE --%>					
			</td>
		</tr>
	</table>				
<%-- END CURRENT ANSWERS BLOCK TABLE --%>
	<div class="spacer4px" ></div>
<%-- BEGIN ADD ANSWERS BLOCK TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead">
				<bean:message key="prompt.add"/> <bean:message key="prompt.answer"/> for <bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.questionName" /> 
			</td>
		</tr>
		<tr>
			<td>	
<%-- BEGIN ADD ANSWERS INPUT TABLE --%>
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
		         	<tr>
						<td  class="formDeLabel"><bean:message key="prompt.entryDate"/></td>
						<td colspan="3" class="formDe">
							<bean:write name="riskAnswerCreateForm" property="currentAnswer.answerEntryDate" formatKey="datetime.format.mmddyyyyHHmm" />
							<html:hidden name="riskAnswerCreateForm" property="currentAnswer.answerEntryDate"/>
						 </td>
					</tr>					
					<tr>
						<td class="formDeLabel" valign="top"><bean:message key="prompt.3.diamond" /> <bean:message key="prompt.answerText"/><div><span class="bodyText">(Max. Char=250)</span></div></td>
						<td class="formDe" colspan="3">
							<html:textarea name="riskAnswerCreateForm" property="currentAnswer.answerText" 
								onmouseout="textLimit(this, 250);"
								onkeyup="textLimit(this, 250);"
								rows='4' style="width:100%" >
							</html:textarea>
						</td>
					</tr>
		 	 		<tr>					
						<td class="formDeLabel" width="15%"><bean:message key="prompt.3.diamond" /> <bean:message key="prompt.weight"/></td>
						<td class="formDe" width="25%"><html:text name="riskAnswerCreateForm" property="currentAnswer.weight" size="2" maxlength="2" styleId="weight"/></td>
						<td class="formDeLabel" width="20%"><bean:message key="prompt.subordinateQuestion" />?</td>
						<td class="formDe" width="40%">
							<html:select name="riskAnswerCreateForm" property="currentAnswer.subordinateQuestionId" size="1" styleId="subordinateQuestionId">
								<html:option value=""><bean:message key="select.generic" /></html:option>
								<html:optionsCollection name="riskAnswerCreateForm" property="questions" value="questionId" label="questionName" />
							</html:select>
							<html:hidden name="riskAnswerCreateForm" property="currentAnswer.subordinateQuestionName" styleId="subordinateQuestionName"/>
						</td>   
					</tr>	 
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.3.diamond" /> <bean:message key="prompt.sortOrder"/></td>
						<td class="formDe"><html:text name="riskAnswerCreateForm" property="currentAnswer.sortOrder" size="2" maxlength="2"/></td>
						<td class="formDeLabel"><bean:message key="prompt.action"/></td>
						<td class="formDe">
							<html:select name="riskAnswerCreateForm" property="currentAnswer.action" size="1">
								<html:option value=""><bean:message key="select.generic" /></html:option>
								<html:option value="SHOW">SHOW</html:option>
				                <html:option value="HIDE">HIDE</html:option>
				                <html:option value="ENABLE">ENABLE</html:option>
				                <html:option value="DISABLE">DISABLE</html:option>
							</html:select>
						</td>	
					</tr>  
				</table>
<%-- END ADD ANSWERS INPUT TABLE --%>
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:submit property="submitAction" onclick="return validateInput() && disableSubmit(this, this.form)"><bean:message key="button.addAnswer" /></html:submit>
			</td>
		</tr>
		<div class="spacer4px" ></div>
		<tr>
			<td>
<%-- BEGIN NEW ANSWERS BLOCK TABLE --%>
				<logic:notEmpty name="riskAnswerCreateForm" property="newAnswerList">
					<input type="hidden" name="newAnswers" id="newAnswersId" value="">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGrey">
						<tr>
							<td>
								<table width="100%" bgcolor="#999999" cellspacing="1">
									<tr class="formDelabel">
										<td width="1%">&nbsp;</td>
										<td width="40%"><bean:message key="prompt.answerText"/></td>
										<td width="1%"><bean:message key="prompt.weight"/>&nbsp;</td>
										<td><bean:message key="prompt.subordinateQuestion"/></td>
										<td width="10%"><bean:message key="prompt.action"/>&nbsp;</td>
										<td width="1%" nowrap="nowrap"><bean:message key="prompt.sortOrder"/>&nbsp;</td>
										<td width="1%" nowrap="nowrap"><bean:message key="prompt.entryDate"/></td>
									</tr>
									<logic:iterate id="naid" name="riskAnswerCreateForm" property="newAnswerList" indexId="index">
										<tr class="formDe" height="100%">
											<td><input type="checkBox" name="selectedValues" value="<%= index.intValue()%>" ></td>
											<td><bean:write name="naid" property="answerText"/></td> 
											<td><bean:write name="naid" property="weight"/></td>
											<td><bean:write name="naid" property="subordinateQuestionName"/></td>
											<td><bean:write name="naid" property="action"/></td>
											<td><bean:write name="naid" property="sortOrder"/></td>
											<td><bean:write name="naid" property="answerEntryDate" formatKey="datetime.format.mmddyyyyHHmm"/></td> 
										</tr>
									</logic:iterate>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="7">
								<table width="100%" cellpadding="2">
									<tr>
										<td align="center">
											<html:submit property="submitAction"><bean:message key="button.removeSelected" /></html:submit>
										</td>
									</tr>
								</table>			
							</td>
						</tr>
					</table>
				</logic:notEmpty>	
<%-- END NEW ANSWERS BLOCK TABLE --%>
				<div class="spacer" ></div>				
			</td>
		</tr>		
	</table>	
<%-- END ADD ANSWERS BLOCK TABLE --%>	
	<div class="spacer4px" ></div>			
<%-- BEGIN BUTTONS TABLE --%>	    
	<table width="100%">
		<tr>
			<td align="center">
				<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
				<html:submit property="submitAction" onclick="return validateEntry() && disableSubmit(this, this.form)"><bean:message key="button.next" /></html:submit>
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
	</table> 
<%-- END BUTONS TABLE --%>	
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>