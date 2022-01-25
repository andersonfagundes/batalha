package br.edu.infnet.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attack")
public class Atack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer id_hero;
    private Integer id_monster;
    private Integer pdvHero;
    private Integer pdvMonster;
    private Date date_time;
}
