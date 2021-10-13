package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.EMysql.Util.VeritabaniUtil;
import java.sql.*;

public class SampleController {
	Connection baglanti=VeritabaniUtil.Baglan();
	
	public static int id;

	   @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextField txt1;

	    @FXML
	    private Button btn3;

	    @FXML
	    private Button btn4;

	    @FXML
	    private Button btn1;

	    @FXML
	    private Button btn2;

	    @FXML
	    private PasswordField pass;

	    @FXML
	    private Label lbl1;

	   

    @FXML
    void btn1_giris(ActionEvent event) { //baþta 	Connection baglanti=VeritabaniUtil.Baglan();
    	PreparedStatement sorguIfade=null;
    	String sql;
    	
    	sql="select * from giris where kulad=? and sifre=?";
    	try {
    	sorguIfade=baglanti.prepareStatement(sql);
    	sorguIfade.setString(1, txt1.getText().trim());
    	sorguIfade.setString(2,VeritabaniUtil.MD5sifrele(pass.getText()));
    	//pass.getText().trim()
    	
    	ResultSet getirilen=sorguIfade.executeQuery();
    	if(!getirilen.next()) {
    		lbl1.setText("    hatali giriþ yapýldý..");
    		lbl1.setTextFill(Color.color(1, 0, 0));
    	}
    	else {
    	//getirilen.getString(1); //Tabloda 1nolu sutundaki degeri getirir
    		
    		 Stage stage = (Stage) btn1.getScene().getWindow();
    		    
    		 stage.close();
    		
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ikinci.fxml"));
        	Parent root1;
    		try {
    		
    			id=getirilen.getInt("ID");
    			
    			root1 = (Parent) fxmlLoader.load();		
    			Stage stage2 = new Stage();
    	    	stage2.setScene(new Scene(root1));  
    	    	stage2.show();
    	    	
    	    	
    	    	
    	    	
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	} catch (Exception e) { 
    		System.out.println(e.getMessage().toString());
    	}
    }

    @FXML
    void btn2_guncelle(ActionEvent event) {
    	PreparedStatement sorguIfade2=null;
    	//String sql="update giris set kulad=?, sifre=? where kID=1";
    	
    	String sql="update giris set sifre=? where kulad=?";
    	
    	try {
    	sorguIfade2=baglanti.prepareStatement(sql);
    	sorguIfade2.setString(1,VeritabaniUtil.MD5sifrele(pass.getText().trim()));
    	sorguIfade2.setString(2, txt1.getText().trim());
    	sorguIfade2.executeUpdate();
    	
    	lbl1.setText("Güncelleme iþlemi baþarýlý");
    	lbl1.setTextFill(Color.color(1, 0, 0));
    	
    	} catch (Exception e) {
    	lbl1.setText(e.getMessage().toString());
    	lbl1.setTextFill(Color.color(1, 0, 0));
    	}
    }

    @FXML
    void btn3_kaydol(ActionEvent event) {
    	PreparedStatement sorguIfade3=null;
    	String sql="insert into giris(kulad,sifre) values(?,?)";
    	try {
    	sorguIfade3=baglanti.prepareStatement(sql);
    	sorguIfade3.setString(1,txt1.getText().trim());
    	sorguIfade3.setString(2,VeritabaniUtil.MD5sifrele(pass.getText().trim()));
    	sorguIfade3.executeUpdate();
    	lbl1.setText("Ekleme iþlemi baþarýlý");
    	lbl1.setTextFill(Color.color(1, 0, 0));
    	
    	} catch (Exception e) {
    	lbl1.setText(e.getMessage().toString());
    	lbl1.setTextFill(Color.color(1, 0, 0));
    	}
    }

    @FXML
    void btn4_sil(ActionEvent event) {
    	String sql="delete from giris where kulad=? and sifre=?";
    	PreparedStatement sorguIfade4=null;
    	try {
    	sorguIfade4=baglanti.prepareStatement(sql);
    	sorguIfade4.setString(1, txt1.getText().trim());
    	sorguIfade4.setString(2, VeritabaniUtil.MD5sifrele(pass.getText().trim()));
    	sorguIfade4.executeUpdate();
    	lbl1.setText("Silme iþlemi baþarýlý");
    	lbl1.setTextFill(Color.color(1, 0, 0));
    	
    	} catch (Exception e) {
    	lbl1.setText(e.getMessage().toString());
    	lbl1.setTextFill(Color.color(1, 0, 0));
    	}
    }
    
    
  

    @FXML
    void initialize() {
    	
    	
    
    	
    

    }
}
