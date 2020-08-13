package Sen.emploi.controller;

import Sen.emploi.dao.IEntreprise;
import Sen.emploi.entities.Entreprise;
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
public class EntrepriseController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private IEntreprise entreprisedao;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @RequestMapping(value = "/online/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "")
    public String home(){
        return "redirect:/online/logon";
    }

    @RequestMapping(value = "/")
    public String index(){
        return "redirect:/online/logon";
    }

    @RequestMapping(value = "/online/logon")
    public String logon(HttpServletRequest request, HttpServletResponse response){
        return "redirect:/Entreprise/liste";
    }

    @RequestMapping(value = "/Entreprise/liste")
    public String liste(ModelMap model){
        model.addAttribute("liste_entreprises",entreprisedao.findAll());
        model.addAttribute("entreprise",new Entreprise());

        return  "entreprise/index";
    }

    @RequestMapping(value = "/Entreprise/edit", method = RequestMethod.GET)
    public String editEntreprise(ModelMap map, String email) {
        map.addAttribute("liste_entreprises",entreprisedao.findAll());
        map.addAttribute("entreprise", entreprisedao.getEntrepriseByEmail(email));
        return "entreprise/liste";
    }
    @RequestMapping(value = "/Entreprise/delete", method = RequestMethod.GET)
    public String deleteEntreprise(String email) {
        entreprisedao.delete(entreprisedao.getEntrepriseByEmail(email));
        return "redirect:/Entreprise/liste";
    }
    @RequestMapping(value = "/Entreprise/add", method = RequestMethod.POST)
    public String addEntreprise(Entreprise ent) {
        String hashedPassword1 = passwordEncoder.encode(ent.getPassword());
        ent.setPassword(hashedPassword1);
        entreprisedao.save(ent);
        /**
         * Emailing
         */
        String subject = "Validation inscription";
        String text = "Votre insciption a été validée, pour retourner vers votre interface " +
                "merci de cliquer sur ce lien http://localhost:7009/Entreprise/liste";
        emailService.sendSimpleMessage(ent.getEmail(), subject, text);
        return "redirect:/Entreprise/liste";
    }

    /*@RequestMapping(value = "/Entreprise/liste")
    public String liste(ModelMap model){
        List<Entreprise> p = entreprisedao.findAll();
        model.put("liste_professeurs",p);
        model.put("entreprise",new Entreprise());
        return  "entreprise/liste";
    }

    @RequestMapping(value = "/Entreprise/add" ,method = RequestMethod.POST)
    public String add(String email,String nom,String prenom,String password,int etat){
        ModelAndView modelAndView = new ModelAndView();
        Entreprise p = new Entreprise();
        p.setEmail(email);
        p.setNom(nom);
        p.setPrenom(prenom);
        String hashedPassword1 = passwordEncoder.encode(password);
        p.setPassword(hashedPassword1);
        p.setEtat(etat);
        try {
            entreprisedao.save(p);
            modelAndView.addObject("result", new String("Donnees ajoutees"));
        }catch (Exception ex){
            modelAndView.addObject("result", new String("Donnees non ajoutees"));
            ex.printStackTrace();
        }
        modelAndView.addObject(new String("entreprise/liste"));
        return "redirect:/Entreprise/liste";
    }

    @RequestMapping(value = "/Entreprise/delete" ,method = RequestMethod.GET)
    public String delete(String email){
        try {
            entreprisedao.delete(entreprisedao.getOne(email));
            entreprisedao.flush();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/Entreprise/liste";
    }

    @RequestMapping(value = "/Entreprise/edit" ,method = RequestMethod.GET)
    public String edit(String email,ModelMap model){
        List<Entreprise> listp = entreprisedao.findAll();
        model.put("liste_professeurs",listp);
        model.put("entreprise",new Entreprise());
        Entreprise p = entreprisedao.getOne(email);
        model.put("entreprise",p);
        return  "entreprise/liste";
    }*/

    @RequestMapping(value = "/online/logout" ,method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response   ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/online/login?logout";
    }

    @RequestMapping(value = "/online/403")
    public String accessDeniedPage(){
        return "403";
    }
}
