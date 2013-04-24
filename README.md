![ZombieLink](https://raw.github.com/sahan/ZombieLink/master/logo.png)

> **ZombieLink** /zŏm'bē'lĭngk/ <em>noun.</em> **1** A lightweight HTTP facade 
which simplifies network communication. **2** An endpoint proxy generator for web services. 
[![Build Status](https://travis-ci.org/sahan/ZombieLink.png?branch=master)](https://travis-ci.org/sahan/ZombieLink)

<br/>
##About

**ZombieLink** allows easy integration with remote services by allowing you to replicate an endpoint 
contract and generate a proxy to access it.   

* Contracts can be very flexible in terms of the resources they access. These could be vary from static 
*html* content or an *RRS* feed, to a RESTful web service endpoint.   
<br/>
* Each endpoint contract is specified on a single interface using annotations to provide the communication 
metadata. It is then wired into your code via an annotation, where it'll be created, cached and injected at 
runtime.   
<br/>

##Setup

### 1. For Maven Projects
Simply add it as a dependency in your [Maven](http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html) 
project's **pom.xml**.

```xml
<dependency>
   <groupId>com.lonepulse</groupId>
   <artifactId>zombielink</artifactId>
   <version>1.2.3</version>
</dependency>
```
      
> For documentation, resolve dependencies with   
```bash
$ mvn dependency:resolve -Dclassifier=javadoc
```   
   
<br/>
### 2. For Non-Maven Projects

For projects which use an alternative build tool, clone the repository and package with Maven to find the uberjar 
in the target directory. 

```bash
$ git clone git://github.com/sahan/ZombieLink.git
$ cd ZombieLink
$ mvn package
```
   
Attach documentation using [ZombieLink-1.2.3-javadoc.jar](http://repo1.maven.org/maven2/com/lonepulse/zombielink/1.2.3/zombielink-1.2.3-javadoc.jar).   
<br/>

##Usage
Coding with ZombieLink is a breeze. It follows a simple annotation based coding style 
and adheres to a *minimal intrusion* policy. Kickoff with the [quickstart](https://github.com/sahan/ZombieLink/wiki/Quickstart) 
and follow the rest of the wiki pages. 

1. [Quickstart](https://github.com/sahan/ZombieLink/wiki/Quickstart)

2. [Defining Endpoint Contracts](https://github.com/sahan/ZombieLink/wiki/Defining-Endpoint-Contracts)

3. [Working With Response Parsers](https://github.com/sahan/ZombieLink/wiki/Working-With-Response-Parsers)

4. [Injecting Endpoint Proxies](https://github.com/sahan/ZombieLink/wiki/Injecting-Endpoint-Proxies)

5. [Accessing RESTful Services](https://github.com/sahan/ZombieLink/wiki/Accessing-RESTful-Services)   

6. [Executing Requests Asynchronously](https://github.com/sahan/ZombieLink/wiki/Executing-Requests-Asynchronously)   
<br/>

##License
This library is licensed under [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
