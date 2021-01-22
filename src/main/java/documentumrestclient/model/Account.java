package documentumrestclient.model;

/**
 * @author 403522
 *
 */
public class Account {

	private String accountNumber;
	private String numberOfDocument;
	
	public Account() {
		
	}

	public Account( String accountNumber, String numberOfDocument ) {
		
		this.accountNumber = accountNumber;
		this.numberOfDocument = numberOfDocument;
	}

	public String getAccountNumber() {

		return accountNumber;
	}

	public void setAccountNumber( String accountNumber ) {
		
		this.accountNumber = accountNumber;
	}

	public String getNumberOfDocument() {
		
		return numberOfDocument;
	}

	public void setNumberOfDocument( String numberOfDocument ) {
		
		this.numberOfDocument = numberOfDocument;
	}
	
}
