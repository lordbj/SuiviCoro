package Sen.emploi.controller;

import Sen.emploi.dao.IOffre;
import Sen.emploi.entities.Offre;
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
public class OffreController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private IOffre offredao;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "/Offre/liste")
    public String liste(ModelMap model){
        model.addAttribute("liste_offres",offredao.findAll());
        model.addAttribute("offre",new Offre());

        return  "offre/index";
    }

    @RequestMapping(value = "/Offre/edit", method = RequestMethod.GET)
    public String editDemandeur(ModelMap map, String categorie) {
        map.addAttribute("liste_offres",offredao.findAll());
        map.addAttribute("offre", offredao.getOffreByCategorie(categorie));
        return "offre/liste";
    }
    @RequestMapping(value = "/Offre/delete", method = RequestMethod.GET)
    public String deleteDemandeur(String categorie) {
        offredao.delete(offredao.getOffreByCategorie(categorie));
        return "redirect:/Offre/liste";
    }
    @RequestMapping(value = "/Offre/add", method = RequestMethod.POST)
    public String addDemandeur(Offre ent) {
        /** String hashedPassword1 = passwordEncoder.encode(ent.getPassword());
        ent.setPassword(hashedPassword1);
        offredao.save(ent);

         * Emailing
         */
        String subject = "Validation inscription";
        String text = "Votre insciption a été validée, pour retourner vers votre interface " +
                "merci de cliquer sur ce lien http://localhost:7009/Offre/liste";
       // emailService.sendSimpleMessage(ent.getEmail(), subject, text);
        return "redirect:/Offre/liste";
    }

}
