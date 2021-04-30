package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class PronostikoaSortuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private DefaultComboBoxModel<Event> lista = new DefaultComboBoxModel<Event>();
	private DefaultComboBoxModel<Question> listaQ = new DefaultComboBoxModel<Question>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PronostikoaSortuGUI frame = new PronostikoaSortuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public PronostikoaSortuGUI() {
		super();
	}

	/**
	 * Create the frame.
	 */
	public PronostikoaSortuGUI(JFrame aurrekoFrame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Eguna :");
		lblNewLabel_1.setBounds(12, 44, 44, 20);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 88, 105, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Hilabetea :");
		lblNewLabel_2.setBounds(12, 91, 76, 16);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(100, 132, 105, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Urtea:");
		lblNewLabel_3.setBounds(12, 135, 56, 16);
		contentPane.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(100, 43, 105, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JComboBox<Event> comboBox = new JComboBox<Event>();
		comboBox.setBounds(245, 43, 162, 22);
		comboBox.setModel(lista);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Datarako Gertaerak:");
		lblNewLabel.setBounds(245, 13, 162, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Bilatu gertaerak");
		btnNewButton.setBounds(44, 180, 123, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lista.removeAllElements();
					MainGUI m = new MainGUI();
					BLFacade f = m.getBusinessLogic();
					int eguna = Integer.parseInt(textField_3.getText());
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
		
		
		JButton btnNewButton_1 = new JButton("gertaera aukeratu");
		btnNewButton_1.setBounds(255, 87, 140, 25);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Event selectedEvent = (Event) comboBox.getSelectedItem();
				List<Question> lista2 = selectedEvent.getQuestions();
				for(Question q: lista2) {
					listaQ.addElement(q);
				}
			}
		});
		contentPane.add(btnNewButton_1);
		
		JComboBox<Question> comboBox_1 = new JComboBox<Question>();
		comboBox_1.setBounds(245, 145, 162, 22);
		comboBox_1.setModel(listaQ);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_4 = new JLabel("Gertaerarako galderak:");
		lblNewLabel_4.setBounds(245, 116, 162, 16);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton_3 = new JButton("pronostikoa sortu");
		btnNewButton_3.setBounds(116, 218, 160, 25);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Question selectedQuestion = (Question) comboBox_1.getSelectedItem();
				JFrame a = new PronostikoaSortu2GUI(selectedQuestion,aurrekoFrame);
				a.setVisible(true);
				contentPane.setVisible(false);	
			}
		});
		contentPane.add(btnNewButton_3);
	}
}
