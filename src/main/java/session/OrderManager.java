/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package session;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import entity.Customer;
import entity.CustomerOrder;
import entity.OrderedProduct;
import entity.OrderedProductPK;
import entity.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author makis
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class OrderManager {

    @PersistenceContext(unitName = "AffableBean")
    private EntityManager entityManager;

    @Resource
    private SessionContext context;

    @EJB
    CustomerOrderFacade customerOrderFacade;

    @EJB
    CustomerFacade customerFacade;

    @EJB
    OrderedProductFacade orderedProductFacade;

    @EJB
    ProductFacade productFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int placeOrder(String name, String email, String phone, String address, String cityRegion, String ccNumber, ShoppingCart cart) {

        try {
            Customer customer = addCustomer(name, email, phone, address, cityRegion, ccNumber);
            CustomerOrder order = addOrder(customer, cart);
            addOrderedItems(order, cart);
            return order.getId();
        } catch (Exception ex) {
            context.setRollbackOnly();
            return 0;
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private Customer addCustomer(String name, String email, String phone, String address, String cityRegion, String ccNumber) {

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCityRegion(cityRegion);
        customer.setCcNumber(ccNumber);

//        entityManager.persist(customer);
        customerFacade.create(customer);

        return customer;
    }

    private CustomerOrder addOrder(Customer customer, ShoppingCart cart) {

        CustomerOrder order = new CustomerOrder();
        order.setCustomer(customer);
        order.setAmount(BigDecimal.valueOf(cart.getTotal()));

        Random random = new Random();
        int i = random.nextInt(999999999);
        order.setConfirmationNumber(i);

        entityManager.persist(order);

        return order;
    }

    private void addOrderedItems(CustomerOrder order, ShoppingCart cart) {

        entityManager.flush();

        List<ShoppingCartItem> items = cart.getItems();

        for (ShoppingCartItem scItem : items) {

            int orderProductId = scItem.getProduct().getId();

            OrderedProductPK orderedProductPK = new OrderedProductPK();
            orderedProductPK.setCustomerOrderId(order.getId());
            orderedProductPK.setProductId(orderProductId);

            OrderedProduct orderedItem = new OrderedProduct(orderedProductPK);

            orderedItem.setQuantity(scItem.getQuantity());

            entityManager.persist(orderedItem);
        }
    }

    public Map getOrderDetails(int orderId) {
        Map orderMap = new HashMap();

        CustomerOrder order = customerOrderFacade.find(orderId);

        Customer customer = order.getCustomer();

        List<OrderedProduct> orderedProducts = orderedProductFacade.findByOrderId(orderId);

        List<Product> products = new ArrayList<>();

        orderedProducts.forEach(
                (OrderedProduct o) -> {
                    Product p = (Product) productFacade.find(o.getOrderedProductPK().getProductId());
                    products.add(p);
                });

        orderMap.put("orderRecord", order);
        orderMap.put("customer", customer);
        orderMap.put("orderedProducts", orderedProducts);
        orderMap.put("products", products);

        return orderMap;
    }
}
