<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb" %>

<html>
<title>Spell Checker Control</title >

<!-- CSS to hide the "add to user dictionary" and "Undo" button for JUV; id=addButton, id-undoButton -->
<!-- <style>#addButton, #undoButton{visibility:hidden;}</style> -->
				


    <body>
        <center>
        <RapidSpellWeb:rapidSpellWeb
        licenseKey="2A787A2822217E24212D7E7E543F3B3D273E3E3E3C4042444046464644484A4C4663687A7B737E2C507D257E262C0"
        userDictionaryFile='<%=java.lang.System.getenv("KEYOTI_DICT_DIR")+"/csc-dictionary.txt"%>'/>
        </center>
    </body>
</html>