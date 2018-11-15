package com.xzpx_zc.pojo;

import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;






@XmlRootElement(name="nav")
@XStreamAlias("zc")
public class ZcNav {
    private Integer navId;

    private Integer navUserid;

    private String navName;

    private Integer navParentid;

    private Integer navStatis;

    private String navEn;
    
    public String getNavEn() {
		return navEn;
	}

	public void setNavEn(String navEn) {
		this.navEn = navEn;
	}
	public Integer getNavId() {
        return navId;
    }

    public void setNavId(Integer navId) {
        this.navId = navId;
    }

    public Integer getNavUserid() {
        return navUserid;
    }

    public void setNavUserid(Integer navUserid) {
        this.navUserid = navUserid;
    }
    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName == null ? null : navName.trim();
    }

    public Integer getNavParentid() {
        return navParentid;
    }

    public void setNavParentid(Integer navParentid) {
        this.navParentid = navParentid;
    }

	public Integer getNavStatis() {
		return navStatis;
	}

	public void setNavStatis(Integer navStatis) {
		this.navStatis = navStatis;
	}
    
    
}