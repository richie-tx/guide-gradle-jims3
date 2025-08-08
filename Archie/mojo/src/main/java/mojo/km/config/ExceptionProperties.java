package mojo.km.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import mojo.km.config.xml.XMLExceptionPropertyAdapter;
import mojo.km.messaging.exception.LocationInfo;

/** @modelguid {C6DABA69-84EA-41C2-8514-478A49661801} */
public class ExceptionProperties
{
    /** @modelguid {019BE988-6370-4FD3-A6AC-451B0D891868} */
    private static ExceptionProperties instance;

    /** @modelguid {3ACBA623-D557-420D-903E-B95E4FAFFCB3} */
    private Map exceptionData = new HashMap();

    /** @modelguid {47ACD7B6-F498-4BC0-A4D5-DDD89919A5AC} */
    private Set locales = new HashSet();

    /** @modelguid {F4739517-000D-4F8E-BF1B-A34F697C123F} */
    private Map exceptions = new HashMap();

    /** @modelguid {7FB7AB8D-5EAF-4595-9A95-40F48BA6D4A6} */
    private ExceptionProperties()
    {
    }

    /** @modelguid {B6A895B8-9B77-46A9-B266-2F8901A8402E} */
    private static IPropertyAdapter getAdapter()
    {
        return new XMLExceptionPropertyAdapter();
    }

    /** @modelguid {60D9DD95-A41A-4BED-8EE8-30D0213BDB8E} */
    public static ExceptionProperties getInstance()
    {
        if (instance == null)
        {
            instance = new ExceptionProperties();
            try
            {
                getAdapter().loadProperties();
            }
            catch (Exception e)
            {
                System.err.println("Could not load contents of ExceptionData.xml");
                e.printStackTrace(System.err);
            }
        }
        return instance;
    }

    /** @modelguid {D8D62F6F-0C7F-4D72-8811-D0B7FE4EE516} */
    public Iterator getDefinedLocales()
    {
        return locales.iterator();
    }

    /** @modelguid {850D8492-E594-42DC-A514-4A2A00AC0103} */
    public Iterator getLocaleExceptions(String aLocaleName)
    {
        Set lExceptions = (Set) exceptions.get(aLocaleName);
        if (lExceptions == null)
        {
            return new HashSet().iterator();
        }
        return lExceptions.iterator();
    }

    /**
     * Check for existence of properties.
     * 
     * @param locale
     * @param exceptionObject
     * @return boolean - true if it has properties.
     * @modelguid {25E66BEF-84E1-48CA-8D6E-55C4C314F482}
     */
    public boolean hasProperties(Locale locale, Throwable exceptionObject)
    {
        return getExceptionData(locale, exceptionObject) != null;
    }

    /**
     * @param locale
     * @param exceptionObject
     * @return
     * @modelguid {E304B040-0122-463F-A1AD-3B1C3975B82C}
     */
    public Iterator getCallbacks(Locale locale, Throwable exceptionClass)
    {
        ExceptionData lData = getExceptionData(locale, exceptionClass);
        return lData != null ? lData.getCallbacks() : new Vector().iterator();
    }

    /**
     * @param local
     * @param exceptionObject
     * @param reason
     * @param code
     * @param diagnosis
     * @param solution
     * @modelguid {2AE6479D-671E-4BB9-8B97-ACE6642CF880}
     */
    public void setException(Locale locale, Throwable exceptionObject, String reason, String code, String diagnosis,
            String solution)
    {
        setException(locale, exceptionObject, reason, code, diagnosis, solution, new Vector());
        getAdapter().saveProperties();
    }

    /**
     * @param local
     * @param exceptionObject
     * @param reason
     * @param code
     * @param diagnosis
     * @param solution
     * @param callbacks
     * @modelguid {24F4AED8-8351-472B-92F0-596105FB0784}
     */
    public void setException(String localeName, String exceptionName, String reason, String code, String diagnosis,
            String solution, List callbacks)
    {
        ExceptionData lData = new ExceptionData();
        lData.setCode(code);
        lData.setDiagnosis(diagnosis);
        lData.setReason(reason);
        lData.setSolution(solution);
        for (Iterator i = callbacks.iterator(); i.hasNext();)
        {
            lData.addCallback((String) i.next());
        }

        locales.add(localeName);
        Set lExceptions = (Set) exceptions.get(localeName);
        if (lExceptions == null)
        {
            lExceptions = new HashSet();
            exceptions.put(localeName, lExceptions);
        }
        lExceptions.add(exceptionName);
        exceptionData.put(localeName + exceptionName, lData);
    }

    /**
     * @param local
     * @param exceptionObject
     * @param reason
     * @param code
     * @param diagnosis
     * @param solution
     * @param callbacks
     * @modelguid {834AD2C3-2228-4540-B756-D6A0C54C044B}
     */
    public void setException(Locale locale, Throwable exceptionObject, String reason, String code, String diagnosis,
            String solution, List callbacks)
    {
        setException(locale.getDisplayName(), exceptionObject.getClass().getName(), reason, code, diagnosis, solution,
                callbacks);
    }

    /**
     * Get the reason for the exception related to a given service and local.
     * 
     * @param local -
     *            ensure language translation.
     * @param exceptionObject -
     *            the type of exception.
     * @return
     * @modelguid {3C432869-4F72-4DD8-BA60-C4DEC7DB925C}
     */
    public String getReason(Locale local, Throwable exceptionClass)
    {
        ExceptionData lData = getExceptionData(local, exceptionClass);
        return lData != null ? lData.getReason() : null;
    }

    /**
     * Get the identity code for the exception related to a given service and
     * local.
     * 
     * @param local -
     *            ensure language translation.
     * @param exceptionObject -
     *            the type of exception.
     * @return
     * @modelguid {91F7B841-38F4-49AA-BC30-8FD22F511C4B}
     */
    public String getCode(Locale local, Throwable exceptionClass)
    {
        ExceptionData lData = getExceptionData(local, exceptionClass);
        return lData != null ? lData.getCode() : null;
    }

    /**
     * Get the diagnosis of the exception related to a given service and local.
     * 
     * @param local -
     *            ensure language translation.
     * @param exceptionObject -
     *            the type of exception.
     * @return
     * @modelguid {A3FB96CB-FFD4-4F78-9BC1-38069079F8CE}
     */
    public String getDiagnosis(Locale local, Throwable exceptionClass)
    {
        ExceptionData lData = getExceptionData(local, exceptionClass);
        return lData != null ? lData.getDiagnosis() : null;
    }

    /**
     * Get the solution to fix the exception related to a given service and
     * local.
     * 
     * @param local -
     *            ensure language translation.
     * @param exceptionObject -
     *            the type of exception.
     * @return
     * @modelguid {01F56E16-5A74-41A9-85DB-5330C90E910D}
     */
    public String getSolution(Locale local, Throwable exceptionClass)
    {
        ExceptionData lData = getExceptionData(local, exceptionClass);
        return lData != null ? lData.getSolution() : null;
    }

    /**
     * Get the reason for the exception related to a given service and current
     * local.
     * 
     * @param exceptionObject -
     *            the type of exception.
     * @return
     * @modelguid {AC6D7662-418F-4E70-8CAD-E4D324B8EFA8}
     */
    public String getReason(Throwable exceptionObject)
    {
        return getReason(Locale.getDefault(), exceptionObject);
    }

    /**
     * Get the identity code for the exception related to a given service and
     * current local.
     * 
     * @param exceptionObject -
     *            the type of exception.
     * @return
     * @modelguid {0B39315C-D5E2-4EA7-ABB8-395A5EA0E992}
     */
    public String getCode(Throwable exceptionObject)
    {
        return getCode(Locale.getDefault(), exceptionObject);
    }

    /**
     * Get the diagnosis of the exception related to a given service and current
     * local.
     * 
     * @param exceptionObject -
     *            the type of exception.
     * @return
     * @modelguid {BC0A6D7C-E2A5-4BEB-A252-C5D56CCCED73}
     */
    public String getDiagnosis(Throwable exceptionObject)
    {
        return getDiagnosis(Locale.getDefault(), exceptionObject);
    }

    /**
     * Get the solution to fix the exception related to a given service and
     * current local.
     * 
     * @param exceptionObject -
     *            the type of exception.
     * @return
     * @modelguid {329B34D0-E8D5-472D-BAB4-EC5E4B770006}
     */
    public String getSolution(Throwable exceptionObject)
    {
        return getSolution(Locale.getDefault(), exceptionObject);
    }

    /**
     * @param exceptionObject
     * @return
     * @modelguid {31C68AD5-8A39-4B97-B6DD-A3085B472AB5}
     */
    public boolean hasProperties(Throwable exceptionObject)
    {
        return hasProperties(Locale.getDefault(), exceptionObject);
    }

    /** @modelguid {F998EE99-8502-48FD-A61E-CA94FD1D78AE} */
    private String[] getThrowableStack(Throwable exceptionObject)
    {
        List lStack = new Vector();

        StringWriter lWriter = new StringWriter();
        exceptionObject.printStackTrace(new PrintWriter(lWriter));
        StringTokenizer lTokens = new StringTokenizer(lWriter.toString(), "\n");
        while (lTokens.hasMoreElements())
        {
            lStack.add(lTokens.nextToken().trim());
        }

        String[] lStrStack = new String[lStack.size()];
        int lCount = 0;
        for (Iterator i = lStack.iterator(); i.hasNext(); lCount++)
        {
            lStrStack[lCount] = (String) i.next();
        }

        return lStrStack;
    }

    /** @modelguid {6C62D1D4-7FBB-46BA-8F76-DF15237B4580} */
    public ExceptionData getExceptionData(Locale aLocale, Throwable exceptionObject)
    {
        String[] stackItems = getThrowableStack(exceptionObject);
        ExceptionData retVal = null;
        retVal = getExceptionData(aLocale.getDisplayName(), exceptionObject.getClass().getName());
        if (retVal != null)
        {
            return retVal;
        }
        for (int i = 1; i < stackItems.length; i++)
        {
            String className = null;
            String fullInfo = stackItems[i];
            if (fullInfo != null)
            {
                int iend = fullInfo.lastIndexOf('(');
                if (iend != -1)
                {
                    int ibegin = fullInfo.lastIndexOf("at ");
                    if (ibegin != -1)
                    {
                        className = fullInfo.substring(ibegin + 3, iend);
                    }
                }
            }
            if (className != null)
            {
                className = new LocationInfo(exceptionObject, className).getClassName();
                retVal = getExceptionData(aLocale.getDisplayName(), className);
                if (retVal != null)
                {
                    return retVal;
                }
            }
        }
        return getExceptionData(aLocale.getDisplayName(), "Default");
    }

    /** @modelguid {85674B5F-0EFE-4333-BFA8-1AA3A976A1E2} */
    public ExceptionData getExceptionData(String aLocaleName, String anExceptionName)
    {
        ExceptionData lData = (ExceptionData) exceptionData.get(aLocaleName + anExceptionName);
        return lData;
    }

    /** @modelguid {ADC4428B-AD5B-4A31-A7AE-D023C57D4B39} */
    public class ExceptionData
    {
        /** @modelguid {C93B2352-78C1-4ED1-96AC-D5F53C33ABF2} */
        private String reason;

        /** @modelguid {29B78EB7-86A2-4653-88B2-9555EC632C52} */
        private String code;

        /** @modelguid {92ED91D2-7EDE-4624-8DF1-7478B9C5809E} */
        private String diagnosis;

        /** @modelguid {C3A2F08A-916E-453B-88B3-1EC256095B08} */
        private String solution;

        /** @modelguid {547FD4A3-9131-49DC-AC0E-806D66872C01} */
        private List callbacks = new Vector();

        /** @modelguid {2A81DA6C-EF66-4FC8-B2A2-A93298138094} */
        public void addCallback(String aCallback)
        {
            callbacks.add(aCallback);
        }

        /** @modelguid {41FF6541-E52D-465B-952C-0369CF4935B3} */
        public Iterator getCallbacks()
        {
            return callbacks.iterator();
        }

        /**
         * @return
         * @modelguid {00DC280F-0BDC-48D0-AB31-EAABB41D3F9C}
         */
        public String getCode()
        {
            return code;
        }

        /**
         * @return
         * @modelguid {5683A3BC-8FC5-423A-B4F1-A2F8BE6C335C}
         */
        public String getDiagnosis()
        {
            return diagnosis;
        }

        /**
         * @return
         * @modelguid {D7F061E5-41C2-4124-8B84-F4247D607ABE}
         */
        public String getReason()
        {
            return reason;
        }

        /**
         * @return
         * @modelguid {DB522FF4-AC89-4EF5-8BA7-1F1E491C6DCB}
         */
        public String getSolution()
        {
            return solution;
        }

        /**
         * @param string
         * @modelguid {EAE061A7-317C-4981-8726-0C27A3DB60EF}
         */
        public void setCode(String string)
        {
            code = string;
        }

        /**
         * @param string
         * @modelguid {83EFD117-AB72-4F45-81BE-A082611C06BE}
         */
        public void setDiagnosis(String string)
        {
            diagnosis = string;
        }

        /**
         * @param string
         * @modelguid {2F47BD6D-18BE-43E1-9257-5F8F7FA5CCE4}
         */
        public void setReason(String string)
        {
            reason = string;
        }

        /**
         * @param string
         * @modelguid {DAAAB3CF-9750-419C-AE26-DB7C500A58E3}
         */
        public void setSolution(String string)
        {
            solution = string;
        }

    }
}
