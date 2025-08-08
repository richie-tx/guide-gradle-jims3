package mojo.ui.common;

/**
 * A sorter for TableModels. The sorter has a model (conforming to TableModel) 
 * and itself implements TableModel. TableSorter does not store or copy 
 * the data in the TableModel, instead it maintains an array of 
 * integers which it keeps the same size as the number of rows in its 
 * model. When the model changes it notifies the sorter that something 
 * has changed eg. "rowsAdded" so that its internal array of integers 
 * can be reallocated. As requests are made of the sorter (like 
 * getValueAt(row, col) it redirects them to its model via the mapping 
 * array. That way the TableSorter appears to hold another copy of the table 
 * with the rows in a different order. The sorting algorthm used is stable 
 * which means that it does not move around rows when its comparison 
 * function returns 0 to denote that they are equivalent. 
 *
 * @version 1.5 12/17/97
 * @author Philip Milne
 */

//import PresentationLayer.OrderEntry.OrderStatusPrice;
import java.util.*;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.table.TableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.event.TableModelEvent;
import javax.swing.SwingConstants;

// Imports for picking up mouse events from the JTable. 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputEvent;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;

/** @modelguid {4753F3AC-3522-420D-8CB6-8010E1B6DFAB} */
public class TableSorter extends TableMap {
	/** @modelguid {0ABCA275-9708-41DF-87B3-82037E4F259F} */
    int[]             indexes;
	/** @modelguid {7A57A39D-5197-4230-A32B-F8B7DB6D0CED} */
    Vector          sortingColumns = new Vector();
	/** @modelguid {4458EF94-C6C5-4E87-B50D-E5483FC81595} */
    boolean         ascending = true;
	/** @modelguid {19955346-2130-448E-8B11-6AB4C3D4A90B} */
    int compares;
	/** @modelguid {76D62862-C4DA-41BF-8F35-5FD639BB53BC} */
    private static final BevelBorder BORDER=new BevelBorder(BevelBorder.RAISED);
	/** @modelguid {C27606DB-D286-46C9-B4F0-AC7BD13A1F6A} */
    boolean mAscendingSortDirection=true;
	/** @modelguid {0B6A3397-B401-4C6F-A910-007A5279CD2A} */
    String mLastColumnTitle="";
    /** Our arrows 
     * @modelguid {91B5A4C1-C31D-4732-9E18-AC3D42FEDD98}
     */
	static ImageIcon upArrowIcon = new ImageIcon("images/sortascending.gif");
	/** @modelguid {5640BA87-3F03-4181-8D5C-6C840A623891} */
	static ImageIcon downArrowIcon = new ImageIcon("images/sortdescending.gif");
  
  
	/** @modelguid {F37592E5-5EAB-4AF6-A2DB-54B7668B8815} */
    private static void initialize()
    {
        /*
		try {
			upArrowIcon.setImageLocation(symantec.itools.net.RelativeURL.getURL("images/sortascending.gif"));
			downArrowIcon.setImageLocation(symantec.itools.net.RelativeURL.getURL("images/sortdescending.gif"));
		}
		catch (java.net.MalformedURLException error) { }
        */
	}
	
	static {
	    initialize();
	}
	
  
	/** @modelguid {556C1636-9283-4FC1-BF9C-9DE06DCCCCEF} */
    public TableSorter() {
        indexes = new int[0]; // for consistency
    }

	/** @modelguid {01A93864-4329-43F6-A1EF-9AA04DEFCB0B} */
    public TableSorter(TableModel model) {
        setModel(model);
    }

	/** @modelguid {5E7E5C23-9137-4309-9BD1-462F02AE3CAC} */
    public void setModel(TableModel model) {
        super.setModel(model); 
        reallocateIndexes(); 
    }

	/** @modelguid {EE9DF330-8A14-47A9-A67C-141E1670D4A4} */
    public int compareRowsByColumn(int row1, int row2, int column) {
        Class type = model.getColumnClass(column);
        TableModel data = model;

        // Check for nulls.
        Object o1 = data.getValueAt(row1, column);
        Object o2 = data.getValueAt(row2, column); 
 
        // If both values are null, return 0.
        if (o1 == null && o2 == null) {
            return 0; 
        } else if (o1 == null) { // Define null less than everything. 
            return -1; 
        } else if (o2 == null) { 
            return 1; 
        }

        /*
         * We copy all returned values from the getValue call in case
         * an optimised model is reusing one object to return many
         * values.  The Number subclasses in the JDK are immutable and
         * so will not be used in this way but other subclasses of
         * Number might want to do this to save space and avoid
         * unnecessary heap allocation.
         */

        if (type.getSuperclass() == Number.class) {
            Number n1 = (Number)data.getValueAt(row1, column);
            double d1 = n1.doubleValue();
            Number n2 = (Number)data.getValueAt(row2, column);
            double d2 = n2.doubleValue();

            if (d1 < d2) {
                return -1;
            } else if (d1 > d2) {
                return 1;
            } else {
                return 0;
            }
        } else if (type == Date.class) {
            Date d1 = (Date)data.getValueAt(row1, column);
            long n1 = d1.getTime();
            Date d2 = (Date)data.getValueAt(row2, column);
            long n2 = d2.getTime();

            if (n1 < n2) {
                return -1;
            } else if (n1 > n2) {
                return 1;
            } else {
                return 0;
            }
        } else if (type == String.class) {
            String s1 = (String)data.getValueAt(row1, column);
            String s2    = (String)data.getValueAt(row2, column);
            int result = s1.compareTo(s2);

            if (result < 0) {
                return -1;
            } else if (result > 0) {
                return 1;
            } else {
                return 0;
            }
        } else if (type == Boolean.class) {
            Boolean bool1 = (Boolean)data.getValueAt(row1, column);
            boolean b1 = bool1.booleanValue();
            Boolean bool2 = (Boolean)data.getValueAt(row2, column);
            boolean b2 = bool2.booleanValue();

            if (b1 == b2) {
                return 0;
            } else if (b1) { // Define false < true
                return 1;
            } else {
                return -1;
            }
        } else if ((o1.getClass().getName().equals("[Ljava.lang.String;"))==true) {
           String s1= ((String)((String[])o1)[0]);
           String s2= ((String)((String[])o2)[0]);
           int result = s1.compareTo(s2);

           if (result < 0) {
             return -1;
           } else if (result > 0) {
             return 1;
           } else {
             return 0;  
           }
         }
         else  {
           Object v1 = data.getValueAt(row1, column);
           String s1 = v1.toString();
           Object v2 = data.getValueAt(row2, column);
           String s2 = v2.toString();
           int result = s1.compareTo(s2);

           if (result < 0) {
             return -1;
           } else if (result > 0) {
             return 1;
           } else {
            return 0;
         }
        }
    }

	/** @modelguid {D9F683EA-8262-4AF3-9AED-B02E6932FB39} */
    public int compare(int row1, int row2) {
        compares++;
        for (int level = 0; level < sortingColumns.size(); level++) {
            Integer column = (Integer)sortingColumns.elementAt(level);
            int result = compareRowsByColumn(row1, row2, column.intValue());
            if (result != 0) {
                return ascending ? result : -result;
            }
        }
        return 0;
    }

	/** @modelguid {DEB13DA2-AFBD-48F2-AEF7-72D36696F843} */
    public void reallocateIndexes() {
        int rowCount = model.getRowCount();

        // Set up a new array of indexes with the right number of elements
        // for the new data model.
        indexes = new int[rowCount];

        // Initialise with the identity mapping.
        for (int row = 0; row < rowCount; row++) {
            indexes[row] = row;
        }
    }

	/** @modelguid {E43E6CDC-B818-47D4-9ADC-325FDE43D1CA} */
    public void tableChanged(TableModelEvent e) {
        reallocateIndexes();
        sort(this);
        super.tableChanged(e);
    }

	/** @modelguid {9ABC2B2A-F324-4C97-B0C2-0B1289964C53} */
    public boolean checkModel() {
        boolean check = true;
        if (indexes.length != model.getRowCount()) {
            check = false;
        }
        return check;
    }

	/** @modelguid {62FEB692-EC81-4287-BA07-D5E2750FE8B5} */
    public void sort(Object sender) {
        checkModel();
        compares = 0;
        shuttlesort((int[])indexes.clone(), indexes, 0, indexes.length);
    }

	/** @modelguid {8D2B0378-4A89-48FD-8A47-83AA8338CDB5} */
    public void n2sort() {
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = i+1; j < getRowCount(); j++) {
                if (compare(indexes[i], indexes[j]) == -1) {
                    swap(i, j);
                }
            }
        }
    }

    // This is a home-grown implementation which we have not had time
    // to research - it may perform poorly in some circumstances. It
    // requires twice the space of an in-place algorithm and makes
    // NlogN assigments shuttling the values between the two
    // arrays. The number of compares appears to vary between N-1 and
    // NlogN depending on the initial order but the main reason for
    // using it here is that, unlike qsort, it is stable.
	/** @modelguid {800712FD-1A0B-4A71-AFE0-06A3C922D1E1} */
    public void shuttlesort(int[] from, int[] to, int low, int high) {
        if (high - low < 2) {
            return;
        }
        int middle = (low + high)/2;
        shuttlesort(to, from, low, middle);
        shuttlesort(to, from, middle, high);

        int p = low;
        int q = middle;

        /* This is an optional short-cut; at each recursive call,
        check to see if the elements in this subset are already
        ordered.  If so, no further comparisons are needed; the
        sub-array can just be copied.  The array must be copied rather
        than assigned otherwise sister calls in the recursion might
        get out of sinc.  When the number of elements is three they
        are partitioned so that the first set, [low, mid), has one
        element and and the second, [mid, high), has two. We skip the
        optimisation when the number of elements is three or less as
        the first compare in the normal merge will produce the same
        sequence of steps. This optimisation seems to be worthwhile
        for partially ordered lists but some analysis is needed to
        find out how the performance drops to Nlog(N) as the initial
        order diminishes - it may drop very quickly.  */

        if (high - low >= 4 && compare(from[middle-1], from[middle]) <= 0) {
            for (int i = low; i < high; i++) {
                to[i] = from[i];
            }
            return;
        }

        // A normal merge. 

        for (int i = low; i < high; i++) {
            if (q >= high || (p < middle && compare(from[p], from[q]) <= 0)) {
                to[i] = from[p++];
            }
            else {
                to[i] = from[q++];
            }
        }
    }

	/** @modelguid {3D5700D4-F6BC-4F9C-AC0B-55D4E4AB0BE4} */
    public void swap(int i, int j) {
        int tmp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = tmp;
    }

	/** @modelguid {6025E951-3F86-420D-8A26-50F81727D53E} */
    public int getMappedRow(int aRow)
    {
      return indexes[aRow];
    }
    
    // The mapping only affects the contents of the data rows.
    // Pass all requests to these rows through the mapping array: "indexes".

	/** @modelguid {47B020F2-D055-4E94-A1F7-BA0D4AD60246} */
    public Object getValueAt(int aRow, int aColumn) {
        checkModel();
        return model.getValueAt(indexes[aRow], aColumn);
    }

	/** @modelguid {1F55C774-7850-41F0-B6B4-3503D043F5BE} */
    public void setValueAt(Object aValue, int aRow, int aColumn) {
        checkModel();
        model.setValueAt(aValue, indexes[aRow], aColumn);
    }

	/** @modelguid {58BE4ECD-96FA-46EA-9EEC-B972C03D4EB8} */
    public void sortByColumn(int column) {
        sortByColumn(column, true);
    }

	/** @modelguid {4444161C-2D53-44EE-8380-F93A09C855BE} */
    public void sortByColumn(int column, boolean ascending) {
        this.ascending = ascending;
        sortingColumns.removeAllElements();
        sortingColumns.addElement(new Integer(column));
        sort(this);
        super.tableChanged(new TableModelEvent(this)); 
    }

	/** @modelguid {4062FA64-D0CA-4EB7-8C6F-835A7953DCAF} */
   class ButtonHeaderRenderer extends JButton implements TableCellRenderer {
		/** @modelguid {0486EA8F-199C-4C31-8A72-F2483A952C0E} */
     String mPushedColumn;
		/** @modelguid {74A7255D-C02B-4E77-9F92-D8B5ED835E6A} */
     boolean mAscendingFlag;
     
		/** @modelguid {0BDF2B3E-D553-4A07-8AE3-67FD5B7D7A44} */
     public ButtonHeaderRenderer() {
       setHorizontalAlignment(SwingConstants.CENTER);    
       mPushedColumn="";
       setBorder(BORDER);
     }
 
		/** @modelguid {409D1393-C63D-4486-A556-AA6E84479AF1} */
     public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {             
       setHorizontalTextPosition(SwingConstants.RIGHT);
       setText((value ==null) ? "" : value.toString());
       setBorder(BORDER);
 
       boolean isPressed=false;
       if ((value.toString()).equals(mPushedColumn)) 
       {
         isPressed=true;
         if (mAscendingFlag)
         {
          setIcon(downArrowIcon);
         }
         else
         {
          setIcon(upArrowIcon);
         }
       }
       else 
       {
         setIcon(null); 
       }
        
       getModel().setPressed(isPressed);
       getModel().setArmed(isPressed);
       return this;
     }
  
		/** @modelguid {7184B38A-BC3E-4A24-9665-1A74B28A9E54} */
     public void setPressedColumn(String columnName)
     {
        mPushedColumn=columnName;  
     }
     
		/** @modelguid {306F6A6F-B55D-497D-B931-3A90A5D1D1DE} */
     public void setSortDirection(boolean ascendingFlag)
     {
       mAscendingFlag=ascendingFlag;      
     }
     
     
   } 

	/** @modelguid {40AF4E8A-4D16-4218-8E41-EDDA47016174} */
    class HeaderMouseListener extends MouseAdapter
    {
		/** @modelguid {68835C4F-4C60-4579-A135-63961D0145A8} */
       final JTable mTableView;
		/** @modelguid {E30497A1-D007-4116-B659-E48A7321DC09} */
       TableSorter sorter=null;
		/** @modelguid {51298EFB-E2D4-4FB2-ABA5-71AE98887D1D} */
       boolean mHighlightHeader;
		/** @modelguid {5FDFBFBD-C355-4DF4-ABC0-7BD8DF7F9476} */
       String mHeaderTitle;
		/** @modelguid {C10B5E85-3694-4465-A98C-DE74B5643C4A} */
       ButtonHeaderRenderer mHeaderRenderer=null;

		/** @modelguid {C9A20AB0-280C-4053-A288-71D779218BB4} */
       public HeaderMouseListener(JTable tableView,boolean highlightColumnHeader)
       { 
          sorter = TableSorter.this; 
          mTableView=tableView;        
          mHighlightHeader=highlightColumnHeader; 
          if (mHighlightHeader==true) 
          {
            TableColumnModel model = tableView.getColumnModel();  
            int columnsToInit=(tableView.getModel()).getColumnCount();
            mHeaderRenderer=new ButtonHeaderRenderer(); 
            for (int i=0;i<columnsToInit;i++) {
             model.getColumn(i).setHeaderRenderer(mHeaderRenderer);
            }
          }
       }
       
		/** @modelguid {C0CB8032-2370-43FE-8EA1-C715878A8265} */
       public void mouseClicked(MouseEvent e) {
         TableColumnModel columnModel = mTableView.getColumnModel();
         int viewColumn = columnModel.getColumnIndexAtX(e.getX()); 
         int column = mTableView.convertColumnIndexToModel(viewColumn); 
         if (e.getClickCount() == 1 && column != -1) {
             String currentColumnTitle=(mTableView.getModel()).getColumnName(column);
             if (mLastColumnTitle.equals(currentColumnTitle))
               mAscendingSortDirection=!mAscendingSortDirection;
             else
               mAscendingSortDirection=true; 
             mLastColumnTitle=currentColumnTitle;
             
             sorter.sortByColumn(column, mAscendingSortDirection); 
             if (mHighlightHeader)
             {  
                mHeaderRenderer.setPressedColumn((mTableView.getModel()).getColumnName(column));
                mHeaderRenderer.setSortDirection(mAscendingSortDirection);
               (mTableView.getTableHeader()).resizeAndRepaint();  
             }
            
          }
       }
    }

    // Add a mouse listener to the Table to trigger a table sort 
    // when a column heading is clicked in the JTable. 
	/** @modelguid {281546C3-D71A-4EF2-A1AD-B8DB93BB8E1E} */
    public void addMouseListenerToHeaderInTable(JTable table) { 
        this.addMouseListenerToHeaderInTable(table,false);
    }
    
    // Add a mouse listener to the Table to trigger a table sort 
    // when a column heading is clicked in the JTable. 
	/** @modelguid {294BA8A2-76D8-44A4-AA2A-D7719E987E61} */
    public void addMouseListenerToHeaderInTable(JTable table,boolean highlightColumnHeader) { 
        final JTable tableView = table; 
        tableView.setColumnSelectionAllowed(false); 
        HeaderMouseListener listMouseListener = new HeaderMouseListener(tableView,highlightColumnHeader);
        JTableHeader th = tableView.getTableHeader(); 
        th.addMouseListener(listMouseListener);
    }    
}
