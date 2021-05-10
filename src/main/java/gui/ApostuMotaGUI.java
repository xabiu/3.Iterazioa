package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.ApostuaSortuGUI;
import javax.swing.JFrame;
import javax.swing.JLabel;

import domain.*;

import javax.swing.JButton;

public class ApostuMotaGUI extends JFrame{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostuMotaGUI window = new ApostuMotaGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public ApostuMotaGUI(JFrame aurrekoframe, Erregistratua e) {
		initialize(aurrekoframe, e);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JFrame aurreko, Erregistratua e) {
		setBounds(100, 100, 312, 192);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Apostu mota aukeratu:");
		lblNewLabel.setBounds(69, 24, 131, 16);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Normala");
		btnNewButton.setBounds(23, 88, 97, 25);
		JFrame t = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				JFrame a = new ApostuaSortuGUI(aurreko,e);
				a.setVisible(true);
				t.setVisible(false);
			}
		});
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Anitza");
		btnNewButton_1.setBounds(150, 88, 97, 25);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				JFrame a = new ApostuAnitzaSortu(aurreko, e);
				a.setVisible(true);
				t.setVisible(false);
			}
		});
		getContentPane().add(btnNewButton_1);
	}
}
