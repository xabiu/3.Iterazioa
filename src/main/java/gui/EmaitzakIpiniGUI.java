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
import javax.swing.JComboBox;

public class EmaitzakIpiniGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private int max;
	private JComboBox<Pronostikoa> comboBox;
	private JButton btnNewButton = new JButton("Sartu Emaitza");
	private JButton btnNewButton_2 = new JButton("Hurrengo Galdera");
	private int a;
	//private BLFacade facade;

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
		getContentPane().setLayout(null);
	}

	/**
	 * Create the frame.
	 */
	public EmaitzakIpiniGUI(JFrame aurrekoframe, Event e1, int i) {
		System.out.println(i);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		List<Question> list = e1.getQuestions();
		max = list.size();
		if(max-1>i) {
			btnNewButton.setEnabled(false);
			btnNewButton_2.setEnabled(true);
		}else {
			btnNewButton_2.setEnabled(false);
			btnNewButton.setEnabled(true);
		}
		
		getContentPane().setLayout(null);
		a = i;
		JLabel lblNewLabel = new JLabel("Gertaera:");
		lblNewLabel.setBounds(70, 34, 56, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(e1.toString());
		lblNewLabel_1.setBounds(138, 34, 220, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Galdera:");
		lblNewLabel_3.setBounds(70, 70, 56, 16);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(list.get(i).getQuestion());
		lblNewLabel_4.setBounds(138, 70, 220, 16);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("Emaitza aukeratu:");
		lblNewLabel_2.setBounds(20, 121, 120, 16);
		getContentPane().add(lblNewLabel_2);
		
		/*textField = new JTextField();
		textField.setBounds(138, 118, 116, 22);
		getContentPane().add(textField);
		textField.setColumns(10);*/
		comboBox = new JComboBox<Pronostikoa>();
		comboBox.setBounds(130, 121, 250, 22);
		comboBox.removeAllItems();
		 for(Pronostikoa p: list.get(i).getP()) {
				comboBox.addItem(p);
		 }
		getContentPane().add(comboBox);
		
		//btnNewButton = new JButton("Sartu Emaitza");
		btnNewButton.setBounds(138, 191, 116, 25);
		JFrame ar = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!list.get(i).getP().isEmpty()) {
				 MainGUI m = new MainGUI();
				 BLFacade bf = m.getBusinessLogic();
				 System.out.println("Galdera: " + e1.getQuestions().get(i));
				 Pronostikoa p1 = (Pronostikoa) comboBox.getSelectedItem();
				 String emaitza = p1.getEmaitza();
				 if(emaitza != null) {
					 bf.emaitzaIpini(list.get(i), emaitza);
					 if(!list.get(i).getP().isEmpty()) {
						 List<Pronostikoa> p2 = list.get(i).getP();
						 if(!p2.isEmpty()) {
							 for(Pronostikoa p: p2) {
								List<Apustua> list2 = p.getApustuak();
									 if(list2 != null) {
									 for(Apustua ap: list2) {
										 if(ap.getEmaitza().equals(emaitza)) {
											 System.out.println("sartu da");
											 Erregistratua e2 = bf.ErregistratuaBilatu(ap.getNAN());
											 double d1 = ap.getDirua()*p.getKuota();
											 System.out.println(d1);
											 bf.diruaSartu(e2.getDirua() + d1, e2.getIz(), e2.getPasahitza());
										 }
									 }
								 }
							 }
						 }
					 }
				 }
				}
				 MainGUI m = new MainGUI();
				 ar.setVisible(false);
				 BLFacade facade = m.getBusinessLogic();
				 facade.gertaeraKendu(e1);
				 aurrekoframe.setVisible(true);
			}
		});
		getContentPane().add(btnNewButton);
		
		//btnNewButton_2 = new JButton("Hurrengo Galdera");
		btnNewButton_2.setBounds(260, 191, 210, 25);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(max != 0) {
				System.out.println("principio");
				if(!list.get(i).getP().isEmpty()) {
				 MainGUI m = new MainGUI();
				 BLFacade bf = m.getBusinessLogic();
				 System.out.println("Galdera: " + e1.getQuestions().get(i));
				 Pronostikoa p1 = (Pronostikoa) comboBox.getSelectedItem();
				 String emaitza = p1.getEmaitza();
				 if(emaitza != null) {
					 System.out.println("emaitza");
					 bf.emaitzaIpini(list.get(i), emaitza);
					 if(!list.get(i).getP().isEmpty()) {
						 List<Pronostikoa> p2 = list.get(i).getP();
						 if(!p2.isEmpty()) {
							 System.out.println("pronostikoak");
							 for(Pronostikoa p: p2) {
								List<Apustua> list2 = p.getApustuak();
									 if(list2 != null) {
									 for(Apustua ap: list2) {
										 System.out.println("apustuak");
										 if(ap.getEmaitza().equals(emaitza)) {
											 System.out.println("sartu da");
											 Erregistratua e2 = bf.ErregistratuaBilatu(ap.getNAN());
											 double d1 = ap.getDirua()*p.getKuota();
											 System.out.println(d1);
											 bf.diruaSartu(e2.getDirua() + d1, e2.getIz(), e2.getPasahitza());
										 }
									 }
								 }
							 }
						 }
					 }
				}
				}
				}
				//a++;
				int z = list.size();
				JFrame hurrengoa = new EmaitzakIpiniGUI(aurrekoframe, e1, z-(z-i)+1);
				ar.setVisible(false);
				hurrengoa.setVisible(true);
			}
		});
		getContentPane().add(btnNewButton_2);
	}
}
