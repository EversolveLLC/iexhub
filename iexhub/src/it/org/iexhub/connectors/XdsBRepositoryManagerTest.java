/*******************************************************************************
 * Copyright (c) 2015, 2016 Substance Abuse and Mental Health Services Administration (SAMHSA)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Eversolve, LLC - initial IExHub implementation for Health Information Exchange (HIE) integration
 *     Anthony Sute, Ioana Singureanu
 *******************************************************************************/
/**
 * 
 */
package org.iexhub.connectors;

import org.apache.log4j.Logger;
import org.iexhub.exceptions.UnexpectedServerException;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.junit.Assert.fail;

/**
 * XdsBRepositoryManagerTest
 * 
 * @author A. Sute
 *
 */
public class XdsBRepositoryManagerTest
{
    /** Logger */
    public static final Logger log = Logger.getLogger(XdsBRepositoryManagerTest.class);

	private static String propertiesFile = "/temp/IExHub.properties";
    private static String xdsBRegistryEndpointURI = "http://ihexds.nist.gov:80/tf6/services/xdsregistryb";
	private static String xdsBRepositoryEndpointURI = "http://ihexds.nist.gov:80/tf6/services/xdsrepositoryb";
	private static String cdaFilename = "/temp/b2 Adam Everyman ToC-PlayWeb.xml";

//	private static final String xdsBRegistryTLSEndpointURI = "	https://nist1:9085/tf6/services/xdsregistryb";
//	private static final String xdsBRepositoryTLSEndpointURI = "https://nist1:9085/tf6/services/xdsrepositoryb";
//	private static final String xdsBRegistryTLSEndpointURI = "https://philips50:8443/philips/services/xdsregistry";
//	private static final String xdsBRepositoryTLSEndpointURI = "https://philips50:8443/philips/services/xdsrepository";
	private static final String xdsBRegistryTLSEndpointURI = "https://nist1:9085/tf6/services/xdsregistryb";
	private static final String xdsBRepositoryTLSEndpointURI = "https://nist1:9085/tf6/services/xdsrepositoryb";
//	private static final String xdsBRegistryTLSEndpointURI = "https://cerner14:9070/ihe/services/xdsregistryb";
//	private static final String xdsBRepositoryTLSEndpointURI = "https://tiani---cisco72:8443/XDS3/rep";
//	private static final String xdsBRegistryTLSEndpointURI = "https://ith-icoserve12:1243/Registry/services/RegistryService";
//	private static final String xdsBRegistryTLSEndpointURI = "https://merge11:443/iti18";
//	private static final String xdsBRepositoryTLSEndpointURI = "https://10.242.43.13:5000/repository";



	private static XdsBRepositoryManager XdsBRepository = null;
	/**
	 * Test method for {@link org.iexhub.connectors.XdsBRepositoryManager\#ProvideAndRegisterDocumentSet(java.lang.String, java.util.List)}.
	 */

	@Before
	public void loadProperties(){
		Properties props = new Properties();
		try
		{
			props.load(new FileInputStream(propertiesFile));
			xdsBRegistryEndpointURI = props.getProperty("XdsBRegistryEndpointURI");
			xdsBRepositoryEndpointURI = props.getProperty("XdsBRepositoryEndpointURI");
			cdaFilename = props.getProperty("CCDToPublish");
		}
		catch (IOException e)
		{
			log.error("Error encountered loading properties file, "
					+ propertiesFile
					+ ", "
					+ e.getMessage());
			throw new UnexpectedServerException("Error encountered loading properties file, "
					+ propertiesFile
					+ ", "
					+ e.getMessage());
		}
	}

	@Test
	public void testProvideAndRegisterDocumentSet()
	{
		try
		{
			log.info("Repository ProvideAndRegisterDocumentSet (ITI-41) unit test started...");
			XdsBRepository = new XdsBRepositoryManager(xdsBRegistryEndpointURI,
					xdsBRepositoryEndpointURI);
			
			// Read contents of file into string...
			byte[] fileContents = readAllBytes(get(cdaFilename));
			
			XdsBRepository.provideAndRegisterDocumentSet(fileContents,
					"text/xml");
			
			log.info("XDS.b document set query response in log message immediately following...");
//			OMElement requestElement = documentSetResponse.getOMElement(RetrieveDocumentSetResponse.MY_QNAME,
//					soapFactory);
//			log.info(requestElement);

			log.info("Repository ProvideAndRegisterDocumentSet (ITI-41) unit test ending.");
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link org.iexhub.connectors.XdsBRepositoryManager\#ProvideAndRegisterDocumentSet(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testTLSProvideAndRegisterDocumentSet()
	{
//		String cdaFilename = "c:/temp/b2 Adam Everyman ToC_IHERED-2298.xml";
//		String cdaFilename = "c:/temp/b2 Adam Everyman ToC_IHEGREEN-2376.xml";
//		String cdaFilename = "c:/temp/b2 Adam Everyman ToC_IHEBLUE-1019.xml";
		String cdaFilename = "c:/temp/Sally_Share_b1_Ambulatory_v3.xml";
		try
		{
			log.info("Repository TLS ProvideAndRegisterDocumentSet (ITI-41) unit test started...");
			XdsBRepository = new XdsBRepositoryManager(xdsBRegistryTLSEndpointURI,
					xdsBRepositoryTLSEndpointURI,
					true);
			
			// Read contents of file into string...
			byte[] fileContents = readAllBytes(get(cdaFilename));
			
			XdsBRepository.provideAndRegisterDocumentSet(fileContents,
					"text/xml");
			
			log.info("XDS.b document set query response in log message immediately following...");
//			OMElement requestElement = documentSetResponse.getOMElement(RetrieveDocumentSetResponse.MY_QNAME,
//					soapFactory);
//			log.info(requestElement);

			log.info("Repository TLS ProvideAndRegisterDocumentSet (ITI-41) unit test ending.");
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}
}