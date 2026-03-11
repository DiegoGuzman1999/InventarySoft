@REM Maven Wrapper startup batch script, for Windows
@REM ----------------------------------------------------------------------------
@echo off
setlocal

set "MVNW_REPOURL="
set "MVNW_VERBOSE=false"

set "BASEDIR=%~dp0"
if "%BASEDIR:~-1%"=="\" set "BASEDIR=%BASEDIR:~0,-1%"

set "WRAPPER_JAR=%BASEDIR%\.mvn\wrapper\maven-wrapper.jar"
set "WRAPPER_PROPERTIES=%BASEDIR%\.mvn\wrapper\maven-wrapper.properties"

if exist "%WRAPPER_JAR%" goto run

if not exist "%WRAPPER_PROPERTIES%" (
  echo [ERROR] Maven Wrapper properties not found: "%WRAPPER_PROPERTIES%"
  exit /b 1
)

for /f "usebackq delims=" %%A in (`powershell -NoProfile -Command "$line = (Select-String -Path '%WRAPPER_PROPERTIES%' -Pattern '^wrapperUrl=' | Select-Object -First 1).Line; if ($null -ne $line) { $line.Substring(11) }"` ) do set "WRAPPER_URL=%%A"

if "%WRAPPER_URL%"=="" (
  echo [ERROR] wrapperUrl not found in "%WRAPPER_PROPERTIES%"
  exit /b 1
)

echo Downloading Maven Wrapper jar...
powershell -NoProfile -Command ^
  "$ProgressPreference='SilentlyContinue';" ^
  "New-Item -ItemType Directory -Force -Path '%BASEDIR%\\.mvn\\wrapper' | Out-Null;" ^
  "Invoke-WebRequest -UseBasicParsing -Uri '%WRAPPER_URL%' -OutFile '%WRAPPER_JAR%';"
if errorlevel 1 (
  echo [ERROR] Failed to download Maven Wrapper jar from "%WRAPPER_URL%"
  exit /b 1
)

:run
set "JAVA_EXE=java"
if defined JAVA_HOME set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"

"%JAVA_EXE%" -classpath "%WRAPPER_JAR%" "-Dmaven.multiModuleProjectDirectory=%BASEDIR%" org.apache.maven.wrapper.MavenWrapperMain %*
endlocal
