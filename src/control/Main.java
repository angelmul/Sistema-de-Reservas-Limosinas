package control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//Clase ejemplo ud1
public class Main {
	
	//Metodo que devuelve si la proceso sigue activo
	public static boolean isAlive(Process p) {
		try {
			p.exitValue();
			return false;
		} catch (IllegalThreadStateException e) {
			return true;
		}
	}

	//Metodo main del programa
	public static void main(String[] args) throws IOException {
		ProcessBuilder builder = new ProcessBuilder("java", "-jar", "./Sistema.jar");//Esta aplicación utiliza un jar generado con eclipse, el builder se encarga de buscar el jar en el directorio no absoluto del proyecto para ejecutarlo.
		builder.redirectErrorStream(true);
		Process process = builder.start();
		InputStream out = process.getInputStream();
		OutputStream in = process.getOutputStream();

		byte[] buffer = new byte[4000];
		while (isAlive(process)) {
			int no = out.available();
			if (no > 0) {
				int n = out.read(buffer, 0, Math.min(no, buffer.length));
				System.out.println(new String(buffer, 0, n));
			}

			int ni = System.in.available();
			if (ni > 0) {
				int n = System.in.read(buffer, 0, Math.min(ni, buffer.length));
				in.write(buffer, 0, n);
				in.flush();
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}

		System.out.println(process.exitValue());
	}
}