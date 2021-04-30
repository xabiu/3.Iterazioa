package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Erregistratua;
import domain.Event;
import domain.Pronostikoa;
import domain.Question;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ApustuaEginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private DefaultComboBoxModel<Event> lista = new DefaultComboBoxModel<Event>();
	private DefaultComboBoxModel<Question> listaQ = new DefaultComboBoxModel<Question>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApustuaEginGUI frame = new ApustuaEginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ApustuaEginGUI() {
		super();
	}

	/**
	 * Create the frame.
	 */
	public ApustuaEginGUI(JFrame aurrekoframe, Erregistratua e1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Eguna:");
		lblNewLabel.setBounds(12, 60, 56, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hilabetea:");
		lblNewLabel_1.setBounds(12, 89, 75, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Urtea:");
		lblNewLabel_2.setBounds(12, 120, 56, 16);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(73, 57, 48, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(73, 86, 48, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(73, 117, 48, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("gertaerak bilatu");
		btnNewButton.setBounds(12, 149, 123, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lista.removeAllElements();
					MainGUI m = new MainGUI();
					BLFacade f = m.getBusinessLogic();
					int eguna = Integer.parseInt(textField.getText());
					int hilabetea = Integer.parseInt(textField_1.getText());
					int urte = Integer.parseInt(textField_2.getText());
					if(urte>1900) {
						urte = urte - 1900;
					}else {
						urte = urte + 100;
					}
					Date d = new Date(urte,hilabetea,eguna);
					List<Event> lista2 = f.getEvents(d);
					for(Event e1: lista2) {
						lista.addElement(e1);
					}
					
				}catch(Exception c) {
					c.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("gertaerak:");
		lblNewLabel_3.setBounds(190, 60, 75, 16);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(263, 57, 157, 22);
		comboBox.setModel(lista);
		contentPane.add(comboBox);
		
		JButton btnNewButton_1 = new JButton("gertaera aukeratu");
		btnNewButton_1.setBounds(273, 99, 147, 25);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaQ.removeAllElements();
				Event selectedEvent = (Event) comboBox.getSelectedItem();
				List<Question> lista2 = selectedEvent.getQuestions();
				for(Question q: lista2) {
					listaQ.addElement(q);
				}
			}
		});
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("galdera aukeratu:");
		lblNewLabel_4.setBounds(147, 153, 104, 16);
		contentPane.add(lblNewLabel_4);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(263, 150, 157, 22);
		comboBox_1.setModel(listaQ);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton_2 = new JButton("Apustua egin");
		btnNewButton_2.setBounds(151, 215, 114, 25);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Question q = (Question) comboBox_1.getSelectedItem();
				Pronostikoa p = q.getPronostikoa();
				if(p != null) {
					ApustuaEgin2GUI ap = new ApustuaEgin2GUI(aurrekoframe,p,e1, q.getBetMinimum());
					ap.setVisible(true);
					contentPane.setVisible(false);
				}else {
					contentPane.setVisible(false);
					aurrekoframe.setVisible(true);
					System.out.println("ez dago pronostikorik galdera honerako");
				}
			}
		});
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel("APOSTATU!");
		lblNewLabel_5.setBounds(166, 13, 75, 16);
		contentPane.add(lblNewLabel_5);
	}
}
