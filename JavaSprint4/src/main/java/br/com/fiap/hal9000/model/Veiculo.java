package br.com.fiap.hal9000.model;

public class Veiculo {

	private int id;
	private String marca;
	private double carga;
	private double tara;
	private double tamanho;
	private String tipoVeiculo;
	
	public Veiculo() {
		super();
	}
	
	public Veiculo(int id, String marca, double carga, double tara, double tamanho, String tipoVeiculo) {
		super();
		this.id = id;
		this.marca = marca;
		this.carga = carga;
		this.tara = tara;
		this.tamanho = tamanho;
		this.tipoVeiculo = tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public double getCarga() {
		return carga;
	}
	public void setCarga(double carga) {
		this.carga = carga;
	}
	public double getTara() {
		return tara;
	}
	public void setTara(double tara) {
		this.tara = tara;
	}
	public double getTamanho() {
		return tamanho;
	}
	public void setTamanho(double tamanho) {
		this.tamanho = tamanho;
	}
	public String getTipoVeiculo() {
		return tipoVeiculo;
	}
	
	
}

