/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartwatchsim.sync;

public class TrainingData {
    
    private int id;
    private long tiempo;
    private int promedioHR;
    private String fecha;
    private double promedioVel;
    private double distancia;

    public int getId() {
        return id;
    }

    public long getTiempo() {
        return tiempo;
    }

    public int getPromedioHR() {
        return promedioHR;
    }

    public String getFecha() {
        return fecha;
    }

    public double getPromedioVel() {
        return promedioVel;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public void setPromedioHR(int promedioHR) {
        this.promedioHR = promedioHR;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPromedioVel(double promedioVel) {
        this.promedioVel = promedioVel;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return this.fecha + " - " + this.tiempo;
    }
}
