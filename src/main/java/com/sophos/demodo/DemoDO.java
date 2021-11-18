package com.sophos.demodo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class DemoDO {

	/* Log de eventos */
	static {
		System.setProperty("log4j2.configurationFile", "com/sophos/demodo/log4j2.xml");
	}
	private static final Logger LOGGER = LogManager.getLogger();

	private DemoDO() {

	}

	/**
	 * Método principal
	 * @param args argumentos de línea de comandos
	 */
	public static void main(String[] args) {
		// Mensaje de bienvenida
		LOGGER.info("");
		LOGGER.info("Bienvenido al modulo de aritmetica basica");
		LOGGER.info("=========================================");
		LOGGER.info("");

		// Recibe el primer argumento
		LOGGER.info("Ingrese el primer argumento: ");
		final int arg1 = leerEnteroDeConsola();

		// Recibe el segundo argumento
		LOGGER.info("Ingrese el segundo argumento: ");
		final int arg2 = leerEnteroDeConsola();

		// Realiza los cálculos
		LOGGER.info(calcular(arg1, arg2));

		// Sale
		System.exit(0);
	}

	/**
	 * Procedimiento que realiza los cálculos
	 * @param arg1 primer argumento
	 * @param arg2 segundo argumento
	 */
	public static String calcular(int arg1, int arg2) {
		// Genera el texto con los resultados
		final StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("Resultados");
		sb.append("\n");
		sb.append("----------------------------------------------------------");
		sb.append("\n");
		sb.append("Suma de los argumentos:            (").append(arg1).append(" + ").append(arg2).append(") = ").append(sumar(arg1, arg2));
		sb.append("\n");
		sb.append("Resta de los argumentos:           (").append(arg1).append(" - ").append(arg2).append(") = ").append(restar(arg1, arg2));
		sb.append("\n");
		sb.append("Producto de los argumentos:        (").append(arg1).append(" x ").append(arg2).append(") = ").append(multiplicar(arg1, arg2));
		sb.append("\n");
		sb.append("Cociente entero de los argumentos: (").append(arg1).append(" / ").append(arg2).append(") = ").append(dividir(arg1, arg2));
		sb.append("\n");
		sb.append("\n");

		// Entrega el resultado
		return sb.toString();
	}

	/**
	 * Procedimiento que lee un número entero de la consola y lo retorna
	 * @return el número entero ingresado por el usuario
	 */
	private static int leerEnteroDeConsola() {
		// Configura el scanner de la línea de comandos
		final Scanner scanner = new Scanner(System.in);

		// Lee lo ingresado por el usuario
		final String tmp = scanner.next();

		// Valida si es un entero y lo retorna
		try {
			return Integer.parseInt(tmp);
		} catch (NumberFormatException nfe) {
			LOGGER.fatal("Se esperaba un numero entero y se recibio '" + tmp + "'");
			System.exit(0);
			return 0;
		}
	}

	/**
	 * Procedimiento que suma dos enteros
	 * @param arg1 argumento 1
	 * @param arg2 argumento 2
	 * @return la suma de los argumentos
	 */
	public static int sumar(int arg1, int arg2) {
		final int resultado = arg2 + arg1;
		return resultado;
	}

	/**
	 * Procedimiento que sustrae dos enteros
	 * @param arg1 argumento 1
	 * @param arg2 argumento 2
	 * @return la sustracción de los argumentos
	 */
	public static int restar(int arg1, int arg2) {
		return arg1 - arg2;
	}

	/**
	 * Procedimiento que multiplica dos enteros
	 * @param arg1 argumento 1
	 * @param arg2 argumento 2
	 * @return el producto de los argumentos
	 */
	public static int multiplicar(int arg1, int arg2) {
		final int signo = Integer.signum(arg1) * Integer.signum(arg2);

		int multiplicando = Math.abs(arg1);
		int multiplicador = Math.abs(arg2);

		int producto = 0;
		for(int i = 0 ; i < multiplicador ; i++) {
			producto += multiplicando;
		}
		return producto * signo;
	}

	/**
	 * Procedimiento que divide dos enteros
	 * @param dividendo dividendo
	 * @param divisor divisor
	 * @return el cociente entero de la división de los argumentos
	 */
	public static int dividir(int dividendo, int divisor) {
		if(divisor == 0) {
			throw new ArithmeticException("División por cero");
		}


		int cociente = 0;

		int signoDividendo = Integer.signum(dividendo);
		int signoDivisor = Integer.signum(divisor);

		int residuo = Math.abs(dividendo);
		int div = Math.abs(divisor);
		while ((residuo -= div) >= 0) {
			cociente++;
		}
		return cociente * (signoDividendo * signoDivisor);
	}

}
