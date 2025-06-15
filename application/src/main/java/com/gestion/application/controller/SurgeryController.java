package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.FullDetailSurgeryResponse;
import com.gestion.application.service.surgery.impl.GetVisibleSurgeriesImpl;
import com.gestion.application.service.surgery.impl.SearchSurgeriesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surgeries")
@RequiredArgsConstructor
public class SurgeryController {

  private final GetVisibleSurgeriesImpl visibleSurgeriesService;
  private final SearchSurgeriesImpl searchSurgeriesService;

  @GetMapping("/visible")
  public ResponseEntity<ApiResponse<Page<FullDetailSurgeryResponse>>> getVisibleSurgeries(
      @PageableDefault(sort = "date", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<FullDetailSurgeryResponse> page = visibleSurgeriesService.getVisibleSurgeries(pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", page));
  }

  @GetMapping("/search/{search}")
  public ResponseEntity<ApiResponse<Page<FullDetailSurgeryResponse>>> searchSurgeries(
      @PathVariable String search,
      @PageableDefault(sort = "date", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<FullDetailSurgeryResponse> page =
        searchSurgeriesService.searchVisibleSurgeries(search, pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", page));
  }
}
