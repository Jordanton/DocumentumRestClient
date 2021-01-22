package documentumrestclient.model;

import java.io.Serializable;

/**
 * @author 403522
 *
 */
public class DocumentumObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String r_object_id;
	private String doc_sub_type;
	private String correspondence_id;
	private String latax_number;
	private String legal_name;
	
	public DocumentumObject() {
		
	}
	
	public DocumentumObject( String r_object_id, String doc_sub_type, String correspondence_id, String latax_number, String legal_name ) {

		this.r_object_id = r_object_id;
		this.doc_sub_type = doc_sub_type;
		this.correspondence_id = correspondence_id;
		this.latax_number = latax_number;
		this.legal_name = legal_name;
	}

	public String getObjectID() {
		
		return r_object_id;
	}

	public void setObjectID( String r_object_id ) {
		
		this.r_object_id = r_object_id;
	}

	public String getDocSubType() {
		
		return doc_sub_type;
	}

	public void setDocSubType( String doc_sub_type ) {
			
		this.doc_sub_type = doc_sub_type;
	}
	
	public String getCorrespondenceID() {
		
		return correspondence_id;
	}

	public void setCorrespondenceID( String correspondence_id ) {
		
		this.correspondence_id = correspondence_id;
	}
	
	public String getLaTaxNumber() {
		
		return latax_number;
	}

	public void setLaTaxNumber( String latax_number ) {
		
		this.latax_number = latax_number;
	}
	
	public String getLegalName() {
		
		return legal_name;
	}

	public void setLegalName( String legal_name ) {
		
		this.legal_name = legal_name;
	}
	
}
