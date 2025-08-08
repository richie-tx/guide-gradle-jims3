// Copyright (C) 1999 by Jason Hunter <jhunter@acm.org>.  All rights reserved.
// Use of this class is limited.  Please see the LICENSE for more information.
 
package mojo.km.service.servlet.multipart;

/**
 * A <code>Part</code> is an abstract upload part which represents an 
 * <code>INPUT</code> form element in a <code>multipart/form-data</code> form 
 * submission.
 * 
 * @see FilePart
 * @see ParamPart
 * 
 * @author Geoff Soutter
 * @version 1.0, 2000/10/27, initial revision
 * @modelguid {2CF32BB5-8E0F-4DC1-BCF0-0F8DFA805CF3}
 */
public abstract class Part {
	/** @modelguid {07F710AB-FA43-4AA7-BA69-30B406DCC96B} */
  private String name;
  
  /**
   * Constructs an upload part with the given name.
   * @modelguid {022313F0-6FA2-474F-8E41-9BED9152DCEE}
   */
  Part(String name) {
    this.name = name;
  }
  
  /**
   * Returns the name of the form element that this Part corresponds to.
   * 
   * @return the name of the form element that this Part corresponds to.
   * @modelguid {04BBECC9-AD06-46A3-9EB8-98580CFA4F35}
   */
  public String getName() {
    return name;
  }
  
  /**
   * Returns true if this Part is a FilePart.
   * 
   * @return true if this is a FilePart.
   * @modelguid {E30E39C4-E127-40B0-AD40-FD1C4D0D56AE}
   */
  public boolean isFile() {
    return false;
  }
  
  /**
   * Returns true if this Part is a ParamPart.
   * 
   * @return true if this is a ParamPart.
   * @modelguid {DC73E77F-3693-46C6-98A0-4AA3C1E807F0}
   */
  public boolean isParam() {
    return false;
  }
}
