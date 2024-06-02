package br.com.ifsp.vcRiquinho.produto.exceptions;

@SuppressWarnings("serial")
public class DaysCannotByNegativeException extends RuntimeException {

	public DaysCannotByNegativeException(String message) {
		super(message);
	}

}
