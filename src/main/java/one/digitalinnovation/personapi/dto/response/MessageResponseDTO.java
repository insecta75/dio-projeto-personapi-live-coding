package one.digitalinnovation.personapi.dto.response;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO { //Criado o objeto MessageResponse tipo DTO
    private String message;
}
