package one.digitalinnovation.personapi.mapper;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper { /*MapStruct: disponibiliza atraves de uma interface, no qual um metodo faz a conversao de
um objeto DTO para Entidade e de uma Entidade para um DTO*/

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO); //Convencoes do Mapper: toModel e toDTO;

    PersonDTO toDTO(Person person);
}
