/*
 * Created on Mar 9, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.tools.requirements;

import rationalrose.*;

import java.util.ArrayList;
import java.util.Iterator;

import reqpro.*;
import java.lang.reflect.*;

/**
 * @author mpatino & rcooper
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RoseReqProSyncher {
	private static _Package dataRequirements = null;
	private static _Project project = null;

	public static void main(String[] args) {
		try {
			// Initialize the Java2Com Environment
			com.ibm.bridge2java.OleEnvironment.Initialize();
		} catch (Exception e) {
			System.out.println("Could not create Rose Import Control.");
			e.printStackTrace();
		}

		try {
			String reqProDB =
				"\\\\ratserver\\Data\\Architecture\\ReqPro\\test.rqs";
			String userid = "eamundson";
			String password = "eric";
			System.out.println(reqProDB);
			String packageName = "Test Package";
			Application reqPro = (Application) new Application();
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
			_RootPackage root = project.GetRootPackage(true);
			String rootName = root.get_Name();

			// load the elements
			root.LoadElements(reqpro.enumElementTypes.eElemType_Package);
			Integer supSpecKey =
				(Integer) root.get_Item(
					"Test Package",
					reqpro.enumPackageLookups.ePackageLookup_Name,
					reqpro.enumElementTypes.eElemType_Package);

			//_Package dataRequirements = new _Package(supSpecKey);
			dataRequirements = new _Package(supSpecKey);

			if (dataRequirements.IDispatch != 0) {
				System.out.println(
					"dataRequirements" + dataRequirements.IDispatch);
				dataRequirements.LoadElements(
					reqpro.enumElementTypes.eElemType_Requirement);
			} else {
				//TODO add exception handling  
				System.out.println("Requirements not found");
			}

			String model =
				"c:\\\\views\\arch_e2\\Arch\\Framework\\RefApp_Model\\JIMS2ReferenceApplication.mdl";
			//"c:\\\\Copy of dgibler_PetStore\\PetStore_Model\\design\\petstore_DESIGN.mdl";
			//"c:\\\\views\\arch_e2\\Arch\\JIMS2\\model\\SoftwareArchitectureDocumentCopy.mdl";

			//String model = "c:\\\\views\\arch_e2\\Arch\\JIMS2\\model\\Software Architecture Document.mdl";
			RoseApplication roseAppl = new RoseApplication();
			IRoseModel roseModel = roseAppl.OpenModel(model);
			//System.out.println("Model Name : " + roseModel.get_Name());
			roseModel.Load();
			IRoseCategory roseCategory = roseModel.get_RootCategory();
			//System.out.println("Category Name : " + roseCategory.get_Name());
			IRoseSubsystem roseSubsys = roseModel.get_RootSubsystem();
			//System.out.println("Subsystem Name : " + roseCategory.get_Name());
			IRoseCategoryCollection units = roseCategory.get_Categories();
			//System.out.println("Number of Subunits : " + units.get_Count());
			int dispatchID = units.IDispatch;
			//System.out.println("Dispatch ID : " + dispatchID);
			if (dispatchID != 0) {
				//for(int unitIndex = 0 ; unitIndex < units.get_Count(); unitIndex++)
				//{

				short unitId = units.FindFirst("Analysis Model");
				//short unitId = units.FindFirst("pd");
				if (unitId != 0) {
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
					short BDUnitId = subUnits.FindFirst("Domain Model");
					//System.out.println("BDUUnit Id : " + BDUnitId);
					if (BDUnitId != 0) {
						IRoseCategory BDUnit = subUnits.GetAt(BDUnitId);
						int BDUDisId = BDUnit.IDispatch;
						System.out.println(
							"Dispatch Id : "
								+ BDUDisId
								+ " Subunit Name : "
								+ BDUnit.get_Name());
						if (BDUnit.Load()) {
							// get all the packages

							IRoseCategoryCollection pkages =
								BDUnit.get_Categories();
							//System.out.println(
							//	"No of Domain Packages : "
							//		+ pkages.get_Count());
							for (int pkgIndex = 1;
								pkgIndex <= pkages.get_Count();
								pkgIndex++) {
								short id = (new Integer(pkgIndex)).shortValue();
								IRoseCategory pkg = pkages.GetAt(id);
								if (pkg.IDispatch != 0) {
									ArrayList cats = getClassList(pkg);
								}
							}
						}
					}

				}
			}
			roseAppl.ReleaseObject();
		} catch (Exception e) {
			System.out.println("Exception occurred in RoseReqProSyncher: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static ArrayList getClassList(IRoseCategory category) {
		//System.out.println("Category Name : " + category.get_Name());
		ArrayList classCategory = new ArrayList();
		IRoseClassCollection objs = category.get_Classes();
		//System.out.println("No of classes : " + objs.get_Count());

		// get the objects for this package

		if (objs.get_Count() == 0) // there is no class in this package
			{
			IRoseCategoryCollection categories = category.get_Categories();
			for (int catIndex = 1;
				catIndex <= categories.get_Count();
				catIndex++) {
				IRoseCategory cat =
					categories.GetAt((new Integer(catIndex)).shortValue());
				getClassList(cat);
			}
		} else {
			for (int objIndex = 1; objIndex <= objs.get_Count(); objIndex++) {
				short objId = (new Integer(objIndex)).shortValue();
				IRoseClass roseClass = objs.GetAt(objId);

				classCategory.add(roseClass);
				IRoseAttributeCollection attribs = roseClass.get_Attributes();

				IRoseOperationCollection ops = roseClass.get_Operations();
				String stereoType = roseClass.get_LocalizedStereotype();

				//Check class inheritance to find the RequestEvents
				boolean eventClass = checkClassInheritance(roseClass);

				if ((stereoType.equals("entity") || (eventClass))
					&& (attribs.get_Count() > 0)) {
					String entityName = roseClass.get_Name();
					System.out.println("Rose Class Name = " + entityName);
					System.out.println("     Stereotype = " + stereoType);
					System.out.println(
						"     Attrib count = " + attribs.get_Count());
					// call method to find associated ReqPro requirement and children
					_Requirement requirement =
						updateRequirement(entityName, attribs, ops);
					if (requirement != null) {

						updateRoleAssociations(
							entityName,
							requirement,
							roseClass);
					} else {
						System.out.println("You have no requirement!!");
					}

				}
			}
		}
		return classCategory;
	}
	public static _Requirement updateRequirement(
		String entityName,
		IRoseAttributeCollection attribs,
		IRoseOperationCollection ops) {
		boolean showClassInfo = false;
		String reqNumber = null;
		_Requirement newReq = null;
		IRoseAttribute attrib = null;
		String name = null;
		String type = null;
		boolean reqFound = false;
		String reqName = null;
		//start of copy   
		Integer keya =
			(Integer) dataRequirements.get_Item(
				entityName,
				reqpro.enumPackageLookups.ePackageLookup_Name,
				reqpro.enumElementTypes.eElemType_Requirement);
		//System.out.println("Entity Requirement Dispatch ID : " + keya);
		if (keya.intValue() == 0) {

			System.out.println("New requirement needed for " + entityName);
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
			if (newReq.IDispatch != 0) {
				dataRequirements.AddElement(
					newReq,
					5,
					reqpro.enumElementTypes.eElemType_Requirement);

				newReq.Save();
				reqNumber = newReq.get_Tag(reqpro.enumTagFormat.eTagFormat_Tag);
				System.out.println(
					"Big Daddy Requirement Created for " + entityName);

			}

		} else {

			newReq = new _Requirement(keya);
			reqNumber = newReq.get_Tag(reqpro.enumTagFormat.eTagFormat_Tag);
			System.out.println("Big Daddy Requirement : " + newReq.get_Name());
			reqName = newReq.get_Name();
			String text = newReq.get_Text();
			String label = newReq.get_VersionLabel();
			String reason = newReq.get_VersionReason();
			_ReqType reqTyp = newReq.get_ReqType();
			String reqTypeName = reqTyp.get_Name();

			// get children for this requirement
		}
		_Relationships rs = newReq.get_Children();
		int reqcount = rs.get_Count();
		//System.out.println("Requirements Count : " + reqcount);

		for (int attribIndex = 1;
			attribIndex <= attribs.get_Count();
			attribIndex++) {
			reqFound = false;
			short attrId = (new Integer(attribIndex)).shortValue();
			attrib = attribs.GetAt(attrId);
			name = attrib.get_Name();
			type = attrib.get_Type();
			rs.MoveFirst();
			for (int loci = 0; loci < reqcount; rs.MoveNext(), loci++) {
				_Relationship rp = rs.GetCurrentRelationship();

				_Requirement req1 =
					rp.get_RelatedRequirement(
						newReq,
						reqpro.enumElementTypes.eElemType_Requirement);

				reqName = req1.get_Name();

				if (reqName.equals(name)) {
					System.out.println("Old Chil'en  " + name);
					updateAttributes(req1, type);
					reqFound = true;
					break;
				}
			}
			if (!reqFound) {
				String getter =
					"get"
						+ name.substring(0, 1).toUpperCase()
						+ name.substring(1, name.length());
				System.out.println("getter Name : " + getter);
				if (ops.FindFirst(getter) > 0) {
					System.out.println("     Attribute Name : " + name);
					// Create child requirement
					createChildRequirement(reqNumber, name, type);
				}
			}
		}
		return newReq;
	}

	public static void createChildRequirement(
		String parentReqNum,
		String entityName,
		String type) {

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
		if (childReq.IDispatch != 0) {

			childReq.Save();
			//call do attrib
			System.out.println("Chil'en created " + entityName);
			updateAttributes(childReq, type);

		}

	}
	public static void updateAttributes(_Requirement childReq, String type) {
		_AttrValues attrValues = childReq.get_AttrValues();
		int count = attrValues.get_Count();

		_AttrValue attr =
			attrValues.get_Item(
				(Object) "Type",
				enumAttrValueLookups.eAttrValueLookup_Label);
		attr.set_Text(type);

		childReq.Save();
		System.out.println("		Chil'en attrib updated " + type);

	}
	public static void updateRoleAssociations(
		String entityName,
		_Requirement requirement,
		IRoseClass roseClass) {
		System.out.println(
			"In updateRoleAssociations Entity name = " + entityName);
		boolean reqFound = false;

		String reqNumber =
			requirement.get_Tag(reqpro.enumTagFormat.eTagFormat_Tag);
		IRoseRoleCollection roles = roseClass.GetAssociateRoles();
		_Relationships rs = requirement.get_Children();
		int reqcount = rs.get_Count();
		System.out.println("*****reqcount = " + reqcount);
		int count = roles.get_Count();
		for (int objIndex = 1; objIndex <= count; objIndex++) {
			short objId = (new Integer(objIndex)).shortValue();
			IRoseRole role = roles.GetAt(objId);
			if (role.get_Navigable()) {

				IRoseItem item = role.GetSupplier();

				String stereoType = item.get_Stereotype();
				String type = item.GetQualifiedName();

				String name = role.get_Name();
				if (stereoType.equals("entity") && (!name.equals(""))) {
					System.out.println("Role name = " + role.get_Name());
					System.out.println("Role name = " + name);
					System.out.println("Role item name = " + type);
					System.out.println("Role item stereotype = " + stereoType);
					reqFound = false;
					rs.MoveFirst();
					for (int loci = 0; loci < reqcount; loci++) {

						System.out.println("******loci = " + loci);
						_Relationship rp = rs.GetCurrentRelationship();
						int dispatchID = rp.IDispatch;
						if (dispatchID != 0) {

							_Requirement req1 =
								rp.get_RelatedRequirement(
									requirement,
									reqpro
										.enumElementTypes
										.eElemType_Requirement);
							//Just playing!
							//getTraceabilities(req1);
							String reqName = req1.get_Name();

							if (reqName.equals(name)) {
								System.out.println("Old Chil'en  " + name);
								updateAttributes(req1, type);
								reqFound = true;
								break;
							}
							rs.MoveNext();
						}
					}
					//create new Child
					if (!reqFound) {
						createChildRequirement(reqNumber, name, type);
					}
				}
			}

		}

	}
//	public static void getTraceabilities(_Requirement req) {
//		_Relationships tracesTo = req.get_TracesTo();
//		int reqcount = tracesTo.get_Count();
//		tracesTo.MoveFirst();
//		for (int loci = 0; loci < reqcount; loci++) {
//			_Relationship rel = tracesTo.GetCurrentRelationship();
//			int dispatchID = rel.IDispatch;
//			if (dispatchID != 0) {
//				_Requirement req1 =
//					rel.get_RelatedRequirement(
//						req,
//						reqpro.enumElementTypes.eElemType_Requirement);
//				String namee = req1.get_Name();
//				String reqeeNamu = req.get_Name();
//				int reqDispatch = req1.IDispatch;
//				_Relationship daddyReq = req.get_Parent(reqDispatch);
//				_Requirement req2 =
//					daddyReq.get_SourceRequirement(
//						reqpro.enumElementTypes.eElemType_Requirement);
//				System.out.println(
//					"traceee nameee = "
//						+ namee
//						+ " reqeeNamu "
//						+ reqeeNamu
//						+ " dad rel "
//						+ req2.get_Name());
//
//			}
//		}
//		//_Relationships tracesTo = req.get_TracesTo();
//
//	}
	public static boolean checkClassInheritance(IRoseClass roseClass) {
		System.out.println(
			"Checking class inheritance for " + roseClass.get_Name());
		IRoseInheritRelationCollection irCollection =
			roseClass.GetInheritRelations();
		if (irCollection.IDispatch != 0) {
			short ircount = irCollection.get_Count();
			for (short loci = 1; loci <= ircount; loci++) {
				IRoseInheritRelation rel = irCollection.GetAt(loci);

				if (rel.IDispatch != 0) {
					IRoseClass parent = rel.GetSupplierClass();
					String name = rel.get_SupplierName();
					System.out.println(
						"Found inheritence rel for " + rel.get_SupplierName());
					if (name.equals("RequestEvent")) {
						return true;
					} else {
						return checkClassInheritance(parent);

					}
				}

			}
		}

		return false;
	}

}
