package controller;

import dao.LibroDao;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class ControllerLibro {

    @Autowired
    private LibroDao libroRepository;

    @GetMapping(value = "/paginaControllo")
    public String paginaControllo(HttpSession session){
        if (session.getAttribute("utenteLoggato") == null) return "redirect:/login";
        return "paginacontrollo";
    }


    @GetMapping(value = "/aggiungiLibro")
    public String aggiungiLibro(Libro libro, HttpSession session) {;
        if (session.getAttribute("utenteLoggato") == null) return "redirect:/login";

        return "aggiungiLibro";
    }

    @PostMapping(value = "/aggiungiLibroAction")
    public String aggiungiLibroAction(@Valid Libro libro, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "aggiungiLibro";

        for(Libro l : libroRepository.findAll()){
            if(Objects.equals(l.getTitolo().toLowerCase(), libro.getTitolo().toLowerCase()) &&
                    l.getAutore().toLowerCase().equals(libro.getAutore().toLowerCase()))
                return "redirect:/infoLibro";
        }

        libroRepository.save(libro);

        return "redirect:/paginacontrollo";
    }


    @GetMapping(value = "/infoLibro")
    public String infoLibro(Model model, HttpSession session) {
        if (session.getAttribute("utenteLoggato") == null) return "redirect:/login";

        model.addAttribute("libri", libroRepository.findAll());
        return "infoLibro";
    }
}
