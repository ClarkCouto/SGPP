package beans;

import com.itextpdf.html2pdf.HtmlConverter;
import entities.Aluno;
import entities.Colaborador;
import entities.Coordenador;
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
    private List<Aluno> alunos;
    private List<Colaborador> colaboradores;
    private List<Coordenador> coordenadores;
    private List<Projeto> projetos;
    private List<TextoBaseDeclaracao> textosBase;
    private Boolean editando;
    private String texto = "ARQUIVO GERADO HERE";
    private StreamedContent file;
    private String arquivoTempName;
    private String projeto;
    private String aluno;
    
// Getters e Setters
    public List<Declaracao> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<Declaracao> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public List<Coordenador> getCoordenadores(){
        this.coordenadores = new Coordenador().buscarTodosCoordenadores();
        return coordenadores;
    }
    
    public Declaracao getDeclaracao() {
        return declaracao;
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

    public Boolean getEditando() {
        return this.editando;
    }
//Editado 26/11/2017, adicionado retorno para SELECT
    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
      
    public List<Aluno> getAlunos(){
        this.alunos = new Aluno().buscarTodos();
        return this.alunos;
    }
      
    public List<Colaborador> getColaboradores(){
        this.colaboradores = new Colaborador().buscarTodos();
        return this.colaboradores;
    }
      
    //Editado 26/11/2017, adicionado retorno para SELECT
    public List<Projeto> getProjetos(){
        this.projetos = new Projeto().buscarTodos();
        return this.projetos;
    }
      
    public List<TextoBaseDeclaracao> getTextosBase(){
        this.textosBase = new TextoBaseDeclaracao().buscarTodos();
        return this.textosBase;
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

        if (this.declaracaoSelecionada != null) {
            this.declaracao = this.declaracaoSelecionada;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "/pages/editar/editarDeclaracao?faces-redirect=true";
    }  
    
    public void limpar(){
        this.editando = false;
        this.declaracao = new Declaracao();
        //texto = "Novo Bean"; //testes para ver se era executado toda vez que entrava na aba
        this.declaracaoSelecionada = new Declaracao();
    }
    
    public String remover(Long id) {
        if(this.declaracao.remover(id))
            return "/pages/listar/listarDeclaracoes?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Declaração!",
                                   "Erro ao excluir Declaração!"));
            return "/pages/listar/listarDeclaracoes";
        }
    }
    
    public String salvar() {
        if(this.declaracao.salvar())
            return "/pages/listar/listarDeclaracoes?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Declaração!",
                                   "Erro ao salvar Declaração!"));
            return "/pages/cadastrar/cadastrarDeclaracao";
        }
    }
    
    //////////////////////para gerar e salvar o PDF
      public void gerarPDF(){
          texto = this.declaracao.getTextoBaseDeclaracao().getTexto();
          String coord = this.declaracao.getResponsavel().getNome();
          String dest = this.declaracao.getDestinatario().getNome();
          //String coord = projeto;
          //String dest = aluno;
                  
          //Tags especiais
          texto = texto.replaceAll("#coordenador", coord);
          texto = texto.replaceAll("#aluno", dest);
          texto = texto.replaceAll("#destinatario", dest);
          texto = texto.replaceAll("#data", (new SimpleDateFormat("dd/mm/yyyy")).format(new Date()));
//          texto.replaceAll("#coordenadorCPF", declaracao.getProjeto().getCoordenador().getCpf());
//          texto.replaceAll("#alunoCPF", declaracao.getProjeto().getCoordenador().getCpf());
                    //... e assim vai nas TAGs possíveis, além de tag de datas
          
        try {
            //String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/declaracoes/");
            String path = System.getProperty("java.io.tmpdir");
            arquivoTempName = path+(new Date().getTime())+" Arquivo para imprimir"+".pdf";
            
            HtmlConverter.convertToPdf(texto, new FileOutputStream(arquivoTempName));
            texto = path + texto; //Testes: Ver diretório que é salvo.
        } catch (IOException ex) {
            Logger.getLogger(DeclaracaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getProjeto() {
        return this.projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }
    
    
      
      
        public List<SelectItem> getTextosBaseSelect(){
        this.textosBase = new TextoBaseDeclaracao().buscarTodos();
        List<SelectItem> items = new ArrayList<>();  
        this.textosBase.forEach((c) -> {
            if (c.getAtivo()){
            items.add(new SelectItem(c, c.getIdentificador()));}
        }); 
        return items;
        }
        
     
        public List<SelectItem> getCoordenadoresSelect(){
            this.coordenadores = new Coordenador().buscarTodosCoordenadores();
            List<SelectItem> items = new ArrayList<>();  
            this.coordenadores.forEach((c) -> {
                if (c.getAtivo()){
                items.add(new SelectItem(c, c.getNome()));}
            }); 
            return items;
        }
        
        

        public void setFile(StreamedContent file) {
        this.file = file;
    }

    //https://pt.stackoverflow.com/questions/49042/como-fazer-download-de-um-arquivo-pdf-com-jsf
    //http://www.guj.com.br/t/resolvido-filedownload-primefaces-jsf/190724/6
    public StreamedContent getFile()  {
        gerarPDF(); //Método que cria o PDF e salva localmente
        //String caminhoWebInf = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/");
        InputStream stream;
        try {
            stream = new FileInputStream(arquivoTempName); //Caminho onde está salvo o arquivo.
            file = new DefaultStreamedContent(stream, "application/pdf", declaracao.getTextoBaseDeclaracao().getIdentificador()+".pdf");  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DeclaracaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return file;  
    } 
    
    
}

