package mojo.km.messaging;

import java.lang.reflect.Array;

/**
 * Insert the type's description here.
 * Creation date: (5/17/00 12:39:03 PM)
 * @author:
 * @modelguid {71ADBEF6-3A28-4C81-B592-AE657B4C0EF5}
 */
public class Field {
	/** @modelguid {9A76C526-3951-443C-8552-517585E73B13} */
    private int index = 0;
	/** @modelguid {F0E2E0C9-8441-4C0E-BC19-EA50D33D4871} */
    private Object column = null;
	/** @modelguid {172B704F-0859-46C1-9CBE-AD54A7141046} */
    private Class originalType = null;

    /**
     * Field constructor comment.
     * @modelguid {11F84491-5F9E-479E-A8C4-52EC83751CB8}
     */
    public Field() {
        super();
    }

    /**
     * Insert the method's description here.
     * Creation date: (5/17/00 12:40:57 PM)
     * @return <{Array}>
     * @modelguid {8AF06910-77C7-49E0-A04F-CCECEF366E76}
     */
    public Object getColumn() {
        return column;
    }

    /**
     * Insert the method's description here.
     * Creation date: (5/17/00 12:40:23 PM)
     * @return int
     * @modelguid {AF5B0F39-269C-43B4-A434-D3BB54B0C5C6}
     */
    public int getIndex() {
        return index;
    }

    /**
     * Insert the method's description here.
     * Creation date: (5/17/00 1:39:58 PM)
     * @return <{Class}>
     * @modelguid {8E5FCC04-D708-4C62-903D-9AA8E0CF79CC}
     */
    public Class getOriginalType() {
        return originalType;
    }

    /**
     * Insert the method's description here.
     * Creation date: (5/17/00 12:40:57 PM)
     * @param newColumn java.lang.reflect.Array
     * @modelguid {CA8EE04B-F10A-45C6-8816-D1E763C1EF9F}
     */
    public void setColumn(Object newColumn) {
        column = newColumn;
    }

    /**
     * Insert the method's description here.
     * Creation date: (5/17/00 12:40:23 PM)
     * @param newIndex int
     * @modelguid {7AF0FC89-6755-4C47-9683-12AD05BC89EB}
     */
    public void setIndex(int newIndex) {
        index = newIndex;
    }

    /**
     * Insert the method's description here.
     * Creation date: (5/17/00 1:39:58 PM)
     * @param newOriginalType java.lang.Class
     * @modelguid {FFEAC990-FCD5-49F3-8D11-BF94D30CCABE}
     */
    public void setOriginalType(Class newOriginalType) {
        originalType = newOriginalType;
    }
}
