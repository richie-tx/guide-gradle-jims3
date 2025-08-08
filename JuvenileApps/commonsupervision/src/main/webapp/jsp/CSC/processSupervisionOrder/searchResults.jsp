<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 11/30/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/16/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 01/25/2007	 Hien Rodriguez - ER#38535 Remove Manage Party Link for now. -->
<!-- 02/07/2007	 Hien Rodriguez - Defect#39167 Put Manage Party Link back until Mary talks to BA. -->
<!-- 06/12/2009	 C Shimek       - #39167 uncommented href code on supervisee name put in place by R. Young to activate link to supervisee detail page -->
<!-- 08/07/2009  D Gibler       - JIMS200060440 PASO Advanced Search - display CON, Race and Sex -->
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
<title><bean:message key="title.heading" /> - processSupervisionOrder/searchResults.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script> 
function findAndInitializeRadio(){
 var radioBtnElems=document.getElementsByName("spn");
 var actualElement;
 if(radioBtnElems!=null && radioBtnElems.length>0){	
 	for(var loopX=0;loopX<radioBtnElems.length;loopX++){
		actualElement=radioBtnElems[loopX];
		if(actualElement.checked==true){
			actualElement.checked=true;
	//		actualElement.onclick();
		}
		else{
			if(radioBtnElems.length==1){
				actualElement.checked=true;
	//			actualElement.onclick();
			}
		}
	}			
  }
}
</script>
</head>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionOrderCaseResults" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|3">
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
							<table width=100%>
								<tr>							
									<td align="center" class="header">
										<bean:message key="title.processSupervisionOrder" />&nbsp;-&nbsp;<bean:message key="title.searchResults" />
									</td>						
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
										<li>Click on Name to view Supervisee Details.</li>
										<li>Select Supervisee Name and click Submit button to View Associated Cases.</li>
									</ul></td>
								</tr>										
							</table>
						<!-- END INSTRUCTION TABLE -->
							<table>
								<tr>
									<td align="center">
										<bean:size id="superviseeListSize" name="supervisionOrderSearchForm" property="superviseeList"/>
										<b><bean:write name="superviseeListSize"/></b>&nbsp; supervisee(s) found in search results</td>            					
								</tr>
							</table>  
						<!-- BEGIN DETAIL TABLE -->	
							<table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
								<tr class="detailHead">
									<td width=1%></td>
									<td><bean:message key="prompt.supervisee" />&nbsp;<bean:message key="prompt.name" /></td>
									<td><bean:message key="prompt.SPN" /></td>								
									<td><bean:message key="prompt.CON" /></td>
									<td><bean:message key="prompt.SSN" /></td>
									<td><bean:message key="prompt.dob" /></td>
									<td><bean:message key="prompt.race" /></td>
									<td><bean:message key="prompt.sex" /></td>
								</tr>
								<%int RecordCounter = 0;
								String bgcolor = "";%>  
								<logic:notEmpty name="supervisionOrderSearchForm" property="superviseeList">	
									<logic:iterate id="superviseeListIndex" name="supervisionOrderSearchForm" property="superviseeList">
										<tr
										class=<%RecordCounter++;
											bgcolor = "alternateRow";
											if (RecordCounter % 2 == 1)
												bgcolor = "normalRow";
											out.print(bgcolor);%>>
											<td width=1%><input type=radio name="spn" value=<bean:write name="superviseeListIndex" property="spn"/> /></td>
											<td>
											<%-- Future feature - Not for release 0.2 - Defect #54316 - DAW --%>
											<%-- Richard Young - Hyper is ready to implement 10/10/08 Just uncomment --%>
											 <a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name="superviseeListIndex" property="spn"/>">
										<%--	<a href="javascript:openWindow('/<msp:webapp/>jsp/common/futureFeaturePopUp.jsp')"> --%>
												<bean:write name="superviseeListIndex" property="name" /></a></td>						
											<td><bean:write name="superviseeListIndex" property="spn" /></td>
											<td><bean:write name="superviseeListIndex" property="connectionId" /></td>
											<td><msp:formatter name="superviseeListIndex" property="ssn" format="A-B-C" /></td>
											<td><bean:write name="superviseeListIndex" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
											<td><bean:write name="superviseeListIndex" property="race" /></td>
											<td><bean:write name="superviseeListIndex" property="sexId" /></td>							
										</tr>																
									</logic:iterate>
								</logic:notEmpty> 				
							</table>	
							<script>
							findAndInitializeRadio();
						</script>				
						<!-- END DETAIL TABLE -->                      
							<br>
						<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>&nbsp;
										<html:submit property="submitAction" onclick="return validateRadios(this.form, 'spn', 'Please select a supervisee.');"><bean:message key="button.submit"></bean:message></html:submit>&nbsp;
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

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>