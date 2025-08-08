function RSCustomInterface(rtbElementName){
          
          this.rtbName =  rtbElementName;
          this.getText = getText;
          this.setText = setText;
          function getText(){     
          var finalVal=null;
          if(window.tinyMCE){
               var editId = tinyMCE.selectedInstance.editorId;
               var inst = tinyMCE.getInstanceById(editId);
               if(inst){
                    finalVal=inst.getBody().innerHTML;
               }
          }
          if(finalVal==null){
               var myFoundElem=document.getElementsByName(tbElementName)[0];
               finalVal=myFoundElem.value;
          }
          return finalVal;
     }
}

function setText(text){     
     if(window.tinyMCE){
          var editId = tinyMCE.selectedInstance.editorId;
          var inst = tinyMCE.getInstanceById(editId);
          if(inst){
               inst.getBody().innerHTML=text;
          }
     } else {
          var myFoundElem=document.getElementsByName(tbElementName)[0];
          myFoundElem.value=text;
     }
}