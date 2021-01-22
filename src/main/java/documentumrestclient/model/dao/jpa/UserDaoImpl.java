package documentumrestclient.model.dao.jpa;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import documentumrestclient.model.User;
import documentumrestclient.model.dao.UserDao;

/**
 * @author 403522
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

//	@PersistenceContext
//	private EntityManager entityManager;
	
/*
	@Override
	public User getUser( String userID ) {
		
		return entityManager.find( User.class, userID );
	}
*/	
	
	/*
	 * JT - 12/20/19 - Add code to use JDBC Template instead of Hibernate & JPA
	 * 
	 */
	@Autowired
//	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource( DataSource dataSource ) {
		
		jdbcTemplate = new JdbcTemplate( dataSource );
	}

	/*
	 * JT - 12/20/19 - Add code to use JDBC Template instead of Hibernate & JPA
	 * 
	 */
	@Override
	public User getUser( String userID ) {
		
		String sql = "SELECT documentum_authority FROM security_user WHERE user_id = :userID";
		
		String role = (String) jdbcTemplate.queryForObject( sql, new Object[] { userID }, String.class);
		
		return new User( userID, role );
	}

}
