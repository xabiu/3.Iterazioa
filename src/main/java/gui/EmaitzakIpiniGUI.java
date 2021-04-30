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
import domain.Apustua;
import domain.Erregistratua;
import domain.Event;
import domain.Pronostikoa;
import domain.Question;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class EmaitzakIpiniGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private int i = 0;
	private int max = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmaitzakIpiniGUI frame = new EmaitzakIpiniGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public EmaitzakIpiniGUI() {
		super();
	}

	/**
	 * Create the frame.
	 */
	public EmaitzakIpiniGUI(JFrame aurrekoframe, Event e1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		List<Question> list = e1.getQuestions();
		max = list.size();
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gertaera:");
		lblNewLabel.setBounds(70, 64, 56, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(e1.toString());
		lblNewLabel_1.setBounds(138, 64, 220, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Emaitza:");
		lblNewLabel_2.setBounds(70, 121, 56, 16);
		getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(138, 118, 116, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Sartu Emaitza");
		btnNewButton.setBounds(138, 191, 116, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 MainGUI m = new MainGUI();
				 BLFacade bf = m.getBusinessLogic();
				 
				 if(i<max) {
					 String emaitza = textField.getText();
					 bf.emaitzaIpini(list.get(i), emaitza);
					 if(!list.get(i).getP().isEmpty()) {
					 Pronostikoa p = list.get(i).getPronostikoa();
					 List<Apustua> list2 = p.getApustuak();
					 for(Apustua ap: list2) {
						 if(ap.getEmaitza().equals(emaitza)) {
							 Erregistratua e2 = bf.ErregistratuaBilatu(ap.getNAN());
							 double d1 = ap.getDirua()*p.getKuota();
							 bf.diruaSartu(e2.getDirua() + d1, e2.getIz(), e2.getPasahitza());
						 }
					 }
					 System.out.println("Galdera: " + e1.getQuestions().get(i));
					 }
				 }else {
					 bf.gertaeraKendu(e1);
					 contentPane.setVisible(false);
					 aurrekoframe.setVisible(true);
				 }
				 i++;
			}
		});
		getContentPane().add(btnNewButton);
	}

}
