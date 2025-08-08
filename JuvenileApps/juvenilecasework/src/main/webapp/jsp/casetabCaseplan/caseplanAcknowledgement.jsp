<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2013	UGopinath Create JSP--%>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- caseplanAcknowledgement.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casetabCaseplan/caseplan.js"></script>

<script  type='text/javascript'>
$(document).ready(function(){
	var guardianExp =  sessionStorage.getItem("guardianExp");
	var juvenileExp =  sessionStorage.getItem("juvenileExp");
	
	if ( guardianExp == "enableGuardianExplanation" ) {
		show('guardianExp', 1, 'row');
	} else {
		show('guardianExp', 0, 'row');
	}
	
	if ( juvenileExp == "enableJuvenileExplanation" ){
		show('juvenileExp', 1, 'row');
	} else {
		show('juvenileExp', 0, 'row');
	}
	
	$("#finishBtn").click(function(){
		sessionStorage.removeItem("guardianExp");
		sessionStorage.removeItem("juvenileExp");
	})
})

var cal = new CalendarPopup();
cal.showYearNavigation();
function validateExplanation(theForm)
{

	clearAllValArrays();	
	var juvEntryDate=document.getElementById("entryDate1").value;
	
	var guardEntryDate=document.getElementById("entryDate2").value;	
	if(juvEntryDate=="")
	{
		alert("Juvenile Signature Entry Date is required.");		
		document.getElementById("entryDate1").focus();
		return false;
	}
	else
	{
		if(!addMMDDYYYYDateValidation("currentJuvenileAcknowledgment.entryDateStr","Juvenile Signature Entry Date is invalid."))
		{
			var today = new Date();
			var testDate = new Date(juvEntryDate);			
			if(testDate>today)
			{
				alert("Juvenile Signature Entry Date cannot be a future date.");
				document.getElementById("entryDate1").focus();
				return false;
			}
		}
			
	}
	
	if(guardEntryDate=="")
	{
		alert("Guardian Signature Entry Date is required.");		
		document.getElementById("entryDate2").focus();
		
		return false;
	}
	else
	{
		
		if(!addMMDDYYYYDateValidation("currentGuardianAcknowledgment.entryDateStr","Guardian Signature Entry Date is invalid."))
		{
			var today = new Date();
			var testDate = new Date(guardEntryDate);			
			if(testDate>today)
			{
				alert("Guardian Signature Entry Date cannot be a future date.");
				document.getElementById("entryDate2").focus();
				return false;
			}
		}
			
	}
	
	if(!document.getElementById("juv1").checked && !document.getElementById("juv2").checked
			&& !document.getElementById("guard1").checked && !document.getElementById("guard2").checked && !document.getElementById("guard3").checked)
	{
		alert("Juvenile and/or Guardian Signature Status required.");
    	$("#juv1").css("box-shadow","1px 1px 2px 2px grey");
		return false;
	}
	
	if(!document.getElementById("juv1").checked && !document.getElementById("juv2").checked
			&& !document.getElementById("guard1").checked && !document.getElementById("guard2").checked && document.getElementById("guard3").checked)
	{
		alert("The juvenile must either sign or refuse to sign this caseplan as Guardian is not available to sign.");
		$("#juv1").css("box-shadow","1px 1px 2px 2px grey");
		return false;
	}
	
	if(document.getElementById("juv2")!=null && document.getElementById("juv2").checked)
		customValRequired("currentJuvenileAcknowledgment.explanation", "Explanation is required if Juveile signature status is Refused to Sign.");	 
	if(document.getElementById("guard2")!=null && document.getElementById("guard2").checked)
		customValRequired("currentGuardianAcknowledgment.explanation", "Explanation is required if Guardian signature status is Refused to Sign.");	
	addDB2FreeTextValidation("currentJuvenileAcknowledgment.explanation", "<bean:message key='errors.comments' arg0='Explanation'/>");
	addDB2FreeTextValidation("currentGuardianAcknowledgment.explanation", "<bean:message key='errors.comments' arg0='Explanation'/>");	
	//customValMaxLength ("currentJuvenileAcknowledgment.explanation", "Explanation max length is 255 characters.",255);
	//customValMaxLength ("currentGuardianAcknowledgment.explanation", "Explanation max length is 255 characters.",255);
	
	return validateCustomStrutsBasedJS(theForm);
}

function trim(textbox) 
{
  if (textbox) 
	{
    while (textbox.value.substring(0,1) == " ") 
		{
      textbox.value = textbox.value.substring(1);
    }
  }
}
function enableGuardianExplanation()
{
	show('guardianExp', 1, 'row');
	sessionStorage.setItem("guardianExp", "enableGuardianExplanation");
}
function disableGuardianExplanation()
{
	show('guardianExp', 0, 'row');
	sessionStorage.setItem("guardianExp", "disableGuardianExplanation");
}

function enableJuvenileExplanation()
{
	show('juvenileExp', 1, 'row');
	sessionStorage.setItem("juvenileExp", "enableJuvenileExplanation");
}
function disableJuvenileExplanation()
{
	show('juvenileExp', 0, 'row');
	sessionStorage.setItem("juvenileExp", "disableJuvenileExplanation");
}


</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0" >
<html:form action="/displayAckSummary" target="content" >


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Process Caseplan - Add Acknowledgement  		

  		<logic:equal name="caseplanForm" property="status" value="summary"><bean:message key="prompt.summary"/></logic:equal>
  		<logic:equal name="caseplanForm" property="status" value="confirm"><bean:message key="prompt.confirmation"/></logic:equal>
   
  	</td>
  </tr>

  <logic:equal name="caseplanForm" property="status" value="confirm">
		<tr id='confMessage'><td align='center' class='confirm'>Caseplan Acknowledgement has been saved.</td></tr>
  </logic:equal>

</table>
<%-- END HEADING TABLE --%>

<logic:messagesPresent>
<div class='spacer'></div>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors /></td>		  
	</tr>   	  
</table>
<div class='spacer'></div>
</logic:messagesPresent> 

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
			<ul>
				<logic:notEqual name="caseplanForm" property="status" value="summary">
						<logic:notEqual name="caseplanForm" property="status" value="confirm">
							<li>Select Juvenile and/or Guardian Signature Status, enter Explanation if signature status is 'Refused to Sign' and select Next button to view Summary. </li>
						</logic:notEqual>						
				</logic:notEqual>
				<logic:equal name="caseplanForm" property="status" value="summary">
					<li>Review signature status and/or explanation entered and select Finish to save acknowledgement.</li>
					<li>To change signature status and/or explanation, select the Back button.</li>
				</logic:equal>
		
			</ul>
		</td>
	</tr>

	<logic:equal name="caseplanForm" property="status" value="">
  	<tr id='reqFieldsInstruct'> 
  		<td class="required"><bean:message key="prompt.diamond" />&nbsp;Required Fields</td> 
  	</tr> 
	</logic:equal>
</table>
<%-- END INSTRUCTION TABLE --%>


<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td valign='top'>

  		<tiles:insert page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="goalstab"/>
  			<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  		</tiles:insert>				

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>
					
					  <div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
  						<tr>
  							<td valign='top'>
    							<tiles:insert page="/jsp/caseworkCommon/casePlanTabs.jsp" flush="true">
    								<tiles:put name="tabid" value="CaseplanAck"/>
    								<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    							</tiles:insert>				
    						</td>
  						</tr>
  					 	<tr>
					  		<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
					  	</tr>
					  </table>

      			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
      				<tr>
      					<td valign='top' align='center'>      
        						 <div class='spacer'></div>	
        								
      						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
      							<tr>
      								<td class='detailHead' colspan='2' nowrap='nowrap'>      									
      									<bean:message key="prompt.casePlan"/><bean:message key="prompt.acknowledgment" /> 
        								
      								</td>
      							</tr>
      							<logic:equal name="caseplanForm" property="caseplanExist" value="N">
        							<tr>
        								<td class="formDeLabel" colspan='4'>No Caseplan available for this case</td> 
        							</tr>
        						</logic:equal>	
        				<logic:notEqual name="caseplanForm" property="caseplanExist" value="N">
      							<logic:equal name="caseplanForm" property="status" value="">
      							<tr>
      								<td>
      								 <div class='spacer'></div>						  			
      			  					<table width='98%' cellpadding='2' cellspacing='1' class="borderTableGrey">		
      			  						 <logic:empty name="caseplanForm" property="previousAcknowledgements">
			                                 <tr>
			                                
			                                   <td class="detailHead" nowrap="nowrap" align="left" valign="top"><bean:message key="prompt.previous"/> <bean:message key="prompt.casePlan"/><bean:message key="prompt.acknowledgments" />			                                   
	          								       <div class='spacer'></div>	          								    
	          								   </td>
	          								</tr>
	          								<tr>
	          								<td align="left">  No Previous Caseplan Acknowledgements	
	          								</td>
	          								</tr>
	          							</logic:empty>
	          							<logic:notEmpty name="caseplanForm" property="previousAcknowledgements">
      			  						<tr>
						          			<td width='1%' class=detailHead><a href="javascript:showHideMulti('behaviour', 'pchar', 1, '/<msp:webapp/>');" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="behaviour"></a></td>
						          			<td class=detailHead nowrap align='left' valign='top'><bean:message key="prompt.previous"/> <bean:message key="prompt.casePlan"/><bean:message key="prompt.acknowledgments" /></td>
						        		</tr>
      			  						<tr id="pchar0" class='hidden'>
						          			<td valign="top" align="center" colspan="2">
						    					<table cellpadding="4" cellspacing="1" width='100%'>
							    					<tr> 
														<td valign='middle' class="formDeLabel" width='10%' align="left">Entry Date
														
														</td> 
														<td valign='middle' class='formDeLabel' width='30%' align="left">Signature Status</td>
														<td valign='middle' class='formDeLabel' width='60%' align="left">Explanation</td>
														
													</tr>													
							    				</table>
							    				
							            		 <table width='100%' cellpadding="2" cellspacing="1" border=0 >							            						             
			        								<logic:iterate id="acknowledgements" name="caseplanForm" property="previousAcknowledgements" indexId="index"> 
			        									<tr>													  				
										  					<td class="formDe" width='10%' align="left"><bean:write name="acknowledgements" property="entryDate" formatKey="date.format.mmddyyyy"/></td>													  				
										  					<td class="formDe" width='30%' align="left"><bean:write name="acknowledgements" property="signatureStatus"/></td>													  				
										  					<td class="formDe" width='60%' align="left"><bean:write name="acknowledgements" property="explanation"/></td>
										  				</tr>
			              							</logic:iterate>          					
			              						</table>	
			              			
							    			</td>
							    		</tr>
							    		
      			  						</logic:notEmpty>
        	  						</table>
      								</td>
      							</tr>
      							</logic:equal>
      						
      							
      									<logic:equal name="caseplanForm" property="status" value="">	
      									<tr>
      										<td>	
      											 <div class='spacer'></div>			  			
		      			  					<table width='98%' cellpadding='2' cellspacing='1' class="borderTableGrey">
		      			  						<tr>						          				
							          				<td class=detailHead nowrap align='left' valign='top'>+<bean:message key="prompt.juvenile"/><bean:message key="prompt.signature"/><bean:message key="prompt.status"/></td>
							        			</tr>
							        				<tr>
							        				<td>
							        				<table width='100%' cellpadding="2" cellspacing="1" border=0 >	
							        				
							        				<tr>	
							        					<td class="formDeLabel" nowrap align='left' valign='top' width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.entryDate"/></td>
								          				<td align="left">
								          					<script type="text/javascript">
								          					var cal = new CalendarPopup();
															cal.showYearNavigation();
															</script> 
								          					<html:text styleId="entryDate1" name="caseplanForm" property="currentJuvenileAcknowledgment.entryDateStr" size="10" maxlength="10" title="SignatureDate"/>
								          					
														</td>			          				
								          				<td class="formDeLabel" nowrap align='left' valign='top' width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.signature"/></td>
								          				<td align="left"><html:radio name="caseplanForm" property="currentJuvenileAcknowledgment.signatureStatus" value="true" onclick="disableJuvenileExplanation()" styleId="juv1"/>Signed
																<html:radio name="caseplanForm" property="currentJuvenileAcknowledgment.signatureStatus" value="false" onclick="enableJuvenileExplanation()" styleId="juv2"/>Refused to Sign
														</td>
														<td width='10%'></td>
													</tr>
													</table>
													</td>
							        			</tr>
							        			<tr id="juvenileExp" class="hidden">	
							        				<td>
							        				<table width='98%' cellpadding="2" cellspacing="1" border=0 class="borderTableGrey">
							        				<tr>						          				
								          				<td class="formDe" nowrap align='left' valign='top' width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.explanation"/>
								          					&nbsp;
						                      					<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
						                      						<tiles:put name="tTextField" value="currentJuvenileAcknowledgment.explanation"/>
						                      						<tiles:put name="tSpellCount" value="spellBtn1" />
						                    						</tiles:insert> 
						                    						(Max. characters allowed: 255)</td>							          				
													</tr>
													<tr>
														 <td><html:textarea rows="4" style="width:100%" name="caseplanForm" property="currentJuvenileAcknowledgment.explanation" onmouseout="textCounter(this,255)" onkeyup="textCounter(this,255)"></html:textarea></td>
													</tr>
													</table>
													</td>
							        			</tr>
		      			  					</table>
		      			  					</td>
		      			  					</tr>
		      			  				</logic:equal>
		      			  				
		      			  				<logic:notEqual name="caseplanForm" property="status" value="">
		      			  				<tr>
		      			  				<td>
		      			  					<table width='98%' cellpadding='2' cellspacing='1' class="borderTableGrey">
		      			  						<tr>						          				
							          				<td class=detailHead nowrap align='left' valign='top'><bean:message key="prompt.juvenile"/><bean:message key="prompt.signature"/><bean:message key="prompt.status"/></td>
							        			</tr>
							        				<tr>
							        				<td>
							        					<table width='98%' cellpadding="2" cellspacing="1" border=0 >								        				
							        					<tr>	
							        						<td class="formDeLabel" nowrap align='left' valign='top' width='1%'><bean:message key="prompt.entryDate"/></td>
								          					<td width='20%' align="left"><bean:write name="caseplanForm" property="currentJuvenileAcknowledgment.entryDateStr"/></td>							          				
									          				<td class="formDeLabel" nowrap align='left' valign='top' width='1%'><bean:message key="prompt.signature"/></td>
									          				<td width='40%' align="left">
										          				<logic:equal name="caseplanForm" property="currentJuvenileAcknowledgment.signatureStatus" value="true">	
										          					Signed 
										          				</logic:equal>
										          				
										          				<logic:equal name="caseplanForm" property="currentJuvenileAcknowledgment.signatureStatus" value="false">	
										          					Refused to Sign 									          				
										          				</logic:equal>	
									          				</td>														
														</tr>
														</table>
														</td>
														</tr>
														<logic:notEqual name="caseplanForm" property="currentJuvenileAcknowledgment.signatureStatus" value="true">
														<tr colspan='4'>
														<td>
															<table width='98%' cellpadding='2' cellspacing='1' class="borderTableGrey">	
																<tr>
																	<td class="formDeLabel" nowrap align='left' valign='top' colspan='4'><bean:message key="prompt.explanation"/></td>
																</tr>
																<tr>
																	<td><bean:write name="caseplanForm" property="currentJuvenileAcknowledgment.explanation" /></td>
																</tr>
															</table>
														</td>
														</tr>
														</logic:notEqual>										
							        		
		      			  					</table>
		      			  					</td>
		      			  					</tr>
		      			  				</logic:notEqual>
		      			  				<logic:equal name="caseplanForm" property="status" value="">
		      			  				<tr>
		      			  				<td>						  			
		      			  					<table width='98%' cellpadding='2' cellspacing='1' class="borderTableGrey">
		      			  						<tr>						          				
							          				<td class=detailHead nowrap align='left' valign='top'>+<bean:message key="prompt.guardian"/><bean:message key="prompt.signature"/><bean:message key="prompt.status"/></td>
							        			</tr>
							        			<tr>	
							        				<td>
							        				<table width='100%' cellpadding="2" cellspacing="1" border=0 >
							        				<tr>	
							        					<td class="formDeLabel" nowrap align='left' valign='top' width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.entryDate"/></td>
							        					<td>
							        						<script type="text/javascript">
								          					var cal = new CalendarPopup();
															cal.showYearNavigation();
															</script>
															<html:text styleId="entryDate2"name="caseplanForm" property="currentGuardianAcknowledgment.entryDateStr" size="10" maxlength="10" title="SignatureDate1"/> 
															
															</td>
														<td width='20%'></td>					          				
								          				<td class="formDeLabel" nowrap align='left' valign='top' width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.signature"/></td>
								          				<td align="left" nowrap><html:radio name="caseplanForm" property="currentGuardianAcknowledgment.signatureStatus" value="true" onclick="disableGuardianExplanation()" styleId="guard1"/>Signed
															<html:radio name="caseplanForm" property="currentGuardianAcknowledgment.signatureStatus" value="false" onclick="enableGuardianExplanation()" styleId="guard2"/>Refused to Sign
															<html:radio name="caseplanForm" property="currentGuardianAcknowledgment.signatureStatus" value="unavailable" onclick="disableGuardianExplanation()" styleId="guard3"/>Not Available for Guardian Signature
														</td>
															<td width='32%'></td>		
													</tr>
													</table>
													</td>
							        			</tr>
							        			<tr id="guardianExp" class="hidden">	
							        				<td>
							        				<table width='98%' cellpadding="2" cellspacing="1" border=0 class="borderTableGrey">
							        				<tr>						          				
								          				<td class="formDe" nowrap align='left' valign='top' width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.explanation"/>
								          				&nbsp;
						                      					<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
						                      						<tiles:put name="tTextField" value="currentGuardianAcknowledgment.explanation"/>
						                      						<tiles:put name="tSpellCount" value="spellBtn2" />
						                    						</tiles:insert> 
								          				(Max. characters allowed: 255)</td>							          				
													</tr>
													<tr>
														 <td><html:textarea rows="4" style="width:100%" name="caseplanForm" property="currentGuardianAcknowledgment.explanation" onmouseout="textCounter(this,255)" onkeyup="textCounter(this,255)"></html:textarea></td>
													</tr>
													</table>
													</td>
							        			</tr>
		      			  					</table>
		      			  					</td>
		      			  					</tr>
		      			  				</logic:equal>
		      			  				<logic:notEqual name="caseplanForm" property="status" value="">
		      			  				<tr>
		      			  				<td>
			      			  				<table width='98%' cellpadding='2' cellspacing='1' class="borderTableGrey">
		      			  						<tr>						          				
							          				<td class=detailHead nowrap align='left' valign='top'><bean:message key="prompt.guardian"/><bean:message key="prompt.signature"/><bean:message key="prompt.status"/></td>
							        			</tr>
							        			<tr>	
							        				<td>
							        				<table width='100%' cellpadding="2" cellspacing="1" border=0 >
							        				<tr>
							        				
							        					<td class="formDeLabel" nowrap align='left' valign='top' width='1%'><bean:message key="prompt.entryDate"/></td>
								          				<td width='20%' align="left"><bean:write name="caseplanForm" property="currentGuardianAcknowledgment.entryDateStr"/></td>					          				
								          				<td class="formDeLabel" align='left' valign='top' width='1%'><bean:message key="prompt.signature"/></td>
								          				<td width='40%' align="left">
								          				<logic:equal name="caseplanForm" property="currentGuardianAcknowledgment.signatureStatus" value="true">	
								          					Signed 
								          				</logic:equal>
								          				<logic:equal name="caseplanForm" property="currentGuardianAcknowledgment.signatureStatus" value="false">	
								          					Refused to Sign 
								          				</logic:equal>
								          				<logic:equal name="caseplanForm" property="currentGuardianAcknowledgment.signatureStatus" value="unavailable">	
								          					Not Available for Guardian Signature 
								          				</logic:equal>
								          				</td>
								          			
													</tr>
													</table>
													</td>
							        			</tr>
							        			<logic:equal name="caseplanForm" property="currentGuardianAcknowledgment.signatureStatus" value="false">
												<tr>
												<td>
													<table width='98%' cellpadding='2' cellspacing='1' class="borderTableGrey">	
														<tr>
															<td class="formDeLabel" nowrap align='left' valign='top' colspan='4'><bean:message key="prompt.explanation"/></td>
														</tr>
														<tr>
															<td align="left"><bean:write name="caseplanForm" property="currentGuardianAcknowledgment.explanation" /></td>
														</tr>
													</table>
												</td>
												</tr>
												</logic:equal>							        			
			      			  				</table>
			      			  				</td>
			      			  				</tr>
		      			  				
		      			  			</logic:notEqual>	
		      			  			</logic:notEqual>		
      			  				</table>
      			  				<br>
      			  							  				 
  			</td>
      		</tr>	
      		
      	</table>
      	
				<%-- BEGIN BUTTON TABLE --%>
				<logic:notEqual name="caseplanForm" property="caseplanExist" value="N">	                  
				<table width="100%">
					<tr>
						<td align="center">
							<logic:equal name="caseplanForm" property="status" value="summary">
								<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
								<input id="finishBtn" type="submit" name="submitAction" value="<bean:message key='button.finish'/>" 
									onclick="changeFormActionURL('/<msp:webapp/>submitCaseplanAcknowledgement.do', false);">
								<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
							</logic:equal>
							<logic:equal name="caseplanForm" property="status" value="confirm">						
								
							<input type="button" name="submitAction" value="<bean:message key='button.caseplanDetails'/>" 
									onclick="changeFormActionURL('/<msp:webapp/>displayCaseplanDetails.do?submitAction=Link', true);">
							</logic:equal>
							                 
							<logic:empty name="caseplanForm" property="status">							
								<html:submit property="submitAction" onclick="return validateExplanation(this.form);"><bean:message key="button.next"/></html:submit>
								<html:reset><bean:message key="button.reset"></bean:message></html:reset>
								<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>	      								
							</logic:empty>
						</td>
					</tr>
				</table>
			</logic:notEqual>
		<%-- END BUTTON TABLE --%>	
		 <div class='spacer'></div>	
		</td>
  	 </tr>			
	</table>
	</td>
  </tr>
</table>

</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
