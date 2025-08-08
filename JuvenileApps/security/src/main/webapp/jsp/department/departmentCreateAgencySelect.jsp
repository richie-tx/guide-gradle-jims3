<!DOCTYPE HTML>
<!-- 08/22/2005	 Hien Rodriguez - Create JSP -->
<!-- 03/29/2006	 C Shimek       - Per ER#26357 change Reset to Refresh button -->
<!-- 12/06/2006	 C Shimek       - #37546 relocate change buttons -->
<!-- 01/10/2007  C Shimek       - #38306 add multiple submit functionality  -->
<!-- 02/05/2009  C Shimek       - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> - departmentCreateAgencySelect.jsp</title>
<!-- AUTO TAB JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<!--JAVASCRIPT FILE FOR THIS PAGE -->
<%-- html:javascript formName="agencySearchForm"/ --%>
<script type="text/javascript" src="/<msp:webapp/>js/department/departmentCreateAgencySelect.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN HEADING TABLE -->
<table width="98%" align="center">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.departmentCreate"/> - Search Agency</td> 
  	</tr>  	
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="98%" align="center">
  	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
  	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" align="center">
  	<tr>
    	<td> <ul>
        	<li>Enter 1 or more search values then select Find Agencies button to search for agencies.</li>
      	</ul></td>
  	</tr>
  	<tr>
    	<td class="required">At least one field is required for search.</td>
  	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0">
   <tr>
      <td align="center" valign="top">
		<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
          	<tr>
          		<td class="detailHead"><bean:message key="prompt.searchForAgencies"/></td>          		
          	</tr>
          	<tr>
            	<td align="center">
					<html:form action="/departmentFindAgencies" target="content" focus="agencyName">
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|48">	
            		<table border="0" cellspacing="1" cellpadding="2" width="100%">               
	                	<tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.agencyName"/></td>
							<td class="formDe" ><html:text property="agencyName" size="60" maxlength="60" /></td>
						</tr>                  
	                	<tr>
	                		<td class="formDeLabel"><bean:message key="prompt.agencyCode"/></td>
							<td class="formDe" ><html:text property="agencyId" size="3" maxlength="3" /></td>
						</tr>
						<tr>             
							<td class="formDeLabel"></td>     		
    	            		<td class="formDe" >	        
	  		                	<html:submit onclick="return validateAgencySearchFields(this.form)" property="submitAction" >
	  		                		<bean:message key="button.findAgencies"></bean:message>
	  		                	</html:submit> 
	  	    	                <html:submit property="submitAction">
	  	    	                	<bean:message key="button.refresh"></bean:message>
	  	    	                </html:submit> 	  	                         
							</td>  
						</tr>	              	
                	</table>
                 	</html:form>
	   			</td>
        	</tr>
			<html:form action="/departmentFindAgencies" target="content">
        	<logic:notEmpty name="departmentForm" property="agencyList">	                
			<tr>
				<td align="center">
					<bean:size id="agenciesSize" name="departmentForm" property="agencyList"/>
					    <bean:write name="agenciesSize"/>&nbsp;search results found.	
				</td>
			</tr>
         	<tr height="100%">
            	<td align="center">
              	<div class="scrollingDiv200">
                	<table border="0" width="100%" cellspacing="1" cellpadding="2" height="100%">
                  		<tr bgcolor="#cccccc" height="100%">
		    				<td width="1%"></td>
                    		<td class="subhead"><bean:message key="prompt.agencyName"/>
                    		    <jims:sortResults beanName="departmentForm" results="agencyList" 
                                    primaryPropSort="agencyName" primarySortType="STRING" defaultSort="true" 
                                    defaultSortOrder="ASC" sortId="1" />
                            </td>
                  		</tr>
 						<logic:iterate id="agencyIndex" name="departmentForm" property="agencyList" indexId="index">                  	
						<tr height="100%" class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							<td align="center">
								<html:radio name="departmentForm" property="selectedAgencyId" idName="agencyIndex" value="agencyId" onclick="show('NextButton')" />
							</td>
							<td><bean:write name="agencyIndex" property="agencyName" /></td>
						</tr>
                  		</logic:iterate>
                	</table>
              	</div>
              	<br>
            	</td>
			</tr>
		</logic:notEmpty> 
<!--BEGIN BUTTON TABLE-->
	<tr>
       <td align="center">
       		<table border="0">
       			<tr>
       				<td>
			    	   	<input type="hidden" id="MyAgencyId" name="agencyId" value=""/>
				       	<input type="hidden" id="MyAgencyName" name="agencyName" value=""/>
				       	<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
			        	<bean:message key="button.back"></bean:message></html:button>&nbsp;
	   				</td>
				    <td id="NextButton" class="hidden">
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
            				<bean:message key="button.next"></bean:message>
				   	    </html:submit>&nbsp;
				    </td>
	   				<td>
			          <html:reset onclick="hide('NextButton')">
            			  <bean:message key="button.reset" />
			          </html:reset>&nbsp;
       				</td>
	   				<td>
				        <html:submit property="submitAction">
             				<bean:message key="button.cancel"></bean:message>
				        </html:submit>                          
			       </td>
				</tr>  	
			</table>
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