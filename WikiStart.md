/BiVeS Framework 
=================

<img src="https://github.com/SemsProject/BiVeS/raw/master/doc/dependency-graph.png" width="380px" title="dependency graph for the /BiVeS project" />

The [BiVeS Framework](http://sems.uni-rostock.de/trac/bives/wiki) is probably the resource you want to use in your project. It is executable from command line (it contains a [ Main](/src/main/java/de/unirostock/sems/bives/Main.java)) and combines all /BiVeS modules (see right hand side dependency graph):
 * [BiVeS Core](http://sems.uni-rostock.de/trac/bives-core/wiki/BiVeS-Core)
 * [BiVeS CellML](http://sems.uni-rostock.de/trac/bives-cellml/wiki/BiVeS-CellML)
 * [BiVeS SBML](http://sems.uni-rostock.de/trac/bives-sbml/wiki/BiVeS-SBML)
 * see the [source code](http://github.com/SemsProject/bives)

In addition, we provide a [web service](http://sems.uni-rostock.de/trac/bivesws/wiki) which uses BiVeS to provide all functionalities through JSON calls, and a [web service client library](http://sems.uni-rostock.de/trac/bivesws-client/wiki) which is able to communicate with the web service.

All these modules utilize the [xmlutils](http://sems.uni-rostock.de/trac/xmlutils/wiki) project, which provides advanced features for handling XML trees.

Include the BiVeS Framework via Maven: ([find latest version id](http://mvn.sems.uni-rostock.de/releases/de/unirostock/sems/BiVeS/), import the [SEMS Maven repository](https://sems.uni-rostock.de/2013/10/maven-repository/))

```xml
<dependency>
    <groupId>de.unirostock.sems</groupId>
    <artifactId>BiVeS</artifactId>
    <version>$VERSION</version>
</dependency>
```
Usage 
------
 * [BiVeS on Commandline](BivesOnCommandLine)
 * [BiVeS Api](BivesApi)
 * [Tell us](TellUs) if you're going to use BiVeS

Build 
------
 * learn how to [build BiVeS](/BuildBives)

