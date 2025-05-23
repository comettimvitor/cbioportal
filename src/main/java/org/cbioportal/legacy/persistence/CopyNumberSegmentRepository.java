package org.cbioportal.legacy.persistence;

import java.util.List;
import org.cbioportal.legacy.model.CopyNumberSeg;
import org.cbioportal.legacy.model.meta.BaseMeta;
import org.springframework.cache.annotation.Cacheable;

public interface CopyNumberSegmentRepository {

  @Cacheable(
      cacheResolver = "generalRepositoryCacheResolver",
      condition = "@cacheEnabledConfig.getEnabled()")
  List<CopyNumberSeg> getCopyNumberSegmentsInSampleInStudy(
      String studyId,
      String sampleId,
      String chromosome,
      String projection,
      Integer pageSize,
      Integer pageNumber,
      String sortBy,
      String direction);

  @Cacheable(
      cacheResolver = "generalRepositoryCacheResolver",
      condition = "@cacheEnabledConfig.getEnabled()")
  BaseMeta getMetaCopyNumberSegmentsInSampleInStudy(
      String studyId, String sampleId, String chromosome);

  @Cacheable(
      cacheResolver = "generalRepositoryCacheResolver",
      condition = "@cacheEnabledConfig.getEnabled()")
  List<Integer> fetchSamplesWithCopyNumberSegments(
      List<String> studyIds, List<String> sampleIds, String chromosome);

  @Cacheable(
      cacheResolver = "generalRepositoryCacheResolver",
      condition = "@cacheEnabledConfig.getEnabled()")
  List<CopyNumberSeg> fetchCopyNumberSegments(
      List<String> studyIds, List<String> sampleIds, String chromosome, String projection);

  @Cacheable(
      cacheResolver = "generalRepositoryCacheResolver",
      condition = "@cacheEnabledConfig.getEnabled()")
  BaseMeta fetchMetaCopyNumberSegments(
      List<String> studyIds, List<String> sampleIds, String chromosome);

  @Cacheable(
      cacheResolver = "generalRepositoryCacheResolver",
      condition = "@cacheEnabledConfig.getEnabled()")
  List<CopyNumberSeg> getCopyNumberSegmentsBySampleListId(
      String studyId, String sampleListId, String chromosome, String projection);
}
