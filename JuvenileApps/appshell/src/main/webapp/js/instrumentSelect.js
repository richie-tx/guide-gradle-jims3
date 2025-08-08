function loadInstruments() {
  var options = window.dialogArguments;
  for (var i=0; i < options.length; i++) {
    document.forms[0].selected.options[i] = new Option(options[i].text, options[i].value);
    for (var j=0; j < document.forms[0].unassociated.options.length; j++) {
      if (document.forms[0].unassociated.options[j].value == document.forms[0].selected.options[i].value) {
        document.forms[0].unassociated.options[j] = null;
      }
    }
  }
}

function validateAdd() {
  if (document.forms[0].unassociated.selectedIndex == -1) {
    alert('You must select a instrument before adding it.');
    return false;
  } else {
    move(document.forms[0].unassociated, document.forms[0].selected);
  }
}

function move(from, to) {
    var selected = from.options[from.selectedIndex];
    to.options[to.length] = new Option(selected.text, selected.value);
    from.options[from.selectedIndex] = null;
}

function validateRemove() {
  if (document.forms[0].selected.selectedIndex == -1) {
    alert('You must select a instrument before deleting it.');
    return false;
  } else {
    move(document.forms[0].selected, document.forms[0].unassociated);
  }
}

function cancelWindow() {
  window.returnValue = null;
  window.close();
}

function closeWindow() {
  var values = new Array();
  var numValues = 0;
  for (var i=0; i < document.forms[0].selected.options.length; i++) {
    values[i] = document.forms[0].selected.options[i].value;
  }

  window.returnValue = values;
  window.close();
}

