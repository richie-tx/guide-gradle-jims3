<!DOCTYPE HTML>
<%-- Dwilliamson  12/09/2010	Create Tile.  This Question tile does not collapse --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>

<title><bean:message key="title.heading" /> - riskQuestionTile.jsp</title>

<tiles:useAttribute id="questionBoxTitle" name="questionBoxTitle"/>

  <!-- BEGIN QUESTION TABLE -->
  <table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
    <tr>
      <td class="detailHead">
         <table width="100%" cellpadding="2" cellspacing="0">
            <tr>
               <td class="detailHead"><bean:write name="questionBoxTitle"/></td>
            </tr>
         </table>
      </td>
    </tr>
    <tr>
      <td>				 
		<!-- BEGIN QUESTION TABLE -->
		  <tiles:useAttribute id="formName" name="formName"/>
		  <tiles:useAttribute id="showQuestionButtons" name="showQuestionButtons"/>
		  <tiles:insert page="riskQuestionInnerTile.jsp" flush="true">
		  	<tiles:put name="formName" type="String" value="${formName}" />
		  	<tiles:put name="showQuestionButtons" type="String" value="${showQuestionButtons}" />
  		  </tiles:insert>
		<%-- END QUESTION TABLE --%>
	  </td>
    </tr>
  </table>
  <!-- END QUESTION TABLE -->
 

