/**
 * 
 */
package de.unirostock.sems.bives;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jdom2.Document;
import org.jdom2.Element;
import org.json.simple.JSONObject;

import de.binfalse.bflog.LOGGER;
import de.binfalse.bfutils.GeneralTools;
import de.unirostock.sems.xmlutils.tools.XmlTools;

//TODO: detect document type
//TODO: graph producer

/**
 * @author Martin Scharm
 *
 */
public class Main
{
	public static boolean exit = true;
	
	
	
	public void usage (String msg)
	{
		if (msg != null && msg.length () > 0)
		{
			System.err.println (msg);
			System.out.println ();
		}

		HashMap<String, Executer.Option> options = exe.getOptions ();
		HashMap<String, Executer.Option> addOptions = exe.getAddOptions ();
		
		System.out.println ("ARGUMENTS:");
		System.out.println ("\t[option] FILE1 [FILE2]  compute the differences between 2 XML files");
		System.out.println ();
		System.out.println ("FILE1 and FILE2 define XML files or URLs to XML files on the internet");
		System.out.println ();
		System.out.println ("OPTIONS:");
		SortedSet<String> keys = new TreeSet<String>(options.keySet());
		int longest = 0;
		for (String key : keys)
		{
			if (key.length () > longest)
				longest = key.length ();
		}
		SortedSet<String> addKeys = new TreeSet<String>(addOptions.keySet());
		for (String key : addKeys)
		{
			if (key.length () > longest)
				longest = key.length ();
		}
		
		longest += 2;
		System.out.println ("\tCOMMON OPTIONS");
		System.out.println ("\t[none]"+GeneralTools.repeat (" ", longest - "[none]".length ()) +"expect XML files and print patch");
		System.out.println ("\t--help"+GeneralTools.repeat (" ", longest - "--help".length ()) +"print this help");
		System.out.println ("\t--debug"+GeneralTools.repeat (" ", longest - "--debug".length ()) +"enable verbose mode");
		System.out.println ("\t--debugg"+GeneralTools.repeat (" ", longest - "--debugg".length ()) +"enable even more verbose mode");

		System.out.println ();
		System.out.println ("\tMAPPING OPTIONS");
		
		for (String key : keys)
			System.out.println ("\t--"+key + GeneralTools.repeat (" ", longest - key.length ()) + options.get (key).description);
		System.out.println ();

		System.out.println ("\tENCODING OPTIONS");
		System.out.println ("\tby default we will just dump the result to the terminal. Thus, it's only usefull if you call for one single output.");
		System.out.println ("\t--json"+GeneralTools.repeat (" ", longest - "--json".length ()) +"encode results in JSON");
		System.out.println ("\t--xml"+GeneralTools.repeat (" ", longest - "--xml".length ()) +"encode results in XML");
		System.out.println ();

		System.out.println ("\tADDITIONAL OPTIONS for single files");
		for (String key : addKeys)
			System.out.println ("\t--"+key + GeneralTools.repeat (" ", longest - key.length ()) + addOptions.get (key).description);
		System.out.println ();

		if (exit)
			System.exit (2);
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main (String[] args)
	{
		LOGGER.setLogToStdErr (false);
		LOGGER.setLogToStdOut (false);
		LOGGER.setLevel (LOGGER.ERROR);
		
		Main m = new Main ();
		try
		{
			m.run (args); 
		}
		catch (HelpException e)
		{
			m.usage (null);
		}
		catch (Exception e)
		{
			m.usage (e.getClass ().getSimpleName () + ": " + e.getMessage ());
			//System.exit (2);
		}
	}
	
	private class HelpException extends Exception
	{}
	
	public static class ExecutionException extends Exception
	{
		public ExecutionException (String msg)
		{
			super (msg);
		}
	}
	
	Executer exe;
	
	public Main ()
	{
		exe = new Executer ();
	}
	
	@SuppressWarnings("unchecked")
	public void run (String[] args) throws Exception
	{
		
    String file1 = null, file2 = null;
    int output = 0;
  	int want = 0;
    HashMap<String, String> toReturn = new HashMap<String, String> ();
    

    
    for (int i = 0; i < args.length; i++)
    {
    	if (args[i].substring (0, 2).equals ("--"))
    	{
	    	Executer.Option o = exe.get (args[i].substring (2));
	    	if (o != null)
	    	{
	    		want |= o.value;
	    		continue;
	    	}
    	}
    	
    	if (args[i].equals ("--debug"))
    	{
    		LOGGER.setLogToStdErr (true);
    		LOGGER.setLevel (LOGGER.INFO | LOGGER.WARN | LOGGER.ERROR);
    		continue;
    	}
    	if (args[i].equals ("--debugg"))
    	{
    		LOGGER.setLogToStdErr (true);
    		LOGGER.setLevel (LOGGER.DEBUG | LOGGER.INFO | LOGGER.WARN | LOGGER.ERROR);
    		continue;
    	}
    	if (args[i].equals ("--xml"))
    	{
    		output = 1;
    		continue;
    	}
    	if (args[i].equals ("--json"))
    	{
    		output = 2;
    		continue;
    	}
    	if (args[i].equals ("--help"))
    	{
    		throw new HelpException ();
    	}
    	if (file1 == null)
    		file1 = args[i];
    	else if (file2 == null)
    		file2 = args[i];
    	else
    		throw new ExecutionException ("do not understand " + args[i] + " (found files " + file1 + " and " + file2 + ")");
    		
    }
    

    if (file1 == null)
    	throw new ExecutionException ("no file provided");
    
    if (!file1.matches ("^https?://.*"))
    {
    	File tmp = new File (file1);
	    if (!tmp.exists ())
	    	throw new ExecutionException ("cannot find " + tmp.getAbsolutePath ());
	    if (!tmp.canRead ())
	    	throw new ExecutionException ("cannot read " + tmp.getAbsolutePath ());
	    file1 = tmp.toURI ().toURL ().toString ();//GeneralTools.fileToString (tmp);
    }
    
    List<Exception> errors = new ArrayList<Exception> ();
    if (file2 == null)
    {
    	// single mode
    	exe.executeSingle (file1, toReturn, want, errors);
    }
    else
    {
    	// compare two files
      if (!file2.matches ("^https?://.*"))
      {
      	File tmp = new File (file2);
  	    if (!tmp.exists ())
  	    	throw new ExecutionException ("cannot find " + tmp.getAbsolutePath ());
  	    if (!tmp.canRead ())
  	    	throw new ExecutionException ("cannot read " + tmp.getAbsolutePath ());
  	    file2 =  tmp.toURI ().toURL ().toString ();//GeneralTools.fileToString (tmp);
      }
    	exe.executeCompare (file1, file2, toReturn, want, errors);
    }
    

		if (toReturn.size () < 1)
		{
			throw new ExecutionException ("invalid call. no output produced.");
		}
    
    if (output == 0)
    {
    	for (Exception e : errors)
    		System.err.println ("ERROR: " + e);
    	
    	for (String ret : toReturn.keySet ())
    		System.out.println (toReturn.get (ret));
    }
    else if (output == 1)
    {
    	//xml
    	Element root =  new Element ("bivesResult");
    	Document document = new Document (root);

    	for (String ret : toReturn.keySet ())
    	{
    		Element el = new Element (ret);
    		el.setText (toReturn.get (ret));
    		root.addContent (el);
    	}
    	
    	System.out.println (XmlTools.prettyPrintDocument (document));
    }
    else
    {
    	// json
    	JSONObject json = new JSONObject ();
    	for (String ret : toReturn.keySet ())
    		json.put (ret, toReturn.get (ret));
    	System.out.println (json);
    }
	}
	 
}
