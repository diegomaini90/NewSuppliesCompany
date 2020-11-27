import java.io.IOException;
import br.newtonpaiva.dominio.*;
import br.newtonpaiva.dominio.frete.*;
import br.newtonpaiva.dominio.imposto.*;
import br.newtonpaiva.dominio.geradorArquivo.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class Main {
  public static void main(String[] args) {
        
//        EntityManager em = Persistence
//                                .createEntityManagerFactory("NewSuppliesCompanyPU")
//                                .createEntityManager();
        
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewSuppliesCompanyPU");
    EntityManager em = emf.createEntityManager();
    
    
    em.getTransaction().begin();
    Categoria c1 = new Categoria();
    c1.setNome("Jogos");
    
    Produto p1 = new Produto();
    p1.setNome("Mouse sem fio");
    p1.setPreco(143.99);
    p1.setCategoria(c1);

    Produto p2 = new Produto();
    p2.setNome("Notebook Dell");
    p2.setPreco(3543.99);
    p2.setCategoria(c1);
    
    c1.getProdutos().add(p1);
    c1.getProdutos().add(p2);
    
    em.persist(c1);
    em.getTransaction().commit();
    
    em.getTransaction().begin();    
    Categoria c2 = new Categoria();
    c2.setNome("Smart TV");
    em.persist(c2);   
    
    em.getTransaction().commit();
    em.close();
    emf.close();
        
//    Produto p1 = new Produto();
//    p1.setNome("Mouse sem fio");
//    p1.setPreco(143.99);
//
//    Produto p2 = new Produto();
//    p2.setNome("Notebook Dell");
//    p2.setPreco(3543.99);

    Cliente c = new Cliente();
    c.setNome("Tarley");

    /*
    PedidoBuilder builder = new PedidoBuilder();
    builder.addProduto(p1, 2);
    builder.addProduto(p2, 1);
    builder.setCliente(c);
    builder.setEnderecoEntrega(new Endereco(
      "MG", "Contagem", "Europa", "32.155-054", "Rua teste", "1987"
    ));
    
    Pedido p = builder.getResultado();
    */

    Pedido p = new PedidoBuilder()
                    .addProduto(p1, 2)
                    .addProduto(p2, 1)
                    .setCliente(c)
                    .setEnderecoEntrega(new Endereco(
                      "MG", "Contagem", "Europa", "32.155-054", "Rua teste", "1987"
                    ))
                    .getResultado();

    System.out.println(p);

    try {
      GeradorArquivoXML xml = new GeradorArquivoXML();
      xml.gerar("teste.xml", p);

      GeradorArquivoJson json = new GeradorArquivoJson();
      json.gerar("teste.json", p);
    }catch(IOException e) {
      e.printStackTrace();
    }
    
    /*
    System.out.println("Hello world!");

    
    

    ItemPedido i1 = new ItemPedido();
    i1.setPreco(p1.getPreco());
    i1.setQuantidade(2);
    i1.setProduto(p1);


    Pedido p = new Pedido(100);
    //p.setCalculoFrete(new FreteMG());
    //p.setCalculoFrete(new FreteRJ());
    p.setCalculoFrete(new FreteES());
    p.getItens().add(i1);
    p.setCliente(c);


    System.out.println("Valor total do pedido = " + p.obterTotal());
    */


  }

}

/*
class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    Cliente c = new Cliente();
    c.setNome("Diego");

    Produto p1 = new Produto();
    p1.setNome("Cadeira");
    p1.setPreco(600.00);

    ItemPedido i1 = new ItemPedido();
    i1.setPreco(p1.getPreco());
    i1.setQuantidade(2);
    i1.setProduto(p1);


    Pedido p = new Pedido(100);
    p.setCalculoImposto(new ImpostoMG());
    p.setCalculoFrete(new FreteMG());
    //p.setCalculoFrete(new FreteRJ());
    //p.setCalculoFrete(new FreteES());
    p.getItens().add(i1);
    p.setCliente(c);


    System.out.println("Valor total do pedido = " + p.obterTotal());
    
    



  }
}
*/
