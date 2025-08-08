<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/14/2005	 Hien Rodriguez - Create JSP -->
<!-- 05/24/2006  Hien Rodriguez - ER27629 Remove requirement for Group1 and set default search fields. -->
<!-- 08/15/2006  Hien Rodriguez - #34271 (ER33103) Add ResequenceConditions button -->
<!-- 10/02/2006  Hien Rodriguez - ER#35457 Add new field PLEA -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 11/06/2008	 Ryoung         - ER#55319 Add summary notes. -->
<!-- 10/30/2009	 C Shimek       - #62665 revised alignment of conditions in "Added Conditions" block to match PT. -->
<!-- 11/03/2009	 C Shimek       - #62650 revise title ending form Set Details to Add Conditions. -->
<!-- 11/11/2009  C Shimek       - #62440 Revised title from Historical to Pretrial Intervention -->
<!-- 08/23/2010  D Williamson   - #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
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
<title><bean:message key="title.heading" /> - processSupervisionOrder/setConditionDetailsLight.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>

</head>
<script type="text/javascript">

function createModReason()
{
var value = ('<bean:write name="supervisionOrderForm" property="versionTypeId"/>' );
	if ( typeof (value) != "undefined"){

		if ( value == 'A' || value == 'M' )
		{
			show('modsumofchange',1,'row');
		}
	
	 }
}

</script>

<bean:define id="literalCaption" name="supervisionOrderForm" property="conditionLiteralCaption" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onload="createModReason();" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayConditionDetailsLight" target="content">
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
										<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|9">
										<bean:message key="prompt.create" />
									</logic:equal>									
									<logic:equal name="supervisionOrderForm" property="action" value="update">
									    <logic:notEqual name="supervisionOrderForm" property="orderStatus" value="INCOMPLETE">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|18">
										  	<bean:message key="prompt.update" />
									    </logic:notEqual>
										<logic:equal name="supervisionOrderForm" property="orderStatus" value="INCOMPLETE">
										  	<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|9">
										  	<bean:message key="prompt.create" />
										</logic:equal>											
									</logic:equal>												
										<bean:message key="prompt.pretrialInterventionOrder" />&nbsp;-&nbsp;<bean:message key="prompt.add"/>&nbsp;<bean:message key="prompt.conditions"/> 
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
										<li>Enter Conditions. Click Save and Continue when done or click Add More Conditions if this order has more conditions.</li>
									</ul>
								</td>
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
						<logic:notEmpty name="supervisionOrderForm" property="conditionSelectedList">	
							<bean:size id="selectCondSize" name="supervisionOrderForm" property="conditionSelectedList"/>	
							<logic:greaterThan value="0" name="selectCondSize">
								<bean:define id="condExist" value="true"/>
							</logic:greaterThan>		
						</logic:notEmpty>	

						<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr class="detailHead">
								<td class="paddedFourPix">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="1%"><img border="0" src="/<msp:webapp/>images/expand.gif" name="addedConditionsFields" onclick="showHide('addedConditionsFields', 'row','/<msp:webapp/>')" style="cursor:pointer"></td>
											<td class="detailHead">&nbsp;Added Conditions</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="addedConditionsFieldsSpan" class="hidden">
								<td>
									<logic:present name="condExist"> 
										<table width="100%" cellpadding="2" cellspacing="1">
											<logic:iterate id="conditionSelectedListIndex" name="supervisionOrderForm" property="conditionSelectedList" indexId="index1">
												<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
													<td width="1%" nowrap="nowrap">
														<b><bean:write name="conditionSelectedListIndex" property="sequenceNum" />&nbsp;</b>
													</td>
													<td>
														<bean:write name="conditionSelectedListIndex" property="resolvedDescription" filter="false"/>
													</td>
												</tr>
											</logic:iterate>													
										</table>	
									</logic:present>
									<logic:notPresent name="condExist"> 
										No conditions have been added.
									</logic:notPresent>
								</td>							                 
							</tr>
						</table>
						<br>
<!-- BEGIN SUPERVISION CONDITION TABLE -->                      
						<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
							<tr class="detailHead">
								<td class="detailHead" colspan="2">
									<bean:message key="prompt.supervisionCondition" />
								</td>
							</tr>
						 	<% int x = 1; %>
						 	<tr>
						 		<td>
<!-- BEGIN INPUT CONDITIONS TABLE -->		
									<tiles:insert page="../../common/spellCheckButtonTile.jsp" flush="true">
										<tiles:put name="agencyCode" beanName="supervisionOrderForm" beanProperty="agencyId"/>
									</tiles:insert>					 		
						 			<table width="100%" cellpadding="2" cellspacing="1">
						 				<nest:iterate id="conditionEnterIndex" name="supervisionOrderForm" property="conditionResultList">
											<tr>
												<td class="formDeLabel" valign="top" width="1%" nowrap="nowrap"><bean:message key="prompt.condition" /></td>
												<td class="formDe" align="center">
													<style>
														.mceEditor{															
															height: "130"
														}
													</style>
													<nest:textarea  styleClass="mceEditor" style="width:100%" property="description" ondblclick="myReverseTinyMCEFix(this)"/>
												</td>
												</tr>
												<tr>
													<td class="formDeLabel" valign="top"><bean:message key="prompt.notes" /></td>
													<td class="formDe"><nest:textarea rows="2" style="width:100%" property="notes" /></td>
												</tr>
													<script>
														addDefinedTinyMCEFieldMask('<nest:writeNesting property="description"/>','Condition Literal # <%=x%>  contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).',null);
														customValMinLength('<nest:writeNesting property="description"/>','Condition Literal # <%=x%>  must be at least 10 characters',10);
														customValMaxLength('<nest:writeNesting property="description"/>','Condition Literal # <%=x%>  cannot be more than 1000 characters',1000);
													</script>
													<% 	x++;  %>
										</nest:iterate>
									</table>	
<!-- END INPUT CONDITIONS TABLE -->
								</td>
							</tr>
						</table>
<!-- END SUPERVISION CONDITION TABLE -->
<!-- BEGIN SELECTED CONDITIONS LIST SECTION -->						
<!-- END SELECTED CONDITIONS LIST SECTION -->
<!-- BEGIN SUMMARY OF CHANGES SECTION -->
						<span id="modsumofchange" class="hidden">
							<table width="98%" border="0" cellspacing="0" cellpadding="0" class="borderTableBlue">
								<tr class="detailHead">
									<td>
										<table width="100%" cellpadding="2" cellspacing="0">
											<tr>
												<td class="detailHead" ><bean:message key="prompt.modificationReason" /> for Casenote</td>
												<td align="right"><img src="/<msp:webapp/>images/step_3.gif"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="2">
											<tr>
												<td class="formDe"><textarea rows="4" style="width:100%" name="casenotes" onmouseout="textLimit( this, 3500 )" onkeyup="textLimit( this, 3500 )"><bean:write name="supervisionOrderForm" property="casenotes" /></textarea></td>					                                		
													<script>
														  addDB2FreeTextValidation('casenotes',"Modification Reason for Casenote must be alphanumeric with no leading spaces and the following symbols . ' \ & ; ( ) / along with spaces are allowed.",null);
																customValMaxLength ('casenotes','Modification Reason for Casenote cannot be more than 3500 characters',3500);
													</script>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<br>
						</span>
<!-- END SUMMARY OF CHANGES SECTION -->			
						<br>                   
<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">		
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
									<html:submit property="submitAction" onclick="return (validateCustomStrutsBasedJS(this.form) && disableSubmit(this, this.form));"><bean:message key="button.saveContinue"></bean:message></html:submit>&nbsp;
									<html:submit property="submitAction" onclick="return (validateCustomStrutsBasedJS(this.form) && disableSubmit(this, this.form));"><bean:message key="button.addMoreConditions"></bean:message></html:submit>&nbsp;
									<html:reset/>
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