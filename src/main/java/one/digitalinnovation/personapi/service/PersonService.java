package one.digitalinnovation.personapi.service;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service /*Indica para gerenciar uma classe, que sera responsavel
por colocar todas as regras de negocios na aplicacao;*/
public class PersonService { //Regra de criacao delegada para essa classe de Service;
    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public MessageResponseDTO createPerson(PersonDTO personDTO) { //Precisa informar que vem uma requisao do tipo Pessoa;
        Person personToSave = personMapper.toModel(personDTO); //Codigo abaixo é igual a isso;
        /*Person personToSave = Person.builder()
                                    .firstName(personDTO.getFirstName())
                                    .lastName(personDTO.getLastName())
                                    .birthDate(personDTO.getBirthDate())
                                    .phones(personDTO.getPhones())
                                    .build();*/

        Person savedPerson = personRepository.save(personToSave); //Primeira operacao: criacao de usuarios;
        return MessageResponseDTO
                .builder()
                .message("Created person with ID "+savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll(); //Para listar todos;
        /*Tem converter esses objetos allPeople em uma lista Person DTO;
        usa-se o stream para transformar dados em colecoes;*/
        return allPeople.stream()
                .map(personMapper::toDTO) //converte cada linha do allPeople em um DTO;
                .collect(Collectors.toList());
    }
}