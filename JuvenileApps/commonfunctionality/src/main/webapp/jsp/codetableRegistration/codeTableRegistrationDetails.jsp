<!DOCTYPE HTML>

<%-- MODIFICATIONS --%>
<%-- 22 oct 2007 - mjt - create JSP --%>

<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%-- TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="messaging.codetable.reply.CodetableAttributeResponseEvent" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ page import="naming.Features" %>

<%-- LOCALE USED FOR INTERNATIONALIZATION - NOT USED YET --%>

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

<title><bean:message key="title.heading"/>Manage <bean:message key="prompt.codeTableRegistration" /> - codeTableRegistrationDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<script type='text/javascript'>
function changeFormAction(myForm, URL, doSubmit)
{
	myForm.action = URL;
	if(doSubmit)
	{
		myForm.submit();
	}
	return true;
}
</script>

</head>

<body topmargin=0 leftmargin='0'>

 <% String actionName = "handleCodetableRegistration"; %>
<logic:equal name="codetableRegistrationForm" property="opStatus" value="summary">
				<% actionName = "submitCodeRegistrationUpdate"; %>
</logic:equal>

<html:form action="<%=actionName%>" target="content">


<%-- BEGIN HEADING TABLE --%>
<%-- title string is built, depending on the 'mode', something like: --%>
<%-- "Manage Code Table Registration - Update Code Table Registration Summary" --%>
<table width='100%'> 
  <tr id='detailsHeading'> 
    <td align="center" class="header">Manage <bean:message key="prompt.codeTableRegistration" /> - 
			<logic:equal name="codetableRegistrationForm" property="opType" value="create">
				Create
			</logic:equal>
			<logic:equal name="codetableRegistrationForm" property="opType" value="update">
				Update
			</logic:equal>
      <bean:message key="prompt.codeTableRegistration" />
			<logic:equal name="codetableRegistrationForm" property="opStatus" value="summary">
 				Summary
			</logic:equal>
			<logic:equal name="codetableRegistrationForm" property="opStatus" value="confirm">
 				Confirmation
			</logic:equal>
			<logic:equal name="codetableRegistrationForm" property="opStatus" value="details">
				Details
			</logic:equal>
  	</td> 
  </tr> 
<logic:equal name="codetableRegistrationForm" property="opStatus" value="confirm">
  <tr> 
    <td align="center" class="confirm" ><bean:message key="prompt.codeTableRegistration" /> 
	<logic:equal name="codetableRegistrationForm" property="attributesUpdated" value="true">
	Attributes have been Updated.
	</logic:equal>	
	<logic:equal name="codetableRegistrationForm" property="attributesUpdated" value="false">
			<logic:equal name="codetableRegistrationForm" property="opType" value="create">
				record has been created.
			</logic:equal>
			<logic:equal name="codetableRegistrationForm" property="opType" value="update">
				record has been updated.
			</logic:equal>  
	</logic:equal> 
  	</td> 
  </tr> 
</logic:equal>
	<logic:equal name="codetableRegistrationForm" property="opStatus" value="deleteConfirm">
    <tr> 
      <td align="center" class="confirm" ><bean:message key="prompt.codeTableRegistration" /> record has been inactivated.</td> 
    </tr> 
  </logic:equal>
  <tr>
	<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
  </tr>
</table> 
<%-- END HEADING TABLE --%> 

<%-- BEGIN INSTRUCTION TABLE --%> 
<br> 
<table width="98%" border="0"> 
  <tr> 
    <td> 
      <ul> 
  			<logic:equal name="codetableRegistrationForm" property="opStatus" value="details">
          <li>Select the Update button to change the data for this Code Table Registration.</li> 
          <li>Select the Update Attributes button to change the attribute data for this Code Table Registration record.</li>          
  			</logic:equal>

  			<logic:equal name="codetableRegistrationForm" property="opStatus" value="summary">
          <li>Review the information and select the Finish button to save the information.</li> 
          <li>Select the Back button to return to the previous screen to change information.</li> 
          <li>Select the Cancel button to return to the Code Table Registration Search.</li> 
  			</logic:equal>

  			<logic:equal name="codetableRegistrationForm" property="opStatus" value="confirm">
          <li>Select the Code Table Registration Search button to return to that screen.</li> 
  			</logic:equal>

  			<logic:equal name="codetableRegistrationForm" property="opStatus" value="deleteSummary">
          	<li>Select the Inactivate button to inactivate the Code from the Code Table.</li> 
  			</logic:equal>
      </ul> 
		</td> 
  </tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 


<%-- Begin Pagination Header Tag (attribute matrix) --%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 


<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
  maxIndexPages="10"  export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>	


<%-- BEGIN DETAIL TABLE --%> 
<div class='spacer'></div> 
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" > 
  <tr> 
    <td valign=top> 

			<div class='spacer'></div>
      <table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue"> 
        <tr> 
          <td class=detailHead><bean:message key="prompt.codeTableRegistration" /></td> 
        </tr> 
        <tr> 
          <td valign=top align=center> 
					  <table cellpadding="4" cellspacing="1" width='100%'>

						  <%-- the attributes that follow are common to all code table types --%> 
              <tr>
                <td class=formDeLabel nowrap><bean:message key="prompt.name" /></td>
                <td class=formDe><bean:write name="codetableRegistrationForm" property="codetableName" /></td>
      			  </tr>
      			  <tr>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.description" /></td>
                <td class=formDe><bean:write name="codetableRegistrationForm" property="codetableDescription" /></td>
              </tr>
      			  <tr>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.type" /></td>
                <td class=formDe><logic:equal name="codetableRegistrationForm" property="codetableType" value="SL">
            		Simple&nbsp;
            	</logic:equal>
            	<logic:equal name="codetableRegistrationForm" property="codetableType" value="CD">
            		Compound&nbsp;
            	</logic:equal>
            	<logic:equal name="codetableRegistrationForm" property="codetableType" value="CX">
            		Complex&nbsp;
            	</logic:equal></td>
      			  </tr>
						  <%-- end common attributes  --%> 
		
						  <%-- attribute specific to simple type > 
        			<logic:notEmpty name="codetableRegistrationForm" property="codetableContextKey"--%>
					<logic:equal name="codetableRegistrationForm" property="codetableType" value="SL">
                <tr>
                  <td class=formDeLabel nowrap><bean:message key="prompt.context" /> <bean:message key="prompt.key" /></td>
                  <td class=formDe><bean:write name="codetableRegistrationForm" property="codetableContextKey" /></td>
                </tr>
				</logic:equal>
        		<%--	</logic:notEmpty>
                end simple type --%> 

						  <%-- common attribute --%> 
              <tr>
                <td class=formDeLabel nowrap><bean:message key="prompt.entity" /> <bean:message key="prompt.name" /></td>
                <td class=formDe><bean:write name="codetableRegistrationForm" property="codetableEntityName"/></td>
              </tr>

            </table>
  				</td> 
        </tr> 
      </table> 
      
      <br>
			<!--agency info start-->
			<!-- VARIABLES NEEDED FOR DISPLAY -->
         <%int RecordCount = 0; 
            String bgcolor = ""; %> 
			<table class=borderTableBlue cellpadding=4 cellspacing=0 border=0 width=98%>
				<tr class=detailHead>
					<td class=detailHead>
					
					       Agencies	Associated with Code Table Registration		
					</td>					
				</tr>
				<tr>
					<td colspan=2 align=center>
						<table width=100% border=0 cellpadding=2>
							<tr>
								<td align="left" class=formDeLabel class=detailHead><bean:message key="prompt.agencyName"/></td>
							</tr>
						    <logic:iterate id="agencyNameIndex" name="codetableRegistrationForm" property="currentAgencies">
						    <tr class= <% RecordCount++;
				            			  bgcolor = "alternateRow";                      
    	           				    	  if (RecordCount % 2 == 1)
							                  bgcolor = "normalRow";
            				   			   out.print(bgcolor);  %>  >
         					    <td align="left">
                                  	<bean:write name="agencyNameIndex" property="agencyName"/>
                                </td>
                            </tr>      	
                            </logic:iterate> 
                            <logic:empty name="codetableRegistrationForm" property="currentAgencies">
                            	<tr><td align="left">Agencies not available.</td></tr>
                            </logic:empty>             	
						</table>
					</td>
				</tr> 
			</table>
			<!-- END AGENCY INFORMATION TABLE -->
			<br>

      <%-- this table houses the attribute matrix --%>
	  <logic:equal name="codetableRegistrationForm" property="showAttributes" value="true">			
  			<div class='spacer'></div>
        <table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue"> 
          <tr> 
            <td align="left" class=detailHead><bean:message key="prompt.codeTableRegistration" /> <bean:message key="prompt.attributes" /></td> 
          </tr> 
          <tr> 
            <td valign=top align=center> 
        			<%--<div style = "overflow: auto; height: 150px; width: 98%; border-style:solid; border-width:1px; border-color:#000000;" >--%>
					<div class=scrollingDiv100 >
  					  <table cellpadding="0" cellspacing="1" width='100%'>
							  <%-- column headings --%> 
                <tr height="100%" bgcolor='#cccccc' class=subHead>
                  <td align="left"><bean:message key="prompt.order" /></td>
                  <td align="left"><bean:message key="prompt.attribute" /> <bean:message key="prompt.name" /></td>
                  <td align="left"><bean:message key="prompt.display" /> <bean:message key="prompt.name" /></td>
                  <td align="left"><bean:message key="prompt.type" /></td>
                  <td align="left"><bean:message key="prompt.required" />?</td>
                  <td align="left"><bean:message key="prompt.audit" />?</td>
                  <td align="left"><bean:message key="prompt.unique" />?</td>
				  <%--<td><bean:message key="prompt.updatable" />?</td>
				  <td><bean:message key="prompt.verification" />?</td>--%>
                  <td align="left"><bean:message key="prompt.minLength" /></td>
                  <td align="left"><bean:message key="prompt.maxLength" /></td>
				  <%--<td><bean:message key="prompt.compoundId" /></td>
				  <td><bean:message key="prompt.compoundName" /></td>
				  <td><bean:message key="prompt.compoundContextKey" /></td>
				  <td><bean:message key="prompt.compoundEntityName" /></td>--%>
        			  </tr>

								<%-- repeating attribute rows --%>
                <% int RecordCounter = 0; %>
        				<logic:iterate indexId="idx" id="codetableDataIter" name="codetableRegistrationForm" property="codetableAttributes">
                  <pg:item>
            
              			<tr height="100%" class="
              				<% RecordCounter++;
              					if (RecordCounter % 2 == 1)
              						out.print("normalRow");
              					else
              						out.print("alternateRow");
              				%>"
          					>		 	
                      <td align="left"><bean:write name="codetableDataIter" property="displayOrder" /></td>
                      <td align="left"><bean:write name="codetableDataIter" property="dbColumn" /></td>
                      <td align="left"><bean:write name="codetableDataIter" property="displayName" /></td>
                      <td align="left"><bean:write name="codetableDataIter" property="type" /></td>
                      <td align="left"><bean:write name="codetableDataIter" property="required" /></td>
                      <td align="left"><bean:write name="codetableDataIter" property="audit" /></td>
                      <td align="left"><bean:write name="codetableDataIter" property="unique" /></td>
					 <%--<td><bean:write name="codetableDataIter" property="updatable" /></td>
					  <td ><bean:write name="codetableDataIter" property="validCheckRequired" /></td>--%>
                      <td align="left"><bean:write name="codetableDataIter" property="minLength" /></td>
                      <td align="left"><bean:write name="codetableDataIter" property="maxLength" /></td>
					  <%--<td><bean:write name="codetableDataIter" property="compoundId" /></td>
					  <td><bean:write name="codetableDataIter" property="compoundName" /></td>
					  <td><bean:write name="codetableDataIter" property="contextKey" /></td>
					  <td><bean:write name="codetableDataIter" property="entityName" /></td>--%>
										</tr>
            			</pg:item>
        				</logic:iterate>
              </table>
        			</div> 
  					</td> 
          </tr> 
        </table>
			</logic:equal>
		</td> 
  </tr> 
</table>
<%-- END DETAIL TABLE --%> 

<%-- Begin Pagination navigation Row--%>
<table align="center">
  <tr>
    <td>
    	<pg:index>
    		<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
    			<tiles:put name="pagerUniqueName" value="pagerSearch"/>
    			<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
    		</tiles:insert>
    	</pg:index>
    </td>
  </tr>
</table>


<%-- button table --%>
<br> 
<table border="0" cellpadding=1 cellspacing=1 align=center> 
  <tr align="center"> 
    <td> 

    <logic:equal name="codetableRegistrationForm" property="opStatus" value="details">
      <input type="button" onclick="goNav('/<msp:webapp/>displayCodetableRegistrationUpdate.do?submitAction=Update')" value="<bean:message key='button.update'/>"/>
      <input type="button" onclick="goNav('/<msp:webapp/>displayCodetableRegistrationAttribute.do?submitAction=Update')" value="<bean:message key='button.updateAttr'/>"/>
      <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
      <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
  	</logic:equal> 
  
  	<logic:equal name="codetableRegistrationForm" property="opStatus" value="summary">	
		<input type="hidden" name="attrFinishPage" value="normal"/>
	<logic:equal name="codetableRegistrationForm" property="showResequenceButton" value="true">
		<input type="button" onclick="goNav('/<msp:webapp/>submitCodetableRegistrationAttributeUpdate.do?submitAction=Resequence')" value="<bean:message key='button.resequenceAttr'/>"/>
		<input type="button" onclick="goNav('/<msp:webapp/>submitCodetableRegistrationAttributeUpdate.do?submitAction=Finish&attrFinishPage=normal')" value="<bean:message key='button.finish'/>"/>
		<input type="button" onclick="goNav('/<msp:webapp/>submitCodetableRegistrationAttributeUpdate.do?submitAction=Back')" value="<bean:message key='button.back'/>"/>
		<input type="button" onclick="goNav('/<msp:webapp/>submitCodetableRegistrationAttributeUpdate.do?submitAction=Cancel')" value="<bean:message key='button.cancel'/>"/>
	  </logic:equal>
	  <logic:equal name="codetableRegistrationForm" property="showResequenceButton" value="false">		
		<html:submit property="submitAction"><bean:message key="button.finish"/></html:submit>
		<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
		<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
	  </logic:equal>

  	</logic:equal>

  	<logic:equal name="codetableRegistrationForm" property="opStatus" value="confirm">
	<input type="button" onclick="goNav('/<msp:webapp/>displayCodetableRegistrationAttribute.do?submitAction=Update')" value="<bean:message key='button.manageCodeRegAttr'/>"/> 
	<%-- this button that follows (from prototype) is hidden until after the user goes through --%>
  		<%-- the 'create/update attribute' flow (cant test code table data until attributes are defined) --%>
    	<logic:equal name="codetableRegistrationForm" property="attributesUpdated" value="true">
		<input type="button" onclick="goNav('/<msp:webapp/>displayCodetableRegistrationTest.do?submitAction=Link&codetableRegId=<bean:write name='codetableRegistrationForm' property='codetableRegId'/>&codetableName=<bean:write name='codetableRegistrationForm' property='codetableName'/>&codetableEntityName=<bean:write name='codetableRegistrationForm' property='codetableEntityName'/>&codetableContextKey=<bean:write name='codetableRegistrationForm' property='codetableContextKey'/>&codetableType=<bean:write name='codetableRegistrationForm' property='codetableType'/>')" value="<bean:message key='button.codeRegTest'/>"/>         
    	</logic:equal>
	<input type="button" onclick="goNav('/<msp:webapp/>displayCodetableRegistrationSearch.do?submitAction=Link')" value="<bean:message key='button.codeRegSearch'/>"/> 		
  	</logic:equal>

  	<logic:equal name="codetableRegistrationForm" property="opStatus" value="deleteSummary">
      <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
      <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
  	</logic:equal>

  </tr>
</table> 
<%-- end button table --%>

<%-- BEGIN NOTES TABLE .... currently HIDDEN, but avail for developer notes --%> 
<!-- 
<br>
<table border="0" width="100%" id="notesTable" class='hidden'> 
  <tr> 
    <td>Notes:</td> 
  </tr> 
  <tr> 
    <td>
		  <ol>
			  <li>Code Table ID is displayed on the Confirmation page for create; otherwise, always displayed.</li> 
			  <li>Attributes' Attr ID are displayed on the Confirmation page for create; otherwise, always displayed.</li> 
			</ol>
		</td> 
  </tr> 
</table> 
-->
<%-- END NOTES TABLE --%>  

</pg:pager>
</html:form> 
<span style="text-align: center;"><script type="text/javascript">renderBackToTop()</script></span> 

</body>
</html:html>

