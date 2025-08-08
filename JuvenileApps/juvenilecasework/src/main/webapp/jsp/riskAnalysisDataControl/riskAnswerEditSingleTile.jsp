<!DOCTYPE HTML>
<%-- Dwilliamson  12/09/2010	Create Tile.  This Question tile collapses --%>

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

<title><bean:message key="title.heading" /> - riskAnswerEditSingleTile.jsp</title>

<tiles:useAttribute id="answerEditBoxTitle" name="answerEditBoxTitle"/>

<!-- BEGIN ANSWERS TABLE --> 
  <table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue> 
   <tr> 
      <td valign=top class=detailHead colspan=4><bean:write name="answerEditBoxTitle"/></td> 
   </tr> 
   <tr> 
      <td colspan=4 class=bodyText> 
        
        <!-- BEGIN UPDATE SINGLE ANSWERS TABLE -->
        <tiles:useAttribute id="formName" name="formName"/>
	    <tiles:insert page="riskAnswerEditInnerTile.jsp" flush="true">
    		<tiles:put name="formName" type="String" value="${formName}" />
    	</tiles:insert>
	    <%-- END UPDATE SINGLE ANSWERS TABLE --%>
             
      </td> 
    </tr>	
  </table> 
  <!-- END ANSWERS TABLE --> 