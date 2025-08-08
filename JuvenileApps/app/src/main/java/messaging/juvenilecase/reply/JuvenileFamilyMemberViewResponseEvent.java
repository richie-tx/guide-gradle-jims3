/*
 * Created on Jul 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JuvenileFamilyMemberViewResponseEvent extends ResponseEvent implements Comparable{

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	
	private String juvenileNum;
	
	 private String memberNum;

	    private String firstName;

	    private String lastName;

	    private String middleName;

	    private boolean isDeceased;
	    
	    private String juvRelationId;
	    
	    private String juvRelation;
	    
	  
	    public int compareTo(Object o) {
			if(o==null){
				return 1;
			}
			if(o instanceof JuvenileFamilyMemberViewResponseEvent){
				JuvenileFamilyMemberViewResponseEvent myEvt=(JuvenileFamilyMemberViewResponseEvent)o;
				if(myEvt.getFullName()==null){
					return 1;
				}
				else if(this.getFullName()==null){
					return -1;
				}
				else{
					return this.getFullName().compareTo(myEvt.getFullName());
				}
			}
			else
				return 1;
		}
	    
	    
	    public String getFullName(){
			if(firstName!=null && !firstName.equals("")){
				return lastName + ", " + firstName;
			}
			else{
				return lastName;
			}
		}

	    public String getFullNameWithRel(){
	    	String fnr = "";
			if(firstName!=null && !firstName.equals("")){
				fnr = lastName + ", " + firstName;
			}
			else{
				fnr = lastName;
			}
			if (fnr != "" &&  !"".equals(this.getJuvRelation() ) ){
				fnr += " (" + this.getJuvRelation() + ")";
			}
			return fnr;
		}

		/**
		 * @return Returns the firstName.
		 */
		public String getFirstName() {
			return firstName;
		}
		/**
		 * @param firstName The firstName to set.
		 */
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		/**
		 * @return Returns the isDeceased.
		 */
		public boolean isDeceased() {
			return isDeceased;
		}
		/**
		 * @param isDeceased The isDeceased to set.
		 */
		public void setDeceased(boolean isDeceased) {
			this.isDeceased = isDeceased;
		}
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
		/**
		 * @return Returns the juvRelation.
		 */
		public String getJuvRelation() {
			return juvRelation;
		}
		/**
		 * @param juvRelation The juvRelation to set.
		 */
		public void setJuvRelation(String juvRelation) {
			this.juvRelation = juvRelation;
		}
		/**
		 * @return Returns the juvRelationId.
		 */
		public String getJuvRelationId() {
			return juvRelationId;
		}
		/**
		 * @param juvRelationId The juvRelationId to set.
		 */
		public void setJuvRelationId(String juvRelationId) {
			this.juvRelationId = juvRelationId;
		}
		/**
		 * @return Returns the lastName.
		 */
		public String getLastName() {
			return lastName;
		}
		/**
		 * @param lastName The lastName to set.
		 */
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	/**
	 * @return Returns the memberNum.
	 */
	public String getMemberNum() {
		return memberNum;
	}
	/**
	 * @param memberNum The memberNum to set.
	 */
	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}
		/**
		 * @return Returns the middleName.
		 */
		public String getMiddleName() {
			return middleName;
		}
		/**
		 * @param middleName The middleName to set.
		 */
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
}
