<!DOCTYPE HTML>
<!-- Used to search for warrants. -->
<!--MODIFICATIONS -->
<%-- DWilliamson	10/06/2004	Create JSP --%>
<%-- LDeen			10/22/2004	Revise JSP --%>
<%-- HRodrigez		02/04/2005	Add JW Recall & Inactivate --%>
<%-- CShimek        08/08/2005  Revised to new look and feel --%>
<%-- CShimek        09/01/2005  Centered "OR" on tables to match prototypes --%>
<%-- CShimek        10/06/2005  ER#23756 revise titles --%>
<%-- CShimek        01/31/2006  Added hidden fields for HelpFile access --%>
<%-- CShimek    	04/05/2006  ER#26357 change Reset button to Refresh button --%>
<%-- DWilliamson	10/18/2006	ER#36128 Remove OR and add drop down lists in searches --%>
<%-- CShimek		12/21/2006  revised helpfile reference value--%>
<%-- CShimek		01/30/2007  #39097 added multiple submit button logic --%>
<%-- LDeen			06/04/2007  #42564 changed path to app.js --%>
<%-- RCapestani		08/07/2007  #44089 added scripts customStrutsBasedValidation --%>
<%-- CShimek		10/08/2007  #45716 added onload() to hold search fields when "Back" is selected --%>
<%-- LDeen			06/10/2008  #52370 fix page title to add space --%>
<%-- RYoung         08/6/2015   #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

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
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading"/> - warrantGenericSearch.jsp</title>

<!-- VALIDATION JAVASCRIPT FILE FOR THIS PAGE --> 
<script type="text/javascript" src="/<msp:webapp/>js/warrantGenericSearch.js"></script>
<!-- JAVASCRIPT LINK FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/showHideFunctions.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<%--
<script type="text/javascript"> 
/* The variables below are global variables for this page for the search types.  They 
*  are used in the javascript functions to hide spans and validate required fields.
*  The values are retrieved from the static codes for the Search Types.  If the codes
*  change they only need to be changed in the PDJuvenileCaseConstants class and not
*  anywhere else in this jsp.
*/
<%--var _juvenileName = "<%out.print(naming.PDJuvenileCaseConstants.SEARCH_JUVENILE_NAME);%>";
var _warrantNum = "<%out.print(naming.PDJuvenileWarrantConstants.WARRANT_NUM);%>";
var _juvenileNum = "<%out.print(naming.PDJuvenileWarrantConstants.JUVENILE_NUM);%>"; --%>

<%-- Search Type (warrantUIType)				Search By
	ACKNOWLEDGING DTA (reqAckDTA)				WARRANT # & JUVENILE NAME
	ACTIVATING VOP (actVOP)						JUVENILE # & REFERRAL # & WARRANT #
	ACTIVATING ARR (actARR)						WARRANT # ONLY		
	ACTIVATING DTA (actDTA)						WARRANT # ONLY
	INACTIVATE (inactivate)						WARRANT # & JUVENILE NAME
	PROCESS RETURN OF SERVICE (processReturn)	WARRANT # & JUVENILE NAME 
	RECALL (recall)								WARRANT # & JUVENILE NAME
	RELEASE DECISION (releaseDecision)			WARRANT # & JUVENILE NAME
	RELEASE TO JUVENILE PROBATION (releaseToJP)	WARRANT # & JUVENILE NAME
	RELEASE TO PERSON (releaseToPerson)			WARRANT # & JUVENILE NAME
	REQUESTING SIGNATURE ON OIC (reqSigOIC) 	WARRANT # & JUVENILE NAME
	RETURN OF SERVICE SIGNATURE	(retSigStatus)	WARRANT # & JUVENILE NAME
	SERVICE STATUS (warrantService)				WARRANT # & JUVENILE NAME
	UPDATE OIC (updateOIC)						WARRANT # ONLY			
	UPDATE VOP (updateVOP)						WARRANT # ONLY
--%>
</head>

<body onload="evalUIType(document.forms[0].warrantType)" onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<html:form action="/displayGenericSearchResults" target="content" >
    <input type="hidden" name="warrantType" value="<bean:write name="juvenileWarrantForm" property="warrantTypeUI"/>">
    <input type="hidden" name="refreshButton" value="">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
    <tr>
      	<td class="header" align="center">
        	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actARR">
            	<bean:message key="prompt.activate"/>&nbsp;<bean:message key="title.juvWarrantArrest"/>&nbsp;<bean:message key="prompt.search"/>
  				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|77">
          	</logic:equal>
          	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actDTA">
            	<bean:message key="prompt.activate"/>&nbsp;<bean:message key="title.juvWarrantDTA"/>&nbsp;<bean:message key="prompt.search"/>
				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|27">
          	</logic:equal>
		  	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actVOP">
            	<bean:message key="prompt.activate"/>&nbsp;<bean:message key="title.juvWarrantVOP"/>&nbsp;<bean:message key="prompt.search"/>
				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|147">           	
          	</logic:equal>          
          	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="inactivate">
            	<bean:message key="prompt.inactivate"/>&nbsp;<bean:message key="title.juvWarrant"/>&nbsp;<bean:message key="prompt.search"/>
  				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|157">
          	</logic:equal>
          	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="processReturn">
            	<bean:message key="title.juvWarrantProcessReturnOfService"/>&nbsp;<bean:message key="prompt.search"/>
  				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|95">
          	</logic:equal>          
          	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="recall">
            	Recall&nbsp;<bean:message key="title.juvWarrant"/>&nbsp;<bean:message key="prompt.search"/>
   				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|100">
          	</logic:equal>          
          	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="reqAckDTA">
            	Acknowledge&nbsp;<bean:message key="title.juvWarrantDTA"/>&nbsp;<bean:message key="prompt.search"/>
				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|24">
          	</logic:equal>
          	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="reqSigOIC">
            	<bean:message key="prompt.update"/>&nbsp;<bean:message key="title.juvWarrantOICSignature"/>&nbsp;<bean:message key="prompt.search"/>
				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|126">          
          	</logic:equal>
          	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="releaseDecision">
            	<bean:message key="title.juvWarrantEnterReleaseDecision"/>&nbsp;<bean:message key="prompt.search"/>
   				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|30">            	
          	</logic:equal>          
          	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="releaseToJP">
            	<bean:message key="title.juvWarrantReleaseToJP"/>&nbsp;<bean:message key="prompt.search"/>
   				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|106">            	
          	</logic:equal>          
          	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="releaseToPerson">
            	<bean:message key="title.juvWarrantReleaseToPerson"/>&nbsp;<bean:message key="prompt.search"/>
 				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|32">            	
          	</logic:equal>          
           	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="retSigStatus">
            	<bean:message key="title.juvWarrantFileReturnOfServiceSignature"/>&nbsp;<bean:message key="prompt.search"/>
   				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|44">
          	</logic:equal>          
           	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="updateOIC">
            	<bean:message key="prompt.update"/>&nbsp;<bean:message key="title.juvWarrantOIC"/>&nbsp;<bean:message key="prompt.search"/>
				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|130">
          	</logic:equal>
          	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="updateVOP">
            	<bean:message key="prompt.update"/>&nbsp;<bean:message key="title.juvWarrantVOP"/>&nbsp;<bean:message key="prompt.search"/>
   				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|134">
          	</logic:equal>           
          	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="warrantService">
            	<bean:message key="prompt.update"/>&nbsp;<bean:message key="title.juvWarrantWarrantServiceStatus"/>&nbsp;<bean:message key="prompt.search"/>
  				<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|140">
          	</logic:equal>          
		</td>	
    </tr>
</table>
<!-- END HEADING TABLE -->
<br>
<!--  BEGIN INSTRUCTION TABLES -->
<span id="instr_actVOP" class="hidden"> 
	<table width="98%">
   		<tr>
      		<td>
    			<ul>
        			<li>Enter the Warrant Number OR the Juvenile Number and Referral Number.</li>            
					<li>Select Submit button to retrieve Warrant information.</li>
	        	</ul>
       		</td>
   		</tr>
	</table>
</span>  
<span id="instr_general" class="hidden">
	<table width="98%">
    	<tr>
       		<td> 
	    		<ul>
         			<li>Enter the Warrant Number OR Juvenile Name.</li>            
					<li>Select Submit button to retrieve Warrant information.</li>
		        </ul>
			</td>
		</tr>
   </table>
</span>  
<span id="instr_warrantNumOnly" class="hidden">
	<table width="98%">
   		<tr>
    		<td>
	    		<ul>
         			<li>Enter the Warrant Number.</li>            
	     			<li>Select Submit button to retrieve Warrant information.</li>
	        	</ul>
    	    </td>
	    </tr>
	</table> 
</span> 
<span id="instr_releaseDecision" class="hidden">
	<table width="98%">
  		<tr> 
    		<td>
    			<ul>
		        	<li>Enter the Warrant Number OR Juvenile Name.</li>
	    			<li>Select Submit button to retrieve Release Decision update.</li>
      			</ul>
      		</td>
		</tr>  	
	</table>
</span>  
<span id="instr_releaseToJP" class="hidden">
    <table width="98%">
  		<tr> 
    		<td>
    			<ul>
        			<li>Enter the Warrant Number OR Juvenile Name.</li>
	    			<li>Select Submit button to retrieve Release To Juvenile Probation update.</li>
		      	</ul>
			</td>
		</tr>  	
	</table>
</span>
<span id="instr_releaseToPerson" class="hidden">
	<table width="98%">
		<tr> 
			<td>
				<ul>
					<li>Enter the Warrant Number OR Juvenile Name.</li>
					<li>Select Submit button to retrieve Release To Person update.</li>
				</ul>
			</td>
		</tr>  	
	</table>
</span>
<span id="instr_warrantService" class="hidden">
	<table width="98%">
		<tr> 
			<td>
				<ul>
					<li>Enter the Warrant Number OR Juvenile Name.</li>
					<li>Select Submit button to retrieve Warrant Service Status information.</li>
				</ul>
			</td>
		</tr>  	
	</table>
</span>
<span id="instr_processReturn" class="hidden">
	<table width="98%">
		<tr>
			<td>
				<ul>
					<li>Enter the Warrant Number OR Juvenile Name.</li>
					<li>Select Submit button to retrieve Warrant Process Return Of Service information.</li>
				</ul>
			</td>
		</tr>  	
	</table>
</span>
<span id="instr_retSigStatus" class="hidden">
	<table width="98%">
		<tr> 
			<td>
				<ul>
					<li>Enter the Warrant Number OR Juvenile Name.</li>
					<li>Select Submit button to retrieve warrants needing Signatures.</li>
				</ul>
			</td>
		</tr>  	
	</table>
</span>
	<table width="98%">
		<tr>		  
			<td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>		  
		</tr>   	  
	</table>
<!--  END INSTRUCTION TABLES -->
<!-- BEGIN ERROR TABLE -->
    <table width="98%" align="center">
    <tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
    </table>
<!-- END ERROR TABLE --> 
<!--  BEGIN SEARCH BY TABLES -->
<span id="searchByWarrantNum_JuvName" class="hidden"> 
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class=borderTableBlue>
   		<tr>
     		<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.searchBy"/></td>
     		<td class="formDe">
     			<select name=searchType onChange="evalSearch(this.form)">
	      			<option value=warrantNumber><bean:message key="prompt.warrantNumber"/></option>
	      			<option value=juvenileName><bean:message key="prompt.juvenileName"/></option>
	    		</select> 
 	 		</td>
   		</tr>
	</table>    
</span>

<span id="searchByWarrantNum_JuvNum" class="hidden"> 
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
   		<tr>
     		<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.searchBy"/></td>
     		<td class="formDe">
    			<select name=searchNumberType onChange="evalSearchNumbers(this.form)">
	      			<option value=warrantNumber><bean:message key="prompt.warrantNumber"/></option>
	      			<option value=juvNumber><bean:message key="prompt.juvenileNumber"/></option>
	    		</select> 
 	 		</td>
   		</tr>
	</table>
</span> 
<!--  END SEARCH BY TABLES -->
<!-- this seperater should only display if drop down list is diaplayed -->
<span id="sep" class="visible">
	<br> 
</span>

<!--  BEGIN SEARCH FIELD TABLES -->
<span id="warrantNumOnly" class="hidden">
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
  		<tr>
    		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.warrantNumber"/></td>
    		<td class=formDe>
				<html:text property="warrantNum" size="10" maxlength="10"/>	    		
    		</td>
  		</tr>
	</table>
</span> 

<span id="juvNameOnly" class=hidden>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
  		<tr>
    		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.juvenileLastName"/></td>
    		<td class=formDe>
 				<html:text property="lastName" size="30" maxlength="75"/>	
    		</td>
  		</tr>
  		<tr>
    		<td class=formDeLabel><bean:message key="prompt.juvenileFirstName"/></td>
    		<td class=formDe>
  				<html:text property="firstName" size="25" maxlength="50"/>	
    		</td>
  		</tr>
	</table>
</span>

<span id="juvNum_RefNum" class=hidden>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class="borderTableBlue">
  		<tr>
    		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.juvenileNumber"/></td>
    		<td class=formDe>
 				<html:text property="juvenileNum" size="8" maxlength="8"/>	    		
<%-- 	    		<input type="text" name="juvenileNum" value="" size="8" maxlength="8"> --%>
    		</td>
  		</tr>  
  		<tr>
    		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.referralNumber"/></td>
    		<td class=formDe>
 				<html:text property="referralNum" size="4" maxlength="4"/>	    		
<%--  			<input type="text" name="referralNum" value="" size="4" maxlength="4"> --%>
    		</td>
		</tr>
	</table>	  
</span>
<!--  END SEARCH FIELD TABLES -->
<br>
 
<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction" onclick="return checkJOTRequired(this.form) && disableSubmit(this, this.form)">
				<bean:message key="button.submit" />
			</html:submit>&nbsp;
			<html:submit property="submitAction" onclick="javascript: this.form.refreshButton.value='Y';">
				<bean:message key="button.refresh"/>
			</html:submit> 
<%--      	<html:reset><bean:message key="button.reset"/></html:reset> --%>
<%--      	<html:button property="org.apache.struts.taglib.html.BUTTON" 
      			   onclick="return resetClear(this.form)">
			<bean:message key="button.reset"/>
	  </html:button>&nbsp; --%>
		</td>
	</tr>		
</table>
<!-- END BUTTON TABLE -->

</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>