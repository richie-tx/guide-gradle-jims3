//Source file: C:\\views\\framework_e2\\framework\\mojo-jims2\\mojo.java\\src\\mojo\\tools\\requirements\\ConfigurationAdapter.java

package mojo.tools.requirements;

import java.util.ArrayList;
import java.util.Iterator;

import reqpro.*;

import java.lang.reflect.*;

import mojo.km.config.ConnectionProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.config.CallbackProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.PropertyBundleProperties;
import rationalrose.*;

import java.util.ArrayList;
import java.util.Iterator;

import reqpro.*;
import java.lang.reflect.*;

import mojo.km.config.MojoProperties;
import mojo.km.config.PropertyBundleProperties;
public class ConfigurationAdapterPF
{
	private static _Package persistencePkg = null;
	private static _Project project = null;
	private static _Requirement req = null;
	private static MojoProperties mojoProp = null;
	private static String callBackSource = null;
	private static PropertyBundleProperties propBundle;
	private static _Package cdmPkg = null;
	private static _Package configPkg = null;

	/**
	@roseuid 4075779F024C
	 */
	public ConfigurationAdapterPF()
	{

	}

	/**
	* This method will find traceabilities on a requirement and look for a 
	* specific attribute and return its string value
	* @author mpatino
	* 
	@param childReq
	@param reqType
	@param attrType
	@return String
	@roseuid 4075761B030C
	 */
	public static String findChildReqTraceability(
		_Requirement childReq,
		String reqType,
		String attrType)
	{
		String returnValue = "0";
		try
		{
			childReq.Refresh(enumRequirementsWeights.eReqWeight_Heavy);
		}
		catch (Throwable y)
		{
		}
		_Relationships tracesTo = childReq.get_TracesTo();
		int reqcount = tracesTo.get_Count();
		tracesTo.MoveFirst();
		for (int loci = 0; loci < reqcount; loci++)
		{
			_Relationship rel = tracesTo.GetCurrentRelationship();
			int dispatchID = rel.IDispatch;
			if (dispatchID != 0)
			{
				_Requirement req1 =
					rel.get_RelatedRequirement(
						childReq,
						enumRequirementsWeights.eReqWeight_Medium);

				_ReqType rt = req1.get_ReqType();
				String rtValue = rt.get_Name();
				if (rtValue.equals(reqType))
				{
					_AttrValues attrValues = req1.get_AttrValues();

					_AttrValue attrValue =
						attrValues.get_Item(
							(Object) attrType,
							enumAttrValueLookups.eAttrValueLookup_Label);
					int attrDispatchID = attrValue.IDispatch;
					if (attrDispatchID != 0)
					{
						returnValue = attrValue.get_Text();
						break;
					}

				}

			}
			tracesTo.MoveNext();
		}
		return returnValue.toString();
	}

	/**
	 * This method will find the Event CDM that the Map requirement traces to,
	 * and return the name. Event name is an attribute on the callback.
	 * @param req - Map requirement
	 * @param attrType - attribute to look for
	 * @return eventName
	 *	@roseuid 4075766001E9
	 */
	public static String findEventName(_Requirement req, String attrType)
	{
		String returnValue = null;
		_Relationships tracesTo = req.get_TracesTo();
		int reqcount = tracesTo.get_Count();
		tracesTo.MoveFirst();
		for (int loci = 0; loci < reqcount; loci++)
		{
			_Relationship rel = tracesTo.GetCurrentRelationship();
			int dispatchID = rel.IDispatch;
			if (dispatchID != 0)
			{
				_Requirement req1 =
					rel.get_RelatedRequirement(
						req,
						enumRequirementsWeights.eReqWeight_Revs);

				_ReqType rt = req1.get_ReqType();
				String rtValue = rt.get_Name();
				if (rtValue.equals("Class Data Member"))
				{
					_AttrValues attrValues = req1.get_AttrValues();

					_AttrValue attrValue =
						attrValues.get_Item(
							(Object) "Type",
							enumAttrValueLookups.eAttrValueLookup_Label);
					int attrDispatchID = attrValue.IDispatch;
					if (attrDispatchID != 0)
					{
						String type = attrValue.get_Text();
						if (type.equals(attrType))
						{
							returnValue = req1.get_Name();
							System.out.println("Event name = " + returnValue);
							break;
						}
					}

				}

			}
			tracesTo.MoveNext();
		}
		return returnValue.toString();
	}

	/** This method will get the traceabilities for a Requirement and return the 
		* requirement name value.
	@param req
	@param reqType
	@param reqName
	@return String
	@roseuid 407575C10096
	 */
	public static String findReqTraceability(_Requirement req, String reqType)
	{
		String reqName = null;
		try
		{
			req.Refresh(enumRequirementsWeights.eReqWeight_Revs);
		}
		catch (Throwable y)
		{
		}
		_Relationships tracesTo = req.get_TracesTo();
		int reqcount = tracesTo.get_Count();
		tracesTo.MoveFirst();
		for (int loci = 0; loci < reqcount; loci++)
		{
			_Relationship rel = tracesTo.GetCurrentRelationship();
			int dispatchID = rel.IDispatch;
			if (dispatchID != 0)
			{

				_Requirement req1 =
					rel.get_RelatedRequirement(
						req,
						enumRequirementsWeights.eReqWeight_Revs);

				_ReqType rt = req1.get_ReqType();
				String rtValue = rt.get_Name();
				if (rtValue.equals(reqType))
				{
					reqName = req1.get_Name();
					break;
				}

			}
			tracesTo.MoveNext();
		}
		return reqName;
	}

	/**
	@param entity
	@roseuid 40575A7B0098
	 */
	public static void getRequirements(
		String packageName,
		String cdmPackageName)
	{
		//	_RootPackage root = project.GetRootPackage(true);
		_RootPackage root = project.GetRootPackage(false);

		String rootName = root.get_Name();

		root.LoadElements(reqpro.enumElementTypes.eElemType_Package);
		Integer packageKey =
			(Integer) root.get_Item(
				packageName,
				reqpro.enumPackageLookups.ePackageLookup_Name,
				reqpro.enumElementTypes.eElemType_Package);

		//Load the elements in the Persistence package
		persistencePkg = new _Package(packageKey);
		if (persistencePkg.IDispatch != 0)
		{
			//System.out.println(
			//	"Persistence Package IDispatch: " + persistencePkg.IDispatch);
			persistencePkg.LoadElements(
				reqpro.enumElementTypes.eElemType_Package);
		}
		else
		{
			//TODO add exception handling  
			System.out.println("Persistence Package not found");
		}

		Integer cdmKey =
			(Integer) persistencePkg.get_Item(
				cdmPackageName,
				reqpro.enumPackageLookups.ePackageLookup_Name,
				reqpro.enumElementTypes.eElemType_Package);

		//Load the Requirement elements in the Persistence CDM package
		cdmPkg = new _Package(cdmKey);
		if (cdmPkg.IDispatch != 0)
		{
			//System.out.println("configPkg: " + cdmPkg.IDispatch);
			
			
			cdmPkg.LoadElements(reqpro.enumElementTypes.eElemType_Requirement);

			int reqcount =
				cdmPkg.get_Count(reqpro.enumElementTypes.eElemType_Requirement);

			cdmPkg.MoveFirst();

			for (int loci = 0; loci < reqcount; loci++)
			{
				Integer reqi = (Integer) cdmPkg.GetCurrentElement();
				int ri = reqi.intValue();
				Integer keya =
					(Integer) cdmPkg.get_Item(
						reqi,
						reqpro.enumPackageLookups.ePackageLookup_Current,
						reqpro.enumElementTypes.eElemType_Requirement);
				//System.out.println("Dispatch ID : " + keya);
				_Requirement reqCDM = new _Requirement(keya);
				System.out.println("Parent Requirement : " + reqCDM.get_Name());
				_ReqType reqtype = reqCDM.get_ReqType();
				String reqTypeValue = reqtype.get_Name();
				String name = reqCDM.get_Name();

				//We're only interested in Connection and Map Requirements
				if (reqTypeValue.equals("Protocal Type"))
				{
                    _Relationships creq = reqCDM.get_Children();
                    
                     creq.MoveFirst();
					_Requirement child = creq.get_Requirement();
                    int chct =  creq.get_Count();
					for (int locx = 0; locx < chct; locx++)
	{				
	}
                    
                      
//					_AttrValues attrValues = reqCDM.get_AttrValues();
//
//					_AttrValue attrValue =
//						attrValues.get_Item(
//							(Object) "Type",
//							enumAttrValueLookups.eAttrValueLookup_Label);
//					int attrDispatchID = attrValue.IDispatch;
//					if (attrDispatchID != 0)
//					{
//
//						String attrType = attrValue.get_Text();
//						if (attrType.equals("entity"))
						{

							if (mojoProp.getEntityMap(name) == null)
							{
								EntityMappingProperties entityMap =
									new EntityMappingProperties();
								entityMap.setEntity(name);
								synchronizeMappings(entityMap, reqCDM);
								mojoProp.addEntityMapping(entityMap);
							}
						}
					}
				}
				//Get next ReqPro requirement
				cdmPkg.MoveNext();

			}
		
		
			//TODO add exception handling  
			//System.out.println("Requirements not found in processRequirements");
	}
	

	/**
	@roseuid 40586F8B03B9
	 */
	public static void synchronizeConfiguration()
	{
		synchronizePFConfiguration();

	}

	/**
	@param reqConfig
	@roseuid 407576B503D6
	 */
	public static void synchronizeConnection(_Requirement reqConfig)
	{
		ConnectionProperties conprop = new ConnectionProperties();
		_AttrValues attrValues = reqConfig.get_AttrValues();
		int count = attrValues.get_Count();
		//_ReqType reqtype = req.get_ReqType();
		//String reqTypeName = reqtype.get_Name();

		_AttrValue attrURL =
			attrValues.get_Item(
				(Object) "URL",
				enumAttrValueLookups.eAttrValueLookup_Label);
		conprop.setURL(attrURL.get_Text());
		_AttrValue attrPORT =
			attrValues.get_Item(
				(Object) "PORT",
				enumAttrValueLookups.eAttrValueLookup_Label);
		conprop.setPort(attrPORT.get_Text());

		_AttrValue attrSchema =
			attrValues.get_Item(
				(Object) "DB2 Schema Name",
				enumAttrValueLookups.eAttrValueLookup_Label);
		conprop.setDb2Schema(attrSchema.get_Text());
		System.out.println("DB2 Schema Name = " + conprop.getDb2Schema());

		_AttrValue attrRegion =
			attrValues.get_Item(
				(Object) "REGION",
				enumAttrValueLookups.eAttrValueLookup_Label);
		conprop.setRegion(attrRegion.get_Text());

		//Connection name is same as ReqPro requirement name
		String name = reqConfig.get_Name();
		conprop.setName(name);

		_AttrValue attrUSERID =
			attrValues.get_Item(
				(Object) "USERID",
				enumAttrValueLookups.eAttrValueLookup_Label);
		conprop.setUserID(attrUSERID.get_Text());

		_AttrValue attrPASSWORD =
			attrValues.get_Item(
				(Object) "PASSWORD",
				enumAttrValueLookups.eAttrValueLookup_Label);
		conprop.setPassword(attrPASSWORD.get_Text());
		//	System.out.println("PASSWORD =" + PSWD);	
		_AttrValue attrDRIVER =
			attrValues.get_Item(
				(Object) "DRIVER",
				enumAttrValueLookups.eAttrValueLookup_Label);
		conprop.setDriver(attrDRIVER.get_Text());
		//	System.out.println("DRIVER =" + driver);
		_AttrValue attrCONADAPTER =
			attrValues.get_Item(
				(Object) "CONNECTIONADAPTER",
				enumAttrValueLookups.eAttrValueLookup_Label);
		conprop.setConnectionAdapter(attrCONADAPTER.get_Text());
		//		System.out.println("CONADAPTER =" + conadd);
		_AttrValue attrPOOLING =
			attrValues.get_Item(
				(Object) "POOLING",
				enumAttrValueLookups.eAttrValueLookup_Label);
		conprop.setPooling(attrPOOLING.get_Text());
		//System.out.println("POOLING = " + attrPOOLING.get_Text());
		mojoProp.addConnection(conprop);
	}

	/**
	@roseuid 40757699014B
	 */
	public static void synchronizeConnections()
	{
		String configPackageName = propBundle.getProperty("ReqproConfigPckg");
		Integer configKey =
			(Integer) persistencePkg.get_Item(
				configPackageName,
				reqpro.enumPackageLookups.ePackageLookup_Name,
				reqpro.enumElementTypes.eElemType_Package);

		//Load the Requirement elements in the Persistence Configuration package
		configPkg = new _Package(configKey);
		if (configPkg.IDispatch != 0)
		{
			//System.out.println("configPkg: " + configPkg.IDispatch);
			configPkg.LoadElements(
				reqpro.enumElementTypes.eElemType_Requirement);

			int reqcount =
				configPkg.get_Count(
					reqpro.enumElementTypes.eElemType_Requirement);

			configPkg.MoveFirst();

			for (int loci = 0; loci < reqcount; loci++)
			{
				Integer reqi = (Integer) configPkg.GetCurrentElement();
				int ri = reqi.intValue();
				Integer keya =
					(Integer) configPkg.get_Item(
						reqi,
						reqpro.enumPackageLookups.ePackageLookup_Current,
						reqpro.enumElementTypes.eElemType_Requirement);
				//System.out.println("Dispatch ID : " + keya);
				_Requirement reqConfig = new _Requirement(keya);
				//System.out.println(
				//	"Parent Requirement : " + reqConfig.get_Name());

				_ReqType reqtype = reqConfig.get_ReqType();
				String reqTypeValue = reqtype.get_Name();
				String name = reqConfig.get_Name();

				//We're only interested in Connection Requirements
				if (reqTypeValue.equals("Connection"))
				{
					synchronizeConnection(reqConfig);
				}
				configPkg.MoveNext();
			}
		}
	}

	/**
	@param requirement
	@param cbProp
	@param fmProperties
	@roseuid 407574B503B2
	 */
	public static void synchronizeMapping(
		_Requirement childReq,
		CallbackProperties cbProp,
		FieldMappingProperties fmProperties)
	{

		if (callBackSource == null)
		{
			childReq.Refresh(enumRequirementsWeights.eReqWeight_Heavy);
			_Relationships tracesTo = childReq.get_TracesTo();
			int reqcount = tracesTo.get_Count();
			tracesTo.MoveFirst();
			for (int loci = 0; loci < reqcount; loci++)
			{
				_Relationship rel = tracesTo.GetCurrentRelationship();
				int dispatchID = rel.IDispatch;
				if (dispatchID != 0)
				{
					_Requirement req1 =
						rel.get_RelatedRequirement(
							childReq,
							enumRequirementsWeights.eReqWeight_Revs);

					_ReqType rt = req1.get_ReqType();
					String rtValue = rt.get_Name();

					if (rtValue.equals("Protocal Type"))
					{
						_Relationship lRel = req1.get_Parent(req1.IDispatch);
						_Requirement r =
							lRel.get_RelatedRequirement(req1, lRel.IDispatch);
						callBackSource = r.get_Name();
						//System.out.println("Relationship" + r.get_Name());

						//callBackSource =			
						//		findChildReqTraceability(
						//			childReq,
						//			"Protocal Type",
						//			"Data Source");

						cbProp.setSource(callBackSource);
					}
				}
			}
		}
		System.out.println("ChildReq Name = " + childReq.get_Name());

		//Change to set xml name from CDM name - mtp 06/01/04
		//fmProperties.setName(childReq.get_Name());
		fmProperties.setName(
			findReqTraceability(childReq, "Class Data Member"));

		fmProperties.setDataItemName(
			findReqTraceability(childReq, "Protocal Type"));

		//Change to set xml property name to map name - mtp 06/01/04		
		//fmProperties.setPropertyName(
		//findReqTraceability(childReq, "Class Data Member"));
		fmProperties.setPropertyName(childReq.get_Name());

		//For some parms(i.e. InsertProgramName, UpdateProgramName) there is
		//no traceability to a CDM. In these cases set PropertyName same as 
		//Map name. 
		//String propName = fmProperties.getPropertyName();		
		if (fmProperties.getName() == null)
		{
			fmProperties.setName(childReq.get_Name());
		}

		//TODO - how should association type be set? Should it be an attribute on
		// the map child requirement or should it be derived somehow based on the
		// CDM Type attribute?

		//fmProperties.setName(childReq.get_Name());
		//if (fmProperties.getAssociationType().equals(null)){
		//  fmProperties.setAssociationType("Assoc1");
		//} 
		fmProperties.setText(childReq.get_Text());
		//System.out.println("ChildReq Text = " + childReq.get_Text());
		//fmProperties.setParmIndex(
		//	findChildReqTraceability(childReq, "Protocal Type", "ParmIndex"));

		//System.out.println("Setting format and position from PF");
		fmProperties.setPosition(
			findChildReqTraceability(childReq, "Protocal Type", "Position"));
		//		TODO - put back after Jason gets field on PFs.
		//			fmProperties.setFormat(findChildReqTraceability(childReq, "Protocal Type", "Data Format"));
		fmProperties.setFormat("none");

		//Add card position  - 06/29/04 mtp.
		fmProperties.setCardPosition(
			findChildReqTraceability(childReq, "VSAM Card Image", "Position"));

		//Add appendFiller - 07/13/04 mtp.
		fmProperties.setAppendFiller(
			findChildReqTraceability(
				childReq,
				"VSAM Card Image",
				"AppendFiller"));

		fmProperties.setPropertyType(
			findChildReqTraceability(childReq, "Class Data Member", "Type"));

		//System.out.println("TESTING## PF length = " + findChildReqTraceability(childReq, "Protocal Type", "Length"));	 
		fmProperties.setLength(
			findChildReqTraceability(childReq, "Protocal Type", "Length"));
		if (fmProperties instanceof ParmMappingProperties)
		{
			System.out.println("parm mapping add - " + cbProp.getName());
			cbProp.addParmMapping((ParmMappingProperties) fmProperties);
			//System.out.println("Adding a parm mapping");
		}
		else if (fmProperties instanceof FieldMappingProperties)
		{
			cbProp.addFieldMap(fmProperties);
		}
	}

	/**
	@roseuid 4075989600A2
	 */
	public static void synchronizePFConfiguration()
	{
		mojoProp = MojoProperties.getInstance();
		mojoProp.clearEntityMappings();
		propBundle = PropertyBundleProperties.getInstance();

		if ((mojoProp == null) || (propBundle == null))
		{
			System.out.println("MojoProperties and/or PropertyBundle is null");
		}
		else
		{

			try
			{ // Initialize the Java2Com Environment
				com.ibm.bridge2java.OleEnvironment.Initialize();
			}
			catch (Exception e)
			{
				System.out.println(
					"Could not initialize Java2Com Environment.");
				e.printStackTrace();
			}
			boolean isComplete = false;
			for (int i = 0; i <= 30 && !isComplete; i++)
			{
				Application reqPro = null;
				try
				{
					String reqProDB = propBundle.getProperty("ReqproDB");

					//String reqProDB =
					//	"\\\\ratserver\\Data\\Architecture\\ReqPro\\test.rqs";
					String userid = propBundle.getProperty("ReqproUserId");
					//String userid = "eamundson";
					String password = propBundle.getProperty("ReqproPswd");
					//String password = "eric";
					System.out.println(reqProDB);
					String packageName =
						propBundle.getProperty("ReqproPersistencePckg");
					String CDMPackageName =
						propBundle.getProperty("ReqproCDMPckg");
					//String packageName = "Test Package";
					System.out.println("$$$$$Open project " + reqProDB);
					reqPro = (Application) new Application();
					project =
						reqPro.OpenProject(
							(Object) reqProDB,
							reqpro.enumOpenProjectOptions.eOpenProjOpt_RQSFile,
							userid,
							password,
							reqpro.enumProjectFlags.eProjFlag_Normal,
							reqpro
								.enumRelatedProjectOptions
								.eRelatedProjOption_ConnectAsSpecified);
                    packageName = "Supplementary Specifications";
                    CDMPackageName = "Data Requirements"; 
					getRequirements(packageName, CDMPackageName);

					synchronizeConnections();

					updateConfiguration();
					isComplete = true;
				}
				catch (Throwable e)
				{
					e.printStackTrace();
					project.CloseProject();
				}
			}
		}

	}

	/**
	 * update Mojo.xml
	 
	@roseuid 40586F360384
	 */
	public static void updateConfiguration()
	{
		mojoProp.saveMapping("JIMS2.xml");
	}

	/**
	@param entityMap
	@param req
	@roseuid 407598B20279
	 */
	public static void synchronizeMappings(
		EntityMappingProperties entityMap,
		_Requirement reqCDM)
	{

		//set attributes on entityMap
		//Trace to ReqPro CDM requirement to get it's name for our entity name
		//String name = findReqTraceability(req, "Class Data Member");
		//System.out.println("Entity name = " + name);

		//entityMap.setEntity(findEventName(req,"entity"));
		try
		{
			reqCDM.Refresh(enumRequirementsWeights.eReqWeight_Heavy);
		}
		catch (Throwable y)
		{
		}

		_Relationships tracesFrom = reqCDM.get_TracesFrom();
		int reqcount2 = tracesFrom.get_Count();
		tracesFrom.MoveFirst();
		for (int i = 0; i < reqcount2; i++)
		{
			_Relationship rel = tracesFrom.GetCurrentRelationship();
			int dispatchID = rel.IDispatch;
			if (dispatchID != 0)
			{
				req =
					rel.get_RelatedRequirement(
						reqCDM,
						enumRequirementsWeights.eReqWeight_Medium);

				_ReqType rt = req.get_ReqType();
				String rtValue = rt.get_Name();

				//System.out.println("Rectype = " + rtValue);
				if (rtValue.equals("Protocal Type"))
				{
					System.out.println("PF= " + req.get_Name());
					_AttrValues attrValues = req.get_AttrValues();
					_AttrValue attrMapComplete =
						attrValues.get_Item(
							(Object) "Mapping Complete",
							enumAttrValueLookups.eAttrValueLookup_Label);

					String mapComplete = attrMapComplete.get_Text();
					if (!mapComplete.equals("Yes"))
					{
					}
					else
					{

						_AttrValue attrMapType =
							attrValues.get_Item(
								(Object) "Map Type",
								enumAttrValueLookups.eAttrValueLookup_Label);

						String mapType = attrMapType.get_Text();
						CallbackProperties prop = null;
						callBackSource = null;
						EventQueryProperties prop1 = null;
						if (mapType.equals("Update"))
						{
							SaveCallbackProperties prop2 =
								new SaveCallbackProperties();

							_AttrValue attrWhereClause =
								attrValues.get_Item(
									(Object) "WhereClause",
									enumAttrValueLookups
										.eAttrValueLookup_Label);

							prop2.setWhereClause(attrWhereClause.get_Text());
							prop = (CallbackProperties) prop2;
						}
						else if (mapType.equals("Retrieve"))
						{
							prop1 = new EventQueryProperties();
						}
						if (prop1 != null)
						{
							//prop1.setEventName(req.get_Name());
							prop1.setEventName(findEventName(req, "event"));

							_AttrValue attrWhereClause =
								attrValues.get_Item(
									(Object) "WhereClause",
									enumAttrValueLookups
										.eAttrValueLookup_Label);

							prop1.setWhereClause(attrWhereClause.get_Text());
							prop = (CallbackProperties) prop1;

						}

						if (prop != null)
						{
							String connName = null;

							//Trace to ReqPro CONN requirement to get connection name for the CallBack
							prop.setConnectionName(
								findReqTraceability(req, "Connection"));
							//System.out.println("Req name = " + req.get_Name());
							prop.setName(req.get_Name());

							_AttrValue mappingClassName =
								attrValues.get_Item(
									(Object) "MappingClassName",
									enumAttrValueLookups
										.eAttrValueLookup_Label);
							prop.setMappingClassName(
								mappingClassName.get_Text());

							_AttrValue mappingMethodName =
								attrValues.get_Item(
									(Object) "MappingMethodName",
									enumAttrValueLookups
										.eAttrValueLookup_Label);
							prop.setMappingMethodName(
								mappingMethodName.get_Text());

							//Add stored procedure name and cics program name - 06/09/04 mtp.
							_AttrValue storedProcedureName =
								attrValues.get_Item(
									(Object) "StoredProcedureName",
									enumAttrValueLookups
										.eAttrValueLookup_Label);
							prop.setStoredProcedureName(
								storedProcedureName.get_Text());

							_AttrValue cicsProgramName =
								attrValues.get_Item(
									(Object) "CicsProgramName",
									enumAttrValueLookups
										.eAttrValueLookup_Label);
							prop.setCicsProgramName(cicsProgramName.get_Text());

							//Added vsam record sequence number - 06/28/04 mtp.
							_AttrValue seqNum =
								attrValues.get_Item(
									(Object) "SequenceNumber",
									enumAttrValueLookups
										.eAttrValueLookup_Label);
							prop.setSequenceNumber(seqNum.get_Text());

							//Added vsam subclass sequence number - 07/15/04 mtp.
							_AttrValue subclassSeqNum =
								attrValues.get_Item(
									(Object) "SubclassSequence",
									enumAttrValueLookups
										.eAttrValueLookup_Label);
							prop.setSubclassSequence(subclassSeqNum.get_Text());

							//Added vsam subclass record type - 07/15/04 mtp.
							_AttrValue subclassRecord =
								attrValues.get_Item(
									(Object) "SubclassRecordType",
									enumAttrValueLookups
										.eAttrValueLookup_Label);
							prop.setSubclassRecord(subclassRecord.get_Text());

							//Add Tranid and filename - 06/15/04 rac.
							_AttrValue tranId =
								attrValues.get_Item(
									(Object) "CicsTransaction",
									enumAttrValueLookups
										.eAttrValueLookup_Label);
							prop.setTranId(tranId.get_Text());
							_AttrValue fileName =
								attrValues.get_Item(
									(Object) "FileName",
									enumAttrValueLookups
										.eAttrValueLookup_Label);
							prop.setFileName(fileName.get_Text());

							//get child Map requirements
							_Relationships rs = req.get_Children();
							int reqcount = rs.get_Count();
							//System.out.println("Child Map Requirements Count : " + reqcount);

							rs.MoveFirst();
							for (int loci = 0; loci < reqcount; loci++)
							{
								_Relationship rp = rs.GetCurrentRelationship();
								//Child entity requirements will consist of fields and parms
								_Requirement childReq =
									rp.get_RelatedRequirement(
										req,
										enumRequirementsWeights
											.eReqWeight_Medium);
								String childReqName = childReq.get_Name();
								//System.out.println(
								//	"Childreq Name = " + childReqName);
								_ReqType childReqtype = childReq.get_ReqType();
								String childReqTypeValue =
									childReqtype.get_Name();
								//System.out.println(
								//	"Childreq Type = " + childReqTypeValue);
								_AttrValues childAttrValues =
									childReq.get_AttrValues();
								_AttrValue childAttrMapType =
									childAttrValues.get_Item(
										(Object) "ChildMapType",
										enumAttrValueLookups
											.eAttrValueLookup_Label);
								String childMapType =
									childAttrMapType.get_Text();
								FieldMappingProperties fmProp = null;
								if (childMapType.equals("Parm"))
								{
									System.out.println(
										"creating parm mapping  "
											+ childReqName);
									fmProp = new ParmMappingProperties();

								}
								else if (childMapType.equals("Field"))
								{
									fmProp = new FieldMappingProperties();

								}

								//create SaveCallBackProperties or EventQueryProperty and add to entityMap
								if (fmProp != null)
								{
									_AttrValue childAttrParmIndex =
										childAttrValues.get_Item(
											(Object) "ParmIndex",
											enumAttrValueLookups
												.eAttrValueLookup_Label);
									String childParmIndex =
										childAttrParmIndex.get_Text();
									fmProp.setParmIndex(childParmIndex);

									//Format will be retrieved from PF from now on - mtp 07/27/04
									//								_AttrValue childAttrFormat =
									//									childAttrValues.get_Item(
									//										(Object) "Format",
									//										enumAttrValueLookups
									//											.eAttrValueLookup_Label);
									//								String childFormat = childAttrFormat.get_Text();
									//								fmProp.setFormat(childFormat);

									_AttrValue assocType =
										childAttrValues.get_Item(
											(Object) "AssociationType",
											enumAttrValueLookups
												.eAttrValueLookup_Label);
									String lAssociationType =
										assocType.get_Text();
									fmProp.setAssociationType(lAssociationType);

									_AttrValue codeTableName =
										childAttrValues.get_Item(
											(Object) "CodeTableName",
											enumAttrValueLookups
												.eAttrValueLookup_Label);
									String lCodeTableName =
										codeTableName.get_Text();
									fmProp.setCodeTableName(lCodeTableName);

									//TODO Put back after jason changes PF load
									//fmProp.setPadChar(findChildReqTraceability(childReq, "Protocal Type", "Pad Char"));
									fmProp.setPadChar("none");
									synchronizeMapping(childReq, prop, fmProp);
								}

								rs.MoveNext();
							}
						}
						getMapTracability(prop, entityMap);
						if (prop instanceof SaveCallbackProperties)
						{
							entityMap.addSaveCallback(
								(SaveCallbackProperties) prop);
						}
						if (prop instanceof EventQueryProperties)
						{
							entityMap.addQueryCallbacks(
								(EventQueryProperties) prop);
						}
						//After all save and query callbacks have been created and added to the 
						//enclosing EntityMapping, add to Mojo.xml
					}
				}
			}
			//Get the next Map requirement related to current CDM entity.
			tracesFrom.MoveNext();
		}
	}

	/**
	@roseuid 405876800374
	Method generated by Rose - not used. Functionality is defined within
	the	Update Entity Mapping Requirements activity but will performed
	within ReqPro.
	 */
	public void updateParmInformation()
	{

	}
	/**
		@roseuid 404F437802DB
		Method generated by Rose - not used. Functionality is defined within
		the	Update Entity Mapping Requirements activity but will performed
		within ReqPro.
		 */
	public void getSavedMappings()
	{

	}

	/**
	@param requirementMap
	@roseuid 4032478002FA
	Method generated by Rose - not used. Functionality is defined within
	the	Update Entity Mapping Requirements activity but will performed
	within ReqPro.
	 */
	public void saveFieldMapping(Requirement requirementMap)
	{

	}

	/**
	@param requirementMap
	@roseuid 40324CD60222
	Method generated by Rose - not used. Functionality is defined within
	the	Update Entity Mapping Requirements activity but will performed
	within ReqPro.
	 */
	public void saveParmInformation(Requirement requirementMap)
	{

	}
	/**
		@roseuid 40322FF601FB
		Method generated by Rose - not used. Functionality is defined within
		the	Update Entity Mapping Requirements activity but will performed
		within ReqPro.
		 */
	public void getEntities()
	{

	}

	/**
	@param entity
	@roseuid 40461A130149
	Method generated by Rose - not used. Functionality is defined within
	the	Update Entity Mapping Requirements activity but will performed
	within ReqPro.
	 */
	public void getEntity(String entity)
	{

	}

	/**
	@param entity
	@roseuid 403240DC021A
	Method generated by Rose - not used. Functionality is defined within
	the	Update Entity Mapping Requirements activity but will performed
	within ReqPro.
	 */
	public void getEntityAttributes(String entity)
	{

	}

	/**
	@param fieldName
	@roseuid 403261310294
	Method generated by Rose - not used. Functionality is defined within
	the	Update Entity Mapping Requirements activity but will performed
	within ReqPro.
	 */
	public void getFieldMetadata(String fieldName)
	{

	}

	/**
	@param parmName
	@roseuid 40324A840214
	Method generated by Rose - not used. Functionality is defined within
	the	Update Entity Mapping Requirements activity but will performed
	within ReqPro.
	 */
	public void getParmDetails(String parmName)
	{

	}

	/**
	@roseuid 403273A10056
	Method generated by Rose - not used. Functionality is defined within
	the	Update Entity Mapping Requirements activity but will performed
	within ReqPro.
	 */
	public void getParms()
	{

	}

	/**
	@param recordType
	@roseuid 40326004018C
	Method generated by Rose - not used. Functionality is defined within
	the	Update Entity Mapping Requirements activity but will performed
	within ReqPro.
	 */
	public void getRecordFields(String recordType)
	{

	}

	/**
	@param source
	@roseuid 40325E9701B0
	Method generated by Rose - not used. Functionality is defined within
	the	Update Entity Mapping Requirements activity but will performed
	within ReqPro.
	 */
	public void getRecordTypes(String source)
	{

	}

	/**
	@param connectionName
	@roseuid 40575627013F
	Method generated by Rose - not used. Functionality is defined within
	the	Update Entity Mapping Requirements activity but will performed
	within ReqPro.
	 */
	public void getRequirement(String connectionName)
	{

	}
	/**
		@param requirementMap
		@roseuid 403240950344
		Method generated by Rose - not used. Functionality is defined within
		the	Update Entity Mapping Requirements activity but will performed
		within ReqPro.
		 */
	public void createMap(Requirement requirementMap)
	{

	}

	/**
	@roseuid 403271EB016E
	Method generated by Rose - not used. Functionality is defined within
	the	Update Entity Mapping Requirements activity but will performed
	within ReqPro.
	 */
	public void createMap()
	{

	}
	public static void getMapTracability(
		CallbackProperties prop,
		EntityMappingProperties entityMap)
	{
		_Relationships tracesTo = req.get_TracesTo();
		int reqcount = tracesTo.get_Count();
		tracesTo.MoveFirst();
		for (int loci = 0; loci < reqcount; loci++)
		{
			_Relationship rel = tracesTo.GetCurrentRelationship();
			int dispatchID = rel.IDispatch;
			if (dispatchID != 0)
			{
				_Requirement req1 =
					rel.get_RelatedRequirement(
						req,
						enumRequirementsWeights.eReqWeight_Medium);

				_ReqType rt = req1.get_ReqType();
				String rtValue = rt.get_Name();
				if (rtValue.equals("Map"))
				{
					System.out.println("Map=" + req1.get_Name());
					_Relationships rs = req1.get_Children();
					int reqcount2 = rs.get_Count();
					//System.out.println("Child Map Requirements Count : " + reqcount);

					rs.MoveFirst();
					for (int loci2 = 0; loci2 < reqcount2; loci2++)
					{
						_Relationship rp = rs.GetCurrentRelationship();
						//Child entity requirements will consist of fields and parms
						_Requirement childReq =
							rp.get_RelatedRequirement(
								req1,
								enumRequirementsWeights.eReqWeight_Heavy);
						String childReqName = childReq.get_Name();
						System.out.println(
							"Childreq Name from map  = " + childReqName);
						if (!childReqName.equals("Function"))
						{

							_ReqType childReqtype = childReq.get_ReqType();
							String childReqTypeValue = childReqtype.get_Name();
							System.out.println(
								"Childreq Type = " + childReqTypeValue);
							_AttrValues childAttrValues =
								childReq.get_AttrValues();
							_AttrValue childAttrMapType =
								childAttrValues.get_Item(
									(Object) "ChildMapType",
									enumAttrValueLookups
										.eAttrValueLookup_Label);
							String childMapType = childAttrMapType.get_Text();
							FieldMappingProperties fmProp = null;
							//if (childMapType.equals("Parm"))
							//{
							//	fmProp = new ParmMappingProperties();

							//}
							if (childMapType.equals("Field"))
							{
								fmProp = new FieldMappingProperties();

							}

							//create SaveCallBackProperties or EventQueryProperty and add to entityMap
							if (fmProp != null)
							{
								_AttrValue childAttrParmIndex =
									childAttrValues.get_Item(
										(Object) "ParmIndex",
										enumAttrValueLookups
											.eAttrValueLookup_Label);
								String childParmIndex =
									childAttrParmIndex.get_Text();
								fmProp.setParmIndex(childParmIndex);

								//Format will be retrieved from PF from now on - mtp 07/27/04
								//								_AttrValue childAttrFormat =
								//									childAttrValues.get_Item(
								//										(Object) "Format",
								//										enumAttrValueLookups
								//											.eAttrValueLookup_Label);
								//								String childFormat = childAttrFormat.get_Text();
								//								fmProp.setFormat(childFormat);

								_AttrValue assocType =
									childAttrValues.get_Item(
										(Object) "AssociationType",
										enumAttrValueLookups
											.eAttrValueLookup_Label);
								String lAssociationType = assocType.get_Text();
								fmProp.setAssociationType(lAssociationType);

								_AttrValue codeTableName =
									childAttrValues.get_Item(
										(Object) "CodeTableName",
										enumAttrValueLookups
											.eAttrValueLookup_Label);
								String lCodeTableName =
									codeTableName.get_Text();
								fmProp.setCodeTableName(lCodeTableName);

								//								  TODO Put back after jason changes load
								//																_AttrValue padChar =
								//																	childAttrValues.get_Item(
								//																		(Object) "padChar",
								//																		enumAttrValueLookups
								//																			.eAttrValueLookup_Label);
								//																String lPadChar = padChar.get_Text();
								//																fmProp.setPadChar(lPadChar);

								synchronizeMapping(childReq, prop, fmProp);
							}
						}

						rs.MoveNext();
					}
					//					if (prop instanceof SaveCallbackProperties)
					//					{
					//						entityMap.addSaveCallback(
					//							(SaveCallbackProperties) prop);
					//					}
					//					if (prop instanceof EventQueryProperties)
					//					{
					//						entityMap.addQueryCallbacks(
					//							(EventQueryProperties) prop);
					//					}
					//					//After all save and query callbacks have been created and added to the 
					//					//enclosing EntityMapping, add to Mojo.xml
					//					mojoProp.addEntityMapping(entityMap);
				}
			}
			tracesTo.MoveNext();
		}
		//Get the next Map requirement related to current CDM entity.

	}

}
