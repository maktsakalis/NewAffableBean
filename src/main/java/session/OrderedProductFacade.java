/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package session;

import entity.OrderedProduct;
import javax.ejb.Stateless;

/**
 *
 * @author tgiunipero
 */
@Stateless
public class OrderedProductFacade extends AbstractFacade<OrderedProduct> {

    public OrderedProductFacade() {
        super(OrderedProduct.class);
    }

}
