package produtos;

public enum Acoes {

    FINALIZAR_COMPRA("finalizar compra"),
    VERIFICAR_CARRINHO("verificar o carrinho");

    private String acao;

private Acoes (String acao) {
    this.acao = acao;
}

public String toString() {
    return this.acao;
}
}
