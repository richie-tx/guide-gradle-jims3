<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/14/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2006  Hien Rodriguez - Implementing interim Back button -->
<!-- 01/18/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerSuggestedOrder/selectOffense.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">

var level = new Array()
	level["F"] = new Array();
	level["M"] = new Array();

	level["F"][0] = new Array();
		level["F"][0][0]="1";
		level["F"][0][1]="1";	
	level["F"][1] = new Array();
		level["F"][1][0]="2";
		level["F"][1][1]="2";	
	level["F"][2] = new Array();
		level["F"][2][0]="3";
		level["F"][2][1]="3";
	level["F"][3] = new Array();
		level["F"][3][0]="Capital";
		level["F"][3][1]="C";
	level["F"][4] = new Array();
		level["F"][4][0]="State";
		level["F"][4][1]="S";

	level["M"][0] = new Array();
		level["M"][0][0]="A";
		level["M"][0][1]="A";
	level["M"][1] = new Array();
		level["M"][1][0]="B";
		level["M"][1][1]="B";
	level["M"][2] = new Array();
		level["M"][2][0]="C";
		level["M"][2][1]="C";

var degreeSelectValue = "<bean:write name="suggestedOrderForm" property="degreeCodeId"/>"

//fills the drop down
function fillSelect(theSelect)
{	
	theForm = theSelect.form;
	var selectedVal=theSelect.options[theSelect.selectedIndex].value

	var selectSize = theForm.degreeCodeId.options.length - 1;

		for (i=selectSize; i>=0; i--)
		{
			theForm.degreeCodeId.options[i] = null;
		}

	if (selectedVal == "")
	{
		for (i=selectSize; i>=0; i--)
		{
			theForm.degreeCodeId.options[i] = null;
		}
		theForm.degreeCodeId.options[0] = new Option ("Please Select", "");
		theForm.degreeCodeId.disabled = true;
	}else{
		theForm.degreeCodeId.disabled = false;
		theForm.degreeCodeId.options[0] = new Option ("Please Select", "");
		for (x=0; x<level[selectedVal].length; x++)
		{
			theForm.degreeCodeId.options[theForm.degreeCodeId.options.length] = new Option (level[selectedVal][x][0], level[selectedVal][x][1]);
			
		}
		if (degreeSelectValue != ""){
			for (x=0; x<level[selectedVal].length; x++)
			{
				if(degreeSelectValue == level[selectedVal][x][1]){
					theForm.degreeCodeId.options[x+1].selected = true;
				}
			}			
		}
	}
}

function validateAddToList(theForm){
if (theForm.offenseId.value==""){
	alert("Please enter an Offense Code");
	theForm.offenseId.focus();
	return false;
	}else {
	return true;
	}
}
</script>         
</head>

<body topmargin="0" leftmargin="0" onload="fillSelect(document.forms[0].levelCodeId)" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySuggestedOrderSelectCourt" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>    	
  	</tr>
  	<tr>
    	<td valign="top">
    	<!-- BEGIN BLUE TAB TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value="suggestedOrderTab"/>
							</tiles:insert>						
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>				
			</table>
		<!-- END BLUE TAB TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">					
					<!-- BEGIN HEADING TABLE -->
						<table width="98%">
							<tr>
								<td align="center" class="header">
									<logic:equal name="suggestedOrderForm" property="action" value="create">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|5">
										<bean:message key="prompt.create" />
									</logic:equal>									
									<logic:equal name="suggestedOrderForm" property="action" value="update">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|12">
										<bean:message key="prompt.update" />
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="copy">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|19">
										<bean:message key="prompt.copy" />
									</logic:equal>
									<bean:message key="title.suggestedOrder" /> - <bean:message key="prompt.selectOffenses" />
								</td>
							 </tr>						 						  
						</table>
					<!-- END HEADING TABLE -->						
					<!-- BEGIN ERROR TABLE -->
						<table width="98%" align="center">							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table>
					<!-- END ERROR TABLE -->		
					<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td><ul>
									<li>If you know the offense code, enter it in the top section and click Add to List.</li>
									<li>To find offenses enter at least 1 criteria in the lower section and click Submit.</li>
								</ul></td>
							</tr>
						</table>
					<!-- BEGIN SUGGESTED ORDER SECTION -->
						<table width="98%" border="0" cellspacing="1" cellpadding="4">
							<tr>
								<td class="detailHead" colspan="4">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="1%"><a href="javascript:showHideMulti('suggestedOrder', 'so', 2,'/<msp:webapp/>')" ><img border="0" src="/<msp:webapp/>images/expand.gif" name="suggestedOrder"></a></td>
											<td class="detailHead">&nbsp;<bean:message key="prompt.suggestedOrder" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_1.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="so0" class="hidden">
								<td class="formDeLabel" width="1%"><bean:message key="prompt.name" /></td>
								<td class="formDe" colspan="3"><bean:write name="suggestedOrderForm" property="orderName"/></td>
							</tr>
							<tr id="so1" class="hidden">
								<td class="formDeLabel" width="1%"><bean:message key="prompt.description" /></td>
								<td class="formDe" colspan="3"><bean:write name="suggestedOrderForm" property="orderDescription" /></td>
							</tr>
					<!-- END SUGGESTED ORDER SECTION -->
							<tr><td><img src="/<msp:webapp/>images/spacer.gif"></td></tr>
					<!-- BEGIN SEARCH OFFENSE SECTION -->
							<tr>
								<td class="detailHead" colspan="4">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td class="detailHead" colspan="3"><bean:message key="prompt.searchForOffenses" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_2.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr class="formDeLabel"><td colspan="4" class="required"><bean:message key="prompt.2.diamond"/>Required For This Section</td></tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.offenseCode" /></td>
								<td class="formDe" colspan="3"><html:text property="offenseId" maxlength="6" size="6"/>&nbsp;
									<html:submit property="submitAction" onclick="return validateAddToList(this.form) && disableSubmit(this, this.form);"><bean:message key="button.addToList"></bean:message></html:submit></td>							
							</tr>
							
							<tr bgcolor="#cccccc"><td colspan="4"><img src="/<msp:webapp/>images/spacer.gif"></td></tr>
							
							<tr class="formDeLabel"><td colspan="4" class="required"><bean:message key="prompt.2.diamond"/>At Least 1 Required For This Section</td></tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.level" /></td>
								<td class="formDe">		
									<html:select name="suggestedOrderForm" property="levelCodeId" onchange="fillSelect(this)">
										<html:option value="">BOTH</html:option>
										<html:option value="F">FELONY</html:option>
										<html:option value="M">MISDEMEANOR</html:option>
									</html:select> 
						         </td> 
								<td class="formDeLabel"><bean:message key="prompt.degree" /></td>
								<td class="formDe">															
						       	  	<html:select disabled="true" name="suggestedOrderForm" property="degreeCodeId" >
								     	<html:option value=""><bean:message key="select.generic"/></html:option>								 								     
								  	</html:select>									
								</td>								
							</tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.offenseCode" /></td>
								<td class="formDe"><html:text property="searchOffenseId" maxlength="6" size="6"/></td>
								<td class="formDeLabel" nowrap><bean:message key="prompt.penalCode" /></td>
								<td class="formDe"><html:text property="penalCodeId" maxlength="10" size="10" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.offenseLiteral" /></td>
								<td class="formDe" colspan="3"><html:text property="offenseLiteral" maxlength="100" size="60"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.stateOffenseCode" /></td>
								<td class="formDe" colspan="3"><html:text property="stateOffenseCodeId" maxlength="8" size="8"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap></td>
								<td class="formDe" colspan="3">
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.submit"></bean:message></html:submit>
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.refresh"></bean:message></html:submit></td>												
							</tr>
						</table>
					<!-- END SEARCH OFFENSE SECTION -->

					<!-- BEGIN SEARCH OFFENSE RESULT SECTION -->					
					<logic:notEmpty name="suggestedOrderForm" property="offenseResultList">
					<bean:size id="offenseSize" name="suggestedOrderForm" property="offenseResultList" />					 							 						
						<table width="98%" border="0" cellspacing="1" cellpadding="2">
							<tr>
								<td>									
									<table width="100%" cellpadding="2" cellspacing="0">
										<tr>
											<td class="detailHead"><bean:message key="prompt.offenseSearchResults" /></td>
										</tr>
									</table>									
									<script type="text/javascript">
									renderScrollingArea(<bean:write name="offenseSize" />);									
									</script>
									
									<table width="100%" cellpadding="2" cellspacing="1">
									<thead>
										<tr class="listHeader">											
											<td align="center">
												<logic:lessEqual name="offenseSize" value="1000">
													<input type="checkbox" name="checkAllOffenses" onclick="checkAllByName(this, 'offenseResultListIndex','offenseCodeId', <bean:write name="offenseSize" />)" title="Check/Uncheck All">				 
						 						</logic:lessEqual>					 						
											</td>										
											<td class="formDeLabel"><bean:message key="prompt.offenseCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.offenseLiteral" /></td>
											<td class="formDeLabel"><bean:message key="prompt.stateOffenseCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.penalCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.levelCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.degreeCode" /></td>
										</tr>
										</thead>
										<tbody>	
										<%int RecordCounter = 0;
										String className = "";%>  	
										<logic:iterate id="offenseResultListIndex" name="suggestedOrderForm" property="offenseResultList">																										
										<tr										
											class=<%RecordCounter++;
											className = "alternateRow";
											if (RecordCounter % 2 == 1){
												className = "normalRow";
											}
											out.print(className);%>
											 id="<bean:write name="offenseResultListIndex" property="offenseCodeId" />"
											>
											<td align="center">
											<logic:lessEqual name="offenseSize" value="1000">
												<html:checkbox name="offenseResultListIndex" property="offenseCodeId" value="offenseCodeId" onclick="uncheckCheckAll(this,'checkAllOffenses')" indexed="true"></html:checkbox>															
						 					</logic:lessEqual>
						 					<logic:greaterEqual name="offenseSize" value="1001">
												<html:checkbox name="offenseResultListIndex" property="offenseCodeId" value="offenseCodeId" indexed="true"></html:checkbox>															
						 					</logic:greaterEqual> 						
											</td>										
											<td><bean:write name="offenseResultListIndex" property="offenseCodeId" /></td>
											<td><bean:write name="offenseResultListIndex" property="description" /></td>
											<td><bean:write name="offenseResultListIndex" property="stateCodeNum" /></td>
											<td><bean:write name="offenseResultListIndex" property="penalCode" /></td>
											<td><bean:write name="offenseResultListIndex" property="level" /></td>
											<td><bean:write name="offenseResultListIndex" property="degree" /></td>
										</tr>
										</logic:iterate>										
										</tbody>						
									</table>
									</div>			
									<a name=resultsList></a>						
									<table border="0" width="100%">
									  	<tr>
									    	<td align="center">
									    		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.addSelected"></bean:message></html:submit>									    		
									    		</td>						    								
									  	</tr>
									</table>
								</td>
							</tr>
						</table>
					</logic:notEmpty>	
					
					<!-- END SEARCH OFFENSE RESULT SECTION -->
						<table width="98%" cellpadding="2" cellspacing="0">
							<tr>
								<td>	
					<!-- BEGIN SELECTED OFFENSE LIST SECTION -->
					<logic:notEmpty name="suggestedOrderForm" property="offenseSelectedList">
					<bean:size id="offenseSelectedSize" name="suggestedOrderForm" property="offenseSelectedList" />								
						<table width="100%" cellpadding="2" cellspacing="0">
							<tr>
								<td class="detailHead"><bean:message key="prompt.offensesSelectedList" /></td>
							</tr>
						</table>
					<%--<table width="100%" cellpadding="0" cellspacing="0" class=borderTable>
							<tr>
								<td>
									<table width=100% cellpadding=2 cellspacing=1>
										<tr class="listHeader">
											<td><img src="/<msp:webapp/>images/spacer.gif" width=50 height=1></td>											
											<td class="formDeLabel"><bean:message key="prompt.offenseCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.offenseLiteral" /></td>
											<td class="formDeLabel"><bean:message key="prompt.stateOffenseCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.penalCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.levelCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.degreeCode" /></td>
										</tr>
									</table>
								</td>
								<td width=1% bgcolor=#cccccc><img src="/<msp:webapp/>images/spacer.gif" width=16></td>								
							</tr>									
						</table>--%>						
						<script type="text/javascript">
						renderScrollingArea(<bean:write name="offenseSelectedSize" />);									
						</script>
						<a name=selectedList></a>
						
						<table width="100%" cellpadding="2" cellspacing="1" border="0">
						<thead>	
							<tr class="listHeader">
								<td align="center"><img src="/<msp:webapp/>images/spacer.gif" width="50" height="1"></td>											
								<td class="formDeLabel"><bean:message key="prompt.offenseCode" /></td>
								<td class="formDeLabel"><bean:message key="prompt.offenseLiteral" /></td>
								<td class="formDeLabel"><bean:message key="prompt.stateOffenseCode" /></td>
								<td class="formDeLabel"><bean:message key="prompt.penalCode" /></td>
								<td class="formDeLabel"><bean:message key="prompt.levelCode" /></td>
								<td class="formDeLabel"><bean:message key="prompt.degreeCode" /></td>
							</tr>
							</thead>
							<tbody>				
							<%int RecordCounter2 = 0;
							String bgcolor = "";%>
							<logic:iterate id="offenseSelectedListIndex" name="suggestedOrderForm" property="offenseSelectedList">  
							<tr
								class=<%RecordCounter2++;
								bgcolor = "alternateRow";
								if (RecordCounter2 % 2 == 1)
									bgcolor = "normalRow";
								out.print(bgcolor);%>>

								<td><a name="selectListAnchor<%=RecordCounter2%>"></a><a href="/<msp:webapp/>removeOffensesFromList.do?offenseId=<bean:write name="offenseSelectedListIndex" property="offenseCodeId"/>#selectListAnchor<%=RecordCounter2-1%>">
									<bean:message key="prompt.remove" /></a></td>								
								<td><bean:write name="offenseSelectedListIndex" property="offenseCodeId" /></td>
								<td><bean:write name="offenseSelectedListIndex" property="description" /></td>
								<td><bean:write name="offenseSelectedListIndex" property="stateCodeNum" /></td>
								<td><bean:write name="offenseSelectedListIndex" property="penalCode" /></td>
								<td><bean:write name="offenseSelectedListIndex" property="level" /></td>
								<td><bean:write name="offenseSelectedListIndex" property="degree" /></td>
							</tr>														
							</logic:iterate>	
							</tbody>											
						</table>
						</div>					
					<br>
				</logic:notEmpty>
			</td></tr></table>
			<!-- END SELECTED OFFENSE LIST SECTION -->

			<!-- BEGIN BUTTON TABLE -->
			<table align="center" width="98%">				
				<tr>
					<td align="center">
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.next"></bean:message></html:submit>&nbsp;
						<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
					</td>
				</tr>					
			</table>						
		<!-- END BUTTON TABLE -->				
		</td>
	</tr>
</table>
</td>
</tr>
</table>
</div>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>


