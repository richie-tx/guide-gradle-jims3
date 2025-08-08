<!--

////////////////////////////////////////////////////////////////////////////////////
//  tree.js
//
//  Saleem Shafi
//
//  This script is a modified version of the script written for productmarketing.com.
//  It presents a menu or object structure in a tree format.  It has configuration
//  variables that allow for different features to be turned on or off.
////////////////////////////////////////////////////////////////////////////////////

//-------------- CONSTANTS -------------------------------------------------------//
var ROOTID = "root";			// The id of the root element
var IE = 1;
var NETSCAPE = 2;
var NETSCAPE6 = 3;
var doc = document;
var browserVersion = IE;
//--------------------------------------------------------------------------------//


//-------------- CONFIGURATION PARAMETERS ----------------------------------------//
var ONNODECLICK_CLICKLEAF = false;	// If true, clicking on the +/- will act like clicking on the node as well
var USETEXTLINKS = true;		// If true, the text of each leaf can act as a link
var TOGGLEICONS = true;			// If true, leaf icons will change depending on whether or not they are open or closed
var HIGHLIGHT = true;
var OPENONCLICK = true;			// If true, the node will open if selected
var TOPNODEVISIBLE = false;		// If true, the root node is visible
var SELECTPARENTONCLOSE = false;	// If true, the parent node will be selected if the child, selected node is hidden.
//--------------------------------------------------------------------------------//


//-------------- IMAGE PRELOADING AND FILENAMES ----------------------------------//
var baseUrl = "/jims2/images/tree/";
var imgLeaf = baseUrl+"GenericFolder_Down.gif";
var imgBlank = baseUrl+"blank.gif";
var imgFirstLeafMinusBlank = baseUrl+"firstleafminus_Blank.gif";
var imgFirstLeafPlusBlank = baseUrl+"firstleafplus_Blank.gif";
var imgLastLeaf = baseUrl+"lastleaf.gif";
var imgLastLeafMinus = baseUrl+"lastleafminus.gif";
var imgLastLeafPlus = baseUrl+"lastleafplus.gif";
var imgLine = baseUrl+"line.gif";
var imgMidLeaf = baseUrl+"midleaf.gif";
var imgMidLeafMinus = baseUrl+"midleafminus.gif";
var imgMidLeafPlus = baseUrl+"midleafplus.gif";

var img = null;
img = new Image(); img.src = imgBlank;
img = new Image(); img.src = imgFirstLeafMinusBlank;
img = new Image(); img.src = imgFirstLeafPlusBlank;
img = new Image(); img.src = imgLastLeaf;
img = new Image(); img.src = imgLastLeafMinus;
img = new Image(); img.src = imgLastLeafPlus;
img = new Image(); img.src = imgLine;
img = new Image(); img.src = imgMidLeaf;
img = new Image(); img.src = imgMidLeafMinus;
img = new Image(); img.src = imgMidLeafPlus;
//--------------------------------------------------------------------------------//


//-------------- TREE OBJECT -----------------------------------------------------//
function Tree(treeImage, treeName, treeLink, treeMsg)
{
  this.id = ROOTID			// ID of the root element
  this.indexOfEntries = new Array	// array of all leaves
  this.nEntries = 0			// number of all leaves
  this.children = new Array		// array of all child leaves
  this.nChildren = 0 			// number of all child leaves
  this.leafImg = treeImage		// base image name for root element
  this.iconImg = this.leafImg+".gif"	// image name for root element
  this.name = treeName			// text for root element
  this.click = treeLink			// link destination for root element. if empty, root is not a link.
  this.message = treeMsg ? treeMsg : treeName	// window.status text for root element rollover
  this.obj = 0				// reference to DOM object representing the root element
  this.imgobj = 0			// reference to DOM object representing the image of the root element
  this.isOpen = true			// is this node open
  this.selectedLeaf = -1		// ID of the selected leaf of this tree
  this.index = -1

  this.add = addLeaf
  this.addToIndex = addToIndex
  this.initialize = initializeTree
  this.draw = drawRoot
  this.display = display
  this.writeLink = writeLinkRoot
}
//--------------------------------------------------------------------------------//


//-------------- INITIALIZETREE --------------------------------------------------//
// Initializes the tree by initializing each of the child leaves and then displaying
// the rendered tree in the browser.  Note: The tree must already be built before
// this method is invoked.
//--------------------------------------------------------------------------------//
function initializeTree()
{
  this.addToIndex(this);			// add the root element to the list of leaves

  this.draw()					// generate the HTML for the root element
  for (var i=0; i < this.nChildren; i++)  	// initialize all of the children
  { 
    if (i == this.nChildren-1) {		// if this is the last child, initialize as the last child
      this.children[i].initialize(1, 1, new Array) 
    } else {					// otherwise, 
      if (i == 0) {				// if this is the first child, initialize as first
        this.children[i].initialize(1, -1, new Array) 
      } else {					// otherwise, initialize as middle child
        this.children[i].initialize(1, 0, new Array) 
      }
    }
  }

  if (browserVersion == IE) 
    this.obj.style.display = "block" 
  else 
  if (browserVersion == NETSCAPE)
    this.obj.visibility = "show" 
}
//-------------- END INITIALIZETREE ----------------------------------------------//


//-------------- DRAWROOT --------------------------------------------------------//
// This method generates the HTML needed to render the root element of the tree.
//--------------------------------------------------------------------------------//
function drawRoot() 
{
  // Generate the layer information for NETSCAPE 
  if (browserVersion == NETSCAPE) { 
    if (!doc.yPos) doc.yPos=30 			// Initialize the starting point, if necessary
    doc.write("<layer id='"+this.id+"' top=" + doc.yPos + " visibility=show>") 
  } 
   
  doc.write("<table ") 
  // Generate the layer information for IE
  if (browserVersion == IE) {
    doc.write(" id='"+this.id+"' style='position:none;' ") 
  }
  doc.write(" border=0 cellspacing=0 cellpadding=0>")

  if (TOPNODEVISIBLE) {
    doc.write("<tr><td>") 

    // Write the HTML to display the image
    this.writeLink() 
    doc.write("<img name='icon_"+this.id+"' src='"+this.iconImg+"' border=0></a></td><td valign=middle nowrap>") 

    // If text should be a link, write the link information, otherwise, write an emtpy anchor
    if (USETEXTLINKS) { 
      this.writeLink() 
    } else {
      doc.write("<a>") 
    }
    doc.write(this.name + "</a>") 
    doc.write("</td></tr>")
  }
  doc.write("</table>") 

  // Close the NETSCAPE layer
  if (browserVersion == NETSCAPE) {
    doc.writeln("</layer>")
  }

  // Set the DOM object references according to browser DOM
  if (browserVersion == IE) { 
    this.obj = doc.all[this.id]
    this.iconImg = doc.all["icon_"+this.id] 
  } else
    if (browserVersion == NETSCAPE) { 
      this.obj = doc.layers[this.id] 
      this.iconImg = this.obj.document.images["icon_"+this.id] 
      doc.yPos=doc.yPos+this.obj.clip.height 
    } 
} 
//-------------- END DRAWROOT ----------------------------------------------------//


//-------------- WRITELINKROOT ---------------------------------------------------//
// This method generates the HTML needed to make the current leaf a link
//--------------------------------------------------------------------------------//
function writeLinkRoot() 
{ 
  // If the leaf has a destination defined, write the link info, otherwise write empty anchor
  if (this.click) {
    doc.write("<a href=\"#\" onMouseOver=\"statusBar('"+this.message+"'); return true;\" onMouseOut=\"statusBar(''); return true;\" onClick=\"onLeafClick('"+this.id+"')\">") 
  } else {
    doc.write("<a>") 
  }
} 
//-------------- END WRITELINKROOT -----------------------------------------------//


//-------------- ADDTOINDEX ------------------------------------------------------//
// This method adds the given leaf to the tree's list of leaves.
//--------------------------------------------------------------------------------//
function addToIndex(thisLeaf)
{
  thisLeaf.index = this.nEntries
  this.indexOfEntries[this.nEntries++] = thisLeaf
}
//-------------- END ADDTOINDEX --------------------------------------------------//


//-------------- GETLEAFINDEX ----------------------------------------------------//
// This method returns the index of the first leaf that matches the ID given.  If
// no leaf matches, -1 is returned.
//--------------------------------------------------------------------------------//
function getLeafIndex(leafID)
{
  for (i = 0; i < ocuTree.nEntries; i++)		// look through all leaves
    if (ocuTree.indexOfEntries[i].id == leafID)		// if this one matches the id given, return the index
      return i
  return -1						// if none found, return -1
}
//-------------- END GETLEAFINDEX ------------------------------------------------//


//-------------- ADDLEAF ---------------------------------------------------------//
// This method adds a leaf with the given properties to the tree and then returns
// the newly created leaf.
//--------------------------------------------------------------------------------//
function addLeaf(parentID, leafID, leafImage, leafName, leafRealClick, leafOnClick, leafMsg, leafNodeClick)
{
  // Create a new Leaf object with the given parameters
  var thisLeaf = new Leaf(leafID, parentID+leafID, leafImage, leafName, leafRealClick, leafOnClick, leafMsg, leafNodeClick)
  // By default, assume this leaf is being added to the root
  var parentLeaf = this

  // If leaf is not being added to the root, find the real parent leaf
  if (parentID != this.id) {
    parentLeafIndex = getLeafIndex(parentID)
    parentLeaf = this.indexOfEntries[parentLeafIndex]
  }

  // Initialize the link between parent leaf and new leaf
  thisLeaf.parent = parentLeaf
  thisLeaf.siblingNum = parentLeaf.nChildren
  parentLeaf.children[parentLeaf.nChildren++] = thisLeaf

  // Initialize the link between root and new leaf
  thisLeaf.root = this
  this.addToIndex(thisLeaf)

  return thisLeaf
}
//-------------- END ADDLEAF -----------------------------------------------------//



//-------------- LEAF OBJECT -----------------------------------------------------//
function Leaf(oid, leafID, leafImage, leafName, leafRealClick, leafLink, leafMsg, leafNodeClick)
{ 
  this.oid = oid
  this.id = leafID				// ID of this leaf
  this.level = 0				// nesting level for this leaf
  this.iconImg = 0				
  this.nodeImg = 0  
  this.name = leafName				// text for this leaf
  this.click = leafLink				// JS that gets executed when the leaf is selected
  this.realclick = leafRealClick		// JS that gets executed when the user clicks on the leaf
  this.nodeClick = leafNodeClick		// JS that needs to be executed when the user clicks on the +/-
  this.message = leafMsg ? leafMsg : leafName	// window.status text of the node
  this.index = -1				// this leaf's index in root node's list of leaves
  this.obj = 0					// reference to DOM object representing this node
  this.isLastBranch = false			// is this leaf the last of it's parent's children
  this.leafImg = leafImage 
  this.isOpen = true				// is this leaf open
  this.iconSrc = imgLeaf;
  this.children = new Array			// list of child nodes
  this.connectors = new Array			// list of lines of HTML that represent the display in front of the leaf
  this.nChildren = 0				// number of child leaves
  this.siblingNum = -1				// this leaf's index in parent's list of children
  this.parent = 0				// reference to parent leaf
  this.root = 0					// reference to root node
  this.kidsInited = false
 
  this.initialize = initializeLeaf
  this.draw = drawLeaf
  this.writeLink = writeLinkLeaf
  this.setState = setState
  this.propagateChangesInState = propagateChangesInState
  this.hide = hide
  this.display = display
} 
//--------------------------------------------------------------------------------//


//-------------- INITIALIZELEAF --------------------------------------------------//
// This method initializes a child leaf and generates the HTML needed to render them
// to the browser.
//--------------------------------------------------------------------------------//
function initializeLeaf(level, position, connectors)
{
  var k 
  var nc=this.nChildren
  var connectCode="" 
  var auxEv = ""
  var node = ""
      
  this.level = level
  this.isLastBranch = (position == 1)

  // Generate the HTML needed to render the space in front of the leaf's image
  for (k = 1; k < level; k++) {
    connectCode += "<img name='connect"+k+"_"+this.id+"' src='"+connectors[k]+"' width=20 height=16>"
  }

  // If this node has children, or has some code to execute on the clicking of the +/-, then
  // generate some code to handle clicking on the +/-.
  if (nc > 0 || this.nodeClick) {
    auxEv = "<a href=\"javascript:onNodeClick('"+this.id+"')\">"
    node = "plus"
  } else {
    auxEv = "<a>"
  }
  
  if (this.isLastBranch) {
    // draw the object with a top half line
    this.draw(connectCode + auxEv + "<img name='node_"+this.id+"' src='"+baseUrl+"lastleaf"+node+".gif' border=0></a>") 
    // use a blank line underneath me
    connectors[k] = imgBlank
  } else { 
    if (position == -1 && this.parent.id == ROOTID && !TOPNODEVISIBLE) {
      // draw the object with bottom half line
      this.draw(connectCode + auxEv + "<img name='node_"+this.id+"' src='"+baseUrl+"topleaf"+node+".gif' border=0></a>") 
    } else {
      // draw a full line
      this.draw(connectCode + auxEv + "<img name='node_"+this.id+"' src='"+baseUrl+"midleaf"+node+".gif' border=0></a>") 
    }
    // use a connected line underneath me
    connectors[k] = imgLine
  } 
   
  // go to the next level
  level++
  // initialize all of the children
  for (var i=0 ; i < this.nChildren; i++) { 
    if (i == this.nChildren-1) {
      this.children[i].initialize(level, 1, connectors) 
    } else {
      if (i == 0) {
        this.children[i].initialize(level, -1, connectors) 
      } else {
        this.children[i].initialize(level, 0, connectors) 
      }
    }
  } 
}
//-------------- END INITIALIZELEAF ----------------------------------------------//


//-------------- DRAWLEAF --------------------------------------------------------//
// This method generates the HTML needed to render the current Leaf object.
//--------------------------------------------------------------------------------//
function drawLeaf(connector) 
{ 
  // Set up the layer for netscape
  if (browserVersion == NETSCAPE) { 
    if (!doc.yPos) doc.yPos=30 			// Initialize the starting point if necessary
    doc.write("<layer id='"+this.id+"' top=" + doc.yPos + " visibility=hidden>") 
  } 

  // draw the leaf line of the tree
  doc.write("<table ") 
  // Setup the layer for IE
  if (browserVersion == IE) 
    doc.write(" id='"+this.id+"' style='position:none;' ") 
  doc.write(" border=0 cellspacing=0 cellpadding=0><tr><td>") 
  // write the code that generates the stuff in front of the leaf
  doc.write(connector)

  // write the code to display the image
  this.writeLink() 
  var img = this.leafImg;
  doc.write("<img name=\"icon_"+this.id+"\" src=\""+img+".gif\" width=20 height=16 border=0></a></td><td valign=middle nowrap ");
  if (this.click || this.realclick) {
    doc.write("onmouseover=\"activeContextMenu=0;favoriteID='"+this.oid+"';\" onmouseout=\"activeContextMenu=-1;\" ");
  }
  doc.write(" >");

  // If the text should also be a link, write the like; otherwise just use any empty anchor
  if (USETEXTLINKS) { 
    this.writeLink() 
  } else {
    doc.write("<a>")
  }
  doc.write(this.name+"</a>") 
  doc.write("</td></table>") 

  // Close the layer for netscape
  if (browserVersion == NETSCAPE) { 
    doc.writeln("</layer>") 
  } 
 
  // Set the DOM object references according to browser DOM
  if (browserVersion == IE) { 
    this.obj = doc.all[this.id]
    this.iconImg = doc.all["icon_"+this.id] 
    this.nodeImg = doc.all["node_"+this.id] 
  } else
    if (browserVersion == NETSCAPE) { 
      this.obj = doc.layers[this.id] 
      this.iconImg = this.obj.document.images["icon_"+this.id] 
      this.nodeImg = this.obj.document.images["node_"+this.id] 
      doc.yPos=doc.yPos+this.obj.clip.height 
    } 
} 
//-------------- END DRAWLEAF ----------------------------------------------------//


//-------------- WRITELINKLEAF----------------------------------------------------//
// This method generates the HTML needed to make this leaf a link
//--------------------------------------------------------------------------------//
function writeLinkLeaf() 
{ 
  // if behavior is defined when this leaf gets selected either by clicking or automatically
  // then generate the code to handle the selection
  if (this.click || this.realclick) { 
	// write the link information to open the page
    doc.write("<a name=\"tag"+this.id+"\" onMouseOver=\"statusBar('"+this.message+"'); return true;\" onMouseOut=\"statusBar(''); return true;\" href=\"javascript:onLeafClick('"+this.id+"')\">") 
  } else {
    doc.write("<a name=\"tag"+this.id+"\">") 
  }
} 
//-------------- END WRITELINKLEAF -----------------------------------------------//


//-------------- STATUSBAR -------------------------------------------------------//
// This method displays the given text on the status bar of the browser.
//--------------------------------------------------------------------------------//
function statusBar(text)
{
  window.status = text;
}
//-------------- END STATUSBAR ---------------------------------------------------//


//-------------- ONLEAFCLICK -----------------------------------------------------//
// This method defines the behaviour of the tree when the icon (or text) of the leaf
// is clicked.
//--------------------------------------------------------------------------------//
function onLeafClick(leafID)
{
  // Get the index of the leaf for the given ID
  var selLeaf = getLeafIndex(leafID)
  // Select the leaf
  select(selLeaf, false)
  // Get the leaf object for the index
  var clickedLeaf = ocuTree.indexOfEntries[selLeaf]

  // If tree should open node on clicking the leaf, open it
  if (OPENONCLICK && clickedLeaf.setState)
    clickedLeaf.setState(true)
} 
//-------------- END ONLEAFCLICK -------------------------------------------------//


//-------------- DESELECT --------------------------------------------------------//
// This method un-selects the leaf at the given index.
//--------------------------------------------------------------------------------//
function deselect(leafIndex)
{
  // get leaf object from given index
  var leaf = ocuTree.indexOfEntries[leafIndex]

  if (leaf) {
    // get reference to original image name
    var unhighlightedImage = leaf.leafImg
    if (TOGGLEICONS == true) {
      if (leaf.isOpen && leaf.nChildren > 0) {
        unhighlightedImage = unhighlightedImage + "_Open"
      } else {
        unhighlightedImage = unhighlightedImage + ""
      }
    }
  
    // substitute the image according to the browser
    if (browserVersion == IE) {
      leaf.iconImg.src = unhighlightedImage+".gif"
    } else 
      if (browserVersion == NETSCAPE) {
       leaf.obj.document.images["icon_"+leaf.id].src = unhighlightedImage+".gif"
      }
  }
}
//-------------- END DESELECT ----------------------------------------------------//


//-------------- SELECT --------------------------------------------------------//
// This method defines the behaviour of the tree when the icon (or text) of the leaf
// is selected.
//--------------------------------------------------------------------------------//
function select(leafIndex, autoSelect)
{
  // Get the leaf for the given index 
  var leaf = ocuTree.indexOfEntries[leafIndex]
  if (leaf) {
    var highlightedImage = leaf.leafImg
    if (HIGHLIGHT)
      highlightedImage += "_Selected"
    if (ocuTree.selectedLeaf != -1) deselect(ocuTree.selectedLeaf)
    ocuTree.selectedLeaf = leafIndex
    
    if (TOGGLEICONS == true) {
      if (leaf.isOpen) {
        highlightedImage += "_Open"
      } else {
        highlightedImage += ""
      }
    }

    // substitute image according browser
    if (browserVersion == IE) {
      leaf.iconImg.src = highlightedImage+".gif"
    } else 
      if (browserVersion == NETSCAPE) {
        leaf.obj.document.images["icon_"+leaf.id].src = highlightedImage+".gif"
      }
    if (leaf.click) {
      eval(leaf.click)
    }
    if (autoSelect == false && leaf.realclick) {
      eval(leaf.realclick)
    }
    return true
  }
  return false
}
//-------------- END SELECT ------------------------------------------------------//


//-------------- SELECTID --------------------------------------------------------//
// This method selects a leaf given the ID
//--------------------------------------------------------------------------------//
function selectID(leafID)
{
  var leafIndex = getLeafIndex(leafID)
  select(leafIndex)
}
//-------------- END SELECTID ----------------------------------------------------//


//-------------- ONNODECLICK -----------------------------------------------------//
// This method defines the behaviour of the tree when the icon (or text) of the leaf
// is clicked.
//--------------------------------------------------------------------------------//
function onNodeClick(leafID,noClick)
{
  // if needed, act as though the leaf was clicked as well.
  if (!noClick && ONNODECLICK_CLICKLEAF)
    onLeafClick(leafID)

  // get the leaf by ID
  selectedLeaf = getLeafIndex(leafID)
  var clickedLeaf = ocuTree.indexOfEntries[selectedLeaf] 
  if (clickedLeaf)
  {
    // get the leaf's state
    var state = clickedLeaf.isOpen 
    // only invoke the nodeClick script if there are no child nodes
    if (clickedLeaf.nChildren == 0)
      eval(clickedLeaf.nodeClick)
    // toggle the leafs state
    clickedLeaf.setState(!state)
  }
} 
//-------------- END ONNODECLICK -------------------------------------------------//


//-------------- SETSTATE --------------------------------------------------------//
// This method opens and closes the tree according to the given state
//--------------------------------------------------------------------------------//
function setState(newState)
{
  var subEntries 
  var totalHeight 
  var fIt = 0 
  var i=0 
 
  // If the new state is the same as the current state, exit
  if (newState == this.isOpen || this.nChildren == 0) 
    return;

  this.isOpen = newState 
  this.propagateChangesInState() 
}
//-------------- END SETSTATE ----------------------------------------------------//


//-------------- PROPAGATECHANGESINSTATE -----------------------------------------//
// This method opens and closes the tree according to the given state
//--------------------------------------------------------------------------------//
function propagateChangesInState()
{   
  var i=0 
  var nodeimgname = baseUrl
  var iconimgname = this.leafImg

  // generate new image name for node
  if (this.isLastBranch) 
    nodeimgname += "lastleaf" 
  else 
    nodeimgname += "midleaf"

  if (this.nChildren > 0) {
    if (this.isOpen && this.nChildren > 0) {
      if (TOGGLEICONS == true) iconimgname = iconimgname + "_Open"
      nodeimgname = nodeimgname + "minus"
    } else {
      if (TOGGLEICONS == true) iconimgname = iconimgname + ""
      nodeimgname = nodeimgname + "plus"
    }
  }
  if (this.nodeImg) this.nodeImg.src = nodeimgname+".gif" 

  // if this is the selected image, mark it
  if (this.index == ocuTree.selectedLeaf && HIGHLIGHT) iconimgname = this.leafImg+"_Selected"
  this.iconImg.src = iconimgname+".gif" 

  // If we just opened this node, show all the children; otherwise, hide them
  if (this.isOpen) {
    for (i=0; i<this.nChildren; i++) {
      this.children[i].display()
    }
  } else {
    for (i=0; i<this.nChildren; i++) {
      this.children[i].hide()
    }
  }
} 
//-------------- END PROPAGATECHANGESINSTATE -------------------------------------//


//-------------- HIDE ------------------------------------------------------------//
// This method hides the current node and its children
//--------------------------------------------------------------------------------//
function hide(ignore)
{
  // hide the children first
  if (this.isOpen)
    for (var i=0; i < this.nChildren; i++)
      this.children[i].hide(ignore)

  // if this was the selected leaf, select the parent
  if (this.index == ocuTree.selectedLeaf && SELECTPARENTONCLOSE) {
    select(this.parent.index)
  }

  // hides this node
  if (browserVersion == IE) {
    this.obj.style.display = "none" 
  } else
    if (browserVersion == NETSCAPE) {
      if (this.obj.visibility == "hidden") ignore = true
      this.obj.visibility = "hidden" 
      // need to move the other layers up
      if (!ignore) moveAllAfter(this,-16)
    }
}
//-------------- END HIDE --------------------------------------------------------//


//-------------- DISPLAY ---------------------------------------------------------//
// This method shows the current node and its children
//--------------------------------------------------------------------------------//
function display()
{
  // make this node visible
  if (browserVersion == IE) { 
    if (this.parent.obj.style.display == "block" || this.parent.obj.style.display == "")
      this.obj.style.display = "block" 
  } else {
    if (this.parent.obj.visibility == "show" || this.parent.obj.visibility == "") {
      this.obj.visibility = "show" 
      // need to push the other layers down
      moveAllAfter(this,16)
    }
  }

  // if this node is open, show all the child nodes
  if (this.isOpen)
  {
    for (var i=0; i < this.nChildren; i++)
      this.children[i].display()
  }
}
//-------------- END DISPLAY -----------------------------------------------------//


//-------------- MOVEALLAFTER ----------------------------------------------------//
// This method moves all of the leaves after the given leaf by the given vertical distance.
// Note: This method is only called when using netscape.
//--------------------------------------------------------------------------------//
function moveAllAfter(thisLeaf, distance)
{
  // if this leaf is not the root
  if (thisLeaf.id != ROOTID) {
    // for all siblings after this leaf, pull them up
    for (var i = thisLeaf.siblingNum+1; i < thisLeaf.parent.nChildren; i++) {
      lift(thisLeaf.parent.children[i], distance);
    }
    // do the same for the parent's siblings
    if (thisLeaf.parent != thisLeaf.root)
      moveAllAfter(thisLeaf.parent,distance)
  }
}
//-------------- END MOVEALLAFTER ------------------------------------------------//


//-------------- LIFT ------------------------------------------------------------//
// This method moves all of the children of the given leaf by the given vertical distance
//--------------------------------------------------------------------------------//
function lift(thisLeaf, distance)
{
  // move this leaf
  thisLeaf.obj.moveBy(0,distance)
  // lift all of the children as well
  for (var m = 0; m < thisLeaf.nChildren; m++)
    lift(thisLeaf.children[m], distance)
}
//-------------- END LIFT --------------------------------------------------------//


function autoSelect(nodeID) {
  if (nodeID != null) {
    var leafIndex = getLeafIndex(nodeID);
    var leaf = ocuTree.indexOfEntries[leafIndex];
    openParents(leaf.parent);
    onLeafClick(leaf.id);
  }
}

function openParents(leaf) {
  if (leaf.id == ROOTID) {
    return;
  } else {
    openParents(leaf.parent);
    onNodeClick(leaf.id);
  }
}


function squish(aString) {
  if (aString == null) return null;
  var index = aString.indexOf(" ");
  while (index > -1) {
    aString = aString.substring(0, index) + aString.substring(index+1);
    index = aString.indexOf(" ", index);
  }
  return aString;
}



//-------------- INITIALIZEDOCUMENT ----------------------------------------------//
// This method initializes the root object and renders the tree to the screen.
//--------------------------------------------------------------------------------//
function initializeDocument() 
{
  // Determine browser type
  if (doc.all) browserVersion = IE
  else browserVersion = NETSCAPE
 
  // Initialize the tree
  ocuTree.initialize()

  // Close all of the nodes
  for (var i=ocuTree.nEntries-2; i >= 0; i--) {
    var thisObj = ocuTree.indexOfEntries[i];
    thisObj.setState(false)
  }

  // If netscape, we need to do this to make the tree visible
  if (browserVersion == NETSCAPE)  
    for (var j=0; j < ocuTree.nChildren; j++)
      ocuTree.children[j].obj.visibility = "show" 
} 
