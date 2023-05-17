package dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
public class AuthRequestDTO {//login

    String username;
    String password;
}
