package windows;

import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JDialog;
import mainProgram.Controller;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class InfoDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private Point windowPosition;
	
	public InfoDialog(Controller controller, MainWindow fenster){
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setTitle("Info");
		setSize(294, 202);
		windowPosition = controller.getLocationForWindow(fenster, this);
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\icon.png"));
		setLocation(windowPosition);
		getContentPane().setLayout(null);
		
		JLabel lblKassaprogV = new JLabel("KassaProg v2.0");
		lblKassaprogV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblKassaprogV.setBounds(24, 11, 142, 62);
		getContentPane().add(lblKassaprogV);
		
		JLabel lblNewLabel = new JLabel("Ein kleines Kassenverwaltungs Programm! ;)");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(24, 65, 283, 19);
		getContentPane().add(lblNewLabel);
		
		JLabel lblIdeeUndEntwicklung = new JLabel("Idee und Entwicklung von Neulii.");
		lblIdeeUndEntwicklung.setVerticalAlignment(SwingConstants.TOP);
		lblIdeeUndEntwicklung.setHorizontalTextPosition(SwingConstants.LEFT);
		lblIdeeUndEntwicklung.setBounds(24, 84, 283, 19);
		getContentPane().add(lblIdeeUndEntwicklung);
		
		JLabel lblJava = new JLabel("Java :)");
		lblJava.setVerticalAlignment(SwingConstants.TOP);
		lblJava.setHorizontalTextPosition(SwingConstants.LEFT);
		lblJava.setBounds(24, 136, 283, 19);
		getContentPane().add(lblJava);
		
		setModal(true);
		setResizable(false);
		setVisible(true);
	}
}
