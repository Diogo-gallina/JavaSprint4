package br.com.fiap.hal9000.model;

import java.time.LocalDateTime;

public class Feedback {

	private int id;
	private LocalDateTime dataFeedback;
	private boolean tipoPrevisao;
	
	public Feedback() {
	}
	
	public Feedback(int id, LocalDateTime dataFeedback, boolean tipoPrevisao) {
		super();
		this.id = id;
		this.dataFeedback = dataFeedback;
		this.tipoPrevisao = tipoPrevisao;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getDataFeedback() {
		return dataFeedback;
	}
	public void setDataFeedback(LocalDateTime dataFeedback) {
		this.dataFeedback = dataFeedback;
	}
	public boolean getTipoPrevisao() {
		return tipoPrevisao;
	}
	public void setTipoPrevisao(boolean tipoPrevisao) {
		this.tipoPrevisao = tipoPrevisao;
	}
	
}
