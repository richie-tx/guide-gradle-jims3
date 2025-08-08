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
	selector: 'textarea',  // change this value according to your HTML
	plugins: 'tabfocus',
	tabfocus_elements: ':prev,:next'
});