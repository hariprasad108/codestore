package baeldung.hibernate.main;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import baeldung.hibernate.application.ApplicationManager;
import baeldung.persistence.hibernate.model.Cart;
import baeldung.persistence.hibernate.model.Items;

public class SpringHibernateMain {
    private final Logger logger = LoggerFactory.getLogger(SpringHibernateMain.class);

    ApplicationManager applicationManager;
    AnnotationConfigApplicationContext context;

    SpringHibernateMain() {
        super();
        applicationManager = new ApplicationManager();
        try {
            context = applicationManager.getContext();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SpringHibernateMain ham = new SpringHibernateMain();
        Cart cart = new Cart(null, "ABC", null);

        Cart cartRet = ham.applicationManager.getApplication()
            .addCart(cart);
        ham.logger.info("Cart added: " + cartRet);

        Items item1 = new Items(cartRet);
        Items item2 = new Items(cartRet);
        List<Items> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);

        List<Items> itemsRet = ham.applicationManager.getApplication()
            .addItems(itemsList);
        cart.setItems(itemsRet);

        //**** cart for find and testing ****
        Cart cartFind = ham.applicationManager.getApplication().getCart(44);

        // treat null
        ham.logger.info("Find cart: " + (cartFind == null ? null : cartFind.toString()));

        List<Cart> carts = ham.applicationManager.getApplication()
            .listCarts();
        ham.logger.info("**** Carts list **** ");
        carts.forEach((cartLambda) -> ham.logger.info("Cart: " + cartLambda.toString()));

        if (cartFind == null) {
            ham.logger.info("Cart not found");
        } else {
            Cart cartUpd = new Cart(cartFind.getId(), cartFind.getId() + cartFind.getIdentification(), cartFind.getItems());

            Cart cartUpdOld = ham.applicationManager.getApplication()
                .updateCart(cartUpd);

            Cart cartFindUpd = ham.applicationManager.getApplication()
                .getCart(cartUpd.getId());

            ham.logger.info("Update cart old: " + cartUpdOld.toString());
            ham.logger.info("Update cart new: " + cartFindUpd.toString());

            Cart cartDelOld = ham.applicationManager.getApplication()
                .deleteCart(cartFind.getId());

            ham.logger.info("Deleted cart: " + (cartDelOld == null ? null : cartDelOld.toString()));
        }
        ham.logger.info("//// Finished ////");
    }

}