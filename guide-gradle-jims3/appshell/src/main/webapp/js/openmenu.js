
function openMenu(menuNum, x, y) {
  if (document.menuFrame && document.menuFrame.dropDownMenus[menuNum]) {
    document.menuFrame.dropDownMenus[menuNum].show(x,y);
  }
}
