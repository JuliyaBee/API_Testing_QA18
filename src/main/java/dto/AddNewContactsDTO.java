package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor

public class AddNewContactsDTO {

    String id;
    String name;
    String lastName;
    String email;
    String phone;
    String address;
    String description;
}
