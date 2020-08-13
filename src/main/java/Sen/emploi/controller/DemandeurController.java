package Sen.emploi.controller;

import Sen.emploi.dao.IDemandeur;
import Sen.emploi.entities.Demandeur;
import Sen.emploi.utilis.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
 public class DemandeurController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private IDemandeur demandeurdao;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "/Demandeur/liste")
    public String liste(ModelMap model){
        model.addAttribute("liste_demandeurs",demandeurdao.findAll());
        model.addAttribute("demandeur",new Demandeur());

        return  "demandeur/index";
    }

    @RequestMapping(value = "/Demandeur/edit", method = RequestMethod.GET)
    public String editDemandeur(ModelMap map, String email) {
        map.addAttribute("liste_demandeurs",demandeurdao.findAll());
        map.addAttribute("demandeur", demandeurdao.getDemandeurByEmail(email));
        return "demandeur/liste";
    }
    @RequestMapping(value = "/Demandeur/delete", method = RequestMethod.GET)
    public String deleteDemandeur(String email) {
        demandeurdao.delete(demandeurdao.getDemandeurByEmail(email));
        return "redirect:/Demandeur/liste";
    }
    @RequestMapping(value = "/Demandeur/add", method = RequestMethod.POST)
    public String addDemandeur(Demandeur ent) {
        String hashedPassword1 = passwordEncoder.encode(ent.getPassword());
        ent.setPassword(hashedPassword1);
        demandeurdao.save(ent);
        /**
         * Emailing
         */
        String subject = "Validation inscription";
        String text = "Votre insciption a été validée, pour retourner vers votre interface " +
                "merci de cliquer sur ce lien http://localhost:7009/Demandeur/liste";
        emailService.sendSimpleMessage(ent.getEmail(), subject, text);
        return "redirect:/Demandeur/liste";
    }

}
