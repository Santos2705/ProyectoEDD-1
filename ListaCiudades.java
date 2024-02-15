/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosantiago.pkg1;

public class ListaCiudades {
    private NodoCiudad pFirst;
    
    public ListaCiudades(){
        this.pFirst = null;
    }
    
    
    public void insertar_Arista(String titulo, int distancia){
        NodoCiudad nuevo = new NodoCiudad(titulo);
        nuevo.setDistancia(distancia);
        if(this.getpFirst() != null){
            NodoCiudad aux = this.getpFirst();
            while(aux.getpNext() != null){
                aux = aux.getpNext();
            }
            aux.setpNext(nuevo);
        }
    }
    
    public void eliminar_Arista(String titulo){
        NodoCiudad aux = this.getpFirst();
        if(this.getpFirst() != null){
            while(aux.getpNext() != null && !aux.getpNext().getTitulo().equals(titulo)){
                aux = aux.getpNext();
            }
            if(aux.getpNext() != null){
                aux.setpNext(aux.getpNext().getpNext());
            }
        }
    }
    
    public String imprimir_ciudades(){
        NodoCiudad aux = this.getpFirst().getpNext();
        String adyacentes = "La ciudad " + this.getpFirst().getTitulo() + " tiene como adyacentes a las ciudades: \n";
        while(aux!= null){
            adyacentes += aux.getTitulo() + "\n";
            aux = aux.getpNext();
        }
        return adyacentes;
    }
    
    public NodoCiudad buscar_mayor(){
        NodoCiudad aux = this.getpFirst();
        NodoCiudad optima = aux;
        while(aux!= null){
            if(aux.getCantidad_feromonas() > optima.getCantidad_feromonas()){
                optima = aux;
            }
            aux = aux.getpNext();
        }
        return optima;
    }
    
    public NodoCiudad buscar(String titulo){
        NodoCiudad aux = this.getpFirst();
        while(aux != null && !aux.getTitulo().equals(titulo)){
            aux = aux.getpNext();
        }
        return aux;
    }

    /**
     * @return the pFirst
     */
    public NodoCiudad getpFirst() {
        return pFirst;
    }

    /**
     * @param pFirst the pFirst to set
     */
    public void setpFirst(NodoCiudad pFirst) {
        this.pFirst = pFirst;
    }
}
