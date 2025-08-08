package mojo.ui.common;

/**
 */
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

/**
 * Responsible for handling the enabling of sorted columns on a tabular list.
* A sorter for TableModels. The sorter has a model (conforming to TableModel) and itself implements TableModel. TableSorter
 * does not store or copy the data in the TableModel, instead it maintains an array of integers which it keeps the same size
 * as the number of rows in its model. When the model changes it notifies the sorter that something has changed eg.
 * "rowsAdded" so that its internal array of integers can be reallocated. As requests are made of the sorter (like
 * getValueAt(row, col) it redirects them to its model via the mapping array. That way the TableSorter appears to hold another
 * copy of the table with the rows in a different order. The sorting algorthm used is stable which means that it does not move
 * around rows when its comparison function returns 0 to denote that they are equivalent.
 * @contextManagerName mojo.km.context.Default.DefaultContextManager
 * @requestDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @replyDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @asyncDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @queueDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @pubSubDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @debugMode false
 * @connectionPool false
 * @workflowEnabled false
 * @modelguid {5FF9558E-201C-49BE-A4A6-7D1F9D2AF0AB}
 */
public class IntegratedSortedTable extends IntegratedTable {
	/** @modelguid {A7502C25-744C-4372-96F1-2AF2BCB7EA86} */
    int[] indexes;
	/** @modelguid {34DE48A6-B5B3-445A-A2E2-D55DCC3AFD53} */
    Vector sortingColumns = new Vector();
	/** @modelguid {035062C7-6174-475D-AE5A-84306524D64B} */
    boolean ascending = true;
	/** @modelguid {3F734C11-D030-4F85-BB0A-80C543DD5924} */
    int compares;
	/** @modelguid {BAEE890F-9F1C-44AE-A5A7-48C9D8A359B6} */
    private static final BevelBorder BORDER = new BevelBorder(BevelBorder.RAISED);
	/** @modelguid {D25F47FB-9CE8-45F1-8334-E20E1DDA2FCD} */
    boolean mAscendingSortDirection = true;
	/** @modelguid {A102B80F-83A0-4CDB-9698-6C36B180DC79} */
    String mLastColumnTitle = "";

    /** Our arrows 
     * @modelguid {E49A8754-85C9-4036-9B20-B7EF2666135C}
     */
    static ImageIcon upArrowIcon = new ImageIcon("ui/images/SortAsc.gif");
	/** @modelguid {607FB38A-D857-4329-95F0-CD961044B169} */
    static ImageIcon downArrowIcon = new ImageIcon("ui/images/SortDesc.gif");

	/** @modelguid {15457929-997C-4960-817B-DCFB40D038EA} */
    private static void initialize() {
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

	/** @modelguid {A19E178F-871A-4CE5-983C-34154383FB1F} */
    public IntegratedSortedTable() {
        indexes = new int[0]; // for consistency
    }

	/** @modelguid {80A4DDC9-0EB2-4A14-9D9C-7760C9677500} */
    public IntegratedSortedTable(TableModel model) {
        setModel(model);
    }

	/** @modelguid {7BE4D399-C9EA-4BA1-81F2-EA2FD9B5E622} */
    public void setModel(TableModel model) {
        super.setModel(model);
        reallocateIndexes();
    }

	/** @modelguid {A492B704-EED9-4924-A70D-FE51310A49A3} */
    public int compareRowsByColumn(int row1, int row2, int column) {
        Class type = this.getModel().getColumnClass(column);
        TableModel data = this.getModel();
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
            String s2 = (String)data.getValueAt(row2, column);
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
        } else if ((o1.getClass().getName().equals("[Ljava.lang.String;")) == true) {
            String s1 = ((String)((String[]) o1) [0]);
            String s2 = ((String)((String[]) o2) [0]);
            int result = s1.compareTo(s2);
            if (result < 0) {
                return -1;
            } else if (result > 0) {
                return 1;
            } else {
                return 0;
            }
        } else if (o1.getClass().isPrimitive()) {
            double d1 = 0;
            double d2 = 0;
            try {
                d1 = Double.parseDouble("" + data.getValueAt(row1, column));
                d2 = Double.parseDouble("" + data.getValueAt(row2, column));
            } catch (Throwable t) { System.err.println(t.getMessage()); }
            if (d1 < d2) {
                return -1;
            } else if (d1 > d2) {
                return 1;
            } else {
                return 0;
            }
        } else {
            Object v1 = data.getValueAt(row1, column);
            String s1 = v1.toString();
            Object v2 = data.getValueAt(row2, column);
            String s2 = v2.toString();
            double d1 = 0;
            double d2 = 0;
            boolean isNumeric = true;
            try {
                d1 = Double.parseDouble(s1);
                d2 = Double.parseDouble(s2);
            } catch (Throwable t) { isNumeric = false; }
            if (isNumeric) {
                if (d1 < d2) {
                    return -1;
                } else if (d1 > d2) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
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
    }

	/** @modelguid {B74898C9-DBB8-4EA1-86C1-13CFC4785A3C} */
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

	/** @modelguid {872DFCDD-0315-4DC2-A2D3-B444E39C1669} */
    public void reallocateIndexes() {
        int rowCount = getModel().getRowCount();
        // Set up a new array of indexes with the right number of elements
        // for the new data model.
        indexes = new int[rowCount];
        // Initialise with the identity mapping.
        for (int row = 0; row < rowCount; row++) {
            indexes[row] = row;
        }
    }

	/** @modelguid {FDC41EA6-457F-4C42-8AF7-5FAEEEAC0258} */
    public void tableChanged(TableModelEvent e) {
        reallocateIndexes();
        sort(this);
        super.tableChanged(e);
    }

	/** @modelguid {4ADD276F-FE4C-4E85-8E80-7A3DB21B3D25} */
    public boolean checkModel() {
        boolean check = true;
        if (indexes.length != getModel().getRowCount()) {
            check = false;
        }
        return check;
    }

	/** @modelguid {954B003E-058B-49A6-B42D-5F7A9D236027} */
    public void sort(Object sender) {
        checkModel();
        compares = 0;
        shuttlesort((int[]) indexes.clone(), indexes, 0, indexes.length);
    }

	/** @modelguid {C9ED5B9D-6162-403F-85C4-CA5BA550BDF4} */
    public void n2sort() {
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = i + 1; j < getRowCount(); j++) {
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
	/** @modelguid {D91762EA-509C-4BBD-9A4E-C579B9FAFCC9} */
    public void shuttlesort(int[] from, int[] to, int low, int high) {
        if (high - low < 2) {
            return;
        }
        int middle = (low + high) / 2;
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

        if (high - low >= 4 && compare(from[middle - 1], from[middle]) <= 0) {
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

	/** @modelguid {E17CDF5C-483F-4DF6-A052-2A22B8E1060C} */
    public void swap(int i, int j) {
        int tmp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = tmp;
    }

	/** @modelguid {053335CF-9896-46EF-9331-82E5DD3DAC1F} */
    public int getMappedRow(int aRow) {
        return indexes[aRow];
    }

    // The mapping only affects the contents of the data rows.
    // Pass all requests to these rows through the mapping array: "indexes".
	/** @modelguid {93E951AE-9D29-40CB-BF34-7D09AC41983E} */
    public Object getValueAt(int aRow, int aColumn) {
        checkModel();
        int trueColumn = this.convertColumnIndexToModel(aColumn);
        return getModel().getValueAt(indexes[aRow], trueColumn);
    }

	/** @modelguid {EB363275-3127-4077-84A3-DDFF8AD5CD16} */
    public void setValueAt(Object aValue, int aRow, int aColumn) {
        checkModel();
        int trueColumn = this.convertColumnIndexToModel(aColumn);
        getModel().setValueAt(aValue, indexes[aRow], trueColumn);
    }

	/** @modelguid {BC9AB0D3-2D6B-47BC-A50A-ED1E368135F4} */
    public void sortByColumn(int column) {
        sortByColumn(column, true);
    }

	/** @modelguid {3DFCF86F-A3C5-4B09-BE63-6F9AD2E406B1} */
    public void sortByColumn(int column, boolean ascending) {
        this.ascending = ascending;
        sortingColumns.removeAllElements();
        sortingColumns.addElement(new Integer(column));
        sort(this);
        //this.fireTableChanged(new TableModelEvent(this));
    }

	/** @modelguid {7BDD6F43-F754-4548-AE9F-10C14FF5AD90} */
    class ButtonHeaderRenderer extends JButton implements TableCellRenderer {
		/** @modelguid {50281B7A-BE19-426E-BA05-B497A2F717DA} */
        String mPushedColumn;
		/** @modelguid {56D21D7E-8ACB-4DA7-90FE-DE8D169E24DD} */
        boolean mAscendingFlag;

		/** @modelguid {5FABCDBA-F6FA-448F-B9A6-73F918BF20C3} */
        public ButtonHeaderRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
            mPushedColumn = "";
            setBorder(BORDER);
        }

		/** @modelguid {5FF34236-4A3D-402B-BE58-658E6B56EA25} */
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
                setHorizontalTextPosition(SwingConstants.RIGHT);
                setText((value == null) ? "" : value.toString());
                setBorder(BORDER);
                boolean isPressed = false;
                if ((value.toString()).equals(mPushedColumn)) {
                    isPressed = true;
                    if (mAscendingFlag) {
                        setIcon(downArrowIcon);
                    }
                    else {
                        setIcon(upArrowIcon);
                    }
                }
                else {
                    setIcon(null);
                }
                //getModel().setPressed(isPressed);
                getModel().setArmed(isPressed);
                return this;
        }

		/** @modelguid {624712BE-FF01-4148-A9D7-F47189F34903} */
        public void setPressedColumn(String columnName) {
            mPushedColumn = columnName;
        }

		/** @modelguid {1CCED3A2-4F3A-4819-99B5-B7A9FC6192B6} */
        public void setSortDirection(boolean ascendingFlag) {
            mAscendingFlag = ascendingFlag;
        }
    }


	/** @modelguid {2B5C5943-8880-448F-83BF-FEAF2FB49A1F} */
    class HeaderMouseListener extends MouseAdapter {
		/** @modelguid {5C3E51BA-F5D3-4CE2-AC8E-A75DFC91DF8A} */
        final JTable mTableView;
		/** @modelguid {300E3BC9-428B-4383-8D9A-1AF8BAC32423} */
        IntegratedSortedTable sorter = null;
		/** @modelguid {D43CEDE6-19D3-4E7C-AF66-E44AFEE03844} */
        boolean mHighlightHeader;
		/** @modelguid {1E03632E-F060-47EF-AEE8-0292ABF0A0CF} */
        String mHeaderTitle;
		/** @modelguid {5723C146-0F7E-4363-8348-525C61EA9A0E} */
        ButtonHeaderRenderer mHeaderRenderer = null;

		/** @modelguid {ED93D56F-AB99-4C36-9337-C1E8C002134E} */
        public HeaderMouseListener(JTable tableView, boolean highlightColumnHeader) {
            sorter = IntegratedSortedTable.this;
            mTableView = tableView;
            mHighlightHeader = highlightColumnHeader;
            if (mHighlightHeader == true) {
                TableColumnModel model = tableView.getColumnModel();
                int columnsToInit = (tableView.getModel()).getColumnCount();
                mHeaderRenderer = new ButtonHeaderRenderer();
                for (int i = 0; i < columnsToInit; i++) {
                    model.getColumn(i).setHeaderRenderer(mHeaderRenderer);
                }
            }
        }

		/** @modelguid {F423DB70-5E1C-4660-9AE1-D66FB7D1AC42} */
        public void mouseClicked(MouseEvent e) {
            TableColumnModel columnModel = mTableView.getColumnModel();
            int viewColumn = columnModel.getColumnIndexAtX(e.getX());
            int column = mTableView.convertColumnIndexToModel(viewColumn);
            if (e.getClickCount() == 1 && column != -1) {
                String currentColumnTitle = (mTableView.getModel()).getColumnName(column);
                if (mLastColumnTitle.equals(currentColumnTitle))
                    mAscendingSortDirection = !mAscendingSortDirection;
                else
                    mAscendingSortDirection = true;
                mLastColumnTitle = currentColumnTitle;
                sorter.sortByColumn(column, mAscendingSortDirection);
                if (mHighlightHeader) {
                    mHeaderRenderer.setPressedColumn((mTableView.getModel()).getColumnName(column));
                    mHeaderRenderer.setSortDirection(mAscendingSortDirection);
                    (mTableView.getTableHeader()).resizeAndRepaint();
                }
            }
        }
    }


    // Add a mouse listener to the Table to trigger a table sort
    // when a column heading is clicked in the JTable.
	/** @modelguid {9DFA058E-8F67-4EAC-9C2C-8D1FC737D028} */
    public void addMouseListenerToHeaderInTable(JTable table) {
        this.addMouseListenerToHeaderInTable(table, false);
    }

    // Add a mouse listener to the Table to trigger a table sort
    // when a column heading is clicked in the JTable.
	/** @modelguid {6C315251-2D73-4DF9-8279-CF06D0EDABB4} */
    public void addMouseListenerToHeaderInTable(JTable table, boolean highlightColumnHeader) {
        final JTable tableView = table;
        tableView.setColumnSelectionAllowed(false);
        HeaderMouseListener listMouseListener = new HeaderMouseListener(tableView, highlightColumnHeader);
        JTableHeader th = tableView.getTableHeader();
        th.addMouseListener(listMouseListener);
    }
}
