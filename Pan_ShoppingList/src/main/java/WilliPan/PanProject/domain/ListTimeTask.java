package WilliPan.PanProject.domain;

import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("S")
public class ListTimeTask extends ListTask
{
  private static final long serialVersionUID = 1L;

  @Column(name = "s_date", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  public ListTimeTask()
  {
    date = new Date();
  }

  public ListTimeTask(Long id, String title, TaskStatus status, DateTime date)
  {
	  super(id, title,status);
    this.date = date.toDate();
  }

  @Override
  public Date getDate()
  {
    return date;
  }
}
