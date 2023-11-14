package br.com.fiap.hal9000.utils;

public class StringUtils {

	public static boolean isNullOrEmpty(String valor) {
		return (valor == null || valor.isEmpty());
	}
	
	public static boolean hasMoreThan(String valor, int caracteres) {
		return valor.length() > caracteres;
	}
	
	public static boolean isNullOrEmptyOrHasMoreThan(String valor, int caracateres) {
		return isNullOrEmpty(valor) || hasMoreThan(valor, caracateres);
	}
	
	public static boolean has(String valor, int caracteres) {
		return valor.length() == caracteres;
	}

}
