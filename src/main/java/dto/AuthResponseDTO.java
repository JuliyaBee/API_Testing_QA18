package dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor

public class AuthResponseDTO {//ответ сервера для положительного ответа
    String token;
}
