<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/14/2005	 Hien Rodriguez - Create JSP -->
<!-- 10/02/2006  Hien Rodriguez - ER#35457 Add new field PLEA -->
<!-- 11/15/2006  Hien Rodriguez - Defect#37001 Hide SummaryOfChanges field for Original order with order status not active  -->
<!-- 11/27/2006  Hien Rodriguez - Defect#36791 Add Date Fields Instruction -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 11/06/2008	 Ryoung         - ER#55319 Add summary notes. -->
<!-- 09/23/2009	 L Deen			- #62100 Remove Preview order button -->
<!-- 09/24/2009  C Shimek       - #61656 reopened, revised page title prefix to Create/Update -->
<!-- 10/07/2009  RYoung         - #62316 PASO lite - Conditions not using the same version of tinymce -->
<!-- 11/11/2009  C Shimek       - #62440 Revised title from Historical to Pretrial Intervention -->
<!-- 08/23/2010  D Williamson   - #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->
 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/condition.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>

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
<title><bean:message key="title.heading" /> - processSupervisionOrder/addRemoveConditionsLight.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
<script type="text/javascript">
function modSumOfChanges(theForm)
{
    <logic:equal name="supervisionOrderForm" property="action" value="update">
    if(('<bean:write name="supervisionOrderForm" property="versionTypeId"/>' == 'O') && ('<bean:write name="supervisionOrderForm" property="orderStatusId"/>' != 'A'))
	{
		show('modsumofchange',0);
	} 
	else{
		show('modsumofchange',1);
	}
    </logic:equal>
}
function saveSumChangesField(){
	<logic:equal name="supervisionOrderForm" property="action" value="update">
		document.forms[0].summaryOfChanges.value=trimAll(document.forms[0].summaryOfChanges.value);
	</logic:equal>
	return true;
}
</script>

</head>

<body topmargin="0" leftmargin="0"   onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleSupervisionOrderLight" target="content">
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
										<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|13">    <!-- create -->	
							<%-- 	  <input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|17"> --%> <!-- update -->										
										<bean:message key="prompt.create" />/<bean:message key="prompt.update" />
										<bean:message key="prompt.pretrialInterventionOrder" />&nbsp;-&nbsp;<bean:message key="title.addRemoveConditions" />
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
										<li>Select Resequence Conditions or select Conditions to Remove. Click Prepare to File when done or click Add More Conditions if this order has more conditions. </li>
									</ul></td>
								</tr>
								<tr>
									<td class="required"><bean:message key="prompt.dateFieldsInstruction"/></td>												
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
						<!--	<table width=98% border="0">
								<tr>
									<td class="required"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>												
								</tr>
							</table>   	-->
						<!-- BEGIN CONDITIONS SET DETAILS SECTION -->
							<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
								<tr class="detailHead">
									<td colspan="2">
										<table width="100%" cellpadding="2" cellspacing="0">
											<tr>
												<td class="detailHead">Supervision Conditions</td>
												<td align="right"><img src="/<msp:webapp/>images/step_2.gif"></td>
											</tr>
										</table>
									</td>
								</tr>
								
								<tr>
									<td colspan="2">
										<table  align="center" cellpadding="2" cellspacing="1" width="100%">	
										<nest:iterate id="conditionSelIndex" name="supervisionOrderForm" property="conditionSelectedList">
							
												
											<tr>
																<td width="1%"><nest:checkbox property="deleted" value="true"/></td>
																<td class="boldText" align="center" width="1%"><nest:write property="sequenceNum" /></td>
																<td width="100%"><nest:write property="resolvedDescription" filter="false"/></td>
															</tr>
											</nest:iterate>														
															
										</table>
									</td>
								</tr>
<%--								
	                      
							
--%>                              			
							</table>	
						<!-- END CONDITIONS SET DETAILS SECTION -->
							<br>
					 <!-- BEGIN SUMMARY OF CHANGES SECTION -->
							<logic:equal name="supervisionOrderForm" property="action" value="update">
							<logic:notEmpty name="supervisionOrderForm" property="casenotes" >
								<table width="98%" border="0" cellspacing="0" cellpadding="0" class="borderTableBlue">
									<tr class="detailHead">
										<td>
											<table width="100%" cellpadding="2" cellspacing="0">
												<tr>
													<td class="detailHead"><bean:message key="prompt.modificationReason"/> for Casenote</td>
													<td align="right"><img src="/<msp:webapp/>images/step_3.gif"></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table width="100%" cellpadding="4">
												<tr>
													<td class="formDe"><bean:write name="supervisionOrderForm" property="casenotes" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<br>
								</logic:notEmpty>
							</logic:equal>
						<!-- END SUMMARY OF CHANGES SECTION -->
						<br>
			  
						<!-- BEGIN BUTTON TABLE -->
							<table align="center" width="98%">
								<tr>
									<td align="center">
										<input type="hidden" value="true" name="conditionBoxes"/>
										<html:submit property="submitAction" onclick="return (disableSubmit(this, this.form))"><bean:message key="button.removeSelected"></bean:message></html:submit>&nbsp; 
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.addMoreConditions"></bean:message></html:submit>&nbsp;
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.resequenceConditions"></bean:message></html:submit>&nbsp;
									</td>
								</tr>	
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.prepareToFile"/></html:submit>&nbsp;
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
                      			