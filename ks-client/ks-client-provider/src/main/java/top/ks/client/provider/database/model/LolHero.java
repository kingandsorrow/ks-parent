package top.ks.client.provider.database.model;

import java.math.BigDecimal;
import java.util.Date;

public class LolHero {
    private String heroId;

    private String heroName;

    private String heroShortName;

    private String heroEngName;

    private Byte status;

    private String heroPosition;

    private String heroSmallImg;

    private String heroBigImg;

    private Byte damageType;

    private String heroAttribute;

    private BigDecimal viabilityValue;

    private BigDecimal physicalAttack;

    private BigDecimal magicAttack;

    private BigDecimal operationDifficulty;

    private String heroBackStory;

    private String heroDesc;

    private Date createTime;

    private Date updateTime;

    public String getHeroId() {
        return heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId == null ? null : heroId.trim();
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName == null ? null : heroName.trim();
    }

    public String getHeroShortName() {
        return heroShortName;
    }

    public void setHeroShortName(String heroShortName) {
        this.heroShortName = heroShortName == null ? null : heroShortName.trim();
    }

    public String getHeroEngName() {
        return heroEngName;
    }

    public void setHeroEngName(String heroEngName) {
        this.heroEngName = heroEngName == null ? null : heroEngName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getHeroPosition() {
        return heroPosition;
    }

    public void setHeroPosition(String heroPosition) {
        this.heroPosition = heroPosition == null ? null : heroPosition.trim();
    }

    public String getHeroSmallImg() {
        return heroSmallImg;
    }

    public void setHeroSmallImg(String heroSmallImg) {
        this.heroSmallImg = heroSmallImg == null ? null : heroSmallImg.trim();
    }

    public String getHeroBigImg() {
        return heroBigImg;
    }

    public void setHeroBigImg(String heroBigImg) {
        this.heroBigImg = heroBigImg == null ? null : heroBigImg.trim();
    }

    public Byte getDamageType() {
        return damageType;
    }

    public void setDamageType(Byte damageType) {
        this.damageType = damageType;
    }

    public String getHeroAttribute() {
        return heroAttribute;
    }

    public void setHeroAttribute(String heroAttribute) {
        this.heroAttribute = heroAttribute == null ? null : heroAttribute.trim();
    }

    public BigDecimal getViabilityValue() {
        return viabilityValue;
    }

    public void setViabilityValue(BigDecimal viabilityValue) {
        this.viabilityValue = viabilityValue;
    }

    public BigDecimal getPhysicalAttack() {
        return physicalAttack;
    }

    public void setPhysicalAttack(BigDecimal physicalAttack) {
        this.physicalAttack = physicalAttack;
    }

    public BigDecimal getMagicAttack() {
        return magicAttack;
    }

    public void setMagicAttack(BigDecimal magicAttack) {
        this.magicAttack = magicAttack;
    }

    public BigDecimal getOperationDifficulty() {
        return operationDifficulty;
    }

    public void setOperationDifficulty(BigDecimal operationDifficulty) {
        this.operationDifficulty = operationDifficulty;
    }

    public String getHeroBackStory() {
        return heroBackStory;
    }

    public void setHeroBackStory(String heroBackStory) {
        this.heroBackStory = heroBackStory == null ? null : heroBackStory.trim();
    }

    public String getHeroDesc() {
        return heroDesc;
    }

    public void setHeroDesc(String heroDesc) {
        this.heroDesc = heroDesc == null ? null : heroDesc.trim();
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