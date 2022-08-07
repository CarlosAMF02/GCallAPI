package br.com.gcall.atendente.models;

public class AtendenteChamadoVM {
    private long atendenteId;
    private long chamadoId;

    public long getAtendenteId() {
        return atendenteId;
    }

    public void setAtendenteId(long atendenteId) {
        this.atendenteId = atendenteId;
    }

    public long getChamadoId() {
        return chamadoId;
    }

    public void setChamadoId(long chamadoId) {
        this.chamadoId = chamadoId;
    }
}
