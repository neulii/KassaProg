package utily;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import model.ProduktListe;
import model.SchuldnerListe;

public class ExcelWorkbook {
	
	private HSSFWorkbook wb;
	private SchuldnerListe schuldnerListe;
	private CellStyle cellWithThickBorder;
	private CellStyle cellWithBorder;
	private Sheet sheet;
	private ProduktListe produktListe;
	private int offsetLeft = 0;
	private int offsetTop = 4;
	private File file;
	
	private FileOutputStream out;

	public ExcelWorkbook(SchuldnerListe schuldnerListe, ProduktListe produktListe, File file){
		
		this.schuldnerListe = schuldnerListe;
		this.produktListe = produktListe;
		this.file = file;
		
		wb = new HSSFWorkbook();
		sheet = wb.createSheet("Kassaliste");

		//Zellenstyles erstellen
		cellWithThickBorder = wb.createCellStyle();
			cellWithThickBorder.setBorderBottom(CellStyle.BORDER_THICK);
			cellWithThickBorder.setBorderLeft(CellStyle.BORDER_THICK);
			cellWithThickBorder.setBorderTop(CellStyle.BORDER_THICK);
			cellWithThickBorder.setBorderRight(CellStyle.BORDER_THICK);
			cellWithThickBorder.setAlignment(CellStyle.ALIGN_CENTER);
			cellWithThickBorder.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			
		cellWithBorder = wb.createCellStyle();
			cellWithBorder.setBorderBottom(CellStyle.BORDER_THIN);
			cellWithBorder.setBorderLeft(CellStyle.BORDER_THIN);
			cellWithBorder.setBorderTop(CellStyle.BORDER_THIN);
			cellWithBorder.setBorderRight(CellStyle.BORDER_THIN);
			
		makeWorkbook();
	}

	public void makeWorkbook(){
		for(int i=0; i<schuldnerListe.getSize(); i++){
			//Namen an linker tabellenseite einfügen
			Row row = sheet.createRow(offsetTop + i +2);
			Cell cell = row.createCell(offsetLeft);

			cell.setCellValue(schuldnerListe.getSchuldnerAt(i).getNameSchuldner());
			cell.setCellStyle(cellWithThickBorder);
			
			row.setHeight((short)(256*1.5));
			
			//Restliche Tabelle zeichnen
			for(int n=1; n<produktListe.getSize()+2; n++){
				Cell emptyCell = row.createCell(offsetLeft+n);
				emptyCell.setCellStyle(cellWithThickBorder);
				if(n==produktListe.getSize()+1){
					emptyCell.setCellValue(String.valueOf(schuldnerListe.getSchuldnerAt(i).getOffenerBetrag()).replace(".", ",") +  " €");				
				}
			}
		}
		
		//ProduktName anzeigen
		Row productRow = sheet.createRow(offsetTop);
		productRow.setHeight((short)(256*1.5));
		for(int f=0; f<produktListe.getSize()+1; f++){
			Cell productName = productRow.createCell(offsetLeft+1+f);
			productName.setCellStyle(cellWithThickBorder);
			
			if(f<produktListe.getSize()){
				productName.setCellValue(produktListe.getProduktAt(f).getName());
			}
			//am ende der tabelle summe anzeigen
			if(f==produktListe.getSize()){
				productName.setCellValue("Schuld");
			}
		}
		
		//Produktpreis anzeigen
		Row priceRow = sheet.createRow(offsetTop+1);
		priceRow.setHeight((short)(256*1.5));
		for(int s=0; s<produktListe.getSize() + 1 ; s++){
			Cell price = priceRow.createCell(offsetLeft+1+s);
			price.setCellStyle(cellWithThickBorder);
			
			//Kommapunkt in Beistrich umwandeln
			if(s<produktListe.getSize()){
				String priceToShow = Double.toString(produktListe.getProduktAt(s).getPreis());	
				price.setCellValue( priceToShow.replace(".", ",") +  "€");
			}
		}
		
		//Spalte Namen breite optimieren
		for(int sb=offsetLeft+1; sb < produktListe.getSize()+offsetLeft+2;sb++){
			sheet.setColumnWidth(sb, 256*12);
		}
	
		sheet.autoSizeColumn(offsetLeft);
		//Querformat einstellen
		sheet.getPrintSetup().setLandscape(true);
		//tabelle auf Blatt zentrieren
		sheet.setHorizontallyCenter(true);
		
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setFitHeight((short)1);
	    printSetup.setFitWidth((short)1);
	}
	
	public void writeWorkbook(){
		try {
			out = new FileOutputStream(file);
			wb.write(out);
			wb.close();
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
