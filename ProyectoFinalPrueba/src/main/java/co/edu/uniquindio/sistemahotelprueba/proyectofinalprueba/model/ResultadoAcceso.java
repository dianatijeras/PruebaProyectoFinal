package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.Resultado;

public class ResultadoAcceso {

    private final Resultado resultado;
    private final String motivoRechazo;

    private ResultadoAcceso(Resultado resultado, String motivoRechazo){
        this.resultado = resultado;
        this.motivoRechazo = motivoRechazo;
    }

    public static ResultadoAcceso autorizado(){
        return new ResultadoAcceso(Resultado.AUTORIZADO, null);
    }

    public static ResultadoAcceso denegado(String motivoRechazo){
        return new ResultadoAcceso(Resultado.DENEGADO, motivoRechazo);
    }

    public boolean esAutorizado(){
        return resultado == Resultado.AUTORIZADO;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    @Override
    public String toString() {
        return "ResultadoAcceso{" +
                "resultado=" + resultado +
                ", motivoRechazo='" + motivoRechazo + '\'' +
                '}';
    }
}
