package WilliPan.PanProject.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import WilliPan.PanProject.domain.ListTimeTask;
import WilliPan.PanProject.domain.ListTask;

public class ListTimeTaskService
{
  protected EntityManager em;

  public ListTimeTaskService(EntityManager em)
  {
    this.em = em;
  }

  public ListTimeTask save(ListTimeTask element)
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
    ListTimeTask object = findById(id);

    if (object != null)
      em.remove(object);
  }

  public ListTimeTask findById(long id)
  {
    return (ListTimeTask)em.find(ListTask.class, id);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<ListTimeTask> findAll()
  {
    TypedQuery<ListTask> query = em.createQuery("SELECT e FROM t_task e where t_type = 'S'", ListTask.class);
    return (List)query.getResultList();
  }
}
