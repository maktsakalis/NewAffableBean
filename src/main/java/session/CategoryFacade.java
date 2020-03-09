/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package session;

import entity.Category;
import entity.Category_;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author tgiunipero
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> {

    @PersistenceContext(unitName = "AffableBean")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }

    public Category getCategoryWithProducts(Short id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Category> category = cq.from(Category.class);
        category.fetch(Category_.PRODUCT_COLLECTION);

        cq.where(cb.equal(category.get(Category_.id), id));
        cq.select(category);

        TypedQuery<Category> tq = em.createQuery(cq);
        return tq.getSingleResult();
    }
}
