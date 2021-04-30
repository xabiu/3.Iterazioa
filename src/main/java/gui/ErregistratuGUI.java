package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.*;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ErregistratuGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErregistratuGUI frame = new ErregistratuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ErregistratuGUI() {
		super();
	}

	/**
	 * Create the frame.
	 */
	public ErregistratuGUI(String izena, String pasahitza) {
		BLFacade bl = MainGUI.getBusinessLogic();
		Erregistratua e = (Erregistratua) bl.erabiltzaileaBadago(izena, pasahitza);
		JFrame unekoframe = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Galderak ikusi");
		btnNewButton.setBounds(12, 32, 408, 43);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new FindQuestionsGUI();
				a.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Apustua Egin");
		btnNewButton_1.setBounds(12, 89, 408, 43);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				ApustuaEginGUI ap = new ApustuaEginGUI(unekoframe,e);
				ap.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Dirua Sartu");
		btnNewButton_2.setBounds(12, 145, 408, 43);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				DiruaSartuGUI a = new DiruaSartuGUI(e.getDirua(),unekoframe,e.getIz(),e.getPasahitza());
				a.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Zure dirua :");
		lblNewLabel.setBounds(51, 208, 83, 32);
		contentPane.add(lblNewLabel);
	
		JLabel lblNewLabel_1 = new JLabel(Double.toString(e.getDirua()));
		lblNewLabel_1.setBounds(182, 216, 56, 16);
		contentPane.add(lblNewLabel_1);
	}
}
