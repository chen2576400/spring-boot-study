package com.chenning.common.enumMode;

public enum HandWorkEnum {
    //当前处理状况
    TREATMENTSTATUSA("认领无面单/无面单已认领,转出"), TREATMENTSTATUSA1("认领无面单/无面单未找到"),
    TREATMENTSTATUSB("已联系发件人/提供仲裁材料"), TREATMENTSTATUSB1("已联系发件人/多次联系发件人一直未提供资料"),
    TREATMENTSTATUSE("已仲裁/遗失件已上报仲裁"), TREATMENTSTATUSE1("已仲裁/破损件已上报仲裁"),
    TREATMENTSTATUSE2("遗失仲裁已下款"), TREATMENTSTATUSE3("已仲裁/遗失仲裁未下款"),
    TREATMENTSTATUSE4("已仲裁/破损仲裁已下款"), TREATMENTSTATUSE5("已仲裁/破损仲裁未下款"),
    TREATMENTSTATUSE6("已仲裁/已私了"), TREATMENTSTATUSF("已拨打收件人电话/已上传录音（遗失）"),
    TREATMENTSTATUSF1("已拨打收件人电话/已跟收件人核实破损、短少情况"), TREATMENTSTATUSF2("已拨打收件人电话/已核实收件人称已收到"),
    TREATMENTSTATUSG("已拨打网点电话/拨打网点电话核实遗失情况"), TREATMENTSTATUSG1("已拨打网点电话/拨打网点电话核实破损、短少情况"),
    TREATMENTSTATUSH("已退回/此件核实已退回"), TREATMENTSTATUSJ("拒收/已拨打收件人电话收件人核实破损、短少情况"),
    TREATMENTSTATUSK("其他/其他"), TREATMENTSTATUSL("退回/改地址/延期"),

    //当前处理状态
    HANDWORKSTATUSA("已完结"), HANDWORKSTATUSB("未完结"), HANDWORKSTATUSC("人工完结");

    String description;

    HandWorkEnum(String description) {
        this.description = description;
    }


    /**
     * 
     * @param handWorkEnum
     * @return
     */
    public static String getdescription001(HandWorkEnum handWorkEnum) {
        switch (handWorkEnum) {
            case TREATMENTSTATUSA:

            case HANDWORKSTATUSB:

            case TREATMENTSTATUSB:

            case TREATMENTSTATUSF1:
                return handWorkEnum.description;

            default:
                return "未匹配";
        }

    }


    public static String getdescription(int key) {
        switch (key) {
            case 1:
                return HANDWORKSTATUSA.description;
            case 0:
                return HANDWORKSTATUSB.description;
            case 10:
                return HANDWORKSTATUSC.description;
            case 11:
                return TREATMENTSTATUSA.description;
            case 12:
                return TREATMENTSTATUSA1.description;
            case 21:
                return TREATMENTSTATUSB.description;
            case 22:
                return TREATMENTSTATUSB1.description;
            case 31:
                return TREATMENTSTATUSE.description;
            case 32:
                return TREATMENTSTATUSE1.description;
            case 33:
                return TREATMENTSTATUSE2.description;
            case 34:
                return TREATMENTSTATUSE3.description;
            case 35:
                return TREATMENTSTATUSE4.description;
            case 36:
                return TREATMENTSTATUSE5.description;
            case 37:
                return TREATMENTSTATUSE6.description;
            case 41:
                return TREATMENTSTATUSF.description;
            case 42:
                return TREATMENTSTATUSF1.description;
            case 43:
                return TREATMENTSTATUSF2.description;
            case 51:
                return TREATMENTSTATUSG.description;
            case 52:
                return TREATMENTSTATUSG1.description;
            case 61:
                return TREATMENTSTATUSH.description;
            case 71:
                return TREATMENTSTATUSJ.description;
            case 81:
                return TREATMENTSTATUSK.description;
            case 91:
                return TREATMENTSTATUSL.description;
        }
        return "未匹配";
    }


}
