<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- Debbie Williamson	07/28/2005	Create JSP -->
<!-- Clarence Shimek    01/24/2007  #38494 revised cancel buttons to transfer to search page -->
<!-- Clarence Shimek    07/27/2007  Replaced inline js script with officerDepartmentSearch.js  -->
<!-- C Shimek           02/05/2009  #56860 add Back to Top  -->
<!-- R Capestani		10/05/2015  #30561 MJCW: IE11 conversion of "Officer Profile"  link on UILeftNav -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--CUSTOM TAG LIBRARIES FOR PRESENTION -->

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css"/>
<html:base />
<title><bean:message key="title.heading" /> - officerDepartmentSearch.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="officerForm"/>
<!--GENERAL UTILITY JAVASCRIPT FOR THIS PAGE --> 
<script type="text/javascript" src="/<msp:webapp/>js/officer/officerDepartmentSearch.js" ></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>
<script type="text/javascript">
function showSelectButton(){
		document.getElementById("selectDepartmentButton").className='visible';
	}
</script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.officerProfile"/>&nbsp;-&nbsp;<bean:message key="title.departmentSearch"/></td>
  </tr>
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table width="100%">
	<tr>
    	<td>
            <ul>
             	<li>Enter search criteria and then select "Find Departments" button to perform search.</li>
				<li>Select Department/User Profile and then click "Select" button.</li>
            </ul>
		</td>
	</tr> 
  <tr>
    <td class="required">At least one field is required for search.</td>
  </tr>
  <tr>
    <td class="required">+ Indicates Last Name is required to use this search field.</td>
  </tr>        
</table>
<!-- END INSTRUCTION TABLE -->
<html:form action="/displaySearchForDepartmentsResults" target="content" focus="departmentNamePrompt">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|173">	
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>	
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="0" cellspacing="0" border="0" width="98%">						
				<tr>
					<td align="center">
						<table border="0" cellspacing="1" cellpadding="2" width="100%">
               
							<tr class="detailHead">
								<td colspan="2" class="detailHead"><bean:message key="prompt.search"/>&nbsp;for&nbsp;
                                                               <bean:message key="prompt.officer"/>'s&nbsp;
                                                               <bean:message key="prompt.department"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.departmentName"/></td>
								<td class="formDe"><html:text property="departmentNamePrompt" size="60" maxlength="60" /></td>
							</tr><tr>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.departmentCode"/></td>
								<td class="formDe"><html:text property="departmentIdPrompt" size="3" maxlength="3" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"></td>
								<td class="formDe">
										<html:submit property="submitAction" onclick="return validateSearchFields(this.form);">
								  		<bean:message key="button.findDepartments"></bean:message>
		  							</html:submit>	
									<html:submit property="submitAction">
											<bean:message key="button.refresh" />
									</html:submit>&nbsp;
								</td>
							</tr>
                                   		
                      <logic:notEmpty name="officerForm" property="departments"> 				
							<tr>
								<td align="center" style="padding:2px" colspan="2"><br>
                            
						            <bean:size id="DepartmentsSize" name="officerForm" property="departments"/>
						              <bean:write name="DepartmentsSize"/>&nbsp;search results found.	
						                <div class="scrollingDiv200">
						                   <table border="0" width="100%" cellspacing="1" cellpadding="2">
						                   <jims:sortResults beanName="officerForm" results="departments" 
                                              primaryPropSort="agencyName" primarySortType="STRING"
                                              secondPropSort="departmentName" secondarySortType="STRING"
                                              defaultSort="true" defaultSortOrder="ASC" sortId="1" />
						                    <logic:iterate id="departmentIndex" name="officerForm" property="departments" indexId="index">
											  <tr height="100%" bgcolor="#cccccc" class="detailHead">
								                	  <td class="boldText" colspan="3" >AgencyName:<bean:write name="departmentIndex" property="agencyName"/> </td>
								                  </tr>
									                  
						                          <tr height="100%" bgcolor="#cccccc">
												      <td width="3%">&nbsp;</td>
														<td class="subhead" valign="top"><bean:message key="prompt.department"/>&nbsp;<bean:message key="prompt.name"/></td>
				        		 						<td  class="subhead" valign="top"><bean:message key="prompt.department"/>&nbsp;<bean:message key="prompt.code"/></td>
												  </tr>
												  <tr height="100%" class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
													<td align="center" width="1%"><input type="radio" name="selectedDepartment" value=<bean:write name='departmentIndex' property='departmentId'/> onclick="javascript:showSelectButton();"></td>
													<td width="70%"><bean:write name="departmentIndex" property="departmentName"/></td>
													<td width="29%" class="boldText"><bean:write name="departmentIndex" property="departmentId"/></td>
												  </tr>
										    </logic:iterate>
										   </table>
										</div>
							    </td>
							</tr>
                         </logic:notEmpty>     
                                            
						</table>
					</td>
				</tr>
			</table>
			 <br>
			<!--BEGIN BUTTON TABLE--> 
        <table> 
          <tr> 
			<td><html:button property="button.back" onclick="history.back();">
					<bean:message key="button.back"></bean:message>
				</html:button>
            </td>
            <logic:notEmpty name="officerForm" property="departments"> 	
            <td id="selectDepartmentButton" class="hidden">
					<input type="submit" value="<bean:message key='button.selectDepartment'/>" name="submitAction" onclick="javascript:changeFormActionURL('officerForm', '/<msp:webapp/>selectOfficerSearchDepartment.do', false);">
            </td> 
            </logic:notEmpty>
			<td>
              <html:button
				  property="org.apache.struts.taglib.html.CANCEL"
				 onclick="document.location.href='/security.war/jsp/officers/officerSearch.jsp'">
				 <bean:message key="button.cancel"></bean:message>
		      </html:button>
           </td>
          </tr> 
        </table>
		 <!--END BUTTON TABLE-->
		</td>
	</tr>
</table>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>