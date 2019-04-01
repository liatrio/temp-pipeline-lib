#!/usr/bin/env groovy

def call(String groupId, String artifactId, String version, String artifactName, String nexus="http://nexus:8081"){

	if (version.contains("SNAPSHOT")){
		url = "nexus/service/local/artifact/maven/content?r=snapshots&g=${groupId}&a=${artifactId}&v=${version}&p=war"
		
	}
	else {
		url = "nexus/service/local/artifact/maven/content?r=releases&g=${groupId}&a=${artifactId}&v=${version}&p=war"
	}
	sh "wget --user=${INITIAL_ADMIN_USER} --password=${INITIAL_ADMIN_PASSWORD} -O ${artifactName}.war \"${nexus}/${url}\""
  sh 'mkdir target'
	sh "mv ${artifactName}.war target/${artifactName}.war"
}
