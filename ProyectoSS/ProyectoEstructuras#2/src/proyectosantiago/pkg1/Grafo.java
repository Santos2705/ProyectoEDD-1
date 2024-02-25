/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosantiago.pkg1;

import java.util.Random;
import javax.swing.JOptionPane;

public class Grafo {

    private ListaCiudades[] vertices_ciudades;
    private int max_ciudades;
    private int ciudades_actuales;
    int primero;
    int ultimo;
    Hormiga menor;

    public Grafo(int tamano) {
        this.max_ciudades = tamano;
        this.ciudades_actuales = 0;
        this.vertices_ciudades = new ListaCiudades[tamano];
        this.inicializar(this.vertices_ciudades);
        this.primero = 0;
        this.ultimo = 1;
        this.menor = null;
    }

    public void añadir_Ciudad(String titulo) {
        int pos = this.buscar_posicion();
        if (pos != -1) {
            if (this.buscar(titulo) == null) {
                this.vertices_ciudades[pos].setpFirst(new NodoCiudad(titulo));
                ciudades_actuales++;
            }
        } else {
            ListaCiudades[] nueva = new ListaCiudades[getMax_ciudades() + getMax_ciudades() / 2];
            this.inicializar(nueva);
            for (int i = 0; i < this.getMax_ciudades(); i++) {
                nueva[i] = this.getVertices_ciudades()[i];
            }
            nueva[max_ciudades].setpFirst(new NodoCiudad(titulo));
            this.setVertices_ciudades(nueva);
            this.max_ciudades += max_ciudades / 2;
            ciudades_actuales++;
        }
    }

    public void eliminar_Ciudad(String titulo) {
        for (int i = 0; i < getMax_ciudades(); i++) {
            if (this.getVertices_ciudades()[i].getpFirst() != null && this.getVertices_ciudades()[i].getpFirst().getTitulo().equals(titulo)) {
                this.vertices_ciudades[i].setpFirst(null);
            }
            this.getVertices_ciudades()[i].eliminar_Arista(titulo);

        }
    }

    public ListaCiudades buscar(String titulo) {
        for (int i = 0; i < this.getMax_ciudades(); i++) {
            if (this.getVertices_ciudades()[i].getpFirst() != null && this.getVertices_ciudades()[i].getpFirst().getTitulo().equals(titulo)) {
                return this.getVertices_ciudades()[i];
            }
        }
        return null;
    }

    public void crear_Camino(String nombre, String nombre2, double distancia) {
        ListaCiudades ciudad1 = this.buscar(nombre);
        ListaCiudades ciudad2 = this.buscar(nombre2);

        if (ciudad1 != null && ciudad2 != null) {
            ciudad1.insertar_Arista(nombre2, distancia);
            ciudad2.insertar_Arista(nombre, distancia);
        } else {
            JOptionPane.showMessageDialog(null, "LA CIUDAD NO EXISTE");
        }
    }

    public void inicializar(ListaCiudades[] lista) {
        for (int i = 0; i < lista.length; i++) {
            lista[i] = new ListaCiudades();
        }
    }

    public int buscar_posicion() {
        for (int i = 0; i < this.getMax_ciudades(); i++) {
            if (this.getVertices_ciudades()[i].getpFirst() == null) {
                return i;
            }
        }
        return -1;
    }

    public void iniciarFeromonas() {
        for (int i = 0; i < this.max_ciudades; i++) {
            if (this.vertices_ciudades[i].getpFirst() != null) {
                this.vertices_ciudades[i].iniciarFeromonas(this.ciudades_actuales);
            }
        }
    }

    public double calcularSumatoria(int pos, boolean[] visitados, double alpha, double beta) {
        NodoCiudad aux = this.vertices_ciudades[pos].getpFirst().getpNext();
        double resultado = 0;
        for (int i = 0; i < this.max_ciudades; i++) {
            if (this.vertices_ciudades[i].getpFirst() != null) {
                NodoCiudad actual = this.vertices_ciudades[pos].buscar(this.vertices_ciudades[i].getpFirst().getTitulo());
                if ((!visitados[i]) && (this.vertices_ciudades[i].getpFirst() != null) && (actual != null)) {
//                double a = this.calcularCiudadesDisp(pos, visitados); 
                    double a = actual.getCantidad_feromonas();
                    double b = actual.getDistancia();
                    resultado += Math.pow((a), alpha) * Math.pow(1 / b, beta);
                }
            }
        }
        return resultado;
    }

    public int calcularCiudadesDisp(int pos, boolean[] visitados) {
        NodoCiudad aux = this.vertices_ciudades[pos].getpFirst().getpNext();
        int disponibles = 0;
        for (int i = 0; i < this.max_ciudades; i++) {
            if ((!visitados[i]) && (this.vertices_ciudades[i].getpFirst() != null) && (this.vertices_ciudades[pos].buscar(this.vertices_ciudades[i].getpFirst().getTitulo()) != null)) {
                disponibles++;
            }
        }
        return disponibles;
    }

    public double calcularProbabilidad(int pos, boolean[] visitados, double random, NodoCiudad aux, double acumulado, double alpha, double beta) {

        double a = aux.getCantidad_feromonas();
        double b = aux.getDistancia();
        double sumatoria = this.calcularSumatoria(pos, visitados, alpha, beta);

//        System.out.println("a " + a + "\nb " + b + "\nsumatoria " + sumatoria);
        acumulado += ((Math.pow((a), alpha)) * (Math.pow(1 / b, beta))) / sumatoria;

        return acumulado;

    }

    public void recorrerProfundidad(Hormiga h, boolean[] visitados, int pos, double alpha, double beta) {
        h.visitados += this.vertices_ciudades[pos].getpFirst().getTitulo() + ",";
        if (pos != ultimo) {
            visitados[pos] = true;
            double visitar = 0;
            Random rand = new Random();
            float random = rand.nextFloat();

//            System.out.println("NUMERO RANDOM : " + random);
            for (int i = 0; i < this.max_ciudades; i++) {
                if (this.vertices_ciudades[i].getpFirst() != null) {
                    NodoCiudad actual = this.vertices_ciudades[pos].buscar(this.vertices_ciudades[i].getpFirst().getTitulo());
                    if ((pos != i) && (!visitados[i]) && (actual != null)) {
                        visitar = calcularProbabilidad(pos, visitados, random, actual, visitar, alpha, beta);
                    }
//                System.out.println("ACUMULADO: " + visitar);
                    if (visitar > random) {
                        h.recorrido += this.vertices_ciudades[pos].buscar(this.vertices_ciudades[i].getpFirst().getTitulo()).getDistancia();
                        recorrerProfundidad(h, visitados, i, alpha, beta);
                        break;
                    }
                }

            }
        }
    }

    public void recorrer(Hormiga h, double alpha, double beta) {
        boolean[] visitados = new boolean[this.max_ciudades];
        for (int i = 0; i < this.max_ciudades; i++) {
            visitados[i] = false;
        }
        this.recorrerProfundidad(h, visitados, this.primero, alpha, beta);
        if (this.menor == null) {
            this.menor = h;
        } else if (this.menor.recorrido > h.recorrido) {
            this.menor = h;
        }
        this.actualizarFeromonas(h);
    }

    public void actualizarFeromonas(Hormiga h) {
        String[] visitados = h.visitados.split(",");
        for (int i = 0; i < visitados.length; i++) {
            if (i + 1 < visitados.length) {
                this.buscar(visitados[i]).buscar(visitados[i + 1]).sumatoria += 1 / h.recorrido;
                this.buscar(visitados[i + 1]).buscar(visitados[i]).sumatoria += 1 / h.recorrido;
            }
        }
    }

    public String actualizarPi(double eva) {
        String res = "";
        for (int i = 0; i < this.max_ciudades; i++) {
            if (this.vertices_ciudades[i].getpFirst() != null) {
                res += this.vertices_ciudades[i].actualizarPi(eva);
            }
        }
        return res;
    }

    public String imprimir() {
        String a = "";
        for (int i = 0; i < this.max_ciudades; i++) {
            if (this.vertices_ciudades[i].getpFirst() != null) {
                a += this.vertices_ciudades[i].getpFirst().getTitulo() + "   ---->  ";
                a += this.vertices_ciudades[i].mostrar();
                a += "\n";
            }
        }
        return a;
    }

    public void definirUltimo(String titulo) {
        for (int i = 0; i < this.getMax_ciudades(); i++) {
            if (this.getVertices_ciudades()[i].getpFirst() != null && this.getVertices_ciudades()[i].getpFirst().getTitulo().equals(titulo)) {
                this.ultimo = i;
            }
        }

    }

    public void definirPrimero(String titulo) {
        for (int i = 0; i < this.getMax_ciudades(); i++) {
            if (this.getVertices_ciudades()[i].getpFirst() != null && this.getVertices_ciudades()[i].getpFirst().getTitulo().equals(titulo)) {
                this.primero = i;
            }
        }

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
