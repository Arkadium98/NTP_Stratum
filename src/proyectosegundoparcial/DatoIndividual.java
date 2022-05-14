/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosegundoparcial;


public class DatoIndividual {
     public int Strat,Prec,Delay;
     public String Nombre;

    public DatoIndividual(String nombre,int stratum,int precision,int delay) {
        Strat = stratum;
        Prec = precision;
        Delay = delay;
        Nombre = nombre;
    }
}
