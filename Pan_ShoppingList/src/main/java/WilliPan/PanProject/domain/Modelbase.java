package WilliPan.PanProject.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

@MappedSuperclass
public class Modelbase implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;

  public Modelbase()
  {
    id = null;
  }

  public Modelbase(Long id, boolean needsRefresh)
  {
    this.id = id;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    // If id already set, throw exception to tell user that there is already an
    // id
    if (this.id != null)
      throw new IllegalStateException(
          "Not allowed to assign id to already id-assigned object");

    this.id = id;
  }

  public boolean isPersisted()
  {
    return id != null;
  }
}
