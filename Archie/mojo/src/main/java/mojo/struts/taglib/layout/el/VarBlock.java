package mojo.struts.taglib.layout.el;

import java.lang.reflect.InvocationTargetException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author jer80876
 */
public class VarBlock implements Block {
	
	private String name;
	private String property;
	
	public VarBlock(String in_name, String in_property) {
		name = in_name;
		property = in_property;	
	}

	/**
	 * @see com.beaufouripsen.seas.presentation.taglib.el.Block#evaluate(PageContext)
	 */
	public String evaluate(PageContext in_pg)  throws EvaluationException {
		Object lc_bean = in_pg.findAttribute(name);
		
		if (lc_bean==null) {
			return "";	
		}
		if (property!=null) {
			Object lc_value = null;
			try {
				lc_value = PropertyUtils.getProperty(lc_bean, property);
			} catch (NoSuchMethodException e) {
				throw new EvaluationException("No method to get the property " + property + " on a " + lc_bean.getClass().getName());
			} catch (InvocationTargetException e) {
				throw new EvaluationException("Getter of property " + property + " failed");
			} catch (IllegalAccessException e) {
				throw new EvaluationException("Illegal access to property " + property);	
			}
			if (lc_value==null) {
				return "";	
			} else {
				return lc_value.toString();	
			}
		}
		return lc_bean.toString();
	}

}
