package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class AdminGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI frame = new AdminGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminGUI() {
		super();
	}

	/**
	 * Create the frame.
	 */
	public AdminGUI(String izena, String pasahitza, JFrame aurrekoa) {
		aurrekoa.setVisible(false);
		BLFacade bl = MainGUI.getBusinessLogic();
		Admin a = (Admin) bl.erabiltzaileaBadago(izena, pasahitza);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JFrame j = this;
		JButton btnNewButton = new JButton("Pronostikoa ipini");
		btnNewButton.setBounds(12, 13, 408, 37);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new PronostikoakSortuGUI(j);
				a.setVisible(true);

			}
		});
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Gertaera sortu");
		btnNewButton_1.setBounds(12, 51, 408, 37);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new GertaerakSortuGUI(j);
				a.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Galdera sortu");
		btnNewButton_2.setBounds(12, 89, 408, 37);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new CreateQuestionGUI(new Vector<Event>());
				a.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Galderak ikusi");
		btnNewButton_3.setBounds(12, 128, 408, 37);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new FindQuestionsGUI();
				a.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Emaitzak ipini");
		btnNewButton_4.setBounds(12, 167, 408, 37);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new GertaerakAurkeratu(j);
				a.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Erabiltzaile erreplikatuena lortu");
		btnNewButton_5.setBounds(12, 206, 408, 37);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				MainGUI m = new MainGUI();
				BLFacade bf = MainGUI.getBusinessLogic();
				List<Erregistratua> lista = bf.erabiltzaileGuztiakLortu();
				Erregistratua lehena = null;
				Erregistratua bigarrena = null;
				Erregistratua hirugarrena = null;
				for (Erregistratua e1 : lista) {
					if (lehena == null) {
						lehena = e1;
					} else {
						if (lehena.erreplikatuListaLortu().size() < e1.erreplikatuListaLortu().size()) {
							hirugarrena = bigarrena;
							bigarrena = lehena;
							lehena = e1;
						} else {
							if (bigarrena == null) {
								bigarrena = e1;
							} else {
								if (bigarrena.erreplikatuListaLortu().size() < e1.erreplikatuListaLortu().size()) {
									hirugarrena = bigarrena;
									bigarrena = e1;
								} else {
									if (hirugarrena == null) {
										hirugarrena = e1;
									} else {
										if (hirugarrena.erreplikatuListaLortu().size() < e1.erreplikatuListaLortu()
												.size()) {
											hirugarrena = e1;
										}
									}
								}
							}
						}
					}
				}
				JFrame a = new RankingGUI(lehena, bigarrena, hirugarrena, j);
				a.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("Amaitu Saioa");
		btnNewButton_6.setBounds(12, 245, 408, 37);
		JFrame s = this;

		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aurrekoa.setVisible(true);
				s.setVisible(false);
			}
		});
		contentPane.add(btnNewButton_6);
	}
}
