/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosantiago.pkg1;

import javax.swing.JOptionPane;

public class Grafo {
    private ListaCiudades[] vertices_ciudades;
    private int max_ciudades;
    private int ciudades_actuales;
    
    
    public Grafo(int tamano){
        this.max_ciudades = tamano;
        this.ciudades_actuales = 0;
        this.vertices_ciudades= new ListaCiudades[tamano];
        this.inicializar(this.vertices_ciudades);
    }
    
    public void añadir_Ciudad(String titulo){
        int pos = this.buscar_posicion();
        if(pos!= -1){
            this.vertices_ciudades[pos].getpFirst().setTitulo(titulo);
        }else{
            ListaCiudades[] nueva = new ListaCiudades[getMax_ciudades() + getMax_ciudades()/2];
            this.inicializar(nueva);
            for (int i = 0; i < this.getMax_ciudades(); i++) {
                nueva[i] = this.getVertices_ciudades()[i];
            }
            nueva[max_ciudades].getpFirst().setTitulo(titulo);
            this.setVertices_ciudades(nueva);
        }
    }
    
    public void eliminar_Ciudad(String titulo){
        for (int i = 0; i < getMax_ciudades(); i++) {
            if(this.getVertices_ciudades()[i].getpFirst().getTitulo().equals(titulo)){
                this.vertices_ciudades[i].getpFirst().setTitulo("");
                this.vertices_ciudades[i].getpFirst().setpNext(null);
            }
            this.getVertices_ciudades()[i].eliminar_Arista(titulo);
            
        }
    }
    
    
    public ListaCiudades buscar(String titulo){
        for (int i = 0; i < this.getMax_ciudades(); i++) {
            if(this.getVertices_ciudades()[i].getpFirst().getTitulo().equals(titulo)){
                return this.getVertices_ciudades()[i];
            }
        }
        return null;
    }
    public void crear_Camino(String nombre, String nombre2, int distancia){
        ListaCiudades ciudad1 = this.buscar(nombre);
        ListaCiudades ciudad2 = this.buscar(nombre2);
        
        if(ciudad1 != null && ciudad2 != null){
            ciudad1.insertar_Arista(nombre, distancia);
            ciudad2.insertar_Arista(nombre2, distancia);
        }else{
            JOptionPane.showMessageDialog(null, "LA CIUDAD NO EXISTE");
        }
    }
    
    public void inicializar(ListaCiudades[] lista){
        for (int i = 0; i < lista.length; i++) {
            lista[i].setpFirst(new NodoCiudad(""));
        }
    }
    
    public int buscar_posicion(){
        for (int i = 0; i < this.getMax_ciudades(); i++) {
            if(this.getVertices_ciudades()[i].getpFirst().getTitulo().equals("")){
                return i;
            }
        }
        return -1;
    }

    /**
     * @return the vertices_ciudades
     */
    public ListaCiudades[] getVertices_ciudades() {
        return vertices_ciudades;
    }

    /**
     * @param vertices_ciudades the vertices_ciudades to set
     */
    public void setVertices_ciudades(ListaCiudades[] vertices_ciudades) {
        this.vertices_ciudades = vertices_ciudades;
    }

    /**
     * @return the max_ciudades
     */
    public int getMax_ciudades() {
        return max_ciudades;
    }

    /**
     * @param max_ciudades the max_ciudades to set
     */
    public void setMax_ciudades(int max_ciudades) {
        this.max_ciudades = max_ciudades;
    }

    /**
     * @return the ciudades_actuales
     */
    public int getCiudades_actuales() {
        return ciudades_actuales;
    }

    /**
     * @param ciudades_actuales the ciudades_actuales to set
     */
    public void setCiudades_actuales(int ciudades_actuales) {
        this.ciudades_actuales = ciudades_actuales;
    }
    
}
