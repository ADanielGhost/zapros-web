package org.polytech.zaprosweb.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polytech.zaprosweb.bean.AlternativePackage;
import org.polytech.zaprosweb.bean.Project;
import org.polytech.zaprosweb.bean.User;
import org.polytech.zaprosweb.dao.AlternativePackageDAO;
import org.polytech.zaprosweb.dao.ProjectDAO;
import org.polytech.zaprosweb.dao.UserDAO;
import org.polytech.zaprosweb.dao.entity.AlternativePackageEntity;
import org.polytech.zaprosweb.dao.entity.ProjectEntity;
import org.polytech.zaprosweb.exception.AlternativePackageNotFoundException;
import org.polytech.zaprosweb.exception.ProjectNotFoundException;
import org.polytech.zaprosweb.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired private ProjectDAO projectDAO;
    @Autowired private AlternativePackageDAO alternativePackageDAO;
    @Autowired private UserDAO userDAO;

    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    public void addProject(Project project) {
        projectDAO.addProject(project);
    }

    public Project getProjectById(Long projectId) throws ProjectNotFoundException {
        return projectDAO.getProjectById(projectId).toModel();
    }

    public void addAlternativePackage(Long projectId, AlternativePackage alternativePackage) throws ProjectNotFoundException {
        ProjectEntity projectEntity = projectDAO.getProjectById(projectId);
        alternativePackageDAO.addAlternativePackage(projectEntity, alternativePackage);
    }

    public AlternativePackage getAlternativePackage(Long alternativePackageId) throws AlternativePackageNotFoundException {
        return alternativePackageDAO.getAlternativePackage(alternativePackageId).toModel();
    }

    public User registerUser(Long alternativePackageId, User user) throws AlternativePackageNotFoundException {
        AlternativePackageEntity alternativePackageEntity = alternativePackageDAO.getAlternativePackage(alternativePackageId);
        return userDAO.registerUser(alternativePackageEntity, user).toModel();
    }

    public User getUserById(Long userId) throws UserNotFoundException {
        return userDAO.getUserById(userId).toModel();
    }
}
