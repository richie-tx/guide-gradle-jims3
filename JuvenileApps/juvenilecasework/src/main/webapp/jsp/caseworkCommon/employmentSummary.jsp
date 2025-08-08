<!DOCTYPE HTML>

<%-- Manages Tabs for Juvenile Casefiles --%>
<%-- 05/20/2005		glyons	Create JSP --%>
<%-- 09/01/2015 RCapestani #29429 MJCW:  Adapt MJCW and Warrants to IE9, IE11 and Chrome (Benefits Assessment UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<%@ page import="ui.common.UIUtil" %>

<tiles:importAttribute name="employmentInfoList"/>
<tiles:importAttribute name="rowIdHeader"/>

<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  <tr>
    <td valign=top class=detailHead align="left">
    	<table width='100%' cellpadding=0 cellspacing=0>
    		<tr>
	        <logic:notEmpty name="employmentInfoList">
	    			<td width='1%'><a href="javascript:showHideExpand('<bean:write name="rowIdHeader"/>', 'row', '/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="<bean:write name="rowIdHeader"/>"></a></td>
	        </logic:notEmpty>
    			<td class=detailHead align="left">&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.employment"/> <bean:message key="prompt.info"/>
		        <logic:empty name="employmentInfoList">
		          (No Employment Information)
		        </logic:empty>
    			</td>
    		</tr>
    	</table>
    </td>
  </tr>
  <tr id="<bean:write name="rowIdHeader"/>Span" class=hidden>
    <td valign=top>
      <table width='100%' bgcolor="#cccccc" cellspacing="1"> 
        <logic:notEmpty name="employmentInfoList">
          <tr bgcolor="#f0f0f0"> 
            <td class="subhead" valign="top" width="10%"><bean:message key="prompt.entryDate"/></td>
            <td class="subhead"><bean:message key="prompt.employment"/> <bean:message key="prompt.status"/> </td> 
            <td class="subhead"><bean:message key="prompt.current"/> <bean:message key="prompt.employer"/></td> 
            <td class="subhead"><bean:message key="prompt.jobTitle"/></td> 
            <td class="subhead"><bean:message key="prompt.annualGrossIncome"/></td> 
          </tr> 

            <logic:iterate indexId="index" id="employments" name="employmentInfoList">
              <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">  
                <td width='10%'><bean:write name="employments" property="entryDate"/></td> 
                <td><bean:write name="employments" property="employmentStatus"/> </td> 
                <td><bean:write name="employments" property="currentEmployer"/>  </td> 
                <td><bean:write name="employments" property="jobTitle"/> </td> 
                <td><bean:write name="employments" property="annualNetIncome"/>  </td> 
              </tr>
            </logic:iterate>
          </logic:notEmpty>

          <logic:empty name="employmentInfoList">
            <tr bgcolor="#f0f0f0"> 
              <td class="subhead" valign="top" width="10%">Employment information does not exist for this member. </td>
            </tr>
          </logic:empty>
      </table>						
    </td>
  </tr>
</table>
