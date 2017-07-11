
public class Empleado {
	
	private int Id;
	private String Nombre;
	private String Apellido;
	private String email;
	
	public Empleado(int id, String nombre, String apellido, String email) {
		super();
		Id = id;
		Nombre = nombre;
		Apellido = apellido;
		this.email = email;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//Usar Source/GenerateGetters and Setters para generar esto
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	
	public Empleado(int id) {
		super();
		Id = id;
	}

	//Usar Source/GenerateGetters and Setters para generar esto
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	//Usar Source/Generate Constructor Using Fields para generar esto
	public Empleado(String nombre) {
		super();
		Nombre = nombre;
	}

	public Empleado(int id, String nombre) {
		super();
		Id = id;
		Nombre = nombre;
	}
	
}
