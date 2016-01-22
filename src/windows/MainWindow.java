package windows;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import actionListener.mainWindow.MainWindowClosingListener;

import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = -3424567658877589751L;
	private DefaultTableModel table_set;	
	
	protected String[] tableTitle = {"Name", "Offener Betrag","Zuletzt Bezahlt"};
	
	//Buttons
	private JButton btnSaveData;
	private JButton btnKlientNeu;
	private JButton btnKlientBearbeiten;
	
	//Menü
	private JMenuBar menuBar;
	private JMenu mnDatei;
	private JMenuItem mntmDateiNeu;
	private JMenuItem mntmDateiSpeichern;
	private JMenuItem mntmDateiOffnen;
	private JMenuItem mntmLastOpened;	//zuletzt geöffnete datei anzeigen
	private JMenuItem mntmProperties;	//einstellungen
	private JMenuItem mntmBeenden;
	private JMenu mnKlient;
	private JMenuItem mntmKlientHinzu;
	private JMenuItem mntmKlientBearbeiten;
	private JMenu mnInfo;
	private JMenuItem mntmInfo;
	private JMenuItem mntmDateiSpeichernUnter;
	private JMenuItem mntmDateiExportieren;
	
	
	
	//protected wegen Zugriff im Controller
	protected JTable table;
	protected JPanel panel;
	protected JLabel textFilename;
	private JLabel statusBarFileName;
	protected JTextPane textGesamtOffen;
	
	public MainWindow() {
		
		//Abfrage Beim Programm schliessen
		//addWindowListener(new MainWindowClosingListener(this));

		//Look and Feel auf windows setzen
		try{
			//UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		  	//UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel" );
		 	UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
		initializeWindow();
	}
	
	//Hauptfenster anzeigen
	public void showWindow(){
		setVisible(true);

	}
	
	//Fenster und Elemente erstellen
	public void initializeWindow(){
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\icon.png"));
		
		setResizable(false);
		setTitle("KassaBuch");
		setBounds(200, 200, 882, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
		//ScrollPane hinzufügen
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 37, 397, 330);
		getContentPane().add(scrollPane);
		
		
	
		//Tabelle hinzufügen
		setTable_set(new DefaultTableModel(tableTitle,0));
		table = new JTable(getTable_set()){
			private static final long serialVersionUID = -9179129647805984887L;

			//Methode überschreiben damit Zeilen nicht editiert werden können
			public boolean isCellEditable(int x, int y){
				return false;
			}
		};
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //nur eine Zeile auswählbar
		table.setFocusable(false);
			
		//Button daten speichern
		btnSaveData = new JButton("Daten Speichern");
		btnSaveData.setBounds(545, 207, 200, 50);
		getContentPane().add(btnSaveData);
		
		//Button Klient hinzufügen
		btnKlientNeu = new JButton("Klient Hinzuf\u00FCgen");
		btnKlientNeu.setBounds(545, 37, 200, 50);
		getContentPane().add(btnKlientNeu);
		
		//Button Klient bearbeiten
		btnKlientBearbeiten = new JButton("Klient Bearbeiten");
		btnKlientBearbeiten.setBounds(545, 98, 200, 50);
		getContentPane().add(btnKlientBearbeiten);
		
		//Menus hinzufugen
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		mntmDateiNeu = new JMenuItem("Neu");
		mnDatei.add(mntmDateiNeu);
		
		mntmDateiOffnen = new JMenuItem("\u00D6ffnen");
		mnDatei.add(mntmDateiOffnen);
		
		mntmDateiSpeichern = new JMenuItem("Speichern");
		mnDatei.add(mntmDateiSpeichern);
		
		mntmDateiSpeichernUnter = new JMenuItem("Speichern unter");
		mnDatei.add(mntmDateiSpeichernUnter);
		
		mntmDateiExportieren = new JMenuItem("Exportieren als XLS");
		mnDatei.add(mntmDateiExportieren);
		
		mntmLastOpened = new JMenuItem("Zuletzt geöffnet ?");
		mnDatei.add(mntmLastOpened);
		
		mntmProperties = new JMenuItem("Einstellungen");
		mnDatei.add(mntmProperties);
		
		mntmBeenden = new JMenuItem("Beenden");
		mnDatei.add(mntmBeenden);
		
		mnKlient = new JMenu("Klient");
		menuBar.add(mnKlient);
		
		mntmKlientHinzu = new JMenuItem("Klient Hinzuf\u00FCgen");
		mnKlient.add(mntmKlientHinzu);
		
		mntmKlientBearbeiten = new JMenuItem("Klient Bearbeiten");
		mnKlient.add(mntmKlientBearbeiten);
		
		mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);
		
		mntmInfo = new JMenuItem("Info");
		mnInfo.add(mntmInfo);
		
		//statusbar
		setStatusBarFileName(new JLabel(""));
		getStatusBarFileName().setFont(new Font("Arial", Font.PLAIN, 14));
		getStatusBarFileName().setBounds(40, 471, 506, 28);
		getContentPane().add(getStatusBarFileName());
		
		//Anzeige Gesamter Betrag offen
		textGesamtOffen = new JTextPane();
		textGesamtOffen.setEditable(false);
		textGesamtOffen.setFont(new Font("Tahoma", Font.BOLD, 13));
		textGesamtOffen.setText("0.0");
		textGesamtOffen.setBounds(306, 394, 131, 28);
			//textpane rechtsbündig einrichten
			StyledDocument doc = textGesamtOffen.getStyledDocument();
			SimpleAttributeSet rightAlign = new SimpleAttributeSet();
			StyleConstants.setAlignment(rightAlign,StyleConstants.ALIGN_RIGHT);
			doc.setParagraphAttributes(0, doc.getLength(), rightAlign, false);
		getContentPane().add(textGesamtOffen);
		
		JLabel lblOffenerGesamtbetrag = new JLabel("Offener Gesamtbetrag:");
		lblOffenerGesamtbetrag.setFont(new Font("Arial", Font.PLAIN, 14));
		lblOffenerGesamtbetrag.setBounds(145, 394, 151, 28);
		getContentPane().add(lblOffenerGesamtbetrag);
	}

	//ActionListener für button SaveData hinzufügen
	public void setSaveDataAction(ActionListener l){
		this.btnSaveData.addActionListener(l);
	}
	
	//ActionListener für Button Klient hinzufügen hinzufügen
	public void setButtonKlientHinzufugenAction(ActionListener l){
		this.btnKlientNeu.addActionListener(l);
	}
	
	//ActionListener für Button Klient bearbeiten hinzufügen
	public void setButtonKlientBearbeitenAction(ActionListener l){
		this.btnKlientBearbeiten.addActionListener(l);
	}
	
	//ActionListener für Menü Klient hinzufügen
	public void setMenuKlientHinzufugenAction(ActionListener l){
		this.mntmKlientHinzu.addActionListener(l);
	}

	//ActionListener für Menü Klient Bearbeiten
	public void setMenuKlientBearbeitenAction(ActionListener l){
		this.mntmKlientBearbeiten.addActionListener(l);
	}
	
	//ActionListener für Menü Datei Beenden
	public void setMenuDateiBeendenAction(ActionListener l){
		this.mntmBeenden.addActionListener(l);
	}
	
	//ActionListener für Menü Datei Öffnen
	public void setMenuDateiOeffnenAction(ActionListener l){
		this.mntmDateiOffnen.addActionListener(l);
	}
	
	//ActionListener für zuletzt geöffnete datei 
	public void setMenuLastOpenedAction(ActionListener l){
		this.mntmLastOpened.addActionListener(l);
	}
	
	//ActionListener für datei - einstellungen
	public void setMenuPropertiesAction(ActionListener l){
		this.mntmProperties.addActionListener(l);
	}
	
	//ActionListner für Menü Datei speichern
	public void setMenuDateiSpeichernAction(ActionListener l){
		this.mntmDateiSpeichern.addActionListener(l);
	}
	
	//ActionListener für Menü datei neu
	public void setMenuDateiNeuAction(ActionListener l){
		this.mntmDateiNeu.addActionListener(l);
	}
	
	//ActionListener für Menü Info
	public void setMenuInfoActionListener(ActionListener l){
		this.mntmInfo.addActionListener(l);
	}
	
	//ActionListener für Tabelle hinzufügen
	public void setTableAction(MouseAdapter l){
		this.table.addMouseListener(l);
	}
	
	//gesamt offenen betrag in JLabel schreiben
	public void setGesamtOffenText(double betrag){
		String tempBetrag = Double.toString(betrag);
		//textGesamtOffen.setText(Double.toString(betrag));
		this.textGesamtOffen.setText(tempBetrag);
	}
	
	public int getSelectedTableRow(){
		return table.getSelectedRow();
	}
	
	
	public JLabel getStatusBarFileName() {
		return statusBarFileName;
	}

	public void setStatusBarFileName(JLabel statusBarFileName) {
		this.statusBarFileName = statusBarFileName;
	}

	public DefaultTableModel getTable_set() {
		return table_set;
	}

	public void setTable_set(DefaultTableModel table_set) {
		this.table_set = table_set;
	}

	public void setWindowClosingAction(MainWindowClosingListener mainWindowClosingListener) {
		addWindowListener(mainWindowClosingListener);
	}
	
	//zuletzt geöffnet punkt ausblenden wenn text string leer ist
	public void setMenuLastOpenedText(String lastOpenedText){
		if(!lastOpenedText.equals("")){
			mntmLastOpened.setVisible(true);
			mntmLastOpened.setText(lastOpenedText);
		}
		else{
			mntmLastOpened.setText(lastOpenedText);
			mntmLastOpened.setVisible(false);
		}
	}
	
	public void setMenuDateiSpeichernUnterAction(ActionListener l){
		this.mntmDateiSpeichernUnter.addActionListener(l);
	}
	
	public void setMenuDateiExportierenAction(ActionListener l){
		this.mntmDateiExportieren.addActionListener(l);
	}
	
	
}