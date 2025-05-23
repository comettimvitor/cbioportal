package org.cbioportal.legacy.web.parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.cbioportal.legacy.utils.removeme.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VirtualStudy extends Session {

  private final Logger LOG = LoggerFactory.getLogger(VirtualStudy.class);
  private VirtualStudyData data;

  @Override
  public void setData(Object data) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      this.data = mapper.readValue(mapper.writeValueAsString(data), VirtualStudyData.class);
    } catch (IOException e) {
      LOG.error("Error occurred", e);
    }
  }

  @Override
  public VirtualStudyData getData() {
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
