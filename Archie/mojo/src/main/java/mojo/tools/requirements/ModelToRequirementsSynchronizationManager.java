//Source file: C:\\views\\framework_e2\\framework\\mojo-jims2\\mojo.java\\src\\mojo\\tools\\requirements\\ModelToRequirementsSynchronizationManager.java

package mojo.tools.requirements;
import rationalrose.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import reqpro.*;
import java.lang.reflect.*;

import mojo.km.config.MojoProperties;
import mojo.km.config.PropertyBundleProperties;
import java.lang.Thread;

public class ModelToRequirementsSynchronizationManager
{
	private static PropertyBundleProperties propBundle;
	private static _Package persistencePkg = null;
	private static _Package cdmPkg = null;
	private static _Project project = null;
	private static Application reqPro = null;
	private static IRoseCategoryCollection units = null;

	/**
	@roseuid 407BEEC90252
	 */
	public ModelToRequirementsSynchronizationManager()
	{

	}

	public static void initReqPro()
	{
		//Add comments fully explaining why the heck we're going thru Reqpro more than once - 
		//Eric made us do it!
		try
		{

			propBundle = PropertyBundleProperties.getInstance();
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
			String cdmPackageName = propBundle.getProperty("ReqproCDMPckg");
			//String packageName = "Test Package";
			reqPro = (Application) new Application();
			System.out.println(
				"userId = "
					+ userid
					+ " reqProDb = "
					+ reqProDB
					+ " Password = "
					+ password
					+ " packageName ="
					+ packageName
					+ " cdmPackageName = "
					+ cdmPackageName);
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

			// get the root package
			_RootPackage root = project.GetRootPackage(false);
			String rootName = root.get_Name();

			//Load the elements in the root package
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
				//System.out.println("Persistence Package IDispatch: " + persistencePkg.IDispatch);
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
				//System.out.println("cdmPkg" +cdmPkg.IDispatch);
				cdmPkg.LoadElements(
					reqpro.enumElementTypes.eElemType_Requirement);
			}
			else
			{
				//TODO add exception handling  
				System.out.println("CDM Requirements not found");
			}
		}
		catch (Exception e)
		{
			System.out.println(
				"Exception occurred in RoseReqProSyncher: " + e.getMessage());
			e.printStackTrace();

		}

	}

	/**
	@param url
	@roseuid 40586D090333
	 */
	public static void synchronize()
	{
		try
		{
			// Initialize the Java2Com Environment
			com.ibm.bridge2java.OleEnvironment.Initialize();
		}
		catch (Exception e)
		{
			System.out.println("Could not initialize Java2Com.");
			e.printStackTrace();
		}
		IRoseModel roseModel = null;
		RoseApplication roseAppl = null;
		initReqPro();
		try
		{

			String model = propBundle.getProperty("RoseDB");
			//"c:\\\\views\\arch_e2\\Arch\\Framework\\RefApp_Model\\JIMS2ReferenceApplication.mdl";
			//"c:\\\\Copy of dgibler_PetStore\\PetStore_Model\\design\\petstore_DESIGN.mdl";
			//"c:\\\\views\\arch_e2\\Arch\\JIMS2\\model\\SoftwareArchitectureDocumentCopy.mdl";

			//String model = "c:\\\\views\\arch_e2\\Arch\\JIMS2\\model\\Software Architecture Document.mdl";
			roseAppl = new RoseApplication();
			roseModel = roseAppl.OpenModel(model);
			int roseDispatchID = roseAppl.IDispatch;
			//System.out.println("Model Name : " + roseModel.get_Name());
			roseModel.Load();
			IRoseCategory roseCategory = roseModel.get_RootCategory();
			//System.out.println("Category Name : " + roseCategory.get_Name());
			IRoseSubsystem roseSubsys = roseModel.get_RootSubsystem();
			
			

			//units is defined at class level
			units = roseCategory.get_Categories();
			//System.out.println("Number of Subunits : " + units.get_Count());
			int dispatchID = units.IDispatch;
			//System.out.println("Dispatch ID : " + dispatchID);
			if (dispatchID != 0)
			{

				//Get events and entities from model
				String pkg1 = "Design Model";
				String pkg2 = "Application Layer";
				navigateModel(pkg1, pkg2);
				//Get entities from model
				//pkg1 = "Analysis Model";
				//pkg2 = "Domain Model";
				//navigateModel(pkg1, pkg2);

			}
			roseModel.Unload();
			roseAppl.Exit();
			roseAppl.ReleaseObject(roseDispatchID);
			reqPro.CloseServer();
		}
		catch (Exception e)
		{
			System.out.println(
				"Exception occurred in RoseReqProSyncher: " + e.getMessage());
			e.printStackTrace();
			roseModel.Unload();
			roseAppl.Exit();
			roseAppl.ReleaseObject();

		}
	}

	/**
	@param  roseClass
	@return boolean
	@roseuid 407AE3990254
	 */
	public static String checkClassInheritance(IRoseClass roseClass)
	{
		System.out.println(
			"Checking class inheritance for " + roseClass.get_Name());
		String name = "noname";
		IRoseInheritRelationCollection irCollection =
			roseClass.GetInheritRelations();
		if (irCollection.IDispatch != 0)
		{
			short ircount = irCollection.get_Count();
			for (short loci = 1; loci <= ircount; loci++)
			{
				IRoseInheritRelation rel = irCollection.GetAt(loci);

				if (rel.IDispatch != 0)
				{
					IRoseClass parent = rel.GetSupplierClass();
					name = rel.get_SupplierName();
					System.out.println("Supplier name = " + name);
					//System.out.println(
					//	"Found inheritence rel for " + rel.get_SupplierName());
					if (name.equals("RequestEvent")
						|| name.equals("PersistentObject"))
					{
						//System.out.println("Found parent = " + name);
						return name;
					}
					else if (parent.IDispatch != 0)
					{
						return checkClassInheritance(parent);

					}
				}

			}
		}

		return name;
	}

	public static ArrayList getParentClass(IRoseClass roseClass)
	{
		System.out.println(
			"Checking parent class inheritance for " + roseClass.get_Name());
		String name = "noname";
		IRoseClass parent = null;
		ArrayList parentList = new ArrayList();
		IRoseInheritRelationCollection irCollection =
			roseClass.GetInheritRelations();
		if (irCollection.IDispatch != 0)
		{
			short ircount = irCollection.get_Count();
			for (short loci = 1; loci <= ircount; loci++)
			{
				IRoseInheritRelation rel = irCollection.GetAt(loci);

				if (rel.IDispatch != 0)
				{
					parent = rel.GetSupplierClass();
					parentList.add(parent);

				}

			}
		}

		return parentList;
	}

	/**
	@param parentReqNum
	@param entityName
	@param type
	@roseuid 407AE3B900D3
	 */
	public static void createChildRequirement(
		String parentReqNum,
		String entityName,
		String type)
	{

		String text = "";
		String reqType = "Class Data Member";
		String versionLabel = "1.0";
		String versionReason = "Reason";
		Object parentReqLookupValue = "";
		int parentReqLookupType = 0;

		_Requirement childReq =
			project.CreateRequirement(
				entityName,
				text,
				reqType,
				reqpro.enumReqTypesLookups.eReqTypesLookups_Name,
				versionLabel,
				versionReason,
				parentReqNum,
				reqpro.enumRequirementLookups.eReqLookup_Tag);
		if (childReq.IDispatch != 0)
		{

			childReq.Save();
			//call do attrib
			//System.out.println("Chil'en created " + entityName);
			updateAttributes(childReq, type);

		}

	}

	/**
	@param category
	@roseuid 407AE3D703A8
	 */
	public static ArrayList getClassList(IRoseCategory category)
	{
		System.out.println("Category Name : " + category.get_Name());
		ArrayList classCategory = new ArrayList();
		
		//Get the classes for this package
		IRoseClassCollection objs = category.get_Classes();
		
		
			IRoseCategoryCollection categories = category.get_Categories();
			for (int catIndex = 1;
				catIndex <= categories.get_Count();
				catIndex++)
			{
				IRoseCategory cat =
					categories.GetAt((new Integer(catIndex)).shortValue());
				getClassList(cat);
			}
		//Loop thru classes in the package
			for (int objIndex = 1; objIndex <= objs.get_Count(); objIndex++)
			{
				short objId = (new Integer(objIndex)).shortValue();
				//Get class from class collection
				IRoseClass roseClass = objs.GetAt(objId);
                System.out.println("Rose Class Name = " + roseClass.get_Name()); 
                 
                 
				//Get attributes of class
				classCategory.add(roseClass);
				IRoseAttributeCollection attribs = roseClass.get_Attributes();
				ArrayList parentClassList = getParentClass(roseClass);
				
				IRoseOperationCollection ops = roseClass.get_Operations();
				String stereoType = roseClass.get_LocalizedStereotype();

				//Check class inheritance to find the RequestEvents
				String parentClassName = checkClassInheritance(roseClass);
				String qualName = roseClass.GetQualifiedName();
				System.out.println("Qual name = " + qualName);
				System.out.println("Parent Class = " + parentClassName);
				System.out.println("Rose Class = " + roseClass.get_Name());
				
				if (parentClassName.equals("PersistentObject")
					|| (parentClassName.equals("RequestEvent")))
				//	&& (attribs.get_Count() > 0))
				{
					String entityName = roseClass.get_Name();
					//String qualName = roseClass.GetQualifiedName();
					
					int idx = qualName.indexOf("Vertical Subsystems");
					int start = idx + 21;
					
					//TODO - fix it if it doesn't find domain model or vertical subsystems
					if (!(idx > 0))
					{
						System.out.println(
							"Didn't find domain model or vertical subsystems - what to do now???");
						return classCategory;
					}
					String lQualName = qualName.substring(start);

					StringTokenizer st = new StringTokenizer(lQualName, "::");
					StringBuffer sb = new StringBuffer();

					boolean firstElement = true;
					while (st.hasMoreElements())
					{

						if (firstElement)
						{
							firstElement = false;
						}
						else
						{
							sb = sb.append(".");
						}
						sb = sb.append(st.nextElement().toString());
						//System.out.println("SB = " + sb.toString());

					}
					System.out.println("Rose Class Name = " + entityName);
					System.out.println(
						"Rose Qualified Name = " + qualName + " - " + sb);
					//System.out.println("     Stereotype = " + stereoType);
					//System.out.println(
					//	"     Attrib count = " + attribs.get_Count());

					//Call method to add or update associated ReqPro requirement 
					//and its children. Try up to 3 times due to ReqPro's habit of
					//dying at times for no apparent reason ("bad invoke" error)
					for (int i = 0; i <= 3; i++)
					{
						try
						{
							_Requirement requirement =
								updateRequirement(sb.toString(), attribs, ops);

							if (requirement != null)
							{
								if (parentClassName.equals("RequestEvent"))
								{
									updateAttributes(requirement, "event");
								}
								if (parentClassName.equals("PersistentObject"))
								{
									updateAttributes(requirement, "entity");
								}
								updateRoleAssociations(
									entityName,
									requirement,
									roseClass);
							}
							else
							{
								System.out.println("You have no requirement!!");
							}
							break;
						}
						catch (Exception e)
						{
							try
							{
								reqPro.CloseServer();
								initReqPro();
							}
							catch (Throwable t)
							{

							}
							System.err.println(e.getMessage());
						}
					}
					//Do loop again to get create child requirements for attributes from
					//parent Rose class. There may be more than one level of parents.
					
					IRoseClass parentClass = null;
					for (int k = 0; k < parentClassList.size(); k++)
					{

						parentClass = (IRoseClass)parentClassList.get(k);
						int dispatchId = 0;
						if (parentClass != null)
						{

							dispatchId = parentClass.IDispatch;
						}
						IRoseAttributeCollection parentAttribs = null;
						IRoseOperationCollection parentOps = null;
						if (dispatchId > 0)
						{
							parentAttribs = parentClass.get_Attributes();
							parentOps = parentClass.get_Operations();
						}
						if (parentAttribs != null)
						{
							System.out.println(
								"Parent class: " + parentClass.get_Name());
								
							//Try up to 3 times due to ReqPro's habit of
							//dying at times for no apparent reason ("bad invoke" error)
							for (int i = 0; i <= 3; i++)
							{
								try
								{
									_Requirement requirement =
										updateRequirement(
											sb.toString(),
											parentAttribs,
											parentOps);

									if (requirement != null)
									{
										if (parentClassName.equals("RequestEvent"))
										{
											updateAttributes(
												requirement,
												"event");
										}
										if (parentClassName.equals("PersistentObject"))
										{
											updateAttributes(
												requirement,
												"entity");
										}
										updateRoleAssociations(
											entityName,
											requirement,
											roseClass);
									}
									else
									{
										System.out.println(
											"You have no parent requirement!!");
									}
									break;
								}
								catch (Exception e)
								{
									try
									{
										reqPro.CloseServer();
										initReqPro();
									}
									catch (Throwable t)
									{

									}
									System.err.println(e.getMessage());
								}
							} //end of for loop to try 3 times
						} //end of parent attribute handling
					}//end of loop thru parent class arraylist

				}
			}//End of loop thru classes in packages
		return classCategory;
	}

	/**
	@param childReq
	@param type
	@roseuid 407AE3E30192
	 */
	public static void updateAttributes(_Requirement childReq, String type)
	{
		//	Added checks for null - 04/26/04 mtp
		if (childReq != null && childReq.IDispatch != 0 && (type != null))
		{
			_AttrValues attrValues = childReq.get_AttrValues();
			 
			int count = attrValues.get_Count();

			_AttrValue attr =
				attrValues.get_Item(
					(Object) "Type",
					enumAttrValueLookups.eAttrValueLookup_Label);
			attr.set_Text(type);

			childReq.Save();
			//System.out.println("		Chil'en attrib updated " + type);
		}
		else
		{
			System.out.println("Invalid paramenter in updateAttributes");
		}

	}

	/**
	@param entityName
	@param attribs
	@param ops
	@roseuid 407AE3EC02DF
	 */
	public static _Requirement updateRequirement(
		String entityName,
		IRoseAttributeCollection attribs,
		IRoseOperationCollection ops)
	{
		boolean showClassInfo = false;
		String reqNumber = null;
		_Requirement newReq = null;
		IRoseAttribute attrib = null;
		String name = null;
		String type = null;
		boolean reqFound = false;
		String reqName = null;

		Integer keya =
			(Integer) cdmPkg.get_Item(
				entityName,
				reqpro.enumPackageLookups.ePackageLookup_Name,
				reqpro.enumElementTypes.eElemType_Requirement);
		System.out.println("Entity Requirement Dispatch ID : " + keya);
		System.out.println("EntityName = " + entityName);
		if (keya.intValue() == 0)
		{

			//System.out.println("New requirement needed for " + entityName);
			String text = "";
			//Object reqType = (Object) "Class Data Member";
			String reqType = "Class Data Member";
			String versionLabel = "1.0";
			String versionReason = "Reason";
			Object parentReqLookupValue = "";
			int parentReqLookupType = 0;

			newReq =
				project.CreateRequirement(
					entityName,
					text,
					reqType,
					reqpro.enumReqTypesLookups.eReqTypesLookups_Name,
					versionLabel,
					versionReason,
					parentReqLookupValue,
					reqpro.enumRequirementLookups.eReqLookup_Empty);
			if (newReq.IDispatch != 0)
			{
				cdmPkg.AddElement(
					newReq,
					5,
					reqpro.enumElementTypes.eElemType_Requirement);
				//				try {
				//					newReq.Save();
				//				} catch (com.ibm.bridge2java.ComException e) {
				//					System.out.println(e.getMessage());
				//					return null;
				//				}
				newReq.Save();
				cdmPkg.Refresh(
					reqpro.enumPackageWeights.ePackageWeight_Package,
					false);

				reqNumber = newReq.get_Tag(reqpro.enumTagFormat.eTagFormat_Tag);
				//System.out.println(
				//	"Big Daddy Requirement Created for " + entityName);

			}

		}
		else
		{

			newReq = new _Requirement(keya);
			reqNumber = newReq.get_Tag(reqpro.enumTagFormat.eTagFormat_Tag);
			//System.out.println(
			//	"Update Existing Requirement : " + newReq.get_Name());
			reqName = newReq.get_Name();
			String text = newReq.get_Text();
			//String label = newReq.get_VersionLabel();
			//String reason = newReq.get_VersionReason();

			_ReqType reqTyp = newReq.get_ReqType();

			String reqTypeName = reqTyp.get_Name();
			//System.out.println("ReqType Name = " + reqTypeName);

		}
		//Get children for this requirement and update the attributes.
		//Add check for IDispatch - 05/05/04
		if (newReq.IDispatch != 0)
		{
			_Relationships rs = newReq.get_Children();
			int reqcount = rs.get_Count();
			//System.out.println("Requirements Count : " + reqcount);

			for (int attribIndex = 1;
				attribIndex <= attribs.get_Count();
				attribIndex++)
			{
				reqFound = false;
				short attrId = (new Integer(attribIndex)).shortValue();
				attrib = attribs.GetAt(attrId);
				name = attrib.get_Name();
				type = attrib.get_Type();
				rs.MoveFirst();
				for (int loci = 0; loci < reqcount; rs.MoveNext(), loci++)
				{
					_Relationship rp = rs.GetCurrentRelationship();

					_Requirement req1 =
						rp.get_RelatedRequirement(
							newReq,
							reqpro.enumElementTypes.eElemType_Requirement);

					reqName = req1.get_Name();
					//Match Rose class attribute name to Reqpro child requirement name
					//and update the child requirement "type" attribute
					if (reqName.equals(name))
					{
						//System.out.println("Old Chil'en  " + name);
						updateAttributes(req1, type);
						reqFound = true;
						break;
					}
				}
				String getter = null;
				String setter = null;
				if (!reqFound)
				{
					getter =
						"get"
							+ name.substring(0, 1).toUpperCase()
							+ name.substring(1, name.length());

					setter =
						"set"
							+ name.substring(0, 1).toUpperCase()
							+ name.substring(1, name.length());

					//System.out.println("getter Name : " + getter);
					if ((ops.FindFirst(getter) > 0)
						|| (ops.FindFirst(setter) > 0))
					{
						//System.out.println("     Attribute Name : " + name);
						// Create child requirement
						createChildRequirement(reqNumber, name, type);
					}
				}
			}

			return newReq;
		}
		else
		{
			//added else  - 05/04/05
			System.out.println("updateRequirement: No requirement returned");
			return null;
		}

	}

	/**
	@param entityName
	@roseuid 407AE3F90021
	 */
	/** Find the roles associated to the Rose class. If a role is navigable,
	 then look for a child requirement with the same name as the role. If found
	 update its attributes. If not found, then create the child requirement. 
	 The navigable roles represent the non-primitive, relationship data
	 mapping requirements.
	*/
	public static void updateRoleAssociations(
		String entityName,
		_Requirement requirement,
		IRoseClass roseClass)
	{
		//System.out.println(
		//	"In updateRoleAssociations Entity name = " + entityName);
		boolean reqFound = false;

		String reqNumber =
			requirement.get_Tag(reqpro.enumTagFormat.eTagFormat_Tag);
		IRoseRoleCollection roles = roseClass.GetAssociateRoles();
		_Relationships rs = requirement.get_Children();
		int reqcount = rs.get_Count();
		//System.out.println("*****reqcount = " + reqcount);
		int count = roles.get_Count();
		for (int objIndex = 1; objIndex <= count; objIndex++)
		{
			short objId = (new Integer(objIndex)).shortValue();
			IRoseRole role = roles.GetAt(objId);

			if (role.get_Navigable())
			{

				IRoseItem item = role.GetSupplier();

				String stereoType = item.get_Stereotype();
				String type = item.GetQualifiedName();

				String name = role.get_Name();
				if (stereoType.equals("entity") && (!name.equals("")))
				{
					System.out.println("Role name = " + role.get_Name());
					System.out.println("Role name = " + name);
					System.out.println("Role item name = " + type);
					System.out.println("Role item stereotype = " + stereoType);
					reqFound = false;
					rs.MoveFirst();
					for (int loci = 0; loci < reqcount; loci++)
					{

						//System.out.println("******loci = " + loci);
						_Relationship rp = rs.GetCurrentRelationship();
						int dispatchID = rp.IDispatch;
						if (dispatchID != 0)
						{

							_Requirement req1 =
								rp.get_RelatedRequirement(
									requirement,
									reqpro
										.enumElementTypes
										.eElemType_Requirement);

							String reqName = req1.get_Name();

							if (reqName.equals(name))
							{
								//System.out.println("Old Chil'en  " + name);
								updateAttributes(req1, type);
								reqFound = true;
								break;
							}
							rs.MoveNext();
						}
					}
					//create new Child
					if (!reqFound)
					{
						createChildRequirement(reqNumber, name, type);
					}
				}
			}

		}

	}

	/**
	This method will take  Rose model package names as parameters, and navigate to 
	the high-level package where it needs to retrieve the entities or events. 
	@param package1 - high level package
	@param package2 - next level down
	@return Void
	@roseuid 4097BD350046
	 */
	public static void navigateModel(String package1, String package2)
	{

		short unitId = units.FindFirst(package1);
		//System.out.println("Unit Id : " + unitId);
		IRoseCategory cUnit = units.GetAt(unitId);
		int disId = cUnit.IDispatch;
		//System.out.println("Dispatch Id : " + disId);
		//System.out.println("Unit Name : " + cUnit.get_Name());

		IRoseCategoryCollection subUnits = cUnit.get_Categories();
		//System.out.println(
		//	"Number of Subunits in Analysis Model : "
		//		+ subUnits.get_Count());
		//short BDUnitId = subUnits.FindFirst("Reference Model");
		//short BDUnitId = subUnits.FindFirst("Domain Model");
		short BDUnitId = subUnits.FindFirst(package2);
		//System.out.println("BDUUnit Id : " + BDUnitId);
		if (BDUnitId != 0)
		{
			IRoseCategory BDUnit = subUnits.GetAt(BDUnitId);
			int BDUDisId = BDUnit.IDispatch;
			//System.out.println(
			//	"Dispatch Id : "
			//		+ BDUDisId
			//		+ " Subunit Name : "
			//		+ BDUnit.get_Name());
			if (BDUnit.Load())
			{
				// get all the packages

				IRoseCategoryCollection pkages = BDUnit.get_Categories();
				//System.out.println(
				//	"No of Domain Packages : "
				//		+ pkages.get_Count());
				for (int pkgIndex = 1;
					pkgIndex <= pkages.get_Count();
					pkgIndex++)
				{
					short id = (new Integer(pkgIndex)).shortValue();
					IRoseCategory pkg = pkages.GetAt(id);
					if (pkg.IDispatch != 0)
					{
						//TODO - take our return of ArrayList and see what happens. Don't think we need it.
						ArrayList cats = getClassList(pkg);
					}
				}

			}
		}
	}
	public static void main(String[] args)
	{
		synchronize();
		System.out.println("We're done");

	}
}
