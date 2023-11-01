package dk.kea.namestat.api;

import dk.kea.namestat.dto.NameStatsResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {

    NameService nameService;

    public NameController(NameService nameService) {
        this.nameService = nameService;
    }


    public NameStatsResponse getNameStats(){

    }
}
