@echo off

rem install htrace dependencies


echo install json-simple
pause
call mvn install:install-file "-DgroupId=taobao.sdk.auto" "-DartifactId=taobao-sdk-java-auto" "-Dversion=1.1.1" "-Dpackaging=jar" "-Dfile=D:\tbk\taobao-sdk-java-auto.jar"

echo install json-simple
pause
call mvn install:install-file "-DgroupId=taobao.sdk.source" "-DartifactId=taobao-sdk-java-auto-source" "-Dversion=1.1.1" "-Dpackaging=jar" "-Dfile=D:\tbk\taobao-sdk-java-auto-source.jar"
