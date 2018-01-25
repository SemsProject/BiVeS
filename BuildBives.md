Build BiVeS 
=============

When you've cloned the source code:

```sh
git clone https://github.com/SemsProject/BiVeS
```

There are two supported options to build this project:

* [Build with Maven](#build-with-maven)
* [Build with Ant](#build-with-ant)

Build with Maven 
-----------------

[Maven](https://maven.apache.org/) is a build automation tool. We ship a [source:pom.xml pom.xml] together with the sources which tells maven about versions and dependencies. Thus, maven is able to resolve everything on its own and, in order to create the library, all you need to call is `mvn package`:

```sh
usr@srv $ mvn package

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running de.unirostock.sems.bives.test.general.GeneralTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.92 sec
Running de.unirostock.sems.bives.test.general.CommandLineTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.008 sec
Running de.unirostock.sems.bives.test.sysmoseek.ManchesterTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.23 sec
Running de.unirostock.sems.bives.test.upstream.XmlTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.151 sec

Results :

Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
```

That done, you'll find the binaries in the `target` directory.

Build with Ant 
---------------

[Ant](https://ant.apache.org/) is an Apache tool for automating software build processes. There is a [source:build.xml build.xml] file included in the source code that tells ant what to do. Since ant is not able to resolve the dependencies you need to create a directory `lib` containing the following libraries:

* [BiVeS-Core](http://sems.uni-rostock.de/trac/bives-core/wiki] (download latest binary from http://bin.sems.uni-rostock.de or see http://sems.uni-rostock.de/trac/bives-core/wiki/BuildBivesCore)
* [BiVeS-CellML](http://sems.uni-rostock.de/trac/bives-cellml/wiki] (download latest binary from http://bin.sems.uni-rostock.de or see http://sems.uni-rostock.de/trac/bives-cellml/wiki/BuildBivesCellml)
* [BiVeS-SBML](http://sems.uni-rostock.de/trac/bives-sbml/wiki] (download latest binary from http://bin.sems.uni-rostock.de or see http://sems.uni-rostock.de/trac/bives-sbml/wiki/BuildBivesSbml)

We defined multiple targets in the `build.xml`. They can be displayed by calling `ant -p`:

```sh
usr@srv $ ant -p
Buildfile: /path/to/BiVeS/build.xml

                A toolkit useful for working with XML documents

Main targets:

 clean    clean up
 compile  compile the source
 dist     generate the distribution
 init     initialize workspace
 sign     sign a dist
Default target: dist
```

* `clean up` will delete all compiled files and produced libraries
* `compile` compiles the source code
* `dist` bundles all compiled binaries into a `jar` package

For example, to create the `jar` library just run `ant dist`:


```sh
usr@srv $ ant dist
Buildfile: /path/to/BiVeS/build.xml

init:

compile:
    [javac] Compiling 3 source files to .../BiVeS/build

dist:
      [jar] Building jar: .../BiVeS/dist/BiVeS-1.2.7.jar
      [jar] Building jar: .../BiVeS/dist/BiVeS-1.2.7-fat.jar

BUILD SUCCESSFUL
Total time: 7 seconds
```

