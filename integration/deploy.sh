#!/bin/sh
projectPath=/home/sadupa/projects/winter-code-fest/social-map
tomcatPath=/usr/local/apache-tomcat-6.0.37

sh $tomcatPath/bin/catalina.sh stop
cd $projectPath
mvn clean install -DskipTests -o
rm -r $tomcatPath/webapps/social-map*
cp rest/target/social-map.war $tomcatPath/webapps
sh $tomcatPath/bin/catalina.sh jpda run
