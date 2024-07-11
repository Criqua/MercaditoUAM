package com.uam.mercaditouam.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Categoria")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Categoria")
    private Long id;

    @Column(name = "Nombre")
    private String name;

    @OneToMany(mappedBy = "categoryId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Publication> publicationList;
}