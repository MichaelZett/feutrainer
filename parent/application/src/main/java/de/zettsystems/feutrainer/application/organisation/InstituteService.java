package de.zettsystems.feutrainer.application.organisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.zettsystems.feutrainer.domain.organisation.InstituteRepository;

@Component
public class InstituteService {
	@Autowired
	InstituteRepository instituteRepository;

}
