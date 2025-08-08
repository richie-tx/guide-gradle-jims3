
function selectContact(var attrName) {
  var  = showModalDialog("/jims2/control/?Server=jims2&Topic=DisplayContactSearchCriteria","","dialogHeight: 500px; dialogWidth: 800px; dialogTop: 150px; dialogLeft: 150px; edge: Raised; center: no; help: No; resizable: Yes; status: No;");
  if (contact) {
    var index = contact.indexOf("|");
    var OID = contact.substring(0,index);
    var name = contact.substring(index+1);
    eval("document.forms[0]."+attrName+"OID").value = OID;
    eval("document.forms[0]."+attrName+"Name").value = name;
  }
}

function createContact(var attrName) {
  var contact = showModalDialog("/jims2/control/?Server=jims2&Topic=DisplayContactCreate");
  if (contact) {
    var index = contact.indexOf("|");
    var OID = contact.substring(0,index);
    var name = contact.substring(index+1);
    eval("document.forms[0]."+attrName+"OID").value = OID;
    eval("document.forms[0]."+attrName+"Name").value = name;
  }
}

function deleteContact() {
   eval("document.forms[0]."+attrName+"OID").value = "";
   eval("document.forms[0]."+attrName+"Name").value = "";
}

function selectOrganization() {
  var  = showModalDialog("/jims2/control/?Server=jims2&Topic=DisplayOrganizationSearchCriteria","","dialogHeight: 500px; dialogWidth: 800px; dialogTop: 150px; dialogLeft: 150px; edge: Raised; center: no; help: No; resizable: Yes; status: No;");
  if (organization) {
    var index = organization.indexOf("|");
    var OID = organization.substring(0,index);
    var name = organization.substring(index+1);
    document.forms[0].attrName.value = OID;
    document.forms[0].attrName.value = name;
  }
}

function createOrganization() {
  var organization = showModalDialog("/jims2/control/?Server=jims2&Topic=DisplayOrganizationCreate");
  if (organization) {
    var index = organization.indexOf("|");
    var OID = organization.substring(0,index);
    var name = organization.substring(index+1);
    eval("document.forms[0]."+attrName+"OID").value = OID;
    eval("document.forms[0]."+attrName+"Name").value = name;
  }
}

function deleteOrganization() {
   eval("document.forms[0]."+attrName+"OID").value = "";
   eval("document.forms[0]."+attrName+"Name").value = "";
}
