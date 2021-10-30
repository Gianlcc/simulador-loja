package produtos;

import java.util.Scanner;

public class Produtos {

    Carrinhos carrinhos = new Carrinhos();
    Partileira partileira = new Partileira();
    Scanner scanner = new Scanner(System.in);

    public Produtos() {
        System.out.println("seja bem vindo a nossa loja");
        System.out.println("--------------");
        boolean continuar = true;

        while (continuar) {
            System.out.println("digite o codigo do produto que deseja adicionar ao carrinho");
            System.out.println(this.getRodape());
            this.partileira.listarProdutos();

            String codigo = this.getDIgito();
            continuar = this.manipuleCodigo(codigo);
            
       
       }
        
        this.carrinhos.finalizarCompra(this.scanner);
    }

    public boolean manipuleCodigo(String codigo) {
        if (codigo.equals("c")) {
            this.carrinhos.listarProdutos();
            System.out.println("pressione enter para continuar");
            this.scanner.nextLine();
            return true;
        } else if (codigo.equals("f")) {
            return false;

        } else {
            int cod = Integer.parseInt(codigo);
            Produto produto = this.partileira.pegarProduto(cod);
            this.carrinhos.adicionarProduto(produto); 
            return true;
        }
    }

    private String getRodape() {
        return "atalhos c = " + Acoes.VERIFICAR_CARRINHO.toString()
                + "; f = " + Acoes.FINALIZAR_COMPRA.toString();

    }

    public String getDIgito() {
        try {
            String digito = this.scanner.nextLine();
            boolean digitoMapeado = digito.toLowerCase().equals("c")
                    || digito.toLowerCase().equals("f")
                    || this.partileira.possuiProdutoPorCodigo(
                            Integer.parseInt(digito)
                    );
            if (!digitoMapeado) {
                throw new Exception();
            }

            return digito.toLowerCase();
        } catch (Exception e) {
            System.out.println("digito não indentificado, por favor gigite novamente...");
            return this.getDIgito();
        }

    }

    public static void main(String[] args) {
        new Produtos();

    }

}
