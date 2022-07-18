/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class GeradorPDF{

    public GeradorPDF() {

    }
    
    public boolean gerarRelatorioPDFultimos(List<Honorario> lista_honorarios) {
        int total_lista = lista_honorarios.size();
        double soma_honorarios = 0.0;
        double soma_inss = 0.0;
        double soma_proc = 0.0;
        double soma_dec = 0.0;
        
        /// ORDERNAR A LISTA DE HONORARIOS POR ORDEM ALFABETICA
        
        for(Honorario hon : lista_honorarios){
            soma_honorarios += hon.getVal_1();
            soma_inss += hon.getVal_2();
            soma_proc += hon.getVal_3();
            soma_dec += hon.getVal_4();            
        }
        
        Document doc = null;
        OutputStream os = null;

        try {
            doc = new Document(PageSize.A4, 20, 20, 20, 20);
            os = new FileOutputStream("ultimo.pdf");
            PdfWriter.getInstance(doc, os);
            
            PdfPTable table = cabecalhoRelatorio(lista_honorarios != null && !lista_honorarios.isEmpty() ? lista_honorarios.get(0).periodoFormatado() : "NULL");
            doc.open();
            doc.add(table);
            
            PdfPTable table2 = new PdfPTable(new float[] { 0.05f, 0.3f, 0.07f, 0.08f, 0.07f, 0.06f, 0.1f, 0.08f, 0.1f });
            table2.setWidthPercentage(100.0f);
            table2.setHorizontalAlignment(Element.ALIGN_CENTER);
            Font f = new Font(FontFamily.COURIER, 8, Font.BOLD);
            
            for(Honorario hono : lista_honorarios){
                // id cliente
                Paragraph cl_id = new Paragraph(new dao.DAOcliente().buscarClientesId(hono.getIdCliente()).getId()+"",f);
                PdfPCell txt_id = new PdfPCell(cl_id);
                txt_id.setHorizontalAlignment(Element.ALIGN_CENTER);
                // nome cliente
                Paragraph nome_cl = new Paragraph(new dao.DAOcliente().buscarClientesId(hono.getIdCliente()).getNome(),f);
                PdfPCell txt_nome_cl = new PdfPCell(nome_cl);
                txt_nome_cl.setHorizontalAlignment(Element.ALIGN_LEFT);
                // periodo
                Paragraph perio = new Paragraph(hono.periodoFormatado(),f);
                PdfPCell txt_perio = new PdfPCell(perio);
                txt_perio.setHorizontalAlignment(Element.ALIGN_CENTER);
                // honorarios
                Paragraph honor = new Paragraph(hono.getVal_1()+"",f);
                PdfPCell txt_honor = new PdfPCell(honor);
                txt_honor.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // inss
                Paragraph inss = new Paragraph(hono.getVal_2()+"",f);
                PdfPCell txt_inss = new PdfPCell(inss);
                txt_inss.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // proc.comp
                Paragraph proc = new Paragraph(hono.getVal_3()+"",f);
                PdfPCell txt_proc = new PdfPCell(proc);
                txt_proc.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // servicos
                Paragraph serv = new Paragraph(hono.getDesc_4(),f);
                PdfPCell txt_serv = new PdfPCell(serv);
                txt_serv.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // valor
                Paragraph valor = new Paragraph(hono.getVal_4()+"",f);
                PdfPCell txt_valor = new PdfPCell(valor);
                txt_valor.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // valor total
                Paragraph valort = new Paragraph((hono.getTotal())+"",f);
                PdfPCell txt_valort = new PdfPCell(valort);
                txt_valort.setHorizontalAlignment(Element.ALIGN_RIGHT);
                
                table2.addCell(txt_id);
                table2.addCell(txt_nome_cl);
                table2.addCell(txt_perio);
                table2.addCell(txt_honor);
                table2.addCell(txt_inss);
                table2.addCell(txt_proc);
                table2.addCell(txt_serv);
                table2.addCell(txt_valor);
                table2.addCell(txt_valort);
            }
            doc.add(table2);
            
            
            PdfPTable table3 = new PdfPTable(new float[] { 0.05f, 0.3f, 0.07f, 0.08f, 0.07f, 0.06f, 0.1f, 0.08f, 0.1f });
            table3.setWidthPercentage(100.0f);
            table3.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            //espaco id
            Paragraph ci = new Paragraph(" ",f);
            PdfPCell txt_ci = new PdfPCell(ci);
            txt_ci.setHorizontalAlignment(Element.ALIGN_CENTER);   
            txt_ci.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_ci);
            // total clientes
            Paragraph cl_lis = new Paragraph("Clientes listados: "+total_lista,f);
            PdfPCell txt_cl_lis = new PdfPCell(cl_lis);
            txt_cl_lis.setHorizontalAlignment(Element.ALIGN_CENTER);
            txt_cl_lis.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_cl_lis);
            // espaco periodo
            Paragraph per = new Paragraph(" ",f);
            PdfPCell txt_per = new PdfPCell(per);
            txt_per.setHorizontalAlignment(Element.ALIGN_CENTER);
            txt_per.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_per);
            // soma hono
            Paragraph hono = new Paragraph(soma_honorarios+"",f);
            PdfPCell txt_hono = new PdfPCell(hono);
            txt_hono.setHorizontalAlignment(Element.ALIGN_CENTER);  
            txt_hono.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_hono);
            // soma inss
            Paragraph ins = new Paragraph(soma_inss+"",f);
            PdfPCell txt_ins = new PdfPCell(ins);
            txt_ins.setHorizontalAlignment(Element.ALIGN_CENTER); 
            txt_ins.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_ins);
            // soma proc comp
            Paragraph proc = new Paragraph(soma_proc+"",f);
            PdfPCell txt_proc = new PdfPCell(proc);
            txt_proc.setHorizontalAlignment(Element.ALIGN_CENTER); 
            txt_proc.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_proc);
            // espaco 13
            Paragraph dec = new Paragraph(" ",f);
            PdfPCell txt_dec = new PdfPCell(dec);
            txt_dec.setHorizontalAlignment(Element.ALIGN_CENTER); 
            txt_dec.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_dec);
            // valor
            Paragraph vl = new Paragraph(soma_dec+"",f);
            PdfPCell txt_vl = new PdfPCell(vl);
            txt_vl.setHorizontalAlignment(Element.ALIGN_CENTER);  
            txt_vl.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_vl);
            // valor total
            Paragraph vlt = new Paragraph((soma_honorarios+soma_inss+soma_proc+soma_dec)+"",f);
            PdfPCell txt_vlt = new PdfPCell(vlt);
            txt_vlt.setHorizontalAlignment(Element.ALIGN_CENTER); 
            txt_vlt.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_vlt);
                        
            doc.add(table3);
            return true;
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean gerarRelatorioPDF(List<Honorario> lista_honorarios) {
        int total_lista = lista_honorarios.size();
        double soma_honorarios = 0.0;
        double soma_inss = 0.0;
        double soma_proc = 0.0;
        double soma_dec = 0.0;
        
        /// ORDERNAR A LISTA DE HONORARIOS POR ORDEM ALFABETICA
        
        for(Honorario hon : lista_honorarios){
            soma_honorarios += hon.getVal_1();
            soma_inss += hon.getVal_2();
            soma_proc += hon.getVal_3();
            soma_dec += hon.getVal_4();            
        }
        
        Document doc = null;
        OutputStream os = null;

        try {
            doc = new Document(PageSize.A4, 20, 20, 20, 20);
            os = new FileOutputStream("relatorio_"+lista_honorarios.get(0).getMes()+"_"+lista_honorarios.get(0).getAno()+".pdf");
            PdfWriter.getInstance(doc, os);
            
            PdfPTable table = cabecalhoRelatorio(lista_honorarios != null && !lista_honorarios.isEmpty() ? lista_honorarios.get(0).periodoFormatado() : "NULL");
            doc.open();
            doc.add(table);
            
            PdfPTable table2 = new PdfPTable(new float[] { 0.05f, 0.3f, 0.07f, 0.08f, 0.07f, 0.06f, 0.1f, 0.08f, 0.1f });
            table2.setWidthPercentage(100.0f);
            table2.setHorizontalAlignment(Element.ALIGN_CENTER);
            Font f = new Font(FontFamily.COURIER, 8, Font.BOLD);
            
            for(Honorario hono : lista_honorarios){
                // id cliente
                Paragraph cl_id = new Paragraph(new dao.DAOcliente().buscarClientesId(hono.getIdCliente()).getId()+"",f);
                PdfPCell txt_id = new PdfPCell(cl_id);
                txt_id.setHorizontalAlignment(Element.ALIGN_CENTER);
                // nome cliente
                Paragraph nome_cl = new Paragraph(new dao.DAOcliente().buscarClientesId(hono.getIdCliente()).getNome(),f);
                PdfPCell txt_nome_cl = new PdfPCell(nome_cl);
                txt_nome_cl.setHorizontalAlignment(Element.ALIGN_LEFT);
                // periodo
                Paragraph perio = new Paragraph(hono.periodoFormatado(),f);
                PdfPCell txt_perio = new PdfPCell(perio);
                txt_perio.setHorizontalAlignment(Element.ALIGN_CENTER);
                // honorarios
                Paragraph honor = new Paragraph(hono.getVal_1()+"",f);
                PdfPCell txt_honor = new PdfPCell(honor);
                txt_honor.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // inss
                Paragraph inss = new Paragraph(hono.getVal_2()+"",f);
                PdfPCell txt_inss = new PdfPCell(inss);
                txt_inss.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // proc.comp
                Paragraph proc = new Paragraph(hono.getVal_3()+"",f);
                PdfPCell txt_proc = new PdfPCell(proc);
                txt_proc.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // servicos
                Paragraph serv = new Paragraph(hono.getDesc_4(),f);
                PdfPCell txt_serv = new PdfPCell(serv);
                txt_serv.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // valor
                Paragraph valor = new Paragraph(hono.getVal_4()+"",f);
                PdfPCell txt_valor = new PdfPCell(valor);
                txt_valor.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // valor total
                Paragraph valort = new Paragraph((hono.getTotal())+"",f);
                PdfPCell txt_valort = new PdfPCell(valort);
                txt_valort.setHorizontalAlignment(Element.ALIGN_RIGHT);
                
                table2.addCell(txt_id);
                table2.addCell(txt_nome_cl);
                table2.addCell(txt_perio);
                table2.addCell(txt_honor);
                table2.addCell(txt_inss);
                table2.addCell(txt_proc);
                table2.addCell(txt_serv);
                table2.addCell(txt_valor);
                table2.addCell(txt_valort);
            }
            doc.add(table2);
            
            
            PdfPTable table3 = new PdfPTable(new float[] { 0.05f, 0.3f, 0.07f, 0.08f, 0.07f, 0.06f, 0.1f, 0.08f, 0.1f });
            table3.setWidthPercentage(100.0f);
            table3.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            //espaco id
            Paragraph ci = new Paragraph(" ",f);
            PdfPCell txt_ci = new PdfPCell(ci);
            txt_ci.setHorizontalAlignment(Element.ALIGN_CENTER);   
            txt_ci.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_ci);
            // total clientes
            Paragraph cl_lis = new Paragraph("Clientes listados: "+total_lista,f);
            PdfPCell txt_cl_lis = new PdfPCell(cl_lis);
            txt_cl_lis.setHorizontalAlignment(Element.ALIGN_CENTER);
            txt_cl_lis.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_cl_lis);
            // espaco periodo
            Paragraph per = new Paragraph(" ",f);
            PdfPCell txt_per = new PdfPCell(per);
            txt_per.setHorizontalAlignment(Element.ALIGN_CENTER);
            txt_per.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_per);
            // soma hono
            Paragraph hono = new Paragraph(soma_honorarios+"",f);
            PdfPCell txt_hono = new PdfPCell(hono);
            txt_hono.setHorizontalAlignment(Element.ALIGN_CENTER);  
            txt_hono.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_hono);
            // soma inss
            Paragraph ins = new Paragraph(soma_inss+"",f);
            PdfPCell txt_ins = new PdfPCell(ins);
            txt_ins.setHorizontalAlignment(Element.ALIGN_CENTER); 
            txt_ins.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_ins);
            // soma proc comp
            Paragraph proc = new Paragraph(soma_proc+"",f);
            PdfPCell txt_proc = new PdfPCell(proc);
            txt_proc.setHorizontalAlignment(Element.ALIGN_CENTER); 
            txt_proc.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_proc);
            // espaco 13
            Paragraph dec = new Paragraph(" ",f);
            PdfPCell txt_dec = new PdfPCell(dec);
            txt_dec.setHorizontalAlignment(Element.ALIGN_CENTER); 
            txt_dec.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_dec);
            // valor
            Paragraph vl = new Paragraph(soma_dec+"",f);
            PdfPCell txt_vl = new PdfPCell(vl);
            txt_vl.setHorizontalAlignment(Element.ALIGN_CENTER);  
            txt_vl.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_vl);
            // valor total
            Paragraph vlt = new Paragraph((soma_honorarios+soma_inss+soma_proc+soma_dec)+"",f);
            PdfPCell txt_vlt = new PdfPCell(vlt);
            txt_vlt.setHorizontalAlignment(Element.ALIGN_CENTER); 
            txt_vlt.setBorder(Rectangle.NO_BORDER);
            table3.addCell(txt_vlt);
                        
            doc.add(table3);
            return true;
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }
    
    private PdfPTable cabecalhoRelatorio(String referente){
        Font f = new Font(FontFamily.COURIER, 11, Font.BOLD);
        Font f2 = new Font(FontFamily.COURIER, 8, Font.BOLD);
        
        Paragraph empresa = new Paragraph("Escritório: "+classes.OperadorAutenticado.operadorAutenticado.getEmpresa().getRazaosocial(), f);
        empresa.setAlignment(Element.ALIGN_LEFT);        
        
        Paragraph ope = new Paragraph("Operador: "+classes.OperadorAutenticado.operadorAutenticado.getNome(), f);
        ope.setAlignment(Element.ALIGN_RIGHT);
        
        Paragraph data_emissao = new Paragraph("Emitido em: "+LocalDate.now().toString(), f);
        data_emissao.setAlignment(Element.ALIGN_RIGHT);
            
        Paragraph ref = new Paragraph("Referente: "+referente, f);
        ref.setAlignment(Element.ALIGN_RIGHT);
        
        
        PdfPTable table = new PdfPTable(new float[] { 0.05f, 0.3f, 0.07f, 0.08f, 0.07f, 0.06f, 0.1f, 0.08f, 0.1f });
        //PdfPTable table = new PdfPTable(9);
        
        
        PdfPCell header = new PdfPCell(empresa);            
        header.setColspan(6);
        header.setBorderWidthRight(0f);
        header.setBorderWidthBottom(0f);
        header.setPaddingLeft(10f);
        
        PdfPCell header2 = new PdfPCell(data_emissao);
        header2.setColspan(3);
        header2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        header2.setBorderWidthBottom(0f);
        header2.setBorderWidthLeft(0f);
        header2.setPaddingRight(10f);
        
        PdfPCell header3 = new PdfPCell(ope);
        header3.setColspan(6);
        header3.setPadding(10f);
        header3.setBorderWidthRight(0f);
        header3.setBorderWidthTop(0f);
        
        PdfPCell header4 = new PdfPCell(ref);
        header4.setColspan(3);
        header4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        header4.setPadding(10f);
        header4.setBorderWidthLeft(0f);
        header4.setBorderWidthTop(0f);
        
        table.addCell(header);
        table.addCell(header2);
        table.addCell(header3);
        table.addCell(header4);
        
        Paragraph txt = new Paragraph("id", f2);
        txt.setAlignment(Element.ALIGN_CENTER);
        Paragraph txt2 = new Paragraph("cliente", f2);
        txt.setAlignment(Element.ALIGN_CENTER);
        Paragraph txt3 = new Paragraph("perío.", f2);
        txt.setAlignment(Element.ALIGN_CENTER);
        Paragraph txt4 = new Paragraph("honor.", f2);
        txt.setAlignment(Element.ALIGN_CENTER);
        Paragraph txt5 = new Paragraph("inss", f2);
        txt.setAlignment(Element.ALIGN_CENTER);
        Paragraph txt6 = new Paragraph("proc.c", f2);
        txt.setAlignment(Element.ALIGN_CENTER);
        Paragraph txt7 = new Paragraph("serviço", f2);
        txt.setAlignment(Element.ALIGN_CENTER);
        Paragraph txt8 = new Paragraph("valor", f2);
        txt.setAlignment(Element.ALIGN_CENTER);
        Paragraph txt9 = new Paragraph("vl total", f2);
        txt.setAlignment(Element.ALIGN_CENTER);
        
        PdfPCell txtx = new PdfPCell(txt);
        txtx.setHorizontalAlignment(Element.ALIGN_CENTER);
        txtx.setPaddingBottom(5f);
        txtx.setBackgroundColor(new BaseColor(255,200,200));
        
        PdfPCell txtx2 = new PdfPCell(txt2);
        txtx2.setHorizontalAlignment(Element.ALIGN_CENTER);
        txtx2.setPaddingBottom(5f);
        txtx2.setBackgroundColor(new BaseColor(255,200,200));
        
        PdfPCell txtx3 = new PdfPCell(txt3);
        txtx3.setHorizontalAlignment(Element.ALIGN_CENTER);
        txtx3.setPaddingBottom(5f);
        txtx3.setBackgroundColor(new BaseColor(255,200,200));
        
        PdfPCell txtx4 = new PdfPCell(txt4);
        txtx4.setHorizontalAlignment(Element.ALIGN_CENTER);
        txtx4.setPaddingBottom(5f);
        txtx4.setBackgroundColor(new BaseColor(255,200,200));
        
        PdfPCell txtx5 = new PdfPCell(txt5);
        txtx5.setHorizontalAlignment(Element.ALIGN_CENTER);
        txtx5.setPaddingBottom(5f);
        txtx5.setBackgroundColor(new BaseColor(255,200,200));
        
        PdfPCell txtx6 = new PdfPCell(txt6);
        txtx6.setHorizontalAlignment(Element.ALIGN_CENTER);
        txtx6.setPaddingBottom(5f);
        txtx6.setBackgroundColor(new BaseColor(255,200,200));
        
        PdfPCell txtx7 = new PdfPCell(txt7);
        txtx7.setHorizontalAlignment(Element.ALIGN_CENTER);
        txtx7.setPaddingBottom(5f);
        txtx7.setBackgroundColor(new BaseColor(255,200,200));
        
        PdfPCell txtx8 = new PdfPCell(txt8);
        txtx8.setHorizontalAlignment(Element.ALIGN_CENTER);
        txtx8.setPaddingBottom(5f);
        txtx8.setBackgroundColor(new BaseColor(255,200,200));
        
        PdfPCell txtx9 = new PdfPCell(txt9);
        txtx9.setHorizontalAlignment(Element.ALIGN_CENTER);
        txtx9.setPaddingBottom(5f);
        txtx9.setBackgroundColor(new BaseColor(255,200,200));
        
        table.addCell(txtx);
        table.addCell(txtx2);
        table.addCell(txtx3);
        table.addCell(txtx4);
        table.addCell(txtx5);
        table.addCell(txtx6);
        table.addCell(txtx7);
        table.addCell(txtx8);
        table.addCell(txtx9);
        
        table.setWidthPercentage(100.0f);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
            
        return table;
    }

    public boolean gerarHonorariosPDF(List<Honorario> lista_honorarios) {       
        Font fonte = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
        
        Document doc = null;
        OutputStream os = null;

        try {
            doc = new Document(PageSize.A4, 2, 2, 2, 2);
            os = new FileOutputStream("honorario_"+lista_honorarios.get(0).getMes()+"_"+lista_honorarios.get(0).getAno()+".pdf");
            PdfWriter.getInstance(doc, os);
            doc.open();
            
            int qnt = 0;
            for(Honorario hono : lista_honorarios){
                PdfPTable table = new PdfPTable(new float[] { 0.5f, 0.004f, 0.5f });
                table.setWidthPercentage(100.0f);
                table.setHorizontalAlignment(Element.ALIGN_CENTER);                      

                PdfPCell[] cell = new PdfPCell[3];

                cell[0] = new PdfPCell(formataFilha01(1,hono));
                cell[0].setBorderColor(new BaseColor(178,178,178));
                cell[0].setPadding(5f);

                cell[1] = new PdfPCell();
                cell[1].setBorder(Rectangle.NO_BORDER);
                cell[1].setPadding(5f);

                cell[2] = new PdfPCell(formataFilha01(2,hono));
                cell[2].setBorderColor(new BaseColor(178,178,178));
                cell[2].setPadding(5f);
                               
                table.addCell(cell[0]);
                table.addCell(cell[1]);
                table.addCell(cell[2]);
                
                
                Paragraph esp = new Paragraph();
                esp.setSpacingAfter(2f);
                doc.add(table);
                doc.add(esp);
                if(qnt >= 2){
                    qnt = 0;
                    doc.newPage();
                }else{
                    qnt++;
                }
            }
            return true;
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }
    
    private PdfPTable formataFilha01(int via, Honorario hono){ 
        Font fonte = new Font(FontFamily.HELVETICA, 9, Font.BOLD);
        Font fonte2 = new Font(FontFamily.HELVETICA, 9, Font.NORMAL);
        
        PdfPTable tableL1 = new PdfPTable(new float[] { 0.25f, 0.75f });
        
        PdfPCell coluna_vazia = new PdfPCell(new Paragraph(" ",fonte));
        coluna_vazia.setHorizontalAlignment(Element.ALIGN_CENTER);
        coluna_vazia.setBorder(Rectangle.NO_BORDER);
        tableL1.addCell(coluna_vazia);
        
        PdfPCell coluna_emp = new PdfPCell(new Paragraph(classes.OperadorAutenticado.operadorAutenticado.getEmpresa().getRazaosocial(),fonte));
        coluna_emp.setHorizontalAlignment(Element.ALIGN_CENTER);
        coluna_emp.setBorder(Rectangle.NO_BORDER);
        coluna_emp.setPaddingBottom(7f);
        tableL1.addCell(coluna_emp);
        
        
        // logo img                       
        try {
            Image img; 
            img = Image.getInstance(getClass().getClassLoader().getResource("imagens/hono_logo.png"));
            img.setAlignment(Element.ALIGN_CENTER);
            img.setAlignment(Element.ALIGN_MIDDLE);
            PdfPCell coluna_img = new PdfPCell(img);
            coluna_img.setHorizontalAlignment(Element.ALIGN_CENTER);
            coluna_img.setBorder(Rectangle.NO_BORDER);
            tableL1.addCell(coluna_img);
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        }                       
        
        
        // passar o honorario aqui
        PdfPCell coluna_dados = new PdfPCell(formataDados(hono));
        coluna_dados.setHorizontalAlignment(Element.ALIGN_CENTER);
        coluna_dados.setBorder(Rectangle.NO_BORDER);
        tableL1.addCell(coluna_dados);
        
        
        // passar o honorario aqui
        PdfPCell coluna_valores = new PdfPCell(formataValores(hono));
        coluna_valores.setHorizontalAlignment(Element.ALIGN_CENTER);
        coluna_valores.setColspan(2);
        coluna_valores.setBorder(Rectangle.NO_BORDER);
        tableL1.addCell(coluna_valores);
        
        
        PdfPCell coluna_rodape = new PdfPCell(formataRodape(via));
        coluna_rodape.setHorizontalAlignment(Element.ALIGN_CENTER);
        coluna_rodape.setColspan(2);
        coluna_rodape.setBorder(Rectangle.NO_BORDER);
        tableL1.addCell(coluna_rodape);
        
        return tableL1;
    }
    
    private PdfPTable formataDados(Honorario hono){
        Font fonte = new Font(FontFamily.HELVETICA, 9, Font.BOLD);
        Font fonte2 = new Font(FontFamily.HELVETICA, 9, Font.NORMAL);
        
        PdfPTable tabled1 = new PdfPTable(new float[] { 0.7f, 0.3f });
        
        
        PdfPCell coluna_rua_emp = new PdfPCell(new Paragraph(classes.OperadorAutenticado.operadorAutenticado.getEmpresa().getEndereco(),fonte2));
        coluna_rua_emp.setHorizontalAlignment(Element.ALIGN_LEFT);
        coluna_rua_emp.setPaddingBottom(5f);
        coluna_rua_emp.setBorder(Rectangle.NO_BORDER);
        tabled1.addCell(coluna_rua_emp);
        
        PdfPCell coluna_esp = new PdfPCell(new Paragraph(" ",fonte2));
        coluna_esp.setHorizontalAlignment(Element.ALIGN_CENTER);
        coluna_esp.setPaddingBottom(5f);
        coluna_esp.setBorder(Rectangle.NO_BORDER);
        tabled1.addCell(coluna_esp);
        
        PdfPCell coluna_cnpj_emp = new PdfPCell(new Paragraph(classes.OperadorAutenticado.operadorAutenticado.getEmpresa().getCnpj(),fonte2));
        coluna_cnpj_emp.setHorizontalAlignment(Element.ALIGN_LEFT);
        coluna_cnpj_emp.setPaddingBottom(9f);
        coluna_cnpj_emp.setBorder(Rectangle.NO_BORDER);
        tabled1.addCell(coluna_cnpj_emp);
        
        PdfPCell coluna_tel_emp = new PdfPCell(new Paragraph(classes.OperadorAutenticado.operadorAutenticado.getEmpresa().getTelefone(),fonte2));
        coluna_tel_emp.setHorizontalAlignment(Element.ALIGN_RIGHT);
        coluna_tel_emp.setPaddingBottom(9f);
        coluna_tel_emp.setBorder(Rectangle.NO_BORDER);
        tabled1.addCell(coluna_tel_emp);
        
        // DADOS
        PdfPCell coluna_per = new PdfPCell(new Paragraph(hono.periodoFormatado(),fonte));
        coluna_per.setHorizontalAlignment(Element.ALIGN_LEFT);
        coluna_per.setPaddingBottom(1f);
        coluna_per.setPaddingTop(9f);
        coluna_per.setPaddingLeft(9f);
        coluna_per.setBackgroundColor(new BaseColor(243, 243, 243));
        coluna_per.setBorderColor(new BaseColor(235, 235, 235));
        coluna_per.setBorderWidthRight(0f);
        coluna_per.setBorderWidthBottom(0f);
        tabled1.addCell(coluna_per);
        
        PdfPCell esp1 = new PdfPCell(new Paragraph(" ",fonte));
        esp1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        esp1.setPaddingBottom(1f);
        esp1.setPaddingRight(9f);
        esp1.setPaddingTop(9f);
        esp1.setBackgroundColor(new BaseColor(243, 243, 243));
        esp1.setBorderColor(new BaseColor(235, 235, 235));
        esp1.setBorderWidthLeft(0f);
        esp1.setBorderWidthBottom(0f);
        tabled1.addCell(esp1);
        
        PdfPCell coluna_nomecl = new PdfPCell(new Paragraph(new dao.DAOcliente().buscarClientesId(hono.getIdCliente()).getNome(),fonte));
        coluna_nomecl.setHorizontalAlignment(Element.ALIGN_LEFT);
        coluna_nomecl.setPaddingBottom(1f);
        coluna_nomecl.setPaddingLeft(9f);
        coluna_nomecl.setBackgroundColor(new BaseColor(243, 243, 243));
        coluna_nomecl.setBorderColor(new BaseColor(235, 235, 235));
        coluna_nomecl.setBorderWidthTop(0f);
        coluna_nomecl.setBorderWidthBottom(0f);
        coluna_nomecl.setBorderWidthRight(0f);
        tabled1.addCell(coluna_nomecl);
        
        PdfPCell esp2 = new PdfPCell(new Paragraph(" ",fonte));
        esp2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        esp2.setPaddingBottom(1f);
        esp2.setPaddingRight(9f);
        esp2.setBackgroundColor(new BaseColor(243, 243, 243));
        esp2.setBorderColor(new BaseColor(235, 235, 235));
        esp2.setBorderWidthTop(0f);
        esp2.setBorderWidthBottom(0f);
        esp2.setBorderWidthLeft(0f);
        tabled1.addCell(esp2);
        
        PdfPCell coluna_endcl = new PdfPCell(new Paragraph(new dao.DAOcliente().buscarClientesId(hono.getIdCliente()).getEndereco(),fonte2));
        coluna_endcl.setHorizontalAlignment(Element.ALIGN_LEFT);
        coluna_endcl.setPaddingBottom(9f);
        coluna_endcl.setPaddingLeft(9f);
        coluna_endcl.setBackgroundColor(new BaseColor(243, 243, 243));
        coluna_endcl.setBorderColor(new BaseColor(235, 235, 235));
        coluna_endcl.setBorderWidthTop(0f);
        coluna_endcl.setBorderWidthRight(0f);
        tabled1.addCell(coluna_endcl);
        
        PdfPCell esp3 = new PdfPCell(new Paragraph(" ",fonte2));
        esp3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        esp3.setPaddingBottom(9f);
        esp3.setPaddingRight(9f);
        esp3.setBackgroundColor(new BaseColor(243, 243, 243));
        esp3.setBorderColor(new BaseColor(235, 235, 235));
        esp3.setBorderColor(new BaseColor(235, 235, 235));
        esp3.setBorderWidthTop(0f);
        esp3.setBorderWidthLeft(0f);
        tabled1.addCell(esp3);
        
        return tabled1;
    }
    
    private PdfPTable formataValores(Honorario hono){
        Font fonte = new Font(FontFamily.HELVETICA, 9, Font.BOLD);
        PdfPTable tableR1 = new PdfPTable(new float[] { 0.3f, 0.2f, 0.2f });
        
        // HONORARIO
        PdfPCell coluna_hono = new PdfPCell(new Paragraph(hono.getDesc_1(),fonte));
        coluna_hono.setHorizontalAlignment(Element.ALIGN_LEFT);
        coluna_hono.setBorder(Rectangle.NO_BORDER);
        coluna_hono.setPaddingBottom(5f);
        coluna_hono.setPaddingTop(9f);
        tableR1.addCell(coluna_hono);
        
        PdfPCell coluna_hono_esp = new PdfPCell(new Paragraph("---------------------",fonte));
        coluna_hono_esp.setHorizontalAlignment(Element.ALIGN_CENTER);
        coluna_hono_esp.setBorder(Rectangle.NO_BORDER);
        coluna_hono_esp.setPaddingBottom(5f);
        coluna_hono_esp.setPaddingTop(9f);
        tableR1.addCell(coluna_hono_esp);
        
        PdfPCell coluna_hono_v = new PdfPCell(new Paragraph(hono.getVal_1().toString(),fonte));
        coluna_hono_v.setHorizontalAlignment(Element.ALIGN_RIGHT);
        coluna_hono_v.setBorder(Rectangle.NO_BORDER);
        coluna_hono_v.setPaddingBottom(5f);
        coluna_hono_v.setPaddingTop(9f);
        tableR1.addCell(coluna_hono_v);
        
        // INSS
        PdfPCell coluna_inss = new PdfPCell(new Paragraph(hono.getDesc_2(),fonte));
        coluna_inss.setHorizontalAlignment(Element.ALIGN_LEFT);
        coluna_inss.setBorder(Rectangle.NO_BORDER);
        coluna_inss.setPaddingBottom(5f);
        tableR1.addCell(coluna_inss);
        
        PdfPCell coluna_inss_esp = new PdfPCell(new Paragraph("---------------------",fonte));
        coluna_inss_esp.setHorizontalAlignment(Element.ALIGN_CENTER);
        coluna_inss_esp.setBorder(Rectangle.NO_BORDER);
        coluna_inss_esp.setPaddingBottom(5f);
        tableR1.addCell(coluna_inss_esp);
        
        PdfPCell coluna_inss_v = new PdfPCell(new Paragraph(hono.getVal_2().toString(),fonte));
        coluna_inss_v.setHorizontalAlignment(Element.ALIGN_RIGHT);
        coluna_inss_v.setBorder(Rectangle.NO_BORDER);
        coluna_inss_v.setPaddingBottom(5f);
        tableR1.addCell(coluna_inss_v);
        
        // PROC. COMP
        PdfPCell coluna_pro = new PdfPCell(new Paragraph(hono.getDesc_3(),fonte));
        coluna_pro.setHorizontalAlignment(Element.ALIGN_LEFT);
        coluna_pro.setBorder(Rectangle.NO_BORDER);
        coluna_pro.setPaddingBottom(5f);
        tableR1.addCell(coluna_pro);
        
        PdfPCell coluna_pro_esp = new PdfPCell(new Paragraph("---------------------",fonte));
        coluna_pro_esp.setHorizontalAlignment(Element.ALIGN_CENTER);
        coluna_pro_esp.setBorder(Rectangle.NO_BORDER);
        coluna_pro_esp.setPaddingBottom(5f);
        tableR1.addCell(coluna_pro_esp);
        
        PdfPCell coluna_pro_v = new PdfPCell(new Paragraph(hono.getVal_3().toString(),fonte));
        coluna_pro_v.setHorizontalAlignment(Element.ALIGN_RIGHT);
        coluna_pro_v.setBorder(Rectangle.NO_BORDER);
        coluna_pro_v.setPaddingBottom(5f);
        tableR1.addCell(coluna_pro_v);
        
        // PARCELA 13º SALARIO
        PdfPCell coluna_parc = new PdfPCell(new Paragraph(hono.getDesc_4(),fonte));
        coluna_parc.setHorizontalAlignment(Element.ALIGN_LEFT);
        coluna_parc.setBorder(Rectangle.NO_BORDER);
        coluna_parc.setPaddingBottom(5f);
        tableR1.addCell(coluna_parc);
        
        PdfPCell coluna_parc_esp = new PdfPCell(new Paragraph("---------------------",fonte));
        coluna_parc_esp.setHorizontalAlignment(Element.ALIGN_CENTER);
        coluna_parc_esp.setBorder(Rectangle.NO_BORDER);
        coluna_parc_esp.setPaddingBottom(5f);
        tableR1.addCell(coluna_parc_esp);
        
        PdfPCell coluna_parc_v = new PdfPCell(new Paragraph(hono.getVal_4().toString(),fonte));
        coluna_parc_v.setHorizontalAlignment(Element.ALIGN_RIGHT);
        coluna_parc_v.setBorder(Rectangle.NO_BORDER);
        coluna_parc_v.setPaddingBottom(5f);
        tableR1.addCell(coluna_parc_v);
        
        // TOTAL
        PdfPCell coluna_total = new PdfPCell(new Paragraph("Total",fonte));
        coluna_total.setHorizontalAlignment(Element.ALIGN_LEFT);
        coluna_total.setBorder(Rectangle.NO_BORDER);
        coluna_total.setPaddingBottom(9f);
        tableR1.addCell(coluna_total);
        
        PdfPCell coluna_total_esp = new PdfPCell(new Paragraph("---------------------",fonte));
        coluna_total_esp.setHorizontalAlignment(Element.ALIGN_CENTER);
        coluna_total_esp.setBorder(Rectangle.NO_BORDER);
        coluna_total_esp.setPaddingBottom(9f);
        tableR1.addCell(coluna_total_esp);
        
        PdfPCell coluna_total_v = new PdfPCell(new Paragraph(hono.getTotal().toString(),fonte));
        coluna_total_v.setHorizontalAlignment(Element.ALIGN_RIGHT);
        coluna_total_v.setBorder(Rectangle.NO_BORDER);
        coluna_total_v.setPaddingBottom(9f);
        tableR1.addCell(coluna_total_v);
                
        return tableR1;
    }
    
    private PdfPTable formataRodape(int via){
        Font fonte = new Font(FontFamily.HELVETICA, 9, Font.BOLD);
        Font fonte2 = new Font(FontFamily.HELVETICA, 9, Font.NORMAL);
        
        PdfPTable tableR1 = new PdfPTable(new float[] { 0.5f, 0.5f });
                
        PdfPCell coluna_data = new PdfPCell(new Paragraph("_____/_____/_____",fonte2));
        coluna_data.setHorizontalAlignment(Element.ALIGN_LEFT);
        coluna_data.setBorderWidthRight(0f);
        coluna_data.setPaddingTop(9f);
        coluna_data.setPaddingLeft(9f);
        coluna_data.setPaddingBottom(9f);
        coluna_data.setBackgroundColor(new BaseColor(243, 243, 243));
        coluna_data.setBorderColor(new BaseColor(235, 235, 235));
        tableR1.addCell(coluna_data);
        
        PdfPCell coluna_via = new PdfPCell(via != 2 ? new Paragraph("CLIENTE",fonte) : new Paragraph("CONTABILIDADE",fonte));
        coluna_via.setHorizontalAlignment(Element.ALIGN_RIGHT);
        coluna_via.setBorderWidthLeft(0f);
        coluna_via.setPaddingTop(9f);
        coluna_via.setPaddingRight(9f);
        coluna_via.setPaddingBottom(9f);
        coluna_via.setBackgroundColor(new BaseColor(243, 243, 243));
        coluna_via.setBorderColor(new BaseColor(235, 235, 235));
        tableR1.addCell(coluna_via);
        
        return tableR1;
    }
            
    public boolean gerarHonorariosPDFavs(List<Honorario> lista_honorarios) {       
        Font fonte = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
        
        Document doc = null;
        OutputStream os = null;

        try {
            doc = new Document(PageSize.A4, 2, 2, 2, 2);
            os = new FileOutputStream("honorario.pdf");
            PdfWriter.getInstance(doc, os);
            doc.open();
            
            int qnt = 0;
            for(Honorario hono : lista_honorarios){
                PdfPTable table = new PdfPTable(new float[] { 0.5f, 0.004f, 0.5f });
                table.setWidthPercentage(100.0f);
                table.setHorizontalAlignment(Element.ALIGN_CENTER);                      

                PdfPCell[] cell = new PdfPCell[3];

                cell[0] = new PdfPCell(formataFilha01(1,hono));
                cell[0].setBorderColor(new BaseColor(178,178,178));
                cell[0].setPadding(5f);

                cell[1] = new PdfPCell();
                cell[1].setBorder(Rectangle.NO_BORDER);
                cell[1].setPadding(5f);

                cell[2] = new PdfPCell(formataFilha01(2,hono));
                cell[2].setBorderColor(new BaseColor(178,178,178));
                cell[2].setPadding(5f);
                               
                table.addCell(cell[0]);
                table.addCell(cell[1]);
                table.addCell(cell[2]);
                
                
                Paragraph esp = new Paragraph();
                esp.setSpacingAfter(2f);
                doc.add(table);
                doc.add(esp);
                if(qnt >= 2){
                    qnt = 0;
                    doc.newPage();
                }else{
                    qnt++;
                }
            }
            return true;
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }
    
    public boolean gerarHonorariosPDFult(List<Honorario> lista_honorarios) {       
        Font fonte = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
        
        Document doc = null;
        OutputStream os = null;

        try {
            doc = new Document(PageSize.A4, 2, 2, 2, 2);
            os = new FileOutputStream("ultimo.pdf");
            PdfWriter.getInstance(doc, os);
            doc.open();
            
            int qnt = 0;
            for(Honorario hono : lista_honorarios){
                PdfPTable table = new PdfPTable(new float[] { 0.5f, 0.004f, 0.5f });
                table.setWidthPercentage(100.0f);
                table.setHorizontalAlignment(Element.ALIGN_CENTER);                      

                PdfPCell[] cell = new PdfPCell[3];

                cell[0] = new PdfPCell(formataFilha01(1,hono));
                cell[0].setBorderColor(new BaseColor(178,178,178));
                cell[0].setPadding(5f);

                cell[1] = new PdfPCell();
                cell[1].setBorder(Rectangle.NO_BORDER);
                cell[1].setPadding(5f);

                cell[2] = new PdfPCell(formataFilha01(2,hono));
                cell[2].setBorderColor(new BaseColor(178,178,178));
                cell[2].setPadding(5f);
                               
                table.addCell(cell[0]);
                table.addCell(cell[1]);
                table.addCell(cell[2]);
                
                
                Paragraph esp = new Paragraph();
                esp.setSpacingAfter(2f);
                doc.add(table);
                doc.add(esp);
                if(qnt >= 2){
                    qnt = 0;
                    doc.newPage();
                }else{
                    qnt++;
                }
            }
            return true;
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }
    
}
