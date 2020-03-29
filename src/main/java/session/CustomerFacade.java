/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package session;

import entity.Customer;
import javax.ejb.Stateless;

/**
 *
 * @author tgiunipero
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {

    public CustomerFacade() {
        super(Customer.class);
    }
}
