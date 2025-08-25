<!DOCTYPE HTML>
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!-- struts layout tag library -->
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>



<!-- STYLE SHEET LINK -->
<style>
</style>
<HEAD>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<script>
	var currentLeft = "none"
	function changeClass(which)
	{ 
		if (currentLeft != "none"){
			document.getElementById(currentLeft).className = "";
		}
		
		currentLeft = which;
		document.getElementById(which).className = "leftSelect"
	}
</script>
<msp:nocache />
<script type="text/javascript" src="/appshell/config/javascript.js"></script>
<% mojo.struts.taglib.layout.util.LayoutUtils.setSkin(request.getSession(), "jimsskin"); %>
<layout:skin/>
</HEAD>
<html:base/>

<layout:treeview name="features"/>