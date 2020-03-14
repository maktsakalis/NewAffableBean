/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package session;

import entity.Product;
import javax.ejb.Stateless;

/**
 *
 * @author tgiunipero
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    public ProductFacade() {
        super(Product.class);
    }

}
