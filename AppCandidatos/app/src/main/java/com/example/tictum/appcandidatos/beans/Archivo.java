package beans;

public class Archivo {
	
	private int idArchivo;
	private String nombreArchivo;
	private String rutaArchivo;
	
	public Archivo() {
		super();
	}

	public Archivo(int idArchivo, String nombreArchivo, String rutaArchivo) {
		super();
		this.idArchivo = idArchivo;
		this.nombreArchivo = nombreArchivo;
		this.rutaArchivo = rutaArchivo;
	}

	public int getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(int idArchivo) {
		this.idArchivo = idArchivo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
	
	
	
	
	
	

}
