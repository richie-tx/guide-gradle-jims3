/* SequenceFileGenerator.java
 */

package mojo.km.utilities;

// java stuff
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import mojo.km.properties.IResourceService;
import mojo.km.utilities.SequenceGenerator;
import java.util.Date;
import java.util.Calendar;

/** @modelguid {A0CA1D45-584A-4AA0-A243-B2181D067223} */
public class SequenceFileGenerator extends SequenceGenerator
{
	/** @modelguid {A6E7956F-71B9-41AE-ABD1-C3CF06760871} */
    final static public String SEQUENCE_FILE_PROP = "seqFile";
	/** @modelguid {A61FAA5F-8C7A-4D07-9635-FA5BC291777F} */
    private String seqGenFile = "";

    //================================================================================
    //                          PUBLIC METHODS
    //================================================================================

	/** @modelguid {6AA98DA3-ED3D-4DBB-983D-68CFDA99D79A} */
    public SequenceFileGenerator( IResourceService resourceService )
    {
        super( resourceService );
        initFromResources();
    }

    //================================================================================
    //                    PROTECTED & PRIVATE METHODS
    //================================================================================

	/** @modelguid {DEEB8B44-8BFE-4912-892D-9C5F01B2A463} */
    private void initFromResources()
    {
        seqGenFile = resourceService.getResourceString( SEQUENCE_FILE_PROP );
        if( (seqGenFile != null) && (seqGenFile.length() > 0) ) {
            // If the path mistakenly ends in a slash, remove it
            if( seqGenFile.endsWith( "/" ) ) 
                seqGenFile = seqGenFile.substring( 0, seqGenFile.length() - 1 );
        }
        // Does the file exist (yet)? If not, create one
        File file = getSeqFile();
        if( !file.exists() ) {
            archiveNextNum( resetValue );
        }
    }

	/** @modelguid {CA42A7FD-141B-4330-B4E4-539DA401F8B4} */
    protected long nextNumSpecific()
    {
        long ret = -1;
        ObjectInputStream reader = null;

        try {
            if(debug) System.out.println( "Reading file named: " + seqGenFile );
            File seqFile = getSeqFile();
            FileInputStream   fileStream = new FileInputStream( seqFile );
                              reader     = new ObjectInputStream( fileStream );
            // Has the file somehow been been completely truncated?
            if( fileStream.available() > 0 ) {
                Long nextNum = (Long)reader.readObject();
                ret = nextNum.longValue();
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
            System.err.println( "[SequenceFileGenerator.nextNum] " + e );
        }
        finally {
            if( reader != null ) {
                try { reader.close(); }
                catch( IOException ioe ) {}
            }
            if( ret == -1 ) ret = 1;
            archiveNextNum( ret + 1 );
            return ret;
        }
    }

	/** @modelguid {594F9F22-2BF2-49BB-A6CA-5BC581E46F4B} */
    protected void resetSpecific(long val)
    {
        archiveNextNum(val);
    }

	/** @modelguid {5C5F90E1-B2A0-4B32-A433-94070EAF7538} */
    protected Calendar getLastModified()
    {
        File seqFile = getSeqFile();
        Date lDate = new Date(seqFile.lastModified());
        Calendar ret = Calendar.getInstance();
        ret.setTime(lDate);
        return ret;
    }

	/** @modelguid {671FAF58-13DA-41C1-B24D-446A80B03CA3} */
    private void archiveNextNum( long num ) 
    {
        ObjectOutputStream writer = null;

        try {
            if(debug) System.out.println( "Write file named: " + seqGenFile );
            FileOutputStream fileStream = new FileOutputStream( seqGenFile );
                             writer     = new ObjectOutputStream( fileStream );
            Long nextNum = new Long( num );
            writer.writeObject( nextNum );
            // NOTE: since a File maintains a lastModified date on it own,
            // we do NOT need to set lastModified = new Date().
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
            System.err.println( "[SequenceFileGenerator.archiveNextNum] " + e );
        }
        finally {
            if( writer != null ) {
                try { writer.close(); }
                catch( IOException ioe ) {}
            }
        }
    }

	/** @modelguid {A3641256-8F98-42E8-8951-EA831F45B0D3} */
    private File getSeqFile()
    {
        return new File(seqGenFile);
    }
}

