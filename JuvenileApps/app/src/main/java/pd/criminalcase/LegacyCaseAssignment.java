// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\criminalcase\\LegacyCaseAssignment.java

package pd.criminalcase;

import java.util.Date;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class LegacyCaseAssignment extends PersistentObject {
    private Date transDate;

    private String poi;

    private String opId;

    /**
     * @roseuid 4769761D037F
     */
    public LegacyCaseAssignment() {

    }

    /**
     * @roseuid 475D9DBB029B
     */
    public void create() {

    }
    public String update(){
        IHome home = new Home();
        String msg = null;
        try {
            home.bind(this);
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }
    /**
     * @return Returns the opId.
     */
    public String getOpId() {
        return opId;
    }

    /**
     * @param opId
     *            The opId to set.
     */
    public void setOpId(String opId) {
        this.opId = opId;
    }

    /**
     * @return Returns the poi.
     */
    public String getPoi() {
        return poi;
    }

    /**
     * @param poi
     *            The poi to set.
     */
    public void setPoi(String poi) {
        this.poi = poi;
    }

    /**
     * @return Returns the transDate.
     */
    public Date getTransDate() {
        return transDate;
    }

    /**
     * @param transDate
     *            The transDate to set.
     */
    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }
}
