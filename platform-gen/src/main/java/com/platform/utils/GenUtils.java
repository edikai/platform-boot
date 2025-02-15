package com.platform.utils;

import com.platform.entity.ColumnEntity;
import com.platform.entity.TableEntity;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器工具类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年1月3日 下午6:35:28
 */
@Component
public class GenUtils {

    private static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("template/Entity.java.vm");
        templates.add("template/Dao.java.vm");
        templates.add("template/Dao.xml.vm");
        templates.add("template/Service.java.vm");
        templates.add("template/ServiceImpl.java.vm");
        templates.add("template/Controller.java.vm");
        templates.add("template/list.html.vm");
        templates.add("template/list.js.vm");
        templates.add("template/menu.sql.vm");
        return templates;
    }

    /**
     * 生成代码
     */
    public static void generatorCode(Map<String, String> table,
                                     List<Map<String, String>> columns, ZipOutputStream zip) {
        //配置信息
        Configuration config = getConfig();
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));
        String tablePrefix = table.get("tableName").split("_")[0];
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), tablePrefix);
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));
        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        boolean hasDate = false;
        boolean hasBigDecimal = false;
        for (Map<String, String> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName"));
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setComments(column.get("columnComment"));
            columnEntity.setExtra(column.get("extra"));
            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));
            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), "String");
            columnEntity.setAttrType(attrType);
            if ("Date".equals(attrType)) {
                hasDate = true;
            }
            if ("BigDecimal".equals(attrType)) {
                hasBigDecimal = true;
            }
            //是否主键
            if ("ORACLE".equals(Constant.USE_DATA)) {
                if ((column.get("columnName").equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null)) {
                    tableEntity.setPk(columnEntity);
                }
            } else {
                if (("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null)) {
                    tableEntity.setPk(columnEntity);
                }
            }
            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);
        //若没主键
        if (tableEntity.getPk() == null) {
            //设置columnName为id的为主键
            boolean flag = true;
            for (ColumnEntity columnEntity : tableEntity.getColumns()) {
                if ("id".equals(columnEntity.getAttrname())) {
                    tableEntity.setPk(columnEntity);
                    flag = false;
                    break;
                }
            }
            //若无id字段则第一个字段为主键
            if (flag) {
                tableEntity.setPk(tableEntity.getColumns().get(0));
            }
        }
        String pre = tablePrefix.replace("_", "").toLowerCase();
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("package", config.getString("package"));
        map.put("author", config.getString("author"));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        map.put("hasDate", hasDate);
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("pre", pre);
        VelocityContext context = new VelocityContext(map);
        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getString("package"), pre)));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RRException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }
    }

    /**
     * 列名转换成Java属性名
     */
    private static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    private static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RRException("获取配置文件失败，", e);
        }
    }

    /**
     * 获取文件名
     */
    private static String getFileName(String template, String className, String packageName, String tablePrefix) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }
        if (template.contains("Entity.java.vm")) {
            return packagePath + "entity" + File.separator + className + "Entity.java";
        }
        if (template.contains("Dao.java.vm")) {
            return packagePath + "dao" + File.separator + className + "Dao.java";
        }
        if (template.contains("Dao.xml.vm")) {
            return packagePath + "dao" + File.separator + className + "Dao.xml";
        }
        if (template.contains("Service.java.vm")) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }
        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }
        if (template.contains("Controller.java.vm")) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }
        if (template.contains("list.html.vm")) {
            return "main" + File.separator + "webapp" + File.separator + "WEB-INF" + File.separator + "page"
                    + File.separator + tablePrefix + File.separator + className.toLowerCase() + ".html";
        }
        if (template.contains("list.js.vm")) {
            return "main" + File.separator + "webapp" + File.separator + "js" + File.separator + tablePrefix + File.separator + className.toLowerCase() + ".js";
        }
        if (template.contains("menu.sql.vm")) {
            return className.toLowerCase() + "_menu.sql";
        }
        return null;
    }
}
