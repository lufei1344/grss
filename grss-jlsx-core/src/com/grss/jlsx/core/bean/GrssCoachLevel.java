package com.grss.jlsx.core.bean;

public class GrssCoachLevel extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6247183125757309410L;

	private Integer id;

    private Integer min;

    private Integer max;

    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}