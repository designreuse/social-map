#!/bin/sh
# This script can be used to automatically build and deploy this project
# Add following lines to your ~/.bash_profile
# export TOMCAT_PATH=path to tomcat without ending slash
# export SOCIAL_MAP_PATH=path to project without ending slash

sh $TOMCAT_PATH/bin/catalina.sh stop
cd $SOCIAL_MAP_PATH
mvn clean install -DskipTests -o
rm -r $TOMCAT_PATH/webapps/social-map*
cp rest/target/social-map.war $TOMCAT_PATH/webapps
sh $TOMCAT_PATH/bin/catalina.sh jpda run
