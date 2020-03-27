package com.newcentury.web.mpCode;

public class Properties {

    /**
     * 是否只生成dal中的文件
     */
    public static final boolean onlyDao = false;
    /**
     *  生成的文件目录  一次运行只能生成同一目录下的文件
     */

    public static final String dir = "system";
//    public static final String[] tableName ={"sys_dept","sys_user","sys_log","sys_menu","sys_role","sys_role_menu","sys_user_role"};
    public static final String[] tableName ={"t_user","t_role","t_user_role","t_authority","t_role_authority","t_company"};

//    public static final String dir = "basic";
    //public static final String[] tableName ={"t_material","t_bom","t_ipc","t_factory","t_workshop","t_calendar","t_procedure","t_techline","t_techline_procedure","t_techline_procedure_device","t_device_type","t_device","t_device_group","t_capacity","t_device_param"};

    //public static final String dir = "config";
    //public static final String[] tableName ={"t_config_plan"};

//    public static final String dir = "abnormal";
//    public static final String[] tableName ={"t_device_ipc"};

//    public static final String dir = "product";
//    public static final String[] tableName ={"t_material_fail"};

    //public static final String dir = "mould";
   // public static final String[] tableName ={"t_mould","t_mould_material","t_mould_his","t_mould_material","t_mould_expend","t_mould_warehouse"};

    //public static final String dir = "abnormal";
    //public static final String[] tableName ={"t_abnormal_type","t_abnormal","t_abnormal_order"};
//
//    public static final String dir = "warehouse";
//    public static final String[] tableName= {"t_stock","t_stock_his","t_stock_detail"};

//      public static final String dir = "spare";
//      public static final String[] tableName= {"t_spare","t_spare_record"};

    //public static final String dir = "frame";
    //public static final String[] tableName= {"t_frame"};
//    public static final String dir = "frame";
//    public static final String[] tableName= {"t_frame"};

    //        "t_spare","t_spare_record"
//      public static final String dir = "spare";
//      public static final String[] tableName= {"t_spare_type"};

    public static final String driverClass = "com.mysql.cj.jdbc.Driver";

    /**
     * 重新生成的时候是否进行覆盖
     */
    public static final Boolean override = true;

    public static final String userName = "root";

    public static final String password = "password";

    public static final String jdbc = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&useSSL=false&useUnicode=true&serverTimezone=UTC";

}
