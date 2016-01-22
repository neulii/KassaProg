package windows;

import javax.swing.JDialog;

import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;

import model.Schuldner;
import model.SchuldnerListe;

import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class KlientBearbeitenWindow extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private Schuldner schuldner;
	private double betragHinzufugen;
	//private SchuldnerListe liste;
	
	private JLabel lblNameKlient;
	private JLabel lblOffen;
	private JLabel lblOffenerBetrag;
	private JLabel lblBezahlt;
	private JLabel lblZuletztBezahlt;
	private JButton btnBetragBezahlen;
	private JButton btnEinkaufVerrechnen;
	private JButton btnBetragAusbezahlen;
	private JLabel lblEuro;
	private JButton btnKlientLoschen;
	private JButton btnUebernehmen;
	private JButton btnFensterSchliessen;
	private JSpinner spinner;
	private JButton btnNewButton;
	private JSpinner spinner_1;
	private JButton button;
	private JSpinner spinner_2;
	private JButton button_1;
	private JSpinner spinner_3;
	private JButton button_2;
	private JSpinner spinner_4;
	private JButton button_3;
	private JSpinner spinner_5;
	private JButton button_4;
	private JSpinner spinner_6;
	private JButton button_5;
	private JSpinner spinner_7;
	private JButton button_6;
	private JSpinner spinner_8;
	private JButton button_7;
	private JSeparator separator;
	private JTextField txtBetrag;
	private JLabel lblBetragHinzufgen;
	private JLabel label;
	private JButton btnBetragHinzufugen;
	private JButton btnKlientNameChange;
	
	public KlientBearbeitenWindow(Schuldner schuldner, SchuldnerListe liste) {
		this.schuldner = schuldner;
		//this.liste = liste;
		
		betragHinzufugen = 0;
		initializeWindow();
		
		//Focus setzen im modalen Modus
		SwingUtilities.invokeLater(new Runnable() 
		  {  
		    public void run() {  
		      txtBetrag.requestFocusInWindow();
		    }
		  });
			
	}
	
	public void initializeWindow(){
		//Fenster allgemein
		setTitle("Klient Bearbeiten");
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\icon.png"));
		getContentPane().setLayout(null);
		setBounds(200, 200, 530, 555);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//Beim Klick auf X wird Fenster verworfen
		setModal(true);		//modal setzen
		
		//Überschrift Name des Klienten
		lblNameKlient = new JLabel(schuldner.getNameSchuldner());
		lblNameKlient.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNameKlient.setBounds(34, 0, 433, 48);
		getContentPane().add(lblNameKlient);
		
		//Beschriftung Offener Betrag
		lblOffen = new JLabel("Offener Betrag");
		lblOffen.setBounds(34, 82, 99, 14);
		getContentPane().add(lblOffen);
		
		//Anzeige offener Betrag
		lblOffenerBetrag = new JLabel(String.valueOf(schuldner.getOffenerBetrag()));
		lblOffenerBetrag.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOffenerBetrag.setOpaque(true);
		lblOffenerBetrag.setForeground(Color.BLACK);
		lblOffenerBetrag.setBackground(Color.WHITE);
		lblOffenerBetrag.setBounds(143, 75, 104, 29);
		getContentPane().add(lblOffenerBetrag);
		
		//Beschriftung zuletzt Bezahlt
		lblBezahlt = new JLabel("Zuletzt Bezahlt:");
		lblBezahlt.setBounds(34, 136, 99, 14);
		getContentPane().add(lblBezahlt);
		
		//Anzeige Datum zuletzt Bezahlt
		lblZuletztBezahlt = new JLabel(schuldner.getZuletztBezahlt());
		lblZuletztBezahlt.setOpaque(true);
		lblZuletztBezahlt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblZuletztBezahlt.setForeground(Color.BLACK);
		lblZuletztBezahlt.setBackground(Color.WHITE);
		lblZuletztBezahlt.setBounds(143, 125, 104, 29);
		getContentPane().add(lblZuletztBezahlt);
		
		//Button Betrag bezahlen button
		btnBetragBezahlen = new JButton("Betrag Bezahlen");
		btnBetragBezahlen.setBounds(324, 82, 143, 23);
		getContentPane().add(btnBetragBezahlen);
		
		//Button Einkauf verrechnen
		btnEinkaufVerrechnen = new JButton("Einkauf Verrechnen");
		btnEinkaufVerrechnen.setBounds(324, 116, 143, 23);
		getContentPane().add(btnEinkaufVerrechnen);
		
		//Button Betrag ausbezahlen
		btnBetragAusbezahlen = new JButton("Betrag Ausbezahlen");
		btnBetragAusbezahlen.setBounds(324, 150, 143, 23);
		getContentPane().add(btnBetragAusbezahlen);
		
		//Beschriftung Eurozeichen
		lblEuro = new JLabel("\u20AC");
		lblEuro.setBounds(256, 82, 14, 14);
		getContentPane().add(lblEuro);
		
		//Button Klient Löschen
		btnKlientLoschen = new JButton("Klient L\u00F6schen");
		btnKlientLoschen.setBounds(324, 184, 143, 23);
		getContentPane().add(btnKlientLoschen);
		
		//Button Änderungen Speichern
		btnUebernehmen = new JButton("\u00DCbernehmen");
		btnUebernehmen.setBounds(87, 433, 143, 43);
		getContentPane().add(btnUebernehmen);
		
		//Button Fenster Schliessen
		btnFensterSchliessen = new JButton("Schliessen");
		btnFensterSchliessen.setBounds(303, 433, 143, 43);
		getContentPane().add(btnFensterSchliessen);
		
		spinner = new JSpinner();
		spinner.setBounds(123, 308, 41, 20);
		getContentPane().add(spinner);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(34, 307, 89, 23);
		getContentPane().add(btnNewButton);
		
		spinner_1 = new JSpinner();
		spinner_1.setBounds(123, 342, 41, 20);
		getContentPane().add(spinner_1);
		
		button = new JButton("New button");
		button.setBounds(34, 341, 89, 23);
		getContentPane().add(button);
		
		spinner_2 = new JSpinner();
		spinner_2.setBounds(123, 374, 41, 20);
		getContentPane().add(spinner_2);
		
		button_1 = new JButton("New button");
		button_1.setBounds(34, 373, 89, 23);
		getContentPane().add(button_1);
		
		spinner_3 = new JSpinner();
		spinner_3.setBounds(287, 309, 41, 20);
		getContentPane().add(spinner_3);
		
		button_2 = new JButton("New button");
		button_2.setBounds(198, 308, 89, 23);
		getContentPane().add(button_2);
		
		spinner_4 = new JSpinner();
		spinner_4.setBounds(287, 343, 41, 20);
		getContentPane().add(spinner_4);
		
		button_3 = new JButton("New button");
		button_3.setBounds(198, 342, 89, 23);
		getContentPane().add(button_3);
		
		spinner_5 = new JSpinner();
		spinner_5.setBounds(287, 375, 41, 20);
		getContentPane().add(spinner_5);
		
		button_4 = new JButton("New button");
		button_4.setBounds(198, 374, 89, 23);
		getContentPane().add(button_4);
		
		spinner_6 = new JSpinner();
		spinner_6.setBounds(445, 309, 41, 20);
		getContentPane().add(spinner_6);
		
		button_5 = new JButton("New button");
		button_5.setBounds(356, 308, 89, 23);
		getContentPane().add(button_5);
		
		spinner_7 = new JSpinner();
		spinner_7.setBounds(445, 343, 41, 20);
		getContentPane().add(spinner_7);
		
		button_6 = new JButton("New button");
		button_6.setBounds(356, 342, 89, 23);
		getContentPane().add(button_6);
		
		spinner_8 = new JSpinner();
		spinner_8.setBounds(445, 375, 41, 20);
		getContentPane().add(spinner_8);
		
		button_7 = new JButton("New button");
		button_7.setBounds(356, 374, 89, 23);
		getContentPane().add(button_7);
		
		separator = new JSeparator();
		separator.setBounds(34, 283, 452, 14);
		getContentPane().add(separator);
		
		//Betrag Hinzufügen Button
		btnBetragHinzufugen = new JButton("Betrag Hinzuf\u00FCgen");
		btnBetragHinzufugen.setBounds(34, 225, 213, 36);
		getRootPane().setDefaultButton(btnBetragHinzufugen);
		getContentPane().add(btnBetragHinzufugen);
		
		//Textfeld betrag zum hinzufügen
		txtBetrag = new JTextField();
		txtBetrag.setHorizontalAlignment(SwingConstants.RIGHT);
		txtBetrag.setBounds(143, 177, 104, 29);
		getContentPane().add(txtBetrag);
		txtBetrag.setColumns(10);
		
		lblBetragHinzufgen = new JLabel("Betrag hinzuf\u00FCgen:");
		lblBetragHinzufgen.setBounds(34, 189, 99, 14);
		getContentPane().add(lblBetragHinzufgen);
		
		label = new JLabel("\u20AC");
		label.setBounds(256, 189, 14, 14);
		getContentPane().add(label);
		
		btnKlientNameChange = new JButton("Name \u00E4ndern");
		btnKlientNameChange.setBounds(324, 218, 143, 23);
		getContentPane().add(btnKlientNameChange);
	}
	
	//Actionlistener für Betrag Hinzufügen setzen
	public void setBetragHinzufugenButtonListener(ActionListener l){
		this.btnBetragHinzufugen.addActionListener(l);
	}
	
	//ActionListener für Speichern Button setzen
	public void setAnderungenSpeichernButtonListener(ActionListener l){
		this.btnUebernehmen.addActionListener(l);
	}
	
	//ActionListener für Schliessen button
	public void setSchliessenButtonListener(ActionListener l){
		this.btnFensterSchliessen.addActionListener(l);
	}
	
	//ActionLIstener für BetragBezahlen button
	public void setBetragBezahlenButtonListener(ActionListener l){
		this.btnBetragBezahlen.addActionListener(l);
	}
	
	//ActionListener für KLient löschen
	public void setKlientLoschenButtonListener(ActionListener l){
		this.btnKlientLoschen.addActionListener(l);
	}
	
	//ActionListener für Einkauf verrechnen button
	public void setEinkaufVerrechnenButtonListener(ActionListener l){
		this.btnEinkaufVerrechnen.addActionListener(l);
	}
	
	//ActionListener für Betrag ausbezahlen button
	public void setBetragAusBezahlenButtonListener(ActionListener l){
		this.btnBetragAusbezahlen.addActionListener(l);
	}
	
	//ActionListener für namen Klient namen ändern
	public void setKlientNameChangeButtonListener(ActionListener l){
		this.btnKlientNameChange.addActionListener(l);
	}
	//aktuellen Schuldner zurückgeben
	public Schuldner getOpenSchuldner(){
		return schuldner;
	}
	
	//eingegebenen Betrag zurückgeben
	public double getBetragFromTxt(){
		double betrag = 0;
		
		betrag = Double.parseDouble(txtBetrag.getText().replace(',', '.'));

		return betrag;
	}
	
	//Label mit Betrag setzen
	public void setOffenerBetragTxt(double betrag){
		int tempBetrag = 0;
		double neuerBetrag = 0;
		//umwandlungen auf int wegen kommaausgabefehler
		tempBetrag = (int) (100*betrag);		
		neuerBetrag = (double)tempBetrag/100;
		lblOffenerBetrag.setText(Double.toString(neuerBetrag));
	}
	
	//Textfeld Betrag hinzufügen setzen
	public void setBetragHinzuTxt(String text){
		txtBetrag.setText(text);
	}
	
	public double getBetragHinzu(){
		return betragHinzufugen;
	}
	
	public void setBetragHinzu(double betrag){
		betragHinzufugen = betrag;
	}
	
	public void updateWindow(){
		this.lblOffenerBetrag.setText(Double.toString(schuldner.getOffenerBetrag()));
		this.lblNameKlient.setText(getOpenSchuldner().getNameSchuldner());
	}	
	
}
