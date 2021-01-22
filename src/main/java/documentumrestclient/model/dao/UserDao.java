package documentumrestclient.model.dao;

import documentumrestclient.model.User;

/**
 * @author 403522
 *
 */
public interface UserDao {

	public abstract User getUser( String userID );
	
}
