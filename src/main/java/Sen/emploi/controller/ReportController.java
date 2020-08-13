package Sen.emploi.controller;

import Sen.emploi.dao.ICv;
import Sen.emploi.entities.Cv;
import Sen.emploi.utilis.PdfProfesseurListReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    private ICv professeurdao;



    @RequestMapping(value = "/online/pdf")
    public ModelAndView createPdf(HttpServletRequest request, HttpServletResponse response) {
        String typeReport = request.getParameter("type");

        //create data
        List<Cv> list = professeurdao.findAll();

        return new ModelAndView(new PdfProfesseurListReportView(), "cvList", list);
    }


}
