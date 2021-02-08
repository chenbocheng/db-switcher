package com.expert.demo.dbswitcher.config.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    /**
     * Find current context DS key. It will be used by AbstractRoutingDataSource.
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String dsKey = DataSourceKeyHolder.getKey();
        log.debug("The current DS: {}", dsKey);
        return dsKey;
    }
}
