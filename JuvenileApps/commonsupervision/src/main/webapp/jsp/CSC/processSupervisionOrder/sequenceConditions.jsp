<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/15/2005	 Hien Rodriguez - Create JSP per ER33103 -->
<!-- 10/02/2006  Hien Rodriguez - ER#35457 Add new field PLEA -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 09/25/2009	 C Shimek       - #62196 revised Reset button to be functional by using submitAction -->

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
<title><bean:message key="title.heading" /> - processSupervisionOrder/sequenceConditions.jsp</title>

<bean:size id="condSize" name="supervisionOrderForm" property="conditionSelectedList"/>

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
	changeFormActionURL(theForm, '/<msp:webapp/>'+theActionString,false);
	changeFormTarget(theForm,theTargetString) ;
	//if the target is not a new window then disable to prevent multiple submissions
	if(theTargetString != 'new') 
	disableSubmit(button, theForm);
}
 //-->
 </SCRIPT>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/resequenceConditions" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
    	<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="processOrderTab"/>
					</tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
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
									<logic:equal name="supervisionOrderForm" property="action" value="create">
										<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|12">
										<bean:message key="prompt.create" />
									</logic:equal>									
									<logic:equal name="supervisionOrderForm" property="action" value="update">
									    <logic:notEqual name="supervisionOrderForm" property="orderStatus" value="INCOMPLETE">
											  <input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|19">
											  <bean:message key="prompt.update" />
									   </logic:notEqual>
									   <logic:equal name="supervisionOrderForm" property="orderStatus" value="INCOMPLETE">
										  <input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|12">
										  <bean:message key="prompt.create" />
									   </logic:equal>										
									</logic:equal>												
									<bean:message key="title.supervisionOrder" />&nbsp;-&nbsp;<bean:message key="title.sequenceConditions" />
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
									<li>Select the Up or Down buttons to sequence the conditions. Note the Sequence # and <span class="boldText">S</span>tandard/<span class="boldText">N</span>on<span class=boldText>S</span>tandard columns.</li>
								</ul></td>
							</tr>
						</table>
					<!-- BEGIN DETAIL HEADER TABLE -->
																									
						<tiles:insert page="caseOrderHeaderTile.jsp" flush="true"></tiles:insert>
						
					<!-- END DETAIL HEADER TABLE -->
						<br>
					<!-- BEGIN ORDER PRESENTATION TABLE -->
						<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr class="detailHead">
								<td class="paddedFourPix">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="1%"><img border="0" src="/<msp:webapp/>images/expand.gif" name="orderPresentationFields" onclick="showHide('orderPresentationFields', 'row','/<msp:webapp/>')" style="cursor:pointer"></td>
											<td class="detailHead">&nbsp;<bean:message key="prompt.orderPresentation" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_1.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="orderPresentationFieldsSpan" class="hidden">
								<td>
												<tiles:insert page="orderPresentationTile.jsp" flush="true"></tiles:insert>
							
								</td>							                 
							</tr>
						</table>
					<!-- END ORDER PRESENTATION TABLE -->
						<br>								
					<!-- BEGIN CONDITIONS SEQUENCE SECTION -->
						<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
							<tr class="detailHead">
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td class="detailHead"><bean:message key="prompt.conditionSequence" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_2.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" align="center" width="100%">
										<tr>
											<td>									
												<table rules="rows" frame="void" class="sequenceTable" cellpadding="2" cellspacing="1">
													<%int RecordCounter = 0;
													String bgcolor = "";%>
													<bean:define id="condSizeMinus1" type="java.lang.String"><%=((condSize.intValue())-1)%></bean:define>
													<logic:iterate id="conditionSelectedListIndex" name="supervisionOrderForm" property="conditionSelectedList" indexId="condIndexCount">
														<bean:define id="tempRadVal" name="conditionSelectedListIndex" property="conditionId" type="java.lang.String"/>
														<tr	id="node<%RecordCounter++;%>" taskid=<%=RecordCounter%> position="<%=RecordCounter%>" origposition="<%=RecordCounter%>" onmouseover="hoverClassFlip(this, 'on')"  onmouseout="hoverClassFlip(this, 'out')" >															
															<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="true">
																<td class="impactedOrderBold" width="1%" align="center" title="This is a Like Condition and Impacts other order(s)" width=1%>&nbsp;<%=RecordCounter%>&nbsp;</td>
															</logic:equal>
															<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="false">
																<td class="sequenceNumberCol" width="1%">&nbsp;<%=RecordCounter%>&nbsp;</td>
															</logic:equal>
															
															<logic:notEqual name="condIndexCount" value="<%=condSizeMinus1%>">
															<td width="1%"><span onclick='moveDown(this.parentNode.parentNode);' onmouseover="this.style.cursor= 'pointer'"><img src="/<msp:webapp/>images/arrow_down_large.gif" border="0"></span></td>
															</logic:notEqual>
															<logic:equal name="condIndexCount" value="<%=condSizeMinus1%>">
															<td width="1%">&nbsp;</td>
															</logic:equal>
															<logic:notEqual name="condIndexCount" value="0">
															<td width="1%"><span onclick='moveUp(this.parentNode.parentNode)' onmouseover="this.style.cursor= 'pointer'"><img src="/<msp:webapp/>images/arrow_up_large.gif" border="0"></span></td>												
															</logic:notEqual>
															<logic:equal name="condIndexCount" value="0">
															<td width="1%">&nbsp;</td>												
															</logic:equal>
															
															<td class="boldText" width="1%"><bean:write name="conditionSelectedListIndex" property="standardDesc" /></td>
															<td><logic:equal name="conditionSelectedListIndex" property="specialCondition" value="true">
																			
																			<bean:write name="conditionSelectedListIndex" property="resolvedDescription" filter="false"/>
																		</logic:equal>
																		<logic:notEqual name="conditionSelectedListIndex" property="specialCondition" value="true">
																			<bean:write name="conditionSelectedListIndex" property="description" filter="false"/>
																		</logic:notEqual></td>
														</tr>		
													</logic:iterate>																						
												</table>
											</td>
										</tr>
										
											<input type="hidden" name="resequencedOrderValue"/>
											
									</table>
								</td>
							</tr>
						</table>
						<!-- END CONDITIONS SEQUENCE SECTION -->
						<!-- BEGIN BUTTON TABLE -->
						<br>
						<table align="center" width="98%">						
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="return (changeFormSettings(this.form, 'content', this, 'resequenceConditions.do') && disableSubmit(this, this.form))"><bean:message key="button.back"/></html:submit>&nbsp;
									<html:submit property="submitAction" onclick="return (updateMasterList() && changeFormSettings(this.form, 'content', this, 'resequenceConditions.do')  && disableSubmit(this, this.form));"><bean:message key="button.saveContinue"></bean:message></html:submit>&nbsp;
									<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.reset"></bean:message></html:submit>&nbsp;
									<html:submit property="submitAction" onclick="return (changeFormSettings(this.form, 'content', this, 'resequenceConditions.do') && disableSubmit(this, this.form))"><bean:message key="button.cancel"></bean:message></html:submit>
									
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
						