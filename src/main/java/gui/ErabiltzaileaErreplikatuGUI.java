package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Erregistratua;
import domain.Pronostikoa;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ErabiltzaileaErreplikatuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox<Erregistratua> comboBox;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErabiltzaileaErreplikatuGUI frame = new ErabiltzaileaErreplikatuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ErabiltzaileaErreplikatuGUI() {
		super();
	}

	/**
	 * Create the frame.
	 */
	public ErabiltzaileaErreplikatuGUI(JFrame aurrekoa, Erregistratua e1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sartu erreplikatu nahi duzun erabiltzaileraen NAN-a:");
		lblNewLabel.setBounds(39, 53, 299, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Erabiltzaile guztiak:");
		lblNewLabel_1.setBounds(20, 120, 180, 16);
		contentPane.add(lblNewLabel_1);
		
		//textField = new JTextField();
		//textField.setBounds(136, 109, 116, 22);
		//contentPane.add(textField);
		//textField.setColumns(10);
		comboBox = new JComboBox();
		comboBox.setBounds(145, 120, 240, 22);
		MainGUI m = new MainGUI();
		BLFacade bf = m.getBusinessLogic();
		List<Erregistratua> l2 = bf.erabiltzaileGuztiakLortu();
		for(Erregistratua e2: l2) {
			if(!e2.getNan().equals(e1.getNan())) {
				comboBox.addItem(e2);
			}
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getItemCount()<1) {
					btnNewButton.setEnabled(false);
				}else {
					btnNewButton.setEnabled(true);
				}
			}
		});
		getContentPane().add(comboBox);
		
		btnNewButton = new JButton("Erabiltzailea erreplikatu");
		btnNewButton.setBounds(112, 178, 180, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				Erregistratua e3 = (Erregistratua) comboBox.getSelectedItem();
				bf.erreplikatu(e3.getNan(), e1);
				contentPane.setVisible(false);
				aurrekoa.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
	}
}
