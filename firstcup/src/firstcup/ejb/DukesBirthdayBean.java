package firstcup.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import firstcup.entity.FirstcupUser;

/**
 * Session Bean implementation class DukesBirthdayBean
 */
@Stateless
@LocalBean
public class DukesBirthdayBean {
	private static final Logger logger = 
	        Logger.getLogger("firstcup.ejb.DukesBirthdayBean");
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public DukesBirthdayBean() {
        // TODO Auto-generated constructor stub
    }
    
    public Double getAverageAgeDifference() {
        Double avgAgeDiff =
                (Double) em.createNamedQuery("findAverageAgeDifferenceOfAllFirstcupUsers")
                                .getSingleResult();
        logger.info("Average age difference is: " + avgAgeDiff);
        return avgAgeDiff;
    }
    
    public int getAgeDifference(Date date) {
        int ageDifference;

        Calendar theirBirthday = new GregorianCalendar();
        Calendar dukesBirthday = new GregorianCalendar(1973, Calendar.APRIL, 25);

        // Set the Calendar object to the passed in Date
        theirBirthday.setTime(date);

        // Subtract the user's age from Duke's age
        ageDifference = dukesBirthday.get(Calendar.YEAR)
                - theirBirthday.get(Calendar.YEAR);
        logger.info("Raw ageDifference is: " + ageDifference);
        // Check to see if Duke's birthday occurs before the user's. If so,
        // subtract one from the age difference
        if (dukesBirthday.before(theirBirthday) && (ageDifference > 0)) {
            ageDifference--;
        }

        // create and store the user's birthday in the database
        FirstcupUser user = new FirstcupUser(date, ageDifference);
        em.persist(user);

        logger.info("Final ageDifference is: " + ageDifference);

        return ageDifference;
    }

}
