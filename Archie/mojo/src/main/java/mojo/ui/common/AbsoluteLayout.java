/** AbsoluteLayout.java
* 
* Copyright (C) 1998  NetBeans, Inc.
* 
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA. 
*/

package mojo.ui.common;

import java.awt.*;
import java.io.Serializable;
import java.util.Hashtable;

/** AbsoluteLayout is a LayoutManager that works as a replacement for "null" layout to
* allow placement of components in absolute positions.
*
* @see AbsoluteConstraints
* @version 1.01, Aug 19, 1998
* @modelguid {78DE5C62-71DD-4217-A4EE-A91AFA85C67C}
*/
public class AbsoluteLayout implements LayoutManager2, Serializable {
  /** generated Serialized Version UID 
   * @modelguid {31C91A78-29E7-4774-999B-478641742317}
   */
  static final long serialVersionUID = -1919857869177070440L;

  /** Adds the specified component with the specified name to
  * the layout.
  * @param name the component name
  * @param comp the component to be added
  * @modelguid {DC5E583C-E9FF-466F-BCE4-DCFC860F13CA}
  */
  public void addLayoutComponent(String name, Component comp) {
    throw new IllegalArgumentException();
  }

  /** Removes the specified component from the layout.
  * @param comp the component to be removed
  * @modelguid {74715CCF-31DB-4E09-9FD3-A83FCE68C4BB}
  */
  public void removeLayoutComponent(Component comp) {
    constraints.remove(comp);
  }

  /** Calculates the preferred dimension for the specified
  * panel given the components in the specified parent container.
  * @param parent the component to be laid out
  *
  * @see #minimumLayoutSize
  * @modelguid {E5376218-22FD-4D90-9685-89B82AA54E3E}
  */
  public Dimension preferredLayoutSize(Container parent) {
    int maxWidth = 0;
    int maxHeight = 0;
    for (java.util.Enumeration e = constraints.keys(); e.hasMoreElements();) {
      Component comp = (Component)e.nextElement();
      AbsoluteConstraints ac = (AbsoluteConstraints)constraints.get(comp);
      Dimension size = comp.getPreferredSize();

      int width = ac.getWidth ();
      if (width == -1) width = size.width;
      int height = ac.getHeight ();
      if (height == -1) height = size.height;

      if (ac.x + width > maxWidth)
        maxWidth = ac.x + width;
      if (ac.y + height > maxHeight)
        maxHeight = ac.y + height;
    }
    return new Dimension (maxWidth, maxHeight);
  }

  /** Calculates the minimum dimension for the specified
  * panel given the components in the specified parent container.
  * @param parent the component to be laid out
  * @see #preferredLayoutSize
  * @modelguid {2ADAB9DA-E9D9-45DB-AA4D-A909340EC6DA}
  */
  public Dimension minimumLayoutSize(Container parent) {
    int maxWidth = 0;
    int maxHeight = 0;
    for (java.util.Enumeration e = constraints.keys(); e.hasMoreElements();) {
      Component comp = (Component)e.nextElement();
      AbsoluteConstraints ac = (AbsoluteConstraints)constraints.get(comp);

      Dimension size = comp.getMinimumSize();

      int width = ac.getWidth ();
      if (width == -1) width = size.width;
      int height = ac.getHeight ();
      if (height == -1) height = size.height;

      if (ac.x + width > maxWidth)
        maxWidth = ac.x + width;
      if (ac.y + height > maxHeight)
        maxHeight = ac.y + height;
    }
    return new Dimension (maxWidth, maxHeight);
  }

  /** Lays out the container in the specified panel.
  * @param parent the component which needs to be laid out
  * @modelguid {A52F973D-6536-4BB9-B870-BB1C7653760D}
  */
  public void layoutContainer(Container parent) {
    for (java.util.Enumeration e = constraints.keys(); e.hasMoreElements();) {
      Component comp = (Component)e.nextElement();
      AbsoluteConstraints ac = (AbsoluteConstraints)constraints.get(comp);
      Dimension size = comp.getPreferredSize();
      int width = ac.getWidth ();
      if (width == -1) width = size.width;
      int height = ac.getHeight ();
      if (height == -1) height = size.height;

      comp.setBounds(ac.x, ac.y, width, height);
    }
  }

  /** Adds the specified component to the layout, using the specified
  * constraint object.
  * @param comp the component to be added
  * @param constr  where/how the component is added to the layout.
  * @modelguid {12FBB318-A330-4C77-A2A8-16FF04B2CF59}
  */
  public void addLayoutComponent(Component comp, Object constr) {
    if (!(constr instanceof AbsoluteConstraints))
      throw new IllegalArgumentException();
    constraints.put(comp, constr);
  }

  /** Returns the maximum size of this component.
  * @see Component#getMinimumSize()
  * @see Component#getPreferredSize()
  * @see <{LayoutManager}>
  * @modelguid {F80C87DC-8FBD-44CC-AF50-01FE677A32C5}
  */
  public Dimension maximumLayoutSize(Container target) {
    return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
  }

  /** Returns the alignment along the x axis.  This specifies how
  * the component would like to be aligned relative to other
  * components.  The value should be a number between 0 and 1
  * where 0 represents alignment along the origin, 1 is aligned
  * the furthest away from the origin, 0.5 is centered, etc.
  * @modelguid {837C52A7-C682-4B7C-A8ED-79455D3997D3}
  */
  public float getLayoutAlignmentX(Container target) {
    return 0;
  }

  /** Returns the alignment along the y axis.  This specifies how
  * the component would like to be aligned relative to other
  * components.  The value should be a number between 0 and 1
  * where 0 represents alignment along the origin, 1 is aligned
  * the furthest away from the origin, 0.5 is centered, etc.
  * @modelguid {BBF15A8B-B1C0-4C4A-AEC9-385FCCED9264}
  */
  public float getLayoutAlignmentY(Container target) {
    return 0;
  }

  /** Invalidates the layout, indicating that if the layout manager
  * has cached information it should be discarded.
  * @modelguid {06843DCF-7081-442B-A267-DB5F750C5A4B}
  */
  public void invalidateLayout(Container target) {
  }


  /** A mapping <Component, AbsoluteConstraints> 
   * @modelguid {0F86311E-B80E-4980-8B2B-1BD39C1211C5}
   */
  protected Hashtable constraints = new Hashtable();
}

