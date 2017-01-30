Use BiVeS from Commandline 
============================

BiVeS contains a Main, so you are free to use it from command line. If you do not pass any arguments you'll see a help page such as (as of version 1.3)

```
usr@srv $ java -jar /path/to/BiVeS-fat.jar
ExecutionException: no file provided

ARGUMENTS:
	[option] FILE1 [FILE2]  compute the differences between 2 XML files

FILE1 and FILE2 define XML files or URLs to XML files on the internet

OPTIONS:
	COMMON OPTIONS
	[none]                      expect XML files and print patch
	--help                      print this help
	--debug                     enable verbose mode
	--debugg                    enable even more verbose mode

	MAPPING OPTIONS
	--CellML                      force CellML comparison
	--SBML                        force SBML comparison
	--compHierarchyDot            get the hierarchy of components in a CellML document encoded in DOT language
	--compHierarchyGraphml        get the hierarchy of components in a CellML document encoded in GraphML
	--compHierarchyJson           get the hierarchy of components in a CellML document encoded in JSON
	--reactionsDot                get the highlighted reaction network encoded in DOT language
	--reactionsGraphml            get the highlighted reaction network encoded in GraphML
	--reactionsJson               get the highlighted reaction network encoded in JSON
	--regular                     force regular XML comparison
	--reportHtml                  get the report of changes encoded in HTML
	--reportMd                    get the report of changes encoded in MarkDown
	--reportRST                   get the report of changes encoded in ReStructuredText
	--xmlDiff                     get the diff encoded in XML format

	ENCODING OPTIONS
	by default we will just dump the result to the terminal. Thus, it's only usefull if you call for one single output.
	--json                      encode results in JSON
	--xml                       encode results in XML

	ADDITIONAL OPTIONS for single files
	--documentType                get the documentType of an XML file
	--meta                        get some meta information about an XML file
	--singleCompHierarchyDot      get the hierarchy of components in a single CellML document encoded in DOT language
	--singleCompHierarchyGraphml  get the hierarchy of components in a single CellML document encoded in GraphML
	--singleCompHierarchyJson     get the hierarchy of components in a single CellML document encoded in JSON
	--singleFlatten               flatten the model
	--singleReactionsDot          get the reaction network of a single file encoded in DOT language
	--singleReactionsGraphml      get the reaction network of a single file encoded in GraphML
	--singleReactionsJson         get the reaction network of a single file encoded in JSON
```

Examples 
---------

### Flatten a CellML file 

```sh
java -jar /path/to/BiVeS-fat.jar --CellML --singleFlatten http://models.cellml.org/exposure/29a0ec2468a49a64a123f927083260f0/CompletedImportExample.cellml
```
