
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Yonetim {

	public int tID;
	Connection baglanti=VeritabaniUtil.Baglan();
   

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button btn_geri;

    @FXML
    private TableView<Kullanicilar> ktablo;

    @FXML
    private TableColumn<Kullanicilar,Integer> kisi_ID;
    
    @FXML
    private TableColumn<Kullanicilar,String> kadi;

    @FXML
    private TableColumn<Kullanicilar,String> sifre;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_yenile;
    
    @FXML
    private Label lbl;

    @FXML
    void geriye_git(ActionEvent event) {
    	
    	Stage stage = (Stage) btn_geri.getScene().getWindow();
	    
		stage.close();

    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sample.fxml"));
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
    void kullanici_sil(ActionEvent event) {
  
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("KULLANICI SÝLÝNSÝN MÝ ? ");
    	alert.setHeaderText(null);
    	alert.setContentText("Silmek istediðnizden emin misiniz?");
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if (result.get() == ButtonType.OK){
    		
    		
    		PreparedStatement sorgu=null;
         	String sql="delete from giris where ID=? ";

         	try {
         		//secili labelý sil
            	sorgu=baglanti.prepareStatement(sql);
            	sorgu.setString(1,lbl.getText());
            	sorgu.executeUpdate();
            	
            	Alert bilgi=new Alert(Alert.AlertType.INFORMATION);
     	    	bilgi.setTitle("BÝLGÝ MESAJI");
     	    	bilgi.setHeaderText(null);
     	    	bilgi.setContentText("SÝLME ÝÞLEMÝ BAÞARILI");
     	    	bilgi.showAndWait();
     	    	
     	    	
            	
            	} catch (Exception e) {
            	
            		Alert hata = new Alert(AlertType.ERROR);
         			hata.setTitle("Hata Mesajý");
         			hata.setHeaderText("HATA VAR");
         			hata.setContentText("Silme iþlemi baþarýsýz oldu");
         			hata.showAndWait();
         			
            	}
    		
    		
    		// ... Kullanýcý OK butonuna bastýysa}
    	}
    	else { // ... Kullanýcý Cancel butonuna bastýysa }
  
    		
    		
    		
    	}
    	
    	
    	/*PreparedStatement sorgu=null;
     	String sql="delete from giris where ID=? ";

     	try {
     		//secili labelý sil
        	sorgu=baglanti.prepareStatement(sql);
        	sorgu.setString(1,lbl.getText());
        	sorgu.executeUpdate();
        	
        	Alert bilgi=new Alert(Alert.AlertType.INFORMATION);
 	    	bilgi.setTitle("BÝLGÝ MESAJI");
 	    	bilgi.setHeaderText(null);
 	    	bilgi.setContentText("SÝLME ÝÞLEMÝ BAÞARILI");
 	    	bilgi.showAndWait();
 	    	
 	    	
        	
        	} catch (Exception e) {
        	
        		Alert hata = new Alert(AlertType.ERROR);
     			hata.setTitle("Hata Mesajý");
     			hata.setHeaderText("HATA VAR");
     			hata.setContentText("Silme iþlemi baþarýsýz oldu");
     			hata.showAndWait();
     			
        	}*/

     	 
    }
    
    @FXML
    void secme(MouseEvent event) {
    		
    	Kullanicilar kisi=new Kullanicilar();
    	kisi=(Kullanicilar)ktablo.getItems().get(ktablo.getSelectionModel().getSelectedIndex());
    	
    	
    	tID=kisi.getID();
  
    	lbl.setText(Integer.toString(tID));
    	
    	System.out.println(tID);
    }
    
    @FXML
    void yenile(ActionEvent event) {

    	initialize();
    }

  

   

    @FXML
    void initialize() {
    	
    
   	    ObservableList<Kullanicilar>kisiler;
	    kisiler=FXCollections.observableArrayList();
   	
	    	  
    	try {
    		PreparedStatement sorguIfade=null;
       	String sql;
       	
       	//kullanici idsine göre mesaj getirme
       	sql="select * from giris  ";
       	sorguIfade=baglanti.prepareStatement(sql);
      
       	ResultSet gelen=sorguIfade.executeQuery();
       	
	    	//ResultSet gelen=baglanti.createStatement().executeQuery("select * from notal where kullanici=1");
	    	
	 	
	    	while(gelen.next()) {
	    		kisiler.add(new Kullanicilar(gelen.getInt(1),gelen.getString(2),gelen.getString(3)));
	    		
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//basta sütun adý sonra kullanýcýlarlar sýnýfý parametreleri 
    	
      kisi_ID.setCellValueFactory(new PropertyValueFactory<> ("ID"));
   	  kadi.setCellValueFactory(new PropertyValueFactory<>("kullanici_adi"));
   	  sifre.setCellValueFactory(new PropertyValueFactory<> ("sifre"));
   	
   	
   	
   	ktablo.setItems(kisiler);
        
    	
    } 
}
