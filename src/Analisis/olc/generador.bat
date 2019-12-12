SET JFLEX_HOME= C:\libs\jflex-1.6.1
cd C:\Users\erick\Documents\NetBeansProjects\MBR_201213050\src\Analisis\olc\
java -jar %JFLEX_HOME%\lib\jflex-1.6.1.jar scanner.jflex

pause


SET JFLEX_HOME= C:\libs\java-cup-11b
cd C:\Users\erick\Documents\NetBeansProjects\MBR_201213050\src\Analisis\olc\
 java -jar %JFLEX_HOME%\java-cup-11b.jar -parser parserOlc parser.cup

pause