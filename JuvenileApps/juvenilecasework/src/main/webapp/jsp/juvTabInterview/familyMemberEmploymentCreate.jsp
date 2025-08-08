<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" /> 
<html:base />

<%-- Javascript for emulated navigation --%>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyMemberEmploymentCreate.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - familyMemberEmploymentCreate.jsp</title>
</head>

<%--END HEADER TAG--%>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/submitManageFamilyMemberEmploymentAction" >


<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|255">
</logic:notEqual>
<logic:equal name="juvenileMemberForm" property="action" value="viewOnly">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|256">
</logic:equal>

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header">
		  <bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.employment"/>
  		<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly"><bean:message key="prompt.create"/></logic:notEqual>
  		<logic:equal name="juvenileMemberForm" property="action" value="viewOnly"><bean:message key="prompt.details"/></logic:equal>
		</td>
	</tr>
</table>

<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	
	<logic:equal name="juvenileMemberForm" property="action" value="createMemberConfirmation">
		<tr>		  
			<td align="center" class="confirm">Update was successful</td>	
		</tr>  	  
	</logic:equal>	    
</table>
	<%-- END HEADING TABLE --%>
	<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
				

<%-- BEGIN INSTRUCTION TABLE --%> 
<div class='spacer'></div>
<table width="98%" border="0"> 
  <tr> 
    <td> 
		  <ul> 
        <li>Add new employment information for family member and click Add button.</li>
        <li>Click Remove link to remove employment information just added.</li>

        <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_EMP_U%>'>	
      		<li>Review information and click the Update button to proceed.</li>
    		</jims2:isAllowed>

    		<li><bean:message key="prompt.plusSign"/>Required if Salary is non blank and Salary Rate is Hour</li>
      </ul>
	  </td> 
  </tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 
</logic:notEqual>

<%-- BEGIN DETAIL TABLE --%> 
<table cellpadding="1" cellspacing="0" border="0" width="100%" align="center"> 
  <tr> 
    <td>
		  <tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader" />
			</tiles:insert>
		</td> 
  </tr> 
</table> 

<%-- begin the tabs and data --%> 
<div class='spacer'></div>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center"> 
  <tr> 
    <td>
      <table width='100%' border="0" cellpadding="0" cellspacing="0" > 
        <tr> 
          <td valign="top">
					  <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
  						<tiles:put name="tabid" value="family" />
  						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  					</tiles:insert>
						</td> 
          </tr> 
          <tr> 
            <td bgcolor='#33cc66'><img src='../../images/spacer.gif' width="5"></td> 
         </tr> 
      </table> 
        <%-- end green tabs --%> 

        <%-- begin outer green border --%> 
       <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen"> 
        <tr> 
          <td valign="top" align="center"> <%-- begin red tabs (3rd row) --%> 

					<div class='spacer'></div>
          <table width='98%' border="0" cellpadding="0" cellspacing="0" > 
            <tr> 
              <td valign="top">
							  <tiles:insert page="../caseworkCommon/memberInfoTabs.jsp" flush="true">
      						<tiles:put name="tabid" value="Employment" />
      						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
      					</tiles:insert>
							</td> 
            </tr> 
            <tr> 
              <td bgcolor='#6699FF'><img src='../../images/spacer.gif' width=5></td> 
            </tr> 
          </table> 
          <%-- end red tabs --%> 

          <%--begin red outer border --%> 
          <table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue"> 
            <tr> 
              <td valign="top" align="center">
                <%-- BEGIN Previous Employment TABLE --%>
					
		  				<logic:notEmpty name="juvenileMemberForm" property="employmentInfoList">
    						<logic:iterate id="employments" name="juvenileMemberForm" property="employmentInfoList">
    							<logic:notEmpty name="employments" property="memberEmploymentId">
    							 	<logic:notPresent name="ExistingRecords">
        							 <bean:define id="ExistingRecords" value="1" type="java.lang.String"/>
      							 </logic:notPresent>
      						</logic:notEmpty>
      						<logic:empty name="employments" property="memberEmploymentId">
      						   <logic:notPresent name="NewRecords"><bean:define id="NewRecords" value="1" type="java.lang.String"/></logic:notPresent>
      						</logic:empty>
     						</logic:iterate>
            	</logic:notEmpty>

            	<logic:notPresent name="ExistingRecords">
            	<div class='spacer'></div>
            	<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
          			<tr>
          				<td valign="top" class="detailHead">
          					<table width='100%' cellpadding="0" cellspacing="0">
          						<tr>
          							<td class="detailHead">&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.address"/> <bean:message key="prompt.employment"/> - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
          						</tr>
          					</table>
          				</td>
          			</tr>
          			<tr>
          				<td valign="top" class="normalRow">No Employment Information Available</td>
          			</tr>
         			</table>
          		</logic:notPresent>

    					<logic:present name="ExistingRecords">
		    					<div class='spacer'></div>
		    		  		<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
		    		  			<tr>
		    		  				<td valign="top" class="detailHead">
		    		  					<table width='100%' cellpadding="0" cellspacing="0">
		    		  						<tr>
		    		  							<td width='1%'><a href="javascript:showHideMulti('Characteristics', 'pChar', 1,'/<msp:webapp/>')" border=0><img border=0 src="../../images/expand.gif" name="Characteristics"></a></td>
		    		  							<td class="detailHead">&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.employment"/> <bean:message key="prompt.info"/>- <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
		    		  						</tr>
		    		  					</table>
		    		  				</td>
		    		  			</tr>
		    		  			<tr id="pChar0" class="hidden">
		    		  				<td valign="top">
		 										 <table width='100%' bgcolor="#cccccc" cellspacing="1"> 
		                        <tr bgcolor="#cccccc"> 
		                          <td class="subhead" valign="top" width="10%"><bean:message key="prompt.entryDate"/></td>
		                          <td class="subhead"><bean:message key="prompt.employment"/> <bean:message key="prompt.status"/> </td> 
		                          <td class="subhead"><bean:message key="prompt.current"/> <bean:message key="prompt.employer"/></td> 
		                          <td class="subhead"><bean:message key="prompt.jobTitle"/></td> 
		                          <td class="subhead"><bean:message key="prompt.annualGrossIncome"/> </td> 
		                        </tr> 
		            							<logic:iterate indexId="index" id="employments" name="juvenileMemberForm" property="employmentInfoList">
		            							 <logic:notEmpty name="employments" property="memberEmploymentId">
       							             <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>"> 
				                            <td width='10%'><bean:write name="employments" property="entryDate"/></td> 
				                            <td><bean:write name="employments" property="employmentStatus"/></td> 
				                            <td><bean:write name="employments" property="currentEmployer"/></td> 
				                            <td><bean:write name="employments" property="jobTitle"/></td> 
				                            <td><bean:write name="employments" property="formattedAnnualNetIncome" formatKey="currency.format"/></td> 
				                         </tr>
				                       </logic:notEmpty>
		                          </logic:iterate>
		 											</table>
								  				</td>
								  			</tr>
								  		</table>
											 <div class="spacer"></div>
									  		<script  type='text/javascript'>
									       if (location.search == "?confirmPC")
									   			{
									   				showHideMulti('Characteristics', 'pChar', 1)
									   			}
									   		</script>
						   		</logic:present>
								  <%-- END Previous Employment TABLE --%>

                   <logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
	                   <%-- BEGIN ADDRESS INFORMATION TABLES --%>
										<div class='spacer'></div> 
	                   <table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue> 
	                     <tr bgcolor="#B3C9F5"> 
	                       <td class="subhead"><bean:message key="prompt.new"/> <bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.employment"/> <bean:message key="prompt.info"/>  - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td> 
	                     </tr> 
	                     <tr> 
	                       <td align="center"> 
												  <table cellspacing="1" width='100%'> 
	                           <tr> 
	                             <td class="formDeLabel"><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border=0 width=10 height=9>Employment Status</td> 
	                             <td class="formDe" colspan="3"><html:select name="juvenileMemberForm" property="currentEmployment.employmentStatusId"> 
	                                 <option value="">Please Select</option> 
	                                 <html:optionsCollection name="juvenileMemberForm" property="employmentStatusList"  value="code" label="description"/>
	                               </html:select> </td> 
	                           </tr> 
	                           <tr> 
	                             <td class="formDeLabel"><bean:message key="prompt.current"/> <bean:message key="prompt.employer"/> </td> 
	                             <td class="formDe" colspan="3"><html:text styleId="currentEmployer" name="juvenileMemberForm" property="currentEmployment.currentEmployer"  size="50" maxlength="50"/></td> 
	                           </tr> 
	                            <tr> 
	                             <td class="formDeLabel"><bean:message key="prompt.jobTitle"/></td> 
	                             <td class="formDe" colspan="3"><html:text styleId="jobTitle" name="juvenileMemberForm" property="currentEmployment.jobTitle" size="50" maxlength="50"/></td> 
	                           </tr> 
	                           <tr> 
	                             <td class="formDeLabel"><bean:message key="prompt.salary"/></td> 
	                             <td class="formDe"><html:text styleId="salary" name="juvenileMemberForm" property="currentEmployment.salary" size="10" maxlength="10"/></td> 
	                             <td class="formDeLabel"><bean:message key="prompt.salary"/> <bean:message key="prompt.rate"/></td> 
	                             <td class="formDe"> <html:select styleId="salaryRate" name="juvenileMemberForm" property="currentEmployment.salaryRateId" > 
	                                 <option value="">Please Select</option> 
	                                 <html:optionsCollection name="juvenileMemberForm" property="salaryRateList"  value="code" label="description"/>
	                               </html:select> </td> 
	                           </tr> 
	                           <tr> 
	                             <td class="formDeLabel"><bean:message key="prompt.plusSign"/><bean:message key="prompt.hoursWorkedPerWeek"/></td> 
	                             <td class="formDe"> <html:text styleId="workHours" name="juvenileMemberForm" property="currentEmployment.workHours" size="5" maxlength="3"/> </td> 
	                             <td class="formDeLabel"><bean:message key="prompt.lengthOfEmployment"/> </td> 
	                             <td class="formDe"><html:text styleId="lengthOfEmployment" name="juvenileMemberForm" property="currentEmployment.lengthOfEmployment" size="20" maxlength="20"/></td> 
	                           </tr> 
	                          
	                           <tr> 
	                             <td colspan="4"> 
									  <div class="paddedFourPix" align="center"> 
	                                 </html:submit> <html:submit property="submitAction" styleId="addEmployValidate"><bean:message key="button.addToList"/></html:submit>
	                               </div>
								</td> 
	                           </tr> 
	                         </table>
							</td> 
		             </tr> 
                     <tr> 
                       <td> 
                        <logic:present name="NewRecords" >
                        <div class='spacer'></div>
                       
                         <table width='100%' bgcolor="#cccccc" cellspacing="1"> 
                           <tr bgcolor="#f0f0f0"> 
                             <td></td> 
                             <td class="subhead"><bean:message key="prompt.employment"/> <bean:message key="prompt.status"/> </td> 
                             <td class="subhead"><bean:message key="prompt.current"/> <bean:message key="prompt.employer"/></td> 
                             <td class="subhead"><bean:message key="prompt.jobTitle"/></td> 
                             <td class="subhead"><bean:message key="prompt.annualGrossIncome"/></td> 
                           </tr> 
              							<logic:iterate indexId="index" id="employments" name="juvenileMemberForm" property="employmentInfoList">
               							 <logic:empty name="employments" property="memberEmploymentId">
                 							 <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">  
                                 <td width='10%'>
                 									<a href="/<msp:webapp/>submitManageFamilyMemberEmploymentAction.do?submitAction=Remove&selectedValue=<%=(index.intValue())%>">Remove</a>
               									</td> 
                                 <td><bean:write name="employments" property="employmentStatus"/> </td> 
                                 <td><bean:write name="employments" property="currentEmployer"/>  </td> 
                                 <td><bean:write name="employments" property="jobTitle"/> </td> 
                                 <td><bean:write name="employments" property="formattedAnnualNetIncome" formatKey="currency.format"/></td>
														</tr> 
                          </logic:empty>
                         </logic:iterate>
         							 </table>
                        <%-- END DETAIL TABLE --%> 
        							</logic:present>
        						</table>
                   </logic:notEqual>
					
		              <%-- begin button table --%>
		            	<div class="spacer"></div>
		            	<table border="0" width="100%">
		            		<tr>
		            			<td align="center">
		            				<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
		            				<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
		              				<logic:present name="NewRecords" >
		              				  <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_EMP_U%>'>	
		                  				 <html:submit onclick="spinner()" property="submitAction" ><bean:message key="button.update"></bean:message></html:submit>
		                				</jims2:isAllowed>
		              				</logic:present>
		              				<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
		            				</logic:notEqual>
		            			</td>
		            		</tr>
		            	</table>
		            	<div class="spacer"></div>
		            	<%-- end button table --%>
                 </td>
							</tr>
						</table> 
						<div class="spacer"></div>
           </td>
         </tr> 
       </table>
       
     </td> 
   </tr> 
 </table>


</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
