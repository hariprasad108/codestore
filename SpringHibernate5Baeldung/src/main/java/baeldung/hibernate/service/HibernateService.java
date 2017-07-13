package baeldung.hibernate.service;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import baeldung.persistence.dao.common.CartDAO;
import baeldung.persistence.hibernate.model.Cart;
import baeldung.persistence.hibernate.model.Items;

@Service
@Repository
@Transactional
public class HibernateService {
    private final Logger logger = LoggerFactory.getLogger(HibernateService.class);

    @Autowired
    CartDAO cartDAO;
    
    public HibernateService() {
        super();
        logger.info("***HibernateService is greating you from Constructor ***");
    }

    public Session getSession() {
        return cartDAO.getSession();
    }
    
    @Transactional
    public Cart createCart(Cart cart) {
        //String SQL = "insert into student (id, name, age) values (?, ?, ?)";
        Integer id;
        Session session = cartDAO.getSession();
        id = (Integer) session.save(cart);
        // deep copy
        Cart cartRet = new Cart(cart);
        cartRet.setId(id);
        logger.info("+++++++ Cart created: " + cartRet.toString());
        return cartRet;
    }
    
    @Transactional
    public Items createItem(Items item) {
        //String SQL = "insert into student (id, name, age) values (?, ?, ?)";
        Integer id;
        Session session = cartDAO.getSession();
        logger.info("------- Item will be created: " + item.toString());

        id = (Integer) session.save(item);
        // deep copy
        Items itemRet = new Items(item);
        itemRet.setId(id);
        logger.info("+++++++ Item created: " + itemRet.toString());
        return itemRet;
    }

    @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Cart getCart(Integer id) {
        Cart cart = null;
        cart = cartDAO.findOne(id);
        return cart;
    }
    
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<Cart> listCarts() {
        List<Cart> carts = null;
        carts = cartDAO.findAll();
        return carts;
    }

    @Transactional
    public Cart updateCart(Cart cart) {
        Cart cartOld = new Cart(getCart(cart.getId()));
        if (cartOld != null) {
          // to avoid org.springframework.dao.DuplicateKeyException
          cartDAO.getSession().merge(cart);
        }        
        System.out.println("Cart updated: " + cartOld);
        return cartOld;
    }

    @Transactional
    public Cart deleteCart(Integer id) {
        Cart cartOld = getCart(id);
        if (cartOld != null) {
            cartDAO.getSession().delete(cartOld);
        }
        System.out.println("Deleted Record with Id = " + cartOld);
        return cartOld;
    }    
}