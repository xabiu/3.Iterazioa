package businessLogic;

import java.util.ArrayList;
//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Admin;
import domain.ApostuAnitza;
import domain.Erabiltzailea;
import domain.Erregistratua;
import domain.Event;
import domain.Pronostikoa;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	DataAccess dbManager = new DataAccess();

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			dbManager = new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
		}

	}

	public BLFacadeImplementation(DataAccess da) {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager = da;
	}

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
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

		// The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum);

		dbManager.close();

		return qry;
	};

	@WebMethod
	public Erabiltzailea erabiltzaileaBadago(String izena, String pasahitza) {
		dbManager.open(false);
		Erabiltzailea isLog = dbManager.erabiltzaileaBadago(izena, pasahitza);
		dbManager.close();
		return isLog;
	}

	@WebMethod
	public Admin createAdmin(String iz, String nan, String adina, String pasahitza, int adminZb) {
		dbManager.open(false);
		Admin adm = null;
		try {
			adm = dbManager.createAdmin(iz, nan, adina, pasahitza, adminZb);
		} catch (Exception e) {
			System.out.println("Erabiltzailea existitzen da");
		}
		dbManager.close();
		return adm;
	}

	@WebMethod
	public Erregistratua createErregistratua(String iz, String nan, String adina, String pasahitza) {
		dbManager.open(false);
		Erregistratua err = null;
		try {
			err = dbManager.createErregistratua(iz, nan, adina, pasahitza);
		} catch (Exception e) {
			System.out.println("Erabiltzailea existitzen da");
		}
		dbManager.close();
		return err;
	}

	@WebMethod
	public boolean erabiltzaileZuzena(String izena, String pasahitza) {
		dbManager.open(false);
		for (Erabiltzailea e : dbManager.getErabiltzaileGuztiak()) {
			if ((izena.compareTo(e.getIz()) == 0) && (pasahitza.compareTo(e.getPasahitza()) == 0)) {
				return true;
			}
		}
		dbManager.close();
		return false;
	}

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod
	public Vector<Event> getEvents(Date date) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date> dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	public void close() {
		DataAccess dB4oManager = new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option "initialize" is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	public void initializeBD() {
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	@WebMethod
	public int getGertaeraHandienaLortu() {
		dbManager.open(false);
		List<Event> l = dbManager.getGertaeraHandiena();
		dbManager.close();
		int h = 0;
		for (Event e : l) {
			if (h < e.getEventNumber()) {
				h = e.getEventNumber();
			}
		}
		return h;
	}

	@WebMethod
	public void gertaeraSortu(String azalpena, Date date, int zenb) {
		dbManager.open(false);
		dbManager.gertaeraSortu(azalpena, date, zenb);
		dbManager.close();
	}

	@WebMethod
	public void diruaSartu(double dirua, String iz, String pas) {
		dbManager.open(false);
		dbManager.diruaSartu(dirua, iz, pas);
		dbManager.close();
	}

	@WebMethod
	public void pronostikoaSortu(double kuota, int pronostikoZb, int z2, String Emaitza) {
		dbManager.open(false);
		dbManager.pronostikoaSortu(kuota, pronostikoZb, z2, Emaitza);
		dbManager.close();
	}

	@WebMethod
	public void apustuaEgin(Pronostikoa p, String emaitza, double dirua, String erabNAN, int z) {
		dbManager.open(false);
		dbManager.apustuaEgin(p, emaitza, dirua, erabNAN, z);
		dbManager.close();
	}

	@WebMethod
	public int zenbakiHandienaLortu() {
		dbManager.open(false);
		int z = dbManager.getApostuZbHandiena();
		dbManager.close();
		return z;
	}

	@WebMethod
	public void emaitzaIpini(Question q, String e) {
		dbManager.open(false);
		dbManager.emaitzaIpini(q, e);
		dbManager.close();
	}

	@WebMethod
	public Erregistratua ErregistratuaBilatu(String NAN) {
		dbManager.open(false);
		Erregistratua e = dbManager.ErregistratuaLortu(NAN);
		dbManager.close();
		return e;
	}

	@WebMethod
	public void gertaeraKendu(Event e) {
		dbManager.open(false);
		dbManager.gertaeraKendu(e.getEventNumber());
		dbManager.close();
	}

	@WebMethod
	public Question galderaLortu(int i) {
		dbManager.open(false);
		Question q = dbManager.galderaLortu(i);
		dbManager.close();
		return q;

	}

	@WebMethod
	public int pronostikoHandienaLortu() {
		dbManager.open(false);
		List<Pronostikoa> l = dbManager.pronostikoHandienaLortu();
		int max = 0;
		for (Pronostikoa p : l) {
			if (p.getPronostikoZb() > max) {
				max = p.getPronostikoZb();
			}
		}
		return max + 1;
	}

	@WebMethod
	public int zenbakiAnitzHandienaLortu() {
		dbManager.open(false);
		int i = dbManager.zenbakiAnitzHandienaLortu();
		dbManager.close();
		return i;
	}

	@WebMethod
	public void apustuAnitzaEgin(int Zb, double dirua, String NAN, double kuotaM, ArrayList<Pronostikoa> l) {
		dbManager.open(false);
		System.out.println(Zb);
		dbManager.apostuAnitzaSortu(Zb, dirua, NAN, kuotaM, l);
		dbManager.close();
	}

	@WebMethod
	public void listatikKendu(int Zb, Pronostikoa p1) {
		dbManager.open(false);
		dbManager.listatikKendu(Zb, p1);
		dbManager.close();
	}

	@WebMethod
	public void erabilgarritasunaKendu(int z) {
		dbManager.open(false);
		dbManager.erabilgarritasunaKendu(z);
		dbManager.close();
	}

	@WebMethod
	public ApostuAnitza apostuAnitzaLortu(int z) {
		dbManager.open(false);
		ApostuAnitza aa = dbManager.apostuAnitzaLortu(z);
		dbManager.close();
		return aa;
	}

	@WebMethod
	public void apostuAnitzaKendu(int z) {
		dbManager.open(false);
		dbManager.apostuAnitzakKendu(z);
		dbManager.close();
	}

	@WebMethod
	public void erreplikatu(String noriNAN, Erregistratua e) {
		dbManager.open(false);
		dbManager.erreplikatu(noriNAN, e);
		dbManager.close();
	}

	@WebMethod
	public List<Erregistratua> erabiltzaileGuztiakLortu() {
		dbManager.open(false);
		List<Erregistratua> e = dbManager.erabiltzaileGuztiakLortu();
		dbManager.close();
		return e;
	}

	@WebMethod
	public void kenduBonoBat(String NAN) {
		dbManager.open(false);
		dbManager.bonoBatKendu(NAN);
		dbManager.close();
	}

	@WebMethod
	public void bonoakEguneratu(String NAN, int z) {
		dbManager.open(false);
		dbManager.bonoakEguneratu(z, NAN);
		dbManager.close();
	}

}
