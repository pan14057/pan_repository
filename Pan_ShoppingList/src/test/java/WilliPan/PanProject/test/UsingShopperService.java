package WilliPan.PanProject.test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.*;

import org.joda.time.DateTime;
import org.junit.*;

import WilliPan.PanProject.domain.Shopper;
import WilliPan.PanProject.service.ShopperService;
import static org.hamcrest.CoreMatchers.*;

public class UsingShopperService
{
  private EntityManagerFactory factory;
  private EntityManager manager;

  @Before
  public void before()
  {
    factory = Persistence.createEntityManagerFactory("BaseService");
    
    if(factory != null)
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
    ShopperService service = new ShopperService(manager);
    List<Shopper> user = service.findAll();
    assertThat(user, notNullValue());
    assertThat(user.size(), is(0));
  }

  @Test
  public void ensureThatAfterInsertingAUserHeIsStored()
  {
    ShopperService service = new ShopperService(manager);
    Shopper newUser = new Shopper(null, 0, "Fritz", "fritz@box.com", "Fitz", "Box",
        DateTime.now(), "123456");
    newUser = service.save(newUser);

    Shopper fromDb = service.findById(newUser.getId());

    assertThat(fromDb, notNullValue());
    assertThat(fromDb.getUsername(), is(newUser.getUsername()));
  }
  
  @Test
  public void ensureThatAfterInsertingOneElementFindAllReturnsAListWithOneElement()
  {
    ShopperService service = new ShopperService(manager);
    Shopper newUser = new Shopper(null, 0, "Fritz", "fritz@box.com", "Fitz", "Box",
        DateTime.now(), "123456");
    newUser = service.save(newUser);

    List<Shopper> fromDb = service.findAll();

    assertThat(fromDb, notNullValue());
    assertThat(fromDb.size(), is(1));
  }
  
  @Test
  public void ensureThatAfterInsertingAUserAndDeletingHimHeIsNotStored()
  {
    ShopperService service = new ShopperService(manager);
    Shopper newUser = new Shopper(null, 0, "Fritz", "fritz@box.com", "Fitz", "Box",
        DateTime.now(), "123456");
    newUser = service.save(newUser);
    service.removeById(newUser.getId());
    
    Shopper fromDb = service.findById(newUser.getId());

    assertThat(fromDb, is(nullValue()));
  }
}
