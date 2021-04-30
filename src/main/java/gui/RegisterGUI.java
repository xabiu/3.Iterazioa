package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Erabiltzailea;
import domain.Erregistratua;
import domain.Admin;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtRegister;
	private JTextField izena;
	private JPasswordField pasahitza;
	private JTextField adina;
	private JTextField nan;
	private JRadioButton rdbtnAdministratzailea;
	private JRadioButton rdbtnErregistratua;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI m = new MainGUI();
					JFrame nireframe = m.getNireFrame();
					RegisterGUI frame = new RegisterGUI(nireframe);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterGUI(JFrame nireframe) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtRegister = new JTextField();
		txtRegister.setBounds(5, 5, 422, 35);
		txtRegister.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtRegister.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegister.setText("Register");
		contentPane.add(txtRegister);
		txtRegister.setColumns(10);

		JLabel lblErabiltzaileIzena = new JLabel("Erabiltzaile izena:");
		lblErabiltzaileIzena.setBounds(58, 53, 108, 16);
		contentPane.add(lblErabiltzaileIzena);

		JLabel lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setBounds(58, 82, 68, 16);
		contentPane.add(lblPasahitza);

		izena = new JTextField();
		izena.setBounds(178, 53, 116, 22);
		contentPane.add(izena);
		izena.setColumns(10);

		pasahitza = new JPasswordField();
		pasahitza.setBounds(178, 79, 116, 22);
		contentPane.add(pasahitza);

		JLabel lblAdina = new JLabel("Adina:");
		lblAdina.setBounds(58, 111, 56, 16);
		contentPane.add(lblAdina);

		adina = new JTextField();
		adina.setBounds(178, 108, 116, 22);
		contentPane.add(adina);
		adina.setColumns(10);

		JLabel lblKontuKorrontea = new JLabel("NAN zenbakia:");
		lblKontuKorrontea.setBounds(58, 140, 108, 16);
		contentPane.add(lblKontuKorrontea);

		nan = new JTextField();
		nan.setBounds(178, 137, 116, 22);
		contentPane.add(nan);
		nan.setColumns(10);

		rdbtnAdministratzailea = new JRadioButton("Administratzailea");
		rdbtnAdministratzailea.setBounds(58, 165, 127, 25);
		contentPane.add(rdbtnAdministratzailea);

		rdbtnErregistratua = new JRadioButton("Erregistratua");
		rdbtnErregistratua.setBounds(219, 165, 127, 25);
		contentPane.add(rdbtnErregistratua);

		final JTextPane erroreak = new JTextPane();
		erroreak.setBounds(58, 218, 207, 22);
		contentPane.add(erroreak);

		JButton btnErregistratu = new JButton("Erregistratu");
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String iz = izena.getText();
				String pass = new String(pasahitza.getPassword());
				String NAN = nan.getText();
				String ad = adina.getText();
				DataAccess dbManager = new DataAccess();
				List<Erabiltzailea> erabiltzaileak = dbManager.getErabiltzaileGuztiak();
				int mota = 2;

				if (rdbtnAdministratzailea.isSelected()) {
					mota = 1;
				} else if (rdbtnErregistratua.isSelected()) {
					mota = 0;
				}

				try {
					BLFacade bl = MainGUI.getBusinessLogic();
					if (iz.isEmpty() || pass.isEmpty() || NAN.isEmpty() || ad.isEmpty()) {
						erroreak.setText("Eremu guztiak bete behar dira");
					} else if (Integer.parseInt(ad) < 18) {
						erroreak.removeAll();
						erroreak.setText("Gutxienez 18 urte eduki behar dituzu.");
					} else if (erabiltzaileak.contains(iz)) {
						erroreak.removeAll();
						erroreak.setText("Izen hori duen erabiltzailea existitzen da.");
					} /*
						 * else if (MainGUI.getBusinessLogic().erabiltzaileaBadago(iz, pass) == null) {
						 * erroreak.setText("Erabiltzailea existitzen da"); }
						 */ else {
						nireframe.setVisible(true);
						contentPane.setVisible(false);
						if (mota == 1) {
							bl.createAdmin(iz, NAN, ad, pass, 1);
						} else {
							bl.createErregistratua(iz, NAN, ad, pass);
						}
					}
				} catch (Exception u) {
//					erroreak.setText("ezin da erabiltzaile berria sortu");
					System.out.println(u);
				}

			}
		});
		btnErregistratu.setBounds(297, 215, 108, 25);
		contentPane.add(btnErregistratu);

	}
}
