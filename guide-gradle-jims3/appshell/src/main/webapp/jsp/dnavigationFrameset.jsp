<!DOCTYPE HTML>

<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<HTML>
<HEAD>
<msp:nocache />  

<link rel="stylesheet" href="/<msp:webapp/>theme/Master.css" />
<!--<link rel="stylesheet" href="<html:rewrite page='/<msp:webapp/>theme/Master.css'/>" />-->


<TITLE>Successfully logged in to - JIMS2</TITLE>

<SCRIPT>
	this.status="Welcome to JIMS2"
</SCRIPT>
</HEAD>

<frameset rows="52,*" frameborder="yes" border="0" framespacing="0" >
   <html:frame frameName="heading" action="/displayHeader.do" scrolling="no"/>
   <frameset cols="160,*" frameborder="yes" border="0" framespacing="0">
       <frameset rows="25,*" frameborder="no" border="1" style="border: 1px solid #f0f0f0" onmouseover="this.style.cursor= 'e-resize'">
         <html:frame frameName="navheader" action="/displayNavigationHeader.do" scrolling="no"/>
         <html:frame frameName="navmenu" action="/displayFeatures.do" scrolling="auto" />
<!--         <html:frame frameName="favouriteHeader" action="/displayFavouritesHeader.do" scrolling="no" />-->
<!--         <html:frame frameName="favouritemenu" action="/displayFavourites.do" scrolling="yes" />-->
       </frameset>
       <html:frame frameName="content" action="/displayMainContent.do"/>
   </frameset>
   <noframes>
     Sorry, this page requires a frame-capable browser
   </noframes>
</frameset>

<%--
<frameset rows="30,*">
<frameset COLS="*,190"  frameborder="NO" border="0" framespacing="0" > 
<html:frame frameName="splash" action="/displaySplash.do" />
 <html:frame frameName="logout" action="/displayLogout.do" />
</frameset> 
 <frameset cols="200,*">
  <frameset rows="60%,*">
  	<!-- features frame -->
    <html:frame frameName="navigation" action="/displayFeatures.do"/>
    <!-- favourites frame -->
    <html:frame frameName="favorites" action="/displayFavourites.do"/>
  </frameset>
  <frameset rows="20,*">
    <html:frame frameName="header" action="/displayNavigationHeader.do" scrolling="NO"/>
    <!-- APP Area -->
    <frame name="workarea"  />
  </frameset>
 </frameset>
</frameset>
--%>


</html>