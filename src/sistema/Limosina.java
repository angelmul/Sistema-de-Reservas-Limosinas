package sistema;

//Clase contenedora de limosina
public class Limosina {

	//Atributos de la limosina
	private String matricula;
	private String modelo;
	private int grupoFlota;
	private boolean reservada = false;
	private int dias = 0;
	
	//Constructor de la clase
	public Limosina(String matricula, String modelo, int grupoFlota) {
		this.matricula = matricula;
		this.modelo = modelo;
		this.grupoFlota = grupoFlota;
	}
	
	//Metodos gets de la clase
	public String getMatricula() {
		return matricula;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public int getGrupoFlota() {
		return grupoFlota;
	}
	
	public Boolean getReservada() {
		return reservada;
	}
	
	public int getDias() {
		return dias;
	}
	
	//Metodos sets de la clase
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public void setGrupoFlota(int grupoFlota) {
		this.grupoFlota = grupoFlota;
	}
	
	public void setReservada(boolean reservada) {
		this.reservada = reservada;
	}
	
	public void setDias(int dias) {
		this.dias = dias;
	}
	
}
