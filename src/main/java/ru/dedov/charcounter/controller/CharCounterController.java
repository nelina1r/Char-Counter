package ru.dedov.charcounter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dedov.charcounter.dto.InputStringRequestDto;
import ru.dedov.charcounter.service.interfaces.CountCollisionsService;

@RestController
public class CharCounterController {

    private final CountCollisionsService countCollisionsService;

    @Autowired
    public CharCounterController(CountCollisionsService countCollisionsService) {
        this.countCollisionsService = countCollisionsService;
    }

    @Operation(summary = "Подсчет количества повторений символа в строке")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Подсчет выполнен успешно"),
            @ApiResponse(responseCode = "400", description = "Подсчет не состоялся - текст пуст, очень длинный, либо null")})
    @PostMapping("/count")
    public ResponseEntity<?> checkBrackets(@Valid @RequestBody InputStringRequestDto request, BindingResult bindingResult) {
        String text = request.getInputText();

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        if (text == null || text.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("текст пуст либо null");
        }

        return ResponseEntity.ok(countCollisionsService.countCharCollisions(text));
    }
}
