package ar.edu.unju.edm.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.util.Date;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Table (name="clientes")
@Component
public class Cliente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer idCliente;
	@Column
	@NotBlank(message="Ingrese Nombre y Apellido")
	private String nombreApellido;
	@Column
	@Min(value=10000000, message="Debe ser Mayor a 10000000" )
	@Max(value=99999999, message="Debe ser Menor a 99999999")
	private int nroDocumento;
	@Column
	private String tipoDocumento;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaUltimaCompra;	
	@Column
	private int codigoAreaTelefono;
    @Column
	private int numTelefono;
	@Column
	private String  numTelefonoCompleto;
	
	@OneToMany(mappedBy ="cliente", cascade = CascadeType.ALL)
	private List<Venta> ventas = new ArrayList<Venta>();
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getCodigoAreaTelefono() {
		return codigoAreaTelefono;
	}

	public void setCodigoAreaTelefono(int codigoAreaTelefono) {
		this.codigoAreaTelefono = codigoAreaTelefono;
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public int getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(int numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getNumTelefonoCompleto() {
		return numTelefonoCompleto;
	}

	public void setNumTelefonoCompleto(String numTelefonoCompleto) {
		this.numTelefonoCompleto = numTelefonoCompleto;
	}

	public int getNroTelefono() {
		return numTelefono;
	}

	public void setNroTelefono(int nroTelefono) {
		this.numTelefono = nroTelefono;
	}

	public LocalDate getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}

	public int getEdad() {
		int edad=0;
		LocalDate hoy = LocalDate.now();
		Period periodo = Period.between(fechaNacimiento, hoy);
		edad = periodo.getYears();
		return edad;
	}
	
	public String getDatoAdicional() {
		String datos;
		datos = getTiempoDesdeUltCompraHastaHoy() + " " + getDiasDesdeNacimHastaHoy() + " " + getTCumple();
		return datos;
	}
	
	public String getTiempoDesdeUltCompraHastaHoy() {
		LocalDate hoy = LocalDate.now();
		Period periodo = Period.between(fechaUltimaCompra, hoy);
		return "TUC:" + String.valueOf(periodo.getYears()) + 
							"-" + String.valueOf(periodo.getMonths()) + 
									"-" + String.valueOf(periodo.getDays());		 	
	}
	
	public String getDiasDesdeNacimHastaHoy() {
		LocalDate hoy = LocalDate.now();
		long dias = ChronoUnit.DAYS.between(fechaNacimiento, hoy);
		return "TFN:" + String.valueOf(dias);
			
	}
	
	public String getTCumple() {
		LocalDate hoy = LocalDate.now();
		LocalDate Nacim = fechaNacimiento;
		LocalDate proximocumple = LocalDate.of(hoy.getYear(),Nacim.getMonth(),Nacim.getDayOfMonth());
		if(proximocumple.isBefore(hoy)) {
			proximocumple = LocalDate.of(proximocumple.getYear()+1, proximocumple.getMonth(), proximocumple.getDayOfMonth());
		}
		Period periodo = Period.between(hoy,proximocumple);
		
		LocalDateTime ahora = LocalDateTime.now();
		LocalDateTime cumpleReciente = LocalDateTime.of(proximocumple.getYear(),			
														proximocumple.getMonthValue(),		
														proximocumple.getDayOfMonth(),		
														0,0,0);		
        Duration duracion = Duration.between(ahora, cumpleReciente);
		
		return "TC:" + periodo.getDays() + "-" + periodo.getMonths() + "-" +
							periodo.getYears() + "-" + duracion.toHoursPart() + "-" +
								duracion.toMinutesPart() + "-" + duracion.toSecondsPart();
	}
}
