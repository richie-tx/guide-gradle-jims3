function setAddCursorPos()
{
    if (typeof(document.forms[0].create2Comments) != "undefined") {
       document.forms[0].create2Comments.focus();
    } else if (typeof(document.forms[0].create1Comments) != "undefined") {
       document.forms[0].create1Comments.focus();
    } 
}
