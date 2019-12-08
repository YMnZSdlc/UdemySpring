package pl.ymz.udemyspring.lang;

import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
class LangService {
    private LangRepository repository;

    LangService(LangRepository repository) {
        this.repository = repository;
    }

    List<LangDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(lang -> new LangDTO(lang))
                .collect(toList());
    }


}
