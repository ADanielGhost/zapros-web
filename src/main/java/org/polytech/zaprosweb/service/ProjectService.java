package org.polytech.zaprosweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.polytech.zaprosweb.bean.AlternativePackage;
import org.polytech.zaprosweb.bean.Criteria;
import org.polytech.zaprosweb.bean.Project;
import org.polytech.zaprosweb.dao.AlternativeDAO;
import org.polytech.zaprosweb.dao.AlternativePackageDAO;
import org.polytech.zaprosweb.dao.AssessmentDAO;
import org.polytech.zaprosweb.dao.CriteriaDAO;
import org.polytech.zaprosweb.dao.ProjectDAO;
import org.polytech.zaprosweb.entity.AlternativePackageEntity;
import org.polytech.zaprosweb.entity.AssessmentEntity;
import org.polytech.zaprosweb.entity.CriteriaEntity;
import org.polytech.zaprosweb.entity.ProjectEntity;
import org.polytech.zaprosweb.exception.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired private AlternativeDAO alternativeDAO;
    @Autowired private AlternativePackageDAO alternativePackageDAO;
    @Autowired private AssessmentDAO assessmentDAO;
    @Autowired private CriteriaDAO criteriaDAO;
    @Autowired private ProjectDAO projectDAO;

    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    public void addProject(Project project) {
        projectDAO.addProject(project);
    }

    public Project getProjectById(Long projectId) throws ProjectNotFoundException {
        return Project.of(projectDAO.getProjectById(projectId));
    }

    public void addAlternativePackage(Long projectId, AlternativePackage alternativePackage) throws ProjectNotFoundException {
        ProjectEntity projectEntity = projectDAO.getProjectById(projectId);
        alternativePackageDAO.addAlternativePackage(projectEntity, alternativePackage);
    }
}
