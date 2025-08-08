<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- CShimek  01/19/2012	Create JSP --%>

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
<title><bean:message key="title.heading" /> - riskCategoryQuestionDetails.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript">
function newCustomWindow( tURL, windowName, width, height ) 
{
	var widthString = "width=" + width;
	var heightString = "height=" + height;
	var params = "resizable=yes, scrollbars=yes, " + widthString + "," + heightString;

	msgWindow = open( tURL, windowName, params );
	if( msgWindow.opener == null ) 
	{
		msgWindow.opener = self;
	}
}

function checkAnswerSelect(button)
{
	var sels = document.getElementsByName("selectedRiskAnswerIds");
	var chkCtr = 0;
	var totSel = 0;
	if (sels != null)
	{	
		totSels = sels.length;
		for (x=0; x<sels.length; x++)
		{
			if (sels[x].checked == true)
			{
				chkCtr = chkCtr + 1;
			}	
		}
	}
	if ( chkCtr == 0 )
	{
		alert("Answer selection required for " + button + ".");
		return false;
	}
	if ( button == "Update" & chkCtr > 1 )
	{
		alert("You can only select 1 Answer to Update.");
		return false;
	}	
	if ( button == "Delete" & chkCtr == totSels )
	{
		alert("You can not Delete all answers.");
		return false;
	}
	return true;
}

function setUpdateType(el)
{
	document.getElementById("updateInd").value = el;
}

function exitPopup()
{
	var pt = document.getElementById("popUpInd");
	if (pt != null)
	{
		pt.value == "";
	}	
	window.close();
}

function checkUIControlType()
{
	var fld = document.getElementById("uicontrolType");
	var selflds = document.getElementsByName("selectedRiskAnswerId");
	if (selflds != null && selflds.length == 1 && fld.value == "QUESTIONHEADER")
	{	
		fld = document.getElementById("addButton");
		fld.disabled = true;
	}	
}
</script>
</head>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" onLoad="checkUIControlType()">

<%--BEGIN FORM TAG--%>
<html:form action="/handleRiskCategoryQuestionDetails" target="content"> 
<input type="hidden" name="helpFile" value="">
<input type="hidden" name="updateType" value="" id="updateInd">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskTotalCategories"/> - <bean:message key="prompt.question"/> <bean:message key="prompt.details"/>
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
		<tr >
			<td>
				<ul>
					<li>Select Back button to return to Category Details page.</li>
				</ul>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<!-- Place formName in request for tiles to use -->	
	<bean:define id="form" value="riskCategoryDetailsForm" toScope="page"/>
	<bean:define id="cqform" value="handleRiskQuestionDetailsSelectionForm" toScope="page"/>
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
				<table width="100%" cellpadding="2" cellspacing="0">
		        	<tr>
		            	<td class="detailHead"><bean:message key="prompt.question"/></td>
		         	</tr>
		        </table>
	        </td>
	    </tr>
	    <tr>
			<td>
<%-- BEGIN QUESTION DETAILS TABLE --%>	
				<tiles:insert page="riskCategoryQuestionInfoTile.jsp" flush="true">
					<tiles:put name="formName" type="String" value="${cqform}" />
				</tiles:insert>			
<%-- END QUESTION DETAILS TABLE --%>					
			</td>
		</tr>
		<tr>
			<td align="center">
				<logic:equal name="handleRiskQuestionDetailsSelectionForm" property="category.updatable" value="true">
					<html:submit property="submitAction" onclick="return setUpdateType('Q') && disableSubmit(this, this.form)"><bean:message key="button.update" /></html:submit>
					<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.remove"/></html:submit>
				</logic:equal>
			</td>
		</tr>
	</table>
<%-- END QUESTION BLOCK TABLE --%>
    <div class="spacer4px" ></div>
<%-- BEGIN ANSWER BLOCK TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr class="detailHead">
  			<td><bean:message key="prompt.answer"/>(s)</td>
		</tr>
		<tr>
			<td>
<%-- BEGIN ANSWER DETAILS TABLE --%>
				<logic:empty  name="handleRiskQuestionDetailsSelectionForm" property="answerList">
					<table width="100%" cellpadding="1" cellspacing="2" class="borderTableGrey">
						<tr class="formDe">
							<td>No Answers found for this Question.</td>
						</tr>
					</table>
				</logic:empty>			
				<logic:iterate id="aId" name="handleRiskQuestionDetailsSelectionForm" property="answerList" indexId="indexA">
					<table width="100%" cellpadding="1" cellspacing="1" class="borderTableGrey">
						<tr class="alternateRow">
							<td width="1%">
								<logic:equal name="riskCategoryDetailsForm" property="category.updatable" value="true">
									<input type="checkbox" name="selectedRiskAnswerIds" value="<bean:write name="aId" property="riskAnswerId"/>" >
								</logic:equal>
							</td>
							<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.entryDateTime" /></td>
							<td colspan="3"><bean:write name="aId" property="answerEntryDate" formatKey="datetime.format.mmddyyyyHHmm"/></td>
						</tr>
						<tr class="alternateRow">	
							<td></td>
							<td class="formDeLabel"><bean:message key="prompt.answerText" /></td>
							<td colspan="3"><bean:write name="aId" property="response"/></td>
						</tr>
						<tr class="alternateRow">
							<td></td>
							<td class="formDeLabel"><bean:message key="prompt.weight" /></td>
							<td width="35%"><bean:write name="aId" property="weight"/></td>
							<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.subordinateQuestion"/></td>
							<td width="35%">
								<logic:notEqual name="aId" property="subordinateQuestionName" value="">
									<a href="javascript:newCustomWindow('/<msp:webapp/>displayRiskCategoryCreateSummary.do?submitAction=Link&questionID=<bean:write name="aId" property="subordinateQuestionId"/>', 'winName',830,500);">
										 <bean:write name="aId" property="subordinateQuestionName"/>
									</a>
								</logic:notEqual>
							</td>
						</tr>	
						<tr class="alternateRow">	
							<td></td>
							<td class="formDeLabel"><bean:message key="prompt.sortOrder"/></td>
							<td width="35%"><bean:write name="aId" property="sortOrder"/></td>
							<td class="formDeLabel"><bean:message key="prompt.action"/></td>
							<td width="35%"><bean:write name="aId" property="action"/></td>
						</tr>
					</table>
					<div class="spacer"></div>
				</logic:iterate>	
<%-- END ANSWER DETAILS TABLE --%>					
			</td>
		</tr>
		<tr>
			<td align="center">
				<logic:equal name="handleRiskQuestionDetailsSelectionForm" property="category.updatable" value="true">
					<html:submit property="submitAction" onclick="disableSubmit(this, this.form)" styleId="addButton"><bean:message key="button.add" /></html:submit>
					<html:submit property="submitAction" onclick="return checkAnswerSelect('Update') && disableSubmit(this, this.form)"><bean:message key="button.update" /></html:submit>
					<html:submit property="submitAction" onclick="return checkAnswerSelect('Delete') && disableSubmit(this, this.form)"><bean:message key="button.delete"/></html:submit>
				</logic:equal>
			</td>
		</tr>
	</table>				
<%-- END ANSWER BLOCK TABLE --%>
	<div class="spacer4px" ></div>
<%-- BEGIN BUTTONS TABLE --%>	    
	<table width="100%">
		<tr>
			<td align="center">
				<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.back" /></html:submit>
				<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
	</table>
<%-- END BUTONS TABLE --%>	
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>