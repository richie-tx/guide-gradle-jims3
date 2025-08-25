////////////////// CONSTANTS ////////////////////////
var STICKYMENU = false;
/////////////////////////////////////////////////////

function hideAllMenus() {
  for (var i = 0; i < contextMenus.length; i++) {
    menuDoc.contextMenus[i].hide();
  }
  for (var i = 0; i < dropDownMenus.length; i++) {
    if (menuDoc && menuDoc.dropDownMenus && menuDoc.dropDownMenus[i] && menuDoc.dropDownMenus[i].hide)
	menuDoc.dropDownMenus[i].hide();
  }
}


function displayMenuAt(obj, x, y) {
  var rightedge = menuDoc.document.body.clientWidth-x;
  var bottomedge = menuDoc.document.body.clientHeight-y;
  if (rightedge < obj.offsetWidth) {
    obj.style.left = (menuDoc.document.body.scrollLeft + x > obj.offsetWidth ? menuDoc.document.body.scrollLeft + x - obj.offsetWidth : menuDoc.document.body.scrollLeft);
  } else {
    obj.style.left = menuDoc.document.body.scrollLeft + x;
  }
  if (bottomedge < obj.offsetHeight) {
    obj.style.top = (menuDoc.document.body.scrollTop + y > obj.offsetHeight ? menuDoc.document.body.scrollTop + y - obj.offsetHeight : menuDoc.document.body.scrollTop);
  } else {
    obj.style.top = menuDoc.document.body.scrollTop + y;
  }
}


function showMenu(x, y) {
  menuDoc.hideAllMenus();

  activeMenu = this;
  var obj = this.obj;
  x = x || x == 0 ? x : event.clientX;
  y = y || y == 0 ? y : event.clientY;
  this.x = x;
  this.y = y;

  displayMenuAt(obj,x,y);
  obj.style.visibility = "visible";
  return false;
}

function hideMenu() {
  var obj = this.obj;
  if (obj)
    obj.style.visibility = "hidden";
}


function highlight(obj) {
  obj.style.backgroundColor = "highlight";
  obj.style.color = "white";
}

function lowlight(obj) {
  obj.style.backgroundColor = "";
  obj.style.color = "black";
}


////////////////////// MENU CLASS ////////////////////////////////

function Menu(name) {
  this.name = name;
  this.menuItems = new Array();
  this.obj = 0;
  this.index = 0;
  this.type = "";
  this.x = 0;
  this.y = 0;

  this.write = writeMenu;
  this.add = addMenuItem;
  this.show = showMenu;
  this.hide = hideMenu;
}

function writeMenu() {
  this.obj = menuDoc.document.all["menu"+(this.index+1)];
  var html = "";
  
  for (var i=0; i<this.menuItems.length; i++) {
    html += this.menuItems[i].write();
  }
  if (this.obj)
    this.obj.innerHTML = html;
}

function addMenuItem(name, func) {
  var menuItem = new MenuItem(name, func);
  this.menuItems[this.menuItems.length] = menuItem;
  return menuItem;
}

////////////////////// MENU ITEM CLASS ///////////////////////////

function MenuItem(name, func) {
  this.name = name;
  this.func = func;

  this.write = writeMenuItem;
}

function writeMenuItem() {
  if (this.name == "separator") {
    return "<hr>";
  } else {
    return "<div class=\"menuitem\" id=\""+this.name+"\" onClick=\"eval('"+this.func+"');\" onMouseover=\"highlight(this)\" onMouseout=\"lowlight(this)\"><nobr>"+this.name+"</nobr></div>";
  }
}

//////////////////////////////////////////////////////////////////

var contextMenus = new Array();
var activeContextMenu = 0;
var dropDownMenus = new Array();
var activeMenu = 0;
var headerDoc = null;
if (parent.parent.parent.parent.parent.header)
  headerDoc = parent.parent.parent.parent.parent.header.document;
var menuDoc = self;
if (parent.criteria)
  menuDoc = parent.criteria;
var numMenus = 0;

function showContextMenu() {
  if (menuDoc.activeContextMenu > -1 && menuDoc.contextMenus.length > 0)
    return menuDoc.contextMenus[activeContextMenu].show();
}

function hideContextMenu() {
  if (menuDoc.activeContextMenu > -1 && menuDoc.contextMenus.length > 0)
    return menuDoc.contextMenus[activeContextMenu].hide();
}

function createContextMenu(name) {
  var menu = new Menu(name);
  menu.type = "context";
  menu.index = contextMenus.length;
  menuDoc.contextMenus[menu.index] = menu;
  return menu;  
}

function createDropDownMenu(name) {
  var menu = new Menu(name);
  menu.type = "dropdown";
  menu.index = numMenus++;
  menuDoc.dropDownMenus[menu.index] = menu;
  return menu;  
}

function writeMenuHeaders() {
  if (headerDoc) {
    headerDoc.menuFrame = self;
    for (var i=0; i < menuDoc.dropDownMenus.length; i++) {
      var obj = headerDoc.all["menu"+(i+1)];
      if (obj) {
        if (menuDoc && menuDoc.dropDownMenus[i]) {
           obj.innerText = menuDoc.dropDownMenus[i].name;
        }
        obj.style.display = "block";
      }
    }
  }
}

function clearMenus() {
  if (headerDoc) {
    headerDoc.menuFrame = null;
    if (menuDoc) {
      for (var i=menuDoc.dropDownMenus.length-1; i >= 0; i--) {
        var obj = headerDoc.all["menu"+(i+1)];
        if (obj) {
          obj.innerText = "";
          obj.style.display = "none";
        }
	    if (menuDoc && menuDoc.dropDownMenus && menuDoc.dropDownMenus[i] && menuDoc.dropDownMenus[i].hide)
		menuDoc.dropDownMenus[i].hide();
	menuDoc.dropDownMenus[i] = null;
      }
    }
  }
}

function initializeMenus() {
  if (document.all && window.print) {
    menuDoc.writeMenuHeaders();
    menuDoc.writeMenus();
    document.oncontextmenu = showContextMenu;
    document.body.onclick = hideAllMenus;
    if (STICKYMENU) {
      document.body.onscroll = scrollMenu;
    } else {
      document.body.onscroll = hideAllMenus;
    }
  }
}

function scrollMenu() {
  if (menuDoc.activeMenu)
    menuDoc.displayMenuAt(menuDoc.activeMenu.obj, activeMenu.x, activeMenu.y);
}

function writeMenus() {
  for (var i = 0; i < dropDownMenus.length; i++) {
    if (dropDownMenus[i]) {
       dropDownMenus[i].write();
    }
  }
//  for (var i = 0; i < contextMenus.length; i++) {
//    contextMenus[i].write();
//  }
}
