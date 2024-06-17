package br.com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restaurant.model.Experience;
import br.com.restaurant.model.Usuario;
import br.com.restaurant.service.AuthService;
import br.com.restaurant.service.ExperienceService;

@RestController
@RequestMapping("/experiences")
public class ExperienceController {

	@Autowired
    private ExperienceService experienceService;

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience,
                                                       @RequestHeader(name="token") String token) {
        if (!authService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Usuario user = authService.getUserFromToken(token);
        experience.setUsuario(user);
        Experience createdExperience = experienceService.save(experience);
        return ResponseEntity.ok(createdExperience);
    }
}