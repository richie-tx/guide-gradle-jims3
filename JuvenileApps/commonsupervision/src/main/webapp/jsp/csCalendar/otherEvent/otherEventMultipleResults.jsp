<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- THIS JSP IS USED FOR ENTER MULTIPLE RESULTS -->
<!-- 08/23/2010 D Williamson - #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.PDCodeTableConstants"%>
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
<title><bean:message key="title.heading" /> - csCalendar - otherEvent - otherEventMultipleResults.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
		<html:javascript formName="otherEventCreateUpdate"/>
		<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
		<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
		<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
<script type="text/javascript">


function checktime(thetime) {
	var a,b,c,f,err=0;
	a=thetime;
	if (a.length != 5) err=1;
	b = a.substring(0, 2);
	c = a.substring(2, 3); 
	f = a.substring(3, 5); 
	if (/\D/g.test(b)) err=1; //not a number
	if (/\D/g.test(f)) err=1; 
	if (b<0 || b>12) err=1;
	if (f<0 || f>59) err=1;
	if (c != ':') err=1;
	if (err==1) {
	return false;	
	}
  return true;	
}


function checkForm(theForm){	
	var a = 0;
	var b = 0;
	var c = 0;
	var focusForm;
	var length =document.getElementById("count").value;
	if(length > 0){
	for(i=0; i<length; i++){
		//alert(document.getElementsByName("selectedEvents["+i+"].outcome")[0].value)
		var outcome = document.getElementsByName("selectedEvents["+i+"].outcome")[0]
		if(outcome!= null && outcome.value==""){     
				c = 1;
				focusForm=document.getElementsByName("selectedEvents["+i+"].outcome")[0];
			   break;
	       }
		
		var sTime = document.getElementsByName("selectedEvents["+i+"].startTime1")[0].value
		if(sTime!=null && sTime!=""){
			if(!checktime(sTime)) { 						                   
				a = 1;
				focusForm=document.getElementsByName("selectedEvents["+i+"].startTime1")[0];
			   break;
		      }	
	       }
    
		var eTime = document.getElementsByName("selectedEvents["+i+"].endTime1")[0].value
		if(eTime!=null && eTime!=""){
			if(!checktime(eTime)) { 						                   
				b = 1;
				focusForm=document.getElementsByName("selectedEvents["+i+"].endTime1")[0];
			   break;
		      }	
	       }
		
	
      }
  }
	
	if(a==1){ 
		alert("Start Time is not in proper 12 hour format, ie 03:15")
		focusForm.focus(); 	    	   
    return false;
	}	
	if(b==1){ 
		alert("End Time is not in proper 12 hour format, ie 03:15") 	  
		focusForm.focus(); 	    
    return false;
	}	
	if(c==1){ 
		alert("Please Select Outcome")
		focusForm.focus(); 	   
    return false;
	}	
   return true;	
}

   
		 
	 function validateResults(theForm) {
	      clearAllValArrays();		   
	      trimCasenote('narrative');	      
	      customValRequired("narrative", "<bean:message key='errors.required' arg0='Narrative'/>","");
	      customValMaxLength("narrative","<bean:message key='errors.maxlength' arg0='Narrative' arg1='3500'/>","7000");
	      addDefinedTinyMCEFieldMask("narrative","Narrative Text cannot have % or _ entries","");
	      
	      if (validateCustomStrutsBasedJS(theForm) && validateForBROnly(theForm.narrative.value, 'Narrative is required')){
	  		return true;
		  	}else {
		  		return false;
		  	}
	  }
 </script>
		
	</head>
	<body topmargin=0 leftmargin="0" onKeyDown="checkEnterKeyAndSubmit(event, true)">
		<html:form action="/handleCSOtherEventMultipleResults"  target="content" onsubmit="myTinyMCEFix()">
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
											<tiles:put name="tab" value="calendarTab"/>
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
								
								<!-- BEGIN Supervisee Details -->
								<logic:equal name="csCalendarOtherForm" property="context" value="S">
									<tr>
										<td valign="top" align="center">
											<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
											<tiles:insert page="../../common/superviseeHeader.jsp" flush="true"></tiles:insert>	
											<!-- END SUPERVISEE INFORMATION TABLE  -->
										</td>
									</tr>	
									<!-- BEGIN GREEN TABS TABLE -->		
									<tr>
										<td valign="top" align="center"> 
											<table width="98%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
												</tr>						
												<tr>
													<td valign="top">
														<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
															<tiles:put name="tab" value="CalendarTab"/> 
														</tiles:insert>					
													</td>
												</tr>
												<tr>
													<td  bgcolor=#33cc66><img src="/<msp:webapp/>js/images/spacer.gif" height="5"></td> 
												</tr>
											</table>
										</td>
									</tr>		
									<!-- END GREEN TABS TABLE -->				
									<tr>
										<td valign="top" align="center">
											<!-- BEGIN GREEN BORDER TABLE -->					
											<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								</logic:equal>
								<tr>
								<!-- Supervisee Details -->
								
								<tr>
									<td valign=top align=center>
										<!--header area start-->

										<!-- BEGIN HEADING TABLE -->
										<table width=100%>
											<tr>
												<td align="center" class="header">CSCD - Multiple Other Events Results
												<logic:equal name="csCalendarOtherForm" property="activityFlow" value="summary">
												Summary
												</logic:equal>												
												</td>
											</tr>
										</table>
										<!-- END HEADING TABLE -->										
										<!-- BEGIN INSTRUCTION TABLE -->
										<table width="98%" border="0">
										 <logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="summary">
											<tr>
												<td>
													<ul>
														<li>Enter required fields. Click Next.</li>
													</ul>
												</td>
										 </logic:notEqual>
										 <logic:equal name="csCalendarOtherForm" property="activityFlow" value="summary">
											<tr>
												<td>
													<ul>
														<li>Review entries. Click Finish. Click Back to make changes. </li>
													</ul>
												</td>
											</tr>	
										 </logic:equal>
										 <logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="summary">
											<tr>
												<td class="required" colspan="2"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
											</tr>
										</logic:notEqual>	
										</table>
										<!-- END INSTRUCTION TABLE -->
										<!-- BEGIN DETAIL TABLE -->
											<tiles:insert page="otherEventMultipleResultsTile.jsp" flush="true">
												<tiles:put name="csCalendarOtherForm" beanName="csCalendarOtherForm" />																								
													<tiles:put name="attrPrefix" value="selectedEvents"/>
											</tiles:insert>	
											<div class=spacer4px></div>											
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<tr>
												<td align="center">
													<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>												
											       <logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="summary">
											       <html:submit property="submitAction" onclick="return( myTinyMCEFix()&& validateResults(this.form) && checkForm(this.form))"><bean:message key="button.next" /></html:submit>
											       <html:reset />											       
											        </logic:notEqual>
											         <logic:equal name="csCalendarOtherForm" property="activityFlow" value="summary">
											        <input type="submit" value="<bean:message key='button.finish'/>" name="submitAction" onclick="changeFormActionURL(this.form, '/<msp:webapp/>submitCSOtherEventUpdate.do?submitAction=Link',false);" >
											        </logic:equal>
											         <html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>										
												</td>
											</tr>
										</table>
										<!-- END BUTTON TABLE -->
										<!-- END DETAIL TABLE -->
									</td>
								</tr>
								
							</table><br>
						</td>
					</tr>
				</table><br>
				<!--casefile tabs end-->
			</td>
		</tr>
	</table>
	<!-- END  TABLE -->
</div>
<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
