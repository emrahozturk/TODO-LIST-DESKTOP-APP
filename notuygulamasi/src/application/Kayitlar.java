package application;

public class Kayitlar {
	
	private int id;
	private String tarih;
	private String konu;
	private String not;
	
	public Kayitlar() {
		
	}
	
	public Kayitlar(int id,String tarih,String konu,String not) {
		
		this.id=id;
		this.tarih=tarih;
		this.konu=konu;
		this.not=not;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTarih() {
		return tarih;
	}
	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	public String getKonu() {
		return konu;
	}
	public void setKonu(String konu) {
		this.konu = konu;
	}
	public String getNot() {
		return not;
	}
	public void setNot(String not) {
		this.not = not;
	}

	

	
	

	

}
