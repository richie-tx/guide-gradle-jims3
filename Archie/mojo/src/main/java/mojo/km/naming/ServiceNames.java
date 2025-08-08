//Source file: Z:/java.vob/CommonServiceLayer/Services/ServiceNames.java

package mojo.km.naming;

/** @modelguid {2BE5F684-6C96-4EF4-924E-E48E2D9E3F4B} */
public class ServiceNames 
{
    /**
 *     Name of service to obtain bar history.
 * @modelguid {158E7CEF-C51B-4A09-9C95-3101C54A6868}
 */
    public static final String BARHISTORY = "BARHISTORY";
	/** @modelguid {CBBF0E5C-23B9-449E-9831-16095B289CE4} */
    public static final String NEWORDER = "NEWORDER";
    
    /**
 *     Name of service that supports user security.
 * @modelguid {00292D96-5069-4C66-B5A2-FCCE33EA52FB}
 */
    public static final String LOGIN = "LOGIN";
    
    /**
 *     Service to verify any order send to OES.
 *     Services currently found in the ORDERREQUESTSAVE server.
 * @modelguid {34FDE3F9-9BB1-452B-8950-816DF2316C13}
 */
    public static final String SAVEPLACEORDERREQUEST                = "SPOR";
	/** @modelguid {5EE1E865-B697-4433-96CF-3130F8989793} */
    public static final String SAVECANCELREQUEST                    = "SCR";
	/** @modelguid {4CC16E26-89B6-4983-B5E4-954A3E835DE7} */
    public static final String SAVEMODIFYREQUEST                    = "SMR";
    
    /**
 *     Service to verify any order send to OES.
 *     Services currently found in the WEB ORDERREQUESTSAVE server.
 * @modelguid {4AA9BB74-366C-4175-98C3-5EA68E799191}
 */
    public static final String WEBSAVEPLACEORDERREQUEST             = "WEBSPOR";
	/** @modelguid {E7A165D6-06FC-4D1C-862B-C5DBA9453B11} */
    public static final String WEBSAVECANCELREQUEST                 = "WEBSCR";
	/** @modelguid {7EDAF7A9-B765-4D69-BE65-6B36138CB6F3} */
    public static final String WEBSAVEMODIFYREQUEST                 = "WEBSMR";
    
    /**
 *     Services currently located in the tuxedo service named: ORDERREQUESTPROCESS
 * @modelguid {ED430322-E448-431F-BE36-896C21ACFD4C}
 */
    public static final String PROCESSPLACEORDERREQUEST             = "PPOR";
	/** @modelguid {2D5709C0-5D18-4B95-8C93-32067950D6E7} */
    public static final String PROCESSCANCELREQUEST                 = "PCR";
	/** @modelguid {7E87DA97-BC34-4C94-8BEB-B0DDB115B65C} */
    public static final String PROCESSMODIFYREQUEST                 = "PMR";
	/** @modelguid {FAC3FB1D-9ECF-434D-92DA-C24AD2B2892D} */
    public static final String PROCESSQUERYREQUEST                  = "PQR";
    
    /**
 *     Services currently located in the tuxedo service named: GETACCOUNTINFO
 * @modelguid {6E2EF4E6-498D-4990-B20A-08081E584402}
 */
    public static final String PROCESSACCOUNTINFOREQUEST            = "PAIR";
	/** @modelguid {F17EE3A7-A04A-40F6-928A-E0E4ED252658} */
    public static final String WEBPROCESSACCOUNTINFOREQUEST         = "WEBPAIR";
    
    /**
 *     Services currently located in the web tuxedo service named: ORDERREQUESTPROCESS
 * @modelguid {3333BB1D-C693-4C9F-816B-98617542FDEB}
 */
    public static final String WEBPROCESSPLACEORDERREQUEST          = "WEBPPOR";
	/** @modelguid {82AC5B4E-3103-4BA4-9EF8-DDBE7141F2A7} */
    public static final String WEBPROCESSCANCELREQUEST              = "WEBPCR";
	/** @modelguid {B40AB488-D9DD-4D56-ADCE-67D62ED4113B} */
    public static final String WEBPROCESSMODIFYREQUEST              = "WEBPMR";
	/** @modelguid {A0A1AF85-5D2D-4C31-B809-0E14A066A07A} */
    public static final String WEBPROCESSQUERYREQUEST               = "WEBPQR";
	/** @modelguid {E9D65F27-713B-405D-8337-959675E6B1DC} */
    public static final String PROCESSORDERACCEPT                   = "POA";
	/** @modelguid {86040430-5BF1-41E7-AF1B-90C975D7F8FE} */
    public static final String PROCESSORDERREJECT                   = "POR";
	/** @modelguid {53B171F1-1789-48A2-A9B1-D856EBFED13C} */
    public static final String PROCESSORDERREVIEWREQUEST            = "PORR";
    
    // Service names for Admin functions

	/** @modelguid {D2731876-C00D-41E7-BD2B-D7003D42BDB2} */
    public static final String ORDERQUERY                           = "ORDERQUERY";
	/** @modelguid {2D17B732-AF26-4987-BCF3-E966581ACC24} */
    public static final String STOREORDERHISTORY                    = "STOREORDERHISTORY";
	/** @modelguid {C6953A01-A7B6-4856-8814-7194AFF3933B} */
    public static final String POSITIONUPDATE                       = "POSITIONUPDATE";
    
    /**
 *     Service name for getting data from penson accounts to Objectivity account objects.
 * @modelguid {7593B044-FCF3-44EA-897C-592609080D46}
 */
    public static final String ACCOUNTUPDATE                        = "ACCOUNTUPDATE";
    
    /**
 *     Service name for getting data from pension users to Objectivity user objects.
 * @modelguid {C5DF589B-9B48-4755-B830-1A503B73A487}
 */
    public static final String USERUPDATE                           = "USERUPDATE";
    
    /**
 *     Service name for getting data from Current Price Manager.
 * @modelguid {37EE18A6-8F79-48A7-BA2B-2BB3B4057738}
 */
    public static final String GETCURRENTPRICE                      = "GETCURRENTPRICE";
	/** @modelguid {721F30E7-E79B-4607-81B6-8F156AF5BD80} */
    public static final String JAVELIN                              = "JAVELIN";
	/** @modelguid {2C289573-7995-4CC3-88F0-0C9B7D331509} */
    public static final String SIS                                  = "SIS";
	/** @modelguid {D1FD2F10-8F49-40E7-932B-AB46F92530FC} */
    public static final String NASDAQ                               = "NASDAQ";
	/** @modelguid {71B438F6-6A26-4435-9732-45BD70DDA594} */
    public static final String SELECTNET                            = "SELECTNET";
	/** @modelguid {93EB72BB-1581-4400-A75E-2BE12DD024BE} */
    public static final String EXTERNALSYSTEMCONSOLE                = "EXTERNALSYSTEMCONSOLE";
    
    /**
 *     Service name for GUI topic
 * @modelguid {A864B995-5C24-4938-B9CE-F95E1C2863DB}
 */
    public static final String REVIEWTERMINAL                       = "RVWTRMNL";
    

    /**
 *     Services that are currently housed in the Tuxedo RESPONSE server.
 * @modelguid {A33F3472-188E-43F9-BDBB-EACF46177C5F}
 */
    public static final String ORDERACCEPTRESPONSE = "ORDERACCEPTRESPONSE";
	/** @modelguid {39C17667-ABF4-487E-AA1F-A612CB8773B7} */
    public static final String ORDERFILLRESPONSE = "ORDERFILLRESPONSE";
	/** @modelguid {0BFE9BE5-4F8E-4AC7-8EA4-F3ACD5AD050D} */
    public static final String ORDERKILLRESPONSE = "ORDERKILLTRESPONSE";
	/** @modelguid {F0977E1F-DB76-44EA-A81C-5A607E2F376B} */
    public static final String ORDERCOMPLETERESPONSE = "ORDERCOMPLETERESPONSE";
	/** @modelguid {4A382E42-209B-4872-9101-A403E0BDF6C0} */
    public static final String ORDERREJECTRESPONSE = "ORDERREJECTRESPONSE";
	/** @modelguid {CC2AF6A2-9E78-4392-A9EE-ECAC415672D0} */
    public static final String CANCELACCEPTRESPONSE = "CANCELACCEPTRESPONSE";
	/** @modelguid {FCAACEC3-8B71-469D-AA2B-69F017A75ED6} */
    public static final String CANCELCOMPLETERESPONSE = "CANCELCOMPLETERESPONSE";
	/** @modelguid {0B0428C7-5942-4212-AAB7-C69D398A7A27} */
    public static final String CANCELREJECTRESPONSE = "CANCELREJECTRESPONSE";
	/** @modelguid {DB72AF2B-687B-4DA3-B524-820AEEC5E010} */
    public static final String QUERYRESPONSE = "QUERYRESPONSE";
    
    /**
 *     Service found in the PUBSUB server.
 * @modelguid {D4307E3A-EACC-4855-843E-65A5CCFFD71C}
 */
    public static final String SUBSCRIBE = "SUBSCRIBE";
	/** @modelguid {F26E0AC5-43BB-4654-81DF-FC28AC5667F1} */
    public static final String UNSUBSCRIBE = "UNSUBSCRIBE";
    
    /**
 *     PUBLISH all responses to client.
 * @modelguid {142AEEB0-5014-4FB6-B936-F72E7DCBA796}
 */
    public static final String PUBSUB = "PUBSUB";
	/** @modelguid {540C90C6-216C-48D2-B518-4C1653445726} */
    public static final String RESPONSE = "RESPONSE";

    /**
 *     used for Penson updates
 * @modelguid {2761589F-02E7-4313-A371-EB31F23AD4F4}
 */
    public static final String TRANSFER = "TRANSFER";
    
    /**
 *     for looking up symbols based on company name
 * @modelguid {70A18CC5-F078-4C5C-B4E3-C684060F68F4}
 */
    public static final String SYMBOLSEARCH = "SYMBOLSEARCH";
	/** @modelguid {99C5DDAD-A3F1-4DEB-97BC-B89339D8C6E5} */
    public static final String OPTIONCHAIN = "OPTIONCHAIN";
    




	/** For when you can't find the context 
	 * @modelguid {DB25683B-11DF-4D62-ADDE-471B62C6CB9A}
	 */
    public static final String CONTEXT_UNAVAILABLE = "ContextUnavailable";

	/** @modelguid {9B0FDCAC-AC91-4361-85F9-6BA6B79DBC40} */
    public ServiceNames() 
    {
    }
}
