package org.alumno.kalo.kalo_primera_app_spring_mvc.model;

public class Pagina {

	String titulo;
	String paginaActiva;
	
	public Pagina(String titulo, String paginaActiva) {
		this.titulo = titulo;
		this.paginaActiva = paginaActiva;
	}
	
	public String getTitulo() {return titulo;}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;}
	
	public String getPaginaActiva() {
		return paginaActiva;}
	
	public void setPaginaActiva(String paginaActiva) {
		this.paginaActiva = paginaActiva;
	}
	
	public String getStrBootstrapActiva(String pagina) {
		
		return paginaActiva.equals(pagina) ? "active" : "";
		
		
	}
	
	
}
