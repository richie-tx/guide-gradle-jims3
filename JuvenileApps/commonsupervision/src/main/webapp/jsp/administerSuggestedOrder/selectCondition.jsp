<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/20/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2006  Hien Rodriguez - Modify all Labels for HCJPD & PST agencies -->
<!--                            - Implementing interim Back button -->
<!-- 05/24/2006  Hien Rodriguez - ER27629 Remove requirement for Group1 and set default search fields. -->
<!-- 12/01/2005	 Hien Rodriguez - Defect#37018 Increase Condition Name field to 50 -->
<!-- 01/18/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

 
<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.supervision.suggestedorder.helper.SuggestedOrderListHelper"/>

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
<title><bean:message key="title.heading" /> - administerSuggestedOrder/selectCondition.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/groups.js"></script>
<script>
<logic:iterate indexId="groupIterIndex" id="groupIter" name="suggestedOrderForm" property="groups">
	groups[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="groupId" />", "<bean:write name="groupIter" property="name" filter="false" />");
	
	
	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="subgroups">
		var innerGroup = new subgroup("<bean:write name="groupIter2" property="groupId" />", "<bean:write name="groupIter2" property="name" filter="false" />");
		groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>] = innerGroup;
		
		
		<logic:iterate indexId="groupIterIndex3" id="groupIter3" name="groupIter2" property="subgroups">
			var innerGroup = new subgroup("<bean:write name="groupIter3" property="groupId" />", "<bean:write name="groupIter3" property="name" filter="false" />");
			groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>].subgroups[<bean:write name="groupIterIndex3"/>] = innerGroup;
		
		</logic:iterate>
	</logic:iterate>
</logic:iterate>

</script>

<script>
function switchText(theLink, theLiteralRowID){
	if (document.getElementById(theLink).innerHTML == "View Sample")
	{
		show(theLiteralRowID, 1, 'row');
		document.getElementById(theLink).innerHTML = "Hide Sample"
	}else {
		show(theLiteralRowID, 0);
		document.getElementById(theLink).innerHTML = "View Sample"
	}
}

function setSelectedGroups(){
			
		updateGroup2(document.forms[0]);
		if (document.forms[0].group2Id.disabled == false){
			for (x = 0; x<document.forms[0].group2Id.length; x++){
				if (document.forms[0].group2Id.options[x].value == document.forms[0].selectedGrp2Id.value){
					document.forms[0].group2Id.selectedIndex = x;
					break;
				}
			}
		}

		updateGroup3(document.forms[0]);
		if (document.forms[0].group3Id.disabled == false){
			for (x = 0; x<document.forms[0].group3Id.length; x++){
				if (document.forms[0].group3Id.options[x].value == document.forms[0].selectedGrp3Id.value){
					document.forms[0].group3Id.selectedIndex = x;
					break;
				}
			}
		}
}


//slin: this is a custom reset button for this page to deal with the group1-3 selections
function resetThisForm(theForm){
	//instead of calling reset() i am setting text fields to "" as well, but jurisdiction should still default to harris
	theForm.conditionName.value="";
	theForm.conditionLiteral.value="";
	theForm.jurisdictionId.selectedIndex=1;
	
	//for simplicity I am setting all selects to 0 (please select)	
	theForm.group1Id.selectedIndex=0;
	theForm.group2Id.selectedIndex=0;
	theForm.group3Id.selectedIndex=0;
	theForm.group2Id.disabled=true;
	theForm.group3Id.disabled=true;
}
</script>

</head>

<bean:define id="conditionLiteralCaption" name="suggestedOrderForm" property="conditionLiteralCaption" type="java.lang.String"/>
<bean:define id="group1Caption" name="suggestedOrderForm" property="group1Caption" type="java.lang.String"/>
<bean:define id="group2Caption" name="suggestedOrderForm" property="group2Caption" type="java.lang.String"/>
<bean:define id="group3Caption" name="suggestedOrderForm" property="group3Caption" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="setSelectedGroups()">
<html:form action="/displayConditionsSequenceList" target="content" focus="conditionName">
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
						<table width="98%" border="0">
							<tr>
								<td align="center" class="header">									
									<logic:equal name="suggestedOrderForm" property="action" value="create">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|7">
										<bean:message key="prompt.create" />
									</logic:equal>										
									<logic:equal name="suggestedOrderForm" property="action" value="update">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|14">
										<bean:message key="prompt.update" />
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="copy">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|21">
										<bean:message key="prompt.copy" />
									</logic:equal>
									<bean:message key="title.suggestedOrder" /> - 
									<bean:message key="prompt.select" />
									<bean:message key="prompt.nonstandard" />
									<bean:message key="prompt.conditions" />
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
									<li>Enter search criteria for nonstandard conditions to add to this Suggested Order.</li>
								</ul></td>
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
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.selectedOffenses" /></td>
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
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.includeConditions" /></td>
											<td class="formDe"><bean:write name="suggestedOrderForm" property="standardLiteral" filter="false"/></td>												
										</tr>																			
									</table>
								</td>
							</tr>	  
					<!-- END SELECT COURTS SECTION -->
							<tr><td><img src="/<msp:webapp/>images/spacer.gif"></td></tr>
					<!-- BEGIN SEARCH SUPERVISION CONDITIONS SECTION -->
							<tr>
								<td class="detailHead" colspan="2">
									<table border="0" width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td class="detailHead">
											<bean:message key="prompt.search" />
									<bean:message key="prompt.nonstandard" />
									<bean:message key="prompt.supervision" />
									<bean:message key="prompt.conditions" />
											</td>
											<td align="right"><img src="/<msp:webapp/>images/step_4.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.conditionName" /></td>
								<td class="formDe"><html:text property="conditionName" maxlength="50" size="50"/></td>								
							</tr>
							<tr>
								<td class="formDeLabel" nowrap>Literal</td>
								<td class="formDe"><html:text property="conditionLiteral" maxlength="100" size="60"/></td>								
							</tr>						   	
							<tr>
								<td class="formDeLabel"><bean:message key="<%=group1Caption%>"/></td>
								<td class="formDe">
									<html:select property="group1Id" size="1" onchange="updateGroup2(this.form);">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection property="groups" value="groupId" label="name" /> 
									</html:select>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="<%=group2Caption%>"/></td>
								<td class="formDe">
									<html:select property="group2Id" disabled="true" onchange="updateGroup3(this.form);">
										<html:option value=""><bean:message key="select.generic" /></html:option>
									</html:select>
									<!-- hidden value for setting selectedIndex after submit -->
									<input type="hidden" name="selectedGrp2Id" value=<bean:write name="suggestedOrderForm" property="group2Id" /> >									
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="<%=group3Caption%>"/></td>
								<td class="formDe">
									<html:select property="group3Id" disabled="true">
										<html:option value=""><bean:message key="select.generic" /></html:option>
									</html:select>
									<!-- hidden value for setting selectedIndex after submit -->
									<input type="hidden" name="selectedGrp3Id" value=<bean:write name="suggestedOrderForm" property="group3Id" /> >
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.jurisdiction" /></td>
								<td class="formDe">
									<html:select property="jurisdictionId" size="1">
										<html:option value=""><bean:message key="select.all" /></html:option>
										<html:optionsCollection name="codeHelper" property="jurisdictions" value="code" label="description" /> 
									</html:select>
								</td>								
							</tr>		   								   				   		
							<tr>
								<td class="formDeLabel" width="1%" nowrap></td>
								<td class="formDe">
								<%--	<html:submit property="submitAction" onclick="return (validateSuggestedOrderPage1(this.form) && disableSubmit(this, this.form));"><bean:message key="button.submit"></bean:message></html:submit>--%>
							<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.submit"></bean:message></html:submit> <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.refresh"></bean:message></html:submit></td>
							</tr>											
					<!-- END SEARCH SUPERVISION CONDITIONS SECTION -- remove two fields for jurisdiction-->
							<tr><td><img src="/<msp:webapp/>images/spacer.gif"></td></tr>
					<!-- BEGIN SEARCH SUPERVISION CONDITIONS RESULTS SECTION -->
						<logic:notEmpty name="suggestedOrderForm" property="conditionResultList">	
						<bean:size id="conditionSearchResultSize" name="suggestedOrderForm" property="conditionResultList" />						
							<tr>
								<td  colspan="2">
									<table width="100%" cellpadding="2" cellspacing="0">
										<tr>
											<td class="detailHead"><bean:message key="prompt.nonstandard" />&nbsp;<bean:message key="prompt.condition" />- <bean:size id="searchSize" name="suggestedOrderForm" property="conditionResultList"/><%=searchSize%>&nbsp;<bean:message key="prompt.searchResults" /></td>
										</tr>
									</table>
									<script type="text/javascript">
									renderScrollingArea(<bean:write name="conditionSearchResultSize" />);									
									</script>
									<table width="100%" cellpadding="2" cellspacing="1">
										<thead>									
										<tr class="formDeLabel">
										<bean:size id="conditionSize" name="suggestedOrderForm" property="conditionResultList" />								
											<td align="center">
											<logic:lessEqual name="conditionSize" value="1000">
												<input type="checkbox" name="checkAllConditions" onclick="checkAllByName(this, 'conditionResultListIndex','conditionId', <bean:write name="conditionSize" />)" title="Check/Uncheck All">				 
					 						</logic:lessEqual>
											</td>
											<td class="formDeLabel"><bean:message key="prompt.conditionName" /></td>
											<td class="formDeLabel"><bean:message key="<%=group1Caption%>"/></td>
											<td class="formDeLabel"><bean:message key="<%=group2Caption%>"/></td>
											<td class="formDeLabel"><bean:message key="<%=group3Caption%>"/></td>
										</tr>
										</thead>
										<tbody>																																								
											<%int RecordCounter = 0;
											String bgcolor = "";%>  
										<logic:iterate id="conditionResultListIndex" name="suggestedOrderForm" property="conditionResultList">			
										<tr
											class=<%RecordCounter++;
											bgcolor = "alternateRow";
											if (RecordCounter % 2 == 1)
												bgcolor = "normalRow";
											out.print(bgcolor);%>>
											<td align="center">
											<logic:lessEqual name="conditionSize" value="1000">
												<html:checkbox name="conditionResultListIndex" property="conditionId" value="conditionId" onclick="uncheckCheckAll(this,'checkAllConditions')" indexed="true"></html:checkbox>
											</logic:lessEqual>											
											<logic:greaterEqual name="conditionSize" value="1001">
												<html:checkbox name="conditionResultListIndex" property="conditionId" value="conditionId" indexed="true"></html:checkbox>
											</logic:greaterEqual>
											</td>																					
											<td><bean:write name="conditionResultListIndex" property="conditionName" />
											<%--<td><a target=_new href="/<msp:webapp/>displayConditionLiteral.do?conditionLiteral=<bean:write name="conditionResultListIndex" property="conditionDetail"/>"><bean:write name="conditionResultListIndex" property="conditionName" /></a></td>--%>						
											<span class="paddedFourPix"><a id="viewSample<%=RecordCounter%>" href="javascript:switchText('viewSample<%=RecordCounter%>', 'sample<%=RecordCounter%>');" >View Sample</a></span>
												</td>	
											<td><bean:write name="conditionResultListIndex" property="conditionType" /></td>
											<td><bean:write name="conditionResultListIndex" property="conditionSubType" /></td>
											<td><bean:write name="conditionResultListIndex" property="conditionSubTypeDetail" /></td>											
										</tr>
										<tr id="sample<%=RecordCounter%>" class="hidden">
													<td></td>
													<td colspan="3"><bean:write name="conditionResultListIndex" property="conditionLiteralPreview" filter="false"/></td>
												</tr>
										</logic:iterate>	
										</tbody>									
									</table>
									</div>
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">									  	
									  	<tr>
									    	<td align="center">
									    		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.addSelected"></bean:message></html:submit></td>							
									  	</tr>
									</table>								
									<!-- END BUTTON TABLE -->
								</td>
							</tr>										
						</logic:notEmpty>
					<!-- END SEARCH SUPERVISION CONDITIONS RESULTS SECTION -->
								
					<!-- BEGIN SELECTED CONDITIONS LIST SECTION -->					
						<logic:equal name="suggestedOrderForm" property="hasNonStandardConditions" value="true">	
						<bean:size id="conditionSelectedSize" name="suggestedOrderForm" property="conditionSelectedList" />							
							<tr>
								<td  colspan="2">
													<a name="#selectedList"></a>
									<table width="100%" cellpadding="2" cellspacing="0">
										<tr>
											<td class="detailHead"><bean:message key="prompt.nonstandard" />&nbsp;<bean:message key="prompt.conditionsSelectedList" /></td>
										</tr>
									</table>																	
									<script type="text/javascript">
									renderScrollingArea(<bean:write name="suggestedOrderForm" property="nonStandardCount" />);									
									</script>
									<table width="100%" cellpadding="2" cellspacing="1">
									<thead>
										<tr class="formDeLabel">				
											<td><img src="/<msp:webapp/>images/spacer.gif" width="50" height="1"></td>											
											<td class="formDeLabel"><bean:message key="prompt.conditionName" /></td>											
											<td class="formDeLabel"><bean:message key="<%=group1Caption%>"/></td>
											<td class="formDeLabel"><bean:message key="<%=group2Caption%>"/></td>
											<td class="formDeLabel"><bean:message key="<%=group3Caption%>"/></td>
										</tr>
										</thead>
										<%int RecordCounter2 = 0;
										String bgcolor = "";%>
										<tbody>
										<logic:iterate id="conditionSelectedListIndex" name="suggestedOrderForm" property="conditionSelectedList">
											<logic:equal name="conditionSelectedListIndex" property="standardId" value="NSO">						
												<logic:notEqual name="conditionSelectedListIndex" property="statusId" value="I" >
													<logic:notEqual name="conditionSelectedListIndex" property="statusId" value="INVALID" >
														<tr
															class=<%RecordCounter2++;
															bgcolor = "alternateRow";
															if (RecordCounter2 % 2 == 1)
																bgcolor = "normalRow";
															out.print(bgcolor);%>>
															<td><a name="selectListAnchor<%=RecordCounter2%>"></a><a href="/<msp:webapp/>removeConditionsFromListSo.do?conditionId=<bean:write name="conditionSelectedListIndex" property="conditionId"/>#selectListAnchor<%=RecordCounter2-1%>">
																<bean:message key="prompt.remove" /></a></td>
															<td><bean:write name="conditionSelectedListIndex" property="conditionName" />
															<span class="paddedFourPix"><a id=viewSampleSelected<%=RecordCounter2%> href="javascript:switchText('viewSampleSelected<%=RecordCounter2%>', 'sampleSelected<%=RecordCounter2%>');" >View Sample</a></span>
													
															</td>
															<td><bean:write name="conditionSelectedListIndex" property="conditionType" /></td>
															<td><bean:write name="conditionSelectedListIndex" property="conditionSubType" /></td>
															<td><bean:write name="conditionSelectedListIndex" property="conditionSubTypeDetail" /></td>				
														</tr>
														<tr id="sampleSelected<%=RecordCounter2%>" class="hidden">
													<td></td>
													<td colspan="3"><bean:write name="conditionSelectedListIndex" property="conditionLiteralPreview" filter="false"/></td>
												</tr>			
													</logic:notEqual>
												</logic:notEqual>
												<logic:equal name="conditionSelectedListIndex" property="statusId" value="I" >												
													<tr class="inactiveCondition" title="Inactive Condition">
														<td><a href="/<msp:webapp/>removeConditionsFromListSo.do?conditionId=<bean:write name="conditionSelectedListIndex" property="conditionId"/>">
															<bean:message key="prompt.remove" /></a></td>
														<td><bean:write name="conditionSelectedListIndex" property="conditionName" />&nbsp;&nbsp;
														<span class="paddedFourPix"><a id="viewSampleSelectedInactive<bean:write name="conditionSelectedListIndex" property="conditionId"/>" href="javascript:switchText('viewSampleSelectedInactive<bean:write name="conditionSelectedListIndex" property="conditionId"/>', 'sampleSelectedInvalid<bean:write name="conditionSelectedListIndex" property="conditionId"/>');" >View Sample</a></span>
													
														</td>
														<td><bean:write name="conditionSelectedListIndex" property="conditionType" /></td>
														<td><bean:write name="conditionSelectedListIndex" property="conditionSubType" /></td>
														<td><bean:write name="conditionSelectedListIndex" property="conditionSubTypeDetail" /></td>				
													</tr>
													<tr id="sampleSelectedInactive<bean:write name="conditionSelectedListIndex" property="conditionId"/>" class="hidden">
													<td></td>
													<td colspan="3"><bean:write name="conditionSelectedListIndex" property="conditionLiteralPreview" filter="false"/></td>
												</tr>		
												</logic:equal>														
												<logic:equal name="conditionSelectedListIndex" property="statusId" value="INVALID" >
													<tr class="wrongCourtCondition" title="Condition No Longer Applies to This Court">
														<td><a href="/<msp:webapp/>removeConditionsFromListSo.do?conditionId=<bean:write name="conditionSelectedListIndex" property="conditionId"/>">
															<bean:message key="prompt.remove" /></a></td>
														<td><bean:write name="conditionSelectedListIndex" property="conditionName" />&nbsp;&nbsp;
														<span class="paddedFourPix"><a id="viewSampleSelectedInvalid<bean:write name="conditionSelectedListIndex" property="conditionId"/>" href="javascript:switchText('viewSampleSelectedInvalid<bean:write name="conditionSelectedListIndex" property="conditionId"/>', 'sampleSelectedInvalid<bean:write name="conditionSelectedListIndex" property="conditionId"/>');" >View Sample</a></span>
													
														</td>
														<td><bean:write name="conditionSelectedListIndex" property="conditionType" /></td>
														<td><bean:write name="conditionSelectedListIndex" property="conditionSubType" /></td>
														<td><bean:write name="conditionSelectedListIndex" property="conditionSubTypeDetail" /></td>
													</tr>
													<tr id="sampleSelectedInvalid<bean:write name="conditionSelectedListIndex" property="conditionId"/>" class="hidden">
													<td></td>
													<td colspan="3"><bean:write name="conditionSelectedListIndex" property="conditionLiteralPreview" filter="false"/></td>
												</tr>		
												</logic:equal>							
											</logic:equal>							
										</logic:iterate>	
										</tbody>												
									</table>									
								</div>
								<table width="98%" cellpadding="0" cellspacing="0">
	                    			<tr>
	                      				<td class="legendSmallText">Red <span class="inactiveCondition">conditions</span> signify that the condition is inactive.</td>
	                    			</tr>
	                    			<tr>
	                      				<td class="legendSmallText">Yellow <span class="wrongCourtCondition">conditions</span> signify that the condition no longer applies to this suggested order's court(s).</td>
	                    			</tr>
	                  			</table>																				
							</td>
						</tr>					
					<!-- END SELECTED CONDITIONS LIST SECTION -->			
						</logic:equal>
						<tr><td  colspan="2">
                      	
					<!-- BEGIN BUTTON TABLE -->
							<table align="center" width="98%">				
								<tr>
									<td  align="center">									
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
										<logic:notEmpty name="suggestedOrderForm" property="conditionSelectedList">	
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.next"></bean:message></html:submit>&nbsp;
										</logic:notEmpty>	
											<input type="button" onclick="resetThisForm(this.form)" value="<bean:message key="button.reset"></bean:message>" >&nbsp;
											
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
									</td>
								</tr>					
							</table>						
					<!-- END BUTTON TABLE -->
						</td></tr>
					</table>		
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