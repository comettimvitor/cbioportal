package org.cbioportal.legacy.service;

import java.util.List;
import org.cbioportal.legacy.model.ResourceData;
import org.cbioportal.legacy.service.exception.PatientNotFoundException;
import org.cbioportal.legacy.service.exception.SampleNotFoundException;
import org.cbioportal.legacy.service.exception.StudyNotFoundException;

public interface ResourceDataService {

  List<ResourceData> getAllResourceDataOfSampleInStudy(
      String studyId,
      String sampleId,
      String resourceId,
      String projection,
      Integer pageSize,
      Integer pageNumber,
      String sortBy,
      String direction)
      throws SampleNotFoundException, StudyNotFoundException;

  List<ResourceData> getAllResourceDataOfPatientInStudy(
      String studyId,
      String patientId,
      String resourceId,
      String projection,
      Integer pageSize,
      Integer pageNumber,
      String sortBy,
      String direction)
      throws PatientNotFoundException, StudyNotFoundException;

  List<ResourceData> getAllResourceDataForStudy(
      String studyId,
      String resourceId,
      String projection,
      Integer pageSize,
      Integer pageNumber,
      String sortBy,
      String direction)
      throws StudyNotFoundException;

  List<ResourceData> getAllResourceDataForStudyPatientSample(
      String studyId,
      String resourceId,
      String projection,
      Integer pageSize,
      Integer pageNumber,
      String sortBy,
      String direction)
      throws StudyNotFoundException;
}
