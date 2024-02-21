package com.example.demo.Generator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import com.example.demo.Domain.Aluno;
import com.example.demo.Domain.Exercicio;
import com.example.demo.Domain.Treino;

@Service
public class RelatorioGen implements Relatorio {

    private Aluno aluno;
    private Treino treino;
    private List<Exercicio> exercicios;
    private String caminhoimagem = "Caminho da sua imagem.jpg";
    private Document documentoPDF;
    private String caminhoRelatorio = "Nome do documento.pdf";

    //Construtor necessário para enviar dados essenciais
    public RelatorioGen(Aluno aluno, Treino treino, List<Exercicio> exercicios) {
        this.aluno = aluno;
        this.treino = treino;
        //Específicações do tamanho do documento e margens
        this.documentoPDF = new Document(PageSize.A4, 50, 50, 30, 30);
        this.exercicios = exercicios;
        //Tratamento de erro caso o documento não seja encontrado pra ser aberto
        try {
            PdfWriter.getInstance(this.documentoPDF, new FileOutputStream(caminhoRelatorio));
            this.documentoPDF.open();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Cabeçalho para colocar os dados do personal e títulos do documento
    @Override
    public void gerarCabecalho() {
        adicionarImagemCabecalho(caminhoimagem);
        adicionarTituloCabecalho("PERSONAL TRAINER\nEDUCADOR FÍSICO");
        adicionarSubtituloCabecalho("Nome do Personal, CREF:*****-***");
        adicionarQuebraDeSessao();
    }

    //Função para gerar o corpo completo do documento
    @Override
    public void gerarCorpo() {
        adicionarDadosAluno();
        adicionarQuebraDeSessao();
        AdicionarDadosTreino();
        adicionarQuebraDeSessao();
        adicionarExercicios();
    }

    //Adicionar a lista dinâmica de exercicios
    public void adicionarExercicios() {
        Paragraph paragrafoExercicio = new Paragraph();
        paragrafoExercicio.setAlignment(Element.ALIGN_LEFT);
    
        paragrafoExercicio.add(new Chunk("Treino ", new Font(Font.COURIER, 16, Font.BOLDITALIC)));
        paragrafoExercicio.add(new Chunk(treino.getTipo() + "\n", new Font(Font.COURIER, 16, Font.BOLDITALIC)));

    
        int count = 1;
        for (Exercicio exercicio : exercicios) {
            paragrafoExercicio.add(new Chunk(
                    count + "- " + exercicio.getNome() + " - " + exercicio.getSeries() + " séries - "
                            + exercicio.getRepeticoes() + "x\n",
                    new Font(Font.COURIER, 12)));
            count++;
        }
    
        try {
            documentoPDF.add(paragrafoExercicio);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gerarRodape() {
        this.pularLinha();
        this.adicionarRodaPe();
    }

    @Override
    public void imprimir() {
        if (this.documentoPDF != null && this.documentoPDF.isOpen()) {
            documentoPDF.close();
        }
    }

    private void adicionarDadosAluno() {
        Paragraph paragrafoDadosAluno = new Paragraph();
        DecimalFormat df = new DecimalFormat("#.##");
        paragrafoDadosAluno.setAlignment(Element.ALIGN_LEFT);

        paragrafoDadosAluno.add(new Chunk("Nome: ", new Font(Font.COURIER, 12, Font.BOLDITALIC)));//
        paragrafoDadosAluno.add(new Chunk(aluno.getNome() + "\n", new Font(Font.COURIER, 12)));//
        paragrafoDadosAluno.add(new Chunk("Data Nascimento: ", new Font(Font.COURIER, 12, Font.BOLDITALIC)));//
        paragrafoDadosAluno.add(new Chunk(aluno.getData() + "\n", new Font(Font.COURIER, 12)));//
        paragrafoDadosAluno.add(new Chunk("Peso: ", new Font(Font.COURIER, 12, Font.BOLDITALIC)));//
        paragrafoDadosAluno.add(new Chunk(df.format(aluno.getPeso()) + "Kg              ", new Font(Font.COURIER, 12)));//
        paragrafoDadosAluno.add(new Chunk("Altura: ", new Font(Font.COURIER, 12, Font.BOLDITALIC)));
        paragrafoDadosAluno.add(new Chunk(aluno.getAltura() + "m\n", new Font(Font.COURIER, 12)));//
        paragrafoDadosAluno.add(new Chunk("Anamnese: ", new Font(Font.COURIER, 12, Font.BOLDITALIC)));//
        paragrafoDadosAluno.add(new Chunk(aluno.getAnamnese() + "\n", new Font(Font.COURIER, 12)));//
        paragrafoDadosAluno.add(new Chunk("Possíveis restrições: ", new Font(Font.COURIER, 12, Font.BOLDITALIC)));//
        paragrafoDadosAluno.add(new Chunk(aluno.getObs() + "\n", new Font(Font.COURIER, 12)));//
        paragrafoDadosAluno.add(new Chunk("Objetivo: ", new Font(Font.COURIER, 12, Font.BOLDITALIC)));//
        paragrafoDadosAluno.add(new Chunk(aluno.getObjetivos() + "\n", new Font(Font.COURIER, 12)));//

        try {
            documentoPDF.add(paragrafoDadosAluno);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void AdicionarDadosTreino() {
        Paragraph paragrafoDadosTreino = new Paragraph();
        paragrafoDadosTreino.setAlignment(Element.ALIGN_LEFT);

        paragrafoDadosTreino.add(new Chunk("Inicio do Treino: ", new Font(Font.COURIER, 12, Font.BOLDITALIC)));
        paragrafoDadosTreino.add(new Chunk(treino.getInicio() + "\n", new Font(Font.COURIER, 12)));
        paragrafoDadosTreino.add(new Chunk("Período Treino: ", new Font(Font.COURIER, 12, Font.BOLDITALIC)));
        paragrafoDadosTreino.add(new Chunk(treino.getPeriodo() + "\n", new Font(Font.COURIER, 12)));
        paragrafoDadosTreino.add(new Chunk("Objetivo Treino: ", new Font(Font.COURIER, 12, Font.BOLDITALIC)));
        paragrafoDadosTreino.add(new Chunk(treino.getObjetivo() + "\n", new Font(Font.COURIER, 12)));
        paragrafoDadosTreino.add(new Chunk("Prescrição de atividades: ", new Font(Font.COURIER, 12, Font.BOLDITALIC)));
        paragrafoDadosTreino.add(new Chunk(treino.getPrescricao() + "\n", new Font(Font.COURIER, 12)));
        paragrafoDadosTreino.add("\n");
        paragrafoDadosTreino
                .add(new Chunk("   Exercícios Resistidos (Musculação)\n", new Font(Font.COURIER, 16, Font.BOLDITALIC)));
        paragrafoDadosTreino.add(new Chunk("Grupo muscular: ", new Font(Font.COURIER, 12, Font.BOLDITALIC)));
        paragrafoDadosTreino.add(new Chunk(treino.getGrupoMuscular(), new Font(Font.COURIER, 12)));

        try {
            documentoPDF.add(paragrafoDadosTreino);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void adicionarQuebraDeSessao() {
        Paragraph paragrafoSessao = new Paragraph(
                "________________________________________________________________________");
        paragrafoSessao.setAlignment(Element.ALIGN_CENTER);
        this.documentoPDF.add(paragrafoSessao);
    }

    private void adicionarImagemCabecalho(String caminhoImagem) {
        Image imgLogo = null;
        try {
            imgLogo = Image.getInstance(caminhoImagem);
            imgLogo.scaleToFit(130, 130);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Imagem não encontrada", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        if (imgLogo != null) {
            imgLogo.setAlignment(Element.ALIGN_LEFT);
            try {
                documentoPDF.add(imgLogo);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }

    private void adicionarTituloCabecalho(String titulo) {
        Paragraph paragrafoTitulo = new Paragraph();
        paragrafoTitulo.setAlignment(Element.ALIGN_CENTER);
        Chunk cTitulo = new Chunk(titulo);
        cTitulo.setFont(new Font(Font.COURIER, 24));
        paragrafoTitulo.add(cTitulo);
        try {
            documentoPDF.add(paragrafoTitulo);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void adicionarSubtituloCabecalho(String subtitulo) {
        Paragraph paragrafoSubtitulo = new Paragraph();
        paragrafoSubtitulo.setAlignment(Element.ALIGN_CENTER);
        Chunk cSubtitulo = new Chunk(subtitulo);
        cSubtitulo.setFont(new Font(Font.COURIER, 14));
        paragrafoSubtitulo.add(cSubtitulo);
        try {
            documentoPDF.add(paragrafoSubtitulo);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void pularLinha() {
        this.documentoPDF.add(new Paragraph(" "));
    }

    private void adicionarRodaPe() {
        Paragraph pRodape = new Paragraph();
        pRodape.setAlignment(Element.ALIGN_CENTER);
        pRodape.add(new Chunk("O resultado depende apenas do seu esforço!", new Font(Font.TIMES_ROMAN, 14)));
        this.documentoPDF.add(pRodape);
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public Document getDocumentoPDF() {
        return documentoPDF;
    }

    public void setDocumentoPDF(Document documentoPDF) {
        this.documentoPDF = documentoPDF;
    }

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }
}
