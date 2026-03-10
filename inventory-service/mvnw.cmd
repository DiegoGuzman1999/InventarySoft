@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements. See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership. The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the "License");
@REM you may not use this file except in compliance with the License.
@REM You may obtain a copy of the License at
@REM
@REM      https://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script, version 3.3.2
@REM ----------------------------------------------------------------------------

@IF "%__MVNW_ARG0_NAME__%"=="" (SET __MVNW_ARG0_NAME__=%~nx0)
@SET BASE_DIR=%~dp0
@SET "WRAPPER_JAR=%BASE_DIR%.mvn\wrapper\maven-wrapper.jar"
@REM BASE_DIR has trailing backslash; remove for multiModuleProjectDirectory (Maven 3.9.x)
@SET "PROJECT_DIR=%BASE_DIR%"
@SET PROJECT_DIR=%PROJECT_DIR:~0,-1%

@IF NOT EXIST "%WRAPPER_JAR%" (
  @ECHO Downloading Maven Wrapper jar...
  @IF NOT EXIST "%BASE_DIR%.mvn\wrapper\MavenWrapperDownloader.java" (
    @ECHO Error: Missing MavenWrapperDownloader.java
    @EXIT /B 1
  )
  @pushd "%BASE_DIR%"
  javac ".mvn\wrapper\MavenWrapperDownloader.java"
  @IF ERRORLEVEL 1 (
    @popd
    @EXIT /B 1
  )
  java -cp ".mvn\wrapper" MavenWrapperDownloader "%BASE_DIR%"
  @popd
)

@REM Pass multiModuleProjectDirectory to JVM so wrapper/Maven 3.9.x can use it
@IF DEFINED JAVA_HOME (
  "%JAVA_HOME%\bin\java" -Dmaven.multiModuleProjectDirectory="%PROJECT_DIR%" -jar "%WRAPPER_JAR%" %*
) ELSE (
  java -Dmaven.multiModuleProjectDirectory="%PROJECT_DIR%" -jar "%WRAPPER_JAR%" %*
)
