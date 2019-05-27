package top.ks.client.provider.database.mapper;

import top.ks.client.provider.database.model.LolHero;

public interface LolHeroMapper {
    int deleteByPrimaryKey(String heroId);

    int insert(LolHero record);

    int insertSelective(LolHero record);

    LolHero selectByPrimaryKey(String heroId);

    int updateByPrimaryKeySelective(LolHero record);

    int updateByPrimaryKey(LolHero record);

    LolHero selectByHeroEngName(String heroEngName);
}