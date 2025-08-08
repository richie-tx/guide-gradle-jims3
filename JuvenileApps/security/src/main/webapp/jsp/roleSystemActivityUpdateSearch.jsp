<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/30/2004	 Hien Rodriguez - Create JSP -->
<!-- 08/18/2004	 Hien Rodriguez - Refactor Action & JSP with new PD Event/Command -->
<!-- 11/09/2004  Hien Rodriguez - ER13690 -->
<!-- 01/10/2007  C Shimek       - #38306 add multiple submit functionality  -->

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
<title><bean:message key="title.heading"/> - roleSystemActivityUpdateSearch.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN FORM -->						
<html:form action="/displayRoleSystemActivitiesSearchResults" focus="systemActivityName">
<html:hidden property="action" />				
<!-- BEGIN CONTENT TABLES -->
<table align="center">
	<tr>
		<td align="center" class="header">
         	<bean:parameter id="action" name="action" />
         	<logic:equal name="action" value="modify">           
           		<bean:message key="title.updateSearchRoleActivity"/>       
         	</logic:equal>
         	<logic:equal name="action" value="copy">           
           		<bean:message key="title.updateSearchRoleActivity"/>       
         	</logic:equal>
         	<logic:equal name="action" value="create">           
           		<bean:message key="title.createSearchRoleActivity"/>       
         	</logic:equal>
         </td>
	</tr>
</table>
<br>
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
        <td>
        	<logic:iterate id="roleGroup" name="roleForm" property="associatedRoleGroups">
				<bean:write name="roleGroup" property="roleGroupName"/><br>
		   	</logic:iterate></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td align="right" class="subhead"><bean:message key="prompt.division" />:</td> 
        <td><bean:write name="roleForm" property="division"/></td>
	</tr>    
</table>
<br>
<!-- DISPLAY SEARCH OPTIONS -->	  
<table align="center">
	<tr>
    	<td><h2>
		    <input type="button" name="A" value="A" onclick="listTerm('A')">
            <input type="button" name="B" value="B" onclick="listTerm('B')">
  			<input type="button" name="C" value="C" onclick="listTerm('C')">
  			<input type="button" name="D" value="D" onclick="listTerm('D')">
 			<input type="button" name="E" value="E" onclick="listTerm('E')">
	  		<input type="button" name="F" value="F" onclick="listTerm('F')">
  			<input type="button" name="G" value="G" onclick="listTerm('G')">
  			<input type="button" name="H" value="H" onclick="listTerm('H')">
  			<input type="button" name="I" value="I" onclick="listTerm('I')">
  			<input type="button" name="J" value="J" onclick="listTerm('J')">
  			<input type="button" name="K" value="K" onclick="listTerm('K')">
  			<input type="button" name="L" value="L" onclick="listTerm('L')">
  			<input type="button" name="M" value="M" onclick="listTerm('M')">
  			<input type="button" name="N" value="N" onclick="listTerm('N')">
  			<input type="button" name="O" value="O" onclick="listTerm('O')">
  			<input type="button" name="P" value="P" onclick="listTerm('P')">
  			<input type="button" name="Q" value="Q" onclick="listTerm('Q')">
  			<input type="button" name="R" value="R" onclick="listTerm('R')">
  			<input type="button" name="S" value="S" onclick="listTerm('S')">
  			<input type="button" name="T" value="T" onclick="listTerm('T')">
  			<input type="button" name="U" value="U" onclick="listTerm('U')">
  			<input type="button" name="V" value="V" onclick="listTerm('V')">
  			<input type="button" name="W" value="W" onclick="listTerm('W')">
  			<input type="button" name="X" value="X" onclick="listTerm('X')">
  			<input type="button" name="Y" value="Y" onclick="listTerm('Y')">
  			<input type="button" name="Z" value="Z" onclick="listTerm('Z')">
		</h2></td>
	</tr>
</table>
<br>
<!-- DISPLAY SEARCH INPUT FIELDS -->	  
<table align="center">
	<tr>
		<td align="right" class="subhead"><bean:message key="prompt.systemActivityName"/>:&nbsp;</td>
        <td><html:text property="systemActivityName" size="50" maxlength="50"/></td>
	</tr>
    <tr>
        <td align="right" class="subhead"><bean:message key="prompt.systemActivityDescription"/>:&nbsp;</td>
        <td><html:text property="systemActivityDescription" size="100" maxlength="100" /></td>	
	</tr>
</table>	
<br>
<!-- DISPLAY BUTTONS -->	
<table align="center">
	<tr>        
        <td align="center" valign=top>		 	    
		  	<html:button property="button.back" onclick="history.back(-1);">
				<bean:message key="button.back"></bean:message>
		  	</html:button> 		  	
      		<html:submit property="submitAction">
		     	<bean:message key="button.next"></bean:message>
          	</html:submit>
		  	<html:reset onclick="setFocus(this.form)">
				<bean:message key="button.reset"></bean:message>
		  	</html:reset>  
		</td>
		</html:form>
		<td valign=top>
		  	<logic:equal name="action" value="create">           
		  		<html:form action="/displayRoleUpdate?action=create">
					<html:submit property="submitAction">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>
				</html:form>
      		</logic:equal>
      		<logic:equal name="action" value="modify">           
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