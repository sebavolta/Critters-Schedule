package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet thePet = this.petService.convertPetDTOToPet(petDTO);
        this.petService.save(thePet, thePet.getOwnerId());

        return petDTO;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet thePet = this.petService.getSinglePet(petId);

        return this.petService.convertPetToDTO(thePet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> thePets = this.petService.getPets();

        return thePets.stream().map(pet ->
                this.petService.convertPetToDTO(pet)
        ).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        List<Pet> thePets = this.petService.getPetsbyOwner(ownerId);

        return thePets.stream().map(pet ->
                this.petService.convertPetToDTO(pet)
        ).collect(Collectors.toList());
    }
}
