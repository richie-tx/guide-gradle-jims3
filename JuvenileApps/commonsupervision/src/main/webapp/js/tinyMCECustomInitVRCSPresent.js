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
		theme_advanced_buttons1 : "bold,separator,italic,separator,underline,separator,charmap",
		theme_advanced_buttons2 : "",
		theme_advanced_buttons3 : "",
		theme_advanced_fonts : "Arial;",
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		theme_advanced_path : false,
		theme_advanced_statusbar_location : "bottom",
		theme_advanced_resizing : false,
		theme_advanced_resizing_use_cookie : false,
		height: "600",
		plugins : "paste,legacyoutput",
		theme_advanced_buttons3_add : "pastetext,pasteword,selectall",
        paste_auto_cleanup_on_paste : true,
        paste_remove_styles: true,
        paste_remove_styles_if_webkit: true,
        paste_strip_class_attributes: true,
        convert_fonts_to_spans : true,
		font_size_classes : "10pt",
		force_br_newlines : true,
		force_p_newlines : false,
		setup : function(ed) {
		    ed.onPostProcess.add(function(ed, o) {
	         // Remove all font tags
		      o.content = o.content.replace(/<font[^>]+>|<font>/g, '');
	          o.content = o.content.replace(/<\/font>/g, '');
	      });
		}

});