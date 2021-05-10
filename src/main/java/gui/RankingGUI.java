package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Erregistratua;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

public class RankingGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RankingGUI frame = new RankingGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public RankingGUI() {
		super();
	}

	/**
	 * Create the frame.
	 */
	public RankingGUI(Erregistratua lehena, Erregistratua bigarrena, Erregistratua hirugarrena, JFrame aurrekoa) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ERREPLIKATUEN SAILKAPENA");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Poor Richard", Font.PLAIN, 16));
		lblNewLabel.setBounds(104, 34, 226, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("1.");
		lblNewLabel_1.setBounds(68, 96, 17, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(lehena.toString());
		lblNewLabel_2.setBounds(86, 96, 203, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("2.");
		lblNewLabel_3.setBounds(68, 125, 17, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(bigarrena.toString());
		lblNewLabel_4.setBounds(85, 125, 204, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("3.");
		lblNewLabel_5.setBounds(68, 154, 17, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(hirugarrena.toString());
		lblNewLabel_6.setBounds(86, 154, 203, 16);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("Sariak banatu");
		btnNewButton.setBounds(143, 195, 130, 25);
		JFrame k = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI m = new MainGUI();
				BLFacade bf = m.getBusinessLogic();
				bf.bonoakEguneratu(lehena.getNan(), lehena.bonoKopLortu() + 10);
				bf.bonoakEguneratu(bigarrena.getNan(), bigarrena.bonoKopLortu() + 6);
				bf.bonoakEguneratu(hirugarrena.getNan(), hirugarrena.bonoKopLortu() + 3);
				k.setVisible(false);
				aurrekoa.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
	}
}
