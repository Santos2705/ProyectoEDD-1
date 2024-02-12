/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosantiago.pkg1;

public class NodoCiudad {
    private NodoCiudad pNext;
    private int cantidad_feromonas;
    private String titulo;
    private int distancia;
    
    public NodoCiudad(String name){
        this.pNext = null;
        this.cantidad_feromonas = 0;
        this.titulo = name;
        this.distancia = 0;
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
    public int getCantidad_feromonas() {
        return cantidad_feromonas;
    }

    /**
     * @param cantidad_feromonas the cantidad_feromonas to set
     */
    public void setCantidad_feromonas(int cantidad_feromonas) {
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
    public int getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
}
