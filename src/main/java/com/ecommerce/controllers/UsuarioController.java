package com.ecommerce.controllers;

import com.ecommerce.models.Usuario;
import com.ecommerce.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository ur;

    @RequestMapping ("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        List<Usuario> lista = ur.findAll();
        mv.addObject("lista", lista);
        mv.setViewName("index");
        return mv;
    }


}
