package br.edu.infnet.model;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attack")
public class Attack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer id_hero;
    private Integer id_monster;
    private Integer pdvHero;
    private Integer pdvMonster;
    private Date date_time;
    
    @ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_battle")
	private Battle battle;
}
