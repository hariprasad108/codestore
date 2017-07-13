package baeldung.persistence.dao.common;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import baeldung.persistence.hibernate.model.Cart;

@Repository
public class CartDAO extends AbstractHibernateDAO<Cart> {

    /** constructor*/
    public CartDAO() {
        super();
        setClazz(Cart.class);
    }
    
    public Session getSession() {
            return this.sessionFactory.getCurrentSession();
    }
    
}
