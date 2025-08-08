<!DOCTYPE HTML>
<%-- User selects the "School" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 01/24/2011 D Williamson Create JSP  --%>
<%-- 10/23/2015 Richard Capestani #30818 MJCW: PROD Juv Profile > Education Tab > Add Traits / Select Casefile 2 Tiny Squares Showing (IE11 conversion) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
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
<title><bean:message key="title.heading"/> - educationCharterHSEPCreateSummary.jsp</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin='0' >
<html:form action="submitGEDCreate" target="content">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<logic:equal name="educationCharterDetailsForm" property="action" value="view">
	  <tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> -
		                                   <bean:message key="title.highSchoolEquivalencyProgram"/>&nbsp;<bean:message key="title.details"/></td>
	  </tr>
   </logic:equal>
   <logic:equal name="educationCharterDetailsForm" property="action" value="summary">
	<tr>
		<td align="center" class="header">
			<bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - 
		    <bean:message key="title.create"/>&nbsp;<bean:message key="title.highSchoolEquivalencyProgram"/>
  			<bean:message key="title.summary"/>
  	    </td>
  	  </tr>  		      				
	</logic:equal>
  	<logic:equal name="educationCharterDetailsForm" property="action" value="confirm">
  	  <tr>
  	    <td align="center" class="header">
  	    	<bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - 
		    <bean:message key="title.create"/>&nbsp;<bean:message key="title.highSchoolEquivalencyProgram"/>
  			<bean:message key="title.confirmation"/> 
		</td>
	</tr>
	
	<tr><td align='center' class='confirm'>HSEP Information successfully added.</td></tr>
	</logic:equal>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<br>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<tr>
		<td>
		  <logic:equal name="educationCharterDetailsForm" property="action" value="summary">
			<ul>
				<li>Review information and select Finish to add Program information.</li>
				<li>Select Back button to return to Create Program page.</li>
			</ul>
		  </logic:equal>	
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<!--juv profile header start-->
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="profileheader"/>
	</tiles:insert>
<!--juv profile header end-->
<!-- BEGIN DETAIL TABLE -->
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
                        <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
				           <tiles:put name="tabid" value="interviewinfotab"/>
				           <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
			            </tiles:insert>
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
				</tr>
			</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align='center'>
  					<!-- BEGIN TABLE -->
						<div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign="top">
  										        <tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										           <tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
										           <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									            </tiles:insert>
											</td>
										</tr>
										<tr>
											<td bgcolor='#6699FF'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
										</tr>
									</table>

									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
										<td valign="top" align="center">
											<div class="spacer"></div>
											<table width='98%' border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top">
														<tiles:insert page="../caseworkCommon/educationTabs.jsp" flush="true">
                                                           <tiles:put name="tabid" value="charter"/>
                                                           <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
                                                        </tiles:insert>
													</td>
												</tr>
												<tr>
													<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
												</tr>
											</table>	
											<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
												<div class="spacer"></div>												
												<tr>
													<td valign="top" align="center">
														<div class='spacer'></div>
<!-- BEGIN SCHOOL INFO TABLE -->												
														<table width='98%' border="0" cellpadding="2" cellspacing="0" class='borderTableBlue'>
															<tr>
																<td valign="top" class='detailHead'><bean:message key="title.highSchoolEquivalencyProgram"/>&nbsp;<bean:message key="prompt.information"/></td>
															</tr>
															<tr>
																<td>
																	<table border='0' cellpadding='2' cellspacing="1" width='100%' >
		                                                                <tr>
																			<td class='formDeLabel'><bean:message key="prompt.GEDProgram"/></td>
																			<td class='formDe' colspan="6" nowrap><bean:write name="educationCharterDetailsForm" property="hsepProgramCode" /></td>
																		</tr>
																		<logic:equal name="educationCharterDetailsForm" property="hsepProgramCode" value="OTHER">
																			<tr>
																				<td class='formDeLabel'><bean:message key="prompt.otherGEDProgram"/></td>
																				<td class='formDe' colspan="6" nowrap>
																					<bean:write name="educationCharterDetailsForm" property="otherProgramCode" />
																				</td>
																			</tr>
																		</logic:equal> 
																		<% int span=2; %> <%-- variable used for colspan on total score --%>   
																		<tr>
																			<td class='formDeLabel' ><bean:message key="prompt.category"/></td>
																			<logic:notEqual name="educationCharterDetailsForm" property="version" value="0">
																				<%span = 3; %>
																				<td class='formDeLabel'><bean:message key="prompt.retest"/></td>
																			</logic:notEqual>
																			<td class='formDeLabel' nowrap="nowrap"><bean:message key="prompt.testDate"/></td>
																			<td class='formDeLabel' ><bean:message key="prompt.score"/></td>
																			<td class='formDeLabel' ><bean:message key="prompt.passFail"/></td>
																			<td class='formDeLabel' width="1%" nowrap><bean:message key="prompt.during"/><br><bean:message key="prompt.placement"/></td>
																			<td class='formDeLabel' width="1%" nowrap><bean:message key="prompt.after"/><br><bean:message key="prompt.placement"/></td>
																		</tr>  
																		<!-- Changes for ER: JIMS200077481 starts -->
																	<%-- READING --%>	                                                              
																	<logic:notEqual name="educationCharterDetailsForm" property="hasGEDEffectiveDateApplied" value="true">
																		<tr>
		                                                                    <td class='formDe' nowrap width="1%"><bean:message key="prompt.languageArtsReading"/></td>
		                                                                    <logic:notEqual name="educationCharterDetailsForm" property="version" value="0">
			                                                                    <td align="left" class='formDeBold'>
			            															<logic:equal name="educationCharterDetailsForm" property="readingRetest" value="true">
			                           												     <bean:message key="prompt.yes" />
			              															</logic:equal>
			 																	</td> 
		 																	</logic:notEqual>       
		                                                                    <td align="left" class='formDeBold'>
		                                                                    	<logic:notEqual name="educationCharterDetailsForm" property="readingScore" value="">
		                                                                    		<bean:write name="educationCharterDetailsForm" property="readingTestDateStr" format="<bean:message key='date.format.mmddyyyy'/>"/>
		                                                                    	</logic:notEqual>	
		                                                                    </td>																	
																			<td align="left" class='formDeBold'>
																				<logic:notEqual name="educationCharterDetailsForm" property="readingScore" value="">
																					<bean:write name="educationCharterDetailsForm" property="readingScore" />
																				</logic:notEqual>
																				<logic:equal name="educationCharterDetailsForm" property="readingScore" value="">
																					 <bean:message key="prompt.notApplicable" />
																				</logic:equal>
																			</td>
		                                                                    <td align="left" class='formDeBold'>
		                                                                    	<logic:equal name="educationCharterDetailsForm" property="readingScoreNotApplicable" value="false">
		            																<logic:equal name="educationCharterDetailsForm" property="readingPassFailInd" value="true">
		                           												    	 <bean:message key="prompt.pass" />
		              																</logic:equal>
		              																<logic:equal name="educationCharterDetailsForm" property="readingPassFailInd" value="false">
		                                												<bean:message key="prompt.fail" />
		                															</logic:equal>
		                														</logic:equal>	
																			</td>          
		                                                                    <td align="left" class='formDeBold'>
		            															<logic:equal name="educationCharterDetailsForm" property="readingBeforePlacement" value="true">
		                           												     <bean:message key="prompt.yes" />
		              															</logic:equal>
		 																	</td>        
		                                                                    <td align="left" class='formDeBold'>
		            															<logic:equal name="educationCharterDetailsForm" property="readingAfterPlacement" value="true">
		                           												     <bean:message key="prompt.yes" />
		              															</logic:equal>
		 																	</td>          
																		</tr>
																	<%-- WRITING --%>
		                                                                <tr>
		                                                                    <td class='formDe' nowrap><bean:message key="prompt.languageArtsWriting"/></td>
		                                                                   <logic:notEqual name="educationCharterDetailsForm" property="version" value="0">
			                                                                    <td align="left" class='formDeBold'>
			            															<logic:equal name="educationCharterDetailsForm" property="writingRetest" value="true">
			                           												     <bean:message key="prompt.yes" />
			              															</logic:equal>
			 																	</td>
		 																	</logic:notEqual>        
		                                                                    <td align="left" class='formDeBold'>
		                                                                    	<logic:notEqual name="educationCharterDetailsForm" property="writingScore" value="">
		                                                                    		<bean:write name="educationCharterDetailsForm" property="writingTestDateStr" format="<bean:message key='date.format.mmddyyyy'/>"/>
		                                                                    	</logic:notEqual>	
		                                                                    </td>																	
																			<td align="left" class='formDeBold'>
																				<logic:notEqual name="educationCharterDetailsForm" property="writingScore" value="">
																					<bean:write name="educationCharterDetailsForm" property="writingScore" />
																				</logic:notEqual>
																				<logic:equal name="educationCharterDetailsForm" property="writingScore" value="">
																					 <bean:message key="prompt.notApplicable" />
																				</logic:equal>
																			</td>
		                                                                    <td align="left" class='formDeBold'>
		                                                                    	<logic:equal name="educationCharterDetailsForm" property="writingScoreNotApplicable" value="false">
		            																<logic:equal name="educationCharterDetailsForm" property="writingPassFailInd" value="true">
		                           												    	 <bean:message key="prompt.pass" />
		              																</logic:equal>
		              																<logic:equal name="educationCharterDetailsForm" property="writingPassFailInd" value="false">
		                                												<bean:message key="prompt.fail" />
		                															</logic:equal>
		                														</logic:equal>	
																			</td>
		                                                                    <td align="left" class='formDeBold'>
		            															<logic:equal name="educationCharterDetailsForm" property="writingBeforePlacement" value="true">
		                           												     <bean:message key="prompt.yes" />
		              															</logic:equal>
		 																	</td>        
		                                                                    <td align="left" class='formDeBold'>
		            															<logic:equal name="educationCharterDetailsForm" property="writingAfterPlacement" value="true">
		                           												     <bean:message key="prompt.yes" />
		              															</logic:equal>
		 																	</td>          
																		</tr>	
																	</logic:notEqual>
																<%-- RLA --%>			
																	<logic:equal name="educationCharterDetailsForm" property="hasGEDEffectiveDateApplied" value="true">
		                                                                <tr>
		                                                                    <td class='formDe' nowrap><bean:message key="prompt.RLA"/></td>
		                                                                   <logic:notEqual name="educationCharterDetailsForm" property="version" value="0">
			                                                                    <td align="left" class='formDeBold'>
			            															<logic:equal name="educationCharterDetailsForm" property="rlaRetest" value="true">
			                           												     <bean:message key="prompt.yes" />
			              															</logic:equal>
			 																	</td>
		 																	</logic:notEqual>        
		                                                                    <td align="left" class='formDeBold'>
		                                                                    	<logic:notEqual name="educationCharterDetailsForm" property="rlaScore" value="">
		                                                                    		<bean:write name="educationCharterDetailsForm" property="rlaTestDateStr" format="<bean:message key='date.format.mmddyyyy'/>"/>
		                                                                    	</logic:notEqual>	
		                                                                    </td>																	
																			<td align="left" class='formDeBold'>
																				<logic:notEqual name="educationCharterDetailsForm" property="rlaScore" value="">
																					<bean:write name="educationCharterDetailsForm" property="rlaScore" />
																				</logic:notEqual>
																				<logic:equal name="educationCharterDetailsForm" property="rlaScore" value="">
																					 <bean:message key="prompt.notApplicable" />
																				</logic:equal>
																			</td>
		                                                                    <td align="left" class='formDeBold'>
		                                                                    	<logic:equal name="educationCharterDetailsForm" property="rlaScoreNotApplicable" value="false">
		            																<logic:equal name="educationCharterDetailsForm" property="rlaPassFailInd" value="true">
		                           												    	 <bean:message key="prompt.pass" />
		              																</logic:equal>
		              																<logic:equal name="educationCharterDetailsForm" property="rlaPassFailInd" value="false">
		                                												<bean:message key="prompt.fail" />
		                															</logic:equal>
		                														</logic:equal>	
																			</td>
		                                                                    <td align="left" class='formDeBold'>
		            															<logic:equal name="educationCharterDetailsForm" property="rlaBeforePlacement" value="true">
		                           												     <bean:message key="prompt.yes" />
		              															</logic:equal>
		 																	</td>        
		                                                                    <td align="left" class='formDeBold'>
		            															<logic:equal name="educationCharterDetailsForm" property="rlaAfterPlacement" value="true">
		                           												     <bean:message key="prompt.yes" />
		              															</logic:equal>
		 																	</td>          
																		</tr>
																	</logic:equal>
																	<!-- Changes for ER: JIMS200077481 ends -->
																		
																	<%-- MATH --%>	
		                                                                <tr>
		                                                                    <td class='formDe' nowrap><bean:message key="prompt.mathematics"/></td>
		                                                                    <logic:notEqual name="educationCharterDetailsForm" property="version" value="0">
			                                                                    <td align="left" class='formDeBold'>
			            															<logic:equal name="educationCharterDetailsForm" property="mathRetest" value="true">
			                           												     <bean:message key="prompt.yes" />
			              															</logic:equal>
			 																	</td>
		 																	</logic:notEqual>
		                                                                    <td align="left" class='formDeBold'>
		                                                                    	<logic:notEqual name="educationCharterDetailsForm" property="mathScore" value="">
		                                                                    		<bean:write name="educationCharterDetailsForm" property="mathTestDateStr" format="<bean:message key='date.format.mmddyyyy'/>"/>
		                                                                    	</logic:notEqual>	
		                                                                    </td>																	
																			<td align="left" class='formDeBold'>
																				<logic:notEqual name="educationCharterDetailsForm" property="mathScore" value="">
																					<bean:write name="educationCharterDetailsForm" property="mathScore" />
																				</logic:notEqual>
																				<logic:equal name="educationCharterDetailsForm" property="mathScore" value="">
																					 <bean:message key="prompt.notApplicable" />
																				</logic:equal>
																			</td>
		                                                                	<td align="left" class='formDeBold'>
			                                                                	<logic:equal name="educationCharterDetailsForm" property="mathScoreNotApplicable" value="false">
			            															<logic:equal name="educationCharterDetailsForm" property="mathPassFailInd" value="true">
			                           												     <bean:message key="prompt.pass" />
			              															</logic:equal>
			              															<logic:equal name="educationCharterDetailsForm" property="mathPassFailInd" value="false">
			                                											 <bean:message key="prompt.fail" />
			                														</logic:equal>
			                													</logic:equal>	
																			</td> 
		                                                                    <td align="left" class='formDeBold'>
		                                                                    	<logic:equal name="educationCharterDetailsForm" property="mathBeforePlacement" value="true">
		                           												     <bean:message key="prompt.yes" />
		              															</logic:equal>
		 																	</td>        
		                                                                    <td align="left" class='formDeBold'>
		            															<logic:equal name="educationCharterDetailsForm" property="mathAfterPlacement" value="true">
		                           												     <bean:message key="prompt.yes" />
		              															</logic:equal>
		 																	</td>          
																		</tr>
																	<%-- SCIENCE --%>	
		                                                                <tr>
		                                                                    <td class='formDe' nowrap><bean:message key="prompt.science"/></td>
		                                                                    <logic:notEqual name="educationCharterDetailsForm" property="version" value="0">
			                                                                    <td align="left" class='formDeBold'>
			            															<logic:equal name="educationCharterDetailsForm" property="scienceRetest" value="true">
			                           												     <bean:message key="prompt.yes" />
			              															</logic:equal>
			 																	</td>        
		 																	</logic:notEqual>
		                                                                    <td align="left" class='formDeBold'>
		                                                                    	<logic:notEqual name="educationCharterDetailsForm" property="scienceScore" value="">
		                                                                    		<bean:write name="educationCharterDetailsForm" property="scienceTestDateStr" format="<bean:message key='date.format.mmddyyyy'/>"/>
		                                                                    	</logic:notEqual>	
		                                                                    </td>																	
																			 <td align="left" class='formDeBold'>
																				<logic:notEqual name="educationCharterDetailsForm" property="scienceScore" value="">
																					<bean:write name="educationCharterDetailsForm" property="scienceScore" />
																				</logic:notEqual>
																				<logic:equal name="educationCharterDetailsForm" property="scienceScore" value="">
																					 <bean:message key="prompt.notApplicable" />
																				</logic:equal>
																			</td>	
		                                                                	<td align="left" class='formDeBold'>
		            															<logic:equal name="educationCharterDetailsForm" property="scienceScoreNotApplicable" value="false">
			            															<logic:equal name="educationCharterDetailsForm" property="sciencePassFailInd" value="true">
			                           												     <bean:message key="prompt.pass" />
			              															</logic:equal>
			              															<logic:equal name="educationCharterDetailsForm" property="sciencePassFailInd" value="false">
			                                											 <bean:message key="prompt.fail" />
			                														</logic:equal>
			                													</logic:equal>	
																			</td> 
		                                                                    <td align="left" class='formDeBold'>
		            															<logic:equal name="educationCharterDetailsForm" property="scienceBeforePlacement" value="true">
		                           												     <bean:message key="prompt.yes" />
		              															</logic:equal>
		 																	</td>        
		                                                                    <td align="left" class='formDeBold'>
		            															<logic:equal name="educationCharterDetailsForm" property="scienceAfterPlacement" value="true">
		                           												     <bean:message key="prompt.yes" />
		              															</logic:equal>
		 																	</td>          
																		</tr>
																	<%-- SOCIAL STUDIES --%>	
		                                                                <tr>
		                                                                    <td class='formDe' nowrap><bean:message key="prompt.socialStudies"/></td>
		                                                                    <logic:notEqual name="educationCharterDetailsForm" property="version" value="0">
			                                                                    <td align="left" class='formDeBold'>
			            															<logic:equal name="educationCharterDetailsForm" property="socialStudiesRetest" value="true">
			                           												     <bean:message key="prompt.yes" />
			              															</logic:equal>
			 																	</td>
		 																	</logic:notEqual>
		                                                                    <td align="left" class='formDeBold'>
		                                                                    	<logic:notEqual name="educationCharterDetailsForm" property="socialStudiesScore" value="">
		                                                                    		<bean:write name="educationCharterDetailsForm" property="socialStudiesTestDateStr" format="<bean:message key='date.format.mmddyyyy'/>"/>
		                                                                    	</logic:notEqual>	
		                                                                    </td>																	
																			 <td align="left" class='formDeBold'>
																				<logic:notEqual name="educationCharterDetailsForm" property="socialStudiesScore" value="">
																					<bean:write name="educationCharterDetailsForm" property="socialStudiesScore" />
																				</logic:notEqual>
																				<logic:equal name="educationCharterDetailsForm" property="socialStudiesScore" value="">
																					 <bean:message key="prompt.notApplicable" />
																				</logic:equal>
																			</td>
		                                                                	<td align="left" class='formDeBold'>
		                                                                		<logic:equal name="educationCharterDetailsForm" property="socialStudiesScoreNotApplicable" value="false">
			            															<logic:equal name="educationCharterDetailsForm" property="socialStudiesPassFailInd" value="true">
			                           												     <bean:message key="prompt.pass" />
			              															</logic:equal>
			              															<logic:equal name="educationCharterDetailsForm" property="socialStudiesPassFailInd" value="false">
			                                											 <bean:message key="prompt.fail" />
			                														</logic:equal>
			                													</logic:equal>	
																			</td> 
		                                                                    <td align="left" class='formDeBold'>
		            															<logic:equal name="educationCharterDetailsForm" property="socialStudiesBeforePlacement" value="true">
		                           												     <bean:message key="prompt.yes" />
		              															</logic:equal>
		 																	</td>        
		                                                                    <td align="left" class='formDeBold'>
		            															<logic:equal name="educationCharterDetailsForm" property="socialStudiesAfterPlacement" value="true">
		                           												     <bean:message key="prompt.yes" />
		              															</logic:equal>
		 																	</td>          
																		</tr>
																		<tr>
		                                                                	<td class='formDeLabel' align="right"><bean:message key="prompt.totalScore"/>:&nbsp;</td>
		                                                                    <td class='formDe' colspan="6">
		                                                                    	<bean:write name="educationCharterDetailsForm" property="totalScore" /> 
		                                                                    		<logic:equal name="educationCharterDetailsForm" property="passFailInd" value="true">
		                           												    	<bean:message key="prompt.pass" />
		              																</logic:equal>
		              																<logic:equal name="educationCharterDetailsForm" property="totalIncomplete" value="">
		              																<logic:equal name="educationCharterDetailsForm" property="passFailInd" value="false">
		                                												<bean:message key="prompt.fail" />
		                															</logic:equal>
		                															</logic:equal>
		                															<logic:equal name="educationCharterDetailsForm" property="totalIncomplete" value="1">
		                                												<bean:message key="prompt.incomplete" />
		                															</logic:equal>
		                													</td>
		                                                                </tr>																
																	</table>
<!-- END SCHOOL INFO TABLE -->                                                           
																</td>
															</tr>
														</table>
<!-- END TABLE -->
													<div class=spacer></div>
<!-- BEGIN BUTTON TABLE -->												
														<table border="0" width="100%">
															<logic:equal name="educationCharterDetailsForm" property="action" value="view">
																<tr>
																	<td align="center">
																		<html:submit property="submitAction"><bean:message key="button.charterDetails" /></html:submit>
																	</td>
																</tr>
															 </logic:equal>
															<logic:equal name="educationCharterDetailsForm" property="action" value="confirm">
																<tr>
																	<td align="center">
																		<html:submit property="submitAction"><bean:message key="button.charterDetails" /></html:submit>
																	</td>
																</tr>
															</logic:equal>
															<logic:equal name="educationCharterDetailsForm" property="action" value="summary">	
																<tr>
																	<td align="center">
																		<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>
																		<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
																	</td>
																</tr>
															</logic:equal>	
														</table>
<!-- END BUTTON TABLE -->
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
					<div class='spacer'></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END DETAIL TABLE -->
<div class='spacer'></div>
</html:form>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>