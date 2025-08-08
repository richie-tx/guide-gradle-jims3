<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/26/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2006  Hien Rodriguez - Implementing interim Back button -->
<!-- 10/10/2006  Hien Rodriguez - Defect#34781 Send reminder when user clicks up/down
     arrows without selecting any condition -->
<!-- 10/24/2006	 Hien Rodriguez - Defect#36270 Revise the look similar to PASO -->
<!-- 01/18/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/condition.tld" prefix="jims2" %>

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
<title><bean:message key="title.heading" /> - administerSuggestedOrder/sequenceConditions.jsp</title>

<bean:size id="condSize" name="suggestedOrderForm" property="conditionSelectedList"/>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/nodomws.js"></script>
<script LANGUAGE="JavaScript" TYPE="text/javascript">
 <!--
 /* <!-- ***** BEGIN LICENSE BLOCK *****
   - Version: MPL 1.1/GPL 2.0/LGPL 2.1
   -
   - The contents of this file are subject to the Mozilla Public License Version
   - 1.1 (the "License"); you may not use this file except in compliance with
   - the License. You may obtain a copy of the License at
   - http://www.mozilla.org/MPL/
   -
   - Software distributed under the License is distributed on an "AS IS" basis,
   - WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
   - for the specific language governing rights and limitations under the
   - License.
   -
   -
   - The Initial Developer of the Original Code is Andy Edmonds.
   - Portions created by the Initial Developer are Copyright (C) 2001
   - the Initial Developer. All Rights Reserved.
   -
   -
   - Alternatively, the contents of this file may be used under the terms of
   - either the GNU General Public License Version 2 or later (the "GPL"), or
   - the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
   - in which case the provisions of the GPL or the LGPL are applicable instead
   - of those above. If you wish to allow use of your version of this file only
   - under the terms of either the GPL or the LGPL, and not to allow others to
   - use your version of this file under the terms of the MPL, indicate your
   - decision by deleting the provisions above and replace them with the notice
   - and other provisions required by the LGPL or the GPL. If you do not delete
   - the provisions above, a recipient may use your version of this file under
   - the terms of any one of the MPL, the GPL or the LGPL.
   -
   - ***** END LICENSE BLOCK *****
   */
var prevNode;

function moveUp(node) {
	var newNode = node.cloneNode("true");
	newNode.setAttribute("position", (newNode.getAttribute("position") - 1));

	var prevRow = node_before(node);
	var prevRowNode = prevRow.cloneNode("true");
	prevRowNode.setAttribute("position", (parseInt(prevRowNode.getAttribute("position")) + 1.0));
	var tableNode = node.parentNode;

	var reattachee = tableNode.replaceChild( newNode, prevRow);
	var reattachee = tableNode.replaceChild(prevRowNode, node);

	rebuild();

	newNode.className="selectedRowGreen"
}

function moveDown(node) {
	var newNode = node.cloneNode("true");
	newNode.setAttribute("position", (parseInt(newNode.getAttribute("position")) + 1.0));

	var nextRow = node_after(node);

	var nextRowNode = nextRow.cloneNode("true");
	nextRowNode.setAttribute("position", (nextRowNode.getAttribute("position") - 1));
	var tableNode = node.parentNode;

	var reattachee = tableNode.replaceChild( newNode, nextRow);

	var reattachee = tableNode.replaceChild(nextRowNode, node);

	rebuild();

	newNode.className="selectedRowGreen"
}

function rebuild() {

	//total number of things to be sequenced
	var numtasks = <%=condSize%>;

	var count =0;
	var movedown = "<span onclick='moveDown(this.parentNode.parentNode);' onmouseover=\"this.style.cursor= 'pointer'\"><img src=\"/CommonSupervision/images/arrow_down_large.gif\" border=0></span>";
	var moveup = "<span onclick='moveUp(this.parentNode.parentNode)' onmouseover=\"this.style.cursor= 'pointer'\"><img src=\"/CommonSupervision/images/arrow_up_large.gif\" border=0></span>";
	var thenode;
	var prevbutton;
	var tasks = document.getElementsByTagName("TR");
	for(var i=0; i<tasks.length;i++) {
		thenode = tasks.item(i);
		
		if(thenode.getAttribute("position")) {

			//alternate row colors here
			//if (i%2 == 1){
				thenode.className = "normalRow"
			//}else thenode.className = "alternateRow"

		count++;
		
		downbutton = node_after(first_child(thenode));
		
		upbutton = node_after(downbutton);
		
		if(count == 1) {
			upbutton.innerHTML="";
		} else {
			node_before(downbutton).innerHTML = count;
			upbutton.innerHTML = moveup;
		}
		if(count  == numtasks) {
			downbutton.innerHTML="";
		} else {
			node_before(downbutton).innerHTML = count;
			downbutton.innerHTML = movedown;
		}
	}
	}
	//updateMasterList();
}

function updateMasterList() {
	var taskidlist= new Array(1);
	var tasks = document.getElementsByTagName("TR");
	var count = 0;
	for(var i=0; i<tasks.length;i++) {
		thenode = tasks.item(i);
		if(thenode.getAttribute("taskid")) {
		taskidlist[count] =  thenode.getAttribute("taskid");
		count++;
		}
	}
	var reorderedListVal=taskidlist.join(",");
	var reorderedElem=document.getElementsByName("resequencedOrderValue")[0];
	reorderedElem.value=reorderedListVal;
	return true;
}

function hoverClassFlip(theRow, action){
	var tasks = document.getElementsByTagName("TR");
	for(var i=0; i<tasks.length;i++) {
		thenode = tasks.item(i);

		if(thenode.getAttribute("position")){

			if (thenode == theRow){
				flipClass(theRow, action);
			}
		}
	}

}

function flipClass(thenode, action){
	if (action == "on" && thenode.className != "selectedRowGreen"){
			thenode.className = "normalRowHover"
	}else if (action == "out" && thenode.className != "selectedRowGreen"){
			thenode.className = "normalRow"
	}
}

function changeFormSettings(theForm, theTargetString, button, theActionString)
{
	if (theTargetString=="new"){
		window.open("", "new", "height=300,width=500,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
	}
	changeFormActionURL(theForm, '/<msp:webapp/>'+theActionString,false);	
	changeFormTarget(theForm,theTargetString) ;
	//if the target is not a new window then disable to prevent multiple submissions
	if(theTargetString != 'new') 
	disableSubmit(button, theForm);
}
 //-->
</script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySuggestedOrderSummary" target="content">
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
									<logic:equal name="suggestedOrderForm" property="action" value="noOffense">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|8">
										<bean:message key="prompt.create" />
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="create">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|8">
										<bean:message key="prompt.create" />
									</logic:equal>									
									<logic:equal name="suggestedOrderForm" property="action" value="update">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|15">
										<bean:message key="prompt.update" />
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="copy">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|22">
										<bean:message key="prompt.copy" />
									</logic:equal>
									<bean:message key="title.suggestedOrder" /> - <bean:message key="prompt.conditionSequence" />
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
								<td>
									<ul>
										<li>Select a condition and click the Up and Down buttons to the right to sequence the conditions. Note the Sequence # and <span class="boldText">S</span>tandard/<span class="boldText">N</span>on<span class="boldText">S</span>tandard columns</li>
									</ul>
								</td>
							</tr>
						</table>										
					<!-- BEGIN SUGGESTED ORDER SECTION -->
						<table width="98%" border="0" cellspacing="1" cellpadding="4">
							<tr>
								<td class="detailHead" colspan="2">
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
								<td class="formDe"><bean:write name="suggestedOrderForm" property="orderName"/></td>
							</tr>
							<tr id="so1" class="hidden">
								<td class="formDeLabel" width="1%"><bean:message key="prompt.description" /></td>
								<td class="formDe"><bean:write name="suggestedOrderForm" property="orderDescription" /></td>
							</tr>
					<!-- END SUGGESTED ORDER SECTION -->				
										
							<tr><td><img src="/<msp:webapp/>images/spacer.gif"></td></tr>
					<!-- BEGIN SELECTED OFFENSES SECTION -->
							<tr>
								<td class="detailHead" colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="1%"><a href="javascript:showHide('offenses','row','/<msp:webapp/>')"><img border="0" src="/<msp:webapp/>images/expand.gif" name="offenses"></a></td>
											<td class="detailHead">&nbsp;<bean:message key="prompt.selectedOffenses" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_2.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<logic:empty name="suggestedOrderForm" property="offenseSelectedList">	
							<tr id="offensesSpan" class="hidden">			
								<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.selectedOffenses" /></td>
								<td class="formDe">None Selected</td>
							
							</tr>
							</logic:empty>	
							<logic:notEmpty name="suggestedOrderForm" property="offenseSelectedList">	
							<tr id="offensesSpan" class="hidden">
								<td colspan="2">								
									<table width="100%" border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.offenseCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.offenseLiteral" /></td>
											<td class="formDeLabel"><bean:message key="prompt.stateOffenseCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.penalCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.levelCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.degreeCode" /></td>
										</tr>
										<%int RecordCounter = 0;
										String bgcolor = "";%>
										<logic:iterate id="offenseSelectedListIndex" name="suggestedOrderForm" property="offenseSelectedList">   
										<tr
											class=<%RecordCounter++;
											bgcolor = "alternateRow";
											if (RecordCounter % 2 == 1)
												bgcolor = "normalRow";
											out.print(bgcolor);%>>
											
											<td><bean:write name="offenseSelectedListIndex" property="offenseCodeId" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="description" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="stateCodeNum" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="penalCode" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="level" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="degree" /></td>
										</tr> 
										</logic:iterate>																			
									</table>									
								</td>
							</tr>
						</logic:notEmpty>
					<!-- END SELECTED OFFENSES SECTION -->
							<tr><td><img src="/<msp:webapp/>images/spacer.gif"></td></tr>													
					<!-- BEGIN SELECT COURTS SECTION -->
							<tr>
								<td class="detailHead" colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="1%"><a href="javascript:showHide('courts','row','/<msp:webapp/>')"><img border="0" src="/<msp:webapp/>images/expand.gif" name="courts"></a></td>
											<td class="detailHead">&nbsp;<bean:message key="prompt.selectCourtsAndIncludeStandardNonstandard" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_3.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="courtsSpan" class="hidden">
								<td colspan="2">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">										
										<tr>
											<td>																	
												<tiles:insert page="../common/courts.jsp" flush="true">
													<tiles:put name="beanName" beanName="suggestedOrderForm" />
													<tiles:put name="mode" value="view" />
												</tiles:insert>
											</td>
										</tr>
									</table>	
									<table width="100%" border="0" cellpadding="2" cellspacing="1">										
										<tr>
											<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.includeConditions" /></td>
											<td class="formDe"><bean:write name="suggestedOrderForm" property="standardLiteral" /></td>												
										</tr>																			
									</table>
								</td>
							</tr>	  
					<!-- END SELECT COURTS SECTION -->
							<tr><td><img src="/<msp:webapp/>images/spacer.gif"></td></tr>										
						</table>
					<!-- BEGIN CONDITIONS SEQUENCE SECTION -->
						<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
							<tr class="detailHead">
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td class="detailHead"><bean:message key="prompt.conditionSequence" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_4.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table border="0" align="center" width="100%">
										<tr>
											<td>									
												<table rules="rows" frame="void" class="sequenceTable" cellpadding="2" cellspacing="1">
													<%int RecordCounter = 0;
													String bgcolor = "";%>
													
													<bean:define id="condSizeMinus1" type="java.lang.String"><%=((condSize.intValue())-1)%></bean:define>
													<logic:iterate id="conditionSelectedListIndex" name="suggestedOrderForm" property="conditionSelectedList" indexId="condIndexCount">
														<bean:define id="tempRadVal" name="conditionSelectedListIndex" property="conditionId" type="java.lang.String"/>
														<tr	id="node<%RecordCounter++;%>" taskid=<%=RecordCounter%> position="<%=RecordCounter%>" origposition="<%=RecordCounter%>" onmouseover="hoverClassFlip(this, 'on')"  onmouseout="hoverClassFlip(this, 'out')" >															
														<logic:notEqual name="conditionSelectedListIndex" property="statusId" value="I" >
															<logic:notEqual name="conditionSelectedListIndex" property="statusId" value="INVALID" >
																<td class="sequenceNumberCol" width="5%"><%=RecordCounter%></td>
															</logic:notEqual>
														</logic:notEqual>			
														<logic:equal name="conditionSelectedListIndex" property="statusId" value="I" >
															<td class="inactiveConditionBold" title="Inactive Condition" width="5%"><%=RecordCounter%></td>
														</logic:equal>
														<logic:equal name="conditionSelectedListIndex" property="statusId" value="INVALID" >			
															<td class="wrongCourtConditionBold" title="Condition No Longer Applies to This Court" width="5%"><%=RecordCounter%></td>			
														</logic:equal>			
																	<logic:notEqual name="condIndexCount" value="<%=condSizeMinus1%>">
																		<td><span onclick='moveDown(this.parentNode.parentNode);' onmouseover="this.style.cursor= 'pointer'"><img src="/<msp:webapp/>images/arrow_down_large.gif" border="0"></span></td>
																	</logic:notEqual>
																	<logic:equal name="condIndexCount" value="<%=condSizeMinus1%>">
																		<td>&nbsp;</td>
																	</logic:equal>
																	<logic:notEqual name="condIndexCount" value="0">
																		<td><span onclick='moveUp(this.parentNode.parentNode)' onmouseover="this.style.cursor= 'pointer'"><img src="/<msp:webapp/>images/arrow_up_large.gif" border="0"></span></td>												
																	</logic:notEqual>
																	<logic:equal name="condIndexCount" value="0">
																		<td>&nbsp;</td>												
																	</logic:equal>
																	
														<logic:notEqual name="conditionSelectedListIndex" property="statusId" value="I" >
															<logic:notEqual name="conditionSelectedListIndex" property="statusId" value="INVALID" >
																<td class="boldText"><bean:write name="conditionSelectedListIndex" property="standardDesc" /></td>
																<td><bean:write name="conditionSelectedListIndex" property="conditionLiteral" filter="false"/></td>
															</logic:notEqual>
														</logic:notEqual>			
														<logic:equal name="conditionSelectedListIndex" property="statusId" value="I" >
															<td class="inactiveConditionBold" title="Inactive Condition"><bean:write name="conditionSelectedListIndex" property="standardDesc" /></td>
															<td class="inactiveCondition" title="Inactive Condition"><bean:write name="conditionSelectedListIndex" property="conditionLiteral" filter="false"/>&nbsp;</td>
														</logic:equal>
														<logic:equal name="conditionSelectedListIndex" property="statusId" value="INVALID" >			
															<td class="wrongCourtConditionBold" title="Condition No Longer Applies to This Court"><bean:write name="conditionSelectedListIndex" property="standardDesc" /></td>
															<td class="wrongCourtCondition" title="Condition No Longer Applies to This Court"><bean:write name="conditionSelectedListIndex" property="conditionLiteral" filter="false"/>&nbsp;</td>			
														</logic:equal>
													</logic:iterate>																						
												</table>
											</td>
										</tr>
										<tr>
											<input type="hidden" name="resequencedOrderValue"/>
												<td align="center" colspan="2">												
												<html:submit property="submitAction" onclick="updateMasterList();changeFormSettings(this.form, 'new', this, 'displaySuggestedOrderSummary.do')"><bean:message key="button.previewOrder"/></html:submit></td>
											</tr>
									</table>
								</td>
							</tr>
						</table>
						<!-- END CONDITIONS SEQUENCE SECTION -->
						<table width="98%" cellpadding="0" cellspacing="0">
                			<tr>
                  				<td class="legendSmallText">Red <span class="inactiveCondition">conditions</span> signify that the condition is inactive.</td>
                			</tr>
                			<tr>
                  				<td class="legendSmallText">Yellow <span class="wrongCourtCondition">conditions</span> signify that the condition no longer applies to this suggested order's court(s).</td>
                			</tr>
              			</table>
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table align="center" width="98%">						
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="return (changeFormSettings(this.form, 'content', this, 'displaySuggestedOrderSummary.do')  && disableSubmit(this, this.form));"><bean:message key="button.back"/></html:submit>&nbsp;
									<html:submit property="submitAction" onclick="return (updateMasterList() && changeFormSettings(this.form, 'content', this, 'displaySuggestedOrderSummary.do')  && disableSubmit(this, this.form));"><bean:message key="button.next"></bean:message></html:submit>&nbsp;									 								
									<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
									<html:submit property="submitAction" onclick="return (changeFormSettings(this.form, 'content', this, 'displaySuggestedOrderSummary.do')  && disableSubmit(this, this.form));"><bean:message key="button.cancel"></bean:message></html:submit>
								</td>
							</tr>					
						</table>						
					<!-- END BUTTON TABLE -->
				</td>
			</tr>
		</table>
</td></tr></table>					

</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>											
						