package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.EMysql.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Form2 {
	
	Connection baglanti=VeritabaniUtil.Baglan();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextArea ta1;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn4;

    @FXML
    private DatePicker dt;
    
    @FXML
    private Label lbl1;
    
    @FXML
    private TextField konu;
    
  
    @FXML
    private Button btn3;

    
    
    @FXML
    void ekle(ActionEvent event) {
    
    
    	PreparedStatement sorguIfade3=null;
    	String sql="insert into notlar(kID,konu,tarih,mesaj) values(?,?,?,?)";
    	
    	
    	try {
    		
    	sorguIfade3=baglanti.prepareStatement(sql);  		
    	sorguIfade3.setLong(1,SampleController.id);
    	
    	sorguIfade3.setString(2,konu.getText().trim());
      	String zaman = dt.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    	sorguIfade3.setString(3,zaman);
    	sorguIfade3.setString(4,ta1.getText().trim());
    	
    	sorguIfade3.executeUpdate();
    	
    	//lbl1.setText("Eklendi");
    	
    	Alert bilgi=new Alert(Alert.AlertType.INFORMATION);
	    	bilgi.setTitle("BÝLGÝ MESAJI");
	    	bilgi.setHeaderText(null);
	    	bilgi.setContentText("NOT EKLENDÝ");
	    	bilgi.showAndWait();
	    	
    	} catch (Exception e) {
    	//lbl1.setText(e.getMessage().toString());
    	System.out.println(e.getMessage().toString());
    	
        }
       
   }
    
    
    
    
    
    @FXML
    void goruntule(ActionEvent event) {
    		
    	if(SampleController.id==1) {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kulpanel.fxml"));
    	Parent root6;
		try {
			
			Stage stage3 = (Stage) btn3.getScene().getWindow();
			stage3.close();
				
			//yenisayfa açýyoruz
			root6 = (Parent) fxmlLoader.load();
			Stage stage4 = new Stage();
	    	stage4.setScene(new Scene(root6));  
	    	stage4.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	}
    	else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("ERÝÞÝM HATASI");
    		alert.setHeaderText("Eriþim izniniz yok !");
    		alert.setContentText("Burayý yalnýzca yönetici görüntüleyebilir . ");
    		alert.showAndWait();
    	}
    	
    }
    
    

    @FXML
    void sil(ActionEvent event) {
    	
    	ta1.setText("");
    }

    @FXML
    void yenisayfa(ActionEvent event) {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ucuncu.fxml"));
    	Parent root1;
		try {
			
			//sayfayý kapatýyoruz.
			Stage stage1 = (Stage) btn4.getScene().getWindow();
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
    void initialize() {
       
     dt.setValue(LocalDate.now());
           
    }
}

    	
 

  
    	
    	
   
    	
  

