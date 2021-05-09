package dataAccess;

import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Question;
import domain.Admin;
import domain.ApostuAnitza;
import domain.Apustua;
import domain.Erabiltzailea;
import domain.Erregistratua;
import domain.Event;
import domain.Pronostikoa;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public DataAccess(boolean initializeMode) {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		open(initializeMode);

	}

	public DataAccess() {
		this(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month + 1, 28));
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month + 1, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month + 1, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month + 1, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

			}

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		// db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	public Admin createAdmin(String iz, String nan, String adina, String pasahitza, int adminZb) {
		System.out.println(">> DataAccess: createAdmin=> Izena= " + iz + " nan= " + nan + " adina= " + adina
				+ " adminZb= " + adminZb);

		db.getTransaction().begin();
		Admin a = new Admin(iz, nan, adina, pasahitza, adminZb);
		db.persist((Erabiltzailea) a);
		db.getTransaction().commit();
		return a;
	}

	public Erregistratua createErregistratua(String iz, String nan, String adina, String pasahitza) {
		System.out.println(">> DataAccess: createErregistratua=> Izena= " + iz + " nan= " + nan + " adina= " + adina);
		db.getTransaction().begin();
		Erregistratua e = new Erregistratua(iz, nan, adina, pasahitza);
		db.persist((Erabiltzailea) e);
		db.getTransaction().commit();
		return e;
	}

	public Erabiltzailea erabiltzaileaBadago(String erabiltzailea, String pasahitza) {
		Erabiltzailea erab;
		if ((erab = db.find(Erregistratua.class, erabiltzailea)) != null) {
			if (erab.getPasahitza().equals(pasahitza)) {
				return erab;
			}
		}
		if ((erab = db.find(Admin.class, erabiltzailea)) != null) {
			if (erab.getPasahitza().equals(pasahitza)) {
				return erab;
			}
		}
		return null;
	}
	
	public Erabiltzailea erabiltzaileaBadago2(String erabiltzailea, String pasahitza) {
		Erabiltzailea erab;
		if ((erab = db.find(Erregistratua.class, erabiltzailea)) != null) {
			if (erab.getPasahitza().equals(pasahitza)) {
				return erab;
			}
		}
		return null;
	}

	public List<Erabiltzailea> getErabiltzaileGuztiak() {
		db.getTransaction().begin();
//		System.out.println("hola");
		TypedQuery<Erabiltzailea> query = db.createQuery("SELECT erab FROM Erabiltzailea erab", Erabiltzailea.class);
		List<Erabiltzailea> erabiltzaileLista = query.getResultList();
		db.getTransaction().commit();
//		System.out.println(erabiltzaileLista.get(0).getIz());
		return erabiltzaileLista;
	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}
	
	public List<Pronostikoa> pronostikoHandienaLortu() {
		db.getTransaction().begin();
		TypedQuery<Pronostikoa> query = db.createQuery("SELECT p FROM Pronostikoa p ",Pronostikoa.class);
		List<Pronostikoa> l = query.getResultList();
		db.getTransaction().commit();
		return l;
	}
	
	public List<Event> getGertaeraHandiena() {
		db.getTransaction().begin();
		TypedQuery<Event> query = db.createQuery("SELECT e FROM Event e ",Event.class);
		List<Event> l = query.getResultList();
		db.getTransaction().commit();
		return l;
	}
	
	public int getApostuZbHandiena() {
		db.getTransaction().begin();
		TypedQuery<Apustua> query = db.createQuery("SELECT ap FROM Apustua ap ",Apustua.class);
		List<Apustua> l = query.getResultList();
		db.getTransaction().commit();
		if(l == null) {
			return 0;
		}else {
			int handiena = 0;
			for(Apustua a:l) {
				if(a.getZenbakia()>handiena) {
					handiena = a.getZenbakia();
				}
			}
			return handiena;
		}
		
	}
	public void gertaeraSortu(String azalpena,Date date,int zenb) {
		db.getTransaction().begin();
		Event e = new Event(zenb,azalpena,date);
		db.persist(e);
		db.getTransaction().commit();
	}
	
	public void pronostikoaSortu(double kuota, int pronostikoZb, int z2, String e) {
		db.getTransaction().begin();
		Pronostikoa p = new Pronostikoa(kuota,pronostikoZb,e);
		Question q = db.find(Question.class, z2);
		q.pronostikoaGehitu(p);
		db.persist(p);
		db.getTransaction().commit();
	}
	
	public void emaitzaIpini(Question q, String em) {
		db.getTransaction().begin();
		Question q1 = db.find(Question.class, q.getQuestionNumber());
		q1.setResult(em);
		db.getTransaction().commit();
	}
	
	public void apustuaEgin(Pronostikoa p, String emaitza, double dirua, String erabNAN,int z) {
		db.getTransaction().begin();
		Pronostikoa p1 = db.find(Pronostikoa.class, p.getPronostikoZb());
		Apustua a = new Apustua(emaitza,dirua,erabNAN,z);
		db.persist(a);
		p1.apustuaGehitu(a);
		db.getTransaction().commit();
	}
	
	public void diruaSartu(double dirua, String iz, String pas) {
		Erregistratua e = (Erregistratua) this.erabiltzaileaBadago2(iz, pas);
		db.getTransaction().begin();
		e.setDirua(dirua);
		db.getTransaction().commit();
	}
	
	public Erregistratua ErregistratuaLortu(String NAN) {
		db.getTransaction().begin();
		TypedQuery<Erregistratua> query = db.createQuery("SELECT e FROM Erregistratua e WHERE e.nan =? 1 ",Erregistratua.class);
		query.setParameter(1, NAN);
		List<Erregistratua> e1 = query.getResultList();
		Erregistratua e = e1.get(0);
		db.getTransaction().commit();
		return e;
	}
	
	public void gertaeraKendu(int e) {
		db.getTransaction().begin();
		Event e1 = db.find(Event.class, e);
		db.remove(e1);
		db.getTransaction().commit();
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}
	
	
	public int zenbakiAnitzHandienaLortu() {
		db.getTransaction().begin();
		TypedQuery<ApostuAnitza> query = db.createQuery("SELECT ap FROM ApostuAnitza ap ",ApostuAnitza.class);
		List<ApostuAnitza> l = query.getResultList();
		db.getTransaction().commit();
		if(l == null) {
			System.out.println("entra1");
			return 0;
		}else {
			System.out.println("entra2");
			int handiena = 0;
			for(ApostuAnitza a:l) {
				if(a.getZenbakia()>handiena) {
					handiena = a.getZenbakia();
				}
			}
			return handiena;
		}
	}
	
	public void apostuAnitzakKendu(int z) {
		db.getTransaction().begin();
		ApostuAnitza aa = db.find(ApostuAnitza.class, z);
		db.remove(aa);
		db.getTransaction().commit();
	}
	
	public void apostuAnitzaSortu(int Zb, double dirua, String NAN, double kuotaM, ArrayList<Pronostikoa> l) {
		db.getTransaction().begin();
		System.out.println(Zb);
		ApostuAnitza a = new ApostuAnitza(Zb,dirua,NAN,kuotaM,l);
		db.persist(a);
		for(Pronostikoa p: l) {
			Pronostikoa p1 = db.find(Pronostikoa.class, p.getPronostikoZb());
			p1.apustuAnitzaGehitu(a);
		}
		db.getTransaction().commit();
	}
	
	public void listatikKendu(int z, Pronostikoa p1) {
		db.getTransaction().begin();
		ApostuAnitza aa = db.find(ApostuAnitza.class, z);
		aa.listatikKenu(p1);
		db.getTransaction().commit();
	}
	
	public void erabilgarritasunaKendu(int z) {
		db.getTransaction().begin();
		ApostuAnitza aa = db.find(ApostuAnitza.class, z);
		aa.erabilgarritasunaKendu();
		db.getTransaction().commit();
	}
	
	public ApostuAnitza apostuAnitzaLortu(int z) {
		db.getTransaction().begin();
		ApostuAnitza aa = db.find(ApostuAnitza.class, z);
		db.getTransaction().commit();
		return aa;
	}

	public void open(boolean initializeMode) {

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode) {
			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}
	
	public Question galderaLortu(int i) {
		db.getTransaction().begin();
		Question q = db.find(Question.class, i);
		db.getTransaction().commit();
		return q;
	}

}
