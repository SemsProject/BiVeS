/**
 * 
 */
package de.unirostock.sems.bives.test.upstream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import javax.xml.transform.TransformerException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.binfalse.bflog.LOGGER;
import de.unirostock.sems.xmlutils.ds.TreeDocument;
import de.unirostock.sems.xmlutils.tools.DocumentTools;
import de.unirostock.sems.xmlutils.tools.XmlTools;



/**
 * The Class XmlTest.
 *
 * @author Martin Scharm
 */
public class XmlTest
{
	
	/** The Constant MATHML_DOC. */
	private static final File		MATHML_DOC	= new File ("test/mathml.xml");
	
	/** The mathml file. */
	private static TreeDocument mathmlFile;

	/**
	 * Read files.
	 */
	@BeforeClass
	public static void readFiles ()
	{
		if (MATHML_DOC.canRead ())
		{
			try
			{
				mathmlFile = new TreeDocument (XmlTools.readDocument (MATHML_DOC), MATHML_DOC.toURI ());
			}
			catch (Exception e)
			{
				LOGGER.error (e, "cannot read ", MATHML_DOC, " -> skipping tests");
			}
		}
		else
		{
			LOGGER.error ("cannot read ", MATHML_DOC, " -> skipping tests");
		}
	}

	/**
	 * Test math ml.
	 */
	@Test
	public void testMathML ()
	{
		if (mathmlFile == null)
		{
			LOGGER.error ("cannot read mathmlFile -> skipping test");
			return;
		}
		
		assertNotNull ("cannot find mathml converter xsl", XmlTest.class.getResource ("/res/mmlctop2_0.xsl"));
		
		try
		{
			String orig = DocumentTools.printSubDoc (mathmlFile.getRoot ());
			// test for typical content functions.
			assertTrue (
				"original mathml doesn't seem to be content mathml",
				orig.contains ("<apply>") && (orig.contains ("<plus />") || orig.contains ("<plus/>"))
					&& orig.contains ("<ci>"));
			
			String presentation = DocumentTools
				.transformMathML (mathmlFile.getRoot ());
			
			// test for non content mathml plus some typical presentation mathml stuff
			assertTrue (
				"converted mathml doesn't seem to be presentation mathml",
				!presentation.contains ("<apply>")
					&& !presentation.contains ("<plus/>")
					&& presentation.contains ("<mrow>") && presentation.contains ("<mi>"));
		}
		catch (TransformerException e)
		{
			fail ("wasn't able to transform mathml, got " + e);
		}
		
	}
}
