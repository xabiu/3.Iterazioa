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
import domain.Event;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class GertaeraAukeratuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private DefaultComboBoxModel<Event> lista = new DefaultComboBoxModel<Event>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GertaeraAukeratuGUI frame = new GertaeraAukeratuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GertaeraAukeratuGUI() {
		super();
	}

	/**
	 * Create the frame.
	 */
	public GertaeraAukeratuGUI(JFrame aurrekoFrame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sartu Data:");
		lblNewLabel.setBounds(12, 13, 88, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Eguna:");
		lblNewLabel_1.setBounds(32, 42, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Hilabetea:");
		lblNewLabel_2.setBounds(32, 71, 68, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Urtea:");
		lblNewLabel_3.setBounds(32, 100, 56, 16);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(100, 39, 56, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 68, 56, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(100, 97, 56, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Aukeratu Gertaera:");
		lblNewLabel_4.setBounds(173, 42, 121, 16);
		contentPane.add(lblNewLabel_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(299, 39, 121, 22);
		comboBox.setModel(lista);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Gertaeraren emaitzak ipini");
		btnNewButton.setBounds(117, 190, 203, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Event e1 = (Event) comboBox.getSelectedItem();
				if(!e1.getQuestions().isEmpty()) {
				System.out.println("Galdera: " + e1.getQuestions().get(0));
				JFrame a = new EmaitzakIpiniGUI(aurrekoFrame,e1);
				a.setVisible(true);
				contentPane.setVisible(false);
				}else {
					System.out.println("honek ez ditu galderarik, aukeratu beste bat.");
				}
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("gertaerak bilatu");
		btnNewButton_1.setBounds(32, 134, 124, 25);
		btnNewButton_1.addActionListener(new ActionListener() {
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
		contentPane.add(btnNewButton_1);
	}
}
