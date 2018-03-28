package com.wetime.fanc.circle.bean;

import java.util.List;

public class SelectLocListBean {


    /**
     * error : 0
     * msg :
     * data : {"list":[{"title":"酱紫沙拉店(京华城店)","address":"海南省海口市龙华区京华城玉沙路23号金城国际架空层4号门市的商铺(宝丽餐厅楼下)","lng":"110.319553","lat":"20.028709"},{"title":"Jin+","address":"海南省海口市龙华区玉沙路金城国际A座26A(宝丽餐厅内圈写字楼上)","lng":"110.319515","lat":"20.028740"},{"title":"美肌工坊皮肤管理(国贸京华城店)","address":"海南省海口市龙华区玉沙路金城国际大厦A栋20C","lng":"110.319454","lat":"20.028665"},{"title":"华为客户服务中心(海口京华城店)","address":"海南省海口市龙华区玉沙路23号金城国际4楼","lng":"110.319576","lat":"20.028765"},{"title":"鱼亭创作料理(京华城店)","address":"海南省海口市龙华区玉沙路西侧海岸金城1层12号(宝丽餐厅1层电梯口)","lng":"110.319505","lat":"20.028739"},{"title":"宝丽餐厅(京华城店)","address":"海南省海口市龙华区玉沙路30号附近玉沙京华城","lng":"110.319505","lat":"20.028745"},{"title":"拾米屋","address":"海南省海口市龙华区玉沙路23号海岸金城项目一层内2号铺面","lng":"110.319580","lat":"20.028450"},{"title":"映时尚美甲美睫","address":"海南省海口市龙华区玉沙路金城国际B座9D(京华城)","lng":"110.319552","lat":"20.028788"},{"title":"c.h.suvin半永久工作室","address":"海南省海口市龙华区国贸玉沙路京华城金城国际b座32e(宝丽餐厅)","lng":"110.319786","lat":"20.028636"},{"title":"瑞丽造型","address":"海南省海口市龙华区玉沙路京华城金城国际B座31楼B室(京华城宝丽餐厅背后)","lng":"110.319502","lat":"20.028792"},{"title":"JM SKIN韩国皮肤管理中心","address":"海南省海口市龙华区玉沙路21号金城国际B栋31E","lng":"110.319810","lat":"20.028670"},{"title":"奈希美甲美睫沙龙","address":"海南省海口市龙华区玉沙路金城国际B座11楼11D室(京华城)","lng":"110.319815","lat":"20.028672"},{"title":"青婧寒舍美妆会所","address":"海南省海口市龙华区金城国际b座15c","lng":"110.319815","lat":"20.028672"},{"title":"芭比先生(玉沙路店)","address":"海南省海口市龙华区玉沙路23号海岸金城B座7B房","lng":"110.319815","lat":"20.028672"},{"title":"慕儿婚纱礼服工作室","address":"海南省海口市龙华区玉沙路金城国际b座20b","lng":"110.319815","lat":"20.028672"},{"title":"熙媛体验馆海南运营中心(金城国际)","address":"海南省海口市龙华区京华城金城国际A座12B","lng":"110.319425","lat":"20.028795"},{"title":"伊沫婚纱","address":"海南省海口市龙华区玉沙京华城金城国际B座8楼","lng":"110.319618","lat":"20.028896"},{"title":"BMIMⅠ工作室","address":"海南省海口市龙华区国贸京华城金城国际B座6","lng":"110.319618","lat":"20.028896"},{"title":"捞友记猪肚鸡火锅(京华城店)","address":"海南省海口市龙华区玉沙路金城国际3层6号","lng":"110.319422","lat":"20.028850"},{"title":"艺匠主题造型工作室","address":"海南省海口市龙华区国贸玉沙路金城国际A座11B房","lng":"110.319530","lat":"20.028920"}]}
     */

    private int error;
    private String msg;
    private DataBean data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<LocItemBean> list;

        public List<LocItemBean> getList() {
            return list;
        }

        public void setList(List<LocItemBean> list) {
            this.list = list;
        }
    }
}
