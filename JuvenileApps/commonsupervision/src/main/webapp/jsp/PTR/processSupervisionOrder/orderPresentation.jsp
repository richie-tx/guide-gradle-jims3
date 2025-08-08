<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/07/2005	 Hien Rodriguez - Create JSP -->
<!-- 08/25/2006	 Hien Rodriguez - ER#34507 Implement new UI Guidelines for date field -->
<!-- 10/02/2006  Hien Rodriguez - ER#35457 Add new field PLEA -->
<!-- 11/15/2006  Hien Rodriguez - Defect#37052 Allow to change PLEA field of Original order 
        with order status not active  -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 07/23/2007	 C Shimek       - #43668 Add Fine Amount input field and edit. -->
<!-- 08/07/2007	 C Shimek       - #44250 Revised Add Fine Amount to allow 0.00 as valid amount. -->
<!-- 08/08/2007	 C Shimek       - #44186 Added Offense Code edits to validateField(). -->
<!-- 08/26/2007	 J JOSE       - Fixing page to work with PTS / separted pages out -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
 
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@page import="naming.UIConstants"%>
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
<title><bean:message key="title.heading" /> - processSupervisionOrder/orderPresentation.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="supervisionOrderForm" />

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script type="text/javascript">
	//check for whether order title is modified sentence
	//params: the orderTitle drop down
	function checkForMod(el){
		if(el==null){
			
		}
		else if(el.type=="hidden")
		{
			if(el.value == "modSentence"){
				show("modServiceRow",1,"row");
			}
		}
		else{
			if(el.options[el.selectedIndex].value == "modSentence"){
				show("modServiceRow",1,"row");
			}
		}
	}

	function showDate(el,theForm){
		if (el!=null && el.checked){
			show("supervisionBeginDate", 1, "row");
			show("supervisionEndDate", 1, "row");
		}else {
				
				show("supervisionBeginDate", 0);
				show("supervisionEndDate", 0);
			}
		if (location.search=="?modOOC"){
			show("hardshipRow", 0);
		}
	}
	
	// If limitedSupervisionPeriod is unchecked, blank both dates out
	function checkSupervisionDates(theForm){
		if (theForm.limitedSupervisonPeriod !=null && !(theForm.limitedSupervisonPeriod.checked)){
	 		theForm.supervisionBeginDateAsString.value = "";
			theForm.supervisionEndDateAsString.value = "";
	 	}
	 	
	 	return true;
	}
	
	function validateField(theForm){
		clearAllValArrays();
		if(theForm.versionTypeId != null)
		{
			if(theForm.versionTypeId.selectedIndex == "")
			{		
	     		alert("Please select Version Type.");
	    		theForm.versionTypeId.focus();
	    		return false;
	   		}
	   	}
		var reDisable=false;
		if(theForm.orderTitleId.disabled){
			reDisable=true;
		}
		theForm.orderTitleId.disabled=false;
		if(theForm.orderTitleId.selectedIndex == 0)
		{		
		
	     	alert("Please select Order Title.");
	    	theForm.orderTitleId.focus();
	    	if(reDisable){
	    		theForm.orderTitleId.disabled=true;
	    	}
	     	return false;
	   	}
		if(reDisable){
    		theForm.orderTitleId.disabled=true;
    	}
	
		if (theForm.limitedSupervisonPeriod!=null){
			if(theForm.limitedSupervisonPeriod.checked){
				addMMDDYYYYDateValidation("supervisionBeginDateAsString","Supervision Begin Date must be in date format ");
		   		addMMDDYYYYDateValidation("supervisionEndDateAsString","Supervision End Date must be in date format ");
			}
			
			if (theForm.limitedSupervisonPeriod.checked &&		
				theForm.supervisionBeginDateAsString.value == "")
			{		
		     	alert("Supervision Begin Date is required when Limited Supervision Period Order is checked.");
		     	theForm.supervisionBeginDateAsString.focus();
		     	return false;
		   	}
		   	if(theForm.limitedSupervisonPeriod.checked &&		
				!(theForm.supervisionEndDateAsString.value == ""))
			{		
		     	var thisBeginDate = new Date(theForm.supervisionBeginDateAsString.value);
				var thisEndDate = new Date(theForm.supervisionEndDateAsString.value);
				if (thisEndDate < thisBeginDate)
				{		
			     	alert("Supervision End Date must be equal or greater than Supervision Begin Date.");
			     	theForm.supervisionEndDateAsString.focus();
			     	return false;
			   	}
		   	}
		   	
		}
		   	
		   
		   	
		   	if(theForm.versionTypeId.value == 'A' || theForm.versionTypeId.value == 'M')
			{
		   	 	addDB2FreeTextValidation('casenotes',"Modification Reason must be alphanumeric with no leading spaces and the following symbols . ' \ & ; ( ) / along with spaces are allowed.",null);
		   	 	customValMaxLength ('casenotes','Modification Reason cannot be more than 3500 characters',3500);
			
			}
		   	

		   
		   	

	   	// notes field is required for update option only and not the Original version 
	   	if (theForm.myAction.value == "update" && theForm.versionTypeId.value != "O")
	   	{		
		  // var casenotesRegex = /^[a-zA-Z0-9_ ,.'-]*$/; 
		  theForm.casenotes.value=trimAll(theForm.casenotes.value);
		    if (trimAll(theForm.casenotes.value) == "")
			{		  
		    	
		     	alert("Modification Reason is required for update option.");
		     	theForm.casenotes.focus();
		     	return false;
		   	}
		    if(!validateCustomStrutsBasedJS(theForm)){
				return false;
			}
		   /*	else
		   	{
		   	    if(!casenotesRegex.test(theForm.casenotes.value)){
		   	    	alert("Invalid Modification Reason.");
		     		theForm.casenotes.focus();
		     		return false;
		   	    }
		   	}*/
	   	}
	   	else{
	   		return validateCustomStrutsBasedJS(theForm);
	   	}
	}
	
	function enableOrderTitles(theForm){
		theForm.orderTitleId.disabled=false;
		return true;
	}
	
	function createModReason(theForm)
	{
	
		if(theForm.versionTypeId.value == 'A' || theForm.versionTypeId.value == 'M')
		{
			//clearAllValArrays();
			show('modtextarea',1,'row');
			show('modprompt',1,'row');	
			//  addDB2FreeTextValidation('casenotes',"Modification Reason must be alphanumeric with no leading spaces and the following symbols . ' \ & ; ( ) / along with spaces are allowed.",null);
		}
		
	}

	//this function renders certain fields based on what the disposition type is
	function checkDisp(theSelect){
		if(theSelect.options[theSelect.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_DEFERRED%>"){
			show("confinementLengthRow", 0);
			show("supervisionLengthRow", 1, "row");
			show("supervisionBeginDate", 1, "row");
			show("supervisionEndDate", 1, "row");
			show("beginDateHeaderSupervision", 1, "row");
			show("endDateHeaderSupervision", 1, "row");
			show("beginDateHeaderPretrial", 0, "row");
			show("endDateHeaderPretrial", 0, "row");
			}else	if(theSelect.options[theSelect.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>"){
				show("confinementLengthRow", 1, "row");
				show("supervisionLengthRow", 1, "row");
				show("supervisionBeginDate", 1, "row");
				show("supervisionEndDate", 1, "row");
				show("beginDateHeaderSupervision", 1, "row");
				show("endDateHeaderSupervision", 1, "row");
				show("beginDateHeaderPretrial", 0, "row");
				show("endDateHeaderPretrial", 0, "row");
			}else	if(theSelect.options[theSelect.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>"){
				show("supervisionBeginDate", 1);
				show("confinementLengthRow", 0);
				show("supervisionLengthRow", 0);
				show("supervisionEndDate", 1);
				show("beginDateHeaderSupervision", 0, "row");
				show("endDateHeaderSupervision", 0, "row");
				show("beginDateHeaderPretrial", 1, "row");
				show("endDateHeaderPretrial", 1, "row");
			}else{
				show("confinementLengthRow", 0);
				show("supervisionLengthRow", 0);
				show("beginDateHeaderSupervision", 0, "row");
				show("endDateHeaderSupervision", 0, "row");
				show("beginDateHeaderPretrial", 0, "row");
				show("endDateHeaderPretrial", 0, "row");
				show("supervisionBeginDate", 0, "row");
				show("supervisionEndDate", 0, "row");
			}
		}
	
</script>
 
</head>

<bean:define id="limitedSupervisonPeriodCaption" name="supervisionOrderForm" property="limitedSupervisonPeriodCaption" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onload="checkForMod(document.forms[0].orderTitleId);showDate(document.forms[0].limitedSupervisonPeriod,document.forms[0]);createModReason(document.forms[0]);checkSupervisionDates(document.forms[0])" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionOrderSelectSugOrder" target="content">
<div align="center">
<input type="hidden" name="myAction" value="<bean:write name="supervisionOrderForm" property="action" />">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign="top">
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
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
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						
						<!-- BEGIN HEADING TABLE -->
							<table width=98%>
								<tr>
									<td align="center" class="header">
										<logic:equal name="supervisionOrderForm" property="action" value="create">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|7">
											<bean:message key="prompt.create" />
										</logic:equal>									
										<logic:equal name="supervisionOrderForm" property="action" value="update">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|16">
											<bean:message key="prompt.update" />
										</logic:equal>												
										<bean:message key="title.supervisionOrder" />&nbsp;-&nbsp;<bean:message key="title.prepareOrderPresentation" />
									</td>
								 </tr>						 						  
							</table>									
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
							<table width=98% align="center">															
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td><ul>
										<li>Enter Required Fields and Click Next.</li>
									</ul></td>
								</tr>
								<tr>
									<td class="required"><bean:message key="prompt.3.diamond"/>&nbsp;&nbsp;&nbsp;<bean:message key="prompt.conditionallyRequiredFields"/>&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>												
								</tr>
							</table>
						<!-- END INSTRUCTION TABLE -->                      
						<!-- BEGIN DETAIL HEADER TABLE -->
																									
							<tiles:insert page="caseOrderHeaderTile.jsp" flush="true"></tiles:insert>
						
						<!-- END DETAIL HEADER TABLE -->
							<br>
						<!-- BEGIN ORDER PRESENTATION TABLE -->                      
							<table width="98%" border="0" cellspacing=0 class=borderTableBlue>
								<tr class=detailHead>
									<td class=detailHead><bean:message key="prompt.orderPresentation" /></td>				                        	
									<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>				                          
								</tr>
								<tr>
									<td colspan=2>
										<table width=100% cellpadding=2 cellspacing=1 border=0>
										<logic:equal name="supervisionOrderForm" property="versionTypeChangeAllowed" value="false"> 				                        		
											<tr>				                          	
												<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.versionType" /></td>
												<td class="formDe"><bean:write name="supervisionOrderForm" property="versionType" /></td>		
											</tr>
											<html:hidden property="versionTypeId"/>
										</logic:equal>
										<logic:equal name="supervisionOrderForm" property="versionTypeChangeAllowed" value="true">
										
											<tr>
												<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.3.diamond"/><bean:message key="prompt.versionType" /></td>
												<td class="formDe">
												
												<html:select property="versionTypeId" size="1" onchange="populateOrdertitles(this.form);createModReason(this.form);">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="supervisionOrderForm" property="versionTypeDropDownList" value="code" label="description" />
												</html:select>
												
												</td>
											</tr>
										</logic:equal>
									
										<logic:equal name="supervisionOrderForm" property="versionTypeChangeAllowed" value="false">
											<logic:equal name="supervisionOrderForm" property="versionTypeId" value="O">
													
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.orderTitle" /></td>
												<td class="formDe">
												<html:select property="orderTitleId" size="1">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="supervisionOrderForm" property="orderTitleList" value="printTemplateId" label="orderTitle" />
												</html:select></td>
										
											</tr>
											</logic:equal>
										</logic:equal>
										<logic:equal name="supervisionOrderForm" property="versionTypeChangeAllowed" value="false">
											<logic:notEqual name="supervisionOrderForm" property="versionTypeId" value="O">
												<html:hidden property="orderTitleId"/>
											</logic:notEqual>
										</logic:equal>
										<logic:equal name="supervisionOrderForm" property="versionTypeChangeAllowed" value="true">
											<html:hidden property="orderTitleId"/>
										</logic:equal>
		<%-- NOT FOR PTS
								<tr>
						        	<td class="formDeLabel" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.offenseCode" /></td>
            						<td class="formDe"><html:text property="offenseId" maxlength="8" size="8" />&nbsp;<logic:notEmpty name="supervisionOrderForm" property="offenseDesc"><bean:write name="supervisionOrderForm" property="offenseDesc" /></logic:notEmpty>&nbsp;&nbsp;<html:submit property="submitAction" onclick="return (disableSubmit(this, this.form));"><bean:message key="button.validate"></bean:message></html:submit>
				  						<a href="javascript:changeFormActionURL(document.forms[0],'/<msp:webapp/>displaySupervisionOrderSelectSugOrder.do?submitAction=<bean:message key="prompt.findOffenseCode"/>',true)"><bean:message key="prompt.findOffenseCode"/></a></td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.dispositionType"/></td>
									<td class="formDe">
										<html:select property="dispositionTypeId" onchange="checkDisp(this)">
											<html:option value=""><bean:message key="select.generic" /></html:option>
											<html:optionsCollection name="supervisionOrderForm" property="dispositionTypes" value="code" label="description" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap><bean:message key="prompt.fineAmount"/></td>
									<td class="formDe"><html:text name="supervisionOrderForm" property="fineAmountTotal" maxlength="9" size="9" /></td>
								</tr>

								<tr class=hidden id=confinementLengthRow>
									<td class="formDeLabel" ><bean:message key="prompt.3.diamond"/><bean:message key="prompt.confinementLength"/> </td>
									<td class="formDe">
										<html:text property="confinementLengthYears" size="2" />
										Years
										<html:text property="confinementLengthMonths" size="2" />
										Months
										<html:text property="confinementLengthDays" size="3" />
										Days</td>
								</tr>
								<tr class=hidden id=supervisionLengthRow>
									<td class="formDeLabel" ><bean:message key="prompt.3.diamond"/><bean:message key="prompt.supervisionLength"/></td>
									<td class="formDe">
										<html:text property="supervisionLengthYears" size="2"/>
										Years
										<html:text property="supervisionLengthMonths" size="2" />
										Months
										<html:text property="supervisionLengthDays" size="3" />
									Days</td>
								</tr>
								<tr class=hidden id="supervisionBeginDate">
									<td class="formDeLabel" nowrap>
										<bean:message key="prompt.3.diamond"/><span id="beginDateHeaderSupervision" class="hidden"><bean:message key="prompt.supervisionBeginDate" />
										</span>
										<span id="beginDateHeaderPretrial" class="hidden"><bean:message key="prompt.pretrialInterventionBegin"/>&nbsp;<bean:message key="prompt.date"/>
										</span>
									</td>
									<td class="formDe">
										<SCRIPT LANGUAGE="JavaScript" ID="js1">
											var cal1 = new CalendarPopup();
											cal1.showYearNavigation();
										</SCRIPT>
										<html:text name="supervisionOrderForm" property="caseSupervisionBeginDateAsString" maxlength="10" size="10" />
											<A HREF="#" onClick="cal1.select(document.forms[0].caseSupervisionBeginDateAsString,'anchor1','MM/dd/yyyy'); return false;" NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.3.calendar"/></A>
										<script>
											if (document.forms[0].caseSupervisionBeginDateAsString.value == "" )
											{
												document.forms[0].caseSupervisionBeginDateAsString.value = getCurrentDate();
											}
										</script>
									</td>                                			
								</tr>
								<tr class=hidden id="supervisionEndDate">
									<td class="formDeLabel">
										<bean:message key="prompt.3.diamond"/><span id="endDateHeaderSupervision" class="hidden"><bean:message key="prompt.supervisionEndDate" />
											</span>
										<span id="endDateHeaderPretrial" class="hidden">
											<bean:message key="prompt.pretrialInterventionEnd"/>&nbsp;<bean:message key="prompt.date"/>
										</span>
									</td>
									<td class="formDe">
										<html:text name="supervisionOrderForm" property="caseSupervisionEndDateAsString" maxlength="10" size="10" />
											<A HREF="#" onClick="cal1.select(document.forms[0].caseSupervisionEndDateAsString,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border=0><bean:message key="prompt.3.calendar"/></A>
									</td>
								</tr>

		--%>
											
										<logic:notEqual name="supervisionOrderForm" property="cdi" value="010">
											<logic:notEqual name="supervisionOrderForm" property="cdi" value="020">	                             
											<tr class=visibleTR id="hardshipRow">
												<td class="formDeLabel" nowrap width="1%">Limited Supervision Period Order</td>
												<td class="formDe">
													<html:checkbox name="supervisionOrderForm" property="limitedSupervisonPeriod" value="true" onclick="showDate(this,this.form)"/></td>
											</tr>
											</logic:notEqual>
										</logic:notEqual>
									
											<input type="hidden" name="limitedSupervisionPeriodParameter" value ="true">
								
											<tr class=hidden id="supervisionBeginDate">
												<td class="formDeLabel"><bean:message key="prompt.plusSign"/><bean:message key="prompt.supervisionBeginDate" /></td>
												<td class="formDe">
													<SCRIPT LANGUAGE="JavaScript" ID="js1">
														var cal1 = new CalendarPopup();
														cal1.showYearNavigation();
													</SCRIPT>
													<html:text name="supervisionOrderForm" property="supervisionBeginDateAsString" maxlength="10" size="10" />
													<A HREF="#" onClick="cal1.select(document.forms[0].supervisionBeginDateAsString,'anchor1','MM/dd/yyyy'); return false;" NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.3.calendar"/></A>
													<script>
														if (document.forms[0].supervisionBeginDateAsString.value == "" )
														{
															document.forms[0].supervisionBeginDateAsString.value = getCurrentDate();
														}
													</script>
												</td>                                			
											</tr>
											<tr class=hidden id="supervisionEndDate">
												<td class="formDeLabel"><bean:message key="prompt.supervisionEndDate" /></td>
												<td class="formDe">
													<html:text name="supervisionOrderForm" property="supervisionEndDateAsString" maxlength="10" size="10" />
													<A HREF="#" onClick="cal1.select(document.forms[0].supervisionEndDateAsString,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border=0><bean:message key="prompt.3.calendar"/></A>
												</td>
											</tr>
									
									<%--	<logic:equal name="supervisionOrderForm" property="action" value="update"> 
											<logic:notEqual name="supervisionOrderForm" property="versionTypeId" value="O"> --%>
								<tr id="modprompt" class=hidden>
									<td class="formDeLabel" colspan=2><bean:message key="prompt.3.diamond"/><bean:message key="prompt.modificationReason" /></td>
								</tr>
								<tr id="modtextarea" class=hidden>
									<td class="formDe" colspan=2>
										<textarea rows=4 style="width:100%" name="casenotes"><bean:write name="supervisionOrderForm" property="casenotes" /></textarea>
									</td>
								</tr>
										<%--	</logic:notEqual>                              
										</logic:equal> --%>
							</table>
						</td>
					</tr>
				</table>
						<!-- END ORDER PRESENTATION TABLE -->
				<br>                     
						<!-- BEGIN BUTTON TABLE -->
				<table border="0" width="100%">											
					<tr>
						<td align="center">
							<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>&nbsp; 
							<html:submit property="submitAction" onclick="return (validateField(this.form) && checkSupervisionDates(this.form) && enableOrderTitles(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"></bean:message></html:submit>&nbsp; 
							<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp; 
							<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
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
<script>populateOrdertitles(document.forms[0]);	
  
	function populateOrdertitles(theForm)
	{
		<logic:equal name="supervisionOrderForm" property="versionTypeChangeAllowed" value="true">
		
		if(theForm.versionTypeId.value == 'M')
		{
			var selectedOrderTitleId="<bean:write name='supervisionOrderForm' property='orderTitleId'/>";
			counter=0;
				<logic:iterate id="iter" name="supervisionOrderForm" property="orderTitleListModifiedVersion">
					if(counter==0)
					{
						var firstOptionId=<bean:write name="iter" property="printTemplateId"/>;
					}
				counter++;
				</logic:iterate>
				if(counter==0)
				{
					firstOptionId="<bean:write name='supervisionOrderForm' property='orderTitleId'/>";
				}
				else
				{
					theForm.orderTitleId.value=firstOptionId;					
				}
				
		}
		else if(theForm.versionTypeId.value == 'A')
		{	
			var selectedOrderTitleId="<bean:write name='supervisionOrderForm' property='orderTitleId'/>";
			counter=0;
		    theForm.orderTitleId.value="";
		}
		
		</logic:equal>
		<logic:equal name="supervisionOrderForm" property="versionTypeChangeAllowed" value="false">
		<logic:equal name="supervisionOrderForm" property="versionTypeId" value="O">
		<logic:notEqual name="supervisionOrderForm" property="orderStatusId" value="A">
		if(theForm.versionTypeId.value == 'O')
		{
			theForm.orderTitleId.options.length = 0;
			var selectedOrderTitleId="<bean:write name='supervisionOrderForm' property='orderTitleId'/>";
			
			theForm.orderTitleId.options[0] = new Option( "Please Select", "");
				theForm.orderTitleId.disabled=false;
				<logic:iterate id="iter" name="supervisionOrderForm" property="orderTitleList">
					var tempOption=new Option("<bean:write name="iter" property="orderTitle"/>", "<bean:write name="iter" property="printTemplateId"/>");
					
					theForm.orderTitleId.options[theForm.orderTitleId.options.length] = tempOption;
					if(selectedOrderTitleId=='<bean:write name="iter" property="printTemplateId"/>'){
						tempOption.selected=true;
					}
				</logic:iterate>
					if(selectedOrderTitleId==''){
						theForm.orderTitleId.options[0].selected=true;
					}
		}
		
		else   
		{
			theForm.orderTitleId.options.length = 0;
			var selectedOrderTitleId="<bean:write name='supervisionOrderForm' property='orderTitleId'/>";
			
			theForm.orderTitleId.options[0] = new Option( "Please Select", "");
				theForm.orderTitleId.disabled=true;
				<logic:iterate id="iter" name="supervisionOrderForm" property="orderTitleList">
					var tempOption=new Option("<bean:write name="iter" property="orderTitle"/>", "<bean:write name="iter" property="printTemplateId"/>");
					
					theForm.orderTitleId.options[theForm.orderTitleId.options.length] = tempOption;
					if(selectedOrderTitleId=='<bean:write name="iter" property="printTemplateId"/>'){
						tempOption.selected=true;
					}
				</logic:iterate>
					if(selectedOrderTitleId==''){
						theForm.orderTitleId.options[0].selected=true;
					}
		
		}
		</logic:notEqual>
		</logic:equal>
		</logic:equal>
	}
</script>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>