package documentumrestclient.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientResponseException;

import com.google.gson.JsonObject;

import documentumrestclient.intf.DocumentumClientIntf;
import documentumrestclient.model.Account;
import documentumrestclient.model.DocumentumObject;
import documentumrestclient.model.User;
import documentumrestclient.model.dao.UserDao;
import documentumrestclient.web.validator.AccountValidator;

/**
 * @author 403522
 * JT - 8/1/19
 */
@Controller
public class DocumentumController {
	
	private static Logger logger = LoggerFactory.getLogger( DocumentumController.class );
	
	@Autowired
	private DocumentumClientIntf documentumClient;
	
	@Autowired
	private AccountValidator accountValidator;
	
	@Autowired
	private UserDao userDao;
	
	private String role;
			
	@RequestMapping(value = {"/", "/index.html", "/documentum_search.html"}, method = RequestMethod.GET)
	public String documentumSearch( ModelMap models ) {
		
		Account account = new Account();
		
		models.put( "account", account );
		
		return "documentum_search";
	}
	
	@RequestMapping(value = {"/", "/index.html", "/documentum_search.html"}, method = RequestMethod.POST)
	public String documentumSearch( @ModelAttribute Account account, BindingResult result, ModelMap models, HttpSession session ) {
		
		accountValidator.validate( account, result );
		
		if ( result.hasErrors() ) {
			
			return "documentum_search";
		}
		
		/**
		 * JT - 11/22/19 retrieving the role of the current authenticated user
		 * This part is using the hard coded user to test the Documentum REST API because there is no session of the
		 * current authenticated user for this stand alone application
		 */
		User user = userDao.getUser( "JTON" );
		
		role = user.getRole();
		
		logger.info( "The role of the current user: " + role );
		
		// Check if the user has any role
		if ( role == null ) {
			
			return "error2";
		}
		
		// Input validation using regular expression		
		if ( (account.getAccountNumber().matches( DocumentumClientIntf.REGEX ) && account.getNumberOfDocument().matches( DocumentumClientIntf.REGEX_3 )
				&& Integer.valueOf( account.getNumberOfDocument() ) <= 300)
				|| (account.getAccountNumber().matches( DocumentumClientIntf.REGEX_2 ) && account.getNumberOfDocument().matches( DocumentumClientIntf.REGEX_3 )
				&& Integer.valueOf( account.getNumberOfDocument() ) <= 300) ) {
						
			try {
				
				// Check to see if the list of DocumentObjects is empty
				if ( documentumClient.getParseJsonStrings( documentumClient.getJsonResponse( account, role ), "r_object_id" ) == null ) {
					
					models.put( "accountNumber", account.getAccountNumber() );
					return "error3";
				}
				
				if ( documentumClient.getJsonResponse( account, role ) != null ) {
					
					JsonObject jsonObject = documentumClient.getJsonResponse( account, role );
					
					List<DocumentumObject> documentumObjects = documentumClient.getDocumentumObjects( jsonObject );
					
					models.put( "documentumObjects", documentumObjects );
					
					// Put the list of objects in session scope
					session.setAttribute( "documentumObjects", documentumObjects );
		
					return "documentum_search_result";					
				} 
				
			} catch ( ConnectException e ) {
				
				logger.error( "Connection Timeout" );
				e.printStackTrace();
				return "error"; 
				
			} catch ( RestClientResponseException e ) {
				
				logger.warn( "RestClientResponseException" );
				e.printStackTrace();
				return "error";
			}
			
		}
		
		return "documentum_search";
	}

/*
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/documentum_download.html", method = RequestMethod.GET)
	public String download( @RequestParam Integer id, HttpSession session ) {
		
		// Get the list of objects from session scope
		List<DocumentumObject> documentumObjects = (List<DocumentumObject>) session.getAttribute( "documentumObjects" );

//		logger.info( "The number of Documentum objects: " + documentumObjects.size() );
		
		try {
			
//			logger.info( "Path Variable: " + id );
			
			documentumClient.getPDFFile( documentumObjects, id );
			
		} catch ( IOException e ) {
			
			logger.warn( "IOException" );
			e.printStackTrace();
			return "error";
		}
		
		return "documentum_download";
	}
*/
	
	/*
	 * JT - 9/23/19
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/documentum_search_result", method = RequestMethod.GET)
	public String documentumSearchResult( ModelMap models, HttpSession session ) {
		
		// Get the list of Documentum objects from session scope
		List<DocumentumObject> documentumObjects = (List<DocumentumObject>) session.getAttribute( "documentumObjects" );
				
		models.put( "documentumObjects", documentumObjects );
		
		return "documentum_search_result";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/documentum_view/{id}.html", method = RequestMethod.GET)
	public String documentumView( @PathVariable Integer id, HttpSession session, HttpServletResponse response ) {
		
		// Get the list of objects from session scope
		List<DocumentumObject> documentumObjects = (List<DocumentumObject>) session.getAttribute( "documentumObjects" );
		
		try {
			
			response.setContentType( "application/pdf" );
			
			response.setHeader( "Content-Disposition", "inline; filename=" + documentumObjects.get(id).getDocSubType() + ".pdf" );
			
			byte[] content = documentumClient.getByteArray( documentumObjects, role, id );
			
			OutputStream outStream = response.getOutputStream();
			
			outStream.write( content );
			
		} catch ( IOException e ) {
			
			logger.error( "IOException" );
			e.printStackTrace();
			return "error";
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/documentum_download/{id}.html", method = RequestMethod.GET)
	public String documentumDownload( @PathVariable Integer id, HttpSession session, HttpServletResponse response ) {
		
		// Get the list of objects from session scope
		List<DocumentumObject> documentumObjects = (List<DocumentumObject>) session.getAttribute( "documentumObjects" );
		
//		logger.info( "The number of Documentum objects: " + documentumObjects.size() );
		
		try {
			
//			logger.info( "Path Variable: " + id );
			
			response.setContentType( "application/pdf" );
			
			response.setHeader( "Content-Disposition", "attachment; filename=" + documentumObjects.get(id).getDocSubType() + ".pdf" );
			
			byte[] content = documentumClient.getByteArray( documentumObjects, role, id );
			
			OutputStream outStream = response.getOutputStream();
			
			outStream.write( content );
			
		} catch ( IOException e ) {
			
			logger.error( "IOException" );
			e.printStackTrace();
			return "error";
		}
		
		return null;
	}

	@RequestMapping(value = "/quit.html", method = RequestMethod.GET)
	public String quit( HttpSession session ) {
		
		session.invalidate();
		return "redirect:documentum_search.html";
	}
	
}
