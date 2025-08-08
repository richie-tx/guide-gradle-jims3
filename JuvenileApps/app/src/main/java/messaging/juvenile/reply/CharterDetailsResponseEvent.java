package messaging.juvenile.reply;

import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

public class CharterDetailsResponseEvent extends ResponseEvent{
	private Collection juvenileCharterGEDs;
    private Collection juvenileCharterVEPs;
    private Collection juvenileCharterPostReleases;
	public Collection getJuvenileCharterGEDs() {
		return juvenileCharterGEDs;
	}
	public void setJuvenileCharterGEDs(Collection juvenileCharterGEDs) {
		this.juvenileCharterGEDs = juvenileCharterGEDs;
	}
	public Collection getJuvenileCharterVEPs() {
		return juvenileCharterVEPs;
	}
	public void setJuvenileCharterVEPs(Collection juvenileCharterVEPs) {
		this.juvenileCharterVEPs = juvenileCharterVEPs;
	}
	public Collection getJuvenileCharterPostReleases() {
		return juvenileCharterPostReleases;
	}
	public void setJuvenileCharterPostReleases(
			Collection juvenileCharterPostReleases) {
		this.juvenileCharterPostReleases = juvenileCharterPostReleases;
	}
}
