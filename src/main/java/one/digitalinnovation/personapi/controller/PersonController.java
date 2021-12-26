package one.digitalinnovation.personapi.controller;
import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController //Controlador que sera acessado por uma API REST;
@RequestMapping("/api/v1/people") //O caminho de acesso principal da API (atende ao level 1 de Richardson);
@AllArgsConstructor(onConstructor = @__(@Autowired)) //Para tirar o constructor padrao;
public class PersonController {//Anotacao: ponto de entrada do projeto;

    private PersonService personService;

    /*@Autowired
    public PersonController (PersonService personService) {
        this.personService = personService;
    }*/
    @PostMapping //Mapeando uma operacao HTTP do tipo POST;
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) { //Precisa informar que vem uma requisao do tipo Pessoa;
        return personService.createPerson(personDTO);
    }
    @GetMapping //Mapeando uma operacao HTTP do tipo GET;
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }
    @GetMapping("/{id}") //Necessario colocar para evitar conflito com o listAll;
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException { //PathVariable: necessario para o mapeamento (http para atributo do metodo);
        return personService.findById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }
}
