function myFocusOnMCEFix(element) {
    if(window.tinyMCE){
        tinyMCE.execCommand(mceFocus,element.name);
    } 
    return true;
}

function myReverseTinyMCEFix(element) {
    if(window.tinyMCE){
        //tinyMCE.updateContent(element.name);
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
		theme_advanced_buttons1 : "bold,italic,underline,separator,charmap",
		theme_advanced_buttons2 : "",
		theme_advanced_buttons3 : "",
		theme_advanced_fonts : "Arial;",
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		theme_advanced_path : false,
		plugins : "paste,legacyoutput",
		theme_advanced_buttons3_add : "pastetext,pasteword,selectall",
		paste_auto_cleanup_on_paste : true,
		font_size_style_values : "10pt",
//		force_br_newlines : true,
//		force_p_newlines : false,
		valid_elements : "b/strong,i/em,u",
		setup : function(ed) {
				    ed.onPostProcess.add(function(ed, o) {
			         // Remove all font tags
			          o.content = o.content.replace(/<font[^>]+>|<font>/g, '');
			          o.content = o.content.replace(/<\/font>/g, '');
				    });
				}
});