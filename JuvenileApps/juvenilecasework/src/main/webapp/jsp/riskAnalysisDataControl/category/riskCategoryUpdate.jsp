<!DOCTYPE HTML>

<%-- Used for creating Risk Categories in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek	12/23/2011	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>



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
<title><bean:message key="title.heading" /> - riskCategoryUpdate.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen)
	{
		field.value = field.value.substring(0, maxlen);
  		alert("Your input has been truncated to " + maxlen +" characters!");
	}
}
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
function checkSelect()
{
	var cbs = document.getElementsByName("selectedValue");
	if (cbs != null){
		for (x=0; x<cbs.length; x++) {
			if (cbs[x].checked == true) {
				return true;
			}
		}
	}	
	alert("No Question selected to Add.");
	return false;
}
function selectAllQuestions(el)
{
	var cbs = document.getElementsByName("selectedValue");	
	if (cbs != null){
		for (x=0; x<cbs.length; x++) {
			cbs[x].checked = el.checked;
		}
	}	
}
function resetMaster(el)
{
	if (el.checked == false)
	{
		document.getElementById("masterSelect").checked = false;
	}	
}
</script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/displayRiskCategoryUpdateSummary" target="content" focus="category.categoryName">

<span align="center">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" ><bean:message key="title.riskTotalCategories"/> - <bean:message key="prompt.update"/>&nbsp;<bean:message key="prompt.category"/></td>
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
	<div class="spacer4px"></div>	
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Select Back button to return to previous page.</li>
					<li>Select Next button to continue to Summary page.</li>       
				</ul>
			</td>
		</tr>
		<tr>
			<td class="required"><bean:message key="prompt.3.diamond" /> <bean:message key="prompt.requiredFieldsInstruction"/></td>
		</tr>  
	</table>
<%-- END INSTRUCTION TABLE --%>
<%-- Place formName in page for tiles to use --%>	
	<bean:define id="form" value="riskCategoryCreateForm" toScope="page"/>

	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead">
				<table width="100%" cellpadding="2" cellspacing="0">
					<tr>
						<td class="detailHead"><bean:message key="prompt.category"/></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>  	
			<td>
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<tr class="detailHead">
						<td colspan="4"><bean:message key="prompt.category"/>&nbsp;<bean:message key="prompt.information"/></td>
					</tr>
					<tr>
						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.categoryName"/></td>
						<td class="formDe"><html:text name="riskCategoryUpdateForm" property="category.categoryName" size="50" maxlength="50"/></td>
						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.entryDate"/></td>
						<td class="formDe" width="35%"><bean:write name="riskCategoryUpdateForm" property="category.entryDate" formatKey="date.format.mmddyyyy" /></td>
					</tr>
					<tr>
						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.riskResultGroup"/></td>
						<td class="formDe">
							<html:select name="riskCategoryUpdateForm" property="category.riskResultGroupId" >
								<html:option value=""><bean:message key="select.generic"/></html:option>
								<html:optionsCollection property="riskResultGroups" value="code" label="description" />
							</html:select>
						</td>
						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.version"/></td>
						<td class="formDe"><bean:write name="riskCategoryUpdateForm" property="category.version" /></td>
					</tr>                                       
					<tr>
						<td class='formDeLabel' colspan='5'>
							<bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.description"/>&nbsp;
 							<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
								<tiles:put name="tTextField" value="category.categoryDescription"/>
								<tiles:put name="tSpellCount" value="spellBtn1" />
							</tiles:insert>  
							(Max Characters allowed: 250) 
						</td>
					</tr>
					<tr>
						<td class='formDe' colspan='5'>
							<html:textarea name="riskCategoryUpdateForm" property="category.categoryDescription" 
								onmouseout="textLimit(this, 250);"
								onkeyup="textLimit(this, 250);"
								rows='3' style="width:100%" >
							</html:textarea>
						</td>
					</tr>
				</table>
				<div class="spacer4px"></div>
<%-- BEGIN QUESTIONS TABLE --%>
				<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableGrey">
					<tr class="detailHead">
						<td colspan="6"><bean:message key="prompt.questions"/></td>
					</tr>
					<tr>
						<td colspan="6">
<%-- BEGIN SELECTABLE LIST TABLE --%>							
							<table width="100%" cellpadding="2" cellspacing="1" border="0">
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="0" class="borderTable">
											<tr class="detailHead">
												<td colspan="6"><bean:message key="prompt.selectableList"/></td>
											</tr>
										</table>
										<bean:size id="sel1" name="riskCategoryUpdateForm" property="newQuestionList"/>
										<table width="100%" cellpadding="0" cellspacing="0" class="borderTable">	
											<tr>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr class="listHeader">
															<td class="formDeLabel" width="7%" align="center">
																<input type="checkbox" name="selectAll" id="masterSelect" onclick="selectAllQuestions(this)">
															</td>
															<td class="formDeLabel" width="27%"><bean:message key="prompt.questionName"/></td>
															<td class="formDeLabel" width="29%"><bean:message key="prompt.questionText"/></td>
															<td class="formDeLabel" width="10%"><bean:message key="prompt.sortOrder"/></td>
															<td class="formDeLabel" width="15%"><bean:message key="prompt.UIControlType"/></td>
															<td class="formDeLabel" width="12%"><bean:message key="prompt.initialAction"/></td>
														</tr>
													</table>
												</td>
												<logic:greaterEqual name="sel1" value="4">
													<td width="2%" class="formDeLabel">&nbsp;</td>
												</logic:greaterEqual>
											</tr>
										</table>
										<div class="scrollingDiv100">
											<table width="100%" cellpadding="2" cellspacing="1">	
												<logic:iterate id="qid" name="riskCategoryUpdateForm" property="newQuestionList" indexId="index1">
													<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
														<td width="7%" align="center">
															<input type="checkbox" name="selectedValue" value="<bean:write name="qid" property="questionId"/>" onclick="resetMaster(this)">
														</td>
														<td width="27%">
															<a href="javascript:newCustomWindow('/<msp:webapp/>displayRiskCategoryCreateSummary.do?submitAction=Link&questionID=<bean:write name="qid" property="questionId"/>', 'winName',830,500);"><bean:write name="qid" property="questionName"/></a>
														</td>
														<td width="29%"><bean:write name="qid" property="questionText"/></td>
														<td width="10%"><bean:write name="qid" property="uiDisplayOrder"/></td> 
														<td width="15%"><bean:write name="qid" property="uiControlType"/></td>  
														<td width="12%"><bean:write name="qid" property="initialAction"/></td>
													</tr>
												</logic:iterate>
											</table>
										</div>
									</td>
								</tr>		
							</table>
<%-- END SELECTABLE LIST TABLE --%>	
<%-- BEGIN SELECTABLE LIST BUTTON TABLE --%>							
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return checkSelect() && disableSubmit(this, this.form)"><bean:message key="button.addSelected"/></html:submit>
									</td>
								</tr>
							</table>
<%-- END SELECTABLE LIST BUTTON TABLE --%>								
						</td>	
					</tr>
					<tr>
						<td colspan="6">
<%-- BEGIN SELECTED LIST TABLE --%>						
							<table width="100%" cellpadding="2" cellspacing="1" border="0">
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="0" class="borderTable">
											<tr class="detailHead">
												<td colspan="6"><bean:message key="prompt.selectedList"/></td>
											</tr>
										</table>
										<bean:size id="sel2" name="riskCategoryUpdateForm" property="questionList"/>			
										<table width="100%" cellpadding="0" cellspacing="0" class="borderTable">	
											<tr>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr class="listHeader">
															<td class="formDeLabel" width="7%">&nbsp;</td>
															<td class="formDeLabel" width="27%"><bean:message key="prompt.questionName"/></td>
															<td class="formDeLabel" width="29%"><bean:message key="prompt.questionText"/></td>
															<td class="formDeLabel" width="10%"><bean:message key="prompt.sortOrder"/></td>
															<td class="formDeLabel" width="15%"><bean:message key="prompt.UIControlType"/></td>
															<td class="formDeLabel" width="15%"><bean:message key="prompt.initialAction"/></td>
														</tr>
													</table>
												</td>
												<logic:greaterEqual name="sel2" value="5">
													<td width="2%"class="formDeLabel">&nbsp;</td>
												</logic:greaterEqual>
											</tr>
										</table>
										<div class="scrollingDiv50">
											<table width="100%" cellpadding="2" cellspacing="1">
												<logic:iterate id="qid2" name="riskCategoryUpdateForm" property="questionList" indexId="index2">
													<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
												 		<td width="7%" >
															<a href="/<msp:webapp/>displayRiskCategoryUpdateSummary.do?submitAction=Remove&questionID=<bean:write name='qid2' property='questionId'/>"><bean:message key="prompt.remove" /></a>&nbsp;
														</td>  
														<td width="27%">
															<a href="javascript:newCustomWindow('/<msp:webapp/>displayRiskCategoryCreateSummary.do?submitAction=Link&questionID=<bean:write name="qid2" property="questionId"/>', 'winName',830,500);"><bean:write name="qid2" property="questionName"/></a>
														</td>
														<td width="29%"><bean:write name="qid2" property="questionText"/></td>
														<td width="10%"><bean:write name="qid2" property="uiDisplayOrder"/></td> 
														<td width="15%"><bean:write name="qid2" property="uiControlType"/></td>  
														<td width="15%"><bean:write name="qid2" property="initialAction"/></td>
													</tr>
												</logic:iterate>
											</table>	
										</div>
									</td>
								</tr>		
							</table>
<%-- END SELECTED LIST TABLE --%>								
						</td>	
					</tr>
				</table>	
<%-- END QUESTIONS TABLE --%>
			</td>
		</tr>      
	</table>
</span>
<div class="spacer4px"></div>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back"/></html:submit>
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.next" /></html:submit>
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
		</td>
	</tr>
</table>
<div class="spacer4px"></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>