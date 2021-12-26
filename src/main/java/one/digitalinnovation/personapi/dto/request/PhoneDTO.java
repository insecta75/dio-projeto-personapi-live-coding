package one.digitalinnovation.personapi.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.enums.PhoneType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO { /*DTO: objeto criado para fazer a transferencia de dados,
ou seja, o objeto responsavel por receber todos os dados de entrada, sendo mapeados no DTO
em vez de na entidade*/

    private Long id;

    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @NotEmpty // Para validacao dos dados de entrada;
    @Size(min = 13, max = 14)
    private String number;
}
