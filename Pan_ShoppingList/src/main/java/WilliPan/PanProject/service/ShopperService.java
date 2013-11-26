package WilliPan.PanProject.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import WilliPan.PanProject.domain.Shopper;

public class ShopperService
{
  protected EntityManager em;

  public ShopperService(EntityManager em)
  {
    this.em = em;
  }

  public Shopper save(Shopper element)
  {
    if (!element.isPersisted())
    {
      em.getTransaction().begin();
      em.persist(element);
      em.getTransaction().commit();
    }

    return element;
  }

  public void removeById(long id)
  {
    Shopper object = findById(id);

    if (object != null)
      em.remove(object);
  }

  public Shopper findById(long id)
  {
    return em.find(Shopper.class, id);
  }

  public List<Shopper> findAll()
  {
    TypedQuery<Shopper> query = em.createQuery("SELECT e FROM u_user e",
        Shopper.class);

    return query.getResultList();
  }
}