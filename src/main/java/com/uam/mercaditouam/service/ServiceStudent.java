package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.*;
import com.uam.mercaditouam.entities.*;
import com.uam.mercaditouam.repository.IRepoPublication;
import com.uam.mercaditouam.repository.IRepoStudent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceStudent implements IServiceStudent {

    @Autowired
    private IRepoStudent repoStudent;

    @Autowired
    private IRepoPublication repoPublication;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Student> getAll() {
        return repoStudent.findAll();
    }

    @Override
    public void createStudent(StudentDTO studentDTO) {
        Student student = repoStudent.findById(studentDTO.getCIF()).orElse(new Student());

        // Mapear las propiedades del DTO a la entidad Student
        modelMapper.map(studentDTO, student);

        // Mapear las publicaciones del DTO a las entidades Publication
        List<Publication> publications = studentDTO.getPublicationList().stream()
                .map(pubDTO -> modelMapper.map(pubDTO, Publication.class))
                .collect(Collectors.toList());
        student.setPublicationList(publications);

        // No se requiere mapear mensajes, tickets, comentarios y compras ya que se han mapeado con ModelMapper

        // Guardar el estudiante en la base de datos
        repoStudent.save(student);
    }

    @Override
    public void deleteStudent(Long CIF) {
        repoStudent.deleteById(CIF);
    }
}
