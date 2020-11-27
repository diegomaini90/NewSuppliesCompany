package br.newtonpaiva.dominio.geradorArquivo;

import java.io.IOException;
import java.io.FileOutputStream;

import br.newtonpaiva.dominio.Pedido;

public abstract class GeradorArquivo {
  public void gerar(String nomeArquivo, Pedido pedido) throws IOException {
    // Converter o pedido para uma formato (XML, Propriedades. CSV, JSON, Yaml)
    String conteudo = getConteudo(pedido);

    // Converter String para Bytes
    byte[] b = conteudo.getBytes();

    b = posProcessamento(b);

    // Gravar o arquivo no disco
    FileOutputStream out = new FileOutputStream(nomeArquivo);
    out.write(b);
    out.close();
  }

  protected abstract String getConteudo(Pedido pedido);

  protected byte[] posProcessamento(byte[] b) {
    return b;
  }
}