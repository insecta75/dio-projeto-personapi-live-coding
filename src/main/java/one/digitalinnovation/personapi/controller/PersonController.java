package one.digitalinnovation.personapi.controller;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController //Controlador que sera acessado por uma API REST;
@RequestMapping("/api/v1/people") //O caminho de acesso principal da API (atende ao level 1 de Richardson);
public class PersonController {//Anotacao: ponto de entrada do projeto;

    private PersonService personService;

    @Autowired
    public PersonController (PersonService personService) {
        this.personService = personService;
    }

    @PostMapping //Mapeando uma operacao HTTP do tipo POST;
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) { //Precisa informar que vem uma requisao do tipo Pessoa;

        return personService.createPerson(personDTO);
    }

    @GetMapping //Mapeando uma operacao HTTP do tipo GET;
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }
}
