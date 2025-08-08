<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/14/2005	 Hien Rodriguez - Create JSP -->
<!-- 05/24/2006  Hien Rodriguez - ER27629 Remove requirement for Group1 and set default search fields. -->
<!-- 08/15/2006  Hien Rodriguez - #34271 (ER33103) Add ResequenceConditions button -->
<!-- 10/02/2006  Hien Rodriguez - ER#35457 Add new field PLEA -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

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
<title><bean:message key="title.heading" /> - processSupervisionOrder/addRemoveConditions.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/groups.js"></script>


<script>
<logic:iterate indexId="groupIterIndex" id="groupIter" name="supervisionOrderForm" property="groups">
	groups[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="groupId" />", "<bean:write name="groupIter" property="name" filter="false" />");
	
	<logic:notEmpty name="groupIter" property="subgroups">
		<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="subgroups">
			var innerGroup = new subgroup("<bean:write name="groupIter2" property="groupId" />", "<bean:write name="groupIter2" property="name" filter="false" />");
			groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>] = innerGroup;
			
			<logic:notEmpty name="groupIter2" property="subgroups">
				<logic:iterate indexId="groupIterIndex3" id="groupIter3" name="groupIter2" property="subgroups">
					var innerGroup = new subgroup("<bean:write name="groupIter3" property="groupId" />", "<bean:write name="groupIter3" property="name" filter="false" />");
					groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>].subgroups[<bean:write name="groupIterIndex3"/>] = innerGroup;
				
				</logic:iterate>
			</logic:notEmpty>
		</logic:iterate>
	</logic:notEmpty>
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
												
</script>

</head>

<bean:define id="group1Caption" name="supervisionOrderForm" property="group1Caption" type="java.lang.String"/>
<bean:define id="group2Caption" name="supervisionOrderForm" property="group2Caption" type="java.lang.String"/>
<bean:define id="group3Caption" name="supervisionOrderForm" property="group3Caption" type="java.lang.String"/>
<bean:define id="literalCaption" name="supervisionOrderForm" property="conditionLiteralCaption" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionOrderAddRemoveConditions" target="content">
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
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						
						<!-- BEGIN HEADING TABLE -->
							<table width="98%">
								<tr>
									<td align="center" class="header">
										<logic:equal name="supervisionOrderForm" property="action" value="create">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|9">
											<bean:message key="prompt.create" />
										</logic:equal>									
										<logic:equal name="supervisionOrderForm" property="action" value="update">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|18">
											<bean:message key="prompt.update" />
										</logic:equal>												
										<bean:message key="title.supervisionOrder" />&nbsp;-&nbsp;<bean:message key="title.addRemoveConditions" />
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
										<li>Search for Conditions to Add to the order.</li>
										<li>If the condition is not found, click Create Special Condition button.</li>
									</ul></td>
								</tr>																				
							</table>
						<!-- END INSTRUCTION TABLE -->				
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
						<!-- BEGIN SEARCH SUPERVISION CONDITIONS SECTION -->                    
							<table width="98%" border="0" cellspacing="0" cellpadding="0" class="borderTableBlue">
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="0">
											<tr class="detailHead">
												<td class="detailHead"><bean:message key="title.addRemoveConditions" />&nbsp;-&nbsp;<bean:message key="prompt.searchSupervisionConditions" /></td>				                        	
												<td align="right"><img src="/<msp:webapp/>images/step_2.gif"></td>				                          
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table border="0" align="center" cellpadding="4" cellspacing="1" width="100%">											
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.standard" /></td>
												<td class="formDe">
													<html:select property="standardSearchCriteria" size="1">
													<html:optionsCollection property="standardOptions" value="code" label="description" /> 
												</html:select>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.conditionName" /></td>
												<td class="formDe"><html:text property="conditionName" maxlength="35" size="35"/></td>								
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.conditionLiteral" /></td>
												<td class="formDe"><html:text property="conditionLiteral" maxlength="1000" size="60"/></td>								
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="<%=group1Caption%>"/></td>
												<td class="formDe">
													<html:select property="group1Id" size="1" onchange="updateGroup2(this.form);">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection property="groups" value="groupId" label="name" /> 
													</html:select>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="<%=group2Caption%>"/></td>
												<td class="formDe">
													<html:select property="group2Id" disabled="true" onchange="updateGroup3(this.form);">
														<html:option value=""><bean:message key="select.generic" /></html:option>
													</html:select>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="<%=group3Caption%>"/></td>
												<td class="formDe">
													<html:select property="group3Id" disabled="true">
														<html:option value=""><bean:message key="select.generic" /></html:option>
													</html:select>
												</td>
											</tr>  								   				   		
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"></td>
												<td class="formDe"><html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.submit"></bean:message></html:submit></td>			
											</tr>
										</table>
									</td>							                 
								</tr>
							</table>											
						<!-- END SEARCH SUPERVISION CONDITIONS SECTION -->
							<br>
						<!-- BEGIN SEARCH SUPERVISION CONDITIONS RESULTS SECTION -->
						<logic:equal name="supervisionOrderForm" property="searchSuperCondPerformed" value="true">
							<logic:notEmpty name="supervisionOrderForm" property="conditionResultList">
							<table width="98%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="0">
											<tr>
												<td class="detailHead"><bean:message key="prompt.conditionsSearchResults" /></td>
											</tr>
										</table>
										
										<div class="scrollingDiv100">
										<table width="100%" cellpadding="2" cellspacing=1>
											<tr class="formDeLabel">
											<bean:size id="conditionSize" name="supervisionOrderForm" property="conditionResultList" />								
												<td align="center"><input type=checkbox name=checkAllConditions onclick="checkAllByName(this, 'conditionResultListIndex','conditionId', <bean:write name="conditionSize" />)" title="Check/Uncheck All"></td>
												<td class="formDeLabel"><bean:message key="prompt.conditionName" /></td>
												<td class="formDeLabel"><bean:message key="<%=group1Caption%>"/></td>
												<td class="formDeLabel"><bean:message key="<%=group2Caption%>"/></td>
												<td class="formDeLabel"><bean:message key="<%=group3Caption%>"/></td>
											</tr>																																								
												<%int RecordCounter = 0;
												String bgcolor = "";%>  
											<logic:iterate id="conditionResultListIndex" name="supervisionOrderForm" property="conditionResultList">			
												<tr
													class=<%RecordCounter++;
													bgcolor = "alternateRow";
													if (RecordCounter % 2 == 1)
														bgcolor = "normalRow";
													out.print(bgcolor);%>>
													<td align="center"><html:checkbox name="conditionResultListIndex" property="conditionId" value="conditionId" onclick="uncheckCheckAll(this,'checkAllConditions')" indexed="true"></html:checkbox></td>																					
													<td><%--<a href="javascript:openWindow('displaySupervisionOrderPreviewNSample.do?submitAction=View Sample&conditionId=<bean:write name="conditionResultListIndex" property="conditionId" />')">--%>
													<bean:write name="conditionResultListIndex" property="name" /><%--</a>--%>
													<span class="paddedFourPix"><a id=viewSample<%=RecordCounter%> href="javascript:switchText('viewSample<%=RecordCounter%>', 'sample<%=RecordCounter%>');" >View Sample</a></span>
													</td>

													<td><bean:write name="conditionResultListIndex" property="group1Name" /></td>
													<td><bean:write name="conditionResultListIndex" property="group2Name" /></td>
													<td><bean:write name="conditionResultListIndex" property="group3Name" /></td>											
												</tr>
												<tr id="sample<%=RecordCounter%>" class="hidden">
													<td></td>
													<td colspan=3><bean:write name="conditionResultListIndex" property="conditionLiteralPreview"  filter="false"/></td>
												</tr>
											</logic:iterate>										
										</table>
										</div>
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">									  	
											<tr>
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.addSelected"></bean:message></html:submit>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.saveCreateSpecialCondition"></bean:message></html:submit></td>							
											</tr>
										</table>								
										<!-- END BUTTON TABLE -->
									</td>
								</tr>
							</table>								
							</logic:notEmpty>
							<logic:empty name="supervisionOrderForm" property="conditionResultList">
								<table border="0" width="100%">									  	
									<tr>
										<td align="center"><html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.saveCreateSpecialCondition"></bean:message></html:submit></td>							
									</tr>
								</table>	
							</logic:empty>
						</logic:equal>	
						<!-- END SEARCH SUPERVISION CONDITIONS RESULTS SECTION -->
						
						<!-- BEGIN SELECTED CONDITIONS LIST SECTION -->						
							<logic:notEmpty name="supervisionOrderForm" property="conditionSelectedList">						
							<table width="98%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
									
										<table width="100%" cellpadding="2" cellspacing="0">
											<tr>
												<td class="detailHead"><bean:message key="prompt.conditionsSelectedList" /></td>
											</tr>
										</table>
										<div class="scrollingDiv100">
										<table width="100%" cellpadding="2" cellspacing=1>
											<tr class="formDeLabel">				
												<td><img src="/<msp:webapp/>images/spacer.gif" width=50 height=1></td>											
												<td class="formDeLabel"><bean:message key="prompt.conditionName" /></td>
												<td class="formDeLabel"><bean:message key="<%=group1Caption%>"/></td>
												<td class="formDeLabel"><bean:message key="<%=group2Caption%>"/></td>
												<td class="formDeLabel"><bean:message key="<%=group3Caption%>"/></td>
											</tr>
											<%int RecordCounter2 = 0;
											String bgcolor = "";%>
											
											<logic:iterate id="conditionSelectedListIndex" name="supervisionOrderForm" property="conditionSelectedList">
												<tr
													class=<%RecordCounter2++;
													bgcolor = "alternateRow";
													if (RecordCounter2 % 2 == 1)
														bgcolor = "normalRow";
													out.print(bgcolor);%>>
													<td width="1%"><a name="selectListAnchor<%=RecordCounter2%>" href="/<msp:webapp/>removeConditionsFromList.do?conditionId=<bean:write name="conditionSelectedListIndex" property="conditionId"/>#selectListAnchor<%=RecordCounter2%>">
														<bean:message key="prompt.remove" /></a>
														
														
														</td>
													<%String classVal=""; %>
													<logic:equal name="conditionSelectedListIndex" property="nonCourtApplicable" value="true" >
													  <%classVal=" class=wrongCourtCondition title='Condition No Longer Applies to This Court'"; %>
													</logic:equal>	
													<logic:equal name="conditionSelectedListIndex" property="status" value="I" >
													  <%classVal=" class=inactiveCondition title='Inactive Condition'"; %>
													</logic:equal>
													
													<td <%=classVal %>>
													<bean:write name="conditionSelectedListIndex" property="name" /><%--</a>--%>
													<span class="paddedFourPix"><a id=viewSampleSelected<%=RecordCounter2%> href="javascript:switchText('viewSampleSelected<%=RecordCounter2%>', 'sampleSelected<%=RecordCounter2%>');" >View Sample</a></span>
													</td>
													<td <%=classVal %>><bean:write name="conditionSelectedListIndex" property="group1Name" /></td>
													<td <%=classVal %>><bean:write name="conditionSelectedListIndex" property="group2Name" /></td>
													<td <%=classVal %>><bean:write name="conditionSelectedListIndex" property="group3Name" /></td>		
												</tr>
												<tr id="sampleSelected<%=RecordCounter2%>" class="hidden">
													<td></td>
													<td colspan=3><bean:write name="conditionSelectedListIndex" property="conditionLiteralPreview"  filter="false"/></td>
												</tr>							
											</logic:iterate>													
										</table>									
										</div>																				
									</td>
								</tr>
							</table>
							</logic:notEmpty>		
											
						<!-- END SELECTED CONDITIONS LIST SECTION -->			
							<br>                   
						<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">		
							<logic:notEmpty name="supervisionOrderForm" property="conditionSelectedList">										
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.saveContinue"></bean:message></html:submit>&nbsp;
										<html:button property="Preview Order" onclick="openWindow('displaySupervisionOrderPreviewNSample.do?submitAction=Preview Order&resequencedOrderValue=')"><bean:message key="button.previewOrder"/></html:button>&nbsp; 
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.resequenceConditions"></bean:message></html:submit>&nbsp;
									</td>
								</tr>
							</logic:notEmpty>
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
										<!--<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;--> 
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