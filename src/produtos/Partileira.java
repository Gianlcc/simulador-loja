package produtos;

import java.util.ArrayList;
import java.util.List;

public class Partileira {

    List<Produto> produtos = new ArrayList();

    public Partileira() {
        this.produtos.add(new Produto(
                "Escova de dente",
                "Para escovar os dentes",
                3.99f,
                5
        ));
        this.produtos.add(new Produto(
                "Pasta de dentes",
                " Sabor menta",
                4.49f,
                4
        ));

        this.produtos.add(new Produto(
                "fio dental",
                "100m",
                9.48f,
                10
        ));

        this.produtos.add(new Produto(
                "Enxaguante bucal",
                "700ml",
                13.17f,
                8
        ));
        this.produtos.add(new Produto(
                "pasta de dentes white",
                "branqueador",
                17.71f,
                7
        ));
    }

    public void listarProdutos() {
        String mensagem = "|---------------------------------\n";
        for (int index = 0; index < this.produtos.size(); index++) {

            if (this.produtos.get(index).getEstoque() > 0) {
                mensagem += " | Cod: " + index + " | "
                        + "R$ " + this.produtos.get(index).getPreco() + "|"
                        + "Produto: " + this.produtos.get(index).getNome() + "\n";

            }
        }
        mensagem += "|----------------------------------";
        System.out.println(mensagem);
    }

    /**
     * pegamos o produto da partileira e diminuimos o estoque
     *
     * @param cod
     * @return
     */
    public Produto pegarProduto(int cod) {
        Produto produto = this.produtos.get(cod);
        produto.setEstoque(produto.getEstoque() - 1);

        return new Produto(
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                1
        );
    }

    public boolean possuiProduto() {
        boolean possui = false;
        for (Produto produto : this.produtos) {
            if (possui == false) {
                possui = produto.getEstoque() > 0;
            }
        }

        return possui;
    }

    public boolean possuiProdutoPorCodigo(int cod) {
        try {
            return this.produtos.get(cod).getEstoque() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
