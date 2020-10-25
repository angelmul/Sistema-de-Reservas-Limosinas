package sistema;

import java.text.ParseException;
import java.util.Scanner;


//Clase encargada de iniciar la clase modelo de limosinas y de seleccionar una opción
public class Gestor {

	private Scanner teclado = new Scanner (System.in);
	private String prompt = "Sistema Reservas: "; //String para la salida a la terminal
	private String modelo1 = "model-1";
	private String modelo2 = "model-2";
	//Lista de limosinas a iniciar
	Limosina[] iniciarLimosinas = {
			new Limosina("1122GHF",modelo1,1),
			new Limosina("1133FRM",modelo1,1),
			new Limosina("1144SDS",modelo2,1),
			new Limosina("2222FGH",modelo1,2),
			new Limosina("2233JKL",modelo2,2),
			new Limosina("2244ASD",modelo2,2),
			new Limosina("3322TYU",modelo2,3),
			new Limosina("3333OLM",modelo2,3),
			new Limosina("3344UJS",modelo2,3),
	};
	private ModeloLimosina lista = new ModeloLimosina(iniciarLimosinas, prompt);
	
	//Metodo de bienvenida al sistema y de la ejecución y selección dl programa.
	public void iniciar() throws ParseException {
		Boolean salir = false;
		String opc = "";
		System.out.println("");
		System.out.println("################################################");
		System.out.println("Bienvenido al Sistema de Reservas de Limosinas");
		System.out.println("################################################");
		
		while(!salir.booleanValue()) {
			System.out.println("\n"+prompt+"¿Qué desea hacer? [MOSTRAR LISTA / RESERVAR / CONSULTAR RESERVA / MODIFICAR RESERVA / ELIMINAR RESERVA / SALIR] ");
			
			opc = teclado.next();
			opc = opc.toLowerCase();
			
			switch(opc) {
			case "mostrar":
			case "mostrar lista":
				lista.mostrarLimosinasDisponibles();
				break;
			case "reservar limosina":
			case "reservar":
				lista.reservarLimosina();
				break;
			case "consultar reserva":
			case "consulta reserva":
			case "consulta":
			case "consultar":
				lista.consultarReserva();
				break;
			case "modificar reserva":
			case "modificar":
				lista.modificarReserva();
				break;
			case "eliminar reserva":
			case "eliminar":
				lista.eleminarReserva();
				break;
			case "salir":
				System.out.println(prompt+"Saliendo del sistema... Que tenga un buen día!");
				salir = true;
				break;
			default:
				System.out.println(prompt+"PORFAVOR INTRODUZCA UNA OPCIÓN VALIDA!");
			}
		}
	}
}