package br.com.fiap.hal9000.enums;

public enum EnumTipoModal {
    GUINCHO_PESADO_NAO_PADRAO("GUINCHO PESADO NAO PADRAO"),
    PESADO_COM_PLAT_HIDRAULICA("PESADO COM PLAT HIDRAULICA"),
    PESADO_COM_PLAT_HIDRAULICA_MUNCK("PESADO COM PLAT HIDRAULICA MUNCK"),
    PESADO_COM_PLAT_HIDRAULICA_E_BAND("PESADO COM PLAT HIDRAULICA E BAND"),
    PESADO_COM_QUINTA_RODA_E_BANDEJA("PESADO COM QUINTA RODA E BANDEJA"),
    PESADO_COM_PLAT_HIDRAULICA_E_LANCA("PESADO COM PLAT HIDRAULICA E LANCA"),
    PESADO_COM_QUINTA_RODA_E_LANCA("PESADO COM QUINTA RODA E LANCA"),
    TECNICO_PESADO("TECNICO PESADO");

    private String valor;

    EnumTipoModal(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}