package com.grss.jlsx.core.bean;

public class GrssUserRank extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3027192487638808773L;

	private Integer rankId;

    private Integer minLevel;

    private Integer maxLevel;

    private Integer series;

    private String rankName;

    private String rankImage;

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName == null ? null : rankName.trim();
    }

    public String getRankImage() {
        return rankImage;
    }

    public void setRankImage(String rankImage) {
        this.rankImage = rankImage == null ? null : rankImage.trim();
    }
}