package com.newcentury.web.mpCode;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MpGenerator {

    public static void main(String[] args) {


        for(int i = 0; i < Type.values().length; i++){

            Type type = Type.getByCode(i);
            if(Properties.onlyDao && type != Type.DAO){
                continue;
            }
            AutoGenerator mpg = new AutoGenerator();
            // 全局配置
            String dir = System.getProperty("user.dir");
            GlobalConfig gc = new GlobalConfig();
            gc.setOutputDir(dir + type.getDesc() + "/src/main/java");
            gc.setFileOverride(Properties.override);
            gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
            gc.setEnableCache(false);// XML 二级缓存
            gc.setBaseResultMap(true);// XML ResultMap
            gc.setBaseColumnList(true);// XML columList
            gc.setDateType(DateType.ONLY_DATE);//设置时间类型
            gc.setAuthor("朱登轩");
            gc.setSwagger2(true);
            gc.setEntityName("%sDao");
            mpg.setGlobalConfig(gc);

            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setDbType(DbType.MYSQL);
            dsc.setTypeConvert(new MySqlTypeConvert(){
                // 自定义数据库表字段类型转换【可选】
                @Override
                public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                    System.out.println("转换类型：" + fieldType);
                    // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                    return super.processTypeConvert(gc,fieldType);
                }
            });
            dsc.setDriverName(Properties.driverClass);
            dsc.setUsername(Properties.userName);
            dsc.setPassword(Properties.password);
            dsc.setUrl(Properties.jdbc);
            mpg.setDataSource(dsc);

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
            strategy.setTablePrefix(new String[] { "t_"});//"sys_",,"qrtz_"
            strategy.setInclude(Properties.tableName); // 需要生成的表
            strategy.setEntityColumnConstant(true);
            strategy.setRestControllerStyle(true);
            strategy.setEntityLombokModel(true);
            strategy.setEntityColumnConstant(false);
//            strategy.setLogicDeleteFieldName("is_delete");


            mpg.setStrategy(strategy);
            // 包配置
            PackageConfig pc = new PackageConfig();
            pc.setParent("com.newCentury.web");
            pc.setEntity("dao."+ Properties.dir);
            pc.setMapper("mapper."+ Properties.dir);
            pc.setService("service." + Properties.dir);
            pc.setServiceImpl("serviceImpl." + Properties.dir);
            pc.setController("controller." + Properties.dir);
            mpg.setPackageInfo(pc);
            // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】  ${cfg.abc}
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    this.setMap(new HashMap<>());
                }
            };
            // 自定义模板配置
            String templatePath = "/templates/mapper.xml.vm";
            if(i == 0) {
                List<FileOutConfig> focList = new ArrayList<>();
                focList.add(new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        //自定义输出文件的位置
                        return dir + "/" + Type.DAO.getDesc() + "/src/main/resources/mapper/" + Properties.dir + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                    }
                });
                cfg.setFileOutConfigList(focList);
            }
            // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
            TemplateConfig tc = new TemplateConfig();
            tc.setXml(null);
            if(i == 0){
                tc.setEntity("/template/entity.java.vm");
                tc.setMapper("/templates/mapper.java.vm");
                tc.setController(null);
                tc.setService(null);
                tc.setServiceImpl(null);
            }else if(i == 1){
                tc.setEntity(null);
                tc.setMapper(null);
                tc.setController(null);
                tc.setService("/template/service.java.vm");
                tc.setServiceImpl("/templates/serviceImpl.java.vm");
            }else {
                tc.setEntity(null);
                tc.setMapper(null);
                tc.setController("/template/controller.java.vm");
                tc.setService(null);
                tc.setServiceImpl(null);
            }

            // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
            mpg.setCfg(cfg);
            mpg.setTemplate(tc);
            // 执行生成
            mpg.execute();

            System.out.println("生成完毕");
        }

    }

    private enum Type{
        DAO(0,"dao","/web"),
        SERVICE(1,"service","/web"),
        WEB(2,"web","/web");

        private Integer code;
        private String value;
        private String desc;

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

        Type(Integer code,String value, String desc){
            this.code = code;
            this.value = value;
            this.desc = desc;
        }
        static Type getByCode(Integer code){
            for(Type type : Type.values()){
                if(type.getCode().equals(code)){
                    return type;
                }
            }
            return null;
        }

    }
}