package org.polytech.zaprosweb.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.polytech.zaprosweb.bean.Project;
import org.polytech.zaprosweb.dao.entity.ProjectEntity;
import org.polytech.zaprosweb.dao.repository.ProjectRepository;
import org.polytech.zaprosweb.exception.ProjectNotFoundException;
import org.polytech.zaprosweb.utils.IterableListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectDAO {

    @Autowired private ProjectRepository projectRepository;
    @Autowired private CriteriaDAO criteriaDAO;
    @Autowired private AssessmentDAO assessmentDAO;
    @Autowired private QuasiExpertConfigDAO quasiExpertConfigDAO;

    public ProjectEntity getProjectById(Long projectId) throws ProjectNotFoundException {
        return projectRepository
            .findById(projectId)
            .orElseThrow(ProjectNotFoundException::new);
    }

    public List<Project> getAllProjects() {
        return IterableListUtils.cast(projectRepository.findAll())
            .stream()
            .map(ProjectEntity::toModel)
            .collect(Collectors.toList());
    }

    public void addProject(Project project) {
        ProjectEntity entity = new ProjectEntity();
        entity.setName(project.getName());

        ProjectEntity projectEntity = projectRepository.save(entity);
        quasiExpertConfigDAO.addConfig(projectEntity, project.getCriteriaList());
        criteriaDAO.addCriteriaList(projectEntity, project.getCriteriaList());
    }
}
