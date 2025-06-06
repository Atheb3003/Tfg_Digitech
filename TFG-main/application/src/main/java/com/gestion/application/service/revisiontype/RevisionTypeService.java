package com.gestion.application.service.revisiontype;

import com.gestion.application.dto.RevisionTypeRequest;
import com.gestion.application.dto.RevisionTypeResponse;
import com.gestion.application.service.revisiontype.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RevisionTypeService {
  private final CreateRevisionTypeImpl create;
  private final GetAllRevisionTypesImpl all;
  private final GetRevisionTypeByIdImpl getById;
  private final UpdateRevisionTypeImpl update;
  private final DeleteRevisionTypeImpl delete;

  public RevisionTypeResponse create(RevisionTypeRequest r) {
    return create.create(r);
  }

  public List<RevisionTypeResponse> getAll() {
    return all.getAll();
  }

  public RevisionTypeResponse getById(Integer id) {
    return getById.getById(id);
  }

  public RevisionTypeResponse update(Integer id, RevisionTypeRequest r) {
    return update.update(id, r);
  }

  public void delete(Integer id) {
    delete.delete(id);
  }
}
