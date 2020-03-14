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
}
