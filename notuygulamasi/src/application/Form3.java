package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.EMysql.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Form3 {
	
	Connection baglanti=VeritabaniUtil.Baglan();
	
	public int notID;
	public int kullanici;
	
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Kayitlar> tablo;
    
    @FXML
    private TableColumn<Kayitlar,Integer> id;

    @FXML
    private TableColumn<Kayitlar, String> tarih;

    @FXML
    private TableColumn<Kayitlar, String> konu;
    
    @FXML
    private TableColumn<Kayitlar, String> not;

    @FXML
    private Button btn1;

    @FXML
    private Button btn3;

    @FXML
    private Button btn2;
    
    @FXML
    private TextField tf1;

    @FXML
    private TextField tf2;

    @FXML
    private TextArea ta;
    
    @FXML
    private Label label1;
    
    @FXML
    private Button btn4;
    
    @FXML
    private Button btn5;

    @FXML
    void cik(ActionEvent event) {

    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sample.fxml"));
    	Parent root1;
		try {
			
			//sayfayý kapatýyoruz.
			Stage stage1 = (Stage) btn5.getScene().getWindow();
			stage1.close();
			
			//yenisayfa açýyoruz
			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
	    	stage.setScene(new Scene(root1));  
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    
    }
    
    
    @FXML
    void duzenle(ActionEvent event) {
    	
    	//seçili olan satýrý bulmam lazým
    	
    	
    	PreparedStatement sorgu=null;
     	
     	String sql="update notlar set konu=?, mesaj=? ,tarih=? where notID=? and kID=? ";
     	
     	try {
 			sorgu=baglanti.prepareStatement(sql);
 			sorgu.setString(1,tf2.getText());
 	    	sorgu.setString(2,ta.getText());
 	    	sorgu.setString(3, tf1.getText());
 	    	
 	    	notID=Integer.parseInt(label1.getText());
 	    	
 	    	sorgu.setLong(4,notID);
 	    	sorgu.setLong(5,SampleController.id);
 	        	
 	    	sorgu.executeUpdate();
 		
 	    	Alert bilgi=new Alert(Alert.AlertType.INFORMATION);
 	    	bilgi.setTitle("BÝLGÝ MESAJI");
 	    	bilgi.setHeaderText(null);
 	    	bilgi.setContentText("GÜNCELLEME BAÞARILI");
 	    	bilgi.showAndWait();
 	    	
 	    	
     	
     	} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			
 			Alert hata = new Alert(AlertType.ERROR);
 			hata.setTitle("Hata Mesajý");
 			hata.setHeaderText("HATA VAR");
 			hata.setContentText("Güncelleme baþarýsýz oldu");
 			hata.showAndWait();
 			
 		}
    	   
    	
    }
   
    
    @FXML
    void ekleme_sayfasina_git(ActionEvent event) {
    	Stage stage = (Stage) btn2.getScene().getWindow();
		stage.close();
	
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ikinci.fxml"));
    	Parent root1;
    
    	try {
		root1 = (Parent) fxmlLoader.load();
		Stage stage2 = new Stage();
    	stage2.setScene(new Scene(root1));  
    	stage2.show();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
  }
    
    @FXML
    void sil(ActionEvent event) {

    	PreparedStatement sorgu2=null;
     	String sql="delete from notlar where notID=? and kID=? ";

     	try {
        	sorgu2=baglanti.prepareStatement(sql);
        	sorgu2.setLong(1,notID);
        	sorgu2.setLong(2,SampleController.id);
        	sorgu2.executeUpdate();
        	
        	Alert bilgi=new Alert(Alert.AlertType.INFORMATION);
 	    	bilgi.setTitle("BÝLGÝ MESAJI");
 	    	bilgi.setHeaderText(null);
 	    	bilgi.setContentText("SÝLME ÝÞLEMÝ BAÞARILI");
 	    	bilgi.showAndWait();
 	    	
 	    	tf1.setText("");
 	    	tf2.setText("");
 	    	ta.setText("");
        	
        	} catch (Exception e) {
        	
        		Alert hata = new Alert(AlertType.ERROR);
     			hata.setTitle("Hata Mesajý");
     			hata.setHeaderText("HATA VAR");
     			hata.setContentText("Silme iþlemi baþarýsýz oldu");
     			hata.showAndWait();
     			
        	}
    	
    	
     	System.out.println(notID);

    }
    
    @FXML
    void tablo_tiklama(MouseEvent event) {
    	

    	Kayitlar kayit=new Kayitlar();
    	kayit=(Kayitlar)tablo.getItems().get(tablo.getSelectionModel().getSelectedIndex());
    	
    	
    	notID=kayit.getId();
    	
    	tf1.setText(kayit.getTarih());
    	tf2.setText(kayit.getKonu());
    	ta.setText(kayit.getNot());
  
    	label1.setText(Integer.toString(notID));
    	
    	
    	//System.out.println(kayit.getNot());
    	
    	//System.out.println(mid);
    
    	
    }
    
    @FXML
    void yenile(ActionEvent event) {
    	
    initialize();
    
    }
    
    @FXML
    void initialize() {
    
    	// kullanici idsi ni görüntüleme
    	//System.out.println(SampleController.id);
    	
    	
         Connection baglanti=VeritabaniUtil.Baglan();
    	 ObservableList<Kayitlar>veriler;
	    	veriler=FXCollections.observableArrayList();
    	
	    	  
     	try {
     		PreparedStatement sorguIfade=null;
        	String sql;
        	
        	//kullanici idsine göre mesaj getirme
        	sql="select * from notlar where kID=? ";
        	sorguIfade=baglanti.prepareStatement(sql);
       
        	sorguIfade.setLong(1, SampleController.id);
        	ResultSet gelen=sorguIfade.executeQuery();
        	
	    	//ResultSet gelen=baglanti.createStatement().executeQuery("select * from notal where kullanici=1");
	    	
	  
	    	
	    	while(gelen.next()) {
	    		veriler.add(new Kayitlar(gelen.getInt(1),gelen.getString(4),gelen.getString(3),gelen.getString(5)));
	    		
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	
     	id.setCellValueFactory(new PropertyValueFactory<> ("id"));
    	konu.setCellValueFactory(new PropertyValueFactory<>("konu"));
    	tarih.setCellValueFactory(new PropertyValueFactory<> ("tarih"));
    	not.setCellValueFactory(new PropertyValueFactory<> ("not"));
    	
    	
    	tablo.setItems(veriler);
    	
    	
    	
    /*	//Tabloyu düzenleme 
    	 tablo.setEditable(true);
    	 konu.setCellFactory(TextFieldTableCell.forTableColumn());
    	 not.setCellFactory(TextFieldTableCell.forTableColumn());
    	 tarih.setCellFactory(TextFieldTableCell.forTableColumn());
 */   	 
   
    	 //mouse týklama fonksiyonuna eklenecek
    	/* Kayitlar kayit=new Kayitlar();
    	 kayit= (Kayitlar) tablo.getItems().get(tablo.getSelectionModel().getSelectedIndex());
     	 */
    	 
    	 
    	 
    	/*ObservableList<Kayitlar>veriler;
    	veriler=FXCollections.observableArrayList();
    	veriler.add(new Kayitlar(1,"a","b","c"));

    	//ilk sütun id 2.ci kaytlar idsi
    	id.setCellValueFactory(new PropertyValueFactory<> ("id"));
    	tarih.setCellValueFactory(new PropertyValueFactory<> ("tarih"));
    	konu.setCellValueFactory(new PropertyValueFactory<>("konu"));
    	not.setCellValueFactory(new PropertyValueFactory<> ("not"));
    	
    	tablo.setItems(veriler);*/

    }
}
