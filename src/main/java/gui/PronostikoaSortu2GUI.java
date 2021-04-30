package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Question;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PronostikoaSortu2GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PronostikoaSortu2GUI frame = new PronostikoaSortu2GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public PronostikoaSortu2GUI() {
		super();
		getContentPane().setLayout(null);
		
		
	}

	/**
	 * Create the frame.
	 */
	public PronostikoaSortu2GUI(Question q, JFrame aurrekoFrame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PRONOSTIKOA SORTU");
		lblNewLabel.setBounds(141, 37, 168, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Kuota:");
		lblNewLabel_2.setBounds(70, 107, 85, 16);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(193, 105, 116, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("sortu");
		btnNewButton.setBounds(154, 201, 97, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text1 = textField.getText();
				double kuota = Double.parseDouble(text1);
				if(kuota>1.0) {
				MainGUI m = new MainGUI();
				BLFacade bf = m.getBusinessLogic();
				bf.pronostikoaSortu(kuota, q.getQuestionNumber());
				contentPane.setVisible(false);
				aurrekoFrame.setVisible(true);
				}else {
					System.out.println("kuota txikiegia");
				}
			}
		});
		contentPane.add(btnNewButton);
	}
}
