package br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.entity;

import jakarta.persistence.*;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.time.LocalDateTime;

@Entity
@RevisionEntity
@Table(name = "revinfo")
public class RevInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    @Column(name = "rev")
    private long rev;

    @RevisionTimestamp
    @Column(name = "revtstmp")
    private LocalDateTime revtstmp;
}
