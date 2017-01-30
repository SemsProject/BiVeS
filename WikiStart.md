/BiVeS Framework 
=================
[[Image(source:doc/dependency-graph.png, align=right, 380px, border=2, title="dependency graph for the /BiVeS project")]]

The [BiVeS Framework](bives:wiki) is probably the resource you want to use in your project. It is executable from command line (it contains a [ Main](/src/main/java/de/unirostock/sems/bives/Main.java)) and combines all /BiVeS modules (see right hand side dependency graph):
* [bives-core:wiki /BiVeS-Core]
* [bives-cellml:wiki /BiVeS-CellML]
* [bives-sbml:wiki /BiVeS-SBML]

In addition, we provide a [web service](bivesws:wiki) which uses /BiVeS to provide all functionalities through JSON calls, and a [web service client library](bivesws-client:wiki) which is able to communicate with the web service.

All these modules utilize the [xmlutils](xmlutils:wiki) project, which provides advanced features for handling XML trees.

Include the /BiVeS Framework via Maven: ([find latest version id](http://mvn.sems.uni-rostock.de/releases/de/unirostock/sems///BiVeS/), import the [SEMS Maven repository](https://sems.uni-rostock.de/2013/10/maven-repository/))
```
#!xml
<dependency>
    <groupId>de.unirostock.sems</groupId>
    <artifactId>BiVeS</artifactId>
    <version>$VERSION</version>
</dependency>
```

* see the source code: /src/main/java/de/unirostock/sems/bives 

Usage 
------
* /BivesOnCommandLine
* /BivesApi
* /TellUs if you're going to use /BiVeS

Build 
------
* learn how to /BuildBives

Starting Points 
----------------
 * /TracGuide --  Built-in Documentation
 * [The Trac project](http://trac.edgewall.org/) -- Trac Open Source Project
 * [Trac FAQ](http://trac.edgewall.org/wiki///TracFaq) -- Frequently Asked Questions
 * /TracSupport --  Trac Support

For a complete list of local wiki pages, see /TitleIndex.
