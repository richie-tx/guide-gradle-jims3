function myFocusOnMCEFix(element) {
    if(window.tinyMCE){
        tinyMCE.execCommand(mceFocus,element.name);
    } 
    return true;
}

function myReverseTinyMCEFix(element) {
    if(window.tinyMCE){
    	tinyMCE.activeEditor.load(element);
    } 
    return true;
}

function myTinyMCEFix() {
	if(window.tinyMCE){
        tinyMCE.triggerSave();
    } 
    return true;
}


tinyMCE.init({
		mode : "textareas",
		editor_selector : "mceEditor",
		theme : "advanced",
		theme_advanced_buttons1 : "fontselect,fontsizeselect,forecolor,separator,bold,italic,underline,separator, bullist,numlist,separator,charmap",
		theme_advanced_buttons2 : "",
		theme_advanced_buttons3 : "",
		theme_advanced_fonts : "Helvetica=helvetica,sans-serif;Courier New=courier new,courier,monospace;Times New Roman=times new roman,times;",
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		theme_advanced_path : false,
		plugins : "paste,legacyoutput",
		theme_advanced_buttons3_add : "pastetext,pasteword,selectall",
		paste_auto_cleanup_on_paste : true,
		font_size_style_values : "10pt,12pt,14pt,18pt,24pt,36pt"
			
});