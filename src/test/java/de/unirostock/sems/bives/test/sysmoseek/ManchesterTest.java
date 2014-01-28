package de.unirostock.sems.bives.test.sysmoseek;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import de.unirostock.sems.bives.Main;
import de.unirostock.sems.bives.test.general.CommandLineTest;
import de.unirostock.sems.bives.test.general.CommandLineTest.CommandLineResults;


/**
 * Tests implemented after BiVeS was integrated in SysMO/Seek in Dec2013 to make sure we don't destroy anything used in seek.
 *  
 */
public class ManchesterTest
{
	
	/**
	 * Test command line used in SysMO/Seek. BiVeS is called from ruby using the command line:
	 * {"--reportHtml", "--json", "--crnJson", FILE1, FILE2};
	 */
	@Test
	public void testManchesterCommandLine ()
	{
		String [] args = new String [] {"--reportHtml", "--json", "--crnJson", "test/Teusink-2013Dec20.xml", "test/Teusink-2013Dec20.xml"};
		
		CommandLineResults clr = CommandLineTest.runCommandLine (args);

		ByteArrayOutputStream sysErr = clr.sysErr;
		ByteArrayOutputStream sysOut = clr.sysOut;
		
		assertTrue ("sys err should be empty: " + sysErr.toString (), sysErr.toString().isEmpty());
		
		try
		{
			JSONObject json = (JSONObject) new JSONParser ().parse (sysOut.toString());
			assertNotNull ("html report must not be null", json.get ("reportHtml"));
			assertNotNull ("json crn must not be null", json.get ("crnJson"));
			assertFalse ("html report must not be empty",((String)json.get ("reportHtml")).isEmpty ());
			assertFalse ("json crn must not be empty",((String)json.get ("crnJson")).isEmpty ());
		}
		catch (ParseException e)
		{
			fail ("bives output not in JSON format: " + e.getMessage ());
		}
		catch (Exception e)
		{
			fail ("unexpected exception during output evaluation: " + e.getMessage ());
		}
	}
	
}