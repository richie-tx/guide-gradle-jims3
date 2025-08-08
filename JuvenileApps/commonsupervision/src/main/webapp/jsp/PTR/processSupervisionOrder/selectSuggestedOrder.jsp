<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/13/2005	 Hien Rodriguez - Create JSP -->
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
<title><bean:message key="title.heading" /> - processSupervisionOrder/selectSuggestedOrder.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionOrderSetCondDetails" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|8">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign=top>
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
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
					<td valign=top align=center>
						<!-- BEGIN HEADING TABLE -->
							<table width=98%>
								<tr>
									<td align="center" class="header"><bean:message key="prompt.create" />&nbsp;<bean:message key="title.supervisionOrder" />&nbsp;-&nbsp;<bean:message key="prompt.select" />&nbsp;<bean:message key="title.suggestedOrder" /></td>
								</tr>						 						  
							</table>									
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
							<table width=98% align=center>															
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->								
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td><ul>
										<li>Select a Suggested Order and Click Save \x26 Continue.</li>
									</ul></td>
								</tr>																				
							</table>
						<!-- END INSTRUCTION TABLE -->
						<!-- BEGIN DETAIL HEADER TABLE -->
																									
							<tiles:insert page="caseOrderHeaderTile.jsp" flush="true"></tiles:insert>
						
						<!-- END DETAIL HEADER TABLE -->
							<br>
						<!-- BEGIN ORDER PRESENTATION TABLE -->
							<table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
								<tr class=detailHead>
									<td class=paddedFourPix>
										<table width=100% cellpadding=0 cellspacing=0>
											<tr>
												<td width=1%><img border=0 src="/<msp:webapp/>images/expand.gif" name="orderPresentationFields" onclick="showHide('orderPresentationFields', 'row','/<msp:webapp/>')" style="cursor:pointer"></td>
												<td class=detailHead>&nbsp;<bean:message key="prompt.orderPresentation" /></td>
												<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="orderPresentationFieldsSpan" class=hidden>
									<td>
													<tiles:insert page="orderPresentationTile.jsp" flush="true"></tiles:insert>
							
									 </td>							                 
								  </tr>
							 </table>
						<!-- END ORDER PRESENTATION TABLE -->
							<br>   	
						<!-- BEGIN SELECT SUGGESTED ORDER TABLE -->
							<table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
								<tr class="detailHead">
									<td width=1%></td>
									<td><bean:message key="prompt.suggestedOrderName" /></td>																			
									<td><bean:message key="prompt.description" /></td>
									
								</tr>
								<%int RecordCounter = 0;
								String bgcolor = "";%>  
								<logic:notEmpty name="supervisionOrderForm" property="suggestedOrderList">	
									<logic:iterate id="suggestedOrderListIndex" name="supervisionOrderForm" property="suggestedOrderList">
										<tr
										class=<%RecordCounter++;
											bgcolor = "alternateRow";
											if (RecordCounter % 2 == 1)
												bgcolor = "normalRow";
											out.print(bgcolor);%>>
											<td width=1%><%--<input type=radio name="suggestedOrderId" value=<bean:write name="suggestedOrderListIndex" property="suggestedOrderId"/> /></td>--%>
											<html:radio name="supervisionOrderForm" property="suggestedOrderId" idName="suggestedOrderListIndex" value="suggestedOrderId" /></td>
											<td><bean:write name="suggestedOrderListIndex" property="orderName" /></td>
											<td><bean:write name="suggestedOrderListIndex" property="orderDescription" /></td>						
										</tr>																
									</logic:iterate>
								</logic:notEmpty> 				
							</table>
						<!-- END SELECT SUGGESTED ORDER TABLE -->
							<br>                     
						<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">											
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp; 
										<html:submit property="submitAction" onclick="return validateRadios(this.form, 'suggestedOrderId', 'Please select a suggested order.', 'No suggested orders found.  Selection of suggested order required.') && disableSubmit(this, this.form);"><bean:message key="button.saveContinue"></bean:message></html:submit>&nbsp; 
										<html:submit property="submitAction" onclick="return validateRadios(this.form, 'suggestedOrderId', 'Please select a suggested order.', 'No suggested orders found.  Selection of suggested order required.') && disableSubmit(this, this.form);"><bean:message key="button.customizeSuggestedOrder"></bean:message></html:submit>&nbsp;  
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

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>