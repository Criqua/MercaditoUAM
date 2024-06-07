package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.AdministratorDTO;
import com.uam.mercaditouam.dto.TicketDTO;
import com.uam.mercaditouam.entities.Administrator;
import com.uam.mercaditouam.repository.IRepoAdministrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAdministrator implements IServiceAdministrator{

    @Autowired
    private IRepoAdministrator repoAdministrator;

    @Override
    public List<Administrator> getAll() {
        return repoAdministrator.findAll();
    }

    @Override
    public void createAdministrator(AdministratorDTO administratorDTO) {
        Administrator administrator = repoAdministrator.findById(administratorDTO.getCIF()).orElse(null);
        if(administrator == null) {
            administrator = new Administrator();
            administrator.setCIF(administratorDTO.getCIF());
            administrator.setName(administratorDTO.getName());
            administrator.setSurname(administratorDTO.getSurname());
            administrator.setEmail(administratorDTO.getEmail());
            administrator.setProfilePhoto(administratorDTO.getProfilePhoto());
        }
        List<TicketDTO> tickets = administratorDTO.getTicketListDTO();
        repoAdministrator.save(administrator);
    }

    @Override
    public void deleteAdministrator(Long id) {
        repoAdministrator.deleteById(id);
    }
}
