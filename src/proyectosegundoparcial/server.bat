set jardir=.\lib
set CLASSPATH=%jardir%/derby.jar;%jardir%/derbytools.jar;%jardir%/derbynet.jar;%jardir%/derbyclient.jar;%jardir%/derbyLocale_es.jar;%jardir%/Derby_UP.jar;
java org.apache.derby.drda.NetworkServerControl start -h 0.0.0.0 -p 1527
pause