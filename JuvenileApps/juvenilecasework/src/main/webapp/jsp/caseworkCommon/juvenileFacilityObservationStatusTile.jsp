<!DOCTYPE HTML>
<%-- User selects the Disposition tab --%>
<%--MODIFICATIONS --%>
<%-- 08/09/2007	Uma Gopinath Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<!--Juvenile Header Information starts  -->
<tiles:importAttribute name="admitReleaseForm"/>  

<%-- Observation status starts --%>
<%-- Observation status starts --%>
<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue'  align="center">
	<tr class='facDetailHead'>
		<td align='left' colspan='8' class='paddedFourPix'>Observation Status</td>
	</tr>
	<tr>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.specialAttention"/></td>
		<td class='formDe' width="29%" colspan='1' nowrap> 
			<logic:iterate id="saIndex" name="admitReleaseForm" property="specialAttentions" indexId="idx">	 
				<input type="radio" name="specialAttentionCd" id="<bean:write name="saIndex" property="code"/>" value="<bean:write name="saIndex" property="code"/>"/><bean:write name="saIndex" property="description"/>
		    </logic:iterate>
		</td>
		<td class='formDe' width="79%" colspan='1'>
			<table id="saReasonId" class="hidden">
				<tr>
					<td class='formDeLabel' valign='top' width="1%" colspan='1' nowrap><bean:message key="prompt.reason"/></td>
					<td class='formDe' width="79%" colspan='1' nowrap> 
					<logic:iterate id="saReasonIndex" name="admitReleaseForm" property="specialAttentionReasons" indexId="idx">	
						<input type="checkbox" name="selectedSAReasons" id="<bean:write name="saReasonIndex" property="code"/>" value="<bean:write name="saReasonIndex" property="code"/>"/><bean:write name="saReasonIndex" property="description"/>
					</logic:iterate>
					</td>
				</tr>
			</table>
			<html:hidden property="saReasonStr" styleId="defaultSAReason"/>
			<html:hidden property="selectedSA" styleId="defaultSA"/>
		</td>
	</tr>
	<tr id="saReasonOther1" nowrap>
		<td class='formDeLabel' colspan="8"><bean:message key="prompt.2.diamond"/>
			<a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileFacilitySplAttn.do?submitAction=Link&juvenileNum=<bean:write name="admitReleaseForm" property="juvenileNum"/>','otherReasonComments',1000,800)">Other Reason Comments</a>
			<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
				<tiles:put name="tTextField" value="saReasonOtherComments"/>
                <tiles:put name="tSpellCount" value="spellBtn1" />
            </tiles:insert>
            (Max. characters allowed:50)
        </td>					
	</tr>
	<tr id="saReasonOther2">
    	<td colspan='8' class='formDeLabel'>
	    	<html:text name="admitReleaseForm" property="saReasonOtherComments" styleId = "saReasonOtherComments" style="width:50%"  />
      	</td>
    </tr>
</table>
<%-- Observation status ends --%>