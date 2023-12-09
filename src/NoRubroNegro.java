public class NoRubroNegro {
    public static final boolean VERMELHO = true;
    public static final boolean PRETO = false;

    int valor;
    NoRubroNegro esquerda, direita;
    boolean cor;

    public NoRubroNegro(int valor, boolean cor) {
        this.valor = valor;
        this.cor = cor;
    }
}