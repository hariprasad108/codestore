package baeldung.hibernate.application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import baeldung.hibernate.service.HibernateService;
import baeldung.persistence.hibernate.model.Cart;
import baeldung.persistence.hibernate.model.Items;

/**
 * 
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 * 
 */
@Component
public class PersistenceApplication {
    private final Logger logger = LoggerFactory.getLogger(PersistenceApplication.class);

    @Autowired
    private HibernateService hibernateService;
        
    public PersistenceApplication() {
        super();
        logger.info("***PersistenceApplication from Constructor ***");
    }
    
    public Cart addCart(Cart cart) {
        Cart cartRet = hibernateService.createCart(cart);
        logger.info("Cart created: " + cartRet);
        return cartRet;
    }
    
    public List<Items> addItems(List<Items> items) {
        List<Items> itemsRet = new ArrayList<>();
        items.forEach((item) -> itemsRet.add(hibernateService.createItem(item)));
        logger.info("Items created: " + itemsRet.toString());
        
        return itemsRet;
    }
    
    public Cart getCart(Integer id) {
        Cart cart = null;
        cart = hibernateService.getCart(id);
        return cart;
    }
    
    public List<Cart> listCarts() {
        List<Cart> carts = null;
        carts = hibernateService.listCarts();
        return carts;
    }
    
    public Cart updateCart(Cart cart) {
        Cart cartOld = null;
        cartOld = hibernateService.updateCart(cart);
        return cartOld;        
    }
    
    public Cart deleteCart(Integer id) {
        Cart cartOld = null;
        cartOld = hibernateService.deleteCart(id);
        return cartOld;                
    }

    public HibernateService getHibernateService() {
        return hibernateService;
    }
    
    
}
