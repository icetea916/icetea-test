package life.icetea.test.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Table 双键Map, 可以存放两个key值（rowKey, columnKey）映射一个value
 *
 * @author icetea
 * @date 2023/12/29
 */
public class TableTest {

    public static void main(String[] args) {
        // 记录员工每人每月的工资
        HashBasedTable<String, String, Long> table = HashBasedTable.create();

        // 存放元素
        table.put("张三", "2023-01", 10000L);
        table.put("张三", "2023-02", 10000L);
        table.put("李四", "2023-01", 12000L);
        table.put("王五", "2023-02", 13000L);

        // 取出元素
        Long dayCount = table.get("张三", "2023-01");
        System.out.println(dayCount);
        // 只获取张三的记录,及某row的记录
        Map<String, Long> row = table.row("张三");
        System.out.println("张三:" + row);
        // 获取2023-02月分的记录，即某列的记录
        Map<String, Long> column = table.column("2023-02");
        System.out.println("2023-02:" + column);

        // 获取rowKey和columnKey集合
        Set<String> rowKeySet = table.rowKeySet();
        System.out.println(rowKeySet);
        Set<String> columnKeySet = table.columnKeySet();
        System.out.println(columnKeySet);

        // 获取value集合
        Collection<Long> values = table.values();
        System.out.println(values);

        // 行列转换
        Table<String, String, Long> transpose = Tables.transpose(table);
        System.out.println(table);
        System.out.println(transpose);

    }


}
