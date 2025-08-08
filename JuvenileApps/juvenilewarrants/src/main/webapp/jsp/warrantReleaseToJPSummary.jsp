<!-- Used to display release to JP summary and confirmation -->
<!--MODIFICATIONS -->
<%-- CShimek	02/09/2005	Create JSP --%>
<%-- CShimek    02/02/2006  Add hidden fields for HelpFile name --%>
<%-- LDeen	    12/21/2006  Revised help file code --%>
<%-- CShimek	03/22/2007  #40475 added missing weight unit annotation --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RYoung     08/06/2015 #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!DOCTYPE HTML>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<html:base />
<title><bean:message key="title.heading"/> - warrantReleaseToJPSummary.jsp</title>
</head>
<!--BEGIN HEADER TAG-->

<!--BEGIN BODY TAG-->
<body>
<html:form action="/submitReleaseToJP" target="content">

<!-- BEGIN HEADING AND INSTRUCTION TABLES -->
<logic:equal name="juvenileWarrantForm" property="action" value="summary"> 
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|108">	
   <table align="center">
     <tr>
        <td class="header">
          <bean:message key="title.juvWarrantReleaseToJP"/> Summary
        </td>
     </tr>
   </table>
   <table width="98%">
   	<tr>
     	<td><ul>
        	<li>Select Finish button to release juvenile to Juvenile Probation.</li>
	  	</ul></td>
  	</tr>
</table>
</logic:equal>
<logic:equal name="juvenileWarrantForm" property="action" value="confirm">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|104">	
   <table align="center">
     <tr>
        <td class="header">
          <bean:message key="title.juvWarrantReleaseToJP"/> Confirmation
        </td>  
     </tr>
   </table>
   <table width="98%">
   	 <tr>
       <td align="center" class="confirm">Juvenile successfully released to Juvenile Probation.</td>
    </tr>
  </table>
  <br>
</logic:equal>
<!-- END HEADING AND INSTRCTION TABLE -->
<!-- BEGIN JUVENILE INFORMATION TABLE -->
          <table align="center" cellspacing="1" width=98% cellpadding=4>
          	<!-- BEGIN JUVENILE INFORMATION TABLE -->
				  	<tr>
						<td colspan=4 class=detailHead nowrap><bean:message key="prompt.juvenileInfo" /></td>
					</tr>
				    <tr>
				        <td class="formDeLabel"><bean:message key="prompt.juvenileName"/></td>          
				        <td class="formDe" colspan="3">
				         <bean:write name="juvenileWarrantForm" property="juvenileName.lastName"/>, 
				         <bean:write name="juvenileWarrantForm" property="juvenileName.firstName"/>
				         <bean:write name="juvenileWarrantForm" property="juvenileName.middleName"/> 
				         <bean:write name="juvenileWarrantForm" property="nameSuffix"/>
				        </td>
				     </tr>
				     <tr>
				        <td class="formDeLabel"><bean:message key="prompt.aka"/></td>
				        <td class="formDe" colspan=3><bean:write name="juvenileWarrantForm" property="aliasName"/></td>
				     </tr>
				     <tr>
				        <td class="formDeLabel"><bean:message key="prompt.dateOfBirth"/></td>
				        <td class="formDe"><bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
				        <td class="formDeLabel" width=1% nowrap><bean:message key="prompt.juvenileNumber"/></td>
				        <td class="formDe"><bean:write name="juvenileWarrantForm" property="juvenileNum"/></td>
				     </tr>
				     <tr>
				        <td class="formDeLabel"><bean:message key="prompt.race"/></td>
				        <td class="formDe"><bean:write name="juvenileWarrantForm" property="race"/></td>
				        <td class="formDeLabel"><bean:message key="prompt.sex"/></td>
				        <td class="formDe"><bean:write name="juvenileWarrantForm" property="sex"/></td>
				     </tr>
				     <tr>
				        <td class="formDeLabel"><bean:message key="prompt.height"/></td>
				        <td class="formDe">
				       		<logic:notEqual name="juvenileWarrantForm" property="height" value="">
     							<logic:notEqual name="juvenileWarrantForm" property="heightFeet" value="0">
				           			<bean:write name="juvenileWarrantForm" property="heightFeet"/>ft&nbsp;
           							<bean:write name="juvenileWarrantForm" property="heightInch"/>in&nbsp;
				           		</logic:notEqual> 
							</logic:notEqual>
				        </td>
				        <td class="formDeLabel"><bean:message key="prompt.weight"/></td>
				        <td class="formDe">
				 			<logic:notEqual name="juvenileWarrantForm" property="weight" value="">
								<logic:notEqual name="juvenileWarrantForm" property="weight" value="0">
									<bean:write name="juvenileWarrantForm" property="weight"/>&nbsp;lbs
								</logic:notEqual>	
							</logic:notEqual>
				        </td>
				     </tr>
				     <tr>
				        <td class="formDeLabel"><bean:message key="prompt.eyeColor"/></td>
				        <td class="formDe" colspan="3">               
				          <bean:write name="juvenileWarrantForm" property="eyeColor"/>
				        </td>
				     </tr>
				     <tr>
				        <td class="formDeLabel"><bean:message key="prompt.hairColor"/></td>
				        <td class="formDe" colspan="3">               
				          <bean:write name="juvenileWarrantForm" property="hairColor"/>
				        </td>
				     </tr>
				     <tr>
				        <td class="formDeLabel"><bean:message key="prompt.complexion"/></td>
				        <td class="formDe" colspan="3">                
				          <bean:write name="juvenileWarrantForm" property="complexion"/>
				        </td>
				     </tr>   
				     <tr>
				        <td class="formDeLabel"><bean:message key="prompt.scarsMarks"/></td>
				        <td class="formDe" colspan="3">
				           <logic:notEmpty name="juvenileWarrantForm" property="scarsAndMarksSelected">
				             <logic:iterate id="scars" name="juvenileWarrantForm" property="scarsAndMarksSelected">
				                <bean:write name="scars" property="description" /><br>
				           	</logic:iterate>
				           </logic:notEmpty>
				        </td>
				     </tr>
				     <tr>
				        <td class="formDeLabel"><bean:message key="prompt.tattoos"/></td>
				        <td class="formDe" colspan="3">
				            <logic:notEmpty name="juvenileWarrantForm" property="tattoosSelected">
				              	<logic:iterate id="tattoo" name="juvenileWarrantForm" property="tattoosSelected">
				                  		<bean:write name="tattoo" property="description" /><br>
				               	</logic:iterate>
				            </logic:notEmpty>
				        </td>
				     </tr>
				     <tr>
				        <td class="formDeLabel"><bean:message key="prompt.schoolDistrict"/></td>
				        <td class="formDe" colspan="3">
				           <bean:write name="juvenileWarrantForm" property="schoolDistrictName"/>
				        </td> 
				    </tr>
				    <tr>        
				        <td class="formDeLabel"><bean:message key="prompt.schoolName"/></td>
				        <td class="formDe" colspan="3">
				          <bean:write name="juvenileWarrantForm" property="schoolName"/>
				        </td> 
				    </tr>  
				<!-- END JUVENILE INFORMATION TABLE -->
            <tr><td><br></td></tr>
				    <!-- BEGIN WARRANT INFORMATION TABLE -->
				    <tr>
								 	<td class=detailHead nowrap colspan=4><bean:message key="prompt.juvenileWarrantInfo" /></td>
								</tr>
								<tr>
				        <td class="formDeLabel"><bean:message key="prompt.warrantNumber"/></td>          
				        <td class="formDe"><bean:write name="juvenileWarrantForm" property="warrantNum"/></td>
				        <td class="formDeLabel"><bean:message key="prompt.warrantStatus"/></td>
				        <td class="formDe"><bean:write name="juvenileWarrantForm" property="warrantStatus"/></td>
				     </tr>
				     <tr>
				        <td class="formDeLabel"><bean:message key="prompt.warrantType"/></td>
				        <td class="formDe" colspan="3">
				           <bean:write name="juvenileWarrantForm" property="warrantType"/>
				        </td>
				     </tr>
				     <tr>
				        <td class="formDeLabel"><bean:message key="prompt.warrantOriginatorName"/></td>
				        <td class="formDe" colspan="3">
				           <bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/>
				        </td>
				     </tr>
				     <!-- END WARRANT INFORMATION TABLE -->
				     <tr><td><br></td></tr>     
						<%-- JJS has single charge, JOT can have multiple charges -- need to add logic tags? --%>
						<!-- BEGIN CHARGE INFORMATION TABLE -->						
						<tr>
							<td colspan=4 class=detailHead nowrap><bean:message key="prompt.chargeInfo"/></td>
						</tr>
						<logic:empty name="juvenileWarrantForm" property="charges">
				        <tr>
				          <td colspan=4>No charges</td>
		       			</tr>
			     		</logic:empty>
			     		<logic:notEmpty name="juvenileWarrantForm" property="charges">
				    		<logic:iterate id="chargeIndex" name="juvenileWarrantForm" property="charges"> 
			        		<tr>    
			         			<td class=formDeLabel><bean:message key="prompt.charge" /></td>
			         			<td class=formDe colspan=3><bean:write name="chargeIndex" property="offense"/></td>
			       			</tr>
			      			<tr>	         
			                    <td class=formDeLabel><bean:message key="prompt.petitionNumber"/></td>
				 				<td class=formDe colspan=3><bean:write name="chargeIndex" property="petitionNum"/></td>
					       </tr>
					       <tr>  				
			  					<td class="formDeLabel"><bean:message key="prompt.chargeCourt"/></td> 
			  					<td class=formDe colspan=3><bean:write name="chargeIndex" property="court"/></td>
						   </tr> 				
                           <tr>
			  				<td class="formDeLabel"><bean:message key="prompt.chargeNCICNumber"/></td>
				   			<td class=formDe colspan=3><bean:write name="chargeIndex" property="offenseCodeId"/></td>
			   			   </tr>
			   			   <tr><td><br></td></tr>
                       </logic:iterate>
			       	</logic:notEmpty>
		           <!-- end Charge-->
		<!-- END CHARGE INFORMATION TABLE -->
		            <!-- BEGIN SERVICE INFORMATION TABLE -->
		            <tr>
						<td colspan=4 class=detailHead nowrap><bean:message key="prompt.serviceInfo" /></td>
					</tr>
					<tr>
		            	<td class="formDeLabel"><bean:message key="prompt.serviceAddress"/></td>          
		            	<td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="serviceAddress"/></td>             
		            </tr>
		            <tr>    
		                <td class="formDeLabel"><bean:message key="prompt.serviceStatus"/></td>
		                <td class="formDe" colspan="3">
		                   <bean:write name="juvenileWarrantForm" property="serviceStatus"/>
		                </td>
		             </tr>
		            <tr>
		                <td class="formDeLabel"><bean:message key="prompt.serviceDate"/></td>          
		                <td class="formDe"><bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="date.format.mmddyyyy" /></td>
		                <td class="formDeLabel"><bean:message key="prompt.serviceTime"/></td>
		                <td class="formDe"><bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="time.format.HHmm" /></td>
		             </tr>   
		             <!-- END SERVICE INFORMATION TABLE -->  
		             <tr><td><br></td></tr>
		             <!-- BEGIN ARREST INFORMATION TABLE -->
		             <tr>
									 	<td class=detailHead nowrap colspan=4><bean:message key="prompt.arrestInfo" /></td>
									</tr>
		             <tr>
		                <td class="formDeLabel"><bean:message key="prompt.arrestAddress"/></td>          
		                <td colspan=3 class="formDe">
		                   <bean:write name="juvenileWarrantForm" property="arrestAddress"/>
		                </td>
			            </tr>
			            <tr>
		                <td class="formDeLabel"><bean:message key="prompt.arrestDate"/></td>          
		                <td class="formDe"><bean:write name="juvenileWarrantForm" property="arrestDate" formatKey="date.format.mmddyyyy" /></td>
                <td class="formDeLabel"><bean:message key="prompt.arrestTime"/></td>
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="arrestDate" formatKey="time.format.HHmm" /></td>
             </tr>     
             <!-- END ARREST INFORMATION TABLE -->  
             <tr><td><br></td></tr>
             <!-- BEGIN EXECUTOR INFORMATION -->
             <tr>
					<td colspan=4 class=detailHead nowrap><bean:message key="prompt.executorInfo" /></td>
			</tr>
			<tr>
	                <td class="formDeLabel"><bean:message key="prompt.officerName"/></td>          
	                <td class="formDe" colspan="3">
	                   <bean:write name="juvenileWarrantForm" property="executorName.formattedName"/>
	                </td>
	            </tr>
	            <tr>
	               <td class="formDeLabel" width=1% nowrap><bean:message key="prompt.officerIdNumber"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorId"/></td>
	               <td class="formDeLabel" width=1% nowrap><bean:message key="prompt.officerIdType"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorIdType"/></td> 
	            </tr>
	            <tr>   
		           <td class="formDeLabel"><bean:message key="prompt.department"/></td>
	               <td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="executorDepartmentName"/></td>
	            </tr>            
	            <tr>
	               <td class="formDeLabel"><bean:message key="prompt.workPhone"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorPhoneNum"/></td>
	               <td class="formDeLabel"><bean:message key="prompt.cellPhone"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorCellNum"/></td>
	            </tr>
	            <tr>
	               <td class="formDeLabel"><bean:message key="prompt.pager"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorPager"/></td>
		           <td class="formDeLabel"><bean:message key="prompt.email"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorEmail"/></td>
	            </tr>   
	            <!-- END EXECUTOR INFORMATIN TABLE -->  
	            <tr><td><br></td></tr>
	            <!-- BEGIN RELEASE DECISION INFORMATION TABLES -->
	            <tr>
					<td colspan=4 class=detailHead nowrap><bean:message key="prompt.releaseDecisionInfo" /></td>
				</tr>
				<tr>
	                <td class="formDeLabel"><bean:message key="prompt.jpOfficerName"/></td>          
	                <td class="formDe" colspan="3">
	                   <bean:write name="juvenileWarrantForm" property="releaseDecisionUserName"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="formDeLabel"><bean:message key="prompt.releaseDecision"/></td>          
	                <td class="formDe" colspan="3">
	                   <bean:write name="juvenileWarrantForm" property="releaseDecision"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="formDeLabel"><bean:message key="prompt.releaseDecisionDate"/></td>          
	                <td class="formDe"><bean:write name="juvenileWarrantForm" property="releaseDecisionDate" formatKey="date.format.mmddyyyy" /></td>
	                <td class="formDeLabel"><bean:message key="prompt.releaseDecisionTime"/></td>
	                <td class="formDe"><bean:write name="juvenileWarrantForm" property="releaseDecisionDate" formatKey="time.format.HHmm" /></td>
	             </tr>       
<!-- END RELEASE DECISION INFORMATION TABLES -->
							<tr><td><br></td></tr>
							<!-- BEGIN TRANSFER INFORMATION TABLE -->
							<tr>
								<td class=detailHead colspan=4 nowrap><bean:message key="prompt.transferInfo"/></td>
							</tr>
							<tr>
							<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.officerIdNumber"/></td>
		    				<td class="formDe"><bean:write name="juvenileWarrantForm" property="transferOfficerId"/></td>
							<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.officerIdType"/></td>
		    				<td class="formDe"><bean:write name="juvenileWarrantForm" property="transferOfficerIdType"/></td>
		  				</tr>
		  				<tr>
							<td class="formDeLabel"><bean:message key="prompt.department"/></td>
		    				<td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="transferOfficerDepartmentName"/></td>
						</tr>
		       		
		  				<tr>
		    				<td class="formDeLabel"><bean:message key="prompt.transferDate"/></td>
		    				<td class="formDe"><bean:write name="juvenileWarrantForm" property="transferCustodyDate" formatKey="date.format.mmddyyyy" /></td>
							<td class="formDeLabel"><bean:message key="prompt.transferTime"/></td>
							<td class="formDe"><bean:write name="juvenileWarrantForm" property="transferCustodyDate" formatKey="time.format.HHmm" /></td>
		  				</tr>		  			
		  				<tr>
							<td class="formDeLabel"><bean:message key="prompt.transferLocation"/></td>
		    				<td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="transferLocation"/></td>
		 			    </tr>
		 			    <!-- END TRANSFER INFORMATION TABLE -->
          </table>
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0" align="center">
	<tr>
	  <td align="center">
 	    <logic:equal name="juvenileWarrantForm" property="action" value="summary">
          <html:button
				property="org.apache.struts.taglib.html.BUTTON"
				onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message>
			</html:button>&nbsp;
	      <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message>
		  </html:submit>&nbsp;
 	  	  <html:submit property="submitAction">
				<bean:message key="button.cancel"></bean:message>
		  </html:submit>     
	  	</logic:equal>
	  	<logic:equal name="juvenileWarrantForm" property="action" value="confirm">  
  		   <html:submit property="submitAction">
       				 <bean:message key="button.mainPage"></bean:message>
       				 </html:submit>
			  <%-- <html:button property="org.apache.struts.taglib.html.CANCEL" 
						 onclick="document.location.href='../security.war/jsp/mainMenu.jsp'">
    			   </html:button> 
    		 --%>  
	  	</logic:equal>
	 </td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>