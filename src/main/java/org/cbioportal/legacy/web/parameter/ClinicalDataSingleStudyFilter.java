package org.cbioportal.legacy.web.parameter;

import static org.cbioportal.legacy.web.parameter.PagingConstants.MAX_PAGE_SIZE;

import jakarta.validation.constraints.Size;
import java.util.List;
import org.cbioportal.legacy.web.ClinicalDataController;

public class ClinicalDataSingleStudyFilter {

  @Size(min = 1, max = ClinicalDataController.CLINICAL_DATA_MAX_PAGE_SIZE)
  private List<String> ids;

  @Size(min = 1, max = MAX_PAGE_SIZE)
  private List<String> attributeIds;

  public List<String> getIds() {
    return ids;
  }

  public void setIds(List<String> ids) {
    this.ids = ids;
  }

  public List<String> getAttributeIds() {
    return attributeIds;
  }

  public void setAttributeIds(List<String> attributeIds) {
    this.attributeIds = attributeIds;
  }
}
