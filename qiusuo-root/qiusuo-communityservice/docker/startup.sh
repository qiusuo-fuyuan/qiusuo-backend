#!/bin/bash

#########Put key store path into environment variable.
####Use Bash to read the environment variable.
####Set the TECH_BLOG HOME environment variable
source
echo "Starting Qiusuo-CommunityService"
if [ -z "$QiuSuo_CommunityService_HOME" ]
then
  echo "QiuSuo_Community_HOME not set"
  exit
fi


export QIUSUO_COMMUNITYSERVICE_CLASSPATH="${TECH_BLOG_HOME}:${KEY_STORE}"
exec java  -classpath "${TECH_BLOGS_CLASSPATH}" $JAVA_OPTS  -Dloader.main=com.qiusuo.techblogs.TechBlogsApplication  org.springframework.boot.loader.PropertiesLauncher
