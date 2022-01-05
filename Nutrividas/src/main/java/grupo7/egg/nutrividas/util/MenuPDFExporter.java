package grupo7.egg.nutrividas.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import grupo7.egg.nutrividas.entidades.Menu;
import grupo7.egg.nutrividas.entidades.Nutricionista;
import grupo7.egg.nutrividas.mail.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuPDFExporter {


    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(4);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);

        table.addCell(cell);

        cell.setPhrase(new Phrase("Almuerzo", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Merienda", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cena", font));
        table.addCell(cell);

    }

   /* private void writeTableData(PdfPTable table) {
        for (Persona personas : listaPacientes) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getEmail());
            table.addCell(user.getFullName());
            table.addCell(user.getRoles().toString());
            table.addCell(String.valueOf(user.isEnabled()));
        }
    }*/
    private String[] dias = {"Lunes","Martes","Miercoles","Jueves","Viernes"};
    private void writeTableData(PdfPTable table,Menu menu) {
        String[][] menuSemanal = menu.getMenuSemanal();
        for (int i = 0; i < menuSemanal.length; i++) {
            table.addCell(dias[i]);
            for (int j = 0; j < menuSemanal[i].length; j++) {
                table.addCell(menu.getMenuSemanal()[i][j]);
            }
        }
    }

    public void export(HttpServletResponse response,Menu menu) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);

        Paragraph p = new Paragraph(menu.getTitulo(), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        Image image = Image.getInstance("C:\\Users\\test\\Documents\\ProyectoFinalEgg2\\Proyecto-Egg\\Nutrividas\\src\\main\\resources\\static\\img\\backgroundMenu.png");
        //image.scaleAbsolute(300, 200);
        //image.setAbsolutePosition(0, 0);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.5f, 3.f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table,menu);

        document.add(table);

        document.close();
    }

    public void export2(HttpServletResponse response, Menu menu, Nutricionista nutricionista) throws IOException {
        //String inputFile = "C:\\Users\\test\\Documents\\ProyectoFinalEgg2\\Proyecto-Egg\\Nutrividas\\src\\main\\resources\\templates\\menuPDF.html";

        //String html = new String(Files.readAllBytes(Paths.get(inputFile)));
        String html = Template.menuTemplate(menu,nutricionista);
        org.jsoup.nodes.Document document = Jsoup.parse(html);
        document.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);


        try (OutputStream outputStream = response.getOutputStream()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            renderer.setDocumentFromString(document.html());
            renderer.layout();
            renderer.createPDF(outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
