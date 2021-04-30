package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class LoginGUI extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtLogIn;
	private JTextField textField;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtLogIn = new JTextField();
		txtLogIn.setBounds(5, 5, 422, 31);
		txtLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtLogIn.setText("Log in");
		contentPane.add(txtLogIn);
		txtLogIn.setColumns(10);

		JLabel lblErabiltzaileIzena = new JLabel("Erabiltzaile izena:");
		lblErabiltzaileIzena.setBounds(75, 73, 101, 16);
		contentPane.add(lblErabiltzaileIzena);

		JLabel lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setBounds(75, 118, 66, 16);
		contentPane.add(lblPasahitza);

		textField = new JTextField();
		textField.setBounds(188, 70, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(153, 115, 151, 22);
		contentPane.add(passwordField);

		JRadioButton rdbtnAdministratzailea = new JRadioButton("Administratzailea");
		buttonGroup.add(rdbtnAdministratzailea);
		rdbtnAdministratzailea.setBounds(75, 160, 127, 25);
		contentPane.add(rdbtnAdministratzailea);

		JRadioButton rdbtnErregistratua = new JRadioButton("Erregistratua");
		buttonGroup.add(rdbtnErregistratua);
		rdbtnErregistratua.setBounds(217, 160, 127, 25);
		contentPane.add(rdbtnErregistratua);

		JButton btnSartu = new JButton("Sartu");
		btnSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int mota = 0;

				if (rdbtnAdministratzailea.isSelected()) {
					mota = 1;
				} else if (rdbtnErregistratua.isSelected()) {
					mota = 0;
				}
				try {
					MainGUI s = new MainGUI();
					String izena = textField.getText();
					String pasahitza = new String(passwordField.getPassword());
					System.out.println(izena + " " + pasahitza);
//				boolean emaitza = s.getBusinessLogic().erabiltzaileZuzena(izena,pasahitza);
					BLFacade bl = MainGUI.getBusinessLogic();
					if (bl != null) {
						boolean emaitza = bl.erabiltzaileZuzena(izena, pasahitza);
						if (emaitza) {
							if (mota == 1) {
								AdminGUI a = new AdminGUI(izena,pasahitza);
								a.setVisible(true);
								contentPane.setVisible(false);
							}else {
								ErregistratuGUI e = new ErregistratuGUI(izena,pasahitza);
								e.setVisible(true);
								contentPane.setVisible(false);
							}
							System.out.println("Erabiltzaile zuzena.");
						} else {
							System.out.println("Pasahitza okerra.");
						}
					} else {
						System.out.println("Erabiltzailea ez da existitzen");
					}
				} catch (Exception d) {
					System.out.println("Erabiltzailea existitzen da");
				}
			}
		});
		btnSartu.setBounds(169, 202, 97, 25);
		contentPane.add(btnSartu);

	}
}
