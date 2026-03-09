@ECHO OFF
SETLOCAL

SET BASE_DIR=%~dp0
SET WRAPPER_JAR=%BASE_DIR%\.mvn\wrapper\maven-wrapper.jar

IF NOT EXIST "%WRAPPER_JAR%" (
  ECHO Downloading Maven Wrapper jar...
  IF NOT EXIST "%BASE_DIR%\.mvn\wrapper\MavenWrapperDownloader.java" (
    ECHO Missing MavenWrapperDownloader.java
    EXIT /B 1
  )
  REM Compile downloader and fetch wrapper jar.
  pushd "%BASE_DIR%"
  javac ".mvn\wrapper\MavenWrapperDownloader.java"
  IF ERRORLEVEL 1 (
    popd
    EXIT /B 1
  )
  java -cp ".mvn\wrapper" MavenWrapperDownloader "%BASE_DIR%"
  popd
)

IF DEFINED JAVA_HOME (
  "%JAVA_HOME%\bin\java" -jar "%WRAPPER_JAR%" %*
) ELSE (
  java -jar "%WRAPPER_JAR%" %*
)

ENDLOCAL

