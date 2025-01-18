package org.jeecg.systemlog.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SystemLogMapper extends ElasticsearchRepository<SystemLog, String> {
    /**
     * 根据内容查询日志
     *
     * @param message
     * @return
     */
    List<SystemLog> findByMessage(String message);
}
