<!DOCTYPE HTML>
<%-- Used in closing of a casefile --%>
<%--MODIFICATIONS --%>
<%-- 11/29/2005		Justin Jose	Created JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
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
<title><bean:message key="title.heading" /> - commonAppJuvenileDetails.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%--HELP JAVASCRIPT FILE --%>
<%--<SCRIPT SRC="../js/help.js" /> --%>
<%--APP JAVASCRIPT FILE --%>
<%-- tiles:insert page="/js/app.js" / --%>
</head>
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="displayCommonAppJuvenileDetails.do" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|53">

<%-- BEGIN HEADING TABLE --%>
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.close"/> <bean:message key="title.casefile"/> 
		- <bean:message key="title.commonApp"/> - <bean:message key="title.juvenileProfileDetails"/></td>
	</tr>
</table>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
 	<tr>
   	<td>
		  <ul>
      	<li>Select the "Next" button to continue once the information below has been reviewed.</li>
    		<li>Select the "Refresh" button to refresh data if changes have been made.</li>
    		<li>Select the "Back" button to go back</li>
    	</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<%-- Begin Casefile App Tabs --%>
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
  	<td valign=top>
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
      <tiles:put name="tabid" value="closing" />
      <tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
      </tiles:insert>


    	<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
   	  	<tr>
     			<td valign=top align=center>
            <%-- Begin Common App Tabs --%>
						<div class=spacer></div>
            <table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
             	<tr>
               	<td valign=top>
               		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
              				<tr>
              					<td valign=top>
												  <tiles:insert page="../caseworkCommon/casefileClosingTabs.jsp" flush="true">
                    				<tiles:put name="tabid" value="CommonApp" />
                    				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
                    			</tiles:insert>
												</td>
              				</tr>
              			 	 <tr>
              			  		<td bgcolor='#33cc66'><img src='images/spacer.gif' width=5></td>
              			  	</tr>
              			</table>

              			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
             			  	<tr>
                  			<td valign=top align=center>
                          <%-- Begin Common App Detail Tabs --%>
            							<div class=spacer></div>
                          <table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
                           	<tr>
                             	<td valign=top>
                             		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
                          				<tr>
                          					<td valign=top>
																		  <tiles:insert page="../caseworkCommon/commonAppFormTabs.jsp" flush="true">
                                				<tiles:put name="tabid" value="JuvenileDetails" />
                                				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
                                			</tiles:insert>
																		</td>
                          				</tr>
                          			 	 <tr>
                          			  		<td bgcolor='#ff6666'><img src='images/spacer.gif' width=5></td>
                          			  	</tr>
                          			</table>

                          			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
                        			  	<tr>
                              			<td valign=top align=center>
																		
                                      <%-- BEGIN Search Results Summary TABLE --%>
																			<div class=spacer></div>
                                      <table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
                                      	<tr>
                                        	<td valign=top align=center>
                                            <%-- BEGIN Current Constellation TABLE --%>
                                           <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
                                        	  <tr>
                                        			<td valign=top><br></td>
                                        	  </tr>
                                        	  <tr>
                                        			<td valign=top align=center>
                                        			<logic:notEmpty name="commonAppForm" property="currentConstellation">
                                        				  <%-- BEGIN Current Constellation TABLE --%> 
                                        				  <table width='98%' cellspacing=0 cellpadding=2 class=borderTableBlue> 
                                        					<tr class=detailHead> 
                                        					  <td width="100%" valign=top class="detailHead"><bean:message key="prompt.current"/> <bean:message key="prompt.constellation"/> - <bean:message key="prompt.family"/> #
                                        					  	<bean:write name="commonAppForm" property="currentConstellation.familyNumber"/>
                                        					  </td> 
                               					  		  	  <td align="right"><input type="button" value="Edit" onclick="javascript:openWindow('/<msp:webapp/>displayFamilyConstellationList.do')"></td>
                                        					</tr> 
      					
                                        					<tr> 
                                        					  <td colspan="2"> 
                                        					  <table width='100%' cellspacing=1> 
                                       					  	  <logic:empty name="commonAppForm" property="currentConstellation.memberList">
                                        						  	<tr bgcolor='#cccccc'> 
                                        								<td colspan="6" class=subHead>No Members Available </td> 
                                        						  	</tr> 
                                        						  </logic:empty>
                                        						  
																											<logic:notEmpty name="commonAppForm" property="currentConstellation.memberList">
                                        							  <tr bgcolor='#cccccc'> 
                                          								<td width="18%" class=subHead><bean:message key="prompt.member"/> #</td> 
                                          								<td width="20%" class=subHead><bean:message key="prompt.name"/></td> 
                                          								<td width="22%" class=subHead><bean:message key="prompt.relationship"/></td> 
                                          								<td width="18%" class=subHead><bean:message key="prompt.guardian"/></td> 
                                          								<td width="18%" class=subHead><bean:message key="prompt.deceased"/></td> 
                                          								<td class=subHead><bean:message key="prompt.SSN"/></td> 
                                        							  </tr> 

                                        							  <nested:nest property="currentConstellation">
                                        							  <nested:iterate id="memberList" property="memberList" indexId="index">
                                        								  <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>"> 
                                        									<td><bean:write name="memberList" property="memberNumber"/></td> 
                                        									<td><bean:write name="memberList" property="memberName.formattedName"/></td> 
                                        									<td><bean:write name="memberList" property="relationshipToJuv"/></td> 
                                        									<td><bean:write name="memberList" property="guardianYesNo"/></td> 
                                        									<td ><bean:write name="memberList" property="deceasedYesNo"/></td> 
                                        									<td  nowrap><bean:write name="memberList" property="socialSecurity.formattedSSN"/></td> 
                                        								  </tr> 
                                        								</nested:iterate>
                                        							  </nested:nest>
                                        						  </logic:notEmpty>
                                        						</table>
                                        					</td>
                                        					</tr>
                                        					</table>
                                        				<%--END Current Constellation TABLE --%> 
                                        				</logic:notEmpty>
                                  	          </td>
                                           </tr>
                                	</table>
                                <%--END Current Constellation TABLE --%>

                                <%-- BEGIN Abuse Summary TABLE --%>
																<div class=spacer></div>
                                	<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
                          					<tr>
                          						<td valign=top align=center>
																			  <div class=spacer></div>
                          							<table align=center width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
                          								<tr class=detailHead>
                          									<td><a href="javascript:showHideMulti('Abuse', 'aAbuse', 1,'/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="Abuse"></a>&nbsp;Existing Abuse/Neglect</td>
                          							    <td align="right"><input type="button" value="Add" onclick="javascript:openWindow('/<msp:webapp/>displayJuvenileAbuse.do')"></td>
                          								</tr>
                          								<tr id="aAbuse0" class=hidden>
                          									<td valign=top align=center>
                          										<%-- BEGIN JOB TABLE --%>
                          										<table width='100%' cellpadding="2" cellspacing=1  bgcolor='#999999'>				
                          										<%-- display detail header --%> 
                          											<tr bgcolor='#cccccc'>
                          												<td class="subhead"><bean:message key="prompt.entryDate" /></td>
                          												<td class="subhead"><bean:message key="prompt.perpetratorName" /></td>
                          												<td class="subhead"><bean:message key="prompt.relationshipToJuvenile" /></td>
                          												<td class="subhead"><bean:message key="prompt.abuseLevel" /></td>
                          												<td class="subhead"><bean:message key="prompt.abuseType" /></td>
                          												<td class="subhead"><bean:message key="prompt.abuseTreatment" /></td>								
                          											</tr>
                            										<%-- display detail info --%>  
                         				   							<logic:iterate id="abuseIndex" name="commonAppForm" property="abuses" indexId="abuseIdU">
                          												<tr class="<%out.print( (abuseIdU.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
                          												  <td><bean:write name="abuseIndex" property="entryDate" format="MM/dd/yyyy" /></td>
                            												<td><bean:write name="abuseIndex" property="perpetratorName.formattedName" /></td>
                            												<td><bean:write name="abuseIndex" property="relationshipToJuvenile" /></td>
                            												<td><bean:write name="abuseIndex" property="traitType" /></td>
                            												<td><bean:write name="abuseIndex" property="abuseLevel" /></td>
                            												<td><bean:write name="abuseIndex" property="abuseTreatment" /></td>    
                            											</tr>
                          											</logic:iterate>						
                          						<%-- END JOB TABLE --%>
                          										</table>					 
                          										<br>
                          									</td>
                          								</tr>
                          							</table>
                          						</td>
                          					</tr>
                          	</table>
                          	<%-- END Abuse Summary TABLE --%>

                          	<%-- BEGIN Health Issues Summary TABLE --%>
														<div class=spacer></div>
                          	<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
                   				  	<tr>
                    						<td valign=top align=center>
																
  																<div class=spacer></div>																                      
                    							<table width='98%' cellpadding="2" cellspacing="0" class=borderTableBlue>
                    								<tr class="detailHead">
                    									<td colspan=3><a href="javascript:showHideMulti('Health', 'aHealth', 1,'/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="Health"></a>&nbsp;Existing Health Issues</td>
                 							        <td align=right>
                   								       <input type="button" value="Add" onclick="javascript:openWindow('../juvTabInterview/interviewInfoMedical.htm');">
                 							        </td>
                    								</tr>
                    								<tr id="aHealth0" class=hidden>
                    									<td>
                    										<table width="100%" cellpadding="2" cellspacing="1" bgcolor="#999999">
                    											<tr bgcolor="#cccccc">
                    												<td class="subhead">Entered Date</td>
                    												<td class="subhead">Issue Code</td>
                    												<td class="subhead">Issue Status</td>
                    											</tr>
                    											<tr class=normalRow><%-- <a href=caseWorkCloseCasefileCommonAppHealthDetails.htm> --%>
                    												<td>7/7/2004</td>
                    												<td>Issue Code 1</td>
                    												<td>Issue Status 1</td>
                    											</tr>
                    											<tr class=alternateRow>
                    												<td>7/7/2004</td>
                    												<td>Issue Code 2</td>
                    												<td>Issue Status 2</td>
                    											</tr>
                    											<tr class=normalRow>
                    												<td>7/8/2004</td>
                    												<td>Issue Code 3</td>
                    												<td>Issue Status 3</td>
                    											</tr>
                    										</table>
                    	   								</td>
                    	  							</tr>
                    							</table>
                    						</td>
                				  	</tr>
                    	</table>
                    	<%-- END Health Issues Summary TABLE --%>

                    	<%-- BEGIN Medication listing TABLE --%>
											<div class=spacer></div>
                    	<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
             				  	<tr>
              						<td valign=top align=center>
													
													  <div class=spacer></div>
              							<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
              								<tr class=detailHead>
              									<td><a href="javascript:showHideMulti('Medication', 'aMeds', 1,'/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="Medication"></a>&nbsp;Existing Medications</td>
              									<td align=right>
              										<input type="button" value="Add" onclick="javascript:openWindow('../juvTabInterview/interviewInfoMedical.htm')">
              									</td>
              								</tr>
              								<tr id="aMeds0" class=hidden>
              									<td>
              										<table width='100%' cellpadding=2 cellspacing=1 bgcolor=#999999>
              											<tr bgcolor='#cccccc'>
              												<td class=subhead>Date Taken</td>
              												<td class=subhead>Medication</td>
              												<td class=subhead>Dosage</td>
              												<td class=subhead>Currently Taking</td>
              												<td class=subhead>Frequency</td>
              											</tr>
              											<tr class=normalRow>
              												<td>7/7/2004</td>
              												<td>Tylenol</td>
              												<td>5</td>
              												<td>Yes</td>
              												<td>Daily</td>
              											</tr>
              											<tr class=alternateRow>
              												<td>7/5/2004</td>
              												<td>Aspirin</td>
              												<td>5</td>
              												<td>Yes</td>
              												<td>Daily</td>
              											</tr>
              											<tr class=normalRow>
              												<td>7/3/2004</td>
              												<td>Prozac</td>
              												<td>5</td>
              												<td>Yes</td>
              												<td>Daily</td>
              											</tr>
              										</table>
              									</td>
              								</tr>
                    				</table>
                  			</td>
              	  	</tr>
              	</table>
              	<%-- END Medication Listing TABLE --%>

              	<%-- BEGIN Hospitalization list TABLE --%>
								<div class=spacer></div>
              	<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
        				  	<tr>
        						<td valign=top align=center>

										  <div class=spacer></div>
        							<table width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
        								<tr class=detailHead>
        									<td><a href="javascript:showHideMulti('Hospitalization', 'aHosp', 1,'/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="Hospitalization"></a>&nbsp;Existing Hospitalization</td>
        									<td align=right>
        										<input type="button" value="Add" onclick="javascript:openWindow('../juvTabInterview/interviewInfoMedical.htm')">
        									</td>
        								</tr>
        								<tr id="aHosp0" class=hidden>
        									<td>
        										<table width='100%' cellpadding="2" cellspacing=1  bgcolor="#999999">
        											<tr bgcolor="#cccccc">
        												<td class="subhead">Admit Date</td>
        												<td class="subhead">Reason</td>
        												<td class="subhead">Facility</td>
        											</tr>
        											<tr class=normalRow>
        												<td>7/7/2004</td>
        												<td>Broken Ankle</td>
        												<td>Hermann Hospital</td>
        											</tr>
        											<tr class=alternateRow>
        												<td>7/5/2004</td>
        												<td>Sprained Ankle</td>
        												<td>Hermann Hospital</td>
        											</tr>
        											<tr class=normalRow>
        												<td>7/3/2004</td>
        												<td>Torn Ligaments - Ankle</td>
        												<td>Hermann Hospital</td>
        											</tr>
        										</table>
        									</td>
        								</tr>
        							</table>
                    </td>
                  </tr>
        	   		</td>
        	  	</tr>
        		</table>
        		<%-- END Hospitalization TABLE --%>

        		<%-- BEGIN School Search TABLE --%>
						<div class=spacer></div>
        		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  					  	<tr>
  							<td valign=top align=center>

                  <div class=spacer></div>  
 		 			  			<table width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
  									<tr class=detailHead>
  										<td><a href="javascript:showHideMulti('School', 'aSchool', 1,'/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="School"></a>&nbsp;Existing School Information</td>
  										<td align=right>
  											<input type="button" value="Add" onclick="javascript:openWindow('/<msp:webapp/>displayTraitsTab.do?tab=school')">
  										</td>
  									</tr>
      							<tr id="aSchool0" class=hidden>
      								<td>
                				<table width='100%' cellpadding="2" cellspacing=1  bgcolor="#999999">
                  				<%-- display detail header --%> 
                					<tr bgcolor=#cccccc>
                						<td class="subhead"><bean:message key="prompt.gradeLevel" /></td>
                						<td class="subhead"><bean:message key="prompt.districtSchool" /></td>
                						<td class="subhead"><bean:message key="prompt.appropriateLevel" /></td>
                						<td class="subhead"><bean:message key="prompt.exitType" /></td>
                						<td class="subhead"><bean:message key="prompt.enrollmentDate" /></td>																				
                					</tr>
                  				<%-- display detail info --%>  
                					<logic:iterate id="schoolIndex" name="commonAppForm" property="schoolHistory" indexId="index">
                						 <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
                							<td ><bean:write name="schoolIndex" property="gradeLevel" /></td>
                							<td >
                								<bean:write name="schoolIndex" property="schoolDistrict" /> / 
                								<bean:write name="schoolIndex" property="school" />
                							</td>
                							<td><bean:write name="schoolIndex" property="appropriateLevel" /></td>
                							<td><bean:write name="schoolIndex" property="exitType" /></td>
                							<td><bean:write name="schoolIndex" property="lastAttendedDate" format="MM/dd/yyyy" /></td>											    
                						</tr>
                					</logic:iterate>																		
                				</table>
      				   			</td>
      				  		</tr>
      					</table>
						</td>
					</tr>
			</table>
			<%--END Schools TABLE--%>
   		</td>
  	</tr>
</table>
<%-- END Search Results Summary TABLE --%>
<div class=spacer></div>
</td>
			  	</tr>
			</table>
   		</td>
	  </tr>
</table>
<%-- End Common App Detail Tabs --%>
<div class=spacer></div>
</td>
			  	</tr>
			</table>
   		</td>
	  </tr>
</table>
<div class=spacer></div>
<%-- End Common App Tabs --%>
					</td>
			  	</tr>
			</table>
   		</td>
	  </tr>
</table>
<%-- End Casefile App Tabs --%>
<%-- END DETAIL TABLE --%>

<%-- BEGIN BUTTON TABLE --%>
<div class=spacer></div>
<table width="100%">
 	<tr>
   	<td align="center">
			<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
   		<html:submit property="submitAction"><bean:message key="button.next"></bean:message></html:submit>
  		<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
 			<html:submit property="submitAction"><bean:message key="button.refresh"></bean:message></html:submit>
  	</tr>
</table>
<%-- END BUTTON TABLE --%>

<div class=spacer></div>


</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

