package br.wan.amaral.GerenciadorUsuarioApp.controller

import br.wan.amaral.GerenciadorUsuarioApp.model.Usuario
import br.wan.amaral.GerenciadorUsuarioApp.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.bind.BindResult
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UsuarioController {

    @Autowired
    lateinit var repository: UsuarioRepository

    @GetMapping("/formulario/cadastro")
    fun openFormRegister(model: Model): String {

        val usuario = Usuario()

        model.addAttribute("usuario", usuario)

        return "forms-register"
    }



    @PostMapping("/cadastrar")
    fun registerUser(@Validated usuario: Usuario, result: BindingResult): String {

        if (result.hasErrors()) {
            return "forms-register"
        }


        println(usuario)
        repository.save(usuario)

        return  "redirect:/home"
    }

    @GetMapping("/home")
    fun openHome(model: Model): String {

        //Buscando todos os usuario no bd
        val usuarios = repository.findAll()

        model.addAttribute("usuarios", usuarios)

        return  "home"
    }
    
    @GetMapping("/formulario/edicao/{id}")
    fun openFormEdit(@PathVariable("id") id: Long, model: Model): String {
        
        val usuario = repository.findById(id).orElse(null)
        
        model.addAttribute("usuario", usuario)
        
        return "forms-edit"
    }
    
    @PostMapping("/editar/{id}")
    fun editUser(usuario: Usuario, @PathVariable("id") id: Long): String {
    
        usuario.id = id
        
        repository.save(usuario)
        
        return "redirect:/home"
    }
    
    fun deleteUser(userId: String) {
    
    }
}