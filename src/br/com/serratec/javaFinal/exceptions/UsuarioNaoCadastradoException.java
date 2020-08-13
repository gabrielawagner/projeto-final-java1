package br.com.serratec.javaFinal.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;

public class UsuarioNaoCadastradoException extends RuntimeException {
	public UsuarioNaoCadastradoException() {
		super();
	}

	public UsuarioNaoCadastradoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioNaoCadastradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioNaoCadastradoException(String message) {
		super(message);
	}

	public UsuarioNaoCadastradoException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}

	@Override
	public synchronized Throwable getCause() {
		return super.getCause();
	}

	@Override
	public synchronized Throwable initCause(Throwable cause) {
		return super.initCause(cause);
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		super.printStackTrace(s);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return super.fillInStackTrace();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return super.getStackTrace();
	}

	@Override
	public void setStackTrace(StackTraceElement[] stackTrace) {
		super.setStackTrace(stackTrace);
	}
	
	
}
