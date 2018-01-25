BiVeS Framework 
=================

<img src="https://github.com/SemsProject/BiVeS/raw/master/doc/dependency-graph.png" width="380px" title="dependency graph for the /BiVeS project" />

The [BiVeS Framework](http://sems.uni-rostock.de/trac/bives/wiki) is probably the resource you want to use in your project. It is executable from command line (it contains a [ Main](/src/main/java/de/unirostock/sems/bives/Main.java)) and combines all /BiVeS modules (see right hand side dependency graph):

 * [BiVeS Core](http://sems.uni-rostock.de/trac/bives-core/wiki)
 * [BiVeS CellML](http://sems.uni-rostock.de/trac/bives-cellml/wiki)
 * [BiVeS SBML](http://sems.uni-rostock.de/trac/bives-sbml/wiki)
 * see the [source code](http://github.com/SemsProject/bives)

In addition, we provide a [web service](http://sems.uni-rostock.de/trac/bivesws/wiki) which uses BiVeS to provide all functionalities through JSON calls, and a [web service client library](http://sems.uni-rostock.de/trac/bivesws-client/wiki) which is able to communicate with the web service.

All these modules utilize the [xmlutils](http://sems.uni-rostock.de/trac/xmlutils/wiki) project, which provides advanced features for handling XML trees.

Include the BiVeS Framework via Maven:

```xml
<dependency>
    <groupId>de.uni-rostock.sbi</groupId>
    <artifactId>BiVeS</artifactId>
    <version>$VERSION</version>
</dependency>
```

([see all versions at Maven Central](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22de.uni-rostock.sbi%22%20AND%20a%3A%22BiVeS%22))


Usage 
------

 * [BiVeS on Commandline](BivesOnCommandLine)
 * [BiVeS Api](BivesApi)
 * [Tell us](https://semsproject.github.io/contact) if you're going to use BiVeS

Build 
------

 * learn how to [build BiVeS](BuildBives)

