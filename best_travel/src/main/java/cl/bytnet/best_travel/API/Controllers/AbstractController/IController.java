package cl.bytnet.best_travel.API.Controllers.AbstractController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IController <RS,ID>{
    public ResponseEntity post(RS request);
    public ResponseEntity get(ID id);
    public ResponseEntity put(ID id,RS request);
    public ResponseEntity<Void>delete(ID id);


}
