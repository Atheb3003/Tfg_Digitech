package com.gestion.application.service.revision;

import com.gestion.application.dto.CreateRevisionRequest;
import com.gestion.application.dto.RevisionResponse;
import com.gestion.application.service.revision.impl.CreateRevisionImpl;
import com.gestion.application.service.revision.impl.DeleteRevisionImpl;
import com.gestion.application.service.revision.impl.GetAllRevisionsImpl;
import com.gestion.application.service.revision.impl.GetContactRevisionsImpl;
import com.gestion.application.service.revision.impl.GetRevisionByIdImpl;
import com.gestion.application.service.revision.impl.GetRevisionsByTypeImpl;
import com.gestion.application.service.revision.impl.GetVisibleRevisionsImpl;
import com.gestion.application.service.revision.impl.SetRevisionInvisibleImpl;
import com.gestion.application.service.revision.impl.SetRevisionVisibleImpl;
import com.gestion.application.service.revision.impl.UpdateRevisionImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase unificadora que expone todos los métodos para Revision.
 * Se inyectan las implementaciones específicas y se delega en ellas.
 */
@Service
@RequiredArgsConstructor
public class RevisionService {

    private final CreateRevisionImpl       createRevisionImpl;
    private final UpdateRevisionImpl       updateRevisionImpl;
    private final DeleteRevisionImpl       deleteRevisionImpl;
    private final GetAllRevisionsImpl      getAllRevisionsImpl;
    private final GetVisibleRevisionsImpl  getVisibleRevisionsImpl;
    private final GetContactRevisionsImpl  getContactRevisionsImpl;
    private final GetRevisionByIdImpl      getRevisionByIdImpl;
    private final GetRevisionsByTypeImpl   getRevisionsByTypeImpl;
    private final SetRevisionVisibleImpl   setRevisionVisibleImpl;
    private final SetRevisionInvisibleImpl setRevisionInvisibleImpl;

    /**
     * Crea una nueva revisión.
     */
    public RevisionResponse createRevision(CreateRevisionRequest req) {
        return createRevisionImpl.execute(req);
    }

    /**
     * Actualiza una revisión existente.
     */
    public RevisionResponse updateRevision(Integer id, CreateRevisionRequest req) {
        return updateRevisionImpl.execute(id, req);
    }

    /**
     * Elimina físicamente una revisión.
     */
    public void deleteRevision(Integer id) {
        deleteRevisionImpl.execute(id);
    }

    /**
     * Lista todas las revisiones (visibles e invisibles).
     */
    public List<RevisionResponse> getAllRevisions() {
        return getAllRevisionsImpl.execute();
    }

    /**
     * Lista solo las revisiones visibles (isVisible = true).
     */
    public List<RevisionResponse> getVisibleRevisions() {
        return getVisibleRevisionsImpl.execute();
    }

    /**
     * Lista revisiones asociadas a un contacto.
     */
    public List<RevisionResponse> getRevisionsByContact(Integer contactId) {
        return getContactRevisionsImpl.execute(contactId);
    }

    /**
     * Obtiene una revisión por su ID.
     */
    public RevisionResponse getRevisionById(Integer id) {
        return getRevisionByIdImpl.execute(id);
    }

    /**
     * Lista revisiones por tipo de revisión.
     */
    public List<RevisionResponse> getRevisionsByType(Integer typeId) {
        return getRevisionsByTypeImpl.execute(typeId);
    }

    /**
     * Marca una revisión como visible.
     */
    public RevisionResponse makeRevisionVisible(Integer id) {
        return setRevisionVisibleImpl.execute(id);
    }

    /**
     * Marca una revisión como invisible.
     */
    public RevisionResponse makeRevisionInvisible(Integer id) {
        return setRevisionInvisibleImpl.execute(id);
    }
}
