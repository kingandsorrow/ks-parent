package top.ks.client.provider.database.mapper;

import top.ks.client.provider.database.model.LolHeroAbility;

public interface LolHeroAbilityMapper {
    int deleteByPrimaryKey(String abilityId);

    int insert(LolHeroAbility record);

    int insertSelective(LolHeroAbility record);

    LolHeroAbility selectByPrimaryKey(String abilityId);

    int updateByPrimaryKeySelective(LolHeroAbility record);

    int updateByPrimaryKey(LolHeroAbility record);
}