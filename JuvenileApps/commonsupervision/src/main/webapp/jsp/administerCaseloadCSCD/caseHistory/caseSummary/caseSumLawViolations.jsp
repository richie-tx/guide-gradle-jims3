<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/25/2008 C. Shimek - Converted to JSP -->
<!-- 07/02/2010 C. Shimek - #66278 revised comment max. check from onsubmit to active -->
<!-- 10/07/2010 C Shimek  - #67758 revised comment max. from 500 to 3500 and added spell check -->
<!-- 06/16/2011 R Young   - #70346 Violation Report-copy Comments in each section(UI) -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ page import="naming.UIConstants" %>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/caseSummary/caseSumLawViolations.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseHistory_util.js"></script>
<script type='text/javascript'>
// begin modified offense level/degree dropdown code from jsp/common/searchForOffenseTile.jsp
var level = new Array()
	level["F"] = new Array();
	level["M"] = new Array();
	
	level["F"][0] = new Array();
	level["F"][0][0]="-";
	level["F"][0][1]="";

	level["F"][1] = new Array();
	level["F"][1][0]="1";
	level["F"][1][1]="1";	
	level["F"][2] = new Array();
		level["F"][2][0]="2";
		level["F"][2][1]="2";	
	level["F"][3] = new Array();
		level["F"][3][0]="3";
		level["F"][3][1]="3";
	level["F"][4] = new Array();
		level["F"][4][0]="CAPITAL";
		level["F"][4][1]="C";
	level["F"][5] = new Array();
		level["F"][5][0]="STATE";
		level["F"][5][1]="S";
			
	level["M"][0] = new Array();
		level["M"][0][0]="-";
		level["M"][0][1]="";
	level["M"][1] = new Array();
		level["M"][1][0]="A";
		level["M"][1][1]="A";
	level["M"][2] = new Array();
		level["M"][2][0]="B";
		level["M"][2][1]="B";
	level["M"][3] = new Array();
		level["M"][3][0]="C";
		level["M"][3][1]="C";

var degreeSelectValue = "<bean:write name="caseSummaryForm" property="offenseDegreeId"/>"

//fills the drop down
function fillSelect(theSelect)
{	
	theForm = theSelect.form;
	var selectedVal=theSelect.options[theSelect.selectedIndex].value

	var selectSize = theForm.offenseDegreeId.options.length - 1;

		for (i=selectSize; i>=0; i--)
		{
			theForm.offenseDegreeId.options[i] = null;
		}

	if (selectedVal == "")
	{
		for (i=selectSize; i>=0; i--)
		{
			theForm.offenseDegreeId.options[i] = null;
		}
		theForm.offenseDegreeId.options[0] = new Option ("-", "");
		theForm.offenseDegreeId.disabled = true;
	}else{
		theForm.offenseDegreeId.disabled = false;
		for (x=0; x<level[selectedVal].length; x++)
		{
			theForm.offenseDegreeId.options[x] = new Option (level[selectedVal][x][0], level[selectedVal][x][1]);
			
		}
		if (degreeSelectValue != ""){
			for (x=0; x<level[selectedVal].length; x++)
			{
				if(degreeSelectValue == level[selectedVal][x][1]){
					theForm.offenseDegreeId.options[x].selected = true;
				}
			}			
		}
	}
}
// end modified offense level/degree dropdown code
</script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseSummary/caseSumLawViolations.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayLawViolationSummary" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Case_Summary/CSCD_Case_Summary.htm#|11">
<div align="center">
<!-- BEGIN PAGE TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert>
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
<!-- ENE BLUE TABS TABLE -->	
<!-- BEGIN BLUE BORDER TABLE -->				
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif"  height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFO TABLE -->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="#cccccc" colspan="2">
									<tiles:insert page="../../../common/caseloadHeaderCase.jsp" flush="true">
									</tiles:insert> 
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END SUPERVISEE INFO TABLE -->	
					</td>
				</tr>							
				<tr>
					<td valign="top" align="center">
						<!--casefile tabs start-->
<!-- BEGIN GREEN TABS TABLE -->							
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
										<tiles:put name="tab" value="CasesTab" />
									</tiles:insert>
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END GREEN TABS TABLE -->
<!-- BEGIN BLUE BORDER TABLE -->								
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">
												<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.caseSummary"/>&nbsp;-
												<bean:message key="prompt.lawViolations"/>
											</td>
										</tr>
									</table>
<!-- END HEADING TABLE -->
<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Click Remove Selected to remove selected Law Violation. Click next when complete.</li>
												</ul>
											</td>
										</tr>
									</table>
<!-- END INSTRUCTION TABLE -->
									<br>
									<SCRIPT LANGUAGE="JavaScript" ID="js1">
										var cal1 = new CalendarPopup();
										cal1.showYearNavigation();
									</SCRIPT>
<!-- BEGIN LAW VIOLATIONS TABLE -->									
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.lawViolations"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
												<% String displayClass; %>
													<tr class="formDeLabel">
														<td width="1%"></td>
														<td><bean:message key="prompt.case#"/>
															<jims2:sortResults beanName="caseSummaryForm" results="create1ElementsList" primaryPropSort="caseId" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="4" hideMe="true"/>  
														</td>
														<td><bean:message key="prompt.CRT"/></td>
														<td><bean:message key="prompt.offenseDate"/></td>
														<td><bean:message key="prompt.offenseLiteral"/></td>
														<td><bean:message key="prompt.offenseLevel"/></td>
														<td><bean:message key="prompt.offenseDegree"/></td>
													</tr>
										 			<logic:iterate id="vrIter" name="caseSummaryForm" property="create1ElementsList" indexId="index">
													  	<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
															<td width="1%">
																<input type="checkbox" name="selectedLawViolationsIds" value="<bean:write name='vrIter' property='lawViolationId'/>" >																
															</td>
															<% displayClass = "class=''"; %>
													 		<logic:equal name="vrIter" property="manualAdded" value="true">
																<% displayClass = "class=pendingSP"; %>	
															</logic:equal> 
															<td <%= displayClass %>><bean:write name="vrIter" property="caseId"/></td>
															<td <%= displayClass %>><bean:write name="vrIter" property="courtId"/></td>
															<td <%= displayClass %>><bean:write name="vrIter" property="offenseDate" formatKey="date.format.mmddyyyy"/></td>
															<td <%= displayClass %>><bean:write name="vrIter" property="offenseLitrel"/></td>
															<td <%= displayClass %>>
																<logic:equal name="vrIter" property="offenseLevel" value="F">
																	<bean:message key="prompt.felony"/>
																</logic:equal>
																<logic:equal name="vrIter" property="offenseLevel" value="M">
																	<bean:message key="prompt.misdemeanor"/>
																</logic:equal>
															</td>
															<td <%= displayClass %>><bean:write name="vrIter" property="offenseDegree"/></td>
														</tr>
												   	</logic:iterate> 
										 			<logic:equal name="caseSummaryForm" property="showAddFields" value="true">  
											 			<tr>	
												 			<td width="1%">&nbsp;</td>
											 				<td><html:text name="caseSummaryForm" property="lvCaseNum" size="15" maxlength="15" /></td>
															<td><html:text name="caseSummaryForm" property="courtNum" size="3" maxlength="3" /></td>
															<td>
																<html:text name="caseSummaryForm" size="10" property="offenseDateStr"/>
																	<a href="#" onClick="cal1.select(caseSummaryForm.offenseDateStr,'anchor1','MM/dd/yyyy'); return false;"
																		NAME="anchor1" ID="anchor1"><bean:message key="prompt.4.calendar"/></a> 
															</td>
															<td><html:text name="caseSummaryForm" property="offenseLiteral" size="30" maxlength="50" /></td>
															<td>
																<html:select name="caseSummaryForm" property="offenseLevelId" onchange="fillSelect(this)">
																	<html:option value=""><bean:message key="select.generic"/></html:option>
																	<html:option value="F"><bean:message key="prompt.felony"/></html:option>
																	<html:option value="M"><bean:message key="prompt.misdemeanor"/></html:option>
													<%-- 			<html:optionsCollection name="caseSummaryForm" property="offenseLevels" value="offenseLevelId" label="offenseLevelCodeDesc" /> --%>
																</html:select>
															</td>
															<td>	
																<html:select disabled="true" name="caseSummaryForm" property="offenseDegreeId" >
														     		<html:option value="">-</html:option>								 								     
														  		</html:select>								
															</td>
														</tr>	
													</logic:equal>	
												</table>
											</td>
										</tr>  
									</table>
<!-- END LAW VIOLATIONS TABLE -->	
<!-- BEGIN ADD BUTTON TABLE -->
									<table width="98%">
										<tr>
											<td><bean:message key="prompt.rowsManuallyAdded"/></td>
										</tr>	
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)"><bean:message key="button.addLawViolation" /></html:submit>
											</td>	
										</tr>
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
										</tr>
									</table>
<!-- END ADD BUTTON TABLE -->										
<!-- BEGIN LAW VIOLATIONS COMMENTS TABLE -->										
									<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.comments"/>
											<tiles:insert page="/jsp/common/spellCheckCaseHistTile.jsp" flush="false">
	                          						<tiles:put name="tTextField" value="create1Comments"/>
	                          						<tiles:put name="tSpellCount" value="spellBtn1" />
	                        					</tiles:insert>
												 (Max. characters allowed: 3500)
											</td>
										</tr>
										<tr>
											<td class="formDe">
												<html:textarea name="caseSummaryForm" property="create1Comments" 
													onmouseout="textLimit(this, 3500);" 
       												onkeyup="textLimit(this, 3500);" 
													style="width:100%" rows="30">
												</html:textarea>
											</td>
										</tr>
										<input type="hidden" name="prevComments" value="<bean:write name='caseSummaryForm' property='previousLawViolationsComments' />" >
									</table>
<!-- END LAW VIOLATIONS COMMENTS TABLE -->										
									<br>
<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)"> <bean:message key="button.next" /></html:submit>
												<html:submit property="submitAction" onclick="return validateCheckBoxSelect(this.form) && disableSubmit(this, this.form)"> <bean:message key="button.removeSelected" /></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.refreshLawViolations" /></html:submit>
												<input type="button" name="copyComments" value="<bean:message key="button.copyComments" />" onclick="loadPrevComments()" />
											</td>
										</tr>
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
<!-- END GREEN BORDER TABLE -->							
						<br>
					</td>
				</tr>
			</table>
<!-- END BLUE BORDERE TABLE -->				
		</td>
	</tr>
</table>
<!-- END PAGE TABLE -->	
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>