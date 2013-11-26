package WilliPan.PanProject.test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.joda.time.DateTime;
import org.junit.*;
import WilliPan.PanProject.domain.ListTimeTask;
import WilliPan.PanProject.domain.ListTask;
import WilliPan.PanProject.domain.Shopper;
import WilliPan.PanProject.domain.ListTask.TaskStatus;
import WilliPan.PanProject.service.ListTimeTaskService;
import WilliPan.PanProject.service.ShopperService;
import static org.hamcrest.CoreMatchers.*;

public class UsingListTimeTaskService
{
  private EntityManagerFactory factory;
  private EntityManager manager;

  @Before
  public void before()
  {
    factory = Persistence.createEntityManagerFactory("BaseService");

    if (factory != null)
      manager = factory.createEntityManager();
  }

  @After
  public void after()
  {
    if (manager != null)
      manager.close();
    if (factory != null)
      factory.close();
  }

  @Test
  public void ensureThatFindAllOnEmptyDBReturnsNoResult()
  {
    ListTimeTaskService service = new ListTimeTaskService(manager);
    List<ListTimeTask> tasks = service.findAll();
    assertThat(tasks, notNullValue());
    assertThat(tasks.size(), is(0));
  }

  @Test
  public void ensureThatAfterInsertingATaskHeIsStored()
  {
    

    ListTimeTaskService service = new ListTimeTaskService(manager);
    ListTimeTask task = new ListTimeTask(null, "Wochenmeeting", TaskStatus.Open, DateTime.now());
    task = service.save(task);

    ListTimeTask fromDb = service.findById(task.getId());

    assertThat(fromDb, notNullValue());
  }

  @Test
  public void ensureThatAfterInsertingOneElementFindAllReturnsAListWithOneElement()
  {
    ListTimeTaskService service = new ListTimeTaskService(manager);
    ListTimeTask task = new ListTimeTask(null, "Wochenmeeting", TaskStatus.Open, DateTime.now());
    task = service.save(task);

    List<ListTimeTask> fromDb = service.findAll();

    assertThat(fromDb, notNullValue());
    assertThat(fromDb.size(), is(1));
  }

  @Test
  public void ensureThatAfterInsertingATaskAndDeletingHimHeIsNotStored()
  {
    Shopper owner = generateAndInsertValidUser();
    Collection<Shopper> worker = new ArrayList<Shopper>();
    worker.add(owner);

    ListTimeTaskService service = new ListTimeTaskService(manager);
    ListTimeTask task = new ListTimeTask(null, "Obst", TaskStatus.Open, DateTime.now());
    task = service.save(task);
    service.removeById(task.getId());

    ListTimeTask fromDb = service.findById(task.getId());

    assertThat(fromDb, is(nullValue()));
  }

  private Shopper generateAndInsertValidUser()
  {
    ShopperService service = new ShopperService(manager);
    Shopper newUser = new Shopper(null, 0, "Hans", "hans.mustermann@gmail.com", "Hans", "Mustermann",
        DateTime.now(), "11223344");
    return service.save(newUser);
  }
}
