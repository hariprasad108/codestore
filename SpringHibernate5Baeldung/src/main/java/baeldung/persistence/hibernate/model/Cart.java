package baeldung.persistence.hibernate.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CART")
public class Cart implements Serializable {

    private Integer id;
    private String identification;

    private List<Items> items;

    /** no-args constructor */
    public Cart() {
        super();
    }

    /** constructor, deep copy */
    public Cart(Cart cart) {
        super();
        this.id = cart.id;
        this.identification = cart.identification;
        this.items = cart.items;
    }

    /** constructor */
    public Cart(Integer id, String identification, List<Items> items) {
        super();
        this.id = id;
        this.identification = identification;
        this.items = items;
    }

    @Id
    @SequenceGenerator(name = "cartSeqLoc", sequenceName = "CART_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartSeqLoc")
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "IDENTIFICATION", length = 80, nullable = true)
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart", cascade = CascadeType.ALL)
    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> itemsRet) {
        this.items = itemsRet;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(), itemsList = new StringBuilder();
        if (items != null) {
            items.forEach((item) -> itemsList.append(item.toString()));
        }
        builder.append("Cart [id=")
            .append(id)
            .append(" identification=")
            .append(identification)
            .append(" items: ")
            .append(itemsList)
            .append("]");
        return builder.toString();
    }

}