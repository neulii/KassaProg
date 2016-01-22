package windows;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class BetragAusBezahlenWindow extends JDialog{

	private static final long serialVersionUID = 1L;

	protected JLabel lblBetrag;
	protected JTextField txtBetrag;
	protected JButton btnBestatigen;
	protected JButton btnAbbrechen;
	
	public BetragAusBezahlenWindow() {
		
		initializeWindow();
	}

	protected void initializeWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\icon.png"));
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Betrag Ausbezahlen:");
		setBounds(100,100,365,214);
		getContentPane().setLayout(null);
		
		//Beschriftung von Textfeld: Betrag
		lblBetrag = new JLabel("Betrag:");
		lblBetrag.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBetrag.setBounds(38, 32, 284, 27);
		getContentPane().add(lblBetrag, BorderLayout.NORTH);
		
		//Eingabefeld für betrag
		txtBetrag = new JTextField();
		txtBetrag.setHorizontalAlignment(SwingConstants.RIGHT);
		txtBetrag.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtBetrag.setBounds(114, 32, 208, 22);
		getContentPane().add(txtBetrag);
		txtBetrag.setColumns(10);
		
		//Button zum Bestätigen
		btnBestatigen = new JButton("Best\u00E4tigen");
		btnBestatigen.setBounds(38, 102, 137, 38);
		System.out.println(getRootPane().toString());
		getRootPane().setDefaultButton(btnBestatigen); //default button setzen
		getContentPane().add(btnBestatigen);
		
		//Button zum Abbrechen
		btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(185, 102, 137, 38);
		getContentPane().add(btnAbbrechen);
	}
	
	//ActionListener für Bestätigen Button
	public void setBestatigenButtonListener(ActionListener l){
		btnBestatigen.addActionListener(l);
	}
	
	//ActionListener für Abbrechen button
	public void setAbbrechenButtonListener(ActionListener l){
		btnAbbrechen.addActionListener(l);
	}
	
	public double getBetragFromTxt(){
		double betrag = 0;
		
		betrag = Double.parseDouble(txtBetrag.getText().replace(',', '.'));

		return betrag;
	}
}
