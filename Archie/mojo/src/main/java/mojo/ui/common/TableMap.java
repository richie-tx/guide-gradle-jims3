package mojo.ui.common;

/** 
 * In a chain of data manipulators some behaviour is common. TableMap
 * provides most of this behavour and can be subclassed by filters
 * that only need to override a handful of specific methods. TableMap 
 * implements TableModel by routing all requests to its model, and
 * TableModelListener by routing all events to its listeners. Inserting 
 * a TableMap which has not been subclassed into a chain of table filters 
 * should have no effect.
 *
 * @version 1.4 12/17/97
 * @author Philip Milne */

import javax.swing.table.*; 
import javax.swing.event.TableModelListener; 
import javax.swing.event.TableModelEvent; 

/** @modelguid {35E70E35-151B-4B7E-9431-8D0CA0476515} */
public class TableMap extends AbstractTableModel 
                      implements TableModelListener {
	/** @modelguid {62126512-0EBC-40C7-BB76-F08BDF971124} */
    protected TableModel model; 

	/** @modelguid {F6F55A40-3D42-4366-B70C-93A04F3610BD} */
    public TableModel getModel() {
        return model;
    }

	/** @modelguid {C3561E61-37F9-42FA-A303-360EB1884A87} */
    public void setModel(TableModel model) {
        this.model = model; 
        model.addTableModelListener(this); 
    }

    // By default, implement TableModel by forwarding all messages 
    // to the model. 

	/** @modelguid {C4FC3801-89D8-44DD-BD79-B2E1A4A43AA0} */
    public Object getValueAt(int aRow, int aColumn) {
        return model.getValueAt(aRow, aColumn); 
    }
        
	/** @modelguid {EC6DD3AA-0D33-454F-B7E5-05EDF43E675A} */
    public void setValueAt(Object aValue, int aRow, int aColumn) {
        model.setValueAt(aValue, aRow, aColumn); 
    }

	/** @modelguid {921BC6DB-7B43-492B-B83F-1A8324B12380} */
    public int getRowCount() {
        return (model == null) ? 0 : model.getRowCount(); 
    }

	/** @modelguid {94801A8F-20D5-44E4-B930-80856543FF3B} */
    public int getColumnCount() {
        return (model == null) ? 0 : model.getColumnCount(); 
    }
        
	/** @modelguid {4675978F-BD78-4340-A13D-BDB9A15B3C25} */
    public String getColumnName(int aColumn) {
        return model.getColumnName(aColumn); 
    }

	/** @modelguid {D2C2C851-C9C0-4210-9A1F-D492A45DB713} */
    public Class getColumnClass(int aColumn) {
        return model.getColumnClass(aColumn); 
    }
        
	/** @modelguid {5D924FE5-6AD8-42DA-82D3-E6D9C8A573B9} */
    public boolean isCellEditable(int row, int column) { 
         return model.isCellEditable(row, column); 
    }
//
// Implementation of the TableModelListener interface, 
//
    // By default forward all events to all the listeners. 
	/** @modelguid {CF064D7E-6DA4-41B4-88BB-A7D8455F6D74} */
    public void tableChanged(TableModelEvent e) {
        fireTableChanged(e);
    }
}
