package br.newtonpaiva.dominio.imposto;

import br.newtonpaiva.dominio.Imposto;

public class ImpostoRO implements Imposto {
  public Double getImposto() {
    return 0.17;
  }
}