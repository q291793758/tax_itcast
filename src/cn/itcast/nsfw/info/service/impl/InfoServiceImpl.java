package cn.itcast.nsfw.info.service.impl;

import cn.itcast.core.service.impl.BaseServiceImpl;
import cn.itcast.nsfw.info.dao.InfoDao;
import cn.itcast.nsfw.info.entity.Info;
import cn.itcast.nsfw.info.service.InfoService;

public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService  {

    private InfoDao infoDao;



/*=======================get&set==============================*/
    public InfoDao getInfoDao() {
        return infoDao;
    }

    public void setInfoDao(InfoDao infoDao) {
        super.setBaseDao(infoDao);
        this.infoDao = infoDao;
    }

}
