package com.ecommerce.repositories;

import com.ecommerce.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select l from tb_usuario where l.nome = :nome and l.senha = :senha")
    public Usuario login(String nome, String senha);


}
