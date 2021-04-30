package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Erregistratua;
import domain.Pronostikoa;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ApustuaEgin2GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApustuaEgin2GUI frame = new ApustuaEgin2GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ApustuaEgin2GUI() {
		super();
	}

	/**
	 * Create the frame.
	 */
	public ApustuaEgin2GUI(JFrame aurrekoFrame, Pronostikoa p, Erregistratua e1, double betMinimum) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Emaitza:");
		lblNewLabel.setBounds(62, 65, 56, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dirua:");
		lblNewLabel_1.setBounds(62, 131, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(147, 62, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(147, 128, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Apustua egin");
		btnNewButton.setBounds(147, 192, 116, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String emaitza = textField.getText();
				String d = textField_1.getText();
				double dirua = Double.parseDouble(d);
				if(dirua>betMinimum) {
					if(dirua<e1.getDirua()) {
						MainGUI m = new MainGUI();
						BLFacade bl = m.getBusinessLogic();
						int z = bl.zenbakiHandienaLortu();
						bl.apustuaEgin(p, emaitza, dirua, e1.getNan(),z+1);
						bl.diruaSartu(e1.getDirua()-dirua, e1.getIz(), e1.getNan());
						contentPane.setVisible(false);
						aurrekoFrame.setVisible(true);
					}else {
						System.out.println("sartutako dirua kontuan duzuna baino handiago da.");
					}
				}else {
					System.out.println("sartutako dirua ez du minimoa gainditzen");
				}
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("APOSTATU!");
		lblNewLabel_2.setBounds(147, 13, 116, 16);
		contentPane.add(lblNewLabel_2);
	}

}
