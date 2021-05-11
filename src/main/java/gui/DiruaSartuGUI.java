package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DiruaSartuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiruaSartuGUI frame = new DiruaSartuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DiruaSartuGUI() {
		super();
	}

	/**
	 * Create the frame.
	 */
	public DiruaSartuGUI(double dirua, JFrame aurrekoframe, String izena, String pasahitza) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 223, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Zenbat euro sartu nahi dituzu?");
		lblNewLabel.setBounds(12, 13, 181, 16);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(22, 37, 139, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Sartu dirua");
		btnNewButton.setBounds(44, 72, 97, 25);
		DiruaSartuGUI d = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				MainGUI a = new MainGUI();
				BLFacade bf = a.getBusinessLogic();
				double dirua2 = Double.parseDouble(textField.getText());
				bf.diruaSartu(dirua + dirua2, izena, pasahitza);
				System.out.println("dirua sartu da");
				d.setVisible(false);
				aurrekoframe.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
	}
}
