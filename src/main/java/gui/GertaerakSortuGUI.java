package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GertaerakSortuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GertaerakSortuGUI frame = new GertaerakSortuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GertaerakSortuGUI() {
		super();
	}

	/**
	 * Create the frame.
	 */
	public GertaerakSortuGUI(JFrame aurrekoframe) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Gertaeraren azalpena :");
		lblNewLabel.setBounds(36, 62, 149, 16);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(210, 59, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Eguna :");
		lblNewLabel_1.setBounds(36, 91, 56, 16);
		contentPane.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(210, 88, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Hilabetea :");
		lblNewLabel_2.setBounds(36, 126, 76, 16);
		contentPane.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(210, 123, 116, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Urtea");
		lblNewLabel_3.setBounds(36, 163, 56, 16);
		contentPane.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(210, 160, 116, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JButton btnNewButton = new JButton("Gertaera sortu");
		btnNewButton.setBounds(130, 215, 142, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
//					MainGUI m = new MainGUI();
					BLFacade f = MainGUI.getBusinessLogic();
					int zenb = f.getGertaeraHandienaLortu() + 1;
					String azalpena = textField.getText();
					int eguna = Integer.parseInt(textField_1.getText());
					int hilabetea = Integer.parseInt(textField_2.getText());
					int urte = Integer.parseInt(textField_3.getText());
					if (urte > 1900) {
						urte = urte - 1900;
					} else {
						urte = urte + 100;
					}
					Date d = new Date(urte, hilabetea, eguna);
					f.gertaeraSortu(azalpena, d, zenb);
					System.out.println("gertaera datu basean sartu da.");
					aurrekoframe.setVisible(true);
					contentPane.setVisible(false);
				} catch (Exception e1) {
					System.out.println("Datu guztiak sartu");
				}
			}
		});
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_4 = new JLabel("GERTAERA SORTU");
		lblNewLabel_4.setBounds(130, 13, 128, 16);
		contentPane.add(lblNewLabel_4);
	}

}
