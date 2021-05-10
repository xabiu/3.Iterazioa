package businessLogic;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Question;
import domain.Admin;
import domain.ApostuAnitza;
import domain.Erabiltzailea;
import domain.Erregistratua;
import domain.Event;
import domain.Pronostikoa;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade {

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	@WebMethod
	Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;

	/**
	 * This method creates a admin type of user.
	 * 
	 * @param iz        the new admins' name
	 * @param nan       the new admins' nan
	 * @param adina     the admins' age
	 * @param pasahitza the admins' account password
	 * @param adminZb   admin number
	 * @return the created admin type user, or null.
	 */
	@WebMethod
	Admin createAdmin(String iz, String nan, String adina, String pasahitza, int adminZb);
	 @WebMethod 
	 public Erabiltzailea erabiltzaileaBadago(String izena, String pasahitza);
	/**
	 * This method creates a registered type of user.
	 * 
	 * @param iz        the new users' name
	 * @param nan       the new users' nan
	 * @param adina     the users' age
	 * @param pasahitza the users' account password
	 * @return the created registered type user, or null.
	 */
	@WebMethod
	Erregistratua createErregistratua(String iz, String nan, String adina, String pasahitza);

	/**
	 * This method allows or prevents the users access to the database and account.
	 * 
	 * @param izena     Username to check whether it exists
	 * @param pasahitza Password to check along with the username
	 * @return True if the user exists and the password is correct, False otherwise.
	 */
	@WebMethod
	boolean erabiltzaileZuzena(String izena, String pasahitza);

	/**
	 * This method retrieves the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod
	public Vector<Event> getEvents(Date date);

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@WebMethod
	public Vector<Date> getEventsMonth(Date date);

	/**
	 * This method calls the data access to initialize the database with some events
	 * and questions. It is invoked only when the option "initialize" is declared in
	 * the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	public void initializeBD();
	
	@WebMethod
	public int getGertaeraHandienaLortu();
	
	@WebMethod
	public void diruaSartu(double dirua,String iz, String pas);
	
	@WebMethod
	public void gertaeraSortu(String azalpena,Date date,int zenb);
	
	@WebMethod
	public void pronostikoaSortu(double kuota, int pronostikoZb, int z2, String emaitza);
	
	@WebMethod
	public void apustuaEgin(Pronostikoa p, String emaitza, double dirua, String erabNAN, int z);
	
	@WebMethod
	public int zenbakiHandienaLortu();
	
	@WebMethod
	public void emaitzaIpini(Question q, String e);
	
	@WebMethod
	public Erregistratua ErregistratuaBilatu(String NAN);
	
	@WebMethod
	public void gertaeraKendu(Event e);
	
	@WebMethod
	public Question galderaLortu(int i);
	
	@WebMethod
	public int pronostikoHandienaLortu();
	
	@WebMethod
	public int zenbakiAnitzHandienaLortu();
	
	@WebMethod
	public void apustuAnitzaEgin(int Zb, double dirua, String NAN, double kuotaM, ArrayList<Pronostikoa> l);
	
	@WebMethod
	public void listatikKendu(int Zb, Pronostikoa p1);
	
	@WebMethod
	public void erabilgarritasunaKendu(int z);
	
	@WebMethod
	public ApostuAnitza apostuAnitzaLortu(int z);
	
	@WebMethod
	public void apostuAnitzaKendu(int z);
	
	@WebMethod
	public void erreplikatu(String noriNAN, Erregistratua e);
	
	@WebMethod
	public List<Erregistratua> erabiltzaileGuztiakLortu();
	
	@WebMethod
	public void kenduBonoBat(String NAN) ;
	
	@WebMethod
	public void bonoakEguneratu(String NAN, int z);
		
	
}
