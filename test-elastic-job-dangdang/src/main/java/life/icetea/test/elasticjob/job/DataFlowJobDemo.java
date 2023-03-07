package life.icetea.test.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.List;
import java.util.Map;

/**
 * 流式类型job demo：
 * 流式处理数据只有fetchData方法的返回值为null或集合长度为空时，作业才停止抓取，否则作业将一直运行下去；
 *
 * @author icetea
 */
public class DataFlowJobDemo implements DataflowJob<Map> {


    @Override
    public List<Map> fetchData(ShardingContext shardingContext) {
        List<Map> list = null;
        return list;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Map> list) {
        for (Map sysMenu : list) {
            System.out.println("*******************输出SysMenu id为：" + sysMenu.get("id") + "********************");
        }
    }

}