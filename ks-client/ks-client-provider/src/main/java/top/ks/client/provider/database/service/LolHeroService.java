package top.ks.client.provider.database.service;

import top.ks.client.provider.database.model.LolHero;
import top.ks.client.provider.database.model.LolHeroAbility;

import java.util.List;

public interface LolHeroService {
    int insertHero(LolHero lolHero, List<LolHeroAbility> lolHeroAbilities);
}
