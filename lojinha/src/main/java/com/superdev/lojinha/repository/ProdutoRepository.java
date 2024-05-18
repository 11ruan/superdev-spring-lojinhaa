package com.superdev.lojinha.repository;

import com.superdev.lojinha.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoRepository {

    List<Produto> listaProdutos = new ArrayList<>();
    int idSequencia = 0;

    // findAll
    public List<Produto> findAll() {
        return this.listaProdutos;
    }

    // findBy
    public Produto findById(int id) {
        Produto produtoEncontrado = new Produto();
        this.listaProdutos.forEach(produto -> {
            if (produto.getId() == id) {
                produtoEncontrado.setId(produto.getId());
                produtoEncontrado.setNome(produto.getNome());
                produtoEncontrado.setPreco(produto.getPreco());
                produtoEncontrado.setStatus(produto.isStatus());
            }
        });
        return produtoEncontrado;
    };

    // create
    public Produto create(Produto produto) {
        produto.setId(this.getIdSequencia());
        this.listaProdutos.add(produto);
        return produto;
    }

    // alter

    public Produto alter(Produto produto) {
        Produto produtoEncontrado = this.findById(produto.getId());

        if (produtoEncontrado.getId() == 0) {
            return null;
        }

        this.listaProdutos.remove(produtoEncontrado);
        this.listaProdutos.add(produto);

        return produto;
    }

    // delete

    public Produto delete(int id) {
        Produto produtoEncontrado = this.findById(id);

        if (produtoEncontrado.getId() == 0) {
            return null;
        }

        this.listaProdutos.remove(produtoEncontrado);
        return produtoEncontrado;
    }

    private int getIdSequencia() {
        this.idSequencia++;
        return this.idSequencia;
    }


}
