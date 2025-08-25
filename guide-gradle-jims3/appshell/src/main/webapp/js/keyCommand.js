addhandlers(window);
addhandlers(document);

var ctrl = 0;
var alt = 1;
var shift = 2;
var ctrlshift = 3;
var altshift = 4;

var g = 71;
var j = 74;
var k = 75;
var m = 77;

var shortcuts = new Array();
shortcuts[ctrl] = new Array();
shortcuts[alt] = new Array();
shortcuts[shift] = new Array();
shortcuts[ctrlshift] = new Array();
shortcuts[altshift] = new Array();

function addhandlers(o) {
  o.onkeypress = handler;
}

function checkKey(e, arry) {
  var value = arry[e.keyCode];
  if (value && value != null) {
    eval(value);
  }
}

function registerKey(command, functionname, key) {
  var array = shortcuts[functionname];
  array[key - 64] = command;
}

function handler(e) {
  if (e) {	// Netscape
  } else {  // IE
    e = window.event;
    if (e.ctrlKey) {
      if (e.shiftKey) {
        checkKey(e,shortcuts[ctrlshift]);
      } else {
        checkKey(e,shortcuts[ctrl]);
      }
    } else {
      if (e.altKey) {
        if (e.shiftKey) {
	    checkKey(e,shortcuts[altshift]);
        } else {
	    checkKey(e,shortcuts[alt]);
        }
      } else {
        if (e.shiftKey) {
	    checkKey(e,shortcuts[shift]);
        }
      }
    }
  }
}