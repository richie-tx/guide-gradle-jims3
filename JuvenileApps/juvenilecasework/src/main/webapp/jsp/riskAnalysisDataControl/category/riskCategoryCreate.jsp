<!DOCTYPE HTML>

<%-- Used for creating Risk Categories in MJCW --%>
<%--MODIFICATIONS --%>
<%-- Dwilliamson  11/30/2011	Create JSP --%>
<%-- 04/20/2012		CShimek		#73240 Add js validation for category information --%>
<%-- 05/17/2012		CShimke		Revised Sort Order to UI Display Order in question listing blocks, per email request --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>  <%-- FOR SPELLCHECK --%>
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
<title><bean:message key="title.heading" /> - riskAnalysisDataControl/category/riskCategoryCreate.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript">
// Category handling functions
function validateCategoryInfo()
{
	var msg = "";
	var textRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \.\\(),\n\r\x27\x3B\x26\x2f\-]*$/;
	var fld = document.getElementById("catName");
	var val = Trim(fld.value);
	if (fld.value == "") {
		msg = "Category Name is required.\n";
		fld.focus();
	} 
	if (fld.value != "" && fld.value.length < 9) {
		msg = "Category Name must be at least 9 characters.\n";
		fld.focus();
	}
	if (fld.value.length > 9){
		if (textRegExp.test(fld.value,textRegExp) == false) {
			msg = "Category Name contains one or more invalid characters.\n";
			fld.focus();
		}
	}
	fld = document.getElementById("gourpId");
	if (fld.selectedIndex == 0){
		if (msg == "") {
			fld.focus();
		}
		msg += "Risk Result Group selection is required\n";
	}
	var fld = document.getElementById("description");
	var val = Trim(fld.value);
	if (fld.value == "") {
		if (msg == "") {
			fld.focus();
		}
		msg += "Description is required.\n";
	} else {
		if (textRegExp.test(fld.value,textRegExp) == false) {
			if (msg == "") {
				fld.focus();
			}
			msg += "Description contains one or more invalid characters.\n";
		}
	}
	fld = document.getElementById("selList");
	if (fld == null){
		msg += "At least 1 question must be selected and added.\n";
	}
	if (msg == "") {
		return true;
	}
	alert(msg);
	return false;
}

function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
	{
  	alert("Your input has been truncated to "  +maxlen +" characters!");
	}

	if (field.value.length > maxlen)
	{
  	field.value = field.value.substring(0, maxlen);
	}
}

// Question handling funcitons
function selectAllQuestions(el)
{
	var flds = document.getElementsByName("selectedValue");
	if(flds != null){
		for (x = 0; x < flds.length; x++)
		{	
			flds[x].checked = el.checked;
		}
	}	
}

function checkSelect(el)
{
	if (el.checked == false)
	{	
		document.getElementById("masterSelect").checked = false;
	}	
}

function checkAddSelected(){
	var fld = document.getElementsByName("selectedValue");
	if (fld != null){
    	for (i = 0; i <fld.length; i++){
			if(fld[i].checked == true){
				return true;
			}
		}     
   }
	alert("No Question(s) selected to add to Selected List.");
	return false;
}

</script>
</head>
<body topmargin="0" leftmargin="0" >
<html:form action="/displayRiskCategoryCreateSummary" target="content" focus="category.categoryName">

<span align="center">
<!-- BEGIN HEADING TABLE -->
	<table width="100%">
		<tr>
			<td align="center" class="header" ><bean:message key="title.riskTotalCategories"/> - <bean:message key="prompt.create"/>&nbsp;<bean:message key="prompt.category"/></td>
		</tr>
	</table>
<!-- END HEADING TABLE -->
<%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
<%-- END ERROR TABLE --%>
	<div class="spacer4px"></div>
<!-- BEGIN INSTRUCTION TABLE -->
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
<!-- END INSTRUCTION TABLE -->

<!-- Place formName in page for tiles to use -->	
<%-- 	<bean:define id="form" value="riskCategoryCreateForm" toScope="page"/>  --%>
<!-- BEGIN BLUE BORDER TABLE -->
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
		            	<td align="left" colspan="2"><bean:message key="prompt.category"/>&nbsp;<bean:message key="prompt.information"/></td>
					</tr>
					<tr>
						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.categoryName"/></td>
						<td class="formDe"><html:text name="riskCategoryCreateForm" property="category.categoryName" size="50" maxlength="40" styleId="catName"/></td>
					</tr>
					<tr>
		    			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.riskResultGroup"/></td>
						<td class="formDe">
							<html:select name="riskCategoryCreateForm" property="category.riskResultGroupId" styleId="gourpId" >
								<html:option value=""><bean:message key="select.generic"/></html:option>
								<html:optionsCollection property="riskResultGroups" value="code" label="description" />
							</html:select>
		    			</td>
					</tr>                                       
					<tr>
		    			<td class='formDeLabel' colspan='2'><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.description"/>
		    				<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
								<tiles:put name="tTextField" value="category.categoryDescription"/>
								<tiles:put name="tSpellCount" value="spellBtn1" />
							</tiles:insert>  
		    			   (Max Characters allowed: 250)
		    			<%--    <input type="text" name="testing" size="10"> --%>
		    		    </td>
		    		    
					</tr>
					<tr>
						<td class='formDe' colspan='2'>
		    				<html:textarea name="riskCategoryCreateForm" property="category.categoryDescription" styleId="description"
								onmouseout="textLimit(this, 250);" 	onkeyup="textLimit(this, 250);" rows='3' style="width:100%" >
							</html:textarea>
						</td>
					</tr>
				</table>
     			<div class="spacer4px"></div>
<!-- BEGIN QUESTIONS TABLE -->
				<table width="100%" cellpadding="2" cellspacing="0"  class="borderTableGrey">
					<tr>
						<td colsapn="6" class="detailHead"><bean:message key="prompt.questions"/> </td>
					</tr>
					<tr>
						<td colspan="6">
							<table width="100%" cellpadding="2" cellspacing="1" border="0">
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="0" class="borderTable">
											<tr class="detailHead">
												<td colspan="6"><bean:message key="prompt.selectableList"/></td>
											</tr>
										</table>
										<bean:size id="sel1" name="riskCategoryCreateForm" property="newQuestionList"/>
										<table width="100%" cellpadding="0" cellspacing="0" class="borderTable">	
											<tr>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr class="listHeader">
															<td class="formDeLabel" width="7%" align="center">
																<input type="checkbox" name="selectAll" id="masterSelect" onClick="selectAllQuestions(this)" title="Check/Uncheck All">
															</td>
															<td class="formDeLabel" width="27%"><bean:message key="prompt.questionName"/></td>
															<td class="formDeLabel" width="29%"><bean:message key="prompt.questionText"/></td>
															<td class="formDeLabel" width="10%"><bean:message key="prompt.UIDisplayOrder"/></td>
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
												<logic:iterate id="quest" name="riskCategoryCreateForm" property="newQuestionList" indexId="index1">
													<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
														<td width="7%" align="center">
															<input type="checkbox" name="selectedValue" value="<bean:write name="quest" property="questionId" />" onclick="checkSelect(this)"></html:checkbox>
														</td>
														<td width="27%">
															<a href="javascript:newCustomWindow('/<msp:webapp/>displayRiskCategoryCreateSummary.do?submitAction=Link&questionID=<bean:write name="quest" property="questionId"/>&showCategory=N', 'winName',830,500);"><bean:write name="quest" property="questionName"/></a>
														</td>
														<td width="29%"><bean:write name="quest" property="questionText"/></td>
														<td width="10%"><bean:write name="quest" property="uiDisplayOrder"/></td> 
														<td width="15%"><bean:write name="quest" property="uiControlType"/></td>  
														<td width="12%"><bean:write name="quest" property="initialAction"/></td>
													</tr>
												</logic:iterate>
											</table>
										</div>
									</td>
								</tr>		
							</table>
							<div class="spacer"></div>       
<!-- BEGIN ADD SELECTED BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<input type="submit" name="submitAction" onclick="return checkAddSelected()" value="<bean:message key="button.addSelected"/>">
									</td>	
								</tr>
							</table>
<!-- END ADD SELECTED BUTTON TABLE -->	
							</logic:notEmpty>							
						</td>	
					</tr>
					<tr>
						<td colspan="6">
<%-- BEGIN SELECTED LIST TABLE --%>	
							<logic:notEmpty  name="riskCategoryCreateForm" property="questionList" > 					
								<table width="100%" cellpadding="2" cellspacing="1" border="0">
									<tr>
										<td>
											<table width="100%" cellpadding="2" cellspacing="0" class="borderTable">
												<tr class="detailHead">
													<td colspan="6">
														<bean:message key="prompt.selectedList"/>
														<input type="hidden" name="selectedList" value="" id="selList">
													</td>
												</tr>
											</table>
											<bean:size id="sel2" name="riskCategoryCreateForm" property="questionList"/>			
											<table width="100%" cellpadding="0" cellspacing="0" class="borderTable">	
												<tr>
													<td>
														<table width="100%" cellpadding="2" cellspacing="1">
															<tr class="listHeader">
																<td class="formDeLabel" width="7%">&nbsp;</td>
																<td class="formDeLabel" width="27%"><bean:message key="prompt.questionName"/></td>
																<td class="formDeLabel" width="29%"><bean:message key="prompt.questionText"/></td>
																<td class="formDeLabel" width="10%"><bean:message key="prompt.UIDisplayOrder"/></td>
																<td class="formDeLabel" width="15%"><bean:message key="prompt.UIControlType"/></td>
																<td class="formDeLabel" width="15%"><bean:message key="prompt.initialAction"/></td>
															</tr>
														</table>
													</td>
													<logic:greaterEqual name="sel2" value="5">
														<td width="2%" class="formDeLabel">&nbsp;</td>
													</logic:greaterEqual>
												</tr>
											</table>
											<div class="scrollingDiv50">
												<table width="100%" cellpadding="2" cellspacing="1">
													<logic:iterate id="quest2" name="riskCategoryCreateForm" property="questionList" indexId="index2">
														<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
													 		<td width="7%" >
																<a href="/<msp:webapp/>displayRiskCategoryCreateSummary.do?submitAction=Remove&questionID=<bean:write name='quest2' property='questionId'/>"><bean:message key="prompt.remove"/></a>&nbsp
															</td>  
															<td width="27%">
																<a href="javascript:newCustomWindow('/<msp:webapp/>displayRiskCategoryCreateSummary.do?submitAction=Link&questionID=<bean:write name="quest2" property="questionId"/>&showCategory=N', 'winName',830,500);"><bean:write name="quest2" property="questionName"/></a>
															</td>
															<td width="29%"><bean:write name="quest2" property="questionText"/></td>
															<td width="10%"><bean:write name="quest2" property="uiDisplayOrder"/></td> 
															<td width="15%"><bean:write name="quest2" property="uiControlType"/></td>  
															<td width="15%"><bean:write name="quest2" property="initialAction"/></td>
														</tr>
													</logic:iterate>
												</table>	
											</div>
										</td>
									</tr>		
								</table>
<%-- END SELECTED LIST TABLE --%>								
							</logic:notEmpty>
							<div class="spacer"></div>
						</td>
					</tr>      
				</table>
<!-- BEGIN BLUE BORDER TABLE -->
</span>
<div class="spacer4px"></div>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back"/></html:submit>
			<html:submit property="submitAction" onclick="return validateCategoryInfo() && disableSubmit(this,this.form)"><bean:message key="button.next" /></html:submit>
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
		</td>
	</tr>
</table>
<div class="spacer"></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>