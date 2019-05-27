package top.ks.client.provider.database.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ks.client.provider.database.mapper.LolHeroAbilityMapper;
import top.ks.client.provider.database.mapper.LolHeroMapper;
import top.ks.client.provider.database.model.LolHero;
import top.ks.client.provider.database.model.LolHeroAbility;
import top.ks.client.provider.database.service.LolHeroService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LolHeroServiceImpl implements LolHeroService {
    @Resource
    private LolHeroMapper lolHeroMapper;

    @Resource
    private LolHeroAbilityMapper lolHeroAbilityMapper;

    @Override
    public int insertHero(LolHero lolHero, List<LolHeroAbility> lolHeroAbilities) {
        int row1 = lolHeroMapper.insertSelective(lolHero);
        if (lolHeroAbilities != null && lolHeroAbilities.size() > 0) {
            for (LolHeroAbility lolHeroAbility : lolHeroAbilities) {
                int row2 = lolHeroAbilityMapper.insertSelective(lolHeroAbility);
            }
        }
        return row1;
    }
}
