package com.superdev.lojinha.controller;

import com.superdev.lojinha.model.Produto;
import com.superdev.lojinha.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;


    @GetMapping
    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> listarPorId(@PathVariable int id) {
        Produto produto = repository.findById(id);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
         Produto novoProduto = repository.create(produto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> alterar(@RequestBody Produto produto,@PathVariable int id) {
        Produto produtoExiste = repository.findById(id);
        if (produtoExiste == null) {
            return ResponseEntity.notFound().build();
        }

        // produtoExiste = repository.alter(produto);
        return ResponseEntity.ok(repository.alter(produto));
    }

//    private boolean validarProduto(int id) {
//        Produto produto = repository.findById(id);
//        if (produto == null) {
//            return false;
//        }
//        return true;
//    }


}
