package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

public class Sendero {

    private Atraccion origen;
    private Atraccion destino;
    private double peso;

    public Sendero(Atraccion origen, Atraccion destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public Atraccion getOrigen() {
        return origen;
    }

    public void setOrigen(Atraccion origen) {
        this.origen = origen;
    }

    public Atraccion getDestino() {
        return destino;
    }

    public void setDestino(Atraccion destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Sendero{" +
                "origen=" + origen +
                ", destino=" + destino +
                ", peso=" + peso +
                '}';
    }
}
