package pd.juvenilecase.refvariable;

import java.util.Map;

import pd.common.refvariable.AbstractResolver;
import pd.contact.officer.OfficerProfile;
import pd.juvenilecase.JuvenileCasefile;

/**
 * @author bschwartz
 *
 */
public class CasefileProbationOfficerNameResolver extends AbstractResolver
{
	
	public CasefileProbationOfficerNameResolver()
	{
		super( "ProbationOfficer", "casefile" );
	}

	/* (non-Javadoc)
	 * @see pd.common.refvariable.NameResolver#resolve(java.util.Map)
	 */
	public Object resolve( Map context )
	{
		String casefileId = (String)context.get( "casefileId" );
		
		JuvenileCasefile casefile = JuvenileCasefile.find( casefileId ); 

		OfficerProfile officer = casefile.getProbationOfficer();

		StringBuffer fullName = new StringBuffer();

		fullName.append(officer.getFirstName());
		fullName.append(" ");
		fullName.append(officer.getLastName());

		return fullName.toString();
	}

}
