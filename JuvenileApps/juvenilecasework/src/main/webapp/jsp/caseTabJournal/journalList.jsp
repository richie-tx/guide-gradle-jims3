<!DOCTYPE HTML>
<%-- Used in listing journal entries for a casefile --%>
<%--MODIFICATIONS --%>
<%-- 04/30/2008	Uma Gopinath Created JSP --%>
<%-- 08/31/2015	RCarter	   #29425 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading"/> - journalList.jsp</title>
<%@include file="../jQuery.fw"%>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
<%--HELP JAVASCRIPT FILE --%> 
<%--<SCRIPT SRC="../js/help.js" /> --%>
<%--APP JAVASCRIPT FILE --%>
<%-- tiles:insert page="/js/app.js" / --%>   

<script type='text/javascript'>
function changeFormSettings(theForm, theTargetString, button, theActionString) 
{
	changeFormActionURL( theForm, '/JuvenileCasework/'+theActionString, false); 
	changeFormTarget(theForm,theTargetString) ; 
	//if the target is not a new window then disable to prevent multiple submissions
	if(theTargetString != 'new') 
	{
		disableSubmit(button, theForm);
	}
} 

function changeFormTarget(theForm, theTargetString) 
{   
	if(theTargetString == null || theTargetString == "") 
	{
    theForm.target = "content";
	}
	else 
	{
	  theForm.target = theTargetString;
	}
	return true;
}


	/*  $(document).ready(function() {
		$("textarea[name=txtcomments]").each(function() {

			var noofLines = $(this).val().split('\n');
			var rows = 1;

			if (noofLines != undefined) {
				rows = (noofLines.length + 1)				
			}
			
			// if legacy text do not have formatting. use default row length.
			if((rows == 1 || rows == 2) && $(this).val().length > 680)
			{
				rows = 5;
			}
			
			$(this).prop('rows', rows);
		});
		
		$("textarea[name=txtucomments]").each(function() {

			var noofLines = $(this).val().split('\n');
			var rows = 1;

			if (noofLines != undefined) {
				rows = (noofLines.length + 1)				
			}
			
			// if legacy text do not have formatting. use default row length.
			if((rows == 1 || rows == 2) && $(this).val().length > 680)
			{
				rows = 5;
			}
			
			$(this).prop('rows', rows);
		});
	});  */
</script>


</head> 
<%--END HEAD TAG--%>

<body topmargin='0' leftmargin="0">
<html:form action="/displayJuvenileCasefileJournalList" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|246">
<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Journal List</td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Expand (+) or collapse (-) a Journal Category to view its Journal entries.</li>
        <!-- <li>Select an Include checkbox at Journal Category level to include all Journal entries for the selected Category.</li>
        <li>Select an Include checkbox for a specific Journal entry to include it.</li> -->
        <!-- <li>Select <b>Generate Journal Report</b> button to generate report.</li>  -->
      </ul>
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<!-- Begin Detail Table -->
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign='top'>
			<!-- Casefiles (1st row) tabs start -->
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="casefiledetailstab"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>		
			<!-- End Casefiles (1st row) tabs start -->

  		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  			  <td valign='top' align='center'>
				
            <!-- Begin Casefile info (2nd row) Tabs -->
						<div class='spacer'></div>
        		<table align="center" width='98%' border="0" cellpadding="0" cellspacing="0">
   						<tr>
   							<td valign='top'>
   								<!-- tabs start -->
   								<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" value="journaltab"/>
										<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
									</tiles:insert>	
   								<!-- tabs end -->
   							</td>
   						</tr>
   				 	  <tr>
     			  		<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
    			  	</tr>
    			  </table>
            <!-- End Casefile info (2nd row) Tabs -->
				
  					<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
            	<tr>
                <td align='center'>

       						<!-- start Journal table -->
      						<div class='spacer'></div>
        					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
        						<tr> <!-- table title bar -->
        							<td valign='top' colspan='2' class='detailHead'>Journals</td>
        						</tr>

                    <!-- this is the very top level table that holds the results of the search  -->      
        						<tr>
        							<td valign='top' colspan='2'>
        								<table width='100%' cellpadding='4' cellspacing='1' bgcolor='#999999'>      									 
        									<tr bgcolor='#cccccc' >
        										<td class="subhead" valign='top' nowrap='nowrap' align='center'>Journal Records</td>
        									</tr>
      										<!-- begin Journal Category (logic:iterate) (i.e. Activity)   -->
      										<logic:notEmpty name="journalForm" property="activityEntries">
      											<!-- added for facility activities : starts #39915 -->
				            					<tr>
												 <td colspan='2' class='formDe'>												
												 	<table  width='100%' class='borderTableGrey'>
														<tr>
														  <td width='1%' class="detailHead" colspan=5>
															<a href="javascript:showHideMulti('facility', 'fchar', 1,'/<msp:webapp/>');" >
									                 		<img border='0' src="/<msp:webapp/>images/expand.gif" name="facility"></a>&nbsp;<bean:message key="prompt.facilityActivities" />
									                      </td>																	
														</tr>
														<tr class="hidden" id="fchar0">
															<td colspan="2">			
															<logic:notEmpty name="journalForm" property="facEventEntries">
										      				<logic:iterate name="journalForm" property="facEventEntries" id="facEventsIter">     
										      				  <table id='facEntry0' align='center' width='100%'>
											      				  <tr class='formDeLabel'>													
		                 											<td><bean:write name="facEventsIter" property="activityDate" formatKey="date.format.mmddyyyy"/>
		                 											<bean:write name="facEventsIter" property="activityTime"/> 
		                 											<bean:write name="facEventsIter" property="activityDesc"/> </td>
		                 										  </tr>
											      				  <tr>
															        <td colspan='2' class='normalRow'><bean:write name="facEventsIter" property="comments"/></td>             			
							                    				  </tr>    
										      				 </table>
										      				</logic:iterate>
															</logic:notEmpty>
															<logic:empty name="journalForm" property="facEventEntries">
																No Facility Activites Available
															</logic:empty>
														</td>
													</tr>
													</table>														
												</td>
												</tr>
												<!-- added for facility activities : ends  #39915-->
	      										<tr>
	      										  <td colspan='2' class='formDe'>
	      											  <table width='100%' class='borderTableGrey'>
	            										<tr>
	                       									<td class='detailHead'>															
															  <a href="javascript:showHideMulti('activities', 'achar', 2,'/<msp:webapp/>');" >
	                                    					  <img border="0" src="/<msp:webapp/>images/expand.gif" name="activities"></a>&nbsp;Activities
	                       									</td>
	            										</tr>
	      												<tr class="hidden" id="achar1">
	      													  <td colspan='2'>  																
																<logic:iterate name="journalForm" property="activityEntries" id="activitesIter">      														  
		      													  <table id='activityEntry0' align='center' width='100%'>      															
	                 												<tr class='formDeLabel'>													
	                 													<td><bean:write name="activitesIter" property="activityDate" formatKey="date.format.mmddyyyy"/>
	                 													<bean:write name="activitesIter" property="activityTime"/> 
	                 													<bean:write name="activitesIter" property="activityDesc"/> </td><td></td>
	                 													<logic:notEmpty name="activitesIter" property="latitude">
	                 														<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_VIEW_MAP%>'>
							            									 <tr>
							            										<td class=formDeLabel nowrap><bean:message key="prompt.activity"/>Address</td>
							            										<td class=formDeLabel nowrap/>
							            										<a href="javascript:openWindow('https://www.google.com/maps/?q=loc:<bean:write name="activitesIter" property="latitude" />,<bean:write name="activitesIter" property="longitude" />')">Address</a>
							            										<html:hidden name='activitesIter' property='latitude' />
							            										<html:hidden name='activitesIter' property='longitude' />
							           										 </td>            										
							            									</tr>
							            									</jims2:isAllowed>
							            								</logic:notEmpty>
	                 												</tr>
	                 												<tr>
		                                       							<td colspan='2' class='normalRow'><bean:write name="activitesIter" property="comments"/></td>
	                 												</tr>
	                 												<logic:notEmpty name="activitesIter" property="updateComments">
	                 												<tr>
	                 												  <td colspan='2' class='normalRow'><br/><b>Update Comments:</b><br/><bean:write name="activitesIter" property="updateComments"/></td>	                 												  
	                 												</tr>	
	                 												</logic:notEmpty>		
			      												  </table>      														
	      														  </logic:iterate>
	      														</td>
	      													</tr>
	      												</table>
	      											</td>
	      										</tr>
      										</logic:notEmpty>
      										<!-- end first Journal Category (Activities) -->

      										<!-- Begin Journal Category Calendar Events -->
      										<logic:notEmpty name="journalForm" property="calEventEntries">
      										<div class='spacer'></div>
												
	      										<tr>
	      										  <td colspan='2' class='formDe'>
	      											 <table width='100%' class='borderTableGrey'>
	            										<tr>
						                                 <td width='2%' class='detailHead'>
						                                 	<a href="javascript:showHideMulti('calendar', 'hchar', 3,'/<msp:webapp/>');" >
						                                 	<img border='0' src="/<msp:webapp/>images/expand.gif" name="calendar"></a>&nbsp;Calendar Events</td>
	            										</tr>       							
	            										<tr class="hidden" id="hchar0">
	            										
														<td valign='top'>												
															<table align='right' width='98%' class='borderTableGrey'>
																	<tr>
																		  <td width='1%' class="detailHead" colspan=5>
																			<a href="javascript:showHideMulti('juvenile', 'jchar', 2,'/<msp:webapp/>');" >
						                                 					<img border='0' src="/<msp:webapp/>images/expand.gif" name="juvenile"></a>&nbsp;<bean:message key="prompt.juvenileEvents" />
						                                 				  </td>																	
																	</tr>
																	<tr class="hidden" id="jchar0">
																		<td colspan="2">			
																			<logic:notEmpty name="journalForm" property="juvEventEntries">
						      														<logic:iterate name="journalForm" property="juvEventEntries" id="juvEventsIter">     
						      														  <table id='juvEntry0' align='center' width='100%'>
						      														  	<tr class='formDeLabel'>
										                                 					<bean:write name="juvEventsIter" property="eventDate" formatKey="date.format.mmddyyyy"/> <bean:write name="juvEventsIter" property="eventTime"/>              			
											                    							<jims2:if name="juvEventsIter" property="eventTypeCategory" value="I" op="equal">
																								<jims2:then>
																									<bean:write name="juvEventsIter" property="interviewType"/>
																								</jims2:then>
																								<jims2:elseif name="juvEventsIter" property="eventTypeCategory" value="P" op="equal">
																									<bean:write name="juvEventsIter" property="serviceName"/>
																								</jims2:elseif>
																								<jims2:else><bean:write name="juvEventsIter" property="eventType"/></jims2:else>
																							</jims2:if>	  	             										
		                    															</tr>    
		                    															<tr class='normalRow'>
									                    									<logic:notEqual name="juvEventsIter" property="progressNotes" value="">
															                                     <td width=80%>Progress Notes :  <bean:write name="juvEventsIter" property="progressNotes"/></td>
														                                    </logic:notEqual>
														                                    <logic:notEqual name="juvEventsIter" property="juvenileAttendanceStatus" value="">
														                                    	<logic:notEqual name="juvEventsIter" property="juvenileAttendanceStatus" value="CONFIRMED">
															                                    	<logic:notEqual name="juvEventsIter" property="juvenileAttendanceStatus" value="UNCONFIRMED">
															                                    		<td width=20% nowrap>Attendance Status :  <bean:write name="juvEventsIter" property="juvenileAttendanceStatus"/></td>
															                                    	</logic:notEqual>
														                                    	</logic:notEqual>
													                                    	</logic:notEqual>
																		                </tr>
																						<tr class='alternateRow'>
													                                      <logic:notEqual name="juvEventsIter" property="interviewSummaryNotes" value="">
														                                     <td colspan='4'>Summary Notes :  <bean:write name="juvEventsIter" property="interviewSummaryNotes"/></td>
														                                    </logic:notEqual>
																		                </tr>
																						<tr class='normalRow'>
																                                <td colspan='4'>Event Comments :  <bean:write name="juvEventsIter" property="eventComments"/></td>
														                   				</tr>  				
						      														  </table>
						      														 </logic:iterate>
						      													
																			</logic:notEmpty>
																			<logic:empty name="journalForm" property="juvEventEntries">
																				No Juvenile Events Available
																			</logic:empty>
																		</td>
																	</tr>
																</table>														
															</td>
														</tr>	
														<tr class="hidden" id="hchar1">
	            											<td valign='top'>
	            												<table align='right' width='98%' class='borderTableGrey'>
																	<tr>
																		<td width='1%' class="detailHead" colspan=5>
																			<a href="javascript:showHideMulti('provider', 'spchar', 2,'/<msp:webapp/>');" >
						                                 					<img border='0' src="/<msp:webapp/>images/expand.gif" name="provider"></a>&nbsp;
						                                 					<bean:message key="prompt.serviceProviderEvents" />
																		</td>
																	</tr>
																	<tr class="hidden" id="spchar0">
																		<td colspan="2">			
																			<logic:notEmpty name="journalForm" property="spEventEntries">
																											
						      														<logic:iterate name="journalForm" property="spEventEntries" id="spEventsIter">     
						      														  <table id='spEntry0' align='center' width='100%'>
						      														  	<tr class='formDeLabel'>
	                                 														<bean:write name="spEventsIter" property="eventDate" formatKey="date.format.mmddyyyy"/> <bean:write name="spEventsIter" property="eventTime"/>
	                                 														<jims2:if name="spEventsIter" property="eventTypeCategory" value="I" op="equal">
																								<jims2:then>
																									<bean:write name="spEventsIter" property="interviewType"/>
																								</jims2:then>
																								<jims2:elseif name="spEventsIter" property="eventTypeCategory" value="P" op="equal">
																									<bean:write name="spEventsIter" property="serviceName"/>
																								</jims2:elseif>
																								<jims2:else> 
																									<bean:write name="spEventsIter" property="eventType"/>
																								</jims2:else>
																							</jims2:if>
	                                 													</tr> 
	                                 													<tr class='normalRow'>
												                    					<logic:notEqual name="spEventsIter" property="progressNotes" value="">
														                                     <td width=80%>Progress Notes :  <bean:write name="spEventsIter" property="progressNotes"/></td>														                                     
													                                    </logic:notEqual>
													                                    <logic:notEqual name="spEventsIter" property="juvenileAttendanceStatus" value="">
													                                    	<logic:notEqual name="spEventsIter" property="juvenileAttendanceStatus" value="CONFIRMED">
													                                    	<logic:notEqual name="spEventsIter" property="juvenileAttendanceStatus" value="UNCONFIRMED">
													                                    		<td width=20% nowrap>Attendance Status :  <bean:write name="spEventsIter" property="juvenileAttendanceStatus"/></td>
													                                    	</logic:notEqual>
													                                    	</logic:notEqual>
													                                    </logic:notEqual>
																	                    </tr>
																						<tr class='alternateRow'>
												                                     		 <logic:notEqual name="spEventsIter" property="interviewSummaryNotes" value="">
													                                     		<td colspan='8'>Summary Notes :  <bean:write name="spEventsIter" property="interviewSummaryNotes"/></td>
													                                    	</logic:notEqual>
																	                    </tr>
																						<tr class='normalRow'>
															                                <td colspan='8'>Event Comments :  <bean:write name="spEventsIter" property="eventComments"/></td>
													                   					</tr>
						      														  </table>
						      														 </logic:iterate>
						      														
																			</logic:notEmpty>
																			<logic:empty name="journalForm" property="spEventEntries">
																				No Service Provider Events Available
																			</logic:empty>
																		</td>
																	</tr>
	            												</table>
	            											</td>
	            										</tr>	
																
													</table>
												</td>
												</tr>							
     											</logic:notEmpty>		 

	      										<!-- begin Journal Category (logic:iterate) (Goals) -->
	      										<logic:notEmpty name="journalForm" property="goalEntries">
		      										<tr>
		      										  <td colspan='2' class='formDe'>
		      											  <table width='100%' class='borderTableGrey'>
		            										<tr>
		                                  <td class='detailHead'>						      									
		                                    <a href="javascript:showHideMulti('goals', 'gchar', 1,'/<msp:webapp/>');" >
		                                    <img border="0" src="/<msp:webapp/>images/expand.gif" name="goals"></a>&nbsp;Goals
		                                  </td>
								            				</tr>
																		<tr class="hidden" id="gchar0">
			    													  <td colspan='2'>
																				<logic:iterate name="journalForm" property="goalEntries" id="goalsIter">    														
				    														  <table align='center' width='100%'>
							                    					<tr class='formDeLabel'>
							                    						<td > <bean:write name="goalsIter" property="entryDate" formatKey="date.format.mmddyyyy"/> <bean:write name="goalsIter" property="goalDescription"/> </td>
							                    					</tr>	      															
							                    					<tr class='normalRow'>
		                                        	<td colspan='2' >End Recommendations : <bean:write name="goalsIter" property="endRecommendations"/></td>
							                    					</tr>
										       									<tr class='alternateRow'>
		                                        	<td colspan='2' >Progress Notes : <bean:write name="goalsIter" property="progressNotes"/></td>
							                    					</tr>
				      														</table>
				      													</logic:iterate>
			      													</td>
			      												</tr>
		      											 </table>
	      											</td>
	      										</tr>
      										</logic:notEmpty>
      										<!-- end Goals  -->

      										<!-- begin Journal Category Traits-->
      										<logic:notEmpty name="journalForm" property="traitEntries">
      										<tr>
      										  <td colspan='2' class='formDe'>
      											  <table width='100%' class='borderTableGrey'>
            										<tr>
                                  <td class='detailHead'>						      									
                                    <a href="javascript:showHideMulti('traits', 'tchar', 1,'/<msp:webapp/>');" >
                                    <img border="0" src="/<msp:webapp/>images/expand.gif" name="traits"></a>&nbsp;Traits
                                  </td>
						            				</tr>
																<tr class="hidden" id="tchar0">
	    													  <td colspan='2'>
																		<logic:iterate name="journalForm" property="traitEntries" id="traitsIter">    														
		    														  <table align='center' width='100%'>
					                    					<tr class='formDeLabel'>
					                    						<td ><bean:write name="traitsIter" property="traitName"/> (<bean:write name="traitsIter" property="traitStatus"/>)</td>
					                    					</tr>	      															
					                    					<tr class='normalRow'>
		                                     	<td colspan='2' > <bean:write name="traitsIter" property="comments"/></td>
					                    					</tr>
		      														</table>
		      													</logic:iterate>
	      													</td>
	      												</tr>
	      											 </table>
	      											</td>
	      										</tr>
      										</logic:notEmpty>
      										<!-- end Goals  -->

      										<!-- begin Journal Category (logic:iterate) (i.e. Risk Analysis)   -->
      										<logic:notEmpty name="journalForm" property="riskEntries">
	      										<tr>
	      										  <td colspan='2' class='formDe'>
	      											  <table width='100%' class='borderTableGrey'>
	            										<tr>
	                      						<td class='detailHead'>															 																
	                   								  <a href="javascript:showHideMulti('risk', 'rchar', 1,'/<msp:webapp/>');" >
				                              <img border="0" src="/<msp:webapp/>images/expand.gif" name="risk"></a>&nbsp;Screening/Forms
	                      						</td>
	            										</tr>
	      													<tr class="hidden" id="rchar0">
	      													  <td colspan='2'>  																	
																			<logic:iterate name="journalForm" property="riskEntries" id="riskIter">      														 
		      														  <table align='center' width='100%'>      															 
	                 												<tr class='formDeLabel'>                                       									
	                 													<td ><bean:write name="riskIter" property="entryDate" formatKey="date.format.mmddyyyy"/> <bean:write name="riskIter" property="title"/></td>
	                 												</tr>
	                 												<tr>
			                                       <td colspan='2' class='normalRow'><bean:write name="riskIter" property="comments" filter="false"/></td>
	                 												</tr>      															
																				</table>
		      														  <!-- End first RA Journal entry -->
	      														  </logic:iterate>
	      														</td>
	      													</tr>
	      												</table>
	      											</td>
	      										</tr>
	      										</logic:notEmpty>
	      										<!-- end Risk Analysis -->

	      										<!-- begin Journal Category Program Referral -->
	      										<logic:notEmpty name="journalForm" property="progReferralEntries">
		      										<tr>
		      										  <td colspan='2' class='formDe'>
		      											  <table width='100%' class='borderTableGrey'>
		            										<tr>
	                        						<td class='detailHead'>	 														  
	                     								  <a href="javascript:showHideMulti('prog', 'prchar', 1,'/<msp:webapp/>');" >
				                                <img border="0" src="/<msp:webapp/>images/expand.gif" name="prog"></a>&nbsp;Program Referrals
	                        						</td>
		            										</tr>
		      													<tr class="hidden" id="prchar0">
		      													  <td colspan='2'>  																	
																				<logic:iterate name="journalForm" property="progReferralEntries" id="refIter">      														 
			      														  <table align='center' width='100%'>      															 
	                   												<tr class='formDeLabel'>                                       									
	                   													<td ><bean:write name="refIter" property="sentDate" formatKey="date.format.mmddyyyy"/> <bean:write name="refIter" property="providerProgramName"/> </td>
	                   												</tr>
	                   												<logic:iterate name="refIter" property="referralComments" id="commentsIter"> 
	                   												<tr>
				                                       <td colspan='2' class='normalRow'><bean:write name="commentsIter" property="commentText"/> [<bean:write name="commentsIter" property="commentsDate" formatKey="date.format.mmddyyyyHHmm"/> - <bean:write name="commentsIter" property="userName"/>]</td>
	                   												</tr>
	                 												</logic:iterate>      															
																				</table>
	      														  <!-- End first RA Journal entry -->
	      														  </logic:iterate>
		     														</td>
		     													</tr>
	      												</table>
	      											</td>
	      										</tr>
      										</logic:notEmpty>
      										<!-- end Risk Analysis -->
	
      										<!-- begin Journal Category (logic:iterate) (i.e. Casefile Closing)   -->
      										<logic:notEmpty name="journalForm" property="closingEntries">
	      										<tr>
	      										  <td colspan='2' class='formDe'>
	      											  <table width='100%' class='borderTableGrey'>
	            										<tr>
	                                  <td class='detailHead'>	      														 																		
	                                  	<a href="javascript:showHideMulti('closing', 'clchar', 1,'/<msp:webapp/>');" >
							                        <img border="0" src="/<msp:webapp/>images/expand.gif" name="closing"></a>&nbsp;Closing Information
	                                  </td>
	            										</tr>
	      													<tr class="hidden" id="clchar0">
	      													  <td colspan='2'>  																	
																			<logic:iterate name="journalForm" property="closingEntries" id="closeIter">  														   
		      														  <table id='closingEntry0' align='center' width='100%'>      															 
		                    									<tr class='formDeLabel'>				                                        					
		                    										<td >Closing Evaluation</td>
		                    									</tr>				      														
		                    									<tr class='normalRow'>
	                                 					<td colspan='2'><bean:write name="closeIter" property="closingEvaluation"/></td>
		                    									</tr>
						      										  </table>	

						      										  <logic:notEqual  name="closeIter" property="closingComments" value="">		      														 
							      										  <table align='center' width='100%'>
							      												<tr class='formDeLabel'>				                                        				
			                    										<td >Closing Comments</td>
				                    								</tr>			      															 
				                    								<tr class='normalRow'>
		                                   				<td colspan='2'><bean:write name="closeIter" property="closingComments"/></td>
				                    								</tr>      																
			      															</table> 
		      															</logic:notEqual>     														  
		     														  </logic:iterate> 														
		     														</td>
		     													</tr>
		     												</table>
		     											</td>
		     										</tr>
		   										</logic:notEmpty>
		   										<!-- end last Journal Category (Casefile Closing) -->      
		     								</table>
		                   </td>
		                 </tr>
		           		</table>
	               <!-- End Journal table -->

                	<!-- Begin Button table -->
                  <div class='spacer'></div> 
              		<table border="0" cellpadding='1' cellspacing='1' align='center' width='100%'>
              		  <tr>
	                      <td align="center">
	                       <!-- had to redefine the button definitions due to bug 135713 -->
	                        <%-- <html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this,  'displayJuvenileCasefileJournalList.do?submitAction=Back');"><bean:message key="button.back"/></html:submit>                       
	                          <html:submit property="submitAction" onclick="changeFormSettings(this.form, 'new', this,  'displayJuvenileCasefileJournalSearch.do?submitAction=Print');"><bean:message key="button.generateJournalReport"/></html:submit>
	                        <html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this,  'displayJuvenileCasefileJournalList.do?submitAction=Cancel');"><bean:message key="button.cancel"/></html:submit>    
	                      --%>
	                        <html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
	                        <input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileCasefileJournalSearch.do?submitAction=Print')" value="<bean:message key='button.generateJournalReport'/>"/>
	                        <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>   
	                        </td>
	                   </tr>
	                   <tr>
	                   	<td align="center">
	                      	 <jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CASE_REV_DRAFT%>'>   
		                      	 <%-- <html:submit property="submitAction" onclick="changeFormSettings(this.form, 'new', this,  'displayJuvenileCasefileJournalSearch.do?submitAction=PrintSummary&Draft=Y');"><bean:message key="button.generateDraftJournalSummaryReport"/></html:submit>                     
		                      --%>
		                      <input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileCasefileJournalSearch.do?submitAction=PrintSummary&Draft=Y')" value="<bean:message key='button.generateDraftJournalSummaryReport'/>"/>
	                        </jims2:isAllowed> 
		                     <jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CASE_REV_FINAL%>'>    
		                         <%-- <html:submit property="submitAction" onclick="changeFormSettings(this.form, 'new', this,  'displayJuvenileCasefileJournalSearch.do?submitAction=PrintSummary&Draft=N');"><bean:message key="button.generateJournalSummaryReport"/></html:submit>                       		
							 --%>
							 <input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileCasefileJournalSearch.do?submitAction=PrintSummary&Draft=N')" value="<bean:message key='button.generateJournalSummaryReport'/>"/>
	                        </jims2:isAllowed> 
	                    </td>
              		  </tr>
              		</table>
              		<!-- End Button table -->
					<div class='spacer'></div>
                </td>
              </tr>
            </table>
				<div class='spacer'></div>
      		</td>
      	</tr>
      </table>
		</td>
	</tr>
</table>
<!-- End Detail Table -->

<div align='center'><script type="text/javascript">renderBackToTop();</script></div>

</html:form>
</body>
</html:html>
 