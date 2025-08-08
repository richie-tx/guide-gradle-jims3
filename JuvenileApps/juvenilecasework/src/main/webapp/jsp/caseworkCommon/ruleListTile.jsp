<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%------------------MODIFICATIONS ----------------------------%>
<%-- Rule list --%>
<%-- 1/23/2006	Blake Schwartz		Create JSP --%>
<%-- 6/28/2007	LDeen				Defect #42874-add space between Rule & ID to match PT --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
					
<tiles:importAttribute name="title"/>
<tiles:importAttribute name="rules"/>
<tiles:importAttribute name="detailAction"/>
<tiles:importAttribute name="from"/>

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">

<input type="hidden" name="pager.offset" value="<%= offset %>">

<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	<tr>
		<td colspan="4" class="detailHead"><bean:write name="title" /></td>
	</tr>
	<tr>
		<td>
		  <%-- BEGIN INNER RULES TABLE --%>
  		<table cellpadding="2" cellspacing="1" width="100%">
  			<logic:empty name="rules">
  				<tr bgcolor="#cccccc">
  					<td colspan="4" class="subHead">No Supervision Rules Available</td>
  				</tr>
  			</logic:empty>

  			<%int RecordCounter = 0; String bgcolor = "";%>

  			<logic:notEmpty name="rules">
  				<tr bgcolor="#cccccc">
  					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RULES_TR%>'>
  						<td class="subHead"></td>
  					</jims2:isAllowed>
  					<td class="subHead" nowrap><bean:message key="prompt.rule" /> <bean:message key="prompt.id" /></td>
  					<td class="subHead"><bean:message key="prompt.rule"/> <bean:message key="prompt.name" /></td>
  					<td class="subHead"><bean:message key="prompt.rule"/> <bean:message key="prompt.type" /></td>
  					<td class="subHead"><bean:message key="prompt.standard" /></td>
  					<td class="subHead"><bean:message key="prompt.monitor"/> <bean:message key="prompt.frequency" /></td>
  					<td class="subHead"><bean:message key="prompt.status" /> <bean:message key="prompt.change" />&nbsp;<bean:message key="prompt.date" /></td>
  					<td class="subHead"><bean:message key="prompt.completion" /> <bean:message key="prompt.status" /></td>
  				</tr>

  				<logic:iterate id="ruleIndex" name="rules">
     				<%-- Begin Pagination item wrap --%>
      		
      					<tr class=<%RecordCounter++;  
								  bgcolor = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";
      						out.print(bgcolor);%>
								>
							<logic:equal name="from" value="casefile">								
								<%-- Bug #64612 --%>	
									<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RULES_TR%>'>		
										<td width="1%"><input type="checkbox" name="selectedRulesToTransfer" value="<bean:write name='ruleIndex' property='ruleId'/>"/></td>
									</jims2:isAllowed>												
							</logic:equal>
						
      						<td>
									  <a href="/<msp:webapp/><bean:write name="detailAction"/>.do?submitAction=Display Rule Details&selectedValue=<bean:write name="ruleIndex" property="ruleId" />">
        						<bean:write name="ruleIndex" property="ruleId" /></a>
									</td>
      						<td><bean:write name="ruleIndex" property="ruleName" /></td>
      						<td><bean:write name="ruleIndex" property="ruleTypeDesc" /></td>
      						<td><logic:equal name="ruleIndex" property="standard" value="true">
      							 	STANDARD
      							</logic:equal>
      							<logic:equal name="ruleIndex" property="standard" value="false">
      								CUSTOM
      							</logic:equal>
      						</td>
      						<td><bean:write name="ruleIndex" property="ruleMonitorFreqDesc" /></td>
      						<td><bean:write name="ruleIndex" property="completionDate" formatKey="date.format.mmddyyyy" /></td>
      						<td><bean:write name="ruleIndex" property="completionStatus" /></td>
      					</tr>
      			 
      		   <%-- End Pagination item wrap --%>
  				</logic:iterate>
  			</logic:notEmpty>
  		</table>

  		<%-- Begin Pagination navigation Row--%>
  		<%--The table below is commented for bug#41598 --%>
 <%-- 			<table align="center">
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
  	  </table> --%> 
  		<%-- End Pagination navigation Row--%>
  		<%-- END INNER RULES TABLE --%>
		</td>
 	</tr>
</table>

<%-- Begin Pagination Closing Tag --%>
</pg:pager>


