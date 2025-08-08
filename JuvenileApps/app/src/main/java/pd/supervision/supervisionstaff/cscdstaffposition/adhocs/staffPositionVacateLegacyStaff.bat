set VIEW=c:\views\Maint_CS

set APP=%VIEW%\app

set LIB=%APP%\lib

set CONFIG=%VIEW%\Arch\build\configuration\dev

set LIB2=%APP%\archcomponents\ear\jims2

set JAVA="C:\Program Files\IBM\SDP\runtimes\base_v61\java"

set classpath=.;%LIB%\commons-collections.jar;%LIB2%\commons-lang.jar;%LIB2%\j204.jar;%LIB%\db2jcc.jar;%LIB%\db2jcc_license_cisuz.jar;%LIB2%\log4j.jar;%LIB%\xerces.jar;%LIB%\xercesImpl.jar;%LIB%\mojo.jar;%APP%\src\bin;%CONFIG%;%LIB%\jdom.jar;%LIB2%\log4j-optional.jar;%LIB%\jcs-1.0-dev.jar;%LIB2%\commons-logging.jar;%LIB2%\commons-pool.jar;%LIB2%\ctgclient.jar;%LIB%\db2jcc_javax.jar;%LIB%\db2policy.jar;%LIB%\db2qgjava.jar;%APP%\test\bin

%JAVA%\jre\bin\java -cp %classpath% -Xmx512M -Dmojo.config=SRVPJ1P.xml pd.supervision.supervisionstaff.cscdstaffposition.adhocs.VacateLegacyStaffPositionAdHoc SRVPJ1P.xml