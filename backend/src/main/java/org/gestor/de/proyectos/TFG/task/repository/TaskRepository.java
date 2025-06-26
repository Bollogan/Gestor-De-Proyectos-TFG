package org.gestor.de.proyectos.TFG.task.repository;

import java.util.List;
import org.gestor.de.proyectos.TFG.task.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Tarea, Long> {

    @Query("""
        SELECT t FROM Tarea t
        WHERE t.id IN :taskIds
    """)
    List<Tarea> findTasksByTasksIds(List<Long> taskIds);
}
