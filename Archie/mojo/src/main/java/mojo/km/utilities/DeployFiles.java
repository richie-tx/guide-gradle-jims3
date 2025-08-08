package mojo.km.utilities;

import java.io.*;
import java.util.*;

/** Responsible for creating a java source directory list. 
 * @modelguid {F8F0FE43-D0CC-4368-A202-2A64B1526301}
 */
public class DeployFiles {
	/** @modelguid {8A06740A-F159-4E7B-972C-DE7AE5FEF53C} */
    public DeployFiles(String extension, String filterList, String destination) {
        this.filterList = filterList;
        this.extension = extension;
        this.destination = destination;
    }

    /** Main method builds java source list. 
     * @modelguid {927D556C-5238-428C-89AB-4085E76D4ED6}
     */
    static public void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage:  java mojo.km.utilities.DeployFiles <path> <search extension .java, .class> <filter buffer, E.G. PresentationLayer;CommonServiceLayer> <MAKEVAR>");
            return;
        }
        //System.out.print(args[3] + " = ");
        DeployFiles dirList = new DeployFiles(args[1], args[2], args[3]);
        dirList.copySubdirs(args[0]);
       // System.out.println("c:\ncd \\build");
       // System.out.println("java weblogic.jspc -d " + args[3] + "\\WEB-INF\\classes *.jsp");
    }

	/** @modelguid {033DBF4E-051A-463F-BB91-78F1D413CEB2} */
    private void copySubdirs(String fileName) {
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
                    copySubdirs(aFile.getPath() + "\\" + fileList[i]);
                    if (fileList[i].endsWith(extension)) {
                        printFlag = true;
                    }
                }
                if (printFlag) {
                    //try {
                       System.out.println("copy " + aFile.getPath() + "\\*" + extension + " " + destination);
                    //} catch (java.io.IOException e) { }
                }
            }
        }
    }

	/** @modelguid {3A607684-885B-4ABE-819C-3B5F0DD4DD45} */
    String filterList = null;
	/** @modelguid {859526CA-FF06-4331-8AD1-F235CE480E68} */
    String extension = null;
	/** @modelguid {75446DFF-39CD-4E73-B874-7B5113A9C6CB} */
    String destination = null;
}
