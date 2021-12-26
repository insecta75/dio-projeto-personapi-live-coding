package one.digitalinnovation.personapi.service;
import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service /*Indica para gerenciar uma classe, que sera responsavel
por colocar todas as regras de negocios na aplicacao;*/
@AllArgsConstructor(onConstructor = @__(@Autowired)) //Para tirar o constructor padrao;
public class PersonService { //Regra de criacao delegada para essa classe de Service;
    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    /*@Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }*/
    public MessageResponseDTO createPerson(PersonDTO personDTO) { //Precisa informar que vem uma requisao do tipo Pessoa;
        Person personToSave = personMapper.toModel(personDTO); //Codigo abaixo é igual a isso;
        /*Person personToSave = Person.builder()
                                    .firstName(personDTO.getFirstName())
                                    .lastName(personDTO.getLastName())
                                    .birthDate(personDTO.getBirthDate())
                                    .phones(personDTO.getPhones())
                                    .build();*/
        Person savedPerson = personRepository.save(personToSave); //Primeira operacao: criacao de usuarios;
        return createMessageResponse(savedPerson.getId(), "Created person with ID "); // Trecho refatorado;
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll(); //Para listar todos;
        /*Tem converter esses objetos allPeople em uma lista Person DTO;
        usa-se o stream para transformar dados em colecoes;*/
        return allPeople.stream()
                .map(personMapper::toDTO) //converte cada linha do allPeople em um DTO;
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id); // Mesma operacao de criacao, mas com a verificacao se o Id ja existe;
        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID "); // Trecho refatorado;
    }
    // Primeiros os metodos publicos, e no final os privados;
    private Person verifyIfExists(Long id) throws PersonNotFoundException { //Evitar codigo repetitivo;
        return personRepository
                .findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}