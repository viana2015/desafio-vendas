package br.com.bagarote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bagarote.model.VendaProduto;
import br.com.bagarote.model.VendaProduto.VendaProdutoId;

@Repository
public interface VendaProdutoRepository extends JpaRepository<VendaProduto, VendaProdutoId> {

}
