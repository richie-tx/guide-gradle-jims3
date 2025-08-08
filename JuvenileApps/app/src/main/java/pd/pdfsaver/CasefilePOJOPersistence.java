/*
 * Created on Sep 07, 2007
 *
 */
package pd.pdfsaver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import pd.juvenilecase.casefile.CommonAppDocMetadata;

/**
 * @author mchowdhury
 *
 */
public class CasefilePOJOPersistence extends FileUtility{
    
	final static String insertPDF = "INSERT INTO JIMS2.JCCOMMONAPPDOC (CASEFILE_ID, DOCUMENT, CREATEUSER) values(?, ?, ?)";
	final static String updatePDF = "UPDATE JIMS2.JCCOMMONAPPDOC SET DOCUMENT = ? WHERE CASEFILE_ID = ?";
	final static String insertCasefile = "INSERT INTO JIMS2.JCCASEFILE (JUVENILE_ID, CASESTATUSCD,SPRVSIONTYPECD, OFFICER_ID,CREATEUSER) values(?,?,?,?,?)";
	final static String query = "SELECT CASEFILE_ID FROM JIMS2.JCCASEFILE WHERE JUVENILE_ID = ? ORDER BY CASEFILE_ID DESC FETCH FIRST ROW ONLY";
	final static String findJuvenile = "SELECT JUVENILE_ID FROM JIMS2.JCJUVENILE WHERE JUVENILE_ID = ?";
	final static String findPdf = "SELECT CASEFILE_ID, DOCUMENT FROM JIMS2.JCCOMMONAPPDOC WHERE CASEFILE_ID LIKE '%'";

	protected static void insertPDF(CommonAppDocMetadata pObj, Connection con, Logger log) throws ClassNotFoundException
    {
        PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(insertPDF);
			pstmt.setString(1, pObj.getCasefileId());
			pstmt.setBytes(2, (byte[]) pObj.getDocument());	
			pstmt.setString(3, "COAPP");	
			int rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			log.severe("Exception during inserting the PDF data: " + e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				log.severe("Connection Rollback for the casefile " + pObj.getCasefileId());
			} 
		}finally{
			try {
				pstmt.close();
			} catch (SQLException e1) {
				log.severe("Exception= " + e1.getMessage());
				e1.printStackTrace();
			}
		}
    }
	
	protected static int insertCasefile(String juvenileId, Connection con, Logger log) throws ClassNotFoundException
    {
        PreparedStatement pstmt = null;
        int casefileId = 0;
		try {
			pstmt = con.prepareStatement(insertCasefile);
			pstmt.setString(1, juvenileId);
			pstmt.setString(2, "P");
			pstmt.setString(3, "CS");
			pstmt.setInt(4, 1037);
			pstmt.setString(4, "COAPP");
			casefileId = pstmt.executeUpdate();
		} catch (SQLException e) {
			log.severe("Exception during creating a new casefile for Juvenile " + juvenileId + ": "+ e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				log.severe("Connection Rollback from creating a new casefile for Juvenile " + juvenileId);
			} 
		}
		finally{
			try {
				pstmt.close();
			} catch (SQLException e1) {
				log.severe("Exception din closing a connection " + juvenileId + ": "+ e1.getMessage());
				e1.printStackTrace();
			}
		}
		return casefileId;
    }

	protected static int retrieveCasefile(String juvenileId, Connection con, Logger log) throws ClassNotFoundException
    {
		int casefileId = 0;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, juvenileId);
			result = pstmt.executeQuery();
						
			if (result.next()){
				casefileId = result.getInt(1);
            }
		} catch (SQLException e) {
			log.severe("Exception " + e.getMessage());
		} finally{
			try {
				result.close();
				pstmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				log.severe("Exception " + e1.getMessage());
			}
		}
		return casefileId;
    }
	
	/*protected static void retrievePDF(Connection con, Logger log) throws ClassNotFoundException
    {
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			pstmt = con.prepareStatement(findPdf);
			result = pstmt.executeQuery();
						
			while (result.next()){
				String str = result.getString(2);
				int casefileId = result.getInt(1);
				
				File file = new File("../../../../old/" + casefileId + ".pdf");
				try {
					write3File(file.getAbsolutePath(), str);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
            }
		} catch (SQLException e) {
			log.severe("Exception " + e.getMessage());
		} finally{
			try {
				result.close();
				pstmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				log.severe("Exception " + e1.getMessage());
			}
		}
    }*/
}