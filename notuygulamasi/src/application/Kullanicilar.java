package application;

public class Kullanicilar {
	
	private int ID;
	private String kullanici_adi;
	private String sifre;

	
	public Kullanicilar() {
		
	}
    public Kullanicilar(int ID,String kullanici_adi,String sifre) {
		
		this.ID=ID;
		this.kullanici_adi=kullanici_adi;
		this.sifre=sifre;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		this.ID = ID;
	}


	public String getKullanici_adi() {
		return kullanici_adi;
	}


	public void setKullanici_adi(String kullanici_adi) {
		this.kullanici_adi = kullanici_adi;
	}


	public String getSifre() {
		return sifre;
	}


	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	
}
