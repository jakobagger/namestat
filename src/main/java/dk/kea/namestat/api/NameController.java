package dk.kea.namestat.api;

import dk.kea.namestat.dto.NameStatsResponse;
import dk.kea.namestat.service.NameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {

    NameService nameService;

    public NameController(NameService nameService) {
        this.nameService = nameService;
    }



        @GetMapping()
        public NameStatsResponse getInfoFromName(@RequestParam String name) {
            NameStatsResponse nameStatsResponse = nameService.getInfo(name);
            return nameStatsResponse;
        }

}
