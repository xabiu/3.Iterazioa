package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
//import domain.Pronostico;
//import domain.RegisteredUser;
//import domain.User;
//import domain.Worker;
//import exceptions.IncorrectBetException;
import domain.*;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;

import com.toedter.calendar.JCalendar;
import javax.swing.SwingConstants;

public class ApostuaSortuGUI extends JFrame{

	private JFrame frame;
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;

	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	BLFacade facade;
	private JComboBox<Pronostikoa> comboBox;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostuaSortuGUI window = new ApostuaSortuGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public ApostuaSortuGUI(JFrame aurrekoa,Erregistratua e2) {
		try {
			initialize(aurrekoa,e2);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JFrame aurr, Erregistratua e) throws Exception{
		this.setSize(new Dimension(900, 500));
		getContentPane().setLayout(null);
		jLabelEventDate.setBounds(42, 57, 140, 25);

		this.getContentPane().add(jLabelEventDate);
		jLabelQueries.setBounds(52, 265, 239, 14);
		this.getContentPane().add(jLabelQueries);
		jLabelEvents.setBounds(297, 61, 259, 16);
		this.getContentPane().add(jLabelEvents);

		BLFacade facade = MainGUI.getBusinessLogic();///////////////////////////////////////////////////////////////////////////////
		this.facade=facade;
		ApostuaSortuGUI tempora=this;
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
		jCalendar1.setBounds(42, 92, 225, 150);
		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					 
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
					}



					CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													
					

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		this.getContentPane().add(jCalendar1);
		scrollPaneQueries.setBounds(new Rectangle(42, 292, 267, 116));

		
		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//btnApostua.setEnabled(false);
				
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());
				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();
					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});
		scrollPaneEvents.setBounds(294, 92, 346, 150);

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBox.removeAllItems();
				Question q=facade.galderaLortu((int)tableQueries.getValueAt(tableQueries.getSelectedRow(),0));
				List<Pronostikoa> tmp = q.getP();
				for (Pronostikoa i : tmp) {
					comboBox.addItem(i);
				}
			}
		});
		
		
		
		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		this.getContentPane().add(scrollPaneEvents);
		this.getContentPane().add(scrollPaneQueries, null);
		
		JLabel PronostikoLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApostuaSortuGUI.lblNewLabel.text"));
		PronostikoLabel.setBounds(328, 265, 152, 16);
		getContentPane().add(PronostikoLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(321, 297, 304, 22);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getItemCount()<1) {
					btnNewButton.setEnabled(false);
				}
			}
		});
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApostuaSortuGUI.lblNewLabel_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_1.setBounds(328, 387, 80, 16);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		//textField_1.setText(ResourceBundle.getBundle("Etiquetas").getString("ApostuaSortuGUI.textField_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		textField_1.setBounds(418, 386, 116, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		btnNewButton = new JButton("Apustua Egin"); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.setBounds(647, 369, 140, 39);
		ApostuaSortuGUI a = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double dirua = Double.parseDouble(textField_1.getText());
				Pronostikoa p = (Pronostikoa) comboBox.getSelectedItem();
				String emaitza = p.getEmaitza();
				if(dirua<= e.getDirua()) {
					int z = facade.zenbakiHandienaLortu();
					z++;
					if(e.bonoKopLortu()==0) {
						facade.apustuaEgin(p, emaitza, dirua, e.getNan(), z);
						facade.diruaSartu(e.getDirua()-dirua, e.getIz(), e.getPasahitza());
					}else {
						facade.apustuaEgin(p, emaitza, dirua, e.getNan(), z);
						facade.diruaSartu(e.getDirua()-(dirua*0.5), e.getIz(), e.getPasahitza());
						facade.kenduBonoBat(e.getNan());
					}
					if(!e.erreplikatuListaLortu().isEmpty()) {
						for(Erregistratua e2: e.erreplikatuListaLortu()) {
							if(dirua<= e2.getDirua()) {
								z++;
								facade.apustuaEgin(p, emaitza, dirua, e2.getNan(), z);
								facade.diruaSartu(e2.getDirua()-dirua, e2.getIz(), e2.getPasahitza());
							}else {
								System.out.println("erabiltzaile erreplikatu honek ezin du zure apostua egin: " + e2);
							}
						}
					}
				}else {
					System.out.println("ez duzu diru nahikorik, sartu diru gehiago zure kontuan edo txikitu apostuan sartutako dirua.");
				}
				a.setVisible(false);
				aurr.setVisible(true);
			}
		});
		getContentPane().add(btnNewButton);
	}
}
