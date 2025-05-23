package org.cbioportal.legacy.model;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public class GenePanelToGene implements Serializable {

  private String genePanelId;
  @NotNull private Integer entrezGeneId;
  @NotNull private String hugoGeneSymbol;

  public String getGenePanelId() {
    return genePanelId;
  }

  public void setGenePanelId(String genePanelId) {
    this.genePanelId = genePanelId;
  }

  public Integer getEntrezGeneId() {
    return entrezGeneId;
  }

  public void setEntrezGeneId(Integer entrezGeneId) {
    this.entrezGeneId = entrezGeneId;
  }

  public String getHugoGeneSymbol() {
    return hugoGeneSymbol;
  }

  public void setHugoGeneSymbol(String hugoGeneSymbol) {
    this.hugoGeneSymbol = hugoGeneSymbol;
  }
}
