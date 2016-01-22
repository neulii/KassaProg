package utily;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

public class PropertyHandler extends Properties {

	private static final long serialVersionUID = 1L;	
		
	private File file;
	
	public PropertyHandler(File file){
		this.file = file;
	}
	public void saveToFile(String title){
		
		try {
			Writer writer = new FileWriter(file);
			
			this.store(writer,title);
			
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern aufgetreten!");
			
			e.printStackTrace();
		}
	}
	
	public void loadFromFile() throws IOException{
			Reader reader;
			reader = new FileReader(file);
			load(reader);
	}
	
	public File getFile() {
		return file;
	}
}
