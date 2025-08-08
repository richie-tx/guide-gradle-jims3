package pd.juvenilewarrant;

/**
 * @author Jim Fisher
 *  
 */
public class JuvenileWarrantBuilder extends JuvenileWarrantLightBuilder
{
    public JuvenileWarrantBuilder(JuvenileWarrant warrant)
    {
        super(warrant);
    }

    /*
     * (non-Javadoc)
     * 
     * @see pd.pattern.IBuilder#build()
     */
    public void build()
    {
        super.build();
        super.setWarrantDetails();
        super.setFileStampValues();
        super.setCautionCodes();
        super.setScarsMarksTattoosCodes();
        super.setOfficerValues();
        super.setJuvenileValues();

        if (super.warrant.isJJS())
        {
            super.setSignatureValues();
        }
        super.setWarrantOriginator();
    }    
}
