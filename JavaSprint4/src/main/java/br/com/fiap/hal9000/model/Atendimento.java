package br.com.fiap.hal9000.model;

import java.time.LocalDateTime;

public class Atendimento {

	private int id;
	
	private String os;
	private LocalDateTime dataAtendimento;
	private String complexidade;
	
	private Modal modal;
	private Usuario usuario;
	private Veiculo veiculo;
	private Endereco endereco;
	private Feedback feedback;
	
	public Atendimento() {
	}
	
	public Atendimento(int id, String os, LocalDateTime dataAtendimento, String complexidade, Modal modal, Usuario usuario,
			Veiculo veiculo, Endereco endereco, Feedback feedback) {
		super();
		this.id = id;
		this.os = os;
		this.dataAtendimento = dataAtendimento;
		this.complexidade = complexidade;
		this.modal = modal;
		this.usuario = usuario;
		this.veiculo = veiculo;
		this.endereco = endereco;
		this.feedback = feedback;
	}
	
	public Atendimento(int id, String os, LocalDateTime dataAtendimento, String complexidade) {
		super();
		this.id = id;
		this.os = os;
		this.dataAtendimento = dataAtendimento;
		this.complexidade = complexidade;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public LocalDateTime getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(LocalDateTime dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	public String getComplexidade() {
		return complexidade;
	}
	public void setComplexidade(String complexidade) {
		this.complexidade = complexidade;
	}
	public Modal getModal() {
		return modal;
	}
	public void setModal(Modal modal) {
		this.modal = modal;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	
}
