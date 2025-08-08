<!DOCTYPE HTML>
<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Caseplan" tab on Juvenile Profile Master Details page after searching for a juvenile profile --%>
<%-- 01/29/2007	Debbie Williamson		Create JSP --%>
<%-- 07/17/2009 C Shimek                #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - generateCaseplanDetailsSubmit.jsp</title>
<%--END HEADER TAG--%>


 <script type='text/javascript'>
function juvFosterCareCallback( isFosterCare )
 {
	 if(isFosterCare == ''){
			var isFosterCare = "<bean:write name='caseplanForm' property='juvFosterCareCandidateStr'/>"; 
			
	 }
		if( isFosterCare == 'Yes' )
	 	{
		   show( 'riskAssessDt', 1, 'row' ) ; 
		   show( 'psychologicalDt', 1, 'row' ) ; 
		   show( 'socialInvestDt', 1, 'row' ) ; 
		   
		   show( 'otherDt', 1, 'row' ) ;
		   show( 'otherExplain', 1, 'row' ) ;
	 	}
	 	else
	 	{
	 		show( 'riskAssessDt', 0, 'row' ) ; 	
	 		show( 'psychologicalDt', 0, 'row' ) ; 
			show( 'socialInvestDt', 0, 'row' ) ; 
			
			show( 'otherDt', 0, 'row' ) ;
			show( 'otherExplain', 0, 'row' ) ;
	 	}	 
 }
</script>

</head>



<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0" onload="juvFosterCareCallback('');">
<html:form action="/handleCaseplan" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|170">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" /> - Process Caseplan - Generate Caseplan</td>
	</tr>
</table>

<table width="100%">
<tr id='confMessage'><td align=center class='confirm'>Caseplan Details successfully updated.</td></tr>
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<div class="spacer"></div>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Enter information and select the button to view Caseplan.</li>
			</ul>
		</td>
	</tr>
	<tr id='reqFieldsInstruct'> 
    <td class="required"><bean:message key='prompt.diamond' />&nbsp;Required Fields</td> 
  </tr> 
</table>
<%-- END INSTRUCTION TABLE --%>

<logic:notEqual name="caseplanForm" property="juvProfile" value="true">

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
    <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%-- END HEADER INFO TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">

	    <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	      <tiles:put name="tabid" value="goalstab" />
	      <tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	    </tiles:insert>
	
	  	<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	  		<tr>
	  			<td valign="top" align="center">
	  			<div class="spacer"></div>
	  			<table width='98%' border="0" cellpadding="0" cellspacing="0">
	  				<tr>
	  					<td valign="top">
	  						  <tiles:insert page="../caseworkCommon/casePlanTabs.jsp" flush="true">
	  								<tiles:put name="tabid" value="GenerateCaseplanDetails" />
	  								<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	  							</tiles:insert>
	  					</td>
	  				</tr>
	  				<tr>
	  					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5" alt=""></td>
	  				</tr>
	  			</table>
	  
	  			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
	  					<tr>
	  						<td valign="top" align="center">
</logic:notEqual>

<logic:equal name="caseplanForm" property="juvProfile" value="true">

<%-- BEGIN HEADER TABLE --%>
<div class="spacer"></div>
<table align="center" cellpadding="1" cellspacing="0" border="0" width='98%'>
  <tr>
    <td bgcolor='#cccccc'>
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader"/>
			</tiles:insert> <%--header info end--%>
		</td>
  </tr>
</table>
<%-- END HEADER TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td valign="top">
   		<table width='98%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<%--tabs start--%> 
            <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
  						<tiles:put name="tabid" value="goalstab" />
  						<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
					  </tiles:insert> 
            <%--tabs end--%>		
					</td>
				</tr>
				<tr>
			  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>

			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
					<div class="spacer"></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
					  			<td valign="top">
					  				<tiles:insert page="../caseworkCommon/juvenileCasePlanTabs.jsp" flush="true">
					  					<tiles:put name="tabid" value="GenerateCaseplanDetails"/>
					  					<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
					  				</tiles:insert>				
					  			</td>
					  		</tr>
  						<tr>
		    		  	<td bgcolor='6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
		    			</tr>
					  </table>

					  <table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
 							<tr>
 								<td valign="top" align="center">
					</logic:equal>	

										<%-- BEGIN Activities TABLE --%>
										<div class='spacer'></div>
										<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr>
												<td class="detailHead">Generate Caseplan</td>
											</tr>
											<tr>
      								<td colspan='0'>
      									<table align="center" width='100%' cellpadding="4" cellspacing="1">
	              											
	              											<tr>
      											<td class='formDeLabel' colspan='2' nowrap='nowrap'>
    	  											<table width="100%">
    		  											<tr>
    			  											<td class='formDeLabel'><bean:message key="prompt.priorServices"/> 
      														  <logic:notEqual name="caseplanForm" property="action" value="view">
      															  &nbsp;
                            					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                            						<tiles:put name="tTextField" value="priorServices" />
                            						<tiles:put name="tSpellCount" value="spellBtn1" />
                          						</tiles:insert>
                          						(Max. characters allowed: 255)
      															</logic:notEqual> 
																	</td>			  											
    			  												</tr>
    	  											</table>
      											</td>
      										</tr>
      										<tr>
      										 <td class='formDe' colspan='2'>
      												 <%-- <logic:equal name="caseplanForm" property="caseplanInfoEditable" value="true"> 
      													<html:textarea style="width:100%" rows="3" name="caseplanForm" property="priorServices" onmouseout="textCounter(this, 255);" onkeydown="textCounter( this, 255 )"/>
      												</logic:equal> --%>
      												<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
      														<bean:write name="caseplanForm" property="priorServices"/>
      												</logic:notEqual> 
      											</td>      										   
      										</tr>
      										
      										<tr>  
												<td class='formDeLabel' width="50%"><bean:message key="prompt.supervisionLevel"/></td>
												<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<td class='formDe' width="50%"><bean:write name="caseplanForm" property="supervisionLevel"/></td>
												</logic:notEqual>
											</tr>
      											
											<tr>
      											<td class='formDeLabel' colspan='2' nowrap='nowrap'>
    	  											<table width="100%">
    		  											<tr>
    			  											<td class='formDeLabel'><bean:message key="prompt.contactInformation"/> 
      														  <logic:notEqual name="caseplanForm" property="action" value="view">
      															  &nbsp;
                            					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                            						<tiles:put name="tTextField" value="GenerateCaseplanDetails" />
                            						<tiles:put name="tSpellCount" value="spellBtn2" />
                          						</tiles:insert>
                          						(Max. characters allowed: 255)
      															</logic:notEqual> 
																	</td>			  											
    			  												</tr>
    	  											</table>
      											</td>
      										</tr>
      										<tr>
      										 <td class='formDe' colspan='2'>
      												  <%-- <logic:equal name="caseplanForm" property="caseplanInfoEditable" value="true">  
      													<html:textarea style="width:100%" rows="3" name="caseplanForm" property="contactInformation" onmouseout="textCounter(this, 255);" onkeydown="textCounter( this, 255 )"/>
      											    </logic:equal> --%>
      												<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
      														<bean:write name="caseplanForm" property="contactInformation"/>
      												</logic:notEqual>  
      											</td>
      										   
      										</tr>	
																	
															</table>
														</td>
													</tr>
										</table>
										<%-- END casefile TABLE --%>
										
										
										
<%-- begin Participation in  development of caseplan and distribution--%>
				<div class='spacer'></div>
					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
						<tr>
							<td class="detailHead">Participation in  Development of Caseplan and Distribution</td>
						</tr>
						<tr>						
      						<td colspan='0'>	
      						
      							<table align="center" width='100%' >         			
		    						<tr>
		    							<td class='formDeLabel'></td>
		    							<td class='formDeLabel' align="center">Child</td>  
		    							<td class='formDeLabel' align="center">Family</td>
		    							<td class='formDeLabel' align="center">CareGiver</td>
		    							<td class='formDeLabel'>
		    								<table border="0">
		    									<tr>
		    										<td class='formDeLabel' align="right"></td>
		    										<td class='formDeLabel' align="center">Other</td>
		    									</tr>
		    									<tr>
		    										<td class='formDeLabel'>Name</td>
		    										<td class='formDe'>		    									
			    										 <logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
															<bean:write name="caseplanForm" property="othername"/>
														</logic:notEqual>														
		    										 </td>
		    									</tr>
		    								</table>
		    							</td>										
										</tr>
										
										<tr>
		    							<td class='formDeLabel'>Date Notified</td>
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="childDtNotified"/>
											</logic:notEqual>
										</td>  
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="familyDtNotified"/>
											</logic:notEqual>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="caregiverDtNotified"/>
											</logic:notEqual>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="otherDtNotified"/>
											</logic:notEqual>
										</td> 										
										</tr>	
										
										<tr>
		    							<td class='formDeLabel'>Method of Notification</td>
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="childNotifMethod"/>
											</logic:notEqual>
										</td>  
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="familyNotifMethod"/>
											</logic:notEqual>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="caregiverNotifMethod"/>
											</logic:notEqual>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="otherNameNotifMethod"/>
											</logic:notEqual>
										</td> 										
										</tr>
										
										<tr>
		    							<td class='formDeLabel'>Date of Participation</td>
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="childDtOfParticipation"/>
											</logic:notEqual>
										</td>  
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="familyDtOfParticipation"/>
											</logic:notEqual>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="caregiverDtOfParticipation"/>
											</logic:notEqual>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="otherNameDtOfParticipation"/>
											</logic:notEqual>
										</td> 										
										</tr>
										
										<tr>
		    							<td class='formDeLabel'>Date Copy Received/Mailed</td>
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="childMailedDt"/>
											</logic:notEqual>
										</td>  
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="familyMailedDt"/>
											</logic:notEqual>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="caregiverMailedDt"/>
											</logic:notEqual>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="otherNameMailedDt"/>
											</logic:notEqual>
										</td> 										
										</tr>							
								</table>
							</td>
						</tr>
					</table>
<%-- END Participation in  development of caseplan and distribution --%> 
										
										
<!-- title IV E Start-->							
										
<div class='spacer'></div>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td class="detailHead">Title IV E Candidacy</td>
	</tr>
	<tr>	
	
		<td colspan='0'>
			<table align="center" width='100%' cellpadding="4" cellspacing="1">
				<tr>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.isJuvFosterCareCand"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<!--<bean:write name="caseplanForm" property="juvFosterCareCandidate"/>
						-->
						<logic:equal name="caseplanForm" property="juvFosterCareCandidateStr" value="Yes"> Yes</logic:equal>
						<logic:equal name="caseplanForm" property="juvFosterCareCandidateStr" value="No"> No</logic:equal>
						</logic:notEqual>
					</td>
				</tr>
				
				<tr  id='socialInvestDt' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.socialInvestHistRepDated"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<bean:write name="caseplanForm" property="socialHistDated"/>
						</logic:notEqual>
					</td>
				</tr>				
				<tr  id='psychologicalDt' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.psychological"/>&nbsp;<bean:message key="prompt.report"/>&nbsp;<bean:message key="prompt.dated"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<bean:write name="caseplanForm" property="psychologicalRepDated"/>
						</logic:notEqual>	
					</td>
				</tr>
				<tr  id='riskAssessDt' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.riskAssessdated"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<bean:write name="caseplanForm" property="riskAssesmentDated"/>
						</logic:notEqual>
					</td>
				</tr>
				
				<tr  id='otherDt' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.other"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<bean:write name="caseplanForm" property="otherDated"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr  id='otherExplain' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.explain"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<bean:write name="caseplanForm" property="explanation"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.comments"/></td>
					<td class='formDe' width="50%">						
							<bean:write name="caseplanForm" property="titleIVEComment"/>
					</td>
				</tr>
				<tr>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.dateDetermMade"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<bean:write name="caseplanForm" property="dtDeterminationMade"/>
						</logic:notEqual>						
					</td>
				</tr>
			</table>
		</td>
	
	
	</tr>
</table>
<div class="spacer"></div>										
<!--end of title IV E -->					
						
										
										  <%-- BEGIN BUTTON TABLE --%>

										<div class="spacer"></div>
										<table border="0" align="center">
                  	<tr>                  	
                  		<td align="center">  
							<html:submit property="submitAction"><bean:message key="button.generateCaseplan"/></html:submit>
							</td>                  		
                    </tr>
                  </table>
										<div class="spacer"></div>
										<%-- END BUTTON TABLE --%>
									</td>
							</tr>
						</table>
						<div class="spacer"></div>
					</td>
				</tr>
			</table>
			<%-- END DETAIL TABLE --%> <br>
			<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
		</td>
	</tr>
</table>

</html:form>
<div class='spacer'></div>
<logic:present name="statusReport">
  <logic:equal name="statusReport" value="confirmFinal">
    <script type="text/javascript">
      goNav('/<msp:webapp/>handleGenerateCaseplan.do?submitAction=<bean:message key="button.print"/>&selectedValue=<bean:write name="caseplanForm" property="selectedValue"/>');
    </script>
  </logic:equal>
</logic:present>
</body>
</html:html>

