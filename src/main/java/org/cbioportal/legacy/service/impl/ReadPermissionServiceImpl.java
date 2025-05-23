package org.cbioportal.legacy.service.impl;

import java.util.Collection;
import org.cbioportal.application.security.CancerStudyPermissionEvaluator;
import org.cbioportal.legacy.model.ReadPermission;
import org.cbioportal.legacy.service.ReadPermissionService;
import org.cbioportal.legacy.utils.security.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ReadPermissionServiceImpl implements ReadPermissionService {

  // The CancerStudyPermissionEvaluator bean does not exist on portals w/o user-authentication
  @Autowired(required = false)
  private CancerStudyPermissionEvaluator cancerStudyPermissionEvaluator;

  @Override
  public void setReadPermission(
      Collection<? extends ReadPermission> entities, Authentication authentication) {
    entities.forEach(
        s -> {
          // Add user read-permission to each entity when authentication is used (defaults
          // to 'true' on portals w/o user-authentication).
          boolean hasReadPermission =
              authentication == null
                  || cancerStudyPermissionEvaluator == null
                  || cancerStudyPermissionEvaluator.hasPermission(
                      authentication, s, AccessLevel.READ);
          s.setReadPermission(hasReadPermission);
        });
  }
}
