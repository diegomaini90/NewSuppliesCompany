package br.newtonpaiva.dominio.imposto;

import br.newtonpaiva.dominio.Imposto;

public class ImpostoBA implements Imposto {
  public Double getImposto() {
    return 0.18;
  }
}