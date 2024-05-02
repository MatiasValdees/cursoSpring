package cl.bytnet.best_travel.API.Controllers;


import cl.bytnet.best_travel.Infraestructure.AbstractService.ModifyUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("user")
@AllArgsConstructor
@Tag(name = "user")
public class AppUserController {
    private final ModifyUserService modifyUserService;
    @PatchMapping
    @Operation(summary = "enabled or disabled user")
    public ResponseEntity<Map<String,Boolean>>enabledOrDisables(@RequestParam String username){
        return ResponseEntity.ok(modifyUserService.enabled(username));
    }

    @PatchMapping(path = "add-role")
    @Operation(summary = "Add role to user")
    public ResponseEntity<Map<String, Set<String>>> addRole(@RequestParam String username,@RequestParam String role){
        return ResponseEntity.ok(modifyUserService.addRole(username,role));
    }

    @PatchMapping(path = "remove-role")
    @Operation(summary = "remove role to user")
    public ResponseEntity<Map<String, Set<String>>> removeRole(@RequestParam String username,@RequestParam String role){
        return ResponseEntity.ok(modifyUserService.removeRole(username,role));
    }
}
