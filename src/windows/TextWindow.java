package windows;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TextWindow extends JFrame {

	private static final long serialVersionUID = 3545677679129328839L;
	private String text;
	
	private JTextArea textArea;
	
	public TextWindow(String text) {
		this.text = text;
		initalizeWindow();
		setText(text);
	}

	private void setText(String text2) {
		textArea.setText(text);
	}

	private void initalizeWindow() {
		setTitle("Text Anzeige Fenster");
//		getContentPane().setLayout(null);
		setBounds(100, 100, 546, 343);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		textArea = new JTextArea();
		//Automatischer Zeilenumbruch aktivieren
		textArea.setLineWrap(true);
		
		textArea.setBounds(10, 11, 510, 282);
		add(textArea);
		
		setVisible(true);
	}
}
