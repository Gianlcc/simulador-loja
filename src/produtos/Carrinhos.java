package produtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Carrinhos {

    private float total = 0;
    private ArrayList<Produto> produto = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        //criando uma variavel para pegar o produto caso ele
        //esteja ja em nosso carrinho
        Produto _produto = null;

        //varrer o nosso carrinho procurando o produto
        for (Produto prod : this.produto) {
            // verificando se é o mesmo produto pelo nome
            // o ideal seria um id unico
            if (prod.getNome().equals(produto.getNome())) {
                // se o produto foi encontrado, adicionamos na variavel
                //e mandamos para o loop
                _produto = prod;
                continue;
            }
        }

        // se o produto for diferente de null
        //significa que ja esta adicionada ao nosso carrinho
        if (_produto != null) {
            //aumentamos o estoque do produto no carrinho
            _produto.setEstoque(_produto.getEstoque() + 1);
        } else //adicionamos o produto ao nosso carrinho
        {
            this.produto.add(produto);
        }
    }

    public void finalizarCompra(Scanner scanner) {
        this.listarProdutos();

        int formaDePagamento = 1;
        if (this.total >= 20) {
            formaDePagamento = this.getFormaPagamento(scanner);

        }
        String mensagem = "";
        if (formaDePagamento == 1) {
            float pago = this.getValorPago(scanner);
            float troco = this.total - pago;
            mensagem = "total \t R$" + total + "\n"
                    + "pago \t R$ " + pago + "\n"
                    + "troco \t R$ " + troco;

        } else {
            int numeroParcelas = this.getNumeroParcelas(scanner);
            float valorParcela = this.total / numeroParcelas;
            mensagem = "total \t R$" + total + "\n"
                    + "parcelas " + numeroParcelas + "x"
                    + "de R$ " + valorParcela;
        }

        System.out.println("informe o nome do cliente");

        String nomeCliente = scanner.nextLine().trim();
        this.imprimirRecibo(nomeCliente, mensagem);

    }

    public void imprimirRecibo(String nomeCliente, String mensagem) {
        String recibo = "--------------------------------------------\n" +
                this.getListagemProdutos() + "\n"
                + "\n"
                + "--------------------------------------------\n"
                + mensagem + "\n"
                + "cliente" + nomeCliente;

        System.out.println(recibo);

    }

    public float getValorPago(Scanner scanner) {

        try {
            System.out.println("digite o valor a ser entregue:");
            float valor = scanner.nextFloat();

            scanner.nextLine();

            if (valor < this.total) {
                System.out.println("Valor insuficiente para o pagamento");
                throw new Exception();

            }
            return valor;
        } catch (Exception e) {
            return this.getValorPago(scanner);
        }
    }

    public int getNumeroParcelas(Scanner scanner) {
        try {
            int numeroMaxParcelas = ((int) total) / 10;
            String mensagem = "escolha a quantidade de parcelas desejadas: \n "
                    + "1,2";
            System.out.println(mensagem);
            int numeroParcelas = scanner.nextInt();

            scanner.nextLine();

            if (numeroParcelas > 0 && numeroParcelas <= numeroMaxParcelas) {
                return numeroParcelas;
            }
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Digite um numero valido de parcelas");
            return this.getNumeroParcelas(scanner);
        }

    }

    public int getFormaPagamento(Scanner scanner) {
        try {
            System.out.println("Qual forma de pagamento? \n"
                    + "1 = ávista\n"
                    + "2 = parcelado\n");

            int digito = scanner.nextInt();
            
            // resetando scanner
            scanner.nextLine();
            
            if (digito == 1 || digito == 2) {
                return digito;

            }
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Método de pagamento não encontrado");
            return this.getFormaPagamento(scanner);
        }

    }

    private String getListagemProdutos() {

        System.out.println("para fazer ainda");

        List<String> linhas = new ArrayList();
        this.total = 0;

        for (Produto produto : this.produto) {
            String nome = produto.getNome();
            float preco = produto.getPreco();
            int quantidade = produto.getEstoque();
            float subtotal = preco * quantidade;
            this.total += subtotal;

            String mensagem = quantidade + "x " + nome + " \tR$" + preco + "\n"
                    + "\tsubtotal \tR$ " + subtotal;
            linhas.add(mensagem);

        }
        linhas.add("-----------------------------------------------------------");
        linhas.add("total: \t\t\tR$ " + this.total);

        return (String.join("\n", linhas));

    }

    public void listarProdutos() {
        System.out.println(this.getListagemProdutos());

    }

}
