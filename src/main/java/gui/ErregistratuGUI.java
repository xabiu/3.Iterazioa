package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

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
	public ErregistratuGUI(String izena, String pasahitza, JFrame main) {
		main.setVisible(false);
		BLFacade bl = MainGUI.getBusinessLogic();
		Erregistratua e = (Erregistratua) bl.erabiltzaileaBadago(izena, pasahitza);
//		lblNewLabel_1.setText(Double.toString(e.getDirua()));
		JFrame unekoframe = this;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Galderak ikusi");
		btnNewButton.setBounds(12, 12, 408, 43);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new FindQuestionsGUI();
				a.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Apustua Egin");
		btnNewButton_1.setBounds(12, 56, 408, 43);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				JFrame ap = new ApostuMotaGUI(unekoframe, e);
				ap.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Dirua Sartu");
		btnNewButton_2.setBounds(12, 100, 408, 43);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				DiruaSartuGUI a = new DiruaSartuGUI(e.getDirua(), unekoframe, e.getIz(), e.getPasahitza());
				a.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Erabiltzailea Erreplikatu");
		btnNewButton_3.setBounds(12, 144, 408, 43);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				ErabiltzaileaErreplikatuGUI a = new ErabiltzaileaErreplikatuGUI(unekoframe, e);
				a.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Amaitu Saioa");
		btnNewButton_4.setBounds(12, 188, 408, 43);
		JFrame s = this;
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				s.setVisible(false);
				main.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_4);

		JLabel lblNewLabel = new JLabel("Zure dirua :");
		lblNewLabel.setBounds(51, 252, 83, 32);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(Double.toString(e.getDirua()));
		lblNewLabel_1.setBounds(182, 260, 56, 16);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton_5 = new JButton("Refresh");
		btnNewButton_5.setBounds(300, 250, 100, 35);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				Erregistratua e = (Erregistratua) bl.erabiltzaileaBadago(izena, pasahitza);
				lblNewLabel_1.setText(Double.toString(e.getDirua()));
//				System.out.println(Double.toString(e.getDirua()));
			}
		});
		contentPane.add(btnNewButton_5);

	}

}
