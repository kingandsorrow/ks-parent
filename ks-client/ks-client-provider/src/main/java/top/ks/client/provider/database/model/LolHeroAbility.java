package top.ks.client.provider.database.model;

import java.util.Date;

public class LolHeroAbility {
    private String abilityId;

    private String heroId;

    private String abilityName;

    private String abilityHotKey;

    private String abilityImg;

    private String abilityDesc;

    private Byte consumeType;

    private Date createTime;

    private Date updateTime;

    public String getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(String abilityId) {
        this.abilityId = abilityId == null ? null : abilityId.trim();
    }

    public String getHeroId() {
        return heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId == null ? null : heroId.trim();
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName == null ? null : abilityName.trim();
    }

    public String getAbilityHotKey() {
        return abilityHotKey;
    }

    public void setAbilityHotKey(String abilityHotKey) {
        this.abilityHotKey = abilityHotKey == null ? null : abilityHotKey.trim();
    }

    public String getAbilityImg() {
        return abilityImg;
    }

    public void setAbilityImg(String abilityImg) {
        this.abilityImg = abilityImg == null ? null : abilityImg.trim();
    }

    public String getAbilityDesc() {
        return abilityDesc;
    }

    public void setAbilityDesc(String abilityDesc) {
        this.abilityDesc = abilityDesc == null ? null : abilityDesc.trim();
    }

    public Byte getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(Byte consumeType) {
        this.consumeType = consumeType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}