package com.ecommerce.controllers;

import com.ecommerce.models.Usuario;
import com.ecommerce.repositories.UsuarioRepository;
import com.ecommerce.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository userRepo;

    @Autowired
    private UsuarioService us;

    @RequestMapping("/usuarios")
    public String listarUsuarios(Model model) {
        List<Usuario> lista = userRepo.findAll();
        model.addAttribute("usuarios", lista);
        return "usuario/lista";
    }

    @GetMapping("usuario/cadastro")
    public String cadastro(){
        return "usuario/cadastro";
    }

    @PostMapping("usuario/cadastrar")
    public String cadastrar(Usuario dados, Model model) {
        userRepo.saveAndFlush(dados);
        return "redirect:/usuarios";
    }

    @GetMapping("usuario/login")
    public String login(){
        return "usuario/login";
    }

    @PostMapping("usuario/logar")
    public String logar(Usuario dados, Model model, HttpSession session){
        Usuario login = this.userRepo.login(dados.getNome(), dados.getSenha());

        if(login != null) {
            session.setAttribute("logado", dados);
            return "redirect:/";
        } else {
            model.addAttribute("erro", "Usuario ou senha inválidos");
            return "usuario/login";
        }

    }


}
