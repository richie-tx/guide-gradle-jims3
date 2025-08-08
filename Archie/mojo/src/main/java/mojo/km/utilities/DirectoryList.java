package mojo.km.utilities;

import java.io.*;
import java.util.*;

/**
 *Responsible for creating a java source directory list.
 * @modelguid {226F1CBF-1703-410B-A3EC-B998F53716AC}
 */
public class DirectoryList {
	/** @modelguid {BC3964A0-8205-42C4-8D7F-C21CF4A5618E} */
    public DirectoryList(String extension, String filterList) {
        this.filterList = filterList;
        this.extension = extension;
    }

    /**
     *    Main method builds java source list.
     * @modelguid {F2EF18F4-843B-47F6-9F5B-D6C67716A259}
     */
    static public void main(String[] args) {
        if (args.length != 4) {
			System.out.println("Usage:  java mojo.km.utilities.DirectoryList <path> <search extension .java, .class> <filter buffer, E.G. PresentationLayer;CommonServiceLayer> <MAKEVAR>");
            return;
        }
        System.out.print(args[3] + " = ");
        DirectoryList dirList = new DirectoryList(args[1], args[2]);
        dirList.printSubdirs(args[0]);
    }

	/** @modelguid {BC3F545E-8ECC-47D9-8035-5433BC2DA7C8} */
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
                boolean printFlag = false;
                String[] fileList = aFile.list();
                for (int i = 0; i < fileList.length; i++) {
                    printSubdirs(aFile.getPath() + "\\" + fileList[i]);
                    if (fileList[i].endsWith(extension)) {
                        printFlag = true;
                    }
                }
                if (printFlag)
                    System.out.print(" \\\r\n\t" + aFile.getPath() + "\\*" + extension);
            }
        }
    }

	/** @modelguid {826226ED-3883-4951-B1DE-884C330B8514} */
    String filterList = null;
	/** @modelguid {F441FDAD-A2C2-493D-B3D6-1B74D6CEF494} */
    String extension = null;
}
