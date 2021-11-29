package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    public Pet save(Pet pet, Long ownerId) {
        pet.setOwnerId(ownerId);

        return this.petRepository.save(pet);
    }

    public List<Pet> getPets() {
        return this.petRepository.findAll();
    }

    public Pet getSinglePet(Long id) {
        return this.petRepository.findById(id).get();
    }

    public List<Pet> getPetsbyOwner(Long id) {
        return this.petRepository.findByOwnerId(id);
    }

    public PetDTO convertPetToDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setType(pet.getType());
        petDTO.setName(pet.getName());
        petDTO.setOwnerId(pet.getOwnerId());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());

        return petDTO;
    }

    public Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet thePet = new Pet();
        thePet.setId(petDTO.getId());
        thePet.setType(petDTO.getType());
        thePet.setName(petDTO.getName());
        thePet.setBirthDate(petDTO.getBirthDate());
        thePet.setNotes(petDTO.getNotes());

        return thePet;
    }

}
