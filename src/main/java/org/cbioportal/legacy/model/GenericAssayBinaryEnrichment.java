package org.cbioportal.legacy.model;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class GenericAssayBinaryEnrichment extends GenericAssayEnrichment {
  @NotNull private List<GenericAssayCountSummary> counts;

  public List<GenericAssayCountSummary> getCounts() {
    return counts;
  }

  public void setCounts(List<GenericAssayCountSummary> counts) {
    this.counts = counts;
  }
}
