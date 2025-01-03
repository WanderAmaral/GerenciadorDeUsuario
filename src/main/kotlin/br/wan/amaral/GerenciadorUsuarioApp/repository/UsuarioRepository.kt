package br.wan.amaral.GerenciadorUsuarioApp.repository

import br.wan.amaral.GerenciadorUsuarioApp.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Long>{
}