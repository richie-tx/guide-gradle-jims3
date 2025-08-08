package pd.pattern;

/**
 * @author Jim Fisher
 *
 * Separates the construction of a complex object from its representation so that 
 * the same construction process can create different representations.
 */
public interface IBuilder
{
	public void build();
	public Object getResult();
}
