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

public class GertaerakAurkeratu extends JFrame{

	private JFrame frame;
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
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
	//private DefaultTableModel tableModelQueries;

	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	//private String[] columnNamesQueries = new String[] {
			//ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			//ResourceBundle.getBundle("Etiquetas").getString("Query")

	//};
	BLFacade facade;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GertaerakAurkeratu window = new GertaerakAurkeratu();
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
	public GertaerakAurkeratu(JFrame aurrekoa) {
		try {
			initialize(aurrekoa);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JFrame aurrekoa) {
		this.setSize(new Dimension(900, 500));
		getContentPane().setLayout(null);
		jLabelEventDate.setBounds(42, 57, 140, 25);

		this.getContentPane().add(jLabelEventDate);
		jLabelEvents.setBounds(297, 61, 259, 16);
		this.getContentPane().add(jLabelEvents);

		BLFacade facade = MainGUI.getBusinessLogic();///////////////////////////////////////////////////////////////////////////////
		this.facade=facade;
		GertaerakAurkeratu tempora=this;
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
		/*Administrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
				tempora.setVisible(false);
				new Administer(tempora).setVisible(true);
			}
		});

		
		
		Administrar.setBounds(42, 11, 117, 23);
		Administrar.setVisible(false);
		getContentPane().add(Administrar);*/
		
		/*btnApostua = new JButton("Apostua egin");
		btnApostua.setVisible(false);
		btnApostua.setEnabled(false);
		btnApostua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double dirukantitatea;
				reset();
				try {
					dirukantitatea=Double.parseDouble(textFieldDirua.getText());
				}
			catch (java.lang.NumberFormatException e) {
				lblErrores.setText("Ez da kantitate egoki bat");
				return;
			}catch (java.lang.NullPointerException e) {
				dirukantitatea=0;
			}
				
				if (usuario.getBalance()<dirukantitatea) {
					lblErrores.setText("Ez duzu diru nahikorik");
					return;
				}

				try {
					loged(facade.ApostuaEgin(dirukantitatea, usuario, (Pronostico)comboBoxPronostico.getSelectedItem()));
				}
				catch (IncorrectBetException e) {
					lblErrores.setText("Kantitateak galderaren minimoa gainditu behar du");
				}
			}
		});
		btnApostua.setBounds(551, 419, 130, 30);
		getContentPane().add(btnApostua);
		*/
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

						//jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		this.getContentPane().add(jCalendar1);
		scrollPaneEvents.setBounds(294, 92, 346, 150);
		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);
		tableEvents.setModel(tableModelEvents);
		this.getContentPane().add(scrollPaneEvents);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("GertaerakAurkeratu.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.setBounds(463, 312, 177, 40);
		JFrame aa = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=tableEvents.getSelectedRow();
				Event e=(Event)tableModelEvents.getValueAt(i,2);
				JFrame a = new EmaitzakIpiniGUI(aurrekoa,e,0);
				a.setVisible(true);
				aa.setVisible(false);
			}
		});
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("GertaerakAurkeratu.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setBounds(42, 13, 280, 16);
		getContentPane().add(lblNewLabel);
	}
}
