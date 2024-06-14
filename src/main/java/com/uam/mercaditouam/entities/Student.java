package com.uam.mercaditouam.entities;

import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="Estudiante")
@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends User{
    @Column(name = "Telefono")
    private String phoneNumber;

    @Column(name = "Descripcion_Personal")
    private String personalDescription;

    @ManyToMany
    @JoinTable(
            name = "SeguimientoEstudiante",
            joinColumns = @JoinColumn(name = "ID_Seguidor"),
            inverseJoinColumns = @JoinColumn(name = "ID_Seguido")
    )
    private Set<Student> following;

    @ManyToMany(mappedBy = "following")
    private Set<Student> followers;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Publication> publicationList;

    @OneToMany(mappedBy = "senderStudent", cascade = CascadeType.ALL)
    private List<Messaging> sentMessages;

    @OneToMany(mappedBy = "recipientStudent", cascade = CascadeType.ALL)
    private List<Messaging> receivedMessages;

    @OneToMany(mappedBy = "requesterStudent", fetch = FetchType.LAZY)
    private List<Ticket> ticketList;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<MainComment> mainCommentList;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Purchase> purchaseList;
}