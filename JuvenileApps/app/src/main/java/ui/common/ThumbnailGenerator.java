/*
 * Created on Mar 21, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common;

	/*
	 * Created on Mar 20, 2006
	 *
	 * To change the template for this generated file go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;

	/**
	 * @author jjose
	 *
	 * This class has several static methods that do various things related to image thumbnail generation.
	 */
public class ThumbnailGenerator
{

		private static final int MAX_DB_THUMBNAIL_WIDTH=100;   // MAX_THUMBNAIL_WIDTH in pixels
		private static final int MAX_DB_THUMBNAIL_HEIGHT=125;  // MAX_THUMBNAIL_HEIGHT in pixels
		private static final int MAX_UI_DISPLAY_WIDTH=300;   // MAX_THUMBNAIL_WIDTH in pixels
		private static final int MAX_UI_DISPLAY_HEIGHT=350;  // MAX_THUMBNAIL_HEIGHT in pixels
		private static final String THUMB_FORMAT="jpeg";
	

	/**
	 * Tester method to see if the thumbnail rendering stuff works
	 * @param args
	 */
		public static void main(String[] args){
			try{
				byte[] bytes=readInImage("C:\\Documents and Settings\\jjose\\Desktop\\memorialPark.jpg");
				byte[] thumbnailByte=createThumbForUI(bytes);
			
			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
		}

		/**
		 * Uses the JPEG encoder classes to produce a jpeg image file and write it
		 * to specified file location
		 * @param incomingImage -- specified as a Bufferred Image
		 * @param fileLoc--- Fully qualified path name of the file name to write. 
		 */
		public static void writeAnImage(BufferedImage incomingImage, String fileLoc){				
				try{
					 // JPEG-encode the image 
					 //and write to file.
					 OutputStream os = 
					  new FileOutputStream(fileLoc);
					 ImageIO.write(incomingImage,THUMB_FORMAT, os);

					 os.close();
				}
				catch(FileNotFoundException e){
					e.printStackTrace();
				}
				catch(IOException io){
					io.printStackTrace();
				}
				return;
			}

		/**
		 * Reads in an image from a specified file location and returns the byte array
		 * represenation of this image
		 * @param fileLoc--- Fully qualified path name of the file name to READ. 

		 * @return rawData of image file
		 */
		public static byte[] readInImage(String fileLoc){
			File f = new File(fileLoc); // Create a FileItem object to access the file.
			byte[] bytes = new byte[(int)f.length()];
			try{
				FileInputStream fs = new FileInputStream(f);
				BufferedInputStream bis = new BufferedInputStream(fs);
				bis.read(bytes);
				bis.close();
				fs.close();
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
			catch(IOException io){
				io.printStackTrace();
			}
			return bytes;
		}
	
			/**
			 * Quick method to create a UI scaled image of a particular size
			 * given a byte stream
			 * @param data
			 * @return
			 * @throws Exception
			 */
			public static byte[] createThumbForUI(byte[] data) 
					throws Exception 
				{
					return createThumb(data,MAX_UI_DISPLAY_WIDTH,MAX_UI_DISPLAY_HEIGHT);
	
				}
	
		/**
		 * Quick method to create a database thumbnail image
		 * @param data
		 * @return
		 * @throws Exception
		 */
		public static byte[] createThumbForDB(byte[] data) 
				throws Exception 
			{
				return createThumb(data,MAX_DB_THUMBNAIL_WIDTH,MAX_DB_THUMBNAIL_HEIGHT);
	
			}

		/**
		 * Create a reduced jpeg version of an image. The width/height
		 * ratio is preserved.
		 *
		 * @param data raw data of the image
		 * @param thumbWidth maximum width of the reduced image
		 * @param thumbHeight maximum heigth of the reduced image
		 * @param out produce a reduced jpeg image if the image represented
		 * by data is bigger than the maximum dimensions of the reduced
		 * image, otherwise data is written to this stream 
		 */
		public static byte[] createThumb(byte[] data,
									   int thumbWidth, int thumbHeight) 
			throws Exception 
		{
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			Image image = Toolkit.getDefaultToolkit().createImage(data);
			MediaTracker mediaTracker = new MediaTracker(new Frame());
			int trackID = 0;
			mediaTracker.addImage(image,trackID);
			mediaTracker.waitForID(trackID);
			if (image.getWidth(null)<=thumbWidth && 
				image.getHeight(null)<=thumbHeight)
			result.write(data);
			else
				createThumb(image,thumbWidth,thumbHeight,result);
			return result.toByteArray();
		}

		/**
		 * Create a scaled jpeg of an image. The width/height ratio is
		 * preserved.
		 *
		 * <p>If image is smaller than thumbWidth x thumbHeight, it will be
		 * magnified, otherwise it will be scaled down.</p>
		 *
		 * @param image the image to reduce
		 * @param thumbWidth the maximum width of the thumbnail
		 * @param thumbHeight the maximum heigth of the thumbnail
		 * @param out a stream where the thumbnail data is written to 
		 */
		private static void createThumb(Image image,
									   int thumbWidth, int thumbHeight,
									   OutputStream out) 
			throws Exception 
		{
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);
			double thumbRatio = (double)thumbWidth / (double)thumbHeight;
			double imageRatio = (double)imageWidth / (double)imageHeight;
			if (thumbRatio < imageRatio) {
				thumbHeight = (int)(thumbWidth / imageRatio);
			} else {
				thumbWidth = (int)(thumbHeight * imageRatio);
			}
			// draw original image to thumbnail image object and
			// scale it to the new size on-the-fly
			BufferedImage thumbImage = 
				new BufferedImage(thumbWidth, 
								  thumbHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = thumbImage.createGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
										RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
			//writeAnImage(thumbImage,"C:\\Documents and Settings\\jjose\\Desktop\\memorialParkThumb.jpg");
//				save thumbnail image to out stream
//			ImageIO.write(thumbImage,"jpeg",out);
		}

}

