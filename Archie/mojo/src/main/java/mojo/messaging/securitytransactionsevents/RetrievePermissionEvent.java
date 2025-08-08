package mojo.messaging.securitytransactionsevents;

import mojo.km.messaging.ResponseEvent;
import mojo.km.security.AccessLevel;

/**
 * Responsible for housing data that will be returned to boundry command RetrievePermissionsCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {AB96B566-F7BE-4BE5-8F14-CE3DB7B18E26}
 */
public class RetrievePermissionEvent extends ResponseEvent implements Comparable {
	/** @modelguid {F385B494-ACAE-477F-B2B3-2F0F2EA648B7} */
	private String featureList = "";
	/** @modelguid {9960C163-541A-4465-87BC-2648CC325578} */
	private String serviceName = "";
	/** @modelguid {99466D63-C611-42B2-8530-8352956CB492} */
	private int serviceNameAccessLevel = AccessLevel.NONE.getCompareWeight();
	/** @modelguid {3644EA69-B3DF-4C3A-A406-1E313A692363} */
	private String propertyName = "";
	/** @modelguid {CF91F06C-CBE8-4D8D-B533-E6E06F6D336A} */
	private int propertyNameAccessLevel = AccessLevel.NONE.getCompareWeight();

	/** @modelguid {B33B0AA4-5930-4054-9B04-3F55B2FADD58} */
    public RetrievePermissionEvent() { }

	/** @modelguid {2DB674C6-BB73-43A5-9F63-8C56019E51D9} */
	public String getFeatureList()
	{
		return this.featureList;
	}

	/** @modelguid {39516ED7-BE52-4E57-925A-F535B12BC28A} */
	public void setFeatureList(String featureList)
	{
		this.featureList = featureList;
	}

	/** @modelguid {E051923C-CC22-4C62-B9A4-22818D1E19C3} */
	public String getServiceName()
	{
		return this.serviceName;
	}

	/** @modelguid {E48AE947-EE3C-4597-93DF-993B91A63A7A} */
	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	/** @modelguid {39FE1C7C-2C3A-4FFA-80CD-6162C1C2252E} */
	public int getServiceNameAccessLevel()
	{
		return this.serviceNameAccessLevel;
	}

	/** @modelguid {B29371F1-87D3-4AC3-A549-F1C54267136A} */
	public void setServiceNameAccessLevel(int serviceNameAccessLevel)
	{
		this.serviceNameAccessLevel = serviceNameAccessLevel;
	}

	/** @modelguid {E9DAAC71-BEDB-49B2-B45C-88991F7F64FF} */
	public void setServiceNameAccessLevel(AccessLevel serviceNameAccessLevel)
	{
		this.serviceNameAccessLevel = serviceNameAccessLevel.getCompareWeight();
	}
	
	/** @modelguid {7C7A9BD1-5E25-4A6E-A5E7-25EE470DD8D2} */
	public String getPropertyName()
	{
		return this.propertyName;
	}

	/** @modelguid {7021ED82-F09C-4EFB-A62D-4C7FB659DD44} */
	public void setPropertyName(String propertyName)
	{
		this.propertyName = propertyName;
	}

	/** @modelguid {8D3BDB0F-103A-4204-BF11-29A45A728205} */
	public int getPropertyNameAccessLevel()
	{
		return this.propertyNameAccessLevel;
	}

	/** @modelguid {08788D8E-C0CD-4A12-8910-8F05EFBCA849} */
	public void setPropertyNameAccessLevel(int propertyNameAccessLevel)
	{
		this.propertyNameAccessLevel = propertyNameAccessLevel;
	}

	/** @modelguid {F8BF22CF-A357-449A-86F8-CF137582B4DF} */
	public void setPropertyNameAccessLevel(AccessLevel propertyNameAccessLevel)
	{
		this.propertyNameAccessLevel = propertyNameAccessLevel.getCompareWeight();
	}

	/** @modelguid {9A2E5B42-1B82-432C-8158-9797050141DA} */
	public int compareTo(Object obj)
    {
        RetrievePermissionEvent n = (RetrievePermissionEvent)obj;

		StringBuffer compareName = new StringBuffer();
		compareName.append(featureList);
		compareName.append(serviceName);
		compareName.append(propertyName);

		StringBuffer compareName2 = new StringBuffer();
		compareName2.append(n.getFeatureList());
		compareName2.append(n.getServiceName());
		compareName2.append(n.getPropertyName());

        return compareName.toString().compareTo(compareName2.toString());
    }
}
