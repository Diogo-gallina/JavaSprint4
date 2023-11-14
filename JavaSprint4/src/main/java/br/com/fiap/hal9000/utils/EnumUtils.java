package br.com.fiap.hal9000.utils;

import br.com.fiap.hal9000.enums.EnumTipoModal;

public class EnumUtils {
	
	public static EnumTipoModal converteStringParaEnumTipoModal(String valor) {
	    switch (valor) {
	        case "GUINCHO PESADO NAO PADRAO":
	            return EnumTipoModal.GUINCHO_PESADO_NAO_PADRAO;
	        case "PESADO COM PLAT HIDRAULICA":
	            return EnumTipoModal.PESADO_COM_PLAT_HIDRAULICA;
	        case "PESADO COM PLAT HIDRAULICA MUNCK":
	            return EnumTipoModal.PESADO_COM_PLAT_HIDRAULICA_MUNCK;
	        case "PESADO COM PLAT HIDRAULICA E BAND":
	            return EnumTipoModal.PESADO_COM_PLAT_HIDRAULICA_E_BAND;
	        case "PESADO COM QUINTA RODA E BANDEJA":
	            return EnumTipoModal.PESADO_COM_QUINTA_RODA_E_BANDEJA;
	        case "PESADO COM PLAT HIDRAULICA E LANCA":
	            return EnumTipoModal.PESADO_COM_PLAT_HIDRAULICA_E_LANCA;
	        case "PESADO COM QUINTA RODA E LANCA":
	            return EnumTipoModal.PESADO_COM_QUINTA_RODA_E_LANCA;
	        case "TECNICO PESADO":
	            return EnumTipoModal.TECNICO_PESADO;
	        default:
	            throw new IllegalArgumentException("Valor de enum desconhecido: " + valor);
	    }
	}

	
}
