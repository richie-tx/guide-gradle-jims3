package mojo.km.security;

/**
 * This is a simple encryption class with static methods to encrypt and decrypt
 * strings.
 *
 * <pre>
 * Change History:
 * Author           Date       Explanation
 * Shannon Ewing   12/10/1999  Minor attribute renaming to conform to standards.
 * Shannon Ewing    1/10/2000  Code cleanup in encrypt
 * |                           Code cleanup in decrypt
 * Shannon Ewing    2/21/2000  Package change.
 * </pre>
 *
 * @author Shannon Ewing 9/11/1999
 * @modelguid {64A79B9F-EFE1-496F-9167-1D356D10F399}
 */
public class Encryptor {
	/** @modelguid {85BFC361-513D-4513-8332-707B4D9CCE0E} */
    private static final long MUTATIONVALUE = 1999;
    // Delimiter characters can not be a number or a hex letter - a,b,c,d,e,f
	/** @modelguid {570C6BF3-D6B0-42BB-A469-09995FD3147D} */
    private static final String[] CHARDELIMITERS = {"q","w","r","t","y"};

    /**
     * Decrypts a string value encrypted by the encrypt method of this class.
     *
     * @param value The string to decrypt
     * @return String - The decrypted value
     * @modelguid {6BCB9385-4AED-4350-8D43-A0534955BFDD}
     */
    public static String decrypt(String value) {
        // The general decryption procedure is as follows:
        // 1. Retreive the number of characters of the decrypted value
        // 2. Break out each encrypted character in the input value into
        //    an array.
        // 3. For each encrypted character, convert the hex value to a long,
        //    subtract the mutation value (mutationValue variable) which gives
        //    the ASCII value of the character (for Windows platforms). This is
        //    then converted to the actual character.
        if (value == null || value.length() < 1) {
            return null;
        }
        //********************************************************************
        //Break out encrypted input values into the encrypted character fields
        //********************************************************************
        // determine number of characters (first field)
        int numCharIndex = value.indexOf(CHARDELIMITERS[0]);
        if (numCharIndex == -1) {
            return null;
        }
        Integer numChars = new Integer(value.substring(0, numCharIndex));
        value = value.substring(numCharIndex + 1);
        // break out the characters
        String encryptedValues[] = new String[numChars.intValue()];
        int insertIndex = 0;
        // Main loop
        while (value.length() > 0) {
            StringBuffer workString = new StringBuffer();
            boolean buildingChar = true;
            // Character loop
            while (buildingChar) {
                String aChar = value.substring(0, 1);
                boolean foundDelimiter = false;
                // Delimiter loop
                for (int x = 0; x < CHARDELIMITERS.length; x++) {
                    if (aChar.equals(CHARDELIMITERS[x])) {
                        foundDelimiter = true;
                        break;
                    }
                } // end Delimiter loop
                if (foundDelimiter) {
                    buildingChar = false;
                    value = value.substring(1);
                } else {
                    workString.append(aChar);
                    value = value.substring(1);
                }
            } // end Character loop
            encryptedValues[insertIndex] = new String(workString);
            insertIndex++;
        } // end Main loop
        //************************
        //Decrypt each character
        //************************
        StringBuffer decryptedString = new StringBuffer();
        for (int x = 0; x < encryptedValues.length; x++) {
            Long objVal = Long.valueOf(encryptedValues[x], 16);
            long longVal = objVal.longValue();
            longVal -= MUTATIONVALUE;
            byte byteVal[] = new byte[1];
            byteVal[0] = (byte)longVal;
            decryptedString.append(
                new String(byteVal));
        }
        return decryptedString.toString();
    }

    /**
     * Encrypts a string value.
     *
     * @param value The string to encrypt
     * @return String - The encrypted value
     * @modelguid {B8C250D5-50D9-4AB3-8756-47DD63928835}
     */
    public static String encrypt(String value) {
        // The encrypted string returned is as follows:
        // 1. The number of bytes in the input value.
        // 2. First delimiter in the charDelimiters array.
        // 3. The first character of the input value turned into a long value by
        //    getting the ASCII value of the character (for Windows platforms).
        //    This value is then mutated by adding the value of
        //    the mutationValue variable. The resulting value is then converted
        //    to hex.
        // 4. The next delimiter in the charDelimiters array.
        // 5. Steps 3 and 4 are repeated for each character in the input value.
        //    When the end of the charDelimiters array is reached, it restarts
        //    from the beginning of the array.
        if (value == null || value.length() < 1) {
            return null;
        }
        // set the number of bytes in the input value
        int delimiterIndex = 0;
        Integer valueLength = new Integer(value.length());
        String encryptedValue = valueLength.toString() + CHARDELIMITERS[delimiterIndex];
        delimiterIndex++;
        byte byteArray[] = value.getBytes();
        for (int x = 0; x < byteArray.length; x++) {
            long longValue = (long)byteArray[x];
            //Mutate Value
            longValue += MUTATIONVALUE;
            //Convert mutated value to Hex
            encryptedValue += Long.toHexString(longValue);
            //Add character delimiter
            if (delimiterIndex >= CHARDELIMITERS.length) {
                delimiterIndex = 0;
            }
            encryptedValue += CHARDELIMITERS[delimiterIndex];
            delimiterIndex++;
        }
        return encryptedValue;
    }
}
