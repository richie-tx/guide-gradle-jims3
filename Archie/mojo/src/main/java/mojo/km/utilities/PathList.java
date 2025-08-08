package mojo.km.utilities;

import java.io.*;
import java.util.*;

/**
Responsible for creating a java source directory list.
 * @modelguid {4D0441CA-BA2F-410B-A240-22D971457870}
 */
public class PathList {    
	/** @modelguid {DB2FB1B7-6724-4BC7-AF25-7371DB2A861B} */
    public PathList(String filterList) {
        this.filterList = filterList;
    }
    
    /**
    Main method builds java source list.
	 * @modelguid {482FD694-9159-4548-B0D6-D97C3F828F33}
	 */
    static public void main(String[] args)
    {
        
        if (args.length != 2) {
            System.out.println("Usage:  java CommonServiceLayer.Utilities.PathList <path> <filter list>");
            return;
        }
        
        PathList dirList = new PathList(args[1]);
        dirList.printSubdirs(args[0]);
    }
    
	/** @modelguid {EB3D73E4-CC88-4F92-A404-B0D7BC21584D} */
    private void printSubdirs(String fileName) {
        boolean flag = true;
        File aFile = new File(fileName);
        if (aFile.isDirectory()) {
            StringTokenizer aTok = new StringTokenizer(filterList, ";");
            while (aTok.hasMoreTokens()) {
                String token = aTok.nextToken();
                if (fileName.endsWith(token)) {
                    flag = false;
                }
            }
            if (flag) {
                System.out.print(aFile.getPath() + "\\;");
                String[] fileList = aFile.list();
                for (int i = 0; i < fileList.length; i++) {
                    printSubdirs(aFile.getPath() + "\\" + fileList[i]);
                }
            }
        }
    }
    
	/** @modelguid {2B8FE43A-E816-4B14-9514-BB033419F0CB} */
    String filterList = null;
}