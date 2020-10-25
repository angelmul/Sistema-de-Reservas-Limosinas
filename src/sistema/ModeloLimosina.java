package sistema;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

//Clase controladora de la lista de limosinas
public class ModeloLimosina {
	
	//La lista de limosinas de almacena en esta clase
	private ArrayList<Limosina> lista;
	Scanner teclado = new Scanner(System.in);
	private String prompt;
	
	//Constructor de la clase se inicializa con el array que contiene todas las limosinas, y un string llamado prompt con la salida del mensaje que va tener la clase
	public ModeloLimosina(Limosina[] listaEnt, String prompt) {
		lista = new ArrayList<>();
		lista.addAll(Arrays.asList(listaEnt));
		this.prompt = prompt;
	}
	
	//Metodo para mostrar la lista de limosinas disponibles
	public void mostrarLimosinasDisponibles() {
		int enc = 0;
		System.out.println("################################################");		
		System.out.println("        MOSTRANDO LIMOSINAS DISPONIBLES");
		System.out.println("################################################");

		for (int i=0; i < lista.size(); i++) {
			if(!lista.get(i).getReservada().booleanValue()) {
				imprimeInfoLimo(i);
				enc++;
			}
		}
		if(enc != 0) {
			System.out.println("");
			System.out.println(prompt+"SE HAN ENCONTRADO "+enc+" LIMOSINAS");
		}else {
			System.out.println(prompt+"LOSENTIMOS NO TENEMOS NINGUNA LIMOSINA RESERVADA AHORA MISMO");
		}
	}
	
	//Metodo para reservar una limosina seleccionando por numeros
	public void reservarLimosina() throws ParseException {
		int cont;
		int opc;
		for (cont=0; cont < lista.size(); cont++) {
			imprimeInfoLimo(cont);
		}
		System.out.println("");
		System.out.println(prompt+"Seleccione un numero de limosina: ");
		opc = teclado.nextInt();
		
		//Mientras que opc sea un numero de limosina selecciona una.
		if(opc > 0 && opc <= cont) {
			if(!lista.get(opc-1).getReservada().booleanValue()) {
				lista.get(opc-1).setDias(devolverDias());
				lista.get(opc-1).setReservada(true);
				System.out.println(prompt+"Reserva realizada, para consultar, modficar o borrar su reserva, porfavor anote el numero de matricula de la limosina --> "+lista.get(opc-1).getMatricula());
			}else {
				System.out.println(prompt+"Esta limosina no esta reservada, porfavor seleccione otra reservada");
				reservarLimosina();
			}
		}else{
			System.out.println(prompt+"No hay ninguna limosina con ese numero, seleccione una de las que se muestra!");
			reservarLimosina();
		}
	}
	
	//Metodo para consultar reservas realizadas
	public int consultarReserva() {
		int nLimo = 0;
		boolean enc = false;
		System.out.println(prompt+"Introduce la matricula de su limosina reservada");
		String matricula = teclado.next();
		for (int i=0; i < lista.size() && !enc; i++) {
			if(lista.get(i).getMatricula().contains(matricula)) {
				System.out.println(prompt+"Se ha encontrado una limosina con esa matricula, comprobando si esta reservada...");
				if(lista.get(i).getReservada().booleanValue()) {
					imprimeInfoLimo(i);
					nLimo = i;
					enc = true;
				}
			}
		}
		if (!enc) System.out.println(prompt+"No se ha encontrado ninguna limosina reservada con esa matricula!");
		return nLimo;
	}
	
	//Metodo para modificar los dias reservados de una reserva
	public void modificarReserva() {
		int limo = consultarReserva();
		String opc;
		if(limo != 0) {
			System.out.println(prompt+"¿Desea modificar los días de la reserva?");
			opc = teclado.next();
			opc = opc.toLowerCase();
			switch(opc) {
				case "si": 
				case "sí":
					lista.get(limo).setDias(devolverDias());
					break;
				case "no":
					System.out.println(prompt+"Volviendo...");
					break;
				default: System.out.println(prompt+"Porfavor introduzca SI / NO");
			}
		}else{
			System.out.println(prompt+"No se ha encontrado ninguna reserva de ninguna limosina con esa matricula");
		}
	}
	
	//Metodo para eleminar una reserva y volver a poner disponible una limosina
	public void eleminarReserva() {
		int limo = consultarReserva();
		if(limo != 0) {
			lista.get(limo).setDias(0);
			lista.get(limo).setReservada(false);
			System.out.println(prompt+"Reserva borrada con exito!");
		}else{
			System.out.println(prompt+"No se ha encontrado ninguna reserva de ninguna limosina con esa matricula");
		}
	}
	
	
	//Metodo para comprobar los dias introducidos por teclado y devolver un numero entre 1 y 15
	private int devolverDias() {
		boolean diasCorrecto = false;
		int dias = 0;
		while(!diasCorrecto) {
			System.out.println("");
			System.out.println(prompt+"Introduce cuantos días quieres reservar [MAX 15]");
		    try
		    {
		    	teclado = new Scanner(System.in);
		    	dias = teclado.nextInt();
		    	if(dias > 0 && dias <= 15) {
		    		diasCorrecto = true;
		    	}
		    }
		    catch (InputMismatchException e)
		    {
		        System.out.println(prompt+"solo se admiten numeros!");
		    }
		}
		return dias;
	}
	
	//Metodo para imprimir la informacion de una limosina
	private void imprimeInfoLimo(int limo) {
		System.out.println("LIMOSINA --> "+lista.get(limo).getMatricula());
		System.out.println(" "+" "+"MODELO --> "+lista.get(limo).getModelo());
		System.out.println(" "+" "+"FLOTA --> "+lista.get(limo).getGrupoFlota());
		System.out.println(" "+" "+"RESERVADA --> "+lista.get(limo).getReservada());
		System.out.println(" "+" "+"DIAS --> "+lista.get(limo).getDias());
	}
}

