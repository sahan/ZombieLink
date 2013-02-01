![ZombieLink](http://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/ZombieLink_Banner.png/1024px-ZombieLink_Banner.png)

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

##Wiki
Coding with ZombieLink is a breeze. It follows a simple annotation based coding style 
and adheres to a *minimal intrusion* policy.   


1. [Setup](https://github.com/sahan/ZombieLink/wiki/Setup)

2. [Quickstart](https://github.com/sahan/ZombieLink/wiki/Quickstart)

3. [Defining Endpoint Contracts](https://github.com/sahan/ZombieLink/wiki/Defining-Endpoint-Contracts)

4. [Working With Response Parsers](https://github.com/sahan/ZombieLink/wiki/Working-With-Response-Parsers)

5. [Injecting Endpoint Proxies](https://github.com/sahan/ZombieLink/wiki/Injecting-Endpoint-Proxies)

6. [Accessing RESTful Services](https://github.com/sahan/ZombieLink/wiki/Accessing-RESTful-Services)   
<br/>

##License
This library is licensed under [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
