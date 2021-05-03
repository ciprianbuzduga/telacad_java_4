/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.clinicamed.rest;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.telacad.clinicamed.service.MapPatientsService;


@RestController
public class RestCNPService {
    
    @Autowired
    private MapPatientsService mapPatientsService;
    
    @GetMapping("/searchNotMappedPatients")
    public List<String> getCnpLikeQuery(@RequestParam("userId") String userId,
            @RequestParam("query") String query) {
        if(query == null || userId == null) {
            return null;
        }   
        return mapPatientsService.listCnpsNotUser(Integer.valueOf(userId), query);
    }
}
