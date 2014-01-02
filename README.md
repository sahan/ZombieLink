<table>
<tr>
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://raw.github.com/sahan/ZombieLink/master/logo.png"/>
</td>
<td rowspan="3">
<font color="#1C1C1C"><b>ZombieLink</b> &nbsp;&nbsp;/zŏm'bē'lĭngk/ &nbsp;&nbsp;<em>noun.</em></font> 
<br><br>
<font color="#424242">
<b>1.</b> A lightweight HTTP facade which simplifies network communication. &nbsp; <b>2.</b> Accepts an interface which describes the remote service and gives you an implementation of it.
</font>
<br><br>
<a href="https://travis-ci.org/sahan/ZombieLink"><img alt="Build Status" src="https://travis-ci.org/sahan/ZombieLink.png?branch=master"></a>&nbsp;&nbsp;
<a href="https://coveralls.io/r/sahan/ZombieLink?branch=master"><img alt="Coverage Status" src="https://coveralls.io/repos/sahan/ZombieLink/badge.png?branch=master"></a>
</td>
</tr>
<tr>
<td>
<a href="http://repo1.maven.org/maven2/com/lonepulse/zombielink/1.3.1/zombielink-1.3.1.jar"><pre>zombielink-1.3.1.jar</pre></a>
</td>
</tr>
</table>

<br>
##Overview

Here's your model.   

```java
public class Repo {

    private String id;
    private String name;
    private boolean fork;
    private int stargazers_count;
    
    ...
}
```

<br>
Define the endpoint.   

```java
@Deserialize(JSON)
@Endpoint("https://api.github.com")
public interface GitHubEndpoint {

    @GET("/users/{user}/repos")
    List<Repo> getRepos(@PathParam("user") String user);
}
```
> Looks for [Gson](http://code.google.com/p/google-gson) on your build path.   

<br>
Inject and invoke.   

```java
@Bite
private GitHubEndpoint endpoint;   

{
    Zombie.infect(this);
}

...

List<Repo> repos = endpoint.getRepos("sahan");
```
<br>
Create as many endpoints as you want...   

```java
@Endpoint("http://example.com")
public interface ExampleEndpoint {

    @Serialize(XML)	
    @PUT("/content")
    void putContent(@Entity Content content);
}
```
> Looks for [Simple-XML](http://simple.sourceforge.net) on your build path.

<br>
...and inject 'em all.   

```java
@Bite
private GitHubEndpoint gitHubEndpoint;

@Bite
private ExampleEndpoint exampleEndpoint;

{
    Zombie.infect(this);
}
```

<br>
##Setup
> If you opt to use the out-of-the-box JSON (de)serializer add the [Gson](http://code.google.com/p/google-gson) dependency; like wise add the [Simple-XML](http://simple.sourceforge.net) dependency for the XML (de)serializer.    

<br>
### 1. For Maven based projects.   

Add the following dependency to project's pom.xml file.

```xml
<dependency>
   <groupId>com.lonepulse</groupId>
   <artifactId>zombielink</artifactId>
   <version>1.3.1</version>
</dependency>
```

<br>   
### 2. For Gradle based projects.   

Add the following repository and dependency to your project's build.gradle file.

```groovy
repositories {
    mavenCentral()
}

dependencies {
    compile 'com.lonepulse:zombielink:1.3.1'
}
```

<br>   
### 3. Add the JAR to your build path manually.   

Download the [ZombieLink-1.3.1.jar](http://repo1.maven.org/maven2/com/lonepulse/zombielink/1.3.1/zombielink-1.3.1.jar) 
and add it to your **libs** folder.   
> Note that [Gson](http://search.maven.org/remotecontent?filepath=com/google/code/gson/gson/2.2.4/gson-2.2.4.jar) 
is required for JSON (de)serialization and [Simple-XML](http://search.maven.org/remotecontent?filepath=org/simpleframework/simple-xml/2.7.1/simple-xml-2.7.1.jar) 
is required for XML (de)serialization.   

<br>
##Wiki

Coding with ZombieLink is a breeze. It follows a simple annotation based coding style and adheres to a *minimal intrusion* policy. 
Kickoff with the [quickstart](https://github.com/sahan/ZombieLink/wiki/Quickstart) and follow the rest of the wiki pages. 

1. [Quickstart](https://github.com/sahan/ZombieLink/wiki/Quickstart)

2. [Defining, Injecting and Invoking](https://github.com/sahan/ZombieLink/wiki/Defining,-Injecting-and-Invoking)

3. [Identifying HTTP Methods](https://github.com/sahan/ZombieLink/wiki/Identifying-HTTP-Methods)

4. [Sending Query and Form Parameters](https://github.com/sahan/ZombieLink/wiki/Sending-Query-and-Form-Parameters)

5. [Sending a Request Body](https://github.com/sahan/ZombieLink/wiki/Sending-a-Request-Body)

6. [Serializing Request Content](https://github.com/sahan/ZombieLink/wiki/Serializing-Request-Content)

7. [Receiving a Response Body](https://github.com/sahan/ZombieLink/wiki/Receiving-a-Response-Body)

8. [Deserializing Response Content](https://github.com/sahan/ZombieLink/wiki/Deserializing-Response-Content)

7. [Sending and Receiving Headers](https://github.com/sahan/ZombieLink/wiki/Sending-and-Receiving-Headers)

8. [Executing Requests Asynchronously](https://github.com/sahan/ZombieLink/wiki/Executing-Requests-Asynchronously)

9. [Creating Stateful Endpoints](https://github.com/sahan/ZombieLink/wiki/Creating-Stateful-Endpoints)

10. [Intercepting Requests](https://github.com/sahan/ZombieLink/wiki/Intercepting-Requests)

11. [Overriding, Detaching and Skipping Components](https://github.com/sahan/ZombieLink/wiki/Overriding,-Detaching-and-Skipping-Components)

12. [Wiring and Injecting Endpoints](https://github.com/sahan/ZombieLink/wiki/Wiring-and-Injecting-Endpoints)

13. [Configuring ZombieLink](https://github.com/sahan/ZombieLink/wiki/Configuring-ZombieLink)
<br><br>   

##License
This library is licensed under [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
