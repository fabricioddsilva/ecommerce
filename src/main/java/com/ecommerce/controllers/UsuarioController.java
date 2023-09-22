package com.ecommerce.controllers;

import com.ecommerce.models.Usuario;
import com.ecommerce.repositories.UsuarioRepository;
import com.ecommerce.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository ur;

    @Autowired
    private UsuarioService us;

    @RequestMapping("/usuarios")
    public ModelAndView listarUsuarios() {
        ModelAndView mv = new ModelAndView();
        List<Usuario> lista = ur.findAll();
        mv.addObject("lista", lista);
        mv.setViewName("usuario/lista");
        return mv;
    }

    @GetMapping("usuario/cadastro")
    public ModelAndView cadastro(Usuario dados) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("dados", dados);
        mv.setViewName("usuario/cadastro");
        return mv;
    }

    @PostMapping("usuario/cadastro/confirmar")
    public ModelAndView salvarDados(Usuario dados) {
        ModelAndView mv = new ModelAndView();
        ur.saveAndFlush(dados);
        mv.setViewName("redirect:/usuarios");
        return mv;
    }

    @GetMapping("usuario/login")
    public ModelAndView userLogin(Usuario dados) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("dados", dados);
        mv.setViewName("usuario/login");
        return mv;
    }

    public ModelAndView login(Usuario dados, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("login", new Usuario());
        Usuario login = us.login(dados);
        if (login == null) {
            mv.setViewName("usuario/cadastro");
            mv.addObject("msg", "Email ou senha não encontrados");

        } else {
            session.setAttribute("user", login);
            session.setAttribute("dadosUser", login.getId());
            mv.setViewName("redirect:/");
            System.out.println("Usuario logado com sucesso");
        }

        return mv;
    }


}
