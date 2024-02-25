/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosantiago.pkg1;

public class NodoCiudad {
    private NodoCiudad pNext;
    private double cantidad_feromonas;
    private String titulo;
    private double distancia;
    public double sumatoria;
    
    public NodoCiudad(String name){
        this.pNext = null;
        this.cantidad_feromonas = 0;
        this.titulo = name;
        this.distancia = 0;
        this.sumatoria = 0;
    }

    /**
     * @return the pNext
     */
    public NodoCiudad getpNext() {
        return pNext;
    }

    /**
     * @param pNext the pNext to set
     */
    public void setpNext(NodoCiudad pNext) {
        this.pNext = pNext;
    }

    /**
     * @return the cantidad_feromonas
     */
    public double getCantidad_feromonas() {
        return cantidad_feromonas;
    }

    /**
     * @param cantidad_feromonas the cantidad_feromonas to set
     */
    public void setCantidad_feromonas(double cantidad_feromonas) {
        this.cantidad_feromonas = cantidad_feromonas;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the distancia
     */
    public double getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
}
