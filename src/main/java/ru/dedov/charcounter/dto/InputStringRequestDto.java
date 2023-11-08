package ru.dedov.charcounter.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputStringRequestDto {

    @Size(max = 32, message = "текст не должен содержать больше 32 знаков")
    private String inputText;
}
