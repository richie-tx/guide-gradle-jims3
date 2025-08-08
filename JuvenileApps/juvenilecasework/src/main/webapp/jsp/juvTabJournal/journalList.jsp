<!DOCTYPE HTML>
<%-- Used in listing journal entries for a casefile --%>
<%--MODIFICATIONS --%>
<%-- 04/30/2008	Uma Gopinath Created JSP --%>
<%-- 10/21/2008 Clarence Shimek  defect#54984 removed extra dash in heading  --%>
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

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
<%--HELP JAVASCRIPT FILE --%> 
<%--<SCRIPT SRC="../js/help.js" /> --%>
<%--APP JAVASCRIPT FILE --%>
<%-- tiles:insert page="/js/app.js" / --%>   
</head> 
<%--END HEAD TAG--%>

<body topmargin='0' leftmargin="0">
<html:form action="/displayJuvenileJournalList" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|18">
<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	  <tr>
	    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Journal List</td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Expand (+) or collapse (-) a Supervision # to view its Journal Categories.</li>
        <li>Expand (+) or collapse (-) a Journal Category to view its Journal entries.</li>

        <!-- 8apr008 - mjt - commented this out because we might have to "re-enable this checkbox
          <li>Select an Include checkbox for a specific Journal Category to include it.</li>
				-->
        <!-- <li>Select <b>Generate Journal Report</b> button to generate report.</li>-->
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

<!-- Juv Profile Header start -->
<table cellpadding="1" cellspacing="0" border="0" width="100%" align="center">
	<tr>
		<td >
			<%--header info start--%> 
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader" />
			</tiles:insert> <%--header info end--%>
		</td>
	</tr>
</table>
<!-- Juv Profile Header end-->

<!-- BEGIN DETAIL TABLE -->
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign='top'>
		  <!-- Profile Master tabs row (first) -->
    	<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  			<tr>
  				<td valign='top'>
    				<!-- tabs start -->
  					<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="casefilestab"/>
							<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
						</tiles:insert>		
    				<!-- tabs end -->
  				</td>
  			</tr>
        <tr>
          <td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
        </tr>
      </table>
		  <!-- End Profile Master tabs row (first) -->

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  			<tr>
  				<td valign='top' align='center'>

            <!-- Begin Profile Casefile Info (blue) tabs (2nd row) -->
						<div class='spacer'></div>
            <table align="center" width='98%' border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td valign='top'> 
                  <!-- tabs start -->
                  <tiles:insert page="../caseworkCommon/juvenileProfileCasefileTabs.jsp" flush="true">
  									<tiles:put name="tabid" value="journal"/>
  									<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
  								</tiles:insert>		
                  <!-- tabs end -->
                </td>
              </tr>
              <tr >
               <td bgcolor='#6699ff'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
              </tr>
            </table>
            <!-- End Profile Casefile Info (blue) tabs (2nd row) -->

		  			<!-- BEGIN Journal TABLE -->
		  			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
              <tr>
                <td align='center'>

									<!-- Start Journal table -->
									<div class='spacer'></div>
									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign='top' colspan='2' class='detailHead'>Journals
											   <logic:empty name="journalForm" property="juvJournalEntries">        							   
        							             (No Journal Entries)        							           
        							           </logic:empty>        							            
        							        </td>
										</tr>
										<tr>
			    						<td valign='top' colspan='2'>
			   								<table width='100%' cellpadding='4' cellspacing='1' bgcolor='#999999'> 									
			   									
			   									<tr bgcolor='#cccccc' >
			   										<td class="subhead" valign='top' nowrap='nowrap' align='center'>Journal Records - 			   										
			   										 	<logic:notEmpty name="journalForm" property="selectedValues">											   				
													   		Selected Casefile(s)	<bean:write name="journalForm" property="selectedValues"/>		        							             	     							           
		        							            </logic:notEmpty>
		        							            <logic:notEmpty name="journalForm" property="journalCategoryCd">
		        							            	& JournalCategory: 		<bean:write name="journalForm" property="journalCategoryCd"/>
		        							            </logic:notEmpty>
		        							             <logic:notEmpty name="journalForm" property="fromDate">
		        							            	 & Date Range
		        							             			<bean:write name="journalForm" property="fromDate"/> -
		        							             			<bean:write name="journalForm" property="endDate"/>  
		        							            </logic:notEmpty> 			   										
			   										</td>
			   										
			   									</tr>
			   									<tr>
		      									<td colspan='2' class='formDe'>
			      									<logic:notEmpty name="journalForm" property="juvJournalEntries">
				      									<logic:iterate name="journalForm" indexId="idx" property="juvJournalEntries" id="journalIter"> 
				      										<%-- <tr>
				      											<td colspan='2' class='formDe'> 
						      									  <table width='100%'>
						            								<tr>
		                               								<td colspan='2' class='detailHead'>	                                  					
		                             								   <a href="javascript:showHideMulti('toggle<bean:write name='idx'/>', 'phChar<bean:write name='idx'/>-', 1,'/<msp:webapp/>');" >
														               <img border="0" src="/<msp:webapp/>images/expand.gif" name="toggle<bean:write name='idx'/>"></a>&nbsp;Supervision # <bean:write name="journalIter" property="casefileId"/> 
		                               								</td>
		                               								</tr>
		                             		  					</table>
		                           		  						</td>
		                         		 					</tr> --%>
		                         		 					<tr <bean:write name='idx'/>>
																	<td colspan='2' class='formDe'>
																		  <table width='99%' align='center' cellpadding='2' cellspacing='0' border='0'>
				            												<tr>
				            										  			<td colspan='2' class='formDe'>
				            											  			<table width='100%' class='borderTableGrey' align='center'>
				            											  				<tr>
					                                        								<td colspan='2' class='formDe'>
					                                        									<logic:notEmpty name="journalIter" property="activityEntries">      
					                                        									
					                                        										<!-- added for facility activities : starts -- 48170 #39915 -->
															            							 <tr>
																										 <td colspan='2' class='formDe'>												
																										 	<table width='100%' class='borderTableGrey'>
																												<tr>
																												  <td width='1%' class="detailHead" colspan=5>
																													<a href="javascript:showHideMulti('facility<bean:write name='idx'/>', 'fchar<bean:write name='idx'/>', 1,'/<msp:webapp/>');" >
																				                        			<img border='0' src="/<msp:webapp/>images/expand.gif" name="facility<bean:write name='idx'/>"></a>&nbsp;<bean:message key="prompt.facilityActivities" />
																				                        		  </td>																	
																												</tr>
																												<tr class="hidden" id="fchar<bean:write name='idx'/>0">
																													<td colspan="2">			
																														<logic:notEmpty name="journalIter" property="facEventEntries">
																					      									<logic:iterate name="journalIter" property="facEventEntries" id="facEventsIter">     
																					      									  <table id='facEntry0' align='center' width='100%'>
																						      									  <tr class='formDeLabel'>													
													                 																<td><bean:write name="facEventsIter" property="activityDate" formatKey="date.format.mmddyyyy"/> <bean:write name="facEventsIter" property="activityDesc"/>#<bean:write name="facEventsIter" property="casefileId" /> </td>
													                 															  </tr>
																						      									  <tr>
																										                    		 <td colspan='2' class='normalRow'><bean:write name="facEventsIter" property="comments"/></td>             			
																		                    									  </tr>    
																					      									  </table>
																					      									</logic:iterate>
																														</logic:notEmpty>
																														<logic:empty name="journalIter" property="facEventEntries">
																															No Facility Activites Available
																														</logic:empty>
																													</td>
																												</tr>
																											</table>														
																										</td>
																									</tr>
																									<!-- added for facility activities : ends -->
					                                        																													
			      																					<tr>
												      										 			 <td colspan='2' class='formDe'>
												      											 			 <table width='100%' class='borderTableGrey'>
												            													<tr>
						                                  															<td class='detailHead'>															
																														<a href="javascript:showHideMulti('activities<bean:write name='idx'/>1', 'achar<bean:write name='idx'/>', 2,'/<msp:webapp/>');" >
												                                    									<img border="0" src="/<msp:webapp/>images/expand.gif" name="activities<bean:write name='idx'/>1"></a>&nbsp;Activities
												                                    								</td>
												                                    							</tr>
												                                    						</table>
						                                  												</td>
												            										</tr>
												            										<tr class="hidden" id="achar<bean:write name='idx'/>1">
												      													<td colspan='2'>  																
																										  <logic:iterate name="journalIter" property="activityEntries" id="activitesIter">      														  
													      													 <table id='activityEntry0' align='center' width='100%'>   															
										                    													<tr class='formDeLabel'>													
										                    														<td > <bean:write name="activitesIter" property="activityDate" formatKey="date.format.mmddyyyy"/> <bean:write name="activitesIter" property="activityDesc"/> #<bean:write name="activitesIter" property="casefileId" /> </td>
										                    													</tr>
										                    													<tr>
														                                     				  		<td colspan='2' class='normalRow'><bean:write name="activitesIter" property="comments"/></td>
										                    													</tr>			
													      														</table>      														
												      														</logic:iterate> 	
											      														 </td>
											      													</tr>																			
				      																			</logic:notEmpty>
		
				      																			<logic:notEmpty name="journalIter" property="calEventEntries">
														      										<tr>
														      										  <td colspan='2' class='formDe'>
														      											  <table width='100%' class='borderTableGrey'>
														            										<tr>
																			                                  <td class='detailHead'>						      														 
																				                                  <a href="javascript:showHideMulti('calendar<bean:write name='idx'/>', 'cachar<bean:write name='idx'/>', 1,'/<msp:webapp/>');" >
																			                                    <img border="0" src="/<msp:webapp/>images/expand.gif" name="calendar<bean:write name='idx'/>"></a>&nbsp;Calendar Events
																			                                  </td>
														            										</tr>
																										
														      													<tr class="hidden" id="cachar<bean:write name='idx'/>0">
														      													  <td colspan='2'> 								
		
															      														<logic:iterate name="journalIter" property="calEventEntries" id="calEventsIter">     
															      														  <table id='calEntry0' align='center' width='100%'>
															      														 
															                    										<tr class='formDeLabel'>
															                        									<bean:write name="calEventsIter" property="eventDate" formatKey="date.format.mmddyyyy"/> <bean:write name="calEventsIter" property="eventTime"/> # <bean:write name="calEventsIter" property="casefileId"/>               			
															                    										<jims2:if name="calEventsIter" property="eventTypeCategory" value="I" op="equal">
																																				<jims2:then>
																																					<bean:write name="calEventsIter" property="interviewType"/>
																																				</jims2:then>
																																				<jims2:elseif name="calEventsIter" property="eventTypeCategory" value="P" op="equal">
																																						<bean:write name="calEventsIter" property="serviceName"/>
																																				</jims2:elseif>
																																				<jims2:else><bean:write name="calEventsIter" property="eventType"/></jims2:else>
																																			</jims2:if>	                    										
															                    									</tr>      															
																                    								<tr class='normalRow'>
																                    									<logic:notEqual name="calEventsIter" property="progressNotes" value="">
																		                                     <td colspan='2'>Progress Notes :  <bean:write name="calEventsIter" property="progressNotes"/></td>
																	                                    </logic:notEqual>
																					                    			</tr>
																																		<tr class='alternateRow'>
																                                      <logic:notEqual name="calEventsIter" property="interviewSummaryNotes" value="">
																	                                     <td colspan='2'>Summary Notes :  <bean:write name="calEventsIter" property="interviewSummaryNotes"/></td>
																	                                    </logic:notEqual>
																					                    			</tr>
																																		<tr class='normalRow'>
															                                        <td colspan='2'>Event Comments :  <bean:write name="calEventsIter" property="eventComments"/></td>
																                    								</tr>
															      															</table>
															      														</logic:iterate>
														      														</td>
														      													</tr>
														      												</table>
													      												</td>
														      										</tr> 
														      									</logic:notEmpty>
		
														      									<logic:notEmpty name="journalIter" property="goalEntries">
														      										<tr>
														      										  <td colspan='2' class='formDe'>
														      											  <table width='100%' class='borderTableGrey'>
														            										<tr>
														                                  <td class='detailHead'>						      									
														                                    <a href="javascript:showHideMulti('goals<bean:write name='idx'/>', 'gchar<bean:write name='idx'/>', 1,'/<msp:webapp/>');" >
														                                    <img border="0" src="/<msp:webapp/>images/expand.gif" name="goals<bean:write name='idx'/>"></a>&nbsp;Goals
														                                  </td>
																				            				</tr>
																														<tr class="hidden" id="gchar<bean:write name='idx'/>0">
															    													  <td colspan='2'>
																																<logic:iterate name="journalIter" property="goalEntries" id="goalsIter">    														
																    														  <table align='center' width='100%'>
																			                    					<tr class='formDeLabel'>
																			                    						<td > <bean:write name="goalsIter" property="entryDate" formatKey="date.format.mmddyyyy"/> <bean:write name="goalsIter" property="goalDescription"/></td>
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
		
														      									<logic:notEmpty name="journalIter" property="traitEntries">
														      										<tr>
														      										  <td colspan='2' class='formDe'>
														      											  <table width='100%' class='borderTableGrey'>
														            										<tr>
														                                  <td class='detailHead'>						      									
														                                    <a href="javascript:showHideMulti('traits<bean:write name='idx'/>', 'tchar<bean:write name='idx'/>', 1,'/<msp:webapp/>');" >
														                                    <img border="0" src="/<msp:webapp/>images/expand.gif" name="traits<bean:write name='idx'/>"></a>&nbsp;Traits
														                                  </td>
																				            				</tr>
																														<tr class="hidden" id="tchar<bean:write name='idx'/>0">
															    													  <td colspan='2'>
																																<logic:iterate name="journalIter" property="traitEntries" id="traitsIter">    														
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
			
															      								<logic:notEmpty name="journalIter" property="riskAnalEntries">
														      										<tr>
														      										  <td colspan='2' class='formDe'>
														      											  <table width='100%' class='borderTableGrey'>
														            										<tr>
								                                  						<td class='detailHead'>															 																
						                                   								  <a href="javascript:showHideMulti('risk<bean:write name='idx'/>', 'rchar<bean:write name='idx'/>', 1,'/<msp:webapp/>');" >
														                                    <img border="0" src="/<msp:webapp/>images/expand.gif" name="risk<bean:write name='idx'/>"></a>&nbsp;Screening/Forms
								                                  						</td>
														            										</tr>
														      													<tr class="hidden" id="rchar<bean:write name='idx'/>0">
														      													  <td colspan='2'>  																	
																																<logic:iterate name="journalIter" property="riskAnalEntries" id="riskIter">      														 
															      														  <table align='center' width='100%'>      															 
												                    												<tr class='formDeLabel'>                                       									
												                    													<td ><bean:write name="riskIter" property="entryDate" formatKey="date.format.mmddyyyy"/> <bean:write name="riskIter" property="title"/> </td>
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
		
														      								<logic:notEmpty name="journalIter" property="progRefEntries">
													      										<tr>
													      										  <td colspan='2' class='formDe'>
													      											  <table width='100%' class='borderTableGrey'>
													            										<tr>
							                                  						<td class='detailHead'>	 														  
					                                   								  <a href="javascript:showHideMulti('prog<bean:write name='idx'/>', 'prchar<bean:write name='idx'/>', 1,'/<msp:webapp/>');" >
													                                    <img border="0" src="/<msp:webapp/>images/expand.gif" name="prog<bean:write name='idx'/>"></a>&nbsp;Program Referrals
							                                  						</td>
													            										</tr>
													      													<tr class="hidden" id="prchar<bean:write name='idx'/>0">
													      													  <td colspan='2'>  																	
																															<logic:iterate name="journalIter" property="progRefEntries" id="refIter">      														 
														      														  <table align='center' width='100%'>      															 
											                    												<tr class='formDeLabel'>                                       									
											                    													<td ><bean:write name="refIter" property="sentDate" formatKey="date.format.mmddyyyy"/> <bean:write name="refIter" property="providerProgramName"/> </td>
											                    												</tr>
		
											                    												<logic:iterate name="refIter" property="referralComments" id="commentsIter"> 
												                    												<tr>
																                                       <td colspan='2' class='normalRow'><bean:write name="commentsIter" property="commentText"/> [<bean:write name="commentsIter" property="commentsDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="commentsIter" property="userName"/>]</td>
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
		
													      									<logic:notEmpty name="journalIter" property="closingEntries">
													      										<tr>
													      										  <td colspan='2' class='formDe'>
													      											  <table width='100%' class='borderTableGrey'>
													            										<tr>
							                                  						<td class='detailHead'>	      														 																		
					                                   								  <a href="javascript:showHideMulti('closing<bean:write name='idx'/>', 'clchar<bean:write name='idx'/>', 1,'/<msp:webapp/>');" >
													                                    <img border="0" src="/<msp:webapp/>images/expand.gif" name="closing<bean:write name='idx'/>"></a>&nbsp;Closing Information
							                                  						</td>
													            										</tr>
													      													<tr class="hidden" id="clchar<bean:write name='idx'/>0">
													      													  <td colspan='2'>  																	
																															<logic:iterate name="journalIter" property="closingEntries" id="closeIter">  														   
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
			      																		</td>
			      																	</tr>
				            											  </table>
				            										  </td>
				            										</tr>
				            									</table>
				            								</td>
				            							</tr>
		                       		  </logic:iterate>
		                       		 </logic:notEmpty>
		                     		 </td>
		                     	</tr>
					   						</table>
				   						</td>
				   					</tr>              
		           		</table>
		   						<!-- end Journal table -->
		
		              <!-- Begin BUTTON TABLE -->
		              <div class='spacer'></div>
		      				<table border="0" width="100%">
		                <tr>
		                  <td align="center">
		                    <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>                       
												<!--<html:button property="submitAction" onclick="openWindow('displayJuvenileJournalList.do?submitAction=Print')"><bean:message key="button.generateJournalReport"/></html:button> -->
		                    <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>    
		                  </td>
		                </tr>
		              </table>
		             	<div class='spacer'></div>
		              <!-- END BUTTON TABLE -->
                </td>
              </tr>
		  			</table>
						<div class='spacer'></div><!-- BEGIN BUTTON TABLE -->
  				</td>
  			</tr>
  		</table>
   	</td>
  </tr>
</table>
<!-- END DETAIL TABLE -->

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</html:form>
</body>

</html:html>

