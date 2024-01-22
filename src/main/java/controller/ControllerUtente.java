package controller;

import dao.UtenteDao;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerUtente {
    @Autowired
    private UtenteDao utenteRepository;

    @GetMapping(value = "/")
    public String signUp(Utente utente) {
        return "signup";
    }

    @PostMapping(value = "/validazioneRegistrazione")
    public String validazioneRegistrazione(@Valid Utente utente, BindingResult bindingResult, HttpSession session) {
        if(bindingResult.hasErrors()) return "signup";
        for (Utente u : utenteRepository.findAll()){
            if(u.getUsername().toLowerCase().equals(utente.getUsername().toLowerCase()))
                return "redirect:/";
        }


        utenteRepository.save(utente);
        session.setAttribute("loggedUtente", utente);

        return "paginacontrollo";
    }

    @GetMapping("/login")
    public String login(Utente utente) {
        return "login";
    }

    @PostMapping("/validazioneLogin")
    public String validazioneLogin(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpSession session) {


        Utente utenteLoggato = utenteRepository.login(username, password);

        if(utenteLoggato == null)
            return "redirect:/login";
        else {
            session.setAttribute("utenteLoggato", utenteLoggato);

            return "paginacontrollo";
        }

    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.setAttribute("utenteLoggato", null);

        return "redirect:/";
    }

    @GetMapping(value = "/infoUtente")
    public String infoUtente(Model model, HttpSession session) {
        if (session.getAttribute("utenteLoggato") == null) return "redirect:/login";

        model.addAttribute("utente", session.getAttribute("utenteLoggato"));
        return "infoUtente";
    }
}
