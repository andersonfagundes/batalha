package br.edu.infnet.model;

import lombok.*;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.text.DateFormat;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "start")
public class Start {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer id_hero;
    private Integer id_monster;
    private String firstPlayer;
    private Date date_time;
}
