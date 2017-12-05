package beans;

import com.itextpdf.html2pdf.HtmlConverter;
import entities.Declaracao;
import entities.Projeto;
import entities.TextoBaseDeclaracao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author CristianoSilva
 */
@ManagedBean
@SessionScoped
public class DeclaracaoBean {
    private Declaracao declaracao = new Declaracao();
    private Declaracao declaracaoSelecionada;
    private List<Declaracao> declaracoes;
    private List<Declaracao> listaFiltrada;
    private List<SelectItem> alunosOption = new ArrayList<>();
    private List<SelectItem> colaboradoresOption = new ArrayList<>();
    private static HashMap<String, String> tiposDestinatario;
    private String tipoDestinatario;
    private StreamedContent file;
    private String arquivoTempName;
        
// Getters e Setters
    public List<Declaracao> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<Declaracao> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    static{
        tiposDestinatario = new HashMap<String, String>();
        tiposDestinatario.put("Aluno", "ALUNO");
        tiposDestinatario.put("Colaborador", "COLABORADOR");
    }
    
    public String getTipoDestinatario() {
        return this.tipoDestinatario;
    }

    public void setTipoDestinatario(String tipoDestinatario) {
        this.tipoDestinatario = tipoDestinatario;
    }
    
    public HashMap<String, String> getTiposDestinatario() {
        return this.tiposDestinatario;
    }

    public void setTipoDestinatario(HashMap<String, String> tipoDestinatario) {
        this.tiposDestinatario = tiposDestinatario;
    }
    
    public Declaracao getDeclaracao() {
        return this.declaracao;
    }

    public void setDeclaracao(Declaracao declaracao) {
        this.declaracao = declaracao;
    }

    public void setDeclaracaoSelecionada(Declaracao declaracao) {
        this.declaracaoSelecionada = declaracao;
    }
      
    public List<Declaracao> getDeclaracoes(){
        this.declaracoes = this.declaracao.buscarTodos();
        return declaracoes;
    }
    
    public void setDeclaracoes(List<Declaracao> lista){
        this.declaracoes = lista;
    }
    
    public List<SelectItem> getAlunosOption() {
        return this.alunosOption;
    }

    public void setAlunosOption(List<SelectItem> alunosOption) {
        this.alunosOption = alunosOption;
    }

    public List<SelectItem> getColaboradoresOption() {
        return this.colaboradoresOption;
    }

    public void setColaboradoresOption(List<SelectItem> colaboradoresOption) {
        this.colaboradoresOption = colaboradoresOption;
    }

    public List<SelectItem> getProjetosSelect(){
        List<SelectItem> items = new ArrayList<>();  
        new Projeto().buscarTodos().forEach((c) -> {
            if (c.getAtivo()){
                items.add(new SelectItem(c, c.getTitulo()));
            }
        }); 
        return items;
    }
    
    public List<SelectItem> getTextosBaseSelect(){
        List<SelectItem> items = new ArrayList<>();  
        new TextoBaseDeclaracao().buscarTodos().forEach((c) -> {
            if (c.getAtivo()){
                items.add(new SelectItem(c, c.getIdentificador()));
            }
        }); 
        return items;
    }
    
// Ações
    public String detalhar(Long id){
        if(id != null)
            this.declaracaoSelecionada = this.declaracao.buscarPeloId(id);

        if (this.declaracaoSelecionada == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Declaração!",
                                   "Erro ao localizar Declaração!"));
            return "/pages/listar/listarDeclaracoes";
        }
        else {
            this.declaracao = this.declaracaoSelecionada;
            return "/pages/detalhes/detalhesDeclaracao?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            this.declaracaoSelecionada = this.declaracao.buscarPeloId(id);

        if (declaracaoSelecionada == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Declaração!",
                            "Erro ao localizar Declaração!"));
            return "/pages/listar/listarDeclaracoes";
        } 
        else {
            this.declaracao = declaracaoSelecionada;
            return "/pages/editar/editarDeclaracao?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.declaracao = new Declaracao();
        this.declaracaoSelecionada = new Declaracao();
    }
    
    public String salvar() {
        this.declaracao.setDataEmissao(new Date());
        this.declaracao.setResponsavel(this.declaracao.getProjeto().getCoordenador());
        if(this.declaracao.salvar())
            return "/pages/listar/listarDeclaracoes?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Declaração!",
                                   "Erro ao salvar Declaração!"));
            return "/pages/cadastrar/cadastrarDeclaracao";
        }
    }
    
    public void onProjetoChange() {
        List<SelectItem> itens = new ArrayList<>();
        if(this.declaracao.getProjeto() != null){
            this.declaracao.getProjeto().getListaAlunos().forEach((c) -> {
                itens.add(new SelectItem(c, c.getNome()));
            });
        }
        this.alunosOption = itens;
        
        List<SelectItem> items = new ArrayList<>();
        if(this.declaracao.getProjeto() != null){
            this.declaracao.getProjeto().getListaColaboradores().forEach((c) -> {
                items.add(new SelectItem(c, c.getNome()));
            });
        }
        this.colaboradoresOption = items;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public StreamedContent getFile()  {
        gerarPDF(); //Método que cria o PDF e salva localmente
        //String caminhoWebInf = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/");
        InputStream stream;
        try {
            stream = new FileInputStream(arquivoTempName); //Caminho onde está salvo o arquivo.
            file = new DefaultStreamedContent(stream, "application/pdf", "Projeto_" + this.declaracao.getProjeto().getTitulo() + "_Destinatario_" + this.declaracao.getDestinatario().getNome() + ".pdf");  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DeclaracaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;  
    }
    
    //////////////////////para gerar e salvar o PDF
    public void gerarPDF(){
        if(this.declaracao.getId() == null)
            salvar();
        String texto = this.declaracao.getTextoBaseDeclaracao().getTexto();

        //Tags replace
        texto = texto.replaceAll("#projeto", declaracao.getProjeto().getTitulo());
        texto = texto.replaceAll("#coordenador", this.declaracao.getProjeto().getCoordenador().getNome());
        texto = texto.replaceAll("#coordenadorCPF", this.declaracao.getProjeto().getCoordenador().getCpf());
        texto = texto.replaceAll("#destinatario", this.declaracao.getDestinatario().getNome());
        if(this.declaracao.getId() == null){
            texto = texto.replaceAll("#data", (new SimpleDateFormat("dd/mm/yyyy")).format(new Date()));
        }
        else{
            texto = texto.replaceAll("#data", (new SimpleDateFormat("dd/mm/yyyy")).format(this.declaracao.getDataEmissao()));
        }
        try {
            //String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/declaracoes/");
            String path = System.getProperty("java.io.tmpdir");
            arquivoTempName = path+(new Date().getTime())+" Arquivo para imprimir"+".pdf";
            
            HtmlConverter.convertToPdf(texto, new FileOutputStream(arquivoTempName));
            texto = path + texto; //Testes: Ver diretório que é salvo.
            
        } catch (IOException ex) {
            Logger.getLogger(DeclaracaoBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao gerar PDF Declaração!",
                                   "Erro ao gerar PDF Declaração!"));
        }
    }
    
    public void imprimirPDF(Long id){
        if(this.declaracao == null){
            this.declaracao = new Declaracao().buscarPeloId(id);
        }
        gerarPDF();
    }
}