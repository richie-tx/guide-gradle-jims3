package pd.common.refvariable;

import java.util.Collection;
import java.util.Iterator;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.mapping.BufferMapping;
import mojo.km.persistence.PersistentObject;
import naming.PDConstants;
import pd.juvenilecase.JuvenileCasefile;
import pd.supervision.supervisionorder.SupervisionOrderReferenceVariableHelper;

/**
 * @author bschwartz
 *
 */
public class ReferenceResolver
{
	
	
	/**
	 *
	 */
	public ReferenceResolver()
	{
	}
	

	
	
	/**
	 * @param orderId
	 * @param criminalCaseId
	 * @return
	 */
	public static void postReferenceVariables(
			String casefileId,
			Collection variableElementNames) {
		
		JuvenileCasefile casefile = null;
		
		String JUVENILECASeFILE_CLASSNAME = JuvenileCasefile.class.getName();
		
		String variableElementName = null;
		String formattedName = null;
		String entityName = null;
		MojoProperties mojoProps = MojoProperties.getInstance();
		EntityMappingProperties eProps = null;

		Iterator variableElementNameIter = variableElementNames.iterator();

		if (variableElementNameIter != null
				&& variableElementNameIter.hasNext()) {

			if (casefileId != null && !casefileId.equals("")) {
				casefile = JuvenileCasefile.find( casefileId );
			}

			BufferMapping mapper = BufferMapping.getInstance();

			while (variableElementNameIter.hasNext()) {
				variableElementName = (String) variableElementNameIter.next();
				formattedName = ReferenceResolver.formatName(variableElementName);

				//Determine if BufferMapping exists for entity
				eProps = mojoProps.getBufferMappingByFieldName(formattedName);
//				if (eProps != null) {
//					entityName = eProps.getEntity();
					//Retrieve entity data
					//if (entityName.equals(SUPERVISION_CLASSNAME)) {
	//				if (JUVENILECASeFILE_CLASSNAME.equals(entityName)) {
						
						if (casefile != null) {
							ReferenceResolver.postValue(mapper,variableElementName,casefile);
						}
				}
//			}
		}
	}
	
	/**
	 * @param mapper
	 * @param formattedName
	 * @param supervision
	 */
	public static void postValue(BufferMapping mapper,
			String variableElementName, PersistentObject persistentObject) {

		if (persistentObject != null) {
			String value = "";

			String formattedName = ReferenceResolver.formatName(variableElementName);

			if (persistentObject instanceof JuvenileCasefile) {
				JuvenileCasefile casefile = (JuvenileCasefile) persistentObject;
				value = mapper.updateBuffer(formattedName, casefile);
			}

			if (!formattedName.equals(value) && !value.equals(PDConstants.NONE)) {
				SupervisionOrderReferenceVariableHelper
						.postVariableElementResponseEvent(variableElementName,
								value, true, true);
			}
		} else {
			SupervisionOrderReferenceVariableHelper
			.postVariableElementResponseEvent(variableElementName,
					"", true, true);
		}
	}
	private static String formatName(String name) {
		StringBuffer sbName = new StringBuffer();
		if (name != null) {
			sbName = new StringBuffer(name);
			sbName.insert(0, "[");
			sbName.append("]");
		}
		return sbName.toString();
	}

	
	
}
