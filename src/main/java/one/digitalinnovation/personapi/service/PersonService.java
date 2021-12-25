package one.digitalinnovation.personapi.service;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service /*Indica para gerenciar uma classe, que sera responsavel
por colocar todas as regras de negocios na aplicacao;*/
public class PersonService { //Regra de criacao delegada para essa classe de Service;
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public MessageResponseDTO createPerson(Person person) { //Precisa informar que vem uma requisao do tipo Pessoa;
        Person savedPerson = personRepository.save(person); //Primeira operacao: criacao de usuarios;
        return MessageResponseDTO
                .builder()
                .message("Created person with ID "+savedPerson.getId())
                .build();
    }
}
