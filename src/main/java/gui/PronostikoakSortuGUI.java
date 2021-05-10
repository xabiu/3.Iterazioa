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

import javax.swing.AbstractButton;
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

public class PronostikoakSortuGUI extends JFrame{

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
					PronostikoakSortuGUI window = new PronostikoakSortuGUI();
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
	public PronostikoakSortuGUI(JFrame aurrekoa) {
		try {
			initialize(aurrekoa);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JFrame aurrekoa) throws Exception {
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
		PronostikoakSortuGUI tempora=this;
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
				tableModelQueries.setColumnCount(3);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());
				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();
					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q);
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2));
			}
		});
		scrollPaneEvents.setBounds(294, 92, 346, 150);

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		//tableQueries.addMouseListener(new MouseAdapter() {
		 /*
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBox.removeAllItems();
				Question q=facade.galderaLortu((int)tableQueries.getValueAt(tableQueries.getSelectedRow(),0));
				List<Pronostikoa> tmp = q.getP();
				for (Pronostikoa i : tmp) {
					comboBox.addItem(i);
				}
			*/
				/*if (tmp.size()>0) {
					btnApostua.setEnabled(true);
				}*/
			
			//}
		//}); 
		
		
		
		
		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		this.getContentPane().add(scrollPaneEvents);
		this.getContentPane().add(scrollPaneQueries, null);
		
		JLabel lblNewLabel = new JLabel("Pronostikoaren kuota"); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setBounds(353, 300, 140, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pronostikoaren emaitza"); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_1.setBounds(353, 352, 140, 16);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		//textField.setText(); //$NON-NLS-1$ //$NON-NLS-2$
		textField.setBounds(505, 297, 116, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		//textField_1.setText("Pronostikoaren emaitza"); //$NON-NLS-1$ //$NON-NLS-2$
		textField_1.setBounds(505, 349, 116, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		btnNewButton = new JButton("Pronostikoak Sortu"); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.setBounds(677, 348, 140, 43);
		PronostikoakSortuGUI p = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double kuota = Double.parseDouble(textField.getText());
				String emaitza = textField_1.getText();
				int i=tableQueries.getSelectedRow();
				Question q=(Question)tableModelQueries.getValueAt(i,2);
				int z = facade.pronostikoHandienaLortu();
				facade.pronostikoaSortu(kuota, z, q.getQuestionNumber(),emaitza);
				p.setVisible(false);
				aurrekoa.setVisible(true);
			}
		});
		getContentPane().add(btnNewButton);
	}
}
