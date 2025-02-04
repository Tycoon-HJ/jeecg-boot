package org.jeecg.systemlog.controller;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.jeecg.systemlog.dao.Message;
import org.jeecg.systemlog.dao.SystemLog;
import org.jeecg.systemlog.dao.SystemLogMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SysTemLogController {
    @Resource
    private SystemLogMapper systemLogMapper;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 全量查询日志信息和根据关键字查询信息
     */
    @GetMapping("/axx")
    public void xxx() {
        List<SystemLog> one = systemLogMapper.findByMessage("检测如果是JAR启动环");
        one.forEach(System.out::println);
        ObjectMapper mapper = new ObjectMapper();
        systemLogMapper.findAll().forEach(e -> {
            try {
                Message message = mapper.readValue(e.getMessage(), Message.class);
                System.out.println(e.getTimestamp() + "||" + message.getThreadName() + "||" + message.getLoggerName() + "||" + message.getLevel() + "||" + message.getMessage());
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    /**
     * 该接口按照时间顺序倒叙，返回最近20条日志信息
     */
    @GetMapping("/pageLogInfo/{value}/{pageNumber}/{pageSize}")
    public List<String> pageLogInfo(@PathVariable String value, @PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        List<String> matchMessageList = new ArrayList<>();
        // 只查20条日志信息，默认是按时间戳倒序
        Query searchQuery = new NativeQueryBuilder()
                // 根据内容在message字段中查询数据
                .withQuery(QueryBuilders.match(e -> e.query(value).field("message")))
                // 分页查询
                .withPageable(PageRequest.of(pageNumber, pageSize))
                // 按照时间戳进行倒序
                .withSort(Sort.by("@timestamp").descending())
                .build();
        elasticsearchTemplate.search(searchQuery, SystemLog.class).forEach(e -> matchMessageList.add(e.getContent().getMessage()));
        return matchMessageList;
    }
}
