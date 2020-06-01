package cn.wangz.antlr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang_zh
 * @date 2020/4/14
 */
public class SqlContext {

    @Getter
    private List<Table> sourceTables = new ArrayList<>();

    @Getter
    private List<Table> targetTables = new ArrayList<>();

    public void addSource(String database, String table) {
        sourceTables.add(new Table(database, table));
    }

    public void addTarget(String database, String table) {
        targetTables.add(new Table(database, table));
    }

    @Data
    @AllArgsConstructor
    public static class Table {
        private String database;
        private String table;
    }

}
