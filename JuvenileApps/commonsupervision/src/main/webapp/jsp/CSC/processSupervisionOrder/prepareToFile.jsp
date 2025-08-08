<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/12/2005	 Hien Rodriguez - Create JSP -->
<!-- 08/25/2006	 Hien Rodriguez - ER#34507 Implement new UI Guidelines for date field -->
<!-- 10/02/2006  Hien Rodriguez - ER#35457 Add new field PLEA -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 11/11/2009  C Shimek       - #62440 Revised title from Historical to Pretrial Intervention -->
<!-- 12/30/2009  C Shimek       - #63282 Revised singed date to be optional for print signature -->
<!-- 01/11/2010  C Shimek       - #63441 Revised to display Defendant Signature input for Prepare To File page state -->
<!-- 02/08/2010  C Shimek       - #63441 Revised Defendant Signature to not require date when Refused to Sign is selected -->
<!-- 11/05/2010  D Gibler       - #67855 PASO-Additional Signature for CLO/CSO and Judge -->
<!-- 02/08/2011  C Shimek       - #67856 revised validation for CLO/CSO Signed Date when field is in non-required state -->
<!-- 03/02/2012		TSVines		- #72521 Updated alert message -->

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
<title><bean:message key="title.heading" /> - processSupervisionOrder/prepareToFile.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="supervisionOrderForm5" />

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>

<script type="text/javascript">

	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();

	//this function determinines what to show when the user selects Other in the drop downs
	//params: el - the select
	//	theOtherIDRow - the row to display if the user selects Other
	function checkForOther(el, theOtherIDRow){
		var theForm = el.form;
		if (el.options[el.selectedIndex].value == "OTHER"){
			show(theOtherIDRow, 1, "row");
		}else {
				show(theOtherIDRow, 0);
			}
	}
	
	//hides all fields that could be visible
	function resetVisibles()
	{
		show('judges', 0);
		<logic:equal name="supervisionOrderForm" property="agencyId" value="PTR">
			show('magistrates', 0);
			show('magistrateOtherFields', 0);
			show('magistrateOther', 0);
		</logic:equal>
		show('judgeOther', 0);
		show('judgeOtherFields', 0);
	}
	
	//this function shows the appropriate drop down depending upon the radio button selection Judge vs Magistrate
	//paramas: el -the radio button
	function renderSelect(el){
		var theID = el.value;
		show(theID, 1);
		if (theID == "judges"){
			<logic:equal name="supervisionOrderForm" property="agencyId" value="PTR">
				show("magistrates", 0);
			</logic:equal>
		}else show("judges", 0);
	}
	
	function validateField(theForm)
	{	   		
//		var orderDateElem=theForm.orderFileDateVal;    commented out 2/8/11, not needed 
		var signedDateElem=trimAll(theForm.signedDateAsString.value);
		theForm.signedDateAsString.value = signedDateElem;
		
		// this should only be false for prepare to file
		if (theForm.dateRequired.value == "Y"){
			if (signedDateElem == ""){
				alert("Signed Date is required.");
		     	theForm.signedDateAsString.focus();
		     	return false;
			}
//			if (!isDate(theForm.signedDateAsString.value)){
//				alert("Signed Date is invalid. Valid format is mm/dd/yyyy.");
//				theForm.signedDateAsString.focus();
//		     	return false;
//			}		
		}

		if (signedDateElem != ""){
			if (!isDate(theForm.signedDateAsString.value)){
				alert("Signed Date is invalid. Valid format is mm/dd/yyyy.");
				theForm.signedDateAsString.focus();
	     		return false;
			}	
		}
//		var myDate=new Date(orderDateElem.value);
//		var validOrNot=compareDate1GreaterEqualDate2(signedDateElem.value,orderDateElem.value);
//		if(!validOrNot){
//			alert("Date Signed must be greater than or equal to to the order's file date to be valid");
//			return false;
//		}
		
		// validate Presented By field (plus Struts validation)
		var theSelect = theForm.presentedById;
		if (theSelect.selectedIndex == 0){
	     	alert("Presented By selection is required.");
	     	theForm.presentedById.focus();
	     	return false;
		}
		if (theSelect.options[theSelect.selectedIndex].value=="OTHER"){
 			if (theForm.presentedByFirstName.value == "" ||	theForm.presentedByLastName.value == "")
			{		
		     	alert("Presented By's First Name & Last Name are required, when Other option is selected.");
		     	theForm.presentedByFirstName.focus();
		     	return false;
			}
		}
		// validate Who Signed Order field
		var ct = 0;
		var signedOrderValue=null;
		if(theForm.whoSignedOrder.length){
			for (var i = 0; i < theForm.whoSignedOrder.length; i++) {
				if (theForm.whoSignedOrder[i].checked)
				{	
					signedOrderValue=theForm.whoSignedOrder[i].value;
					ct++;
			   	}
		   	} 
		}
		else{
			signedOrderValue=theForm.whoSignedOrder.value;
		}

		if (signedOrderValue==null || signedOrderValue == ""){
	   		alert("Who Signed Order is required.");
	     	return false;
	   	}

		if (signedOrderValue == "judges"){
			var theSelect = theForm.judgeSelectId;
			if (theSelect.options[theSelect.selectedIndex].value=="")
			{			
	     	alert("Judge is required.");
    	 	theForm.judgeSelectId.focus();
     		return false;
			}    				
			if (theSelect.options[theSelect.selectedIndex].value=="OTHER"){
 				if (theForm.judgeFirstName.value == "" || theForm.judgeLastName.value == "")
				{		
	     			alert("Judge's First Name & Last Name are required, when Other option is selected.");
	     			theForm.judgeFirstName.focus();
	     			return false;
     			}
//			}
			}else if (signedOrderValue == "magistrates"){
    			var theSelect = theForm.magistrateSelectId;
    			if (theSelect.options[theSelect.selectedIndex].value=="")
    			{			
			     	alert("Magistrate is required.");
			     	theForm.magistrateSelectId.focus();
			     	return false;
    			}   
    			if (theSelect.options[theSelect.selectedIndex].value=="OTHER"){
     				if (theForm.magFirstName.value == "" ||		
						theForm.magLastName.value == "")
					{		
				     	alert("Magistrate's First Name & Last Name are required, when Other option is selected.");
				     	theForm.magFirstName.focus();
				     	return false;
			     	}
    			}
   			}
		}	
		
    	// validate Defendant Signature fields
			if (theForm.defendantSignedDateAsString.value != ""){
		   		if (!isDate( theForm.defendantSignedDateAsString.value)) {
 			   		alert("Defendant Signature Signed Date is invalid. Valid format is mm/dd/yyyy");
 			  		theForm.defendantSignedDateAsString.focus();
 			  		return false;
		   		}  	
				if(! compareDate1GreaterEqualDate2(getCurrentDate(), theForm.defendantSignedDateAsString.value)){
					alert("Defendant Signature Signed Date cannot be a future date.");
					theForm.defendantSignedDateAsString.focus();
					return false;
				}
				if (theForm.defendantSignature[1].checked == true){
					alert("Defendant Signature Signed Date cannot be entered when Refused to Sign is selected.");
					theForm.defendantSignedDateAsString.focus();
					return false;
				}	
				theForm.defendantSignature[0].checked = true;  // default to Signed selection on valid date 
				theForm.defendantSignature[1].checked = false;
			}
			if (theForm.defendantSignature[0].checked == true ){
				if (theForm.defendantSignedDateAsString.value == ""){
	 			   	alert("Defendant Signature Signed Date is required when Defendant Signature is selected.");
	 			  	theForm.defendantSignedDateAsString.focus();
	 			  	return false;
			   	}
			}	

		   	// validate Judge Signature fields
			if (theForm.judgeSignedDateAsString.value != ""){
		   		if (!isDate( theForm.judgeSignedDateAsString.value)) {
 			   		alert("Judge Signature Date is invalid. Valid format is mm/dd/yyyy");
 			  		theForm.judgeSignedDateAsString.focus();
 			  		return false;
		   		}  	
				if(! compareDate1GreaterEqualDate2(getCurrentDate(), theForm.judgeSignedDateAsString.value)){
					alert("Judge Signature Signed Date cannot be a future date.");
					theForm.judgeSignedDateAsString.focus();
					return false;
				}
			}

	   	return true;
	}
</script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="checkForOther(document.forms[0].judgeSelectId, 'judgeOther');checkForOther(document.forms[0].presentedById, 'filedByOther');"
>
<html:form action="/displaySupervisionOrderPrepareToFileSummary" target="content" focus="signedDateAsString">

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
									<logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
										<bean:message key="prompt.pretrialInterventionOrder" />
										
									</logic:equal>
									<logic:notEqual name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
										<bean:message key="title.processSupervisionOrder" />
										
									</logic:notEqual>-
									<logic:equal name="supervisionOrderForm" property="action" value="printSignature">
										<bean:message key="button.printSignature" />
										<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|38">
									</logic:equal>
									<logic:notEqual name="supervisionOrderForm" property="action" value="printSignature">
										<bean:message key="prompt.prepareToFile" />
										<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|23">
									</logic:notEqual>	
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
										<li>Enter Required Fields and Click 
										<logic:equal name="supervisionOrderForm" property="action" value="printSignature">
										Save & Continue.										
										</logic:equal>
										<logic:notEqual name="supervisionOrderForm" property="action" value="printSignature">
										Next.										
										</logic:notEqual>
										</li>
									</ul></td>
								</tr>
								<tr>
									<td class="required"><bean:message key="prompt.3.diamond"/>Indicates Required Fields&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>												
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
						<!-- BEGIN CONDITIONS SECTION -->
							<table width="98%" border="0" cellspacing="0" cellpadding="0" class="borderTableBlue">
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="0">
											<tr class="detailHead">
												<td width="1%"><a href="javascript:showHide('conditions', 'row','/<msp:webapp/>')" ><img border="0" src="/<msp:webapp/>images/expand.gif" name="conditions"></a></td>
												<td class="detailHead">&nbsp;<bean:message key="prompt.conditions" /></td>
												<td align="right"><img src="/<msp:webapp/>images/step_2.gif"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="conditionsSpan" class="hidden">
									<td>
										<table border="0" align="center" cellpadding="4" cellspacing="1" width="100%">
											<tr>
												<td>
													<table width="100%" border="0" cellpadding="4" cellspacing="1" class="borderTable">
														<tr>
															<td class="formDeLabel" width="1%"></td>
															<td class="formDeLabel"><bean:message key="prompt.condition" /></td>
														</tr>
														<%int RecordCounter2 = 0;
														String bgcolor = "";%>
														<logic:iterate id="conditionSelectedListIndex" name="supervisionOrderForm" property="conditionSelectedList">
															<logic:equal name="conditionSelectedListIndex" property="compareToPreviousVersion" value="" >															
																<tr
																	class=<%RecordCounter2++;
																	bgcolor = "alternateRow";
																	if (RecordCounter2 % 2 == 1)
																		bgcolor = "normalRow";
																	out.print(bgcolor);%>>
																	<td class="hidden"><bean:write name="conditionSelectedListIndex" property="conditionId" /></td>
																	<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="true">
																		<td class="impactedOrderBold" title="This is a Like Condition and Impacts other order(s)"/>														
																			<bean:write name="conditionSelectedListIndex" property="sequenceNum" />
																		</td>
																	</logic:equal>
																	<%String classVal=""; %>
																	<logic:equal name="conditionSelectedListIndex" property="nonCourtApplicable" value="true" >
													  					<%classVal=" class=wrongCourtCondition title='Condition No Longer Applies to This Court'"; %>
																	</logic:equal>	
																	<logic:equal name="conditionSelectedListIndex" property="status" value="I" >
													  					<%classVal=" class=inactiveCondition title='Inactive Condition'"; %>
																	</logic:equal>
																	<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="false">
																		<td class="boldText"><bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
																	</logic:equal>
																	<td <%=classVal%>>
																		<logic:equal name="conditionSelectedListIndex" property="specialCondition" value="true">
																			<bean:write name="conditionSelectedListIndex" property="specialConditionDesc"  filter="false"/>
																		</logic:equal>
																		<bean:write name="conditionSelectedListIndex" property="resolvedDescription" filter="false" />
																	</td>
																</tr>															
															</logic:equal>
														</logic:iterate>																						
													</table>															
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>	
						<!-- END CONDITIONS SECTION -->
							<br>
						<!-- BEGIN ORDER PRINT DETAIL TABLE -->                      
							<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td class="detailHead"><bean:message key="prompt.prepareToFile" /> <bean:message key="prompt.details" /></td>				                        	
									<td align="right"><img src="/<msp:webapp/>images/step_3.gif"></td>				                          
								</tr>
								<tr>
									<td colspan="2">
										<table width="100%" cellpadding="2" cellspacing="1" border="0">
											<tr>			                          	
												<td class="formDeLabel" nowrap="nowrap">
													<logic:notEqual name="supervisionOrderForm" property="action" value="prepareToFile">
														<bean:message key="prompt.3.diamond"/><bean:message key="prompt.CLOCSO" /> <bean:message key="prompt.signedDate" />
													</logic:notEqual>	
													<logic:equal name="supervisionOrderForm" property="action" value="prepareToFile">
														<bean:message key="prompt.CLOCSO" /> <bean:message key="prompt.signedDate" />
													</logic:equal>	
													
												</td>
												<td class="formDe">
													<input type="hidden" id="orderFileDateVal" value="<bean:write name="supervisionOrderForm" property="orderFileDate" formatKey="date.format.mmddyyyy"/>"/>
													<html:text name="supervisionOrderForm" property="signedDateAsString" maxlength="10" size="10" />
														<A HREF="#" onClick="cal1.select(document.forms[0].signedDateAsString,'anchor1','MM/dd/yyyy'); return false;" NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.3.calendar"/></A>
														<input type="hidden" name="dateRequired" value="N" />
												 		<logic:notEqual name="supervisionOrderForm" property="action" value="prepareToFile">
															<script type="text/javascript">
															//	if (document.forms[0].signedDateAsString.value == "" )
															//	{
															//		document.forms[0].signedDateAsString.value = getCurrentDate();
															//	}
																document.forms[0].dateRequired.value = "Y";
															</script>  
													</logic:notEqual>	
												</td>     		
											</tr>
					 						<tr>
												<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.presentedBy" /></td>
												<td class="formDe">
													<html:select property="presentedById" size="1" onchange="checkForOther(this, 'filedByOther')">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection name="supervisionOrderForm" property="presentedByList" value="supervisionStaffId" label="formattedName" />
														<html:option value="OTHER"><bean:message key="select.OTHER" /></html:option>
													</html:select>
													<span id="filedByOther" class="hidden">
														<table>
															<tr>
																<td class="formDeLabel">+<bean:message key="prompt.firstName" /></td>
																<td class="formDeLabel">+<bean:message key="prompt.lastName" /></td>
															</tr>
															<tr>
																<td><html:text property="presentedByFirstName" maxlength="25" size="25" /></td>
																<td><html:text property="presentedByLastName" maxlength="30" size="30" /></td>
															</tr>
														</table>
													</span>
												</td>
											</tr>
											<tr>			                          	
												<td class="formDeLabel">
											<%--	 commented out 2/8/11 as part of validation changes, field is never required 
													<logic:notEqual name="supervisionOrderForm" property="action" value="prepareToFile">
														<bean:message key="prompt.3.diamond"/><bean:message key="prompt.judge" /> <bean:message key="prompt.signedDate" />
													</logic:notEqual>	
													<logic:equal name="supervisionOrderForm" property="action" value="prepareToFile">   --%>
														<bean:message key="prompt.judge" /> <bean:message key="prompt.signedDate" />
											<%--	</logic:equal>	 --%>
													
												</td>
												<td class="formDe">
													<input type="hidden" id="orderFileDateVal" value="<bean:write name="supervisionOrderForm" property="orderFileDate" formatKey="date.format.mmddyyyy"/>"/>
													<html:text name="supervisionOrderForm" property="judgeSignedDateAsString" maxlength="10" size="10" />
														<A HREF="#" onClick="cal1.select(document.forms[0].judgeSignedDateAsString,'anchor1','MM/dd/yyyy'); return false;" NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.3.calendar"/></A>
											<%--    commented out 2/8/11 as part of validation changes 
													<input type="hidden" name="dateRequired" value="N" />  --%>
												</td>     		
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.whoSignedOrder?" /></td>
												<td class="formDe">
													<table cellpadding="2" cellspacing="0" border="0">
														<tr>
															<logic:equal name="supervisionOrderForm" property="agencyId" value="PTR">
																<td class="formDe" valign="top">
																	<html:radio name="supervisionOrderForm" property="whoSignedOrder" value="judges" onclick="renderSelect(this)"/></td>
																<td valign="top"><bean:message key="prompt.judge" /></td>
															</logic:equal>
															<logic:notEqual name="supervisionOrderForm" property="agencyId" value="PTR">
																<html:hidden name="supervisionOrderForm" property="whoSignedOrder" value="judges"/>
																<td><bean:message key="prompt.judge" /></td>
															</logic:notEqual>
															<td>
																<span id="judges" 
																	<logic:equal name="supervisionOrderForm" property="agencyId" value="PTR">
																		class="hidden"
																	</logic:equal>
																>
																	<table cellpadding="0" cellspacing="0" border="0">
																		<tr>
																			<td colspan="2">
																				<html:select property="judgeSelectId" size="1" onchange="checkForOther(this, 'judgeOther')">
																					<html:option value=""><bean:message key="select.generic" /></html:option>
																					<html:optionsCollection name="supervisionOrderForm" property="judgeSelectList" value="courtId" label="formattedName" />
																					<html:option value="OTHER"><bean:message key="select.OTHER" /></html:option>
																				</html:select>
																			</td>	                                         
																		</tr>
																		<tr id="judgeOther" class="hidden">
																			<td>
																				<table>
																					<tr>
																						<td class="formDeLabel">+<bean:message key="prompt.firstName" /></td>
																						<td class="formDeLabel">+<bean:message key="prompt.lastName" /></td>
																					</tr>
																					<tr>
																						<td><html:text property="judgeFirstName" maxlength="25" size="25" /></td>
																						<td><html:text property="judgeLastName" maxlength="30" size="30" /></td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</span>
															</td>
														</tr>
														<logic:equal name="supervisionOrderForm" property="agencyId" value="PTR">
															<tr>
																<td class="formDe" valign="top">
																	<html:radio name="supervisionOrderForm" property="whoSignedOrder" value="magistrates" onclick="renderSelect(this)"/></td>
																<td valign="top"><bean:message key="prompt.magistrate" /></td>
																<td>
																	<span id="magistrates" class="hidden">
																	<table cellpadding="0" cellspacing="2" border="0">
																		<tr>
																			<td colspan="2">
																				<html:select property="magistrateSelectId" size="1" onchange="checkForOther(this, 'magistrateOther')">
																					<html:option value=""><bean:message key="select.generic" /></html:option>
																					<html:optionsCollection name="supervisionOrderForm" property="magistrateSelectList" value="magistrateId" label="formattedName" />
																					<html:option value="OTHER"><bean:message key="select.OTHER" /></html:option>
																				</html:select></td>	                                         
																		</tr>					                                          	
																		<tr id="magistrateOther" class="hidden">
																			<td>
																				<table>
																					<tr>
																						<td class="formDeLabel">+<bean:message key="prompt.firstName" /></td>
																						<td class="formDeLabel">+<bean:message key="prompt.lastName" /></td>
																					</tr>
																					<tr>
																						<td><html:text property="magFirstName" maxlength="25" size="25" /></td>
																						<td><html:text property="magLastName" maxlength="30" size="30" /></td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																	</span>
																</td>
															</tr>
														</logic:equal>
													</table>
												</td>
											</tr>
										</table>  
									</td>
								</tr> 
							</table>  
						<!-- END ORDER PRINT DETAIL TABLE -->
						<!--  BEGIN DEFENDANT SIGNATURE TABLE -->  
				<%-- 		<logic:equal name="supervisionOrderForm" property="action" value="printSignature"> --%>
							<div class="spacer4px"></div>
							<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td class="detailHead"><bean:message key="prompt.defendantSignature" /></td>
								</tr>
								<tr>
									<td colspan="2">
										<table width="100%" cellpadding="2" cellspacing="1" border="0">				                        		
											<tr>				                          	
												<td class="formDeLabel"><bean:message key="prompt.signedDate" /></td>
												<td class="formDe">													
													<html:text name="supervisionOrderForm" property="defendantSignedDateAsString" maxlength="10" size="10" />
													<A HREF="#" onClick="cal1.select(document.forms[0].defendantSignedDateAsString,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.3.calendar"/></A>
											<%-- 		<script>
														if (document.forms[0].defendantSignedDateAsString.value == "" )
														{
															document.forms[0].defendantSignedDateAsString.value = getCurrentDate();
														}
													</script>  --%>
												</td>      		
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.defendantSignature" /></td>
												<td class="formDe">
													<html:checkbox name="supervisionOrderForm" property="defendantSignature" value="Signed" onclick="javascript: document.forms[0].defendantSignature[1].checked = false;"/><bean:message key="prompt.signed" />
													<html:checkbox name="supervisionOrderForm" property="defendantSignature" value="Not Signed" onclick="javascript: document.forms[0].defendantSignature[0].checked = false;"/><bean:message key="prompt.refusedToSign" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>  
				<%-- 		</logic:equal>  --%>
						<!--  END DEFENDANT SIGNATURE TABLE --> 
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">			  		
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp; 
									<html:submit property="submitAction" onclick="return validateField(this.form) && disableSubmit(this, this.form);">										
										<logic:equal name="supervisionOrderForm" property="action" value="printSignature">
											<bean:message key="button.saveAndContinue"></bean:message>
										</logic:equal>					
										<logic:notEqual name="supervisionOrderForm" property="action" value="printSignature">
											<bean:message key="button.next"></bean:message>
										</logic:notEqual>
									</html:submit>&nbsp;
									<html:reset ><bean:message key="button.reset"></bean:message></html:reset>&nbsp; 
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