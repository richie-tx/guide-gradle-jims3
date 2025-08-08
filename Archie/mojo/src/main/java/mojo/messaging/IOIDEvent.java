package mojo.messaging;

/**
 * Interface used for events that have OIDs.  This interface is used by the abstract base classes for commands. <pre>
 * Change History:
 *
 *  Author          	Date        Explanation
 * </pre>
 */
public interface IOIDEvent {
    void setOID(String anOID);

    String getOID();
}
