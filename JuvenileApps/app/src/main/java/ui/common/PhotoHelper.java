/**
 * 
 */
package ui.common;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * @author cc_cwalters
 *
 */
public class PhotoHelper 
{
	private static final String MIME_TYPE_IMAGE = "image/jpg";

	public static boolean returnPhoto(
			HttpServletResponse aResponse, 
				byte[] photoBytes) throws Exception 
	{
		try 
		{
			OutputStream out = aResponse.getOutputStream();
			if (photoBytes != null && photoBytes.length > 0) 
			{
				aResponse.setContentType(MIME_TYPE_IMAGE);
				out.write(photoBytes);
				out.flush();
				return true;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
