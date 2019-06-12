# JAX-RS on Weblogic 12c Minimal Demo

## Prerequisites

weblogic-maven-plugin is used to deploy the resulting WAR-file to weblogic. 

In order to get this plugin you need to do the steps described at http://docs.oracle.com/middleware/1213/core/MAVEN/config_maven_repo.htm#MAVEN9015 (you need an Oracle account, accept their terms and configure the oracle maven repository and your credentials in settings.xml)


In order to use "start-weblogic.sh" (dockerized WLS 12c) you also need an Oracle account, go to https://container-registry.oracle.com and accept their terms.

After that you need to build the "dmn1k/wls"-image which you can build yourself from source at https://github.com/dmn1k/dockerfiles

## Build and deploy

First start a dockerized WLS 12c instance via

```
./start-weblogic.sh
```

Then build and deploy via

```
./deploy.sh
```

or directly with maven:

```
mvn clean install weblogic:deploy
```