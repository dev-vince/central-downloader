package dev.vince.maven.manager.bean.cache;

import dev.vince.maven.manager.bean.search.MavenSearchBean;
import lombok.Data;

@Data
public final class SearchCacheEntryBean {
    MavenSearchBean search;
    byte[] base64Data;
}
