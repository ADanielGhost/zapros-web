package org.polytech.zaprosweb.rest;

import java.util.List;

import org.polytech.zaprosweb.bean.AlternativePackage;
import org.polytech.zaprosweb.bean.Project;
import org.polytech.zaprosweb.bean.User;
import org.polytech.zaprosweb.exception.AlternativePackageNotFoundException;
import org.polytech.zaprosweb.exception.ProjectNotFoundException;
import org.polytech.zaprosweb.exception.UserNotFoundException;
import org.polytech.zaprosweb.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/project")
public class RestProject {

    @Autowired
    private ProjectService projectService;

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @ResponseBody
    @RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
    public Project getProjectById(@PathVariable("projectId") Long projectId) throws ProjectNotFoundException {
        return projectService.getProjectById(projectId);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProject(@RequestBody Project project) {
        projectService.addProject(project);
    }

    @ResponseBody
    @RequestMapping(value = "/{projectId}/alternative/package", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addAlternativePackage(@PathVariable(value = "projectId") Long projectId,
                                      @RequestBody AlternativePackage alternativePackage) throws ProjectNotFoundException {
        projectService.addAlternativePackage(projectId, alternativePackage);
    }

    @ResponseBody
    @RequestMapping(value = "/alternative/package/{alternativePackageId}", method = RequestMethod.GET)
    public AlternativePackage getAlternativePackage(@PathVariable(value = "alternativePackageId") Long alternativePackageId) throws AlternativePackageNotFoundException {
        return projectService.getAlternativePackage(alternativePackageId);
    }

    @ResponseBody
    @RequestMapping(value = "/new/user/{alternativePackageId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User registerUser(@PathVariable(value = "alternativePackageId") Long alternativePackageId,
                             @RequestBody User user) throws AlternativePackageNotFoundException {
        return projectService.registerUser(alternativePackageId, user);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "userId") Long userId) throws UserNotFoundException {
        return projectService.getUserById(userId);
    }
}
