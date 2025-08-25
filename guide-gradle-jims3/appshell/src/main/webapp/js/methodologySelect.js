function validateAdd() {
  if (document.forms[0].unassociated.selectedIndex == -1) {
    alert('You must select a methodology before adding it.');
    return false;
  } else {
    move(document.forms[0].unassociated, document.forms[0].meths);
  }
}

function move(from, to) {
    var selected = from.options[from.selectedIndex];
    to.options[to.length] = new Option(selected.text, selected.value);
    from.options[from.selectedIndex] = null;
}

function validateRemove() {
  if (document.forms[0].meths.selectedIndex == -1) {
    alert('You must select a methodology before deleting it.');
    return false;
  } else {
    move(document.forms[0].meths, document.forms[0].unassociated);
  }
}

function closeWindow() {
  var values = new Array();
  var numValues = 0;
  for (var i=0; i < document.forms[0].meths.options.length; i++) {
    var value = new Array();
    value[0] = document.forms[0].meths.options[i].value;
    value[1] = document.forms[0].meths.options[i].text;
    values[numValues++] = value;
  }

  window.returnValue = values;
  window.close();
}

function loadMethodologies() {
  var options = window.dialogArguments;
  for (var i=0; i < options.length; i++) {
    document.forms[0].meths.options[i] = new Option(options[i].text, options[i].value);
    for (var j=0; j < document.forms[0].unassociated.options.length; j++) {
      if (document.forms[0].unassociated.options[j].value == options[i].value) {
        document.forms[0].unassociated.options[j] = null;
      }
    }
  }
}

function cancelWindow() {
  window.returnValue = null;
  window.close();
}

var names = new Array();
var descs = new Array();
var num = 0;

function addDescription(name, desc) {
  names[num] = name;
  descs[num] = desc;
  num++;
}

function getDescription(name) {
  var index = -1;
  for (var i=0; i<num; i++) {
    if (names[i] == name)
      index = i;
  }
  if (index != -1)
    return descs[index];
}

function showDescription(select, box) {
  var name = select.options[select.selectedIndex].value;
  var text = getDescription(name);
  box.value = text;
}