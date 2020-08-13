package Sen.emploi.utilis;

import Sen.emploi.entities.Cv;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


public class PdfProfesseurListReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.addHeader("Content-Dispostion", "attachment; filename=\"prof_list.pdf\"");
		
		@SuppressWarnings("unused")
		List<Cv> list = (List<Cv>) model.get("professeursList");
		
		Table table = new Table(6);//6 colonnes
		table.addCell("NOM");
		table.addCell("PRENOM");
		table.addCell("EMAIL");
		table.addCell("ETAT COMPTE");
		for(Cv cv : list) {
			/*table.addCell(entreprise.getNom());
			table.addCell(entreprise.getPrenom());
			table.addCell(entreprise.getEmail());
			table.addCell(String.valueOf(entreprise.getEtat()));*/
		}
		
		document.add(table);
	}

}
