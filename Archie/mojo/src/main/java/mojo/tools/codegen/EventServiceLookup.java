package mojo.tools.codegen;

public class EventServiceLookup
{
	private String name;

	private String eventName;

	private String serviceName;

	public String getEventName()
	{
		return eventName;
	}

	public void setEventName(String eventName)
	{
		this.eventName = eventName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("<bean id=\"");
		builder.append(this.name);
		builder.append("\" class=\"");
		builder.append(this.eventName);
		builder.append("\" scope=\"prototype\">");
		builder.append("\n\t<property name=\"topic\" value=\"");
		builder.append(this.name);
		builder.append(".SERVICE");
		builder.append("\"/>");
		builder.append("\n</bean>");
		builder.append("\n<bean id=\"");
		builder.append(this.name);
		builder.append(".SERVICE");
		builder.append("\"");
		builder.append(" class=\"");
		builder.append(this.serviceName);
		builder.append("\" scope=\"singleton\" />");
		return builder.toString();
	}
}
