package WilliPan.PanProject.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Entity(name = "t_task")
@DiscriminatorColumn(name="t_type")
public abstract class ListTask extends Modelbase
{
  private static final long serialVersionUID = 1L;

  @Column(name = "t_productname", length = 50, nullable = false)
  private String productname;

  @Column(name = "t_status", length = 20, nullable = false)
  @Enumerated(EnumType.STRING)
  private TaskStatus status;


  public ListTask()
  {
    status = TaskStatus.Open;
  }

  public ListTask(Long id, String title, TaskStatus status)
  {
    super(id, false);
    this.productname = title;
    this.status = status;
  }

public abstract Date getDate();

  public String getTitle()
  {
    return productname;
  }

  public TaskStatus getStatus()
  {
    return status;
  }


  public void setTitle(String title)
  {
    this.productname = title;
  }

  public void accomplished()
  {
    status = TaskStatus.Accomplished;
  }

  public void delete()
  {
    status = TaskStatus.Deleted;
  }

  public enum TaskStatus
  {
    Open, Accomplished, Deleted;
  }
}
