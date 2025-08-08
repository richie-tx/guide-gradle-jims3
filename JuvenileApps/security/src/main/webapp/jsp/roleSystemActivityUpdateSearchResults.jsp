<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/30/2004	 Hien Rodriguez - Create JSP -->
<!-- 06/22/2004	 Steven Lin - Update JSP -->
<!-- 08/18/2004	 Hien Rodriguez - Refactor Action & JSP with new PD Event/Command -->
<!-- 11/09/2004  Hien Rodriguez - ER13690 -->
<!-- 01/12/2007  CShimek     	- #38306 add multiple submit functionality  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET --> 


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%-- <msp:login /> --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />

<title><bean:message key="title.heading"/> - roleSystemActivityUpdateSearchResults.jsp</title>

<!-- JAVASCRIPT FILES --> 
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<tiles:insert page="/js/checkboxBackground.js" />
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onload="maintainChks()" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN FORM -->						
<html:form action="/displayRoleUpdateSummary">
<html:hidden property="action" />
						
<!-- DISPLAY HEADER & INSTRUCTION-->
<table align="center">
	<tr>
		<td align="center" class="header">
        
		<logic:equal name="roleForm" property="action" value="associateSystemActivities"> 
			<bean:message key="title.searchRoleActivityResults"/>
          	<tr><td>&nbsp;</td></tr>
          	<tr><td><ul>
              	<li>Select System Activities to associate to Role and select Next button.</li>                          
            </ul></td></tr>
        </logic:equal>
        
        <logic:equal name="roleForm" property="action" value="disassociateSystemActivities"> 
       	  	<bean:message key="title.disassociateRoleActivity"/>&nbsp;List
          	<tr><td>&nbsp;</td></tr>
          	<tr><td><ul>
              	<li>Select System Activities to disassociate to Role and select Next button.</li>                          
            </ul></td></tr>
        </logic:equal>
        
        <logic:equal name="roleForm" property="action" value="create"> 
       	  	<bean:message key="title.searchRoleActivityResults"/>
          	<tr><td>&nbsp;</td></tr>
          	<tr><td><ul>
              	<li>Select System Activities to associate to Role and select Next button.</li>                          
            </ul></td></tr>
        </logic:equal>
        
        <logic:equal name="roleForm" property="action" value="modify"> 
       	  	<bean:message key="title.searchRoleActivityResults"/>
          	<tr><td>&nbsp;</td></tr>
          	<tr><td><ul>
              	<li>Select System Activities to associate to Role and select Next button.</li>                          
            </ul></td></tr>
        </logic:equal>
        
        <logic:equal name="roleForm" property="action" value="copy"> 
       	  	<bean:message key="title.searchRoleActivityResults"/>
          	<tr><td>&nbsp;</td></tr>
          	<tr><td><ul>
              	<li>Select System Activities to associate to Role and select Next button.</li>                          
            </ul></td></tr>
        </logic:equal>
       </td>
	</tr>      	  
</table>

<!-- BEGIN ERROR TABLE -->
<table width="100%">
    <tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
    </tr>   	  
</table>
<!-- END ERROR TABLE -->	

<!-- DISPLAY ROLE INFO -->	
<table align="center">
	<tr>    
      	<td align="right" class="subhead"><bean:message key="prompt.roleName"/>:</td> 
        <td><bean:write name="roleForm" property="roleName"/></td>
        <td>&nbsp;&nbsp;&nbsp;</td>
        <td align="right" class="subhead"><bean:message key="prompt.roleDescription"/>:</td> 
        <td><bean:write name="roleForm" property="roleDescription"/></td>
        <td>&nbsp;&nbsp;&nbsp;</td>
		<td align="right" class="subhead"><bean:message key="prompt.roleType" />:</td> 
        <td><bean:write name="roleForm" property="roleType"/></td>			
	</tr>	  
	<tr>             
        <td align="right" class="subhead"><bean:message key="prompt.agency" />:</td>		   
        <td><logic:iterate id="roleGroup" name="roleForm" property="associatedRoleGroups">
				<bean:write name="roleGroup" property="roleGroupName"/><br>
		   	</logic:iterate></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td align="right" class="subhead"><bean:message key="prompt.division" />:</td> 
        <td><bean:write name="roleForm" property="division"/></td>
	</tr>    
</table>
<br>
	
<!-- DISPLAY SYSTEM ACTIVITIES INFO -->
<% int RecordCounter = 0; %>
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue">		 
	<tr class="detailHead"> 
		<td>&nbsp;</td>
		<td><bean:message key="prompt.systemActivityName"/></td>
		<td><bean:message key="prompt.systemActivityDescription"/></td>
	</tr>
		 
<!-- display disassociated system activities --> 	
	<logic:equal name="roleForm" property="availableSystemActivitiesSize" value="0">
		<logic:equal name="roleForm" property="action" value="disassociateSystemActivities">
			<tr>
				<td colspan=3 bgcolor=#ffffff>Role <bean:write name="roleForm" property="roleName"/> Has No Associated System Activities</td>
			</tr>
		</logic:equal>
		<logic:notEqual name="roleForm" property="action" value="disassociateSystemActivities">
			<tr>
				<td colspan=3 bgcolor=#ffffff>&nbsp;No More System Activities Available</td>
			</tr>
		</logic:notEqual>
	</logic:equal>
	
	<logic:iterate id="activity" name="roleForm" property="systemActivities">          
	<tr class= 
		<% RecordCounter++;
			if (RecordCounter % 2 == 1)
				out.print("normalRow");
			else
				out.print("alternateRow");	
		%> id=row<bean:write name="activity" property="systemActivityId"/>>           
        <td align="center" width="5%">
          	<html:multibox property="selectedSystemActivities" onclick="chooseRow(this)">
		  		<bean:write name="activity" property="systemActivityId"/>
		  	</html:multibox></td>
        <td><bean:write name="activity" property="name"/></td>
        <td><bean:write name="activity" property="description"/></td>
	</tr>		
	</logic:iterate>
</table>    
    
<logic:equal name="roleForm" property="action" value="associateSystemActivities"> 
	<logic:notEqual name="roleForm" property="associatedSystemActivitiesSize" value="0"> 
    <br>    
    <% int RecordCounter2 = 0; %>
	<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue">
		<tr>
			<td class=detailHead>Activities Associated to Role <bean:write name="roleForm" property="roleName"/></td>
		</tr>
		<tr>
			<td>
			    <table width="100%" align=center border="0" cellpadding=2 cellspacing=1>					 
					<tr bgcolor=#cccccc> 						
						<td class=subhead><bean:message key="prompt.systemActivityName"/></td>
						<td class=subhead><bean:message key="prompt.systemActivityDescription"/></td>
					</tr>
						 
<!-- display associated system activities -->
				 	<logic:iterate id="activity" name="roleForm" property="associatedSystemActivities">          
			      	<tr class= 
						<% RecordCounter2++;
							if (RecordCounter2 % 2 == 1)
								out.print("normalRow");
							else
								out.print("alternateRow");	
						%> id=row<bean:write name="activity" property="systemActivityId"/>>			        
			        	<td><bean:write name="activity" property="name"/></td>
			        	<td><bean:write name="activity" property="description"/></td>
			      	</tr>		
				  	</logic:iterate>
			    </table>
			</td>
		</tr>
	</table>
	</logic:notEqual>
</logic:equal>
<br>
<!-- BEGIN BUTTONS TABLES -->
<table align="center">
	<tr>
        <td align="center" valign=top>		 	    
		  	<html:button property="button.back" onclick="history.back();">
				<bean:message key="button.back"></bean:message>
		  	</html:button> 
		  	<html:submit>
				<bean:message key="button.next"></bean:message>
		  	</html:submit>
		</td>
		</html:form>
		<td valign=top>
		  	<logic:equal name="roleForm" property="action" value="create">           
		  		<html:form action="/displayRoleUpdate?action=create">
					<html:submit property="submitAction">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>
				</html:form>
      		</logic:equal>
      		<logic:equal name="roleForm" property="action" value="modify">           
		  		<html:form action="/displayRoleSearch?action=modify">
					<html:submit property="submitAction">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>
				</html:form>
      		</logic:equal>	
      		<logic:equal name="roleForm" property="action" value="associateSystemActivities">           
		  		<html:form action="/displayRoleSearch?action=modify">
					<html:submit property="submitAction">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>
				</html:form>
      		</logic:equal>	
      		<logic:equal name="roleForm" property="action" value="disassociateSystemActivities">           
		  		<html:form action="/displayRoleSearch?action=modify">
					<html:submit property="submitAction">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>
				</html:form>
      		</logic:equal>		    
        </td>
	</tr>		    
</table><p>&nbsp;</p>
<!-- END CONTENT TABLE -->
   
</body>
<!--END BODY TAG-->
</html:html>