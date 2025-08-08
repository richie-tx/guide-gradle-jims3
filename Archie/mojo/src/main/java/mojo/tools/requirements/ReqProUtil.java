/*

 * Created on Mar 4, 2004

 *

 * To change the template for this generated file go to

 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments

 */

package mojo.tools.requirements;

import reqpro.*;
import java.util.*;
import java.lang.reflect.*;

/**
 * @author srajibdassharma
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class ReqProUtil {

	private static _Project project = null;
	private static _RootPackage root = null;
	private static Application reqPro = null;
	private static String mainPackageName = "Supplementary Specifications";

	public static void init() {
		try {
			// Initialize the Java2Com Environment
			com.ibm.bridge2java.OleEnvironment.Initialize();
		} catch (Exception e) {
			System.out.println("Could not create Rose Import Control.");
			e.printStackTrace();
		}
		try {
			//String fileName = "c:\\\\test.rqs";
			//String fileName = "\\\\ratserver\\Data\\Architecture\\ReqPro\\test.rqs";
			String fileName = "\\\\ratserver\\Data\\RatProjects\\jims2\\Jims2ReqPro\\Jims2.rqs";
			//String fileName = "C:\\\\views\\dev\\framework\\mojo-jims2\\mojo.java\\src\\mojo\\tools\\requirements\\project\\archtest.rqs";

			String userid = "eamundson";
			String password = "eric";
			System.out.println(fileName);
			reqPro = (Application) new Application();
			System.out.println("Application created");

			try {
				project =
					reqPro.OpenProject(
						(Object) fileName,
						reqpro.enumOpenProjectOptions.eOpenProjOpt_RQSFile,
						userid,
						password,
						reqpro.enumProjectFlags.eProjFlag_Normal,
						reqpro
							.enumRelatedProjectOptions
							.eRelatedProjOption_ConnectAsSpecified);
			} catch (Exception ex) {
				System.out.println("Error while creating the project");
			}
			System.out.println("Project created");
			// get the root package
			root = project.GetRootPackage(false);
			System.out.println("Got the root package");
			String rootName = root.get_Name();
			System.out.println("Root Name : " + rootName);
			// load the elements
			root.LoadElements(reqpro.enumElementTypes.eElemType_Package);
			System.out.println("Elements loaded");
		} catch (Exception e) {
			System.out.println("Error initializing ReqPro !!!");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		//display();
		init();
		delete();
	}

	private static void delete() {
		try {
			try {
				Integer supSpecKey =
					(Integer) root.get_Item(
						mainPackageName,
						reqpro.enumPackageLookups.ePackageLookup_Name,
						reqpro.enumElementTypes.eElemType_Package);
				_Package supSpec = new _Package(supSpecKey);
				if (supSpec.IDispatch != 0) {
					supSpec.LoadElements(
						reqpro.enumElementTypes.eElemType_Package);
					int childCount =
						supSpec.get_CountWithChildren(
							reqpro.enumElementTypes.eElemType_Package,
							false);
					if (childCount > 0) {
						Integer key1 =
							(Integer) supSpec.get_Item(
								"Data Requirements",
								reqpro.enumPackageLookups.ePackageLookup_Name,
								reqpro.enumElementTypes.eElemType_Package);
						_Package dataReq = new _Package(key1);
						if (dataReq.IDispatch != 0) {
							drilldown(key1);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private static void deletePackage(String sourceType, String packageName) {

		try {
			try {
				Integer supSpecKey =
					(Integer) root.get_Item(
						mainPackageName,
						reqpro.enumPackageLookups.ePackageLookup_Name,
						reqpro.enumElementTypes.eElemType_Package);
				_Package supSpec = new _Package(supSpecKey);
				if (supSpec.IDispatch != 0) {
					supSpec.LoadElements(
						reqpro.enumElementTypes.eElemType_Package);
					int childCount =
						supSpec.get_CountWithChildren(
							reqpro.enumElementTypes.eElemType_Package,
							false);
					if (childCount > 0) {
						Integer key1 =
							(Integer) supSpec.get_Item(
								"Data Requirements",
								reqpro.enumPackageLookups.ePackageLookup_Name,
								reqpro.enumElementTypes.eElemType_Package);
						_Package dataReq = new _Package(key1);
						if (dataReq.IDispatch != 0) {
							dataReq.LoadElements(
								reqpro.enumElementTypes.eElemType_Package);
							int dataReqChildCount =
								dataReq.get_CountWithChildren(
									reqpro.enumElementTypes.eElemType_Package,
									false);
							if (dataReqChildCount > 0) {
								Integer key2 =
									(Integer) dataReq.get_Item(
										sourceType,
										reqpro
											.enumPackageLookups
											.ePackageLookup_Name,
										reqpro
											.enumElementTypes
											.eElemType_Package);
								_Package sType = new _Package(key2);
								if (sType.IDispatch != 0) {
									sType.LoadElements(
										reqpro
											.enumElementTypes
											.eElemType_Package);
									int sTypeChildCount =
										sType.get_CountWithChildren(
											reqpro
												.enumElementTypes
												.eElemType_Package,
											false);
									if (sTypeChildCount > 0) {
										Integer key3 =
											(Integer) sType.get_Item(
												packageName,
												reqpro
													.enumPackageLookups
													.ePackageLookup_Name,
												reqpro
													.enumElementTypes
													.eElemType_Package);
										drilldown(key3);
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private static void deleteParentChildRequirements(_Requirement req) {

		_Relationships rs = req.get_Children();
		int reqcount = rs.get_Count();
		//System.out.println("Requirements Count : " + reqcount);
		// set the cursor to the first position of the Relationships collection
		if (reqcount > 0) {
			rs.MoveFirst();
			while (reqcount > 0) {
				_Relationship rp = rs.GetCurrentRelationship();
				_Requirement req1 =
					rp.get_RelatedRequirement(
						req,
						reqpro.enumElementTypes.eElemType_Requirement);
				//System.out.println("Child Requirement : " + req1.get_Name());
				//System.out.println(req1.get_Name() + " DELETED >>>>>>>>>>>>>>>>>>>>");                                       
				req1.DeleteRequirementHierarchy(
					reqpro
						.enumRequirementDeleteFlags
						.eReqDelFlag_DeleteChildren,
					"",
					reqpro.enumRequirementLookups.eReqLookup_Empty);
				reqcount--;
				if (reqcount > 0)
					rs.MoveNext();
			}
		}
		// now delete the parent requirement
		//System.out.println("PARENT REQUIREMENT " + req.get_Name() + " DELETED >>>>>>>>>>>>>>>>>>>>");                                         
		req.DeleteRequirementHierarchy(
			reqpro.enumRequirementDeleteFlags.eReqDelFlag_DeleteChildren,
			"",
			reqpro.enumRequirementLookups.eReqLookup_Empty);
	}

	private static void drilldown(Integer key) {
		// 1. try to load the package with child packages
		// 2. if there are child packages
		// 3.       for each package
		// 4.                   perform step 1 for this package
		// 5. else load the package with requirements
		// 6.       delete all the requirements
		// 7. else there is nothing so delete the package
		try {
			_Package supSpec = new _Package(key);
			if (supSpec.IDispatch != 0) {
				supSpec.LoadElements(reqpro.enumElementTypes.eElemType_Package);
				int childCount =
					supSpec.get_CountWithChildren(
						reqpro.enumElementTypes.eElemType_Package,
						false);
				if (childCount > 0) {
					System.out.println(
						"Package : "
							+ supSpec.get_Name()
							+ "No of Child Packages : "
							+ childCount);
					supSpec.MoveFirst();
					while (childCount > 0) {
						Integer iKey = (Integer) supSpec.get_ItemCurrent();
						_Package pkg = new _Package(iKey);
						if (!pkg.get_HasChildren()) {
							System.out.println(
								"Package : "
									+ pkg.get_Name()
									+ " deleted since it has no child");
							pkg.DeletePackage();
							childCount--;
							supSpec.MovePrevious();
						} else {
							drilldown(iKey);
							System.out.println(
								"Package : "
									+ pkg.get_Name()
									+ " deleted after deleting children");
							if (!pkg.get_HasChildren()) {
								pkg.DeletePackage();
								childCount--;
								supSpec.MovePrevious();
							}
						}
						if (childCount > 0)
							supSpec.MoveNext();
					}
				} else // there are no child packages inside, there may be requirements
					{
					supSpec.LoadElements(
						reqpro.enumElementTypes.eElemType_Requirement);
					childCount =
						supSpec.get_CountWithChildren(
							reqpro.enumElementTypes.eElemType_Requirement,
							false);
					//System.out.println(" Package : " + supSpec.get_Name() + "Child Count : " + childCount);
					if (childCount > 0) {
						supSpec.MoveFirst();
						while (childCount > 0) {
							Integer iKey = (Integer) supSpec.get_ItemCurrent();
							_Requirement parentReq = new _Requirement(iKey);
							if (parentReq.get_Children().get_Count() > 0) {
								System.out.println(
									"Package : "
										+ supSpec.get_Name()
										+ " deleting parent-child requirements");
								deleteParentChildRequirements(parentReq);
								childCount--;
								supSpec.MovePrevious();
							} else {
								// there is no child so delete the parent requirement
								System.out.println(
									"Package : "
										+ supSpec.get_Name()
										+ " deleting parent requirements");
								parentReq.DeleteRequirementHierarchy(
									reqpro
										.enumRequirementDeleteFlags
										.eReqDelFlag_DeleteChildren,
									"",
									reqpro
										.enumRequirementLookups
										.eReqLookup_Empty);
								childCount--;
								supSpec.MovePrevious();
							}
							if (childCount > 0)
								supSpec.MoveNext();
						}
					} else {
						supSpec.DeletePackage();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
