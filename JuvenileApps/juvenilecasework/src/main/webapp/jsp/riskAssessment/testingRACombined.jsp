<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 05/11/2011     CShimek    #70253 created this jsp  --%>
<%-- 05/04/2011		DGibler	   #69838 added CLM Update --%>
<%-- 06/17/2011	 	CShimek	   #70669 add missing hidden field "Mode" for required for validation and scripting to validate Reason for Modification --%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<msp:nocache />
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- testingRACombined.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/riskAnalysis.js"></script>
<script type='text/javascript'>
function validateSelect()
{
	cbFound = false; 
	var cbs = document.getElementsByTagName("input");
	if (cbs.length > 0)
	{
		for (x=0; x<cbs.length; x++)
		{
			if (cbs[x].type == "checkbox" && cbs[x].checked == true)
			{
				cbFound = true;
				break;
			}		
		}	
	}
	if (cbFound == false)
	{	
		alert("At least one selection must be made in one test category.");
		return false;
	}
// following code copied form riskAnalyis.js to be consistent	
	var theMode = document.getElementById('theMode');
	if (theMode != null && theMode.value == 'update')
	{
		var reasonModMessage = "Modification Reason is required.";
		var reasonMod = document.getElementById('reasonMod');
		if ( Trim(reasonMod.value).length < 1 )
		{
			alert( reasonModMessage );
			reasonMod.focus();
			return false;
		}
	}		
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayTestingPeer" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|#" >
<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<tr>
    	<td align="center" class="header">
    		<bean:message key="title.juvenileCasework"/> - 
    		<bean:message key="prompt.riskAssessment"/>
    		<bean:message key="prompt.testing"/>
    		<bean:message key="prompt.info"/>
    		<logic:equal name="riskAnalysisForm" property="mode" value="update">
			Update 
			</logic:equal>	
    		
    	</td>
  	</tr>
</table>
<!-- END HEADING TABLE -->
<div class='spacer'></div>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<tr>
    	<td>
  	  		<ul>
        		<li>Expand testing information section(s) and check all relevant boxes.</li>
        		<li>After making all selections, select Next button to view summary display.</li>
        		<li>Check all relevant boxes then select Next button.</li>
        		<li>Select Back button to return to previous page.</li>
  	  		</ul>	
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<input type="hidden" id="theMode" name="modeField" value="<bean:write name="riskAnalysisForm" property="mode"/>">
<input type="hidden" name="clearRiskCheckBoxes" value="" />
<!-- BEGIN HEADER INFO TABLE -->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<!-- END HEADER INFO TABLE -->
<div class='spacer'></div>
<!-- BEGIN CASEFILE TABS TABLE -->
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    	<td valign="top">
	    	<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	    		<tiles:put name="tabid" value="casefiledetailstab"/>
	    		<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	    	</tiles:insert>				
<!-- BEGIN CASEFILE TABS BLUE BORDER TABLE -->
	  		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	  			<tr>
	  				<td valign="top" align="center">
	  					<div class='spacer'></div>
<!-- BEGIN CASEFILE INFO TABS INNER TABLE -->
		  				<table width='98%' border="0" cellpadding="0" cellspacing="0">
	  						<tr>
	  							<td>
<!-- BEGIN CASEFILE INFO TABS TABLE -->	  							
	  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
	  									<tr>
	  										<td valign="top">
	  										<%--tabs start--%>
	  											<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
	  												<tiles:put name="tabid" value="riskAnalysis"/>
	   												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	  											</tiles:insert>	
	  										<%--tabs end--%>
	  										</td>
	  									</tr>
	  									<tr>
											<td bgcolor='#33cc66'><img src='../../images/spacer.gif' width="5"></td>
	  									</tr>
								  	</table>
<!-- END CASEFILE INFO TABS TABLE -->
<!-- BEGIN CASEFILE INFO TABS GREEN BORDER TABLE -->
									<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
	  									<tr>
	  										<td valign="top" align="center">
	                        					<div class='spacer'></div>	
<!-- BEGIN TESTING INFORMATION TABLE -->
						                        <table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
						                        	<tr>
						                        		<td class="detailHead" ><bean:message key="prompt.testing"/>&nbsp;<bean:message key="prompt.info"/></td>
						                        	</tr>
						                        	<tr>
						                        		<td>
<!-- BEGIN TESTING INFORMATION DETAILS TABLE -->	
						                        			<table width="100%" cellpadding="2" cellspacing="1">
						                        				<tr>
						                        					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.dateTaken"/></td>
						                        					<td class="formDe"><bean:write name="riskAnalysisForm" property="riskAssessmentDate" formatKey="date.format.mmddyyyy"/></td>
						                        				</tr>
						                        		 		<nested:iterate id="questionAnswers" name="riskAnalysisForm" property="questionAnswers" indexId="outer"> 
							                        				<tr>
							                        					<td class='formDeLabel' colspan="2">
																			<a href="javascript:showHideExpand('<bean:write name="questionAnswers" property="questionText"/>', 'row','/<msp:webapp/>')" border=0><img src="../../images/expand.gif" border="0" name="<bean:write name="questionAnswers" property="questionText"/>"></a>
																			<bean:write name="questionAnswers" property="questionText"/>
																		</td>
																	</tr>
																	<tr id="<bean:write name='questionAnswers' property='questionText'/>Span" class="hidden">
																		<td align="center" colspan="2">
<!-- BEGIN CHECKBOX SELECT TABLE TABLE -->																	
																			<table width="100%" cellpadding="2" cellspacing="1">
									                        					<%int formatIdentifier = 0;%>
									                          <%-- 	        		<bean:define id="questionNum" name="riskTestingForm" property="questionNbr" type="java.lang.Integer"/>
						                  	           							<input type="hidden" name="number" value="<%=questionNum%>">  --%>
					                        									<tr>
			                            											<logic:notEmpty name="questionAnswers" property="answers">
			                              												<logic:equal name="questionAnswers" property="uiControlType" value="CHECKBOX">
				                                											<logic:iterate id="answer" name="questionAnswers" property="answers" indexId="inner">
					                                  											<% if(inner.intValue() == 0 || inner.intValue() %2 == 0) 
																							   	{
					                                  						    			   		out.print( "<tr>" ) ;
																							   	}
																								%>
				                                  												<td class="formDe" align="left" width='50%'>
				                                    												<bean:define id="answerValue" name="answer" property="weightedResponseID" type="java.lang.Integer"/>
				                                    													<nested:multibox property="selectedAnswerIDs" value="<%=answerValue.toString()%>" />
				                                    													<bean:write name="answer" property="answerText" />						                                    															
				                                  												</td>
				                                  												<% if(inner.intValue() %2 != 0)
																				           			{
				                                  						     			    		out.print( "</tr>" ) ;
																						  			}
				                                  						   				   			formatIdentifier = inner.intValue();
																								%>
				                                											</logic:iterate>			
				                                											<% if(formatIdentifier %2 == 0)
				                                						   			   			{
																					   			out.print( "<td class='formDe' width='50%'></td>" ) ;
				                                						               			}
																							%>	
			                               												</logic:equal>						
			                              											</logic:notEmpty>
				                            									</tr>
				                            									
						                                					</table>
<!-- END CHECKBOX SELECT TABLE -->							                                				
						                              					</td>
						                           	 				</tr>
		                         								</nested:iterate>
		                         								<logic:equal name="riskAnalysisForm" property="mode" value="update">
													 				<tr id='modificationReasonRow' class=''>
				        												<td colspan='2'>
														         			<table align="center" width='100%' border="0" cellpadding="4" cellspacing="1" class="borderTableGrey">
																				<tr>
						                 										<td class='formDeLabel' colspan='2'>Reason for modification&nbsp;
						                 											<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
								                  										<tiles:put name="tTextField" value="modReason"/>
								                   										<tiles:put name="tSpellCount" value="spellBtn1" />
						                											</tiles:insert>
						                											(Max. characters allowed: 550)
					                											</td>
						                 										</tr>
							                 									<tr>
							                 										<td class='formDe' colspan='2'><html:textarea name="riskAnalysisForm" property="modReason" rows='3' style="width:100%" onmouseout="textCounter(this,550)" onkeydown="textCounter(this,550)" styleId="reasonMod"></html:textarea></td>
							  	              									</tr>
					      	          										</table>
					          	      									</td>
				        											</tr>
																</logic:equal>				                    				
		                         								
						                        			</table>
<!-- END TESTING INFORMATION DETAILS TABLE -->							                        			
						                        		</td>
						                        	</tr>
						                        </table>			
<!-- END TESTING INFORMATION TABLE -->	                          					
												<div class='spacer'></div>
<!-- BEGIN BUTTON TABLE -->												
						                        <table border="0" width="100%">
						                        	<tr>
						                            	<td align="center">
						                                	<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>&nbsp;
						                                	<html:submit property="submitAction" onclick="return validateSelect();"><bean:message key="button.next" /></html:submit>&nbsp;
						                                	<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
						                            	</td>
						                          	</tr>
						                        </table>
<!-- END BUTTON TABLE -->
	                      					</td>
	                    				</tr>
	                  				</table>
<!-- END CASEFILE INFO TABS GREEN BORDER TABLE -->
	                			</td>
	              			</tr>
	            		</table>
<!-- END CASEFILE INFO TABS INNER TABLE -->
						<div class='spacer'></div>
	          		</td>
	        	</tr>
	      </table>
<!-- END CASEFILE TABS BLUE BORDER TABLE -->
		<div class='spacer'></div>
    	</td>
	</tr>
</table>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>