package org.cbioportal.legacy.persistence.cachemaputil;

import java.util.Map;
import org.cbioportal.legacy.model.CancerStudy;
import org.cbioportal.legacy.model.MolecularProfile;
import org.cbioportal.legacy.model.SampleList;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
// This implementation of the CacheMapUtils is instantiated on portals where all uses can access any
// study.
@ConditionalOnExpression(
    "'false' eq '${authenticate}' or ('optional_oauth2' eq '${authenticate}' and 'true' ne '${security.method_authorization_enabled}')")
public class InactiveCacheMapUtil implements CacheMapUtil {

  // Since user-permission evaluation is not needed when this bean is present, throw an error when
  // it is accessed.

  @Override
  public Map<String, MolecularProfile> getMolecularProfileMap() {
    throw new RuntimeException(
        "A CacheMapUtils method was called on a portal where studies are accessible to all users.");
  }

  @Override
  public Map<String, SampleList> getSampleListMap() {
    throw new RuntimeException(
        "A CacheMapUtils method was called on a portal where studies are accessible to all users.");
  }

  @Override
  public Map<String, CancerStudy> getCancerStudyMap() {
    throw new RuntimeException(
        "A CacheMapUtils method was called on a portal where studies are accessible to all users.");
  }

  //  bean is only instantiated when there is no user authorization
  @Override
  public boolean hasCacheEnabled() {
    return false;
  }
}
