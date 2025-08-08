<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 22 oct 2007 - mjt - create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>



<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonfunctionality.css" />
<html:base />


<title><bean:message key="title.heading"/>Manage <bean:message key="prompt.codeTableRegistration" /> - codeTableRegistrationSearch.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<html:javascript formName="codetableRegistrationSearch"/>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
function checkSearch()
{
	
	var myOption = (-1);

	if( document.getElementsByName('agencyId' ).length >= 1 )
	{
		for( i = 0; i < document.getElementsByName('agencyId' ).length; i++ )
		{
			if( document.getElementsByName( 'agencyId' )[ i ].checked )
			{
				myOption = i;
			}
		}
	}
	var tableName = document.codetableRegistrationForm.promptCodetableRegisterName;
	var tableDescription =document.codetableRegistrationForm.codetableDescription;
	var tableType = document.codetableRegistrationForm.codetableType;
	var elem1 = tableName.value;
	var elem2 = tableDescription.value;	
	var elem3 = tableType.value;
	if((elem1==null||elem1=="") && (elem2==null||elem2=="")&& (elem3==null||elem3=="")&& (myOption == (-1))){
	alert("Enter At least one Search Criteria");	
	return false;
	}
	if(!(elem1==null||elem1=="")&& elem1.length<2){
	alert("Enter At least 2 characters for a search field");
	tableName.focus();
	return false;
	}
	if(!(elem2==null||elem2=="")&& elem2.length<2){
	alert("Enter At least 2 characters for a search field");
	tableDescription.focus();
	return false;
	}
	else
	return true;
}

function checkAgency()
{
	var tableAgency = document.codetableRegistrationForm.agencyName;
	var tableAgencyCode =document.codetableRegistrationForm.agencyCode;
	
	var elem3 = tableAgency.value;
	var elem4 = tableAgencyCode.value;
	
	if((elem3==null||elem3=="") && (elem4==null||elem4=="")){
	alert("Enter At least one Search field Agency Name/Code");	
	return false;
	}		
	
	if(!(elem3==null||elem3=="")&& elem3.length<2){
	alert("Enter At least 2 characters for Agency Name");
	tableAgency.focus();
	return false;
	}
	if(!(elem4==null||elem4=="")&& elem4.length<2){
	alert("Enter At least 2 characters for Agency Code");
	tableAgencyCode.focus();
	return false;
	}
	else
	return true;
}
</script>
</head>

<html:form action="/displayCodetableRegistrationSearchResults" target="content" focus="promptCodetableRegisterName"> 

<body topmargin=0 leftmargin='0' onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" > Manage <bean:message key="prompt.codeTableRegistration" /> - <bean:message key="prompt.codeTableRegistration" /> Search</td>
  </tr>
  <tr>
	<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
  </tr>
</table>
<%-- END HEADING TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>At least one search field is required for search criteria.</li>
        <li>Select the Refresh button to clear the data.</li>
        <li>Select the Submit button to execute search.</li>
      </ul>
    </td>
  </tr>  
</table>
<%-- END INSTRUCTION TABLE --%>


<%-- search field --%>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
  <tr>
    <td class="formDeLabel" width="1%" nowrap>&nbsp;<bean:message key="prompt.codeTableRegistration" /> <bean:message key="prompt.name" /></td>
    <td class="formDe"><html:text name="codetableRegistrationForm" property="promptCodetableRegisterName" size="30" maxlength="30" /> </td>
  </tr>
  <tr>
    <td class="formDeLabel" width="1%" nowrap>&nbsp;<bean:message key="prompt.codetableDescription" /> </td>
    <td class="formDe"><html:text name="codetableRegistrationForm" property="codetableDescription" size="30" maxlength="30" /> </td>
  </tr>
  <tr>
    <td class="formDeLabel" width="1%" nowrap>&nbsp;<bean:message key="prompt.codeTableType" /></td>
    <td class="formDe"> 
      <html:select name="codetableRegistrationForm" property="codetableType">
       <html:option value=""><bean:message key="select.generic" /></html:option>
        <html:option value="CX">Complex</html:option>
        <html:option value="CD">Compound</html:option>
        <html:option value="SL">Simple</html:option>
      </html:select>
		</td>
  </tr>
  
  <tr>
	  <td colspan='2'>
      <table class=borderTableBlue cellpadding=4 cellspacing=0 border=0 width='100%'>
        <tr class=detailHead>
          <td class=detailHead>Search for Agency</td>
        </tr>
        <tr>
          <td align=center>
            <table border=0 cellspacing=1 cellpadding=2 width='100%'>
              <tr>
                <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.agencyName" /> </td>
                <td class=formDe><html:text name="codetableRegistrationForm" property="agencyName" size='60' maxlength='60' /></td>
              </tr>
              <tr>
                <td class=formDeLabel><bean:message key="prompt.agencyCode" /></td>
                <td class=formDe><html:text name="codetableRegistrationForm" property="agencyCode" size='5' /></td>
              </tr>                    
              <tr>
                <td class=formDeLabel></td>
                <td class=formDe>
                               
                    <html:submit property="submitAction" onclick="return checkAgency()  && disableSubmit(this, this.form)"><bean:message key="button.find"></bean:message></html:submit> 
                    <html:submit property="submitAction"><bean:message key="button.clear"></bean:message>	</html:submit>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <logic:notEmpty name="codetableRegistrationForm" property="availableAgencies">
						<tr>
							<td colspan=2 align=center>
							   <bean:write name="codetableRegistrationForm" property="agencySearchResultSize"/> agency matches found.
                        <%--		   <logic:notEqual name="codetableRegistrationForm" property="agencyName" value="">
			 					   		<bean:message key="prompt.agencyName"/>:<bean:write name="codetableRegistrationForm" property="AgencyName"/> 
								   </logic:notEqual>
            		               <logic:notEqual name="codetableRegistrationForm" property="agencyType" value="">						   
									   <bean:message key="prompt.agencyType"/>:<bean:write name="codetableRegistrationForm" property="agencyType"/>
   						   			</logic:notEqual> 
            		               <logic:notEqual name="codetableRegistrationForm" property="jmcRep" value="">						   
									   <bean:message key="prompt.jmcRep"/>:<bean:write name="codetableRegistrationForm" property="jmcRep"/>
   						   			</logic:notEqual> --%>
					</td>
				</tr>              
							<tr bgcolor=#cccccc>
								<td colspan=2 class=boldText><bean:message key="prompt.agencyName"/></td>
							</tr>                
							<tr>
								<td colspan=2> 
					<!-- VARIABLES NEEDED FOR DISPLAY -->
								<%int RecordCounter = 0; 
								  String bgcolor = ""; %> 
								<div class=scrollingDiv200>
							<!-- agency checkbox selection start-->
									<table border=0 width=100% cellspacing=1 cellpadding=2>
										<jims:sortResults beanName="codetableRegistrationForm" results="availableAgencies" primaryPropSort="agencyName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="2" hideMe="true" /> 						   						   													
								        <logic:iterate id="agenciesIndex" name="codetableRegistrationForm" property="availableAgencies"> 
											<tr height='100%' class= <% RecordCounter++;
				                    			 bgcolor = "normalRow";                      
    	           				    			 if (RecordCounter % 2 == 1)
							                         bgcolor = "alternateRow";
            				   				     out.print(bgcolor);  %>  >
												<td class=boldText>
                                    			   <input type="radio" name="agencyId" value=<bean:write name='agenciesIndex' property='agencyId'/>> 
                                    		       <bean:write name='agenciesIndex' property='agencyName'/> 
                                    			 </td>
											</tr>
									    </logic:iterate>		
									</table>
						 <!-- agency checkbox selection end -->	
								</div>
								</td>
							</tr>							
						</logic:notEmpty>
				
				
					</table>
					</td>
				</tr>		
</table>


<%-- BEGIN BUTTON TABLE --%>
<br>
<table border="0" width="100%">
	<tr align="center">
		<td> 
			<html:submit property="submitAction" onclick="return checkSearch()  && disableSubmit(this, this.form)"><bean:message key="button.submit"/></html:submit>
      <html:submit property="submitAction"><bean:message key="button.refresh"/></html:submit>
      <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>


</body>
</html:form>

<span style="text-align: center;"><script type="text/javascript">renderBackToTop()</script></span>

</html:html>
