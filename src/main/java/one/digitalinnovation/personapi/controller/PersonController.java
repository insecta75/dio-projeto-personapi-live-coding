package one.digitalinnovation.personapi.controller;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Controlador que sera acessado por uma API REST;
@RequestMapping("/api/v1/people") //O caminho de acesso principal da API (atende ao level 1 de Richardson);
public class PersonController {//Anotacao: ponto de entrada do projeto;

    private PersonRepository personRepository; // Injetar da classe Repsitory (Person);
    @Autowired //Injecao de um construtor (facilita testes unitarios);
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping //Mapeando uma operacao HTTP do tipo POST;
    public MessageResponseDTO createPerson(@RequestBody Person person) { //Precisa informar que vem uma requisao do tipo Pessoa;
        Person savedPerson = personRepository.save(person); //Primeira operacao: criacao de usuarios;
        return MessageResponseDTO
                .builder()
                .message("Created person with ID "+savedPerson.getId())
                .build();
    }
}
