package org.cbioportal.legacy.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import org.cbioportal.legacy.model.Gistic;
import org.cbioportal.legacy.service.SignificantCopyNumberRegionService;
import org.cbioportal.legacy.service.exception.StudyNotFoundException;
import org.cbioportal.legacy.web.config.annotation.InternalApi;
import org.cbioportal.legacy.web.parameter.Direction;
import org.cbioportal.legacy.web.parameter.HeaderKeyConstants;
import org.cbioportal.legacy.web.parameter.PagingConstants;
import org.cbioportal.legacy.web.parameter.Projection;
import org.cbioportal.legacy.web.parameter.sort.SignificantCopyNumberRegionSortBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@InternalApi
@RestController()
@RequestMapping("/api")
@Validated
@Tag(name = "Significant Copy Number Regions", description = " ")
public class SignificantCopyNumberRegionController {

  @Autowired private SignificantCopyNumberRegionService significantCopyNumberRegionService;

  @PreAuthorize(
      "hasPermission(#studyId, 'CancerStudyId', T(org.cbioportal.legacy.utils.security.AccessLevel).READ)")
  @RequestMapping(
      value = "/studies/{studyId}/significant-copy-number-regions",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(description = "Get significant copy number alteration regions in a study")
  @ApiResponse(
      responseCode = "200",
      description = "OK",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gistic.class))))
  public ResponseEntity<List<Gistic>> getSignificantCopyNumberRegions(
      @Parameter(required = true, description = "Study ID e.g. acc_tcga") @PathVariable
          String studyId,
      @Parameter(description = "Level of detail of the response")
          @RequestParam(defaultValue = "SUMMARY")
          Projection projection,
      @Parameter(description = "Page size of the result list")
          @Max(PagingConstants.MAX_PAGE_SIZE)
          @Min(PagingConstants.MIN_PAGE_SIZE)
          @RequestParam(defaultValue = PagingConstants.DEFAULT_PAGE_SIZE)
          Integer pageSize,
      @Parameter(description = "Page number of the result list")
          @Min(PagingConstants.MIN_PAGE_NUMBER)
          @RequestParam(defaultValue = PagingConstants.DEFAULT_PAGE_NUMBER)
          Integer pageNumber,
      @Parameter(description = "Name of the property that the result list is sorted by")
          @RequestParam(required = false)
          SignificantCopyNumberRegionSortBy sortBy,
      @Parameter(description = "Direction of the sort") @RequestParam(defaultValue = "ASC")
          Direction direction)
      throws StudyNotFoundException {

    if (projection == Projection.META) {
      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.add(
          HeaderKeyConstants.TOTAL_COUNT,
          significantCopyNumberRegionService
              .getMetaSignificantCopyNumberRegions(studyId)
              .getTotalCount()
              .toString());
      return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(
          significantCopyNumberRegionService.getSignificantCopyNumberRegions(
              studyId,
              projection.name(),
              pageSize,
              pageNumber,
              sortBy == null ? null : sortBy.getOriginalValue(),
              direction.name()),
          HttpStatus.OK);
    }
  }
}
