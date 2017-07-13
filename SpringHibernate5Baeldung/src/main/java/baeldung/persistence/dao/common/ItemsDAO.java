package baeldung.persistence.dao.common;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import baeldung.persistence.hibernate.model.Items;

@Repository
public class ItemsDAO extends AbstractHibernateDAO<Items> {

    /** constructor*/
    public ItemsDAO() {
        super();
        setClazz(Items.class);
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

}