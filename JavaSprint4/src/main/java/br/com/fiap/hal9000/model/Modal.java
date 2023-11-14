package br.com.fiap.hal9000.model;

import br.com.fiap.hal9000.enums.EnumTipoModal;

public class Modal {

	private int id;
	private EnumTipoModal tipoModal;
	
	public Modal() {
	}
	
	public Modal(int id, EnumTipoModal tipoModal) {
		super();
		this.id = id;
		this.tipoModal = tipoModal;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public EnumTipoModal getTipoModal() {
		return tipoModal;
	}
	public void setTipoModal(EnumTipoModal tipoModal) {
		this.tipoModal = tipoModal;
	}
	
}
