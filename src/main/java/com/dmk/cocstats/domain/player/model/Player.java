package com.dmk.cocstats.domain.player.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Player {

    private String tag;

    private String name;

    private Integer townHallLevel;

    private Integer townHallWeaponLevel;

    private Integer expLevel;

    private Integer trophies;

    private Integer bestTrophies;

    private Integer warStars;

    private Integer attackWins;

    private Integer defenseWins;

    private Integer builderHallLevel;

    private Integer builderBaseTrophies;

    private Integer bestBuilderBaseTrophies;

    private String role;

    private String warPreference; // 전쟁 선호도

    private Integer donations; // 지원한 유닛

    private Integer donationsReceived; // 지원 받은 유닛

    private Integer clanCapitalContributions; // 클랜 캐피탈 기여도

    private Clan clan;

    private BuilderBaseLeague builderBaseLeague;

    private List<Labels> labels;
    private List<Troops> troops;
    private List<Heroes> heroes;
    private List<Spells> spells;
}
