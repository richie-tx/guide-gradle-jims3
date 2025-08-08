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

<title><bean:message key="title.heading" /> - riskAnswerTile.jsp</title>

<tiles:useAttribute id="answerBoxTitle" name="answerBoxTitle"/>

<!-- BEGIN CURRENT ANSWER TABLE -->
  <table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  	<tr>
      <td class="detailHead">
      	<table width="100%" cellpadding="2" cellspacing="0">
          <tr>
            <td class="detailHead"><bean:write name="answerBoxTitle"/> </td>
          </tr>
        </table>
        </td>
    </tr>
  
     <tr>
     	<td>
     	
		  	<!-- BEGIN ANSWER TABLE -->
		  	<tiles:useAttribute id="formName" name="formName"/>
		  	<tiles:useAttribute id="answerListName" name="answerListName"/>
		  	<tiles:useAttribute id="showAnswerButtons" name="showAnswerButtons"/>
       		<tiles:useAttribute id="showAnswerRadioButtons" name="showAnswerRadioButtons"/>
       		<tiles:useAttribute id="showAnswerNameAsReferenceLinks" name="showAnswerNameAsReferenceLinks"/>
		  	<tiles:insert page="riskAnswersInnerTile.jsp" flush="true">
			  	<tiles:put name="answerListName" type="String" value="${answerListName}" />
			  	<tiles:put name="showAnswerButtons" type="String" value="${showAnswerButtons}" />
			  	<tiles:put name="showAnswerRadioButtons" type="String" value="${showAnswerRadioButtons}" />
			  	<tiles:put name="showAnswerNameAsReferenceLinks" type="String" value="${showAnswerNameAsReferenceLinks}" />
			  	<tiles:put name="formName" type="String" value="${formName}" />
			</tiles:insert>
			<%-- END ANSWER TABLE --%>
  		</td>
     </tr>
   </table>
   <!-- END ANSWER TABLE -->