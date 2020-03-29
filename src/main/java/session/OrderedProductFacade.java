/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package session;

import entity.OrderedProduct;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author tgiunipero
 */
@Stateless
public class OrderedProductFacade extends AbstractFacade<OrderedProduct> {

    public OrderedProductFacade() {
        super(OrderedProduct.class);
    }

    public List<OrderedProduct> findByOrderId(int orderId) {
        Query query = getEntityManager().createNamedQuery("OrderedProduct.findByCustomerOrderId");

        query.setParameter("customerOrderId", orderId);
        return query.getResultList();
    }

}
