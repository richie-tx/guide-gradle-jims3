<!DOCTYPE HTML>
<!-- User hits the 'Submit'button on the Create Service Provider page -->
<!--MODIFICATIONS -->
<!-- 05/24/2006 Uma Gopinath	Create Summary JSP -->
<!-- 07/30/2007 Leslie Deen		Defect #43346 -->
<!-- 09/11/2007 Clarence Shimek Defect #44936 correct Locations Unit display problem -->
<!-- 10/13/2015 Richard Capestani Task #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) -->

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
<html:html>
<!--BEGIN HEADER TAG-->
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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" />
<html:base />
<title><bean:message key="title.heading"/> - programDashboard.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
	
<script>
function setAction(action)
{
	
	document.forms[0].actionType.value = action;
	
}
function checkRadioFieldSelected(thisForm)
{
	var myOption = 0;    
	
	for(var i=0; i<thisForm.length;i++) 
    {
    	if(thisForm.elements[i].type == 'radio')
     	{
     	  if(thisForm.elements[i].checked)
	      {
	        
	      	myOption=1;
	      }   
     	}
     
     }
     
	if (myOption == 0) {
		alert("You must select a Service to perform the operation.");
		return false;
	}
	
	return true;

}
function showRecords()
{
	alert();
	showHide2("locTable", "table", "1");
}

var currentSelectedValue = "";
function setSelectedVal(code){
 currentSelectedValue = code;
}
function loadView(file, actionVal){
	
	var myURL=file  + "&selectedValue=" + currentSelectedValue + "&actionType=" + actionVal;
	load(myURL,top.opener);
	window.close();
}
function load(file,target) {
   
        window.location.href = file;
}

function submitUpdate(theForm){
	setAction("updateProgram")
	//commented out this value passing as values with & was not getting passed selectedValue is set without this -bug 144884
	 //var isIE = (window.navigator.userAgent.indexOf("MSIE") > 0);
	 //var val=val=document.getElementById("progName").textContent;;
	//if(isIE)
		//val=document.getElementById("progName").innerText;
	//else
		//val=document.getElementById("progName").textContent;
	//changeFormActionURL(theForm, '/<msp:webapp/>handleServiceProviderDashboard.do?submitAction=Update&selectedValue='+val,true);
	var selectedProgram = '<bean:write name="serviceProviderForm" property="currentProgram.programName"/>'
	changeFormActionURL(theForm, '/<msp:webapp/>handleServiceProviderDashboard.do?submitAction=Update&selectedValue='+selectedProgram,true);
}
</script>
</head>
<body topmargin=0 leftmargin="0">
<html:form action="/handleProgramDashboard" >
<logic:equal name="serviceProviderForm" property="actionType" value="viewProgram">
    <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|370">
</logic:equal>
<logic:notEqual name="serviceProviderForm" property="actionType" value="viewProgram">
    <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|369">
</logic:notEqual>   
  <div align="center">
    <table width="98%" border="0" cellpadding="0" cellspacing="0" >
      <tr>
        <td valign=top><bean:message key="prompt.3.spacer"/></td>
      </tr>
       <tr>
        <td><html:hidden name="serviceProviderForm" property="actionType"/></td>
      </tr>
      <tr>
        <td valign=top>
          <table width=100% border="0" cellpadding="0" cellspacing="0" >
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
					 <td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>		
            <tr>
              <td bgcolor=#6699FF><bean:message key="prompt.3.spacer"/></td>
            </tr>
          </table>
          <table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
              <tr>
              <td><bean:message key="prompt.3.spacer"/></td>
            </tr>
            <tr>
              <td valign=top align=center>
              	<!-- BEGIN HEADING TABLE -->
                <table width=100%>
                   <tr>
                    <td align="center" class="header"> <bean:message key="title.serviceProvider"/> - <bean:message key="prompt.program"/>
                    <logic:equal name="serviceProviderForm" property="actionType" value="viewProgram"> <bean:message key="prompt.details"/>
                    </logic:equal></td>
                  </tr>
                </table>
                <!-- END HEADING TABLE -->
                <!-- BEGIN INSTRUCTION TABLE -->
                <table width="98%" border="0">
                	<tr>
       
                       	<logic:notEqual name="serviceProviderForm" property="confirmMessage" value="">
                    		<td class="confirm"><bean:write name="serviceProviderForm" property="confirmMessage"/></td>
                    	</logic:notEqual>
                    	           	
                    
                  </tr>
                </table>
                   <!-- BEGIN INSTRUCTION TABLE -->
                <table width="98%" border="0">
                  <tr>
                    <td>
                      <ul>
                      <logic:notEqual name="serviceProviderForm" property="actionType" value="viewProgram">
	                        <logic:empty name="serviceProviderForm" property="currentProgram.services">
	                        	<li>Please add at least 1 service.</li>
	                        </logic:empty>
                        	<logic:notEmpty name="serviceProviderForm" property="currentProgram.services">
                        		<li>Services may be added, updated or deleted.</li>
                        	</logic:notEmpty>
                       </logic:notEqual>
                      </ul>
                    </td>
                  </tr>
               
                </table>
                 
	                 <!--header start-->
	                <table cellpadding=1 cellspacing=0 border=0 width=98%>
						<tr>
						<td bgcolor=#cccccc>
						<table width=100% border=0 cellpadding=2 cellspacing=1>
						<tr>
							<td class="headerLabel" width=1% nowrap>Provider <bean:message key="prompt.name" /></td>
							<td colspan=3 class=headerData><bean:write name="serviceProviderForm" property="providerName"/></td>
						</tr>
						<tr>
							<td class="headerLabel"><bean:message key="prompt.start" /> <bean:message key="prompt.date" /></td>
							<td class="headerData"><bean:write name="serviceProviderForm" property="startDate" formatKey="date.format.mmddyyyy"/></td>
							<td class="headerLabel" width=1% nowrap><bean:message key="prompt.inHouse" /></td>
							<td class="headerData"><bean:write name="serviceProviderForm" property="isInHouse"/></td>
						</tr>
					</table>
					</td>
					</tr>
					</table>
	              <!--header end-->
	          <br>
                <!--program info start-->
                <table width="98%" border="0" cellspacing=0 class=borderTableBlue>
                  <tr>
                    <td class=detailHead>
                      <table width=100% cellpadding=2 cellspacing=0>
                         <tr>
                          <td width=1%><a href=""></a></td>
                           <td width=1%><a href="javascript:showHideMulti('program', 'pr', 1,'/<msp:webapp/>')"><img border=0 src="/<msp:webapp/>/images/expand.gif" name="program"></a></td>
                          <td class=detailHead><bean:write name="serviceProviderForm" property="currentProgram.programName"/></td>
                        </tr>
                       
                      </table>
                    </td>
                  </tr>
                  <tr class=hidden id=pr0>
                    <td>
                      <table width=100% cellpadding=4 cellspacing=1>
                     
                         <tr>
                         	<td class=formDeLabel width=1% nowrap><bean:message key="prompt.name" /></td>
                         	<td class=formDe id="progName"> <bean:write name="serviceProviderForm" property="currentProgram.programName"/> </td>
                        	<td class=formDeLabel nowrap width='1%'>Maximum Youth In Program</td>
                      		<td class=formDe> <bean:write name="serviceProviderForm" property="currentProgram.maxYouth"/> </td>  
                           
                        </tr>
                        <tr>
                   			<td class=formDeLabel width=1% nowrap><bean:message key="prompt.targetIntervention"/></td>
                       		<td class=formDe > <bean:write property="currentProgram.targetIntervention" name="serviceProviderForm"/> 
                       		</td>
                       		<td class=formDeLabel width=1% nowrap>TJJD EDI Code</td>
                          <td colspan=3 class=formDe id="progName"> <bean:write name="serviceProviderForm" property="currentProgram.tjjdEdiCode"/> </td>
                        </tr>                     
                        </tr>
                        <tr>
	                      <td class=formDeLabel width=1% nowrap><bean:message key="prompt.code" /></td>
                          <td class=formDe> <bean:write name="serviceProviderForm" property="currentProgram.programCode"/> </td>
                          <td  class=formDeLabel width=1% nowrap>Program Referral Transferable ?</td>
                     		<td class=formDe>
                     			<logic:equal name="serviceProviderForm" property="currentProgram.transferredProgRef" value="1">
		                      		Yes
		                      	</logic:equal>
		                      	<logic:equal name="serviceProviderForm" property="currentProgram.transferredProgRef" value="0">
		                      		No
		                      	</logic:equal>
                     		</td>  
	                                      
	                    </tr>
                      
                        <!--tr>
                          <td class=formDeLabel><bean:message key="prompt.type"/></td>
                          <td colspan=3 class=formDe> <bean:write property="currentProgram.type" name="serviceProviderForm"/> </td>
                        </tr>
                        <tr>
                          <td class=formDeLabel><bean:message key="prompt.subType"/></td>
                          <td colspan=3 class=formDe><bean:write property="currentProgram.subType" name="serviceProviderForm"/></td>
                        </tr-->
                        <tr>
                          <td class=formDeLabel width=1% nowrap><bean:message key="prompt.state"/> <bean:message key="prompt.program"/> <bean:message key="prompt.code"/></td>
                          <td class=formDe> <bean:write property="currentProgram.stateProgramCode" name="serviceProviderForm"/></td>                          
                           <td colspan="2" rowspan="4" class="formDe">
		                       <logic:notEmpty name="serviceProviderForm" property="currentProgram.supervisionCategories">	 
		                       	 <table>
		                       	 	<tr>
		                       	 		<td class=formDeLabel>Selected Supervision Categories</td>
		                       	 	</tr>
			                       	 <logic:iterate name="serviceProviderForm" id="supervisionCategory" property="currentProgram.supervisionCategories">
			                       	 	<tr>
			                       	 		<td>
			                       	 			<bean:write property="code" name="supervisionCategory"/> - <bean:write property="description" name="supervisionCategory"/>
			                       	 		</td>
			                       	 	</tr>
			                       	 </logic:iterate>
		                       	 </table>
	                       	 </logic:notEmpty>
	                       	 <logic:empty name="serviceProviderForm" property="currentProgram.supervisionCategories">	 
		                       	 <table>
		                       	 	<tr>
		                       	 		<td class=formDeLabel>Selected Supervision Categories</td>
		                       	 	</tr>
		                       	 	<tr>
		                       	 		<td>None</td>
		                       	 	</tr>
		                     	</table>
	                       	 </logic:empty>
						 </td>    
                        </tr>
                        <tr>
	                      <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.program"/> <bean:message key="prompt.type"/> <bean:message key="prompt.code"/></td>
	                      <td class=formDe> <bean:write property="currentProgram.typeProgramCode" name="serviceProviderForm"/></td>
                                   
                    	</tr>
                        <tr>
                          <td class=formDeLabel nowrap> <bean:message key="prompt.program"/> <bean:message key="prompt.start"/> <bean:message key="prompt.date"/></td>
                          <td class=formDe><bean:write name="serviceProviderForm" property="currentProgram.startDate"/> </td>
                          
                        </tr>
                        <tr> <!--//added for U.S #11099 -->
                       		<td class=formDeLabel><bean:message key="prompt.createProgram"/></td>
                        	<td class=formDe> <bean:write property="currentProgram.programScheduleType" name="serviceProviderForm"/> </td>
                        </tr>
                          <!--//added for U.S #11376-->
	                    <tr> 
	                      <td class='formDeLabel'><bean:message key="prompt.program"/> <bean:message key="prompt.location"/></td>
	                      <td class='formDe'>
	                        <bean:write property="currentProgram.programLocationStr" name="serviceProviderForm"/>
	                       </td>
		                     <td class=formDeLabel nowrap width='1%'>Discontinue Date</td>
	                      		<td class=formDe> <bean:write name="serviceProviderForm" property="currentProgram.discontinueDate"/> 
	                      	</td>                   
	                    </tr>
	                    <tr> 
	                      <td class='formDeLabel'><bean:message key="prompt.category"/></td>
	                      <td class='formDe'>
	                        <bean:write property="currentProgram.programCategoryStr" name="serviceProviderForm"/>
	                        </td>  
	                         <td class=formDeLabel nowrap width=1%> <bean:message key="prompt.program"/> <bean:message key="prompt.end"/> <bean:message key="prompt.date"/></td>
                          <td class=formDe> <bean:write name="serviceProviderForm" property="currentProgram.endDate"/> </td>                 
	                    </tr>
	                    <tr>
	                        <td class='formDeLabel'><bean:message key="prompt.source"/> <bean:message key="prompt.fund"/></td>
	                      	<td class='formDe'><bean:write property="currentProgram.programFundSourceStr" name="serviceProviderForm"/></td>
	                      	<td class='formDeLabel' nowrap width='1%'>Contract Database OID</td>
                      		<td class='formDe'>
                        	<bean:write property="currentProgram.programID" name="serviceProviderForm"/></td> 
	                    </tr>
	                    <tr>                     
	                      <td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.fund"/> <bean:message key="prompt.start"/> <bean:message key="prompt.date"/></td>
	                      <td class='formDe'><bean:write name="serviceProviderForm" property="currentProgram.fundStartDate" /></td>	
						  <td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.fund"/> <bean:message key="prompt.end"/> <bean:message key="prompt.date"/></td>
	                      <td class='formDe'><bean:write name="serviceProviderForm" property="currentProgram.fundEndDate"/></td>
	                    </tr>
                        <tr>
                          <td colspan=4 class=formDeLabel><bean:message key="prompt.description"/></td>
                        </tr>
                        <tr>
                          <td colspan=4 class=formDe> <bean:write name="serviceProviderForm" property="currentProgram.description"/> </td>
                        </tr>
                         <tr>
                          <td colspan=4 class=formDe></td>
                        </tr>
                      </table>
                      
                       <!--program fund history start-->
                 <logic:notEmpty name="serviceProviderForm" property="currentProgram.funds">
					  	
					        		  <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					        		  	 <tr class=detailHead>
					                          <td>Program Source Fund History</td>
					                     </tr>
					                     <tr>
					                          <td>
					                            <table width="100%" cellpadding="2" cellspacing="1" align="center">
					                              <tr class=formDeLabel>
						                                <td valign=top><bean:message key="prompt.entryDate" /></td>
						                                <td valign=top><bean:message key="prompt.sourceFund"/></td>
						                                <td valign=top><bean:message key="prompt.fund"/> <bean:message key="prompt.start"/> <bean:message key="prompt.date"/></td>
						                                <td valign=top><bean:message key="prompt.fund"/> <bean:message key="prompt.end"/> <bean:message key="prompt.date"/></td>	
						                                <td valign=top><bean:message key="prompt.status"/></td>                               
						                          </tr>
						                      
						                  				 <% int RecordCounter=0;
						                                  int locCounter=0;
									                      String bgcolor="";
									                      String selectUser=""; %>
						                              <logic:iterate name="serviceProviderForm" id="fundsIndex" property="currentProgram.funds">
						                              	 <tr class= <% RecordCounter++;
									                     		bgcolor = "alternateRow";                      
						            				     		if (RecordCounter % 2 == 1)
									                         	bgcolor = "normalRow";
						               				     		out.print(bgcolor); %>>			                             
								                                <td><bean:write name="fundsIndex" property="fundEntryDate" formatKey="date.format.mmddyyyy"/></td>
								                                <td><bean:write name="fundsIndex" property="programSourceFund"/></td>
								                                <td><bean:write name="fundsIndex" property="fundStartDate" formatKey="date.format.mmddyyyy"/></td>
								                                <td><bean:write name="fundsIndex" property="fundEndDate" formatKey="date.format.mmddyyyy"/></td>
								                                <td><bean:write name="fundsIndex" property="fundStatus"/></td>
								                          </tr>
								                         
						                              </logic:iterate>
						                            </table>
						                       </td>
						                  </tr>
					        		  </table>
					        
					  </logic:notEmpty>
					 <!--program fund history end-->
                    </td>
                  </tr>
                </table>
                <!--program info end-->
               
                <br>
                <table width="98%" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width=90%>
                      <table width="100%" cellpadding="2" cellspacing="0" class=borderTableBlue>
                        <tr class=detailHead>
                          <td>Services</td>
                        </tr>
                        <tr>
                          <td>
                            <table width="100%" cellpadding="2" cellspacing="1">
                              <tr class=formDeLabel>
	                                <td width=1%></td>
	                                <td valign=top><bean:message key="prompt.name" /></td>
	                                <td valign=top><bean:message key="prompt.code"/></td>
	                                <td valign=top><bean:message key="prompt.type"/></td>
	                                <td valign=top><bean:message key="prompt.location"/> Unit(s)</td>
	                                <td valign=top>Max Enrollment</td>
	                                <td valign=top><bean:message key="prompt.status"/></td>                               
	                              </tr>
	                               <% int RecordCounter=0;
	                                  int locCounter=0;
				                      String bgcolor="";
				                      String selectUser=""; %>
	                              <logic:iterate name="serviceProviderForm" id="serviceIndex" property="currentProgram.services">
		                               <tr class= <% RecordCounter++;
				                     		bgcolor = "alternateRow";                      
	            				     		if (RecordCounter % 2 == 1)
				                         	bgcolor = "normalRow";
	               				     		out.print(bgcolor); %>>			                             
			                                <td>
			                              		<bean:define id="service" name="serviceIndex" property="serviceId"/>
			                              		<logic:notEmpty name="serviceProviderForm" property="currentProgram.services">
	                                  				<logic:notEqual name="serviceProviderForm" property="statusId" value="I">
    	                             	  				<logic:notEqual name="serviceProviderForm" property="currentProgram.statusId" value="I">
        	                         	  					<logic:notEqual name="serviceIndex" property="statusId" value="I">
            	                      							<input type="radio" name="selectedServiceValue" value="<%=service%>"/>
                	                  						</logic:notEqual>	
                    	              					</logic:notEqual>	
                        	          				</logic:notEqual>
	                                  			</logic:notEmpty>
			                                </td>
			                                <td>
			                                	<logic:equal name="serviceIndex" property="statusId" value="P">
			                                		<a href="javascript:loadView('/<msp:webapp/>handleProgramDashboard.do?submitAction=View', 'view')" style="font-style: Italic" onclick="setSelectedVal('<bean:write name="serviceIndex" property="serviceId"/>')">
			                                	</a>
			                                	</logic:equal>
			                                  	<logic:notEqual name="serviceIndex" property="statusId" value="P">
			                                		<a href="javascript:loadView('/<msp:webapp/>handleProgramDashboard.do?submitAction=View', 'view')" onclick="setSelectedVal('<bean:write name="serviceIndex" property="serviceId"/>')">
			                                	</a>
			                                	</logic:notEqual>
			                                 	<bean:write name="serviceIndex" property="serviceName"/></td>
			                                <td><bean:write name="serviceIndex" property="code"/></td>
			                                <td><bean:write name="serviceIndex" property="type"/></td>
			                                <td>
			                                 <% locCounter=0;%>
			                                	<logic:notEmpty name="serviceIndex" property="previousLocations">
			                                		<span id="firstLine<%=RecordCounter%>" class=visible>
			                            				<table width="100%" cellpadding="2" cellspacing="1">
				                            				<tr>
						                                  	<logic:iterate name="serviceIndex" property="previousLocations" id="prev" >
						                                  		<%locCounter++;%>
							                                  	<% if(locCounter == 1){ %>
																    <td>
																    	<bean:write name="prev" property="locationUnitName"/>
																    	<logic:notEqual name="serviceIndex" property="prevLocationsSize" value="1">
																    		<a href="javascript:showHide('locTable<%=RecordCounter%>', 1); showHide('firstLine<%=RecordCounter%>', 0)">more...</a>
																    	</logic:notEqual>
																    </td>
															       	<% }%>
							                               	</logic:iterate>
							                               	</tr>				                               	  
														</table> 
						                            </span>
				                             <% locCounter=0;%>
				                               		<span class=hidden id="locTable<%=RecordCounter%>">
    													<div class=scrollingDiv100NoBorder>
    									  					<table border=0 width=100%>
							                                	<logic:iterate name="serviceIndex" property="previousLocations" id="prev" >
							                                  	<tr class= <% locCounter++;%>>
						                                  	  		<td> <bean:write name="prev" property="locationUnitName"/></td>							                               	
					                               	  		  	</tr>
					                               	  		  	</logic:iterate>
					                               	     		<tr>
				    									  			<td><a href="javascript:showHide('locTable<%=RecordCounter%>', 0);showHide('firstLine<%=RecordCounter%>', 1)">hide</a></td>
				    									  		</tr>
						                               	  </table>
					                               	   </div>
  										  			</span>
  									  			</logic:notEmpty>
				                            </td>     
				                            <td><bean:write name="serviceIndex" property="maxEnrollment"/></td>
				                            <td>
 												<logic:equal name="serviceIndex" property="statusId" value="A">ACTIVE</logic:equal>
					                            <logic:equal name="serviceIndex" property="statusId" value="P">PENDING</logic:equal>
					                            <logic:equal name="serviceIndex" property="statusId" value="I">INACTIVE</logic:equal>	
											</td>
		                              </tr>
                             	</logic:iterate>
                            
                            </table>
                          </td>
                        </tr>
                        <tr>
                          <td align=center>
                            <table cellpadding="2" cellspacing="0">
                              <tr>
                                <td>
                                <jims2:isAllowed requiredFeatures="CS-ASP-UPDATEJUV">
                                 <logic:notEqual name="serviceProviderForm" property="statusId" value="I">
                                 	  <logic:notEqual name="serviceProviderForm" property="currentProgram.statusId" value="I">
	                                   		<html:submit property="submitAction" onclick="setAction('createService');">
												<bean:message key="button.add"></bean:message>
											</html:submit>
										</logic:notEqual>
								</logic:notEqual>
									<logic:notEmpty name="serviceProviderForm" property="currentProgram.services">
									 <logic:notEqual name="serviceProviderForm" property="statusId" value="I">
                                 	  <logic:notEqual name="serviceProviderForm" property="currentProgram.statusId" value="I">
										
											<html:submit property="submitAction" onclick="setAction('updateService'); return checkRadioFieldSelected(this.form);">
												<bean:message key="button.update"></bean:message>
											</html:submit>										
										</logic:notEqual>
								</logic:notEqual>
									</logic:notEmpty>
								</jims2:isAllowed>
                                  </td>
                              </tr>
                            </table>
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
                <br>
                <!-- BEGIN BUTTON TABLE -->
                <table border="0">
                
                 <tr>
                    			<td>
                   					 <html:submit property="submitAction">
										<bean:message key="button.back"></bean:message>
									</html:submit>
									 <html:submit property="submitAction">
										<bean:message key="button.returnToServiceProvider"></bean:message>
									</html:submit>
									<logic:notEqual name="serviceProviderForm" property="currentProgram.statusId" value="I">
									<jims2:isAllowed requiredFeatures="CS-ASP-UPDATEJUV">
									<input type="button" name="submitAction" onclick="submitUpdate(this.form)" value='Update Program'></input>
									</jims2:isAllowed>
									</logic:notEqual>
									<logic:notEqual name="serviceProviderForm" property="actionType" value="viewProgram">
										<html:submit property="submitAction">
											<bean:message key="button.cancel"></bean:message>
										</html:submit>
									</logic:notEqual>
                    			</td>
                 		 	</tr>
                </table>
              
			
                <!-- END BUTTON TABLE -->
           
          </table>
          <br>
        </td>
      </tr>
    </table>
    <!-- END  TABLE -->

  <br>
  </div>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
