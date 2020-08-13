package Sen.emploi.controller;

import Sen.emploi.dao.IRoles;
import Sen.emploi.entities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RolesController {
    @Autowired
    private IRoles roledao;
    @RequestMapping(value = "/Roles/liste")
    public String liste(ModelMap model){
        List<Roles> c = roledao.findAll();
        model.put("liste_roles",c);
        model.put("role",new Roles());
        return  "role/liste";
    }

    @RequestMapping(value = "/Roles/add" ,method = RequestMethod.POST)
    public String add(String nom){
        ModelAndView modelAndView = new ModelAndView();
        Roles r = new Roles();
        r.setNom(nom);
        try {
            roledao.save(r);
            modelAndView.addObject("result", new String("Donnees ajoutees"));
        }catch (Exception ex){
            modelAndView.addObject("result", new String("Donnees non ajoutees"));
            ex.printStackTrace();
        }
        modelAndView.addObject(new String("cours/liste"));
        return "redirect:/Roles/liste";
    }

    @RequestMapping(value = "/Roles/delete" ,method = RequestMethod.GET)
    public String delete(String nom){
        try {
            roledao.delete(roledao.getOne(nom));
            roledao.flush();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/Roles/liste";
    }

    @RequestMapping(value = "/Roles/edit" ,method = RequestMethod.GET)
    public String edit(String nom, ModelMap model){
        List<Roles> listp = roledao.findAll();
        model.put("liste_roles",listp);
        model.put("role",new Roles());
        Roles r = roledao.getOne(nom);
        model.put("role",r);
        return  "role/liste";
    }
}

