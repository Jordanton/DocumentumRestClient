package documentumrestclient.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import documentumrestclient.model.Account;

/**
 * @author 403522
 * JT - 8/2/19
 */
@Component
public class AccountValidator implements Validator {

	@Override
	public boolean supports( Class<?> clazz ) {
	
		return Account.class.isAssignableFrom( clazz );
	}

	@Override
	public void validate( Object target, Errors errors ) {
		
		Account account = (Account) target;
		
		if ( !StringUtils.hasText( account.getAccountNumber() ) ) {
			
			errors.rejectValue( "accountNumber", "error.field.empty" );
		}
		
		if ( !StringUtils.hasText( account.getNumberOfDocument() ) ) {
			
			errors.rejectValue( "numberOfDocument", "error.field.empty" );
		}
	}

}
