package documentumrestclient.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import documentumrestclient.intf.DocumentumClientIntf;
import documentumrestclient.model.Account;
import documentumrestclient.model.DocumentumObject;

/**
 * @author 403522
 *
 */
@Component
public class DocumentumClientImpl implements DocumentumClientIntf {

	private static Logger logger = LoggerFactory.getLogger( DocumentumClientImpl.class );

	/*
	 * JT - 7/8/19
	 * Method to connect to the API to get the JSON object
	 *
	 */
/*	@Override
	public JsonObject getJsonResponse( Account account ) throws ConnectException, RestClientResponseException {

		String apiURL = "https://api-test.lacity.org/documentum-qa/repositories/cwdocbase1";
		String requestParam = "?dql=";

//		String dQLQuery = "SELECT * FROM oof_doc WHERE latax_no LIKE '" + account.getAccountNumber()
//			+ "%' AND r_modify_date between '01/01/1900' AND '12/31/9999' AND i_folder_id != '' enable(RETURN_RANGE 1 10 'r_creation_date DESC')";

		String dQLQuery = "SELECT * FROM oof_doc WHERE latax_no LIKE '"
			+ account.getAccountNumber()
			+ "%' AND r_modify_date between '01/01/1900' AND '12/31/9999' "
			+ "AND i_folder_id != '' enable(RETURN_RANGE 1 "
			+ account.getNumberOfDocument()
			+ " 'r_creation_date DESC')";

		StringBuilder uri = new StringBuilder();

		uri.append( apiURL )
		   .append( requestParam )
		   .append( dQLQuery );

		// Verify full URL
		logger.info( "Inside the getJsonResponse Method - Full URL: " + uri.toString() );

		HttpHeaders headers = getHeaders();

		headers.set( "Accept", MediaType.APPLICATION_JSON_VALUE );

		HttpEntity<String> entity = new HttpEntity<>( headers );

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.exchange( uri.toString(), HttpMethod.GET, entity, String.class );

		// Verify status code
		logger.info( "Inside the getJsonResponse Method - Status Code: " + response.getStatusCode().toString() );

		if ( response.getStatusCode() == HttpStatus.OK ) {

			JsonObject jsonObject = new JsonParser().parse( response.getBody() ).getAsJsonObject();
			return jsonObject;
		}

		logger.info( "Inside the getJsonResponse Method - The JSON Response is null!" );

		return null;
	}
*/

	/*
	 * JT - 11/22/19
	 * Method to connect to the API to get the JSON object
	 * Modified the original getJsonResponse method to pass in the two roles, ROLE_AUDIT and ROLE_USER
	 */
	@Override
	public JsonObject getJsonResponse( Account account, String role ) throws ConnectException, RestClientResponseException {

		String apiURL = "https://api-test.lacity.org/documentum-qa/repositories/cwdocbase1";
		String requestParam = "?dql=";

		String dQLQuery = "SELECT * FROM oof_doc WHERE latax_no LIKE '"
			+ account.getAccountNumber()
			+ "%' AND r_modify_date between '01/01/1900' AND '12/31/9999' "
			+ "AND i_folder_id != '' enable(RETURN_RANGE 1 "
			+ account.getNumberOfDocument()
			+ " 'r_creation_date DESC')";

		StringBuilder uri = new StringBuilder();

		uri.append( apiURL )
		   .append( requestParam )
		   .append( dQLQuery );

		// Verify full URL
		logger.info( "Full URL: " + uri.toString() );
	
		HttpHeaders headers = getHeaders( role );

		headers.set( "Accept", MediaType.APPLICATION_JSON_VALUE );

		HttpEntity<String> entity = new HttpEntity<>( headers );

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.exchange( uri.toString(), HttpMethod.GET, entity, String.class );

		// Verify status code
		logger.info( "The Status Code:" + response.getStatusCode().toString() );
//		System.out.println( "The Status Code: " + response.getStatusCode().toString() );
		
		if ( response.getStatusCode() == HttpStatus.OK ) {

			JsonObject jsonObject = new JsonParser().parse( response.getBody() ).getAsJsonObject();
			return jsonObject;
		}
		
		logger.info( "The JSON Response is null!" );
		
		return null;
	}

	/*
	 * JT - 7/26/19
	 * Method to get the PDF file from the API
	 *
	 */
/*	@Override
	public void getPDFFile( List<DocumentumObject> DocumentumObjects, Integer id ) throws IOException {

		String apiURL = "https://api-test.lacity.org/documentum-qa/repositories/cwdocbase1";

		StringBuilder uri = new StringBuilder();

		uri.append( apiURL )
		   .append( "/objects/" )
		   .append( DocumentumObjects.get(id).getObjectID() )
		   .append( "/content-media" );

		// Verify full URL
		logger.info( "Inside the getPDFFile Method - Full URL: " + uri.toString() );

		HttpHeaders headers = getHeaders();

		headers.setAccept( Arrays.asList( MediaType.APPLICATION_PDF, MediaType.APPLICATION_OCTET_STREAM ) );

		HttpEntity<String> entity = new HttpEntity<>( headers );

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<byte[]> response = restTemplate.exchange( uri.toString(), HttpMethod.GET, entity, byte[].class );

		byte[] content = response.getBody();

		// Verify status code
		logger.info( "Inside the getPDFFile Method - Status Code: " + response.getStatusCode().toString() );

		// Verify the size of the PDF file
		logger.info( "Inside the getPDFFile Method - Size of the PDF File: " + content.length );

		String home = System.getProperty( "user.home" );

		String path = home + "/Downloads/" + DocumentumObjects.get(id).getDocSubType() + ".pdf";

		if ( response.getStatusCode() == HttpStatus.OK ) {

			Files.write( Paths.get( path ), content, StandardOpenOption.CREATE );
		}
	}
*/

	/*
	 * JT - 11/22/19
	 * Method to get the PDF file from the API
	 * Modified the original getPDFFile method to pass in the two roles, ROLE_AUDIT and ROLE_USER
	 */
	@Override
	public byte[] getByteArray( List<DocumentumObject> DocumentumObjects, String role, Integer id ) throws IOException {

		String apiURL = "https://api-test.lacity.org/documentum-qa/repositories/cwdocbase1";

		StringBuilder uri = new StringBuilder();

		uri.append( apiURL )
		   .append( "/objects/" )
		   .append( DocumentumObjects.get(id).getObjectID() )
		   .append( "/content-media" );

		// Verify full URL
		logger.info( "Full URL: " + uri.toString() );
		
		HttpHeaders headers = getHeaders( role );

//		headers.setAccept( Arrays.asList( MediaType.APPLICATION_PDF, MediaType.APPLICATION_OCTET_STREAM ) );
		
		headers.setAccept( Arrays.asList( MediaType.APPLICATION_OCTET_STREAM ) );

		HttpEntity<String> entity = new HttpEntity<>( headers );

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<byte[]> response = restTemplate.exchange( uri.toString(), HttpMethod.GET, entity, byte[].class );

		byte[] content = response.getBody();

		// Verify status code
		logger.info( "The Status Code:" + response.getStatusCode().toString() );
//		System.out.println( "The Status Code: " + response.getStatusCode());
		
		// Verify the size of the PDF file
		logger.info( "Size of the PDF File:" + content.length );
//		System.out.println( "Size of the PDF File: " + content.length );
		
		/*
		String home = System.getProperty( "user.home" );
		
		String path = home + "/Downloads/" + DocumentumObjects.get(id).getDocSubType() + ".pdf";
				
		if ( response.getStatusCode() == HttpStatus.OK ) {

			Files.write( Paths.get( path ), content, StandardOpenOption.CREATE );
		}
		*/
		
		if ( response.getStatusCode() == HttpStatus.OK ) {
			
			return content;
		}
		
		logger.info( "The byte array is null!" );
		
		return null;
	}
	
	/*
	 * JT - 05/11/20
	 * Method to get the byte array file from the API
	 * 
	 */
/*	
	@Override
	public byte[] getView( List<DocumentumObject> DocumentumObjects, String role, Integer id ) throws IOException {
		
		String apiURL = "https://api-test.lacity.org/documentum-qa/repositories/cwdocbase1";

		StringBuilder uri = new StringBuilder();

		uri.append( apiURL )
		   .append( "/objects/" )
		   .append( DocumentumObjects.get(id).getObjectID() )
		   .append( "/content-media" );

		// Verify full URL
		logger.info( "Full URL: " + uri.toString() );
		
		HttpHeaders headers = getHeaders( role );

		headers.setAccept( Arrays.asList( MediaType.APPLICATION_OCTET_STREAM ) );

		HttpEntity<String> entity = new HttpEntity<>( headers );

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<byte[]> response = restTemplate.exchange( uri.toString(), HttpMethod.GET, entity, byte[].class );

		byte[] content = response.getBody();
		
		if ( response.getStatusCode() == HttpStatus.OK ) {
			
			return content;
		}
		
		logger.info( "The byte array is null!" );
		
		return null;
	}
*/	
	
	/*
	 * JT - 7/18/19
	 * Method to parse the JSON object to extract the r_object_id
	 * JT - Modified on 8/8/19
	 *
	 */
	@Override
	public List<String> getObjectIDs( JsonObject jsonObject ) {

		List<String> rObjectIDs = getParseJsonStrings( jsonObject, "r_object_id" );

		return rObjectIDs;
	}

	/*
	 * JT - 7/18/19
	 * Method to parse the JSON object to extract the object_name
	 * JT - Modified on 8/8/19
	 *
	 */
	@Override
	public List<String> getDocSubTypes( JsonObject jsonObject ) {

		List<String> docSubTypes = getParseJsonStrings( jsonObject, "doc_sub_type" );

		return docSubTypes;
	}

	/*
	 * JT - 8/7/19
	 * Method to parse the JSON object to extract the correspondence_id
	 *
	 */
	@Override
	public List<String> getCorrespondenceIDs( JsonObject jsonObject ) {

		List<String> correspondenceIDs = getParseJsonStrings( jsonObject, "correspondence_id" );

		return correspondenceIDs;
	}

	/*
	 * JT - 8/8/19
	 * Method to parse the JSON object to extract the latax_no
	 *
	 */
	@Override
	public List<String> getLATaxNumbers( JsonObject jsonObject ) {

		List<String> lATaxNumbers = getParseJsonStrings( jsonObject, "latax_no" );

		return lATaxNumbers;
	}

	/*
	 * JT - 8/8/19
	 * Method to parse the JSON object to extract the legal_name
	 *
	 */
	@Override
	public String getLegalName( JsonObject jsonObject ) {

		List<String> legalNameList = getParseJsonStrings( jsonObject, "legal_name" );

		String legalName = legalNameList.get( 0 );

		return legalName;
	}

	/*
	 * JT - 8/8/19
	 * Method to parse the JSON object to extract a list of String using the property name
	 *
	 */
	@Override
	public List<String> getParseJsonStrings( JsonObject jsonObject, String propertyName ) {

		List<String> parseJsonStrings = new ArrayList<>();

		if ( jsonObject.getAsJsonArray( "entries" ) != null ) {

			JsonArray entriesArray = jsonObject.getAsJsonArray( "entries" ).getAsJsonArray();

			String content;
			String properties;

			JsonObject jsonContent;
			JsonObject jsonProperties;

			for ( int i = 0; i < entriesArray.size(); i++ ) {

				content = ((JsonObject) entriesArray.get( i )).get( "content" ).toString();

				jsonContent = new JsonParser().parse( content ).getAsJsonObject();

				properties = jsonContent.get( "properties" ).toString();

				jsonProperties = new JsonParser().parse( properties ).getAsJsonObject();

				parseJsonStrings.add( jsonProperties.get( propertyName ).getAsString() );
			}

			return parseJsonStrings;
		}
		
		logger.info( "The List of Strings is null!" );
		
		return null;
	}

	/*
	 * JT - 7/22/19
	 * Method to return a list of documentum objects
	 * JT - Modified on 8/8/19
	 *
	 */
	@Override
	public List<DocumentumObject> getDocumentumObjects( JsonObject jsonObject ) {

		List<String> rObjectIDs = getObjectIDs( jsonObject );
		List<String> docSubTypes = getDocSubTypes( jsonObject );
		List<String> correspondenceIDs = getCorrespondenceIDs( jsonObject );
		List<String> lATaxNumbers = getLATaxNumbers( jsonObject );

		String legalName = getLegalName( jsonObject );

		List<DocumentumObject> documentumObjects = new ArrayList<>();

		if ( rObjectIDs != null ) {

			DocumentumObject documentumObject;

			for ( int i = 0; i < rObjectIDs.size(); i++ ) {

				documentumObject = new DocumentumObject();

				documentumObject.setObjectID( rObjectIDs.get(i) );
				documentumObject.setDocSubType( docSubTypes.get(i) );
				documentumObject.setCorrespondenceID( correspondenceIDs.get(i) );
				documentumObject.setLaTaxNumber( lATaxNumbers.get(i) );
				documentumObject.setLegalName( legalName );

				documentumObjects.add( documentumObject );
			}

			return documentumObjects;
		}
		
		logger.info( "The List of Documentum Objects is null!" );
		
		return null;
	}

	/*
	 * JT - 7/31/19
	 * Method to return the HTTP Headers with API Key and Authentication
	 * JT - Modified 8/23/19
	 * JT - Modified 10/15/19 - Added Spring Security for the two roles (ROLE_AUDIT, ROLE_USER)
	 *
	 */
/*
	@Override
	public HttpHeaders getHeaders() {

		try ( InputStream input = DocumentumClientImpl.class.getResourceAsStream( "/documentum.properties" ) ) {

			Properties prop = new Properties();

			// Load a properties file
            prop.load( input );

            HttpHeaders headers = new HttpHeaders();

            headers.add( "x-api-key", prop.getProperty( "app.apikey" ) );

            // Using Spring Security to access the role of the current authenticated user
//            String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
/*
    		if ( role.contains( "ROLE_AUDIT" ) ) {

    			// Verifying role
        		logger.info( "Inside the getHeaders Method - Role: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities() );

    	        headers.add( "Authorization", "Basic " + prop.getProperty( "app.authentication.audit" ) );

    		} else {

    			// Verifying role
        		logger.info( "Inside the getHeaders Method - Role: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities() );

    	        headers.add( "Authorization", "Basic " + prop.getProperty( "app.authentication.general" ) );
    		}
*/
/*            headers.add( "Authorization", "Basic " + prop.getProperty( "app.authentication.general" ) );

            return headers;

		} catch ( FileNotFoundException e ) {

			logger.warn( " FileNotFoundException - Inside the getHeaders Method" );
			e.printStackTrace();

		} catch ( IOException e ) {

			logger.warn( "IOException - Inside the getHeaders Method" );
			e.printStackTrace();
		}

		return null;
	}
*/

	/*
	 * JT - 11/22/19
	 * Method to return the HTTP Headers with API Key and Authentication
	 * Modified the original getHeaders method for the two roles, ROLE_AUDIT and ROLE_USER
	 *
	 */
	@Override
	public HttpHeaders getHeaders( String role ) {

		try ( InputStream input = DocumentumClientImpl.class.getResourceAsStream( "/documentum.properties" ) ) {

			Properties prop = new Properties();

			// Load a properties file
            prop.load( input );

            HttpHeaders headers = new HttpHeaders();

            headers.add( "x-api-key", prop.getProperty( "app.apikey" ) );
            
//            headers.add( "Authorization", "Basic " + prop.getProperty( "app.authentication" ) );
    		if ( role.toUpperCase().contains( "ROLE_AUDIT" ) ) {
    			
    			// Verifying role
        		logger.info( "Inside the getHeaders Method - Role: " + role );

    	        headers.add( "Authorization", "Basic " + prop.getProperty( "app.authentication.audit" ) );

    		} else {

    			// Verifying role
        		logger.info( "Inside the getHeaders Method - Role: " + role );

    	        headers.add( "Authorization", "Basic " + prop.getProperty( "app.authentication.general" ) );
    		}

            return headers;

		} catch ( FileNotFoundException e ) {

			logger.warn( "FileNotFoundException - Inside the getHeaders Method" );
			e.printStackTrace();

		} catch ( IOException e ) {

			logger.warn( "IOException - Inside the getHeaders Method" );
			e.printStackTrace();
		}

		return null;
	}

}
