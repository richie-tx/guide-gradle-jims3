<SCRIPT LANGUAGE="JavaScript1.2" SRC="/<msp:webapp/>js/menu.js"></script>
<SCRIPT language="JavaScript1.2" src="/<msp:webapp/>js/keyCommand.js"></SCRIPT>
<SCRIPT>
  function openPage(url) {
    if (parent.header) {
      self.location = url;
    } else {
      parent.location = url;
    }
  }
</SCRIPT>
<SCRIPT>
  <msp:loop list="root.menu.nodes" as="node">
    <msp:if name="node.parentID" value="Drop Down" operator="equals">
  var <msp:get name="node.ID" /> = createDropDownMenu("<msp:get name="node.alias" />","skin1");
    </msp:if>
    <msp:if name="node.parentID" value="Drop Down" operator="notequals"> 

	<msp:if name="node.deactivated" value="true" operator="equals">
<msp:get name="node.parentID" />.add("<font color=gray><msp:get name="node.alias" /></font>", "");
	</msp:if>

	<msp:if name="node.deactivated" value="true" operator="notequals">

        <msp:if name="node.displayType" value="null" operator="equals">
<msp:get name="node.parentID" />.add("<msp:get name="node.alias" />", "<msp:if name="node.confirmationMessage" value="null" operator="notequals">if (confirm(\\'<msp:get name="node.confirmationMessage" />\\'))</msp:if> openPage(\\'<msp:get name="node.URL" />\\'); ");
        </msp:if>
 
        <msp:if name="node.displayType" value="Inset" operator="equals">
<msp:get name="node.parentID" />.add("<msp:get name="node.alias" />", "<msp:if name="node.confirmationMessage" value="null" operator="notequals">if (confirm(\\'<msp:get name="node.confirmationMessage" />\\'))</msp:if> openPage(\\'<msp:get name="node.URL" />\\'); ");
        </msp:if>

        <msp:if name="node.displayType" value="Modal" operator="equals">
<msp:get name="node.parentID" />.add("<msp:get name="node.alias" />", "<msp:if name="node.confirmationMessage" value="null" operator="notequals">if (confirm(\\'<msp:get name="node.confirmationMessage" />\\'))</msp:if>  showModalDialog(\\'<msp:get name="node.URL" />\\',\\'\\',\\'<msp:get name="node.popupOptions"/>\\'); ");
        </msp:if>

        <msp:if name="node.displayType" value="Modeless" operator="equals">
<msp:get name="node.parentID" />.add("<msp:get name="node.alias" />", "<msp:if name="node.confirmationMessage" value="null" operator="notequals">if (confirm(\\'<msp:get name="node.confirmationMessage" />\\'))</msp:if>  showModelessDialog(\\'<msp:get name="node.URL" />\\',\\'\\',\\'<msp:get name="node.popupOptions"/>\\'); ");
        </msp:if>

        <msp:if name="node.displayType" value="PopUp" operator="equals">
<msp:get name="node.parentID" />.add("<msp:get name="node.alias" />", "<msp:if name="node.confirmationMessage" value="null" operator="notequals">if (confirm(\\'<msp:get name="node.confirmationMessage" />\\'))</msp:if>  window.open(\\'<msp:get name="node.URL" />\\',\\'\\',\\'<msp:get name="node.popupOptions"/>\\'); ");
        </msp:if>

      </msp:if>
    </msp:if>
  </msp:loop> 
</SCRIPT>







