<!DOCTYPE HTML>

<!-- User selects the 'Create HCJDP' link on the left UI Navigation -->
<!--MODIFICATIONS -->
<!-- 05/24/2006 Uma Gopinath 	Summary JSP -->
<!-- 09/20/2006 Uma Gopinath 	Update/Inactivate Summary flow added -->
<!-- 06/06/2007 C Shimek     	#42431 added logic tags to not display Manage Contract when page state is inactivate summary. 
				Also correct js error in submitUpdate() found during testing -->
<!-- 07/30/2007 Leslie Deen		#43346 Rearrange buttons -->
<!-- 10/13/2015 Richard Capestani #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>



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
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>/css/commonsupervision.css" />
<html:base />

<title><bean:message key="title.heading"/> - serviceSummary.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type='text/javascript'>
function setAction(action)
{
  document.forms[0].actionType.value = action;	
}

function submitUpdate(theForm)
{
	setAction("updateService")
	changeFormActionURL(theForm.name, '/<msp:webapp/>handleProgramDashboard.do?submitAction=Update',true);
}
</script>

</head>

<body topmargin=0 leftmargin="0">
<html:form action="/submitJuvServiceCreate" target="content">


<logic:equal name="serviceProviderForm" property="actionType" value="createService">
   <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|380">
</logic:equal>
<logic:equal name="serviceProviderForm" property="actionType" value="updateService">
   <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|378">
</logic:equal>
<logic:equal name="serviceProviderForm" property="actionType" value="inactivateService">
   <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|381">
</logic:equal>
<logic:equal name="serviceProviderForm" property="actionType" value="view">
   <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|376">
</logic:equal>

<html:hidden name="serviceProviderForm" property="actionType"/>


<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>    	
  </tr>
  <tr>
    <td valign=top>
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
					<td valign=top>
						<!--tabs start-->
							<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value="suggestedOrderTab"/>
							</tiles:insert>													
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>		          
      </table>
      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
        <tr>
					<td><bean:message key="prompt.3.spacer"/></td>
  			</tr>
        <tr>
          <td valign=top align=center>
            <!-- BEGIN HEADING TABLE -->
            <table width='100%'>
              <tr>
    			      <td class="header" align="center">
      			       <logic:equal name="serviceProviderForm" property="actionType" value="createService">
      			       		<bean:message key="prompt.add"/>
      			       </logic:equal>
      			       <logic:equal name="serviceProviderForm" property="actionType" value="updateService">
      			       		<bean:message key="prompt.update"/>
      			       </logic:equal>
      			       <logic:equal name="serviceProviderForm" property="actionType" value="inactivateService">
      			       		<bean:message key="prompt.inactivate"/>
      			       </logic:equal>
      			       <bean:message key="prompt.service"/>  
      			       <logic:notEqual name="serviceProviderForm" property="actionType" value="view"> <bean:message key="prompt.summary"/> </logic:notEqual>
      			       <logic:equal name="serviceProviderForm" property="actionType" value="view"> <bean:message key="prompt.details"/> </logic:equal>
    			      </td>	
    				  </tr>         
            </table>
            <!-- END HEADING TABLE -->

            <!-- BEGIN INSTRUCTION TABLE -->
            <table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td>
                  <ul>
                    <logic:notEqual name="serviceProviderForm" property="actionType" value="view"> 
                   		<li>Review entries. Click Manage Funding to associate funding to this program or Save & Continue.</li>
                   	</logic:notEqual>
                  </ul>
                </td>
              </tr>
            </table>

            <table cellpadding=1 cellspacing=0 border=0 width='98%'>
    					<tr>
      					<td bgcolor='#cccccc'>
      						<table width='100%' border=0 cellpadding=2 cellspacing=1>
        						<tr>
        							<td class="headerLabel" width='1%' nowrap>Provider <bean:message key="prompt.name" /></td>
        							<td colspan=3 class=headerData><bean:write name="serviceProviderForm" property="providerName"/></td>
        						</tr>
        						<tr>
        							<td class="headerLabel"><bean:message key="prompt.start" /> <bean:message key="prompt.date" /></td>
        							<td class="headerData"><bean:write name="serviceProviderForm" property="startDate" formatKey="date.format.mmddyyyy"/></td>
        							<td class="headerLabel" width='1%' nowrap><bean:message key="prompt.inHouse" /></td>
        							<td class="headerData"><bean:write name="serviceProviderForm" property="isInHouse"/></td>
        						</tr>
        						<tr>
        							<td class="headerLabel" width='1%' nowrap><bean:message key="prompt.program"/> <bean:message key="prompt.name" /></td>
        							<td colspan=3 class=headerData><bean:write name="serviceProviderForm" property="currentProgram.programName"/></td>
        						</tr>
        						<tr>
        							<td class="headerLabel" width='1%' nowrap><bean:message key="prompt.targetIntervention"/></td>
        							<td colspan=3 class=headerData><bean:write name="serviceProviderForm" property="currentProgram.targetIntervention"/></td>
        						</tr>
        					</table>
      				  </td>
      				</tr>
      		  </table>
            <!-- BEGIN  TABLE -->

            <br>
            <table width='98%' cellpadding=2 cellspacing=0 class=borderTableBlue>
              <tr>
                <td class=detailHead><bean:message key="prompt.service" /> <bean:message key="prompt.info" /></td>
              </tr>
              <tr>
                <td>
                  <table width='100%' cellpadding=4 cellspacing=1>
                    <tr>
                      <td class=formDeLabel nowrap width='1%'><bean:message key="prompt.name" /></td>
        						  <td class="formDe"><bean:write name="serviceProviderForm" property="currentProgram.programService.serviceName"/></td>                     
                      <td class=formDeLabel nowrap width='1%'><bean:message key="prompt.code" /></td> 
                       <td class="formDe"><bean:write name="serviceProviderForm" property="currentProgram.programService.code"/></td>                                            
                    </tr>
                    <tr>
                      <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.type"/></td>
                      <td class=formDe colspan=3>
                      	<bean:write property="currentProgram.programService.type" name="serviceProviderForm"/>							  					
                      </td>
                    </tr>
                    <tr>
                      <td class=formDeLabel nowrap width='1%'><bean:message key="prompt.maxEnrollment" /></td>
                      <td class=formDe>
                        <bean:write name="serviceProviderForm" property="currentProgram.programService.maxEnrollment"/>
                      </td>
                      <td class=formDeLabel nowrap width='1%'><bean:message key="prompt.cost"/></td>
                      <td class=formDe>
                       <bean:write name="serviceProviderForm" property="currentProgram.programService.displayCost"/>                        
                      </td>
                    </tr>
                    <tr>
                      <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.service"/> <bean:message key="prompt.location"/> Unit(s)</td>
                      <td class=formDe colspan=3><bean:write name="serviceProviderForm" property="currentProgram.programService.locationString"/></td>
                    </tr>
                      <tr>
                      <td colspan=4 class=formDeLabel><bean:message key="prompt.description"/></td>
                    </tr>
                    <tr>
                      <td colspan=4 class=formDe> <bean:write name="serviceProviderForm"  name="serviceProviderForm" property="currentProgram.programService.description"/> </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
                
                
            <!-- added for displaying future service events for removed locations -->                

            <logic:equal name="serviceProviderForm" property="actionType" value="updateService">
              <bean:define id="sv" name="serviceProviderForm" property="currentProgram.programService"/>		                

              <logic:notEmpty name="serviceProviderForm" property="serviceEvents">
                <br>
                <table width='98%' cellpadding=2 cellspacing=0 class=borderTableBlue>
                  <tr>
                    <td class=detailHead><bean:message key="prompt.future" /> <bean:message key="prompt.service" /> <bean:message key="prompt.events" /></td>
                  </tr>
                  <tr>
                    <td>
        							<table width="100%" cellpadding="4" cellspacing="1">
        							<% int RecordCounter=0;
        							   String bgcolor="";
        							%>
                      <tr bgcolor='#cccccc'>
                        <td class="subhead" valign=top><bean:message key="prompt.locationName"/> </td>
                        <td class="subhead" valign=top><bean:message key="prompt.service"/> </td>
                        <td class="subhead" valign=top><bean:message key="prompt.service"/> <bean:message key="prompt.provider"/> </td>								   										   		
                        <td class="subhead" valign=top><bean:message key="prompt.dateTime"/> </td>
                        <td class="subhead" valign=top><bean:message key="prompt.#Scheduled"/></td>
                      </tr>

                      <logic:iterate id="svIndex" name="serviceProviderForm" property="serviceEvents">
                        <tr class= <% RecordCounter++;
                          bgcolor = "alternateRow";                      
                          if (RecordCounter % 2 == 1)
                            bgcolor = "normalRow";
                          out.print(bgcolor); %>
												>
                          <td>
													  <logic:notEmpty name="svIndex" property="serviceLocationName">
                              <logic:notEqual name="svIndex" property="serviceLocationName" value="">
                                <bean:write name="svIndex" property="serviceLocationName"/>
                              </logic:notEqual>
                              <logic:equal name="svIndex" property="serviceLocationName" value="">
                                <bean:write name="svIndex" property="serviceLocationName"/>
                              </logic:equal>
                            </logic:notEmpty>

                            <logic:empty name="svIndex" property="serviceLocationName">
                              <bean:write name="svIndex" property="serviceLocationName"/>
                            </logic:empty>
                          </td>
  		                    <td><bean:write name="svIndex" property="serviceName"/></td>
  		                    <td><bean:write name="svIndex" property="serviceProviderName"/></td>
  		                    <td><bean:write name="svIndex" property="startDatetime" formatKey="datetime.format.mmddyyyyHHmm"/></td>
  		                  <td><bean:write name="svIndex" property="currentEnrollment"/></td>
  		                </tr>
    								</logic:iterate>
    							</table>
    							</td>
  							</tr>
							</table>							
					</logic:notEmpty>
				</logic:equal>

<!-- nidhi -->
        <logic:equal name="serviceProviderForm" property="actionType" value="inactivateService">
        <logic:notEmpty name="serviceProviderForm" property="serviceEvents">
          <table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue> 
            <tr>
              <td class=detailHead colspan=6><bean:message key="prompt.funding"/> <bean:message key="prompt.information"/></td>
            </tr>
						<tr bgcolor='#cccccc'> 
							<td class="subhead" valign=top><bean:message key="prompt.name" /></td> 
							<td class="subhead" valign=top><bean:message key="prompt.number" /></td> 
							<td class="subhead" valign=top><bean:message key="prompt.type" /></td> 
							<td class="subhead" valign=top><bean:message key="prompt.startDate" /></td> 
							<td class="subhead" valign=top><bean:message key="prompt.endDate" /></td> 
						</tr> 
						<%  int RecCounter = 0;
							String bgcol = "";%>  								
						<logic:iterate id="contractIterator" name="serviceProviderForm" property="contracts">
    		 				<pg:item>
    						<tr class=<%RecCounter++;
    							bgcol = "alternateRow";
    							if (RecCounter % 2 == 1)
    								bgcol = "normalRow";
    							out.print(bgcol);%>
								>
    	 						<td><bean:write name="contractIterator" property="contractName"/></td> 
    							<td><bean:write name="contractIterator" property="number"/></td> 
    							<td><bean:write name="contractIterator" property="contractType"/></td> 
    							<td><bean:write name="contractIterator" property="startDate" formatKey="date.format.mmddyyyy"/></td> 
    							<td><bean:write name="contractIterator" property="endDate" formatKey="date.format.mmddyyyy"/></td> 
    						</tr> 					
  						</pg:item>
						</logic:iterate>
					</table>
					
  			</logic:notEmpty>
  		</logic:equal>
			
			<br> 
      <bean:define id="sv" name="serviceProviderForm" property="currentProgram.programService"/>
      <logic:equal name="serviceProviderForm" property="actionType" value="inactivateService">
        <logic:notEmpty name="serviceProviderForm" property="serviceEvents">
          <br>
          <table width='98%' cellpadding=2 cellspacing=0 class=borderTableBlue>
            <tr>
              <td class=detailHead><bean:message key="prompt.future" /> <bean:message key="prompt.service" /> <bean:message key="prompt.events" /></td>
            </tr>
            <tr>
              <td>
								<table width="100%" cellpadding="4" cellspacing="1">
  								<% int RecordCounter=0;
  								   String bgcolor="";
  								%>
								   <tr bgcolor='#cccccc'>
  						   		<td class="subhead" valign=top><bean:message key="prompt.locationName"/> </td>
  						   		<td class="subhead" valign=top><bean:message key="prompt.service"/> </td>
  						   		<td class="subhead" valign=top><bean:message key="prompt.service"/> <bean:message key="prompt.provider"/> </td>								   										   		
  						   		<td class="subhead" valign=top><bean:message key="prompt.dateTime"/> </td>
										<td class="subhead" valign=top><bean:message key="prompt.#Scheduled"/></td>
									</tr>

							   	<logic:iterate id="svIndex" name="serviceProviderForm" property="serviceEvents">
										<tr class= <% RecordCounter++;
                      bgcolor = "alternateRow";                      
                      if (RecordCounter % 2 == 1)
                      bgcolor = "normalRow";
                      out.print(bgcolor); %>
										>
                      <td>
  	                    <logic:notEmpty name="svIndex" property="serviceLocationName">
    	                    <logic:notEqual name="svIndex" property="serviceLocationName" value="">
      	                    <bean:write name="svIndex" property="serviceLocationName"/>
    	                    </logic:notEqual>
    	                     <logic:equal name="svIndex" property="serviceLocationName" value="">
    	                  		 <bean:write name="svIndex" property="serviceLocationName"/>
    	                    </logic:equal>
  	                    </logic:notEmpty>

 	                     <logic:empty name="svIndex" property="serviceLocationName">
    	                    <bean:write name="svIndex" property="serviceLocationName"/>
  	                   </logic:empty>
	                    </td>
	                    <td><bean:write name="svIndex" property="serviceName"/></td>
	                    <td><bean:write name="svIndex" property="serviceProviderName"/></td>
	                    <td><bean:write name="svIndex" property="startDatetime" formatKey="datetime.format.mmddyyyyHHmm"/></td>
  	                  <td><bean:write name="svIndex" property="currentEnrollment"/></td>
	                </tr>
								</logic:iterate>
  						</table>
  					</td>
  					</tr>
  					</table>
  				</logic:notEmpty>
  			</logic:equal>
						
						<!-- Begin Funding Table -->		
						
						<logic:notEmpty name="serviceProviderForm" property="contracts">
								<table width="98%" cellpadding="1" cellspacing="0">
									<tr>
										<td class="detailHead" width="100%">&nbsp;
											<bean:message key="prompt.funding" /> Information
										</td>
									</tr>	 							
									<tr>
										<td>
											<table width="100%" border="0" cellpadding="2" cellspacing="1" class=borderTable> 
												<tr class="formDeLabel"> 
													<td><bean:message key="prompt.name" /></td> 
													<td><bean:message key="prompt.number" /></td> 
													<td><bean:message key="prompt.type" /></td> 
													<td><bean:message key="prompt.startDate" /></td> 
													<td><bean:message key="prompt.endDate" /></td> 
												</tr> 
												<%  int RecordCounter = 0;
													String bgcolor = "";%>  								
												<logic:iterate id="currentIter" name="serviceProviderForm" property="contracts">
    							 					<pg:item>
    												<tr class=<%RecordCounter++;
    													bgcolor = "alternateRow";
    													if (RecordCounter % 2 == 1)
    														bgcolor = "normalRow";
    													out.print(bgcolor);%>
														>
     													<td><bean:write name="currentIter" property="contractName"/></td> 
    													<td><bean:write name="currentIter" property="number"/></td> 
    													<td><bean:write name="currentIter" property="contractType"/></td> 
    													<td><bean:write name="currentIter" property="startDate" formatKey="date.format.mmddyyyy"/></td> 
    													<td><bean:write name="currentIter" property="endDate" formatKey="date.format.mmddyyyy"/></td> 													
    												</tr> 
    												</pg:item>
												</logic:iterate>
											</table> 
										</td> 
									</tr>									
								</table>
							</logic:notEmpty>
					
						<logic:empty name="serviceProviderForm" property="contracts">
							<table width="98%" cellpadding="1" cellspacing="0">
  							<tr>
  								<td class=formDeLabel width='1%' nowrap>No Contract Funding Available</td>
  							</tr>
  							</table>			
  						</logic:empty>
  						<!-- End Funding Table -->

                <!-- BEGIN BUTTON TABLE -->
             <table border="0">
      					<tr>
                	<td align="center">
                 		<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>					

        						<logic:notEqual name="serviceProviderForm" property="actionType" value="view"> 							
        							<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
        								<bean:message key="button.saveAndContinue"></bean:message>
        							</html:submit>
        						</logic:notEqual>

        						<logic:equal name="serviceProviderForm" property="actionType" value="view"> 							
        						 <logic:notEqual name="serviceProviderForm" property="statusId" value="I">  
        						   <logic:notEqual name="serviceProviderForm" property="currentProgram.programService.statusId" value="I">                                  
        						     <jims2:isAllowed requiredFeatures="CS-ASP-UPDATEJUV">
        							<input type="button" name="submitAction" onclick="submitUpdate(this.form)" value='Update Service'></input>
        						     </jims2:isAllowed>	
        						   </logic:notEqual>								
        						  </logic:notEqual>
        						</logic:equal>

        						<logic:notEqual name="serviceProviderForm" property="actionType" value="inactivateService">
        							<html:submit property="submitAction">
        								<bean:message key="button.manageContract"></bean:message>
        							</html:submit>
     			    			</logic:notEqual>

       							<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
        						</td>
                	</tr>
             	</table>
                <!-- END BUTTON TABLE -->
              </td>
            </tr>
          </table>
          <br>
      </td>
   </tr>
</table>
<!-- END  TABLE -->
</div>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

