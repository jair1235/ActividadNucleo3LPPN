package co.edu.poli.cajeroapp.entities;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {
	@Id
	private long documento;
	private String nombre;
	private BigDecimal saldo;
	private String clave;
	@Column(name="intentos_fallidos")
	private Integer intentosFallidos;
	private boolean bloqueado;
	@Column(name="ultima_conexion")
	private LocalDateTime ultimaConexion;
	public Cliente() {
	}
	public long getDocumento() {
		return documento;
	}
	public void setDocumento(long documento) {
		this.documento = documento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Integer getIntentosFallidos() {
		return intentosFallidos;
	}
	public void setIntentosFallidos(Integer intentosFallidos) {
		this.intentosFallidos = intentosFallidos;
	}
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	public LocalDateTime getUltimaConexion() {
		return ultimaConexion;
	}
	public void setUltimaConexion(LocalDateTime ultimaConexion) {
		this.ultimaConexion = ultimaConexion;
	}
	
}
