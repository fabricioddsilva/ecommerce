package com.ecommerce.repositories;

import com.ecommerce.models.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select * from tb_usuario where nome = :nome and senha = :senha", nativeQuery = true)
    public Usuario login(String nome, String senha);


}
