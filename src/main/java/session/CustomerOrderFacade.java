/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package session;

import entity.CustomerOrder;
import javax.ejb.Stateless;

/**
 *
 * @author tgiunipero
 */
@Stateless
public class CustomerOrderFacade extends AbstractFacade<CustomerOrder> {

    public CustomerOrderFacade() {
        super(CustomerOrder.class);
    }

    @Override
    // overridden - refresh method called to retrieve order id from database
    public CustomerOrder find(Object id) {
        CustomerOrder order = getEntityManager().find(CustomerOrder.class, id);
        getEntityManager().refresh(order);
        return order;
    }

    // manually created
    // in this implementation, there is only one order per customer
    // the data model however allows for multiple orders per customer
    public CustomerOrder findByCustomer(Object customer) {
        return (CustomerOrder) getEntityManager().createNamedQuery("CustomerOrder.findByCustomer").setParameter("customer", customer).getSingleResult();
    }
}
