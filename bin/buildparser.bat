@echo off

REM If there is a generated javacc.jar in the base directory, use that, otherwise, use
REM the bootstrap javacc.jar that is in the bin directory.

java -classpath "%~f0\..\..\javacc.jar;%~f0\..\javacc.jar;%~f0\..\freemarker.jar" javacc.Main %~f0\..\..\src\grammars\JavaCC.javacc

