package documentumrestclient.intf;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientResponseException;

import com.google.gson.JsonObject;

import documentumrestclient.model.Account;
import documentumrestclient.model.DocumentumObject;

/**
 * @author 403522
 *
 */
public interface DocumentumClientIntf {
	
	// Regular expressions to check if the account number is consisted with only numbers and has fixed length
	public static final String REGEX = "[0-9]{10}$";
	
	public static final String REGEX_2 = "[0-9]{15}$";
	
	public static final String REGEX_3 = "[0-9]{2,3}$";
	
//	public abstract JsonObject getJsonResponse( Account account ) throws ConnectException, RestClientResponseException;
	
	public abstract JsonObject getJsonResponse( Account account, String role ) throws ConnectException, RestClientResponseException;
		
//	public abstract void getPDFFile( List<DocumentumObject> DocumentumObjects, String role, Integer id ) throws IOException;
	
	public abstract byte[] getByteArray( List<DocumentumObject> DocumentumObjects, String role, Integer id ) throws IOException;
	
//	public abstract byte[] getView( List<DocumentumObject> DocumentumObjects, String role, Integer id ) throws IOException;
	
	public abstract List<String> getObjectIDs( JsonObject jsonObject );
	
	public abstract List<String> getDocSubTypes( JsonObject jsonObject );
	
	public abstract List<String> getCorrespondenceIDs( JsonObject jsonObject );
	
	public abstract List<String> getLATaxNumbers( JsonObject jsonObject );
	
	public abstract String getLegalName( JsonObject jsonObject );
	
	public abstract List<String> getParseJsonStrings( JsonObject jsonObject, String propertyName );
	
	public abstract List<DocumentumObject> getDocumentumObjects( JsonObject jsonObject );
	
//	public abstract HttpHeaders getHeaders();
	
	public abstract HttpHeaders getHeaders( String role );
	
}
