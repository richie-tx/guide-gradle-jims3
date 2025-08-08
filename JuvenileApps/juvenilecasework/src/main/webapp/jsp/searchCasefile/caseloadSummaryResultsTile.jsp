<!-- Added for user story 11056 -->

<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<tiles:importAttribute name="casefileSearchForm"/>  
<table width='100%'>
  <tr>
    <td align="center" class="header" >Juvenile Casework - Caseload Summary Count</td>
  </tr>
</table>
</br>
 <table width='98%' cellpadding="0" cellspacing="0" border="0" class='borderTableBlue' align="center" valign="top">
        <tr>
            <td colspan="3" width="10%">
                <table cellpadding='2' cellspacing='1'width="98%" colspan="3" align="center" border="0">
                    <tr>
                        <td width="10%" valign="top">
                            <table cellpadding='2' cellspacing='2' width="90%" valign="top">
                                <tr>
                                    <td width="1%" colspan="1" bgcolor="#B3C9F5"></td>
                                    <td colspan="1" width="10%" nowrap="nowrap" bgcolor="#B3C9F5" align="left" class="detailHead"><bean:message key="prompt.zipCode" />
										<jims:sortResults beanName="casefileSearchForm" results="zipCodes_count" primaryPropSort="zipCode" primarySortType="STRING" defaultSortOrder="ASC" sortId="10" />
									</td>
                                     <td colspan="1" width="5%" nowrap="nowrap" bgcolor="#B3C9F5" class="detailHead"><bean:message key="prompt.juvenileCount" />
										<jims:sortResults beanName="casefileSearchForm" results="zipCodes_count" primaryPropSort="zipCode_juvenileCount" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="DESC" sortId="11" />
									</td>
                                </tr>
                                <logic:iterate id="casefiles1" name="casefileSearchForm" property="zipCodes_count" indexId='indexer'> 
				    				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
				    					<td></td>
				    					<td nowrap align="left"><bean:write name="casefiles1" property="zipCode"/></td>
				    					<td nowrap align="left"><bean:write name="casefiles1" property="zipCode_juvenileCount"/></td>
				    				</tr>
  		  						</logic:iterate> 
  		  						 <tr class='alternateRow'>
                                    <td width="1%" bgcolor="#e3e9f8" colspan="1"><b>Total</b></td>
                                    <td colspan="1" bgcolor="#e3e9f8" width="1%"></td>
                                    <td colspan="1" width="1%" bgcolor="#e3e9f8"><bean:write name="casefileSearchForm" property="totZipJuvCount"/></td>
                                </tr>
                            </table>
                        </td>
                        <td width="10%" valign="top">
                             <table cellpadding='2' cellspacing='2' width="90%" valign="top">
                                <tr>
                                    <td width="1%" colspan="1" bgcolor="#B3C9F5"></td>
                                    <td colspan="1" width="10%" nowrap="nowrap" bgcolor="#B3C9F5" align="left" class="detailHead"><bean:message key="prompt.supervisionType" />
										<jims:sortResults beanName="casefileSearchForm" results="supervisions_count" primaryPropSort="supervisionType" primarySortType="STRING" defaultSortOrder="ASC" sortId="12" />
									</td>
                                     <td colspan="1" width="5%" nowrap="nowrap" bgcolor="#B3C9F5" class="detailHead"><bean:message key="prompt.juvenileCount" />
										<jims:sortResults beanName="casefileSearchForm" results="supervisions_count" primaryPropSort="supType_juvenileCount" primarySortType="STRING" defaultSortOrder="ASC" sortId="13" />	
									</td>
                                </tr>
                                <logic:iterate id="casefiles" name="casefileSearchForm" property="supervisions_count" indexId='indexer'> 
				    				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
				    					<td></td>
				    					<td nowrap align="left"><bean:write name="casefiles" property="supervisionType"/></td>
				    					<td nowrap align="left"><bean:write name="casefiles" property="supType_juvenileCount"/></td>
				    				</tr>
  		  						</logic:iterate> 
  		  						 <tr class='alternateRow'>
                                    <td width="1%" bgcolor="#e3e9f8" colspan="1"><b>Total</b></td>
                                    <td colspan="1" bgcolor="#e3e9f8" width="1%"></td>
                                    <td colspan="1" width="1%" bgcolor="#e3e9f8"><bean:write name="casefileSearchForm" property="totSupvJuvCount"/></td>
                                </tr>
                            </table>
                        </td>
                        <td width="10%"  valign="top">
                             <table cellpadding='2' cellspacing='2' width="90%" valign="top">
                                <tr>
                                    <td width="1%" colspan="1" bgcolor="#B3C9F5"></td>
                                    <td colspan="1" width="10%" nowrap="nowrap" bgcolor="#B3C9F5" align="left" class="detailHead"><bean:message key="prompt.probationOfficer" />
										<jims:sortResults beanName="casefileSearchForm" results="officers_count" primaryPropSort="probationOfficerFullName" primarySortType="STRING" defaultSortOrder="ASC" sortId="14" />
									</td>
                                    <td colspan="1" width="5%" nowrap="nowrap" bgcolor="#B3C9F5" class="detailHead" align="left"><bean:message key="prompt.juvenileCount" />
										<jims:sortResults beanName="casefileSearchForm" results="officers_count" primaryPropSort="officer_juvenileCount" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="DESC" sortId="15" />
									</td>
                                </tr>
                               <logic:iterate id="casefiles" name="casefileSearchForm" property="officers_count" indexId='indexer'> 
				    				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
				    					<td></td>
				    					<td nowrap align="left"><bean:write name="casefiles" property="probationOfficerFullName"/></td>
				    					<td nowrap align="left"><bean:write name="casefiles" property="officer_juvenileCount"/></td>
				    				</tr>
  		  						</logic:iterate> 
  		  						 <tr class='alternateRow'>
                                    <td width="1%" bgcolor="#e3e9f8" colspan="1"><b>Total</b></td>
                                    <td colspan="1" bgcolor="#e3e9f8" width="1%"></td>
                                    <td colspan="1" width="1%" bgcolor="#e3e9f8"><bean:write name="casefileSearchForm" property="totSupvJuvCount"/></td>
                                </tr>
                            </table>
                       </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    
 </pg:pager>