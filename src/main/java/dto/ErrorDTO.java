package dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor

public class ErrorDTO {//ответ сервера для негативного ответа ошибки 400 401 402,,,409
    /*
    {
  "timestamp": "2023-05-16T16:18:07.377Z",
  "status": 0,
  "error": "string",
  "message": {},
  "path": "string"
}
     */
    int status;
    String error;
    String message;

}
