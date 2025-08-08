<!DOCTYPE HTML>
<!-- User hits the 'Submit'button on the Add Program page -->
<!--MODIFICATIONS -->
<!-- 05/24/2006 Uma Gopinath Add Summary JSP -->
<!-- 05/24/2006 Uma Gopinath Update/Inacrivate Summary flow added for ASP part 2 -->
<!-- 10/14/2015 Richard Capestani  #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<html:html>
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

<title><bean:message key="title.heading"/> - programSummary.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body topmargin=0 leftmargin="0">
<html:form action="/submitJuvProviderProgramCreate" >

<logic:equal name="serviceProviderForm" property="actionType" value="addProgram">
  <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|374"> 
</logic:equal>

<logic:equal name="serviceProviderForm" property="actionType" value="updateProgram">
  <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|372"> 
</logic:equal>

<logic:equal name="serviceProviderForm" property="actionType" value="inactivateProgram">
  <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|375"> 
</logic:equal>


<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top><bean:message key="prompt.3.spacer"/></td>
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
                <td align="center" class="header">
                <logic:equal name="serviceProviderForm" property="actionType" value="addProgram">
                  <bean:message key="prompt.add"/> 
                  </logic:equal>
                  <logic:equal name="serviceProviderForm" property="actionType" value="updateProgram">
                  	<bean:message key="prompt.update"/> 
                  </logic:equal>
                   <logic:equal name="serviceProviderForm" property="actionType" value="inactivateProgram">
                  	<bean:message key="prompt.inactivate"/> 
                  </logic:equal>
                  <bean:message key="prompt.program"/> - <bean:message key="title.summary"/></td>
              </tr>
            </table>
            <!-- END HEADING TABLE -->

            <!-- BEGIN INSTRUCTION TABLE -->
            <table width="98%" border="0">
              <tr>
                <td>
                  <ul>
                    <li>Review entries and click Save & Continue or click Back to make changes.</li>
                  </ul>
                </td>
              </tr>
            </table>
            
            <!--header start-->
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
                	</table>
              	</td>
            	</tr>
          	</table>
            <!--header end-->

            <!-- BEGIN  TABLE -->
            <br>
            <table width='98%' cellpadding=2 cellspacing=0 class=borderTableBlue>
              <tr>
                <td class=detailHead> <bean:message key="prompt.program" /> <bean:message key="prompt.info" /> </td>
              </tr>
              <tr>
                <td>
                  <table width='100%' cellpadding=4 cellspacing=1>
                    <tr>
	         			<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.name" /></td>
	                   	<td class=formDe><bean:write name="serviceProviderForm" property="currentProgram.programName"/> </td>
	         			 <td class=formDeLabel nowrap width='1%'>Maximum Youth In Program</td>
                      	<td class=formDe> <bean:write name="serviceProviderForm" property="currentProgram.maxYouth"/> </td>
    				</tr>
    				<tr>
	           			<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.code" /></td>
	                   	<td class=formDe><bean:write name="serviceProviderForm" property="currentProgram.programCode"/> </td>
	                  	<td class=formDeLabel width='1%' nowrap>TJJD EDI Code</td>
	                   	<td class=formDe><bean:write name="serviceProviderForm" property="currentProgram.tjjdEdiCode"/> </td>
                    </tr>                  
                    <tr>
                      <td class=formDeLabel><bean:message key="prompt.targetIntervention"/></td>
                       <td class=formDe> <bean:write property="currentProgram.targetIntervention" name="serviceProviderForm"/> </td> 
                       <td class=formDeLabel width='1%' nowrap>Program Referral Transferable ?</td>
	                    <td class=formDe>
		                      <logic:equal name="serviceProviderForm" property="currentProgram.transferredProgRef" value="1">
		                      	Yes
		                      </logic:equal>
		                      <logic:equal name="serviceProviderForm" property="currentProgram.transferredProgRef" value="0">
		                      	No
		                      </logic:equal>
		       			</td>
                    </tr>
                    <tr>
                      <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.state"/> <bean:message key="prompt.program"/> <bean:message key="prompt.code"/></td>
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
					 </td> 
                                   
                    </tr>
                    <tr>
                      <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.program"/> <bean:message key="prompt.type"/> <bean:message key="prompt.code"/></td>
                      <td class=formDe> <bean:write property="currentProgram.typeProgramCode" name="serviceProviderForm"/></td>

                                   
                    </tr>
                    <tr>
                      <td class=formDeLabel nowrap><bean:message key="prompt.program"/> <bean:message key="prompt.start"/> <bean:message key="prompt.date"/></td>
                      <td class=formDe><bean:write name="serviceProviderForm" property="currentProgram.startDate"/> </td>
                      
                    </tr>
                      <tr> <!--added for U.S #11099-->
                      <td class=formDeLabel><bean:message key="prompt.createProgram"/></td>
                       <td class=formDe> <bean:write property="currentProgram.programScheduleType" name="serviceProviderForm"/> </td>  
                                           
                    </tr>
                   
                    <tr>  <!--added for U.S #11376-->
                      <td class='formDeLabel'><bean:message key="prompt.program"/> <bean:message key="prompt.location"/></td>
                      <td class='formDe'>
                        <bean:write property="currentProgram.programLocationStr" name="serviceProviderForm"/>
                      </td>  
	                     <td class=formDeLabel nowrap width='1%'>Discontinue Date</td>
                      	<td class=formDe> <bean:write name="serviceProviderForm" property="currentProgram.discontinueDate"/> </td>               
                    </tr>
                    <tr> 
                      <td class='formDeLabel'><bean:message key="prompt.category"/></td>
                      <td class='formDe'>
                        <bean:write property="currentProgram.programCategoryStr" name="serviceProviderForm"/>
                      </td>
                      <td class=formDeLabel nowrap width='1%'> <bean:message key="prompt.program"/> <bean:message key="prompt.end"/> <bean:message key="prompt.date"/></td>
                      <td class=formDe> <bean:write name="serviceProviderForm" property="currentProgram.endDate"/> </td>                          
                    </tr>
                    <tr>
                        <td class='formDeLabel'><bean:message key="prompt.source"/> <bean:message key="prompt.fund"/></td>
                      	<td  class='formDe'><bean:write property="currentProgram.programFundSourceStr" name="serviceProviderForm"/>
                      	</td>
                      	 <td class='formDeLabel' nowrap width='1%'>Contract Database OID</td>
                      <td class='formDe'>
                        <bean:write property="currentProgram.programID" name="serviceProviderForm"/>
                      </td>                      	
                    </tr>
                    <tr>                     
                      <td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.fund"/> <bean:message key="prompt.start"/> <bean:message key="prompt.date"/></td>
                      <td class='formDe'><bean:write name="serviceProviderForm" property="currentProgram.fundStartDate" /></td>	
                      <td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.fund"/> <bean:message key="prompt.end"/> <bean:message key="prompt.date"/></td>
	                      <td class='formDe'><bean:write name="serviceProviderForm" property="currentProgram.fundEndDate"/>
	                  </td>					  
                    </tr>
                    <tr>
                      <td colspan=4 class=formDeLabel><bean:message key="prompt.description"/></td>
                    </tr>
                    <tr>
                       <td class='formDe' colspan='4'><html:textarea rows="8" style="width:100%" name="serviceProviderForm" property="currentProgram.description"></html:textarea></td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>

            <!-- BEGIN BUTTON TABLE -->
            <br>
            <table border="0">
              <tr>
                <td align="center">
								  <html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
      						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
      							<bean:message key="button.saveAndContinue"></bean:message>
      						</html:submit>
      						<html:submit property="submitAction">
      							<bean:message key="button.cancel"></bean:message>
      						</html:submit>
								</td>
              </tr>
            </table>
            <!-- END BUTTON TABLE -->
          </td>
        </tr>    	
      </table><br>
    </td>
  </tr>
   <logic:notEmpty name="serviceProviderForm" property="currentProgram.funds">
  	 <tr>
        	<td>
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
        	</td>
        </tr>
  </logic:notEmpty>
</table>
<!-- END  TABLE -->
</div>

</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>

</html:html>
