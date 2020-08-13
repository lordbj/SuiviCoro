package Sen.emploi.controller;

import Sen.emploi.dao.ICv;
import Sen.emploi.dao.IDemandeur;
import Sen.emploi.entities.Cv;
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
public class CvController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private ICv cvdao;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "/Cv/liste")
    public String liste(ModelMap model){
        model.addAttribute("liste_demandeurs",cvdao.findAll());
        model.addAttribute("cv",new Cv());

        return  "cv/liste";
    }

    @RequestMapping(value = "/Cv/edit", method = RequestMethod.GET)
    public String editDemandeur(ModelMap map, String email) {
        map.addAttribute("liste_demandeurs",cvdao.findAll());
        map.addAttribute("cv", cvdao.getCvByEmail(email));
        return "cv/liste";
    }
    @RequestMapping(value = "/Cv/delete", method = RequestMethod.GET)
    public String deleteDemandeur(String email) {
        cvdao.delete(cvdao.getCvByEmail(email));
        return "redirect:/Cv/liste";
    }
    @RequestMapping(value = "/Cv/add", method = RequestMethod.POST)
    public String addDemandeur(Cv ent) {
        /**  String hashedPassword1 = passwordEncoder.encode(ent.getPassword());
        ent.setPassword(hashedPassword1);
        cvdao.save(ent);
        /**
         * Emailing
         */
        String subject = "Validation inscription";
        String text = "Votre insciption a été validée, pour retourner vers votre interface " +
                "merci de cliquer sur ce lien http://localhost:7009/Cv/liste";
      //  emailService.sendSimpleMessage(ent.getEmail(), subject, text);
        return "redirect:/Cv/liste";
    }


}
