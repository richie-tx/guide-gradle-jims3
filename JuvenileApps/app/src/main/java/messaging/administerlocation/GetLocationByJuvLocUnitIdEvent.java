package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

public class GetLocationByJuvLocUnitIdEvent extends RequestEvent
{
	private String juvLocUnitId;

	public String getJuvLocUnitId()
	{
	    return juvLocUnitId;
	}

	public void setJuvLocUnitId(String juvLocUnitId)
	{
	    this.juvLocUnitId = juvLocUnitId;
	}
	
	
	
}
