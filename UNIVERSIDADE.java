public class Aluno {
    private String nome;
    private String ra;
    private double[] notas;
    private boolean ead; // true se EAD, false se presencial
    private double presenca; // percentual de presença (0 a 100)

    // Construtor para EAD
    public Aluno(String nome, String ra, double[] notas) {
        this.nome = nome;
        this.ra = ra;
        this.notas = notas;
        this.ead = true;
        this.presenca = 100; // Presença não é considerada no EAD
    }

    // Construtor para Presenciais
    public Aluno(String nome, String ra, double[] notas, double presenca) {
        this.nome = nome;
        this.ra = ra;
        this.notas = notas;
        this.ead = false;
        this.presenca = presenca;
    }

    public double calcularNotaFinal() {
        double notaFinal = 0;

        if (notas.length == 1 || notas.length == 2) {
            // Média Aritmética
            for (double nota : notas) {
                notaFinal += nota;
            }
            notaFinal /= notas.length;
        } else if (notas.length == 3) {
            // Média Ponderada
            notaFinal = (notas[0] * 1 + notas[1] * 2 + notas[2] * 4) / 7;
        } else if (notas.length == 4) {
            // Cálculo específico para 4 notas
            notaFinal = (notas[0] * 0.15) + (notas[1] * 0.30) + (notas[2] * 0.10) + (notas[3] * 0.45);
        }

        return notaFinal;
    }

    public String situacao() {
        double notaFinal = calcularNotaFinal();
        if (ead || presenca >= 75) {
            return notaFinal >= 5 ? "Aprovado" : "Reprovado";
        } else {
            return "Reprovado"; // Presença insuficiente
        }
    }

    public void imprimirDetalhes() {
        System.out.printf("Nome: %s%nRA: %s%nNota Final: %.2f%nSituação: %s%n", nome, ra, calcularNotaFinal(), situacao());
    }

    // Getters e Setters (se necessário)
    public String getNome() {
        return nome;
    }

    public String getRa() {
        return ra;
    }

    public double[] getNotas() {
        return notas;
    }

    public boolean isEAD() {
        return ead;
    }

    public double getPresenca() {
        return presenca;
    }
}

public class Main {
    public static void main(String[] args) {
        // Aluno EAD
        double[] notasEAD = {8.0, 7.5, 9.0};
        Aluno alunoEAD = new Aluno("Maria Silva", "123456", notasEAD);
        alunoEAD.imprimirDetalhes();

        // Aluno Presencial
        double[] notasPresencial = {6.0, 7.5, 8.0, 9.0};
        Aluno alunoPresencial = new Aluno("João Santos", "654321", notasPresencial, 80);
        alunoPresencial.imprimirDetalhes();
    }

}
