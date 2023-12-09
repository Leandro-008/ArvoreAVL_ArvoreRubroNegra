
public class ArvoreRubroNegra {
    private NoRubroNegro raiz;
    private static final boolean VERMELHO = true;
    private static final boolean PRETO = false;

    private boolean ehVermelho(NoRubroNegro no) {
        if (no == null) {
            return false;
        }
        return no.cor == VERMELHO;
    }

    private NoRubroNegro rotacaoEsquerda(NoRubroNegro h) {
        NoRubroNegro x = h.direita;
        h.direita = x.esquerda;
        x.esquerda = h;
        x.cor = h.cor;
        h.cor = VERMELHO;
        return x;
    }

    private NoRubroNegro rotacaoDireita(NoRubroNegro h) {
        NoRubroNegro x = h.esquerda;
        h.esquerda = x.direita;
        x.direita = h;
        x.cor = h.cor;
        h.cor = VERMELHO;
        return x;
    }

    private void inverterCores(NoRubroNegro h) {
        h.cor = !h.cor;
        h.esquerda.cor = !h.esquerda.cor;
        h.direita.cor = !h.direita.cor;
    }

    public void remover(int valor) {
        // Implemente o método de remoção aqui...
        raiz = remover(raiz, valor);
        if (raiz != null) {
            raiz.cor = PRETO;
        }
    }

    private NoRubroNegro remover(NoRubroNegro h, int valor) {
        // Implemente a remoção aqui...
        return null;
    }

    public void imprimir() {
        imprimir(raiz, 0);
    }

    private void imprimir(NoRubroNegro no, int nivel) {
        if (no != null) {
            imprimir(no.direita, nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("   ");
            }
            System.out.println(no.valor + (no.cor ? " (Vermelho)" : " (Preto)"));
            imprimir(no.esquerda, nivel + 1);
        }
    }

    public int contar(int valor) {
        return contar(raiz, valor);
    }

    private int contar(NoRubroNegro no, int valor) {
        if (no == null) {
            return 0;
        }

        int cmp = Integer.compare(valor, no.valor);
        if (cmp < 0) {
            return contar(no.esquerda, valor);
        } else if (cmp > 0) {
            return contar(no.direita, valor);
        } else {
            return 1 + contar(no.esquerda, valor) + contar(no.direita, valor);
        }
    }

    // Método principal inserir
    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
        raiz.cor = PRETO; // A raiz sempre deve ser preta
    }

    private NoRubroNegro inserir(NoRubroNegro no, int valor) {
        if (no == null) {
            return new NoRubroNegro(valor, VERMELHO);
        }

        if (valor < no.valor) {
            no.esquerda = inserir(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserir(no.direita, valor);
        } else {
            // Valor já existe, não fazemos nada
            return no;
        }

        // Correções após inserção
        if (ehVermelho(no.direita) && !ehVermelho(no.esquerda)) {
            no = rotacaoEsquerda(no);
        }
        if (ehVermelho(no.esquerda) && ehVermelho(no.esquerda.esquerda)) {
            no = rotacaoDireita(no);
        }
        if (ehVermelho(no.esquerda) && ehVermelho(no.direita)) {
            inverterCores(no);
        }

        return no;
    }

}
