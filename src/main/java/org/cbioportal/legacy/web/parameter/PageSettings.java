package org.cbioportal.legacy.web.parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.cbioportal.legacy.utils.removeme.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageSettings extends Session {

  private final Logger LOG = LoggerFactory.getLogger(PageSettings.class);
  private PageSettingsData data;

  @Override
  public void setData(Object data) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      this.data = mapper.readValue(mapper.writeValueAsString(data), PageSettingsData.class);
    } catch (IOException e) {
      LOG.error("Error occurred", e);
    }
  }

  @Override
  public PageSettingsData getData() {
    return data;
  }

  @JsonIgnore
  @Override
  public String getSource() {
    return super.getSource();
  }

  @JsonIgnore
  @Override
  public SessionType getType() {
    return super.getType();
  }
}
