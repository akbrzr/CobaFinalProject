package model;

import javafx.beans.property.SimpleStringProperty;

public class Product {
	String kode;
	String nama;
	int harga;
	int stok;
	
	public Product(String kode, String nama, int harga, int stok) {
		super();
		this.kode = kode;
		this.nama = nama;
		this.harga = harga;
		this.stok = stok;
	}
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public int getHarga() {
		return harga;
	}
	public void setHarga(int harga) {
		this.harga = harga;
	}
	public int getStok() {
		return stok;
	}
	public void setStok(int stok) {
		this.stok = stok;
	}
	
	public SimpleStringProperty kodeProperty() {
		return new SimpleStringProperty(kode);
	}
	
	public SimpleStringProperty namaProperty() {
		return new SimpleStringProperty(nama);
	}
	
	public SimpleStringProperty hargaProperty() {
		return new SimpleStringProperty(Integer.toString(harga));
	}
	
	public SimpleStringProperty stokProperty() {
		return new SimpleStringProperty(Integer.toString(stok));
	}

}