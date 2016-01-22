package windows;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

public class KlientHinzufWindow extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField txtNameSchuldner;
	private JTextField txtSaldoSchuldner;

	private JButton btnOK;
	private JButton btnAbbrechen;

	public KlientHinzufWindow() {

		initializeWindow();
	}
	
	public void initializeWindow(){
		//Fenster einstellungen
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\icon.png"));
		setTitle("Klient Hinzuf\u00FCgen");
		getContentPane().setLayout(null);
		setResizable(false);
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(40, 20, 58, 23);
		getContentPane().add(lblName);
		
		//Beschriftung Saldo
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSaldo.setBounds(40, 66, 58, 23);
		getContentPane().add(lblSaldo);
		
		//Eingabefeld Name Schuldner
		txtNameSchuldner = new JTextField();
		txtNameSchuldner.setBounds(96, 22, 216, 20);
		getContentPane().add(txtNameSchuldner);
		txtNameSchuldner.setColumns(10);
		
		//Eingabefeld Betrag Schuldner
		txtSaldoSchuldner = new JTextField();
		txtSaldoSchuldner.setColumns(10);
		txtSaldoSchuldner.setBounds(96, 68, 216, 20);
		getContentPane().add(txtSaldoSchuldner);
		
		//OK Button
		btnOK = new JButton("OK");
		btnOK.setBounds(39, 120, 116, 37);
		getContentPane().add(btnOK);
		getRootPane().setDefaultButton(btnOK);
		
		//Abbrechen Button
		btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(196, 120, 116, 37);
		getContentPane().add(btnAbbrechen);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//Beim Klick auf X wird Fenster verworfen
		//setAlwaysOnTop(true);
		setBounds(200, 200, 359, 214);
		setModal(true);		//modal setzen
	}
	
	//ActionListener für Button OK hinzufügen
	public void setButtonOKListener(ActionListener l){
		this.btnOK.addActionListener(l);
	}
	
	//ActionListener für Button Abrrechen
	public void setButtonAbbrechen(ActionListener l){
		this.btnAbbrechen.addActionListener(l);
	}
	
	//Name von Eingabefeld zurückgeben
	public String getNameInput(){
		return txtNameSchuldner.getText();
	}
	
	//Saldo von Eingabefeld zurückgeben
	public String getSaldoInput(){
		return txtSaldoSchuldner.getText();
	}
	
	//inputname feld eingabe markieren
	public void setInputNameMarked(){
		txtNameSchuldner.selectAll();
	}
	
	//eingabefeld von inputname feld focussieren
	public void setFocusInputName(){
		txtNameSchuldner.requestFocus();
	}
	
	//input saldo feld markieren
	public void setInputSaldoMarked(){
		txtSaldoSchuldner.selectAll();
	}
	
	//input saldo feld focussieren
	public void setFocusInputSaldo(){
		txtSaldoSchuldner.requestFocus();
	}
}
