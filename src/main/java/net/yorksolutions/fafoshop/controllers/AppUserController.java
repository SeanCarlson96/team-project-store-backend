package net.yorksolutions.fafoshop.controllers;

import net.yorksolutions.fafoshop.models.AppUser;
import net.yorksolutions.fafoshop.services.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class AppUserController {

    private final AppUserService service;

    public AppUserController(AppUserService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<AppUser> getAll(){
        return service.getAll();
    }

    @GetMapping(params = {"email", "password"})
    public AppUser getUserByEmailAndPassword(
            @RequestParam String email,
            @RequestParam String password
    ){
        try{
            return service.getUserByEmailAndPassword(email, password);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public void createAppUser(
            @RequestBody AppUser appUser
    ){
        try{
            service.createAppUser(appUser);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAppUserById(
        @PathVariable Long id
    ){
        try{
            service.deleteAppUserById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }




}