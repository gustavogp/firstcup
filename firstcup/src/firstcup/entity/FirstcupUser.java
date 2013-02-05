package firstcup.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

/**
 * Entity implementation class for Entity: FirstcupUser
 *
 */
@Entity

@NamedQuery(name="findAverageAgeDifferenceOfAllFirstcupUsers",
query="SELECT AVG(u.ageDifference) FROM FirstcupUser u")

public class FirstcupUser implements Serializable {

	@Id
	@TableGenerator(name = "TAB_GEN", initialValue=1, pkColumnValue="SEQUENCE")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TAB_GEN")
	private int id;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Calendar birthday;
	private int ageDifference;
	private static final long serialVersionUID = 1L;

	public FirstcupUser() {
		super();
	}   
	
	public FirstcupUser(Date date, int ageDifference) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        this.setBirthday(cal);
        this.setAgeDifference(ageDifference);
    }
	
	public long getId() {
		return this.id;
	}

	public Calendar getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}   
	public int getAgeDifference() {
		return this.ageDifference;
	}

	public void setAgeDifference(int ageDifference) {
		this.ageDifference = ageDifference;
	}
   
}
