BiVeS API 
===========

The BiVeS API provides some easy way to compare two computational models.

The base API is defined in the [BiVeS-core API](http://sems.uni-rostock.de/trac/bives-core/wiki/API), which is extended by

* the [BiVeS-SBML API](http://sems.uni-rostock.de/trac/bives-sbml/wiki/SBML-API) to handle SBML models
* the [BiVeS-CellML API](http://sems.uni-rostock.de/trac/bives-cellml/wiki/CellML-API) to handle CellML models

To get a better understanding of the usage of BiVeS within Java projects you might also want to have a look at the [Executer class](https://github.com/SemsProject/BiVeS/blob/master/src/main/java/de/unirostock/sems/bives/Executer.java) wich makes use of these APIs.
