package baeldung.persistence.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ITEMS")
public class Items implements Serializable {
    private static final long serialVersionUID = 6138075372911409678L;
    
    private Integer id;
    private Integer cartId;
    private Cart cart;

    // Hibernate requires no-args constructor
    public Items() {
        super();
    }
    
    public Items(Cart cart) {
        super();
        this.cartId = cart.getId();
        this.cart = cart;
    }

    /* constructor */
    public Items(Items items) {
        super();
        this.id = items.id;
        this.cartId = items.cartId;
        this.cart = items.cart;
    }

    @ManyToOne(targetEntity = Cart.class)
    @JoinColumn(name = "CART_ID")
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Id
    @SequenceGenerator(name = "itemsSeqLoc", sequenceName = "ITEMS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemsSeqLoc")
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "CART_ID", insertable = false, updatable=false)
    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Item [id=").append(id)
          .append(" cart_id: ").append(cartId)
          .append("]");
        return builder.toString();
    }

}